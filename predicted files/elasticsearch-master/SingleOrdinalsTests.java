// Type of training: selfTestingIdentifier
// Path to file: C:\Users\Igor\IdeaProjects\SLP-Core-0.3\java-projects\elasticsearch-master\server\src\test\java\org\elasticsearch\index\fielddata\ordinals\SingleOrdinalsTests.java
// Number of identifiers: 14	Accuracy: 42.86%	MRR: 57.86%
// True 	Rank of true in predictions : [(predicted token, probability of a token), ...]

package org.elasticsearch.index.fielddata.ordinals;

import org.apache.lucene.index.DocValues;
import org.apache.lucene.index.SortedDocValues;
import org.apache.lucene.index.SortedSetDocValues;
import org.elasticsearch.test.ESTestCase;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.not;

public class SingleOrdinalsTests extends ESTestCase {

    public void testSvValues() throws IOException {
        int numDocs = 1000000;
// numDocs              0	: [('numDocs', 0.37085380106030824), ('maxDoc', 0.3708352864973279), ('values', 0.2041711911600396), ('reader', 0.18198575139427975), ('numOfDoc', 0.1420087915565814), ('ordinalPlan', 0.13350450498906907), ('length', 0.11118908723311571), ('size', 0.10646319493260906), ('endDoc', 0.10205768940725017), ('numDocsPerKey', 0.0710044581172162)]
        int numOrdinals = numDocs / 4;
// numOrdinals          0	: [('numOrdinals', 0.5000962130755213), ('i', 0.055942626830784), ('bufferSize', 0.022633785871178994), ('bulkActions', 0.022628357673520606), ('numUniqueGeoPoints', 0.022627000624106008), ('length', 0.018331597944651726), ('size', 0.013827821727511418), ('interval', 0.013623616531333113), ('period', 0.01132174723574236), ('unitMillis', 0.01131542624241647)]
        Map<Integer, Long> controlDocToOrdinal = new HashMap<>();
// controlDocToOrdinal  No	: [('ordAndId', 0.5002510608545179), ('expected', 0.3897960047879204), ('counts', 0.10853435281098411), ('fileSizes', 0.1078786973205597), ('strings', 0.10763654750724086), ('longs', 0.1076070831816255), ('manifest', 0.10760377513044983), ('sortedValues', 0.10760359880315438), ('otherStats', 0.10760357648324356), ('entry', 0.08615583148287521)]
        OrdinalsBuilder builder = new OrdinalsBuilder(numDocs);
// builder              1	: [('mergeMap', 0.890625607294378), ('builder', 0.8205532027557696), ('newDocDeltas', 0.500003261705676), ('ordinal', 0.18750801195435632), ('in', 0.07019497475165516), ('ords', 0.015627226144396125), ('ordinalPlan', 0.011719539454778305), ('i', 0.011157861745186043), ('ordinals', 0.007814938689543072), ('ordinalSet', 0.007813051494505273)]
        long ordinal = builder.currentOrdinal();
// ordinal              No	: [('sum', 0.6257328032656111), ('bucket', 0.17828362265051312), ('maxSeqNo', 0.12599635332269946), ('listener', 0.04161606184021474), ('value', 0.034133904312318136), ('retry', 0.033478560399933015), ('builder', 0.033461307006436895), ('validationException', 0.02507000705886842), ('ord', 0.022326267739655403), ('bucketOrd', 0.01790990973816729)]
        for (int doc = 0; doc < numDocs; doc++) {
// doc                  1	: [('i', 0.7859359433949058), ('doc', 0.7506160187397415), ('hashMap', 0.4908237914358632), ('ordAndId', 0.19632978079035673), ('obj1', 0.09990144013101945), ('j', 0.06044001612632712), ('key', 0.04129012049111921), ('docId', 0.035678984967380506), ('mapData', 0.02306577108245232), ('name', 0.020610299163975554)]
            if (doc % numOrdinals == 0) {
                ordinal = builder.nextOrdinal();
            }
            controlDocToOrdinal.put(doc, ordinal);
            builder.addDoc(doc);
        }
        Ordinals ords = builder.build();
// ords                 0	: [('ords', 0.9505212347780823), ('ordinals', 0.5852888969939347), ('response', 0.08173274950310123), ('searchResponse', 0.030398987953830198), ('e', 0.026308753523268086), ('result', 0.023961301163063074), ('clusterState', 0.018459156613048748), ('buckets', 0.015989220423676932), ('stats', 0.01364725818005568), ('request', 0.01163520903707487)]
        assertThat(ords, instanceOf(SinglePackedOrdinals.class));
        SortedSetDocValues docs = ords.ordinals();
// docs                 5	: [('values', 0.5813564923532079), ('sorted', 0.12167029976533783), ('raw', 0.12166965588462249), ('globalOrds', 0.12166915989754676), ('bytesValues', 0.08896186416996181), ('docs', 0.0782531137888146), ('segmentOrds', 0.06083460643028738), ('asMultiLongs', 0.06083456276090997), ('docValues', 0.022647170159856973), ('v1', 0.022377458972890812)]
        final SortedDocValues singleOrds = DocValues.unwrapSingleton(docs);
// singleOrds           2	: [('selected', 0.4128474785876599), ('selectedValues', 0.1933161526998981), ('singleOrds', 0.18654528588666985), ('singleValues', 0.08741349098575604), ('response', 0.06425704960937104), ('sortedValues', 0.061545317134571766), ('expected', 0.05892980448626667), ('entry', 0.054371946267264055), ('getResult', 0.046697216371932825), ('e', 0.04413416645792641)]
        assertNotNull(singleOrds);
        for (Map.Entry<Integer, Long> entry : controlDocToOrdinal.entrySet()) {
// entry                9	: [('i', 0.5256605698685579), ('expected', 0.3897775363840724), ('docId', 0.27559062932567413), ('args', 0.22069950137276545), ('a', 0.17411178352121476), ('expectedBucketCount', 0.103772005740784), ('doc', 0.07207441451659162), ('value', 0.058181257803163695), ('categoryTerms', 0.05161157375007925), ('entry', 0.04791245729923563)]
            assertTrue(singleOrds.advanceExact(entry.getKey()));
            assertEquals(singleOrds.ordValue(), (long) entry.getValue());
        }
    }

    public void testMvOrdinalsTrigger() throws IOException {
        int numDocs = 1000000;
// numDocs              0	: [('numDocs', 0.370853803286434), ('maxDoc', 0.37083528649684194), ('values', 0.20417119114929805), ('reader', 0.18198575138858472), ('numOfDoc', 0.1420087915565661), ('ordinalPlan', 0.13350450498902316), ('length', 0.11118908046412666), ('size', 0.1064631818964539), ('endDoc', 0.1020576894071067), ('numDocsPerKey', 0.07100445811721046)]
        OrdinalsBuilder builder = new OrdinalsBuilder(numDocs);
// builder              0	: [('builder', 0.8169075673494546), ('ordsBuilder', 0.7583340139697062), ('leaf', 0.5625455970184086), ('toClose', 0.040882223605690146), ('comparatorSource', 0.03363371678945106), ('ords', 0.03333778115907697), ('out', 0.02781959223872648), ('matchAssertion', 0.02643013536416294), ('ordinalPlan', 0.025001578909740262), ('filterFactory', 0.024040629265933495)]
        builder.nextOrdinal();
        for (int doc = 0; doc < numDocs; doc++) {
// doc                  1	: [('i', 0.7859359430827138), ('doc', 0.7506160230558179), ('docId', 0.5356789849354899), ('hashMap', 0.1783237914353148), ('ordAndId', 0.07132978078972084), ('j', 0.0604400161070937), ('obj1', 0.0374014401298433), ('mapData', 0.02306577108207175), ('obj', 0.010718362536117671), ('index', 0.004509641056266603)]
            builder.addDoc(doc);
        }
        Ordinals ords = builder.build();
// ords                 0	: [('ords', 0.9505212325463227), ('response', 0.08174376221620266), ('searchResponse', 0.030403399500663755), ('e', 0.02631020192256678), ('result', 0.02396354034345384), ('clusterState', 0.0184608137165974), ('buckets', 0.01599213813631728), ('ordinals', 0.015625213957570796), ('searchRequest', 0.014584266723131092), ('stats', 0.013648625788906648)]
        assertThat(ords, instanceOf(SinglePackedOrdinals.class));
        builder.nextOrdinal();
        builder.addDoc(0);
        ords = builder.build();
        assertThat(ords, not(instanceOf(SinglePackedOrdinals.class)));
    }
}
