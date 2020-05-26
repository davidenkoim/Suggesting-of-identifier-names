// Type of training: selfTestingIdentifier
// Path to file: C:\Users\Igor\IdeaProjects\SLP-Core-0.3\java-projects\ant-master\src\tests\junit\org\apache\tools\ant\MockBuildListener.java
// Number of identifiers: 12	Accuracy: 75.00%	MRR: 76.19%
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
package org.apache.tools.ant;

import java.util.Vector;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MockBuildListener implements BuildListener {

    private final Vector<BuildEvent> buffer = new Vector<>();

    private final Project project;

    public MockBuildListener(final Project project) {
// project              0	: [('project', 0.812599377211736), ('p', 0.24238812570820625), ('other', 0.023586989124480023), ('prj', 0.0230055529881318), ('task', 0.014376043741418317), ('target', 0.0130672293706638), ('subProject', 0.011776024842715612), ('proj', 0.011775333642305095), ('parent', 0.011684338263222559), ('event', 0.00395642427045437)]
        this.project = project;
    }

    public void buildStarted(BuildEvent event) {
// event                0	: [('event', 0.9659385860006721), ('actual', 0.00023869177849245878), ('expected', 0.00012425776594088498), ('be', 0.0001231518599605878), ('b', 3.173451562394901e-05), ('length', 3.069772876742041e-05), ('classpath', 1.314147133020269e-05), ('block', 7.784739238138228e-06), ('jmod', 4.8817360398581335e-06), ('publicId', 4.7434977923209865e-06)]
    }

    public void buildFinished(BuildEvent event) {
// event                0	: [('event', 0.9659385860006721), ('actual', 0.00023869177849245878), ('expected', 0.00012425776594088498), ('be', 0.0001231518599605878), ('b', 3.173451562394901e-05), ('length', 3.069772876742041e-05), ('classpath', 1.314147133020269e-05), ('block', 7.784739238138228e-06), ('jmod', 4.8817360398581335e-06), ('publicId', 4.7434977923209865e-06)]
    }

    public void targetStarted(BuildEvent event) {
// event                0	: [('event', 0.9659385860006721), ('actual', 0.00023869177849245878), ('expected', 0.00012425776594088498), ('be', 0.0001231518599605878), ('b', 3.173451562394901e-05), ('length', 3.069772876742041e-05), ('classpath', 1.314147133020269e-05), ('block', 7.784739238138228e-06), ('jmod', 4.8817360398581335e-06), ('publicId', 4.7434977923209865e-06)]
    }

    public void targetFinished(BuildEvent event) {
// event                0	: [('event', 0.9659385860006721), ('actual', 0.00023869177849245878), ('expected', 0.00012425776594088498), ('be', 0.0001231518599605878), ('b', 3.173451562394901e-05), ('length', 3.069772876742041e-05), ('classpath', 1.314147133020269e-05), ('block', 7.784739238138228e-06), ('jmod', 4.8817360398581335e-06), ('publicId', 4.7434977923209865e-06)]
    }

    public void taskStarted(BuildEvent event) {
// event                0	: [('event', 0.9659385860006721), ('actual', 0.00023869177849245878), ('expected', 0.00012425776594088498), ('be', 0.0001231518599605878), ('b', 3.173451562394901e-05), ('length', 3.069772876742041e-05), ('classpath', 1.314147133020269e-05), ('block', 7.784739238138228e-06), ('jmod', 4.8817360398581335e-06), ('publicId', 4.7434977923209865e-06)]
    }

    public void taskFinished(BuildEvent event) {
// event                0	: [('event', 0.9659385860006721), ('actual', 0.00023869177849245878), ('expected', 0.00012425776594088498), ('be', 0.0001231518599605878), ('b', 3.173451562394901e-05), ('length', 3.069772876742041e-05), ('classpath', 1.314147133020269e-05), ('block', 7.784739238138228e-06), ('jmod', 4.8817360398581335e-06), ('publicId', 4.7434977923209865e-06)]
    }

    public void messageLogged(final BuildEvent actual) {
// actual               6	: [('event', 0.7703514180690592), ('e', 0.32011493439340355), ('classpath', 0.22927995708063198), ('path', 0.2282850897273273), ('ioe', 0.20652904892109897), ('collectorFile', 0.20231114332483685), ('actual', 0.1942230895459013), ('f', 0.10945991496846208), ('substring', 0.10147212035307894), ('testDir', 0.10077216698470565)]
        if (actual.getPriority() == Project.MSG_DEBUG) {
            return;
        }
        assertFalse("unexpected messageLogged: " + actual.getMessage(), buffer.isEmpty());
        assertEquals("unexpected project ", project, actual.getProject());
        BuildEvent expected = buffer.elementAt(0);
// expected             No	: [('event', 0.4569047290044406), ('line', 0.06721552542464572), ('l', 0.05099679324595689), ('text', 0.0449483509966482), ('impl', 0.04465495498939977), ('prefix', 0.03412579775192152), ('data2', 0.03353123689158982), ('reg', 0.03350440399859114), ('lastModified', 0.023733810912705385), ('dir', 0.02345764346266817)]
        buffer.removeElementAt(0);
        assertEquals("unexpected messageLogged ", expected.getMessage(), actual.getMessage());
        assertEquals("unexpected priority ", expected.getPriority(), actual.getPriority());
    }

    public void assertEmpty() {
        assertTrue("MockBuildListener is not empty", buffer.isEmpty());
    }

    public void addBuildEvent(final String message, final int priority) {
// message              0	: [('message', 0.40743572940986406), ('mymessage', 0.11275482429654803), ('msg', 0.09868249289272042), ('name', 0.09381721308274059), ('value', 0.024700689854886583), ('output', 0.01756248812682309), ('s', 0.01730918177304069), ('line', 0.013743759270251515), ('pattern', 0.01276357636784246), ('text', 0.011258256149701443)]
// priority             0	: [('priority', 0.7633679131458537), ('msgLevel', 0.23129910840490642), ('level', 0.08010343330160449), ('t', 0.02611821623759971), ('offset', 0.02325971244279096), ('length', 0.021639232609831836), ('cause', 0.015622775890162768), ('loglevel', 0.015610589305593338), ('e', 0.01085265697963096), ('ioe', 0.01052251684573643)]
        final BuildEvent be = new BuildEvent(project);
// be                   No	: [('event', 0.9629595148419261), ('name', 0.11734251783086129), ('filter', 0.08993930574376402), ('set', 0.0450274582520959), ('p', 0.04132815795579171), ('value', 0.023351132512098468), ('args', 0.01881935012031717), ('exe', 0.01863512756617307), ('f', 0.017702208948018692), ('packageRoot', 0.014412646710316482)]
        be.setMessage(message, priority);
        buffer.addElement(be);
    }
}
