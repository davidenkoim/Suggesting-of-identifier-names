// Type of training: selfTestingIdentifier
// Path to file: C:\Users\Igor\IdeaProjects\SLP-Core-0.3\java-projects\ant-master\src\main\org\apache\tools\ant\taskdefs\XmlProperty.java
// Number of identifiers: 69	Accuracy: 40.58%	MRR: 53.07%
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
import java.io.IOException;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.types.Resource;
import org.apache.tools.ant.types.ResourceCollection;
import org.apache.tools.ant.types.XMLCatalog;
import org.apache.tools.ant.types.resources.FileProvider;
import org.apache.tools.ant.types.resources.FileResource;
import org.apache.tools.ant.util.FileUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.SAXException;

/**
 *  Loads property values from a valid XML file, generating the
 *  property names from the file's element and attribute names.
 *
 *  <p>Example:</p>
 *  <pre>
 *    &lt;root-tag myattr="true"&gt;
 *      &lt;inner-tag someattr="val"&gt;Text&lt;/inner-tag&gt;
 *      &lt;a2&gt;&lt;a3&gt;&lt;a4&gt;false&lt;/a4&gt;&lt;/a3&gt;&lt;/a2&gt;
 *      &lt;x&gt;x1&lt;/x&gt;
 *      &lt;x&gt;x2&lt;/x&gt;
 *    &lt;/root-tag&gt;
 * </pre>
 *
 *  <p>this generates the following properties:</p>
 *
 *  <pre>
 *   root-tag(myattr)=true
 *   root-tag.inner-tag=Text
 *   root-tag.inner-tag(someattr)=val
 *   root-tag.a2.a3.a4=false
 *   root-tag.x=x1,x2
 *  </pre>
 *
 *  <p>The <i>collapseAttributes</i> property of this task can be set
 *  to true (the default is false) which will instead result in the
 *  following properties (note the difference in names of properties
 *  corresponding to XML attributes):</p>
 *
 *  <pre>
 *   root-tag.myattr=true
 *   root-tag.inner-tag=Text
 *   root-tag.inner-tag.someattr=val
 *   root-tag.a2.a3.a4=false
 *   root-tag.x=x1,x2
 *  </pre>
 *
 *  <p>Optionally, to more closely mirror the abilities of the Property
 *  task, a selected set of attributes can be treated specially.  To
 *  enable this behavior, the "semanticAttributes" property of this task
 *  must be set to true (it defaults to false).  If this attribute is
 *  specified, the following attributes take on special meaning
 *  (setting this to true implicitly sets collapseAttributes to true as
 *  well):</p>
 *
 *  <ul>
 *   <li><b>value</b>: Identifies a text value for a property.</li>
 *   <li><b>location</b>: Identifies a file location for a property.</li>
 *   <li><b>id</b>: Sets an id for a property</li>
 *   <li><b>refid</b>: Sets a property to the value of another property
 *        based upon the provided id</li>
 *   <li><b>pathid</b>: Defines a path rather than a property with
 *        the given id.</li>
 *  </ul>
 *
 *  <p>For example, with keepRoot = false, the following properties file:</p>
 *
 *  <pre>
 *  &lt;root-tag&gt;
 *    &lt;build&gt;
 *    &lt;build folder="build"&gt;
 *      &lt;classes id="build.classes" location="${build.folder}/classes"/&gt;
 *      &lt;reference refid="build.classes"/&gt;
 *    &lt;/build&gt;
 *    &lt;compile&gt;
 *      &lt;classpath pathid="compile.classpath"&gt;
 *        &lt;p                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          athelement location="${build.classes}"/&gt;
 *      &lt;/classpath&gt;
 *    &lt;/compile&gt;
 *    &lt;run-time&gt;
 *      &lt;jars&gt;*.jar&lt;/jars&gt;
 *      &lt;classpath pathid="run-time.classpath"&gt;
 *        &lt;path refid="compile.classpath"/&gt;
 *        &lt;pathelement path="${run-time.jars}"/&gt;
 *      &lt;/classpath&gt;
 *    &lt;/run-time&gt;
 *  &lt;/root-tag&gt;
 *  </pre>
 *
 *  <p>is equivalent to the following entries in a build file:</p>
 *
 *  <pre>
 *  &lt;property name="build" location="build"/&gt;
 *  &lt;property name="build.classes" location="${build.location}/classes"/&gt;
 *  &lt;property name="build.reference" refid="build.classes"/&gt;
 *
 *  &lt;property name="run-time.jars" value="*.jar/&gt;
 *
 *  &lt;classpath id="compile.classpath"&gt;
 *    &lt;pathelement location="${build.classes}"/&gt;
 *  &lt;/classpath&gt;
 *
 *  &lt;classpath id="run-time.classpath"&gt;
 *    &lt;path refid="compile.classpath"/&gt;
 *    &lt;pathelement path="${run-time.jars}"/&gt;
 *  &lt;/classpath&gt;
 *  </pre>
 *
 *  <p>This task <i>requires</i> the following attributes:</p>
 *
 *  <ul>
 *  <li><b>file</b>: The name of the file to load.</li>
 *  </ul>
 *
 *  <p>This task supports the following attributes:</p>
 *
 *  <ul>
 *  <li><b>prefix</b>: Optionally specify a prefix applied to
 *      all properties loaded.  Defaults to an empty string.</li>
 *  <li><b>keepRoot</b>: Indicate whether the root xml element
 *      is kept as part of property name.  Defaults to true.</li>
 *  <li><b>validate</b>: Indicate whether the xml file is validated.
 *      Defaults to false.</li>
 *  <li><b>collapseAttributes</b>: Indicate whether attributes are
 *      stored in property names with parens or with period
 *      delimiters.  Defaults to false, meaning properties
 *      are stored with parens (i.e., foo(attr)).</li>
 *  <li><b>semanticAttributes</b>: Indicate whether attributes
 *      named "location", "value", "refid" and "path"
 *      are interpreted as ant properties.  Defaults
 *      to false.</li>
 *  <li><b>rootDirectory</b>: Indicate the directory to use
 *      as the root directory for resolving location
 *      properties.  Defaults to the directory
 *      of the project using the task.</li>
 *  <li><b>includeSemanticAttribute</b>: Indicate whether to include
 *      the semantic attribute ("location" or "value") as
 *      part of the property name.  Defaults to false.</li>
 *  </ul>
 *
 *  @ant.task name="xmlproperty" category="xml"
 */
public class XmlProperty extends Task {

    private static final String ID = "id";

    private static final String REF_ID = "refid";

    private static final String LOCATION = "location";

    private static final String VALUE = "value";

    private static final String PATH = "path";

    private static final String PATHID = "pathid";

    private static final String[] ATTRIBUTES = new String[] { ID, REF_ID, LOCATION, VALUE, PATH, PATHID };

    private static final FileUtils FILE_UTILS = FileUtils.getFileUtils();

    private Resource src;

    private String prefix = "";

    private boolean keepRoot = true;

    private boolean validate = false;

    private boolean collapseAttributes = false;

    private boolean semanticAttributes = false;

    private boolean includeSemanticAttribute = false;

    private File rootDirectory = null;

    private Map<String, String> addedAttributes = new Hashtable<>();

    private XMLCatalog xmlCatalog = new XMLCatalog();

    private String delimiter = ",";

    /**
     * Initializes the task.
     */
    @Override
    public void init() {
        super.init();
        xmlCatalog.setProject(getProject());
    }

    /**
     * @return the xmlCatalog as the EntityResolver.
     */
    protected EntityResolver getEntityResolver() {
        return xmlCatalog;
    }

    /**
     * Run the task.
     * @throws BuildException The exception raised during task execution.
     * @todo validate the source file is valid before opening, print a better error message
     * @todo add a verbose level log message listing the name of the file being loaded
     */
    @Override
    public void execute() throws BuildException {
        Resource r = getResource();
// r                    0	: [('r', 0.3982636124721517), ('archive', 0.08554325193244808), ('genericJarFile', 0.07188522583219714), ('systemId', 0.0718763188160768), ('file', 0.04972582974886224), ('e', 0.04841493079185729), ('name', 0.045263079759358706), ('line', 0.03846230390202204), ('l', 0.037819276199697986), ('is', 0.03749491029637475)]
        if (r == null) {
            throw new BuildException("XmlProperty task requires a source resource");
        }
        try {
            log("Loading " + src, Project.MSG_VERBOSE);
            if (r.isExists()) {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
// factory              6	: [('dbfactory', 0.6250080885428859), ('saxParserFactory', 0.17201750052328185), ('spFactory', 0.17201739684046832), ('dbFactory', 0.12500808854288592), ('runner', 0.05752501951413667), ('ea', 0.05738874007820683), ('factory', 0.05735097122336115), ('main', 0.057341726690463786), ('ts', 0.057339760244146144), ('parserFactory', 0.057339760244146144)]
                factory.setValidating(validate);
                factory.setNamespaceAware(false);
                DocumentBuilder builder = factory.newDocumentBuilder();
// builder              3	: [('doc', 0.5006771207023253), ('dbuilder', 0.33125432075894573), ('dBuilder', 0.33125418251519434), ('builder', 0.196886647677769), ('db', 0.015629182515194347), ('i', 0.00655388810176439), ('out', 0.003591271941675153), ('name', 0.0030767535942954893), ('b', 0.002940320448477736), ('value', 0.0027100249892455983)]
                builder.setEntityResolver(getEntityResolver());
                Document document;
// document             7	: [('doc', 0.8083487135923897), ('os', 0.16790029884562543), ('buildFile', 0.16776109220577082), ('base', 0.16763988868754479), ('cmd', 0.058745948703817254), ('sb', 0.03584549558190128), ('d', 0.02979691563365739), ('document', 0.02798064947888759), ('buf', 0.026100995447981442), ('out', 0.022021601373978485)]
                FileProvider fp = src.as(FileProvider.class);
// fp                   1	: [('in', 0.7555992729149196), ('fp', 0.6750338015320569), ('up', 0.06361375253023646), ('a', 0.03855460024727148), ('view', 0.03816621654781684), ('f', 0.03169439076768295), ('c', 0.0316665732428162), ('t', 0.029243604359231474), ('setProjectM', 0.025444527468164602), ('data2', 0.01654106874832094)]
                if (fp != null) {
                    document = builder.parse(fp.getFile());
                } else {
                    document = builder.parse(src.getInputStream());
                }
                Element topElement = document.getDocumentElement();
// topElement           5	: [('currentTest', 0.7275644652371399), ('node', 0.3416905178967866), ('nodeChildren', 0.2541712532753065), ('topChildren', 0.25417118415434814), ('messageElement', 0.07551459369968748), ('topElement', 0.07551424809489568), ('root', 0.014361698402682914), ('i', 0.0072208984107825095), ('element', 0.006169055227750703), ('name', 0.006153090237870431)]
                // Keep a hashtable of attributes added by this task.
                // This task is allow to override its own properties
                // but not other properties.  So we need to keep track
                // of which properties we've added.
                addedAttributes = new Hashtable<>();
                if (keepRoot) {
                    addNodeRecursively(topElement, prefix, null);
                } else {
                    NodeList topChildren = topElement.getChildNodes();
// topChildren          1	: [('nodeChildren', 0.9375036393888722), ('topChildren', 0.5476230918868212), ('node', 0.1708452589483933), ('topElement', 0.1270850027992046), ('children', 0.11906230794298), ('recmatches', 0.05357588756495236), ('nl', 0.029767884416512454), ('nl2', 0.011910326833619443), ('childList', 0.011909220898285694), ('name', 0.00047179998885547544)]
                    int numChildren = topChildren.getLength();
// numChildren          0	: [('numChildren', 0.5040200247324476), ('size', 0.11798244838689298), ('len', 0.04469824926020034), ('alphaSize', 0.03578906304753663), ('attrs', 0.033725013462821966), ('nSelectors', 0.029663132485464386), ('length', 0.027533323670713924), ('count', 0.02630868786405597), ('max', 0.02469294388295597), ('min', 0.02469166516219651)]
                    for (int i = 0; i < numChildren; i++) {
// i                    0	: [('i', 0.9391957696806699), ('j', 0.045201086921069465), ('x', 0.026150280833698202), ('counter', 0.02446774516271218), ('t', 0.014188295327257633), ('col', 0.010486378182522344), ('pcounter', 0.010356077606494212), ('n', 0.008637114206981747), ('c', 0.007112609467809439), ('pcount', 0.005559620513413562)]
                        addNodeRecursively(topChildren.item(i), prefix, null);
                    }
                }
            } else {
                log("Unable to find property resource: " + r, Project.MSG_VERBOSE);
            }
        } catch (SAXException sxe) {
// sxe                  No	: [('fis', 0.7625366773956611), ('e', 0.42380079572903123), ('se', 0.40480584890993926), ('exc', 0.0731203629031173), ('s', 0.05160532610566664), ('lastShadow', 0.05023497629660924), ('rn', 0.05004876580608359), ('ex', 0.036575444505949094), ('c', 0.011542277663381935), ('f', 0.00983866587145015)]
            // Error generated during parsing
            Exception x = sxe;
// x                    2	: [('ioe', 0.8777275166324812), ('nested', 0.5008514630281099), ('x', 0.25296648476007433), ('cmd', 0.16829655582079406), ('e', 0.05114645703528096), ('to', 0.04611991031253789), ('commandLine', 0.03345921708902013), ('project', 0.0320839714917787), ('cp', 0.028512991921464444), ('execute', 0.020271733793640775)]
            if (sxe.getException() != null) {
                x = sxe.getException();
            }
            throw new BuildException("Failed to load " + src, x);
        } catch (ParserConfigurationException pce) {
// pce                  1	: [('e', 0.5867738820581697), ('pce', 0.2914072610660261), ('ex', 0.11204047629028827), ('msg', 0.10219735856518988), ('ioe', 0.08419998332807777), ('message', 0.07231955788507098), ('s', 0.023973260873358767), ('t', 0.018260886469848702), ('ie', 0.01784126892436277), ('exc', 0.01266073206350885)]
            // Parser with specified options can't be built
            throw new BuildException(pce);
        } catch (IOException ioe) {
// ioe                  2	: [('x', 0.8776196791570218), ('e', 0.40890889848132056), ('ioe', 0.314466985478059), ('ex', 0.12990932333622068), ('ioex', 0.020484517696587202), ('exc', 0.01379596141978942), ('eyeOhEx', 0.009756907569454491), ('ex2', 0.006432359003743138), ('be', 0.0063639461816513716), ('exception', 0.006361043139927197)]
            // I/O error
            throw new BuildException("Failed to load " + src, ioe);
        }
    }

    /**
     * Iterate through all nodes in the tree.
     */
    private void addNodeRecursively(Node node, String prefix, Object container) {
// node                 1	: [('prefix', 0.7505174859213747), ('node', 0.6250398036213297), ('jarFile', 0.33428932836595815), ('parent', 0.1753237231123288), ('manifest', 0.16712001512858057), ('n', 0.08602877240433462), ('attributeNode', 0.05731981978233852), ('child', 0.027079631254106517), ('ftab', 0.0106076842115073), ('len', 0.0097130575016632)]
// prefix               0	: [('prefix', 0.5017503348911572), ('name', 0.3816832257028476), ('file', 0.07133955280816928), ('r', 0.041660244890013506), ('f', 0.03764768845296513), ('rc', 0.031983095432943316), ('d', 0.022371758015211556), ('listenerDef', 0.02167454094381564), ('ftp', 0.019806182990112473), ('tokenizedPattern', 0.01579789471738176)]
// container            0	: [('container', 0.8755433819375488), ('nodeObject', 0.7500124398697565), ('value', 0.017639230666579516), ('parent', 0.007059082417654457), ('o', 0.00499851819637297), ('arg', 0.002790303803479129), ('source', 0.002327449201774477), ('child', 0.0020892848412929132), ('l', 0.0020293675441306618), ('offset', 0.001988670715964758)]
        // Set the prefix for this node to include its tag name.
        String nodePrefix = prefix;
// nodePrefix           1	: [('prefix', 0.29222456531082663), ('nodePrefix', 0.29169603274842987), ('sTestCmdLine', 0.1967075686854077), ('listPrefix', 0.1966961327621587), ('project', 0.16126029909764747), ('attributes', 0.16088761704117843), ('cmd', 0.07553960727384437), ('args', 0.06354104608299174), ('msg', 0.061545285575872154), ('name', 0.05995839003689423)]
        if (node.getNodeType() != Node.TEXT_NODE) {
            if (!prefix.trim().isEmpty()) {
                nodePrefix += ".";
            }
            nodePrefix += node.getNodeName();
        }
        // Pass the container to the processing of this node,
        Object nodeObject = processNode(node, nodePrefix, container);
// nodeObject           No	: [('container', 0.7500229450251952), ('o', 0.7420271015595429), ('rv', 0.062212680178424934), ('instance', 0.05526802837385734), ('value', 0.023226114542937076), ('currValue', 0.020545287751327225), ('obj', 0.011652999674847701), ('target', 0.010556968676313509), ('expanded', 0.010349952716883373), ('masterValue', 0.010273166688686927)]
        // now, iterate through children.
        if (node.hasChildNodes()) {
            NodeList nodeChildren = node.getChildNodes();
// nodeChildren         2	: [('topChildren', 0.9375036393888722), ('recmatches', 0.30357588756495235), ('nodeChildren', 0.29762309188682123), ('node', 0.1708452591261879), ('topElement', 0.12708500281031676), ('children', 0.11906230794298), ('nl', 0.029767884416512454), ('nl2', 0.011910326833619443), ('childList', 0.011909220898285694), ('name', 0.00047180748956506257)]
            int numChildren = nodeChildren.getLength();
// numChildren          0	: [('numChildren', 0.5040200247324476), ('size', 0.11798244838689298), ('len', 0.04469824926020034), ('alphaSize', 0.03578906304753663), ('attrs', 0.033725013462821966), ('nSelectors', 0.029663132485464386), ('length', 0.027533323670713924), ('count', 0.02630868786405597), ('max', 0.02469294388295597), ('min', 0.02469166516219651)]
            for (int i = 0; i < numChildren; i++) {
// i                    0	: [('i', 0.9391957696806699), ('j', 0.045201086921069465), ('x', 0.026150280833698202), ('counter', 0.02446774516271218), ('t', 0.014188295327257633), ('col', 0.010486378182522344), ('pcounter', 0.010356077606494212), ('n', 0.008637114206981747), ('c', 0.007112609467809439), ('pcount', 0.005559620513413562)]
                // For each child, pass the object added by
                // processNode to its children -- in other word, each
                // object can pass information along to its children.
                addNodeRecursively(nodeChildren.item(i), nodePrefix, nodeObject);
            }
        }
    }

    void addNodeRecursively(org.w3c.dom.Node node, String prefix) {
// node                 0	: [('node', 0.533803207998725), ('topChildren', 0.25417125327072), ('nodeChildren', 0.25417125327072), ('topElement', 0.2541700055947399), ('n', 0.03047321364908295), ('parent', 0.027175567478653045), ('child', 0.027079628861673182), ('attributeNode', 0.020282782481106394), ('copy', 0.006781987055146407), ('idNode', 0.0067656747254581885)]
// prefix               1	: [('nodePrefix', 0.6000467080387696), ('prefix', 0.5035006561090092), ('name', 0.26336597742983275), ('filename', 0.011882304906167918), ('value', 0.007330501747597327), ('encoding', 0.0043969008696299805), ('tag', 0.0034423176007988185), ('qname', 0.0031825619701667477), ('dir', 0.0031364234584993315), ('message', 0.0027611138292720256)]
        addNodeRecursively(node, prefix, null);
    }

    /**
     * Process the given node, adding any required attributes from
     * this child node alone -- but <em>not</em> processing any
     * children.
     *
     * @param node the XML Node to parse
     * @param prefix A string to prepend to any properties that get
     * added by this node.
     * @param container Optionally, an object that a parent node
     * generated that this node might belong to.  For example, this
     * node could be within a node that generated a Path.
     * @return the Object created by this node.  Generally, this is
     * either a String if this node resulted in setting an attribute,
     * or a Path.
     */
    public Object processNode(Node node, String prefix, Object container) {
// node                 7	: [('child', 0.9375626808541832), ('attributeNode', 0.5714489738000494), ('c', 0.3978860917605892), ('locals', 0.39737895861542033), ('id', 0.2504771101195407), ('idNode', 0.25045775136982834), ('parent', 0.17686815460679017), ('node', 0.16535781164384408), ('file', 0.14527897217561658), ('arg', 0.13988053558802044)]
// prefix               1	: [('n', 0.6364241978612425), ('prefix', 0.5017502593206552), ('name', 0.38168299065504757), ('propertyName', 0.2455212392350883), ('filename', 0.005941152851200718), ('value', 0.003665252066314302), ('encoding', 0.00219845069900307), ('tag', 0.0017211588691983884), ('qname', 0.001591281020858843), ('dir', 0.0015682121631418943)]
// container            0	: [('container', 0.8755431747728252), ('path', 0.4584399962156118), ('userClasspath', 0.22917490212002328), ('properties', 0.13721867313855976), ('entry', 0.09473188825204355), ('value', 0.06871081243643444), ('props', 0.06862236457705594), ('destination', 0.06861491716166161), ('xmlproperties', 0.06860876866189908), ('valueMethod', 0.06860849215971802)]
        // Parse the attribute(s) and text of this node, adding
        // properties for each.
        // if the "path" attribute is specified, then return the created path
        // which will be passed to the children of this node.
        Object addedPath = null;
// addedPath            No	: [('id', 0.5000778067826521), ('o', 0.20484073302025455), ('result', 0.13408578470347424), ('element', 0.10101967171725366), ('loader', 0.08027485852905179), ('categoryObject', 0.06589644382306513), ('commandLine', 0.060231771215956), ('location', 0.06021299584157945), ('value', 0.060004733475892164), ('ch', 0.059834449282169765)]
        // The value of an id attribute of this node.
        String id = null;
// id                   6	: [('addedPath', 0.5000257092900885), ('node', 0.25157969279589926), ('idNode', 0.250454550859011), ('m', 0.1877409519440969), ('name', 0.06659193097852749), ('line', 0.0543881919357073), ('id', 0.05047706036713647), ('depends', 0.05040337065127158), ('resMp', 0.050388052061224806), ('uri', 0.026876373645056652)]
        if (node.hasAttributes()) {
            NamedNodeMap nodeAttributes = node.getAttributes();
// nodeAttributes       0	: [('nodeAttributes', 0.775004044271443), ('size', 0.11798244865016373), ('attributes', 0.05002367488413927), ('attrs', 0.05002091000911162), ('len', 0.044698249559246575), ('alphaSize', 0.03578906313376468), ('nSelectors', 0.029663132516653257), ('length', 0.027533324460526205), ('max', 0.02469294395083763), ('min', 0.02469166519613734)]
            // Is there an id attribute?
            Node idNode = nodeAttributes.getNamedItem(ID);
// idNode               5	: [('copy', 0.5641954968224943), ('n', 0.25761830361224375), ('node', 0.25158108349560365), ('id', 0.2504740257016172), ('loader', 0.08027485797497598), ('idNode', 0.06334654760752305), ('location', 0.06021299526915676), ('p', 0.0406492566216749), ('prefix', 0.04018762842935126), ('suffix', 0.04014463519325176)]
            id = semanticAttributes && idNode != null ? idNode.getNodeValue() : null;
            // Now, iterate through the attributes adding them.
            for (int i = 0; i < nodeAttributes.getLength(); i++) {
// i                    0	: [('i', 0.852483269108941), ('icounter', 0.16990177757330402), ('j', 0.02264469594362585), ('t', 0.014188295327257633), ('counter', 0.009430151177749774), ('x', 0.0073532883524951975), ('n', 0.004877715710741146), ('v', 0.004476483398197383), ('ret', 0.0034955682958700152), ('c', 0.0033532109715688377)]
                Node attributeNode = nodeAttributes.item(i);
// attributeNode        3	: [('child', 0.8940099539067633), ('node', 0.5417143715082083), ('n', 0.02724665210609738), ('attributeNode', 0.025550343174734102), ('i', 0.007222052057789394), ('name', 0.0061547429100293006), ('b', 0.005881956593926352), ('value', 0.0054212051785523414), ('file', 0.004261598146753617), ('path', 0.003641423300378674)]
                if (!semanticAttributes) {
                    String attributeName = getAttributeName(attributeNode);
// attributeName        0	: [('attributeName', 0.773212961768549), ('name', 0.05633330192724723), ('msg', 0.03077264113123468), ('sep', 0.02102915495546897), ('message', 0.013649599464769513), ('s', 0.012169708382783868), ('dir', 0.01018621662599182), ('key', 0.008577536652840655), ('className', 0.008444260430360149), ('entry', 0.007734650365107266)]
                    String attributeValue = getAttributeValue(attributeNode);
// attributeValue       0	: [('attributeValue', 0.900005449970949), ('output', 0.03492317823564679), ('name', 0.029975359965481514), ('value', 0.028758062311775565), ('line', 0.017204295384761113), ('s', 0.016517748907764716), ('result', 0.014130906142798464), ('path', 0.011832036525181425), ('log', 0.011532059087392016), ('filename', 0.009811884442487836)]
                    addProperty(prefix + attributeName, attributeValue, null);
                } else {
                    String nodeName = attributeNode.getNodeName();
// nodeName             No	: [('attributeName', 0.5018570994979529), ('base', 0.17903773179925644), ('currentSuffix', 0.13020016124330702), ('ant', 0.08072632923638835), ('name', 0.0713418060543872), ('token', 0.0658058596225308), ('path', 0.060953052685774244), ('shortLabel', 0.05973782438704913), ('defaultManifest', 0.059597802042348186), ('errorMsg', 0.0595940592034038)]
                    String attributeValue = getAttributeValue(attributeNode);
// attributeValue       0	: [('attributeValue', 0.9000053117437098), ('filename', 0.3511077064063658), ('value', 0.20469651258971228), ('id', 0.20449033341484213), ('loaderName', 0.19316059630974858), ('compiledScriptRefName', 0.19033113292945897), ('classpath', 0.09985467501413868), ('cp', 0.07972965095020436), ('parent', 0.07208083553023566), ('project', 0.07126405020548852)]
                    Path containingPath = container instanceof Path ? (Path) container : null;
// containingPath       No	: [('classpath', 0.15209725164050716), ('arg', 0.13966679718952596), ('p', 0.09155370056692164), ('myPath', 0.08247151865004254), ('n', 0.060634150063881634), ('resource', 0.06055791880350118), ('d', 0.05998962062896976), ('path', 0.05865105817356128), ('pb', 0.05498544809422208), ('c', 0.0532210550029457)]
                    /*
                     * The main conditional logic -- if the attribute
                     * is somehow "special" (i.e., it has known
                     * semantic meaning) then deal with it
                     * appropriately.
                     */
                    if (ID.equals(nodeName)) {
                        // ID has already been found above.
                        continue;
                    }
                    if (containingPath != null && PATH.equals(nodeName)) {
                        // A "path" attribute for a node within a Path object.
                        containingPath.setPath(attributeValue);
                    } else if (containingPath != null && container instanceof Path && REF_ID.equals(nodeName)) {
                        // A "refid" attribute for a node within a Path object.
                        containingPath.setPath(attributeValue);
                    } else if (containingPath != null && container instanceof Path && LOCATION.equals(nodeName)) {
                        // A "location" attribute for a node within a
                        // Path object.
                        containingPath.setLocation(resolveFile(attributeValue));
                    } else if (PATHID.equals(nodeName)) {
                        // A node identifying a new path
                        if (container != null) {
                            throw new BuildException("XmlProperty does not support nested paths");
                        }
                        addedPath = new Path(getProject());
                        getProject().addReference(attributeValue, addedPath);
                    } else {
                        // An arbitrary attribute.
                        String attributeName = getAttributeName(attributeNode);
// attributeName        0	: [('attributeName', 0.773212961768549), ('base', 0.17903773122316435), ('currentSuffix', 0.1302001612304642), ('ant', 0.08072632918868647), ('name', 0.07134180217402178), ('token', 0.06580585942988854), ('nodeName', 0.06510077622835805), ('path', 0.060953051513408525), ('shortLabel', 0.0597378243613635), ('defaultManifest', 0.05959780202583599)]
                        addProperty(prefix + attributeName, attributeValue, id);
                    }
                }
            }
        }
        String nodeText = null;
// nodeText             1	: [('emptyNode', 0.875009038776923), ('nodeText', 0.5000565726844001), ('suffix', 0.345488567024831), ('rv', 0.27380951956492006), ('name', 0.157824522031716), ('pwdfile', 0.14879406429087602), ('obj', 0.07848556809717322), ('k', 0.06930407915126396), ('uri', 0.06921998115954518), ('ftp', 0.06802579377832785)]
        boolean emptyNode = false;
// emptyNode            No	: [('nodeText', 0.8750423339326395), ('uri', 0.6338937212293754), ('success', 0.14871242153325379), ('isInIncludeMode', 0.06117157703270878), ('args', 0.04981847907980738), ('debug', 0.047603057552755755), ('caseSensitive', 0.0476029884299627), ('haltError', 0.047311737228858), ('autoFound', 0.047253639058781764), ('cacheFileExists', 0.04725356993598872)]
        boolean semanticEmptyOverride = false;
// semanticEmptyOverride No	: [('base', 0.7506503786792769), ('attrUri', 0.12054104080857009), ('fullpath', 0.08002318645850036), ('line', 0.07985507888267529), ('toReturn', 0.07978389965906137), ('values', 0.04647796488655887), ('prefix', 0.04624659452825785), ('other', 0.040166294933269966), ('success', 0.04007207065606081), ('vpath', 0.03991854059168267)]
        if (node.getNodeType() == Node.ELEMENT_NODE && semanticAttributes && node.hasAttributes() && (node.getAttributes().getNamedItem(VALUE) != null || node.getAttributes().getNamedItem(LOCATION) != null || node.getAttributes().getNamedItem(REF_ID) != null || node.getAttributes().getNamedItem(PATH) != null || node.getAttributes().getNamedItem(PATHID) != null)) {
            semanticEmptyOverride = true;
        }
        if (node.getNodeType() == Node.TEXT_NODE) {
            // For the text node, add a property.
            nodeText = getAttributeValue(node);
        } else if (node.getNodeType() == Node.ELEMENT_NODE && node.getChildNodes().getLength() == 1 && node.getFirstChild().getNodeType() == Node.CDATA_SECTION_NODE) {
            nodeText = node.getFirstChild().getNodeValue();
            if (nodeText.isEmpty() && !semanticEmptyOverride) {
                emptyNode = true;
            }
        } else if (node.getNodeType() == Node.ELEMENT_NODE && node.getChildNodes().getLength() == 0 && !semanticEmptyOverride) {
            nodeText = "";
            emptyNode = true;
        } else if (node.getNodeType() == Node.ELEMENT_NODE && node.getChildNodes().getLength() == 1 && node.getFirstChild().getNodeType() == Node.TEXT_NODE && node.getFirstChild().getNodeValue().isEmpty() && !semanticEmptyOverride) {
            nodeText = "";
            emptyNode = true;
        }
        if (nodeText != null) {
            // If the containing object was a String, then use it as the ID.
            if (semanticAttributes && id == null && container instanceof String) {
                id = (String) container;
            }
            if (!nodeText.trim().isEmpty() || emptyNode) {
                addProperty(prefix, nodeText, id);
            }
        }
        // Return the Path we added or the ID of this node for
        // children to reference if needed.  Path objects are
        // definitely used by child path elements, and ID may be used
        // for a child text node.
        return addedPath != null ? addedPath : id;
    }

    /**
     * Actually add the given property/value to the project
     * after writing a log message.
     */
    private void addProperty(String name, String value, String id) {
// name                 3	: [('eyeOhEx', 0.7542557896171486), ('n', 0.7506961232610186), ('targetName', 0.5683600830158606), ('name', 0.36455833438174134), ('propertyName', 0.28282956086638383), ('test', 0.2790086763128387), ('prop', 0.09569183958655755), ('property', 0.09043720978166604), ('value', 0.0825469598200819), ('outV', 0.0823265206974714)]
// value                0	: [('value', 0.8145525131148755), ('target', 0.7917770029114303), ('r', 0.18322332232932276), ('matchDirEntries', 0.13862205663161611), ('matchFileEntries', 0.1386219875051534), ('buffer', 0.12049685706117678), ('project', 0.07013627472842758), ('url', 0.06971690717850365), ('localName', 0.06947570524825893), ('names', 0.06943234827431165)]
// id                   No	: [('name', 0.33750750711499333), ('value', 0.20469643283444677), ('attributeValue', 0.19316208590529552), ('loaderName', 0.1931605962078855), ('compiledScriptRefName', 0.19033113286093864), ('filename', 0.04757669013969184), ('javah', 0.018789983793603252), ('encoding', 0.017604700445939866), ('c', 0.016801479720694658), ('ch', 0.014870198319213686)]
        String msg = name + ":" + value;
// msg                  1	: [('p', 0.5042442442839803), ('msg', 0.35564874965205584), ('message', 0.19899889638901203), ('name', 0.05995932634767719), ('e', 0.049675143926411314), ('toExecute', 0.04875165265651057), ('t', 0.02465296544328353), ('commandline', 0.02444496664014417), ('ioe', 0.024404245352119155), ('errorMessage', 0.02437529679717503)]
        if (id != null) {
            msg += ("(id=" + id + ")");
        }
        log(msg, Project.MSG_DEBUG);
        if (addedAttributes.containsKey(name)) {
            // If this attribute was added by this task, then
            // we append this value to the existing value.
            // We use the setProperty method which will
            // forcibly override the property if it already exists.
            // We need to put these properties into the project
            // when we read them, though (instead of keeping them
            // outside of the project and batch adding them at the end)
            // to allow other properties to reference them.
            value = addedAttributes.get(name) + getDelimiter() + value;
            getProject().setProperty(name, value);
            addedAttributes.put(name, value);
        } else if (getProject().getProperty(name) == null) {
            getProject().setNewProperty(name, value);
            addedAttributes.put(name, value);
        } else {
            log("Override ignored for property " + name, Project.MSG_VERBOSE);
        }
        if (id != null) {
            getProject().addReference(id, value);
        }
    }

    /**
     * Return a reasonable attribute name for the given node.
     * If we are using semantic attributes or collapsing
     * attributes, the returned name is ".nodename".
     * Otherwise, we return "(nodename)".  This is long-standing
     * (and default) &lt;xmlproperty&gt; behavior.
     */
    private String getAttributeName(Node attributeNode) {
// attributeNode        0	: [('attributeNode', 0.8073007354944538), ('node', 0.18533028748558), ('parent', 0.17532371562680119), ('n', 0.0860287692046385), ('child', 0.027079628861673182), ('name', 0.01600956716981417), ('copy', 0.006781987055146407), ('idNode', 0.0067656747254581885), ('newChild', 0.006765398245293981), ('item', 0.003391167069378709)]
        String attributeName = attributeNode.getNodeName();
// attributeName        1	: [('nodeValue', 0.7504693133305954), ('attributeName', 0.6256184065733964), ('ioe', 0.25263886945814), ('l', 0.25027186170097837), ('context', 0.25026992330150744), ('nodeName', 0.1265199564419333), ('suffix', 0.12603441098978913), ('msg', 0.015386321670099599), ('name', 0.014989598479353536), ('args', 0.009182095533005424)]
        if (semanticAttributes) {
            // Never include the "refid" attribute as part of the
            // attribute name.
            if (REF_ID.equals(attributeName)) {
                return "";
            }
            // Otherwise, return it appended unless property to hide it is set.
            if (!isSemanticAttribute(attributeName) || includeSemanticAttribute) {
                return "." + attributeName;
            }
            return "";
        }
        return collapseAttributes ? "." + attributeName : "(" + attributeName + ")";
    }

    /**
     * Return whether the provided attribute name is recognized or not.
     */
    private static boolean isSemanticAttribute(String attributeName) {
// attributeName        No	: [('exclusionLogMsg', 0.18357857973638028), ('name', 0.06261027970569133), ('up', 0.05561243642840029), ('t', 0.04618226839855344), ('element', 0.04602280031750989), ('fileNameMapper', 0.045920171764270865), ('contains', 0.045907262374653875), ('serverLanguageCodeConfig', 0.045900284787973075), ('cnfeMessage', 0.045896126973113696), ('missingMainClass', 0.045896126973113696)]
        return Arrays.asList(ATTRIBUTES).contains(attributeName);
    }

    /**
     * Return the value for the given attribute.
     * If we are not using semantic attributes, its just the
     * literal string value of the attribute.
     *
     * <p>If we <em>are</em> using semantic attributes, then first
     * dependent properties are resolved (i.e., ${foo} is resolved
     * based on the foo property value), and then an appropriate data
     * type is used.  In particular, location-based properties are
     * resolved to absolute file names.  Also for refid values, look
     * up the referenced object from the project.</p>
     */
    private String getAttributeValue(Node attributeNode) {
// attributeNode        0	: [('attributeNode', 0.8072981767087787), ('node', 0.18533028765069537), ('parent', 0.17532371749810857), ('n', 0.08602877000453069), ('child', 0.0270796294597577), ('name', 0.016009593229208453), ('i', 0.007221303103901564), ('copy', 0.006781987289977139), ('idNode', 0.0067656747438043395), ('newChild', 0.006765398259970902)]
        String nodeValue = attributeNode.getNodeValue().trim();
// nodeValue            No	: [('attributeName', 0.7513091195127298), ('s', 0.20787288469809684), ('msg', 0.20594630714279843), ('index', 0.20536467945883255), ('value', 0.12403278556116934), ('key', 0.12390923434224693), ('property', 0.12384136977418565), ('prop', 0.12381954412752103), ('loaderName', 0.12381237200353316), ('compiledScriptRefName', 0.12381178266999868)]
        if (semanticAttributes) {
            String attributeName = attributeNode.getNodeName();
// attributeName        0	: [('attributeName', 0.6253162458616451), ('nodeName', 0.503044831470195), ('msg', 0.030772641683453818), ('name', 0.029979191138081698), ('args', 0.018393703329032205), ('other', 0.015292972506666148), ('message', 0.013649600005980967), ('s', 0.012169709394574093), ('p2', 0.009728968845230028), ('h1', 0.009728415877563153)]
            nodeValue = getProject().replaceProperties(nodeValue);
            if (LOCATION.equals(attributeName)) {
                File f = resolveFile(nodeValue);
// f                    0	: [('f', 0.2828130323515463), ('file', 0.0642729926942293), ('result', 0.060528355146956106), ('srcFile', 0.03628605572587186), ('dir', 0.035083246173945405), ('newFilter', 0.021862550513660862), ('fromDir', 0.021304284089886646), ('element', 0.021121905408578535), ('workingDir', 0.019131268068561844), ('classFile', 0.018930918176580273)]
                return f.getPath();
            }
            if (REF_ID.equals(attributeName)) {
                Object ref = getProject().getReference(nodeValue);
// ref                  No	: [('o', 0.22741129419716422), ('value', 0.15499572443042076), ('obj', 0.1441576076597491), ('result', 0.14177420665858614), ('ret', 0.06745085821037974), ('theClass', 0.06731283278093017), ('val', 0.06726627684971313), ('element', 0.05050796493689068), ('jExecutable', 0.04483460223697166), ('categoryObject', 0.03294784779286717)]
                if (ref != null) {
                    return ref.toString();
                }
            }
        }
        return nodeValue;
    }

    /**
     * The XML file to parse; required.
     * @param src the file to parse
     */
    public void setFile(File src) {
// src                  1	: [('file', 0.6166805272630077), ('src', 0.41994346091659074), ('srcFile', 0.18488028985010344), ('f', 0.1336861090516601), ('value', 0.029221260083765976), ('v', 0.029078724062658608), ('fromDir', 0.018574677733352325), ('sourceFile', 0.015008525765476835), ('f1', 0.014977303534254227), ('entryFile', 0.014964966349886437)]
        setSrcResource(new FileResource(src));
    }

    /**
     * The resource to pack; required.
     * @param src resource to expand
     */
    public void setSrcResource(Resource src) {
// src                  0	: [('src', 0.8880155247234042), ('r', 0.03438255862827722), ('javah', 0.018789982693710256), ('c', 0.016801473467381273), ('ch', 0.014870195106278978), ('o', 0.013696990708906313), ('test', 0.013407900084607986), ('from', 0.013052698082273512), ('t', 0.012632935131182606), ('name', 0.010810923386422415)]
        if (src.isDirectory()) {
            throw new BuildException("the source can't be a directory");
        }
        if (src.as(FileProvider.class) != null || supportsNonFileResources()) {
            this.src = src;
        } else {
            throw new BuildException("Only FileSystem resources are supported.");
        }
    }

    /**
     * Set the source resource.
     * @param a the resource to pack as a single element Resource collection.
     */
    public void addConfigured(ResourceCollection a) {
// a                    0	: [('a', 0.890680685722847), ('rc', 0.2213240226242129), ('c', 0.010008069486282484), ('line', 0.009369315642933628), ('file', 0.00917861336882755), ('name', 0.00850774272766269), ('instr', 0.007226583966231555), ('event', 0.005844283705674793), ('project', 0.0055575600136026845), ('t', 0.0053801450678961655)]
        if (a.size() != 1) {
            throw new BuildException("only single argument resource collections are supported as archives");
        }
        setSrcResource(a.iterator().next());
    }

    /**
     * the prefix to prepend to each property
     * @param prefix the prefix to prepend to each property
     */
    public void setPrefix(String prefix) {
// prefix               0	: [('prefix', 0.8659505818286185), ('aPrefix', 0.08752773781857259), ('other', 0.03315768109759898), ('name', 0.00769441960549964), ('oldPrefix', 0.0055235327899963065), ('zfs', 0.0037046634203227857), ('s', 0.0027081106119227188), ('value', 0.002569089730620228), ('message', 0.002106870632192223), ('msg', 0.0019098336013175267)]
        this.prefix = prefix.trim();
    }

    /**
     * flag to include the xml root tag as a
     * first value in the property name; optional,
     * default is true
     * @param keepRoot if true (default), include the xml root tag
     */
    public void setKeeproot(boolean keepRoot) {
// keepRoot             No	: [('b', 0.08840525847196394), ('value', 0.022603553559821576), ('verbose', 0.016829026586382915), ('append', 0.012448459200976782), ('debug', 0.008734879145209597), ('caseSensitive', 0.00873460266504539), ('recursive', 0.008621098130951438), ('enable', 0.008266223194396603), ('preserveLastModified', 0.007973671083471783), ('overwrite', 0.00728360869636253)]
        this.keepRoot = keepRoot;
    }

    /**
     * flag to validate the XML file; optional, default false
     * @param validate if true validate the XML file, default false
     */
    public void setValidate(boolean validate) {
// validate             0	: [('validate', 0.9375042614669483), ('b', 0.011050657308995493), ('value', 0.002825444194977697), ('verbose', 0.0021036283232978644), ('append', 0.0015560574001220978), ('debug', 0.0010918598931511996), ('caseSensitive', 0.0010918253331306737), ('recursive', 0.0010776372663689297), ('enable', 0.0010332778992995753), ('preserveLastModified', 0.000996708885433973)]
        this.validate = validate;
    }

    /**
     * flag to treat attributes as nested elements;
     * optional, default false
     * @param collapseAttributes if true treat attributes as nested elements
     */
    public void setCollapseAttributes(boolean collapseAttributes) {
// collapseAttributes   No	: [('b', 0.08840525847196394), ('value', 0.022603553559821576), ('verbose', 0.016829026586382915), ('append', 0.012448459200976782), ('debug', 0.008734879145209597), ('caseSensitive', 0.00873460266504539), ('recursive', 0.008621098130951438), ('enable', 0.008266223194396603), ('preserveLastModified', 0.007973671083471783), ('overwrite', 0.00728360869636253)]
        this.collapseAttributes = collapseAttributes;
    }

    /**
     * Attribute to enable special handling of attributes - see ant manual.
     * @param semanticAttributes if true enable the special handling.
     */
    public void setSemanticAttributes(boolean semanticAttributes) {
// semanticAttributes   No	: [('b', 0.08840525847196394), ('value', 0.022603553559821576), ('verbose', 0.016829026586382915), ('append', 0.012448459200976782), ('debug', 0.008734879145209597), ('caseSensitive', 0.00873460266504539), ('recursive', 0.008621098130951438), ('enable', 0.008266223194396603), ('preserveLastModified', 0.007973671083471783), ('overwrite', 0.00728360869636253)]
        this.semanticAttributes = semanticAttributes;
    }

    /**
     * The directory to use for resolving file references.
     * Ignored if semanticAttributes is not set to true.
     * @param rootDirectory the directory.
     */
    public void setRootDirectory(File rootDirectory) {
// rootDirectory        No	: [('file', 0.11232352844888811), ('basedir', 0.056928704029137675), ('f', 0.049014734482246254), ('dir', 0.021439889396601547), ('src', 0.01976219144848298), ('sourceFile', 0.018506140556666197), ('srcDir', 0.01630017200973539), ('baseDir', 0.013866037632408231), ('destDir', 0.013781939158909459), ('destFile', 0.009656581025905846)]
        this.rootDirectory = rootDirectory;
    }

    /**
     * Include the semantic attribute name as part of the property name.
     * Ignored if semanticAttributes is not set to true.
     * @param includeSemanticAttribute if true include the semantic attribute
     *                                 name.
     */
    public void setIncludeSemanticAttribute(boolean includeSemanticAttribute) {
// includeSemanticAttribute No	: [('b', 0.08840525847196394), ('value', 0.022603553559821576), ('verbose', 0.016829026586382915), ('append', 0.012448459200976782), ('debug', 0.008734879145209597), ('caseSensitive', 0.00873460266504539), ('recursive', 0.008621098130951438), ('enable', 0.008266223194396603), ('preserveLastModified', 0.007973671083471783), ('overwrite', 0.00728360869636253)]
        this.includeSemanticAttribute = includeSemanticAttribute;
    }

    /**
     * add an XMLCatalog as a nested element; optional.
     * @param catalog the XMLCatalog to use
     */
    public void addConfiguredXMLCatalog(XMLCatalog catalog) {
// catalog              0	: [('catalog', 0.9419662644608866), ('xmlCatalog', 0.20000385206264007), ('catalogA', 0.008930031774864367), ('catalogB', 0.004465573260476022), ('cat', 0.0022337068834973723), ('name', 0.0004718065195123305), ('i', 0.00031621421019327234), ('p', 0.00027912830640669945), ('project', 0.0002738060632457128), ('file', 0.0002633229499963878)]
        xmlCatalog.addConfiguredXMLCatalog(catalog);
    }

    /* Expose members for extensibility */
    /**
     * @return the file attribute.
     */
    protected File getFile() {
        FileProvider fp = src.as(FileProvider.class);
// fp                   0	: [('fp', 0.7791479916516663), ('c', 0.13934264415045278), ('cons', 0.1370722017827103), ('fr', 0.09666042376519027), ('result', 0.08331444929364794), ('f', 0.07020149843275358), ('up', 0.0685373667553688), ('fileResource1', 0.06853636450147259), ('attrMap', 0.06852517877385392), ('elemMap', 0.06852472948762459)]
        return fp != null ? fp.getFile() : null;
    }

    /**
     * @return the resource.
     */
    protected Resource getResource() {
        // delegate this way around to support subclasses that
        // overwrite getFile
        File f = getFile();
// f                    1	: [('dir', 0.5093667440258691), ('f', 0.16614622920972066), ('other', 0.14248007971251567), ('c', 0.13934250909446416), ('cons', 0.13707218310814678), ('fp', 0.13706273011050923), ('file', 0.13093966165236062), ('dir1', 0.11317158823726048), ('subdir', 0.09431095007295102), ('result', 0.08331390790880992)]
        FileProvider fp = src.as(FileProvider.class);
// fp                   0	: [('fp', 0.9187583812620559), ('zipFile', 0.25159578494803203), ('ds', 0.16906881018542394), ('keyArray', 0.16686543534179754), ('file', 0.01231039299914446), ('other', 0.009598322653821592), ('value', 0.006829039031180864), ('pcounter', 0.00623856977514733), ('array', 0.0061302028976716475), ('oldValue', 0.006127576301253993)]
        return f == null ? src : fp != null && fp.getFile().equals(f) ? src : new FileResource(f);
    }

    /**
     * @return the prefix attribute.
     */
    protected String getPrefix() {
        return this.prefix;
    }

    /**
     * @return the keeproot attribute.
     */
    protected boolean getKeeproot() {
        return this.keepRoot;
    }

    /**
     * @return the validate attribute.
     */
    protected boolean getValidate() {
        return this.validate;
    }

    /**
     * @return the collapse attributes attribute.
     */
    protected boolean getCollapseAttributes() {
        return this.collapseAttributes;
    }

    /**
     * @return the semantic attributes attribute.
     */
    protected boolean getSemanticAttributes() {
        return this.semanticAttributes;
    }

    /**
     * @return the root directory attribute.
     */
    protected File getRootDirectory() {
        return this.rootDirectory;
    }

    @Deprecated
    protected boolean getIncludeSementicAttribute() {
        return getIncludeSemanticAttribute();
    }

    /**
     * @return the include semantic attribute.
     */
    protected boolean getIncludeSemanticAttribute() {
        return this.includeSemanticAttribute;
    }

    /**
     * Let project resolve the file - or do it ourselves if
     * rootDirectory has been set.
     */
    private File resolveFile(String fileName) {
// fileName             No	: [('file', 0.5044379953078821), ('name', 0.03077767842199856), ('s', 0.010832442447690875), ('value', 0.010276358922480912), ('message', 0.008427482528768892), ('msg', 0.007639334405270108), ('uri', 0.00709272686411501), ('target', 0.00694392573208436), ('line', 0.005779721828559096), ('filename', 0.005717599223618766)]
        return FILE_UTILS.resolveFile(rootDirectory == null ? getProject().getBaseDir() : rootDirectory, fileName);
    }

    /**
     * Whether this task can deal with non-file resources.
     *
     * <p>This implementation returns true only if this task is
     * &lt;xmlproperty&gt;.  Any subclass of this class that also wants to
     * support non-file resources needs to override this method.  We
     * need to do so for backwards compatibility reasons since we
     * can't expect subclasses to support resources.</p>
     * @return true for this task.
     * @since Ant 1.7
     */
    protected boolean supportsNonFileResources() {
        return getClass().equals(XmlProperty.class);
    }

    /**
     * Get the current delimiter.
     * @return delimiter
     */
    public String getDelimiter() {
        return delimiter;
    }

    /**
     * Sets a new delimiter.
     * @param delimiter new value
     * @since Ant 1.7.1
     */
    public void setDelimiter(String delimiter) {
// delimiter            0	: [('delimiter', 0.895837214640056), ('delim', 0.5833882471847193), ('name', 0.00769441960549964), ('s', 0.0027081106119227188), ('value', 0.002569089730620228), ('message', 0.002106870632192223), ('msg', 0.0019098336013175267), ('uri', 0.0017731817160287525), ('target', 0.00173598143302109), ('line', 0.001444930457139774)]
        this.delimiter = delimiter;
    }
}
