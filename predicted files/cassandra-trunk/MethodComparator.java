// Type of training: selfTestingIdentifier
// Path to file: C:\Users\Igor\IdeaProjects\SLP-Core-0.3\java-projects\cassandra-trunk\test\unit\org\apache\cassandra\MethodComparator.java
// Number of identifiers: 23	Accuracy: 34.78%	MRR: 46.38%
// True 	Rank of true in predictions : [(predicted token, probability of a token), ...]

package org.apache.cassandra;

import org.junit.Ignore;
import org.junit.runners.model.FrameworkMethod;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.lang.reflect.Method;
import java.util.Comparator;

public class MethodComparator<T> implements Comparator<T> {

    private static final char[] METHOD_SEPARATORS = { 1, 7 };

    private MethodComparator() {
    }

    public static MethodComparator<FrameworkMethod> getFrameworkMethodComparatorForJUnit4() {
        return new MethodComparator<FrameworkMethod>();
    }

    @Override
    public int compare(T o1, T o2) {
// o1                   1	: [('o2', 0.8806966344292995), ('o1', 0.8758945951568233), ('aClass', 0.022729068245371072), ('t', 0.008490349054401982), ('value', 0.008217249552863199), ('frameworkMethod', 0.005682445811878616), ('left', 0.005381614746892206), ('item', 0.0030796570024452484), ('position', 0.0026848266339884043), ('source', 0.0023808332047281174)]
// o2                   1	: [('o1', 0.8807096655176248), ('o2', 0.875444073685687), ('aClass', 0.022729068245371072), ('result', 0.006771824470390816), ('value', 0.006512385235009724), ('payload', 0.006330017063516976), ('frameworkMethod', 0.005682445811878616), ('right', 0.004509260378675683), ('target', 0.0035332900393016467), ('end', 0.0013525850374141442)]
        final MethodPosition methodPosition1 = this.getIndexOfMethodPosition(o1);
// methodPosition1      No	: [('methodPosition2', 0.5312537215763824), ('builder', 0.033655548291252634), ('result', 0.03272594555159731), ('o', 0.031337764051928965), ('methodPosition', 0.031253953524337054), ('sb', 0.022062622221132373), ('size', 0.020782054772217413), ('ret', 0.018530181199989663), ('row', 0.011069617809186673), ('value', 0.006543596863553228)]
        final MethodPosition methodPosition2 = this.getIndexOfMethodPosition(o2);
// methodPosition2      No	: [('methodPosition1', 0.5312537215763824), ('o2', 0.04165105284355794), ('o', 0.031337764051928965), ('methodPosition', 0.031253953524337054), ('comparator', 0.02426688255356487), ('next', 0.022198786023279397), ('right', 0.022166764857525546), ('b', 0.019856751423490217), ('range', 0.01823102641654402), ('sstable', 0.017934396448210772)]
        return methodPosition1.compareTo(methodPosition2);
    }

    private MethodPosition getIndexOfMethodPosition(final Object method) {
// method               1	: [('name', 0.2511197646337692), ('method', 0.25032231849386305), ('value', 0.045980993773306444), ('type', 0.03497040252857986), ('s', 0.03260944088535386), ('notification', 0.028868709651157457), ('c', 0.0286796391289444), ('cmp', 0.027243091434408784), ('i', 0.021000204840485176), ('msgType', 0.0192872866555489)]
        if (method instanceof FrameworkMethod) {
            return this.getIndexOfMethodPosition((FrameworkMethod) method);
        } else if (method instanceof Method) {
            return this.getIndexOfMethodPosition((Method) method);
        } else {
            return new NullMethodPosition();
        }
    }

    private MethodPosition getIndexOfMethodPosition(final FrameworkMethod frameworkMethod) {
// frameworkMethod      No	: [('aClass', 0.6000071820723933), ('o1', 0.025111428001218548), ('o2', 0.025059264989925285), ('i', 0.0034443986563092686), ('cfs', 0.0018246126673812963), ('value', 0.0009175968172268051), ('e', 0.0008923229627564906), ('key', 0.0008653261652857458), ('sstable', 0.0008083695629309537), ('name', 0.0007884700489173061)]
        return getIndexOfMethodPosition(frameworkMethod.getMethod());
    }

    private MethodPosition getIndexOfMethodPosition(final Method method) {
// method               2	: [('methodName', 0.8125113175338516), ('name', 0.25111975182701957), ('method', 0.25032786738324775), ('m', 0.08839798892264283), ('get_counter', 0.02941967176029516), ('mCleanerClean', 0.02941951712717769), ('i', 0.02033383724321538), ('custom', 0.014714871839176238), ('mFindLoadedClass', 0.014713634774236515), ('mDirectBufferCleaner', 0.014713634774236515)]
        if (method.getAnnotation(Ignore.class) == null) {
            final Class<?> aClass = method.getDeclaringClass();
// aClass               0	: [('aClass', 0.5103616753309295), ('frameworkMethod', 0.27272978324413727), ('clazz', 0.06155368133564098), ('type', 0.026224638001217493), ('o1', 0.022838700225289017), ('o2', 0.022786537470662678), ('c', 0.021824543091616484), ('klass', 0.020554939285757638), ('cls', 0.010365044448649514), ('expected', 0.01030803761415162)]
            return getIndexOfMethodPosition(aClass, method.getName());
        } else {
            return new NullMethodPosition();
        }
    }

    private MethodPosition getIndexOfMethodPosition(final Class<?> aClass, final String methodName) {
// aClass               0	: [('aClass', 0.568188273185913), ('clazz', 0.06155368133564098), ('type', 0.026224638001217493), ('o1', 0.022838700225289017), ('o2', 0.022786537470662678), ('frameworkMethod', 0.022729783244137266), ('c', 0.021824543091616484), ('klass', 0.020554939285757638), ('cls', 0.010365044448649514), ('expected', 0.01030803761415162)]
// methodName           0	: [('methodName', 0.7537554565352722), ('method', 0.6666738057006946), ('name', 0.02019974808813963), ('id', 0.015383791927070522), ('keyspace', 0.012938802889984826), ('message', 0.009281903523501578), ('columnFamily', 0.007563950247596656), ('expectedDatacenter', 0.007472742244174308), ('endpointString', 0.0074727325796761996), ('expectedRack', 0.007468108772618421)]
        MethodPosition methodPosition;
// methodPosition       No	: [('o', 0.38919100484628844), ('builder', 0.15239469293987443), ('other', 0.07884276341059823), ('methodPosition1', 0.06250759778817787), ('methodPosition2', 0.0625074431550604), ('receiver', 0.05035083253193086), ('obj', 0.0418422168153863), ('e', 0.04103973214147813), ('parsed', 0.0369926440796732), ('sign', 0.027915138688281084)]
        for (final char methodSeparator : METHOD_SEPARATORS) {
// methodSeparator      2	: [('args', 0.2556515317215385), ('targetBean', 0.2555602403932678), ('methodSeparator', 0.25172801413938667), ('subject', 0.12778362235526297), ('c', 0.05703959670524422), ('threadCount', 0.027798785360627014), ('quoteChar', 0.010351255222059581), ('last', 0.006926096839464766), ('operator', 0.006915272601583893), ('symbol', 0.005178377533046952)]
            methodPosition = getIndexOfMethodPosition(aClass, methodName, methodSeparator);
            if (!(methodPosition instanceof NullMethodPosition)) {
                return methodPosition;
            }
        }
        return new NullMethodPosition();
    }

    private MethodPosition getIndexOfMethodPosition(final Class<?> aClass, final String methodName, final char methodSeparator) {
// aClass               1	: [('resourceName', 0.5250047689682585), ('aClass', 0.5103616560025788), ('name', 0.17578846708158002), ('clazz', 0.06155368133969391), ('type', 0.026224638100245456), ('c', 0.021824543156320014), ('klass', 0.02055493928808897), ('cls', 0.010365044450873249), ('expected', 0.010308037657155796), ('leftType', 0.008544128370005346)]
// methodName           0	: [('methodName', 0.7537574645374969), ('name', 0.020227962748918943), ('id', 0.015393830227700382), ('keyspace', 0.012944948341724589), ('message', 0.009293970010183979), ('columnFamily', 0.007567970186182078), ('expectedDatacenter', 0.007476755511833596), ('endpointString', 0.007476745847335488), ('expectedRack', 0.007472121698178933), ('tableName', 0.006132197305115752)]
// methodSeparator      0	: [('methodSeparator', 0.25172801413938667), ('c', 0.05703959670524422), ('i', 0.04416386314321466), ('quoteChar', 0.010351255222059581), ('j', 0.009027444430891969), ('type', 0.008522817846217287), ('last', 0.006926096839464766), ('operator', 0.006915272601583893), ('uuid', 0.006357683841746356), ('symbol', 0.005178377533046952)]
        final InputStream inputStream = aClass.getResourceAsStream(aClass.getSimpleName() + ".class");
// inputStream          0	: [('inputStream', 0.7575029432119793), ('p', 0.39888782778153115), ('log', 0.13295540425055147), ('is', 0.13294595332053585), ('socket', 0.13294205189576655), ('input', 0.049749117792821713), ('in', 0.020088918587147404), ('stream', 0.010010288230541342), ('from', 0.005029965148689074), ('tsf', 0.00500201542016096)]
        final LineNumberReader lineNumberReader = new LineNumberReader(new InputStreamReader(inputStream));
// lineNumberReader     No	: [('reader', 0.44212138756647457), ('executor', 0.27089830676918836), ('indexer', 0.2635480291807828), ('input', 0.21825875401596856), ('r', 0.21824199658588467), ('client', 0.04697631413673946), ('es', 0.03337709873024686), ('inp', 0.03068311821323418), ('filter', 0.03025066372100561), ('lock', 0.02709431744115521)]
        final String methodNameWithSeparator = methodName + methodSeparator;
// methodNameWithSeparator No	: [('n', 0.26331047278847874), ('strace', 0.26327489200907866), ('m', 0.10281593106440247), ('hosts', 0.08968041942397179), ('indexName', 0.0665541723771424), ('a', 0.0520384495981105), ('item', 0.04687785363013038), ('reader', 0.04419416591340465), ('key', 0.041905002129718934), ('c', 0.041433463204149784)]
        try {
            try {
                String line;
// line                 0	: [('line', 0.5346128402984651), ('value', 0.11402580952647386), ('name', 0.0820100007911839), ('query', 0.06886110843753904), ('type', 0.06210537356164471), ('fName', 0.04955473314220345), ('currentNode', 0.04336602051832319), ('dataCenter', 0.04336592387262477), ('filename', 0.037869353779339004), ('command', 0.03752151688875182)]
                while ((line = lineNumberReader.readLine()) != null) {
                    if (line.contains(methodNameWithSeparator)) {
                        return new MethodPosition(lineNumberReader.getLineNumber(), line.indexOf(methodNameWithSeparator));
                    }
                }
            } finally {
                lineNumberReader.close();
            }
        } catch (IOException e) {
// e                    0	: [('e', 0.8484860619816283), ('ex', 0.044827243743489675), ('ioe', 0.03008167046635235), ('suppressed', 0.006617781011148578), ('exc', 0.0043909812322465115), ('ignore', 0.0014509872373463981), ('ignored', 0.0014508712637995003), ('ie', 0.0014506683100924292), ('clientException', 2.0307403620985437e-05), ('range', 1.4604305235371078e-05)]
            return new NullMethodPosition();
        }
        return new NullMethodPosition();
    }

    private static class MethodPosition implements Comparable<MethodPosition> {

        private final Integer lineNumber;

        private final Integer indexInLine;

        public MethodPosition(int lineNumber, int indexInLine) {
// lineNumber           No	: [('i', 0.29786017593296465), ('j', 0.027243589926990684), ('size', 0.017131988575360822), ('version', 0.011065782890632163), ('pageSize', 0.010645833937491059), ('index', 0.008851039033573847), ('count', 0.008760652811978697), ('nowInSec', 0.008237082840024324), ('idx', 0.007655716625544738), ('messageSize', 0.007180574295885989)]
// indexInLine          No	: [('i', 0.0781476969778737), ('version', 0.07732495059454958), ('nowInSec', 0.031052016649745804), ('size', 0.017175519002943933), ('length', 0.013796229442540279), ('count', 0.011887436166958153), ('offset', 0.011575789733337909), ('index', 0.008988319989875008), ('end', 0.00828489491774895), ('messagingVersion', 0.006896025692964451)]
            this.lineNumber = lineNumber;
            this.indexInLine = indexInLine;
        }

        @Override
        public int compareTo(MethodPosition o) {
// o                    No	: [('that', 0.0698978337748063), ('methodPosition', 0.06250790705441281), ('methodPosition1', 0.06250759778817787), ('methodPosition2', 0.0625074431550604), ('other', 0.04268080158598494), ('o2', 0.04182202920830644), ('comparator', 0.02436917056634507), ('next', 0.022289304888820353), ('right', 0.022257283246480956), ('b', 0.01993721447071517)]
            if (this.lineNumber.equals(o.lineNumber)) {
                return this.indexInLine.compareTo(o.indexInLine);
            } else {
                return this.lineNumber.compareTo(o.lineNumber);
            }
        }
    }

    private static class NullMethodPosition extends MethodPosition {

        public NullMethodPosition() {
            super(-1, -1);
        }
    }
}
