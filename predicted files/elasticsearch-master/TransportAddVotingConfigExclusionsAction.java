// Type of training: selfTestingIdentifier
// Path to file: C:\Users\Igor\IdeaProjects\SLP-Core-0.3\java-projects\elasticsearch-master\server\src\main\java\org\elasticsearch\action\admin\cluster\configuration\TransportAddVotingConfigExclusionsAction.java
// Number of identifiers: 37	Accuracy: 67.57%	MRR: 70.32%
// True 	Rank of true in predictions : [(predicted token, probability of a token), ...]

package org.elasticsearch.action.admin.cluster.configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.ElasticsearchTimeoutException;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.support.ActionFilters;
import org.elasticsearch.action.support.master.TransportMasterNodeAction;
import org.elasticsearch.cluster.ClusterState;
import org.elasticsearch.cluster.ClusterStateObserver;
import org.elasticsearch.cluster.ClusterStateObserver.Listener;
import org.elasticsearch.cluster.ClusterStateUpdateTask;
import org.elasticsearch.cluster.block.ClusterBlockException;
import org.elasticsearch.cluster.block.ClusterBlockLevel;
import org.elasticsearch.cluster.coordination.CoordinationMetadata;
import org.elasticsearch.cluster.coordination.CoordinationMetadata.VotingConfigExclusion;
import org.elasticsearch.cluster.metadata.IndexNameExpressionResolver;
import org.elasticsearch.cluster.metadata.Metadata;
import org.elasticsearch.cluster.service.ClusterService;
import org.elasticsearch.common.Priority;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.common.settings.ClusterSettings;
import org.elasticsearch.common.settings.Setting;
import org.elasticsearch.common.settings.Setting.Property;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.tasks.Task;
import org.elasticsearch.threadpool.ThreadPool;
import org.elasticsearch.threadpool.ThreadPool.Names;
import org.elasticsearch.transport.TransportService;
import java.io.IOException;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TransportAddVotingConfigExclusionsAction extends TransportMasterNodeAction<AddVotingConfigExclusionsRequest, AddVotingConfigExclusionsResponse> {

    private static final Logger logger = LogManager.getLogger(TransportAddVotingConfigExclusionsAction.class);

    public static final Setting<Integer> MAXIMUM_VOTING_CONFIG_EXCLUSIONS_SETTING = Setting.intSetting("cluster.max_voting_config_exclusions", 10, 1, Property.Dynamic, Property.NodeScope);

    private volatile int maxVotingConfigExclusions;

    @Inject
    public TransportAddVotingConfigExclusionsAction(Settings settings, ClusterSettings clusterSettings, TransportService transportService, ClusterService clusterService, ThreadPool threadPool, ActionFilters actionFilters, IndexNameExpressionResolver indexNameExpressionResolver) {
// settings             0	: [('settings', 0.493477882494034), ('i', 0.16704708233482957), ('index', 0.024267457185680123), ('indexSettings', 0.02103476837289478), ('nodeSettings', 0.0177820224397715), ('key', 0.017305833237751204), ('current', 0.016372488978950984), ('name', 0.013316935038487613), ('bucket', 0.011726343057645432), ('fieldName', 0.008413779177390533)]
// clusterSettings      0	: [('clusterSettings', 0.9604345338592983), ('clusterService', 0.22373495766349186), ('builder', 0.01998604216939653), ('connectionManager', 0.014829700863571642), ('bootstrap', 0.014828761474530888), ('discoveryTypes', 0.014825910620565576), ('nonDeprecatedListSetting', 0.014825838889125122), ('userOptional', 0.014825838889125122), ('licenseState', 0.003946217467406847), ('metadata', 0.0026878739257788055)]
// transportService     0	: [('transportService', 0.9502458513038623), ('request', 0.2804175673877779), ('clusterService', 0.028855563326146538), ('threadPool', 0.018878962168650537), ('scripts', 0.006887864851896424), ('settings', 0.004048041135861282), ('e', 0.0037489975832023853), ('args', 0.003713757256244013), ('fieldName', 0.003470706615550732), ('testParams', 0.0025817627938859453)]
// clusterService       0	: [('clusterService', 0.9668695773854848), ('actionFilters', 0.4515507004802901), ('ingestService', 0.00639417098782118), ('connectionManager', 0.0037179557800917193), ('threadPool', 0.0018118227206170617), ('e', 0.001095400890297011), ('fcr', 0.0010314537932812893), ('indicesService', 0.0009781596340073017), ('client', 0.0009221479679770805), ('namedWriteableRegistry', 0.0008837664414583993)]
// threadPool           0	: [('threadPool', 0.9655145226666587), ('indicesService', 0.08990613649872971), ('transportService', 0.034072026720313564), ('client', 0.01760133409801322), ('shardStateAction', 0.016796007585005462), ('actionFilters', 0.008950448754340203), ('availableProcessors', 0.008098601504004621), ('licenseState', 0.005214623037173546), ('ingestService', 0.0042504233951805165), ('metaStateService', 0.004050425237565626)]
// actionFilters        0	: [('actionFilters', 0.9663628627186107), ('client', 0.056421390450799996), ('clusterService', 0.023455503495636187), ('resourceWatcherService', 0.017507546078162906), ('xContentRegistry', 0.009152576948101962), ('persistentTasksService', 0.008728584512407194), ('createIndexService', 0.00872779104088989), ('recoveryTargetService', 0.008727505904589677), ('indexStateService', 0.008727103589068834), ('shardStateAction', 0.007636524029468704)]
// indexNameExpressionResolver 0	: [('indexNameExpressionResolver', 0.9250305643806833), ('in', 0.05403059499240782), ('resolver', 0.028959509077089), ('client', 0.02845660144954436), ('clusterService', 0.01919921422480527), ('version', 0.01898718054070576), ('p', 0.018934706739955853), ('parser', 0.017401756754642582), ('clientConnections', 0.01403629826756461), ('handler', 0.011795419437216615)]
        super(AddVotingConfigExclusionsAction.NAME, transportService, clusterService, threadPool, actionFilters, AddVotingConfigExclusionsRequest::new, indexNameExpressionResolver);
        maxVotingConfigExclusions = MAXIMUM_VOTING_CONFIG_EXCLUSIONS_SETTING.get(settings);
        clusterSettings.addSettingsUpdateConsumer(MAXIMUM_VOTING_CONFIG_EXCLUSIONS_SETTING, this::setMaxVotingConfigExclusions);
    }

    private void setMaxVotingConfigExclusions(int maxVotingConfigExclusions) {
// maxVotingConfigExclusions No	: [('i', 0.3704160223267249), ('j', 0.02981584863473214), ('doc', 0.016187666125353117), ('in', 0.015389755907140812), ('size', 0.009185214935877128), ('parser', 0.008394558211773701), ('index', 0.008109386196149334), ('nodeOrdinal', 0.006723486785135556), ('docId', 0.006123773004490048), ('columnIndex', 0.005897547466133541)]
        this.maxVotingConfigExclusions = maxVotingConfigExclusions;
    }

    @Override
    protected String executor() {
        return Names.SAME;
    }

    @Override
    protected AddVotingConfigExclusionsResponse read(StreamInput in) throws IOException {
// in                   0	: [('in', 0.9286733329256854), ('input', 0.0044367795530483025), ('streamInput', 0.001952737591149535), ('i', 0.0005613415823315991), ('stream', 0.00045743549901088677), ('request', 0.0003590119943867906), ('e', 0.0003134268474244343), ('response', 0.00031326103234247474), ('builder', 0.0002830581050915242), ('parser', 0.00027641528179189685)]
        return new AddVotingConfigExclusionsResponse(in);
    }

    @Override
    protected void masterOperation(Task task, AddVotingConfigExclusionsRequest request, ClusterState state, ActionListener<AddVotingConfigExclusionsResponse> listener) throws Exception {
// task                 0	: [('task', 0.9418697905293645), ('ignoredTask', 0.017655237960632147), ('thisTask', 0.0012698883745461322), ('parentTask', 0.0005350755343451083), ('t', 0.0002598359443190378), ('mainTask', 0.00016867756921255277), ('theTask', 0.00015518724412317877), ('joinTask', 0.00011247169621905784), ('asyncTask', 8.438326763947274e-05), ('e', 5.7274711545850724e-05)]
// request              0	: [('request', 0.5639357197024174), ('timeout', 0.21575536986643562), ('bulkRequest', 0.10783430163786537), ('maximumTime', 0.1078256002849407), ('clusterState', 0.06313595431397695), ('state', 0.0628331563954249), ('deserialized', 0.031256354205995464), ('threadPool', 0.02824478013608659), ('addVotingConfigExclusionsRequest', 0.023438747406023468), ('originalRequest', 0.015628479399018222)]
// state                0	: [('state', 0.7832912321801977), ('currentState', 0.5026592161668331), ('clusterState', 0.04764238582708175), ('channel', 0.04616217994190541), ('listener', 0.03313284426235348), ('oldState', 0.02498657305890015), ('newState', 0.020192749845473576), ('machineLearningClient', 0.01658323633913906), ('response', 0.01035889141169786), ('authorizedIndices', 0.007093103417360316)]
// listener             1	: [('onResponse', 0.7500639176705626), ('listener', 0.6097969087615104), ('latch', 0.10264522216548404), ('onResult', 0.10199889050519201), ('mappingUpdateListener', 0.10199722259715781), ('error', 0.03747755637040053), ('handler', 0.02675321216960837), ('taskFailed', 0.022357985160223276), ('throwableRef', 0.021617100343835093), ('failure', 0.017692490008220645)]
        resolveVotingConfigExclusionsAndCheckMaximum(request, state, maxVotingConfigExclusions);
        clusterService.submitStateUpdateTask("add-voting-config-exclusions", new ClusterStateUpdateTask(Priority.URGENT) {

            private Set<VotingConfigExclusion> resolvedExclusions;
// resolvedExclusions   9	: [('clusterState', 0.8438871467984228), ('request', 0.25278312177423135), ('metaBuilder', 0.2500791925838322), ('initialState', 0.2500525843434561), ('timeOut', 0.2500268829826135), ('newVotingConfigExclusion', 0.2500174794474752), ('votingConfigExclusions', 0.23660987856093957), ('nodes', 0.20557144456237744), ('newVotingConfigExclusions', 0.1151813249883733), ('resolvedExclusions', 0.10268047537365883)]

            @Override
            public ClusterState execute(ClusterState currentState) {
// currentState         0	: [('currentState', 0.8851925971729304), ('clusterState', 0.5533508851244977), ('state', 0.5086404391824929), ('coordinationMetadata', 0.15023224358824772), ('metadata', 0.07579858371700661), ('meta', 0.0504153565919845), ('lastAcceptedState', 0.05030901812371564), ('channel', 0.046160651410004847), ('listener', 0.03312980016219833), ('machineLearningClient', 0.01658272666658238)]
                assert resolvedExclusions == null : resolvedExclusions;
                final int finalMaxVotingConfigExclusions = TransportAddVotingConfigExclusionsAction.this.maxVotingConfigExclusions;
// finalMaxVotingConfigExclusions No	: [('waitCount', 0.7605671556288693), ('rightChild', 0.19307869172111713), ('numShards', 0.09664018047832695), ('maxActiveShards', 0.09657248634514787), ('maxNumberOfElements', 0.0965724818811657), ('queue', 0.09654011685665444), ('vr', 0.09653960796268779), ('numKeysToKeep', 0.0965393758356153), ('previousState', 0.036984109454252116), ('numDocs', 0.034774115109729824)]
                resolvedExclusions = resolveVotingConfigExclusionsAndCheckMaximum(request, currentState, finalMaxVotingConfigExclusions);
                final CoordinationMetadata.Builder builder = CoordinationMetadata.builder(currentState.coordinationMetadata());
// builder              0	: [('builder', 0.23554516927659847), ('newCoordinationMetadata', 0.23183295427640882), ('coordinationMetadata', 0.22971489074088186), ('coordMetadataBuilder', 0.21011097986466323), ('filter', 0.11299565676635102), ('must', 0.11298385614639944), ('mustNot', 0.11297412003509612), ('should', 0.11296927653134636), ('state', 0.10513863364422092), ('metaBuilder', 0.08439563215524148)]
                resolvedExclusions.forEach(builder::addVotingConfigExclusion);
                final Metadata newMetadata = Metadata.builder(currentState.metadata()).coordinationMetadata(builder.build()).build();
// newMetadata          No	: [('metadata', 0.5278841233142827), ('mdBuilder', 0.21467112328072407), ('metadataBuilder', 0.07231525908966327), ('builder', 0.06544760158615527), ('metadata2', 0.03297632858741572), ('fromStreamMeta', 0.03289062392840672), ('metadata3', 0.03289061946442839), ('updatedMetadata', 0.031050834606346438), ('b', 0.029672126123319875), ('mdb', 0.029633303659302827)]
                final ClusterState newState = ClusterState.builder(currentState).metadata(newMetadata).build();
// newState             5	: [('customSettings', 0.5009658190644857), ('state', 0.2454621720483985), ('clusterState', 0.23048827037861502), ('builder', 0.09172696720773371), ('initialState', 0.05636728291337855), ('newState', 0.05416082218013494), ('newClusterState', 0.044253273148138365), ('channel', 0.03257386556153733), ('args', 0.02510906259872503), ('updatedState', 0.024551492339179623)]
                assert newState.getVotingConfigExclusions().size() <= finalMaxVotingConfigExclusions;
                return newState;
            }

            @Override
            public void onFailure(String source, Exception e) {
// source               0	: [('source', 0.8750213740143006), ('name', 0.010180240404232075), ('index', 0.0033440460882227844), ('s', 0.0030694665735093575), ('id', 0.0029862301352152295), ('field', 0.002763519275646963), ('jobId', 0.002225537568735473), ('key', 0.0021481389226197054), ('fieldName', 0.001896113753949454), ('message', 0.001601025130734832)]
// e                    0	: [('e', 0.9086884572805172), ('ex', 0.05751561442210618), ('exception', 0.034364937847593445), ('validationException', 0.022012302356918482), ('exc', 0.021148375224813543), ('t', 0.019249807083873582), ('frozenSecurityIndex', 0.017859664841704002), ('failure', 0.016504855336152743), ('error', 0.015928278374963033), ('exp', 0.007553021361600738)]
                listener.onFailure(e);
            }

            @Override
            public void clusterStateProcessed(String source, ClusterState oldState, ClusterState newState) {
// source               0	: [('source', 0.8774564389493655), ('name', 0.010180240404232075), ('index', 0.0033440460882227844), ('id', 0.0029862301352152295), ('field', 0.002763519275646963), ('jobId', 0.002225537568735473), ('key', 0.0021481389226197054), ('fieldName', 0.001896113753949454), ('message', 0.001601025130734832), ('type', 0.0013072601276179756)]
// oldState             0	: [('oldState', 0.8779146975612494), ('state', 0.03605566795052121), ('clusterState', 0.016330556191656905), ('newState', 0.010096374920010764), ('currentState', 0.003590360889273487), ('currentClusterState', 0.002548066480969185), ('newClusterState', 0.0013174649377595575), ('previousClusterState', 0.0009752764743556438), ('execute', 0.0007759671913179061), ('previousState', 0.0006543791724886526)]
// newState             0	: [('newState', 0.8850372460882414), ('index', 0.3235545536569449), ('source', 0.2080726663861782), ('state', 0.10406275975187212), ('applyCommitRequest', 0.05186584246579756), ('fieldValue', 0.027680516065058527), ('action', 0.02048905013714788), ('monitoringIndex', 0.01844279815127658), ('metricbeatIndex', 0.016598131396068794), ('clusterState', 0.0163416141132864)]
                final ClusterStateObserver observer = new ClusterStateObserver(clusterService, request.getTimeout(), logger, threadPool.getThreadContext());
// observer             0	: [('observer', 0.5367041717658835), ('clusterStateObserver', 0.34969389912658944), ('listener', 0.07649492779897064), ('builder', 0.06093014870989071), ('out', 0.043814802366099304), ('metadata', 0.013840175914913138), ('parser', 0.012264091923492839), ('value', 0.010334420967145484), ('sb', 0.01020948090628215), ('request', 0.009189076922157452)]
                final Set<String> excludedNodeIds = resolvedExclusions.stream().map(VotingConfigExclusion::getNodeId).collect(Collectors.toSet());
// excludedNodeIds      No	: [('builder', 0.40587941022975155), ('channel', 0.031907336440156), ('result', 0.02494534385886817), ('roles', 0.023234864790578687), ('inSyncAllocationIds', 0.019253408946298414), ('names', 0.019146447601730034), ('responseParams', 0.015504129467309452), ('remoteClusters', 0.014707011365641256), ('cluster', 0.014096466487019914), ('nodeIds', 0.013741864860884054)]
                final Predicate<ClusterState> allNodesRemoved = clusterState -> {
// clusterState         No	: [('in', 0.015389852012936845), ('parser', 0.008394608076210475), ('response', 0.005328739287040509), ('client', 0.0046282932232021325), ('i', 0.004553436273602849), ('builder', 0.003861110792193015), ('request', 0.0037223830359965457), ('out', 0.0030966380890042108), ('settings', 0.0022530912294347026), ('context', 0.0022086761014018133)]
// allNodesRemoved      No	: [('clusterStatePredicate', 0.15175665732360683), ('statePredicate', 0.12953363236913795), ('action', 0.10739157953711793), ('shardsAllocatedPredicate', 0.07587838988473529), ('acceptableClusterStatePredicate', 0.07587799633058075), ('validationPredicate', 0.07587798740261642), ('acceptableClusterStateOrNotMasterPredicate', 0.07587798517062534), ('allExclusionsRemoved', 0.07587798517062534), ('masterChangePredicate', 0.0740501716153289), ('changePredicate', 0.05587461068179167)]
                    final Set<String> votingConfigNodeIds = clusterState.getLastCommittedConfiguration().getNodeIds();
// votingConfigNodeIds  No	: [('onFailure', 0.05673825024317428), ('roles', 0.023234864790578687), ('fromXContent', 0.022355499218058322), ('inSyncAllocationIds', 0.019253408946298414), ('names', 0.019146447601730034), ('get', 0.01683077491868564), ('responseParams', 0.015504129467309452), ('remoteClusters', 0.014707011365641256), ('cluster', 0.014096466487019914), ('nodeIds', 0.013741864860884054)]
                    return excludedNodeIds.stream().noneMatch(votingConfigNodeIds::contains);
                };
                final Listener clusterStateListener = new Listener() {
// clusterStateListener 3	: [('listener', 0.7113973212823401), ('stateListener', 0.36087006319771586), ('observerListener', 0.36087006319771586), ('clusterStateListener', 0.055435459730709964), ('builder', 0.04408521680974564), ('out', 0.021039766540357812), ('parser', 0.017798311654665572), ('request', 0.010082393482191617), ('validationException', 0.003983778770043043), ('originalListener', 0.003705290372187947)]

                    @Override
                    public void onNewClusterState(ClusterState state) {
// state                0	: [('state', 0.8281055003802802), ('newState', 0.08009060170771581), ('clusterState', 0.022337570011170725), ('currentState', 0.017008713370125966), ('oldState', 0.001364831650478687), ('initialState', 0.0012905824307898248), ('execute', 0.0007759671913179061), ('oldClusterState', 0.0007222653170101493), ('previousClusterState', 0.0006989902605694301), ('newClusterState', 0.0006785205488151686)]
                        listener.onResponse(new AddVotingConfigExclusionsResponse());
                    }

                    @Override
                    public void onClusterServiceClose() {
                        listener.onFailure(new ElasticsearchException("cluster service closed while waiting for voting config exclusions " + resolvedExclusions + " to take effect"));
                    }

                    @Override
                    public void onTimeout(TimeValue timeout) {
// timeout              0	: [('timeout', 0.8934941290905452), ('timeValue', 0.004488626731354713), ('bucketSpan', 0.0039934962517186425), ('interval', 0.002779050726420797), ('delay', 0.0019892231332284327), ('frequency', 0.0016004098957221042), ('keepAlive', 0.0015103594493345589), ('elapsedTime', 0.001491918000647733), ('commitTime', 0.0012232337744696549), ('min', 0.0011784054677631277)]
                        listener.onFailure(new ElasticsearchTimeoutException("timed out waiting for voting config exclusions " + resolvedExclusions + " to take effect"));
                    }
                };
                if (allNodesRemoved.test(newState)) {
                    clusterStateListener.onNewClusterState(newState);
                } else {
                    observer.waitForNextChange(clusterStateListener, allNodesRemoved);
                }
            }
        });
    }

    private static Set<VotingConfigExclusion> resolveVotingConfigExclusionsAndCheckMaximum(AddVotingConfigExclusionsRequest request, ClusterState state, int maxVotingConfigExclusions) {
// request              0	: [('request', 0.5395904432817402), ('deserialized', 0.03125635419687426), ('addVotingConfigExclusionsRequest', 0.02343874740565617), ('state', 0.019173421599873213), ('builder', 0.01698018764463244), ('field', 0.015747966228697747), ('originalRequest', 0.015628479394824916), ('votingConfigExclusionsRequest', 0.01562608670243602), ('a', 0.014743732691158537), ('value', 0.010536979160771455)]
// state                0	: [('state', 0.8916456160900988), ('clusterState', 0.38952484175329405), ('request', 0.11254714123274491), ('oldState', 0.012493286529450075), ('newState', 0.010096374922736788), ('currentState', 0.005463020068524941), ('currentClusterState', 0.002548066481110747), ('i', 0.0022453663293263964), ('newClusterState', 0.0013174649383085886), ('e', 0.0012537073896977371)]
// maxVotingConfigExclusions No	: [('shardId', 0.5052669051442085), ('request', 0.09862455845439128), ('i', 0.05023928325497848), ('options', 0.037738610924037436), ('indicesOptions', 0.030921556326097974), ('includeHiddenOptions', 0.02763909693472933), ('index', 0.026516491996715453), ('excludeHiddenOptions', 0.026013299139937097), ('aliasActions', 0.01951027759194498), ('nonce', 0.01746522669419019)]
        return request.resolveVotingConfigExclusionsAndCheckMaximum(state, maxVotingConfigExclusions, MAXIMUM_VOTING_CONFIG_EXCLUSIONS_SETTING.getKey());
    }

    @Override
    protected ClusterBlockException checkBlock(AddVotingConfigExclusionsRequest request, ClusterState state) {
// request              0	: [('request', 0.5395904606860324), ('deserialized', 0.031256354192313665), ('addVotingConfigExclusionsRequest', 0.023438747405472523), ('originalRequest', 0.015628479392728267), ('votingConfigExclusionsRequest', 0.01562608670239011), ('e', 0.0004581976923668058), ('context', 0.00016105769298390562), ('shard', 0.00010850778503463122), ('action', 9.372310145257237e-05), ('threadPool', 6.949264780419815e-05)]
// state                0	: [('state', 0.891641586796484), ('clusterState', 0.023822260789652088), ('oldState', 0.012493466739119637), ('newState', 0.010096666648290979), ('shouldAutoCreate', 0.006047540317188532), ('currentState', 0.005463341234272674), ('builder', 0.00424504691115811), ('field', 0.003936991557174437), ('a', 0.003685933172789634), ('value', 0.002634244790192864)]
        return state.blocks().globalBlockedException(ClusterBlockLevel.METADATA_WRITE);
    }
}
