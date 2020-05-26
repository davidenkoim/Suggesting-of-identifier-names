// Type of training: selfTestingIdentifier
// Path to file: C:\Users\Igor\IdeaProjects\SLP-Core-0.3\java-projects\cassandra-trunk\src\java\org\apache\cassandra\transport\frame\compress\LZ4Compressor.java
// Number of identifiers: 14	Accuracy: 50.00%	MRR: 70.24%
// True 	Rank of true in predictions : [(predicted token, probability of a token), ...]

package org.apache.cassandra.transport.frame.compress;

import java.io.IOException;
import net.jpountz.lz4.LZ4Factory;
import net.jpountz.lz4.LZ4SafeDecompressor;

public class LZ4Compressor implements Compressor {

    public static final LZ4Compressor INSTANCE = new LZ4Compressor();

    private final net.jpountz.lz4.LZ4Compressor compressor;

    private final LZ4SafeDecompressor decompressor;

    private LZ4Compressor() {
        final LZ4Factory lz4Factory = LZ4Factory.fastestInstance();
// lz4Factory           0	: [('lz4Factory', 0.7500039535257716), ('descriptor', 0.7354458701939013), ('decompressor', 0.25002445396688383), ('encryptionContext', 0.11771436820929373), ('encryption', 0.03438166677672533), ('compressor', 0.02502370811345964), ('i', 0.0034709036942656797), ('cfs', 0.0033441203206928346), ('parameters', 0.003153375284584295), ('commitLog', 0.003137671359595056)]
        compressor = lz4Factory.fastCompressor();
        decompressor = lz4Factory.safeDecompressor();
    }

    public int maxCompressedLength(int length) {
// length               0	: [('length', 0.7506355627735883), ('payloadLength', 0.5580738516972114), ('uncompressedLength', 0.19453621651514394), ('chunkLength', 0.07448386443931879), ('uncompressable', 0.05807468319127068), ('i', 0.03723298592537239), ('input', 0.016479221594823557), ('inputBuf', 0.003908963992640661), ('j', 0.0034054868988815327), ('size', 0.00214153970850646)]
        return compressor.maxCompressedLength(length);
    }

    public int compress(byte[] src, int srcOffset, int length, byte[] dest, int destOffset) throws IOException {
// src                  1	: [('input', 0.5785299641479423), ('src', 0.5133319018011444), ('bytes', 0.05867497598688054), ('in', 0.05298531053039811), ('inputBuffer', 0.05281539343451393), ('b', 0.051623228287208806), ('plainTextBuffer', 0.030681864537413214), ('payload', 0.0264157553311647), ('bb', 0.022190724338914353), ('uncompressable', 0.022133180065630184)]
// srcOffset            2	: [('compressed', 0.5064378533732187), ('offset', 0.3463130330699198), ('srcOffset', 0.34499457555197577), ('dest', 0.2606018894350937), ('byteIndex', 0.1149756872422008), ('srcPosition', 0.05490710843551363), ('srcPos', 0.017497841056747763), ('dst', 0.015281264483392759), ('serializer', 0.011677551522637815), ('i', 0.009767998188482398)]
// length               0	: [('length', 0.8767066334476613), ('dst', 0.29949155672135164), ('trg', 0.2747527862399769), ('trgBuf', 0.274741741110197), ('i', 0.009768559665768778), ('version', 0.009667906663583978), ('srcOffset', 0.007819135800369796), ('peer', 0.003938765835834944), ('nowInSec', 0.003882397708217425), ('size', 0.002147285711535999)]
// dest                 0	: [('dest', 0.34466452417660337), ('output', 0.2080373855265795), ('end', 0.09508680464597272), ('length', 0.053587567169151175), ('totalRead', 0.0494088103389161), ('input', 0.047556110478303826), ('bufferType', 0.0474307506389545), ('decompressed', 0.04742773428142725), ('expectedLength', 0.04742634155958212), ('buffer2', 0.03335553404405554)]
// destOffset           0	: [('destOffset', 0.5500369359153858), ('offset', 0.2764469616413483), ('version', 0.1815821002377137), ('count', 0.05148590810730427), ('len2', 0.032261582346072966), ('len1', 0.028641198712962335), ('count1', 0.01612690372652418), ('count2', 0.016126787752546883), ('i', 0.009767998188482398), ('nowInSec', 0.003881476159533672)]
        try {
            return compressor.compress(src, srcOffset, length, dest, destOffset);
        } catch (Throwable t) {
// t                    1	: [('e', 0.6413765670471365), ('t', 0.6331744966073465), ('future', 0.05774011714204128), ('th', 0.03831948160827733), ('ex', 0.03612951392061388), ('throwable', 0.020867548574275925), ('i', 0.01961399633763047), ('t2', 0.013195586195938656), ('pageSize', 0.012941038451622928), ('accumulate', 0.011565499891766935)]
            throw new IOException("Error caught during LZ4 compression", t);
        }
    }

    public byte[] decompress(byte[] src, int offset, int length, int expectedDecompressedLength) throws IOException {
// src                  1	: [('compressedNioBuffer', 0.5701401004629683), ('src', 0.5133319018011444), ('input', 0.24069797159482353), ('buf', 0.07019349168731026), ('bytes', 0.05867497598688054), ('b', 0.051623228287208806), ('in', 0.02031283096946804), ('output', 0.020154124089477206), ('buffer', 0.019763878975303885), ('hash', 0.016801189925360067)]
// offset               1	: [('srcOffset', 0.45996818255074906), ('offset', 0.23133942607114644), ('byteIndex', 0.1149756872422008), ('srcPos', 0.06999136422699105), ('dst', 0.06112505793357104), ('srcPosition', 0.05490710843551363), ('serializer', 0.04671020609055126), ('dest', 0.04240755774037489), ('src', 0.035056045794248904), ('out', 0.02906590082760913)]
// length               0	: [('length', 0.795313620269924), ('count', 0.1133951332590068), ('numberOfRows', 0.07069772100354431), ('len', 0.05398395266182012), ('index', 0.036442591772245375), ('limit', 0.03575926415075782), ('filePos', 0.03535584108532251), ('size', 0.02733043589468618), ('totalSize', 0.012649293245868354), ('keyLength', 0.012631904921292568)]
// expectedDecompressedLength 0	: [('expectedDecompressedLength', 0.2738464687536155), ('seed', 0.19058146609214333), ('messagingVersion', 0.13776696021202373), ('position', 0.13756605991557208), ('expectedLength', 0.13695997088367468), ('size', 0.12338048332623633), ('i', 0.054422988624450615), ('length', 0.04731804048259304), ('value1', 0.03234656004795175), ('key', 0.02774215518078349)]
        try {
            byte[] decompressed = new byte[expectedDecompressedLength];
// decompressed         No	: [('output', 0.39922152769755037), ('totalRead', 0.38734451311873574), ('out', 0.17113090347851687), ('s', 0.16850324398905075), ('preparedStatementId', 0.16846792124941415), ('bytes', 0.07467236079629445), ('b', 0.034181122629816686), ('builder', 0.033655548739442945), ('result', 0.03272594591284673), ('dest', 0.024732074461619794)]
            decompressor.decompress(src, offset, length, decompressed, 0, expectedDecompressedLength);
            return decompressed;
        } catch (Throwable t) {
// t                    1	: [('e', 0.6413765670471365), ('t', 0.6331744966073465), ('future', 0.05774011714204128), ('th', 0.03831948160827733), ('ex', 0.03612951392061388), ('throwable', 0.020867548574275925), ('i', 0.01961399633763047), ('t2', 0.013195586195938656), ('pageSize', 0.012941038451622928), ('accumulate', 0.011565499891766935)]
            throw new IOException("Error caught during LZ4 decompression", t);
        }
    }
}
