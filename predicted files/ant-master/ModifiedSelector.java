// Type of training: selfTestingIdentifier
// Path to file: C:\Users\Igor\IdeaProjects\SLP-Core-0.3\java-projects\ant-master\src\main\org\apache\tools\ant\types\selectors\modifiedselector\ModifiedSelector.java
// Number of identifiers: 77	Accuracy: 33.77%	MRR: 41.11%
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
package org.apache.tools.ant.types.selectors.modifiedselector;

// Java
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.apache.tools.ant.BuildEvent;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.BuildListener;
import org.apache.tools.ant.IntrospectionHelper;
// Ant
import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.EnumeratedAttribute;
import org.apache.tools.ant.types.Parameter;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.types.Resource;
import org.apache.tools.ant.types.resources.FileResource;
import org.apache.tools.ant.types.resources.selectors.ResourceSelector;
import org.apache.tools.ant.types.selectors.BaseExtendSelector;
import org.apache.tools.ant.util.FileUtils;
import org.apache.tools.ant.util.ResourceUtils;

/**
 * <p>Selector class that uses <i>Algorithm</i>, <i>Cache</i> and <i>Comparator</i>
 * for its work.
 * The <i>Algorithm</i> is used for computing a hashvalue for a file.
 * The <i>Comparator</i> decides whether to select or not.
 * The <i>Cache</i> stores the other value for comparison by the <i>Comparator</i>
 * in a persistent manner.</p>
 *
 * <p>The ModifiedSelector is implemented as a <b>CoreSelector</b> and uses default
 * values for all its attributes therefore the simplest example is</p><pre>
 *   &lt;copy todir="dest"&gt;
 *       &lt;filelist dir="src"&gt;
 *           &lt;modified/&gt;
 *       &lt;/filelist&gt;
 *   &lt;/copy&gt;
 * </pre>
 *
 * <p>The same example rewritten as CoreSelector with setting the all values
 * (same as defaults are) would be</p><pre>
 *   &lt;copy todir="dest"&gt;
 *       &lt;filelist dir="src"&gt;
 *           &lt;modified update="true"
 *                     cache="propertyfile"
 *                     algorithm="digest"
 *                     comparator="equal"&gt;
 *               &lt;param name="cache.cachefile"     value="cache.properties"/&gt;
 *               &lt;param name="algorithm.algorithm" value="MD5"/&gt;
 *           &lt;/modified&gt;
 *       &lt;/filelist&gt;
 *   &lt;/copy&gt;
 * </pre>
 *
 * <p>And the same rewritten as CustomSelector would be</p><pre>
 *   &lt;copy todir="dest"&gt;
 *       &lt;filelist dir="src"&gt;
 *           &lt;custom class="org.apache.tools.ant.type.selectors.ModifiedSelector"&gt;
 *               &lt;param name="update"     value="true"/&gt;
 *               &lt;param name="cache"      value="propertyfile"/&gt;
 *               &lt;param name="algorithm"  value="digest"/&gt;
 *               &lt;param name="comparator" value="equal"/&gt;
 *               &lt;param name="cache.cachefile"     value="cache.properties"/&gt;
 *               &lt;param name="algorithm.algorithm" value="MD5"/&gt;
 *           &lt;/custom&gt;
 *       &lt;/filelist&gt;
 *   &lt;/copy&gt;
 * </pre>
 *
 * <p>If you want to provide your own interface implementation you can do
 * that via the *classname attributes. If the classes are not on Ant's core
 * classpath, you will have to provide the path via nested &lt;classpath&gt;
 * element, so that the selector can find the classes.</p><pre>
 *   &lt;modified cacheclassname="com.mycompany.MyCache"&gt;
 *       &lt;classpath&gt;
 *           &lt;pathelement location="lib/mycompany-antutil.jar"/&gt;
 *       &lt;/classpath&gt;
 *   &lt;/modified&gt;
 * </pre>
 *
 * <p>All these three examples copy the files from <i>src</i> to <i>dest</i>
 * using the ModifiedSelector. The ModifiedSelector uses the <i>PropertyfileCache
 * </i>, the <i>DigestAlgorithm</i> and the <i>EqualComparator</i> for its
 * work. The PropertyfileCache stores key-value-pairs in a simple java
 * properties file. The filename is <i>cache.properties</i>. The <i>update</i>
 * flag lets the selector update the values in the cache (and on first call
 * creates the cache). The <i>DigestAlgorithm</i> computes a hashvalue using the
 * java.security.MessageDigest class with its MD5-Algorithm and its standard
 * provider. The new computed hashvalue and the stored one are compared by
 * the <i>EqualComparator</i> which returns 'true' (more correct a value not
 * equals zero (1)) if the values are not the same using simple String
 * comparison.</p>
 *
 * <p>A useful scenario for this selector is inside a build environment
 * for homepage generation (e.g. with <a href="https://forrest.apache.org/">
 * Apache Forrest</a>).</p><pre>
 * &lt;target name="generate-and-upload-site"&gt;
 *     &lt;echo&gt; generate the site using forrest &lt;/echo&gt;
 *     &lt;antcall target="site"/&gt;
 *
 *     &lt;echo&gt; upload the changed files &lt;/echo&gt;
 *     &lt;ftp server="${ftp.server}" userid="${ftp.user}" password="${ftp.pwd}"&gt;
 *         &lt;fileset dir="htdocs/manual"&gt;
 *             &lt;modified/&gt;
 *         &lt;/fileset&gt;
 *     &lt;/ftp&gt;
 * &lt;/target&gt;
 * </pre><p>Here all <b>changed</b> files are uploaded to the server. The
 * ModifiedSelector saves therefore much upload time.</p>
 *
 * <p>This selector uses reflection for setting the values of its three interfaces
 * (using org.apache.tools.ant.IntrospectionHelper) therefore no special
 * 'configuration interfaces' has to be implemented by new caches, algorithms or
 * comparators. All present <i>set</i>XX methods can be used. E.g. the DigestAlgorithm
 * can use a specified provider for computing its value. For selecting this
 * there is a <i>setProvider(String providername)</i> method. So you can use
 * a nested <i>&lt;param name="algorithm.provider" value="MyProvider"/&gt;</i>.
 *
 * @since  Ant 1.6
 */
public class ModifiedSelector extends BaseExtendSelector implements BuildListener, ResourceSelector {

    private static final String CACHE_PREFIX = "cache.";

    private static final String ALGORITHM_PREFIX = "algorithm.";

    private static final String COMPARATOR_PREFIX = "comparator.";

    // -----  attributes  -----
    /**
     * Cache name for later instantiation.
     */
    private CacheName cacheName = null;

    /**
     * User specified classname for Cache.
     */
    private String cacheClass;

    /**
     * Algorithm name for later instantiation.
     */
    private AlgorithmName algoName = null;

    /**
     * User specified classname for Algorithm.
     */
    private String algorithmClass;

    /**
     * Comparator name for later instantiation.
     */
    private ComparatorName compName = null;

    /**
     * User specified classname for Comparator.
     */
    private String comparatorClass;

    /**
     * Should the cache be updated?
     */
    private boolean update = true;

    /**
     * Are directories selected?
     */
    private boolean selectDirectories = true;

    /**
     * Should Resources without an InputStream, and
     * therefore without checking, be selected?
     */
    private boolean selectResourcesWithoutInputStream = true;

    /**
     * Delay the writing of the cache file
     */
    private boolean delayUpdate = true;

    // ----- internal member variables -----
    /**
     * How should the cached value and the new one compared?
     */
    private Comparator<? super String> comparator = null;

    /**
     * Algorithm for computing new values and updating the cache.
     */
    private Algorithm algorithm = null;

    /**
     * The Cache containing the old values.
     */
    private Cache cache = null;

    /**
     * Count of modified properties
     */
    private int modified = 0;

    /**
     * Flag whether this object is configured. Configuration is only done once.
     */
    private boolean isConfigured = false;

    /**
     * Parameter vector with parameters for later initialization.
     * @see #configure
     */
    private List<Parameter> configParameter = Collections.synchronizedList(new ArrayList<>());

    /**
     * Parameter vector with special parameters for later initialization.
     * The names have the pattern '*.*', e.g. 'cache.cachefile'.
     * These parameters are used <b>after</b> the parameters with the pattern '*'.
     * @see #configure
     */
    private List<Parameter> specialParameter = Collections.synchronizedList(new ArrayList<>());

    /**
     * The classloader of this class.
     */
    private ClassLoader myClassLoader = null;

    /**
     * provided classpath for the classloader
     */
    private Path classpath = null;

    // -----  constructors  -----
    /**
     * Bean-Constructor.
     */
    public ModifiedSelector() {
    }

    // ----- configuration  -----
    /**
     * Overrides BaseSelector.verifySettings().
     */
    @Override
    public void verifySettings() {
        configure();
        if (cache == null) {
            setError("Cache must be set.");
        } else if (algorithm == null) {
            setError("Algorithm must be set.");
        } else if (!cache.isValid()) {
            setError("Cache must be proper configured.");
        } else if (!algorithm.isValid()) {
            setError("Algorithm must be proper configured.");
        }
    }

    /**
     * Configures this Selector.
     * Does this work only once per Selector object.
     * <p>Because some problems while configuring from &lt;custom&gt;Selector
     * the configuration is done in the following order:</p><ol>
     * <li>collect the configuration data</li>
     * <li>wait for the first isSelected() call</li>
     * <li>set the default values</li>
     * <li>set values for name pattern '*': update, cache, algorithm, comparator</li>
     * <li>set values for name pattern '*.*: cache.cachefile, ...</li>
     * </ol>
     * <p>This configuration algorithm is needed because you don't know
     * the order of arriving config-data. E.g. if you first set the
     * <i>cache.cachefilename</i> and after that the <i>cache</i> itself,
     * the default value for cachefilename is used, because setting the
     * cache implies creating a new Cache instance - with its defaults.</p>
     */
    public void configure() {
        // 
        // -----  The "Singleton"  -----
        // 
        if (isConfigured) {
            return;
        }
        isConfigured = true;
        // 
        // -----  Set default values  -----
        // 
        Project p = getProject();
// p                    2	: [('filename', 0.5095734883536789), ('project', 0.28926355405554716), ('p', 0.22476735155168476), ('aProj', 0.1489300500801775), ('dir', 0.027225472347838873), ('workDir', 0.024643567859360005), ('image', 0.018092871385116133), ('output', 0.017423170570315828), ('toDir', 0.017236121479101228), ('tmp', 0.013877169354526394)]
        String filename = "cache.properties";
// filename             No	: [('p', 0.50356173957536), ('executable', 0.2541753735530977), ('path', 0.13140628528644407), ('link1Rem', 0.12719666152411582), ('antHome', 0.11719236837267948), ('file', 0.06021414559876737), ('src', 0.0590179927532515), ('fileName', 0.0588131127548127), ('project', 0.05880103610504365), ('relativeFilename', 0.05859954369501078)]
        File cachefile;
// cachefile            No	: [('p', 0.5042441982369172), ('f', 0.06706644490173282), ('cmd', 0.058745948703817254), ('jmod', 0.056905294039955416), ('file', 0.040487243655271024), ('sb', 0.03584549558190128), ('buf', 0.026100995447981442), ('out', 0.022021601373978485), ('dir', 0.01771564396341806), ('log', 0.016454432032477127)]
        if (p != null) {
            // normal use inside Ant
            cachefile = new File(p.getBaseDir(), filename);
            // set self as a BuildListener to delay cachefile saves
            getProject().addBuildListener(this);
        } else {
            // no reference to project - e.g. during normal JUnit tests
            cachefile = new File(filename);
            setDelayUpdate(false);
        }
        Cache defaultCache = new PropertiesfileCache(cachefile);
// defaultCache         No	: [('b', 0.6577200484384097), ('cache', 0.5000465898744805), ('i', 0.001805217313769851), ('name', 0.0015382686792566797), ('value', 0.0013549130785888532), ('file', 0.001065103099387838), ('path', 0.0009100867155512394), ('line', 0.0009099484754691358), ('p', 0.0009054614691008683), ('project', 0.0008740739344921455)]
        Algorithm defaultAlgorithm = new DigestAlgorithm();
// defaultAlgorithm     No	: [('algorithm', 0.5001334184742492), ('algo', 0.5000410602711963), ('i', 0.003610434627539702), ('name', 0.0030765373585133595), ('b', 0.002940096876819394), ('value', 0.0027098261571777064), ('file', 0.002130206198775676), ('path', 0.0018201734311024788), ('line', 0.0018198969509382714), ('p', 0.0018109229382017368)]
        Comparator<? super String> defaultComparator = new EqualComparator();
// defaultComparator    No	: [('comparator', 0.2504321383072786), ('localComparator', 0.25002828565213836), ('test', 0.12562138198169345), ('test2', 0.1252009239271639), ('test1', 0.1252009239271639), ('comparatorInstance', 0.12520085480712284), ('files', 0.005950318291043798), ('i', 0.003610434627539702), ('name', 0.0030765373585133595), ('b', 0.002940096876819394)]
        // 
        // -----  Set the main attributes, pattern '*'  -----
        // 
        for (Parameter parameter : configParameter) {
// parameter            1	: [('param', 0.4671115651582004), ('parameter', 0.2789994239012291), ('rc', 0.03742684229768837), ('name', 0.02236662676429412), ('instr', 0.021552906992501245), ('r', 0.019661259307305383), ('line', 0.018738636494133244), ('c', 0.018706465184346766), ('file', 0.018357236151475064), ('fileNameMapper', 0.018337889542313388)]
            if (parameter.getName().indexOf('.') > 0) {
                // this is a *.* parameter for later use
                specialParameter.add(parameter);
            } else {
                useParameter(parameter);
            }
        }
        configParameter.clear();
        // specify the algorithm classname
        if (algoName != null) {
            // use Algorithm defined via name
            if ("hashvalue".equals(algoName.getValue())) {
                algorithm = new HashvalueAlgorithm();
            } else if ("digest".equals(algoName.getValue())) {
                algorithm = new DigestAlgorithm();
            } else if ("checksum".equals(algoName.getValue())) {
                algorithm = new ChecksumAlgorithm();
            } else if ("lastmodified".equals(algoName.getValue())) {
                algorithm = new LastModifiedAlgorithm();
            }
        } else if (algorithmClass != null) {
            // use Algorithm specified by classname
            algorithm = loadClass(algorithmClass, "is not an Algorithm.", Algorithm.class);
        } else {
            // nothing specified - use default
            algorithm = defaultAlgorithm;
        }
        // specify the cache classname
        if (cacheName != null) {
            // use Cache defined via name
            if ("propertyfile".equals(cacheName.getValue())) {
                cache = new PropertiesfileCache();
            }
        } else if (cacheClass != null) {
            // use Cache specified by classname
            cache = loadClass(cacheClass, "is not a Cache.", Cache.class);
        } else {
            // nothing specified - use default
            cache = defaultCache;
        }
        // specify the comparator classname
        if (compName != null) {
            // use Algorithm defined via name
            if ("equal".equals(compName.getValue())) {
                comparator = new EqualComparator();
            } else if ("rule".equals(compName.getValue())) {
                // TODO there is a problem with the constructor for the RBC.
                // you have to provide the rules in the constructors - no setters
                // available.
                throw new BuildException("RuleBasedCollator not yet supported.");
            // Have to think about lazy initialization here...  JHM
            // comparator = new java.text.RuleBasedCollator();
            }
        } else if (comparatorClass != null) {
            // use Algorithm specified by classname
            @SuppressWarnings("unchecked")
            Comparator<? super String> localComparator = loadClass(comparatorClass, "is not a Comparator.", Comparator.class);
// localComparator      No	: [('comparator', 0.2504321383072786), ('defaultComparator', 0.25002828565213836), ('test', 0.12562138198169345), ('test2', 0.1252009239271639), ('test1', 0.1252009239271639), ('comparatorInstance', 0.12520085480712284), ('files', 0.005950318291043798), ('i', 0.003610434627539702), ('name', 0.0030765373585133595), ('b', 0.002940096876819394)]
            comparator = localComparator;
        } else {
            // nothing specified - use default
            comparator = defaultComparator;
        }
        // 
        // -----  Set the special attributes, pattern '*.*'  -----
        // 
        specialParameter.forEach(this::useParameter);
        specialParameter.clear();
    }

    /**
     * Loads the specified class and initializes an object of that class.
     * Throws a BuildException using the given message if an error occurs during
     * loading/instantiation or if the object is not from the given type.
     * @param <T> desired type
     * @param classname the classname
     * @param msg the message-part for the BuildException
     * @param type the type to check against
     * @return a castable object
     */
    @SuppressWarnings("unchecked")
    protected <T> T loadClass(String classname, String msg, Class<? extends T> type) {
// classname            0	: [('classname', 0.5051775335426747), ('className', 0.20543666262172045), ('e', 0.1642678777613925), ('analyzerClassName', 0.0959885139819173), ('ftp', 0.07420788847964391), ('ex', 0.054233762189873615), ('name', 0.053390480012960734), ('helperClass', 0.048233994428940836), ('engineClassName', 0.04822332105993552), ('mainClassname', 0.04822165499818338)]
// msg                  No	: [('optional', 0.35719233271009176), ('mappedname', 0.17858428261599482), ('name', 0.05346579786235865), ('filename', 0.047529744464428494), ('value', 0.029322739845903407), ('resolve', 0.023840034999806532), ('encoding', 0.017587846696943812), ('prefix', 0.014580039578116955), ('tag', 0.013769398412892061), ('qname', 0.012730321486242643)]
// type                 No	: [('iter', 0.5961752429571782), ('mapper', 0.09608579664289511), ('ftp', 0.03785415641396605), ('prefix', 0.032477308355945096), ('file', 0.031465788248363526), ('f', 0.024773819822066803), ('s', 0.017628544984300335), ('clazz', 0.01452867805221784), ('p', 0.013069293039379955), ('localFile', 0.012954211133316225)]
        try {
            // load the specified class
            ClassLoader cl = getClassLoader();
// cl                   2	: [('clazz', 0.500590431532637), ('classLoader', 0.480489714213993), ('cl', 0.43264197358458306), ('def', 0.1980360668113825), ('c', 0.031962381441860095), ('helper', 0.03152226099335465), ('loader', 0.008895049467230067), ('f', 0.004919325248939104), ('ch', 0.0046425343155695915), ('al', 0.004516455177395382)]
            Class<?> clazz;
// clazz                7	: [('list', 0.25028914874419994), ('c', 0.19000617148725885), ('in', 0.16878588055123975), ('cl', 0.16777161035726176), ('def', 0.16716916634505596), ('event', 0.12603915305971014), ('defaultValue', 0.12511681581261108), ('clazz', 0.07671431894657921), ('theClass', 0.07306895405674321), ('genclass', 0.06520083975279051)]
            if (cl != null) {
                clazz = cl.loadClass(classname);
            } else {
                clazz = Class.forName(classname);
            }
            Object rv = clazz.newInstance();
// rv                   No	: [('method', 0.7000223105543567), ('o', 0.48405420355388934), ('value', 0.3758929863299833), ('field', 0.17501107319156878), ('instance', 0.11053605676422622), ('nodeObject', 0.10395607258142976), ('currValue', 0.041090575505406374), ('obj', 0.023305999472614615), ('target', 0.021113937790182717), ('expanded', 0.02069990544660905)]
            if (!type.isInstance(rv)) {
                throw new BuildException("Specified class (%s) %s", classname, msg);
            }
            return (T) rv;
        } catch (ClassNotFoundException e) {
// e                    0	: [('e', 0.4608690506078705), ('cnfe', 0.24094409055570043), ('ex', 0.1478917725565058), ('x', 0.022887449854423578), ('cnf1', 0.022883233587873694), ('cnf2', 0.022883233587873694), ('cnf3', 0.022883233587873694), ('cnfexcept', 0.00972533885103159), ('cnfe2', 0.00972533885103159), ('ce', 0.0004291278164515242)]
            throw new BuildException("Specified class (%s) not found.", classname);
        } catch (Exception e) {
// e                    0	: [('e', 0.563983241398157), ('ex', 0.2334127431539531), ('msg', 0.10219735856518988), ('ioe', 0.08419998332807777), ('message', 0.07231955788507098), ('t', 0.06344869951599263), ('exc', 0.055728524758199054), ('s', 0.023973260873358767), ('ie', 0.01784126892436277), ('e2', 0.011012111097548884)]
            throw new BuildException(e);
        }
    }

    // -----  the selection work  -----
    /**
     * Implementation of ResourceSelector.isSelected().
     *
     * @param resource The resource to check
     * @return whether the resource is selected
     * @see ResourceSelector#isSelected(Resource)
     */
    @Override
    public boolean isSelected(Resource resource) {
// resource             6	: [('r', 0.9093825593942799), ('another', 0.6666783149144241), ('fromResource', 0.6323564876014827), ('c', 0.5074273474911705), ('fileProvider', 0.1666709916475836), ('name', 0.15221252521143486), ('resource', 0.1324347898498186), ('source', 0.05893663485032506), ('en', 0.05058417680732518), ('file', 0.0497258308946554)]
        if (resource.isFilesystemOnly()) {
            // We have a 'resourced' file, so reconvert it and use
            // the 'old' implementation.
            FileResource fileResource = (FileResource) resource;
// fileResource         No	: [('file', 0.7756991786956388), ('f', 0.4624792146274148), ('scanner', 0.45008069011021845), ('resolver', 0.2537575233553918), ('fr', 0.17839916551043902), ('fs', 0.15036664994614612), ('basedir', 0.1250889763352286), ('f1', 0.11500667141866107), ('includedFile', 0.043301845770584664), ('line', 0.032695053218818416)]
            File file = fileResource.getFile();
// file                 0	: [('file', 0.8457173750275001), ('f', 0.033533218906343004), ('jmod', 0.028452646601679912), ('absBase', 0.027277970156199365), ('charset', 0.013663871527722506), ('dir', 0.008857820246140116), ('destFile', 0.006619486972905073), ('expected', 0.005857767995390384), ('baseDir', 0.005633025167964612), ('base', 0.005303957998321014)]
            String filename = fileResource.getName();
// filename             0	: [('filename', 0.8000611579909168), ('name', 0.046028780544340835), ('value', 0.0158102052331163), ('expectedResult', 0.013727825194242708), ('mainClass', 0.012066667585786258), ('output', 0.010454932489946181), ('filenames', 0.010433162059891993), ('file', 0.009813559286222856), ('classpath', 0.008882331571174334), ('line', 0.007749349708259244)]
            File basedir = fileResource.getBaseDir();
// basedir              0	: [('basedir', 0.5543676758773356), ('f', 0.10027247556773326), ('release', 0.0635457785042356), ('file', 0.05040821303919228), ('dir2', 0.047672002310768145), ('jmod', 0.04450582640643002), ('dir', 0.02651310612030953), ('r', 0.02184939643538473), ('commandDir', 0.020528226593541195), ('src', 0.020112966524992896)]
            return isSelected(basedir, filename, file);
        }
        try {
            // How to handle non-file-Resources? I copy temporarily the
            // resource to a file and use the file-implementation.
            FileUtils fu = FileUtils.getFileUtils();
// fu                   No	: [('fileUtils', 0.016067834562559963), ('i', 0.007220869255079404), ('name', 0.00615307471702672), ('b', 0.005880193753638788), ('value', 0.005419652314355413), ('file', 0.004260412397551352), ('path', 0.0036403468622049576), ('line', 0.003639793901876543), ('p', 0.003621845876403473), ('project', 0.003496295737968582)]
            File tmpFile = fu.createTempFile("modified-", ".tmp", null, true, false);
// tmpFile              No	: [('f', 0.6072321833879779), ('p', 0.14713566757881028), ('a', 0.13230646947734123), ('buildFile', 0.13222067846061894), ('newfile', 0.1321957993454439), ('basedir', 0.11476473151936072), ('file', 0.07688081341119679), ('release', 0.06354577857853948), ('commandLine', 0.04915372445109117), ('dir2', 0.047672002354800076)]
            Resource tmpResource = new FileResource(tmpFile);
// tmpResource          No	: [('name', 0.30077460534087064), ('zf', 0.1993661698604776), ('r1', 0.1638612218482646), ('r', 0.11329536784313993), ('stack', 0.09967325654538746), ('r2', 0.0827563147580171), ('s', 0.0809859249570735), ('fromFileAttribute', 0.0808338608667596), ('e', 0.05863217108124348), ('link', 0.04988801882339312)]
            ResourceUtils.copyResource(resource, tmpResource);
            boolean isSelected = isSelected(tmpFile.getParentFile(), tmpFile.getName(), resource.toLongString());
// isSelected           No	: [('line', 0.5036946875306478), ('success', 0.05870735942772667), ('result', 0.024507088786739024), ('found', 0.022104468120170244), ('ok', 0.021807894788736376), ('optional', 0.014945571848179424), ('canary', 0.014539161253393799), ('isAbsolute', 0.014538884773229592), ('selfMove', 0.01453847005298328), ('even', 0.014538193572819073)]
            tmpFile.delete();
            return isSelected;
        } catch (UnsupportedOperationException uoe) {
// uoe                  No	: [('e', 0.8594301313390197), ('b', 3.173451562394901e-05), ('length', 3.069772876742041e-05), ('classpath', 1.314147133020269e-05), ('block', 7.784739238138228e-06), ('be', 5.227331658701002e-06), ('jmod', 4.8817360398581335e-06), ('publicId', 4.7434977923209865e-06), ('elementName', 4.259663925940971e-06), ('dataShadow', 3.983187430866675e-06)]
            log("The resource '" + resource.getName() + "' does not provide an InputStream, so it is not checked. " + "According to 'selres' attribute value it is " + ((selectResourcesWithoutInputStream) ? "" : " not") + "selected.", Project.MSG_INFO);
            return selectResourcesWithoutInputStream;
        } catch (Exception e) {
// e                    0	: [('e', 0.563983241398157), ('ex', 0.2334127431539531), ('msg', 0.10219735856518988), ('ioe', 0.08419998332807777), ('message', 0.07231955788507098), ('t', 0.06344869951599263), ('exc', 0.055728524758199054), ('s', 0.023973260873358767), ('ie', 0.01784126892436277), ('e2', 0.011012111097548884)]
            throw new BuildException(e);
        }
    }

    /**
     * Implementation of BaseExtendSelector.isSelected().
     *
     * @param basedir as described in BaseExtendSelector
     * @param filename as described in BaseExtendSelector
     * @param file as described in BaseExtendSelector
     * @return as described in BaseExtendSelector
     */
    @Override
    public boolean isSelected(File basedir, String filename, File file) {
// basedir              0	: [('basedir', 0.8820083884832249), ('r', 0.022106522500621287), ('name', 0.014082348029268834), ('file', 0.014040441056111016), ('base', 0.006380451777991227), ('f', 0.006126841810280782), ('tmpFile', 0.0030966218167475606), ('myBaseDir', 0.00306044918121752), ('dir', 0.0026799861745751934), ('src', 0.0024702739310603723)]
// filename             0	: [('filename', 0.868772242969927), ('name', 0.04606548416787796), ('filenames', 0.010469841417909855), ('file', 0.009838026141969623), ('f', 0.005911675312699767), ('element', 0.005716787677248008), ('files', 0.004945194932441735), ('srcFile', 0.004820008932511346), ('fileName', 0.004814671194408527), ('myfile', 0.004803373637833425)]
// file                 0	: [('file', 0.8863818683706871), ('absBase', 0.027320058243687537), ('charset', 0.013684918571170069), ('destFile', 0.0063328686738236654), ('dir', 0.004475297442027271), ('revision', 0.0034887204786117547), ('srcDir', 0.0033446235425567817), ('workingDir', 0.0029986280258029762), ('destDir', 0.002704371618795394), ('dest', 0.0026832433203591234)]
        return isSelected(basedir, filename, file.getAbsolutePath());
    }

    /**
     * The business logic of this selector for use as ResourceSelector of
     * FileSelector.
     *
     * @param basedir as described in BaseExtendSelector
     * @param filename as described in BaseExtendSelector
     * @param cacheKey the name for the key for storing the hashvalue
     * @return <code>true</code> if the file is selected otherwise <code>false</code>
     */
    private boolean isSelected(File basedir, String filename, String cacheKey) {
// basedir              0	: [('basedir', 0.7640174235405234), ('d', 0.08709878495398604), ('dir', 0.08278102768645118), ('toDir', 0.05427315847164885), ('path', 0.048952201377067545), ('name', 0.043393333664663976), ('filepath', 0.040330392266687644), ('baseDir', 0.030280863171379367), ('file', 0.028082852994638908), ('parent', 0.027917438834268147)]
// filename             0	: [('filename', 0.868772242969927), ('file', 0.1973380261419696), ('element', 0.09946678767724801), ('srcFile', 0.09857000893251133), ('fileName', 0.09856467119440852), ('currentelement', 0.0985497639007569), ('fName', 0.09853984874500282), ('name', 0.01481548416787796), ('filenames', 0.010469841417909855), ('f', 0.005911675312699767)]
// cacheKey             No	: [('contains', 0.5002325022383088), ('classname', 0.37582894481369966), ('name', 0.006688188665419646), ('filename', 0.0058749091162705325), ('value', 0.003668426142211781), ('encoding', 0.002200587027366323), ('prefix', 0.0018240844940947843), ('tag', 0.001722904960561742), ('qname', 0.001592945135443908), ('dir', 0.001569736429812705)]
        validate();
        File f = new File(basedir, filename);
// f                    No	: [('myBaseDir', 0.5045398656265897), ('key1', 0.2520244373801416), ('key2', 0.25202436825734853), ('destfiles', 0.25047092309835206), ('file', 0.12899582878598403), ('filename', 0.12574170082246802), ('i', 0.08431877177168126), ('bytes', 0.08135719169407883), ('name', 0.07965840297315917), ('properties', 0.05348914984918659)]
        // You can not compute a value for a directory
        if (f.isDirectory()) {
            return selectDirectories;
        }
        // Get the values and do the comparison
        String cachedValue = String.valueOf(cache.get(f.getAbsolutePath()));
// cachedValue          No	: [('foo', 0.32828854015957776), ('o1', 0.1094338293940356), ('r', 0.05547092925999838), ('f1', 0.054775628178017614), ('o2', 0.05472046098521424), ('result', 0.04843643142016834), ('name', 0.04132123200862741), ('value', 0.0374003484919035), ('msg', 0.03009806663138987), ('filename', 0.029565734932509927)]
        String newValue = algorithm.getValue(f);
// newValue             No	: [('expected', 0.13837442716849707), ('msg', 0.13286949741471688), ('sysid', 0.13240632965878787), ('uri', 0.12991567746839888), ('output', 0.09395806748936804), ('f', 0.08617956482783658), ('dir2', 0.08051694207556094), ('hash1', 0.06813981780371282), ('destFile', 0.05430435971624215), ('l', 0.0423054041761979)]
        boolean rv = comparator.compare(cachedValue, newValue) != 0;
// rv                   No	: [('result', 0.13408524190822596), ('ch', 0.05983437411882282), ('success', 0.058707359519457415), ('sb', 0.053916811499597504), ('bi', 0.032351319253342316), ('buf', 0.026546138265986615), ('found', 0.02210446827794714), ('ok', 0.021807894838270985), ('upToDate', 0.0189905050561846), ('relPath', 0.0161704018295194)]
        // Maybe update the cache
        if (update && rv) {
            cache.put(f.getAbsolutePath(), newValue);
            setModified(getModified() + 1);
            if (!getDelayUpdate()) {
                saveCache();
            }
        }
        return rv;
    }

    /**
     * save the cache file
     */
    protected void saveCache() {
        if (getModified() > 0) {
            cache.save();
            setModified(0);
        }
    }

    // -----  attribute and nested element support  -----
    /**
     * Setter for algorithmClass.
     * @param classname  new value
     */
    public void setAlgorithmClass(String classname) {
// classname            9	: [('name', 0.06155535684399712), ('s', 0.02166488489538175), ('value', 0.020552717844961824), ('message', 0.016854965057537783), ('msg', 0.015278668810540214), ('uri', 0.01418545372823002), ('target', 0.01388785146416872), ('line', 0.011559443657118192), ('filename', 0.011435198447237532), ('classname', 0.010355616324979753)]
        algorithmClass = classname;
    }

    /**
     * Setter for comparatorClass.
     * @param classname  new value
     */
    public void setComparatorClass(String classname) {
// classname            9	: [('name', 0.06155535684399712), ('s', 0.02166488489538175), ('value', 0.020552717844961824), ('message', 0.016854965057537783), ('msg', 0.015278668810540214), ('uri', 0.01418545372823002), ('target', 0.01388785146416872), ('line', 0.011559443657118192), ('filename', 0.011435198447237532), ('classname', 0.010355616324979753)]
        comparatorClass = classname;
    }

    /**
     * Setter for cacheClass.
     * @param classname  new value
     */
    public void setCacheClass(String classname) {
// classname            9	: [('name', 0.06155535684399712), ('s', 0.02166488489538175), ('value', 0.020552717844961824), ('message', 0.016854965057537783), ('msg', 0.015278668810540214), ('uri', 0.01418545372823002), ('target', 0.01388785146416872), ('line', 0.011559443657118192), ('filename', 0.011435198447237532), ('classname', 0.010355616324979753)]
        cacheClass = classname;
    }

    /**
     * Support for <i>update</i> attribute.
     * @param update new value
     */
    public void setUpdate(boolean update) {
// update               No	: [('b', 0.08840525847196394), ('value', 0.022603553559821576), ('verbose', 0.016829026586382915), ('append', 0.012448459200976782), ('debug', 0.008734879145209597), ('caseSensitive', 0.00873460266504539), ('recursive', 0.008621098130951438), ('enable', 0.008266223194396603), ('preserveLastModified', 0.007973671083471783), ('overwrite', 0.00728360869636253)]
        this.update = update;
    }

    /**
     * Support for <i>seldirs</i> attribute.
     * @param seldirs new value
     */
    public void setSeldirs(boolean seldirs) {
// seldirs              No	: [('b', 0.08840525847196394), ('value', 0.022603553559821576), ('verbose', 0.016829026586382915), ('append', 0.012448459200976782), ('debug', 0.008734879145209597), ('caseSensitive', 0.00873460266504539), ('recursive', 0.008621098130951438), ('enable', 0.008266223194396603), ('preserveLastModified', 0.007973671083471783), ('overwrite', 0.00728360869636253)]
        selectDirectories = seldirs;
    }

    /**
     * Support for <i>selres</i> attribute.
     * @param newValue the new value
     */
    public void setSelres(boolean newValue) {
// newValue             No	: [('b', 0.08840525847196394), ('value', 0.022603553559821576), ('verbose', 0.016829026586382915), ('append', 0.012448459200976782), ('debug', 0.008734879145209597), ('caseSensitive', 0.00873460266504539), ('recursive', 0.008621098130951438), ('enable', 0.008266223194396603), ('preserveLastModified', 0.007973671083471783), ('overwrite', 0.00728360869636253)]
        this.selectResourcesWithoutInputStream = newValue;
    }

    /**
     * Getter for the modified count
     * @return modified count
     */
    public int getModified() {
        return modified;
    }

    /**
     * Setter for the modified count
     * @param modified count
     */
    public void setModified(int modified) {
// modified             No	: [('i', 0.28776176064215125), ('remoteFile', 0.1251403304783544), ('pkFile', 0.1250302210132878), ('implFile', 0.1250296680529594), ('homeFile', 0.1250293915727952), ('j', 0.025939579627335747), ('t', 0.023652516249910407), ('index', 0.014370087744103439), ('b', 0.01127188647484174), ('options', 0.009771806379562988)]
        this.modified = modified;
    }

    /**
     * Getter for the delay update
     * @return true if we should delay for performance
     */
    public boolean getDelayUpdate() {
        return delayUpdate;
    }

    /**
     * Setter for the delay update
     * @param delayUpdate true if we should delay for performance
     */
    public void setDelayUpdate(boolean delayUpdate) {
// delayUpdate          No	: [('b', 0.08840525847196394), ('value', 0.022603553559821576), ('verbose', 0.016829026586382915), ('append', 0.012448459200976782), ('debug', 0.008734879145209597), ('caseSensitive', 0.00873460266504539), ('recursive', 0.008621098130951438), ('enable', 0.008266223194396603), ('preserveLastModified', 0.007973671083471783), ('overwrite', 0.00728360869636253)]
        this.delayUpdate = delayUpdate;
    }

    /**
     * Add the classpath.
     * @param path the classpath
     */
    public void addClasspath(Path path) {
// path                 2	: [('manifest', 0.7535651970140128), ('classpath', 0.1889024476849974), ('path', 0.13376536211881285), ('p', 0.06238592262525661), ('localPath', 0.0226101610110122), ('cp', 0.021436930367838173), ('trackerFile', 0.017354174812970635), ('s', 0.017261559229625505), ('source', 0.01703373957431886), ('src', 0.01188628888333051)]
        if (classpath != null) {
            throw new BuildException("<classpath> can be set only once.");
        }
        classpath = path;
    }

    /**
     * Returns and initializes the classloader for this class.
     * @return the classloader
     */
    public ClassLoader getClassLoader() {
        if (myClassLoader == null) {
            // the usual classloader
            // additional use the provided classpath
            // Memory leak in line below
            myClassLoader = (classpath == null) ? getClass().getClassLoader() : getProject().createClassLoader(classpath);
        }
        return myClassLoader;
    }

    /**
     * Set the used ClassLoader.
     * If you invoke this selector by API (e.g. inside some testcases) the selector
     * will use a different classloader for loading the interface implementations than
     * the caller. Therefore you will get a ClassCastException if you get the
     * implementations from the selector and cast them.
     * @param loader the ClassLoader to use
     */
    public void setClassLoader(ClassLoader loader) {
// loader               1	: [('classLoader', 0.6321626939766565), ('loader', 0.2812560568290229), ('parent', 0.014128416041301736), ('i', 0.007220869255079404), ('c', 0.006755150652717413), ('al', 0.006726051115434615), ('cl', 0.0065562186503495355), ('name', 0.00615307471702672), ('b', 0.005880193753638788), ('value', 0.005419652314355413)]
        myClassLoader = loader;
    }

    /**
     * Support for nested &lt;param&gt; tags.
     * @param key the key of the parameter
     * @param value the value of the parameter
     */
    public void addParam(String key, Object value) {
// key                  No	: [('name', 0.7653888392109992), ('p', 0.03226269262762015), ('include', 0.03118182022905669), ('exclude', 0.03116089645214312), ('newName', 0.022247297402023962), ('tok', 0.020862269428830425), ('pkg', 0.020789371197441932), ('testClassName', 0.020773707618579145), ('value', 0.012728532117741168), ('s', 0.012609369724829072)]
// value                1	: [('bean', 0.781714729668244), ('value', 0.11092578854452592), ('key', 0.07915362477892009), ('text', 0.07910509836256038), ('r', 0.0728261537105011), ('message', 0.04794786115615866), ('o', 0.04789729747843322), ('entry', 0.0478630995257548), ('timetorun', 0.04780628226415947), ('t', 0.03967168494029519)]
        Parameter par = new Parameter();
// par                  No	: [('p', 0.5283283874787754), ('parameter', 0.50129752914634), ('param', 0.4380288877505936), ('out', 0.18644565141200706), ('finalString', 0.1845629394703221), ('param1', 0.17521113873719368), ('rc', 0.018713419237408085), ('f', 0.018299873444611956), ('propElement', 0.0179021403883899), ('name', 0.01118329837959629)]
        par.setName(key);
        par.setValue(String.valueOf(value));
        configParameter.add(par);
    }

    /**
     * Support for nested &lt;param&gt; tags.
     * @param parameter the parameter object
     */
    public void addParam(Parameter parameter) {
// parameter            3	: [('p', 0.8769912352941416), ('par', 0.5006364670937656), ('param', 0.0378305227529152), ('parameter', 0.02369729346594839), ('rc', 0.0187134167829908), ('name', 0.011183279497348911), ('instr', 0.010776451079640457), ('r', 0.009830615942109811), ('c', 0.009353219270522774), ('fileNameMapper', 0.009168943859122735)]
        configParameter.add(parameter);
    }

    /**
     * Defined in org.apache.tools.ant.types.Parameterizable.
     * Overwrite implementation in superclass because only special
     * parameters are valid.
     * @see #addParam(String,Object)
     * @param parameters the parameters to set.
     */
    @Override
    public void setParameters(Parameter... parameters) {
// parameters           0	: [('parameters', 0.9479193711970899), ('line', 0.018738631285867256), ('file', 0.0183572267376551), ('name', 0.01701548545532538), ('c', 0.014854674998775495), ('instr', 0.01445316793246311), ('event', 0.011688567411349587), ('project', 0.011115120027205369), ('t', 0.010760290135792331), ('value', 0.01000895907878819)]
        if (parameters != null) {
            Collections.addAll(configParameter, parameters);
        }
    }

    /**
     * Support for nested <code>&lt;param name="" value=""/&gt;</code> tags.
     * Parameter named <i>cache</i>, <i>algorithm</i>,
     * <i>comparator</i> or <i>update</i> are mapped to
     * the respective set-Method.
     * Parameter which names starts with <i>cache.</i> or
     * <i>algorithm.</i> or <i>comparator.</i> are tried
     * to set on the appropriate object via its set-methods.
     * Other parameters are invalid and an BuildException will
     * be thrown.
     *
     * @param parameter  Key and value as parameter object
     */
    public void useParameter(Parameter parameter) {
// parameter            2	: [('param', 0.3026441824856446), ('attrs', 0.27676147813991236), ('parameter', 0.1895780714528998), ('line', 0.13857659356959204), ('h', 0.138404952212566), ('attributeName', 0.138355252143685), ('keyValue', 0.13835272922870484), ('field', 0.08238826793204415), ('p', 0.081888988858107), ('result', 0.08174947155164404)]
        String key = parameter.getName();
// key                  No	: [('paramname', 0.4388491103400365), ('parameter', 0.3134647637567082), ('arg', 0.14041611046284672), ('def', 0.11954933285099519), ('event', 0.09765440370679514), ('vpath', 0.09483020173720966), ('msg', 0.06176442993565997), ('name', 0.057332253916847827), ('c', 0.05349641568889775), ('entry', 0.04427110636983162)]
        String value = parameter.getValue();
// value                0	: [('value', 0.628259990293758), ('attr2', 0.1472493166157799), ('s', 0.13887612325405038), ('methodName', 0.07370525990369729), ('otherName', 0.07363240759864925), ('iDir', 0.07345914782245783), ('newName', 0.07340406790168562), ('prefix', 0.05817080637194625), ('arg', 0.04465487007734813), ('zOut', 0.043821818654883264)]
        if ("cache".equals(key)) {
            CacheName cn = new CacheName();
// cn                   No	: [('cacheName', 0.5000963247537191), ('name', 0.16785126441786719), ('b', 0.07240374392798637), ('i', 0.006553884457204768), ('out', 0.0035912710197565974), ('compareFiles', 0.0025721062700165605), ('result', 0.0024090760426990028), ('cmd', 0.002366216199938778), ('fs', 0.0023247036924552546), ('ds', 0.002247270985235519)]
            cn.setValue(value);
            setCache(cn);
        } else if ("algorithm".equals(key)) {
            AlgorithmName an = new AlgorithmName();
// an                   No	: [('name', 0.5011845977512006), ('i', 0.013107768914409536), ('out', 0.007182542039513195), ('compareFiles', 0.005144212540033121), ('result', 0.0048181520853980055), ('cmd', 0.004732432399877556), ('fs', 0.004649407384910509), ('ds', 0.004494541970471038), ('graphics', 0.0042788915121071535), ('t', 0.004073428708056242)]
            an.setValue(value);
            setAlgorithm(an);
        } else if ("comparator".equals(key)) {
            ComparatorName cn = new ComparatorName();
// cn                   No	: [('comparatorInstance', 0.7750032007120501), ('compName', 0.5001905582525931), ('name', 0.10118459775120053), ('comparator', 0.025007773260915425), ('i', 0.006553884457204768), ('out', 0.0035912710197565974), ('compareFiles', 0.0025721062700165605), ('result', 0.0024090760426990028), ('cmd', 0.002366216199938778), ('fs', 0.0023247036924552546)]
            cn.setValue(value);
            setComparator(cn);
        } else if ("update".equals(key)) {
            setUpdate("true".equalsIgnoreCase(value));
        } else if ("delayupdate".equals(key)) {
            setDelayUpdate("true".equalsIgnoreCase(value));
        } else if ("seldirs".equals(key)) {
            setSeldirs("true".equalsIgnoreCase(value));
        } else if (key.startsWith(CACHE_PREFIX)) {
            String name = key.substring(CACHE_PREFIX.length());
// name                 0	: [('name', 0.6268149233010561), ('msg', 0.07928010381780184), ('message', 0.0546943755841725), ('s', 0.045751797935022674), ('className', 0.03456366341543477), ('filename', 0.02118777188197019), ('line', 0.020607917142519333), ('classname', 0.02036891207535121), ('token', 0.019222287233648497), ('value', 0.017514907977354967)]
            tryToSetAParameter(cache, name, value);
        } else if (key.startsWith(ALGORITHM_PREFIX)) {
            String name = key.substring(ALGORITHM_PREFIX.length());
// name                 0	: [('name', 0.7518149233010563), ('msg', 0.07928010381780184), ('message', 0.0546943755841725), ('s', 0.045751797935022674), ('className', 0.03456366341543477), ('filename', 0.02118777188197019), ('line', 0.020607917142519333), ('classname', 0.02036891207535121), ('token', 0.019222287233648497), ('value', 0.017514907977354967)]
            tryToSetAParameter(algorithm, name, value);
        } else if (key.startsWith(COMPARATOR_PREFIX)) {
            String name = key.substring(COMPARATOR_PREFIX.length());
// name                 0	: [('name', 0.7518149233010563), ('msg', 0.07928010381780184), ('message', 0.0546943755841725), ('s', 0.045751797935022674), ('className', 0.03456366341543477), ('filename', 0.02118777188197019), ('line', 0.020607917142519333), ('classname', 0.02036891207535121), ('token', 0.019222287233648497), ('value', 0.017514907977354967)]
            tryToSetAParameter(comparator, name, value);
        } else {
            setError("Invalid parameter " + key);
        }
    }

    /**
     * Try to set a value on an object using reflection.
     * Helper method for easier access to IntrospectionHelper.setAttribute().
     * @param obj the object on which the attribute should be set
     * @param name the attributename
     * @param value the new value
     */
    protected void tryToSetAParameter(Object obj, String name, String value) {
// obj                  0	: [('obj', 0.3750796713984121), ('prj', 0.3750197656060452), ('o', 0.15264120853468865), ('value', 0.06778051676930207), ('key', 0.030389118510436904), ('parent', 0.028173341484651886), ('other', 0.017127359218236507), ('element', 0.01476995498565797), ('target', 0.014012977560062537), ('o1', 0.012967818645699711)]
// name                 1	: [('methodName', 0.7007735539525007), ('name', 0.5184817906486427), ('fieldName', 0.17528958678448078), ('arg1', 0.03338128825966486), ('arg', 0.01695463443628511), ('filename', 0.005941152453083959), ('value', 0.0036652508737986633), ('encoding', 0.0021984504348149902), ('l', 0.002029238821079302), ('offset', 0.001988545265533348)]
// value                1	: [('qName', 0.19206803051001492), ('value', 0.17295105453268667), ('qname', 0.14502612487683322), ('classname', 0.09645106478668194), ('content', 0.09595173309023126), ('prefix', 0.05538614505639444), ('defaultValue', 0.04797853745228644), ('arch', 0.04796377190054336), ('instruction', 0.047884212769255745), ('zOut', 0.04173331081329609)]
        Project prj = (getProject() != null) ? getProject() : new Project();
// prj                  5	: [('p', 0.43441260785230695), ('project', 0.41455952085760706), ('owner', 0.15656003044811773), ('name', 0.10656703291392526), ('entry', 0.046624098228870335), ('prj', 0.04373762011137828), ('subProject', 0.04244619042334086), ('attr', 0.029188155336712353), ('aProj', 0.02383573442223979), ('expectedProject', 0.01103469259137782)]
        IntrospectionHelper iHelper = IntrospectionHelper.getHelper(prj, obj.getClass());
// iHelper              No	: [('ih', 0.9062560310944332), ('sql', 0.13402421690287628), ('process', 0.06571225972650431), ('helper', 0.06559115270153335), ('execute', 0.06553128423990294), ('caseSensitive', 0.06551808636713981), ('executeMethod', 0.06550762251455038), ('channel', 0.012147539040124857), ('reader', 0.010012078777102803), ('result', 0.006965825292196861)]
        try {
            iHelper.setAttribute(prj, obj, name, value);
        } catch (BuildException e) {
// e                    0	: [('e', 0.3743943110260786), ('be', 0.2544111325154466), ('ex', 0.23133868233055715), ('exc', 0.04830065280495179), ('mfe', 0.01586956212991777), ('b', 5.685509440208406e-05), ('thrownException', 5.235812864538528e-05), ('buildException', 5.20470925884267e-05), ('deepest', 5.1355901350740965e-05), ('length', 3.069772876742041e-05)]
        // no-op
        }
    }

    // ----- 'beautiful' output -----
    /**
     * Override Object.toString().
     * @return information about this selector
     */
    @Override
    public String toString() {
        return String.format("{modifiedselector update=%s seldirs=%s cache=%s algorithm=%s comparator=%s}", update, selectDirectories, cache, algorithm, comparator);
    }

    // ----- BuildListener interface methods -----
    /**
     * Signals that the last target has finished.
     * @param event received BuildEvent
     */
    @Override
    public void buildFinished(BuildEvent event) {
// event                0	: [('event', 0.9659385860006721), ('actual', 0.00023869177849245878), ('expected', 0.00012425776594088498), ('be', 0.0001231518599605878), ('b', 3.173451562394901e-05), ('length', 3.069772876742041e-05), ('classpath', 1.314147133020269e-05), ('block', 7.784739238138228e-06), ('jmod', 4.8817360398581335e-06), ('publicId', 4.7434977923209865e-06)]
        if (getDelayUpdate()) {
            saveCache();
        }
    }

    /**
     * Signals that a target has finished.
     * @param event received BuildEvent
     */
    @Override
    public void targetFinished(BuildEvent event) {
// event                0	: [('event', 0.9659385860006721), ('actual', 0.00023869177849245878), ('expected', 0.00012425776594088498), ('be', 0.0001231518599605878), ('b', 3.173451562394901e-05), ('length', 3.069772876742041e-05), ('classpath', 1.314147133020269e-05), ('block', 7.784739238138228e-06), ('jmod', 4.8817360398581335e-06), ('publicId', 4.7434977923209865e-06)]
        if (getDelayUpdate()) {
            saveCache();
        }
    }

    /**
     * Signals that a task has finished.
     * @param event received BuildEvent
     */
    @Override
    public void taskFinished(BuildEvent event) {
// event                0	: [('event', 0.9659385860006721), ('actual', 0.00023869177849245878), ('expected', 0.00012425776594088498), ('be', 0.0001231518599605878), ('b', 3.173451562394901e-05), ('length', 3.069772876742041e-05), ('classpath', 1.314147133020269e-05), ('block', 7.784739238138228e-06), ('jmod', 4.8817360398581335e-06), ('publicId', 4.7434977923209865e-06)]
        if (getDelayUpdate()) {
            saveCache();
        }
    }

    /**
     * Signals that a build has started.
     * @param event received BuildEvent
     */
    @Override
    public void buildStarted(BuildEvent event) {
// event                0	: [('event', 0.9659385860006721), ('actual', 0.00023869177849245878), ('expected', 0.00012425776594088498), ('be', 0.0001231518599605878), ('b', 3.173451562394901e-05), ('length', 3.069772876742041e-05), ('classpath', 1.314147133020269e-05), ('block', 7.784739238138228e-06), ('jmod', 4.8817360398581335e-06), ('publicId', 4.7434977923209865e-06)]
    // no-op
    }

    /**
     * Signals that a target is starting.
     * @param event received BuildEvent
     */
    @Override
    public void targetStarted(BuildEvent event) {
// event                0	: [('event', 0.9659385860006721), ('actual', 0.00023869177849245878), ('expected', 0.00012425776594088498), ('be', 0.0001231518599605878), ('b', 3.173451562394901e-05), ('length', 3.069772876742041e-05), ('classpath', 1.314147133020269e-05), ('block', 7.784739238138228e-06), ('jmod', 4.8817360398581335e-06), ('publicId', 4.7434977923209865e-06)]
    // no-op
    }

    /**
     * Signals that a task is starting.
     * @param event received BuildEvent
     */
    @Override
    public void taskStarted(BuildEvent event) {
// event                0	: [('event', 0.9659385860006721), ('actual', 0.00023869177849245878), ('expected', 0.00012425776594088498), ('be', 0.0001231518599605878), ('b', 3.173451562394901e-05), ('length', 3.069772876742041e-05), ('classpath', 1.314147133020269e-05), ('block', 7.784739238138228e-06), ('jmod', 4.8817360398581335e-06), ('publicId', 4.7434977923209865e-06)]
    // no-op
    }

    /**
     * Signals a message logging event.
     * @param event received BuildEvent
     */
    @Override
    public void messageLogged(BuildEvent event) {
// event                0	: [('event', 0.9659385860006721), ('actual', 0.00023869177849245878), ('expected', 0.00012425776594088498), ('be', 0.0001231518599605878), ('b', 3.173451562394901e-05), ('length', 3.069772876742041e-05), ('classpath', 1.314147133020269e-05), ('block', 7.784739238138228e-06), ('jmod', 4.8817360398581335e-06), ('publicId', 4.7434977923209865e-06)]
    // no-op
    }

    // The EnumeratedAttributes for the three interface implementations.
    // Name-Classname mapping is done in the configure() method.
    /**
     * Get the cache type to use.
     * @return the enumerated cache type
     */
    public Cache getCache() {
        return cache;
    }

    /**
     * Set the cache type to use.
     * @param name an enumerated cache type.
     */
    public void setCache(CacheName name) {
// name                 3	: [('cn', 0.12501949481838812), ('cacheName', 0.12501728297707446), ('i', 0.007220869255079404), ('name', 0.006111021588182371), ('b', 0.005880193753638788), ('value', 0.005419652314355413), ('file', 0.004260412397551352), ('path', 0.0036403468622049576), ('line', 0.003639793901876543), ('p', 0.003621845876403473)]
        cacheName = name;
    }

    /**
     * The enumerated type for cache.
     * The values are "propertyfile".
     */
    public static class CacheName extends EnumeratedAttribute {

        /**
         * {@inheritDoc}
         * @see EnumeratedAttribute#getValues()
         */
        @Override
        public String[] getValues() {
            return new String[] { "propertyfile" };
        }
    }

    /**
     * Get the algorithm type to use.
     * @return the enumerated algorithm type
     */
    public Algorithm getAlgorithm() {
        return algorithm;
    }

    /**
     * Set the algorithm type to use.
     * @param name an enumerated algorithm type.
     */
    public void setAlgorithm(AlgorithmName name) {
// name                 2	: [('an', 0.25001728297707443), ('i', 0.007220869255079404), ('name', 0.006111021588182371), ('b', 0.005880193753638788), ('value', 0.005419652314355413), ('file', 0.004260412397551352), ('path', 0.0036403468622049576), ('line', 0.003639793901876543), ('p', 0.003621845876403473), ('project', 0.003496295737968582)]
        algoName = name;
    }

    /**
     * The enumerated type for algorithm.
     * The values are "hashValue", "digest", "checksum" and "lastmodified".
     */
    public static class AlgorithmName extends EnumeratedAttribute {

        /**
         * {@inheritDoc}
         * @see EnumeratedAttribute#getValues()
         */
        @Override
        public String[] getValues() {
            return new String[] { "hashvalue", "digest", "checksum", "lastmodified" };
        }
    }

    /**
     * Get the comparator type to use.
     * @return the enumerated comparator type
     */
    public Comparator<? super String> getComparator() {
        return comparator;
    }

    /**
     * Set the comparator type to use.
     * @param name an enumerated comparator type.
     */
    public void setComparator(ComparatorName name) {
// name                 3	: [('compName', 0.16668505556439794), ('cn', 0.08335282815172144), ('i', 0.007220869255079404), ('name', 0.006111021588182371), ('b', 0.005880193753638788), ('value', 0.005419652314355413), ('file', 0.004260412397551352), ('path', 0.0036403468622049576), ('line', 0.003639793901876543), ('p', 0.003621845876403473)]
        compName = name;
    }

    /**
     * The enumerated type for algorithm.
     * The values are "equal" and "rule".
     */
    public static class ComparatorName extends EnumeratedAttribute {

        /**
         * {@inheritDoc}
         * @see EnumeratedAttribute#getValues()
         */
        @Override
        public String[] getValues() {
            return new String[] { "equal", "rule" };
        }
    }
}
