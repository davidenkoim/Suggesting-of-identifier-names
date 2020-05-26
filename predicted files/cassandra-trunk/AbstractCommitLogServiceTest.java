// Type of training: selfTestingIdentifier
// Path to file: C:\Users\Igor\IdeaProjects\SLP-Core-0.3\java-projects\cassandra-trunk\test\unit\org\apache\cassandra\db\commitlog\AbstractCommitLogServiceTest.java
// Number of identifiers: 36	Accuracy: 80.56%	MRR: 83.33%
// True 	Rank of true in predictions : [(predicted token, probability of a token), ...]

package org.apache.cassandra.db.commitlog;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.apache.cassandra.config.Config;
import org.apache.cassandra.config.DatabaseDescriptor;
import org.apache.cassandra.db.commitlog.AbstractCommitLogService.SyncRunnable;
import org.apache.cassandra.utils.FreeRunningClock;
import static org.apache.cassandra.db.commitlog.AbstractCommitLogService.DEFAULT_MARKER_INTERVAL_MILLIS;

public class AbstractCommitLogServiceTest {

    @BeforeClass
    public static void before() {
        DatabaseDescriptor.daemonInitialization();
        DatabaseDescriptor.setCommitLogSync(Config.CommitLogSync.periodic);
        DatabaseDescriptor.setCommitLogSyncPeriod(10 * 1000);
    }

    @Test
    public void testConstructorSyncIsQuantized() {
        long syncTimeMillis = 10 * 1000;
// syncTimeMillis       0	: [('syncTimeMillis', 0.9305588999793803), ('expectedMillis', 0.5867591637907082), ('size', 0.08373365259195742), ('confirmedCount', 0.03728900301721938), ('total', 0.036867463519557234), ('seed', 0.03650843480181645), ('sum', 0.031766597234046526), ('timeInMillis', 0.027120614867509916), ('count', 0.02642872230317792), ('now', 0.025340975714584484)]
        FakeCommitLogService commitLogService = new FakeCommitLogService(syncTimeMillis);
// commitLogService     0	: [('commitLogService', 0.8750466534096616), ('row', 0.0014774609528410668), ('i', 0.0014472356716285382), ('e', 0.0013608505430386044), ('key', 0.0012331320476795188), ('version', 0.0010674691263480402), ('cfs', 0.0009506445321213274), ('out', 0.0008361704854384235), ('sstable', 0.0008347028122107182), ('entry', 0.0007502264697425982)]
        Assert.assertEquals(toNanos(DEFAULT_MARKER_INTERVAL_MILLIS), commitLogService.markerIntervalNanos);
        Assert.assertEquals(toNanos(syncTimeMillis), commitLogService.syncIntervalNanos);
    }

    @Test
    public void testConstructorSyncEqualsMarkerDefault() {
        long syncTimeMillis = 100;
// syncTimeMillis       0	: [('syncTimeMillis', 0.9305588999793803), ('expectedMillis', 0.5867591637907082), ('size', 0.08373365259195742), ('confirmedCount', 0.03728900301721938), ('total', 0.036867463519557234), ('seed', 0.03650843480181645), ('sum', 0.031766597234046526), ('timeInMillis', 0.027120614867509916), ('count', 0.02642872230317792), ('now', 0.025340975714584484)]
        FakeCommitLogService commitLogService = new FakeCommitLogService(syncTimeMillis);
// commitLogService     0	: [('commitLogService', 0.8958346696412991), ('expected', 0.062020329297697914), ('i', 0.05197600428526123), ('desc1', 0.03967104718552599), ('rows', 0.03566547234330099), ('session', 0.02767874555947282), ('number', 0.019721329727789574), ('repairExceptions', 0.018602214271460832), ('withIndex', 0.01686844138027895), ('canonicalSet', 0.014322125336478685)]
        Assert.assertEquals(toNanos(DEFAULT_MARKER_INTERVAL_MILLIS), commitLogService.markerIntervalNanos);
        Assert.assertEquals(toNanos(syncTimeMillis), commitLogService.syncIntervalNanos);
        Assert.assertEquals(commitLogService.markerIntervalNanos, commitLogService.syncIntervalNanos);
    }

    @Test
    public void testConstructorSyncShouldRoundUp() {
        long syncTimeMillis = 151;
// syncTimeMillis       0	: [('syncTimeMillis', 0.9305590720487612), ('size', 0.08373365231205368), ('confirmedCount', 0.03728900301506738), ('total', 0.036867463505784424), ('seed', 0.03650843477118629), ('sum', 0.03176659721475024), ('timeInMillis', 0.027120614864568845), ('count', 0.026428722192062874), ('now', 0.025340975673122577), ('timestamp', 0.023884307572612763)]
        long expectedMillis = 200;
// expectedMillis       1	: [('syncTimeMillis', 0.587617843841858), ('expectedMillis', 0.2942361509714297), ('u', 0.029187936909444042), ('timestamp', 0.027961048856868544), ('size', 0.016685372275396487), ('now', 0.011733563577341934), ('start', 0.011664843369687837), ('count', 0.01152503506211025), ('repairedAt', 0.007878471862762993), ('length', 0.007495522650396977)]
        FakeCommitLogService commitLogService = new FakeCommitLogService(syncTimeMillis);
// commitLogService     0	: [('commitLogService', 0.8750466534096616), ('row', 0.0014774609528410668), ('i', 0.0014472356716285382), ('e', 0.0013608505430386044), ('key', 0.0012331320476795188), ('version', 0.0010674691263480402), ('cfs', 0.0009506445321213274), ('out', 0.0008361704854384235), ('sstable', 0.0008347028122107182), ('entry', 0.0007502264697425982)]
        Assert.assertEquals(toNanos(DEFAULT_MARKER_INTERVAL_MILLIS), commitLogService.markerIntervalNanos);
        Assert.assertEquals(toNanos(expectedMillis), commitLogService.syncIntervalNanos);
    }

    @Test
    public void testConstructorSyncShouldRoundDown() {
        long syncTimeMillis = 121;
// syncTimeMillis       0	: [('syncTimeMillis', 0.9305590720487612), ('size', 0.08373365231205368), ('confirmedCount', 0.03728900301506738), ('total', 0.036867463505784424), ('seed', 0.03650843477118629), ('sum', 0.03176659721475024), ('timeInMillis', 0.027120614864568845), ('count', 0.026428722192062874), ('now', 0.025340975673122577), ('timestamp', 0.023884307572612763)]
        long expectedMillis = 100;
// expectedMillis       1	: [('syncTimeMillis', 0.587617843841858), ('expectedMillis', 0.2942361509714297), ('u', 0.029187936909444042), ('timestamp', 0.027961048856868544), ('size', 0.016685372275396487), ('now', 0.011733563577341934), ('start', 0.011664843369687837), ('count', 0.01152503506211025), ('repairedAt', 0.007878471862762993), ('length', 0.007495522650396977)]
        FakeCommitLogService commitLogService = new FakeCommitLogService(syncTimeMillis);
// commitLogService     0	: [('commitLogService', 0.8958347276252032), ('row', 0.0014774609528410668), ('i', 0.0014472356716285382), ('e', 0.0013608505430386044), ('key', 0.0012331320476795188), ('version', 0.0010674691263480402), ('cfs', 0.0009506445321213274), ('out', 0.0008361704854384235), ('sstable', 0.0008347028122107182), ('entry', 0.0007502264697425982)]
        Assert.assertEquals(toNanos(DEFAULT_MARKER_INTERVAL_MILLIS), commitLogService.markerIntervalNanos);
        Assert.assertEquals(toNanos(expectedMillis), commitLogService.syncIntervalNanos);
    }

    @Test
    public void testConstructorSyncTinyValue() {
        long syncTimeMillis = 10;
// syncTimeMillis       0	: [('syncTimeMillis', 0.9305588999658713), ('size', 0.08373387487904947), ('timeout', 0.04810055250958847), ('confirmedCount', 0.03728901523079587), ('total', 0.036867494053498456), ('seed', 0.03650850808327537), ('second', 0.034309032080817294), ('minute', 0.03426330653374184), ('hour', 0.03426330653374184), ('sum', 0.031766624103914794)]
        long expectedNanos = toNanos(syncTimeMillis);
// expectedNanos        No	: [('seed', 0.2829523321997862), ('startTime', 0.2823800927609792), ('last', 0.09500888169890732), ('colLong', 0.06268542313383137), ('expected', 0.06215475665903444), ('i', 0.05207957670741535), ('desc1', 0.03975850920015124), ('rows', 0.035747142599036504), ('load', 0.03134567987833465), ('session', 0.02773988871108208)]
        FakeCommitLogService commitLogService = new FakeCommitLogService(syncTimeMillis);
// commitLogService     0	: [('commitLogService', 0.7916694552504064), ('i', 0.013849704569586023), ('version', 0.006279873668627468), ('key', 0.0039902230569899486), ('cfs', 0.0039511213374706505), ('out', 0.003727992472897129), ('e', 0.0036210351592059014), ('value1', 0.0034843638219622746), ('metadata', 0.0033607002977302652), ('value', 0.003344363550417755)]
        Assert.assertEquals(expectedNanos, commitLogService.markerIntervalNanos);
        Assert.assertEquals(expectedNanos, commitLogService.syncIntervalNanos);
    }

    private static long toNanos(long millis) {
// millis               No	: [('timeoutMillis', 0.15052236010893985), ('currentTimeMs', 0.07847321337630034), ('in', 0.07500033096946804), ('period', 0.07482989758105166), ('expiresInMillis', 0.07482779188947522), ('milli', 0.07482739937196321), ('logIntervalMillis', 0.07482721760995438), ('timeQuotaMs', 0.07482701651894932), ('latency', 0.03854875633892805), ('currentTimeMillis', 0.038543993384938885)]
        return TimeUnit.MILLISECONDS.toNanos(millis);
    }

    private static class FakeCommitLogService extends AbstractCommitLogService {

        FakeCommitLogService(long syncIntervalMillis) {
// syncIntervalMillis   No	: [('def', 0.35439175475080387), ('receiver', 0.14269202963339866), ('statement', 0.04081186513284567), ('destination', 0.04076010350512982), ('javaType', 0.04074457742597813), ('columnDef', 0.04074124981451874), ('newRestrictionStart', 0.04073983879779498), ('humanReadable', 0.038090591608012765), ('timestamp', 0.02875202759999454), ('timeout', 0.026102604189549433)]
            super(new FakeCommitLog(), "This is not a real commit log", syncIntervalMillis, true);
            lastSyncedAt = 0;
        }

        protected void maybeWaitForSync(CommitLogSegment.Allocation alloc) {
// alloc                0	: [('alloc', 0.9080980361067752), ('allocation', 0.034329398904513114), ('range', 1.4604305235371078e-05), ('expected', 1.2033558279135476e-05), ('a', 1.0342277386875209e-05), ('protocolVersion', 9.723751803420026e-06), ('partitioner', 7.124011460459961e-06), ('strategy', 6.476492490280317e-06), ('rs', 5.90628921803257e-06), ('results', 5.645348737512415e-06)]
        }
    }

    @Test
    public void testSync() {
        long syncTimeMillis = AbstractCommitLogService.DEFAULT_MARKER_INTERVAL_MILLIS * 2;
// syncTimeMillis       0	: [('syncTimeMillis', 0.9305590720487612), ('size', 0.08373365231205368), ('confirmedCount', 0.03728900301506738), ('total', 0.036867463505784424), ('seed', 0.03650843477118629), ('sum', 0.03176659721475024), ('timeInMillis', 0.027120614864568845), ('count', 0.026428722192062874), ('now', 0.025340975673122577), ('timestamp', 0.023884307572612763)]
        FreeRunningClock clock = new FreeRunningClock();
// clock                0	: [('clock', 0.6250130769164302), ('promise', 0.17483009846770034), ('results', 0.04684025363603083), ('tokens', 0.04409858979553813), ('prepareLatch', 0.04381587700424131), ('cfs', 0.025590780292695594), ('latch', 0.022470419181690304), ('filter', 0.022349695712051414), ('it', 0.022337182797982844), ('executor', 0.022330032223547878)]
        FakeCommitLogService commitLogService = new FakeCommitLogService(syncTimeMillis);
// commitLogService     0	: [('commitLogService', 0.7916694165958572), ('strategy', 0.2764474386561376), ('waited', 0.04226580965414537), ('async', 0.03955650791500756), ('task', 0.03954371963310431), ('epState', 0.03951345806648429), ('status', 0.039496096596565125), ('allocator', 0.0394960330881776), ('rate', 0.03949024774324328), ('spyFinalize', 0.03948768053986164)]
        SyncRunnable syncRunnable = commitLogService.new SyncRunnable(clock);
// syncRunnable         0	: [('syncRunnable', 0.7833358513155736), ('rows', 0.20696247509280002), ('coordinator', 0.0644650872472119), ('op', 0.05832164076796832), ('resolver', 0.04957964126912168), ('repairSubmitted', 0.0495714120201253), ('sstable', 0.04096878725171127), ('permit', 0.03966294143423832), ('e', 0.035380463130905454), ('frame', 0.03353995262848969)]
        FakeCommitLog commitLog = (FakeCommitLog) commitLogService.commitLog;
// commitLog            No	: [('results', 0.09697142248765503), ('logFiles', 0.08357993107919254), ('bounds', 0.07233926385802444), ('handler', 0.05470814884776054), ('expressions', 0.05015424765911704), ('rs', 0.04749744968949279), ('tracker', 0.04103753627768026), ('cfs', 0.039219585536962275), ('resultSet', 0.031075621191523795), ('res', 0.030737027004923107)]
        Assert.assertTrue(syncRunnable.sync());
        Assert.assertEquals(1, commitLog.markCount.get());
        Assert.assertEquals(0, commitLog.syncCount.get());
        clock.advance(DEFAULT_MARKER_INTERVAL_MILLIS, TimeUnit.MILLISECONDS);
        Assert.assertTrue(syncRunnable.sync());
        Assert.assertEquals(2, commitLog.markCount.get());
        Assert.assertEquals(0, commitLog.syncCount.get());
        clock.advance(DEFAULT_MARKER_INTERVAL_MILLIS, TimeUnit.MILLISECONDS);
        Assert.assertTrue(syncRunnable.sync());
        Assert.assertEquals(2, commitLog.markCount.get());
        Assert.assertEquals(1, commitLog.syncCount.get());
        clock.advance(DEFAULT_MARKER_INTERVAL_MILLIS, TimeUnit.MILLISECONDS);
        commitLogService.shutdown();
        Assert.assertFalse(syncRunnable.sync());
        Assert.assertEquals(2, commitLog.markCount.get());
        Assert.assertEquals(2, commitLog.syncCount.get());
    }

    private static class FakeCommitLog extends CommitLog {

        private final AtomicInteger markCount = new AtomicInteger();

        private final AtomicInteger syncCount = new AtomicInteger();

        FakeCommitLog() {
            super(null);
        }

        @Override
        public void sync(boolean flush) {
// flush                0	: [('flush', 0.8772288874277229), ('i', 0.040667664378299814), ('value', 0.028561317395259054), ('bytes', 0.01249078344907921), ('sstable', 0.011327516697055043), ('type', 0.011294434128011274), ('e', 0.009508373882546248), ('version', 0.007750425754943499), ('f', 0.0075149878771272405), ('result', 0.006870150304868795)]
            if (flush)
                syncCount.incrementAndGet();
            else
                markCount.incrementAndGet();
        }
    }

    @Test
    public void maybeLogFlushLag_MustLog() {
        long syncTimeMillis = 10;
// syncTimeMillis       0	: [('syncTimeMillis', 0.9305590720487612), ('size', 0.08373365231205368), ('confirmedCount', 0.03728900301506738), ('total', 0.036867463505784424), ('seed', 0.03650843477118629), ('sum', 0.03176659721475024), ('timeInMillis', 0.027120614864568845), ('count', 0.026428722192062874), ('now', 0.025340975673122577), ('timestamp', 0.023884307572612763)]
        SyncRunnable syncRunnable = new FakeCommitLogService(syncTimeMillis).new SyncRunnable(new FreeRunningClock());
// syncRunnable         0	: [('syncRunnable', 0.9458339821565984), ('rows', 0.20613711073236546), ('now', 0.03911607970498021), ('e', 0.03525550608722413), ('sessions', 0.031996177208945334), ('handler', 0.02666766759739323), ('permit', 0.024876736130131222), ('visitor', 0.024465270071024135), ('coordinator', 0.023802881862911435), ('futures', 0.023101972310734305)]
        long pollStarted = 1;
// pollStarted          0	: [('pollStarted', 0.9312522557035859), ('length', 0.050911636758088866), ('time', 0.03710160659803412), ('start', 0.03258158724724154), ('size', 0.031058343739153765), ('count', 0.027503382474758552), ('offset', 0.026062006593898592), ('address', 0.024781248623955275), ('expectedBucket', 0.02438423853622611), ('toAgoInMillis', 0.019792403331007)]
        long now = Integer.MAX_VALUE;
// now                  0	: [('now', 0.9063032411987255), ('latency', 0.06696723477497035), ('oldExpectedKeySize', 0.06685692837733684), ('expected', 0.062020328450118956), ('i', 0.05197599611396434), ('desc1', 0.039671047102866605), ('rows', 0.0356654718550906), ('session', 0.02767874508897075), ('ts2', 0.026157171254041763), ('timestamp', 0.0250468552778935)]
        Assert.assertTrue(syncRunnable.maybeLogFlushLag(pollStarted, now));
        Assert.assertEquals(now - pollStarted, syncRunnable.getTotalSyncDuration());
    }

    @Test
    public void maybeLogFlushLag_NoLog() {
        long syncTimeMillis = 10;
// syncTimeMillis       0	: [('syncTimeMillis', 0.9305588999658713), ('size', 0.08373365259195742), ('confirmedCount', 0.03728900301721938), ('total', 0.036867463519557234), ('seed', 0.03650843480181645), ('sum', 0.031766597234046526), ('timeInMillis', 0.027120614867509916), ('count', 0.02642872230317792), ('now', 0.025340956385516533), ('timestamp', 0.02388430763832056)]
        SyncRunnable syncRunnable = new FakeCommitLogService(syncTimeMillis).new SyncRunnable(new FreeRunningClock());
// syncRunnable         0	: [('syncRunnable', 0.9458339821565984), ('coordinator', 0.06446508688163587), ('op', 0.05832164052636835), ('resolver', 0.049579641121411555), ('repairSubmitted', 0.04957141198526013), ('sstable', 0.04096878437593654), ('permit', 0.039662941319273545), ('now', 0.03911607970498021), ('frame', 0.03353995234629079), ('iter', 0.0261079839792734)]
        long pollStarted = 1;
// pollStarted          0	: [('pollStarted', 0.9312522460403073), ('length', 0.0509121623759802), ('time', 0.037101796041250676), ('start', 0.03258234117098881), ('size', 0.031059292258621963), ('count', 0.027504067342104234), ('offset', 0.026062371632784485), ('address', 0.024781386544125806), ('expectedBucket', 0.024384295573530244), ('toAgoInMillis', 0.019792403331186335)]
        long now = pollStarted + (syncTimeMillis - 1);
// now                  0	: [('now', 0.9063032411987255), ('latency', 0.06696723477497035), ('oldExpectedKeySize', 0.06685692837733684), ('expected', 0.062020328450118956), ('i', 0.05197599611396434), ('desc1', 0.039671047102866605), ('rows', 0.0356654718550906), ('session', 0.02767874508897075), ('ts2', 0.026157171254041763), ('timestamp', 0.0250468552778935)]
        Assert.assertFalse(syncRunnable.maybeLogFlushLag(pollStarted, now));
        Assert.assertEquals(now - pollStarted, syncRunnable.getTotalSyncDuration());
    }

    @Test
    public void maybeLogFlushLag_MultipleOperations() {
        long syncTimeMillis = 10;
// syncTimeMillis       0	: [('syncTimeMillis', 0.9305588999658713), ('size', 0.08373365259195742), ('confirmedCount', 0.03728900301721938), ('total', 0.036867463519557234), ('seed', 0.03650843480181645), ('sum', 0.031766597234046526), ('timeInMillis', 0.027120614867509916), ('count', 0.02642872230317792), ('now', 0.025340956385516533), ('timestamp', 0.02388430763832056)]
        SyncRunnable syncRunnable = new FakeCommitLogService(syncTimeMillis).new SyncRunnable(new FreeRunningClock());
// syncRunnable         0	: [('syncRunnable', 0.9458339628288934), ('op', 0.2745793093327129), ('rows', 0.20613711102385007), ('simple', 0.12878399060848084), ('refs', 0.12689402789758158), ('now', 0.0391160705675057), ('e', 0.03525550733935202), ('sessions', 0.03199617733119149), ('handler', 0.026667667722922662), ('permit', 0.024876736170795742)]
        long pollStarted = 1;
// pollStarted          0	: [('pollStarted', 0.9305576101911593), ('toAgoInMillis', 0.08319371688413232), ('m', 0.07516789812358624), ('timeOfLastLogging', 0.07507105729934775), ('length', 0.050912162587526726), ('lastEventAt', 0.045659176617336074), ('entry', 0.03764731365101926), ('sentAt', 0.03759496975798203), ('updated', 0.03754131071256378), ('lastTimestamp', 0.03753678764351994)]
        long now = pollStarted + (syncTimeMillis - 1);
// now                  0	: [('now', 0.9018386078544198), ('sb', 0.07357113753464176), ('latency', 0.06696723478745215), ('oldExpectedKeySize', 0.06685692837755203), ('expected', 0.062020704106036065), ('i', 0.05527304391575984), ('out', 0.04973657961814229), ('writer', 0.04128540579315184), ('desc1', 0.03967115894652595), ('rows', 0.035665524729104586)]
        int runCount = 12;
// runCount             No	: [('length', 0.10398261291948045), ('c', 0.10241002606508615), ('newLength', 0.10142291027287888), ('chunkCount', 0.10139324126325563), ('ubKeyIndex', 0.10138192627409984), ('throttleInKB', 0.10129928655036252), ('max', 0.07354004977955854), ('splittedCount', 0.07121674268256951), ('count', 0.070326461085589), ('partitionCount', 0.07025909835036885)]
        for (int i = 1; i <= runCount; i++) {
// i                    0	: [('i', 0.8468884407209105), ('expected', 0.06202032849312346), ('j', 0.060206831323419925), ('desc1', 0.039671047106202235), ('rows', 0.035665471902470866), ('session', 0.02767874513509567), ('number', 0.019721329569823542), ('repairExceptions', 0.01860221424681581), ('withIndex', 0.016868441340292833), ('pageSize', 0.01659343249049094)]
            Assert.assertFalse(syncRunnable.maybeLogFlushLag(pollStarted, now));
            Assert.assertEquals(i * (now - pollStarted), syncRunnable.getTotalSyncDuration());
        }
        now = pollStarted + Integer.MAX_VALUE;
        Assert.assertTrue(syncRunnable.maybeLogFlushLag(pollStarted, now));
        Assert.assertEquals(now - pollStarted, syncRunnable.getTotalSyncDuration());
    }
}
