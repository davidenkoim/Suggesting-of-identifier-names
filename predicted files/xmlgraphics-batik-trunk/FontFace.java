// Type of training: selfTestingIdentifier
// Path to file: C:\Users\Igor\IdeaProjects\SLP-Core-0.3\java-projects\xmlgraphics-batik-trunk\batik-bridge\src\main\java\org\apache\batik\bridge\FontFace.java
// Number of identifiers: 51	Accuracy: 70.59%	MRR: 78.27%
// True 	Rank of true in predictions : [(predicted token, probability of a token), ...]

package org.apache.batik.bridge;

import java.util.LinkedList;
import java.util.List;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.svg.SVGDocument;
import org.apache.batik.dom.AbstractNode;
import org.apache.batik.gvt.font.GVTFontFace;
import org.apache.batik.gvt.font.GVTFontFamily;
import org.apache.batik.util.ParsedURL;
import org.apache.batik.constants.XMLConstants;

public abstract class FontFace extends GVTFontFace implements ErrorConstants {

    List srcs;

    public FontFace(List srcs, String familyName, float unitsPerEm, String fontWeight, String fontStyle, String fontVariant, String fontStretch, float slope, String panose1, float ascent, float descent, float strikethroughPosition, float strikethroughThickness, float underlinePosition, float underlineThickness, float overlinePosition, float overlineThickness) {
// srcs                 0	: [('srcs', 0.22552031499036737), ('vec', 0.08336014180822494), ('textRuns', 0.06910127021895576), ('defSet', 0.0518403736835417), ('list', 0.04870365258370041), ('sources', 0.04521745289108972), ('urls', 0.025054237207653297), ('l', 0.012840265510226781), ('rules', 0.012539060522945426), ('areas', 0.012533883682060806)]
// familyName           0	: [('familyName', 0.8752647358876563), ('srcs', 0.5069912280054951), ('prefix', 0.11950696852197748), ('target', 0.03741807979263099), ('srcCM', 0.02318073305233639), ('src', 0.01947610726692067), ('source', 0.018036128791916684), ('generatorContext', 0.016279638603323317), ('elt', 0.013803830302612428), ('owner', 0.013032591424424214)]
// unitsPerEm           0	: [('unitsPerEm', 0.8753877165767452), ('resolvedFF', 0.26923883286168243), ('src', 0.019687236405353414), ('x', 0.010457726981488297), ('y', 0.00971935379621695), ('y1', 0.0037699505515950012), ('y2', 0.00371286023298101), ('x2', 0.0024720127752619735), ('interpolation', 0.002464304748570054), ('value', 0.002072110830785592)]
// fontWeight           0	: [('fontWeight', 0.8754288409840053), ('src', 0.0115918752690702), ('ln', 0.005021990820734289), ('ns', 0.0040200196014584435), ('value', 0.0034703893155818323), ('s', 0.003401740118788032), ('prefix', 0.002820868259720942), ('oldv', 0.0024772091132937266), ('uri', 0.002442399123370017), ('name', 0.0023562002371159906)]
// fontStyle            0	: [('fontStyle', 0.888530548916956), ('imp', 0.03117924804051402), ('ln', 0.005024232677719098), ('ns', 0.004021619481342089), ('value', 0.0034719229274805525), ('s', 0.0034026743752378634), ('src', 0.003090312588132635), ('prefix', 0.0028213944650376283), ('oldv', 0.0024783746070886925), ('uri', 0.0024433138095309764)]
// fontVariant          0	: [('fontVariant', 0.65651790901763), ('fontFamilyStr', 0.3411234602516782), ('fontFamily', 0.2189480413005465), ('imp', 0.03403494497515832), ('ln', 0.005024232677719098), ('ns', 0.004021619481342089), ('value', 0.0034719229274805525), ('src', 0.003403595796152685), ('s', 0.0034026743752378634), ('prefix', 0.0028213944650376283)]
// fontStretch          0	: [('fontStretch', 0.876400385536767), ('imp', 0.044488161349427326), ('ln', 0.005024232677719098), ('src', 0.004280788778608826), ('ns', 0.004021619481342089), ('value', 0.0034719229274805525), ('s', 0.0034026743752378634), ('prefix', 0.0028213944650376283), ('oldv', 0.0024783746070886925), ('uri', 0.0024433138095309764)]
// slope                0	: [('slope', 0.9147914998976394), ('x', 0.010457726981488297), ('y', 0.00971935379621695), ('src', 0.005795940293760341), ('y1', 0.0037699505515950012), ('y2', 0.00371286023298101), ('x2', 0.0024720127752619735), ('interpolation', 0.002464304748570054), ('value', 0.002072110830785592), ('opacity', 0.001873485274891813)]
// panose1              0	: [('panose1', 0.9023552250777078), ('yintcpt', 0.007817387069583327), ('ln', 0.005021990820734289), ('src', 0.004020372111942159), ('ns', 0.0040200196014584435), ('intercept', 0.003913060229557314), ('value', 0.0034703893155818323), ('s', 0.003401740118788032), ('prefix', 0.002820868259720942), ('oldv', 0.0024772091132937266)]
// ascent               0	: [('ascent', 0.9147873107315075), ('x', 0.010457726981488297), ('y', 0.00971935379621695), ('src', 0.005795940293760341), ('y1', 0.0037699505515950012), ('y2', 0.00371286023298101), ('x2', 0.0024720127752619735), ('interpolation', 0.002464304748570054), ('value', 0.002072110830785592), ('opacity', 0.001873485274891813)]
// descent              0	: [('descent', 0.9062653611231839), ('x', 0.010463793723059858), ('y', 0.009725424420524308), ('src', 0.004578407826227873), ('glyphMetrics', 0.004466646797396052), ('y1', 0.0037724144113927684), ('y2', 0.0037152883351742527), ('x2', 0.002473518545719826), ('interpolation', 0.0024659526786343913), ('value', 0.002073427366982369)]
// strikethroughPosition 0	: [('strikethroughPosition', 0.5902863806080161), ('strikethroughPos', 0.2861149196721768), ('height', 0.17534517169814934), ('lineHeight', 0.1753381756451886), ('fontHeight', 0.03611779191964631), ('x', 0.010463793723059858), ('y', 0.009725424420524308), ('src', 0.004280788778608826), ('y1', 0.0037724144113927684), ('y2', 0.0037152883351742527)]
// strikethroughThickness 0	: [('strikethroughThickness', 0.9097338914314632), ('x', 0.010463793723059858), ('y', 0.009725424420524308), ('src', 0.007058566556386603), ('y1', 0.0037724144113927684), ('y2', 0.0037152883351742527), ('x2', 0.002473518545719826), ('interpolation', 0.0024659526786343913), ('value', 0.002073427366982369), ('opacity', 0.0018747148945985478)]
// underlinePosition    0	: [('underlinePosition', 0.8653360053527897), ('underlineOffset', 0.21885748791521736), ('underlinePos', 0.04613476094201807), ('x', 0.010463793723059858), ('y', 0.009725424420524308), ('src', 0.005322455445275492), ('y1', 0.0037724144113927684), ('y2', 0.0037152883351742527), ('x2', 0.002473518545719826), ('interpolation', 0.0024659526786343913)]
// underlineThickness   0	: [('underlineThickness', 0.9097339561419743), ('x', 0.010463793723059858), ('y', 0.009725424420524308), ('src', 0.007058566556386603), ('y1', 0.0037724144113927684), ('y2', 0.0037152883351742527), ('x2', 0.002473518545719826), ('interpolation', 0.0024659526786343913), ('value', 0.002073427366982369), ('opacity', 0.0018747148945985478)]
// overlinePosition     0	: [('overlinePosition', 0.8653331978158312), ('overlineOffset', 0.21885744477487665), ('overlinePos', 0.046133832286422045), ('x', 0.010463793723059858), ('y', 0.009725424420524308), ('src', 0.005322455445275492), ('y1', 0.0037724144113927684), ('y2', 0.0037152883351742527), ('x2', 0.002473518545719826), ('interpolation', 0.0024659526786343913)]
// overlineThickness    0	: [('overlineThickness', 0.8958375085872949), ('src', 0.010530788778608826), ('x', 0.010463793723059858), ('y', 0.009725424420524308), ('y1', 0.0037724144113927684), ('y2', 0.0037152883351742527), ('x2', 0.002473518545719826), ('interpolation', 0.0024659526786343913), ('value', 0.002073427366982369), ('opacity', 0.0018747148945985478)]
        super(familyName, unitsPerEm, fontWeight, fontStyle, fontVariant, fontStretch, slope, panose1, ascent, descent, strikethroughPosition, strikethroughThickness, underlinePosition, underlineThickness, overlinePosition, overlineThickness);
        this.srcs = srcs;
    }

    protected FontFace(String familyName) {
// familyName           0	: [('familyName', 0.501638761627338), ('prefix', 0.11950696852197748), ('name', 0.04820237056753768), ('s', 0.04219955999891182), ('ns', 0.037445288970862675), ('target', 0.03741807979263099), ('namespaceURI', 0.028858645484045726), ('uri', 0.024806476927407492), ('srcCM', 0.02318073305233639), ('key', 0.01977845212600428)]
        super(familyName);
    }

    public static CSSFontFace createFontFace(String familyName, FontFace src) {
// familyName           0	: [('familyName', 0.5002054089364474), ('prefix', 0.11653713131481933), ('name', 0.04820237056753768), ('s', 0.04219955999891182), ('ns', 0.037445288970862675), ('namespaceURI', 0.028858645484045726), ('uri', 0.024806476927407492), ('key', 0.01977845212600428), ('message', 0.016333802786506705), ('text', 0.014286435583009552)]
// src                  No	: [('fontFace', 0.9375024032647603), ('unitsPerEm', 0.6154350401035226), ('fontWeight', 0.3335379103762539), ('slope', 0.3334910446318998), ('ascent', 0.3334575088224642), ('underlineThickness', 0.30010136718623004), ('strikethroughThickness', 0.30010084943781434), ('fontStyle', 0.2729206182015481), ('descent', 0.26679706882309107), ('fontStretch', 0.25009946877537254)]
        return new CSSFontFace(new LinkedList(src.srcs), familyName, src.unitsPerEm, src.fontWeight, src.fontStyle, src.fontVariant, src.fontStretch, src.slope, src.panose1, src.ascent, src.descent, src.strikethroughPosition, src.strikethroughThickness, src.underlinePosition, src.underlineThickness, src.overlinePosition, src.overlineThickness);
    }

    public GVTFontFamily getFontFamily(BridgeContext ctx) {
// ctx                  0	: [('ctx', 0.9652903081081792), ('userAgent', 0.753476842083872), ('element', 0.11177495914167987), ('ex', 0.02372812019274059), ('rgn', 0.017502916016053804), ('report', 0.010483689848060952), ('at', 0.008795252612800626), ('i', 0.008041002180558445), ('cr', 0.007900516996710218), ('e', 0.00769204833211937)]
        final FontFamilyResolver fontFamilyResolver = ctx.getFontFamilyResolver();
// fontFamilyResolver   0	: [('fontFamilyResolver', 0.8750039178164623), ('i', 0.0080409349318548), ('e', 0.007692023466908339), ('n', 0.006534218838388104), ('ctx', 0.004473590183161642), ('x', 0.0038604906838505173), ('t', 0.003832876786821285), ('raf', 0.0037429257187126257), ('y', 0.0034154802950523916), ('src', 0.003239706714886585)]
        GVTFontFamily family = fontFamilyResolver.resolve(familyName, this);
// family               3	: [('fontFamily', 0.5791452363493693), ('choice', 0.286708987479778), ('resolvedFF', 0.21583013983784566), ('family', 0.12501481951497342), ('idx', 0.07649746743467237), ('t', 0.07285640909632479), ('cursor', 0.07180478252914559), ('n', 0.07100242437473966), ('attr', 0.05827170439716085), ('textRuns', 0.043415587881103834)]
        if (family != null) {
            return family;
        }
        for (Object o : srcs) {
// o                    0	: [('o', 0.3290563304351122), ('args', 0.2533708842043657), ('aList', 0.10977635371861265), ('ln', 0.10560521527429101), ('aDefSet', 0.10055326275813617), ('bridge1', 0.1005284217406481), ('hintKey', 0.09986818797597732), ('e', 0.07259642236462113), ('type', 0.06766026014802368), ('spec', 0.06055108014376252)]
            if (o instanceof String) {
                family = fontFamilyResolver.resolve((String) o, this);
                if (family != null) {
                    return family;
                }
            } else if (o instanceof ParsedURL) {
                try {
                    GVTFontFamily ff = getFontFamily(ctx, (ParsedURL) o);
// ff                   No	: [('v', 0.5062810162827927), ('ret', 0.3229180620812367), ('ffam', 0.2666751925507598), ('gvtFontFamily', 0.1333418592174265), ('result', 0.041692630182066606), ('fontFamily', 0.04168917013692388), ('um', 0.03459673452598008), ('r2d', 0.026209631696227025), ('tpi', 0.019898962624269902), ('cm', 0.019276626274582435)]
                    if (ff != null)
                        return ff;
                } catch (SecurityException ex) {
// ex                   0	: [('ex', 0.4505159879006668), ('e', 0.331813271264206), ('se', 0.317751876997868), ('be', 0.18044879318544294), ('secEx', 0.17249434859394266), ('se2', 0.03613228045895812), ('de', 0.031947315299424676), ('message', 0.0024744516266907517), ('i', 0.0008401778434656275), ('ctx', 0.00047379449309116397)]
                    ctx.getUserAgent().displayError(ex);
                } catch (BridgeException ex) {
// ex                   1	: [('e', 0.6356272263836689), ('ex', 0.45051554620295015), ('be', 0.18044879332829403), ('de', 0.031947316075725665), ('s', 0.02066482325133238), ('n', 0.007741527342787862), ('tag', 0.0069170735466803825), ('prefix', 0.004502250766919512), ('ns', 0.004176579684563619), ('namespaceURI', 0.0041011828204001975)]
                    if (ERR_URI_UNSECURE.equals(ex.getCode()))
                        ctx.getUserAgent().displayError(ex);
                } catch (Exception ex) {
// ex                   1	: [('e', 0.669780039681636), ('ex', 0.2760398696719209), ('te', 0.007678689071041626), ('exc', 0.003087548099716111), ('exception', 0.0007789725422288081), ('relatedException', 9.213046546173739e-05), ('ctx', 6.115973057198469e-05), ('node', 2.796351336182326e-05), ('ret', 1.9011966810675116e-05), ('uri', 1.4568548522635315e-05)]
                }
            }
        }
        return null;
    }

    protected GVTFontFamily getFontFamily(BridgeContext ctx, ParsedURL purl) {
// ctx                  0	: [('ctx', 0.9375784852396872), ('purlStr', 0.3750232659384773), ('f', 0.3381704386182916), ('cx', 0.08394532735404289), ('fontName', 0.04960596603557598), ('file', 0.04196498384457546), ('ua', 0.03367511696508666), ('ref', 0.027906420566653222), ('userAgent', 0.02405177615268917), ('ur', 0.01391281763900513)]
// purl                 0	: [('purl', 0.6251218606487998), ('resourceURL', 0.3656975219452761), ('docURL', 0.1647194736558229), ('scriptURL', 0.1575458031648821), ('purlStr', 0.1250095670137737), ('uri', 0.1085532663102278), ('e', 0.09905328422341057), ('paraEnds', 0.07687379778247695), ('namespaceURI', 0.05098863786652601), ('e2', 0.04246276277454789)]
        String purlStr = purl.toString();
// purlStr              No	: [('uri', 0.7543232802779221), ('ctx', 0.7517648759522636), ('uriStr', 0.6250147783785602), ('s', 0.15089294681467016), ('msg', 0.054546095044198994), ('ns', 0.03923764385440376), ('ln', 0.0339984488333583), ('m', 0.028576312317454552), ('name', 0.025725798620711508), ('node', 0.023266883944720766)]
        Element e = getBaseElement(ctx);
// e                    0	: [('e', 0.4687185140633165), ('altGlyphElement', 0.24710440316158866), ('elt', 0.19637461688437602), ('cursorElement', 0.1904460071669058), ('paintElement', 0.17086148160638615), ('n', 0.14904621478477645), ('impl', 0.14878093747638274), ('targetElement', 0.14178992908190483), ('ref', 0.12357515219364532), ('ffuri', 0.1235518985715063)]
        SVGDocument svgDoc = (SVGDocument) e.getOwnerDocument();
// svgDoc               0	: [('svgDoc', 0.9375061324980227), ('d', 0.2192705430049376), ('doc', 0.20985364981226376), ('outDoc', 0.10038339593998678), ('rdoc', 0.10038296453657973), ('document', 0.03553081197476511), ('svgDocument', 0.005687720453238238), ('imgDocument', 0.002845603432789403), ('ttdoc', 0.002845258310063762), ('i', 0.0005025473531734412)]
        String docURL = svgDoc.getURL();
// docURL               0	: [('docURL', 0.5024968570917177), ('baseURI', 0.33369947830226665), ('uri', 0.07294528134996639), ('base', 0.06346128541355751), ('s', 0.03830325037834686), ('url', 0.03713293767026655), ('s0', 0.03612482945474662), ('doc', 0.03351651053555471), ('type', 0.03110859241112281), ('uriStr', 0.025078149783119386)]
        ParsedURL pDocURL = null;
// pDocURL              0	: [('pDocURL', 0.7927175229374234), ('purl', 0.21511629152838466), ('url', 0.08238469953977373), ('durl', 0.05896721935673271), ('x', 0.02727962274228752), ('burl', 0.023317220402390506), ('base', 0.021424694149183177), ('colorspace', 0.017832961851233325), ('needRawData', 0.015678220844420592), ('ret', 0.013570472327608437)]
        if (docURL != null)
            pDocURL = new ParsedURL(docURL);
        String baseURI = AbstractNode.getBaseURI(e);
// baseURI              0	: [('baseURI', 0.18645311794543631), ('uri', 0.1470193544394208), ('base', 0.1004983220238001), ('s', 0.09588815909675284), ('url', 0.09268849274485758), ('doc', 0.08907206503735829), ('uriStr', 0.0806337052614455), ('docPURL', 0.05856516753732568), ('script', 0.053767411243630006), ('pDocURL', 0.02928370981783418)]
        purl = new ParsedURL(baseURI, purlStr);
        UserAgent userAgent = ctx.getUserAgent();
// userAgent            1	: [('ctx', 0.7535418360396848), ('userAgent', 0.6283501933592067), ('ua', 0.24167929890413245), ('ati', 0.12677906455345722), ('ss', 0.09342377668935714), ('pp', 0.07573452553206732), ('ase', 0.06565533528886408), ('rb', 0.03461970730839527), ('patternTxf', 0.03169515021077943), ('outputStream', 0.0313686245539317)]
        try {
            userAgent.checkLoadExternalResource(purl, pDocURL);
        } catch (SecurityException ex) {
// ex                   2	: [('e', 0.5734831719739037), ('se', 0.317751876997868), ('ex', 0.21043514652953327), ('secEx', 0.17249434859394266), ('de', 0.10109625109597399), ('se2', 0.03613228045895812), ('be', 0.007371870040581034), ('message', 0.00247445142287432), ('i', 0.0008401699625636016), ('ctx', 0.00047378893909340005)]
            userAgent.displayError(ex);
            return null;
        }
        if (purl.getRef() != null) {
            Element ref = ctx.getReferencedElement(e, purlStr);
// ref                  No	: [('refElement', 0.8857231063825425), ('fontElt', 0.758404221205034), ('e', 0.37503112790886184), ('def', 0.28513636908128687), ('node', 0.27807852224755264), ('d', 0.2778341180956193), ('altGlyphElement', 0.24710440323887983), ('elt', 0.1351964880275459), ('cursorElement', 0.123554476380972), ('ffuri', 0.12355189859790665)]
            if (!ref.getNamespaceURI().equals(SVG_NAMESPACE_URI) || !ref.getLocalName().equals(SVG_FONT_TAG)) {
                return null;
            }
            SVGDocument doc = (SVGDocument) e.getOwnerDocument();
// doc                  2	: [('aref', 0.500071170219426), ('document', 0.43840836636125835), ('doc', 0.29697451839588834), ('n', 0.2687061459332189), ('svgDoc', 0.25710886528988297), ('svgDocument', 0.2556877204875424), ('localRefElement', 0.21920285583752758), ('node', 0.05323536612383721), ('evt', 0.049466484022044725), ('child', 0.0382779530895049)]
            SVGDocument rdoc = (SVGDocument) ref.getOwnerDocument();
// rdoc                 1	: [('d', 0.3596352715024688), ('rdoc', 0.250118237469754), ('doc', 0.22992678176579118), ('svgDoc', 0.22751276595540537), ('outDoc', 0.05019169796999339), ('document', 0.017765405987382556), ('i', 0.003249851865483918), ('svgDocument', 0.002843860226619119), ('toValue', 0.0017922114253688098), ('before', 0.0017801321299713666)]
            Element fontElt = ref;
// fontElt              0	: [('fontElt', 0.750019465324182), ('localRefElement', 0.37804533338155677), ('localFontElement', 0.37669318263477464), ('imgElem', 0.2577659319669981), ('gElem', 0.257765867254343), ('ctx', 0.2041457440756374), ('gen', 0.20239834278308333), ('e', 0.1997385064056069), ('node', 0.12065335147946646), ('elt', 0.08212446739565085)]
            if (doc != rdoc) {
                fontElt = (Element) doc.importNode(ref, true);
                String base = AbstractNode.getBaseURI(ref);
// base                 0	: [('base', 0.6250304520480588), ('svgDoc', 0.31250436922752567), ('filename', 0.08453671373851264), ('media', 0.04262310042781312), ('isWellFormedLabelVal', 0.04227768909981651), ('baseFieldName', 0.04226890052165065), ('s', 0.02397203977418821), ('ln', 0.0072620898534742385), ('prefix', 0.006764986504398547), ('ns', 0.004977887534562079)]
                Element g = doc.createElementNS(SVG_NAMESPACE_URI, SVG_G_TAG);
// g                    1	: [('viewElement', 0.7516684141251182), ('g', 0.5023086846292171), ('proxyElement', 0.2500050557057343), ('e', 0.042423019713752376), ('i', 0.02446572638538834), ('t', 0.01306419502660173), ('targetElement', 0.009919354731611056), ('sb', 0.009167446833680176), ('root', 0.008509093380766188), ('n', 0.008021993569456197)]
                g.appendChild(fontElt);
                g.setAttributeNS(XMLConstants.XML_NAMESPACE_URI, "xml:base", base);
                CSSUtilities.computeStyleAndURIs(ref, fontElt, purlStr);
            }
            Element fontFaceElt = null;
// fontFaceElt          No	: [('fontFaceElement', 0.3816297698411112), ('localFontFaceElement', 0.3753177043833675), ('e', 0.11759928746750742), ('root', 0.10664728794751384), ('refElement', 0.08892334536512274), ('svgPath', 0.08878354444662917), ('contextElement', 0.08060883665828023), ('ancestorSVG', 0.08013487678146007), ('compositeFilter', 0.08010742565799821), ('clonedGlyphElement', 0.08010703739171586)]
            for (Node n = fontElt.getFirstChild(); n != null; n = n.getNextSibling()) {
// n                    0	: [('n', 0.8760768024576935), ('tmp', 0.17515357281692365), ('fontElt', 0.1751486977161428), ('viewElement', 0.17514861143117325), ('e', 0.14122247787893685), ('node', 0.053235374638757424), ('m', 0.05132786716715673), ('evt', 0.04946649214393003), ('child', 0.03827795541374677), ('ancestors', 0.03343923995301122)]
                if ((n.getNodeType() == Node.ELEMENT_NODE) && n.getNamespaceURI().equals(SVG_NAMESPACE_URI) && n.getLocalName().equals(SVG_FONT_FACE_TAG)) {
                    fontFaceElt = (Element) n;
                    break;
                }
            }
            SVGFontFaceElementBridge fontFaceBridge;
// fontFaceBridge       0	: [('fontFaceBridge', 0.7500072451914668), ('i', 0.009953869951854611), ('e', 0.007691799780930559), ('n', 0.006534029262882009), ('ctx', 0.004473808706418339), ('x', 0.0038603922882591186), ('t', 0.00383276461584709), ('raf', 0.0037427964925025887), ('y', 0.0034153976427556167), ('src', 0.003239602415559702)]
            fontFaceBridge = (SVGFontFaceElementBridge) ctx.getBridge(SVG_NAMESPACE_URI, SVG_FONT_FACE_TAG);
            GVTFontFace gff = fontFaceBridge.createFontFace(ctx, fontFaceElt);
// gff                  0	: [('gff', 0.7565830377455315), ('fontFace', 0.08553592813019438), ('face', 0.006582865184168671), ('i', 0.0033606483274948983), ('e', 0.0019101042286413204), ('ctx', 0.0018951335409805843), ('n', 0.0017349554487501582), ('x', 0.0015579086332251591), ('s', 0.0014617920656198261), ('t', 0.0009293150906903884)]
            return new SVGFontFamily(gff, fontElt, ctx);
        }
        try {
            return ctx.getFontFamilyResolver().loadFont(purl.openStream(), this);
        } catch (Exception ex) {
// ex                   1	: [('e', 0.669780039681636), ('ex', 0.2760398696719209), ('te', 0.007678689071041626), ('exc', 0.003087548099716111), ('exception', 0.0007789725422288081), ('relatedException', 9.213046546173739e-05), ('ctx', 6.115973057198469e-05), ('node', 2.796351336182326e-05), ('ret', 1.9011966810675116e-05), ('uri', 1.4568548522635315e-05)]
        }
        return null;
    }

    protected Element getBaseElement(BridgeContext ctx) {
// ctx                  0	: [('ctx', 0.9652903713253749), ('elt', 0.4009315741848818), ('e', 0.34011657451741195), ('doc', 0.05298910703194585), ('n', 0.02278741422127372), ('o', 0.022607939874444916), ('ref', 0.022526894215821155), ('impl', 0.022522145832016904), ('cursorElement', 0.022520548902829143), ('svgDoc', 0.007814731260399025)]
        SVGDocument d = (SVGDocument) ctx.getDocument();
// d                    No	: [('doc', 0.5163440344276483), ('ttdoc', 0.1524179078827133), ('svgDocument', 0.08047404523956302), ('report', 0.060464489302868234), ('n', 0.04016658208466616), ('ret', 0.0352635282790064), ('result', 0.03217671361489113), ('v', 0.03154602372949735), ('svgDoc', 0.026339634474913297), ('document', 0.023511581205534343)]
        return d.getRootElement();
    }
}
