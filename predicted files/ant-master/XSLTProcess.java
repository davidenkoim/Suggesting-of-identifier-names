// Type of training: selfTestingIdentifier
// Path to file: C:\Users\Igor\IdeaProjects\SLP-Core-0.3\java-projects\ant-master\src\main\org\apache\tools\ant\taskdefs\XSLTProcess.java
// Number of identifiers: 122	Accuracy: 44.26%	MRR: 53.38%
// True 	Rank of true in predictions : [(predicted token, probability of a token), ...]

/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
package org.apache.tools.ant.taskdefs;

import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import javax.xml.namespace.QName;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.apache.tools.ant.AntClassLoader;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.DynamicConfigurator;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectComponent;
import org.apache.tools.ant.PropertyHelper;
import org.apache.tools.ant.taskdefs.optional.TraXLiaison;
import org.apache.tools.ant.types.CommandlineJava;
import org.apache.tools.ant.types.Environment;
import org.apache.tools.ant.types.Mapper;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.types.PropertySet;
import org.apache.tools.ant.types.Reference;
import org.apache.tools.ant.types.Resource;
import org.apache.tools.ant.types.ResourceCollection;
import org.apache.tools.ant.types.XMLCatalog;
import org.apache.tools.ant.types.resources.FileProvider;
import org.apache.tools.ant.types.resources.FileResource;
import org.apache.tools.ant.types.resources.Resources;
import org.apache.tools.ant.types.resources.Union;
import org.apache.tools.ant.util.ClasspathUtils;
import org.apache.tools.ant.util.FileNameMapper;
import org.apache.tools.ant.util.FileUtils;
import org.apache.tools.ant.util.ResourceUtils;
import org.apache.tools.ant.util.StringUtils;

/**
 * Processes a set of XML documents via XSLT. This is
 * useful for building views of XML based documentation.
 *
 * @since Ant 1.1
 *
 * @ant.task name="xslt" category="xml"
 */
public class XSLTProcess extends MatchingTask implements XSLTLogger {

    /**
     * The default processor is trax
     * @since Ant 1.7
     */
    public static final String PROCESSOR_TRAX = "trax";

    /**
     * Utilities used for file operations
     */
    private static final FileUtils FILE_UTILS = FileUtils.getFileUtils();

    /**
     * destination directory
     */
    private File destDir = null;

    /**
     * where to find the source XML file, default is the project's basedir
     */
    private File baseDir = null;

    /**
     * XSL stylesheet as a filename
     */
    private String xslFile = null;

    /**
     * XSL stylesheet as a {@link org.apache.tools.ant.types.Resource}
     */
    private Resource xslResource = null;

    /**
     * extension of the files produced by XSL processing
     */
    private String targetExtension = ".html";

    /**
     * name for XSL parameter containing the filename
     */
    private String fileNameParameter = null;

    /**
     * name for XSL parameter containing the file directory
     */
    private String fileDirParameter = null;

    /**
     * additional parameters to be passed to the stylesheets
     */
    private final List<Param> params = new ArrayList<>();

    /**
     * Input XML document to be used
     */
    private File inFile = null;

    /**
     * Output file
     */
    private File outFile = null;

    /**
     * The name of the XSL processor to use
     */
    private String processor;

    /**
     * Classpath to use when trying to load the XSL processor
     */
    private Path classpath = null;

    /**
     * The Liaison implementation to use to communicate with the XSL
     *  processor
     */
    private XSLTLiaison liaison;

    /**
     * Flag which indicates if the stylesheet has been loaded into
     *  the processor
     */
    private boolean stylesheetLoaded = false;

    /**
     * force output of target files even if they already exist
     */
    private boolean force = false;

    /**
     * XSL output properties to be used
     */
    private final List<OutputProperty> outputProperties = new Vector<>();

    /**
     * for resolving entities such as dtds
     */
    private final XMLCatalog xmlCatalog = new XMLCatalog();

    /**
     * Whether to style all files in the included directories as well.
     *
     * @since Ant 1.5
     */
    private boolean performDirectoryScan = true;

    /**
     * factory element for TraX processors only
     * @since Ant 1.6
     */
    private Factory factory = null;

    /**
     * whether to reuse Transformer if transforming multiple files.
     * @since 1.5.2
     */
    private boolean reuseLoadedStylesheet = true;

    /**
     * AntClassLoader for the nested &lt;classpath&gt; - if set.
     *
     * <p>We keep this here in order to reset the context classloader
     * in execute.  We can't use liaison.getClass().getClassLoader()
     * since the actual liaison class may have been loaded by a loader
     * higher up (system classloader, for example).</p>
     *
     * @since Ant 1.6.2
     */
    private AntClassLoader loader = null;

    /**
     * Mapper to use when a set of files gets processed.
     *
     * @since Ant 1.6.2
     */
    private Mapper mapperElement = null;

    /**
     * Additional resource collections to process.
     *
     * @since Ant 1.7
     */
    private final Union resources = new Union();

    /**
     * Whether to use the implicit fileset.
     *
     * @since Ant 1.7
     */
    private boolean useImplicitFileset = true;

    /**
     * whether to suppress warnings.
     *
     * @since Ant 1.8.0
     */
    private boolean suppressWarnings = false;

    /**
     * whether to fail the build if an error occurs during transformation.
     *
     * @since Ant 1.8.0
     */
    private boolean failOnTransformationError = true;

    /**
     * whether to fail the build if an error occurs.
     *
     * @since Ant 1.8.0
     */
    private boolean failOnError = true;

    /**
     * Whether the build should fail if the nested resource collection
     * is empty.
     *
     * @since Ant 1.8.0
     */
    private boolean failOnNoResources = true;

    /**
     * For evaluating template params
     *
     * @since Ant 1.9.3
     */
    private XPathFactory xpathFactory;

    /**
     * For evaluating template params
     *
     * @since Ant 1.9.3
     */
    private XPath xpath;

    /**
     * System properties to set during transformation.
     *
     * @since Ant 1.8.0
     */
    private final CommandlineJava.SysProperties sysProperties = new CommandlineJava.SysProperties();

    /**
     * Trace configuration for Xalan2.
     *
     * @since Ant 1.8.0
     */
    private TraceConfiguration traceConfiguration;

    /**
     * Whether to style all files in the included directories as well;
     * optional, default is true.
     *
     * @param b true if files in included directories are processed.
     * @since Ant 1.5
     */
    public void setScanIncludedDirectories(final boolean b) {
// b                    0	: [('b', 0.16574118497307813), ('verbose', 0.08989094051446246), ('value', 0.019825784806803234), ('include', 0.014295620023115617), ('v', 0.01415671502344134), ('append', 0.013891633243744598), ('f', 0.010938263789962878), ('failOnError', 0.010810944674345508), ('quiet', 0.010693918926724364), ('yesOrNo', 0.010459590951317868)]
        performDirectoryScan = b;
    }

    /**
     * Controls whether the stylesheet is reloaded for every transform.
     *
     * <p>Setting this to true may get around a bug in certain
     * Xalan-J versions, default is false.</p>
     * @param b a <code>boolean</code> value
     * @since Ant 1.5.2
     */
    public void setReloadStylesheet(final boolean b) {
// b                    0	: [('b', 0.16574132321316024), ('verbose', 0.08989094051446246), ('oldCMP', 0.08373880021869595), ('existsAsLowerCase', 0.08373852373853174), ('success', 0.04287850243323361), ('entryName', 0.042088722361881714), ('toDir', 0.04189943350888785), ('is9x', 0.041874273813945005), ('proceed', 0.041873167893288175), ('value', 0.019825784806803234)]
        reuseLoadedStylesheet = !b;
    }

    /**
     * Defines the mapper to map source to destination files.
     * @param mapper the mapper to use
     * @exception BuildException if more than one mapper is defined
     * @since Ant 1.6.2
     */
    public void addMapper(final Mapper mapper) {
// mapper               0	: [('mapper', 0.28004347798922735), ('myMapper', 0.2550105768496867), ('m', 0.06511868059389166), ('m2', 0.010010853329850881), ('m3', 0.010010300369522466), ('me', 0.010009747409194051), ('i', 0.007220869255079404), ('name', 0.00615307471702672), ('b', 0.005880193753638788), ('value', 0.005419652314355413)]
        if (mapperElement != null) {
            handleError("Cannot define more than one mapper");
        } else {
            mapperElement = mapper;
        }
    }

    /**
     * Adds a collection of resources to style in addition to the
     * given file or the implicit fileset.
     *
     * @param rc the collection of resources to style
     * @since Ant 1.7
     */
    public void add(final ResourceCollection rc) {
// rc                   0	: [('rc', 0.34296998991407174), ('res', 0.27056018396165493), ('rs', 0.15064519603022017), ('a', 0.1373214183292125), ('resourceCollection', 0.1358148016930329), ('r', 0.07991687896489492), ('resource', 0.07606842337618049), ('fromFileAttribute', 0.07531726515043646), ('rcol', 0.02034348174418406), ('c', 0.017178180629101415)]
        resources.add(rc);
    }

    /**
     * Add a nested &lt;style&gt; element.
     * @param rc the configured Resources object represented as &lt;style&gt;.
     * @since Ant 1.7
     */
    public void addConfiguredStyle(final Resources rc) {
// rc                   0	: [('rc', 0.37919843065838343), ('resourceCollection', 0.06367738205127464), ('hs', 0.06289338935326659), ('resourcesToDelete', 0.06252115378009641), ('filesetDirs', 0.06251783597409517), ('savedPath', 0.0625172830064283), ('line', 0.009369315642933628), ('file', 0.00917861336882755), ('name', 0.00850774272766269), ('c', 0.007427337499387747)]
        if (rc.size() != 1) {
            handleError("The style element must be specified with exactly one nested resource.");
        } else {
            setXslResource(rc.iterator().next());
        }
    }

    /**
     * API method to set the XSL Resource.
     * @param xslResource Resource to set as the stylesheet.
     * @since Ant 1.7
     */
    public void setXslResource(final Resource xslResource) {
// xslResource          No	: [('r', 0.32286822320251213), ('source', 0.09887702883409384), ('resource', 0.06701425590997327), ('r1', 0.04970035150761896), ('stylesheet', 0.0424842776150223), ('res', 0.03130146032029988), ('dest', 0.027161063550622942), ('sr', 0.024030036824932204), ('r2', 0.013436714258641326), ('dir', 0.012354961149426635)]
        this.xslResource = xslResource;
    }

    /**
     * Adds a nested filenamemapper.
     * @param fileNameMapper the mapper to add
     * @exception BuildException if more than one mapper is defined
     * @since Ant 1.7.0
     */
    public void add(final FileNameMapper fileNameMapper) throws BuildException {
// fileNameMapper       0	: [('fileNameMapper', 0.8949133185399387), ('mapper', 0.057802586045664094), ('rc', 0.0374398204483925), ('name', 0.022373680833439283), ('instr', 0.021560023998022374), ('r', 0.019667515859579734), ('c', 0.018712722516405662), ('i', 0.01386649468983482), ('p', 0.01342948988045593), ('set', 0.012818322477049308)]
        final Mapper mapper = new Mapper(getProject());
// mapper               1	: [('m', 0.8959059189219506), ('mapper', 0.2800432019823938), ('myMapper', 0.25501057689004825), ('exe', 0.04204813296774827), ('ds', 0.038020044643207344), ('myPath', 0.03519794507210621), ('helper', 0.029304209684173813), ('ret', 0.026210045733822334), ('commandLine', 0.024576861996209565), ('fs', 0.023159892053205285)]
        mapper.add(fileNameMapper);
        addMapper(mapper);
    }

    /**
     * Executes the task.
     *
     * @exception BuildException if there is an execution problem.
     * @todo validate that if either in or out is defined, then both are
     */
    @Override
    public void execute() throws BuildException {
        if ("style".equals(getTaskType())) {
            log("Warning: the task name <style> is deprecated. Use <xslt> instead.", Project.MSG_WARN);
        }
        final File savedBaseDir = baseDir;
// savedBaseDir         No	: [('b', 0.27527968076443515), ('target', 0.1660488287683583), ('l', 0.16597434404351322), ('fsDir', 0.16595554339234714), ('d', 0.1584584318232356), ('parent', 0.10807996182946064), ('baseDir', 0.07155268660278934), ('fromDir', 0.04138531978470001), ('f', 0.038681584404146256), ('savedBase', 0.03297810442908399)]
        final String baseMessage = "specify the stylesheet either as a filename in style attribute or as a nested resource";
// baseMessage          No	: [('e', 0.2921257913576821), ('ioe', 0.29174467649794866), ('name', 0.04915127045079073), ('message', 0.04003964081812348), ('output', 0.03283712968066203), ('value', 0.016247060369775895), ('className', 0.012043461808788371), ('filename', 0.011927476546359537), ('toDir', 0.010551975136944692), ('fdir', 0.010540224574023607)]
        if (xslResource == null && xslFile == null) {
            handleError(baseMessage);
            return;
        }
        if (xslResource != null && xslFile != null) {
            handleError(baseMessage + " but not as both");
            return;
        }
        if (inFile != null && !inFile.exists()) {
            handleError("input file " + inFile + " does not exist");
            return;
        }
        try {
            setupLoader();
            if (sysProperties.size() > 0) {
                sysProperties.setSystem();
            }
            Resource styleResource;
// styleResource        2	: [('archive', 0.6070470732362021), ('mapper', 0.2106841746360208), ('styleResource', 0.16669181880569367), ('r', 0.11495578908627421), ('currentResource', 0.10684044423399819), ('m', 0.105444107707957), ('ds', 0.10541863943639226), ('srcFiles', 0.10523534181698213), ('ftp', 0.06802429543124126), ('stylesheet', 0.052614406488970866)]
            if (baseDir == null) {
                baseDir = getProject().getBaseDir();
            }
            liaison = getLiaison();
            // check if liaison wants to log errors using us as logger
            if (liaison instanceof XSLTLoggerAware) {
                ((XSLTLoggerAware) liaison).setLogger(this);
            }
            log("Using " + liaison.getClass().toString(), Project.MSG_VERBOSE);
            if (xslFile != null) {
                // If we enter here, it means that the stylesheet is supplied
                // via style attribute
                File stylesheet = getProject().resolveFile(xslFile);
// stylesheet           0	: [('stylesheet', 0.7601971551355798), ('links', 0.5007904099157293), ('dir', 0.14766982864907008), ('checkDir', 0.14065762697705303), ('f', 0.1117347558927898), ('inclFile', 0.07142671950253242), ('exclFile', 0.07142671950253242), ('outfile', 0.07037396027450551), ('parentFile', 0.07032928446151904), ('antHomeLibDir', 0.07032921533964334)]
                if (!stylesheet.exists()) {
                    final File alternative = FILE_UTILS.resolveFile(baseDir, xslFile);
// alternative          No	: [('stylesheet', 0.5000982774816023), ('resource', 0.2721464477617592), ('endOfBaseName', 0.18138775806646817), ('f', 0.12217343267329275), ('file', 0.09224610195234711), ('classpath', 0.09096979191335981), ('nextSym', 0.09071560161173275), ('suite', 0.09068702670981937), ('dir', 0.07389078569140783), ('destFile', 0.037315818081730755)]
                    /*
                     * shouldn't throw out deprecation warnings before we know,
                     * the wrong version has been used.
                     */
                    if (alternative.exists()) {
                        log("DEPRECATED - the 'style' attribute should be relative to the project's");
                        log("             basedir, not the tasks's basedir.");
                        stylesheet = alternative;
                    }
                }
                final FileResource fr = new FileResource();
// fr                   1	: [('f', 0.37555526635864905), ('fr', 0.3751155939234584), ('r', 0.17166697406126047), ('exe', 0.042048159887975105), ('ds', 0.038020089123926314), ('myPath', 0.03519794750074603), ('helper', 0.02930422036063293), ('ret', 0.026210058936706124), ('commandLine', 0.02457689356757127), ('fs', 0.023159913839322758)]
                fr.setProject(getProject());
                fr.setFile(stylesheet);
                styleResource = fr;
            } else {
                styleResource = xslResource;
            }
            if (!styleResource.isExists()) {
                handleError("stylesheet " + styleResource + " doesn't exist.");
                return;
            }
            // if we have an in file and out then process them
            if (inFile != null && outFile != null) {
                process(inFile, outFile, styleResource);
                return;
            }
            /*
             * if we get here, in and out have not been specified, we are
             * in batch processing mode.
             */
            // -- make sure destination directory exists...
            checkDest();
            if (useImplicitFileset) {
                DirectoryScanner scanner = getDirectoryScanner(baseDir);
// scanner              5	: [('ds', 0.650710560073513), ('dirs', 0.5938489777331529), ('dsc', 0.29169151275105804), ('c', 0.2066426775878386), ('path', 0.20075305788656905), ('scanner', 0.10816300026342357), ('environment', 0.10029514400041889), ('classpath', 0.10028295586445449), ('filesToMerge', 0.10022332790376241), ('filesToAdd', 0.10022332790376241)]
                log("Transforming into " + destDir, Project.MSG_INFO);
                // Process all the files marked for styling
                for (String element : scanner.getIncludedFiles()) {
// element              9	: [('dir', 0.7875033284024073), ('path', 0.13516232694606342), ('driveSpec', 0.13288378353204366), ('driveSpecLower', 0.1328826430362307), ('secJar', 0.13286771883868165), ('file', 0.09700987087174176), ('name', 0.08337933435664274), ('filename', 0.07539758064613053), ('key', 0.06873109260020734), ('element', 0.06646471743958707)]
                    process(baseDir, element, destDir, styleResource);
                }
                if (performDirectoryScan) {
                    // Process all the directories marked for styling
                    for (String dir : scanner.getIncludedDirectories()) {
// dir                  1	: [('element', 0.7599029156036946), ('dir', 0.26981182267790554), ('relativePath', 0.1447154952994452), ('path', 0.13498742697803057), ('sourceFileName', 0.1348622180782414), ('xmlFile', 0.13485694944504403), ('value', 0.05530106601204827), ('name', 0.0552174010474463), ('filename', 0.04160418926238408), ('include', 0.037806775516055706)]
                        for (String element : new File(baseDir, dir).list()) {
// element              6	: [('path', 0.13516232665298367), ('driveSpec', 0.1328837835008552), ('driveSpecLower', 0.13288264302017783), ('secJar', 0.13286771883730567), ('filename', 0.07539758044707479), ('key', 0.0687310924626112), ('element', 0.06646475187072588), ('child', 0.0664606392282833), ('ibmJar', 0.06643434767165562), ('value', 0.05530106541579836)]
                            process(baseDir, dir + File.separator + element, destDir, styleResource);
                        }
                    }
                }
            } else if (resources.isEmpty()) {
                // only resource collections, there better be some
                if (failOnNoResources) {
                    handleError("no resources specified");
                }
                return;
            }
            processResources(styleResource);
        } finally {
            if (loader != null) {
                loader.resetThreadContextLoader();
                loader.cleanup();
                loader = null;
            }
            if (sysProperties.size() > 0) {
                sysProperties.restoreSystem();
            }
            liaison = null;
            stylesheetLoaded = false;
            baseDir = savedBaseDir;
        }
    }

    /**
     * Set whether to check dependencies, or always generate;
     * optional, default is false.
     *
     * @param force true if always generate.
     */
    public void setForce(final boolean force) {
// force                0	: [('force', 0.885426011974618), ('f', 0.7527345659474908), ('b', 0.04230663788056469), ('verbose', 0.022472735128615615), ('value', 0.004956446201700809), ('include', 0.0035739050057789043), ('v', 0.003539178755860335), ('append', 0.0034729083109361495), ('failOnError', 0.002702736168586377), ('quiet', 0.002673479731681091)]
        this.force = force;
    }

    /**
     * Set the base directory;
     * optional, default is the project's basedir.
     *
     * @param dir the base directory
     */
    public void setBasedir(final File dir) {
// dir                  7	: [('value', 0.6375822184440564), ('baseDir', 0.5055142046050768), ('basedir', 0.25602321287676394), ('file', 0.03567534725438661), ('f', 0.015609344144405988), ('b', 0.012639786123966754), ('fileset', 0.012046290627241359), ('dir', 0.010288529613351543), ('destFile', 0.007493918849396663), ('srcDir', 0.007402245857440508)]
        baseDir = dir;
    }

    /**
     * Set the destination directory into which the XSL result
     * files should be copied to;
     * required, unless <code>in</code> and <code>out</code> are
     * specified.
     * @param dir the name of the destination directory
     */
    public void setDestdir(final File dir) {
// dir                  0	: [('dir', 0.627519492288635), ('destDir', 0.25306862736082447), ('destdir', 0.2509884616903496), ('dest', 0.12745150683144654), ('destDirName', 0.12740741642038397), ('file', 0.03567534725438661), ('f', 0.015609344144405988), ('destFile', 0.007493918849396663), ('srcDir', 0.007402245857440508), ('basedir', 0.006023212876763991)]
        destDir = dir;
    }

    /**
     * Set the desired file extension to be used for the target;
     * optional, default is html.
     * @param name the extension to use
     */
    public void setExtension(final String name) {
// name                 0	: [('name', 0.09279935474197894), ('message', 0.028050703927654484), ('value', 0.024700689854886583), ('output', 0.01756248812682309), ('s', 0.01730918177304069), ('line', 0.013743759270251515), ('pattern', 0.01276357636784246), ('text', 0.011258256149701443), ('classname', 0.011106847978797857), ('msg', 0.010637268144850536)]
        targetExtension = name;
    }

    /**
     * Name of the stylesheet to use - given either relative
     * to the project's basedir or as an absolute path; required.
     *
     * @param xslFile the stylesheet to use
     */
    public void setStyle(final String xslFile) {
// xslFile              No	: [('name', 0.09381721308274059), ('message', 0.028050703927654484), ('value', 0.024700689854886583), ('output', 0.01756248812682309), ('s', 0.01730918177304069), ('line', 0.013743759270251515), ('pattern', 0.01276357636784246), ('text', 0.011258256149701443), ('classname', 0.011106847978797857), ('msg', 0.010637268144850536)]
        this.xslFile = xslFile;
    }

    /**
     * Set the optional classpath to the XSL processor
     *
     * @param classpath the classpath to use when loading the XSL processor
     */
    public void setClasspath(final Path classpath) {
// classpath            1	: [('path', 0.5794547360934182), ('classpath', 0.26845472618198113), ('s', 0.1594223698054656), ('mp', 0.05412774869650775), ('filepath', 0.05256564745861509), ('junitPlatformResources', 0.05255778097581219), ('antRuntimeResources', 0.05255778097581219), ('nestedClasspath', 0.052557157115597435), ('nestedCatalogPath', 0.052556533255382676), ('stats', 0.028409108666980668)]
        createClasspath().append(classpath);
    }

    /**
     * Set the optional classpath to the XSL processor
     *
     * @return a path instance to be configured by the Ant core.
     */
    public Path createClasspath() {
        if (classpath == null) {
            classpath = new Path(getProject());
        }
        return classpath.createPath();
    }

    /**
     * Set the reference to an optional classpath to the XSL processor
     *
     * @param r the id of the Ant path instance to act as the classpath
     *          for loading the XSL processor
     */
    public void setClasspathRef(final Reference r) {
// r                    0	: [('r', 0.717307726513226), ('ref', 0.3201150222342934), ('pathRef', 0.018898065882657782), ('reference', 0.015037144127326974), ('value', 0.0024529790608626696), ('from', 0.002259151206398789), ('name', 0.0004718140205048085), ('i', 0.0003162175661928699), ('p', 0.00027913272921411614), ('project', 0.0002738104860531295)]
        createClasspath().setRefid(r);
    }

    /**
     * Set the name of the XSL processor to use; optional, default trax.
     *
     * @param processor the name of the XSL processor
     */
    public void setProcessor(final String processor) {
// processor            No	: [('processorClass', 0.16669550527913343), ('name', 0.09381721308274059), ('message', 0.028050703927654484), ('value', 0.024700689854886583), ('output', 0.01756248812682309), ('s', 0.01730918177304069), ('line', 0.013743759270251515), ('pattern', 0.01276357636784246), ('text', 0.011258256149701443), ('classname', 0.011106847978797857)]
        this.processor = processor;
    }

    /**
     * Whether to use the implicit fileset.
     *
     * <p>Set this to false if you want explicit control with nested
     * resource collections.</p>
     * @param useimplicitfileset set to true if you want to use implicit fileset
     * @since Ant 1.7
     */
    public void setUseImplicitFileset(final boolean useimplicitfileset) {
// useimplicitfileset   No	: [('b', 0.16922655152225877), ('verbose', 0.08989094051446246), ('value', 0.019825784806803234), ('include', 0.014295620023115617), ('v', 0.01415671502344134), ('append', 0.013891633243744598), ('f', 0.010938263789962878), ('failOnError', 0.010810944674345508), ('quiet', 0.010693918926724364), ('yesOrNo', 0.010459590951317868)]
        useImplicitFileset = useimplicitfileset;
    }

    /**
     * Add the catalog to our internal catalog
     *
     * @param xmlCatalog the XMLCatalog instance to use to look up DTDs
     */
    public void addConfiguredXMLCatalog(final XMLCatalog xmlCatalog) {
// xmlCatalog           1	: [('catalog', 0.8000099886241002), ('xmlCatalog', 0.7767914174308039), ('catalogA', 0.03572012709945747), ('catalogB', 0.01786229304190409), ('cat', 0.008934827533989489), ('name', 0.000943613039024661), ('i', 0.0006324284203865447), ('p', 0.0005582566128133989), ('project', 0.0005476121264914256), ('file', 0.0005266458999927756)]
        this.xmlCatalog.addConfiguredXMLCatalog(xmlCatalog);
    }

    /**
     * Pass the filename of the current processed file as a xsl parameter
     * to the transformation. This value sets the name of that xsl parameter.
     *
     * @param fileNameParameter name of the xsl parameter retrieving the
     *                          current file name
     */
    public void setFileNameParameter(final String fileNameParameter) {
// fileNameParameter    No	: [('name', 0.09381721308274059), ('message', 0.028050703927654484), ('value', 0.024700689854886583), ('output', 0.01756248812682309), ('s', 0.01730918177304069), ('line', 0.013743759270251515), ('pattern', 0.01276357636784246), ('text', 0.011258256149701443), ('classname', 0.011106847978797857), ('msg', 0.010637268144850536)]
        this.fileNameParameter = fileNameParameter;
    }

    /**
     * Pass the directory name of the current processed file as a xsl parameter
     * to the transformation. This value sets the name of that xsl parameter.
     *
     * @param fileDirParameter name of the xsl parameter retrieving the
     *                         current file directory
     */
    public void setFileDirParameter(final String fileDirParameter) {
// fileDirParameter     No	: [('name', 0.09381721308274059), ('message', 0.028050703927654484), ('value', 0.024700689854886583), ('output', 0.01756248812682309), ('s', 0.01730918177304069), ('line', 0.013743759270251515), ('pattern', 0.01276357636784246), ('text', 0.011258256149701443), ('classname', 0.011106847978797857), ('msg', 0.010637268144850536)]
        this.fileDirParameter = fileDirParameter;
    }

    /**
     * Whether to suppress warning messages of the processor.
     *
     * @param b boolean
     * @since Ant 1.8.0
     */
    public void setSuppressWarnings(final boolean b) {
// b                    1	: [('xsltTask', 0.5001115624693344), ('b', 0.16574118497307813), ('verbose', 0.08989094051446246), ('value', 0.019825784806803234), ('include', 0.014295620023115617), ('v', 0.01415671502344134), ('append', 0.013891633243744598), ('f', 0.010938263789962878), ('failOnError', 0.010810944674345508), ('quiet', 0.010693918926724364)]
        suppressWarnings = b;
    }

    /**
     * Whether to suppress warning messages of the processor.
     *
     * @return boolean
     * @since Ant 1.8.0
     */
    public boolean getSuppressWarnings() {
        return suppressWarnings;
    }

    /**
     * Whether transformation errors should make the build fail.
     *
     * @param b boolean
     * @since Ant 1.8.0
     */
    public void setFailOnTransformationError(final boolean b) {
// b                    0	: [('b', 0.16574118497307813), ('verbose', 0.08989094051446246), ('value', 0.019825784806803234), ('include', 0.014295620023115617), ('v', 0.01415671502344134), ('append', 0.013891633243744598), ('f', 0.010938263789962878), ('failOnError', 0.010810944674345508), ('quiet', 0.010693918926724364), ('yesOrNo', 0.010459590951317868)]
        failOnTransformationError = b;
    }

    /**
     * Whether any errors should make the build fail.
     *
     * @param b boolean
     * @since Ant 1.8.0
     */
    public void setFailOnError(final boolean b) {
// b                    0	: [('b', 0.5323360376352035), ('failOnError', 0.37770270160856584), ('failonerror', 0.37680186161422174), ('fail', 0.2238230539142627), ('value', 0.0642810359619549), ('flag', 0.03199059771606988), ('failure', 0.03197804363365496), ('verbose', 0.022472735128615615), ('include', 0.0035739050057789043), ('v', 0.003539178755860335)]
        failOnError = b;
    }

    /**
     * Whether the build should fail if the nested resource collection is empty.
     *
     * @param b boolean
     * @since Ant 1.8.0
     */
    public void setFailOnNoResources(final boolean b) {
// b                    0	: [('b', 0.16574118497307813), ('verbose', 0.08989094051446246), ('value', 0.019825784806803234), ('include', 0.014295620023115617), ('v', 0.01415671502344134), ('append', 0.013891633243744598), ('f', 0.010938263789962878), ('failOnError', 0.010810944674345508), ('quiet', 0.010693918926724364), ('yesOrNo', 0.010459590951317868)]
        failOnNoResources = b;
    }

    /**
     * A system property to set during transformation.
     *
     * @param sysp Environment.Variable
     * @since Ant 1.8.0
     */
    public void addSysproperty(final Environment.Variable sysp) {
// sysp                 0	: [('sysp', 0.7854249202330426), ('var', 0.3822821340720825), ('sysProp', 0.16370253467699533), ('v', 0.02029136621128678), ('property', 0.010147294559313272), ('variable', 0.010141903196111234), ('name', 0.0004718065195123305), ('i', 0.00031621421019327234), ('p', 0.00027912830640669945), ('project', 0.0002738060632457128)]
        sysProperties.addVariable(sysp);
    }

    /**
     * A set of system properties to set during transformation.
     *
     * @param sysp PropertySet
     * @since Ant 1.8.0
     */
    public void addSyspropertyset(final PropertySet sysp) {
// sysp                 0	: [('sysp', 0.8475017713864778), ('propertySet', 0.09875139122625187), ('ps', 0.058741925941978225), ('set', 0.002505987708981829), ('ref', 0.0012528427471139732), ('name', 0.0004718065195123305), ('i', 0.00031621421019327234), ('p', 0.00027912830640669945), ('project', 0.0002738060632457128), ('file', 0.0002633229499963878)]
        sysProperties.addSyspropertyset(sysp);
    }

    /**
     * Enables Xalan2 traces and uses the given configuration.
     *
     * <p>Note that this element doesn't have any effect with a
     * processor other than trax or if the Transformer is not Xalan2's
     * transformer implementation.</p>
     *
     * @return TraceConfiguration
     * @since Ant 1.8.0
     */
    public TraceConfiguration createTrace() {
        if (traceConfiguration != null) {
            throw new BuildException("can't have more than one trace configuration");
        }
        traceConfiguration = new TraceConfiguration();
        return traceConfiguration;
    }

    /**
     * Configuration for Xalan2 traces.
     *
     * @return TraceConfiguration
     * @since Ant 1.8.0
     */
    public TraceConfiguration getTraceConfiguration() {
        return traceConfiguration;
    }

    /**
     * Load processor here instead of in setProcessor - this will be
     * called from within execute, so we have access to the latest
     * classpath.
     *
     * @param proc the name of the processor to load.
     * @exception Exception if the processor cannot be loaded.
     */
    private void resolveProcessor(final String proc) throws Exception {
// proc                 No	: [('classname', 0.26244557904009336), ('name', 0.09381721696295152), ('args', 0.03672837937995296), ('other', 0.0305367566521586), ('message', 0.02805070501007739), ('value', 0.02470069223988621), ('helperClass', 0.023848940609986116), ('engineClassName', 0.023827594293653696), ('mainClassname', 0.02382426222236301), ('p2', 0.01942645898858193)]
        if (PROCESSOR_TRAX.equals(proc)) {
            liaison = new TraXLiaison();
        } else {
            // anything else is a classname
            final Class<? extends XSLTLiaison> clazz = loadClass(proc).asSubclass(XSLTLiaison.class);
// clazz                3	: [('c', 0.01833726208020244), ('stk', 0.014736303901343053), ('files', 0.012609222450211089), ('clazz', 0.009987873342433035), ('e', 0.007568591385394789), ('list', 0.007315730761397085), ('filterChains', 0.007248269601330552), ('i', 0.007220869255079404), ('ejbFiles', 0.006741362734087147), ('result', 0.00652539187654582)]
            liaison = clazz.newInstance();
        }
    }

    /**
     * Load named class either via the system classloader or a given
     * custom classloader.
     *
     * As a side effect, the loader is set as the thread context classloader
     * @param classname the name of the class to load.
     * @return the requested class.
     */
    private Class<?> loadClass(final String classname) throws ClassNotFoundException {
// classname            0	: [('classname', 0.7525222475654514), ('className', 0.26781350677949195), ('cName', 0.09597215578873095), ('launcherClass', 0.09597090806738412), ('analyzerClassName', 0.02527320172412398), ('name', 0.02345430424073788), ('value', 0.012858531312664232), ('helperClass', 0.01264020841866117), ('helperClassName', 0.012639653678946117), ('managerClass', 0.012638164034245032)]
        setupLoader();
        if (loader == null) {
            return Class.forName(classname);
        }
        return Class.forName(classname, true, loader);
    }

    /**
     * If a custom classpath has been defined but no loader created
     * yet, create the classloader and set it as the context
     * classloader.
     */
    private void setupLoader() {
        if (classpath != null && loader == null) {
            loader = getProject().createClassLoader(classpath);
            loader.setThreadContextLoader();
        }
    }

    /**
     * Specifies the output name for the styled result from the
     * <code>in</code> attribute; required if <code>in</code> is set
     *
     * @param outFile the output File instance.
     */
    public void setOut(final File outFile) {
// outFile              1	: [('out', 0.4480003096026129), ('outFile', 0.44792165389404603), ('file', 0.14270138901754645), ('f', 0.06243737657762395), ('dir', 0.04505965987263728), ('destFile', 0.029975675397586648), ('srcDir', 0.029608983429762033), ('basedir', 0.024092851507055965), ('sourceFile', 0.022058615541374497), ('baseDir', 0.022056956660389256)]
        this.outFile = outFile;
    }

    /**
     * specifies a single XML document to be styled. Should be used
     * with the <code>out</code> attribute; required if <code>out</code> is set
     *
     * @param inFile the input file
     */
    public void setIn(final File inFile) {
// inFile               0	: [('inFile', 0.9062542614669483), ('file', 0.14270138901754645), ('f', 0.06243737657762395), ('dir', 0.04505965987263728), ('destFile', 0.029975675397586648), ('srcDir', 0.029608983429762033), ('basedir', 0.024092851507055965), ('sourceFile', 0.022058615541374497), ('baseDir', 0.022056956660389256), ('container', 0.020551678401429442)]
        this.inFile = inFile;
    }

    /**
     * Throws a BuildException if the destination directory hasn't
     * been specified.
     * @since Ant 1.7
     */
    private void checkDest() {
        if (destDir == null) {
            handleError("destdir attributes must be set!");
        }
    }

    /**
     * Styles all existing resources.
     *
     * @param stylesheet style sheet to use
     * @since Ant 1.7
     */
    private void processResources(final Resource stylesheet) {
// stylesheet           No	: [('r', 0.32286822320251213), ('mapper', 0.19473893498362552), ('source', 0.09887702883409384), ('m', 0.09747146938223693), ('ds', 0.09744600772142613), ('srcFiles', 0.09726274976891293), ('styleResource', 0.09725214286738373), ('resource', 0.06701425590997327), ('r1', 0.04970035150761896), ('res', 0.03130146032029988)]
        for (final Resource r : resources) {
// r                    0	: [('r', 0.7562854261443588), ('resource', 0.37684243904857445), ('event', 0.10463905791570582), ('entry', 0.09618182511456008), ('initialResources', 0.07679301923627663), ('file', 0.07133955338104306), ('stylesheet', 0.0634051772214208), ('f', 0.05112977389845788), ('tmp1', 0.046526349052657894), ('ue', 0.045918058829730046)]
            if (!r.isExists()) {
                continue;
            }
            File base = baseDir;
// base                 0	: [('base', 0.5315276333793044), ('baseDir', 0.35835204539024795), ('entryFile', 0.13730765859265948), ('newFile', 0.13429740157576817), ('myfile', 0.13427492542552355), ('d', 0.06870824649116941), ('f', 0.03210862828782995), ('t', 0.017269661137129667), ('parent', 0.017137556100650226), ('c', 0.015684615568042584)]
            String name = r.getName();
// name                 0	: [('name', 0.5170641154451169), ('base', 0.2531747065160399), ('initialResources', 0.09118755000035565), ('baseURL', 0.08471358419792786), ('pathToTraverse', 0.08430042198280421), ('files', 0.06135425568129144), ('ds', 0.060943404594312675), ('tokens', 0.060762098800340725), ('includedFile', 0.039003830310187955), ('f', 0.0313392139720236)]
            final FileProvider fp = r.as(FileProvider.class);
// fp                   0	: [('fp', 0.6271081840316387), ('r', 0.3693353555016556), ('res', 0.15209366066170604), ('fileResource2', 0.11930663574345732), ('up', 0.06361375265247486), ('a', 0.038554601136278004), ('view', 0.038166216581154586), ('f', 0.03169439416813287), ('c', 0.03166657616542513), ('t', 0.029243606492847112)]
            if (fp != null) {
                final FileResource f = ResourceUtils.asFileResource(fp);
// f                    No	: [('fr', 0.8161913846152563), ('r', 0.1777027559209428), ('className', 0.1407072028517735), ('newname', 0.14065636807554796), ('name', 0.12972406621087657), ('fileset', 0.06624724030841017), ('text', 0.02049628174748774), ('value', 0.017266991214169025), ('prefix', 0.017072635904364076), ('key', 0.016645919670193403)]
                base = f.getBaseDir();
                if (base == null) {
                    name = f.getFile().getAbsolutePath();
                }
            }
            process(base, name, destDir, stylesheet);
        }
    }

    /**
     * Processes the given input XML file and stores the result
     * in the given resultFile.
     *
     * @param baseDir the base directory for resolving files.
     * @param xmlFile the input file
     * @param destDir the destination directory
     * @param stylesheet the stylesheet to use.
     * @exception BuildException if the processing fails.
     */
    private void process(final File baseDir, final String xmlFile, final File destDir, final Resource stylesheet) throws BuildException {
// baseDir              No	: [('inFile', 0.7521410781131822), ('dir', 0.05445094426179128), ('workDir', 0.04928713565359117), ('image', 0.03618574263263614), ('file', 0.03567633269559505), ('output', 0.03484634073426442), ('toDir', 0.03447224286922362), ('tmp', 0.027754338510914356), ('d', 0.02604942175982396), ('srcDir', 0.025874028747222996)]
// xmlFile              No	: [('dir', 0.4128879499215641), ('name', 0.3868324971445895), ('entry', 0.3761021095350589), ('relativePath', 0.1438985018353929), ('srcName', 0.14259071989085628), ('path', 0.13457893024600442), ('sourceFileName', 0.13445372134621525), ('rc', 0.11394397214812464), ('source', 0.07135058934780514), ('srcFile', 0.07133486367154562)]
// destDir              No	: [('aLocalFile', 0.11407149083751213), ('file', 0.1065721091652876), ('dir', 0.054450941328180416), ('destFile', 0.05081860904772526), ('workDir', 0.04928713487573981), ('localFile', 0.047451788416408645), ('image', 0.036185741410298275), ('toDir', 0.03539432031622217), ('output', 0.034846336044931904), ('zipFile', 0.029173661950598586)]
// stylesheet           0	: [('stylesheet', 0.9375096909237508), ('fr', 0.4062640080568579), ('dest', 0.2271610638863575), ('r2', 0.11343671431184517), ('r', 0.10858251044870147), ('resource', 0.04737139918173915), ('src', 0.03504987400890998), ('resource2', 0.028087294080752844), ('source', 0.027448457917380042), ('r1', 0.013986065861214)]
        File outF = null;
// outF                 No	: [('outFile', 0.9062602276093275), ('savedDir', 0.23035007796316906), ('toFile', 0.21479492993443594), ('srcFile', 0.15617333142302625), ('genericJarFile', 0.1538802244358647), ('now', 0.12537251944648836), ('info', 0.12535682643776425), ('destFile', 0.1253481152017744), ('javaFile', 0.12534023478502482), ('classFile', 0.1253399928424053)]
        try {
            final long styleSheetLastModified = stylesheet.getLastModified();
// styleSheetLastModified 0	: [('styleSheetLastModified', 0.7578871108479112), ('len', 0.5878471915409943), ('start', 0.09726609165673715), ('currentTime', 0.023671267329003858), ('filesize', 0.01682818229650073), ('endTime', 0.015747936877685737), ('parent', 0.010581436710936983), ('timeElapsed', 0.009731871534246941), ('uri', 0.00889372076704008), ('dir', 0.007903215817476222)]
            File inF = new File(baseDir, xmlFile);
// inF                  No	: [('inFile', 0.9375501404115875), ('in', 0.7285183850908581), ('outFileName', 0.5002366222766921), ('file', 0.2349994772891112), ('f', 0.23474096743439665), ('srcFile', 0.2344990999566117), ('pathElement', 0.23440444252826365), ('release', 0.06354577862807868), ('dir2', 0.04767200238415665), ('jmod', 0.044505826929323075)]
            if (inF.isDirectory()) {
                log("Skipping " + inF + " it is a directory.", Project.MSG_VERBOSE);
                return;
            }
            FileNameMapper mapper = mapperElement == null ? new StyleMapper() : mapperElement.getImplementation();
// mapper               2	: [('m', 0.3877175681450471), ('myMapper', 0.376587566905856), ('mapper', 0.049072371906006065), ('fileNameMapper', 0.03956916245426497), ('i', 0.007220869255079404), ('name', 0.00615307471702672), ('b', 0.005880193753638788), ('value', 0.005419652314355413), ('file', 0.004260412397551352), ('path', 0.0036403468622049576)]
            final String[] outFileName = mapper.mapFileName(xmlFile);
// outFileName          No	: [('inF', 0.5002240887075586), ('filename', 0.2866084305293988), ('files', 0.14683140701760694), ('f', 0.09746707157889373), ('baseName', 0.09551159781859397), ('subTarget', 0.0954893934806374), ('pathToClass', 0.09548932435784435), ('destFileName', 0.0954892552350513), ('destName', 0.09548922067365478), ('args', 0.07186060405178801)]
            if (outFileName == null || outFileName.length == 0) {
                log("Skipping " + inFile + " it cannot get mapped to output.", Project.MSG_VERBOSE);
                return;
            }
            if (outFileName.length > 1) {
                log("Skipping " + inFile + " its mapping is ambiguous.", Project.MSG_VERBOSE);
                return;
            }
            outF = new File(destDir, outFileName[0]);
            if (force || inF.lastModified() > outF.lastModified() || styleSheetLastModified > outF.lastModified()) {
                ensureDirectoryFor(outF);
                log("Processing " + inF + " to " + outF);
                configureLiaison(stylesheet);
                setLiaisonDynamicFileParameters(liaison, inF);
                liaison.transform(inF, outF);
            }
        } catch (final Exception ex) {
// ex                   0	: [('ex', 0.9167338013854246), ('e', 0.6832411589941966), ('x', 0.05805362587694279), ('exc', 0.028859129392572604), ('t', 0.0010837506763595377), ('name', 0.0004718065195123305), ('i', 0.00031621421019327234), ('p', 0.00027912830640669945), ('project', 0.0002738060632457128), ('file', 0.0002633229499963878)]
            // If failed to process document, must delete target document,
            // or it will not attempt to process it the second time
            log("Failed to process " + inFile, Project.MSG_INFO);
            if (outF != null) {
                outF.delete();
            }
            handleTransformationError(ex);
        }
    }

    // -- processXML
    /**
     * Process the input file to the output file with the given stylesheet.
     *
     * @param inFile the input file to process.
     * @param outFile the destination file.
     * @param stylesheet the stylesheet to use.
     * @exception BuildException if the processing fails.
     */
    private void process(final File inFile, final File outFile, final Resource stylesheet) throws BuildException {
// inFile               No	: [('inF', 0.9375495874237392), ('baseDir', 0.7555142396178247), ('in', 0.7285183868229997), ('outFile', 0.37517626832747797), ('styleSheetLastModified', 0.3750326791696095), ('srcFile', 0.21887423009385587), ('file', 0.04992310207269484), ('e', 0.04859998726616638), ('name', 0.045441260768869146), ('f', 0.026810047797270715)]
// outFile              1	: [('outF', 0.9062525353828402), ('outFile', 0.7500167247829523), ('inFile', 0.37517292238584943), ('styleSheetLastModified', 0.3750325593295051), ('srcfile', 0.2633541881043591), ('entryDate', 0.26335366964212636), ('sourceLastMod', 0.26335363507797754), ('toFile', 0.21479570156602376), ('fe', 0.16718977426647327), ('formatterArg', 0.1671625814967929)]
// stylesheet           0	: [('stylesheet', 0.9375096563784077), ('fr', 0.40626400809263385), ('dest', 0.227161064222101), ('r2', 0.11343671436505041), ('r', 0.10858251198064574), ('e', 0.04759798957367004), ('resource', 0.047371399596373175), ('src', 0.03504987456848243), ('resource2', 0.028087294086256834), ('source', 0.027448458429251237)]
        try {
            final long styleSheetLastModified = stylesheet.getLastModified();
// styleSheetLastModified 0	: [('styleSheetLastModified', 0.7578870762906426), ('start', 0.5972660918287324), ('inFile', 0.375228503161789), ('outFile', 0.3751761209700366), ('len', 0.0878471916905154), ('currentTime', 0.023671267338635584), ('filesize', 0.01682818231347092), ('endTime', 0.015747936889152082), ('parent', 0.010581436944850408), ('timeElapsed', 0.009731871542502709)]
            log("In file " + inFile + " time: " + inFile.lastModified(), Project.MSG_DEBUG);
            log("Out file " + outFile + " time: " + outFile.lastModified(), Project.MSG_DEBUG);
            log("Style file " + xslFile + " time: " + styleSheetLastModified, Project.MSG_DEBUG);
            if (force || inFile.lastModified() >= outFile.lastModified() || styleSheetLastModified >= outFile.lastModified()) {
                ensureDirectoryFor(outFile);
                log("Processing " + inFile + " to " + outFile, Project.MSG_INFO);
                configureLiaison(stylesheet);
                setLiaisonDynamicFileParameters(liaison, inFile);
                liaison.transform(inFile, outFile);
            } else {
                log("Skipping input file " + inFile + " because it is older than output file " + outFile + " and so is the stylesheet " + stylesheet, Project.MSG_DEBUG);
            }
        } catch (final Exception ex) {
// ex                   0	: [('ex', 0.9167338013854246), ('e', 0.6832411589941966), ('x', 0.05805362587694279), ('exc', 0.028859129392572604), ('t', 0.0010837506763595377), ('name', 0.0004718065195123305), ('i', 0.00031621421019327234), ('p', 0.00027912830640669945), ('project', 0.0002738060632457128), ('file', 0.0002633229499963878)]
            log("Failed to process " + inFile, Project.MSG_INFO);
            if (outFile != null) {
                outFile.delete();
            }
            handleTransformationError(ex);
        }
    }

    /**
     * Ensure the directory exists for a given file
     *
     * @param targetFile the file for which the directories are required.
     * @exception BuildException if the directories cannot be created.
     */
    private void ensureDirectoryFor(final File targetFile) throws BuildException {
// targetFile           No	: [('file', 0.14278097740975096), ('directory', 0.09102932720187447), ('f', 0.06247931470024899), ('dir', 0.04508352236821029), ('destFile', 0.02999621600619985), ('srcDir', 0.029621772414460266), ('basedir', 0.024112315425870147), ('sourceFile', 0.022069735792543746), ('baseDir', 0.022068076911558505), ('container', 0.020560974118088132)]
        final File directory = targetFile.getParentFile();
// directory            No	: [('d', 0.513196797861825), ('name', 0.38305868363622486), ('pathComponent', 0.19104254427589717), ('link', 0.18958080197563446), ('propsFile', 0.18918975748198086), ('casesFile', 0.18918927362242954), ('savedFile', 0.18903543447769472), ('path', 0.18872942080555236), ('newFile', 0.17978299691582533), ('targetFile', 0.1797327618985048)]
        if (!directory.exists() && !directory.mkdirs() && !directory.isDirectory()) {
            handleError("Unable to create directory: " + directory.getAbsolutePath());
        }
    }

    /**
     * Get the factory instance configured for this processor
     *
     * @return the factory instance in use
     */
    public Factory getFactory() {
        return factory;
    }

    /**
     * Get the XML catalog containing entity definitions
     *
     * @return the XML catalog for the task.
     */
    public XMLCatalog getXMLCatalog() {
        xmlCatalog.setProject(getProject());
        return xmlCatalog;
    }

    /**
     * Get an enumeration on the outputproperties.
     * @return the outputproperties
     */
    public Enumeration<OutputProperty> getOutputProperties() {
        return Collections.enumeration(outputProperties);
    }

    /**
     * Get the Liaison implementation to use in processing.
     *
     * @return an instance of the XSLTLiaison interface.
     */
    protected XSLTLiaison getLiaison() {
        // if processor wasn't specified, use TraX.
        if (liaison == null) {
            if (processor != null) {
                try {
                    resolveProcessor(processor);
                } catch (final Exception e) {
// e                    0	: [('e', 0.6553337774068572), ('baseMessage', 0.40812410652807796), ('ex', 0.2145913243795609), ('ioe', 0.20413783860032453), ('x', 0.05805362587694279), ('exc', 0.028859129392572604), ('e1', 0.009630644049931945), ('t', 0.0010837506763595377), ('name', 0.000943613039024661), ('i', 0.0006324284203865447)]
                    handleError(e);
                }
            } else {
                try {
                    resolveProcessor(PROCESSOR_TRAX);
                } catch (final Throwable e1) {
// e1                   No	: [('t', 0.4407064451596462), ('e', 0.25268362072043704), ('exc', 0.18752835601901527), ('ex', 0.15154022631719627), ('err', 0.10100067702460477), ('baseMessage', 0.07694087092337339), ('ioe', 0.050529816297249175), ('error', 0.05051194920993005), ('thrownException', 0.05049823116327367), ('npe', 0.05049320741166437)]
                    log(StringUtils.getStackTrace(e1), Project.MSG_ERR);
                    handleError(e1);
                }
            }
        }
        return liaison;
    }

    /**
     * Create an instance of an XSL parameter for configuration by Ant.
     *
     * @return an instance of the Param class to be configured.
     */
    public Param createParam() {
        final Param p = new Param();
// p                    1	: [('param', 0.7509713200132658), ('p', 0.48285694890805736), ('ph', 0.2738691654772738), ('paramx', 0.08334197483654747), ('result', 0.015132089256859145), ('rc', 0.009356709482938921), ('name', 0.0055916482197196655), ('newFilter', 0.005465637671070016), ('instr', 0.00538822614396171), ('r', 0.004915311398878124)]
        params.add(p);
        return p;
    }

    /**
     * The Param inner class used to store XSL parameters
     */
    public static class Param {

        /**
         * The parameter name
         */
        private String name = null;

        /**
         * The parameter's value
         */
        private String expression = null;

        /**
         * Type of the expression.
         * @see ParamType
         */
        private String type;

        private Object ifCond;

        private Object unlessCond;

        private Project project;

        /**
         * Set the current project
         *
         * @param project the current project
         */
        public void setProject(final Project project) {
// project              0	: [('project', 0.8771305826056911), ('p', 0.06850295484922184), ('task', 0.014376043741418317), ('target', 0.0130672293706638), ('other', 0.013063706007562964), ('prj', 0.00575138824703295), ('event', 0.00395642427045437), ('subProject', 0.002944006210678903), ('proj', 0.002943833410576274), ('parent', 0.0029210845658056398)]
            this.project = project;
        }

        /**
         * Set the parameter name.
         *
         * @param name the name of the parameter.
         */
        public void setName(final String name) {
// name                 0	: [('name', 0.8253012655543025), ('test', 0.05822995119659478), ('aName', 0.057947748783503686), ('normalizedName', 0.03787271154593717), ('value', 0.01743983153146476), ('other', 0.015895038861217244), ('message', 0.007012675981913621), ('output', 0.004390622031705773), ('s', 0.004327295443260173), ('line', 0.0034359398175628788)]
            this.name = name;
        }

        /**
         * The parameter value -
         * can be a primitive type value or an XPath expression.
         * @param expression the parameter's value/expression.
         * @see #setType(java.lang.String)
         */
        public void setExpression(final String expression) {
// expression           0	: [('expression', 0.9062539504267636), ('name', 0.09381721308274059), ('param', 0.03131083859937758), ('message', 0.028050703927654484), ('value', 0.024700689854886583), ('output', 0.01756248812682309), ('s', 0.01730918177304069), ('line', 0.013743759270251515), ('pattern', 0.01276357636784246), ('text', 0.011258256149701443)]
            this.expression = expression;
        }

        /**
         * @param type String
         * @see ParamType
         * @since Ant 1.9.3
         */
        public void setType(final String type) {
// type                 0	: [('type', 0.8352143764178802), ('fileTypes', 0.07963015908314067), ('name', 0.023454303270685147), ('message', 0.007012675981913621), ('value', 0.006175172463721646), ('output', 0.004390622031705773), ('s', 0.004327295443260173), ('line', 0.0034359398175628788), ('pattern', 0.003190894091960615), ('data', 0.002961329785508861)]
            this.type = type;
        }

        /**
         * Get the parameter name
         *
         * @return the parameter name
         * @exception BuildException if the name is not set.
         */
        public String getName() throws BuildException {
            if (name == null) {
                throw new BuildException("Name attribute is missing.");
            }
            return name;
        }

        /**
         * Get the parameter's value
         *
         * @return the parameter value
         * @exception BuildException if the value is not set.
         * @see #getType()
         */
        public String getExpression() throws BuildException {
            if (expression == null) {
                throw new BuildException("Expression attribute is missing.");
            }
            return expression;
        }

        /**
         * @return String
         * @see ParamType
         * @since Ant 1.9.3
         */
        public String getType() {
            return type;
        }

        /**
         * Set whether this param should be used.  It will be used if
         * the expression evaluates to true or the name of a property
         * which has been set, otherwise it won't.
         * @param ifCond evaluated expression
         * @since Ant 1.8.0
         */
        public void setIf(final Object ifCond) {
// ifCond               0	: [('ifCond', 0.8875040541068252), ('parent', 0.22270538153444527), ('key', 0.11790985209294336), ('o', 0.08298752844230696), ('element', 0.07377207874685449), ('obj', 0.07119450307028367), ('other', 0.059207040034833514), ('value', 0.03165166984353948), ('unlessCond', 0.029204850398531674), ('child', 0.014441016214880609)]
            this.ifCond = ifCond;
        }

        /**
         * Set whether this param should be used.  It will be used if
         * the expression evaluates to true or the name of a property
         * which has been set, otherwise it won't.
         * @param ifProperty evaluated expression
         */
        public void setIf(final String ifProperty) {
// ifProperty           0	: [('ifProperty', 0.7505136485043228), ('c', 0.1490045500950189), ('propertyName', 0.14896327143029314), ('cond', 0.14896143974920528), ('ifCond', 0.12448170924790306), ('value', 0.06463738821956935), ('unlessProperty', 0.04896154342926685), ('v', 0.03230626875796747), ('cmd', 0.031286409464252644), ('e', 0.02453884589904037)]
            setIf((Object) ifProperty);
        }

        /**
         * Set whether this param should NOT be used. It will not be
         * used if the expression evaluates to true or the name of a
         * property which has been set, otherwise it will be used.
         * @param unlessCond evaluated expression
         * @since Ant 1.8.0
         */
        public void setUnless(final Object unlessCond) {
// unlessCond           0	: [('unlessCond', 0.8875039504267637), ('parent', 0.22270538153444527), ('key', 0.11790985209294336), ('o', 0.08298752844230696), ('element', 0.07377207874685449), ('obj', 0.07119450307028367), ('other', 0.059207040034833514), ('value', 0.03165166984353948), ('ifCond', 0.029205265118777985), ('child', 0.014441016214880609)]
            this.unlessCond = unlessCond;
        }

        /**
         * Set whether this param should NOT be used. It will not be
         * used if the expression evaluates to true or the name of a
         * property which has been set, otherwise it will be used.
         * @param unlessProperty evaluated expression
         */
        public void setUnless(final String unlessProperty) {
// unlessProperty       0	: [('unlessProperty', 0.7505135793842816), ('c', 0.1490045500950189), ('propertyName', 0.14896327143029314), ('cond', 0.14896143974920528), ('unlessCond', 0.12448160556784146), ('value', 0.06463738821956935), ('ifProperty', 0.0489616125493079), ('v', 0.03230626875796747), ('cmd', 0.031286409464252644), ('e', 0.02453884589904037)]
            setUnless((Object) unlessProperty);
        }

        /**
         * Ensures that the param passes the conditions placed
         * on it with <code>if</code> and <code>unless</code> properties.
         * @return true if the task passes the "if" and "unless" parameters
         */
        public boolean shouldUse() {
            final PropertyHelper ph = PropertyHelper.getPropertyHelper(project);
// ph                   1	: [('propertyHelper', 0.8950518876625836), ('ph', 0.8760575780774402), ('result', 0.14013208925685913), ('cat', 0.12568424923467542), ('launcher', 0.12538018233575965), ('helper', 0.005911174168194351), ('newFilter', 0.005465637671070016), ('p', 0.0046270011785780815), ('cmd', 0.00416512223444186), ('buf', 0.003295728398361043)]
            return ph.testIfCondition(ifCond) && ph.testUnlessCondition(unlessCond);
        }
    }

    // Param
    /**
     * Enum for types of the parameter expression.
     *
     * <p>The expression can be:</p>
     * <ul>
     * <li>primitive type that will be parsed from the string value e.g.
     * {@linkplain Integer#parseInt(java.lang.String)}</li>
     * <li>XPath expression that will be evaluated (outside of the transformed
     * document - on empty one) and casted to given type. Inside XPath
     * expressions the Ant variables (properties) can be used (as XPath
     * variables - e.g. $variable123). n.b. placeholders in form of
     * ${variable123} will be substituted with their values before evaluating the
     * XPath expression (so it can be used for dynamic XPath function names and
     * other hacks).</li>
     * </ul>
     * <p>The parameter will be then passed to the XSLT template.</p>
     *
     * <p>Default type (if omitted) is primitive String. So if the expression is e.g
     * "true" with no type, in XSLT it will be only a text string, not true
     * boolean.</p>
     *
     * @see Param#setType(java.lang.String)
     * @see Param#setExpression(java.lang.String)
     * @since Ant 1.9.3
     */
    public enum ParamType {

        STRING,
        BOOLEAN,
        INT,
        LONG,
        DOUBLE,
        XPATH_STRING,
        XPATH_BOOLEAN,
        XPATH_NUMBER,
        XPATH_NODE,
        XPATH_NODESET;

        public static final Map<ParamType, QName> XPATH_TYPES;

        static {
            final Map<ParamType, QName> m = new EnumMap<>(ParamType.class);
// m                    No	: [('map', 0.4687640271242404), ('se', 0.4687523607194885), ('attrMap', 0.07122414157167244), ('runner', 0.06110341017169926), ('def', 0.030957877262217548), ('elemMap', 0.030514973134502712), ('project', 0.020820407831384564), ('autoFound', 0.020330148235946574), ('includeProject', 0.020319108763857254), ('c', 0.01833729470139054)]
            m.put(XPATH_STRING, XPathConstants.STRING);
            m.put(XPATH_BOOLEAN, XPathConstants.BOOLEAN);
            m.put(XPATH_NUMBER, XPathConstants.NUMBER);
            m.put(XPATH_NODE, XPathConstants.NODE);
            m.put(XPATH_NODESET, XPathConstants.NODESET);
            XPATH_TYPES = Collections.unmodifiableMap(m);
        }
    }

    /**
     * Create an instance of an output property to be configured.
     * @return the newly created output property.
     * @since Ant 1.5
     */
    public OutputProperty createOutputProperty() {
        final OutputProperty p = new OutputProperty();
// p                    0	: [('p', 0.48285694890805736), ('ph', 0.2738691654772738), ('rc', 0.037426837931755684), ('name', 0.022366592878878662), ('instr', 0.02155290457584684), ('r', 0.019661245595512494), ('c', 0.01870645186245405), ('fileNameMapper', 0.018337888630262856), ('result', 0.015132089256859145), ('i', 0.013862326085852245)]
        outputProperties.add(p);
        return p;
    }

    /**
     * Specify how the result tree should be output as specified
     * in the <a href="https://www.w3.org/TR/xslt#output">
     * specification</a>.
     * @since Ant 1.5
     */
    public static class OutputProperty {

        /**
         * output property name
         */
        private String name;

        /**
         * output property value
         */
        private String value;

        /**
         * @return the output property name.
         */
        public String getName() {
            return name;
        }

        /**
         * set the name for this property
         * @param name A non-null String that specifies an
         * output property name, which may be namespace qualified.
         */
        public void setName(final String name) {
// name                 0	: [('name', 0.8253012655543025), ('test', 0.05822995119659478), ('aName', 0.057947748783503686), ('normalizedName', 0.03787271154593717), ('value', 0.01743983153146476), ('other', 0.015895038861217244), ('message', 0.007012675981913621), ('output', 0.004390622031705773), ('s', 0.004327295443260173), ('line', 0.0034359398175628788)]
            this.name = name;
        }

        /**
         * @return the output property value.
         */
        public String getValue() {
            return value;
        }

        /**
         * set the value for this property
         * @param value The non-null string value of the output property.
         */
        public void setValue(final String value) {
// value                0	: [('value', 0.8423409700789647), ('file', 0.0295479316190028), ('path', 0.02950917752304365), ('name', 0.023454303270685147), ('message', 0.007012675981913621), ('output', 0.004390622031705773), ('s', 0.004327295443260173), ('attrs', 0.003614430483464086), ('line', 0.0034359398175628788), ('pattern', 0.003190894091960615)]
            this.value = value;
        }
    }

    /**
     * Initialize internal instance of XMLCatalog.
     * Initialize XPath for parameter evaluation.
     * @throws BuildException on error
     */
    @Override
    public void init() throws BuildException {
        super.init();
        xmlCatalog.setProject(getProject());
        xpathFactory = XPathFactory.newInstance();
        xpath = xpathFactory.newXPath();
        xpath.setXPathVariableResolver(variableName -> getProject().getProperty(variableName.toString()));
// variableName         No	: [('propertyName', 0.2828295580787768), ('name', 0.2171244026243129), ('property', 0.09043720446022985), ('value', 0.08254692159382959), ('outV', 0.0823265203493465), ('tempJar', 0.08232537630897856), ('key', 0.035402647296415256), ('i', 0.005059481059085919), ('p', 0.004466123667425858), ('project', 0.004380967776850072)]
    }

    /**
     * Loads the stylesheet and set xsl:param parameters.
     *
     * @param stylesheet the file from which to load the stylesheet.
     * @exception BuildException if the stylesheet cannot be loaded.
     * @deprecated since Ant 1.7
     */
    @Deprecated
    protected void configureLiaison(final File stylesheet) throws BuildException {
// stylesheet           0	: [('stylesheet', 0.7601972241253361), ('file', 0.14270138901754645), ('f', 0.06243737657762395), ('dir', 0.04505965987263728), ('destFile', 0.029975675397586648), ('srcDir', 0.029608983429762033), ('javah', 0.028744587430510917), ('basedir', 0.024092851507055965), ('sourceFile', 0.022058615541374497), ('baseDir', 0.022056956660389256)]
        final FileResource fr = new FileResource();
// fr                   1	: [('stylesheet', 0.8125161582833796), ('fr', 0.3833419396485719), ('f', 0.37555526486936736), ('r', 0.21083348703063023), ('exe', 0.04204815554423693), ('ds', 0.03802008370459583), ('myPath', 0.03519794712842561), ('helper', 0.029304218829982336), ('ret', 0.026210056702783636), ('commandLine', 0.024576889968473927)]
        fr.setProject(getProject());
        fr.setFile(stylesheet);
        configureLiaison(fr);
    }

    /**
     * Loads the stylesheet and set xsl:param parameters.
     *
     * @param stylesheet the resource from which to load the stylesheet.
     * @exception BuildException if the stylesheet cannot be loaded.
     * @since Ant 1.7
     */
    protected void configureLiaison(final Resource stylesheet) throws BuildException {
// stylesheet           No	: [('xsl', 0.5303249577506062), ('resource', 0.37684243527664635), ('initialResources', 0.3267930188457496), ('r', 0.3228682277983449), ('fp', 0.10616500173986808), ('source', 0.09887703036970744), ('file', 0.04992245749788257), ('r1', 0.04970035171126664), ('e', 0.04859918153526591), ('name', 0.04544023229371882)]
        if (stylesheetLoaded && reuseLoadedStylesheet) {
            return;
        }
        stylesheetLoaded = true;
        try {
            log("Loading stylesheet " + stylesheet, Project.MSG_INFO);
            // We call liaison.configure() and then liaison.setStylesheet()
            // so that the internal variables of liaison can be set up
            if (liaison instanceof XSLTLiaison2) {
                ((XSLTLiaison2) liaison).configure(this);
            }
            if (liaison instanceof XSLTLiaison3) {
                // If we are here we can set the stylesheet as a
                // resource
                ((XSLTLiaison3) liaison).setStylesheet(stylesheet);
            } else {
                // If we are here we cannot set the stylesheet as
                // a resource, but we can set it as a file. So,
                // we make an attempt to get it as a file
                final FileProvider fp = stylesheet.as(FileProvider.class);
// fp                   1	: [('xsl', 0.7651624785421287), ('fp', 0.7402946785438063), ('up', 0.06361375253023646), ('stylesheet', 0.053046530179365455), ('a', 0.03855460024727148), ('view', 0.03816621654781684), ('r', 0.03289697756217804), ('fileResource1', 0.03278223677130157), ('fileResource2', 0.03278209852938485), ('f', 0.03169439076768295)]
                if (fp != null) {
                    liaison.setStylesheet(fp.getFile());
                } else {
                    handleError(liaison.getClass().toString() + " accepts the stylesheet only as a file");
                    return;
                }
            }
            for (final Param p : params) {
// p                    3	: [('param', 0.6666878538568207), ('fileNameParameter', 0.18940075580275292), ('fileDirParameter', 0.18940061755349727), ('p', 0.16684407551572336), ('matchingEntry', 0.06877434453082765), ('e', 0.06148894240552952), ('classname', 0.05298245075592394), ('javaFile', 0.051412058846272736), ('paramx', 0.04167098744762953), ('attributeName', 0.040044331307721615)]
                if (p.shouldUse()) {
                    final Object evaluatedParam = evaluateParam(p);
// evaluatedParam       No	: [('o', 0.6102675598893733), ('entry', 0.6029214996936281), ('r', 0.19542231860686712), ('value', 0.182322908226377), ('name', 0.1522144269960395), ('arg', 0.1389255316416437), ('buildNumber', 0.0934800522881535), ('v', 0.08987150564268938), ('realObj', 0.08841985553485289), ('matchingEntry', 0.06877434453082765)]
                    if (liaison instanceof XSLTLiaison4) {
                        ((XSLTLiaison4) liaison).addParam(p.getName(), evaluatedParam);
                    } else if (evaluatedParam == null || evaluatedParam instanceof String) {
                        liaison.addParam(p.getName(), (String) evaluatedParam);
                    } else {
                        log("XSLTLiaison '" + liaison.getClass().getName() + "' supports only String parameters. Converting parameter '" + p.getName() + "' to its String value '" + evaluatedParam, Project.MSG_WARN);
                        liaison.addParam(p.getName(), String.valueOf(evaluatedParam));
                    }
                }
            }
        } catch (final Exception ex) {
// ex                   1	: [('e', 0.6832411589941966), ('ex', 0.3338704110833958), ('x', 0.05805362587694279), ('exc', 0.028859129392572604), ('name', 0.003774452156098644), ('i', 0.0025297136815461787), ('p', 0.0022330264512535956), ('project', 0.0021904485059657023), ('file', 0.0021065835999711023), ('value', 0.0017859678899673876)]
            log("Failed to transform using stylesheet " + stylesheet, Project.MSG_INFO);
            handleTransformationError(ex);
        }
    }

    /**
     * Evaluates parameter expression according to its type.
     *
     * @param param parameter from Ant build file
     * @return value to be passed to XSLT as parameter
     * @throws IllegalArgumentException if param type is unsupported
     * @throws NumberFormatException if expression of numeric type is not
     * desired numeric type
     * @throws XPathExpressionException if XPath expression can not be compiled
     * @since Ant 1.9.3
     */
    private Object evaluateParam(final Param param) throws XPathExpressionException {
// param                No	: [('p', 0.8335117006580892), ('expression', 0.5000523568207287), ('paramx', 0.041670987418273735), ('i', 0.007221303103901564), ('name', 0.0061534916673352995), ('b', 0.005880634358213717), ('value', 0.005420040438239479), ('file', 0.004260708764856733), ('path', 0.003640615907470202), ('line', 0.0036400629398033275)]
        final String typeName = param.getType();
// typeName             No	: [('fs', 0.5004953301237484), ('i', 0.1539643761701457), ('c', 0.1506555433628438), ('value', 0.13731837299203598), ('name', 0.08306597842033166), ('specificationVersion', 0.07526507150748644), ('time', 0.07520924799472659), ('size', 0.07520341352106569), ('method', 0.07519643202631245), ('level', 0.07519594816033945)]
        final String expression = param.getExpression();
// expression           No	: [('val', 0.8750477614926577), ('progressRegExp', 0.5357252566531038), ('bis', 0.5001101287187709), ('line', 0.26096874480914556), ('buildNumber', 0.2608261917391211), ('param', 0.23338635894289383), ('halt', 0.15357871837795295), ('parameter', 0.11670445292254152), ('message', 0.07972144974856407), ('haltOnFailure', 0.07679486336039851)]
        ParamType type;
// type                 2	: [('state', 0.47108068899991307), ('testExecutionResult', 0.38597872357281104), ('type', 0.12512179672822607), ('result', 0.12357315435974317), ('file', 0.12133965771504375), ('path', 0.12010520084911182), ('baseURL', 0.11858042665946689), ('cmd', 0.07553953998077356), ('name', 0.06936242210617198), ('i', 0.06377734336757919)]
        if (typeName == null || typeName.isEmpty()) {
            // String is default
            type = ParamType.STRING;
        } else {
            try {
                type = ParamType.valueOf(typeName);
            } catch (final IllegalArgumentException e) {
// e                    2	: [('ex', 0.5666295456612033), ('typeClass', 0.5000279177491318), ('e', 0.3799892734644444), ('l', 0.004057962851972621), ('offset', 0.003976588828766798), ('name', 0.0036469110213933234), ('p', 0.0031471665782486663), ('result', 0.0029822955454522986), ('value', 0.002843678624226581), ('s', 0.0027561891139727566)]
                throw new IllegalArgumentException("Invalid XSLT parameter type: " + typeName, e);
            }
        }
        switch(type) {
            case STRING:
                return expression;
            case BOOLEAN:
                return Boolean.parseBoolean(expression);
            case DOUBLE:
                return Double.parseDouble(expression);
            case INT:
                return Integer.parseInt(expression);
            case LONG:
                return Long.parseLong(expression);
            default:
                // XPath expression
                final QName xpathType = ParamType.XPATH_TYPES.get(type);
// xpathType            No	: [('zOut', 0.5026445636086326), ('instance', 0.250458590962681), ('typeClass', 0.2502236350404691), ('driveSpec', 0.020420928781072153), ('overwrite', 0.01661582476001444), ('driveSpecLower', 0.012253262579102402), ('symbolicLink', 0.012249944773101155), ('value', 0.009551922997925925), ('haltOnError', 0.008391059661294489), ('f', 0.006694390767682944)]
                if (xpathType == null) {
                    throw new IllegalArgumentException("Invalid XSLT parameter type: " + typeName);
                }
                final XPathExpression xpe = xpath.compile(expression);
// xpe                  No	: [('result', 0.060528355146956106), ('newFilter', 0.021862550513660862), ('p', 0.01850800239719346), ('cmd', 0.01666048724808694), ('buf', 0.013182912672467394), ('c', 0.011915487661322255), ('exe', 0.011007812400096453), ('commandLine', 0.0109612910055086), ('tool', 0.010934887149826824), ('ph', 0.009762376092070995)]
                // null = evaluate XPath on empty XML document
                return xpe.evaluate((Object) null, xpathType);
        }
    }

    /**
     * Sets file parameter(s) for directory and filename if the attribute
     * 'filenameparameter' or 'filedirparameter' are set in the task.
     *
     * @param  liaison    to change parameters for
     * @param  inFile     to get the additional file information from
     * @throws Exception  if an exception occurs on filename lookup
     *
     * @since Ant 1.7
     */
    private void setLiaisonDynamicFileParameters(final XSLTLiaison liaison, final File inFile) throws Exception {
// liaison              1	: [('ze', 0.5361854864215322), ('liaison', 0.25002668345676515), ('propertyValue', 0.07147398078508908), ('cmd', 0.04390998639636185), ('offset', 0.03625063147046681), ('buf', 0.015889871386269735), ('project', 0.015387392649939168), ('toExecute', 0.013911919149696177), ('writer', 0.010977701006441035), ('redirector', 0.01085044463627932)]
// inFile               No	: [('dir', 0.15001336268920204), ('aLocalFile', 0.11407105469933679), ('file', 0.1065681696918881), ('name', 0.07660998594880951), ('relativePath', 0.07482477875379932), ('destFile', 0.05081773703005531), ('localFile', 0.04745144581202609), ('expandedLine', 0.047038052946304525), ('f', 0.038372667522262684), ('filename', 0.03799362047763145)]
        // NOSONAR
        if (fileNameParameter != null) {
            liaison.addParam(fileNameParameter, inFile.getName());
        }
        if (fileDirParameter != null) {
            final String fileName = FileUtils.getRelativePath(baseDir, inFile);
// fileName             No	: [('dir', 0.16055880399742353), ('msg', 0.13705910320752884), ('path', 0.07858182935098498), ('parent', 0.07606558554894735), ('currentParent', 0.07073559297850808), ('folder', 0.07073545473842598), ('type', 0.05285722814788565), ('systemid', 0.05250972027630079), ('fullpath', 0.05241520542890656), ('actual', 0.05240553214602146)]
            final File file = new File(fileName);
// file                 3	: [('helper', 0.7550231123554008), ('modules', 0.06530805888924912), ('basedir', 0.05416403053533064), ('file', 0.04963181142600935), ('destDir', 0.041085015182481234), ('java1', 0.03925069486035851), ('java2', 0.03925069486035851), ('javaccJar', 0.039184303240512024), ('workDir', 0.028103248251902784), ('targetFile', 0.02656534072465039)]
            // Give always a slash as file separator, so the stylesheet could be sure about that
            // Use '.' so a dir + "/" + name would not result in an absolute path
            liaison.addParam(fileDirParameter, file.getParent() != null ? file.getParent().replace('\\', '/') : ".");
        }
    }

    /**
     * Create the factory element to configure a trax liaison.
     * @return the newly created factory element.
     * @throws BuildException if the element is created more than one time.
     */
    public Factory createFactory() throws BuildException {
        if (factory != null) {
            handleError("'factory' element must be unique");
        } else {
            factory = new Factory();
        }
        return factory;
    }

    /**
     * Throws an exception with the given message if failOnError is
     * true, otherwise logs the message using the WARN level.
     *
     * @param msg String
     * @since Ant 1.8.0
     */
    protected void handleError(final String msg) {
// msg                  0	: [('msg', 0.35564816557467643), ('e', 0.28299991162098787), ('message', 0.19899890101175874), ('ex', 0.11204047745730478), ('name', 0.09381721696295152), ('ioe', 0.08419998398464977), ('toExecute', 0.0487516532343539), ('value', 0.02470069223988621), ('t', 0.024652969710434344), ('commandline', 0.02444496684016686)]
        if (failOnError) {
            throw new BuildException(msg, getLocation());
        }
        log(msg, Project.MSG_WARN);
    }

    /**
     * Throws an exception with the given nested exception if
     * failOnError is true, otherwise logs the message using the WARN
     * level.
     *
     * @param ex Throwable
     * @since Ant 1.8.0
     */
    protected void handleError(final Throwable ex) {
// ex                   2	: [('t', 0.4294924473052515), ('e', 0.28299991162098787), ('ex', 0.10906730879000538), ('msg', 0.10219736146386868), ('exception', 0.09078394534793288), ('ioe', 0.08419998398464977), ('exc', 0.08344675740939447), ('message', 0.07231956046709365), ('throwable', 0.05057212477463221), ('e1', 0.038927135141451956)]
        if (failOnError) {
            throw new BuildException(ex);
        }
        log("Caught an exception: " + ex, Project.MSG_WARN);
    }

    /**
     * Throws an exception with the given nested exception if
     * failOnError and failOnTransformationError are true, otherwise
     * logs the message using the WARN level.
     *
     * @param ex Exception
     * @since Ant 1.8.0
     */
    protected void handleTransformationError(final Exception ex) {
// ex                   1	: [('e', 0.5511464570352809), ('ex', 0.20128122659862482), ('msg', 0.10219736146386868), ('ioe', 0.08419998398464977), ('message', 0.07231956046709365), ('x', 0.05039632191524697), ('file', 0.03474703808372972), ('link', 0.029303907004578446), ('d', 0.028626754748121114), ('prefix', 0.026469407885625355)]
        if (failOnError && failOnTransformationError) {
            throw new BuildException(ex);
        }
        log("Caught an error during transformation: " + ex, Project.MSG_WARN);
    }

    /**
     * The factory element to configure a transformer factory
     * @since Ant 1.6
     */
    public static class Factory {

        /**
         * the factory class name to use for TraXLiaison
         */
        private String name;

        /**
         * the list of factory attributes to use for TraXLiaison
         */
        private final List<Attribute> attributes = new ArrayList<>();

        /**
         * the list of factory features to use for TraXLiaison
         */
        private final List<Feature> features = new ArrayList<>();

        /**
         * @return the name of the factory.
         */
        public String getName() {
            return name;
        }

        /**
         * Set the name of the factory
         * @param name the name of the factory.
         */
        public void setName(final String name) {
// name                 0	: [('name', 0.8253012655543025), ('test', 0.05822995119659478), ('aName', 0.057947748783503686), ('normalizedName', 0.03787271154593717), ('value', 0.01743983153146476), ('other', 0.015895038861217244), ('message', 0.007012675981913621), ('output', 0.004390622031705773), ('s', 0.004327295443260173), ('line', 0.0034359398175628788)]
            this.name = name;
        }

        /**
         * Create an instance of a factory attribute.
         * @param attr the newly created factory attribute
         */
        public void addAttribute(final Attribute attr) {
// attr                 No	: [('feature', 0.7554435270355915), ('attribute', 0.667951192665634), ('pair', 0.08365139408011352), ('rc', 0.0093567083914954), ('name', 0.005591639748674456), ('classpath', 0.005487349141618403), ('classpathAttribute', 0.0054402092736210925), ('instr', 0.005388225539820228), ('r', 0.004915307971054906), ('c', 0.004676609635261387)]
            attributes.add(attr);
        }

        /**
         * return the attribute elements.
         * @return the enumeration of attributes
         */
        public Enumeration<Attribute> getAttributes() {
            return Collections.enumeration(attributes);
        }

        /**
         * Create an instance of a factory feature.
         * @param feature the newly created feature
         * @since Ant 1.9.8
         */
        public void addFeature(final Feature feature) {
// feature              No	: [('reason', 0.16670772693786293), ('rc', 0.0374268335659816), ('name', 0.022366558994697822), ('instr', 0.021552902159280914), ('r', 0.019661231884219622), ('c', 0.018706438541045547), ('fileNameMapper', 0.01833788771824547), ('i', 0.013862305372928077), ('p', 0.013424462700167838), ('set', 0.012810362774926498)]
            features.add(feature);
        }

        /**
         * The configured features.
         * @since Ant 1.9.8
         *
         * @return Iterable&lt;Feature&gt;
         */
        public Iterable<Feature> getFeatures() {
            return features;
        }

        /**
         * A JAXP factory attribute. This is mostly processor specific, for
         * example for Xalan 2.3+, the following attributes could be set:
         * <ul>
         *  <li>http://xml.apache.org/xalan/features/optimize (true|false) </li>
         *  <li>http://xml.apache.org/xalan/features/incremental (true|false) </li>
         * </ul>
         */
        public static class Attribute extends ProjectComponent implements DynamicConfigurator {

            /**
             * attribute name, mostly processor specific
             */
            private String name;

            /**
             * attribute value, often a boolean string
             */
            private Object value;

            /**
             * @return the attribute name.
             */
            public String getName() {
                return name;
            }

            /**
             * @return the attribute value.
             */
            public Object getValue() {
                return value;
            }

            /**
             * Not used.
             * @param name not used
             * @return null
             * @throws BuildException never
             */
            @Override
            public Object createDynamicElement(final String name) throws BuildException {
// name                 0	: [('name', 0.09279962734386976), ('message', 0.028050702845260303), ('value', 0.02470068746995026), ('output', 0.017562487314110174), ('s', 0.01730917974951395), ('line', 0.013743758099798152), ('pattern', 0.012763575956899585), ('text', 0.011258255674548745), ('classname', 0.011106847349541581), ('msg', 0.010637267040441562)]
                return null;
            }

            /**
             * Set an attribute.
             * Only "name" and "value" are supported as names.
             * @param name the name of the attribute
             * @param value the value of the attribute
             * @throws BuildException on error
             */
            @Override
            public void setDynamicAttribute(final String name, final String value) throws BuildException {
// name                 5	: [('msg', 0.6282262818798879), ('e', 0.16345280164558662), ('compilerType', 0.14109763463247146), ('paramname', 0.14107475074981415), ('summaryOption', 0.12653432069292475), ('name', 0.09279895549916178), ('anImplementation', 0.09200455713643066), ('ftp', 0.07383301570565373), ('s', 0.05595185165536725), ('ex', 0.053962495955205074)]
// value                0	: [('value', 0.8423382881421482), ('name', 0.8411294738104861), ('s', 0.5275321189030637), ('key', 0.12390923961907685), ('property', 0.12384137133793523), ('prop', 0.12381954459860754), ('nodeValue', 0.12381313428797663), ('loaderName', 0.12381237210723495), ('compiledScriptRefName', 0.12381178274035644), ('oldKey', 0.12381136618734105)]
                // only 'name' and 'value' exist.
                if ("name".equalsIgnoreCase(name)) {
                    this.name = value;
                } else if ("value".equalsIgnoreCase(name)) {
                    // a value must be of a given type
                    // say boolean|integer|string that are mostly used.
                    if ("true".equalsIgnoreCase(value)) {
                        this.value = Boolean.TRUE;
                    } else if ("false".equalsIgnoreCase(value)) {
                        this.value = Boolean.FALSE;
                    } else {
                        try {
                            this.value = Integer.valueOf(value);
                        } catch (final NumberFormatException e) {
// e                    1	: [('nfe', 0.7147338105539275), ('e', 0.24514438249374343), ('px', 0.0017867599103849878), ('nfe1', 0.0017867253508231035), ('ex', 0.0009105604763560714), ('ignored', 0.000894248363146688), ('b', 3.173451562394901e-05), ('length', 3.069772876742041e-05), ('classpath', 1.314147133020269e-05), ('block', 7.784739238138228e-06)]
                            this.value = value;
                        }
                    }
                } else if ("valueref".equalsIgnoreCase(name)) {
                    this.value = getProject().getReference(value);
                } else if ("classloaderforpath".equalsIgnoreCase(name)) {
                    this.value = ClasspathUtils.getClassLoaderForPath(getProject(), new Reference(getProject(), value));
                } else {
                    throw new BuildException("Unsupported attribute: %s", name);
                }
            }
        }

        // -- class Attribute
        /**
         * A feature for the TraX factory.
         * @since Ant 1.9.8
         */
        public static class Feature {

            private String name;

            private boolean value;

            public Feature() {
            }

            public Feature(String name, boolean value) {
// name                 0	: [('name', 0.8253012655543025), ('normalizedName', 0.03787271154593717), ('value', 0.01743983153146476), ('other', 0.015895038861217244), ('s', 0.010832442447690875), ('message', 0.008427482528768892), ('msg', 0.007639334405270108), ('uri', 0.00709272686411501), ('target', 0.00694392573208436), ('line', 0.005779721828559096)]
// value                0	: [('value', 0.8780552557932503), ('haltOnError', 0.31091058284284917), ('exists', 0.30975604548185337), ('append', 0.10528535761379731), ('preserveLeadingSlashes', 0.10327094933516644), ('user', 0.040975166686690566), ('encodable', 0.013239061722957059), ('file', 0.011690788761859945), ('path', 0.011652034665900797), ('overwrite', 0.006035317540036769)]
                this.name = name;
                this.value = value;
            }

            /**
             * @param name the feature name.
             */
            public void setName(String name) {
// name                 0	: [('name', 0.8253012655543025), ('normalizedName', 0.03787271154593717), ('s', 0.033065253469065574), ('value', 0.023997661159191656), ('pattern', 0.02250229386664753), ('n', 0.02177662265838697), ('fname', 0.02145630924714401), ('other', 0.015895038861217244), ('vpath', 0.0028976415110043285), ('trim', 0.002259564903932684)]
                this.name = name;
            }

            /**
             * @param value the feature value.
             */
            public void setValue(boolean value) {
// value                0	: [('value', 0.8777107594001764), ('file', 0.0295479316190028), ('path', 0.02950917752304365), ('b', 0.011050657308995493), ('attrs', 0.003614430483464086), ('verbose', 0.0021036283232978644), ('append', 0.0015560574001220978), ('args', 0.0015270389188943305), ('field', 0.001435885935003963), ('beginToken', 0.0014323936139380442)]
                this.value = value;
            }

            /**
             * @return the feature name.
             */
            public String getName() {
                return name;
            }

            /**
             * @return the feature value.
             */
            public boolean getValue() {
                return value;
            }
        }
    }

    // -- class Factory
    /**
     * Mapper implementation of the "traditional" way &lt;xslt&gt;
     * mapped filenames.
     *
     * <p>If the file has an extension, chop it off.  Append whatever
     * the user has specified as extension or ".html".</p>
     *
     * @since Ant 1.6.2
     */
    private class StyleMapper implements FileNameMapper {

        @Override
        public void setFrom(final String from) {
// from                 No	: [('ignore', 0.7505172876448591), ('name', 0.023454302300658165), ('message', 0.007012675711315076), ('value', 0.006175171867487565), ('output', 0.004390621828527543), ('s', 0.004327294937378487), ('line', 0.003435939524949538), ('pattern', 0.003190893989224896), ('text', 0.0028145639186371862), ('classname', 0.0027767118373853952)]
        }

        @Override
        public void setTo(final String to) {
// to                   No	: [('ignore', 0.7505172876448591), ('name', 0.023454302300658165), ('message', 0.007012675711315076), ('value', 0.006175171867487565), ('output', 0.004390621828527543), ('s', 0.004327294937378487), ('line', 0.003435939524949538), ('pattern', 0.003190893989224896), ('text', 0.0028145639186371862), ('classname', 0.0027767118373853952)]
        }

        @Override
        public String[] mapFileName(String xmlFile) {
// xmlFile              No	: [('sourceFileName', 0.6878677677950179), ('tmpdir', 0.1474287432623162), ('dir1', 0.09827895716240073), ('name', 0.07176503326951793), ('value', 0.06896477262304417), ('s', 0.06520818093486036), ('fileName', 0.06304760569707463), ('root', 0.05188876227326622), ('result', 0.04831725240868999), ('extPointName', 0.03610800381709809)]
            final int dotPos = xmlFile.lastIndexOf('.');
// dotPos               No	: [('index', 0.15574738619267003), ('suffixPos', 0.10044580972776544), ('lastDot', 0.10044449290768542), ('size', 0.05474744423298737), ('idx', 0.05045003491090336), ('classIndex', 0.05022477905815322), ('startExtn', 0.050222700146595654), ('thech', 0.048958151195296444), ('pos', 0.039487212733700004), ('len', 0.03701228668234064)]
            if (dotPos > 0) {
                xmlFile = xmlFile.substring(0, dotPos);
            }
            return new String[] { xmlFile + targetExtension };
        }
    }

    /**
     * Configuration for Xalan2 traces.
     *
     * @since Ant 1.8.0
     */
    public final class TraceConfiguration {

        private boolean elements, extension, generation, selection, templates;

        /**
         * Set to true if the listener is to print events that occur
         * as each node is 'executed' in the stylesheet.
         *
         * @param b boolean
         */
        public void setElements(final boolean b) {
// b                    0	: [('b', 0.16574118497307813), ('verbose', 0.08989094051446246), ('value', 0.019825784806803234), ('include', 0.014295620023115617), ('v', 0.01415671502344134), ('append', 0.013891633243744598), ('f', 0.010938263789962878), ('failOnError', 0.010810944674345508), ('quiet', 0.010693918926724364), ('yesOrNo', 0.010459590951317868)]
            elements = b;
        }

        /**
         * True if the listener is to print events that occur as each
         * node is 'executed' in the stylesheet.
         *
         * @return boolean
         */
        public boolean getElements() {
            return elements;
        }

        /**
         * Set to true if the listener is to print information after
         * each extension event.
         *
         * @param b boolean
         */
        public void setExtension(final boolean b) {
// b                    0	: [('b', 0.16574118497307813), ('ext', 0.14290949635550965), ('verbose', 0.08989094051446246), ('extensionAdapter', 0.07145851596169503), ('originalExtension', 0.0714574100410382), ('value', 0.019825784806803234), ('include', 0.014295620023115617), ('v', 0.01415671502344134), ('append', 0.013891633243744598), ('f', 0.010938263789962878)]
            extension = b;
        }

        /**
         * True if the listener is to print information after each
         * extension event.
         *
         * @return boolean
         */
        public boolean getExtension() {
            return extension;
        }

        /**
         * Set to true if the listener is to print information after
         * each result-tree generation event.
         *
         * @param b boolean
         */
        public void setGeneration(final boolean b) {
// b                    0	: [('b', 0.16574118497307813), ('verbose', 0.08989094051446246), ('value', 0.019825784806803234), ('include', 0.014295620023115617), ('v', 0.01415671502344134), ('append', 0.013891633243744598), ('f', 0.010938263789962878), ('failOnError', 0.010810944674345508), ('quiet', 0.010693918926724364), ('yesOrNo', 0.010459590951317868)]
            generation = b;
        }

        /**
         * True if the listener is to print information after each
         * result-tree generation event.
         *
         * @return boolean
         */
        public boolean getGeneration() {
            return generation;
        }

        /**
         * Set to true if the listener is to print information after
         * each selection event.
         *
         * @param b boolean
         */
        public void setSelection(final boolean b) {
// b                    0	: [('b', 0.16574118497307813), ('verbose', 0.08989094051446246), ('value', 0.019825784806803234), ('include', 0.014295620023115617), ('v', 0.01415671502344134), ('append', 0.013891633243744598), ('f', 0.010938263789962878), ('failOnError', 0.010810944674345508), ('quiet', 0.010693918926724364), ('yesOrNo', 0.010459590951317868)]
            selection = b;
        }

        /**
         * True if the listener is to print information after each
         * selection event.
         *
         * @return boolean
         */
        public boolean getSelection() {
            return selection;
        }

        /**
         * Set to true if the listener is to print an event whenever a
         * template is invoked.
         *
         * @param b boolean
         */
        public void setTemplates(final boolean b) {
// b                    0	: [('b', 0.16574118497307813), ('verbose', 0.08989094051446246), ('value', 0.019825784806803234), ('include', 0.014295620023115617), ('v', 0.01415671502344134), ('append', 0.013891633243744598), ('f', 0.010938263789962878), ('failOnError', 0.010810944674345508), ('quiet', 0.010693918926724364), ('yesOrNo', 0.010459590951317868)]
            templates = b;
        }

        /**
         * True if the listener is to print an event whenever a
         * template is invoked.
         *
         * @return boolean
         */
        public boolean getTemplates() {
            return templates;
        }

        /**
         * The stream to write traces to.
         *
         * @return OutputStream
         */
        public OutputStream getOutputStream() {
            return new LogOutputStream(XSLTProcess.this);
        }
    }
}
