// Type of training: selfTestingIdentifier
// Path to file: C:\Users\Igor\IdeaProjects\SLP-Core-0.3\java-projects\cassandra-trunk\src\java\org\apache\cassandra\service\BatchlogResponseHandler.java
// Number of identifiers: 10	Accuracy: 70.00%	MRR: 75.00%
// True 	Rank of true in predictions : [(predicted token, probability of a token), ...]

package org.apache.cassandra.service;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import org.apache.cassandra.exceptions.RequestFailureReason;
import org.apache.cassandra.exceptions.WriteFailureException;
import org.apache.cassandra.exceptions.WriteTimeoutException;
import org.apache.cassandra.locator.InetAddressAndPort;
import org.apache.cassandra.net.Message;

public class BatchlogResponseHandler<T> extends AbstractWriteResponseHandler<T> {

    AbstractWriteResponseHandler<T> wrapped;

    BatchlogCleanup cleanup;

    protected volatile int requiredBeforeFinish;

    private static final AtomicIntegerFieldUpdater<BatchlogResponseHandler> requiredBeforeFinishUpdater = AtomicIntegerFieldUpdater.newUpdater(BatchlogResponseHandler.class, "requiredBeforeFinish");

    public BatchlogResponseHandler(AbstractWriteResponseHandler<T> wrapped, int requiredBeforeFinish, BatchlogCleanup cleanup, long queryStartNanoTime) {
// wrapped              0	: [('wrapped', 0.914793582404486), ('queryStartNanoTime', 0.5585652649177716), ('info', 0.5078900179459404), ('command', 0.23200335062964667), ('writeType', 0.14066608862227115), ('replicaPlan', 0.10238434444219258), ('cfs', 0.07070745005766493), ('pageSize', 0.05128024253964188), ('callback', 0.044504567106982626), ('targets', 0.025635922894144757)]
// requiredBeforeFinish No	: [('size', 0.5085875949551265), ('i', 0.03907199275392959), ('version', 0.0386622627716623), ('nowInSec', 0.015525904638134688), ('length', 0.00689804226935679), ('cfs', 0.006688084307456239), ('count', 0.005943632429217072), ('offset', 0.0057878465653933885), ('restrictions', 0.005282158593559186), ('keyspace', 0.004880286816463754)]
// cleanup              0	: [('cleanup', 0.6875068915317618), ('cmd', 0.5503852252925695), ('i', 0.0017354297573350607), ('cfs', 0.0016720210768640597), ('restrictions', 0.0013205396483897964), ('keyspace', 0.0012200717041159386), ('sstable', 0.0009848730462962251), ('row', 0.0008877545392610592), ('in', 0.0008843821344527309), ('builder', 0.0008718050871949652)]
// queryStartNanoTime   0	: [('queryStartNanoTime', 0.8814174670545363), ('wfe', 0.25610754614691716), ('wte', 0.256107352856955), ('cl', 0.0488525624869769), ('consistency', 0.012290022428852005), ('deserializedWfe', 0.006105998793102303), ('failureReasonByEndpoint', 0.0061039865092606845), ('timestamp', 0.006046852465730748), ('timeElapsed', 0.0034919091445945595), ('i', 0.003462471639658706)]
        super(wrapped.replicaPlan, wrapped.callback, wrapped.writeType, queryStartNanoTime);
        this.wrapped = wrapped;
        this.requiredBeforeFinish = requiredBeforeFinish;
        this.cleanup = cleanup;
    }

    protected int ackCount() {
        return wrapped.ackCount();
    }

    public void onResponse(Message<T> msg) {
// msg                  1	: [('message', 0.3802757670820267), ('msg', 0.15413726874654704), ('m', 0.12799452857605656), ('response', 0.1279039712338374), ('msgIn', 0.06389139940913062), ('out', 0.06187876900550331), ('obj', 0.030877095833427244), ('range', 0.004817992965300007), ('serializer', 0.004248734686918474), ('elementsClass', 0.0040733258611947674)]
        wrapped.onResponse(msg);
        if (requiredBeforeFinishUpdater.decrementAndGet(this) == 0)
            cleanup.ackMutation();
    }

    public void onFailure(InetAddressAndPort from, RequestFailureReason failureReason) {
// from                 0	: [('from', 0.8809859565180701), ('message', 0.17329729929046167), ('info', 0.17284722535239866), ('endpoint', 0.032423946147047515), ('t', 0.012675525765812982), ('ep', 0.006773147913403336), ('future', 0.006155696041045434), ('address', 0.004204813660867619), ('peer', 0.004074399454672345), ('i', 0.0034443986563092686)]
// failureReason        0	: [('failureReason', 0.9027735990772814), ('to', 0.31035857987171145), ('start', 0.05730284869929031), ('sessionIndex', 0.04876837082482793), ('out', 0.027032626624523604), ('keyspace', 0.019542322207097472), ('digest', 0.01906643370002892), ('that', 0.010887918520376879), ('value', 0.01034866890469578), ('count', 0.009746600760080526)]
        wrapped.onFailure(from, failureReason);
    }

    public boolean invokeOnFailure() {
        return wrapped.invokeOnFailure();
    }

    public void get() throws WriteTimeoutException, WriteFailureException {
        wrapped.get();
    }

    protected int blockFor() {
        return wrapped.blockFor();
    }

    protected int candidateReplicaCount() {
        return wrapped.candidateReplicaCount();
    }

    protected boolean waitingFor(InetAddressAndPort from) {
// from                 0	: [('from', 0.8809859565180701), ('message', 0.14392328615351335), ('endpoint', 0.032423946147047515), ('i', 0.006888797312618536), ('ep', 0.006773147913403336), ('address', 0.004204813660867619), ('peer', 0.004074399454672345), ('cfs', 0.0036492253347625925), ('target', 0.0034369293283572715), ('host', 0.002334836520915713)]
        return wrapped.waitingFor(from);
    }

    protected void signal() {
        wrapped.signal();
    }

    public static class BatchlogCleanup {

        private final BatchlogCleanupCallback callback;

        protected volatile int mutationsWaitingFor;

        private static final AtomicIntegerFieldUpdater<BatchlogCleanup> mutationsWaitingForUpdater = AtomicIntegerFieldUpdater.newUpdater(BatchlogCleanup.class, "mutationsWaitingFor");

        public BatchlogCleanup(int mutationsWaitingFor, BatchlogCleanupCallback callback) {
// mutationsWaitingFor  No	: [('i', 0.29786017593296465), ('j', 0.027243589926990684), ('size', 0.017131988575360822), ('version', 0.011065782890632163), ('pageSize', 0.010645833937491059), ('index', 0.008851039033573847), ('count', 0.008760652811978697), ('nowInSec', 0.008237082840024324), ('idx', 0.007655716625544738), ('messageSize', 0.007180574295885989)]
// callback             0	: [('callback', 0.9017922709114681), ('range', 0.00046733944895039356), ('i', 0.00043385743933376517), ('cfs', 0.0004180052692160149), ('expected', 0.0003850752410557511), ('a', 0.0003309540516513811), ('restrictions', 0.0003301349120974491), ('protocolVersion', 0.0003111611595263543), ('keyspace', 0.00030501792602898465), ('sstable', 0.0002462182615740563)]
            this.mutationsWaitingFor = mutationsWaitingFor;
            this.callback = callback;
        }

        public void ackMutation() {
            if (mutationsWaitingForUpdater.decrementAndGet(this) == 0)
                callback.invoke();
        }
    }

    public interface BatchlogCleanupCallback {

        void invoke();
    }
}
