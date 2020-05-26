// Type of training: selfTestingIdentifier
// Path to file: C:\Users\Igor\IdeaProjects\SLP-Core-0.3\java-projects\xmlgraphics-batik-trunk\batik-test-old\src\test\java\org\apache\batik\bridge\ExternalResourcesTest.java
// Number of identifiers: 47	Accuracy: 70.21%	MRR: 75.80%
// True 	Rank of true in predictions : [(predicted token, probability of a token), ...]

package org.apache.batik.bridge;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.apache.batik.anim.dom.SAXSVGDocumentFactory;
import org.apache.batik.gvt.GraphicsNode;
import org.apache.batik.test.AbstractTest;
import org.apache.batik.test.DefaultTestReport;
import org.apache.batik.test.TestReport;
import org.apache.batik.util.ParsedURL;
import org.apache.batik.util.XMLResourceDescriptor;

public class ExternalResourcesTest extends AbstractTest implements ErrorConstants {

    public static final String ERROR_CANNOT_LOAD_SVG_DOCUMENT = "ExternalResourcesTest.error.cannot.load.svg.document";

    public static final String ERROR_WHILE_PROCESSING_SVG_DOCUMENT = "ExternalResourcesTest.error.while.processing.svg.document";

    public static final String ERROR_UNTHROWN_SECURITY_EXCEPTIONS = "ExternalResourcesTest.error.unthrown.security.exceptions";

    public static final String ERROR_THROWN_SECURITY_EXCEPTIONS = "ExternalResourcesTest.error.thrown.security.exceptions";

    public static final String ERROR_NO_INSERTION_POINT_IN_DOCUMENT = "ExternalResourceTest.error.no.insertion.point.in.document";

    public static final String ERROR_NO_ID_LIST = "ExternalResourceTest.error.no.id.list";

    public static final String ERROR_TARGET_ID_NOT_FOUND = "ExternalResourcesTest.error.target.id.not.found";

    public static final String ENTRY_KEY_ERROR_DESCRIPTION = "ExternalResourcesTest.entry.key.error.description";

    public static final String ENTRY_KEY_INSERTION_POINT_ID = "ExternalResourcesTest.entry.key.insertion.point.id";

    public static final String ENTRY_KEY_TARGET_ID = "ExternalResourcesTest.entry.target.id";

    public static final String ENTRY_KEY_EXPECTED_EXCEPTION_ON = "ExternalResourcesTest.entry.key.expected.exception.on";

    public static final String ENTRY_KEY_UNEXPECTED_EXCEPTION_ON = "ExternalResourcesTest.entry.key.unexpected.exception.on";

    public static final String EXTERNAL_STYLESHEET_ID = "external-stylesheet";

    public static final String testNS = "http://xml.apache.org/batik/test";

    public static final String INSERTION_POINT_ID = "insertionPoint";

    public static final String FILE_DIR = "test-resources/org/apache/batik/bridge/";

    protected boolean secure = true;

    String svgURL;

    public void setId(String id) {
// id                   0	: [('id', 0.8862693595200326), ('path', 0.7000260987220547), ('url', 0.19585330947248927), ('file', 0.0250493175351319), ('prefix', 0.014567483559764291), ('name', 0.006025474798655082), ('s', 0.005275243058273005), ('ns', 0.004680837132223944), ('hackedFile', 0.004168799568417648), ('namespaceURI', 0.0036074427255874245)]
        super.setId(id);
        String file = id;
// file                 No	: [('path', 0.8875261002770144), ('clName', 0.7503016713687236), ('eng', 0.1422496634789528), ('si', 0.10465577173052959), ('as', 0.08719899640877604), ('sb', 0.053008876703423885), ('id', 0.04048898260038237), ('xt', 0.040365476823749954), ('lnLocs', 0.040357822239781446), ('t', 0.036570958456579365)]
        int idx = file.indexOf('.');
// idx                  1	: [('n', 0.7037981473749063), ('idx', 0.2549432360512131), ('dotIndex', 0.2501251803088708), ('i', 0.11934573829382722), ('offset', 0.06778256840133966), ('s', 0.05184111047156464), ('len', 0.034354128180217616), ('fileNameStart', 0.033719066673481894), ('v', 0.017021118914732136), ('suffixStart', 0.01694447540847584)]
        if (idx != -1) {
            file = file.substring(0, idx);
        }
        svgURL = resolveURL(FILE_DIR + file + ".svg");
    }

    public Boolean getSecure() {
        return secure ? Boolean.TRUE : Boolean.FALSE;
    }

    public void setSecure(Boolean secure) {
// secure               0	: [('secure', 0.9375055398749951), ('validate', 0.00791062000355532), ('constrain', 0.003955389895509412), ('isInherited', 0.0013308745388312206), ('b', 0.000692792643558198), ('customSpacing', 0.0006659377814692392), ('i', 0.0005025473531734412), ('e', 0.00048073670928962367), ('n', 0.00040837616375652), ('ctx', 0.0002796125454942746)]
        this.secure = secure;
    }

    protected String resolveURL(String url) {
// url                  0	: [('url', 0.8755342988329406), ('o', 0.14441944088348704), ('svgFile', 0.14436740018192037), ('id', 0.108320760345291), ('clName', 0.10827165334262967), ('purl', 0.09120631069600234), ('file', 0.08549722939816184), ('base', 0.07506593744201254), ('expectedSource', 0.038610397588735815), ('mue', 0.036091171721995635)]
        File f = (new File(url)).getAbsoluteFile();
// f                    0	: [('f', 0.8858367344709238), ('cx', 0.17098633165894248), ('file', 0.08549124583249794), ('s', 0.02778186243355746), ('n', 0.021213704472028905), ('ln', 0.01764790922545487), ('dst', 0.01669890861589714), ('aci', 0.012672391273573964), ('token', 0.01232736029648159), ('o', 0.012147391708589188)]
        if (f.getParentFile().exists()) {
            try {
                return f.toURI().toURL().toString();
            } catch (MalformedURLException e) {
// e                    0	: [('e', 0.7338745061123945), ('mue', 0.18828238044370008), ('ex', 0.045804034373471525), ('ctx', 6.115973057198469e-05), ('node', 2.796351336182326e-05), ('ret', 1.9011966810675116e-05), ('uri', 1.4568548522635315e-05), ('chunk', 9.86629033432135e-06), ('ps', 8.572090832950533e-06), ('p1', 6.048401805277442e-06)]
                throw new IllegalArgumentException();
            }
        }
        try {
            return (new URL(url)).toString();
        } catch (MalformedURLException e) {
// e                    0	: [('e', 0.7338745061123945), ('mue', 0.18828238044370008), ('ex', 0.045804034373471525), ('ctx', 6.115973057198469e-05), ('node', 2.796351336182326e-05), ('ret', 1.9011966810675116e-05), ('uri', 1.4568548522635315e-05), ('chunk', 9.86629033432135e-06), ('ps', 8.572090832950533e-06), ('p1', 6.048401805277442e-06)]
            throw new IllegalArgumentException(url);
        }
    }

    public TestReport runImpl() throws Exception {
        DefaultTestReport report = new DefaultTestReport(this);
// report               0	: [('report', 0.954281290311834), ('ase', 0.7875305927600265), ('e', 0.5432570485478838), ('imageElement', 0.17724233050351637), ('compositeFilter', 0.17715020511250623), ('p', 0.06975778381987416), ('ang', 0.04877184740549916), ('res', 0.041430216097537394), ('n', 0.03809379093925013), ('sb', 0.037508017494038794)]
        String parserClassName = XMLResourceDescriptor.getXMLParserClassName();
// parserClassName      1	: [('parser', 0.6169969488181972), ('parserClassName', 0.37531058936415945), ('nodeDescription', 0.3753015635141197), ('userAgent', 0.046489154176843836), ('s', 0.02397203977418821), ('ln', 0.0072620898534742385), ('prefix', 0.006764986504398547), ('ns', 0.004977887534562079), ('namespaceURI', 0.00495921731578239), ('name', 0.0044752636572972316)]
        SAXSVGDocumentFactory f = new SAXSVGDocumentFactory(parserClassName);
// f                    0	: [('f', 0.882388538817277), ('df', 0.2381268854010917), ('impl', 0.005243754991352052), ('e', 0.002564070042622957), ('ctx', 0.002362945878827608), ('loader', 0.0020917295859288155), ('b', 0.0011363464531007228), ('parser', 0.0010609729722036074), ('canvas', 0.0010537934454189083), ('textElement', 0.0010466672874029726)]
        Document doc = null;
// doc                  0	: [('doc', 0.9037148709921783), ('svgDoc', 0.7662110118629579), ('ois', 0.5625032977840596), ('element', 0.3103291690272617), ('realRoot', 0.2676111800068726), ('node', 0.18767605921767466), ('childElt', 0.1804666902030904), ('n', 0.12553808440431463), ('child', 0.10811985287445228), ('inverseTransform', 0.0974472268445796)]
        try {
            doc = f.createDocument(svgURL);
        } catch (IOException e) {
// e                    0	: [('e', 0.5757543040926443), ('ioe', 0.23224480689460106), ('be', 0.23011724944428466), ('ex', 0.1719311890986261), ('th', 0.11506684276788214), ('ioEx', 0.04306623403059721), ('ignored', 0.032518282187674365), ('io', 0.02612996040680798), ('ioe2', 0.02148506032737621), ('e1', 0.004645555980813388)]
            report.setErrorCode(ERROR_CANNOT_LOAD_SVG_DOCUMENT);
            report.addDescriptionEntry(ENTRY_KEY_ERROR_DESCRIPTION, e.getMessage());
            report.setPassed(false);
            return report;
        } catch (Exception e) {
// e                    0	: [('e', 0.6624917480000742), ('ex', 0.28332814061766887), ('be', 0.23011724944428466), ('th', 0.11506684276788214), ('te', 0.0076786890772948336), ('exc', 0.0030875480998947746), ('exception', 0.0007789725468740477), ('y', 0.000714801120195915), ('ctx', 0.00044118489907983235), ('i', 0.0004206435892297972)]
            report.setErrorCode(ERROR_CANNOT_LOAD_SVG_DOCUMENT);
            report.addDescriptionEntry(ENTRY_KEY_ERROR_DESCRIPTION, e.getMessage());
            report.setPassed(false);
            return report;
        }
        List failures = new ArrayList();
// failures             0	: [('failures', 0.5002562462601517), ('l', 0.15187468072252186), ('children', 0.1500986196704987), ('srcs', 0.08634522345923372), ('v', 0.07603296160574975), ('dirtyAreas', 0.04831049824579922), ('list', 0.046510918002827445), ('textRuns', 0.045346090088036806), ('glyphs', 0.03666622481223783), ('keySplines', 0.03666061628921562)]
        MyUserAgent userAgent = buildUserAgent();
// userAgent            0	: [('userAgent', 0.920468370491441), ('defSz', 0.1253235952875848), ('e', 0.10384625409280311), ('t', 0.05191655953018311), ('sm', 0.05060073760593644), ('bounds', 0.025356060515053326), ('sppsm', 0.025200607730894144), ('srcSM', 0.025098830847492874), ('litRegion', 0.025069419468494284), ('myBounds', 0.025063814807781457)]
        GVTBuilder builder = new GVTBuilder();
// builder              0	: [('builder', 0.9639461560267844), ('gnBridge', 0.6106985030223808), ('gnce', 0.09701522201939931), ('bc', 0.013688001463575865), ('isStatic', 0.013680735156252607), ('i', 0.013313403627319993), ('g', 0.006529444448418881), ('n', 0.005537757959712313), ('gn', 0.004697732478884012), ('t', 0.003972388635955888)]
        BridgeContext ctx = new BridgeContext(userAgent);
// ctx                  0	: [('ctx', 0.9288153332815461), ('bc', 0.16862016836550528), ('theCtx', 0.03418917551473705), ('i', 0.013313683379355816), ('g', 0.006529790907797762), ('n', 0.005537885620822617), ('subCtx', 0.004700437014310468), ('t', 0.003972640166262963), ('sb', 0.0031750295308854075), ('y', 0.002375994139386104)]
        ctx.setDynamic(true);
        Throwable th = null;
// th                   No	: [('e', 0.7675883022858498), ('t', 0.6310459316359591), ('temp', 0.4591393166528517), ('v', 0.25898233393773445), ('be', 0.2301172064163398), ('res', 0.18733175503726576), ('filt', 0.140203365065302), ('chunkType', 0.09091182436619086), ('r', 0.07649307235024334), ('rgn', 0.07586275399002841)]
        try {
            GraphicsNode gn = builder.build(ctx, doc);
// gn                   0	: [('gn', 0.8989074590568703), ('ti', 0.027821359301923428), ('oos', 0.02781742801525926), ('rootElement', 0.027794050638102245), ('input', 0.013918261553605643), ('ta', 0.01390460092478419), ('node', 0.011459688744931928), ('result', 0.009435458086504114), ('i', 0.006116241059813898), ('filteredNode', 0.004765960648633845)]
            gn.getBounds();
            th = userAgent.getDisplayError();
        } catch (BridgeException e) {
// e                    0	: [('e', 0.7344081115500636), ('ex', 0.4558525811352574), ('t', 0.15619192836781676), ('be', 0.07768375333334435), ('defSz', 0.014921397662034777), ('sm', 0.006027465909021047), ('userAgent', 0.005965715660672938), ('bounds', 0.003020694289241867), ('sppsm', 0.0030012640190111403), ('srcSM', 0.0029885431850743016)]
            th = e;
        } catch (SecurityException e) {
// e                    0	: [('e', 0.7344081115500636), ('se', 0.317751876997868), ('secEx', 0.17249434859394266), ('t', 0.15619192836781676), ('ex', 0.14013251101394192), ('se2', 0.03613228045895812), ('defSz', 0.014921397662034777), ('sm', 0.006027465909021047), ('userAgent', 0.005965715660672938), ('bounds', 0.003020694289241867)]
            th = e;
        } catch (Throwable t) {
// t                    0	: [('t', 0.8986164787889105), ('e', 0.3123854986140515), ('th', 0.06697797884997356), ('defSz', 0.014921397662034777), ('sm', 0.006027465909021047), ('userAgent', 0.005965715660672938), ('w', 0.003231840872295735), ('bounds', 0.003020694289241867), ('sppsm', 0.0030012640190111403), ('srcSM', 0.0029885431850743016)]
            th = t;
        }
        if (th == null) {
            if (secure)
                failures.add(EXTERNAL_STYLESHEET_ID);
        } else if (th instanceof SecurityException) {
            if (!secure)
                failures.add(EXTERNAL_STYLESHEET_ID);
        } else if (th instanceof BridgeException) {
            BridgeException be = (BridgeException) th;
// be                   0	: [('be', 0.937953324011707), ('e', 0.6908120731719407), ('th', 0.11506684279039406), ('ex', 0.0629311361342686), ('s', 0.02066481054643279), ('n', 0.007741513109223879), ('tag', 0.006917073020140426), ('prefix', 0.0045022428348498456), ('ns', 0.004176575642095566), ('namespaceURI', 0.004101178574110227)]
            if (!secure || (secure && !ERR_URI_UNSECURE.equals(be.getCode()))) {
                report.setErrorCode(ERROR_WHILE_PROCESSING_SVG_DOCUMENT);
                report.addDescriptionEntry(ENTRY_KEY_ERROR_DESCRIPTION, be.getMessage());
                report.setPassed(false);
                return report;
            }
        }
        Node child = doc.getFirstChild();
// child                0	: [('child', 0.7280373280824165), ('n', 0.603466901311284), ('i', 0.2803640045110011), ('curr', 0.1942990970904878), ('parent', 0.13162233115532285), ('c', 0.11180759152573262), ('iter', 0.06932956541429033), ('e', 0.065964405978691), ('imageFile', 0.06252114054012398), ('ch', 0.062461703140412696)]
        Node next = null;
// next                 7	: [('m', 0.5188957333587108), ('root', 0.5015181230812712), ('child', 0.10343594782108992), ('n', 0.09681609738755456), ('parent', 0.06228105574180834), ('nextSibling', 0.03456485368050249), ('node', 0.033838381538522276), ('next', 0.026921069690868934), ('element', 0.020658314845033967), ('n2', 0.01729406153899635)]
        while (child != null) {
            next = child.getNextSibling();
            if (child.getNodeType() == Node.PROCESSING_INSTRUCTION_NODE) {
                doc.removeChild(child);
            }
            child = next;
        }
        Element root = doc.getDocumentElement();
// root                 1	: [('e', 0.23519857182694068), ('root', 0.05308459129503243), ('child', 0.05225829653433133), ('target', 0.04010881706357788), ('targetElement', 0.036387211825969074), ('imp', 0.035830682887422395), ('refElement', 0.03498954784951873), ('localRefElement', 0.03493404163282245), ('celt', 0.03476571108842403), ('svgPath', 0.034709946029683525)]
        String idList = root.getAttributeNS(testNS, "targetids");
// idList               No	: [('lang', 0.25559968925806587), ('base', 0.1822191316860819), ('ans', 0.18218982773878664), ('securityPolicyProperty', 0.18178000097495206), ('lname', 0.12727417294367507), ('function', 0.12689435161328624), ('s', 0.12384107130321864), ('args', 0.08533235660404584), ('valueStr', 0.08525940412562635), ('mediaText', 0.056840813639882984)]
        if (idList == null || "".equals(idList)) {
            report.setErrorCode(ERROR_NO_ID_LIST);
            report.setPassed(false);
            return report;
        }
        StringTokenizer st = new StringTokenizer(idList, ",");
// st                   0	: [('st', 0.5793377813750596), ('tokens', 0.23319084807431892), ('numGlyphs', 0.1496174092264187), ('platforms', 0.08967891557276592), ('raf', 0.08008626078917506), ('i', 0.06347076725893502), ('ret', 0.059885325956860795), ('keys', 0.05978675486714713), ('chunk', 0.043502033903872465), ('len', 0.030164576990283232)]
        String[] ids = new String[st.countTokens()];
// ids                  0	: [('ids', 0.5001324109259278), ('sb', 0.17533115572222144), ('len', 0.12154715893447576), ('variationURLs', 0.06286610205476753), ('ret', 0.061095713280792434), ('argsArray', 0.06028981579163805), ('modifiers', 0.05756985246898012), ('urls', 0.05755790481526125), ('h', 0.05569919474066642), ('dirNfile', 0.04898205134804987)]
        for (int i = 0; i < ids.length; i++) {
// i                    0	: [('i', 0.8533983429790657), ('y', 0.058031075971716646), ('j', 0.05504940801970687), ('e', 0.024897200112435236), ('k', 0.013248250451386376), ('m', 0.012460260244875123), ('x', 0.009934250142668025), ('sp', 0.008810619556730098), ('chunk', 0.006883601875727965), ('n', 0.006359896737963132)]
            ids[i] = st.nextToken().trim();
        }
        for (String id : ids) {
// id                   0	: [('id', 0.5004367122440521), ('frag', 0.4375315719860752), ('anExpectedDest', 0.15396344917197066), ('anEventNamesSVG11', 0.15396340603055794), ('anEventNamesSVG12', 0.15396340603055794), ('booleanParamName', 0.1519822143132218), ('XVG_FILE_EXTENSION', 0.15198219274251543), ('vh', 0.07293533912644454), ('elementId', 0.03126142456307292), ('c', 0.023262832583269326)]
            userAgent = buildUserAgent();
            builder = new GVTBuilder();
            ctx = new BridgeContext(userAgent);
            ctx.setDynamic(true);
            Document cloneDoc = (Document) doc.cloneNode(true);
// cloneDoc             No	: [('doc', 0.6257873865955753), ('childElt', 0.1804666901859376), ('evt', 0.1257231038316088), ('child', 0.10811987425951541), ('domFactory', 0.08462700116796316), ('ref', 0.07207567282425668), ('e', 0.06341021796281315), ('target', 0.06281259765064502), ('document', 0.03999037882967016), ('childElement', 0.03643683061864996)]
            Element insertionPoint = cloneDoc.getElementById(INSERTION_POINT_ID);
// insertionPoint       No	: [('p', 0.5070306804445115), ('report', 0.35719922039986857), ('e', 0.1787813682262353), ('s', 0.03468689814477672), ('targetElement', 0.019838709463222113), ('n', 0.018419058213368873), ('root', 0.017018186761532376), ('i', 0.01677036477198079), ('g', 0.016750248774692892), ('e2', 0.013398150432493244)]
            if (insertionPoint == null) {
                report.setErrorCode(ERROR_NO_INSERTION_POINT_IN_DOCUMENT);
                report.addDescriptionEntry(ENTRY_KEY_INSERTION_POINT_ID, INSERTION_POINT_ID);
                report.setPassed(false);
                return report;
            }
            Element target = cloneDoc.getElementById(id);
// target               No	: [('o', 0.42150716902243746), ('celt', 0.38369142777424997), ('fc', 0.1918393192629683), ('testResult', 0.1918392329815722), ('doc', 0.10292310488932278), ('id', 0.08443400122565464), ('e', 0.07085513058837023), ('e2', 0.0392640034031523), ('n', 0.03822360744081803), ('result', 0.027390718241090163)]
            if (target == null) {
                report.setErrorCode(ERROR_TARGET_ID_NOT_FOUND);
                report.addDescriptionEntry(ENTRY_KEY_TARGET_ID, id);
                report.setPassed(false);
                return report;
            }
            insertionPoint.appendChild(target);
            th = null;
            try {
                GraphicsNode gn = builder.build(ctx, cloneDoc);
// gn                   0	: [('gn', 0.8989074590568703), ('i', 0.024464964239255593), ('g', 0.018522682056596865), ('t', 0.01306324020753395), ('node', 0.011459688744931928), ('result', 0.009435458086504114), ('sb', 0.009166792421446876), ('n', 0.008021546473197654), ('report', 0.00751489129550803), ('p', 0.004883110491509408)]
                gn.getBounds();
                th = userAgent.getDisplayError();
            } catch (BridgeException e) {
// e                    0	: [('e', 0.7344081115500636), ('ex', 0.4558525811352574), ('t', 0.15619192836781676), ('be', 0.07768375333334435), ('defSz', 0.014921397662034777), ('sm', 0.006027465909021047), ('userAgent', 0.005965715660672938), ('bounds', 0.003020694289241867), ('sppsm', 0.0030012640190111403), ('srcSM', 0.0029885431850743016)]
                th = e;
            } catch (SecurityException e) {
// e                    0	: [('e', 0.7344081115500636), ('se', 0.317751876997868), ('secEx', 0.17249434859394266), ('t', 0.15619192836781676), ('ex', 0.14013251101394192), ('se2', 0.03613228045895812), ('defSz', 0.014921397662034777), ('sm', 0.006027465909021047), ('userAgent', 0.005965715660672938), ('bounds', 0.003020694289241867)]
                th = e;
            } catch (Throwable t) {
// t                    0	: [('t', 0.8986164787889105), ('e', 0.3123854986140515), ('th', 0.06697797884997356), ('defSz', 0.014921397662034777), ('sm', 0.006027465909021047), ('userAgent', 0.005965715660672938), ('w', 0.003231840872295735), ('bounds', 0.003020694289241867), ('sppsm', 0.0030012640190111403), ('srcSM', 0.0029885431850743016)]
                th = t;
            }
            if (th == null) {
                if (secure)
                    failures.add(id);
            } else if (th instanceof SecurityException) {
                if (!secure)
                    failures.add(id);
            } else if (th instanceof BridgeException) {
                BridgeException be = (BridgeException) th;
// be                   0	: [('be', 0.937953324011707), ('e', 0.6908120731719407), ('th', 0.11506684279039406), ('ex', 0.0629311361342686), ('s', 0.02066481054643279), ('n', 0.007741513109223879), ('tag', 0.006917073020140426), ('prefix', 0.0045022428348498456), ('ns', 0.004176575642095566), ('namespaceURI', 0.004101178574110227)]
                if (!secure || (secure && !ERR_URI_UNSECURE.equals(be.getCode()))) {
                    report.setErrorCode(ERROR_WHILE_PROCESSING_SVG_DOCUMENT);
                    report.addDescriptionEntry(ENTRY_KEY_ERROR_DESCRIPTION, be.getMessage());
                    report.setPassed(false);
                    return report;
                }
            } else {
                report.setErrorCode(ERROR_WHILE_PROCESSING_SVG_DOCUMENT);
                report.addDescriptionEntry(ENTRY_KEY_ERROR_DESCRIPTION, th.getMessage());
                report.setPassed(false);
                return report;
            }
        }
        if (failures.size() == 0) {
            return reportSuccess();
        }
        if (secure) {
            report.setErrorCode(ERROR_UNTHROWN_SECURITY_EXCEPTIONS);
            for (Object failure : failures) {
// failure              No	: [('o', 0.11700936545977086), ('component', 0.05885395333131308), ('aList', 0.04666198678407422), ('source', 0.04354325408666176), ('textRun1', 0.037746633957863034), ('aChildren', 0.036704544713577876), ('entry', 0.022150905755760574), ('layout', 0.02047602827983046), ('area', 0.02047460464858719), ('defRec1', 0.020473979113646967)]
                report.addDescriptionEntry(ENTRY_KEY_EXPECTED_EXCEPTION_ON, failure);
            }
        } else {
            report.setErrorCode(ERROR_THROWN_SECURITY_EXCEPTIONS);
            for (Object failure : failures) {
// failure              No	: [('o', 0.11700936545977086), ('component', 0.05885395333131308), ('aList', 0.04666198678407422), ('source', 0.04354325408666176), ('textRun1', 0.037746633957863034), ('aChildren', 0.036704544713577876), ('entry', 0.022150905755760574), ('layout', 0.02047602827983046), ('area', 0.02047460464858719), ('defRec1', 0.020473979113646967)]
                report.addDescriptionEntry(ENTRY_KEY_UNEXPECTED_EXCEPTION_ON, failure);
            }
        }
        report.setPassed(false);
        return report;
    }

    protected interface MyUserAgent extends UserAgent {

        Exception getDisplayError();
    }

    protected MyUserAgent buildUserAgent() {
        if (secure) {
            return new SecureUserAgent();
        } else {
            return new RelaxedUserAgent();
        }
    }

    static class MyUserAgentAdapter extends UserAgentAdapter implements MyUserAgent {

        Exception ex = null;

        public void displayError(Exception ex) {
// ex                   0	: [('ex', 0.6104962658809582), ('e', 0.3449209445557848), ('message', 0.2549645083942687), ('value', 0.20083591086416433), ('ie', 0.10011477928109008), ('de', 0.06409750132014681), ('be', 0.014790556574498768), ('se', 0.004941881158247273), ('i', 0.004020395637145679), ('n', 0.0032670146314410045)]
            this.ex = ex;
            super.displayError(ex);
        }

        public Exception getDisplayError() {
            return ex;
        }
    }

    static class SecureUserAgent extends MyUserAgentAdapter {

        public ExternalResourceSecurity getExternalResourceSecurity(ParsedURL resourcePURL, ParsedURL docPURL) {
// resourcePURL         1	: [('resourceURL', 0.7468256046649837), ('resourcePURL', 0.15650269122742796), ('purl', 0.03062242438246941), ('externalResourceURL', 0.004884921400774184), ('url', 0.004588477072359147), ('uri', 0.0038005948917672885), ('docURL', 0.002992846748829388), ('scriptURL', 0.002702934077193609), ('base', 0.002441722963091243), ('u', 0.0010758664078104596)]
// docPURL              0	: [('docPURL', 0.8774023592691188), ('docURL', 0.020599190997859645), ('scriptURL', 0.019701586383552287), ('uri', 0.0135754285082449), ('purl', 0.012943981132994272), ('origURL', 0.004222488778733105), ('scriptPURL', 0.003519029716188816), ('resourceURL', 0.0025174731195286397), ('key', 0.0014210337037857319), ('base', 0.0012900459603700962)]
            return new NoLoadExternalResourceSecurity();
        }
    }

    static class RelaxedUserAgent extends MyUserAgentAdapter {

        public ExternalResourceSecurity getExternalResourceSecurity(ParsedURL resourcePURL, ParsedURL docPURL) {
// resourcePURL         1	: [('resourceURL', 0.9111223580140405), ('resourcePURL', 0.15650266965815093), ('purl', 0.030622424431065765), ('externalResourceURL', 0.004884921403275467), ('url', 0.004588477117382239), ('uri', 0.0038005950045036816), ('docURL', 0.0029928467647304005), ('scriptURL', 0.0027029340884493817), ('base', 0.0024417230048984), ('u', 0.0010758664221035047)]
// docPURL              0	: [('docPURL', 0.8774023377032364), ('docURL', 0.02059919101376066), ('scriptURL', 0.01970158639480806), ('uri', 0.013575428620981293), ('purl', 0.012943981181590625), ('y', 0.005718558484034258), ('origURL', 0.004222488780877061), ('e', 0.0037693938290402186), ('ctx', 0.003529567431953655), ('scriptPURL', 0.0035190297190474252)]
            return new RelaxedExternalResourceSecurity(resourcePURL, docPURL);
        }
    }
}
