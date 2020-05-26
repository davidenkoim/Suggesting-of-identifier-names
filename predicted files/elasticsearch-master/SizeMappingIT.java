// Type of training: selfTestingIdentifier
// Path to file: C:\Users\Igor\IdeaProjects\SLP-Core-0.3\java-projects\elasticsearch-master\plugins\mapper-size\src\test\java\org\elasticsearch\index\mapper\size\SizeMappingIT.java
// Number of identifiers: 16	Accuracy: 56.25%	MRR: 64.58%
// True 	Rank of true in predictions : [(predicted token, probability of a token), ...]

package org.elasticsearch.index.mapper.size;

import org.elasticsearch.action.admin.indices.mapping.get.GetMappingsResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.plugin.mapper.MapperSizePlugin;
import org.elasticsearch.plugins.Plugin;
import org.elasticsearch.test.ESIntegTestCase;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;
import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;
import static org.elasticsearch.test.hamcrest.ElasticsearchAssertions.assertAcked;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class SizeMappingIT extends ESIntegTestCase {

    @Override
    protected Collection<Class<? extends Plugin>> nodePlugins() {
        return Arrays.asList(MapperSizePlugin.class);
    }

    public void testThatUpdatingMappingShouldNotRemoveSizeMappingConfiguration() throws Exception {
        String index = "foo";
// index                0	: [('index', 0.9168792012215551), ('indexName', 0.30737280819713186), ('mapping', 0.1850792577154175), ('leaderIndex', 0.15365446232980165), ('sourceIndex', 0.09978639898671637), ('jobId', 0.07487282944009266), ('password', 0.03890142531179864), ('groupSearchBase', 0.029096721695360248), ('json', 0.02346631023376665), ('transformId', 0.023057853769285194)]
        XContentBuilder builder = jsonBuilder().startObject().startObject("_size").field("enabled", true).endObject().endObject();
// builder              0	: [('builder', 0.9243539959170459), ('mapping', 0.2871950593053998), ('mappingBuilder', 0.046488943826900664), ('xContentBuilder', 0.028934410596369364), ('mappings', 0.027976509424619506), ('queryFieldName', 0.007232027210972118), ('toXContent', 0.005798888671705609), ('sortField', 0.004827175214523231), ('queryShapesMapping', 0.004821176497841999), ('source', 0.0029547526475402985)]
        assertAcked(client().admin().indices().prepareCreate(index).setMapping(builder));
        assertSizeMappingEnabled(index, true);
        XContentBuilder updateMappingBuilder = jsonBuilder().startObject().startObject("properties").startObject("otherField").field("type", "text").endObject().endObject().endObject();
// updateMappingBuilder 0	: [('updateMappingBuilder', 0.5836547569813202), ('source', 0.24599229183856583), ('field', 0.1980750758921388), ('builder', 0.19288624613415156), ('mapping', 0.09197312368445781), ('sourceClass', 0.07918954035001854), ('leaderIndexSettings', 0.055525373956223166), ('mappingUpdate', 0.0395950371777134), ('sourceNotClass', 0.039594834745927766), ('xContentBuilder', 0.017029187617365292)]
        AcknowledgedResponse putMappingResponse = client().admin().indices().preparePutMapping(index).setSource(updateMappingBuilder).get();
// putMappingResponse   1	: [('client', 0.39319569139209276), ('putMappingResponse', 0.30626251233085555), ('acknowledgedResponse', 0.26081290924257117), ('response', 0.1728952544717354), ('builder', 0.09565119409044977), ('createIndexResponse', 0.06003175866910935), ('putLifecycleResponse', 0.05002463570611161), ('putRepositoryResponse', 0.04647902056445823), ('closeIndexResponse', 0.03001579392517053), ('ack', 0.028219165758954487)]
        assertAcked(putMappingResponse);
        assertSizeMappingEnabled(index, true);
    }

    public void testThatSizeCanBeSwitchedOnAndOff() throws Exception {
        String index = "foo";
// index                0	: [('index', 0.9168792012215551), ('indexName', 0.30737280819713186), ('mapping', 0.1850792577154175), ('leaderIndex', 0.15365446232980165), ('sourceIndex', 0.09978639898671637), ('jobId', 0.07487282944009266), ('password', 0.03890142531179864), ('groupSearchBase', 0.029096721695360248), ('json', 0.02346631023376665), ('transformId', 0.023057853769285194)]
        XContentBuilder builder = jsonBuilder().startObject().startObject("_size").field("enabled", true).endObject().endObject();
// builder              0	: [('builder', 0.9243539959170459), ('mapping', 0.2871950593053998), ('mappingBuilder', 0.046488943826900664), ('xContentBuilder', 0.028934410596369364), ('mappings', 0.027976509424619506), ('queryFieldName', 0.007232027210972118), ('toXContent', 0.005798888671705609), ('sortField', 0.004827175214523231), ('queryShapesMapping', 0.004821176497841999), ('source', 0.0029547526475402985)]
        assertAcked(client().admin().indices().prepareCreate(index).setMapping(builder));
        assertSizeMappingEnabled(index, true);
        XContentBuilder updateMappingBuilder = jsonBuilder().startObject().startObject("_size").field("enabled", false).endObject().endObject();
// updateMappingBuilder 0	: [('updateMappingBuilder', 0.5836547569813202), ('source', 0.24599229183856583), ('field', 0.1980750758921388), ('builder', 0.19288624613415156), ('mapping', 0.09197312368445781), ('sourceClass', 0.07918954035001854), ('leaderIndexSettings', 0.055525373956223166), ('mappingUpdate', 0.0395950371777134), ('sourceNotClass', 0.039594834745927766), ('xContentBuilder', 0.017029187617365292)]
        AcknowledgedResponse putMappingResponse = client().admin().indices().preparePutMapping(index).setSource(updateMappingBuilder).get();
// putMappingResponse   1	: [('client', 0.39319569139209276), ('putMappingResponse', 0.30626251233085555), ('acknowledgedResponse', 0.26081290924257117), ('response', 0.1728952544717354), ('builder', 0.09565119409044977), ('createIndexResponse', 0.06003175866910935), ('putLifecycleResponse', 0.05002463570611161), ('putRepositoryResponse', 0.04647902056445823), ('closeIndexResponse', 0.03001579392517053), ('ack', 0.028219165758954487)]
        assertAcked(putMappingResponse);
        assertSizeMappingEnabled(index, false);
    }

    private void assertSizeMappingEnabled(String index, boolean enabled) throws IOException {
// index                0	: [('index', 0.39604594382634506), ('indexName', 0.2639700303262899), ('i', 0.1950243771061055), ('indices', 0.1319967394920102), ('sourceIdx', 0.13194628397805333), ('name', 0.08144192393112341), ('key', 0.030711717874009954), ('id', 0.023889841457099034), ('aliasName', 0.02351515140643421), ('hiddenIndex', 0.022490641817276877)]
// enabled              No	: [('i', 0.41243235943107615), ('activePrimaryLocal', 0.266204188685148), ('managedByILM', 0.1774695311865951), ('includeFrozen', 0.08900274862287806), ('force', 0.08892477256032037), ('onlyExpungeDeletes', 0.08883261885914008), ('search', 0.08876231001820606), ('allowNoIndices', 0.03335147837486455), ('userIsAllowed', 0.03316171788603338), ('index', 0.030844819919596603)]
        String errMsg = String.format(Locale.ROOT, "Expected size field mapping to be " + (enabled ? "enabled" : "disabled") + " for %s", index);
// errMsg               No	: [('mapping', 0.12107626135269653), ('json', 0.11831013568723622), ('response', 0.08173344486541474), ('endpoint', 0.06563586321903875), ('e', 0.05275852879099913), ('query', 0.04808635689643137), ('jobId', 0.04147071281455985), ('fieldName', 0.03645109882374642), ('result', 0.03166142010874542), ('searchResponse', 0.030399206830648336)]
        GetMappingsResponse getMappingsResponse = client().admin().indices().prepareGetMappings(index).get();
// getMappingsResponse  2	: [('response', 0.6308772707281446), ('mappingMetadata', 0.6145861773506129), ('getMappingsResponse', 0.1832340078306841), ('mapping', 0.1521150256027392), ('mappings', 0.15209836092339463), ('getMappingResponse', 0.04194923019475782), ('mappingsResponse', 0.022212397017451337), ('resp', 0.011109780537806714), ('testMappingsResponse', 0.010487504609428702), ('request', 0.006482647861203588)]
        Map<String, Object> mappingSource = getMappingsResponse.getMappings().get(index).getSourceAsMap();
// mappingSource        No	: [('options', 0.3125833604643028), ('suggestionList', 0.3125008031970073), ('sizeAsString', 0.3125002145147288), ('metadata', 0.1431065263048414), ('map', 0.04877492475092438), ('params', 0.04047471136260366), ('config', 0.035525160500424285), ('source', 0.033757827664139005), ('entry', 0.022127030407093556), ('in', 0.015389756368251172)]
        assertThat(errMsg, mappingSource, hasKey("_size"));
        String sizeAsString = mappingSource.get("_size").toString();
// sizeAsString         No	: [('options', 0.3125833602729562), ('mappingSource', 0.3125019303393374), ('suggestionList', 0.3125008031954226), ('response', 0.08173302080615776), ('fieldName', 0.03043214713336931), ('searchResponse', 0.030399073610332557), ('e', 0.026308949931590046), ('result', 0.02396142986728686), ('clusterState', 0.01845926188042976), ('buckets', 0.015989236123706924)]
        assertThat(sizeAsString, is(notNullValue()));
        assertThat(errMsg, sizeAsString, is("{enabled=" + (enabled) + "}"));
    }

    public void testBasic() throws Exception {
        assertAcked(prepareCreate("test").setMapping("_size", "enabled=true"));
        final String source = "{\"f\":10}";
// source               0	: [('source', 0.20447447251148604), ('docSource', 0.18406579652054147), ('doc', 0.08788830580347845), ('data', 0.07954714855294083), ('expected', 0.058929804292987845), ('leaderIndexSettings', 0.05552537395870694), ('docBody', 0.05226645896305841), ('mapping', 0.02907801390726776), ('transformed', 0.02639064782768398), ('doc1', 0.026134738345921126)]
        indexRandom(true, client().prepareIndex("test").setId("1").setSource(source, XContentType.JSON));
        GetResponse getResponse = client().prepareGet("test", "1").setStoredFields("_size").get();
// getResponse          0	: [('getResponse', 0.6580815236867523), ('nbDocs', 0.20344523816998603), ('tuple', 0.1017238455460572), ('localCheckPoint', 0.10172239279743829), ('singleBucketAggregations', 0.10172239056544721), ('response', 0.07191347068375864), ('a', 0.05228321988525408), ('getResult', 0.04592868350758486), ('firstGet', 0.045703299726539116), ('notFoundResponse', 0.04070891963025833)]
        assertNotNull(getResponse.getField("_size"));
        assertEquals(source.length(), (int) getResponse.getField("_size").getValue());
    }
}
