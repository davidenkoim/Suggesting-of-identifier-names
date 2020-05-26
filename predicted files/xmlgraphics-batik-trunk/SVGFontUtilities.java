// Type of training: selfTestingIdentifier
// Path to file: C:\Users\Igor\IdeaProjects\SLP-Core-0.3\java-projects\xmlgraphics-batik-trunk\batik-bridge\src\main\java\org\apache\batik\bridge\SVGFontUtilities.java
// Number of identifiers: 48	Accuracy: 39.58%	MRR: 47.81%
// True 	Rank of true in predictions : [(predicted token, probability of a token), ...]

package org.apache.batik.bridge;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import org.apache.batik.gvt.font.GVTFontFamily;
import org.apache.batik.gvt.font.GVTFontFace;
import org.apache.batik.gvt.font.UnresolvedFontFamily;
import org.apache.batik.util.SVGConstants;
import org.apache.batik.anim.dom.SVGOMDocument;
import org.apache.batik.css.engine.CSSEngine;
import org.apache.batik.css.engine.FontFaceRule;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public abstract class SVGFontUtilities implements SVGConstants {

    public static List getFontFaces(Document doc, BridgeContext ctx) {
// doc                  1	: [('localFontElement', 0.9375046124849524), ('doc', 0.5293657034584434), ('fontKeyName', 0.3754492962854469), ('document', 0.10427597650679231), ('imgDocument', 0.10220530400025846), ('bc', 0.08235340019926003), ('domFactory', 0.05875197206645421), ('i', 0.04030679766565574), ('e', 0.02849980814962719), ('ctx', 0.024156619920208917)]
// ctx                  0	: [('ctx', 0.9375785692063139), ('fontFamilyMap', 0.12501025741464092), ('fontName', 0.09921193201585111), ('bc', 0.06444909486134628), ('i', 0.0020102421389820275), ('e', 0.0019230089748527184), ('n', 0.0016335573703355258), ('c', 0.0011403491817849737), ('x', 0.0009651250079529313), ('t', 0.00095822068894867)]
        Map fontFamilyMap = ctx.getFontFamilyMap();
// fontFamilyMap        4	: [('refs', 0.7588888095755932), ('aci', 0.3294646888461812), ('o', 0.2987805165171343), ('clIter', 0.1646899081438244), ('fontFamilyMap', 0.08235131047797262), ('hints', 0.04232585806988665), ('attrMap', 0.02805862971436739), ('map', 0.010051770682648429), ('m', 0.009716350764900027), ('filterMap', 0.00499905045525263)]
        List ret = (List) fontFamilyMap.get(doc);
// ret                  0	: [('ret', 0.6518538607250035), ('doc', 0.25257891510949154), ('fontFaces', 0.2501252666080225), ('curr', 0.2187221183043022), ('sb', 0.16516682077443942), ('result', 0.13720552409890568), ('list', 0.1234422925613184), ('sm', 0.10021710722048759), ('glyphCodes', 0.07559080609914734), ('hkernElementArray', 0.07558871370596724)]
        if (ret != null)
            return ret;
        ret = new LinkedList();
        NodeList fontFaceElements = doc.getElementsByTagNameNS(SVG_NAMESPACE_URI, SVG_FONT_FACE_TAG);
// fontFaceElements     3	: [('nl', 0.3598206752500151), ('n', 0.26870614460284964), ('len', 0.12154715880190338), ('fontFaceElements', 0.11957359483778308), ('list', 0.10272461050776313), ('lst', 0.1027169314635125), ('childList', 0.06378826711326467), ('h', 0.05569919458129368), ('node', 0.053235365676450076), ('evt', 0.04946648372188083)]
        SVGFontFaceElementBridge fontFaceBridge;
// fontFaceBridge       0	: [('fontFaceBridge', 0.7500072452367174), ('purl', 0.14050574536460103), ('src1', 0.13933252813277006), ('src2', 0.1392392445506805), ('oRgn', 0.13923860823235237), ('curr', 0.08549258390897604), ('b', 0.043271461713106626), ('p', 0.019069695173904826), ('s', 0.015296383186589567), ('r2d', 0.014606804311734197)]
        fontFaceBridge = (SVGFontFaceElementBridge) ctx.getBridge(SVG_NAMESPACE_URI, SVG_FONT_FACE_TAG);
        for (int i = 0; i < fontFaceElements.getLength(); i++) {
// i                    0	: [('i', 0.8533983429790657), ('j', 0.0625749135833625), ('y', 0.06051212205741389), ('item', 0.04410757845640648), ('k', 0.018348770086789732), ('m', 0.012460260244875123), ('x', 0.011595379710774338), ('index', 0.010132675769140453), ('g', 0.008470797119035527), ('n', 0.008138157237153863)]
            Element fontFaceElement = (Element) fontFaceElements.item(i);
// fontFaceElement      No	: [('localFontFaceElement', 0.37531770438140216), ('fontFaceElt', 0.37531664641009066), ('hkernElement', 0.13536832257977624), ('vkernElement', 0.13536830100960587), ('glyphRefElement', 0.08988608872739369), ('element', 0.0734514043030839), ('glyphElement', 0.07325459266795656), ('glyph', 0.06767228437460718), ('entry', 0.06767180983085942), ('glyphElem', 0.06767073132234179)]
            ret.add(fontFaceBridge.createFontFace(ctx, fontFaceElement));
        }
        CSSEngine engine = ((SVGOMDocument) doc).getCSSEngine();
// engine               0	: [('engine', 0.6250502508425381), ('eng', 0.5004937186035741), ('result', 0.25532016526035245), ('e', 0.011014548909597835), ('cssEngine', 0.010271493526605072), ('i', 0.004020395637145679), ('n', 0.0032670146314410045), ('source', 0.0026276759788911954), ('srceng', 0.0025731461365721725), ('ctx', 0.0022369043532091694)]
        List sms = engine.getFontFaces();
// sms                  No	: [('list', 0.12344229222720185), ('layouts', 0.0592261978372531), ('l', 0.05620169781273946), ('ret', 0.03250762741213934), ('dest', 0.03248096668158356), ('rgns', 0.031043122212321583), ('chunkLayouts', 0.030192997777970687), ('lnLocs', 0.029904896995999283), ('paraElems', 0.02961412151290416), ('paraEnds', 0.029614035232222752)]
        for (Object sm : sms) {
// sm                   0	: [('sm', 0.7500746175720571), ('o', 0.11700936545977086), ('component', 0.05885395333131308), ('aList', 0.04666198678407422), ('source', 0.04354325408666176), ('textRun1', 0.037746633957863034), ('aChildren', 0.036704544713577876), ('entry', 0.022150905755760574), ('layout', 0.02047602827983046), ('area', 0.02047460464858719)]
            FontFaceRule ffr = (FontFaceRule) sm;
// ffr                  1	: [('idx', 0.34106473025621464), ('ffr', 0.1428741946110359), ('y', 0.00571840896156732), ('e', 0.003769306432105318), ('ctx', 0.003529479192638659), ('i', 0.0033651487138383774), ('w', 0.0025138029191135646), ('s', 0.00226412022467707), ('x', 0.0019360368255255125), ('r', 0.001525545421608575)]
            ret.add(CSSFontFace.createCSSFontFace(engine, ffr));
        }
        return ret;
    }

    public static GVTFontFamily getFontFamily(Element textElement, BridgeContext ctx, String fontFamilyName, String fontWeight, String fontStyle) {
// textElement          No	: [('paintedElement', 0.755355891579445), ('e', 0.18262372779881414), ('elt', 0.0754644705991132), ('df', 0.06959614486296135), ('filterElement', 0.05466653948515861), ('element', 0.04655325393925812), ('impl', 0.026795479129283088), ('f', 0.025672759811915625), ('imageElement', 0.01835661459416222), ('paintElement', 0.010945660202595719)]
// ctx                  0	: [('ctx', 0.7109774640316358), ('element', 0.300331919843036), ('uri', 0.2619486555854403), ('fontFamilyMap', 0.12501025757864545), ('durl', 0.09541159558809457), ('dl', 0.07155490279306324), ('root', 0.04779976965695789), ('node', 0.024306301058135006), ('stringWriter', 0.0238576728433208), ('view', 0.023854389945990304)]
// fontFamilyName       1	: [('glyphUri', 0.7501669491389138), ('fontFamilyName', 0.16667890265034446), ('ln', 0.0100442439916171), ('i', 0.00804096855592811), ('ns', 0.008040391483719132), ('e', 0.007692035899410875), ('value', 0.006940965194608), ('s', 0.0068040771805594966), ('n', 0.006534229481342102), ('prefix', 0.005642421254794061)]
// fontWeight           No	: [('fontFaceWeight', 0.666679945988473), ('localName', 0.3338343438660377), ('bridge', 0.33383216526078685), ('w', 0.08438090367377792), ('h', 0.0841184749606764), ('ln', 0.04019491037029237), ('ns', 0.03217436433251635), ('value', 0.027776128196886067), ('s', 0.027223780137384097), ('fontStyle', 0.027005256931214822)]
// fontStyle            0	: [('fontStyle', 0.87539176666466), ('val', 0.09421489719180587), ('w', 0.08752411287268749), ('h', 0.08647416510609009), ('localName', 0.08533739487366693), ('bridge', 0.08532868045266341), ('total', 0.08528954014885622), ('fontWeight', 0.08526713843588746), ('used', 0.08526040848699365), ('c', 0.07112813036850084)]
        String fontKeyName = fontFamilyName.toLowerCase() + " " + fontWeight + " " + fontStyle;
// fontKeyName          No	: [('doc', 0.7515011162200789), ('s', 0.15089414070074653), ('msg', 0.0545462379358649), ('i', 0.04030680975009569), ('ns', 0.03923834841604258), ('ln', 0.03399897353368006), ('syntaxName', 0.033937782810326056), ('m', 0.028576377267727236), ('key', 0.02654006590517108), ('name', 0.02572651307761159)]
        Map fontFamilyMap = ctx.getFontFamilyMap();
// fontFamilyMap        1	: [('svgFontFamilies', 0.5625218007555833), ('fontFamilyMap', 0.5104845515242618), ('end', 0.5066613393046175), ('aci', 0.32946469742044854), ('o', 0.29878053491141493), ('substString', 0.26061078353343076), ('svgFontFamily', 0.1875064232194171), ('clIter', 0.16468990842216827), ('c', 0.10622718073513582), ('result', 0.09442326766474414)]
        GVTFontFamily fontFamily = (GVTFontFamily) fontFamilyMap.get(fontKeyName);
// fontFamily           0	: [('fontFamily', 0.3633171847571585), ('family', 0.26499178350787916), ('resolvedFF', 0.21583013983034144), ('t', 0.07151569373352974), ('n', 0.06981182882057521), ('attr', 0.05719903047338636), ('textRuns', 0.04261151609919033), ('msg', 0.04260596325926155), ('ti', 0.042603655231916567), ('s', 0.03468689814477672)]
        if (fontFamily != null) {
            return fontFamily;
        }
        Document doc = textElement.getOwnerDocument();
// doc                  1	: [('fontKeyName', 0.7537438471238094), ('doc', 0.6253485290831579), ('document', 0.15745992478758822), ('otherDocument', 0.15059932847663654), ('domFactory', 0.08215376381100488), ('i', 0.04030678978454155), ('ns', 0.017244905013073443), ('syntaxName', 0.01684208821676284), ('key', 0.013171406442710091), ('namespaceURI', 0.008829894408701643)]
        List fontFaces = (List) fontFamilyMap.get(doc);
// fontFaces            No	: [('srcs', 0.7695415127195733), ('uri', 0.2619486458225496), ('ret', 0.25424975308386644), ('doc', 0.25257847166032826), ('list', 0.12344229242766847), ('durl', 0.0954115949677445), ('ctx', 0.0771493609649163), ('dl', 0.07155490249146446), ('layouts', 0.059226197868341504), ('l', 0.056201698440939606)]
        if (fontFaces == null) {
            fontFaces = getFontFaces(doc, ctx);
            fontFamilyMap.put(doc, fontFaces);
        }
        Iterator iter = fontFaces.iterator();
// iter                 0	: [('iter', 0.40901927590496356), ('frIter', 0.2520041766606505), ('i', 0.185986211333498), ('n', 0.15626889594154855), ('it', 0.13701547952353624), ('j', 0.03857138364299834), ('next', 0.0371771446755424), ('child', 0.035916146696093124), ('st', 0.030084066228957825), ('e', 0.02268256516027084)]
        List svgFontFamilies = new LinkedList();
// svgFontFamilies      No	: [('gvtFontFamily', 0.9375028798099843), ('chunkLayouts', 0.5150964989282951), ('fontFamilyMap', 0.37501981236459914), ('svgFontFamily', 0.3750063368109922), ('n', 0.22302173618085783), ('len', 0.12154716012772607), ('chunkType', 0.08995166771484998), ('list', 0.06172114648114739), ('h', 0.05569919617513983), ('numGlyphs', 0.04893804169219105)]
        while (iter.hasNext()) {
            FontFace fontFace = (FontFace) iter.next();
// fontFace             0	: [('fontFace', 0.20003706923217396), ('src', 0.050360109454823186), ('ff', 0.050019122553137854), ('rect', 0.040987981085317475), ('r', 0.020704957371575756), ('ctx', 0.01905527355010562), ('bounds', 0.018721619355944987), ('resAt', 0.018642969910374545), ('val', 0.018609113409934246), ('sm', 0.01857483393040868)]
            if (!fontFace.hasFamilyName(fontFamilyName)) {
                continue;
            }
            String fontFaceStyle = fontFace.getFontStyle();
// fontFaceStyle        No	: [('ln', 0.25823386391524417), ('cNS', 0.25340113816528304), ('aprens', 0.12670109116522285), ('url', 0.12600543467732403), ('s', 0.02778186666840999), ('nodeName', 0.021360635304599442), ('n', 0.021213709216422506), ('res', 0.015794103423049374), ('aci', 0.012672391913331096), ('token', 0.012327360477651752)]
            if (fontFaceStyle.equals(SVG_ALL_VALUE) || fontFaceStyle.indexOf(fontStyle) != -1) {
                GVTFontFamily ffam = fontFace.getFontFamily(ctx);
// ffam                 0	: [('ffam', 0.566670670764525), ('in', 0.10762099090300357), ('s', 0.07368147724140245), ('ff', 0.07083811396375636), ('gvtFontFamily', 0.06667092960871325), ('v', 0.03445003176566928), ('c', 0.023221957656928922), ('n', 0.02193276861666224), ('units', 0.021667622035639923), ('in2', 0.02150423601800109)]
                if (ffam != null)
                    svgFontFamilies.add(ffam);
            }
        }
        if (svgFontFamilies.size() == 1) {
            fontFamilyMap.put(fontKeyName, svgFontFamilies.get(0));
            return (GVTFontFamily) svgFontFamilies.get(0);
        } else if (svgFontFamilies.size() > 1) {
            String fontWeightNumber = getFontWeightNumberString(fontWeight);
// fontWeightNumber     No	: [('s', 0.1310020273105736), ('prefix', 0.11899451763313006), ('message', 0.11462403128046846), ('val', 0.09421489960188767), ('c', 0.07112813649215133), ('scriptType', 0.07057153760404826), ('psgn', 0.07047227120103891), ('before', 0.056656306661826805), ('src', 0.0561488861141342), ('slope', 0.05611943078026494)]
            List fontFamilyWeights = new ArrayList(svgFontFamilies.size());
// fontFamilyWeights    No	: [('w', 0.2262818838107016), ('n', 0.22302164961539567), ('o', 0.19058609811057264), ('list', 0.11644980224664131), ('e2', 0.11250045361128554), ('h', 0.08550388530214764), ('nComponents', 0.08453528745664915), ('l', 0.068925136012314), ('len', 0.06555470159798193), ('iter', 0.06432187509482165)]
            for (Object svgFontFamily : svgFontFamilies) {
// svgFontFamily        No	: [('svgFontFamilies', 0.5625217988457697), ('fontFamilyMap', 0.18751981140024432), ('o', 0.11700936545977086), ('component', 0.05885395333131308), ('aList', 0.04666198678407422), ('source', 0.04354325408666176), ('textRun1', 0.037746633957863034), ('aChildren', 0.036704544713577876), ('entry', 0.022150905755760574), ('layout', 0.02047602827983046)]
                GVTFontFace fontFace;
// fontFace             1	: [('face', 0.5138967245218135), ('fontFace', 0.15279631230381557), ('i', 0.03981479980767444), ('gff', 0.02778630366187094), ('g', 0.010011131777033642), ('n', 0.007417047564273043), ('t', 0.005806344098482212), ('y', 0.005167018573723961), ('sb', 0.0045496834583701315), ('e', 0.004236977458582161)]
                fontFace = ((GVTFontFamily) svgFontFamily).getFontFace();
                String fontFaceWeight = fontFace.getFontWeight();
// fontFaceWeight       1	: [('fontWeight', 0.5833634380585636), ('fontFaceWeight', 0.08334281642504657), ('s', 0.0766065015828591), ('namespaceURI', 0.03858085547612188), ('ln', 0.03681717371057947), ('text', 0.032082925453902525), ('i', 0.026627147260981025), ('c', 0.023221973341655603), ('prefix', 0.023084114025235122), ('details', 0.022850176664607653)]
                fontFaceWeight = getFontWeightNumberString(fontFaceWeight);
                fontFamilyWeights.add(fontFaceWeight);
            }
            List newFontFamilyWeights = new ArrayList(fontFamilyWeights);
// newFontFamilyWeights No	: [('srcs', 0.3765059448213797), ('o', 0.1905860979179643), ('perNodeRuns', 0.18499524910616538), ('e2', 0.1125004535809113), ('l', 0.07593733952362605), ('children', 0.07504930965943178), ('iter', 0.06432187502978506), ('args', 0.053370884009613255), ('tableModel', 0.04821707522220682), ('spec', 0.047178987105140915)]
            for (int i = 100; i <= 900; i += 100) {
// i                    0	: [('i', 0.9399853562588302), ('unitsPerEm', 0.2657502287562976), ('minDifferenceIndex', 0.1254487898055075), ('fontFace', 0.10630331523463796), ('point_plus1', 0.07695568454063621), ('c', 0.06101440813207707), ('y', 0.06051222205782577), ('value', 0.05328579486819065), ('cn', 0.05321076017868823), ('ascent', 0.053156655830259386)]
                String weightString = String.valueOf(i);
// weightString         No	: [('val', 0.43727648921522927), ('text', 0.25002403118660477), ('s', 0.1508929451623394), ('c', 0.07112813763775787), ('scriptType', 0.07057153767908798), ('psgn', 0.07047227122033485), ('node', 0.054918346149747964), ('msg', 0.05454609493485358), ('str', 0.047074680771668154), ('publicId', 0.04697705363853639)]
                boolean matched = false;
// matched              No	: [('important', 0.7522329212022678), ('substString', 0.26059952249673424), ('foundMatchingGlyph', 0.13974493375794766), ('result', 0.09441197523276096), ('c', 0.08970778571458697), ('fontFamilyMap', 0.08755728744306555), ('li', 0.05293855069445668), ('e', 0.04748974649359739), ('isCSS', 0.047397490110844834), ('namespaceDeclarations', 0.04707242160683249)]
                int minDifference = 1000;
// minDifference        0	: [('minDifference', 0.5000351182575503), ('exp', 0.1148377212653919), ('beginWS', 0.08133397144892138), ('len', 0.07499476972104833), ('i', 0.07396974889605983), ('length', 0.07144318341514257), ('dx', 0.07004836434777122), ('arabInitGlyphIndex', 0.06937510335819336), ('numAltGlyphDefChildren', 0.06937471509191102), ('numChars', 0.057665472652438744)]
                int minDifferenceIndex = 0;
// minDifferenceIndex   0	: [('minDifferenceIndex', 0.750006468661046), ('i', 0.580613563807279), ('index', 0.1388051319527208), ('y', 0.09297772309932449), ('year', 0.09231240615063209), ('name', 0.046670974612006894), ('idx', 0.04655859375321626), ('j', 0.04648827475273981), ('array', 0.04616756663816537), ('position', 0.023110785389951278)]
                for (int j = 0; j < fontFamilyWeights.size(); j++) {
// j                    0	: [('j', 0.7461187987930743), ('i', 0.6218082030463725), ('m', 0.08025687053854365), ('cn', 0.05321075984430088), ('y', 0.04406975538590274), ('key', 0.036421789787649705), ('ns', 0.036010332204255785), ('namespaceURI', 0.035319578097209564), ('name', 0.034290361907269515), ('s', 0.0309695998868015)]
                    String fontFamilyWeight = (String) fontFamilyWeights.get(j);
// fontFamilyWeight     No	: [('s', 0.184010115739055), ('mi', 0.16716925971456956), ('childElement', 0.1667962581001533), ('key', 0.13885240353396802), ('args', 0.08533235481950141), ('valueStr', 0.0852594040898698), ('type', 0.07191411328964471), ('pn', 0.07134717780999947), ('aname', 0.06886254304809274), ('mediaText', 0.05684081359298181)]
                    if (fontFamilyWeight.indexOf(weightString) > -1) {
                        matched = true;
                        break;
                    }
                    StringTokenizer st = new StringTokenizer(fontFamilyWeight, " ,");
// st                   0	: [('st', 0.5793377813750596), ('tokens', 0.23319084807431892), ('scanner', 0.13252181484065953), ('s', 0.08025267070933212), ('i', 0.0702454627118552), ('n', 0.04978740971567348), ('aStr', 0.04640894506101497), ('rStr', 0.04640833030737444), ('gStr', 0.04640696059413127), ('attr', 0.04421657783078126)]
                    while (st.hasMoreTokens()) {
                        int weightNum = Integer.parseInt(st.nextToken());
// weightNum            No	: [('p', 0.29128871027772046), ('a', 0.14781749628061094), ('value', 0.09839716365171645), ('rx', 0.09312116155654543), ('ry', 0.09219308145797792), ('n', 0.07876610619080707), ('type', 0.07605643313659016), ('w', 0.07588886904235134), ('count', 0.0744925087894356), ('nt', 0.07341927694308281)]
                        int difference = Math.abs(weightNum - i);
// difference           0	: [('difference', 0.6250132316789416), ('c', 0.08500141696736335), ('n', 0.08362853314800509), ('len', 0.06808345767348775), ('i', 0.040237770206228464), ('w', 0.040160023019061165), ('b', 0.04001847557848765), ('g', 0.039682967654347506), ('ty0', 0.039252660669252086), ('tx1', 0.0392524449657619)]
                        if (difference < minDifference) {
                            minDifference = difference;
                            minDifferenceIndex = j;
                        }
                    }
                }
                if (!matched) {
                    String newFontFamilyWeight = newFontFamilyWeights.get(minDifferenceIndex) + ", " + weightString;
// newFontFamilyWeight  No	: [('s', 0.15089294351003602), ('msg', 0.05454609482550997), ('ns', 0.03923764281813223), ('ln', 0.0339984480872428), ('m', 0.028576311324063235), ('name', 0.025725797528695728), ('uri', 0.023622866555291652), ('id', 0.017250641452492343), ('prefix', 0.01576681304403792), ('str', 0.012051477370579647)]
                    newFontFamilyWeights.set(minDifferenceIndex, newFontFamilyWeight);
                }
            }
            for (int i = 0; i < svgFontFamilies.size(); i++) {
// i                    0	: [('i', 0.9399853138105616), ('unitsPerEm', 0.2657502287562976), ('minDifferenceIndex', 0.1254487898055075), ('j', 0.1155625273652391), ('fontFace', 0.10630331523463796), ('k', 0.09964944348479805), ('point_plus1', 0.07695568454063621), ('c', 0.06101440813207707), ('value', 0.05328579486819065), ('cn', 0.05321075990891703)]
                String fontFaceWeight = (String) newFontFamilyWeights.get(i);
// fontFaceWeight       No	: [('key', 0.2122833445165699), ('type', 0.10738562789258377), ('pn', 0.10710216030248328), ('aname', 0.10585984294975914), ('c', 0.08500141486364746), ('v', 0.0543164619725072), ('publicId', 0.052975119833724854), ('newAttrName', 0.05265630633193335), ('rgbStr', 0.05265613377057053), ('n', 0.042993307481028725)]
                if (fontFaceWeight.indexOf(fontWeightNumber) > -1) {
                    fontFamilyMap.put(fontKeyName, svgFontFamilies.get(i));
                    return (GVTFontFamily) svgFontFamilies.get(i);
                }
            }
            fontFamilyMap.put(fontKeyName, svgFontFamilies.get(0));
            return (GVTFontFamily) svgFontFamilies.get(0);
        } else {
            GVTFontFamily gvtFontFamily = new UnresolvedFontFamily(fontFamilyName);
// gvtFontFamily        No	: [('svgFontFamilies', 0.9375059027526147), ('ffam', 0.2666751925507598), ('ff', 0.14167622792751272), ('report', 0.08361554619585536), ('fontFamily', 0.04168917013692388), ('ret', 0.04067631088752993), ('result', 0.034502681724386794), ('n', 0.028094360859877487), ('v', 0.025778615608695602), ('wr', 0.021670753315670345)]
            fontFamilyMap.put(fontKeyName, gvtFontFamily);
            return gvtFontFamily;
        }
    }

    protected static String getFontWeightNumberString(String fontWeight) {
// fontWeight           No	: [('value', 0.3799499037837242), ('writer', 0.3753504574856975), ('prefix', 0.11653987203437124), ('chunkType', 0.09026938202792596), ('n', 0.051827710600169705), ('name', 0.04820380057330839), ('s', 0.042201951075561815), ('ns', 0.03744669913036893), ('ln', 0.03171427558102909), ('type', 0.03081694294918519)]
        if (fontWeight.equals(SVG_NORMAL_VALUE)) {
            return SVG_400_VALUE;
        } else if (fontWeight.equals(SVG_BOLD_VALUE)) {
            return SVG_700_VALUE;
        } else if (fontWeight.equals(SVG_ALL_VALUE)) {
            return "100, 200, 300, 400, 500, 600, 700, 800, 900";
        }
        return fontWeight;
    }
}
