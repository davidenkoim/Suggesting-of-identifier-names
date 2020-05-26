// Type of training: selfTestingIdentifier
// Path to file: C:\Users\Igor\IdeaProjects\SLP-Core-0.3\java-projects\xmlgraphics-batik-trunk\batik-bridge\src\main\java\org\apache\batik\bridge\SVGGVTFont.java
// Number of identifiers: 139	Accuracy: 71.22%	MRR: 75.28%
// True 	Rank of true in predictions : [(predicted token, probability of a token), ...]

package org.apache.batik.bridge;

import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.text.AttributedCharacterIterator;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import org.apache.batik.css.engine.SVGCSSEngine;
import org.apache.batik.css.engine.value.Value;
import org.apache.batik.dom.util.XMLSupport;
import org.apache.batik.gvt.font.GVTFont;
import org.apache.batik.gvt.font.GVTFontFace;
import org.apache.batik.gvt.font.GVTGlyphVector;
import org.apache.batik.gvt.font.GVTLineMetrics;
import org.apache.batik.gvt.font.Glyph;
import org.apache.batik.gvt.font.Kern;
import org.apache.batik.gvt.font.KerningTable;
import org.apache.batik.gvt.font.SVGGVTGlyphVector;
import org.apache.batik.gvt.text.GVTAttributedCharacterIterator;
import org.apache.batik.gvt.text.TextPaintInfo;
import org.apache.batik.util.SVGConstants;
import org.w3c.dom.Element;

public final class SVGGVTFont implements GVTFont, SVGConstants {

    public static final AttributedCharacterIterator.Attribute PAINT_INFO = GVTAttributedCharacterIterator.TextAttribute.PAINT_INFO;

    private float fontSize;

    private GVTFontFace fontFace;

    private String[] glyphUnicodes;

    private String[] glyphNames;

    private String[] glyphLangs;

    private String[] glyphOrientations;

    private String[] glyphForms;

    private Element[] glyphElements;

    private Element[] hkernElements;

    private Element[] vkernElements;

    private BridgeContext ctx;

    private Element textElement;

    private Element missingGlyphElement;

    private KerningTable hKerningTable;

    private KerningTable vKerningTable;

    private String language;

    private String orientation;

    private float scale;

    private GVTLineMetrics lineMetrics = null;

    public SVGGVTFont(float fontSize, GVTFontFace fontFace, String[] glyphUnicodes, String[] glyphNames, String[] glyphLangs, String[] glyphOrientations, String[] glyphForms, BridgeContext ctx, Element[] glyphElements, Element missingGlyphElement, Element[] hkernElements, Element[] vkernElements, Element textElement) {
// fontSize             0	: [('fontSize', 0.8783008140157571), ('size', 0.42037327037019007), ('scale', 0.42035687189518955), ('x', 0.10492661342521784), ('y', 0.03633471665756367), ('x1', 0.032641066326639864), ('f', 0.023827897899898486), ('offset', 0.022674843130243358), ('v', 0.02249200938538396), ('x2', 0.01900563899426524)]
// fontFace             0	: [('fontFace', 0.9144783178420357), ('fontHeight', 0.8753340415833449), ('domFactory', 0.00896072795635594), ('fontFaceBridge', 0.008934402111060499), ('gff', 0.006581078839444343), ('scaleX', 0.0054808484999312075), ('scaleY', 0.0049826813465623995), ('face', 0.0032914325924416677), ('ctx', 0.0012827989093358364), ('a', 0.0010318135373436628)]
// glyphUnicodes        No	: [('mimeTypes', 0.1723322416810487), ('exts', 0.1649258108225036), ('val', 0.047344615943941515), ('pe', 0.047244951096088006), ('args', 0.028743561744980564), ('glyphForms', 0.025444908736698822), ('glyphNames', 0.025384471355840002), ('glyphLangs', 0.02538425565413648), ('glyphOrientations', 0.02538425565413648), ('expectedDest', 0.023562392086181865)]
// glyphNames           No	: [('mimeTypes', 0.1723322416810487), ('exts', 0.1649258108225036), ('val', 0.047344615943941515), ('pe', 0.047244951096088006), ('args', 0.028743561744980564), ('glyphForms', 0.025444908736698822), ('glyphLangs', 0.02538425565413648), ('glyphOrientations', 0.02538425565413648), ('glyphUnicodes', 0.023563556875380903), ('expectedDest', 0.023562392086181865)]
// glyphLangs           No	: [('mimeTypes', 0.1723322416810487), ('exts', 0.1649258108225036), ('val', 0.047344615943941515), ('pe', 0.047244951096088006), ('args', 0.028743561744980564), ('glyphForms', 0.025444908736698822), ('glyphNames', 0.0253844282154993), ('glyphOrientations', 0.02538425565413648), ('glyphUnicodes', 0.02356360001572161), ('expectedDest', 0.023562392086181865)]
// glyphOrientations    No	: [('mimeTypes', 0.1723322416810487), ('exts', 0.1649258108225036), ('val', 0.047344615943941515), ('pe', 0.047244951096088006), ('args', 0.028743561744980564), ('glyphForms', 0.025444908736698822), ('glyphNames', 0.025384471355840002), ('glyphLangs', 0.025384212513795773), ('glyphUnicodes', 0.02356360001572161), ('expectedDest', 0.023562392086181865)]
// glyphForms           No	: [('mimeTypes', 0.1723322416810487), ('exts', 0.1649258108225036), ('val', 0.047344615943941515), ('pe', 0.047244951096088006), ('args', 0.028743561744980564), ('glyphNames', 0.025384471355840002), ('glyphLangs', 0.02538425565413648), ('glyphOrientations', 0.025384212513795773), ('glyphUnicodes', 0.02356360001572161), ('expectedDest', 0.023562392086181865)]
// ctx                  0	: [('ctx', 0.9002784159576969), ('engine', 0.022274526643741594), ('bc', 0.015592758802151154), ('c', 0.009122788871815663), ('subCtx', 0.0019523110265742116), ('elt', 0.0014957776003424346), ('token', 0.0013939439917081944), ('theCtx', 0.000783727614535801), ('resCtx', 0.0007826922463588772), ('i', 0.0005025473531734412)]
// glyphElements        No	: [('fontElement', 0.33339808749501765), ('vkernElements', 0.19804053570443503), ('hkernElements', 0.1979798826218727), ('srcElems', 0.19797983948153197), ('localFontElement', 0.16670356552802634), ('imports', 0.03131433760406436), ('hkernElementArray', 0.03131317281486532), ('vkernElementArray', 0.03131312967452462), ('elemary', 0.03131308653418391), ('glyphElementArray', 0.031313000253502504)]
// missingGlyphElement  No	: [('domFactory', 0.25025724357745327), ('e', 0.18908078876984694), ('filteredElement', 0.06523949442913762), ('filterElement', 0.0637113294814055), ('element', 0.05783818348308424), ('imageElement', 0.057747245458401875), ('paintedElement', 0.0233548720025744), ('elt', 0.02133753859870376), ('bound', 0.01422489670388822), ('paintElement', 0.00992421627752151)]
// hkernElements        No	: [('fontElement', 0.5000647541616844), ('glyphElements', 0.19804088082716068), ('vkernElements', 0.19804053570443503), ('srcElems', 0.19797983948153197), ('imports', 0.03131433760406436), ('hkernElementArray', 0.03131317281486532), ('vkernElementArray', 0.03131312967452462), ('elemary', 0.03131308653418391), ('glyphElementArray', 0.031313000253502504), ('args', 0.007616801181600285)]
// vkernElements        No	: [('fontElement', 0.5000647541616844), ('glyphElements', 0.19804088082716068), ('hkernElements', 0.19797983948153197), ('srcElems', 0.19797983948153197), ('imports', 0.03131433760406436), ('hkernElementArray', 0.03131317281486532), ('vkernElementArray', 0.03131312967452462), ('elemary', 0.03131308653418391), ('glyphElementArray', 0.031313000253502504), ('args', 0.007616801181600285)]
// textElement          0	: [('textElement', 0.9062537610655301), ('e', 0.39348978716475663), ('element', 0.3484367096685918), ('filteredElement', 0.06523949507234064), ('filterElement', 0.06371133111085313), ('imageElement', 0.057747246021561845), ('paintElement', 0.042613168604171615), ('strokedElement', 0.03784161637621251), ('filledElement', 0.03784161637621251), ('elt', 0.026258332535242287)]
        this.fontFace = fontFace;
        this.fontSize = fontSize;
        this.glyphUnicodes = glyphUnicodes;
        this.glyphNames = glyphNames;
        this.glyphLangs = glyphLangs;
        this.glyphOrientations = glyphOrientations;
        this.glyphForms = glyphForms;
        this.ctx = ctx;
        this.glyphElements = glyphElements;
        this.missingGlyphElement = missingGlyphElement;
        this.hkernElements = hkernElements;
        this.vkernElements = vkernElements;
        this.scale = fontSize / fontFace.getUnitsPerEm();
        this.textElement = textElement;
        this.language = XMLSupport.getXMLLang(textElement);
        Value v = CSSUtilities.getComputedStyle(textElement, SVGCSSEngine.WRITING_MODE_INDEX);
// v                    0	: [('v', 0.30416047248884126), ('value', 0.123826704853407), ('s', 0.03468689731862503), ('v1', 0.03193340318251027), ('o', 0.028303831092400616), ('right', 0.02801211817103877), ('bottom', 0.02801194560967595), ('fontFamily', 0.027766737975594258), ('lv2', 0.027759921801762844), ('lv3', 0.027759921801762844)]
        if (v.getStringValue().startsWith(CSS_TB_VALUE)) {
            this.orientation = SVG_V_VALUE;
        } else {
            this.orientation = SVG_H_VALUE;
        }
        createKerningTables();
    }

    private void createKerningTables() {
        Kern[] hEntries = new Kern[hkernElements.length];
// hEntries             No	: [('vEntries', 0.8125030158413972), ('entries', 0.25012979689557896), ('p', 0.14149854955519972), ('result', 0.07072579339703275), ('report', 0.06785093901496518), ('constraints', 0.035432573253149795), ('panel', 0.03525224454595833), ('eng', 0.03511461106309988), ('q', 0.035100911201206335), ('n', 0.033255373401694335)]
        for (int i = 0; i < hkernElements.length; i++) {
// i                    0	: [('i', 0.9456678350060136), ('y', 0.05803117539318347), ('j', 0.05504945531680976), ('k', 0.013248265984595206), ('m', 0.012460275548743554), ('x', 0.009934331645093039), ('n', 0.0063599340523915), ('e', 0.00625212420885349), ('srcX', 0.00504807522564204), ('chunk', 0.004305981939381277)]
            Element hkernElement = hkernElements[i];
// hkernElement         9	: [('vkernElement', 0.7503167758328995), ('glyphElement', 0.20374391695893468), ('e', 0.14876945745190515), ('localGlyphElement', 0.10410490922435625), ('nodeElement', 0.10187674608746182), ('localFontFaceElement', 0.10187186041516763), ('glyphRefElement', 0.08988608872989501), ('element', 0.07345140440063555), ('fontFaceElement', 0.0677550691655518), ('hkernElement', 0.06769857071280067)]
            SVGHKernElementBridge hkernBridge = (SVGHKernElementBridge) ctx.getBridge(hkernElement);
// hkernBridge          No	: [('i', 0.008040412528049419), ('e', 0.007691787348633978), ('n', 0.006534018620104319), ('ctx', 0.004473800727908393), ('x', 0.0038603829404527685), ('t', 0.0038327586469725774), ('raf', 0.0037427951689446865), ('y', 0.0034153883807089584), ('src', 0.0032395995597619817), ('v', 0.0031715357931006395)]
            Kern hkern = hkernBridge.createKern(ctx, hkernElement, this);
// hkern                1	: [('vkern', 0.3977317082271088), ('hkern', 0.1988680718634724), ('raf', 0.08008624447045408), ('i', 0.06347074509915739), ('chunk', 0.0435020226867903), ('glyphElement', 0.0251899940396016), ('val', 0.020168989702405396), ('iter', 0.01798832155971116), ('st', 0.01685507791110167), ('cumulativeDistances1', 0.01629350900973464)]
            hEntries[i] = hkern;
        }
        hKerningTable = new KerningTable(hEntries);
        Kern[] vEntries = new Kern[vkernElements.length];
// vEntries             No	: [('hEntries', 0.8125030158413972), ('entries', 0.25012979689557896), ('p', 0.14149859356711827), ('result', 0.07072581397239822), ('report', 0.0678510129765784), ('constraints', 0.03543258565013328), ('panel', 0.03525225144851727), ('eng', 0.035114612434562244), ('q', 0.035100911989601066), ('n', 0.03325543589462035)]
        for (int i = 0; i < vkernElements.length; i++) {
// i                    0	: [('i', 0.9456678350060136), ('y', 0.05803117539318347), ('j', 0.05504945531680976), ('k', 0.013248265984595206), ('m', 0.012460275548743554), ('x', 0.009934331645093039), ('n', 0.0063599340523915), ('e', 0.00625212420885349), ('srcX', 0.00504807522564204), ('chunk', 0.004305981939381277)]
            Element vkernElement = vkernElements[i];
// vkernElement         9	: [('hkernElement', 0.7503167974032485), ('glyphElement', 0.20374391695893468), ('e', 0.14876945745190515), ('localGlyphElement', 0.10410490922435625), ('nodeElement', 0.10187674608746182), ('localFontFaceElement', 0.10187186041516763), ('glyphRefElement', 0.08988608872989501), ('element', 0.07345140440063555), ('fontFaceElement', 0.0677550691655518), ('vkernElement', 0.06769854914245164)]
            SVGVKernElementBridge vkernBridge = (SVGVKernElementBridge) ctx.getBridge(vkernElement);
// vkernBridge          No	: [('i', 0.008040412528049419), ('e', 0.007691787348633978), ('n', 0.006534018620104319), ('ctx', 0.004473800727908393), ('x', 0.0038603829404527685), ('t', 0.0038327586469725774), ('raf', 0.0037427951689446865), ('y', 0.0034153883807089584), ('src', 0.0032395995597619817), ('v', 0.0031715357931006395)]
            Kern vkern = vkernBridge.createKern(ctx, vkernElement, this);
// vkern                1	: [('hkern', 0.39773188078847166), ('vkern', 0.1988678993021096), ('raf', 0.08008624447045408), ('i', 0.06347074509915739), ('chunk', 0.0435020226867903), ('glyphElement', 0.0251899940396016), ('val', 0.020168989702405396), ('iter', 0.01798832155971116), ('st', 0.01685507791110167), ('cumulativeDistances1', 0.01629350900973464)]
            vEntries[i] = vkern;
        }
        vKerningTable = new KerningTable(vEntries);
    }

    public float getHKern(int glyphCode1, int glyphCode2) {
// glyphCode1           0	: [('glyphCode1', 0.875382648050197), ('glyphCode2', 0.08992077244771829), ('index', 0.06881417466043112), ('i', 0.05752153643904079), ('n', 0.025913491972484253), ('v', 0.013251072798168043), ('offset', 0.010566030014890405), ('node', 0.010466643375541674), ('e', 0.008180059852689658), ('c', 0.0053979488980026285)]
// glyphCode2           0	: [('glyphCode2', 0.8753174012862369), ('npoints', 0.16689148971658493), ('glyphCode1', 0.08992085873054373), ('glyphUnicode1', 0.07639174712101944), ('index', 0.06881580429134836), ('i', 0.05752153643904079), ('v', 0.013251369094698451), ('offset', 0.010566227545910679), ('y', 0.006848858427535194), ('image', 0.0052851134496369615)]
        if (glyphCode1 < 0 || glyphCode1 >= glyphUnicodes.length || glyphCode2 < 0 || glyphCode2 >= glyphUnicodes.length) {
            return 0f;
        }
        float ret;
// ret                  0	: [('ret', 0.8903264486145505), ('pol', 0.12544933564250718), ('seg', 0.0627211347772928), ('x', 0.022979497660575443), ('report', 0.02090407476382098), ('result', 0.008625852942473316), ('x1', 0.007464686796514726), ('n', 0.007023755615904432), ('v', 0.006444750861342729), ('delta', 0.005729402109682985)]
        ret = hKerningTable.getKerningValue(glyphCode1, glyphCode2, glyphUnicodes[glyphCode1], glyphUnicodes[glyphCode2]);
        return ret * scale;
    }

    public float getVKern(int glyphCode1, int glyphCode2) {
// glyphCode1           0	: [('glyphCode1', 0.875382648050197), ('glyphCode2', 0.08992077244771829), ('index', 0.06881417466043112), ('i', 0.05752153643904079), ('n', 0.025913491972484253), ('v', 0.013251072798168043), ('offset', 0.010566030014890405), ('node', 0.010466643375541674), ('e', 0.008180059852689658), ('c', 0.0053979488980026285)]
// glyphCode2           0	: [('glyphCode2', 0.8753174012862369), ('npoints', 0.16689148971658493), ('glyphCode1', 0.08992085873054373), ('glyphUnicode1', 0.07639174712101944), ('index', 0.06881580429134836), ('i', 0.05752153643904079), ('v', 0.013251369094698451), ('offset', 0.010566227545910679), ('y', 0.006848858427535194), ('image', 0.0052851134496369615)]
        if (glyphCode1 < 0 || glyphCode1 >= glyphUnicodes.length || glyphCode2 < 0 || glyphCode2 >= glyphUnicodes.length) {
            return 0f;
        }
        float ret;
// ret                  0	: [('ret', 0.8903264486145505), ('pol', 0.12544933564250718), ('seg', 0.0627211347772928), ('x', 0.022979497660575443), ('report', 0.02090407476382098), ('result', 0.008625852942473316), ('x1', 0.007464686796514726), ('n', 0.007023755615904432), ('v', 0.006444750861342729), ('delta', 0.005729402109682985)]
        ret = vKerningTable.getKerningValue(glyphCode1, glyphCode2, glyphUnicodes[glyphCode1], glyphUnicodes[glyphCode2]);
        return ret * scale;
    }

    public int[] getGlyphCodesForName(String name) {
// name                 0	: [('name', 0.15493825483390236), ('colors', 0.15400548700720312), ('unicode', 0.15318805542575048), ('listenerType', 0.1531861141017088), ('prefix', 0.11653713131481933), ('rect', 0.11279873318536042), ('s', 0.04219955999891182), ('ns', 0.037445288970862675), ('namespaceURI', 0.028858645484045726), ('rlm', 0.028191636087093908)]
        List glyphCodes = new ArrayList();
// glyphCodes           0	: [('glyphCodes', 0.5049870250581067), ('indexes', 0.2658812945835922), ('aci', 0.17471292254340035), ('len', 0.12154715906705035), ('textRuns', 0.10361160951807304), ('ret', 0.06605567278529655), ('lnLocs', 0.06352154970834686), ('h', 0.05569919490004179), ('defSet', 0.05050749227398321), ('sources', 0.04975115479030386)]
        for (int i = 0; i < glyphNames.length; i++) {
// i                    0	: [('i', 0.9456704358585102), ('y', 0.06051222205782577), ('cn', 0.05321076015606122), ('j', 0.05278137477495969), ('colorTbl', 0.026631057781648444), ('k', 0.01834878568128812), ('m', 0.01246027567293084), ('x', 0.011595461797505004), ('key', 0.009105449909752403), ('ns', 0.009002586393796908)]
            if (glyphNames[i] != null && glyphNames[i].equals(name)) {
                glyphCodes.add(i);
            }
        }
        int[] glyphCodeArray = new int[glyphCodes.size()];
// glyphCodeArray       0	: [('glyphCodeArray', 0.5012744373122167), ('props', 0.2141404896802882), ('sb', 0.17533115556141937), ('ret', 0.10795150995519102), ('inherited', 0.10672339426888669), ('firstGlyphs', 0.10639260852872376), ('result', 0.03493243096213599), ('c', 0.024459881117027498), ('orderXY', 0.02411667069560062), ('pixel', 0.02405914531364597)]
        for (int i = 0; i < glyphCodes.size(); i++) {
// i                    0	: [('i', 0.9456704358585102), ('y', 0.05803117597212853), ('j', 0.05504945553141254), ('cn', 0.05321075990891703), ('colorTbl', 0.02663105753450425), ('k', 0.01324826604588476), ('m', 0.01246027567293084), ('x', 0.009934332229398692), ('key', 0.009105449909752403), ('ns', 0.009002586393796908)]
            glyphCodeArray[i] = (Integer) glyphCodes.get(i);
        }
        return glyphCodeArray;
    }

    public int[] getGlyphCodesForUnicode(String unicode) {
// unicode              No	: [('name', 0.30812248353016447), ('colors', 0.15400548700720312), ('listenerType', 0.1531861141017088), ('prefix', 0.11653713131481933), ('rect', 0.11279873318536042), ('s', 0.04219955999891182), ('ns', 0.037445288970862675), ('namespaceURI', 0.028858645484045726), ('rlm', 0.028191636087093908), ('uri', 0.024806476927407492)]
        List glyphCodes = new ArrayList();
// glyphCodes           0	: [('glyphCodes', 0.5049870250581067), ('indexes', 0.2658812945835922), ('aci', 0.17471292254340035), ('len', 0.12154715906705035), ('textRuns', 0.10361160951807304), ('ret', 0.06605567278529655), ('lnLocs', 0.06352154970834686), ('h', 0.05569919490004179), ('defSet', 0.05050749227398321), ('sources', 0.04975115479030386)]
        for (int i = 0; i < glyphUnicodes.length; i++) {
// i                    0	: [('i', 0.9456704358585102), ('y', 0.06051222205782577), ('cn', 0.05321076015606122), ('j', 0.05278137477495969), ('colorTbl', 0.026631057781648444), ('k', 0.01834878568128812), ('glyphCode', 0.014841360776838227), ('m', 0.01246027567293084), ('x', 0.011595461797505004), ('key', 0.009105449909752403)]
            if (glyphUnicodes[i] != null && glyphUnicodes[i].equals(unicode)) {
                glyphCodes.add(i);
            }
        }
        int[] glyphCodeArray = new int[glyphCodes.size()];
// glyphCodeArray       0	: [('glyphCodeArray', 0.5012744373122167), ('props', 0.2141404896802882), ('sb', 0.17533115556141937), ('ret', 0.10795150995519102), ('inherited', 0.10672339426888669), ('firstGlyphs', 0.10639260852872376), ('result', 0.03493243096213599), ('c', 0.024459881117027498), ('orderXY', 0.02411667069560062), ('pixel', 0.02405914531364597)]
        for (int i = 0; i < glyphCodes.size(); i++) {
// i                    0	: [('i', 0.9456704358585102), ('y', 0.05803117597212853), ('j', 0.05504945553141254), ('cn', 0.05321075990891703), ('colorTbl', 0.02663105753450425), ('glyphCode', 0.014841360776838227), ('k', 0.01324826604588476), ('m', 0.01246027567293084), ('x', 0.009934332229398692), ('key', 0.009105449909752403)]
            glyphCodeArray[i] = (Integer) glyphCodes.get(i);
        }
        return glyphCodeArray;
    }

    private boolean languageMatches(String glyphLang) {
// glyphLang            No	: [('s', 0.12384107130321864), ('prefix', 0.11653713487101733), ('args', 0.08533235660404584), ('valueStr', 0.08525940412562635), ('mediaText', 0.056840813639882984), ('n', 0.05182698261457722), ('name', 0.04820237275156924), ('ns', 0.037445291043405715), ('fontNames', 0.032887166776052544), ('namespaceURI', 0.028858647333611726)]
        if (glyphLang == null || glyphLang.length() == 0) {
            return true;
        }
        StringTokenizer st = new StringTokenizer(glyphLang, ",");
// st                   0	: [('st', 0.5793377813750596), ('filterElement', 0.4490519219618642), ('tokens', 0.23319084807431892), ('e', 0.13569778651914668), ('i', 0.07024545483116532), ('v', 0.06974933115110052), ('lu', 0.0512723598892746), ('n', 0.04978740497140759), ('iter', 0.029485580103018893), ('elt', 0.01953721834432282)]
        while (st.hasMoreTokens()) {
            String s = st.nextToken();
// s                    0	: [('s', 0.22188094802749203), ('token', 0.10528161575372644), ('charVal', 0.08212735095257273), ('key', 0.05430231231403405), ('msg', 0.04992684636903172), ('e', 0.04795666843033939), ('args', 0.045036977503201736), ('ns', 0.03558079959243683), ('i', 0.03300301146592532), ('attrName', 0.031030841876109865)]
            if (s.equals(language) || (s.startsWith(language) && s.length() > language.length() && s.charAt(language.length()) == '-')) {
                return true;
            }
        }
        return false;
    }

    private boolean orientationMatches(String glyphOrientation) {
// glyphOrientation     No	: [('s1', 0.5170294837363324), ('res', 0.22227994520923738), ('prefix', 0.11653713487101733), ('n', 0.05182697312553457), ('name', 0.04820237275156924), ('s', 0.042199566608180104), ('ns', 0.037445291043405715), ('namespaceURI', 0.028858647333611726), ('e', 0.027463933727833956), ('t', 0.027268849875427447)]
        if (glyphOrientation == null || glyphOrientation.length() == 0) {
            return true;
        }
        return glyphOrientation.equals(orientation);
    }

    private boolean formMatches(String glyphUnicode, String glyphForm, AttributedCharacterIterator aci, int currentIndex) {
// glyphUnicode         No	: [('glyphs', 0.6269291775803368), ('text', 0.25166316177138787), ('w', 0.17686327915953878), ('prefix', 0.11653713664916052), ('usesUnderlying', 0.07631373163412153), ('e', 0.05472560874225042), ('name', 0.04820237384361215), ('s', 0.04219956991289637), ('elt', 0.041394309089394656), ('ns', 0.037445292079702985)]
// glyphForm            No	: [('e', 0.07010751193904866), ('f', 0.04813866667750768), ('ln', 0.04017698044345803), ('a', 0.03478367693888702), ('m', 0.0332359222668739), ('ns', 0.03216157215291768), ('value', 0.027763870890253402), ('t', 0.027457558488732815), ('c', 0.02735237141105826), ('s', 0.027216328551356795)]
// aci                  1	: [('foundMatchingGlyph', 0.6666819638343695), ('aci', 0.6483400863490978), ('s', 0.5358775559234462), ('iter', 0.2552899055239363), ('end', 0.16686702220974622), ('lnLocs', 0.1318306175535432), ('data', 0.1253617031998837), ('sb', 0.11396963790578485), ('tcount', 0.1129496467770498), ('splitLo', 0.11294945263426011)]
// currentIndex         8	: [('numWords', 0.6375945061790604), ('aciIdx', 0.1967787924733784), ('end', 0.195788694027442), ('index', 0.19158259308589784), ('i', 0.14576015139577425), ('charOrder', 0.14483828516380667), ('start', 0.13862666096246828), ('ci', 0.12075308280317631), ('currentIndex', 0.10941763081795093), ('aciIndex', 0.09427451895238817)]
        if (aci == null || glyphForm == null || glyphForm.length() == 0) {
            return true;
        }
        char c = aci.setIndex(currentIndex);
// c                    0	: [('c', 0.8764847349122941), ('ch', 0.08280769674292122), ('q', 0.07773564164921466), ('key', 0.07700281343394537), ('i', 0.0729914525375097), ('lookupTableBuf', 0.07256078992087267), ('ret', 0.055599114609432176), ('lastPixel', 0.05495968304154078), ('dp', 0.05442984040255945), ('shortData', 0.03628387409433189)]
        Integer form = (Integer) aci.getAttribute(GVTAttributedCharacterIterator.TextAttribute.ARABIC_FORM);
// form                 2	: [('subBidiLevel', 0.27153126090362795), ('ps', 0.25042205820584545), ('form', 0.250173851406737), ('matched', 0.25017337678405277), ('currentForm', 0.2501336945086292), ('key', 0.0905637977679562), ('integer', 0.09051306094503393), ('lengthAdj', 0.09051254322235104), ('chunkType', 0.09026955199772321), ('n', 0.07808889656713539)]
        if (form == null || form.equals(GVTAttributedCharacterIterator.TextAttribute.ARABIC_NONE)) {
            return false;
        }
        if (glyphUnicode.length() > 1) {
            boolean matched = true;
// matched              0	: [('matched', 0.7520325118745914), ('form', 0.2504774532034835), ('ps', 0.25042189337376863), ('b', 0.0827837351130977), ('c', 0.047428317683867405), ('oldValue', 0.042606849457147426), ('important', 0.03162389360678584), ('passed', 0.025757055100323258), ('foundMatchingGlyph', 0.025564779474354194), ('strippedSome', 0.0255640892231856)]
            for (int j = 1; j < glyphUnicode.length(); j++) {
// j                    0	: [('j', 0.7576375158177897), ('i', 0.5969861408971742), ('y', 0.11892851186915254), ('k', 0.09337645557959151), ('lnLocs', 0.05032175885267432), ('s', 0.042952580413208666), ('idx', 0.0336042361977969), ('len', 0.01682398664373901), ('cn', 0.016624370811648335), ('matrix', 0.015446620798241124)]
                c = aci.next();
                if (glyphUnicode.charAt(j) != c) {
                    matched = false;
                    break;
                }
            }
            aci.setIndex(currentIndex);
            if (matched) {
                aci.setIndex(currentIndex + glyphUnicode.length() - 1);
                Integer lastForm = (Integer) aci.getAttribute(GVTAttributedCharacterIterator.TextAttribute.ARABIC_FORM);
// lastForm             No	: [('nextForm', 0.5226291368204035), ('form', 0.3758651850181429), ('subBidiLevel', 0.13576563035425426), ('key', 0.04528189737805612), ('integer', 0.04525653043749552), ('lengthAdj', 0.04525627159116327), ('currentForm', 0.022629568230957315), ('level', 0.022629223102514313), ('vOrient', 0.02262905053829281), ('e', 0.02018274058648531)]
                aci.setIndex(currentIndex);
                if (form != null && lastForm != null) {
                    if (form.equals(GVTAttributedCharacterIterator.TextAttribute.ARABIC_TERMINAL) && lastForm.equals(GVTAttributedCharacterIterator.TextAttribute.ARABIC_INITIAL)) {
                        return glyphForm.equals(SVGConstants.SVG_ISOLATED_VALUE);
                    } else if (form.equals(GVTAttributedCharacterIterator.TextAttribute.ARABIC_TERMINAL)) {
                        return glyphForm.equals(SVGConstants.SVG_TERMINAL_VALUE);
                    } else if (form.equals(GVTAttributedCharacterIterator.TextAttribute.ARABIC_MEDIAL) && lastForm.equals(GVTAttributedCharacterIterator.TextAttribute.ARABIC_MEDIAL)) {
                        return glyphForm.equals(SVGConstants.SVG_MEDIAL_VALUE);
                    }
                }
            }
        }
        if (form.equals(GVTAttributedCharacterIterator.TextAttribute.ARABIC_ISOLATED)) {
            return glyphForm.equals(SVGConstants.SVG_ISOLATED_VALUE);
        }
        if (form.equals(GVTAttributedCharacterIterator.TextAttribute.ARABIC_TERMINAL)) {
            return glyphForm.equals(SVGConstants.SVG_TERMINAL_VALUE);
        }
        if (form.equals(GVTAttributedCharacterIterator.TextAttribute.ARABIC_INITIAL)) {
            return glyphForm.equals(SVGConstants.SVG_INITIAL_VALUE);
        }
        if (form.equals(GVTAttributedCharacterIterator.TextAttribute.ARABIC_MEDIAL)) {
            return glyphForm.equals(SVGConstants.SVG_MEDIAL_VALUE);
        }
        return false;
    }

    public boolean canDisplayGivenName(String name) {
// name                 0	: [('name', 0.15493825483390236), ('colors', 0.15400548700720312), ('unicode', 0.15318805542575048), ('listenerType', 0.1531861141017088), ('prefix', 0.11653713131481933), ('rect', 0.11279873318536042), ('s', 0.04219955999891182), ('ns', 0.037445288970862675), ('namespaceURI', 0.028858645484045726), ('rlm', 0.028191636087093908)]
        for (int i = 0; i < glyphNames.length; i++) {
// i                    0	: [('i', 0.9456678955198132), ('j', 0.07490140546122406), ('y', 0.050320771766998694), ('x', 0.031275783791246), ('b', 0.024563993502417003), ('gi', 0.01612801267421259), ('k', 0.01399827367300174), ('idx', 0.010101119644462787), ('property', 0.008836268576309475), ('i_h', 0.007925929048581828)]
            if (glyphNames[i] != null && glyphNames[i].equals(name) && languageMatches(glyphLangs[i]) && orientationMatches(glyphOrientations[i])) {
                return true;
            }
        }
        return false;
    }

    public boolean canDisplay(char c) {
// c                    0	: [('c', 0.9039869022491732), ('val', 0.023553724900471917), ('scriptType', 0.017642884401012065), ('psgn', 0.017618067800259728), ('node', 0.013729586313747131), ('text', 0.011786055786668869), ('str', 0.01176867015968516), ('publicId', 0.011744263400879463), ('ch', 0.0060028500791390145), ('gn', 0.005912585727106554)]
        for (int i = 0; i < glyphUnicodes.length; i++) {
// i                    0	: [('i', 0.945670535314867), ('j', 0.07490140524663196), ('y', 0.05032077118808242), ('x', 0.03127578320696939), ('b', 0.02456399327924835), ('gi', 0.016128012647232234), ('glyphCode', 0.014658612204519528), ('k', 0.013998273611715233), ('idx', 0.010101119451490694), ('property', 0.008836268558799045)]
            if (glyphUnicodes[i].indexOf(c) != -1 && languageMatches(glyphLangs[i]) && orientationMatches(glyphOrientations[i])) {
                return true;
            }
        }
        return false;
    }

    public int canDisplayUpTo(char[] text, int start, int limit) {
// text                 1	: [('chars', 0.7968800347086755), ('text', 0.5570385481944029), ('ch', 0.0853219780375929), ('cbuf', 0.05684886682572965), ('cbuff', 0.031252694305997086), ('data', 0.028159061699234003), ('buffer', 0.017108306379668484), ('buf', 0.015660675629521784), ('digit', 0.015627014837791935), ('device', 0.015626885416769817)]
// start                0	: [('start', 0.8344148066061663), ('beginIndex', 0.30005297608863984), ('index', 0.04374449536820936), ('y', 0.006848760181992624), ('i', 0.006523713324354609), ('width', 0.0038111038967530487), ('x', 0.0035320457873829997), ('height', 0.0031842074787853205), ('idx', 0.0024959098860809466), ('off', 0.0020052853243117488)]
// limit                0	: [('limit', 0.43859782912601547), ('last', 0.377608733116666), ('length', 0.262820030261126), ('end', 0.1929766746714471), ('stop', 0.08754649769931952), ('value', 0.025747882354324556), ('wsStart', 0.025174918104057434), ('extent', 0.008349861471869731), ('y', 0.006852739884411042), ('i', 0.0065244457525009)]
        StringCharacterIterator sci = new StringCharacterIterator(new String(text));
// sci                  0	: [('sci', 0.8819497918265223), ('aci', 0.007006930845457662), ('text', 0.00698951302696406), ('iter', 0.006964470233381204), ('str', 0.0034999053589234116), ('i', 0.0008401620818737246), ('e', 0.0004775260571603301), ('ctx', 0.00047378338524514614), ('n', 0.00043373886218753954), ('x', 0.0003894771583062898)]
        return canDisplayUpTo(sci, start, limit);
    }

    public int canDisplayUpTo(CharacterIterator iter, int start, int limit) {
// iter                 0	: [('iter', 0.9091482297984614), ('ci', 0.7708373565217199), ('s', 0.5359576877421662), ('aci', 0.2855225718995296), ('valuesString', 0.19071722919673667), ('keySplinesString', 0.18990690452064626), ('start', 0.136814514197605), ('data', 0.12689136670080417), ('raf', 0.09877308847602073), ('keyPointsString', 0.09535606825596313)]
// start                0	: [('start', 0.876081468912849), ('currentIndex', 0.6542589208676953), ('firstUnset', 0.37880145592039005), ('position', 0.13150790670373627), ('index', 0.022895555640685395), ('iter', 0.022886992780917177), ('match', 0.022754564401448435), ('charOrder', 0.022750925889717697), ('displayUpToIndex', 0.022736753593626126), ('aciIdx', 0.018207639157807015)]
// limit                1	: [('numSegments', 0.5359847816483829), ('limit', 0.43859782912601547), ('length', 0.262820030261126), ('end', 0.11188398905462023), ('numChars', 0.10892151779496224), ('stop', 0.08754649769931952), ('len', 0.009081637590704473), ('y', 0.006852739884411042), ('i', 0.0065244457525009), ('count', 0.005476577643582396)]
        AttributedCharacterIterator aci = null;
// aci                  0	: [('aci', 0.8237644721846454), ('paramTypes', 0.0928217534753061), ('scriptOrigin', 0.05801458066860032), ('node', 0.038941475607476225), ('parameters', 0.029935547124988283), ('toLengthList', 0.02912659762689994), ('accLengthList', 0.029126511345503866), ('types', 0.011875876501566587), ('yPoints', 0.01174185494534117), ('values', 0.011618387442547135)]
        if (iter instanceof AttributedCharacterIterator) {
            aci = (AttributedCharacterIterator) iter;
        }
        char c = iter.setIndex(start);
// c                    0	: [('c', 0.8764846497715492), ('index', 0.5644608547312724), ('v', 0.2534764342168272), ('currentIndex', 0.25020714952363404), ('curr', 0.1889879079009333), ('ch', 0.08280769726321822), ('q', 0.07773564184647012), ('key', 0.07700281515492778), ('i', 0.07299144830267343), ('lookupTableBuf', 0.07256079108456585)]
        int currentIndex = start;
// currentIndex         1	: [('ci', 0.7666407802625429), ('currentIndex', 0.750282786594123), ('start', 0.64550312362389), ('position', 0.13150790683505856), ('chunkACIs', 0.08620066576325434), ('height', 0.06541765071403102), ('n', 0.051617252643622956), ('sb', 0.050436034336320865), ('i', 0.047582122084023334), ('len', 0.04131778950409727)]
        while (c != CharacterIterator.DONE && currentIndex < limit) {
            boolean foundMatchingGlyph = false;
// foundMatchingGlyph   2	: [('aci', 0.6668103774309815), ('c', 0.10621221956078503), ('foundMatchingGlyph', 0.09321182511634395), ('fontFamilyMap', 0.08322828512953712), ('b', 0.08297616939660653), ('item', 0.08267251914133553), ('position', 0.08258228500739538), ('p1', 0.082577764682201), ('p2', 0.08254093989976931), ('li', 0.05293855071071556)]
            for (int i = 0; i < glyphUnicodes.length; i++) {
// i                    0	: [('i', 0.9456573088768859), ('j', 0.10953656743727759), ('y', 0.035595182588300286), ('glyphCode', 0.019797170837277465), ('m', 0.012460261114157328), ('k', 0.01004312267527083), ('x', 0.009934254232672074), ('glyphCode1', 0.008341981625188911), ('glyphCode2', 0.008341873766296644), ('e', 0.006224311938644263)]
                if (glyphUnicodes[i].indexOf(c) == 0 && languageMatches(glyphLangs[i]) && orientationMatches(glyphOrientations[i]) && formMatches(glyphUnicodes[i], glyphForms[i], aci, currentIndex)) {
                    if (glyphUnicodes[i].length() == 1) {
                        foundMatchingGlyph = true;
                        break;
                    } else {
                        boolean matched = true;
// matched              0	: [('matched', 0.7520325093631162), ('b', 0.05581137266521977), ('c', 0.04742827534757015), ('important', 0.026662421359913136), ('oldValue', 0.024238089158197293), ('maxIndexB', 0.023078823023433618), ('maxIndexR', 0.023078715171688524), ('maxIndexG', 0.023078715171688524), ('node', 0.01811448125318754), ('i', 0.01748289002774942)]
                        for (int j = 1; j < glyphUnicodes[i].length(); j++) {
// j                    0	: [('j', 0.75822167808492), ('i', 0.4592422688576709), ('y', 0.11892851186915254), ('k', 0.09337645557959151), ('lnLocs', 0.05032175885267432), ('matrix', 0.015446620798241124), ('m', 0.012877730996291945), ('s', 0.010738140868335636), ('x', 0.009934250142668025), ('idx', 0.008401057990707592)]
                            c = iter.next();
                            if (glyphUnicodes[i].charAt(j) != c) {
                                matched = false;
                                break;
                            }
                        }
                        if (matched) {
                            foundMatchingGlyph = true;
                            break;
                        } else {
                            c = iter.setIndex(currentIndex);
                        }
                    }
                }
            }
            if (!foundMatchingGlyph) {
                return currentIndex;
            }
            c = iter.next();
            currentIndex = iter.getIndex();
        }
        return -1;
    }

    public int canDisplayUpTo(String str) {
// str                  0	: [('str', 0.8760599327802616), ('workBuff', 0.18281413735411572), ('prefix', 0.01456714163661295), ('ps', 0.013249113918413654), ('ctx', 0.010078758454918988), ('w', 0.007657138324772179), ('tmp', 0.006632862515101493), ('name', 0.006025296457443052), ('s', 0.005274945412939824), ('t', 0.005234941104370511)]
        StringCharacterIterator sci = new StringCharacterIterator(str);
// sci                  0	: [('sci', 0.9522074442014776), ('aci', 0.007006930845457662), ('text', 0.00698951302696406), ('iter', 0.006964470233381204), ('str', 0.003499883788753059), ('i', 0.0008401620818737246), ('e', 0.0004775260571603301), ('ctx', 0.00047378338524514614), ('n', 0.00043373886218753954), ('x', 0.0003894771583062898)]
        return canDisplayUpTo(sci, 0, str.length());
    }

    public GVTGlyphVector createGlyphVector(FontRenderContext frc, char[] chars) {
// frc                  0	: [('frc', 0.9672660550308835), ('localFRC', 0.003909084497297168), ('g', 0.0013760348521268098), ('i', 0.0008401699625636016), ('e', 0.00047753120904810896), ('ctx', 0.00047378893909340005), ('n', 0.0004337436064534282), ('x', 0.0003894814383361368), ('s', 0.00036545225114348247), ('t', 0.0002323312806795048)]
// chars                0	: [('chars', 0.7705868941905715), ('text', 0.39067006906940127), ('ch', 0.031278859023234275), ('cbuff', 0.03125269428901239), ('data', 0.028159061699234003), ('buffer', 0.017108306379668484), ('buf', 0.015660675227550488), ('digit', 0.015627014826468798), ('device', 0.015626885405446683), ('t', 0.010575376571253119)]
        StringCharacterIterator sci = new StringCharacterIterator(new String(chars));
// sci                  0	: [('sci', 0.9522074442014776), ('str', 0.22585023939721996), ('fontStyleStrings', 0.07986418942985012), ('font', 0.05919821073458679), ('fontFamily', 0.05917143893220909), ('ci', 0.029587947141232664), ('glyphCodes', 0.0295872116895123), ('chars', 0.029582786705697462), ('y', 0.000714801120195915), ('e', 0.00047116330401316477)]
        return createGlyphVector(frc, sci);
    }

    public GVTGlyphVector createGlyphVector(FontRenderContext frc, CharacterIterator ci) {
// frc                  0	: [('frc', 0.9672660550308835), ('y', 0.002859279242017129), ('e', 0.0018846969145201093), ('ctx', 0.0017647837159768275), ('i', 0.0016825936263638718), ('w', 0.0012569348388680637), ('s', 0.0011320870685015889), ('localFRC', 0.0009932155073632174), ('x', 0.0009680382086949988), ('r', 0.0007627907166788272)]
// ci                   0	: [('ci', 0.9537057401763929), ('aci', 0.7855225075315613), ('currentIndex', 0.7686595864439316), ('iter', 0.7612488531969654), ('data', 0.5553048284628028), ('s', 0.2916741294038603), ('index', 0.2063010412463292), ('i', 0.15480148306779581), ('charOrder', 0.1540019990636016), ('aciIdx', 0.10697687651801646)]
        AttributedCharacterIterator aci = null;
// aci                  0	: [('aci', 0.8237636130071666), ('tpi', 0.5005453437821006), ('runaci', 0.3076998956966426), ('o1', 0.23078267155165144), ('paramTypes', 0.09282175350961255), ('scriptOrigin', 0.05801458073149544), ('node', 0.038941477397128534), ('s0', 0.036712036325994894), ('type', 0.031598221102182224), ('parameters', 0.029935547336544625)]
        if (ci instanceof AttributedCharacterIterator) {
            aci = (AttributedCharacterIterator) ci;
        }
        List glyphs = new ArrayList();
// glyphs               No	: [('gv', 0.36154424820493336), ('glyphElements', 0.22225067582443941), ('it', 0.18441967252191863), ('l', 0.1518746798848731), ('children', 0.15009861949467823), ('glyphVector', 0.111124342798629), ('values', 0.09223609768146114), ('srcs', 0.08634522321765925), ('v', 0.07603295963455924), ('raf', 0.056491255671382516)]
        char c = ci.first();
// c                    0	: [('c', 0.8764846497715492), ('ch', 0.39472876445652494), ('v', 0.2534764313360437), ('i', 0.18598622181264055), ('n', 0.15626891283630034), ('lookupTableBuf', 0.07256079050840915), ('iter', 0.0711522479188694), ('ret', 0.05559911960040389), ('lastPixel', 0.05495968417533291), ('dp', 0.05442984120862082)]
        while (c != CharacterIterator.DONE) {
            boolean foundMatchingGlyph = false;
// foundMatchingGlyph   2	: [('charAdvance', 0.29168246548396376), ('newPath', 0.229176864069975), ('foundMatchingGlyph', 0.09321182511634395), ('b', 0.08278373600572793), ('li', 0.05293855071071556), ('e', 0.0474897472706288), ('isCSS', 0.047397490123709), ('namespaceDeclarations', 0.04707242160897652), ('matched', 0.046563729915008054), ('isStatic', 0.046563082799177424)]
            for (int i = 0; i < glyphUnicodes.length; i++) {
// i                    0	: [('i', 0.945651983857008), ('fontSize', 0.4167859084367728), ('size', 0.20851388471912746), ('j', 0.10953656829502369), ('y', 0.03559518490421477), ('glyphCode', 0.019797171660629106), ('sp', 0.01763880379047291), ('m', 0.012460261610935282), ('k', 0.010043122920443258), ('x', 0.00993425657003021)]
                if (glyphUnicodes[i].indexOf(c) == 0 && languageMatches(glyphLangs[i]) && orientationMatches(glyphOrientations[i]) && formMatches(glyphUnicodes[i], glyphForms[i], aci, ci.getIndex())) {
                    if (glyphUnicodes[i].length() == 1) {
                        Element glyphElement = glyphElements[i];
// glyphElement         0	: [('glyphElement', 0.3791067442658582), ('localGlyphElement', 0.37563246435993825), ('e', 0.14876945745190515), ('nodeElement', 0.10187674608746182), ('localFontFaceElement', 0.10187186041516763), ('hkernElement', 0.10187186041516763), ('vkernElement', 0.1018718388448186), ('imageElement', 0.04823514441270557), ('root', 0.0450226000746118), ('elt', 0.04421131360235532)]
                        SVGGlyphElementBridge glyphBridge = (SVGGlyphElementBridge) ctx.getBridge(glyphElement);
// glyphBridge          0	: [('glyphBridge', 0.9562511304451293), ('glyphs', 0.03336946680697368), ('scale', 0.00626375588327098), ('glyphTrans', 0.00625341576987251), ('i', 0.0005025257830030887), ('e', 0.00048073670928962367), ('n', 0.00040837616375652), ('ctx', 0.0002796125454942746), ('x', 0.00024127393377829803), ('t', 0.00023954741543578606)]
                        TextPaintInfo tpi = null;
// tpi                  0	: [('tpi', 0.9122007699211075), ('aci', 0.501124205163837), ('glyphCodes', 0.0604236469902514), ('pi', 0.04175781108790521), ('n', 0.008819400830974763), ('result', 0.008412133224800047), ('g2d', 0.007041973485771047), ('t', 0.005740238565503733), ('e', 0.005027976734204851), ('at', 0.0040870889557909395)]
                        if (aci != null) {
                            tpi = (TextPaintInfo) aci.getAttribute(PAINT_INFO);
                        }
                        Glyph glyph = glyphBridge.createGlyph(ctx, glyphElement, textElement, i, fontSize, fontFace, tpi);
// glyph                0	: [('glyph', 0.9200606936112563), ('c', 0.005805487310516335), ('n', 0.0054831867447260665), ('p', 0.005180806108315241), ('l', 0.004592222324904454), ('item', 0.004485499396748877), ('ts', 0.0034801925232418743), ('bf', 0.0034792326541451144), ('e', 0.003375384326787803), ('idx', 0.0028816976243628174)]
                        glyphs.add(glyph);
                        foundMatchingGlyph = true;
                        break;
                    } else {
                        int current = ci.getIndex();
// current              No	: [('start', 0.6455035649422469), ('i', 0.1420866109455101), ('s', 0.13994584980744054), ('n', 0.11456259084386924), ('ret', 0.09996528059768428), ('idx', 0.07050785504342272), ('count', 0.04670899551785421), ('len', 0.03975569358124261), ('word', 0.03572085790048001), ('numSp', 0.03570300379851855)]
                        boolean matched = true;
// matched              0	: [('matched', 0.7520325087150022), ('expectNumber', 0.12604025747178532), ('c', 0.04742826540982118), ('uc', 0.04244090853349342), ('isLocal', 0.027451779509250457), ('maxIndexB', 0.0230788230913706), ('maxIndexR', 0.023078715239625503), ('maxIndexG', 0.023078715239625503), ('n', 0.02138163670302343), ('rgnBr', 0.0212532999056153)]
                        for (int j = 1; j < glyphUnicodes[i].length(); j++) {
// j                    0	: [('j', 0.75822167808492), ('i', 0.4592422688576709), ('y', 0.11892851186915254), ('k', 0.09337645557959151), ('lnLocs', 0.05032175885267432), ('matrix', 0.015446620798241124), ('m', 0.012877730996291945), ('s', 0.010738140868335636), ('x', 0.009934250142668025), ('idx', 0.008401057990707592)]
                            c = ci.next();
                            if (glyphUnicodes[i].charAt(j) != c) {
                                matched = false;
                                break;
                            }
                        }
                        if (matched) {
                            Element glyphElement = glyphElements[i];
// glyphElement         0	: [('glyphElement', 0.3791067442658582), ('localGlyphElement', 0.37563246435993825), ('e', 0.14876946260393162), ('nodeElement', 0.10187674614973906), ('localFontFaceElement', 0.10187186042649077), ('hkernElement', 0.10187186042649077), ('vkernElement', 0.10187183885614173), ('imageElement', 0.04823514441270557), ('root', 0.0450226000746118), ('elt', 0.04421131360235532)]
                            SVGGlyphElementBridge glyphBridge = (SVGGlyphElementBridge) ctx.getBridge(glyphElement);
// glyphBridge          0	: [('glyphBridge', 0.9562511304451293), ('glyphs', 0.03336946680697368), ('scale', 0.00626375588327098), ('glyphTrans', 0.00625341576987251), ('i', 0.0005025257830030887), ('e', 0.00048073670928962367), ('n', 0.00040837616375652), ('ctx', 0.0002796125454942746), ('x', 0.00024127393377829803), ('t', 0.00023954741543578606)]
                            TextPaintInfo tpi = null;
// tpi                  0	: [('tpi', 0.9122007699211075), ('glyphCodes', 0.0604236469902514), ('pi', 0.04175781108790521), ('sb', 0.03733037885748059), ('ctx', 0.031206148700495314), ('report', 0.010725345944730883), ('g', 0.009019169494559446), ('g2d', 0.0058829941739955895), ('i', 0.0051582933532072755), ('p', 0.004529089785001178)]
                            if (aci != null) {
                                aci.setIndex(ci.getIndex());
                                tpi = (TextPaintInfo) aci.getAttribute(PAINT_INFO);
                            }
                            Glyph glyph = glyphBridge.createGlyph(ctx, glyphElement, textElement, i, fontSize, fontFace, tpi);
// glyph                0	: [('glyph', 0.9200606936112563), ('c', 0.005805487310516335), ('n', 0.0054831867447260665), ('p', 0.005180806108315241), ('l', 0.004592222324904454), ('item', 0.004485499396748877), ('ts', 0.0034801925232418743), ('bf', 0.0034792326541451144), ('e', 0.003375384326787803), ('idx', 0.0028816976243628174)]
                            glyphs.add(glyph);
                            foundMatchingGlyph = true;
                            break;
                        } else {
                            c = ci.setIndex(current);
                        }
                    }
                }
            }
            if (!foundMatchingGlyph) {
                SVGGlyphElementBridge glyphBridge = (SVGGlyphElementBridge) ctx.getBridge(missingGlyphElement);
// glyphBridge          0	: [('glyphBridge', 0.8041700608663688), ('glyphs', 0.03336946680697368), ('scale', 0.00626375588327098), ('glyphTrans', 0.00625341576987251), ('ctx', 0.0009785636675295312), ('i', 0.0005025473531734412), ('e', 0.00048073670928962367), ('node', 0.0004474197927676657), ('n', 0.00040837616375652), ('ret', 0.0003041938616265519)]
                TextPaintInfo tpi = null;
// tpi                  0	: [('tpi', 0.8562643843438505), ('pi', 0.16703124435162084), ('glyphCodes', 0.0604236469902514), ('sb', 0.03733037885748059), ('ctx', 0.031206148700495314), ('report', 0.010725345944730883), ('parentPI', 0.010531786671065307), ('g', 0.009019169494559446), ('g2d', 0.0058829941739955895), ('i', 0.0051582933532072755)]
                if (aci != null) {
                    aci.setIndex(ci.getIndex());
                    tpi = (TextPaintInfo) aci.getAttribute(PAINT_INFO);
                }
                Glyph glyph = glyphBridge.createGlyph(ctx, missingGlyphElement, textElement, -1, fontSize, fontFace, tpi);
// glyph                0	: [('glyph', 0.9200606936112563), ('c', 0.005805487310516335), ('n', 0.0054831867447260665), ('p', 0.005180806108315241), ('l', 0.004592222324904454), ('item', 0.004485499396748877), ('ts', 0.0034801925232418743), ('bf', 0.0034792326541451144), ('e', 0.003375384326787803), ('idx', 0.0028816976243628174)]
                glyphs.add(glyph);
            }
            c = ci.next();
        }
        int numGlyphs = glyphs.size();
// numGlyphs            No	: [('numAltGlyphRefNodes', 0.29169908054680027), ('numGlyphRefNodes', 0.2708517148829775), ('count', 0.09315461685818734), ('srcY', 0.09086523642209196), ('i', 0.06562554131621136), ('idx', 0.05021964352229493), ('end', 0.04749683010726382), ('i1', 0.045445833934157194), ('mark', 0.045433528556336304), ('fromSize', 0.0454331187230996)]
        Glyph[] glyphArray = (Glyph[]) glyphs.toArray(new Glyph[numGlyphs]);
// glyphArray           0	: [('glyphArray', 0.8394388441301455), ('ns', 0.1532605799080898), ('e', 0.06977397110537055), ('charnum', 0.04094569573312147), ('extension', 0.035691785814049776), ('namespaceURI', 0.03080007588506644), ('name', 0.023529634700241487), ('element', 0.02070781104128732), ('root', 0.020484979631127463), ('pt', 0.02047340975610498)]
        return new SVGGVTGlyphVector(this, glyphArray, frc);
    }

    public GVTGlyphVector createGlyphVector(FontRenderContext frc, int[] glyphCodes, CharacterIterator ci) {
// frc                  0	: [('frc', 0.9672660550308835), ('localFRC', 0.003909084497297168), ('g', 0.0013760348521268098), ('i', 0.0008401699625636016), ('e', 0.00047753120904810896), ('ctx', 0.00047378893909340005), ('n', 0.0004337436064534282), ('x', 0.0003894814383361368), ('s', 0.00036545225114348247), ('t', 0.0002323312806795048)]
// glyphCodes           0	: [('glyphCodes', 0.9377046006628282), ('charMap', 0.04940269198184111), ('codeReturn', 0.02458032463453797), ('props', 0.019461138024192373), ('yPoints', 0.01852650031879942), ('xOff', 0.018437483596716594), ('yOff', 0.01843690119729309), ('xTile', 0.01843597367228529), ('yTile', 0.01843597367228529), ('orderXY', 0.013017304945072077)]
// ci                   0	: [('ci', 0.9537059557869716), ('iter', 0.009264142881513303), ('ctx', 6.115973057198469e-05), ('node', 2.796351336182326e-05), ('ret', 1.9011966810675116e-05), ('uri', 1.4568548522635315e-05), ('chunk', 9.86629033432135e-06), ('ps', 8.572090832950533e-06), ('p1', 6.048401805277442e-06), ('filteredElement', 5.811131896692792e-06)]
        int nGlyphs = glyphCodes.length;
// nGlyphs              No	: [('nSlots', 0.5218323931677016), ('value', 0.08745689568283681), ('i', 0.056925143410687926), ('len', 0.05017477076537077), ('idx', 0.04691836775640532), ('numChars', 0.0327512923923765), ('hash', 0.028890989385079634), ('n', 0.025064424763228264), ('sp', 0.02241532266755998), ('s', 0.022195524386575665)]
        StringBuffer workBuff = new StringBuffer(nGlyphs);
// workBuff             2	: [('str', 0.9140901831699331), ('sb', 0.44297552500446224), ('workBuff', 0.37839904316425027), ('value', 0.01391147430269288), ('result', 0.007928424815645532), ('key', 0.007364842989490631), ('buf', 0.007348578946330606), ('filterAttrBuf', 0.00679617056096734), ('c', 0.006410422825488604), ('g', 0.005594552662627155)]
        for (int glyphCode : glyphCodes) {
// glyphCode            No	: [('i', 0.7300861205381082), ('y', 0.06051202439076842), ('j', 0.05278128061857465), ('glyphCode1', 0.026350360370853032), ('glyphCode2', 0.026349928967445983), ('k', 0.01834875474028782), ('m', 0.012460245316303284), ('x', 0.011595299975904822), ('g', 0.008470785878808534), ('n', 0.008138121924795613)]
            workBuff.append(glyphUnicodes[glyphCode]);
        }
        StringCharacterIterator sci = new StringCharacterIterator(workBuff.toString());
// sci                  0	: [('sci', 0.39249898629506697), ('str', 0.22585023939721996), ('fontStyleStrings', 0.07986418942985012), ('font', 0.05919821073458679), ('fontFamily', 0.05917143893220909), ('ci', 0.029587947141232664), ('glyphCodes', 0.0295872116895123), ('chars', 0.029582808275867813), ('ctx', 0.0009785636675295312), ('y', 0.000714801120195915)]
        return createGlyphVector(frc, sci);
    }

    public GVTGlyphVector createGlyphVector(FontRenderContext frc, String str) {
// frc                  0	: [('frc', 0.9672660550308835), ('localFRC', 0.003909084497297168), ('g', 0.0013760348521268098), ('i', 0.0008401699625636016), ('e', 0.00047753120904810896), ('ctx', 0.00047378893909340005), ('n', 0.0004337436064534282), ('x', 0.0003894814383361368), ('s', 0.00036545225114348247), ('t', 0.0002323312806795048)]
// str                  0	: [('str', 0.8759230893710885), ('workBuff', 0.18281413734631016), ('ln', 0.005021990820734289), ('ns', 0.0040200196014584435), ('value', 0.0034703893155818323), ('s', 0.003401740118788032), ('prefix', 0.002820868259720942), ('oldv', 0.0024772091132937266), ('uri', 0.002442399123370017), ('name', 0.0023562002371159906)]
        StringCharacterIterator sci = new StringCharacterIterator(str);
// sci                  0	: [('sci', 0.9522074442014776), ('str', 0.22585021782704962), ('fontStyleStrings', 0.07986418942985012), ('font', 0.05919821073458679), ('fontFamily', 0.05917143893220909), ('ci', 0.029587947141232664), ('glyphCodes', 0.0295872116895123), ('chars', 0.029582808275867813), ('y', 0.000714801120195915), ('e', 0.00047116330401316477)]
        return createGlyphVector(frc, sci);
    }

    public GVTFont deriveFont(float size) {
// size                 0	: [('size', 0.8760637658937185), ('x', 0.013115826093914332), ('y', 0.004541839003317543), ('x1', 0.00408013323883817), ('f', 0.0029784871270717083), ('offset', 0.0028343553003394137), ('v', 0.0028115009267925487), ('x2', 0.002375704836405958), ('rx', 0.0017560825141210493), ('i', 0.0016803399251272032)]
        return new SVGGVTFont(size, fontFace, glyphUnicodes, glyphNames, glyphLangs, glyphOrientations, glyphForms, ctx, glyphElements, missingGlyphElement, hkernElements, vkernElements, textElement);
    }

    public String getFamilyName() {
        return fontFace.getFamilyName();
    }

    protected GVTLineMetrics getLineMetrics(int beginIndex, int limit) {
// beginIndex           0	: [('beginIndex', 0.7501004415433051), ('i', 0.24091046157034474), ('x', 0.03561629276545434), ('y', 0.031235348015275992), ('index', 0.023302598554667965), ('j', 0.02002156102514255), ('glyphIndex', 0.015700295649726255), ('idx', 0.01431224192220356), ('srcIndex', 0.010340135932267428), ('charnum', 0.008191633638053841)]
// limit                0	: [('limit', 0.6158306513494988), ('fontHeight', 0.37501929356810915), ('endIndex', 0.13666116190126124), ('y', 0.013697713381745353), ('i', 0.013048548870277827), ('width', 0.007622300224546147), ('x', 0.007064248707534085), ('height', 0.006368478028162675), ('idx', 0.00499194210736196), ('index', 0.004155773757453464)]
        if (lineMetrics != null)
            return lineMetrics;
        float fontHeight = fontFace.getUnitsPerEm();
// fontHeight           0	: [('fontHeight', 0.7345004673180145), ('limit', 0.5000662870751093), ('strikethroughPosition', 0.4750382132005033), ('fontFace', 0.2031688487324638), ('strikethroughPos', 0.15834856790213805), ('x', 0.04850601516323601), ('y', 0.045733577921756335), ('src', 0.01712316717986448), ('t', 0.016779597708874872), ('w', 0.013914277382677944)]
        float scale = fontSize / fontHeight;
// scale                1	: [('scaleFactor', 0.7651464344934182), ('scale', 0.6670540525491131), ('norm', 0.058056681496992175), ('y', 0.04958140456866645), ('delta', 0.04829098145375897), ('workTileWidth', 0.04777352658405175), ('scx', 0.04312673757371782), ('low', 0.04176317264683747), ('x', 0.039692815741767536), ('scy', 0.03670011387802145)]
        float ascent = fontFace.getAscent() * scale;
// ascent               0	: [('ascent', 0.5419055923645972), ('twoThirdSurfaceScaleX', 0.08825119425516838), ('twoThirdSurfaceScaleY', 0.08825119425516838), ('srcPix', 0.07948602044586163), ('b', 0.06647921244046236), ('x', 0.04850046305511202), ('sy', 0.046296199925724854), ('y', 0.045729910887544536), ('a', 0.04448198714411201), ('halfSurfaceScaleX', 0.04421893619065225)]
        float descent = fontFace.getDescent() * scale;
// descent              0	: [('descent', 0.625061353844789), ('ulOffset', 0.27092083077489504), ('ulThickness', 0.27092083077489504), ('olOffset', 0.27092080920454603), ('stThickness', 0.020920830774895097), ('olThickness', 0.02092078763419706), ('i', 0.014091019590222256), ('x', 0.006062556713384173), ('pt5', 0.005773338224102964), ('y', 0.005716237703158466)]
        float[] baselineOffsets = new float[3];
// baselineOffsets      0	: [('baselineOffsets', 0.750012036697693), ('i', 0.2253156807486672), ('vb', 0.15457521446467123), ('matrix', 0.15182397471715678), ('rgbTrans', 0.10073488798870094), ('bkgdRGB', 0.10073488798870094), ('n', 0.08001238570980437), ('pd', 0.05270972684870497), ('ret', 0.051433316722662416), ('result', 0.050769919689791505)]
        baselineOffsets[Font.ROMAN_BASELINE] = 0;
        baselineOffsets[Font.CENTER_BASELINE] = (ascent + descent) / 2 - ascent;
        baselineOffsets[Font.HANGING_BASELINE] = -ascent;
        float stOffset = fontFace.getStrikethroughPosition() * -scale;
// stOffset             0	: [('stOffset', 0.510011260147976), ('limit', 0.10006256595573916), ('x', 0.048500449033170204), ('y', 0.045729896994244394), ('t', 0.016778914083587258), ('w', 0.013913633276124679), ('h', 0.012947917950673843), ('dy', 0.012696927699184313), ('f', 0.011359091966661744), ('dx', 0.011121839249373926)]
        float stThickness = fontFace.getStrikethroughThickness() * scale;
// stThickness          1	: [('olThickness', 0.7709207876338398), ('stThickness', 0.750011260147976), ('descent', 0.021285902560879012), ('ulOffset', 0.020920830774180433), ('ulThickness', 0.020920830774180433), ('olOffset', 0.02092080920401008), ('x', 0.0060625561291462755), ('y', 0.005716237124280549), ('t', 0.002097364260448407), ('e', 0.0018846969145201093)]
        float ulOffset = fontFace.getUnderlinePosition() * scale;
// ulOffset             0	: [('ulOffset', 0.7500112603585832), ('descent', 0.271285902560879), ('ulThickness', 0.2709208307741804), ('olOffset', 0.27092080920401007), ('stThickness', 0.02092080920401008), ('olThickness', 0.020920787633839727), ('x', 0.0060625561291462755), ('y', 0.005716237124280549), ('t', 0.002097364260448407), ('e', 0.0018847406155242598)]
        float ulThickness = fontFace.getUnderlineThickness() * scale;
// ulThickness          0	: [('ulThickness', 0.7500112603585832), ('descent', 0.271285902560879), ('ulOffset', 0.27092080920401007), ('olOffset', 0.27092080920401007), ('stThickness', 0.02092080920401008), ('olThickness', 0.020920787633839727), ('x', 0.0060625561291462755), ('y', 0.005716237124280549), ('t', 0.002097364260448407), ('e', 0.0018847406155242598)]
        float olOffset = fontFace.getOverlinePosition() * -scale;
// olOffset             0	: [('olOffset', 0.7500075459468247), ('descent', 0.271285902560879), ('ulOffset', 0.27092080920401007), ('ulThickness', 0.27092080920401007), ('stThickness', 0.020920830774180433), ('olThickness', 0.020920787633839727), ('x', 0.0060625561291462755), ('y', 0.005716237124280549), ('t', 0.002097364260448407), ('e', 0.0018847406155242598)]
        float olThickness = fontFace.getOverlineThickness() * scale;
// olThickness          No	: [('stThickness', 0.7709208307741804), ('descent', 0.021285902560879012), ('ulOffset', 0.020920830774180433), ('ulThickness', 0.02092080920401008), ('olOffset', 0.020920787633839727), ('y', 0.011437416030361981), ('e', 0.007538962462097039), ('ctx', 0.007059311352781905), ('i', 0.00673045158770859), ('x', 0.0060625561291462755)]
        lineMetrics = new GVTLineMetrics(ascent, Font.ROMAN_BASELINE, baselineOffsets, descent, fontHeight, fontHeight, limit - beginIndex, stOffset, stThickness, ulOffset, ulThickness, olOffset, olThickness);
        return lineMetrics;
    }

    public GVTLineMetrics getLineMetrics(char[] chars, int beginIndex, int limit, FontRenderContext frc) {
// chars                0	: [('chars', 0.5705869157550246), ('text', 0.08544729618865515), ('ch', 0.08532197800507622), ('cbuf', 0.05684886682430034), ('data', 0.028159061624731505), ('buffer', 0.017108306353405014), ('t', 0.010575376198204641), ('buf', 0.010444211679843558), ('cbuff', 0.006818842262902825), ('args', 0.0038084004934287726)]
// beginIndex           0	: [('beginIndex', 0.8759620053332625), ('sci', 0.06744002166357238), ('aci', 0.013220381137855322), ('y', 0.006848760181992624), ('str', 0.006606630505122241), ('i', 0.006523713324354609), ('width', 0.0038111038967530487), ('x', 0.0035320457873829997), ('ci', 0.003296794726423932), ('chars', 0.003294486732131925)]
// limit                0	: [('limit', 0.835953120313653), ('endIndex', 0.16833066660931162), ('y', 0.006852739884411042), ('i', 0.0065244457525009), ('stOffset', 0.005212098596089136), ('width', 0.003813377237978901), ('x', 0.0035338946331742384), ('height', 0.0031861520579568557), ('idx', 0.0024971988281085513), ('index', 0.0020788576771113235)]
// frc                  0	: [('frc', 0.967266076574433), ('localFRC', 0.0009932155057552497), ('ctx', 6.115973057198469e-05), ('node', 2.796351336182326e-05), ('ret', 1.9011966810675116e-05), ('uri', 1.4568548522635315e-05), ('chunk', 9.86629033432135e-06), ('ps', 8.572090832950533e-06), ('p1', 6.048401805277442e-06), ('filteredElement', 5.811131896692792e-06)]
        return getLineMetrics(beginIndex, limit);
    }

    public GVTLineMetrics getLineMetrics(CharacterIterator ci, int beginIndex, int limit, FontRenderContext frc) {
// ci                   0	: [('ci', 0.9172476224536383), ('iter', 0.0405141428815133), ('ctx', 6.115973057198469e-05), ('node', 2.796351336182326e-05), ('ret', 1.9011966810675116e-05), ('uri', 1.4568548522635315e-05), ('chunk', 9.86629033432135e-06), ('ps', 8.572090832950533e-06), ('p1', 6.048401805277442e-06), ('filteredElement', 5.811131896692792e-06)]
// beginIndex           0	: [('beginIndex', 0.8759620053332625), ('sci', 0.06744002172018652), ('aci', 0.013220381777595235), ('y', 0.006848760181992624), ('str', 0.00660663081083866), ('i', 0.006523713324354609), ('width', 0.0038111038967530487), ('x', 0.0035320457873829997), ('ci', 0.003296773224190561), ('chars', 0.0032945083475935987)]
// limit                0	: [('limit', 0.835953120313653), ('endIndex', 0.16833066660931162), ('y', 0.006852739884411042), ('i', 0.0065244457525009), ('stOffset', 0.005212098596089136), ('width', 0.003813377237978901), ('x', 0.0035338946331742384), ('height', 0.0031861520579568557), ('idx', 0.0024971988281085513), ('index', 0.0020788576771113235)]
// frc                  0	: [('frc', 0.967266076574433), ('localFRC', 0.0009932155057552497), ('ctx', 6.115973057198469e-05), ('node', 2.796351336182326e-05), ('ret', 1.9011966810675116e-05), ('uri', 1.4568548522635315e-05), ('chunk', 9.86629033432135e-06), ('ps', 8.572090832950533e-06), ('p1', 6.048401805277442e-06), ('filteredElement', 5.811131896692792e-06)]
        return getLineMetrics(beginIndex, limit);
    }

    public GVTLineMetrics getLineMetrics(String str, FontRenderContext frc) {
// str                  0	: [('str', 0.8760599327802616), ('workBuff', 0.18281413735411572), ('prefix', 0.01456714163661295), ('ps', 0.013249113918413654), ('ctx', 0.010078758454918988), ('w', 0.007657138324772179), ('tmp', 0.006632862515101493), ('name', 0.006025296457443052), ('s', 0.005274945412939824), ('t', 0.005234941104370511)]
// frc                  0	: [('frc', 0.9672660550308835), ('offset', 0.5635829827435956), ('maxLength', 0.18766577393707304), ('s', 0.007827367142858487), ('e', 0.007360142258954873), ('src', 0.0068467437322405865), ('evt', 0.005401982839542254), ('wr', 0.005233714499690783), ('at', 0.0038309713395967048), ('firstChar', 0.0033635530121966187)]
        StringCharacterIterator sci = new StringCharacterIterator(str);
// sci                  0	: [('sci', 0.9522074442014776), ('beginIndex', 0.08991842618013536), ('aci', 0.013220381137855322), ('str', 0.006606630505122241), ('ci', 0.003296794726423932), ('chars', 0.0032945083023022776), ('i', 0.0008401620818737246), ('e', 0.0004775260571603301), ('ctx', 0.00047378338524514614), ('n', 0.00043373886218753954)]
        return getLineMetrics(sci, 0, str.length(), frc);
    }

    public GVTLineMetrics getLineMetrics(String str, int beginIndex, int limit, FontRenderContext frc) {
// str                  0	: [('str', 0.8760599543177361), ('workBuff', 0.18281413734631016), ('prefix', 0.014567141414352417), ('name', 0.00602529632094221), ('s', 0.005274944999863977), ('ns', 0.004680661121357834), ('namespaceURI', 0.003607330685505716), ('uri', 0.0031008096159259365), ('key', 0.002472306515750535), ('message', 0.002041725348313338)]
// beginIndex           0	: [('beginIndex', 0.35096200533326255), ('start', 0.30043249593241167), ('x', 0.17853204578738302), ('off', 0.17700528532431176), ('fromIndex', 0.17509724817729916), ('y', 0.006848760181992624), ('i', 0.006523713324354609), ('width', 0.0038111038967530487), ('height', 0.0031842074787853205), ('idx', 0.0024959098860809466)]
// limit                0	: [('limit', 0.7079160109441973), ('endIndex', 0.16833066660931162), ('stOffset', 0.020848394700285756), ('y', 0.006852739884411042), ('i', 0.0065244457525009), ('width', 0.003813377237978901), ('x', 0.0035338946331742384), ('height', 0.0031861520579568557), ('idx', 0.0024971988281085513), ('index', 0.0020788576771113235)]
// frc                  0	: [('frc', 0.9672660550308835), ('localFRC', 0.0009932155073632174), ('y', 0.0007148758879396075), ('e', 0.00047120700628588643), ('ctx', 0.000441229022579279), ('i', 0.00042066286035246725), ('w', 0.000314258747107154), ('s', 0.0002830419865950356), ('x', 0.00024202440084676537), ('r', 0.0001907111851435668)]
        StringCharacterIterator sci = new StringCharacterIterator(str);
// sci                  0	: [('sci', 0.9522074442014776), ('beginIndex', 0.08991842618013536), ('aci', 0.013220381137855322), ('str', 0.006606630505122241), ('ci', 0.003296794726423932), ('chars', 0.0032945083023022776), ('i', 0.0008401620818737246), ('e', 0.0004775260571603301), ('ctx', 0.00047378338524514614), ('n', 0.00043373886218753954)]
        return getLineMetrics(sci, beginIndex, limit, frc);
    }

    public float getSize() {
        return fontSize;
    }

    public String toString() {
        return fontFace.getFamilyName() + " " + fontFace.getFontWeight() + " " + fontFace.getFontStyle();
    }
}
