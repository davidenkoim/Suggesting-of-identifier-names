// Type of training: selfTestingIdentifier
// Path to file: C:\Users\Igor\IdeaProjects\SLP-Core-0.3\java-projects\cassandra-trunk\src\java\org\apache\cassandra\index\sasi\utils\trie\AbstractPatriciaTrie.java
// Number of identifiers: 84	Accuracy: 59.52%	MRR: 69.66%
// True 	Rank of true in predictions : [(predicted token, probability of a token), ...]

package org.apache.cassandra.index.sasi.utils.trie;

import java.util.AbstractCollection;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import org.apache.cassandra.index.sasi.utils.trie.Cursor.Decision;

abstract class AbstractPatriciaTrie<K, V> extends AbstractTrie<K, V> {

    private static final long serialVersionUID = -2303909182832019043L;

    final TrieEntry<K, V> root = new TrieEntry<>(null, null, -1);

    private transient volatile Set<K> keySet;

    private transient volatile Collection<V> values;

    private transient volatile Set<Map.Entry<K, V>> entrySet;

    private int size = 0;

    transient int modCount = 0;

    public AbstractPatriciaTrie(KeyAnalyzer<? super K> keyAnalyzer) {
// keyAnalyzer          1	: [('comparator', 0.650925264611419), ('keyAnalyzer', 0.5015886570469436), ('column', 0.025569714665230083), ('cfs', 0.02070744527944883), ('keyspaceName', 0.01720814712092794), ('baseCfs', 0.013898097425052854), ('message', 0.012830990454128118), ('returnDataType', 0.012000750130775872), ('name', 0.011766566505288205), ('channel', 0.00953817262476416)]
        super(keyAnalyzer);
    }

    public AbstractPatriciaTrie(KeyAnalyzer<? super K> keyAnalyzer, Map<? extends K, ? extends V> m) {
// keyAnalyzer          1	: [('comparator', 0.650925264611419), ('keyAnalyzer', 0.5015886667114416), ('column', 0.025569714665230083), ('cfs', 0.02070744527944883), ('keyspaceName', 0.01720814712092794), ('baseCfs', 0.013898097425052854), ('message', 0.012830990454128118), ('returnDataType', 0.012000750130775872), ('name', 0.011766566505288205), ('channel', 0.00953817262476416)]
// m                    2	: [('offsets', 0.7634167386014734), ('entry', 0.31937011395146553), ('m', 0.29460631112529667), ('function', 0.09886689906264737), ('wrapped', 0.09832095878539863), ('c', 0.07116388781394849), ('map', 0.008965490145838643), ('newenv', 0.00893087859599852), ('key', 0.0071290897712844815), ('replica', 0.0067872137389936565)]
        super(keyAnalyzer);
        putAll(m);
    }

    @Override
    public void clear() {
        root.key = null;
        root.bitIndex = -1;
        root.value = null;
        root.parent = null;
        root.left = root;
        root.right = null;
        root.predecessor = root;
        size = 0;
        incrementModCount();
    }

    @Override
    public int size() {
        return size;
    }

    void incrementSize() {
        size++;
        incrementModCount();
    }

    void decrementSize() {
        size--;
        incrementModCount();
    }

    private void incrementModCount() {
        ++modCount;
    }

    @Override
    public V put(K key, V value) {
// key                  0	: [('key', 0.9338083527865364), ('value', 0.4385232030976101), ('current', 0.18776280280325408), ('h', 0.1876554447125467), ('prefix', 0.17931787933620943), ('i', 0.0882389309809398), ('K', 0.05013087662629858), ('type', 0.030617106435207847), ('e', 0.023622758839867693), ('myType', 0.01715706719671014)]
// value                1	: [('sstable', 0.751354365202271), ('value', 0.6873384151659578), ('old', 0.1474931531793528), ('oldToReplace', 0.049164510895850486), ('metadata', 0.045242425874776386), ('i', 0.044173182762500825), ('a', 0.042646231435508365), ('found', 0.0265557559525386), ('now', 0.01691402815845042), ('op', 0.016762681267823647)]
        if (key == null)
            throw new NullPointerException("Key cannot be null");
        int lengthInBits = lengthInBits(key);
// lengthInBits         3	: [('numRows', 0.05795094244084287), ('cl', 0.05787025918032103), ('partitions', 0.05748559086180594), ('lengthInBits', 0.05296443239203595), ('value', 0.03586715109368246), ('n', 0.031032861456285067), ('rowCount', 0.030245400836778524), ('c', 0.029865383207943293), ('uncompressedLength', 0.029336021506777436), ('index', 0.029212621465814423)]
        if (lengthInBits == 0) {
            if (root.isEmpty())
                incrementSize();
            else
                incrementModCount();
            return root.setKeyValue(key, value);
        }
        TrieEntry<K, V> found = getNearestEntryForKey(key);
// found                0	: [('found', 0.7052462449537984), ('entry', 0.13181409012725678), ('v', 0.09417620208859204), ('updateF', 0.05660562190095915), ('result', 0.05166314869237955), ('otherKey', 0.051312086703881175), ('node', 0.05104028980980544), ('e', 0.04190654482025892), ('current', 0.03932304759738217), ('h', 0.03163550005016071)]
        if (compareKeys(key, found.key)) {
            if (found.isEmpty())
                incrementSize();
            else
                incrementModCount();
            return found.setKeyValue(key, value);
        }
        int bitIndex = bitIndex(key, found.key);
// bitIndex             0	: [('bitIndex', 0.9250044462555387), ('chunkLength', 0.5263365727699437), ('cause', 0.37697265144226044), ('defaultValue', 0.3769534445961195), ('fName', 0.19235131110056297), ('typeDef', 0.15114986468779992), ('values', 0.09599010107434233), ('transientReplicas', 0.09585080141083599), ('keystring', 0.0958496416495427), ('idx', 0.05396838217085581)]
        if (!Tries.isOutOfBoundsIndex(bitIndex)) {
            if (Tries.isValidBitIndex(bitIndex)) {
                TrieEntry<K, V> t = new TrieEntry<>(key, value, bitIndex);
// t                    No	: [('added', 0.9375052527029135), ('entry', 0.13181408988228357), ('v', 0.09417620179878479), ('updateF', 0.05660562189235101), ('node', 0.051040289671357916), ('e', 0.04190654402866906), ('current', 0.03932304743884899), ('h', 0.03163550001590751), ('value', 0.03158880278756633), ('found', 0.03138276012048024)]
                addEntry(t);
                incrementSize();
                return null;
            } else if (Tries.isNullBitKey(bitIndex)) {
                if (root.isEmpty())
                    incrementSize();
                else
                    incrementModCount();
                return root.setKeyValue(key, value);
            } else if (Tries.isEqualBitKey(bitIndex)) {
                if (found != root) {
                    incrementModCount();
                    return found.setKeyValue(key, value);
                }
            }
        }
        throw new IndexOutOfBoundsException("Failed to put: " + key + " -> " + value + ", " + bitIndex);
    }

    TrieEntry<K, V> addEntry(TrieEntry<K, V> entry) {
// entry                No	: [('key', 0.8877163290353394), ('current', 0.7640375228583509), ('p', 0.5626474188276152), ('path', 0.5001510961081457), ('child', 0.3833761429872863), ('parent', 0.3611820047165), ('prefix', 0.2875116590275067), ('lengthInBits', 0.2504501541775498), ('left', 0.22791696174981815), ('right', 0.22337322417211763)]
        TrieEntry<K, V> current = root.left;
// current              0	: [('current', 0.8757826221097695), ('entry', 0.7833628888275495), ('path', 0.7502086680833597), ('lengthInBits', 0.500178884436404), ('needsFixing', 0.2500896574093084), ('v', 0.09417620249434028), ('dcBlockFor', 0.0774008133561413), ('updateF', 0.05660562191301108), ('node', 0.05104029000364061), ('right', 0.05091870645610842)]
        TrieEntry<K, V> path = root;
// path                 0	: [('path', 0.8306289433523774), ('current', 0.7508693658930083), ('staleEntries', 0.5004922405799498), ('parent', 0.36118198952364416), ('entry', 0.25009803592088986), ('lengthInBits', 0.2366180068811033), ('p', 0.11324262811980794), ('bitIndex', 0.10746604975786174), ('sibling', 0.09922214792924831), ('v', 0.09417620220451796)]
        while (true) {
            if (current.bitIndex >= entry.bitIndex || current.bitIndex <= path.bitIndex) {
                entry.predecessor = entry;
                if (!isBitSet(entry.key, entry.bitIndex)) {
                    entry.left = entry;
                    entry.right = current;
                } else {
                    entry.left = current;
                    entry.right = entry;
                }
                entry.parent = path;
                if (current.bitIndex >= entry.bitIndex)
                    current.parent = entry;
                if (current.bitIndex <= path.bitIndex)
                    current.predecessor = entry;
                if (path == root || !isBitSet(entry.key, path.bitIndex))
                    path.left = entry;
                else
                    path.right = entry;
                return entry;
            }
            path = current;
            current = !isBitSet(entry.key, current.bitIndex) ? current.left : current.right;
        }
    }

    @Override
    public V get(Object k) {
// k                    No	: [('key', 0.8525055123634506), ('object', 0.02579781838249374), ('o', 0.02228548014180254), ('value', 0.00486217855469398), ('obj', 0.0043492815722923385), ('i', 0.0034443986563092686), ('parsed', 0.003399235584674067), ('other', 0.002972967528597586), ('cfs', 0.0018246126673812963), ('element', 0.0011755765844848544)]
        TrieEntry<K, V> entry = getEntry(k);
// entry                0	: [('entry', 0.7550077334145324), ('tables', 0.5001926237332682), ('v', 0.09417620185674538), ('updateF', 0.056605621894072616), ('node', 0.05104028969904701), ('e', 0.041906544186984675), ('current', 0.03932304747055516), ('h', 0.03163550002275804), ('value', 0.03158880293738155), ('found', 0.031382760124317975)]
        return entry != null ? entry.getValue() : null;
    }

    TrieEntry<K, V> getEntry(Object k) {
// k                    0	: [('k', 0.792692598002291), ('o', 0.1782838411344203), ('stmt', 0.04266787126248975), ('value', 0.03889742843755184), ('obj', 0.03479425257833871), ('parsed', 0.027193884677392536), ('other', 0.023783740228780686), ('instance', 0.02135834051101863), ('fail', 0.021339927211568598), ('proxy', 0.021334443841575887)]
        K key = Tries.cast(k);
// key                  0	: [('key', 0.9338095577198524), ('fromKey', 0.23523746481936733), ('k', 0.11741856105414952), ('test', 0.11585598373759079), ('toKey', 0.0614912854680951), ('oldKey', 0.057917730691301385), ('value', 0.01910272776024569), ('i', 0.01853575327921723), ('flush', 0.016800010277219554), ('version', 0.010051460206691287)]
        if (key == null)
            return null;
        TrieEntry<K, V> entry = getNearestEntryForKey(key);
// entry                0	: [('entry', 0.5797663783687937), ('current', 0.28911532377470756), ('found', 0.18510224405877204), ('result', 0.15958629274402045), ('v', 0.09417620191470641), ('s1', 0.07970742816996874), ('updateF', 0.056605621895794225), ('node', 0.05104028972673631), ('e', 0.04190654434530147), ('row', 0.037779310184677985)]
        return !entry.isEmpty() && compareKeys(key, entry.key) ? entry : null;
    }

    @Override
    public Map.Entry<K, V> select(K key) {
// key                  0	: [('key', 0.9286447377307738), ('threadName', 0.044814735943844816), ('st', 0.04329657392088009), ('lt', 0.043293847020358524), ('iteration', 0.04324058572983857), ('diskVersion', 0.043240508413853705), ('i', 0.020129864843996315), ('time', 0.011369596180501375), ('now2', 0.009542701236683743), ('now', 0.008016007980106028)]
        Reference<Map.Entry<K, V>> reference = new Reference<>();
// reference            1	: [('cursor', 0.7595359620938931), ('reference', 0.2284926293014129), ('future', 0.15640420428198298), ('it', 0.07649730849647045), ('javaType', 0.07625711448395199), ('empty', 0.07616712365945627), ('entryFuture', 0.07616345479918021), ('intervals', 0.04140661841582513), ('row', 0.037779309463895384), ('listener', 0.027905164700775514)]
        return !selectR(root.left, -1, key, reference) ? reference.get() : null;
    }

    @Override
    public Map.Entry<K, V> select(K key, Cursor<? super K, ? super V> cursor) {
// key                  0	: [('key', 0.9286447377307738), ('threadName', 0.044814735943844816), ('st', 0.04329657392088009), ('lt', 0.043293847020358524), ('iteration', 0.04324058572983857), ('diskVersion', 0.043240508413853705), ('i', 0.020129864843996315), ('time', 0.011369596180501375), ('now2', 0.009542701236683743), ('now', 0.008016007980106028)]
// cursor               1	: [('reference', 0.7548668412611108), ('cursor', 0.5394569977130775), ('comparator', 0.24927324988238145), ('indexEntry', 0.011447847519192974), ('filter', 0.008616554853094135), ('metadata', 0.006525751082914232), ('holder', 0.005924569536381393), ('cstart', 0.005720829600982561), ('value', 0.005215926700677104), ('sstable', 0.0050650283055820354)]
        Reference<Map.Entry<K, V>> reference = new Reference<>();
// reference            0	: [('reference', 0.9375039470354104), ('future', 0.15640420428198298), ('it', 0.07649730849647045), ('javaType', 0.07625711448395199), ('empty', 0.07616712365945627), ('entryFuture', 0.07616345479918021), ('intervals', 0.04140661841582513), ('builder', 0.033655548739442945), ('result', 0.03272594591284673), ('listener', 0.027905164700775514)]
        selectR(root.left, -1, key, cursor, reference);
        return reference.get();
    }

    private boolean selectR(TrieEntry<K, V> h, int bitIndex, final K key, final Reference<Map.Entry<K, V>> reference) {
// h                    0	: [('h', 0.9375118741480677), ('current', 0.4367343730223135), ('entry', 0.2515913352657073), ('v', 0.09417620243637495), ('updateF', 0.05660562191128933), ('i', 0.05478036958854233), ('bitIndex', 0.053266742381443584), ('node', 0.051040289975949255), ('e', 0.04190654577020554), ('value', 0.03158880443559485)]
// bitIndex             0	: [('bitIndex', 0.8751707369417285), ('path', 0.11756523682904421), ('node', 0.02349855311909431), ('from', 0.023498137545675667), ('i', 0.009767998188482398), ('version', 0.009665565692915575), ('nowInSec', 0.003881476159533672), ('size', 0.0021468987387816343), ('length', 0.0017245105673391976), ('count', 0.001485908107304268)]
// key                  0	: [('key', 0.9457940898320223), ('entry', 0.41769112030604305), ('prefix', 0.032208628370233534), ('K', 0.005714698117097825), ('fromKey', 0.0011170585165512954), ('toKey', 0.0009497444486813442), ('i', 0.0008656526427923649), ('k', 0.0005085057897792617), ('cfs', 0.00045615398791501227), ('version', 0.00039251833865633237)]
// reference            1	: [('cursor', 0.7597186005194079), ('reference', 0.22849260997557297), ('future', 0.1564042043140483), ('sb', 0.12100251248388993), ('it', 0.07649730851433234), ('javaType', 0.07625711449503497), ('empty', 0.0761671236677416), ('entryFuture', 0.07616345479961062), ('out', 0.0506330569882945), ('intervals', 0.041406618427768924)]
        if (h.bitIndex <= bitIndex) {
            if (!h.isEmpty()) {
                reference.set(h);
                return false;
            }
            return true;
        }
        if (!isBitSet(key, h.bitIndex)) {
            if (selectR(h.left, h.bitIndex, key, reference)) {
                return selectR(h.right, h.bitIndex, key, reference);
            }
        } else {
            if (selectR(h.right, h.bitIndex, key, reference)) {
                return selectR(h.left, h.bitIndex, key, reference);
            }
        }
        return false;
    }

    private boolean selectR(TrieEntry<K, V> h, int bitIndex, final K key, final Cursor<? super K, ? super V> cursor, final Reference<Map.Entry<K, V>> reference) {
// h                    0	: [('h', 0.9375113483759833), ('current', 0.7552689010524756), ('key', 0.5635668678041009), ('added', 0.5357247919102676), ('entry', 0.2515913189824992), ('v', 0.09417620596990336), ('e', 0.0898486849994604), ('params', 0.07213760164784506), ('updateF', 0.05660562290165092), ('i', 0.05478037963446651)]
// bitIndex             0	: [('bitIndex', 0.8751707369417285), ('path', 0.11756523682904421), ('node', 0.02349855311909431), ('from', 0.023498137545675667), ('i', 0.009767998188482398), ('version', 0.009665565692915575), ('nowInSec', 0.003881476159533672), ('size', 0.0021468987387816343), ('length', 0.0017245105673391976), ('count', 0.001485908107304268)]
// key                  0	: [('key', 0.9457940898320223), ('entry', 0.41769112030604305), ('prefix', 0.032208628370233534), ('K', 0.005714698117097825), ('fromKey', 0.0011170585165512954), ('toKey', 0.0009497444486813442), ('i', 0.0008656526427923649), ('k', 0.0005085057897792617), ('cfs', 0.00045615398791501227), ('version', 0.00039251833865633237)]
// cursor               1	: [('reference', 0.7549593657545342), ('cursor', 0.7500121880694268), ('comparator', 0.24927325002541942), ('indexEntry', 0.011668819595163521), ('filter', 0.008782285405054477), ('metadata', 0.006625078632264912), ('holder', 0.0060359615175258514), ('cstart', 0.005831315463725583), ('value', 0.005267255794093748), ('sstable', 0.005157563434504967)]
// reference            0	: [('reference', 0.9375032799002734), ('s', 0.2656976154656359), ('r', 0.17720060884488067), ('future', 0.1564042043247369), ('columnName', 0.08855223527052658), ('it', 0.07649730852028638), ('javaType', 0.07625711449872935), ('empty', 0.07616712367050342), ('entryFuture', 0.07616345479975409), ('i', 0.05668004078228248)]
        if (h.bitIndex <= bitIndex) {
            if (!h.isEmpty()) {
                Decision decision = cursor.select(h);
// decision             0	: [('decision', 0.875001938104176), ('type', 0.11466315938417573), ('column', 0.02934565928066637), ('kind', 0.02881452210699136), ('in', 0.025769129582403735), ('m', 0.025529620820384822), ('expression', 0.02544767978912336), ('err', 0.025432871949379442), ('limits', 0.025429398794993178), ('ident', 0.02542435695944714)]
                switch(decision) {
                    case REMOVE:
                        throw new UnsupportedOperationException("Cannot remove during select");
                    case EXIT:
                        reference.set(h);
                        return false;
                    case REMOVE_AND_EXIT:
                        TrieEntry<K, V> entry = new TrieEntry<>(h.getKey(), h.getValue(), -1);
// entry                1	: [('h', 0.5029000415398988), ('entry', 0.12553988876922081), ('v', 0.09417620179878479), ('updateF', 0.05660562189235101), ('i', 0.054079538298220875), ('node', 0.051040289671357916), ('e', 0.04190654402866906), ('current', 0.03932304743884899), ('value', 0.03158880278756633), ('found', 0.03138276012048024)]
                        reference.set(entry);
                        removeEntry(h);
                        return false;
                    case CONTINUE:
                }
            }
            return true;
        }
        if (!isBitSet(key, h.bitIndex)) {
            if (selectR(h.left, h.bitIndex, key, cursor, reference)) {
                return selectR(h.right, h.bitIndex, key, cursor, reference);
            }
        } else {
            if (selectR(h.right, h.bitIndex, key, cursor, reference)) {
                return selectR(h.left, h.bitIndex, key, cursor, reference);
            }
        }
        return false;
    }

    @Override
    public Map.Entry<K, V> traverse(Cursor<? super K, ? super V> cursor) {
// cursor               0	: [('cursor', 0.7500123426939362), ('comparator', 0.24927324988238145), ('entry', 0.0047867709536340945), ('v', 0.002030719850671267), ('promise', 0.001840979967564639), ('i', 0.0017354297573350607), ('cfs', 0.0016720210768640597), ('e', 0.0015761290272014224), ('valuesClass', 0.001443266025132874), ('restrictions', 0.0013205396483897964)]
        TrieEntry<K, V> entry = nextEntry(null);
// entry                3	: [('start', 0.8134729993120617), ('undo', 0.2510838173200707), ('inputCells', 0.25108363555791835), ('entry', 0.12564524547420833), ('v', 0.09423844258707628), ('current', 0.07953591785732356), ('output', 0.06485431634460974), ('updates', 0.06474525991269918), ('iter', 0.06350022162518819), ('updateF', 0.056643234881837076)]
        while (entry != null) {
            TrieEntry<K, V> current = entry;
// current              8	: [('h', 0.7545199319680644), ('key', 0.5635670274561829), ('added', 0.562521011297843), ('entry', 0.1750337382479163), ('node', 0.1670327043112868), ('found', 0.12501956462625638), ('v', 0.09417620544821934), ('e', 0.08984867421772988), ('current', 0.08368012270592434), ('params', 0.07213759960770036)]
            Decision decision = cursor.select(current);
// decision             0	: [('decision', 0.875001938104176), ('type', 0.11466315841829758), ('column', 0.02934565824555569), ('kind', 0.028814521973592102), ('in', 0.02576912800525304), ('m', 0.025529620362774716), ('expression', 0.025447679679364482), ('err', 0.02543287190885309), ('limits', 0.02542939877473), ('ident', 0.02542435694931555)]
            entry = nextEntry(current);
            switch(decision) {
                case EXIT:
                    return current;
                case REMOVE:
                    removeEntry(current);
                    break;
                case REMOVE_AND_EXIT:
                    Map.Entry<K, V> value = new TrieEntry<>(current.getKey(), current.getValue(), -1);
// value                No	: [('output', 0.7505343190658129), ('entry', 0.13181408988228357), ('v', 0.09417620179878479), ('updateF', 0.05660562189235101), ('node', 0.051040289671357916), ('e', 0.04190654402866906), ('current', 0.03932303777435088), ('h', 0.03163550001590751), ('found', 0.03138276012048024), ('map', 0.02606604281086937)]
                    removeEntry(current);
                    return value;
                case CONTINUE:
            }
        }
        return null;
    }

    @Override
    public boolean containsKey(Object k) {
// k                    1	: [('key', 0.8775055124924991), ('k', 0.7926924164044543), ('stmt', 0.04266787130514339), ('o', 0.022285480180861374), ('instance', 0.021358340636940636), ('fail', 0.021339927260703384), ('proxy', 0.021334443864565143), ('i', 0.020333836274771366), ('value', 0.014280659827686172), ('bytes', 0.006245392229242984)]
        if (k == null)
            return false;
        K key = Tries.cast(k);
// key                  0	: [('key', 0.9338097287504573), ('first', 0.08877804385459681), ('last', 0.08877178123658125), ('toKey', 0.07281534683698487), ('k', 0.047546306668728694), ('token', 0.04446846959452486), ('K', 0.008961451443103612), ('fromKey', 0.008936468084204799), ('prefix', 0.003591927779364853), ('nextToken', 0.00268515370758425)]
        TrieEntry<K, V> entry = getNearestEntryForKey(key);
// entry                0	: [('entry', 0.5797663976490467), ('current', 0.28911532374300114), ('found', 0.18510224405493428), ('result', 0.15958629265370744), ('v', 0.09417620185674538), ('s1', 0.07970742816688418), ('updateF', 0.056605621894072616), ('node', 0.05104028969904701), ('e', 0.041906544186984675), ('h', 0.03163550002275804)]
        return !entry.isEmpty() && compareKeys(key, entry.key);
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        if (entrySet == null)
            entrySet = new EntrySet();
        return entrySet;
    }

    @Override
    public Set<K> keySet() {
        if (keySet == null)
            keySet = new KeySet();
        return keySet;
    }

    @Override
    public Collection<V> values() {
        if (values == null)
            values = new Values();
        return values;
    }

    @Override
    public V remove(Object k) {
// k                    0	: [('k', 0.7926924164044543), ('key', 0.7909670509540375), ('o', 0.10882394171932291), ('stmt', 0.04266787130514339), ('instance', 0.021358340636940636), ('fail', 0.021339927260703384), ('proxy', 0.021334443864565143), ('i', 0.020333836274771366), ('value', 0.014280659827686172), ('bytes', 0.006245392229242984)]
        if (k == null)
            return null;
        K key = Tries.cast(k);
// key                  0	: [('key', 0.9338097287504573), ('entry', 0.2926911204908721), ('prefix', 0.28220862839395), ('first', 0.08877804385459681), ('last', 0.08877178123658125), ('toKey', 0.07281534683698487), ('k', 0.047546306668728694), ('token', 0.04446846959452486), ('fromKey', 0.008936468084204799), ('K', 0.005714698196350366)]
        TrieEntry<K, V> current = root.left;
// current              0	: [('current', 0.8757826606445153), ('entry', 0.5729110423453108), ('h', 0.43465186047321225), ('found', 0.1851285503643498), ('node', 0.14322317937097093), ('added', 0.1428991653446629), ('path', 0.11218086157857099), ('v', 0.09417620226248156), ('updateF', 0.056605621906124184), ('i', 0.05479613190853158)]
        TrieEntry<K, V> path = root;
// path                 0	: [('path', 0.8440745615964862), ('entry', 0.13181408993127747), ('v', 0.09417620185674538), ('updateF', 0.056605621894072616), ('node', 0.05104028969904701), ('bitIndex', 0.04703634398819197), ('e', 0.041906544186984675), ('current', 0.039323037806021185), ('h', 0.03163550002275804), ('value', 0.03158880293738155)]
        while (true) {
            if (current.bitIndex <= path.bitIndex) {
                if (!current.isEmpty() && compareKeys(key, current.key)) {
                    return removeEntry(current);
                } else {
                    return null;
                }
            }
            path = current;
            current = !isBitSet(key, current.bitIndex) ? current.left : current.right;
        }
    }

    TrieEntry<K, V> getNearestEntryForKey(K key) {
// key                  0	: [('key', 0.4291579018461905), ('entry', 0.29269112011006526), ('prefix', 0.28220862833881394), ('fromKey', 0.05771695587301456), ('nextToken', 0.020977836623223108), ('k', 0.016263167577362014), ('min', 0.01403767389491741), ('toKey', 0.013695516489309192), ('left', 0.010599214768573685), ('K', 0.005714535602882082)]
        TrieEntry<K, V> current = root.left;
// current              0	: [('current', 0.8757826702467457), ('child', 0.66684120150535), ('h', 0.4346465991508393), ('path', 0.2788474894305719), ('entry', 0.1318140901762525), ('v', 0.09417620214655478), ('updateF', 0.056605621902680814), ('i', 0.054780340060061626), ('bitIndex', 0.053266722920431206), ('node', 0.05104028983749556)]
        TrieEntry<K, V> path = root;
// path                 0	: [('path', 0.8440745615964862), ('entry', 0.13181408993127747), ('v', 0.09417620185674538), ('updateF', 0.056605621894072616), ('node', 0.05104028969904701), ('bitIndex', 0.04703633432365799), ('e', 0.041906544186984675), ('current', 0.03932302814148721), ('h', 0.03163550002275804), ('value', 0.03158880293738155)]
        while (true) {
            if (current.bitIndex <= path.bitIndex)
                return current;
            path = current;
            current = !isBitSet(key, current.bitIndex) ? current.left : current.right;
        }
    }

    V removeEntry(TrieEntry<K, V> h) {
// h                    0	: [('h', 0.5007109692667334), ('entry', 0.1318140900782614), ('v', 0.09417620203062974), ('updateF', 0.0566056218992375), ('result', 0.05151922349830554), ('node', 0.05104028978211553), ('e', 0.0419065446619386), ('i', 0.040682902890827), ('current', 0.03932304756567506), ('value', 0.031588803386833855)]
        if (h != root) {
            if (h.isInternalNode()) {
                removeInternalEntry(h);
            } else {
                removeExternalEntry(h);
            }
        }
        decrementSize();
        return h.setKeyValue(null, null);
    }

    private void removeExternalEntry(TrieEntry<K, V> h) {
// h                    1	: [('p', 0.8854535134380409), ('h', 0.5008709814786568), ('e', 0.4496413369504966), ('entry', 0.13193230752477436), ('tokens', 0.12595305902256657), ('v', 0.09424250678776463), ('allowFiltering', 0.06264322396825134), ('numericTypes', 0.06256170029096605), ('stx', 0.062301959517978865), ('formatError', 0.0595824039680615)]
        if (h == root) {
            throw new IllegalArgumentException("Cannot delete root Entry!");
        } else if (!h.isExternalNode()) {
            throw new IllegalArgumentException(h + " is not an external Entry!");
        }
        TrieEntry<K, V> parent = h.parent;
// parent               0	: [('parent', 0.8437593812603275), ('h', 0.7812732784100886), ('p', 0.4501473867506305), ('entry', 0.3005266007759664), ('lastToken', 0.15008412594694812), ('child', 0.15007695618153793), ('indexedColumn', 0.15002761976174195), ('sibling', 0.14980553689364287), ('v', 0.09417620539025659), ('node', 0.09378457714270161)]
        TrieEntry<K, V> child = (h.left == h) ? h.right : h.left;
// child                0	: [('child', 0.6390926129074905), ('p', 0.13775917867556278), ('entry', 0.1318140900782614), ('node', 0.10837206322271556), ('v', 0.09417620203062974), ('updateF', 0.0566056218992375), ('right', 0.05091870421842744), ('left', 0.04507530460444303), ('e', 0.0419065446619386), ('current', 0.03932304756567506)]
        if (parent.left == h) {
            parent.left = child;
        } else {
            parent.right = child;
        }
        if (child.bitIndex > parent.bitIndex) {
            child.parent = parent;
        } else {
            child.predecessor = parent;
        }
    }

    private void removeInternalEntry(TrieEntry<K, V> h) {
// h                    2	: [('parent', 0.7916994653911433), ('bitIndex', 0.7500571392652582), ('h', 0.5008144569234485), ('query', 0.5002680599304085), ('e', 0.4496413396276685), ('p', 0.3515202839942357), ('right', 0.20367479020158374), ('left', 0.18030107773178844), ('inProgress', 0.17425200860017837), ('entry', 0.1334551408815402)]
        if (h == root) {
            throw new IllegalArgumentException("Cannot delete root Entry!");
        } else if (!h.isInternalNode()) {
            throw new IllegalArgumentException(h + " is not an internal Entry!");
        }
        TrieEntry<K, V> p = h.predecessor;
// p                    1	: [('h', 0.9042839538055242), ('p', 0.7514883299728669), ('child', 0.5536333135032607), ('current', 0.5375442395066152), ('entry', 0.5005267418391056), ('parent', 0.394301394004124), ('node', 0.3875457560186688), ('lastToken', 0.1500841477335367), ('indexedColumn', 0.1500276267685487), ('tokens', 0.1259530613008031)]
        p.bitIndex = h.bitIndex;
        {
            TrieEntry<K, V> parent = p.parent;
// parent               0	: [('parent', 0.8437593812546604), ('cur', 0.7503814313180406), ('sibling', 0.14980553680573153), ('entry', 0.1318141196527459), ('update', 0.10013029132018704), ('v', 0.09417620533229427), ('node', 0.09378457711501169), ('updateF', 0.05660562288271204), ('e', 0.04190654971980768), ('current', 0.039323048970638694)]
            TrieEntry<K, V> child = (p.left == h) ? p.right : p.left;
// child                0	: [('child', 0.6390926225675052), ('p', 0.13775917864765785), ('entry', 0.13181409002926642), ('node', 0.1083720629400768), ('v', 0.09417620197266785), ('updateF', 0.056605621897515855), ('right', 0.05091870417843517), ('left', 0.04507530455343944), ('e', 0.04190654450361945), ('current', 0.03932304753396819)]
            if (p.predecessor == p && p.parent != h)
                p.predecessor = p.parent;
            if (parent.left == p) {
                parent.left = child;
            } else {
                parent.right = child;
            }
            if (child.bitIndex > parent.bitIndex) {
                child.parent = parent;
            }
        }
        {
            if (h.left.parent == h)
                h.left.parent = p;
            if (h.right.parent == h)
                h.right.parent = p;
            if (h.parent.left == h) {
                h.parent.left = p;
            } else {
                h.parent.right = p;
            }
        }
        p.parent = h.parent;
        p.left = h.left;
        p.right = h.right;
        if (isValidUplink(p.left, p))
            p.left.predecessor = p;
        if (isValidUplink(p.right, p))
            p.right.predecessor = p;
    }

    TrieEntry<K, V> nextEntry(TrieEntry<K, V> node) {
// node                 0	: [('node', 0.9375199777855268), ('entry', 0.13181408998027178), ('v', 0.09417620191470641), ('a', 0.05742260903331935), ('updateF', 0.056605621895794225), ('e', 0.04190654434530147), ('current', 0.04171090434462949), ('flags', 0.03273453711148809), ('h', 0.03163550002960863), ('value', 0.03158880308719787)]
        return (node == null) ? firstEntry() : nextEntryImpl(node.predecessor, node, null);
    }

    TrieEntry<K, V> nextEntryImpl(TrieEntry<K, V> start, TrieEntry<K, V> previous, TrieEntry<K, V> tree) {
// start                No	: [('entry', 0.8144173611678232), ('v', 0.09423844252911526), ('value', 0.07339917193385781), ('bytes', 0.06563918504821642), ('iter', 0.06350022158975163), ('updateF', 0.05664323488011547), ('node', 0.05107763338593749), ('e', 0.04195218998337101), ('current', 0.038526489148429044), ('cqlType', 0.03287832081940747)]
// previous             No	: [('current', 0.7541174929379318), ('ep', 0.37707576979064217), ('count', 0.3768741396207834), ('entry', 0.1318140901762525), ('message', 0.12615316144793115), ('value', 0.12478971437915186), ('v', 0.09417620214655478), ('end', 0.08397917397292924), ('text', 0.0834681229906753), ('bytes', 0.06128958767411926)]
// tree                 No	: [('next', 0.5537207014708966), ('updated', 0.1706787557684463), ('entry', 0.13181409002926642), ('current', 0.09937373578108677), ('v', 0.09417620197266785), ('max', 0.08545490771268699), ('previous', 0.08538032572457174), ('updateF', 0.056605621897515855), ('node', 0.051040289754425816), ('c', 0.04198332802812782)]
        TrieEntry<K, V> current = start;
// current              4	: [('original', 0.8036039692126803), ('previous', 0.7581676198642637), ('p', 0.6146224891531489), ('start', 0.5626753775159596), ('current', 0.5032003346156924), ('node', 0.500855912511256), ('left', 0.5004752742540044), ('ep', 0.3770757711588711), ('next', 0.25253860641146164), ('min', 0.25127172113303353)]
        if (previous == null || start != previous.predecessor) {
            while (!current.left.isEmpty()) {
                if (previous == current.left)
                    break;
                if (isValidUplink(current.left, current))
                    return current.left;
                current = current.left;
            }
        }
        if (current.isEmpty())
            return null;
        if (current.right == null)
            return null;
        if (previous != current.right) {
            if (isValidUplink(current.right, current))
                return current.right;
            return nextEntryImpl(current.right, previous, tree);
        }
        while (current == current.parent.right) {
            if (current == tree)
                return null;
            current = current.parent;
        }
        if (current == tree)
            return null;
        if (current.parent.right == null)
            return null;
        if (previous != current.parent.right && isValidUplink(current.parent.right, current.parent))
            return current.parent.right;
        if (current.parent.right == current.parent)
            return null;
        return nextEntryImpl(current.parent.right, previous, tree);
    }

    TrieEntry<K, V> firstEntry() {
        return isEmpty() ? null : followLeft(root);
    }

    TrieEntry<K, V> followLeft(TrieEntry<K, V> node) {
// node                 0	: [('node', 0.5313764867612744), ('path', 0.4702609475507469), ('bitIndex', 0.18814533733767247), ('entry', 0.1319236151938441), ('p', 0.10029477305478073), ('child', 0.10010611689297008), ('v', 0.09423844264503774), ('from', 0.0939925504964669), ('updateF', 0.056643234883558706), ('e', 0.041952190300005784)]
        while (true) {
            TrieEntry<K, V> child = node.left;
// child                No	: [('current', 0.6675867864991312), ('p', 0.30016696596476616), ('keyEnd', 0.2822769938765766), ('failedDirectories', 0.20835893595621757), ('leftBound', 0.20835881998051867), ('node', 0.2058219417178431), ('parent', 0.1501093860817539), ('lastToken', 0.15008413674999743), ('indexedColumn', 0.15002762321175822), ('inner', 0.14115554414039239)]
            if (child.isEmpty())
                child = node.right;
            if (child.bitIndex <= node.bitIndex)
                return child;
            node = child;
        }
    }

    static boolean isValidUplink(TrieEntry<?, ?> next, TrieEntry<?, ?> from) {
// next                 0	: [('next', 0.2526160434241311), ('partitionKey', 0.25252326715216183), ('map', 0.0849957596073316), ('replicaPlan', 0.08274503385004203), ('mapType', 0.08254093970196735), ('mt', 0.05502966116080796), ('resolver', 0.04128007756278185), ('type', 0.03434383681166496), ('entry', 0.030522349737230493), ('cHeader', 0.027642686888132425)]
// from                 No	: [('path', 0.47026094731617685), ('bitIndex', 0.18814537593110436), ('node', 0.09399421247637724), ('map', 0.084995752741538), ('replicaPlan', 0.0827450310899906), ('mapType', 0.08254093927782412), ('mt', 0.05502966080232195), ('resolver', 0.04128007698479979), ('type', 0.03434381820858469), ('entry', 0.030522320064757812)]
        return next != null && next.bitIndex <= from.bitIndex && !next.isEmpty();
    }

    private static class Reference<E> {

        private E item;

        public void set(E item) {
// item                 0	: [('item', 0.9062580813265881), ('e', 0.09826323241079608), ('natural', 0.07480273156988361), ('object', 0.06411481714384186), ('value', 0.054627813797169425), ('to', 0.04277772360017494), ('replicas', 0.022311092616571212), ('v', 0.021496916093488562), ('first', 0.02140421422763736), ('elt', 0.021375993893162228)]
            this.item = item;
        }

        public E get() {
            return item;
        }
    }

    static class TrieEntry<K, V> extends BasicEntry<K, V> {

        private static final long serialVersionUID = 4596023148184140013L;

        protected int bitIndex;

        protected TrieEntry<K, V> parent;

        protected TrieEntry<K, V> left;

        protected TrieEntry<K, V> right;

        protected TrieEntry<K, V> predecessor;

        public TrieEntry(K key, V value, int bitIndex) {
// key                  0	: [('key', 0.4291579018461905), ('fromKey', 0.05771695587301456), ('column', 0.05113943036557085), ('cfs', 0.04141489472129215), ('keyspaceName', 0.03441629507433478), ('baseCfs', 0.027796194978439166), ('message', 0.025661982105472343), ('returnDataType', 0.024001500359490436), ('name', 0.02353313472788075), ('nextToken', 0.020977836623223108)]
// value                1	: [('repairs', 0.7539225082614196), ('value', 0.6873384627406165), ('old', 0.14749315316823397), ('oldToReplace', 0.04916451089531248), ('metadata', 0.01113697601176671), ('a', 0.010490966248562454), ('found', 0.006532320126015891), ('v', 0.0059146183577407545), ('i', 0.004781237037141597), ('now', 0.004163320683385078)]
// bitIndex             1	: [('h', 0.5357564596492418), ('bitIndex', 0.2143394672994663), ('version', 0.19250841661781615), ('size', 0.08551067187820346), ('index', 0.0814171676861861), ('maxSize', 0.07811354610221778), ('digits', 0.07721685808994223), ('i', 0.03907199275392959), ('expectedSize', 0.03892061723296682), ('nowInSec', 0.015525904638134688)]
            super(key, value);
            this.bitIndex = bitIndex;
            this.parent = null;
            this.left = this;
            this.right = null;
            this.predecessor = this;
        }

        public boolean isEmpty() {
            return key == null;
        }

        public boolean isInternalNode() {
            return left != this && right != this;
        }

        public boolean isExternalNode() {
            return !isInternalNode();
        }
    }

    private class EntrySet extends AbstractSet<Map.Entry<K, V>> {

        @Override
        public Iterator<Map.Entry<K, V>> iterator() {
            return new EntryIterator();
        }

        @Override
        public boolean contains(Object o) {
// o                    0	: [('o', 0.8971910841593773), ('type', 0.4173084714045874), ('other', 0.13271373139907292), ('value', 0.08707393760874747), ('column', 0.08632219166836602), ('e', 0.07120102433505472), ('that', 0.0698978337748063), ('receiver', 0.06583470385766119), ('typeInSchema', 0.049987964324575064), ('typeInHeader', 0.044731685650897426)]
            if (!(o instanceof Map.Entry))
                return false;
            TrieEntry<K, V> candidate = getEntry(((Map.Entry<?, ?>) o).getKey());
// candidate            No	: [('entry', 0.13181408993127747), ('v', 0.09417620185674538), ('ret', 0.05704605632853482), ('updateF', 0.056605621894072616), ('node', 0.05104028969904701), ('builder', 0.0449874759030784), ('e', 0.041906544186984675), ('current', 0.03932304747055516), ('result', 0.036929789047530935), ('h', 0.03163550002275804)]
            return candidate != null && candidate.equals(o);
        }

        @Override
        public boolean remove(Object o) {
// o                    0	: [('o', 0.8491141803336194), ('key', 0.38795627916943776), ('endpoint', 0.0120397622637268), ('m', 0.010321619745968591), ('k', 0.010013007606677012), ('name', 0.007157492339099133), ('value', 0.00486217855469398), ('reader', 0.0046900172834895846), ('i', 0.004514417271418037), ('obj', 0.0043492815722923385)]
            int size = size();
// size                 0	: [('size', 0.7551205535536034), ('cmp', 0.0680794824464581), ('c', 0.0663101475169173), ('retval', 0.06271846116020578), ('i', 0.0148179944940773), ('builder', 0.008413887072813158), ('result', 0.008181486387899327), ('length', 0.0064479475700964135), ('count', 0.005944558730895145), ('sb', 0.005515655555283093)]
            AbstractPatriciaTrie.this.remove(o);
            return size != size();
        }

        @Override
        public int size() {
            return AbstractPatriciaTrie.this.size();
        }

        @Override
        public void clear() {
            AbstractPatriciaTrie.this.clear();
        }

        private class EntryIterator extends TrieIterator<Map.Entry<K, V>> {

            @Override
            public Map.Entry<K, V> next() {
                return nextEntry();
            }
        }
    }

    private class KeySet extends AbstractSet<K> {

        @Override
        public Iterator<K> iterator() {
            return new KeyIterator();
        }

        @Override
        public int size() {
            return AbstractPatriciaTrie.this.size();
        }

        @Override
        public boolean contains(Object o) {
// o                    0	: [('o', 0.8971911034105424), ('endpoint', 0.007042251010264196), ('value', 0.00486217855469398), ('obj', 0.0043492815722923385), ('parsed', 0.003399235584674067), ('key', 0.0031768578571109104), ('ep', 0.0029977632149346288), ('dc', 0.002992219647275996), ('other', 0.002972967528597586), ('DATA_CENTER', 0.0026385102607496577)]
            return containsKey(o);
        }

        @Override
        public boolean remove(Object o) {
// o                    0	: [('o', 0.8491141803336194), ('key', 0.38795627916943776), ('endpoint', 0.0120397622637268), ('m', 0.010321619745968591), ('k', 0.010013007606677012), ('name', 0.007157492339099133), ('value', 0.00486217855469398), ('reader', 0.0046900172834895846), ('i', 0.004514417271418037), ('obj', 0.0043492815722923385)]
            int size = size();
// size                 0	: [('size', 0.7551205535536034), ('cmp', 0.0680794824464581), ('c', 0.0663101475169173), ('retval', 0.06271846116020578), ('i', 0.0148179944940773), ('builder', 0.008413887072813158), ('result', 0.008181486387899327), ('length', 0.0064479475700964135), ('count', 0.005944558730895145), ('sb', 0.005515655555283093)]
            AbstractPatriciaTrie.this.remove(o);
            return size != size();
        }

        @Override
        public void clear() {
            AbstractPatriciaTrie.this.clear();
        }

        private class KeyIterator extends TrieIterator<K> {

            @Override
            public K next() {
                return nextEntry().getKey();
            }
        }
    }

    private class Values extends AbstractCollection<V> {

        @Override
        public Iterator<V> iterator() {
            return new ValueIterator();
        }

        @Override
        public int size() {
            return AbstractPatriciaTrie.this.size();
        }

        @Override
        public boolean contains(Object o) {
// o                    0	: [('o', 0.8971911034105424), ('mapType', 0.2500168233486607), ('token', 0.08399595145132242), ('i', 0.006888797312618536), ('value', 0.00486217855469398), ('obj', 0.0043492815722923385), ('cfs', 0.0036492253347625925), ('parsed', 0.003399235584674067), ('other', 0.002972967528597586), ('key', 0.0025055123634506716)]
            return containsValue(o);
        }

        @Override
        public void clear() {
            AbstractPatriciaTrie.this.clear();
        }

        @Override
        public boolean remove(Object o) {
// o                    0	: [('o', 0.8491141803336194), ('other', 0.7514353417512313), ('key', 0.04096705082498913), ('idx', 0.03691508154303311), ('protocolVersion', 0.02465770613383317), ('version', 0.013190421838355011), ('out', 0.011056937694869054), ('column', 0.01015571266409497), ('k', 0.010013007606677012), ('size', 0.0057645846884401344)]
            for (Iterator<V> it = iterator(); it.hasNext(); ) {
// it                   0	: [('it', 0.5051616480228983), ('canon', 0.2524123118319979), ('btree', 0.2524012397906707), ('value', 0.1103592238184682), ('values', 0.08839214581885099), ('valuesClass', 0.026191616798610873), ('i', 0.02584860742369489), ('promise', 0.024868400656996383), ('valuesType', 0.021435637134707846), ('options', 0.020039569999422482)]
                V value = it.next();
// value                2	: [('node', 0.641758174171732), ('oldVal', 0.1518841401881656), ('value', 0.1421251015512624), ('ai', 0.10115414761658373), ('castedCurrent', 0.10115298787681078), ('key', 0.0712659964159762), ('v', 0.058869516022904744), ('next', 0.051554833756181244), ('prev', 0.05075199417383001), ('o', 0.05061932959629696)]
                if (Tries.areEqual(value, o)) {
                    it.remove();
                    return true;
                }
            }
            return false;
        }

        private class ValueIterator extends TrieIterator<V> {

            @Override
            public V next() {
                return nextEntry().getValue();
            }
        }
    }

    abstract class TrieIterator<E> implements Iterator<E> {

        protected int expectedModCount = AbstractPatriciaTrie.this.modCount;

        protected TrieEntry<K, V> next;

        protected TrieEntry<K, V> current;

        protected TrieIterator() {
            next = AbstractPatriciaTrie.this.nextEntry(null);
        }

        protected TrieIterator(TrieEntry<K, V> firstEntry) {
// firstEntry           No	: [('unfiltered', 0.7511414835114685), ('entry', 0.13181408988228357), ('v', 0.09417620179878479), ('updateF', 0.05660562189235101), ('node', 0.051040289671357916), ('e', 0.04190654402866906), ('current', 0.03932304743884899), ('h', 0.03163550001590751), ('value', 0.03158880278756633), ('found', 0.03138276012048024)]
            next = firstEntry;
        }

        protected TrieEntry<K, V> nextEntry() {
            if (expectedModCount != AbstractPatriciaTrie.this.modCount)
                throw new ConcurrentModificationException();
            TrieEntry<K, V> e = next;
// e                    No	: [('row', 0.5365458308545565), ('current', 0.26622709559849417), ('next', 0.19768555154742473), ('entry', 0.13181409002926642), ('state', 0.11732516056685496), ('v', 0.09417620197266785), ('node', 0.06139442929959572), ('hasNext', 0.05769499693880383), ('updateF', 0.056605621897515855), ('marker', 0.036072691891882266)]
            if (e == null)
                throw new NoSuchElementException();
            next = findNext(e);
            current = e;
            return e;
        }

        protected TrieEntry<K, V> findNext(TrieEntry<K, V> prior) {
// prior                No	: [('found', 0.25003912835907194), ('entry', 0.13181408988228357), ('current', 0.12535389651924678), ('added', 0.12504202162330857), ('v', 0.09417620179878479), ('updateF', 0.05660562189235101), ('node', 0.051040289671357916), ('e', 0.04190654402866906), ('h', 0.03163550001590751), ('value', 0.03158880278756633)]
            return AbstractPatriciaTrie.this.nextEntry(prior);
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public void remove() {
            if (current == null)
                throw new IllegalStateException();
            if (expectedModCount != AbstractPatriciaTrie.this.modCount)
                throw new ConcurrentModificationException();
            TrieEntry<K, V> node = current;
// node                 4	: [('current', 0.21463961080496105), ('added', 0.14289916448045142), ('entry', 0.13181408988228357), ('v', 0.09417620179878479), ('node', 0.07179314578574297), ('h', 0.07153786774035592), ('updateF', 0.05660562189235101), ('e', 0.04190654402866906), ('value', 0.03158880278756633), ('found', 0.03138276012048024)]
            current = null;
            AbstractPatriciaTrie.this.removeEntry(node);
            expectedModCount = AbstractPatriciaTrie.this.modCount;
        }
    }
}
