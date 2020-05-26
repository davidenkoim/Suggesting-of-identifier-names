// Type of training: selfTestingIdentifier
// Path to file: C:\Users\Igor\IdeaProjects\SLP-Core-0.3\java-projects\ant-master\src\tests\junit\org\apache\tools\ant\taskdefs\optional\PropertyFileTest.java
// Number of identifiers: 19	Accuracy: 42.11%	MRR: 57.07%
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
package org.apache.tools.ant.taskdefs.optional;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Properties;
import org.apache.tools.ant.BuildFileRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 *   JUnit testcase that exercises the optional PropertyFile task in ant.
 *   (this is really more of a functional test so far.., but it's enough to let
 *    me start refactoring...)
 *
 * created    October 2, 2001
 */
public class PropertyFileTest {

    @Rule
    public BuildFileRule buildRule = new BuildFileRule();

    @Before
    public void setUp() throws Exception {
        buildRule.configureProject(projectFilePath);
        buildRule.executeTarget("setUp");
        initTestPropFile();
        initBuildPropFile();
        buildRule.configureProject(projectFilePath);
        buildRule.getProject().setProperty(valueDoesNotGetOverwrittenPropertyFileKey, valueDoesNotGetOverwrittenPropertyFile);
    }

    @Test
    public void testNonExistingFile() {
        PropertyFile pf = new PropertyFile();
// pf                   0	: [('pf', 0.7500709267400242), ('cache', 0.5008374208690913), ('f', 0.07324960490753783), ('myBuild', 0.0725129785476814), ('name', 0.07207643930522145), ('builder', 0.07201273407418982), ('file', 0.07185095094929941), ('cachefile', 0.07179495957605517), ('out', 0.028458874978075022), ('commandLine', 0.024576889968473927)]
        pf.setProject(buildRule.getProject());
        File file = new File("this-file-does-not-exist.properties");
// file                 6	: [('cache', 0.13427114765415252), ('javah', 0.11497835651941939), ('jmod', 0.08789608196411704), ('result', 0.08725577188232558), ('src', 0.07930099304956349), ('f', 0.07917664315348458), ('file', 0.07761795653096884), ('myBuild', 0.07251410212776359), ('pf', 0.07218063801522641), ('name', 0.07207689898666556)]
        pf.setFile(file);
        assertFalse("Properties file exists before test.", file.exists());
        pf.execute();
        assertTrue("Properties file does not exist after test.", file.exists());
        file.delete();
    }

    /**
     *  A unit test for JUnit- Exercises the propertyfile tasks ability to
     *  update properties that are already defined-
     */
    @Test
    public void testUpdatesExistingProperties() throws Exception {
        Properties beforeUpdate = getTestProperties();
// beforeUpdate         1	: [('zipFile', 0.5543988852092185), ('beforeUpdate', 0.5000413923439481), ('props', 0.34016301122359277), ('testProps', 0.29677471766437563), ('afterUpdate', 0.25014240967512863), ('acl', 0.054391785741282346), ('link3Rem', 0.03355211153672109), ('link1Rem', 0.03355211153672109), ('link2Rem', 0.03355211153672109), ('dirlinkRem', 0.033552076974407166)]
        assertEquals(FNAME, beforeUpdate.getProperty(FNAME_KEY));
        assertEquals(LNAME, beforeUpdate.getProperty(LNAME_KEY));
        assertEquals(EMAIL, beforeUpdate.getProperty(EMAIL_KEY));
        assertNull(beforeUpdate.getProperty(PHONE_KEY));
        assertNull(beforeUpdate.getProperty(AGE_KEY));
        assertNull(beforeUpdate.getProperty(DATE_KEY));
        // ask ant to update the properties...
        buildRule.executeTarget("update-existing-properties");
        Properties afterUpdate = getTestProperties();
// afterUpdate          1	: [('currentProperties', 0.38530907425800576), ('afterUpdate', 0.38530903969569186), ('props', 0.08507041863100018), ('p', 0.0115453363028297), ('info', 0.0107880744219124), ('links', 0.010548384247232544), ('p2', 0.010310525875190263), ('after', 0.01030917794494752), ('fileProperties', 0.010309143382633603), ('originalProps', 0.010309074258005771)]
        assertEquals(NEW_FNAME, afterUpdate.getProperty(FNAME_KEY));
        assertEquals(NEW_LNAME, afterUpdate.getProperty(LNAME_KEY));
        assertEquals(NEW_EMAIL, afterUpdate.getProperty(EMAIL_KEY));
        assertEquals(NEW_PHONE, afterUpdate.getProperty(PHONE_KEY));
        assertEquals(NEW_AGE, afterUpdate.getProperty(AGE_KEY));
        assertEquals(NEW_DATE, afterUpdate.getProperty(DATE_KEY));
    }

    @Test
    public void testDeleteProperties() throws Exception {
        Properties beforeUpdate = getTestProperties();
// beforeUpdate         1	: [('afterUpdate', 0.9062678022819171), ('beforeUpdate', 0.500059015148135), ('props', 0.3401630107997686), ('testProps', 0.29677471764419355), ('p', 0.006105519170742256), ('l', 0.00405873712835331), ('offset', 0.003977343706852235), ('name', 0.0036475241365330185), ('e', 0.003628405328764237), ('result', 0.002982832673002476)]
        assertEquals("Property '" + FNAME_KEY + "' should exist before deleting", FNAME, beforeUpdate.getProperty(FNAME_KEY));
        assertEquals("Property '" + LNAME_KEY + "' should exist before deleting", LNAME, beforeUpdate.getProperty(LNAME_KEY));
        buildRule.executeTarget("delete-properties");
        Properties afterUpdate = getTestProperties();
// afterUpdate          1	: [('beforeUpdate', 0.9375116085759437), ('afterUpdate', 0.38530917793210434), ('currentProperties', 0.3853090742506668), ('e', 0.08641005699106664), ('props', 0.08507041820717598), ('j', 0.042360173469479755), ('tmpdir', 0.036928676788127265), ('reader', 0.03185465799203443), ('collectorFile', 0.03160191724721579), ('result', 0.022239857507214224)]
        assertEquals("Property '" + LNAME_KEY + "' should exist after deleting", LNAME, afterUpdate.getProperty(LNAME_KEY));
        assertNull("Property '" + FNAME_KEY + "' should be deleted", afterUpdate.getProperty(FNAME_KEY));
    }

    @Test
    public void testExerciseDefaultAndIncrement() {
        buildRule.executeTarget("exercise");
        assertEquals("3", buildRule.getProject().getProperty("int.with.default"));
        assertEquals("1", buildRule.getProject().getProperty("int.without.default"));
        assertEquals("-->", buildRule.getProject().getProperty("string.with.default"));
        assertEquals(".", buildRule.getProject().getProperty("string.without.default"));
        assertEquals("2002/01/21 12:18", buildRule.getProject().getProperty("ethans.birth"));
        assertEquals("2003/01/21", buildRule.getProject().getProperty("first.birthday"));
        assertEquals("0124", buildRule.getProject().getProperty("olderThanAWeek"));
        assertEquals("37", buildRule.getProject().getProperty("existing.prop"));
        assertEquals("6", buildRule.getProject().getProperty("int.without.value"));
    }

    @Test
    public void testValueDoesNotGetOverwritten() {
        // this test shows that the bug report 21505 is fixed
        buildRule.executeTarget("bugDemo1");
        buildRule.executeTarget("bugDemo2");
        assertEquals("5", buildRule.getProject().getProperty("foo"));
    }

    @Test
    public void testDirect() throws Exception {
        PropertyFile pf = new PropertyFile();
// pf                   0	: [('pf', 0.7500708921859665), ('entry', 0.08421675741839484), ('jarFile', 0.08365459564751272), ('zf', 0.042062081688431716), ('exe', 0.04204815565294365), ('ti', 0.041695228831993184), ('ds', 0.03802008388669105), ('myPath', 0.03519794713943389), ('helper', 0.029304218916672498), ('ret', 0.02621005679406058)]
        pf.setProject(buildRule.getProject());
        pf.setFile(new File(buildRule.getOutputDir(), testPropsFilePath));
        long delta = 123L;
// delta                No	: [('ts1', 0.16876996572417025), ('lastModified', 0.14722594186768662), ('value', 0.1294382837661989), ('size', 0.09867074818819868), ('key', 0.07915366114348682), ('text', 0.07910509925926175), ('compressedSize', 0.07811128906813639), ('r', 0.07282615713843915), ('l', 0.04881039295153664), ('jarModifiedDate', 0.04867170821474282)]
        PropertyFile.Entry entry = pf.createEntry();
// entry                1	: [('offset', 0.5010729336129536), ('entry', 0.256297177474089), ('m', 0.15328549492096755), ('msg', 0.15303071439752874), ('i', 0.015199356538858282), ('ze', 0.012560863968753774), ('ent1', 0.012504320773623623), ('ent2', 0.012504320773623623), ('out', 0.007688667483248008), ('e', 0.006470539739842588)]
        entry.setKey("date");
        entry.setValue(String.valueOf(delta));
        PropertyFile.Entry.Type type = new PropertyFile.Entry.Type();
// type                 No	: [('value', 0.16740059576936442), ('glob', 0.16584157816158296), ('merge', 0.12438388815161043), ('mt', 0.11927783127595665), ('identity', 0.07271339318074678), ('mapperType', 0.04146325493883007), ('file', 0.03740533490774634), ('directory', 0.03642800168869452), ('flatten', 0.03636827854774097), ('i', 0.013107715384981492)]
        type.setValue("date");
        entry.setType(type);
        entry.setPattern("yyyy/MM/dd");
        PropertyFile.Entry.Operation operation = new PropertyFile.Entry.Operation();
// operation            0	: [('operation', 0.25001252636436705), ('value', 0.0562894846582533), ('i', 0.013107715384981492), ('out', 0.0071823647232828), ('compareFiles', 0.005144181314533428), ('result', 0.004818069560863105), ('cmd', 0.0047322974611110295), ('fs', 0.004649344933911125), ('ds', 0.004494408146900928), ('graphics', 0.004278821254732845)]
        operation.setValue("+");
        entry.setOperation(operation);
        pf.execute();
        Properties props = getTestProperties();
// props                0	: [('props', 0.6247623549184209), ('zid', 0.16672005113612148), ('charset', 0.0626900992467685), ('p', 0.06154533340627736), ('links', 0.060548384187604394), ('p2', 0.0603105257696943), ('fileProperties', 0.06030914336887327), ('encoding', 0.042566372878809176), ('enc', 0.0419579848711048), ('image', 0.04172460926694758)]
        LocalDate currentDate = LocalDate.now().plusDays(delta);
// currentDate          No	: [('e', 0.036557187526214265), ('name', 0.03008615356703097), ('f', 0.02896794125357442), ('ioe', 0.02123811628710536), ('buf', 0.01898824267363719), ('p', 0.013240535211300891), ('t', 0.011890856334126971), ('l', 0.011339057698316292), ('en', 0.009415248447459069), ('hash1', 0.00941469547245354)]
        assertEquals(String.format("%d/%02d/%02d", currentDate.getYear(), currentDate.getMonthValue(), currentDate.getDayOfMonth()), props.getProperty("date"));
    }

    private Properties getTestProperties() throws Exception {
        Properties testProps = new Properties();
// testProps            0	: [('testProps', 0.6250895448002167), ('beforeUpdate', 0.588441349750381), ('props', 0.3401630107997686), ('sw', 0.1705011119735451), ('crc', 0.17013464242226786), ('errorText', 0.170134089454601), ('result', 0.024507089256859144), ('name', 0.014510978302586843), ('p', 0.014002001178578081), ('newFilter', 0.008590637671070015)]
        FileInputStream propsFile = new FileInputStream(new File(buildRule.getOutputDir(), testPropsFilePath));
// propsFile            4	: [('fis', 0.6304181589139324), ('in', 0.2159799545111439), ('fis2', 0.15676163047603073), ('is', 0.13388990039438975), ('propsFile', 0.08502918106507062), ('bis', 0.04252132352387964), ('input', 0.021412226142276385), ('bais', 0.02126864601872733), ('tis', 0.0212592172156196), ('i', 0.015197628252237364)]
        testProps.load(propsFile);
        propsFile.close();
        return testProps;
    }

    private void initTestPropFile() throws IOException {
        Properties testProps = new Properties();
// testProps            0	: [('testProps', 0.6250681967288664), ('buildProps', 0.4400564869947366), ('original', 0.4400564524328814), ('props', 0.27837821851275585), ('p', 0.1200559396095009), ('result', 0.04088715281441118), ('info', 0.039804278604690164), ('h1', 0.039758405379034904), ('properties', 0.03974563729874192), ('projProperties', 0.03973184009355727)]
        testProps.put(FNAME_KEY, FNAME);
        testProps.put(LNAME_KEY, LNAME);
        testProps.put(EMAIL_KEY, EMAIL);
        testProps.put("existing.prop", "37");
        FileOutputStream fos = new FileOutputStream(new File(buildRule.getOutputDir(), testPropsFilePath));
// fos                  0	: [('fos', 0.788472494825148), ('os', 0.14670690631549757), ('data', 0.0981271862309576), ('parent', 0.09812269949597895), ('bos', 0.09777113283872105), ('output', 0.04943454001042355), ('baos', 0.048897569968426056), ('outstream', 0.04888523144136939), ('i', 0.015197628252237364), ('out', 0.007687363865116022)]
        testProps.store(fos, "defaults");
        fos.close();
    }

    private void initBuildPropFile() throws IOException {
        Properties buildProps = new Properties();
// buildProps           No	: [('testProps', 0.44261172100192886), ('original', 0.4400564524452667), ('props', 0.2783782492770896), ('p', 0.12005598586994648), ('result', 0.04088722795243999), ('info', 0.03980428482482512), ('h1', 0.039758411197869124), ('properties', 0.03974564008407397), ('projProperties', 0.03973184118566202), ('originalProps', 0.039730669431746524)]
        buildProps.put(testPropertyFileKey, testPropertyFile);
        buildProps.put(FNAME_KEY, NEW_FNAME);
        buildProps.put(LNAME_KEY, NEW_LNAME);
        buildProps.put(EMAIL_KEY, NEW_EMAIL);
        buildProps.put(PHONE_KEY, NEW_PHONE);
        buildProps.put(AGE_KEY, NEW_AGE);
        buildProps.put(DATE_KEY, NEW_DATE);
        FileOutputStream fos = new FileOutputStream(new File(buildRule.getOutputDir(), buildPropsFilePath));
// fos                  0	: [('fos', 0.788472494825148), ('os', 0.14670690631549757), ('data', 0.0981271862309576), ('parent', 0.09812269949597895), ('bos', 0.09777113283872105), ('exe', 0.08452248801757883), ('output', 0.04943454001042355), ('baos', 0.048897569968426056), ('outstream', 0.04888523144136939), ('mangler', 0.018955841255452126)]
        buildProps.store(fos, null);
        fos.close();
    }

    @SuppressWarnings("unused")
    private static final String projectFilePath = "src/etc/testcases/taskdefs/optional/propertyfile.xml", testPropertyFile = "propertyfile.test.properties", testPropertyFileKey = "test.propertyfile", testPropsFilePath = testPropertyFile, valueDoesNotGetOverwrittenPropertyFile = "overwrite.test.properties", valueDoesNotGetOverwrittenPropertyFileKey = "overwrite.test.propertyfile", valueDoesNotGetOverwrittenPropsFilePath = valueDoesNotGetOverwrittenPropertyFile, buildPropsFilePath = "propertyfile.build.properties", FNAME = "Bruce", NEW_FNAME = "Clark", FNAME_KEY = "firstname", LNAME = "Banner", NEW_LNAME = "Kent", LNAME_KEY = "lastname", EMAIL = "incredible@hulk.com", NEW_EMAIL = "kc@superman.com", EMAIL_KEY = "email", NEW_PHONE = "(520) 555-1212", PHONE_KEY = "phone", NEW_AGE = "30", AGE_KEY = "age", NEW_DATE = "2001/01/01 12:45", DATE_KEY = "date";
}
