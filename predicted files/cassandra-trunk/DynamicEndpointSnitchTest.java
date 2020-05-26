// Type of training: selfTestingIdentifier
// Path to file: C:\Users\Igor\IdeaProjects\SLP-Core-0.3\java-projects\cassandra-trunk\test\unit\org\apache\cassandra\locator\DynamicEndpointSnitchTest.java
// Number of identifiers: 18	Accuracy: 44.44%	MRR: 60.05%
// True 	Rank of true in predictions : [(predicted token, probability of a token), ...]

package org.apache.cassandra.locator;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import org.junit.BeforeClass;
import org.junit.Test;
import org.apache.cassandra.Util;
import org.apache.cassandra.config.DatabaseDescriptor;
import org.apache.cassandra.exceptions.ConfigurationException;
import org.apache.cassandra.service.StorageService;
import org.apache.cassandra.utils.FBUtilities;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class DynamicEndpointSnitchTest {

    @BeforeClass
    public static void setupDD() {
        DatabaseDescriptor.daemonInitialization();
    }

    private static void setScores(DynamicEndpointSnitch dsnitch, int rounds, List<InetAddressAndPort> hosts, Integer... scores) throws InterruptedException {
// dsnitch              0	: [('dsnitch', 0.26103653524442233), ('builder', 0.1431647041931831), ('values', 0.0812379173761652), ('sb', 0.04490493353025473), ('size', 0.034049193012918595), ('result', 0.02503306360755584), ('seed', 0.02175854459499335), ('rh', 0.021754383436836913), ('snitch', 0.014740066427359816), ('out', 0.013098817158262627)]
// rounds               0	: [('rounds', 0.10715177025642941), ('i', 0.07814398550785918), ('version', 0.0773245255433246), ('nowInSec', 0.031051809276269368), ('size', 0.017175189910253075), ('length', 0.01379608453871358), ('count', 0.011887264858434145), ('offset', 0.011575693130786777), ('index', 0.008988181526218384), ('end', 0.008284822143827099)]
// hosts                2	: [('host', 0.5496593983914019), ('address', 0.29774760050988164), ('hosts', 0.26289476151803226), ('endpoints', 0.23688974581090944), ('ep2', 0.17144901218788536), ('count', 0.06552387679787813), ('size', 0.05623789833330818), ('length', 0.02410703015516559), ('targets', 0.020230843568815125), ('values', 0.019712908844444384)]
// scores               No	: [('depths', 0.22958736523985446), ('components', 0.14731295571469671), ('expected', 0.1163065096145122), ('replicas', 0.11608019717932125), ('terms', 0.11500917341951455), ('i', 0.09239505423550716), ('keyspaceName', 0.07278165321675144), ('row', 0.05870417235104222), ('results', 0.03271272387218472), ('contents', 0.031462555394439776)]
        for (int round = 0; round < rounds; round++) {
// round                1	: [('i', 0.8107329376260513), ('round', 0.7502883762605888), ('j', 0.04203217902050656), ('ii', 0.012595153730349893), ('n', 0.0063072838689603086), ('size', 0.00444403772378627), ('out', 0.004051736200750938), ('builder', 0.0037020366985369623), ('cfs', 0.003348496083898385), ('pageSize', 0.0031959899629412074)]
            for (int i = 0; i < hosts.size(); i++) dsnitch.receiveTiming(hosts.get(i), scores[i], MILLISECONDS);
// i                    0	: [('i', 0.8886908105186809), ('MOVING_NODE', 0.3958099496852082), ('j', 0.10979774695020217), ('movingIndex', 0.031795378933201376), ('LEAVING_NODE', 0.03179448944284946), ('ii', 0.021752662915978854), ('n', 0.019127796748008238), ('LEAVING', 0.015962001214750474), ('leaving', 0.01596198188553906), ('perUnitCount', 0.010464705279526238)]
        }
        Thread.sleep(150);
    }

    private static EndpointsForRange full(InetAddressAndPort... endpoints) {
// endpoints            1	: [('num', 0.8875019059553785), ('endpoints', 0.3022426813924207), ('elem', 0.18792116247139246), ('eps', 0.1877121688182456), ('transientNodes', 0.18771127968111992), ('leavingEndpoints', 0.11887512057318346), ('allEndpoints', 0.09755414467356964), ('commonRange', 0.055891939525262196), ('hostToTokens', 0.05067336877858987), ('replicas', 0.04898066325740058)]
        EndpointsForRange.Builder rlist = EndpointsForRange.builder(ReplicaUtils.FULL_RANGE, endpoints.length);
// rlist                No	: [('replicas', 0.7836247832168908), ('epState', 0.27519430854822696), ('checkStoppedTo', 0.27518855776082124), ('sb', 0.06566373134582283), ('result', 0.05683226528080704), ('builder', 0.049922295694743775), ('size', 0.04909582146039755), ('hosts', 0.025207711199696753), ('stringEndpoints', 0.025193500141372754), ('r', 0.01550507841101424)]
        for (InetAddressAndPort endpoint : endpoints) {
// endpoint             0	: [('endpoint', 0.6099556036823004), ('addr', 0.2726331119599219), ('ep', 0.14044890316173922), ('participant', 0.12573281726068264), ('peer', 0.07835755357653615), ('a', 0.06114968778419293), ('host', 0.05586466242977388), ('node', 0.04758231782827197), ('host1', 0.03572189392437319), ('target', 0.03283081053675461)]
            rlist.add(ReplicaUtils.full(endpoint));
        }
        return rlist.build();
    }

    @Test
    public void testSnitch() throws InterruptedException, IOException, ConfigurationException {
        StorageService.instance.unsafeInitialize();
        SimpleSnitch ss = new SimpleSnitch();
// ss                   0	: [('ss', 0.9437523497812446), ('i', 0.35182831721551233), ('j', 0.21923640966743435), ('r', 0.11342918693007775), ('snitch', 0.08752019154148502), ('value', 0.021067637040259893), ('size', 0.012972734337337678), ('k', 0.012712739760001838), ('endTime', 0.01268131701157811), ('startTime', 0.012680135378350972)]
        DynamicEndpointSnitch dsnitch = new DynamicEndpointSnitch(ss, String.valueOf(ss.hashCode()));
// dsnitch              0	: [('dsnitch', 0.8529419316186484), ('snitch', 0.09559250850227458), ('i', 0.006925249245616222), ('cfs', 0.0036494814378340294), ('version', 0.0031401259451660367), ('key', 0.00199522995593345), ('out', 0.0018641072724818472), ('value', 0.0018353202090264525), ('e', 0.0018106238713286734), ('value1', 0.0017422876690411195)]
        InetAddressAndPort self = FBUtilities.getBroadcastAddressAndPort();
// self                 0	: [('self', 0.6041746588656781), ('peer2', 0.3192464495134946), ('peer1', 0.11865758681268325), ('address', 0.10429522397800067), ('myAddress', 0.10417253191793471), ('host', 0.0911574819610814), ('peer3', 0.053462051001885245), ('peer4', 0.05284298811542876), ('i', 0.04591339095900968), ('boot2', 0.045652674685118425)]
        InetAddressAndPort host1 = InetAddressAndPort.getByName("127.0.0.2");
// host1                0	: [('host1', 0.7500094822482231), ('host4', 0.675002303912356), ('endpoint', 0.28532937006242176), ('remote', 0.2644832851308181), ('host2', 0.2197435167808931), ('host3', 0.2036220441474404), ('range', 0.12559555739655595), ('peer2', 0.11823912355588392), ('rows', 0.09786191102836064), ('peer1', 0.07103853924561071)]
        InetAddressAndPort host2 = InetAddressAndPort.getByName("127.0.0.3");
// host2                2	: [('host3', 0.8203192439311283), ('dst', 0.7525159633757965), ('host2', 0.6317181413589174), ('host1', 0.4711554958994635), ('host4', 0.45833798023578015), ('peer1', 0.04127663448370596), ('peer2', 0.04085817117493155), ('endpoint', 0.014496036729088446), ('local', 0.011309799863946842), ('address', 0.010181767733864344)]
        InetAddressAndPort host3 = InetAddressAndPort.getByName("127.0.0.4");
// host3                1	: [('host2', 0.908619870158216), ('host3', 0.6875085169151154), ('host1', 0.49610768711953956), ('ranges', 0.31274571023839937), ('host4', 0.282808111973795), ('peer1', 0.16510653793482383), ('peer2', 0.1634326846997262), ('endpoint', 0.057984146916353785), ('local', 0.04523919945578737), ('address', 0.040727070935457375)]
        InetAddressAndPort host4 = InetAddressAndPort.getByName("127.0.0.5");
// host4                No	: [('host1', 0.658340975671204), ('host2', 0.2166867674766519), ('peer1', 0.16510653763452773), ('peer2', 0.16343268441248643), ('host3', 0.16257376479637953), ('endpoint', 0.05798414336129572), ('local', 0.045239199030523335), ('address', 0.040727069400403444), ('peer', 0.02974186504248479), ('ep', 0.02350595852059153)]
        List<InetAddressAndPort> hosts = Arrays.asList(host1, host2, host3);
// hosts                1	: [('out', 0.5101913595042354), ('hosts', 0.31417676457533195), ('i', 0.2165680943592148), ('tmd', 0.19094843638432712), ('cfs', 0.13768255605498508), ('endpoints', 0.13432564333486158), ('key', 0.07164337278817291), ('rows', 0.06636910302287098), ('FLOAT_INFINITY', 0.06359008067327775), ('targets', 0.05869238205205343)]
        setScores(dsnitch, 1, hosts, 10, 10, 10);
        EndpointsForRange order = full(host1, host2, host3);
// order                6	: [('orig', 0.7875031047342368), ('file', 0.2743238949466934), ('replicas', 0.24129806229401196), ('cfs', 0.19946146639784146), ('w', 0.1990149416213563), ('futures', 0.1979737837632297), ('order', 0.15000456447964763), ('rows', 0.07127838729021391), ('neighbors', 0.038050440179966116), ('newEndpoints', 0.0380498216199491)]
        Util.assertRCEquals(order, dsnitch.sortedByProximity(self, full(host1, host2, host3)));
        setScores(dsnitch, 1, hosts, 20, 10, 10);
        order = full(host2, host3, host1);
        Util.assertRCEquals(order, dsnitch.sortedByProximity(self, full(host1, host2, host3)));
        setScores(dsnitch, 2, hosts, 15, 20, 10);
        order = full(host3, host1, host2);
        Util.assertRCEquals(order, dsnitch.sortedByProximity(self, full(host1, host2, host3)));
        setScores(dsnitch, 3, hosts, 10, 10, 30);
        order = full(host1, host2, host3);
        Util.assertRCEquals(order, dsnitch.sortedByProximity(self, full(host1, host2, host3)));
        setScores(dsnitch, 5, hosts, 10, 10, 10);
        order = full(host1, host2, host3);
        Util.assertRCEquals(order, dsnitch.sortedByProximity(self, full(host1, host2, host3)));
        setScores(dsnitch, 20, hosts, 10, 70, 20);
        order = full(host1, host3, host2);
        Util.assertRCEquals(order, dsnitch.sortedByProximity(self, full(host1, host2, host3)));
        order = full(host4, host1, host3, host2);
        Util.assertRCEquals(order, dsnitch.sortedByProximity(self, full(host1, host2, host3, host4)));
        setScores(dsnitch, 20, hosts, 10, 10, 10);
        order = full(host4, host1, host2, host3);
        Util.assertRCEquals(order, dsnitch.sortedByProximity(self, full(host1, host2, host3, host4)));
    }
}
