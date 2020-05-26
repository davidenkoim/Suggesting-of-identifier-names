// Type of training: selfTestingIdentifier
// Path to file: C:\Users\Igor\IdeaProjects\SLP-Core-0.3\java-projects\cassandra-trunk\src\java\org\apache\cassandra\triggers\TriggerExecutor.java
// Number of identifiers: 39	Accuracy: 38.46%	MRR: 47.01%
// True 	Rank of true in predictions : [(predicted token, probability of a token), ...]

package org.apache.cassandra.triggers;

import java.io.File;
import java.nio.ByteBuffer;
import java.util.*;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Maps;
import org.apache.cassandra.cql3.QueryProcessor;
import org.apache.cassandra.db.*;
import org.apache.cassandra.db.partitions.PartitionUpdate;
import org.apache.cassandra.exceptions.CassandraException;
import org.apache.cassandra.exceptions.InvalidRequestException;
import org.apache.cassandra.schema.TableId;
import org.apache.cassandra.schema.TriggerMetadata;
import org.apache.cassandra.schema.Triggers;
import org.apache.cassandra.utils.FBUtilities;
import org.apache.cassandra.utils.Pair;

public class TriggerExecutor {

    public static final TriggerExecutor instance = new TriggerExecutor();

    private final Map<String, ITrigger> cachedTriggers = Maps.newConcurrentMap();

    private final ClassLoader parent = Thread.currentThread().getContextClassLoader();

    private volatile ClassLoader customClassLoader;

    private TriggerExecutor() {
        reloadClasses();
    }

    public void reloadClasses() {
        File triggerDirectory = FBUtilities.cassandraTriggerDir();
// triggerDirectory     No	: [('buffer', 0.18970602163085576), ('query', 0.18960553134883829), ('samplingLevel', 0.1894103832120123), ('dir', 0.11416968497103618), ('tempFile', 0.09639792397210956), ('f', 0.0952747359418221), ('file', 0.09013133787675358), ('cachesDir', 0.06379160359146498), ('evict', 0.045459075634925294), ('dataPath', 0.03973965907802886)]
        if (triggerDirectory == null)
            return;
        customClassLoader = new CustomClassLoader(parent, triggerDirectory);
        cachedTriggers.clear();
    }

    public PartitionUpdate execute(PartitionUpdate updates) throws InvalidRequestException {
// updates              No	: [('format', 0.4272375965608874), ('update', 0.318975341871235), ('txn', 0.21185307496400663), ('query', 0.13369085687189744), ('accumulate', 0.06270072432064924), ('mostInteresting', 0.059367398414020195), ('upd', 0.05737662356772674), ('out', 0.056708569875712656), ('peer', 0.055038157426996824), ('builder', 0.05493985811709158)]
        List<Mutation> intermediate = executeInternal(updates);
// intermediate         No	: [('mutations', 0.5687915182238092), ('augmentations', 0.18432288723066784), ('partition', 0.10478969560046393), ('params', 0.0831425581350912), ('value', 0.07339917253312313), ('bytes', 0.06563918533113507), ('holder', 0.06222974533006369), ('b', 0.042275172844864664), ('query', 0.04163353015206339), ('toPrepare', 0.04139760094820404)]
        if (intermediate == null || intermediate.isEmpty())
            return updates;
        List<PartitionUpdate> augmented = validateForSinglePartition(updates.metadata().id, updates.partitionKey(), intermediate);
// augmented            No	: [('mutatedCFs', 0.6949868729500419), ('updates', 0.5094588947289462), ('accumulate', 0.08778871984365072), ('i', 0.03306666393291682), ('fail', 0.022539506553511526), ('left', 0.019051316590665324), ('iterators', 0.01890772556404216), ('toMerge', 0.01417295789563895), ('otherSlice', 0.014172308166965644), ('existingBuilder', 0.014171712695677702)]
        augmented.add(updates);
        return PartitionUpdate.merge(augmented);
    }

    public Collection<Mutation> execute(Collection<? extends IMutation> mutations) throws InvalidRequestException {
// mutations            0	: [('mutations', 0.881357175209927), ('tmutations', 0.2790305623185193), ('message', 0.05723704410765282), ('type', 0.014292504422057815), ('responseHandler', 0.013417715316256148), ('schema', 0.007528880774387608), ('batch', 0.007516768441457541), ('writeHandler', 0.006705890126309558), ('o', 0.005617609164733963), ('codec', 0.003485566684960652)]
        boolean hasCounters = false;
// hasCounters          No	: [('responseHandlers', 0.825001283622552), ('value', 0.12197381987631066), ('bytes', 0.059881964897551515), ('typeInHeader', 0.04445596312928484), ('mutation', 0.037510327051064316), ('current', 0.030298451711811402), ('previous', 0.029980872219484644), ('terminal', 0.02955400135067671), ('latestBucket', 0.029529499637298774), ('cHeaderElem', 0.029529460979162873)]
        List<Mutation> augmentedMutations = null;
// augmentedMutations   No	: [('mutations', 0.6104581896142487), ('tmutations', 0.08422090769195537), ('rms', 0.08015622070507142), ('options', 0.040820875773804687), ('repairs', 0.03368034034883672), ('ranges', 0.03251608614367875), ('tokenizer', 0.02877563256678278), ('i', 0.02616619982852849), ('ret', 0.024179341502113823), ('version', 0.02323538865853938)]
        for (IMutation mutation : mutations) {
// mutation             0	: [('mutation', 0.8804692636227168), ('fm', 0.22569989304475785), ('updates', 0.020884888951808078), ('i', 0.020333836274771366), ('value', 0.014280659827686172), ('m1', 0.01329473582043347), ('bytes', 0.006245392229242984), ('sstable', 0.005663759347781913), ('type', 0.0056472176459777), ('e', 0.0047541880350818605)]
            if (mutation instanceof CounterMutation)
                hasCounters = true;
            for (PartitionUpdate upd : mutation.getPartitionUpdates()) {
// upd                  2	: [('update', 0.5063217230348103), ('format', 0.42723759323519395), ('upd', 0.16589470963198605), ('query', 0.1336908505904666), ('partitionUpdate', 0.06018346405033918), ('pu', 0.053248996157145914), ('partition', 0.0468964927342506), ('cql', 0.034568639732922894), ('executionController', 0.03183079620797819), ('insert', 0.029697643772534112)]
                List<Mutation> augmentations = executeInternal(upd);
// augmentations        No	: [('mutations', 0.5687915182323815), ('intermediate', 0.18432287756634907), ('value', 0.07339917313239289), ('bytes', 0.06563918561405581), ('key', 0.04406938016770588), ('cqlType', 0.03287832088052501), ('iterator', 0.0327603358185482), ('datacenters', 0.03229671458299812), ('replicas', 0.029436666703002726), ('codec', 0.02575958966601228)]
                if (augmentations == null || augmentations.isEmpty())
                    continue;
                validate(augmentations);
                if (augmentedMutations == null)
                    augmentedMutations = new LinkedList<>();
                augmentedMutations.addAll(augmentations);
            }
        }
        if (augmentedMutations == null)
            return null;
        if (hasCounters)
            throw new InvalidRequestException("Counter mutations and trigger mutations cannot be applied together atomically.");
        @SuppressWarnings("unchecked")
        Collection<Mutation> originalMutations = (Collection<Mutation>) mutations;
// originalMutations    No	: [('mutations', 0.34145208403237737), ('sstables', 0.1561013094014284), ('sstableNew', 0.15603393203955881), ('sources', 0.08103782822118458), ('newFiles', 0.0810321047549723), ('transientRepairs', 0.08103189399946914), ('cs', 0.0780191591687971), ('sortedTokens', 0.07758817921639938), ('schema', 0.07510306700339878), ('message', 0.04544115808179597)]
        return mergeMutations(Iterables.concat(originalMutations, augmentedMutations));
    }

    private Collection<Mutation> mergeMutations(Iterable<Mutation> mutations) {
// mutations            0	: [('mutations', 0.4420943643531401), ('batch', 0.20559369151317997), ('schema', 0.14791349614576818), ('tmutations', 0.13706208130557393), ('message', 0.09088231616359194), ('repairs', 0.03928273915084834), ('handler', 0.03264730242970477), ('next', 0.016471080730314776), ('temp', 0.016256690828266314), ('it1', 0.016241668037688735)]
        ListMultimap<Pair<String, ByteBuffer>, Mutation> groupedMutations = ArrayListMultimap.create();
// groupedMutations     No	: [('keys', 0.6284353037247882), ('repairs', 0.383032761972383), ('mutations', 0.17781036018365115), ('accumulate', 0.175577441495524), ('size', 0.09676819746899668), ('builder', 0.07268830624711446), ('entry', 0.05452700843081331), ('out', 0.0527745654684223), ('sb', 0.051156551100485205), ('iterator', 0.04804220777671704)]
        for (Mutation mutation : mutations) {
// mutation             0	: [('mutation', 0.8529046532595881), ('value', 0.3332258187373505), ('actual', 0.3228005485441132), ('st', 0.21438503537015785), ('cm', 0.10718551275934168), ('rm', 0.08150309620032233), ('iterable', 0.07223172040199098), ('type', 0.07158001700859483), ('builder', 0.06429464086425715), ('index', 0.06388402538462215)]
            Pair<String, ByteBuffer> key = Pair.create(mutation.getKeyspaceName(), mutation.key().getKey());
// key                  0	: [('key', 0.7579032066525516), ('customPayload', 0.3804792571146941), ('entry', 0.06359317850478095), ('m', 0.0474082309577626), ('reqMap', 0.047335186063400134), ('keyColumns', 0.04724132273020862), ('values', 0.04079495648953582), ('respMap', 0.03168792665815605), ('data', 0.031542366077884125), ('val', 0.03150295816585337)]
            groupedMutations.put(key, mutation);
        }
        List<Mutation> merged = new ArrayList<>(groupedMutations.size());
// merged               No	: [('mutations', 0.47091636976380735), ('f', 0.11803962985367007), ('futures', 0.11802908226452404), ('keyColumns', 0.11788592142338682), ('totalIndexBytes', 0.11788565081643552), ('tokenStat', 0.11788548108999435), ('builder', 0.08571706336478482), ('result', 0.08196972416955933), ('ret', 0.047238439207971716), ('message', 0.0454411582128529)]
        for (Pair<String, ByteBuffer> key : groupedMutations.keySet()) merged.add(Mutation.merge(groupedMutations.get(key)));
// key                  0	: [('key', 0.7579030365642455), ('customPayload', 0.38047925711842423), ('i', 0.23141385924227317), ('entry', 0.06359317855377486), ('m', 0.0474082309938445), ('reqMap', 0.0473351860648348), ('keyColumns', 0.04724132273135635), ('values', 0.04079495653949814), ('respMap', 0.031687926659160315), ('data', 0.031542366108334954)]
        return merged;
    }

    private List<PartitionUpdate> validateForSinglePartition(TableId tableId, DecoratedKey key, Collection<Mutation> tmutations) throws InvalidRequestException {
// tableId              0	: [('tableId', 0.6667260330090738), ('id', 0.15791787991577866), ('tid', 0.01572850620537624), ('baseTableId', 0.014515068604910123), ('table', 0.013448995818489098), ('dirtyTableId', 0.013301321739356822), ('val', 0.006698640814283489), ('id2', 0.006074543444061365), ('id1', 0.0036492145685650752), ('i', 0.0034444149987951016)]
// key                  0	: [('key', 0.9181128532051852), ('indexName', 0.16289326858095315), ('updateBuilder', 0.08874630566725845), ('files', 0.08139947349260246), ('sender', 0.08138276504715325), ('i', 0.051501666513923634), ('session', 0.04095354053058664), ('that', 0.04088237093951336), ('k', 0.040807123950003334), ('partitionKey', 0.026663179703557756)]
// tmutations           9	: [('mutations', 0.5707260467571058), ('cfs', 0.27567179573610096), ('expectedUpdate', 0.2752164428225783), ('units', 0.22034719228087674), ('batch', 0.20559369153398288), ('schema', 0.14791349619870803), ('decodedMutations', 0.10917257684399746), ('row', 0.0749155162811216), ('allReplicas', 0.07345032743946373), ('tmutations', 0.06853139889480767)]
        validate(tmutations);
        if (tmutations.size() == 1) {
            List<PartitionUpdate> updates = Lists.newArrayList(Iterables.getOnlyElement(tmutations).getPartitionUpdates());
// updates              0	: [('updates', 0.6650836517310197), ('actualUpdate', 0.3585497761488701), ('mutatedCFs', 0.1949868729514766), ('augmented', 0.03900071382267582), ('builder', 0.02774604898407962), ('options', 0.026374998673866543), ('data', 0.025809661477577475), ('rows', 0.025805776637886177), ('cfs', 0.02567178700055771), ('expected', 0.025300924399166397)]
            if (updates.size() > 1)
                throw new InvalidRequestException("The updates generated by triggers are not all for the same partition");
            validateSamePartition(tableId, key, Iterables.getOnlyElement(updates));
            return updates;
        }
        ArrayList<PartitionUpdate> updates = new ArrayList<>(tmutations.size());
// updates              1	: [('builder', 0.5243676571266729), ('updates', 0.5010329768768637), ('actualUpdate', 0.35854977614915706), ('mutatedCFs', 0.2243919362618284), ('result', 0.09747608613373206), ('accumulate', 0.09451252185357242), ('augmented', 0.044891744182461564), ('entry', 0.04101115134453466), ('modifications', 0.032083818709906724), ('map', 0.02935701582876209)]
        for (Mutation mutation : tmutations) {
// mutation             0	: [('mutation', 0.8529046725440377), ('mutatedCFs', 0.13059113706815278), ('updates', 0.08877923418481024), ('rm', 0.08150309618511478), ('a', 0.06536517948812107), ('sorted', 0.06533603560879228), ('generator', 0.06529840088259134), ('commitLogReplayer', 0.06529568515862301), ('m', 0.006669901713212318), ('expected', 0.0024094540770915997)]
            for (PartitionUpdate update : mutation.getPartitionUpdates()) {
// update               0	: [('update', 0.6075123039956061), ('upd', 0.5003569671326525), ('pu', 0.09210068855537316), ('indexEntry', 0.04579138971911949), ('cursor', 0.03813782952937227), ('filter', 0.03446621834882066), ('metadata', 0.026102997629386372), ('holder', 0.02369827777169181), ('cstart', 0.022883318326979755), ('value', 0.020863700529741)]
                validateSamePartition(tableId, key, update);
                updates.add(update);
            }
        }
        return updates;
    }

    private void validateSamePartition(TableId tableId, DecoratedKey key, PartitionUpdate update) throws InvalidRequestException {
// tableId              0	: [('tableId', 0.3984489562059883), ('id', 0.15791787956485887), ('matcher', 0.023123922099312143), ('dir', 0.01858894718035293), ('tid', 0.015728506201646102), ('baseTableId', 0.01451506860060612), ('table', 0.013448995266715752), ('dirtyTableId', 0.013301321735626684), ('result', 0.012370593319094509), ('sstable', 0.011119152143419167)]
// key                  0	: [('key', 0.9181128627407424), ('receiver', 0.2171570034828266), ('otherRestriction', 0.07272050022021395), ('expression', 0.07239433780602265), ('restrictions', 0.07224831268826275), ('columnDefs', 0.07208472702199804), ('partitionKey', 0.0266631796790608), ('o1', 0.0077490148166615425), ('iter', 0.006950587709976868), ('cfs', 0.005793890193133872)]
// update               4	: [('mutation', 0.2295600317439832), ('gcState', 0.22934248511401129), ('oldKey', 0.22934230335193068), ('that', 0.22248194391557485), ('update', 0.13925370759899763), ('stream', 0.1260660468878924), ('summary', 0.06426866712410237), ('tableMetadata', 0.06299153708636504), ('cck', 0.06285052699313783), ('keys', 0.06269723261849286)]
        if (!key.equals(update.partitionKey()))
            throw new InvalidRequestException("Partition key of additional mutation does not match primary update key");
        if (!tableId.equals(update.metadata().id))
            throw new InvalidRequestException("table of additional mutation does not match primary update table");
    }

    private void validate(Collection<Mutation> tmutations) throws InvalidRequestException {
// tmutations           3	: [('mutations', 0.5207260420161887), ('batch', 0.20559369151317997), ('schema', 0.1875515335016994), ('tmutations', 0.06853144721227682), ('message', 0.022720579040897984), ('next', 0.011260627325435837), ('temp', 0.01120702984992372), ('repairs', 0.009820684787712084), ('augmented', 0.009175578639680446), ('changes', 0.009172634759877117)]
        for (Mutation mutation : tmutations) {
// mutation             1	: [('key', 0.9127163326117731), ('mutation', 0.8474759924703639), ('mutatedCFs', 0.13059113706958747), ('updates', 0.08877923418990331), ('a', 0.06536517952484856), ('sorted', 0.06533603561119534), ('generator', 0.06529840089299267), ('commitLogReplayer', 0.06529568515894581), ('rm', 0.02133952571877039), ('m', 0.01333980349858844)]
            QueryProcessor.validateKey(mutation.key().getKey());
            for (PartitionUpdate update : mutation.getPartitionUpdates()) update.validate();
// update               0	: [('update', 0.4825123136255287), ('upd', 0.22859590270786947), ('size', 0.20336384277566186), ('tableNames', 0.20241035872782467), ('partitionUpdate', 0.0925917320251696), ('partition', 0.08594824636712531), ('gcgs', 0.03574315084926398), ('pu', 0.03574266219827988), ('resultBuilder', 0.035742526895306374), ('cf', 0.005585952332490922)]
        }
    }

    private List<Mutation> executeInternal(PartitionUpdate update) {
// update               1	: [('builder', 0.335076984870285), ('update', 0.31464380947035064), ('val', 0.16699419713787744), ('upd', 0.05733865028029403), ('value', 0.051245348820154614), ('i', 0.049405861016155364), ('name', 0.03395810829619266), ('source', 0.027183662628088107), ('parsed', 0.02672828897149749), ('partitionUpdate', 0.025149440635878876)]
        Triggers triggers = update.metadata().triggers;
// triggers             0	: [('triggers', 0.7687532138232346), ('i', 0.01938883465724251), ('value', 0.017032808642771784), ('sstables', 0.013340372583005885), ('before', 0.012509399124978376), ('flush', 0.010333383924330567), ('version', 0.008469599109591046), ('cfs', 0.007895119968919182), ('size', 0.007130640534260593), ('type', 0.007057342275979225)]
        if (triggers.isEmpty())
            return null;
        List<Mutation> tmutations = Lists.newLinkedList();
// tmutations           No	: [('mutations', 0.6104581848819037), ('statement', 0.3759460443288827), ('wrapped', 0.3754701927418118), ('rms', 0.08015622056371424), ('augmentedMutations', 0.08015473895931448), ('size', 0.05407399586197156), ('builder', 0.04592302276203657), ('result', 0.045037079581221905), ('sb', 0.04309216857399859), ('message', 0.02272057910642645)]
        Thread.currentThread().setContextClassLoader(customClassLoader);
        try {
            for (TriggerMetadata td : triggers) {
// td                   4	: [('trigger', 0.8500369657530867), ('triggerClass', 0.5002598303445799), ('i', 0.11570693779245869), ('entry', 0.03875586814067212), ('td', 0.03687524109146529), ('key', 0.0321937212907959), ('buffer', 0.027671278327238744), ('name', 0.019353447667219983), ('endpoint', 0.01910240025746589), ('column', 0.01439160734250418)]
                ITrigger trigger = cachedTriggers.get(td.classOption);
// trigger              No	: [('rhs', 0.535748326749975), ('metadata', 0.036387721969216205), ('tokenMetadata', 0.035878144885041555), ('f', 0.03583753912951966), ('addrs', 0.0357199110830313), ('result', 0.02193376178533255), ('type', 0.02157395898737383), ('value', 0.019102724138894467), ('i', 0.018535742747316297), ('flush', 0.016800009844664916)]
                if (trigger == null) {
                    trigger = loadTriggerInstance(td.classOption);
                    cachedTriggers.put(td.classOption, trigger);
                }
                Collection<Mutation> temp = trigger.augment(update);
// temp                 No	: [('mutations', 0.34145208404952165), ('views', 0.2502530506064631), ('reconciled', 0.25011122213192827), ('schema', 0.0751030670298684), ('message', 0.0454411582128529), ('tmutations', 0.04076322349326191), ('replicas', 0.02943666523506168), ('canonical1', 0.024913566221173203), ('next', 0.02252125473609096), ('builder', 0.019927693961849374)]
                if (temp != null)
                    tmutations.addAll(temp);
            }
            return tmutations;
        } catch (CassandraException ex) {
// ex                   1	: [('ce', 0.9531255038550402), ('ex', 0.5286882648848888), ('e', 0.16387573212569936), ('t', 0.01189351526168673), ('error', 0.009314574113679072), ('thread', 0.00924603231450035), ('exception', 0.009245587747587387), ('exc', 0.0001290260092126911), ('ioe', 0.00012854278430729494), ('checked', 0.0001283688233413523)]
            throw ex;
        } catch (Exception ex) {
// ex                   3	: [('e', 0.8447943345997857), ('session', 0.37549261980108894), ('loadSSTables', 0.375022408003156), ('ex', 0.04930532988932573), ('ignore', 0.038187855505605675), ('e1', 0.006735390733181196), ('suppressed', 0.003367831320133423), ('exc', 0.0031974625372334434), ('row', 0.002954621494559816), ('i', 0.0028943234329613324)]
            throw new RuntimeException(String.format("Exception while executing trigger on table with ID: %s", update.metadata().id), ex);
        } finally {
            Thread.currentThread().setContextClassLoader(parent);
        }
    }

    public synchronized ITrigger loadTriggerInstance(String triggerClass) throws Exception {
// triggerClass         No	: [('name', 0.6674551479541705), ('td', 0.5001373113791413), ('i', 0.11574656883025544), ('keyspace', 0.07699626340057446), ('keyspaceName', 0.03923364478836063), ('query', 0.02391906101974347), ('ksName', 0.02185552418148856), ('key', 0.012056237589243346), ('s', 0.011743109061954018), ('msg', 0.011429495370714988)]
        if (cachedTriggers.get(triggerClass) != null)
            return cachedTriggers.get(triggerClass);
        return (ITrigger) customClassLoader.loadClass(triggerClass).getConstructor().newInstance();
    }
}
