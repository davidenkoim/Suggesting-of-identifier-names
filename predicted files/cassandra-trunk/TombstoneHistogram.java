// Type of training: selfTestingIdentifier
// Path to file: C:\Users\Igor\IdeaProjects\SLP-Core-0.3\java-projects\cassandra-trunk\src\java\org\apache\cassandra\utils\streamhist\TombstoneHistogram.java
// Number of identifiers: 19	Accuracy: 42.11%	MRR: 55.00%
// True 	Rank of true in predictions : [(predicted token, probability of a token), ...]

package org.apache.cassandra.utils.streamhist;

import java.io.IOException;
import org.apache.cassandra.db.TypeSizes;
import org.apache.cassandra.io.ISerializer;
import org.apache.cassandra.io.util.DataInputPlus;
import org.apache.cassandra.io.util.DataOutputPlus;
import org.apache.cassandra.utils.streamhist.StreamingTombstoneHistogramBuilder.DataHolder;

public class TombstoneHistogram {

    public static final HistogramSerializer serializer = new HistogramSerializer();

    private final DataHolder bin;

    TombstoneHistogram(DataHolder holder) {
// holder               1	: [('maxBinSize', 0.7031267375311097), ('holder', 0.27501980330123404), ('size', 0.20325103299949915), ('other', 0.025049028743512412), ('dataHolder', 0.025003876208352194), ('i', 0.0008610996640773172), ('cfs', 0.00045615316684532406), ('value', 0.00022939920430670128), ('e', 0.00022308074068912265), ('key', 0.00021633154132143646)]
        bin = new DataHolder(holder);
    }

    public static TombstoneHistogram createDefault() {
        return new TombstoneHistogram(new DataHolder(0, 1));
    }

    public double sum(double b) {
// b                    No	: [('crc', 0.11844295947614061), ('in', 0.062449485859726796), ('ratio', 0.028325467837309142), ('value', 0.023051078726081073), ('useChance', 0.02238489637162234), ('cumProb', 0.022070671387893818), ('size', 0.019628061281429302), ('targetUncertainty', 0.018649002647036166), ('v', 0.01849197494750246), ('tree', 0.01843914647774281)]
        return bin.sum((int) b);
    }

    public int size() {
        return this.bin.size();
    }

    public <E extends Exception> void forEach(HistogramDataConsumer<E> histogramDataConsumer) throws E {
// histogramDataConsumer 1	: [('consumer', 0.3856689472061121), ('histogramDataConsumer', 0.3765141673860705), ('l', 0.06142019108038592), ('s', 0.05027451119926463), ('v', 0.04545231664247158), ('t', 0.042409027666182014), ('f', 0.02610680927270169), ('c', 0.022970740892303845), ('p', 0.022733559248716208), ('i', 0.022171574116779155)]
        this.bin.forEach(histogramDataConsumer);
    }

    public static class HistogramSerializer implements ISerializer<TombstoneHistogram> {

        public void serialize(TombstoneHistogram histogram, DataOutputPlus out) throws IOException {
// histogram            1	: [('dout', 0.5153667828227685), ('histogram', 0.47919056779056196), ('bytes', 0.25874692503645425), ('updates', 0.2546755859753416), ('hist', 0.08334161657032568), ('in', 0.03916802397990344), ('input', 0.02338937124113513), ('deserialized', 0.02084339484457702), ('estimatedTombstoneDropTime', 0.020837828073007634), ('buffer', 0.019555993829974524)]
// out                  0	: [('out', 0.7445007025397232), ('maxSpoolSize', 0.6666738509988969), ('i', 0.21433822779942513), ('values', 0.17017431091365864), ('a', 0.17011376957809723), ('size', 0.08719808118931846), ('repairSubmitted', 0.010789771052643685), ('j', 0.00946505745882903), ('service', 0.009252804430103484), ('config', 0.007717100007884352)]
            final int size = histogram.size();
// size                 0	: [('size', 0.6252977414033337), ('decompressedLength', 0.19042993718576773), ('message', 0.146440503780146), ('compressedLength', 0.09670447335569586), ('plainTextLength', 0.09624157548366495), ('minIndexInterval', 0.09531840349433238), ('inOffset', 0.09514051914611185), ('replica', 0.09499949784789966), ('SAMPLES_CAP', 0.09499514995742671), ('component', 0.0884140780925867)]
            final int maxBinSize = size;
// maxBinSize           No	: [('message', 0.14644050431322558), ('component', 0.08841407818177677), ('header', 0.06319652067188528), ('size', 0.05485374676814782), ('p', 0.050548667899881355), ('ROWS_PER_SSTABLE', 0.049637758273873185), ('NUM_PARTITIONS', 0.045912458412953674), ('t', 0.042008927212353636), ('position', 0.03382116682739626), ('summary', 0.029272794794685447)]
            out.writeInt(maxBinSize);
            out.writeInt(size);
            histogram.forEach((point, value) -> {
// point                0	: [('point', 0.5232916727374816), ('bytes', 0.06887249471834814), ('k', 0.06809442222476769), ('totalKeyCountAfter', 0.06216427211974269), ('sstable', 0.0580436037992898), ('estimatedTotalKeys', 0.0438309774444018), ('count', 0.03917070718422369), ('node', 0.03787191861695016), ('completed', 0.03443428772858166), ('parts', 0.03441794480585894)]
// value                0	: [('value', 0.8569795156812143), ('key', 0.3724593728038378), ('probe', 0.0842814966137993), ('i', 0.050314436754968966), ('tokens', 0.043190476247883594), ('delta', 0.03256136802513764), ('builder', 0.024893028928863036), ('point', 0.024742717424354035), ('workPermits', 0.021059080652458175), ('droppedForVerb', 0.021058752059522505)]
                out.writeDouble((double) point);
                out.writeLong((long) value);
            });
        }

        public TombstoneHistogram deserialize(DataInputPlus in) throws IOException {
// in                   0	: [('in', 0.824670824826611), ('crc', 0.16693268004184075), ('values', 0.15534366382080755), ('input', 0.14859944760308935), ('buffer', 0.07823037800488268), ('cb', 0.07622563449921427), ('entry', 0.0756014679629545), ('difference', 0.07558167208669712), ('totalSourceCQLRows', 0.07558035650992939), ('rs', 0.07235562568740346)]
            in.readInt();
            int size = in.readInt();
// size                 4	: [('holder', 0.406276430656175), ('maxBinSize', 0.4062534750633671), ('count', 0.06552386043008293), ('blockFor', 0.060207982975339094), ('size', 0.05525557548577213), ('columnCount', 0.04146341798833354), ('length', 0.02410702292288399), ('i', 0.023354856226060594), ('actual', 0.022755904188135355), ('compressedLength', 0.02120371300309743)]
            DataHolder dataHolder = new DataHolder(size, 1);
// dataHolder           No	: [('holder', 0.04547450344857916), ('buffers', 0.025612285016443786), ('reads', 0.023863624737740965), ('other', 0.022776301639502106), ('cluster', 0.021981101143152985), ('builder', 0.0190156114528529), ('b', 0.018800692082476037), ('histogram', 0.018648955106043474), ('data', 0.016790560967559397), ('endpointTokens', 0.015768903070287194)]
            for (int i = 0; i < size; i++) {
// i                    0	: [('i', 0.8886905778013117), ('j', 0.06561274126113845), ('ii', 0.013171789774224564), ('pageSize', 0.010476369537076133), ('c', 0.008246661003297828), ('k', 0.005766836326963185), ('r', 0.005401513909763683), ('n', 0.0037206896325245858), ('size', 0.003405675808438516), ('round', 0.0033702679008935765)]
                dataHolder.addValue((int) in.readDouble(), (int) in.readLong());
            }
            return new TombstoneHistogram(dataHolder);
        }

        public long serializedSize(TombstoneHistogram histogram) {
// histogram            0	: [('histogram', 0.47919064503108333), ('hist', 0.08334161655282274), ('deserialized', 0.020843394820474596), ('estimatedTombstoneDropTime', 0.020837828069564433), ('that', 0.010474354800483775), ('tombstoneHistogram', 0.010420542875018859), ('i', 0.006941719029340243), ('cfs', 0.006688084307456239), ('restrictions', 0.005282158593559186), ('keyspace', 0.004880286816463754)]
            int maxBinSize = 0;
// maxBinSize           No	: [('message', 0.10274698229095469), ('size', 0.0736508860659387), ('i', 0.0592719779763092), ('request', 0.054310211535477854), ('desc', 0.05065475463717489), ('parameters', 0.05058800556019584), ('chunk', 0.043162309759681716), ('syn', 0.04314927382862584), ('schema', 0.039444353806970436), ('intervals', 0.03943650010490382)]
            long size = TypeSizes.sizeof(maxBinSize);
// size                 1	: [('i', 0.6368666591469577), ('size', 0.5014779117390313), ('ones', 0.06974232509139239), ('count', 0.058265874372373856), ('j', 0.04933517200963871), ('sum', 0.03608390883103969), ('builder', 0.03365554918763659), ('result', 0.03272594627409884), ('start', 0.030608518713346487), ('sz', 0.02787873594161779)]
            final int histSize = histogram.size();
// histSize             No	: [('message', 0.19984519664219744), ('header', 0.1605595126207008), ('column', 0.04908033686687796), ('summary', 0.04612788730869538), ('desc', 0.03837796895628108), ('component', 0.038326465079574226), ('parameters', 0.03831121985462573), ('numKeys', 0.037263812576982656), ('pointSerializer', 0.036899539143030666), ('version', 0.036789097920191895)]
            size += TypeSizes.sizeof(histSize);
            size += histSize * (8L + 8L);
            return size;
        }
    }

    @Override
    public boolean equals(Object o) {
// o                    0	: [('o', 0.7440822712259232), ('other', 0.12323606386935354), ('obj', 0.09745619485227691), ('receiver', 0.050350833068916245), ('that', 0.043802653670629146), ('previous', 0.042780389855514245), ('e', 0.04103973494798664), ('parsed', 0.03699264418099119), ('pos', 0.01522277988456904), ('p1', 0.014211069736771142)]
        if (this == o)
            return true;
        if (!(o instanceof TombstoneHistogram))
            return false;
        TombstoneHistogram that = (TombstoneHistogram) o;
// that                 3	: [('hist', 0.38334161655282273), ('deserialized', 0.12084339482047458), ('histogram', 0.07294079966305307), ('that', 0.06919937674816405), ('tombstoneHistogram', 0.060420542875018864), ('other', 0.04268079993803997), ('estimatedTombstoneDropTime', 0.020837828069564433), ('traces', 0.011185638596889933), ('name', 0.010746776625696166), ('message', 0.00960353691506049)]
        return bin.equals(that.bin);
    }

    @Override
    public int hashCode() {
        return bin.hashCode();
    }
}
