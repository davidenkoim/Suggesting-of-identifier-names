// Type of training: selfTestingIdentifier
// Path to file: C:\Users\Igor\IdeaProjects\SLP-Core-0.3\java-projects\ant-master\src\main\org\apache\tools\ant\taskdefs\optional\junitlauncher\confined\JUnitLauncherTask.java
// Number of identifiers: 61	Accuracy: 47.54%	MRR: 53.90%
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
package org.apache.tools.ant.taskdefs.optional.junitlauncher.confined;

import org.apache.tools.ant.AntClassLoader;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.taskdefs.Execute;
import org.apache.tools.ant.taskdefs.ExecuteWatchdog;
import org.apache.tools.ant.taskdefs.LogOutputStream;
import org.apache.tools.ant.taskdefs.PumpStreamHandler;
import org.apache.tools.ant.types.CommandlineJava;
import org.apache.tools.ant.types.Environment;
import org.apache.tools.ant.types.Path;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;
import static org.apache.tools.ant.taskdefs.optional.junitlauncher.confined.Constants.LD_XML_ATTR_EXCLUDE_TAGS;
import static org.apache.tools.ant.taskdefs.optional.junitlauncher.confined.Constants.LD_XML_ATTR_HALT_ON_FAILURE;
import static org.apache.tools.ant.taskdefs.optional.junitlauncher.confined.Constants.LD_XML_ATTR_INCLUDE_TAGS;
import static org.apache.tools.ant.taskdefs.optional.junitlauncher.confined.Constants.LD_XML_ATTR_PRINT_SUMMARY;
import static org.apache.tools.ant.taskdefs.optional.junitlauncher.confined.Constants.LD_XML_ELM_LAUNCH_DEF;

/**
 * An Ant {@link Task} responsible for launching the JUnit platform for running tests.
 * This requires a minimum of JUnit 5, since that's the version in which the JUnit platform launcher
 * APIs were introduced.
 * <p>
 * This task in itself doesn't run the JUnit tests, instead the sole responsibility of
 * this task is to setup the JUnit platform launcher, build requests, launch those requests and then parse the
 * result of the execution to present in a way that's been configured on this Ant task.
 * </p>
 * <p>
 * Furthermore, this task allows users control over which classes to select for passing on to the JUnit 5
 * platform for test execution. It however, is solely the JUnit 5 platform, backed by test engines that
 * decide and execute the tests.
 *
 * @see <a href="https://junit.org/junit5/">JUnit 5 documentation</a>
 */
public class JUnitLauncherTask extends Task {

    private static final String LAUNCHER_SUPPORT_CLASS_NAME = "org.apache.tools.ant.taskdefs.optional.junitlauncher.LauncherSupport";

    private static final String IN_VM_TEST_EXECUTION_CONTEXT_CLASS_NAME = "org.apache.tools.ant.taskdefs.optional.junitlauncher.InVMExecution";

    private static final String TEST_EXECUTION_CONTEXT_CLASS_NAME = "org.apache.tools.ant.taskdefs.optional.junitlauncher.TestExecutionContext";

    private Path classPath;

    private boolean haltOnFailure;

    private String failureProperty;

    private boolean printSummary;

    private final List<TestDefinition> tests = new ArrayList<>();

    private final List<ListenerDefinition> listeners = new ArrayList<>();

    private List<String> includeTags = new ArrayList<>();

    private List<String> excludeTags = new ArrayList<>();

    public JUnitLauncherTask() {
    }

    @Override
    public void execute() throws BuildException {
        if (this.tests.isEmpty()) {
            return;
        }
        final Project project = getProject();
// project              0	: [('project', 0.7085223324518486), ('ex', 0.11338920472399616), ('ioe', 0.11203794971585064), ('be', 0.11084619697588427), ('execMethod', 0.11022583649181351), ('expectedLength', 0.11022580193133433), ('p', 0.08784267347987056), ('e', 0.06963060902874008), ('prj', 0.023005553094539475), ('path', 0.011823638255290177)]
        for (final TestDefinition test : this.tests) {
// test                 0	: [('test', 0.9351693093324231), ('request', 0.14584574394315603), ('urlFromPath', 0.1458362506843959), ('file', 0.07133955452683621), ('n', 0.06063410669716359), ('resource', 0.060557893248822124), ('d', 0.05998960525469562), ('e', 0.04841493223029097), ('name', 0.045263081699592915), ('r', 0.04166024603900221)]
            if (!test.shouldRun(project)) {
                log("Excluding test " + test + " since it's considered not to run " + "in context of project " + project, Project.MSG_DEBUG);
                continue;
            }
            if (test.getForkDefinition() != null) {
                forkTest(test);
            } else {
                launchViaReflection(new InVMLaunch(Collections.singletonList(test)));
            }
        }
    }

    /**
     * Adds the {@link Path} to the classpath which will be used for execution of the tests
     *
     * @param path The classpath
     */
    public void addConfiguredClassPath(final Path path) {
// path                 0	: [('path', 0.30544214770724337), ('trackerFile', 0.11241567188137115), ('classpath', 0.08619570139435409), ('mp', 0.04914481261567589), ('rc', 0.0374268335659816), ('p', 0.022560705981460392), ('name', 0.022366558994697822), ('instr', 0.021552902159280914), ('r', 0.019661231884219622), ('c', 0.018706438541045547)]
        if (this.classPath == null) {
            // create a "wrapper" path which can hold on to multiple
            // paths that get passed to this method (if at all the task in the build is
            // configured with multiple classpaht elements)
            this.classPath = new Path(getProject());
        }
        this.classPath.add(path);
    }

    /**
     * Adds a {@link SingleTestClass} that will be passed on to the underlying JUnit platform
     * for possible execution of the test
     *
     * @param test The test
     */
    public void addConfiguredTest(final SingleTestClass test) {
// test                 3	: [('testClasses', 0.8875029517699221), ('testDefinition', 0.3958483339647165), ('singleTestClass', 0.19792586113754765), ('test', 0.08599655553442183), ('className', 0.0837128585772114), ('rc', 0.009356709482938921), ('name', 0.0055916482197196655), ('instr', 0.00538822614396171), ('r', 0.004915311398878124), ('c', 0.004676612965613513)]
        this.preConfigure(test);
        this.tests.add(test);
    }

    /**
     * Adds {@link TestClasses} that will be passed on to the underlying JUnit platform for
     * possible execution of the tests
     *
     * @param testClasses The test classes
     */
    public void addConfiguredTestClasses(final TestClasses testClasses) {
// testClasses          No	: [('test', 0.9126450147764836), ('className', 0.0837128585772114), ('rc', 0.009356709482938921), ('name', 0.0055916482197196655), ('instr', 0.00538822614396171), ('r', 0.004915311398878124), ('c', 0.004676612965613513), ('fileNameMapper', 0.004584472157565714), ('i', 0.0034655815214630612), ('p', 0.003356120677129093)]
        this.preConfigure(testClasses);
        this.tests.add(testClasses);
    }

    /**
     * Adds a {@link ListenerDefinition listener} which will be enrolled for listening to test
     * execution events
     *
     * @param listener The listener
     */
    public void addConfiguredListener(final ListenerDefinition listener) {
// listener             0	: [('listener', 0.8278287651248926), ('listenerDef', 0.07618299148217145), ('applicableListener', 0.045904363034016675), ('rc', 0.0093567083914954), ('formatterDefinition', 0.008972406612116388), ('name', 0.005591639748674456), ('instr', 0.005388225539820228), ('r', 0.004915307971054906), ('c', 0.004676609635261387), ('fileNameMapper', 0.004584471929561368)]
        this.listeners.add(listener);
    }

    public void setHaltonfailure(final boolean haltonfailure) {
// haltonfailure        2	: [('value', 0.7549564462017009), ('haltOnFailure', 0.5687634597093867), ('haltonfailure', 0.3562536393865789), ('b', 0.04230663788056469), ('verbose', 0.022472735128615615), ('reader', 0.01264701845829783), ('include', 0.0035739050057789043), ('v', 0.003539178755860335), ('append', 0.0034729083109361495), ('f', 0.0027345659474907196)]
        this.haltOnFailure = haltonfailure;
    }

    public void setFailureProperty(final String failureProperty) {
// failureProperty      0	: [('failureProperty', 0.7250116625883194), ('propertyName', 0.3766244609773381), ('name', 0.023454303270685147), ('message', 0.007012675981913621), ('value', 0.006175172463721646), ('output', 0.004390622031705773), ('s', 0.004327295443260173), ('line', 0.0034359398175628788), ('pattern', 0.003190894091960615), ('text', 0.002814564037425361)]
        this.failureProperty = failureProperty;
    }

    public void setPrintSummary(final boolean printSummary) {
// printSummary         0	: [('printSummary', 0.906253777626661), ('b', 0.08461327576112938), ('verbose', 0.04494547025723123), ('reader', 0.03139701845829783), ('value', 0.009912892403401617), ('include', 0.007147810011557809), ('v', 0.00707835751172067), ('append', 0.006945816621872299), ('f', 0.005469131894981439), ('failOnError', 0.005405472337172754)]
        this.printSummary = printSummary;
    }

    /**
     * Tags to include. Will trim each tag.
     *
     * @param includes comma separated list of tags to include while running the tests.
     * @since Ant 1.10.7
     */
    public void setIncludeTags(final String includes) {
// includes             No	: [('list', 0.3253801756654647), ('string', 0.1288201026539124), ('excludes', 0.11691490652367423), ('name', 0.09381721308274059), ('path', 0.03736672212815948), ('message', 0.028050703927654484), ('classpath', 0.025424427316767924), ('src', 0.025418054123129467), ('value', 0.024700689854886583), ('output', 0.01756248812682309)]
        final StringTokenizer tokens = new StringTokenizer(includes, ",");
// tokens               2	: [('filter', 0.5038325950672982), ('tok', 0.454949219125035), ('tokens', 0.2152890272350495), ('tokenizer', 0.1540726102236277), ('st', 0.0977543009815584), ('tk', 0.05305783112061246), ('ch', 0.019357927273994952), ('rc', 0.018713417054513834), ('line', 0.01572206200244841), ('t', 0.01236893788298136)]
        while (tokens.hasMoreTokens()) {
            includeTags.add(tokens.nextToken().trim());
        }
    }

    /**
     * Tags to exclude. Will trim each tag.
     *
     * @param excludes comma separated list of tags to exclude while running the tests.
     * @since Ant 1.10.7
     */
    public void setExcludeTags(final String excludes) {
// excludes             No	: [('list', 0.3253801756654647), ('string', 0.1288201026539124), ('includes', 0.11691251480732709), ('name', 0.09381721308274059), ('path', 0.03736672212815948), ('message', 0.028050703927654484), ('classpath', 0.025424427316767924), ('src', 0.025418054123129467), ('value', 0.024700689854886583), ('output', 0.01756248812682309)]
        final StringTokenizer tokens = new StringTokenizer(excludes, ",");
// tokens               2	: [('filter', 0.5038325950672982), ('tok', 0.454949219125035), ('tokens', 0.2152890272350495), ('tokenizer', 0.1540726102236277), ('st', 0.0977543009815584), ('tk', 0.05305783112061246), ('ch', 0.019357927273994952), ('rc', 0.018713417054513834), ('line', 0.01572206200244841), ('t', 0.01236893788298136)]
        while (tokens.hasMoreTokens()) {
            excludeTags.add(tokens.nextToken().trim());
        }
    }

    private void preConfigure(final TestDefinition test) {
// test                 0	: [('test', 0.7406772362306704), ('fe', 0.12657538352827094), ('ownerTest', 0.10316366142562523), ('message', 0.10272403542582827), ('log', 0.054469847091795275), ('fs', 0.04961337981863145), ('msg', 0.030557070976444296), ('name', 0.029743438861450797), ('c', 0.028663062839721355), ('path', 0.027563994052992112)]
        if (test.getHaltOnFailure() == null) {
            test.setHaltOnFailure(this.haltOnFailure);
        }
        if (test.getFailureProperty() == null) {
            test.setFailureProperty(this.failureProperty);
        }
    }

    private void launchViaReflection(final InVMLaunch launchDefinition) {
// launchDefinition     No	: [('actualParams', 0.2576703335407637), ('ancestor', 0.25767026441980534), ('classpath', 0.16725653200205967), ('className', 0.08126271643355455), ('classname', 0.030802902013228637), ('attr', 0.02302178345831834), ('name', 0.016277465910807143), ('args', 0.015530581933036093), ('task', 0.015485173874205315), ('ostream', 0.01534238804500559)]
        final ClassLoader cl = launchDefinition.getClassLoader();
// cl                   3	: [('myLoader', 0.58921553807369), ('session', 0.3550852672032786), ('inputEncoding', 0.16295467218931278), ('cl', 0.09676535958564653), ('previousClassLoader', 0.08838165176535111), ('loader', 0.0815706191998588), ('classLoader', 0.04078327162919676), ('libPathURLs', 0.040708079888328565), ('preservedDirectories', 0.040707526913323036), ('parent', 0.036148247159683726)]
        // instantiate a new TestExecutionContext instance using the launch definition's classloader
        final Class<?> testExecutionCtxClass;
// testExecutionCtxClass No	: [('s', 0.18269677789924815), ('runtimeVersionClass', 0.10991080385875275), ('c', 0.10093054082740398), ('cause', 0.09075548091413044), ('format', 0.09069610947584975), ('fClazz', 0.09068003462798352), ('taskClass', 0.08562738937006324), ('clazz', 0.06394930085627529), ('type', 0.050805717935603484), ('klass', 0.04995669447632909)]
        final Object testExecutionCtx;
// testExecutionCtx     2	: [('forkedExecution', 0.6250127152682926), ('def', 0.1676951004725229), ('testExecutionCtx', 0.16669209366689478), ('realObject', 0.14847536744333192), ('attribute', 0.14833402978647825), ('launcherSupport', 0.14832227922355717), ('parent', 0.09112643510167792), ('nestedElement', 0.0799326421545231), ('value', 0.0316516722285391), ('o', 0.030355950364546098)]
        try {
            testExecutionCtxClass = Class.forName(TEST_EXECUTION_CONTEXT_CLASS_NAME, false, cl);
            final Class<?> klass = Class.forName(IN_VM_TEST_EXECUTION_CONTEXT_CLASS_NAME, false, cl);
// klass                7	: [('c', 0.10093054041966078), ('taskClass', 0.08562738934942382), ('clazz', 0.06394930079986087), ('type', 0.05080571784479004), ('testClass', 0.04986930543978648), ('arg', 0.03333423856666146), ('reflectedArg', 0.033310500762369945), ('klass', 0.03331001692208258), ('theClass', 0.028531138884826817), ('element', 0.021379153705114694)]
            testExecutionCtx = klass.getConstructor(JUnitLauncherTask.class).newInstance(this);
        } catch (Exception e) {
// e                    0	: [('e', 0.563983241398157), ('ex', 0.2334127431539531), ('ftp', 0.07383301504149661), ('t', 0.06344869951599263), ('exc', 0.055728524758199054), ('name', 0.03622929009843586), ('file', 0.029526853397537186), ('r', 0.024818312334290275), ('dir', 0.021122244007121146), ('classname', 0.020735957831455546)]
            throw new BuildException("Failed to create a test execution context for in-vm tests", e);
        }
        // instantiate a new LauncherSupport instance using the launch definition's ClassLoader
        try {
            final Class<?> klass = Class.forName(LAUNCHER_SUPPORT_CLASS_NAME, false, cl);
// klass                7	: [('c', 0.10093054082740398), ('taskClass', 0.08562738937006324), ('clazz', 0.06394930085627529), ('type', 0.050805717935603484), ('testClass', 0.049869305472350904), ('arg', 0.033334238687746064), ('reflectedArg', 0.03331050077383629), ('klass', 0.033309982366648595), ('theClass', 0.028531138907759507), ('element', 0.021379153835372363)]
            final Object launcherSupport = klass.getConstructor(LaunchDefinition.class, testExecutionCtxClass).newInstance(launchDefinition, testExecutionCtx);
// launcherSupport      6	: [('parent', 0.6638255324653984), ('nestedElement', 0.539966321048825), ('realObject', 0.0742376837124929), ('attribute', 0.07416701480792953), ('obj', 0.06066199170921284), ('testExecutionCtx', 0.03715805585625787), ('launcherSupport', 0.03708164998828721), ('value', 0.01582583492176974), ('o', 0.015177974747469274), ('t', 0.012410619109838077)]
            klass.getMethod("launch").invoke(launcherSupport);
        } catch (Exception e) {
// e                    0	: [('e', 0.563983241398157), ('ex', 0.2334127431539531), ('ftp', 0.07383301504149661), ('t', 0.06344869951599263), ('exc', 0.055728524758199054), ('name', 0.03622929009843586), ('file', 0.029526853397537186), ('r', 0.024818312334290275), ('dir', 0.021122244007121146), ('classname', 0.020735957831455546)]
            throw new BuildException("Failed to launch in-vm tests", e);
        }
    }

    private java.nio.file.Path dumpProjectProperties() throws IOException {
        final java.nio.file.Path propsPath = Files.createTempFile(null, "properties");
// propsPath            No	: [('launchDefXmlPath', 0.23449998080972545), ('xmlFilePath', 0.2344998771269119), ('projectPropsPath', 0.23449984256597406), ('resultOutputFile', 0.23449984256597406), ('result', 0.11870229964707865), ('dest', 0.09961458171049614), ('file', 0.0976175802470596), ('f', 0.04873890750350212), ('out', 0.04314083790099354), ('tmp', 0.04304049885281665)]
        propsPath.toFile().deleteOnExit();
        final Hashtable<String, Object> props = this.getProject().getProperties();
// props                4	: [('map', 0.1749731742951648), ('entry', 0.1406459981711325), ('task', 0.12530264062829294), ('m', 0.12529033726098573), ('props', 0.12516742302790757), ('ps', 0.12511921705258477), ('set', 0.12511116397541178), ('inheritedRestrictedDef', 0.12500653961606448), ('properties', 0.04642315713869038), ('result', 0.04632810524861795)]
        final Properties projProperties = new Properties();
// projProperties       No	: [('properties', 0.7502530875414627), ('props', 0.4383331012408514), ('userList', 0.24305885460217774), ('p', 0.1200561303448768), ('result', 0.04088732862308986), ('info', 0.03980430026672919), ('buildProps', 0.03976920555422895), ('testProps', 0.03975975425428447), ('h1', 0.03975841350233547), ('tmpProps', 0.039730681438875655)]
        projProperties.putAll(props);
        try (final OutputStream os = Files.newOutputStream(propsPath)) {
// os                   0	: [('os', 0.8095238853582941), ('fos', 0.1598260169763159), ('out', 0.10806492230478967), ('data', 0.09812719165120595), ('parent', 0.09812270429420102), ('bos', 0.09777113313927178), ('output', 0.04943454857635409), ('baos', 0.04889757028732295), ('outstream', 0.04888523154536149), ('ostream', 0.01328640012343171)]
            // TODO: Is it always UTF-8?
            projProperties.store(os, StandardCharsets.UTF_8.name());
        }
        return propsPath;
    }

    private void forkTest(final TestDefinition test) {
// test                 0	: [('test', 0.8958603661812381), ('name', 0.4561536830349221), ('writer', 0.2526120738178503), ('project', 0.1802485574327323), ('conn', 0.17831398858598135), ('inShadow', 0.17815834064686556), ('channel', 0.10579390466910846), ('ownerTest', 0.10316366144764295), ('propertyName', 0.09978940211159415), ('prop', 0.09569183897090192)]
        // create launch command
        final ForkDefinition forkDefinition = test.getForkDefinition();
// forkDefinition       0	: [('forkDefinition', 0.8750050119611906), ('i', 0.007221303103901564), ('name', 0.0061534916673352995), ('b', 0.005880634358213717), ('value', 0.005420040438239479), ('file', 0.004260708764856733), ('path', 0.003640615907470202), ('line', 0.0036400629398033275), ('p', 0.003622090127980325), ('project', 0.0034965355957683486)]
        final CommandlineJava commandlineJava = forkDefinition.generateCommandLine(this);
// commandlineJava      0	: [('commandlineJava', 0.5775307296836554), ('analyzer', 0.2709038256050346), ('xmlFilePath', 0.2709031689210702), ('cmd', 0.1530056598083389), ('cmdLine', 0.041825844918754955), ('ea', 0.020907509117818808), ('commandline', 0.01830433091834243), ('buf', 0.015889873228345525), ('project', 0.01538739671938548), ('command', 0.015269237106848485)]
        if (this.classPath != null) {
            commandlineJava.createClasspath(getProject()).createPath().append(this.classPath);
        }
        final java.nio.file.Path projectPropsPath;
// projectPropsPath     No	: [('propsPath', 0.2345002954872848), ('launchDefXmlPath', 0.23450022636632642), ('xmlFilePath', 0.23450012268488887), ('resultOutputFile', 0.2345000881244097), ('arg', 0.10010764842917345), ('value', 0.050978136044613244), ('name', 0.05037241443356241), ('argument', 0.04975249764467398), ('sourceJar', 0.03706636712022237), ('args', 0.026017496451450395)]
        try {
            projectPropsPath = dumpProjectProperties();
        } catch (IOException e) {
// e                    0	: [('e', 0.405729469964719), ('ioe', 0.31764641399466065), ('ex', 0.12990932333622068), ('ioex', 0.020484517696587202), ('exc', 0.01379596141978942), ('x', 0.010950033749040225), ('eyeOhEx', 0.009756907569454491), ('l', 0.008115925703945242), ('offset', 0.007953177657533596), ('name', 0.007293822042786647)]
            throw new BuildException("Could not create the necessary properties file while forking a process" + " for a test", e);
        }
        // --properties <path-to-properties-file>
        commandlineJava.createArgument().setValue(Constants.ARG_PROPERTIES);
        commandlineJava.createArgument().setValue(projectPropsPath.toAbsolutePath().toString());
        final java.nio.file.Path launchDefXmlPath = newLaunchDefinitionXml();
// launchDefXmlPath     No	: [('propsPath', 0.23450004992701445), ('xmlFilePath', 0.23449987712461856), ('projectPropsPath', 0.2344998425641394), ('resultOutputFile', 0.2344998425641394), ('arg', 0.1001076495959945), ('dest', 0.09961458231552733), ('file', 0.0976175879422492), ('value', 0.050978139389500264), ('name', 0.050372421934554885), ('argument', 0.04975249785581303)]
        try (final OutputStream os = Files.newOutputStream(launchDefXmlPath)) {
// os                   0	: [('os', 0.8095238853582941), ('out', 0.10806492230478967), ('ostream', 0.01328640012343171), ('outShadow', 0.009130836127972242), ('name', 0.007549024328076936), ('i', 0.005059481059085919), ('fos', 0.004483515716344337), ('p', 0.004466123667425858), ('project', 0.004380967776850072), ('file', 0.004213233342026988)]
            final XMLStreamWriter writer = XMLOutputFactory.newFactory().createXMLStreamWriter(os, "UTF-8");
// writer               0	: [('writer', 0.8958737351552134), ('testMailServer', 0.19974370410839626), ('msg', 0.09621164402377624), ('out', 0.08585687857781409), ('result', 0.07995167774931375), ('cmd', 0.07129386166286025), ('s', 0.05714412745516865), ('bft', 0.05707675553976566), ('message', 0.05288933949549912), ('output', 0.04832548919543335)]
            try {
                writer.writeStartDocument();
                writer.writeStartElement(LD_XML_ELM_LAUNCH_DEF);
                if (this.printSummary) {
                    writer.writeAttribute(LD_XML_ATTR_PRINT_SUMMARY, "true");
                }
                if (this.haltOnFailure) {
                    writer.writeAttribute(LD_XML_ATTR_HALT_ON_FAILURE, "true");
                }
                if (this.includeTags.size() > 0) {
                    writer.writeAttribute(LD_XML_ATTR_INCLUDE_TAGS, commaSeparatedListElements(includeTags));
                }
                if (this.excludeTags.size() > 0) {
                    writer.writeAttribute(LD_XML_ATTR_EXCLUDE_TAGS, commaSeparatedListElements(excludeTags));
                }
                // task level listeners
                for (final ListenerDefinition listenerDef : this.listeners) {
// listenerDef          0	: [('listenerDef', 0.4397959989089696), ('applicableListener', 0.2584753927429558), ('listener', 0.20156077858908317), ('containers', 0.09539630884016773), ('file', 0.07133955280816928), ('name', 0.04935484627902113), ('value', 0.04919362022977913), ('sb', 0.04808205831097626), ('writer', 0.04807648408148272), ('n', 0.04777423719737719)]
                    if (!listenerDef.shouldUse(getProject())) {
                        continue;
                    }
                    // construct the listener definition argument
                    listenerDef.toForkedRepresentation(writer);
                }
                // test definition as XML
                test.toForkedRepresentation(this, writer);
                writer.writeEndElement();
                writer.writeEndDocument();
            } finally {
                writer.close();
            }
        } catch (Exception e) {
// e                    0	: [('e', 0.6962462383400531), ('ex', 0.1466390428481427), ('ftp', 0.07383301504149661), ('err', 0.04809587268343748), ('name', 0.03622929009843586), ('file', 0.029526853397537186), ('t', 0.026751451809570618), ('r', 0.024818312334290275), ('exc', 0.023618433015079782), ('dir', 0.021122244007121146)]
            throw new BuildException("Failed to construct command line for test", e);
        }
        // --launch-definition <xml-file-path>
        commandlineJava.createArgument().setValue(Constants.ARG_LAUNCH_DEFINITION);
        commandlineJava.createArgument().setValue(launchDefXmlPath.toAbsolutePath().toString());
        // launch the process and wait for process to complete
        final int exitCode = executeForkedTest(forkDefinition, commandlineJava);
// exitCode             No	: [('size', 0.22252596557460363), ('count', 0.18332938760064654), ('c', 0.17353352694389462), ('ret', 0.1723026526382591), ('type', 0.08344794546248527), ('priority', 0.0606265931664922), ('key', 0.05471325242423411), ('s', 0.05314019070208265), ('attrs', 0.05278141886496513), ('state', 0.039541683930326414)]
        switch(exitCode) {
            case Constants.FORK_EXIT_CODE_SUCCESS:
                {
                    // success
                    break;
                }
            case Constants.FORK_EXIT_CODE_EXCEPTION:
                {
                    // process failed with some exception
                    throw new BuildException("Forked test(s) failed with an exception");
                }
            case Constants.FORK_EXIT_CODE_TESTS_FAILED:
                {
                    // test has failure(s)
                    try {
                        if (test.getFailureProperty() != null) {
                            // if there are test failures and the test is configured to set a property in case
                            // of failure, then set the property to true
                            this.getProject().setNewProperty(test.getFailureProperty(), "true");
                        }
                    } finally {
                        if (test.isHaltOnFailure()) {
                            // if the test is configured to halt on test failures, throw a build error
                            final String errorMessage;
// errorMessage         0	: [('errorMessage', 0.8750112340173516), ('msg', 0.18522147523335047), ('message', 0.12018895024391751), ('e', 0.11797207644810828), ('exc', 0.09744737387533912), ('name', 0.07673432098223043), ('t', 0.07623862169822392), ('prefix', 0.07310610073760486), ('index', 0.07254731384959544), ('ddPrefix', 0.07249859210496643)]
                            if (test instanceof NamedTest) {
                                errorMessage = "Test " + ((NamedTest) test).getName() + " has failure(s)";
                            } else {
                                errorMessage = "Some test(s) have failure(s)";
                            }
                            throw new BuildException(errorMessage);
                        }
                    }
                    break;
                }
            case Constants.FORK_EXIT_CODE_TIMED_OUT:
                {
                    throw new BuildException(new TimeoutException("Forked test(s) timed out"));
                }
        }
    }

    private static String commaSeparatedListElements(final List<String> stringList) {
// stringList           No	: [('lines', 0.09875399830348768), ('files', 0.07569741424482357), ('packagesToDoc', 0.06578979836294466), ('extraArgs', 0.05261631431670501), ('v', 0.045932705352673235), ('cmdLine', 0.032889891021141315), ('libPaths', 0.032887575499766085), ('dirs', 0.03101962461842451), ('result', 0.022183034298245984), ('list', 0.02146134003100844)]
        return stringList.stream().map(Object::toString).collect(Collectors.joining(", "));
    }

    private int executeForkedTest(final ForkDefinition forkDefinition, final CommandlineJava commandlineJava) {
// forkDefinition       0	: [('forkDefinition', 0.7500091945369307), ('env', 0.33350935668599807), ('watchdog', 0.2500589928730622), ('exe', 0.1669821165811126), ('filename', 0.08733815573208473), ('tmpDir', 0.087069581203737), ('name', 0.060197756855374145), ('link', 0.059180456069346354), ('parent', 0.05144409357197071), ('container', 0.04490510588333532)]
// commandlineJava      4	: [('msg', 0.3800146861815708), ('cmd', 0.3744504628999415), ('commandline', 0.34698919150826724), ('message', 0.1989988987003418), ('commandlineJava', 0.15506256521457712), ('cloned', 0.0901636058229431), ('cmdLine', 0.0836516887513457), ('e', 0.04967514721561137), ('toExecute', 0.048751652945426785), ('command', 0.04096988653829713)]
        final LogOutputStream outStream = new LogOutputStream(this, Project.MSG_INFO);
// outStream            5	: [('out', 0.8114386451462245), ('errStream', 0.515633365008373), ('outputstream', 0.04086840546879936), ('dummyOut', 0.025242608819594545), ('fos', 0.02044558752921489), ('outStream', 0.02043668261041448), ('logstr', 0.01563391796870143), ('outLog', 0.015633641488537225), ('errLog', 0.015633365008373017), ('name', 0.0004718065195123305)]
        final LogOutputStream errStream = new LogOutputStream(this, Project.MSG_WARN);
// errStream            2	: [('blockSize', 0.6073033842222141), ('outStream', 0.5156383416513287), ('errStream', 0.03572644746778427), ('logstr', 0.01563391796870143), ('outLog', 0.015633641488537225), ('errLog', 0.015633365008373017), ('l', 0.0020289814259863105), ('offset', 0.001988294414383399), ('name', 0.0018234555106966617), ('e', 0.0018138840215960721)]
        final ExecuteWatchdog watchdog = forkDefinition.getTimeout() > 0 ? new ExecuteWatchdog(forkDefinition.getTimeout()) : null;
// watchdog             0	: [('watchdog', 0.9204589177340404), ('outputDir', 0.5503350632247364), ('destOffs', 0.05898564458477142), ('numToSkip', 0.05898519353944116), ('p', 0.05060289072624277), ('count', 0.050356564136180504), ('o1', 0.05032927752300987), ('e', 0.02161360154377327), ('f', 0.01891676482999471), ('name', 0.018035617509326193)]
        final Execute execute = new Execute(new PumpStreamHandler(outStream, errStream), watchdog);
// execute              0	: [('execute', 0.833368353120422), ('process', 0.43625734961386176), ('exe', 0.38371480864769536), ('jmod', 0.23339325557111026), ('cmd', 0.17013194717866298), ('jlink', 0.11669159930648242), ('ese', 0.11668122424967592), ('builder', 0.06686064165864505), ('to', 0.04665150899438427), ('commandLine', 0.03383959449953055)]
        execute.setCommandline(commandlineJava.getCommandline());
        execute.setAntRun(getProject());
        if (forkDefinition.getDir() != null) {
            execute.setWorkingDirectory(Paths.get(forkDefinition.getDir()).toFile());
        }
        final Environment env = forkDefinition.getEnv();
// env                  1	: [('environment', 0.6718874879447726), ('env', 0.5470946847744922), ('from', 0.05735051076956362), ('newEnv', 0.057296460778871225), ('file', 0.024620788289783996), ('other', 0.01919664586171165), ('t', 0.017269661137129667), ('c', 0.015684615568042584), ('ch', 0.014761154661126775), ('params', 0.014696274509048184)]
        if (env != null && env.getVariables() != null) {
            execute.setEnvironment(env.getVariables());
        }
        log(commandlineJava.describeCommand(), Project.MSG_VERBOSE);
        int exitCode;
// exitCode             No	: [('retCode', 0.3775572488403269), ('err', 0.375623536739365), ('result', 0.01776229294825781), ('testMailClient', 0.013493706440386976), ('files', 0.011261865362523832), ('log', 0.010486930414246155), ('r', 0.010162856272221093), ('reader', 0.009745285322742362), ('out', 0.00945835002056099), ('index', 0.008959582516163285)]
        try {
            exitCode = execute.execute();
        } catch (IOException e) {
// e                    0	: [('e', 0.405729469964719), ('ioe', 0.31764641399466065), ('ex', 0.12990932333622068), ('ftp', 0.07383301504149661), ('name', 0.03622929009843586), ('file', 0.029526853397537186), ('r', 0.024818312334290275), ('dir', 0.021122244007121146), ('classname', 0.020735957831455546), ('ioex', 0.020484517696587202)]
            throw new BuildException("Process fork failed", e, getLocation());
        }
        return (watchdog != null && watchdog.killedProcess()) ? Constants.FORK_EXIT_CODE_TIMED_OUT : exitCode;
    }

    private java.nio.file.Path newLaunchDefinitionXml() {
        final java.nio.file.Path xmlFilePath;
// xmlFilePath          No	: [('localFile', 0.506936599386705), ('commandlineJava', 0.3719336538274602), ('propsPath', 0.23450029549187143), ('launchDefXmlPath', 0.23450022636999573), ('projectPropsPath', 0.23450008812624437), ('resultOutputFile', 0.23450008812624437), ('analyzer', 0.1859679027031349), ('result', 0.024507089726991737), ('ea', 0.019304919128507004), ('cmdLine', 0.019302964083360533)]
        try {
            xmlFilePath = Files.createTempFile(null, ".xml");
        } catch (IOException e) {
// e                    0	: [('e', 0.405729469964719), ('ioe', 0.31764641399466065), ('ex', 0.12990932333622068), ('ftp', 0.07383301504149661), ('name', 0.03622929009843586), ('file', 0.029526853397537186), ('r', 0.024818312334290275), ('dir', 0.021122244007121146), ('classname', 0.020735957831455546), ('ioex', 0.020484517696587202)]
            throw new BuildException("Failed to construct command line for test", e);
        }
        xmlFilePath.toFile().deleteOnExit();
        return xmlFilePath;
    }

    private final class InVMLaunch implements LaunchDefinition {

        private final List<TestDefinition> inVMTests;

        private final ClassLoader executionCL;

        private InVMLaunch(final List<TestDefinition> inVMTests) {
// inVMTests            No	: [('tests', 0.6250644781044931), ('testDefinitions', 0.3125165581454529), ('i', 0.007220869255079404), ('name', 0.00615307471702672), ('b', 0.005880193753638788), ('value', 0.005419652314355413), ('file', 0.004260412397551352), ('path', 0.0036403468622049576), ('line', 0.003639793901876543), ('p', 0.003621845876403473)]
            this.inVMTests = inVMTests;
            this.executionCL = createInVMExecutionClassLoader();
        }

        @Override
        public List<TestDefinition> getTests() {
            return this.inVMTests;
        }

        @Override
        public List<ListenerDefinition> getListeners() {
            return listeners;
        }

        @Override
        public boolean isPrintSummary() {
            return printSummary;
        }

        @Override
        public boolean isHaltOnFailure() {
            return haltOnFailure;
        }

        @Override
        public List<String> getIncludeTags() {
            return includeTags;
        }

        @Override
        public List<String> getExcludeTags() {
            return excludeTags;
        }

        @Override
        public ClassLoader getClassLoader() {
            return this.executionCL;
        }

        private ClassLoader createInVMExecutionClassLoader() {
            final Path taskConfiguredClassPath = JUnitLauncherTask.this.classPath;
// taskConfiguredClassPath No	: [('p', 0.17137904405070103), ('path', 0.11830359356041406), ('classpath', 0.10956343309480454), ('parent', 0.09737536617823207), ('cmd', 0.09052072394821399), ('typeName', 0.09017505455560842), ('tName', 0.09017432877591366), ('mp', 0.08493929023055957), ('ump', 0.08193673017865896), ('msp', 0.08193673017865896)]
            if (taskConfiguredClassPath == null) {
                // no specific classpath configured for the task, so use the classloader
                // of this task
                return JUnitLauncherTask.class.getClassLoader();
            }
            // there's a classpath configured for the task.
            // we first check if the Ant runtime classpath has JUnit platform classes.
            // - if it does, then we use the Ant runtime classpath plus the task's configured classpath
            // with the traditional parent first loading.
            // - else (i.e. Ant runtime classpath doesn't have JUnit platform classes), then we
            // expect/assume the task's configured classpath to have the JUnit platform classes and we
            // then create a "overriding" classloader which prefers certain resources (specifically the classes
            // from org.apache.tools.ant.taskdefs.optional.junitlauncher package), from the task's
            // classpath, even if the Ant's runtime classpath has those resources.
            if (JUnitLauncherClassPathUtil.hasJUnitPlatformResources(JUnitLauncherTask.class.getClassLoader())) {
                return new AntClassLoader(JUnitLauncherTask.class.getClassLoader(), getProject(), taskConfiguredClassPath, true);
            }
            final Path cp = new Path(getProject());
// cp                   No	: [('resource', 0.2823089537511956), ('mp', 0.18899751270983467), ('projectResourceName', 0.18802318849062508), ('ump', 0.1859949526579341), ('bp', 0.1855048855952209), ('lowerCasePath', 0.1850147839715699), ('className', 0.09406176917795342), ('classResource', 0.09401421555301874), ('testResourceName', 0.0940120133768983), ('linkPath', 0.060505196643661524)]
            cp.add(taskConfiguredClassPath);
            // add the Ant runtime resources to this path
            JUnitLauncherClassPathUtil.addLauncherSupportResourceLocation(cp, JUnitLauncherTask.class.getClassLoader());
            return new TaskConfiguredPathClassLoader(JUnitLauncherTask.class.getClassLoader(), cp, getProject());
        }
    }

    /**
     * A {@link ClassLoader}, very similar to the {@link org.apache.tools.ant.util.SplitClassLoader},
     * which uses the {@link #TaskConfiguredPathClassLoader(ClassLoader, Path, Project) configured Path}
     * to load a class, if the class belongs to the {@code org.apache.tools.ant.taskdefs.optional.junitlauncher}
     * package.
     * <p>
     * While looking for classes belonging to the {@code org.apache.tools.ant.taskdefs.optional.junitlauncher}
     * package, this classloader completely ignores Ant runtime classpath, even if that classpath has
     * those classes. This allows the users of this classloader to use a custom location and thus more control over
     * where these classes reside, when running the {@code junitlauncher} task
     */
    private final class TaskConfiguredPathClassLoader extends AntClassLoader {

        /**
         * @param parent  ClassLoader
         * @param path    Path
         * @param project Project
         */
        private TaskConfiguredPathClassLoader(ClassLoader parent, Path path, Project project) {
// parent               3	: [('project', 0.5107802727992002), ('loader', 0.17974625376964534), ('classLoader', 0.14063488514658493), ('parent', 0.09999647233978244), ('in', 0.07198777705208179), ('c', 0.0540412052217393), ('al', 0.05380840892347692), ('cl', 0.052449749202796284), ('name', 0.038226449537313245), ('l', 0.02955519793311894)]
// path                 0	: [('path', 0.9189520864341408), ('classpath', 0.30731998698620305), ('actual', 0.014261805963806468), ('force', 0.01423813202786481), ('p', 0.005933074837109871), ('cmd', 0.005615431344276442), ('parent', 0.004596209529089669), ('newcmd', 0.0028022911368588248), ('target', 0.0025274433741001917), ('trackerFile', 0.0021692718516213294)]
// project              0	: [('project', 0.905748125489796), ('p', 0.04700228821244153), ('child', 0.02375764317782123), ('name', 0.013087890187838435), ('prefix', 0.008560799501556662), ('suffix', 0.008444776878471876), ('relPath', 0.008430076126896497), ('realObject', 0.008426733201103681), ('value', 0.0051003473842836985), ('attribute', 0.004229264085550789)]
            super(parent, project, path, true);
        }

        // forceLoadClass is not convenient here since it would not
        // properly deal with inner classes of these classes.
        @Override
        protected synchronized Class<?> loadClass(String classname, boolean resolve) throws ClassNotFoundException {
// classname            0	: [('classname', 0.9375655949015654), ('className', 0.7000544784005333), ('dir', 0.20011829185970478), ('index', 0.1463497560998721), ('pos', 0.03948721329879896), ('i', 0.03128659246930134), ('name', 0.031210388834449636), ('percIndex', 0.03076420165411045), ('s', 0.026463573937789867), ('filename', 0.02321598767390472)]
// resolve              0	: [('resolve', 0.7654816502848895), ('theClass', 0.22838526499228629), ('optional', 0.18849989116183974), ('container', 0.0915648754092847), ('genclass', 0.09135354927852231), ('c', 0.052121121619970574), ('loader', 0.01552569649706798), ('classData', 0.01548986243912056), ('f', 0.006694394168132867), ('overwrite', 0.006035317584526185)]
            Class<?> theClass = findLoadedClass(classname);
// theClass             0	: [('theClass', 0.9375059639069246), ('i', 0.2632814722749651), ('resExports', 0.26266048713562856), ('c', 0.1939807351008916), ('clazz', 0.14425818133440782), ('message', 0.1319680542473707), ('container', 0.0915648755179914), ('resolve', 0.0913689935428334), ('genclass', 0.09135354928953059), ('ret', 0.06745094723631784)]
            if (theClass != null) {
                return theClass;
            }
            final String packageName = classname.contains(".") ? classname.substring(0, classname.lastIndexOf('.')) : "";
// packageName          No	: [('project', 0.14522924284848654), ('name', 0.07560241988510448), ('file', 0.07518545948004299), ('current', 0.07223208469402397), ('missingBehaviour', 0.07159781765802846), ('outDir', 0.029136927878920072), ('includeEngs', 0.029136927878920072), ('excludeEngs', 0.029136927878920072), ('propName', 0.02422337726849032), ('tearDown', 0.024003713749801286)]
            if (packageName.equals("org.apache.tools.ant.taskdefs.optional.junitlauncher")) {
                theClass = findClass(classname);
                if (resolve) {
                    resolveClass(theClass);
                }
                return theClass;
            }
            return super.loadClass(classname, resolve);
        }
    }
}
