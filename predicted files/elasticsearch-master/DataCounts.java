// Type of training: selfTestingIdentifier
// Path to file: C:\Users\Igor\IdeaProjects\SLP-Core-0.3\java-projects\elasticsearch-master\x-pack\plugin\core\src\main\java\org\elasticsearch\xpack\core\ml\dataframe\stats\common\DataCounts.java
// Number of identifiers: 17	Accuracy: 88.24%	MRR: 93.14%
// True 	Rank of true in predictions : [(predicted token, probability of a token), ...]

package org.elasticsearch.xpack.core.ml.dataframe.stats.common;

import org.elasticsearch.common.ParseField;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.common.io.stream.StreamOutput;
import org.elasticsearch.common.io.stream.Writeable;
import org.elasticsearch.common.xcontent.ConstructingObjectParser;
import org.elasticsearch.common.xcontent.ToXContentObject;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.xpack.core.ml.dataframe.stats.Fields;
import org.elasticsearch.xpack.core.ml.utils.ToXContentParams;
import java.io.IOException;
import java.util.Objects;

public class DataCounts implements ToXContentObject, Writeable {

    public static final String TYPE_VALUE = "analytics_data_counts";

    public static final ParseField TRAINING_DOCS_COUNT = new ParseField("training_docs_count");

    public static final ParseField TEST_DOCS_COUNT = new ParseField("test_docs_count");

    public static final ParseField SKIPPED_DOCS_COUNT = new ParseField("skipped_docs_count");

    public static final ConstructingObjectParser<DataCounts, Void> STRICT_PARSER = createParser(false);

    public static final ConstructingObjectParser<DataCounts, Void> LENIENT_PARSER = createParser(true);

    private static ConstructingObjectParser<DataCounts, Void> createParser(boolean ignoreUnknownFields) {
// ignoreUnknownFields  0	: [('ignoreUnknownFields', 0.9187610858581705), ('lenient', 0.3337730968389445), ('equals', 0.004146453532148857), ('acknowledged', 0.0038440193644018084), ('complete', 0.0033744061632392758), ('enabled', 0.0015721531961474789), ('available', 0.001401351603738567), ('coerce', 0.0012234076965923983), ('value', 0.0010832887551494553), ('a', 0.0010249721820062856)]
        ConstructingObjectParser<DataCounts, Void> parser = new ConstructingObjectParser<>(TYPE_VALUE, ignoreUnknownFields, a -> new DataCounts((String) a[0], (long) a[1], (long) a[2], (long) a[3]));
// a                    0	: [('a', 0.9252006912028201), ('args', 0.37539372406735855), ('bucket', 0.03874313750704523), ('i', 0.02820931140426903), ('termsBucket', 0.015811781375494535), ('numDocs', 0.015323174592172875), ('parsed', 0.013927562526590593), ('value', 0.012662158586791837), ('invocationOnMock', 0.010200990249501577), ('v', 0.00931501045529136)]
// parser               0	: [('parser', 0.7640385179310416), ('builder', 0.15163695323362822), ('objectParser', 0.10609378824193613), ('listener', 0.09853431498502403), ('shardsParser', 0.08488270351575843), ('request', 0.06728995338821873), ('channel', 0.05433600000957845), ('result', 0.024839909509749285), ('i', 0.022315829354621092), ('createParser', 0.02221467256419622)]
        parser.declareString((bucket, s) -> {
// bucket               2	: [('builder', 0.1914030929613305), ('request', 0.15202282609794637), ('bucket', 0.09187785638173347), ('map', 0.056066993797997525), ('c', 0.03573155335515652), ('action', 0.03566308709873028), ('response', 0.02118658535615035), ('agg', 0.02101505174024918), ('entry', 0.020552683229759304), ('anomalyRecord', 0.02034602840388637)]
// s                    0	: [('s', 0.7576126422152341), ('i', 0.01768087117610525), ('fingerprint', 0.017669140921462215), ('bucketCount', 0.012624496084957571), ('index', 0.0076989296244856995), ('kahanSummation', 0.007572623582973268), ('state', 0.006405385383079241), ('values', 0.006361674573078173), ('max', 0.005074638259306067), ('min', 0.005065083214853622)]
        }, Fields.TYPE);
        parser.declareString(ConstructingObjectParser.constructorArg(), Fields.JOB_ID);
        parser.declareLong(ConstructingObjectParser.constructorArg(), TRAINING_DOCS_COUNT);
        parser.declareLong(ConstructingObjectParser.constructorArg(), TEST_DOCS_COUNT);
        parser.declareLong(ConstructingObjectParser.constructorArg(), SKIPPED_DOCS_COUNT);
        return parser;
    }

    private final String jobId;

    private final long trainingDocsCount;

    private final long testDocsCount;

    private final long skippedDocsCount;

    public DataCounts(String jobId) {
// jobId                0	: [('jobId', 0.8772223141974433), ('name', 0.03533429280855598), ('id', 0.021379820293432692), ('source', 0.019792538137307913), ('settings', 0.018542754655117982), ('shardId', 0.014663907050679081), ('client', 0.014109098752652862), ('fieldName', 0.013912216793072465), ('index', 0.012138746982618047), ('in', 0.00946203974799323)]
        this(jobId, 0, 0, 0);
    }

    public DataCounts(String jobId, long trainingDocsCount, long testDocsCount, long skippedDocsCount) {
// jobId                0	: [('jobId', 0.8772223164294325), ('name', 0.03841140561457919), ('client', 0.023671506284278172), ('type', 0.019300444376912224), ('id', 0.017888726237083173), ('source', 0.012943498591642616), ('auditor', 0.010969841029631583), ('index', 0.010032739025265639), ('indices', 0.009505639443332202), ('xContentType', 0.0081898487041136)]
// trainingDocsCount    1	: [('in', 0.7092951930775296), ('trainingDocsCount', 0.2083337004327337), ('bucketCount', 0.1595108347602821), ('searchCount', 0.15923706195682422), ('retentionDays', 0.15916404339881765), ('modelBytes', 0.07967768577358664), ('cutoffEpochMs', 0.07967575652306189), ('processedRecordCount', 0.07962440785278574), ('id', 0.04038042962149568), ('numDocs', 0.04002869140002854)]
// testDocsCount        0	: [('testDocsCount', 0.7501069147926398), ('in', 0.45929519307752964), ('primaryTerm', 0.005329012661375796), ('docCount', 0.005311277236018148), ('version', 0.005046289662114145), ('seqNo', 0.004439889264333583), ('bucket', 0.004142947119469152), ('count', 0.0035363359387335603), ('startTime', 0.002926392538347194), ('position', 0.002653886891073899)]
// skippedDocsCount     0	: [('skippedDocsCount', 0.8750179178746033), ('in', 0.45929519307752964), ('primaryTerm', 0.002664506330687898), ('docCount', 0.002655638618009074), ('version', 0.0025231448310570723), ('seqNo', 0.0022199446321667913), ('bucket', 0.002071473559734576), ('count', 0.0017681679693667802), ('startTime', 0.001463196269173597), ('position', 0.0013269434455369495)]
        this.jobId = Objects.requireNonNull(jobId);
        this.trainingDocsCount = trainingDocsCount;
        this.testDocsCount = testDocsCount;
        this.skippedDocsCount = skippedDocsCount;
    }

    public DataCounts(StreamInput in) throws IOException {
// in                   0	: [('in', 0.9643366598533049), ('trainingDocsCount', 0.9166672404837389), ('testDocsCount', 0.9166672404837389), ('skippedDocsCount', 0.9166672293237643), ('jobId', 0.755561975805883), ('modelSizeStats', 0.02314834614688916), ('modelSnapshot', 0.022737692674943927), ('other', 0.015517160264129226), ('config', 0.012844858363024654), ('restRequest', 0.0037003613649160753)]
        this.jobId = in.readString();
        this.trainingDocsCount = in.readVLong();
        this.testDocsCount = in.readVLong();
        this.skippedDocsCount = in.readVLong();
    }

    @Override
    public void writeTo(StreamOutput out) throws IOException {
// out                  0	: [('out', 0.9640272642052823), ('builder', 0.5079802395418929), ('request', 0.03463239945933867), ('client', 0.0165991965589725), ('i', 0.011157843155810773), ('job', 0.010983538764527215), ('getBucketsRequest', 0.008223044017424385), ('flushRequest', 0.0054814624942589735), ('closeJobRequest', 0.005480440072074127), ('jobBuilder', 0.0041312048054804925)]
        out.writeString(jobId);
        out.writeVLong(trainingDocsCount);
        out.writeVLong(testDocsCount);
        out.writeVLong(skippedDocsCount);
    }

    @Override
    public XContentBuilder toXContent(XContentBuilder builder, Params params) throws IOException {
// builder              0	: [('builder', 0.9544494938427712), ('out', 0.06352723982970902), ('doc', 0.04177556830721504), ('mappings', 0.027887424568418695), ('changed', 0.02780540963484504), ('searcher', 0.027742811282747557), ('server', 0.02772407167829349), ('proxyServer', 0.027721729684093067), ('fieldStats', 0.02770767661120297), ('ingestPipeline', 0.027701599295580893)]
// params               0	: [('params', 0.9623004747057369), ('token', 0.03665692191843413), ('in', 0.03285380347695218), ('i', 0.012368403552282902), ('request', 0.009739088074797226), ('value', 0.009124074309317554), ('parser', 0.007884677078961752), ('response', 0.006287950498530833), ('size', 0.006270972035515764), ('state', 0.0055185808101594555)]
        builder.startObject();
        if (params.paramAsBoolean(ToXContentParams.FOR_INTERNAL_STORAGE, false)) {
            builder.field(Fields.TYPE.getPreferredName(), TYPE_VALUE);
            builder.field(Fields.JOB_ID.getPreferredName(), jobId);
        }
        builder.field(TRAINING_DOCS_COUNT.getPreferredName(), trainingDocsCount);
        builder.field(TEST_DOCS_COUNT.getPreferredName(), testDocsCount);
        builder.field(SKIPPED_DOCS_COUNT.getPreferredName(), skippedDocsCount);
        builder.endObject();
        return builder;
    }

    @Override
    public boolean equals(Object o) {
// o                    0	: [('o', 0.6511638478611426), ('other', 0.5208595673002624), ('obj', 0.3580834481494799), ('a', 0.12506589511357724), ('args', 0.03130283686853655), ('object', 0.015138908039715985), ('value', 0.007518806916898216), ('i', 0.004311057132440561), ('token', 0.0036089344840049835), ('in', 0.0031309968956647455)]
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        DataCounts that = (DataCounts) o;
// that                 0	: [('that', 0.938925290221339), ('other', 0.5410394342383847), ('request', 0.0832986822890753), ('dataCounts', 0.03471097338276144), ('o', 0.019618162658498667), ('counts2', 0.017417318453017104), ('stats', 0.01236770916670435), ('snapshotId', 0.01148098788699732), ('obj', 0.007848490562290717), ('tasks', 0.006777804059372674)]
        return Objects.equals(jobId, that.jobId) && trainingDocsCount == that.trainingDocsCount && testDocsCount == that.testDocsCount && skippedDocsCount == that.skippedDocsCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(jobId, trainingDocsCount, testDocsCount, skippedDocsCount);
    }

    public static String documentId(String jobId) {
// jobId                0	: [('jobId', 0.7612992251767334), ('transformId', 0.14519766755160046), ('id', 0.09957714674310716), ('datafeedId', 0.048692125581432495), ('calendarId', 0.048449228323601594), ('filterId', 0.048386016118396206), ('eventId', 0.04831269349007469), ('i', 0.02631717194596598), ('job', 0.019371007211183046), ('rec', 0.013814289677264025)]
        return TYPE_VALUE + "_" + jobId;
    }

    public String getJobId() {
        return jobId;
    }

    public long getTrainingDocsCount() {
        return trainingDocsCount;
    }

    public long getTestDocsCount() {
        return testDocsCount;
    }

    public long getSkippedDocsCount() {
        return skippedDocsCount;
    }
}
