// Type of training: selfTestingIdentifier
// Path to file: C:\Users\Igor\IdeaProjects\SLP-Core-0.3\java-projects\ant-master\src\tests\junit\org\apache\tools\mail\MailMessageTest.java
// Number of identifiers: 58	Accuracy: 82.76%	MRR: 85.06%
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
package org.apache.tools.mail;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.apache.tools.ant.DummyMailServer;
import org.junit.AssumptionViolatedException;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.util.Vector;

/**
 * JUnit testcases for org.apache.tools.mail.MailMessage.
 *
 * @since Ant 1.6
 */
public class MailMessageTest {

    private String local = null;

    @Before
    public void setUp() {
        try {
            local = InetAddress.getLocalHost().getCanonicalHostName();
        } catch (java.net.UnknownHostException uhe) {
// uhe                  No	: [('e', 0.2508821014243138), ('e1', 0.2500277890545342), ('b', 0.0005077522499831842), ('length', 0.0004911636602787265), ('classpath', 0.00021026354128324303), ('block', 0.00012455582781021164), ('be', 8.363730653921603e-05), ('jmod', 7.810777663773014e-05), ('publicId', 7.589596467713578e-05), ('elementName', 6.815462281505554e-05)]
        // ignore
        }
    }

    /**
     *  Test an example that is similar to the one given in the API
     *  If this testcase takes >90s to complete, it is very likely that
     *  the two threads are blocked waiting for each other and Thread.join()
     *  timed out.
     * @throws InterruptedException if something goes wrong
     */
    @Test
    public void testAPIExample() throws InterruptedException {
        final DummyMailServer testMailServer = DummyMailServer.startMailServer(this.local);
// testMailServer       0	: [('testMailServer', 0.9285767356477389), ('out', 0.08585681385918018), ('s', 0.05714411260970961), ('writer', 0.05713864454882585), ('bft', 0.05707675302362877), ('mailServer', 0.03829108815926083), ('localProperties', 0.030218484304613626), ('sb', 0.028759502002734538), ('f', 0.028592847251124508), ('context', 0.02856632366905898)]
        final ClientThread testMailClient;
// testMailClient       0	: [('testMailClient', 0.9562534651623408), ('su', 0.11382638348012337), ('group', 0.1028031464702184), ('f', 0.07607166411846408), ('streamer', 0.05603039625371543), ('ca', 0.04832053765073099), ('out', 0.02845921141902432), ('p', 0.02829256234416925), ('mailServer', 0.028016414018578528), ('streamContentDeliver', 0.028015790075976876)]
        try {
            testMailClient = new ClientThread(testMailServer.getPort());
            testMailClient.from("Mail Message <EmailTaskTest@ant.apache.org>");
            testMailClient.to("to@you.com");
            testMailClient.cc("cc1@you.com");
            testMailClient.cc("cc2@you.com");
            testMailClient.bcc("bcc@you.com");
            testMailClient.setSubject("Test subject");
            testMailClient.setMessage("test line 1\n" + "test line 2");
            Thread client = new Thread(testMailClient);
// client               0	: [('client', 0.875066825355324), ('t', 0.12872735105262), ('out', 0.04436534880174505), ('p', 0.04357794859422387), ('w', 0.042940354613899445), ('thread', 0.042693087599646885), ('proc', 0.042665008330383905), ('errorPumper', 0.042664835527988006), ('error', 0.0426496304287971), ('process', 0.042642214029934906)]
            client.start();
            // a further 30s
            client.join(30 * 1000);
        } finally {
            testMailServer.disconnect();
        }
        String result = testMailServer.getResult();
// result               0	: [('result', 0.9289420824622), ('name', 0.08598820273899896), ('msg', 0.04864293771047905), ('dir', 0.039725464066804404), ('od', 0.03624654381012529), ('n', 0.03548226044429535), ('content', 0.0354441167502212), ('parentUri', 0.03543708037461553), ('newRootDir', 0.03539722254809669), ('implementationVersionString', 0.03539351428018096)]
        String expectedResult = "220 test SMTP EmailTaskTest\r\n" + "HELO " + local + "\r\n" + "250 " + local + " Hello " + local + " [127.0.0.1], pleased to meet you\r\n" + "MAIL FROM: <EmailTaskTest@ant.apache.org>\r\n" + "250\r\n" + "RCPT TO: <to@you.com>\r\n" + "250\r\n" + "RCPT TO: <cc1@you.com>\r\n" + "250\r\n" + "RCPT TO: <cc2@you.com>\r\n" + "250\r\n" + "RCPT TO: <bcc@you.com>\r\n" + "250\r\n" + "DATA\r\n" + "354\r\n" + "Subject: Test subject\r\n" + "From: Mail Message <EmailTaskTest@ant.apache.org>\r\n" + "To: to@you.com\r\n" + "Cc: cc1@you.com, cc2@you.com\r\n" + "X-Mailer: org.apache.tools.mail.MailMessage (ant.apache.org)\r\n" + "\r\n" + "test line 1\r\n" + "test line 2\r\n" + "\r\n" + ".\r\n" + "250\r\n" + "QUIT\r\n" + "221\r\n";
// expectedResult       0	: [('expectedResult', 0.7536536470677017), ('value', 0.0158102052331163), ('name', 0.012666253784473827), ('mainClass', 0.012066667585786258), ('filename', 0.011073660765794373), ('output', 0.010454932489946181), ('classpath', 0.008882331571174334), ('line', 0.007749349708259244), ('driveSpec', 0.007034164636855419), ('path', 0.006406284993364322)]
        // order of headers cannot be guaranteed
        assertEquals(expectedResult, result);
        assertFalse(testMailClient.getFailMessage(), testMailClient.isFailed());
    }

    /**
     *  Test a MailMessage with no cc or bcc lines
     * @throws InterruptedException if something goes wrong
     */
    @Test
    public void testToOnly() throws InterruptedException {
        final DummyMailServer testMailServer = DummyMailServer.startMailServer(this.local);
// testMailServer       0	: [('testMailServer', 0.9285767356477389), ('out', 0.08585681385918018), ('s', 0.05714411260970961), ('writer', 0.05713864454882585), ('bft', 0.05707675302362877), ('mailServer', 0.03829108815926083), ('localProperties', 0.030218484304613626), ('sb', 0.028759502002734538), ('f', 0.028592847251124508), ('context', 0.02856632366905898)]
        final ClientThread testMailClient;
// testMailClient       0	: [('testMailClient', 0.9562535687515683), ('su', 0.11382638125656835), ('group', 0.1028031458135768), ('f', 0.07607165258534454), ('streamer', 0.05603039610794799), ('ca', 0.04832053606348898), ('out', 0.02845904838711253), ('p', 0.02829254733453576), ('mailServer', 0.02801641390615638), ('streamContentDeliver', 0.02801578999827627)]
        try {
            testMailClient = new ClientThread(testMailServer.getPort());
            testMailClient.from("Mail Message <EmailTaskTest@ant.apache.org>");
            testMailClient.to("to@you.com");
            testMailClient.setSubject("Test subject");
            testMailClient.setMessage("test line 1\n" + "test line 2");
            Thread client = new Thread(testMailClient);
// client               0	: [('client', 0.875066825355324), ('t', 0.12872735105262), ('out', 0.04436534880174505), ('p', 0.04357794859422387), ('w', 0.042940354613899445), ('thread', 0.042693087599646885), ('proc', 0.042665008330383905), ('errorPumper', 0.042664835527988006), ('error', 0.0426496304287971), ('process', 0.042642214029934906)]
            client.start();
            // a further 30s
            client.join(30 * 1000);
        } finally {
            testMailServer.disconnect();
        }
        String result = testMailServer.getResult();
// result               0	: [('result', 0.9289420824622), ('name', 0.08598820273899896), ('msg', 0.04864293771047905), ('dir', 0.039725464066804404), ('od', 0.03624654381012529), ('n', 0.03548226044429535), ('content', 0.0354441167502212), ('parentUri', 0.03543708037461553), ('newRootDir', 0.03539722254809669), ('implementationVersionString', 0.03539351428018096)]
        String expectedResult = "220 test SMTP EmailTaskTest\r\n" + "HELO " + local + "\r\n" + "250 " + local + " Hello " + local + " [127.0.0.1], pleased to meet you\r\n" + "MAIL FROM: <EmailTaskTest@ant.apache.org>\r\n" + "250\r\n" + "RCPT TO: <to@you.com>\r\n" + "250\r\n" + "DATA\r\n" + "354\r\n" + "Subject: Test subject\r\n" + "From: Mail Message <EmailTaskTest@ant.apache.org>\r\n" + "To: to@you.com\r\n" + "X-Mailer: org.apache.tools.mail.MailMessage (ant.apache.org)\r\n" + "\r\n" + "test line 1\r\n" + "test line 2\r\n" + "\r\n" + ".\r\n" + "250\r\n" + "QUIT\r\n" + "221\r\n";
// expectedResult       0	: [('expectedResult', 0.7536536470677017), ('value', 0.0158102052331163), ('name', 0.012666253784473827), ('mainClass', 0.012066667585786258), ('filename', 0.011073660765794373), ('output', 0.010454932489946181), ('classpath', 0.008882331571174334), ('line', 0.007749349708259244), ('driveSpec', 0.007034164636855419), ('path', 0.006406284993364322)]
        // order of headers cannot be guaranteed
        assertEquals(expectedResult, result);
        assertFalse(testMailClient.getFailMessage(), testMailClient.isFailed());
    }

    /**
     *  Test a MailMessage with no to or bcc lines
     * @throws InterruptedException if something goes wrong
     */
    @Test
    public void testCcOnly() throws InterruptedException {
        final DummyMailServer testMailServer = DummyMailServer.startMailServer(this.local);
// testMailServer       0	: [('testMailServer', 0.9285767356477389), ('out', 0.08585681385918018), ('s', 0.05714411260970961), ('writer', 0.05713864454882585), ('bft', 0.05707675302362877), ('mailServer', 0.03829108815926083), ('localProperties', 0.030218484304613626), ('sb', 0.028759502002734538), ('f', 0.028592847251124508), ('context', 0.02856632366905898)]
        final ClientThread testMailClient;
// testMailClient       0	: [('testMailClient', 0.9562535687515683), ('su', 0.11382638125656835), ('group', 0.1028031458135768), ('f', 0.07607165258534454), ('streamer', 0.05603039610794799), ('ca', 0.04832053606348898), ('out', 0.02845904838711253), ('p', 0.02829254733453576), ('mailServer', 0.02801641390615638), ('streamContentDeliver', 0.02801578999827627)]
        try {
            testMailClient = new ClientThread(testMailServer.getPort());
            testMailClient.from("Mail Message <EmailTaskTest@ant.apache.org>");
            testMailClient.cc("cc@you.com");
            testMailClient.setSubject("Test subject");
            testMailClient.setMessage("test line 1\n" + "test line 2");
            Thread client = new Thread(testMailClient);
// client               0	: [('client', 0.875066825355324), ('t', 0.12872735105262), ('out', 0.04436534880174505), ('p', 0.04357794859422387), ('w', 0.042940354613899445), ('thread', 0.042693087599646885), ('proc', 0.042665008330383905), ('errorPumper', 0.042664835527988006), ('error', 0.0426496304287971), ('process', 0.042642214029934906)]
            client.start();
            // a further 30s
            client.join(30 * 1000);
        } finally {
            testMailServer.disconnect();
        }
        String result = testMailServer.getResult();
// result               0	: [('result', 0.9289420824622), ('name', 0.08598820273899896), ('msg', 0.04864293771047905), ('dir', 0.039725464066804404), ('od', 0.03624654381012529), ('n', 0.03548226044429535), ('content', 0.0354441167502212), ('parentUri', 0.03543708037461553), ('newRootDir', 0.03539722254809669), ('implementationVersionString', 0.03539351428018096)]
        String expectedResult = "220 test SMTP EmailTaskTest\r\n" + "HELO " + local + "\r\n" + "250 " + local + " Hello " + local + " [127.0.0.1], pleased to meet you\r\n" + "MAIL FROM: <EmailTaskTest@ant.apache.org>\r\n" + "250\r\n" + "RCPT TO: <cc@you.com>\r\n" + "250\r\n" + "DATA\r\n" + "354\r\n" + "Subject: Test subject\r\n" + "From: Mail Message <EmailTaskTest@ant.apache.org>\r\n" + "Cc: cc@you.com\r\n" + "X-Mailer: org.apache.tools.mail.MailMessage (ant.apache.org)\r\n" + "\r\n" + "test line 1\r\n" + "test line 2\r\n" + "\r\n" + ".\r\n" + "250\r\n" + "QUIT\r\n" + "221\r\n";
// expectedResult       0	: [('expectedResult', 0.7536536470677017), ('value', 0.0158102052331163), ('name', 0.012666253784473827), ('mainClass', 0.012066667585786258), ('filename', 0.011073660765794373), ('output', 0.010454932489946181), ('classpath', 0.008882331571174334), ('line', 0.007749349708259244), ('driveSpec', 0.007034164636855419), ('path', 0.006406284993364322)]
        assertEquals(expectedResult, result);
        assertFalse(testMailClient.getFailMessage(), testMailClient.isFailed());
    }

    /**
     *  Test a MailMessage with no to or cc lines
     * @throws InterruptedException if something goes wrong
     */
    @Test
    public void testBccOnly() throws InterruptedException {
        final DummyMailServer testMailServer = DummyMailServer.startMailServer(this.local);
// testMailServer       0	: [('testMailServer', 0.9285767356477389), ('out', 0.08585681385918018), ('s', 0.05714411260970961), ('writer', 0.05713864454882585), ('bft', 0.05707675302362877), ('mailServer', 0.03829108815926083), ('localProperties', 0.030218484304613626), ('sb', 0.028759502002734538), ('f', 0.028592847251124508), ('context', 0.02856632366905898)]
        final ClientThread testMailClient;
// testMailClient       0	: [('testMailClient', 0.9562535687515683), ('su', 0.11382638125656835), ('group', 0.1028031458135768), ('f', 0.07607165258534454), ('streamer', 0.05603039610794799), ('ca', 0.04832053606348898), ('out', 0.02845904838711253), ('p', 0.02829254733453576), ('mailServer', 0.02801641390615638), ('streamContentDeliver', 0.02801578999827627)]
        try {
            testMailClient = new ClientThread(testMailServer.getPort());
            testMailClient.from("Mail Message <EmailTaskTest@ant.apache.org>");
            testMailClient.bcc("bcc@you.com");
            testMailClient.setSubject("Test subject");
            testMailClient.setMessage("test line 1\n" + "test line 2");
            Thread client = new Thread(testMailClient);
// client               0	: [('client', 0.875066825355324), ('t', 0.12872735105262), ('out', 0.04436534880174505), ('p', 0.04357794859422387), ('w', 0.042940354613899445), ('thread', 0.042693087599646885), ('proc', 0.042665008330383905), ('errorPumper', 0.042664835527988006), ('error', 0.0426496304287971), ('process', 0.042642214029934906)]
            client.start();
            // a further 30s
            client.join(30 * 1000);
        } finally {
            testMailServer.disconnect();
        }
        String result = testMailServer.getResult();
// result               0	: [('result', 0.9289420824622), ('name', 0.08598820273899896), ('msg', 0.04864293771047905), ('dir', 0.039725464066804404), ('od', 0.03624654381012529), ('n', 0.03548226044429535), ('content', 0.0354441167502212), ('parentUri', 0.03543708037461553), ('newRootDir', 0.03539722254809669), ('implementationVersionString', 0.03539351428018096)]
        String expectedResult = "220 test SMTP EmailTaskTest\r\n" + "HELO " + local + "\r\n" + "250 " + local + " Hello " + local + " [127.0.0.1], pleased to meet you\r\n" + "MAIL FROM: <EmailTaskTest@ant.apache.org>\r\n" + "250\r\n" + "RCPT TO: <bcc@you.com>\r\n" + "250\r\n" + "DATA\r\n" + "354\r\n" + "Subject: Test subject\r\n" + "From: Mail Message <EmailTaskTest@ant.apache.org>\r\n" + "X-Mailer: org.apache.tools.mail.MailMessage (ant.apache.org)\r\n" + "\r\n" + "test line 1\r\n" + "test line 2\r\n" + "\r\n" + ".\r\n" + "250\r\n" + "QUIT\r\n" + "221\r\n";
// expectedResult       0	: [('expectedResult', 0.7536536470677017), ('value', 0.0158102052331163), ('name', 0.012666253784473827), ('mainClass', 0.012066667585786258), ('filename', 0.011073660765794373), ('output', 0.010454932489946181), ('classpath', 0.008882331571174334), ('line', 0.007749349708259244), ('driveSpec', 0.007034164636855419), ('path', 0.006406284993364322)]
        assertEquals(expectedResult, result);
        assertFalse(testMailClient.getFailMessage(), testMailClient.isFailed());
    }

    /**
     *  Test a MailMessage with no subject line
     *  Subject is an optional field (RFC 822 s4.1)
     * @throws InterruptedException if something goes wrong
     */
    @Test
    public void testNoSubject() throws InterruptedException {
        final DummyMailServer testMailServer = DummyMailServer.startMailServer(this.local);
// testMailServer       0	: [('testMailServer', 0.9285767356477389), ('out', 0.08585681385918018), ('s', 0.05714411260970961), ('writer', 0.05713864454882585), ('bft', 0.05707675302362877), ('mailServer', 0.03829108815926083), ('localProperties', 0.030218484304613626), ('sb', 0.028759502002734538), ('f', 0.028592847251124508), ('context', 0.02856632366905898)]
        final ClientThread testMailClient;
// testMailClient       0	: [('testMailClient', 0.9562536032794777), ('su', 0.1138263805154386), ('group', 0.10280314559471235), ('f', 0.07607164874125144), ('streamer', 0.056030396059362414), ('ca', 0.048320535534447574), ('out', 0.028458994056406863), ('p', 0.02829254233168907), ('mailServer', 0.028016413868685062), ('streamContentDeliver', 0.028015789972377938)]
        try {
            testMailClient = new ClientThread(testMailServer.getPort());
            testMailClient.from("Mail Message <EmailTaskTest@ant.apache.org>");
            testMailClient.to("to@you.com");
            testMailClient.setMessage("test line 1\n" + "test line 2");
            Thread client = new Thread(testMailClient);
// client               0	: [('client', 0.875066825355324), ('t', 0.12872735105262), ('out', 0.04436534880174505), ('p', 0.04357794859422387), ('w', 0.042940354613899445), ('thread', 0.042693087599646885), ('proc', 0.042665008330383905), ('errorPumper', 0.042664835527988006), ('error', 0.0426496304287971), ('process', 0.042642214029934906)]
            client.start();
            // a further 30s
            client.join(30 * 1000);
        } finally {
            testMailServer.disconnect();
        }
        String result = testMailServer.getResult();
// result               0	: [('result', 0.9289420824622), ('name', 0.08598820273899896), ('msg', 0.04864293771047905), ('dir', 0.039725464066804404), ('od', 0.03624654381012529), ('n', 0.03548226044429535), ('content', 0.0354441167502212), ('parentUri', 0.03543708037461553), ('newRootDir', 0.03539722254809669), ('implementationVersionString', 0.03539351428018096)]
        String expectedResult = "220 test SMTP EmailTaskTest\r\n" + "HELO " + local + "\r\n" + "250 " + local + " Hello " + local + " [127.0.0.1], pleased to meet you\r\n" + "MAIL FROM: <EmailTaskTest@ant.apache.org>\r\n" + "250\r\n" + "RCPT TO: <to@you.com>\r\n" + "250\r\n" + "DATA\r\n" + "354\r\n" + "From: Mail Message <EmailTaskTest@ant.apache.org>\r\n" + "To: to@you.com\r\n" + "X-Mailer: org.apache.tools.mail.MailMessage (ant.apache.org)\r\n" + "\r\n" + "test line 1\r\n" + "test line 2\r\n" + "\r\n" + ".\r\n" + "250\r\n" + "QUIT\r\n" + "221\r\n";
// expectedResult       0	: [('expectedResult', 0.7536536470677017), ('value', 0.0158102052331163), ('name', 0.012666253784473827), ('mainClass', 0.012066667585786258), ('filename', 0.011073660765794373), ('output', 0.010454932489946181), ('classpath', 0.008882331571174334), ('line', 0.007749349708259244), ('driveSpec', 0.007034164636855419), ('path', 0.006406284993364322)]
        assertEquals(expectedResult, result);
        assertFalse(testMailClient.getFailMessage(), testMailClient.isFailed());
    }

    /**
     *  Test a MailMessage with empty body message
     * @throws InterruptedException if something goes wrong
     */
    @Test
    public void testEmptyBody() throws InterruptedException {
        final DummyMailServer testMailServer = DummyMailServer.startMailServer(this.local);
// testMailServer       0	: [('testMailServer', 0.9285767356477389), ('out', 0.08585681385918018), ('s', 0.05714411260970961), ('writer', 0.05713864454882585), ('bft', 0.05707675302362877), ('mailServer', 0.03829108815926083), ('localProperties', 0.030218484304613626), ('sb', 0.028759502002734538), ('f', 0.028592847251124508), ('context', 0.02856632366905898)]
        final ClientThread testMailClient;
// testMailClient       0	: [('testMailClient', 0.9562535687515683), ('su', 0.11382638125656835), ('group', 0.1028031458135768), ('f', 0.07607165258534454), ('streamer', 0.05603039610794799), ('ca', 0.04832053606348898), ('out', 0.02845904838711253), ('p', 0.02829254733453576), ('mailServer', 0.02801641390615638), ('streamContentDeliver', 0.02801578999827627)]
        try {
            testMailClient = new ClientThread(testMailServer.getPort());
            testMailClient.from("Mail Message <EmailTaskTest@ant.apache.org>");
            testMailClient.to("to@you.com");
            testMailClient.setSubject("Test subject");
            testMailClient.setMessage("");
            Thread client = new Thread(testMailClient);
// client               0	: [('client', 0.875066825355324), ('t', 0.12872735105262), ('out', 0.04436534880174505), ('p', 0.04357794859422387), ('w', 0.042940354613899445), ('thread', 0.042693087599646885), ('proc', 0.042665008330383905), ('errorPumper', 0.042664835527988006), ('error', 0.0426496304287971), ('process', 0.042642214029934906)]
            client.start();
            // a further 30s
            client.join(30 * 1000);
        } finally {
            testMailServer.disconnect();
        }
        String result = testMailServer.getResult();
// result               0	: [('result', 0.9289420824622), ('name', 0.08598820273899896), ('msg', 0.04864293771047905), ('dir', 0.039725464066804404), ('od', 0.03624654381012529), ('n', 0.03548226044429535), ('content', 0.0354441167502212), ('parentUri', 0.03543708037461553), ('newRootDir', 0.03539722254809669), ('implementationVersionString', 0.03539351428018096)]
        String expectedResult = "220 test SMTP EmailTaskTest\r\n" + "HELO " + local + "\r\n" + "250 " + local + " Hello " + local + " [127.0.0.1], pleased to meet you\r\n" + "MAIL FROM: <EmailTaskTest@ant.apache.org>\r\n" + "250\r\n" + "RCPT TO: <to@you.com>\r\n" + "250\r\n" + "DATA\r\n" + "354\r\n" + "Subject: Test subject\r\n" + "From: Mail Message <EmailTaskTest@ant.apache.org>\r\n" + "To: to@you.com\r\n" + "X-Mailer: org.apache.tools.mail.MailMessage (ant.apache.org)\r\n" + "\r\n" + "\r\n" + "\r\n" + ".\r\n" + "250\r\n" + "QUIT\r\n" + "221\r\n";
// expectedResult       0	: [('expectedResult', 0.7536536470677017), ('value', 0.0158102052331163), ('name', 0.012666253784473827), ('mainClass', 0.012066667585786258), ('filename', 0.011073660765794373), ('output', 0.010454932489946181), ('classpath', 0.008882331571174334), ('line', 0.007749349708259244), ('driveSpec', 0.007034164636855419), ('path', 0.006406284993364322)]
        assertEquals(expectedResult, result);
        assertFalse(testMailClient.getFailMessage(), testMailClient.isFailed());
    }

    /**
     *  Test a MailMessage with US-ASCII character set
     *  The next four testcase can be kinda hard to debug as Ant will often
     *  print the junit failure in US-ASCII.
     * @throws InterruptedException if something goes wrong
     */
    @Test
    public void testAsciiCharset() throws InterruptedException {
        final DummyMailServer testMailServer = DummyMailServer.startMailServer(this.local);
// testMailServer       0	: [('testMailServer', 0.9285767356477389), ('out', 0.08585681385918018), ('s', 0.05714411260970961), ('writer', 0.05713864454882585), ('bft', 0.05707675302362877), ('mailServer', 0.03829108815926083), ('localProperties', 0.030218484304613626), ('sb', 0.028759502002734538), ('f', 0.028592847251124508), ('context', 0.02856632366905898)]
        final ClientThread testMailClient;
// testMailClient       0	: [('testMailClient', 0.9562535687515683), ('su', 0.34459561202579914), ('f', 0.19145626796995993), ('group', 0.1028031458135768), ('dd', 0.06217444146239778), ('out', 0.057037076194144914), ('streamer', 0.05603039610794799), ('ca', 0.04832053606348898), ('i', 0.04338558192462494), ('first', 0.043075338742304944)]
        try {
            testMailClient = new ClientThread(testMailServer.getPort());
            testMailClient.from("Mail Message <EmailTaskTest@ant.apache.org>");
            testMailClient.to("Ceki G\u00fclc\u00fc <abuse@mail-abuse.org>");
            testMailClient.setSubject("Test subject");
            testMailClient.setMessage("");
            Thread client = new Thread(testMailClient);
// client               0	: [('client', 0.875066825355324), ('t', 0.12872735105262), ('out', 0.04436534880174505), ('p', 0.04357794859422387), ('w', 0.042940354613899445), ('thread', 0.042693087599646885), ('proc', 0.042665008330383905), ('errorPumper', 0.042664835527988006), ('error', 0.0426496304287971), ('process', 0.042642214029934906)]
            client.start();
            // a further 30s
            client.join(30 * 1000);
        } finally {
            testMailServer.disconnect();
        }
        String result = testMailServer.getResult();
// result               1	: [('output', 0.22999959756245572), ('result', 0.21849288587034038), ('name', 0.08598820273899896), ('out', 0.06387396120427873), ('msg', 0.04864293771047905), ('dir', 0.039725464066804404), ('od', 0.03624654381012529), ('value', 0.03552501857701833), ('n', 0.03548226044429535), ('content', 0.0354441167502212)]
        String expectedResult = "220 test SMTP EmailTaskTest\r\n" + "HELO " + local + "\r\n" + "250 " + local + " Hello " + local + " [127.0.0.1], pleased to meet you\r\n" + "MAIL FROM: <EmailTaskTest@ant.apache.org>\r\n" + "250\r\n" + "RCPT TO: <abuse@mail-abuse.org>\r\n" + "250\r\n" + "DATA\r\n" + "354\r\n" + "Subject: Test subject\r\n" + "From: Mail Message <EmailTaskTest@ant.apache.org>\r\n" + "To: Ceki G\u00fclc\u00fc <abuse@mail-abuse.org>\r\n" + "X-Mailer: org.apache.tools.mail.MailMessage (ant.apache.org)\r\n" + "\r\n" + "\r\n" + "\r\n" + ".\r\n" + "250\r\n" + "QUIT\r\n" + "221\r\n";
// expectedResult       0	: [('expectedResult', 0.5117679310010567), ('output', 0.22999959756245572), ('out', 0.06387396120427873), ('value', 0.03552501857701833), ('key', 0.03503084788959703), ('n', 0.03475315353795789), ('r', 0.02944471276564082), ('line', 0.02926190715191032), ('result', 0.029107976306942927), ('ex', 0.028950826191175744)]
        ByteArrayOutputStream baos1 = new ByteArrayOutputStream();
// baos1                No	: [('stdout', 0.13963680882202129), ('stderr', 0.13963653233818785), ('baos', 0.10427989216762608), ('output', 0.09336397314000434), ('bos', 0.09309617481705128), ('line', 0.06626249524297181), ('out', 0.0514521613333309), ('l', 0.050356108423006876), ('e', 0.04700051902652742), ('o', 0.046728078144810586)]
        ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
// baos2                No	: [('stderr', 0.7375035416052541), ('stdout', 0.13963680899982261), ('e', 0.12353368646929384), ('mainClass', 0.10394286372185962), ('baos', 0.0983165127204482), ('output', 0.09336397782951372), ('bos', 0.09309617499485258), ('ioe', 0.08157330393986226), ('results', 0.051905898338802055), ('out', 0.0514521652894099)]
        PrintStream bos1 = new PrintStream(baos1, true);
// bos1                 No	: [('err', 0.49995390771981174), ('out', 0.2862310231929008), ('sysErr', 0.04904103663084216), ('runner', 0.04014348621195415), ('rc', 0.04001403731033274), ('ps', 0.03544134460809069), ('bos2', 0.0314998293698033), ('result', 0.022315262857759203), ('t', 0.02202637375068422), ('exec', 0.0215062576972572)]
        PrintStream bos2 = new PrintStream(baos2, true);
// bos2                 No	: [('err', 0.3999078154396235), ('out', 0.1724620463858016), ('sysErr', 0.09808207326168432), ('ps', 0.07088268921618138), ('bos1', 0.0629996587396066), ('logTo', 0.01754953696768847), ('i', 0.015197159311067684), ('cmd', 0.005458183624066895), ('ds', 0.00531629450429108), ('output', 0.004435637510941441)]
        bos1.print(expectedResult);
        bos2.print(result);
        assertEquals("baos1 and baos2 should be the same in testAsciiCharset()", baos1.toString(), baos2.toString());
        assertFalse(testMailClient.getFailMessage(), testMailClient.isFailed());
    }

    /**
     *  A private test class that wraps MailMessage
     */
    private class ClientThread implements Runnable {

        private final int port;

        private MailMessage msg;

        private boolean fail = false;

        private String failMessage = null;

        protected String from = null;

        protected String subject = null;

        protected String message = null;

        protected Vector<String> replyToList = new Vector<>();

        protected Vector<String> toList = new Vector<>();

        protected Vector<String> ccList = new Vector<>();

        protected Vector<String> bccList = new Vector<>();

        ClientThread(int port) {
// port                 0	: [('port', 0.7981155600711806), ('i', 0.28776176064215125), ('p', 0.13964944229035214), ('j', 0.025939579627335747), ('t', 0.023652516249910407), ('index', 0.014370087744103439), ('b', 0.01127188647484174), ('options', 0.009771806379562988), ('size', 0.008829429459394281), ('counter', 0.0077695090270853764)]
            this.port = port;
        }

        public void run() {
            for (int i = 9; i > 0; i--) {
// i                    0	: [('i', 0.6670824733734217), ('j', 0.10394894154413709), ('pos', 0.10251071596704125), ('len', 0.10036540823645457), ('nPart', 0.10030436150132219), ('k', 0.04161107930821687), ('t', 0.02677723854430598), ('icounter', 0.01661575050275077), ('status', 0.016534067795483012), ('b1', 0.016201338909894003)]
                try {
                    msg = new MailMessage("localhost", port);
                } catch (java.net.ConnectException ce) {
// ce                   No	: [('b', 0.0010155044999663684), ('length', 0.000982327320557453), ('classpath', 0.00042052708256648606), ('block', 0.0002491116556204233), ('be', 0.00016727461307843207), ('jmod', 0.00015621555327546027), ('publicId', 0.00015179192935427157), ('elementName', 0.00013630924563011107), ('dataShadow', 0.0001274619977877336), ('found', 0.00012525018582713925)]
                    try {
                        Thread.sleep(10 * 1000);
                    } catch (InterruptedException ie) {
// ie                   1	: [('e', 0.5411765594426828), ('ie', 0.37535099112183673), ('ex', 0.1029329162511877), ('e2', 0.0341303577326182), ('eyeEx', 0.0138258559757414), ('l', 0.0070554545732241375), ('jmod', 0.006693438406148921), ('p', 0.004856772029379023), ('name', 0.004815677532990829), ('result', 0.004663841855102437)]
                        throw new AssumptionViolatedException("Thread interrupted", ie);
                    }
                } catch (IOException ioe) {
// ioe                  0	: [('ioe', 0.7656822616461776), ('e', 0.39795977259661913), ('ex', 0.15365259355580813), ('exc', 0.03285107000366214), ('ioex', 0.0176111283069556), ('x', 0.01361169970996213), ('ftp', 0.013395943741033759), ('ignored', 0.010866794430479405), ('taskClass', 0.009989606730008455), ('file', 0.00935124292721764)]
                    fail = true;
                    failMessage = "IOException: " + ioe;
                    return;
                }
                if (msg != null) {
                    break;
                }
            }
            if (msg == null) {
                fail = true;
                failMessage = "java.net.ConnectException: Connection refused";
                return;
            }
            try {
                msg.from(from);
                replyToList.forEach(e -> msg.replyto(e));
// e                    0	: [('e', 0.8564783139281343), ('t', 0.07097803896118147), ('key', 0.0320461322183142), ('l', 0.03194716376871607), ('f', 0.021956810179695346), ('pat', 0.02113511177816516), ('pkg', 0.021123464236614223), ('o', 0.016197026229365558), ('classpathAttribute', 0.01584558623958759), ('line', 0.012674569669050447)]
                for (String e : toList) {
// e                    0	: [('e', 0.8564770435555825), ('file', 0.09700987201750451), ('name', 0.08337933629682545), ('t', 0.0709780465814039), ('element', 0.05566256176874629), ('bcc', 0.050599645074274266), ('cc', 0.049490503711805085), ('filename', 0.04456174871872342), ('key', 0.0440610948941889), ('to', 0.03695552032571183)]
                    msg.to(e);
                }
                for (String e : ccList) {
// e                    0	: [('e', 0.8564770435555825), ('s', 0.08969858370587572), ('t', 0.0709780465814039), ('element', 0.0640353789233431), ('key', 0.06172170523488398), ('currentPathElement', 0.057025322485309), ('dstName', 0.057024907748550714), ('name', 0.05236335016431389), ('bcc', 0.050599645074274266), ('cc', 0.049490503711805085)]
                    msg.cc(e);
                }
                for (String e : bccList) {
// e                    0	: [('e', 0.8564770435555825), ('s', 0.08969858370587572), ('t', 0.0709780465814039), ('element', 0.0640353789233431), ('key', 0.06172170523488398), ('currentPathElement', 0.057025322485309), ('dstName', 0.057024907748550714), ('name', 0.05236335016431389), ('bcc', 0.050599645074274266), ('cc', 0.049490503711805085)]
                    msg.bcc(e);
                }
                if (subject != null) {
                    msg.setSubject(subject);
                }
                if (message != null) {
                    PrintStream out = msg.getPrintStream();
// out                  2	: [('ps', 0.7503814220011381), ('sysOut', 0.2850864587861821), ('out', 0.2306785531224206), ('writer', 0.14345338445946662), ('err', 0.010513876045684116), ('output', 0.004435637510941441), ('s', 0.0036527500910039095), ('stream', 0.003510777526683564), ('i', 0.003276870232731693), ('sysErr', 0.002627527807138856)]
                    out.println(message);
                }
                msg.sendAndClose();
            } catch (IOException ioe) {
// ioe                  0	: [('ioe', 0.7656822616461776), ('e', 0.40890889848132056), ('ex', 0.12990932333622068), ('ioex', 0.020484517696587202), ('exc', 0.01379596141978942), ('ftp', 0.013395943741033759), ('x', 0.010950033749040225), ('taskClass', 0.009989606730008455), ('eyeOhEx', 0.009756907569454491), ('file', 0.00935124292721764)]
                fail = true;
                failMessage = "IOException: " + ioe;
            }
        }

        public boolean isFailed() {
            return fail;
        }

        public String getFailMessage() {
            return failMessage;
        }

        @SuppressWarnings("unused")
        public void replyTo(String replyTo) {
// replyTo              No	: [('address', 0.5025407076143704), ('name', 0.06155535684399712), ('s', 0.02166488489538175), ('value', 0.020552717844961824), ('rc', 0.0187134167829908), ('message', 0.016854965057537783), ('msg', 0.015278668810540214), ('uri', 0.01418545372823002), ('target', 0.01388785146416872), ('line', 0.011559443657118192)]
            replyToList.add(replyTo);
        }

        public void to(String to) {
// to                   0	: [('to', 0.8754844686965944), ('address', 0.7512703538071853), ('rc', 0.0093567083914954), ('name', 0.00769441960549964), ('instr', 0.005388225539820228), ('r', 0.004915307971054906), ('c', 0.004676609635261387), ('fileNameMapper', 0.004584471929561368), ('i', 0.0034655763432320194), ('p', 0.0033561156750419596)]
            toList.add(to);
        }

        public void cc(String cc) {
// cc                   0	: [('cc', 0.8750291202193937), ('address', 0.7512703538071853), ('rc', 0.0093567083914954), ('name', 0.00769441960549964), ('instr', 0.005388225539820228), ('r', 0.004915307971054906), ('c', 0.004676609635261387), ('fileNameMapper', 0.004584471929561368), ('i', 0.0034655763432320194), ('p', 0.0033561156750419596)]
            ccList.add(cc);
        }

        public void bcc(String bcc) {
// bcc                  0	: [('bcc', 0.8750280834187779), ('address', 0.7512703538071853), ('rc', 0.0093567083914954), ('name', 0.00769441960549964), ('instr', 0.005388225539820228), ('r', 0.004915307971054906), ('c', 0.004676609635261387), ('fileNameMapper', 0.004584471929561368), ('i', 0.0034655763432320194), ('p', 0.0033561156750419596)]
            bccList.add(bcc);
        }

        public void setSubject(String subject) {
// subject              0	: [('subject', 0.9375091033878077), ('subj', 0.29169436992521874), ('name', 0.00769441960549964), ('s', 0.0027081106119227188), ('value', 0.002569089730620228), ('message', 0.002106870632192223), ('msg', 0.0019098336013175267), ('uri', 0.0017731817160287525), ('target', 0.00173598143302109), ('line', 0.001444930457139774)]
            this.subject = subject;
        }

        public void from(String from) {
// from                 0	: [('from', 0.8788365089050467), ('address', 0.04735841767678504), ('name', 0.00769441960549964), ('s', 0.0027081106119227188), ('value', 0.002569089730620228), ('message', 0.002106870632192223), ('msg', 0.0019098336013175267), ('uri', 0.0017731817160287525), ('target', 0.00173598143302109), ('line', 0.001444930457139774)]
            this.from = from;
        }

        public void setMessage(String message) {
// message              0	: [('message', 0.39153583678507337), ('msg', 0.29357650026798415), ('value', 0.1654983027377323), ('m', 0.1639203615230148), ('s', 0.14854144394525604), ('name', 0.00769441960549964), ('t', 0.00676633607108435), ('event', 0.005449042594379685), ('taskClass', 0.005334269285534898), ('aSource', 0.0026685396158105746)]
            this.message = message;
        }
    }
}
