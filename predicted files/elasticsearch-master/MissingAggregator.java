// Type of training: selfTestingIdentifier
// Path to file: C:\Users\Igor\IdeaProjects\SLP-Core-0.3\java-projects\elasticsearch-master\server\src\main\java\org\elasticsearch\search\aggregations\bucket\missing\MissingAggregator.java
// Number of identifiers: 13	Accuracy: 76.92%	MRR: 86.54%
// True 	Rank of true in predictions : [(predicted token, probability of a token), ...]

package org.elasticsearch.search.aggregations.bucket.missing;

import org.apache.lucene.index.LeafReaderContext;
import org.elasticsearch.index.fielddata.DocValueBits;
import org.elasticsearch.search.aggregations.Aggregator;
import org.elasticsearch.search.aggregations.AggregatorFactories;
import org.elasticsearch.search.aggregations.InternalAggregation;
import org.elasticsearch.search.aggregations.LeafBucketCollector;
import org.elasticsearch.search.aggregations.LeafBucketCollectorBase;
import org.elasticsearch.search.aggregations.bucket.BucketsAggregator;
import org.elasticsearch.search.aggregations.bucket.SingleBucketAggregator;
import org.elasticsearch.search.aggregations.support.ValuesSource;
import org.elasticsearch.search.internal.SearchContext;
import java.io.IOException;
import java.util.Map;

public class MissingAggregator extends BucketsAggregator implements SingleBucketAggregator {

    private final ValuesSource valuesSource;

    public MissingAggregator(String name, AggregatorFactories factories, ValuesSource valuesSource, SearchContext aggregationContext, Aggregator parent, Map<String, Object> metadata) throws IOException {
// name                 1	: [('in', 0.6871501796366066), ('name', 0.09266103212400212), ('index', 0.026752368921798258), ('id', 0.023889841206847354), ('field', 0.02210815434027943), ('source', 0.01967726308154028), ('jobId', 0.017804300615889942), ('key', 0.01718511150752174), ('fieldName', 0.01516891010559048), ('client', 0.013507635725988761)]
// factories            0	: [('factories', 0.9110705999304396), ('metadata', 0.10817192988837392), ('config', 0.083718544288863), ('context', 0.07884779485116183), ('queryShardContext', 0.040609555943682575), ('bucketsPaths', 0.03761984500282479), ('reader', 0.03716917733262861), ('valuesSource', 0.02440064244888096), ('docCount', 0.024095383370920834), ('transportService', 0.02400209647162909)]
// valuesSource         0	: [('valuesSource', 0.9328390918103113), ('entry', 0.0016584614987006144), ('vs', 0.001344637156172072), ('in', 0.0009618597441963009), ('getField', 0.0007719974548305131), ('parser', 0.0005246598882358563), ('orig', 0.00046364340612463936), ('response', 0.0003330443088217809), ('rawValuesSource', 0.0003088023649940006), ('client', 0.0002892665960641238)]
// aggregationContext   3	: [('context', 0.6819975278579271), ('valuesSource', 0.2837132840340138), ('searchContext', 0.1981210468769981), ('aggregationContext', 0.0842864801147994), ('interval', 0.027755600810241907), ('rounding', 0.027749415985248445), ('cellIdSource', 0.027735700460358302), ('ordinalsValuesSource', 0.027735615087076046), ('distanceSource', 0.012793336289489384), ('tag', 0.010124161663173526)]
// parent               0	: [('parent', 0.9522673656785194), ('aggregator', 0.004288838652294074), ('build', 0.0018701727269232187), ('create', 0.0006739915816183267), ('equalTo', 0.0005208214072442647), ('e', 0.00047686193646745526), ('children', 0.0004766863305275828), ('request', 0.0004748057308806729), ('first', 0.00034249025353697997), ('i', 0.00033016648616683466)]
// metadata             0	: [('metadata', 0.6107077447360527), ('valuesSource', 0.06841810850955068), ('map', 0.048774924742092045), ('subFactoriesBuilder', 0.04777550434202177), ('params', 0.04047471134575201), ('config', 0.03552516048936521), ('bucketCountThresholds', 0.034737608189147375), ('significanceHeuristic', 0.03473464132877243), ('source', 0.03375782764387456), ('collectionMode', 0.033317624673744516)]
        super(name, factories, aggregationContext, parent, metadata);
        this.valuesSource = valuesSource;
    }

    @Override
    public LeafBucketCollector getLeafCollector(LeafReaderContext ctx, final LeafBucketCollector sub) throws IOException {
// ctx                  0	: [('ctx', 0.782116339908399), ('values', 0.19322240726712636), ('bytes', 0.19310794020846697), ('context', 0.13585075461537255), ('geoPoints', 0.09649266654717882), ('ordinals', 0.09649237968750628), ('reader', 0.033453424316002275), ('leaf', 0.005123175889965013), ('readerContext', 0.003666687654261307), ('i', 0.0022453663293263964)]
// sub                  0	: [('sub', 0.9268580577716343), ('subCollector', 0.014718090484376589), ('leafCollector', 0.014151878863867293), ('collector', 0.006502788568491748), ('queueCollector', 0.003248667016317997), ('inner', 0.0021666580548133194), ('next', 0.001361023305226432), ('i', 0.0011226834768538895), ('request', 0.000718024101682873), ('e', 0.0006268537928061251)]
        final DocValueBits docsWithValue;
// docsWithValue        1	: [('values', 0.6031365649355059), ('docsWithValue', 0.5000040661368292), ('builder', 0.25782145375151966), ('parser', 0.10392193048785277), ('doc', 0.0893740812376217), ('listener', 0.0764949321201065), ('in', 0.05227797518845053), ('out', 0.043814807333506074), ('value', 0.043085624046168045), ('docAValues', 0.04139062556229901)]
        if (valuesSource != null) {
            docsWithValue = valuesSource.docsWithValue(ctx);
        } else {
            docsWithValue = new DocValueBits() {

                @Override
                public boolean advanceExact(int doc) throws IOException {
// doc                  0	: [('doc', 0.40070340895087586), ('target', 0.30899323848114135), ('docId', 0.09503070459830613), ('docID', 0.04935004917055639), ('i', 0.046302002634745536), ('parentDoc', 0.02454055830816189), ('j', 0.0037269810697248655), ('size', 0.0011481518538882414), ('index', 0.0010136732475166687), ('nodeOrdinal', 0.0008404358475106545)]
                    return false;
                }
            };
        }
        return new LeafBucketCollectorBase(sub, docsWithValue) {

            @Override
            public void collect(int doc, long bucket) throws IOException {
// doc                  0	: [('doc', 0.7995597542115368), ('i', 0.10264228126831453), ('docId', 0.10236251735781562), ('target', 0.0929674679633964), ('parentDoc', 0.07017015317361178), ('childDocId', 0.0701692503428705), ('values', 0.045444257221715134), ('segmentDocId', 0.017468866372406067), ('d', 0.012549834053229594), ('child', 0.010008970321102427)]
// bucket               0	: [('bucket', 0.7849259774091844), ('bucketOrd', 0.36316919781782764), ('bucketOrdinal', 0.23617691108493485), ('globalOrd', 0.029885817153936428), ('zeroBucket', 0.019036750513124263), ('i', 0.012539468066953009), ('seqNo', 0.010488647694936593), ('tookInNanos', 0.010467073967501085), ('owningBucketOrdinal', 0.009679146038683332), ('ord', 0.009661721283993438)]
                if (docsWithValue.advanceExact(doc) == false) {
                    collectBucket(sub, doc, bucket);
                }
            }
        };
    }

    @Override
    public InternalAggregation buildAggregation(long owningBucketOrdinal) throws IOException {
// owningBucketOrdinal  0	: [('owningBucketOrdinal', 0.8032667257046757), ('bucket', 0.33862447978973576), ('bucketOrd', 0.14141499141404723), ('i', 0.0812643555479494), ('zeroBucket', 0.015763234248656773), ('rhs', 0.008879618611897745), ('list', 0.007367670391700148), ('index', 0.003416009187572011), ('id', 0.0031673097477015687), ('size', 0.0028240248890047015)]
        return new InternalMissing(name, bucketDocCount(owningBucketOrdinal), bucketAggregations(owningBucketOrdinal), metadata());
    }

    @Override
    public InternalAggregation buildEmptyAggregation() {
        return new InternalMissing(name, 0, buildEmptySubAggregations(), metadata());
    }
}
