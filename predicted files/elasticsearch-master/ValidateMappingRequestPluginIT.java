// Type of training: selfTestingIdentifier
// Path to file: C:\Users\Igor\IdeaProjects\SLP-Core-0.3\java-projects\elasticsearch-master\server\src\test\java\org\elasticsearch\action\admin\indices\mapping\put\ValidateMappingRequestPluginIT.java
// Number of identifiers: 16	Accuracy: 93.75%	MRR: 96.88%
// True 	Rank of true in predictions : [(predicted token, probability of a token), ...]

package org.elasticsearch.action.admin.indices.mapping.put;

import org.elasticsearch.action.RequestValidators;
import org.elasticsearch.common.util.concurrent.ConcurrentCollections;
import org.elasticsearch.index.Index;
import org.elasticsearch.plugins.ActionPlugin;
import org.elasticsearch.plugins.Plugin;
import org.elasticsearch.test.ESSingleNodeTestCase;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import static org.elasticsearch.test.hamcrest.ElasticsearchAssertions.assertAcked;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class ValidateMappingRequestPluginIT extends ESSingleNodeTestCase {

    static final Map<String, Collection<String>> allowedOrigins = ConcurrentCollections.newConcurrentMap();

    public static class TestPlugin extends Plugin implements ActionPlugin {

        @Override
        public Collection<RequestValidators.RequestValidator<PutMappingRequest>> mappingRequestValidators() {
            return Collections.singletonList((request, state, indices) -> {
// request              1	: [('index', 0.9379684660841927), ('request', 0.4071441965847755), ('dn', 0.3958466405238158), ('name', 0.09166669563873095), ('actionName', 0.07440597529636739), ('s', 0.04695389340628348), ('v', 0.042533498617783515), ('jobId', 0.03931678395043583), ('version', 0.03923158467211605), ('datafeedId', 0.03870336021384612)]
// state                0	: [('state', 0.509757650373109), ('jobId', 0.06830992293630392), ('val', 0.039833192349414945), ('bulkListener', 0.028399678327694886), ('channel', 0.0230803256819928), ('snapshotId', 0.017085366858554912), ('listener', 0.016564899958888747), ('builder', 0.011985442264261075), ('timeout', 0.011755371510093282), ('id', 0.011583406127869688)]
// indices              0	: [('indices', 0.40522859496271574), ('concreteIndices', 0.1338248509096509), ('listener', 0.09150558901245107), ('stats', 0.06753439200623437), ('finalListener', 0.06687819803358411), ('request', 0.05768641293799498), ('closeIndices', 0.05751374082393151), ('indicesToClose', 0.0572620176066431), ('openIndices', 0.05726201314266476), ('randomDataStream', 0.0572603077812897)]
                for (Index index : indices) {
// index                0	: [('index', 0.8424435569854662), ('e', 0.06993502869146846), ('bucket', 0.06905463393533678), ('attr', 0.04580574171607003), ('type', 0.03477101595748945), ('field', 0.032458348156394846), ('key', 0.028055190548485192), ('name', 0.025323402831378426), ('path', 0.019039443906717392), ('a', 0.01846550139777742)]
                    if (allowedOrigins.getOrDefault(index.getName(), Collections.emptySet()).contains(request.origin()) == false) {
                        return Optional.of(new IllegalStateException("not allowed: index[" + index.getName() + "] origin[" + request.origin() + "]"));
                    }
                }
                return Optional.empty();
            });
        }
    }

    @Override
    protected Collection<Class<? extends Plugin>> getPlugins() {
        return Collections.singletonList(TestPlugin.class);
    }

    public void testValidateMappingRequest() {
        createIndex("index_1");
        createIndex("index_2");
        allowedOrigins.put("index_1", Arrays.asList("1", "2"));
        allowedOrigins.put("index_2", Arrays.asList("2", "3"));
        {
            String origin = randomFrom("", "3", "4", "5");
// origin               0	: [('origin', 0.7851791144278882), ('id', 0.5382179322855876), ('mapping', 0.14663413655627588), ('i', 0.058962537833803626), ('source', 0.04834097881704773), ('unknownMetric1', 0.0443488050480244), ('invalidAction', 0.04434390900993559), ('value', 0.041973656821346204), ('request', 0.04140216532313764), ('type', 0.032540894494179996)]
            PutMappingRequest request = new PutMappingRequest().indices("index_1").source("t1", "type=keyword").origin(origin);
// request              0	: [('request', 0.84056221543769), ('putMappingRequest', 0.20867721417396062), ('request2', 0.05863749924597097), ('mapping', 0.05015084366771306), ('pmReq', 0.029318599971227786), ('mappings', 0.014256985456250626), ('result', 0.010937871670579935), ('originalMappingMetadata', 0.010800238274480555), ('mappingType', 0.010341959754568744), ('mappingMd', 0.006894394309627016)]
            Exception e = expectThrows(IllegalStateException.class, () -> client().admin().indices().putMapping(request).actionGet());
// e                    0	: [('e', 0.8839076696175263), ('response', 0.0689466819948097), ('result', 0.03166109245525727), ('clusterState', 0.02660130148844942), ('bucket', 0.02297092635173799), ('exception', 0.02278021721104356), ('searchResponse', 0.01966149112770082), ('stats', 0.01861978223296321), ('request', 0.018474171323430662), ('terms', 0.009629565456883848)]
            assertThat(e.getMessage(), equalTo("not allowed: index[index_1] origin[" + origin + "]"));
        }
        {
            PutMappingRequest request = new PutMappingRequest().indices("index_1").origin(randomFrom("1", "2")).source("t1", "type=keyword");
// request              0	: [('request', 0.9274097429102175), ('putMappingRequest', 0.20867721417396062), ('request2', 0.05863749924597097), ('mapping', 0.05015084366771306), ('pmReq', 0.029318599971227786), ('mappings', 0.014256985456250626), ('result', 0.010937871670579935), ('originalMappingMetadata', 0.010800238274480555), ('mappingType', 0.010341959754568744), ('mappingMd', 0.006894394309627016)]
            assertAcked(client().admin().indices().putMapping(request).actionGet());
        }
        {
            String origin = randomFrom("", "1", "4", "5");
// origin               0	: [('origin', 0.7851791144278882), ('mapping', 0.28520974223714474), ('badConfigMapping', 0.07886601096304069), ('i', 0.058962537833803626), ('docId', 0.05643423001357274), ('nonExistentIndex', 0.056370731892503544), ('indexName', 0.04695379404361551), ('unknownMetric1', 0.0443488050480244), ('invalidAction', 0.04434390900993559), ('value', 0.041973656821346204)]
            PutMappingRequest request = new PutMappingRequest().indices("index_2").source("t2", "type=keyword").origin(origin);
// request              0	: [('request', 0.84056221543769), ('putMappingRequest', 0.20867721417396062), ('request2', 0.05863749924597097), ('mapping', 0.05015084366771306), ('pmReq', 0.029318599971227786), ('mappings', 0.014256985456250626), ('result', 0.010937871670579935), ('originalMappingMetadata', 0.010800238274480555), ('mappingType', 0.010341959754568744), ('mappingMd', 0.006894394309627016)]
            Exception e = expectThrows(IllegalStateException.class, () -> client().admin().indices().putMapping(request).actionGet());
// e                    0	: [('e', 0.8839076696175263), ('response', 0.0689466819948097), ('result', 0.03166109245525727), ('clusterState', 0.02660130148844942), ('bucket', 0.02297092635173799), ('exception', 0.02278021721104356), ('searchResponse', 0.01966149112770082), ('stats', 0.01861978223296321), ('request', 0.018474171323430662), ('terms', 0.009629565456883848)]
            assertThat(e.getMessage(), equalTo("not allowed: index[index_2] origin[" + origin + "]"));
        }
        {
            PutMappingRequest request = new PutMappingRequest().indices("index_2").origin(randomFrom("2", "3")).source("t1", "type=keyword");
// request              0	: [('request', 0.9274097429102175), ('putMappingRequest', 0.20867721417396062), ('request2', 0.05863749924597097), ('mapping', 0.05015084366771306), ('pmReq', 0.029318599971227786), ('mappings', 0.014256985456250626), ('result', 0.010937871670579935), ('originalMappingMetadata', 0.010800238274480555), ('mappingType', 0.010341959754568744), ('mappingMd', 0.006894394309627016)]
            assertAcked(client().admin().indices().putMapping(request).actionGet());
        }
        {
            String origin = randomFrom("", "1", "3", "4");
// origin               0	: [('origin', 0.7851791166534381), ('mapping', 0.2852097422305468), ('badConfigMapping', 0.0788660109630139), ('docId', 0.056434230011407224), ('nonExistentIndex', 0.056370731892375375), ('indexName', 0.04695379403629253), ('restContent', 0.04085594819231883), ('id', 0.03627557638022251), ('template', 0.022744082620857187), ('minShouldMatch', 0.02253362497563063)]
            PutMappingRequest request = new PutMappingRequest().indices("*").source("t3", "type=keyword").origin(origin);
// request              0	: [('request', 0.5022192399172242), ('putMappingRequest', 0.3608300121665066), ('request2', 0.05863749924597097), ('mapping', 0.05015084366771306), ('pmReq', 0.029318599971227786), ('mappings', 0.014256985456250626), ('result', 0.010937871670579935), ('originalMappingMetadata', 0.010800238274480555), ('mappingType', 0.010341959754568744), ('source', 0.007386757079072748)]
            Exception e = expectThrows(IllegalStateException.class, () -> client().admin().indices().putMapping(request).actionGet());
// e                    0	: [('e', 0.8839076696175263), ('response', 0.0689466819948097), ('result', 0.03166109245525727), ('clusterState', 0.02660130148844942), ('bucket', 0.02297092635173799), ('exception', 0.02278021721104356), ('searchResponse', 0.01966149112770082), ('stats', 0.01861978223296321), ('request', 0.018474171323430662), ('terms', 0.009629565456883848)]
            assertThat(e.getMessage(), containsString("not allowed:"));
        }
        {
            PutMappingRequest request = new PutMappingRequest().indices("index_2").origin("2").source("t3", "type=keyword");
// request              0	: [('request', 0.9274097429102175), ('putMappingRequest', 0.20867721417396062), ('request2', 0.05863749924597097), ('mapping', 0.05015084366771306), ('pmReq', 0.029318599971227786), ('mappings', 0.014256985456250626), ('result', 0.010937871670579935), ('originalMappingMetadata', 0.010800238274480555), ('mappingType', 0.010341959754568744), ('mappingMd', 0.006894394309627016)]
            assertAcked(client().admin().indices().putMapping(request).actionGet());
        }
    }
}
