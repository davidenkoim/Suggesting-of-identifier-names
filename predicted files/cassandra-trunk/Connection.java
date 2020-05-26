// Type of training: selfTestingIdentifier
// Path to file: C:\Users\Igor\IdeaProjects\SLP-Core-0.3\java-projects\cassandra-trunk\test\burn\org\apache\cassandra\net\Connection.java
// Number of identifiers: 106	Accuracy: 68.87%	MRR: 74.72%
// True 	Rank of true in predictions : [(predicted token, probability of a token), ...]

package org.apache.cassandra.net;

import java.io.IOException;
import java.nio.channels.ClosedChannelException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.cassandra.io.util.DataInputPlus;
import org.apache.cassandra.io.util.DataOutputPlus;
import org.apache.cassandra.locator.InetAddressAndPort;
import org.apache.cassandra.net.Verifier.Destiny;
import static org.apache.cassandra.net.MessagingService.VERSION_40;
import static org.apache.cassandra.net.MessagingService.current_version;
import static org.apache.cassandra.utils.ExecutorUtils.runWithThreadName;
import static org.apache.cassandra.utils.MonotonicClock.approxTime;

public class Connection implements InboundMessageCallbacks, OutboundMessageCallbacks, OutboundDebugCallbacks {

    static class IntentionalIOException extends IOException {
    }

    static class IntentionalRuntimeException extends RuntimeException {
    }

    final InetAddressAndPort sender;

    final InetAddressAndPort recipient;

    final BytesInFlightController controller;

    final InboundMessageHandlers inbound;

    final OutboundConnection outbound;

    final OutboundConnectionSettings outboundTemplate;

    final Verifier verifier;

    final MessageGenerator sendGenerator;

    final String linkId;

    final long minId;

    final long maxId;

    final AtomicInteger isSending = new AtomicInteger();

    private volatile Runnable onSync;

    final Lock managementLock = new ReentrantLock();

    private final AtomicLong nextSendId = new AtomicLong();

    Connection(InetAddressAndPort sender, InetAddressAndPort recipient, ConnectionType type, InboundMessageHandlers inbound, OutboundConnectionSettings outboundTemplate, ResourceLimits.EndpointAndGlobal reserveCapacityInBytes, MessageGenerator generator, long minId, long maxId) {
// sender               0	: [('sender', 0.8750067706834744), ('endpoint', 0.2593915702701751), ('ep', 0.054185183582110115), ('from', 0.04852960330447806), ('address', 0.03363850975923544), ('peer', 0.03259519589275063), ('target', 0.027495434850667227), ('host', 0.018678692392282495), ('participant', 0.018356137761341543), ('addr', 0.015048234955516835)]
// recipient            No	: [('to', 0.3750926847057815), ('endpoint', 0.11968782268402246), ('peer', 0.07773936886432331), ('from', 0.03768470970497306), ('address', 0.03738808767219086), ('addr', 0.01898443842656092), ('initiator', 0.018369201855539467), ('destination', 0.014415129937063157), ('ep', 0.014328026064037474), ('participant', 0.012285788452462504)]
// type                 1	: [('settings', 0.8208637419967022), ('type', 0.5752596868686841), ('tag', 0.25576012199175047), ('connectionType', 0.08705392818470718), ('metadata', 0.08559383108613194), ('clientSuppliedName', 0.08525833449469454), ('zone_parts', 0.08524373400522271), ('Suffix', 0.08524357937267912), ('specifyConnection', 0.027448284701391722), ('forceConnection', 0.027447975436304534)]
// inbound              0	: [('inbound', 0.8984444335468829), ('endpoint', 0.6879099738206007), ('handlers', 0.2037132240010448), ('transformer', 0.031264985972485226), ('connection', 0.009273109267821), ('inboundHandlers', 0.009263135468759186), ('i', 0.0034624755134060104), ('version', 0.0015699980188979066), ('key', 0.00099757326448382), ('cfs', 0.0009877959567842994)]
// outboundTemplate     3	: [('outbound', 0.5127285300107226), ('settings', 0.5003336124471809), ('template', 0.04446418122618642), ('outboundTemplate', 0.02543718460878093), ('i', 0.0034708595146701214), ('cfs', 0.0033440421537281194), ('outboundSettings', 0.002719561356478512), ('newTemplate', 0.0027194840404936487), ('reconnectWith', 0.002719445382501217), ('restrictions', 0.002641079296779593)]
// reserveCapacityInBytes 0	: [('reserveCapacityInBytes', 0.5404065197201104), ('endpointAndGlobalPayloadsInFlight', 0.12247687886888572), ('i', 0.0069248939078036375), ('version', 0.003139965551709949), ('key', 0.0019951279961654056), ('cfs', 0.0019755746488713034), ('out', 0.0018640120528915702), ('e', 0.0018105312833209659), ('value1', 0.0017421988195202224), ('metadata', 0.0016803635534062397)]
// generator            1	: [('large', 0.2833387558613828), ('generator', 0.26669250061472005), ('small', 0.050005422528049466), ('get', 0.016954679119391802), ('i', 0.006941719029340243), ('cfs', 0.006688084307456239), ('restrictions', 0.005282158593559186), ('keyspace', 0.004880286816463754), ('sstable', 0.003939492185184901), ('row', 0.003551018157044237)]
// minId                No	: [('maxId', 0.25002545855546837), ('i', 0.10815908047018905), ('queryStartNanoTime', 0.051679528930615115), ('timestamp', 0.04837481998867718), ('timeElapsed', 0.027935273199509586), ('repairedAt', 0.026417683216057784), ('row', 0.023444480462255498), ('column', 0.017704178291024473), ('timeout', 0.014546048474220707), ('keyCount', 0.014312653024459632)]
// maxId                No	: [('queryStartNanoTime', 0.05170822753190804), ('timestamp', 0.048399327947980124), ('timeElapsed', 0.02795091814596725), ('repairedAt', 0.02643196883782037), ('timeout', 0.014552946540563468), ('keyCount', 0.014320666157740556), ('size', 0.013678416465555608), ('count', 0.012714157745795318), ('offset', 0.012551839282866175), ('now', 0.011339995702938309)]
        this.sender = sender;
        this.recipient = recipient;
        this.controller = new BytesInFlightController(1 << 20);
        this.sendGenerator = generator.copy();
        this.minId = minId;
        this.maxId = maxId;
        this.nextSendId.set(minId);
        this.linkId = sender.toString(false) + "->" + recipient.toString(false) + "-" + type;
        this.outboundTemplate = outboundTemplate.toEndpoint(recipient).withFrom(sender).withCallbacks(this).withDebugCallbacks(this);
        this.inbound = inbound;
        this.outbound = new OutboundConnection(type, this.outboundTemplate, reserveCapacityInBytes);
        this.verifier = new Verifier(controller, outbound, inbound);
    }

    void startVerifier(Runnable onFailure, Executor executor, long deadlineNanos) {
// onFailure            No	: [('command', 0.1436901058049558), ('runnable', 0.11651727108488359), ('r', 0.08842217825238378), ('task', 0.0860374228185725), ('cluster', 0.08278769831001988), ('run', 0.04871526390879566), ('probe', 0.03763927923071395), ('scanner', 0.033136327990347964), ('runFirst', 0.017411106282727518), ('hook', 0.017410951650757792)]
// executor             0	: [('executor', 0.7500280761116145), ('out', 0.002647597093245795), ('result', 0.0020913262068819128), ('builder', 0.0016751843439031613), ('sb', 0.0016723320790682974), ('size', 0.0011407723742472061), ('conf', 0.0010722086782985966), ('writer', 0.0008399004791167969), ('e', 0.0008353428629217426), ('reads', 0.0007773828456960972)]
// deadlineNanos        No	: [('delay', 0.5014319263773996), ('queryStartNanoTime', 0.025839764407060036), ('timestamp', 0.024187409862922993), ('timeElapsed', 0.013967636578378238), ('repairedAt', 0.013208841565562717), ('timeout', 0.007273024191774841), ('keyCount', 0.0071563264948703315), ('i', 0.0069248939078036375), ('size', 0.006836627097008699), ('count', 0.006354194486428395)]
        executor.execute(runWithThreadName(() -> verifier.run(onFailure, deadlineNanos), "Verify-" + linkId));
    }

    boolean isSending() {
        return isSending.get() > 0;
    }

    boolean registerSender() {
        return isSending.updateAndGet(i -> i < 0 ? i : i + 1) > 0;
// i                    0	: [('i', 0.8814398466285445), ('cur', 0.02980163424639724), ('slice', 0.020294894553270576), ('prev', 0.017903186876398747), ('a', 0.011082645303483822), ('cell', 0.010189219481212588), ('right1', 0.01015863034678245), ('rhsLenDiff', 0.010143234173103794), ('index', 0.0069166564645081075), ('beforeMap', 0.006654687688303346)]
    }

    void unregisterSender() {
        if (isSending.updateAndGet(i -> i < 0 ? i + 1 : i - 1) == -1) {
// i                    0	: [('i', 0.8814398466285445), ('current', 0.14691113315486323), ('maximumLevel', 0.07522996854737042), ('upperPosition', 0.07335104246483745), ('cur', 0.029801634410192963), ('o2', 0.022806965057959275), ('slice', 0.01962154571181829), ('prev', 0.017903187060457862), ('comparator', 0.011577155675769994), ('c1', 0.011449885599708054)]
            Runnable onSync = this.onSync;
// onSync               No	: [('runnable', 0.5528595745095779), ('r', 0.1069653927255659), ('task', 0.10401245162837858), ('file', 0.10288192488198629), ('start', 0.06859088133206304), ('cdl', 0.06856381096576161), ('command', 0.03429750580819649), ('activeCompactions', 0.03429615454066165), ('copy', 0.03428950018646373), ('time', 0.0342851823983087)]
            this.onSync = null;
            verifier.onSync(() -> {
                onSync.run();
                isSending.set(0);
            });
        }
    }

    boolean setInFlightByteBounds(long minBytes, long maxBytes) {
// minBytes             4	: [('previousMin', 0.6190492378317576), ('minimumInFlightBytes', 0.5003500092854085), ('min', 0.11911315729410463), ('random', 0.05955717624195224), ('minBytes', 0.05952506478393038), ('timestamp', 0.014376013799997268), ('timeout', 0.013051302094774717), ('position', 0.00986614392198881), ('i', 0.009298510173660143), ('value', 0.008624430737465806)]
// maxBytes             No	: [('queryStartNanoTime', 0.05168035933732115), ('timestamp', 0.04837629024045497), ('timeElapsed', 0.027935683532926425), ('repairedAt', 0.02641822052849084), ('timeout', 0.014546629749790443), ('keyCount', 0.014312863063256232), ('i', 0.013849689074596805), ('size', 0.01367414334238562), ('count', 0.012708955682805764), ('offset', 0.0125462656439488)]
        if (managementLock.tryLock()) {
            try {
                if (isSending.get() >= 0) {
                    controller.setInFlightByteBounds(minBytes, maxBytes);
                    return true;
                }
            } finally {
                managementLock.unlock();
            }
        }
        return false;
    }

    void sync(Runnable onCompletion) {
// onCompletion         No	: [('run', 0.5243576706123902), ('command', 0.0718450529024779), ('runnable', 0.05825863554244179), ('r', 0.04421108912619189), ('task', 0.04301871140928625), ('i', 0.03306640585763547), ('onFailure', 0.008707099461061027), ('runFirst', 0.008705553141363759), ('hook', 0.008705475825378896), ('callback', 0.007896903853780451)]
        managementLock.lock();
        try {
            assert onSync == null;
            assert isSending.get() >= 0;
            isSending.updateAndGet(i -> -2 - i);
// i                    0	: [('i', 0.7222346307553453), ('boundary', 0.15053048670582983), ('maxSendQueueCapacity', 0.050118456966504694), ('cur', 0.0276038318304269), ('prev', 0.016712710429557815), ('start', 0.0055226653509393925), ('offset', 0.0017892295073702684), ('count', 0.001400742485436019), ('index', 0.0011736967496057337), ('size', 0.001142380102596586)]
            long previousMin = controller.minimumInFlightBytes();
// previousMin          7	: [('minBytes', 0.6145849521140947), ('timestamp', 0.21534046464336695), ('min', 0.1146488713130204), ('seed', 0.1047387694161196), ('readCount1', 0.1003550752108391), ('rw', 0.10034624219459719), ('random', 0.05732503326323031), ('previousMin', 0.05729292192509893), ('u', 0.02146702471319097), ('size', 0.009232071813788392)]
            long previousMax = controller.maximumInFlightBytes();
// previousMax          0	: [('previousMax', 0.5000061976778916), ('timestamp', 0.03026583777769529), ('unconfirmedCount', 0.022110900119995218), ('now', 0.01842904017828013), ('repairedAt', 0.01620008506340058), ('oldWarnings', 0.015338701850860656), ('size', 0.014829086739161525), ('seed', 0.014067127625074827), ('start', 0.013067881638240436), ('startTime', 0.012735778901355209)]
            controller.setInFlightByteBounds(0, Long.MAX_VALUE);
            onSync = () -> {
                long inFlight = controller.inFlight();
// inFlight             No	: [('repairExceptions', 0.18964498378518457), ('now', 0.11821858323156538), ('start', 0.09649698745873281), ('count', 0.0960765929181066), ('delay', 0.0948448456907894), ('lastTimestamp', 0.09474217878792263), ('start1', 0.09473770429241848), ('size', 0.06558792327419083), ('using', 0.029964625002158157), ('i', 0.0162042918561357)]
                if (inFlight != 0)
                    verifier.logFailure("%s has %d bytes in flight, but connection is idle", linkId, inFlight);
                controller.setInFlightByteBounds(previousMin, previousMax);
                onCompletion.run();
            };
            unregisterSender();
        } finally {
            managementLock.unlock();
        }
    }

    void sendOne() throws InterruptedException {
        long id = nextSendId.getAndUpdate(i -> i == maxId ? minId : i + 1);
// i                    0	: [('i', 0.7629095728657209), ('sstables', 0.013340372583005885), ('beforeMap', 0.013309316377753223), ('afterMap', 0.013309277719617324), ('excludes', 0.013218252754635205), ('cfs', 0.007895119968919182), ('result', 0.007435255800371257), ('values', 0.004758586679858707), ('ranges', 0.0043730022491632594), ('row', 0.004294981947676291)]
// id                   7	: [('nodeNum', 0.3357227285623929), ('timeout', 0.23004209328219627), ('start', 0.21015613528375296), ('chunkSequenceId', 0.11509292436206567), ('now', 0.10615717970206251), ('startNano', 0.10429765563511503), ('sentBytes', 0.10423027247583724), ('id', 0.08414193766388331), ('pid', 0.01080004412264021), ('size', 0.005938800327221423)]
        try {
            Destiny destiny = Destiny.SUCCEED;
// destiny              0	: [('destiny', 0.35715231051983654), ('ranges', 0.29247984488907885), ('chrList', 0.2708483572021144), ('intervals', 0.08380383216339346), ('c1', 0.0831971616629289), ('out', 0.05285615503749398), ('cmd12', 0.041728925783441326), ('c2', 0.04164096362132004), ('version', 0.03433735599364988), ('cause', 0.03125434734538918)]
            byte realDestiny = 0;
// realDestiny          No	: [('destiny', 0.5000180840403893), ('pvt', 0.2504117817755649), ('verb', 0.09890406820286117), ('txn', 0.08805054439064913), ('clock', 0.07737400061094868), ('peer', 0.04705988462891578), ('creationTime', 0.038037696215496714), ('priority', 0.03802740621448998), ('b', 0.023023374385995474), ('version', 0.018748316631863795)]
            Message<?> msg;
// msg                  1	: [('message', 0.8690726626466414), ('msg', 0.7502636802732223), ('reply', 0.09415279895362545), ('response', 0.07073509348298855), ('next', 0.05280188846924487), ('ignored', 0.05259436291401991), ('builder', 0.04061421943166318), ('messageOut', 0.023543249920773834), ('gDigestAckMessage', 0.023541834474649862), ('useSameMessageID', 0.023540303053257383)]
            synchronized (sendGenerator) {
                if (0 == sendGenerator.uniformInt(1 << 10)) {
                    realDestiny = (byte) (1 + sendGenerator.uniformInt(6));
                    destiny = realDestiny <= 3 ? Destiny.FAIL_TO_SERIALIZE : Destiny.FAIL_TO_DESERIALIZE;
                }
                msg = sendGenerator.generate(id, realDestiny);
            }
            controller.send(msg.serializedSize(current_version));
            Verifier.EnqueueMessageEvent e = verifier.onEnqueue(msg, destiny);
// e                    0	: [('e', 0.12568937013926), ('enqueue', 0.12501038116018973), ('success', 0.012685511838044252), ('i', 0.008266693272243093), ('builder', 0.001640329243970146), ('out', 0.0016349754368950114), ('cfs', 0.0015446183487444036), ('sb', 0.0010804433322614923), ('bounds', 0.001048969572434638), ('restrictions', 0.0008199848203790517)]
            outbound.enqueue(msg);
            e.complete(verifier);
        } catch (ClosedChannelException e) {
// e                    0	: [('e', 0.6839028599756362), ('message', 0.050227802729397905), ('msg', 0.01676463356499491), ('columns', 0.016720714115897914), ('onInstance', 0.016699179091026754), ('i', 0.0008610996640773172), ('cfs', 0.00045615316684532406), ('range', 0.00023366972447519678), ('value', 0.00022939920430670128), ('key', 0.00021633154132143646)]
            throw new IllegalStateException(e);
        }
    }

    void reconnectWith(OutboundConnectionSettings template) {
// template             0	: [('template', 0.6250296730607882), ('reconnectWith', 0.5235527013998497), ('settings', 0.031271576364331394), ('outbound', 0.023561824686063566), ('outboundTemplate', 0.006795996234932137), ('i', 0.0034443986563092686), ('outboundSettings', 0.002719561356478512), ('newTemplate', 0.0027194840404936487), ('cfs', 0.0018246126673812963), ('value', 0.0009175968172268051)]
        outbound.reconnectWith(template);
    }

    void serialize(long id, byte[] payload, DataOutputPlus out, int messagingVersion) throws IOException {
// id                   1	: [('childBlockIndex', 0.7507060217516575), ('id', 0.6252119187783187), ('header', 0.5021652078316117), ('in', 0.24262691803469263), ('val', 0.16099671622476286), ('value', 0.05240436809835704), ('crc', 0.04767305257886), ('address', 0.04021435425317717), ('bytes', 0.03929316190680471), ('header8b', 0.03919337523020617)]
// payload              1	: [('function', 0.7500868144407118), ('payload', 0.625037338353625), ('header', 0.5025845081662024), ('buffer', 0.18271938125908496), ('utfBytes', 0.16254413182373623), ('inputBuffer', 0.13438946750790906), ('oneByte', 0.13438608940967922), ('bytes', 0.11297810017614629), ('buf', 0.08445387800652174), ('output', 0.0827414379312287)]
// out                  0	: [('out', 0.9680625877115137), ('message', 0.545753674310133), ('payloadOffset', 0.2934936621721905), ('version', 0.05954099815265435), ('result', 0.05609223885366778), ('id', 0.05274871648982168), ('body', 0.052592364633452654), ('sb', 0.027418853563395247), ('msg', 0.021958237745616282), ('writer', 0.019266849564504803)]
// messagingVersion     No	: [('version', 0.8723995720951242), ('verb', 0.09890407075878242), ('txn', 0.08805056520315076), ('clock', 0.07737400175850516), ('peer', 0.04705988916698005), ('creationTime', 0.0380376971022449), ('priority', 0.038027406736106564), ('buffer', 0.016832559998108385), ('btree', 0.015964545851932885), ('keyspaceName', 0.015543679536165998)]
        verifier.onSerialize(id, messagingVersion);
        int firstWrite = payload.length, remainder = 0;
// firstWrite           No	: [('remainder', 0.7508655272621965), ('paramType', 0.501089562139501), ('length', 0.33612547013285154), ('maxMutationSize', 0.33342931996748615), ('mi', 0.08348570816553032), ('i', 0.02721146962621628), ('value1', 0.016173271456248493), ('key', 0.01387106884009483), ('tmd', 0.011676293631191704), ('size', 0.010990262481986628)]
// remainder            No	: [('firstWrite', 0.7508561862363765), ('out', 0.1543483544775285), ('sb', 0.1540093589560185), ('dest', 0.15289394678004978), ('newSlices', 0.1528064223031727), ('iter', 0.07724112562028548), ('i', 0.07656511246140593), ('count', 0.05306772116897623), ('tokenizer', 0.04983391777934611), ('j', 0.04928808488596756)]
        boolean willFail = false;
// willFail             No	: [('count', 0.6094401616463379), ('frameDecoder', 0.527787039417998), ('file', 0.3145337618423413), ('inSlice', 0.11045064368593915), ('builder', 0.08605270511315957), ('k1', 0.08597823500617738), ('h64', 0.08597315416511025), ('consumer', 0.06452039843418168), ('enforceStrictLiveness', 0.05683262089913706), ('isStatic', 0.056351746833557835)]
        if (outbound.type() != ConnectionType.LARGE_MESSAGES || messagingVersion >= VERSION_40) {
            willFail = outbound.type() != ConnectionType.LARGE_MESSAGES;
            byte info = MessageGenerator.getInfo(payload);
// info                 No	: [('type', 0.11466315841829758), ('column', 0.02934565824555569), ('kind', 0.028814521973592102), ('in', 0.02576912800525304), ('m', 0.025529620362774716), ('expression', 0.025447679679364482), ('err', 0.02543287190885309), ('limits', 0.02542939877473), ('ident', 0.02542435694931555), ('aggregationSpec', 0.025424167387952407)]
            switch(info) {
                case 1:
                    switch((int) (id & 1)) {
                        case 0:
                            throw new IntentionalIOException();
                        case 1:
                            throw new IntentionalRuntimeException();
                    }
                    break;
                case 2:
                    willFail = true;
                    firstWrite -= (int) id % payload.length;
                    break;
                case 3:
                    willFail = true;
                    remainder = (int) id & 65535;
                    break;
            }
        }
        MessageGenerator.writeLength(payload, out, messagingVersion);
        out.write(payload, 0, firstWrite);
        while (remainder > 0) {
            out.write(payload, 0, Math.min(remainder, payload.length));
            remainder -= payload.length;
        }
        if (!willFail)
            verifier.onFinishSerializeLarge(id);
    }

    byte[] deserialize(MessageGenerator.Header header, DataInputPlus in, int messagingVersion) throws IOException {
// header               0	: [('header', 0.8178903856465058), ('id', 0.5032026011525093), ('payload', 0.50249474215315), ('cluster', 0.4392299279295506), ('size', 0.30625076470099144), ('buffer', 0.19067004172388505), ('bytes', 0.18763252029198085), ('val', 0.16099671669080728), ('btree', 0.14750928187039192), ('charRunLength', 0.1457524295890461)]
// in                   0	: [('in', 0.6998055053920376), ('header6b', 0.283352827243057), ('dest', 0.2668289468883584), ('reader', 0.061646248038511806), ('b', 0.05409228927870176), ('buffer', 0.034411362065271116), ('sb', 0.02903968394619098), ('r', 0.017263752541071497), ('action', 0.016693803339088795), ('prefix', 0.01668161784315007)]
// messagingVersion     No	: [('version', 0.8584660529727406), ('messageSize', 0.5690663535438945), ('cause', 0.28592216675700605), ('other', 0.2858889747971913), ('header', 0.18926289290272147), ('txn', 0.10709117529568873), ('i', 0.07432294616941593), ('size', 0.007882467903828391), ('length', 0.006390836972411764), ('out', 0.0043276255091917355)]
        verifier.onDeserialize(header.id, messagingVersion);
        int length = header.length;
// length               7	: [('count', 0.5181214676125855), ('size', 0.5081337512837414), ('firstWrite', 0.333524744760471), ('maxMutationSize', 0.33342931996942304), ('in', 0.1801237076041607), ('buckets', 0.17990501060412886), ('memory', 0.17986588371536125), ('length', 0.1442227895218538), ('bytes', 0.09048917660082045), ('mi', 0.08348570817413854)]
        switch(header.info) {
            case 4:
                switch((int) (header.id & 1)) {
                    case 0:
                        throw new IntentionalIOException();
                    case 1:
                        throw new IntentionalRuntimeException();
                }
                break;
            case 5:
                {
                    length -= (int) header.id % header.length;
                    break;
                }
            case 6:
                {
                    length += (int) header.id & 65535;
                    break;
                }
        }
        byte[] result = header.read(in, Math.min(header.length, length), messagingVersion);
// result               7	: [('bytes', 0.5689398766491323), ('size', 0.09197206272609884), ('failure', 0.050418451674144604), ('count', 0.031218857531524553), ('buffer', 0.029863216723648257), ('builder', 0.0294550177624772), ('rate', 0.0284120908271959), ('result', 0.028402808160117872), ('sb', 0.02803959073416601), ('key', 0.02774976979456393)]
        if (length > header.length) {
            length -= header.length;
            while (length >= 8) {
                in.readLong();
                length -= 8;
            }
            while (length-- > 0) in.readByte();
        }
        return result;
    }

    public void process(Message message) {
// message              3	: [('query', 0.2001363303056309), ('cql', 0.19606704085264992), ('iter', 0.0755587508960481), ('message', 0.06679366119367071), ('msg', 0.06507088130442654), ('bytes', 0.03055061704081433), ('format', 0.0303181195886231), ('queryString', 0.03015466035636951), ('defaultSUQuery', 0.03014183664385911), ('frame', 0.015149506163947547)]
        verifier.process(message);
    }

    public void onHeaderArrived(int messageSize, Message.Header header, long timeElapsed, TimeUnit unit) {
// messageSize          0	: [('messageSize', 0.8758846351051408), ('i', 0.03723252102319094), ('j', 0.00340544865881143), ('size', 0.0021414984319692693), ('version', 0.0013832227508245312), ('pageSize', 0.0013307292133497677), ('index', 0.0011063798182237897), ('count', 0.0010950815459402276), ('nowInSec', 0.0010296353248035013), ('idx', 0.0009569645493206113)]
// header               0	: [('header', 0.9501623670233726), ('headerOut', 0.005535234893702886), ('range', 1.4604305235371078e-05), ('expected', 1.2033558279135476e-05), ('a', 1.0342277386875209e-05), ('protocolVersion', 9.723751803420026e-06), ('partitioner', 7.124011460459961e-06), ('strategy', 6.476492490280317e-06), ('rs', 5.90628921803257e-06), ('results', 5.645348737512415e-06)]
// timeElapsed          0	: [('timeElapsed', 0.8561280161851271), ('previousUnfilteredSize', 0.023001526845227396), ('queryStartNanoTime', 0.006459941087203235), ('timestamp', 0.006046852432877093), ('repairedAt', 0.003302210380774214), ('timeout', 0.0018182560366099166), ('keyCount', 0.001789081619377744), ('size', 0.0017091566343013414), ('count', 0.0015885485660499894), ('offset', 0.0015682251531303323)]
// unit                 0	: [('unit', 0.6076658557565269), ('timeUnit', 0.3374672823798429), ('units', 0.004962099936492778), ('rateUnit', 0.0013361078923409392), ('durationUnits', 0.0008909290969142033), ('tsUnit', 0.0008909194324519618), ('timeoutUnit', 0.0008909001035274788), ('sstableWindowUnit', 0.0008909001035274788), ('delayUnit', 0.0008908711101407544), ('precision', 0.0005475804902810588)]
    }

    public void onArrived(int messageSize, Message.Header header, long timeElapsed, TimeUnit unit) {
// messageSize          0	: [('messageSize', 0.8758846254506495), ('header', 0.1892628883026229), ('txn', 0.10709117005605102), ('messagingVersion', 0.09475658065325233), ('i', 0.03723252199162058), ('out', 0.00432761707023726), ('update', 0.004283886179662732), ('j', 0.0034054487408738355), ('max', 0.003310056439308537), ('peer', 0.002657204982201005)]
// header               0	: [('header', 0.9501623574078681), ('messageSize', 0.6111841049298233), ('headerOut', 0.005535234893810486), ('i', 0.0034443861877003616), ('cfs', 0.0018246043425923049), ('value', 0.0009175928963018482), ('e', 0.0008923192208229398), ('key', 0.0008653223997118207), ('sstable', 0.0008083660540239508), ('name', 0.0007884666143086228)]
// timeElapsed          0	: [('timeElapsed', 0.8561280161851271), ('previousUnfilteredSize', 0.023001526845227396), ('queryStartNanoTime', 0.006459941087203235), ('timestamp', 0.006046852432877093), ('repairedAt', 0.003302210380774214), ('timeout', 0.0018182560366099166), ('keyCount', 0.001789081619377744), ('size', 0.0017091566343013414), ('count', 0.0015885485660499894), ('offset', 0.0015682251531303323)]
// unit                 0	: [('unit', 0.6076658557565269), ('timeUnit', 0.3374672823798429), ('units', 0.004962099936492778), ('rateUnit', 0.0013361078923409392), ('durationUnits', 0.0008909290969142033), ('tsUnit', 0.0008909194324519618), ('timeoutUnit', 0.0008909001035274788), ('sstableWindowUnit', 0.0008909001035274788), ('delayUnit', 0.0008908711101407544), ('precision', 0.0005475804902810588)]
        verifier.onArrived(header.id, messageSize);
    }

    public void onArrivedExpired(int messageSize, Message.Header header, boolean wasCorrupt, long timeElapsed, TimeUnit timeUnit) {
// messageSize          0	: [('messageSize', 0.8758846157960865), ('message', 0.2916577441006646), ('header', 0.1892628883516168), ('txn', 0.10709117009256333), ('messagingVersion', 0.09475658066182448), ('payloadSizeInBytes', 0.0881320364481146), ('v', 0.0513431126261088), ('i', 0.037232522960057406), ('out', 0.004327617176366832), ('update', 0.004283886214345833)]
// header               0	: [('header', 0.9501623574078681), ('messageSize', 0.6111841049298233), ('headerOut', 0.005535234893810486), ('i', 0.0034443861877003616), ('cfs', 0.0018246043425923049), ('value', 0.0009175928963018482), ('e', 0.0008923192208229398), ('key', 0.0008653223997118207), ('sstable', 0.0008083660540239508), ('name', 0.0007884666143086228)]
// wasCorrupt           1	: [('timeElapsed', 0.8336760528008859), ('wasCorrupt', 0.7506418467614654), ('skipHeader', 0.08340924381502073), ('header', 0.022248936491457216), ('frozen', 0.021655633602732184), ('hasComplexDeletion', 0.021095034037418043), ('out', 0.00859859885174419), ('message', 0.008441472502162081), ('bytesReceived', 0.008368432131101809), ('established', 0.008368035376140864)]
// timeElapsed          0	: [('timeElapsed', 0.8784494350973658), ('wasExpired', 0.1761951224484393), ('queryStartNanoTime', 0.006459941101765009), ('timestamp', 0.006046852465730748), ('i', 0.0034624963259198786), ('repairedAt', 0.0033022103913906793), ('timeout', 0.0018182560479437103), ('keyCount', 0.0017890816237175829), ('size', 0.0017091567742521748), ('count', 0.0015885486216070987)]
// timeUnit             1	: [('unit', 0.838947412370356), ('timeUnit', 0.3153757234660219), ('units', 0.004962099939146894), ('rateUnit', 0.0013361078925920042), ('durationUnits', 0.0008909290972370013), ('tsUnit', 0.0008909194327388933), ('sstableWindowUnit', 0.0008909001037426776), ('timeoutUnit', 0.0008909001037426776), ('delayUnit', 0.0008908711102483537), ('i', 0.0008656240814799696)]
        controller.fail(messageSize);
        verifier.onArrivedExpired(header.id, messageSize, wasCorrupt, timeElapsed, timeUnit);
    }

    public void onArrivedCorrupt(int messageSize, Message.Header header, long timeElapsed, TimeUnit unit) {
// messageSize          0	: [('messageSize', 0.8758846157960865), ('message', 0.2916577441006646), ('header', 0.1892628883516168), ('txn', 0.10709117009256333), ('messagingVersion', 0.09475658066182448), ('payloadSizeInBytes', 0.0881320364481146), ('v', 0.0513431126261088), ('i', 0.037232522960057406), ('out', 0.004327617176366832), ('update', 0.004283886214345833)]
// header               0	: [('header', 0.9501623574078681), ('messageSize', 0.12501824845467807), ('headerOut', 0.005535234893810486), ('i', 0.0008610965469250904), ('cfs', 0.0004561510856480762), ('value', 0.00022939822407546203), ('e', 0.00022307980520573496), ('key', 0.00021633059992795517), ('sstable', 0.0002020915135059877), ('name', 0.0001971166535771557)]
// timeElapsed          0	: [('timeElapsed', 0.8561280161851271), ('previousUnfilteredSize', 0.023001526845227396), ('queryStartNanoTime', 0.006459941087203235), ('timestamp', 0.006046852432877093), ('repairedAt', 0.003302210380774214), ('timeout', 0.0018182560366099166), ('keyCount', 0.001789081619377744), ('size', 0.0017091566343013414), ('count', 0.0015885485660499894), ('offset', 0.0015682251531303323)]
// unit                 0	: [('unit', 0.6076658557565269), ('timeUnit', 0.3374672823798429), ('units', 0.004962099936492778), ('rateUnit', 0.0013361078923409392), ('durationUnits', 0.0008909290969142033), ('tsUnit', 0.0008909194324519618), ('timeoutUnit', 0.0008909001035274788), ('sstableWindowUnit', 0.0008909001035274788), ('delayUnit', 0.0008908711101407544), ('precision', 0.0005475804902810588)]
        controller.fail(messageSize);
        verifier.onFailedDeserialize(header.id, messageSize);
    }

    public void onClosedBeforeArrival(int messageSize, Message.Header header, int bytesReceived, boolean wasCorrupt, boolean wasExpired) {
// messageSize          0	: [('messageSize', 0.8758846157960865), ('message', 0.2916577441006646), ('header', 0.1892628883516168), ('txn', 0.10709117009256333), ('messagingVersion', 0.09475658066182448), ('payloadSizeInBytes', 0.0881320364481146), ('v', 0.0513431126261088), ('i', 0.037232522960057406), ('out', 0.004327617176366832), ('update', 0.004283886214345833)]
// header               0	: [('header', 0.9501623574078681), ('messageSize', 0.6111841049298233), ('headerOut', 0.005535234893810486), ('i', 0.0034443861877003616), ('cfs', 0.0018246043425923049), ('value', 0.0009175928963018482), ('e', 0.0008923192208229398), ('key', 0.0008653223997118207), ('sstable', 0.0008083660540239508), ('name', 0.0007884666143086228)]
// bytesReceived        0	: [('bytesReceived', 0.6227243583695788), ('version', 0.242838642505488), ('i', 0.02899876645082199), ('nowInSec', 0.0038814761293341324), ('size', 0.002146898598830801), ('length', 0.0017245104968257844), ('count', 0.0014859080517471588), ('offset', 0.0014469616034733907), ('index', 0.001123522629804357), ('end', 0.0010356027417600226)]
// wasCorrupt           0	: [('wasCorrupt', 0.8756418564248876), ('reversed', 0.0031760976203949916), ('isTransient', 0.002662878841190911), ('inclusive', 0.002046894082824501), ('equals', 0.0014862276732647657), ('skipCorrupted', 0.0012310526956083622), ('countPartitionsWithOnlyStaticData', 0.0012193743773774115), ('isIncremental', 0.0011877566669205985), ('isStart', 0.0011090098911550601), ('isInclusive', 0.0010741682257070376)]
// wasExpired           0	: [('wasExpired', 0.8751514350123226), ('reversed', 0.0031774135489693153), ('isTransient', 0.0026640898054818836), ('inclusive', 0.002047824584295548), ('skipCorrupted', 0.001231613621248214), ('countPartitionsWithOnlyStaticData', 0.001219933167191746), ('isIncremental', 0.0011882991292105412), ('isStart', 0.0011094856666016622), ('isInclusive', 0.0010746375936770884), ('checkData', 0.0010428554458693671)]
        controller.fail(messageSize);
        verifier.onClosedBeforeArrival(header.id, messageSize);
    }

    public void onFailedDeserialize(int messageSize, Message.Header header, Throwable t) {
// messageSize          0	: [('messageSize', 0.8758846157960865), ('message', 0.2916577441006646), ('header', 0.1892628883516168), ('txn', 0.10709117009256333), ('messagingVersion', 0.09475658066182448), ('payloadSizeInBytes', 0.0881320364481146), ('v', 0.0513431126261088), ('i', 0.037232522960057406), ('out', 0.004327617176366832), ('update', 0.004283886214345833)]
// header               0	: [('header', 0.9501623574078681), ('messageSize', 0.12501824845467807), ('headerOut', 0.005535234893810486), ('i', 0.0008610965469250904), ('cfs', 0.0004561510856480762), ('value', 0.00022939822407546203), ('e', 0.00022307980520573496), ('key', 0.00021633059992795517), ('sstable', 0.0002020915135059877), ('name', 0.0001971166535771557)]
// t                    1	: [('failure', 0.5687166963812282), ('t', 0.33285662004055616), ('cause', 0.01823605982218229), ('accumulate', 0.013688754880809341), ('e', 0.0071543809786922515), ('th', 0.003710497308330313), ('throwable', 0.0024996952261517277), ('error', 0.002208682056704529), ('failureException', 0.0020372500843828705), ('failsWith', 0.0013584023257659512)]
        controller.fail(messageSize);
        verifier.onFailedDeserialize(header.id, messageSize);
    }

    public void onDispatched(int messageSize, Message.Header header) {
// messageSize          0	: [('messageSize', 0.8758846351051408), ('i', 0.03723252102319094), ('j', 0.00340544865881143), ('size', 0.0021414984319692693), ('version', 0.0013832227508245312), ('pageSize', 0.0013307292133497677), ('index', 0.0011063798182237897), ('count', 0.0010950815459402276), ('nowInSec', 0.0010296353248035013), ('idx', 0.0009569645493206113)]
// header               0	: [('header', 0.9501623670233726), ('headerOut', 0.005535234893702886), ('range', 1.4604305235371078e-05), ('expected', 1.2033558279135476e-05), ('a', 1.0342277386875209e-05), ('protocolVersion', 9.723751803420026e-06), ('partitioner', 7.124011460459961e-06), ('strategy', 6.476492490280317e-06), ('rs', 5.90628921803257e-06), ('results', 5.645348737512415e-06)]
    }

    public void onExecuting(int messageSize, Message.Header header, long timeElapsed, TimeUnit unit) {
// messageSize          0	: [('messageSize', 0.8758846351051408), ('i', 0.03723252102319094), ('j', 0.00340544865881143), ('size', 0.0021414984319692693), ('version', 0.0013832227508245312), ('pageSize', 0.0013307292133497677), ('index', 0.0011063798182237897), ('count', 0.0010950815459402276), ('nowInSec', 0.0010296353248035013), ('idx', 0.0009569645493206113)]
// header               0	: [('header', 0.9501623670233726), ('headerOut', 0.005535234893702886), ('range', 1.4604305235371078e-05), ('expected', 1.2033558279135476e-05), ('a', 1.0342277386875209e-05), ('protocolVersion', 9.723751803420026e-06), ('partitioner', 7.124011460459961e-06), ('strategy', 6.476492490280317e-06), ('rs', 5.90628921803257e-06), ('results', 5.645348737512415e-06)]
// timeElapsed          0	: [('timeElapsed', 0.8561280161851271), ('previousUnfilteredSize', 0.023001526845227396), ('queryStartNanoTime', 0.006459941087203235), ('timestamp', 0.006046852432877093), ('repairedAt', 0.003302210380774214), ('timeout', 0.0018182560366099166), ('keyCount', 0.001789081619377744), ('size', 0.0017091566343013414), ('count', 0.0015885485660499894), ('offset', 0.0015682251531303323)]
// unit                 0	: [('unit', 0.6076658557565269), ('timeUnit', 0.3374672823798429), ('units', 0.004962099936492778), ('rateUnit', 0.0013361078923409392), ('durationUnits', 0.0008909290969142033), ('tsUnit', 0.0008909194324519618), ('timeoutUnit', 0.0008909001035274788), ('sstableWindowUnit', 0.0008909001035274788), ('delayUnit', 0.0008908711101407544), ('precision', 0.0005475804902810588)]
    }

    public void onProcessed(int messageSize, Message.Header header) {
// messageSize          0	: [('messageSize', 0.8758846351051408), ('i', 0.03723252102319094), ('j', 0.00340544865881143), ('size', 0.0021414984319692693), ('version', 0.0013832227508245312), ('pageSize', 0.0013307292133497677), ('index', 0.0011063798182237897), ('count', 0.0010950815459402276), ('nowInSec', 0.0010296353248035013), ('idx', 0.0009569645493206113)]
// header               0	: [('header', 0.9501623670233726), ('headerOut', 0.005535234893702886), ('range', 1.4604305235371078e-05), ('expected', 1.2033558279135476e-05), ('a', 1.0342277386875209e-05), ('protocolVersion', 9.723751803420026e-06), ('partitioner', 7.124011460459961e-06), ('strategy', 6.476492490280317e-06), ('rs', 5.90628921803257e-06), ('results', 5.645348737512415e-06)]
    }

    public void onExpired(int messageSize, Message.Header header, long timeElapsed, TimeUnit timeUnit) {
// messageSize          0	: [('messageSize', 0.8758846157960865), ('message', 0.2916577441006646), ('header', 0.1892628883516168), ('txn', 0.10709117009256333), ('messagingVersion', 0.09475658066182448), ('payloadSizeInBytes', 0.0881320364481146), ('v', 0.0513431126261088), ('i', 0.037232522960057406), ('out', 0.004327617176366832), ('update', 0.004283886214345833)]
// header               0	: [('header', 0.9501623574078681), ('i', 0.006888772375400723), ('headerOut', 0.005535234893810486), ('cfs', 0.0036492086851846093), ('value', 0.001835185792603696), ('e', 0.0017846384416458799), ('key', 0.0017306447994236411), ('sstable', 0.0016167321080479016), ('name', 0.0015769332286172456), ('metadata', 0.0015118300700999751)]
// timeElapsed          0	: [('timeElapsed', 0.8561280065259373), ('wasCorrupt', 0.7667360895459471), ('previousUnfilteredSize', 0.023001526846626187), ('header', 0.022248936491457216), ('out', 0.00859859885174419), ('message', 0.008441472502162081), ('bytesReceived', 0.008368432131101809), ('established', 0.008368035376140864), ('sending', 0.00836727137025182), ('queryStartNanoTime', 0.006459941101765009)]
// timeUnit             0	: [('timeUnit', 0.813016143795473), ('unit', 0.6297574050270469), ('units', 0.004962099939146894), ('rateUnit', 0.0013361078925920042), ('durationUnits', 0.0008909290972370013), ('tsUnit', 0.0008909194327388933), ('sstableWindowUnit', 0.0008909001037426776), ('timeoutUnit', 0.0008909001037426776), ('delayUnit', 0.0008908711102483537), ('i', 0.0008656240814799696)]
        controller.fail(messageSize);
        verifier.onProcessExpired(header.id, messageSize, timeElapsed, timeUnit);
    }

    public void onExecuted(int messageSize, Message.Header header, long timeElapsed, TimeUnit unit) {
// messageSize          0	: [('messageSize', 0.8758846351051408), ('i', 0.03723252102319094), ('j', 0.00340544865881143), ('size', 0.0021414984319692693), ('version', 0.0013832227508245312), ('pageSize', 0.0013307292133497677), ('index', 0.0011063798182237897), ('count', 0.0010950815459402276), ('nowInSec', 0.0010296353248035013), ('idx', 0.0009569645493206113)]
// header               0	: [('header', 0.9501623670233726), ('headerOut', 0.005535234893702886), ('range', 1.4604305235371078e-05), ('expected', 1.2033558279135476e-05), ('a', 1.0342277386875209e-05), ('protocolVersion', 9.723751803420026e-06), ('partitioner', 7.124011460459961e-06), ('strategy', 6.476492490280317e-06), ('rs', 5.90628921803257e-06), ('results', 5.645348737512415e-06)]
// timeElapsed          0	: [('timeElapsed', 0.8561280161851271), ('previousUnfilteredSize', 0.023001526845227396), ('queryStartNanoTime', 0.006459941087203235), ('timestamp', 0.006046852432877093), ('repairedAt', 0.003302210380774214), ('timeout', 0.0018182560366099166), ('keyCount', 0.001789081619377744), ('size', 0.0017091566343013414), ('count', 0.0015885485660499894), ('offset', 0.0015682251531303323)]
// unit                 0	: [('unit', 0.6076658557565269), ('timeUnit', 0.3374672823798429), ('units', 0.004962099936492778), ('rateUnit', 0.0013361078923409392), ('durationUnits', 0.0008909290969142033), ('tsUnit', 0.0008909194324519618), ('timeoutUnit', 0.0008909001035274788), ('sstableWindowUnit', 0.0008909001035274788), ('delayUnit', 0.0008908711101407544), ('precision', 0.0005475804902810588)]
    }

    InboundCounters inboundCounters() {
        return inbound.countersFor(outbound.type());
    }

    public void onSendSmallFrame(int messageCount, int payloadSizeInBytes) {
// messageCount         0	: [('messageCount', 0.8753045751443238), ('i', 0.03723252199162058), ('j', 0.0034054487408738355), ('size', 0.0021414985719201028), ('cfs', 0.0018246126673812963), ('version', 0.0013832228613290204), ('pageSize', 0.0013307292421863824), ('index', 0.0011063798791967308), ('count', 0.001095081601497337), ('nowInSec', 0.0010296353550030405)]
// payloadSizeInBytes   0	: [('payloadSizeInBytes', 0.8752242614922223), ('i', 0.009768462122234213), ('version', 0.009665618824318697), ('nowInSec', 0.0038815020812182255), ('size', 0.0021469398753679917), ('length', 0.0017245286803175349), ('count', 0.0014859295208697692), ('offset', 0.0014469737166672386), ('index', 0.001123539998734376), ('end', 0.0010356118647186188)]
        verifier.onSendFrame(messageCount, payloadSizeInBytes);
    }

    public void onSentSmallFrame(int messageCount, int payloadSizeInBytes) {
// messageCount         0	: [('messageCount', 0.8753045751443238), ('i', 0.03723252199162058), ('j', 0.0034054487408738355), ('size', 0.0021414985719201028), ('cfs', 0.0018246126673812963), ('version', 0.0013832228613290204), ('pageSize', 0.0013307292421863824), ('index', 0.0011063798791967308), ('count', 0.001095081601497337), ('nowInSec', 0.0010296353550030405)]
// payloadSizeInBytes   0	: [('payloadSizeInBytes', 0.8752242614922223), ('i', 0.009768462122234213), ('version', 0.009665618824318697), ('nowInSec', 0.0038815020812182255), ('size', 0.0021469398753679917), ('length', 0.0017245286803175349), ('count', 0.0014859295208697692), ('offset', 0.0014469737166672386), ('index', 0.001123539998734376), ('end', 0.0010356118647186188)]
        verifier.onSentFrame(messageCount, payloadSizeInBytes);
    }

    public void onFailedSmallFrame(int messageCount, int payloadSizeInBytes) {
// messageCount         0	: [('messageCount', 0.8753045751443238), ('i', 0.03723252199162058), ('j', 0.0034054487408738355), ('size', 0.0021414985719201028), ('cfs', 0.0018246126673812963), ('version', 0.0013832228613290204), ('pageSize', 0.0013307292421863824), ('index', 0.0011063798791967308), ('count', 0.001095081601497337), ('nowInSec', 0.0010296353550030405)]
// payloadSizeInBytes   0	: [('payloadSizeInBytes', 0.8752242518287644), ('messageSize', 0.44067385269185366), ('message', 0.2916577441006646), ('v', 0.0513431126261088), ('header', 0.011675010682891689), ('i', 0.009768463090671038), ('version', 0.009665618934824007), ('name', 0.004100964641554427), ('nowInSec', 0.003881502111417989), ('type', 0.0030238879663676242)]
        controller.fail(payloadSizeInBytes);
        verifier.onFailedFrame(messageCount, payloadSizeInBytes);
    }

    public void onConnect(int messagingVersion, OutboundConnectionSettings settings) {
// messagingVersion     0	: [('messagingVersion', 0.8753321717122307), ('i', 0.03723252199162058), ('j', 0.0034054487408738355), ('size', 0.0021414985719201028), ('cfs', 0.0018246126673812963), ('version', 0.0013832228613290204), ('pageSize', 0.0013307292421863824), ('index', 0.0011063798791967308), ('count', 0.001095081601497337), ('nowInSec', 0.0010296353550030405)]
// settings             0	: [('settings', 0.9199782008490431), ('channel', 0.3462624675155012), ('handler', 0.1796040470976636), ('decoder', 0.038474850079783525), ('bytesWrittenToNetwork', 0.0128308070992747), ('template', 0.011116045306546606), ('outboundTemplate', 0.009198999058733033), ('out', 0.007342254103962406), ('result', 0.006814517214499248), ('allocator', 0.006460392614105078)]
        verifier.onConnectOutbound(messagingVersion, settings);
    }

    public void onConnectInbound(int messagingVersion, InboundMessageHandler handler) {
// messagingVersion     0	: [('messagingVersion', 0.7506643434244614), ('i', 0.07446504398324116), ('j', 0.006810897481747671), ('size', 0.0042829971438402056), ('version', 0.002766445722658041), ('pageSize', 0.0026614584843727648), ('index', 0.0022127597583934617), ('count', 0.002190163202994674), ('nowInSec', 0.002059270710006081), ('idx', 0.0019139291563861846)]
// handler              0	: [('handler', 0.9010466065263385), ('channel', 0.3462624675155012), ('settings', 0.1924607710160849), ('decoder', 0.038474850079783525), ('bytesWrittenToNetwork', 0.0128308070992747), ('out', 0.007342254103962406), ('result', 0.006814517214499248), ('allocator', 0.006460392614105078), ('abortRequested', 0.006413587210196804), ('i', 0.003462422268649201)]
        verifier.onConnectInbound(messagingVersion, handler);
    }

    public void onOverloaded(Message<?> message, InetAddressAndPort peer) {
// message              0	: [('message', 0.6671983255261598), ('messageSize', 0.44067385269185366), ('payloadSizeInBytes', 0.0881320364481146), ('v', 0.0513431126261088), ('m', 0.032000094559843886), ('type', 0.021224638100245455), ('msg', 0.01740347002030581), ('response', 0.017392868898964655), ('respondTo', 0.014655274884446746), ('remove', 0.012048382328060716)]
// peer                 0	: [('peer', 0.5886384858670416), ('to', 0.17661240818910473), ('endpoint', 0.08842321800312249), ('sendTo', 0.0518903563383456), ('from', 0.004710050150344094), ('address', 0.004672987771398683), ('addr', 0.0023728137460615815), ('initiator', 0.0022960835578262786), ('destination', 0.0018018091780155616), ('ep', 0.0017904236947491117)]
        controller.fail(message.serializedSize(current_version));
        verifier.onOverloaded(message.id());
    }

    public void onExpired(Message<?> message, InetAddressAndPort peer) {
// message              1	: [('callbackInfo', 0.7505840327891231), ('message', 0.5786053570135854), ('messageSize', 0.440673852868983), ('to', 0.24132962451559462), ('payloadSizeInBytes', 0.08813203645209873), ('v', 0.05134311341579945), ('cause', 0.03375778582474043), ('version', 0.03261421085072867), ('m', 0.032000094632008494), ('start', 0.03182221894404424)]
// peer                 0	: [('peer', 0.5886384858670416), ('to', 0.17661240818910473), ('endpoint', 0.08842321800312249), ('sendTo', 0.0518903563383456), ('from', 0.004710050150344094), ('address', 0.004672987771398683), ('addr', 0.0023728137460615815), ('initiator', 0.0022960835578262786), ('destination', 0.0018018091780155616), ('ep', 0.0017904236947491117)]
        controller.fail(message.serializedSize(current_version));
        verifier.onExpiredBeforeSend(message.id(), message.serializedSize(current_version), approxTime.now() - message.createdAtNanos(), TimeUnit.NANOSECONDS);
    }

    public void onFailedSerialize(Message<?> message, InetAddressAndPort peer, int messagingVersion, int bytesWrittenToNetwork, Throwable failure) {
// message              0	: [('message', 0.5786053762119536), ('messageSize', 0.2563477053837074), ('v', 0.1026862252522176), ('next', 0.08351293896281708), ('send', 0.0833448614432325), ('payloadSizeInBytes', 0.05126407289622919), ('m', 0.032000094559843886), ('header', 0.023350021365783378), ('type', 0.021224638100245455), ('msg', 0.01740347002030581)]
// peer                 0	: [('peer', 0.5886384858670416), ('to', 0.17661240818910473), ('endpoint', 0.08842321800312249), ('sendTo', 0.0518903563383456), ('from', 0.004710050150344094), ('address', 0.004672987771398683), ('addr', 0.0023728137460615815), ('initiator', 0.0022960835578262786), ('destination', 0.0018018091780155616), ('ep', 0.0017904236947491117)]
// messagingVersion     1	: [('version', 0.4098391768040267), ('messagingVersion', 0.16091319135997872), ('queueCapacity', 0.16018427324512458), ('sessionIndex', 0.0737065478473796), ('id', 0.07360516240695553), ('v', 0.061539886891581914), ('message', 0.03475340694275007), ('e', 0.031135230458137594), ('m', 0.030797444375898746), ('header', 0.014169598538723297)]
// bytesWrittenToNetwork 0	: [('bytesWrittenToNetwork', 0.8752012149476074), ('header', 0.5003864102544888), ('to', 0.2413296254850384), ('message', 0.16840051677775775), ('cause', 0.03375778623834351), ('version', 0.032614218029999684), ('replica', 0.030801650480008786), ('msg2', 0.03038948557772098), ('m', 0.030384103286175797), ('peer', 0.030379596782781074)]
// failure              0	: [('failure', 0.7055377809756798), ('t', 0.2862511077527345), ('cause', 0.018236059833803015), ('accumulate', 0.01368875489913709), ('e', 0.0071543811370067), ('th', 0.0037104973103747), ('i', 0.003462471639658706), ('throwable', 0.0024996952281243815), ('error', 0.002208682069831645), ('failureException', 0.002037250084813268)]
        if (bytesWrittenToNetwork == 0)
            controller.fail(message.serializedSize(messagingVersion));
        verifier.onFailedSerialize(message.id(), bytesWrittenToNetwork, failure);
    }

    public void onDiscardOnClose(Message<?> message, InetAddressAndPort peer) {
// message              0	: [('message', 0.5786053762119536), ('messageSize', 0.44067385269185366), ('payloadSizeInBytes', 0.0881320364481146), ('v', 0.0513431126261088), ('m', 0.032000094559843886), ('type', 0.021224638100245455), ('msg', 0.01740347002030581), ('response', 0.017392868898964655), ('respondTo', 0.014655274884446746), ('remove', 0.012048382328060716)]
// peer                 0	: [('peer', 0.5886384858670416), ('to', 0.17661240818910473), ('endpoint', 0.08842321800312249), ('sendTo', 0.0518903563383456), ('from', 0.004710050150344094), ('address', 0.004672987771398683), ('addr', 0.0023728137460615815), ('initiator', 0.0022960835578262786), ('destination', 0.0018018091780155616), ('ep', 0.0017904236947491117)]
        controller.fail(message.serializedSize(current_version));
        verifier.onFailedClosing(message.id());
    }

    public String toString() {
        return linkId;
    }
}
