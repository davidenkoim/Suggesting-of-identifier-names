// Type of training: selfTestingIdentifier
// Path to file: C:\Users\Igor\IdeaProjects\SLP-Core-0.3\java-projects\elasticsearch-master\x-pack\plugin\ccr\src\main\java\org\elasticsearch\xpack\ccr\action\TransportGetAutoFollowPatternAction.java
// Number of identifiers: 17	Accuracy: 82.35%	MRR: 86.03%
// True 	Rank of true in predictions : [(predicted token, probability of a token), ...]

package org.elasticsearch.xpack.ccr.action;

import org.elasticsearch.ResourceNotFoundException;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.support.ActionFilters;
import org.elasticsearch.action.support.master.TransportMasterNodeReadAction;
import org.elasticsearch.cluster.ClusterState;
import org.elasticsearch.cluster.block.ClusterBlockException;
import org.elasticsearch.cluster.block.ClusterBlockLevel;
import org.elasticsearch.cluster.metadata.IndexNameExpressionResolver;
import org.elasticsearch.cluster.metadata.Metadata;
import org.elasticsearch.cluster.service.ClusterService;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.tasks.Task;
import org.elasticsearch.threadpool.ThreadPool;
import org.elasticsearch.transport.TransportService;
import org.elasticsearch.xpack.core.ccr.AutoFollowMetadata;
import org.elasticsearch.xpack.core.ccr.AutoFollowMetadata.AutoFollowPattern;
import org.elasticsearch.xpack.core.ccr.action.GetAutoFollowPatternAction;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

public class TransportGetAutoFollowPatternAction extends TransportMasterNodeReadAction<GetAutoFollowPatternAction.Request, GetAutoFollowPatternAction.Response> {

    @Inject
    public TransportGetAutoFollowPatternAction(TransportService transportService, ClusterService clusterService, ThreadPool threadPool, ActionFilters actionFilters, IndexNameExpressionResolver indexNameExpressionResolver) {
// transportService     0	: [('transportService', 0.6257262899144582), ('request', 0.280417559153912), ('clusterService', 0.028855561044243735), ('threadPool', 0.0188789600103508), ('service', 0.018326387841435768), ('scripts', 0.00688786481703402), ('settings', 0.004048038682815769), ('e', 0.003748989132238739), ('args', 0.0037137565558266242), ('fieldName', 0.0034707057408213237)]
// clusterService       0	: [('clusterService', 0.9668695773854848), ('actionFilters', 0.45155070034658384), ('ingestService', 0.006394170983463347), ('connectionManager', 0.0037179557650373857), ('threadPool', 0.0018118224508275572), ('e', 0.0010953998339185765), ('fcr', 0.0010314537922908728), ('indicesService', 0.0009781596038986346), ('client', 0.0009221477778170774), ('namedWriteableRegistry', 0.0008837664125382321)]
// threadPool           0	: [('threadPool', 0.9655145226666587), ('indicesService', 0.08990613649872971), ('transportService', 0.034072026720313564), ('client', 0.01760133409801322), ('shardStateAction', 0.016796007585005462), ('actionFilters', 0.008950448754340203), ('availableProcessors', 0.008098601504004621), ('licenseState', 0.005214623037173546), ('ingestService', 0.0042504233951805165), ('metaStateService', 0.004050425237565626)]
// actionFilters        0	: [('actionFilters', 0.9663628627186107), ('client', 0.056421390450799996), ('clusterService', 0.023455503495636187), ('resourceWatcherService', 0.017507546078162906), ('xContentRegistry', 0.009152576948101962), ('persistentTasksService', 0.008728584512407194), ('createIndexService', 0.00872779104088989), ('recoveryTargetService', 0.008727505904589677), ('indexStateService', 0.008727103589068834), ('shardStateAction', 0.007636524029468704)]
// indexNameExpressionResolver 0	: [('indexNameExpressionResolver', 0.9250305643806833), ('client', 0.03409578922283065), ('resolver', 0.028959509077089), ('in', 0.013507648684715287), ('executor', 0.01246695630573037), ('expressionResolver', 0.00771179944760962), ('clusterService', 0.0047998032709613125), ('version', 0.004746794984236938), ('p', 0.004733676577231629), ('parser', 0.00435043864927972)]
        super(GetAutoFollowPatternAction.NAME, transportService, clusterService, threadPool, actionFilters, GetAutoFollowPatternAction.Request::new, indexNameExpressionResolver);
    }

    @Override
    protected String executor() {
        return ThreadPool.Names.SAME;
    }

    @Override
    protected GetAutoFollowPatternAction.Response read(StreamInput in) throws IOException {
// in                   0	: [('in', 0.9643366664628428), ('patterns', 0.5018934499854145), ('autoFollowPatterns', 0.25094469085987225), ('mappingVersion', 0.009751437046021943), ('autoFollowStats', 0.005668112666101272), ('executingPolicies', 0.005666449443216409), ('docs', 0.00409466074281369), ('jobs', 0.0037814412188863613), ('tasks', 0.002995930576905653), ('filter', 0.002854719617429956)]
        return new GetAutoFollowPatternAction.Response(in);
    }

    @Override
    protected void masterOperation(Task task, GetAutoFollowPatternAction.Request request, ClusterState state, ActionListener<GetAutoFollowPatternAction.Response> listener) throws Exception {
// task                 0	: [('task', 0.9418697905293645), ('ignoredTask', 0.017655237960632147), ('thisTask', 0.0012698883745461322), ('parentTask', 0.0005350755343451083), ('t', 0.0002598359443190378), ('mainTask', 0.00016867756921255277), ('theTask', 0.00015518724412317877), ('joinTask', 0.00011247169621905784), ('asyncTask', 8.438326763947274e-05), ('e', 5.7274711545850724e-05)]
// request              0	: [('request', 0.652436323028849), ('allocation', 0.16904099627777497), ('newMetadata', 0.09378465128639676), ('index', 0.038429797512608097), ('clusterState', 0.0383077816970539), ('templateName', 0.037518219160189985), ('equalTo', 0.02262293949025118), ('state', 0.019621920614902415), ('context', 0.01959028897457926), ('r', 0.019414733739110724)]
// state                0	: [('state', 0.8632768217638578), ('metadata', 0.2193346159229772), ('pattern', 0.09387226553521796), ('clusterState', 0.04332473901283165), ('request', 0.03412207532574106), ('deleteAutoFollowPatternRequest', 0.031252762053879134), ('currentState', 0.014328268295475297), ('oldState', 0.012493286529450075), ('newState', 0.010096374922736788), ('i', 0.004490729070503969)]
// listener             0	: [('listener', 0.438873413907447), ('builder', 0.1521201542309246), ('action', 0.05151741120729672), ('table', 0.0387618210314757), ('senderTransportService', 0.03410756879733028), ('phase', 0.025653536523824814), ('tasks', 0.020313344937415732), ('putJobListener', 0.01879439054737663), ('lifecycleState', 0.0179864528945491), ('source', 0.017524257679207893)]
        Map<String, AutoFollowPattern> autoFollowPatterns = getAutoFollowPattern(state.metadata(), request.getName());
// autoFollowPatterns   5	: [('patterns', 0.5302171042410482), ('in', 0.28003252462918937), ('result', 0.09736010297841352), ('existingAutoFollowPatterns', 0.09722687947680482), ('entry', 0.06832158057239714), ('autoFollowPatterns', 0.0648187951905902), ('patternsCopy', 0.032409047408152376), ('mappingVersion', 0.009751437044490864), ('autoFollowStats', 0.0056681126639577615), ('executingPolicies', 0.005666449442866448)]
        listener.onResponse(new GetAutoFollowPatternAction.Response(autoFollowPatterns));
    }

    @Override
    protected ClusterBlockException checkBlock(GetAutoFollowPatternAction.Request request, ClusterState state) {
// request              0	: [('request', 0.6524363317309951), ('followRequest', 0.011761666127179176), ('req', 0.004391669388365163), ('putJobRequest', 0.0029826526923475826), ('other', 0.002334397556282179), ('getRequest', 0.001666740395271359), ('serverInstance', 0.0016450383980479986), ('getBucketsRequest', 0.0014803826577402732), ('openJobRequest', 0.0014800880354225146), ('closeJobRequest', 0.0013157894130757088)]
// state                0	: [('state', 0.8632727924702428), ('clusterState', 0.04332580688894287), ('currentState', 0.01432858946122303), ('oldState', 0.012493466739119637), ('newState', 0.010096666648290979), ('shouldAutoCreate', 0.006047540317188532), ('builder', 0.00424504691115811), ('field', 0.003936991557174437), ('a', 0.003685933172789634), ('value', 0.002634244790192864)]
        return state.blocks().globalBlockedException(ClusterBlockLevel.METADATA_READ);
    }

    static Map<String, AutoFollowPattern> getAutoFollowPattern(Metadata metadata, String name) {
// metadata             0	: [('metadata', 0.25207480323497367), ('result', 0.15140003418564846), ('clusterState', 0.10128118749995031), ('currentState', 0.05049430024227192), ('followerClusterState', 0.05036778661981515), ('finalLocalClusterState', 0.05036765270046502), ('oldMetadata', 0.007051621277877601), ('metadata1', 0.004872858356052849), ('newMetadata', 0.0046201746204201505), ('before', 0.0043672012404068535)]
// name                 7	: [('request', 0.4206419597309075), ('indexName', 0.2360046452576406), ('aliasName', 0.19525128772472833), ('i', 0.19502437891996569), ('repoId', 0.13016327633655814), ('concreteIndex', 0.10143398147720376), ('expression', 0.09176080319537194), ('name', 0.08456270147850065), ('prefix', 0.0788055742803429), ('routing', 0.07863185074905084)]
        AutoFollowMetadata autoFollowMetadata = metadata.custom(AutoFollowMetadata.TYPE);
// autoFollowMetadata   0	: [('autoFollowMetadata', 0.625011384999065), ('currentAutoFollowMetadata', 0.26543914757564485), ('defaultValue', 0.10072864225604453), ('snapshots', 0.05400483175164611), ('snapshotsInProgress', 0.03597697811407805), ('builder', 0.035427228191289434), ('currentState', 0.03389468917295108), ('tasks', 0.03166420270758269), ('restoreInProgress', 0.031488765143359555), ('deletionsInProgress', 0.03147180253157156)]
        if (autoFollowMetadata == null) {
            if (name == null) {
                return Collections.emptyMap();
            } else {
                throw new ResourceNotFoundException("auto-follow pattern [{}] is missing", name);
            }
        }
        if (name == null) {
            return autoFollowMetadata.getPatterns();
        }
        AutoFollowPattern autoFollowPattern = autoFollowMetadata.getPatterns().get(name);
// autoFollowPattern    2	: [('supplier', 0.7501714327875268), ('previousPattern', 0.7196430019086317), ('autoFollowPattern', 0.22678602645082), ('value', 0.036790581643873714), ('values', 0.029990049472265898), ('out', 0.024435611677319974), ('factory', 0.023296929142472642), ('descriptor', 0.018411919902713396), ('variable', 0.01830284543802066), ('aggregation', 0.01390203088785397)]
        if (autoFollowPattern == null) {
            throw new ResourceNotFoundException("auto-follow pattern [{}] is missing", name);
        }
        return Collections.singletonMap(name, autoFollowPattern);
    }
}
