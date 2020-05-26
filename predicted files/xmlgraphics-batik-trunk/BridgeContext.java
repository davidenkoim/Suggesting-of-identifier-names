// Type of training: selfTestingIdentifier
// Path to file: C:\Users\Igor\IdeaProjects\SLP-Core-0.3\java-projects\xmlgraphics-batik-trunk\batik-bridge\src\main\java\org\apache\batik\bridge\BridgeContext.java
// Number of identifiers: 244	Accuracy: 64.75%	MRR: 72.92%
// True 	Rank of true in predictions : [(predicted token, probability of a token), ...]

package org.apache.batik.bridge;

import java.awt.Cursor;
import java.awt.geom.Dimension2D;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import org.apache.batik.anim.dom.AnimatedAttributeListener;
import org.apache.batik.anim.dom.AnimatedLiveAttributeValue;
import org.apache.batik.anim.dom.SVGDOMImplementation;
import org.apache.batik.anim.dom.SVGOMDocument;
import org.apache.batik.anim.dom.SVGOMElement;
import org.apache.batik.anim.dom.SVGStylableElement;
import org.apache.batik.bridge.svg12.SVG12BridgeContext;
import org.apache.batik.bridge.svg12.SVG12BridgeExtension;
import org.apache.batik.css.engine.CSSContext;
import org.apache.batik.css.engine.CSSEngine;
import org.apache.batik.css.engine.CSSEngineEvent;
import org.apache.batik.css.engine.CSSEngineListener;
import org.apache.batik.css.engine.CSSEngineUserAgent;
import org.apache.batik.css.engine.SVGCSSEngine;
import org.apache.batik.css.engine.SystemColorSupport;
import org.apache.batik.css.engine.value.Value;
import org.apache.batik.dom.AbstractNode;
import org.apache.batik.dom.events.NodeEventTarget;
import org.apache.batik.dom.svg.SVGContext;
import org.apache.batik.dom.xbl.XBLManager;
import org.apache.batik.gvt.CompositeGraphicsNode;
import org.apache.batik.gvt.GraphicsNode;
import org.apache.batik.script.Interpreter;
import org.apache.batik.script.InterpreterPool;
import org.apache.batik.util.CSSConstants;
import org.apache.batik.util.CleanerThread;
import org.apache.batik.util.ParsedURL;
import org.apache.batik.util.SVGConstants;
import org.apache.batik.util.Service;
import org.apache.batik.constants.XMLConstants;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.EventTarget;
import org.w3c.dom.events.MouseEvent;
import org.w3c.dom.events.MutationEvent;
import org.w3c.dom.svg.SVGDocument;

public class BridgeContext implements ErrorConstants, CSSContext {

    protected Document document;

    protected boolean isSVG12;

    protected GVTBuilder gvtBuilder;

    protected Map interpreterMap = new HashMap(7);

    private Map fontFamilyMap;

    protected Map viewportMap = new WeakHashMap();

    protected List viewportStack = new LinkedList();

    protected UserAgent userAgent;

    protected Map elementNodeMap;

    protected Map nodeElementMap;

    protected Map namespaceURIMap;

    protected Bridge defaultBridge;

    protected Set reservedNamespaceSet;

    protected Map elementDataMap;

    protected InterpreterPool interpreterPool;

    protected DocumentLoader documentLoader;

    protected Dimension2D documentSize;

    protected TextPainter textPainter;

    public static final int STATIC = 0;

    public static final int INTERACTIVE = 1;

    public static final int DYNAMIC = 2;

    protected int dynamicStatus = STATIC;

    protected UpdateManager updateManager;

    protected XBLManager xblManager;

    protected BridgeContext primaryContext;

    protected HashSet childContexts = new HashSet();

    protected SVGAnimationEngine animationEngine;

    protected int animationLimitingMode;

    protected float animationLimitingAmount;

    private static InterpreterPool sharedPool = new InterpreterPool();

    protected BridgeContext() {
    }

    public final FontFamilyResolver getFontFamilyResolver() {
        return userAgent.getFontFamilyResolver();
    }

    public BridgeContext(UserAgent userAgent) {
// userAgent            0	: [('userAgent', 0.9257278776466457), ('s', 0.09724102469181624), ('name', 0.06901609036098981), ('tile', 0.06745757248976868), ('center', 0.06742815027240338), ('errorCode', 0.056864151588320096), ('prefFileName', 0.0568577667127498), ('l', 0.04509793722859261), ('start', 0.045007082030092904), ('ua', 0.04177947384884013)]
        this(userAgent, sharedPool, new DocumentLoader(userAgent));
    }

    public BridgeContext(UserAgent userAgent, DocumentLoader loader) {
// userAgent            0	: [('userAgent', 0.9257279207601864), ('s', 0.09724101539595949), ('name', 0.06901608703074165), ('tile', 0.06745757199396343), ('center', 0.0674281501509534), ('errorCode', 0.05686415142468206), ('prefFileName', 0.05685776662812788), ('l', 0.045097935320725764), ('start', 0.04500708131899369), ('ua', 0.04177947383061619)]
// loader               0	: [('loader', 0.8921890618485364), ('dl', 0.0437513892871736), ('documentLoader', 0.018751173585470078), ('l', 0.009388598003593162), ('y', 0.0057187080151809904), ('e', 0.00376948123104852), ('ctx', 0.0035296556763909524), ('i', 0.0033652257938542956), ('w', 0.0025139364402340346), ('s', 0.0022642280524589006)]
        this(userAgent, sharedPool, loader);
    }

    public BridgeContext(UserAgent userAgent, InterpreterPool interpreterPool, DocumentLoader documentLoader) {
// userAgent            0	: [('userAgent', 0.9257278345323906), ('e', 0.5065633429676424), ('ua', 0.04177947386706437), ('ns', 0.034489810026146886), ('syntaxName', 0.03368417643352568), ('key', 0.026342812885420182), ('ctx', 0.009894998158200895), ('n', 0.009279900068500553), ('id', 0.008516194248353613), ('namespaceURI', 0.007717830627349409)]
// interpreterPool      0	: [('interpreterPool', 0.9687511304451293), ('i', 0.0010050947063468825), ('e', 0.0009614734185792473), ('n', 0.00081675232751304), ('ctx', 0.0005592250909885492), ('x', 0.0004825478675565961), ('t', 0.0004790948308715721), ('raf', 0.0004678493961180858), ('y', 0.00042692354758861974), ('src', 0.00040494994497024776)]
// documentLoader       0	: [('documentLoader', 0.884376108874959), ('newDocumentLoader', 0.8125042658027872), ('dl', 0.06252088861092947), ('loader', 0.026564104988877128), ('l', 0.009388598003593162), ('i', 0.0010050947063468825), ('e', 0.0009614734185792473), ('n', 0.00081675232751304), ('ctx', 0.0005592250909885492), ('x', 0.0004825478675565961)]
        this.userAgent = userAgent;
        this.viewportMap.put(userAgent, new UserAgentViewport(userAgent));
        this.interpreterPool = interpreterPool;
        this.documentLoader = documentLoader;
    }

    protected void finalize() {
        if (primaryContext != null) {
            dispose();
        }
    }

    public BridgeContext createSubBridgeContext(SVGOMDocument newDoc) {
// newDoc               0	: [('newDoc', 0.8773983642811224), ('doc', 0.7781288119031275), ('refDocument', 0.687501864591785), ('imgDocument', 0.6500040297613935), ('document', 0.35008170417088263), ('wr', 0.18753454488252053), ('model', 0.08188794681172339), ('svgDocument', 0.06872944808509733), ('ctx', 0.014448150618339744), ('impl', 0.01395973709667619)]
        BridgeContext subCtx;
// subCtx               No	: [('ctx', 0.34492990737265317), ('resCtx', 0.22955801284130659), ('ret', 0.18777292988693825), ('bi', 0.18759629251376148), ('n', 0.09461819702225953), ('c', 0.09443229536919753), ('eng', 0.09384611993524516), ('ras', 0.09383149358887509), ('canvas', 0.0937925762274108), ('m', 0.09185494879301949)]
        CSSEngine eng = newDoc.getCSSEngine();
// eng                  0	: [('eng', 0.65074404977218), ('cssEngine', 0.06965187576483005), ('engine', 0.06513938799142348), ('e', 0.037765335862853806), ('s', 0.027781862020481617), ('n', 0.021213703806855295), ('result', 0.0187891141249795), ('ln', 0.017647909132191206), ('aci', 0.012672391157619714), ('token', 0.01232736028129498)]
        if (eng != null) {
            subCtx = (BridgeContext) newDoc.getCSSEngine().getCSSContext();
            return subCtx;
        }
        subCtx = createBridgeContext(newDoc);
        subCtx.primaryContext = primaryContext != null ? primaryContext : this;
        subCtx.primaryContext.childContexts.add(new WeakReference(subCtx));
        subCtx.dynamicStatus = dynamicStatus;
        subCtx.setGVTBuilder(getGVTBuilder());
        subCtx.setTextPainter(getTextPainter());
        subCtx.setDocument(newDoc);
        subCtx.initializeDocument(newDoc);
        if (isInteractive())
            subCtx.addUIEventListeners(newDoc);
        return subCtx;
    }

    public BridgeContext createBridgeContext(SVGOMDocument doc) {
// doc                  0	: [('doc', 0.5008699281319503), ('newDoc', 0.032987405182556634), ('n', 0.025913480488015447), ('document', 0.013479756891856712), ('d', 0.010846804484948559), ('node', 0.010466639409384308), ('i', 0.008511031842383561), ('e', 0.008180047217403801), ('c', 0.005397944404008198), ('sd', 0.005390564487941107)]
        if (doc.isSVG12()) {
            return new SVG12BridgeContext(getUserAgent(), getDocumentLoader());
        }
        return new BridgeContext(getUserAgent(), getDocumentLoader());
    }

    protected void initializeDocument(Document document) {
// document             6	: [('doc', 0.5355170116230493), ('paintElement', 0.20144050021996193), ('e', 0.12252392561574603), ('filterElement', 0.10073125455841463), ('subCtx', 0.10072133375321578), ('patternElement', 0.10072001649966093), ('document', 0.0754353769509027), ('domFactory', 0.05875197183204244), ('n', 0.04348958324677866), ('refElement', 0.034771589518996913)]
        SVGOMDocument doc = (SVGOMDocument) document;
// doc                  0	: [('doc', 0.6225707759264698), ('newDoc', 0.21112781834962863), ('document', 0.05807899198263222), ('d', 0.05676268903091792), ('svgDoc', 0.054034050342776696), ('t', 0.04426288066618349), ('sb', 0.03014328129516832), ('ctx', 0.020714948952767633), ('g', 0.01826816082422934), ('result', 0.017065067614139486)]
        CSSEngine eng = doc.getCSSEngine();
// eng                  1	: [('result', 0.9063497129470467), ('eng', 0.6507432741565746), ('is', 0.275348710791012), ('ret', 0.2724656301055643), ('p', 0.14149879037213373), ('cssEngine', 0.06965187597995463), ('report', 0.06785132890012374), ('engine', 0.06513938913708696), ('d', 0.044717935626710746), ('mi', 0.04457571438508867)]
        if (eng == null) {
            SVGDOMImplementation impl;
// impl                 0	: [('impl', 0.5250200875911671), ('doc', 0.279181820668293), ('parserClassName', 0.2500199370124019), ('newDoc', 0.1111779360491705), ('ctx', 0.05779245990876472), ('i', 0.01990739990383722), ('g', 0.005005565888516821), ('e', 0.003845899890465279), ('n', 0.003708523782136521), ('t', 0.002903172049241106)]
            impl = (SVGDOMImplementation) doc.getImplementation();
            eng = impl.createCSSEngine(doc, this);
            eng.setCSSEngineUserAgent(new CSSEngineUserAgentWrapper(userAgent));
            doc.setCSSEngine(eng);
            eng.setMedia(userAgent.getMedia());
            String uri = userAgent.getUserStyleSheetURI();
// uri                  1	: [('s', 0.343972040187264), ('uri', 0.1895873686714213), ('base', 0.18846128579855448), ('doc', 0.15851651139047443), ('docURL', 0.15248441584099565), ('baseURI', 0.08369947842682449), ('url', 0.03713291653586997), ('val', 0.03223853936382251), ('href', 0.029656502634285032), ('dir', 0.028935933885501298)]
            if (uri != null) {
                try {
                    ParsedURL url = new ParsedURL(uri);
// url                  No	: [('purl', 0.4666762473686915), ('burl', 0.44681565533402906), ('is', 0.2664902232584197), ('text', 0.10671436718669618), ('ss', 0.06253873221876569), ('uri', 0.053584422327187735), ('source', 0.053433430490184015), ('ir', 0.015655362940409236), ('docPURL', 0.009605222887584449), ('durl', 0.009508578138767326)]
                    eng.setUserAgentStyleSheet(eng.parseStyleSheet(url, "all"));
                } catch (Exception e) {
// e                    0	: [('e', 0.6624917480000742), ('ex', 0.28332814061766887), ('de', 0.10109625109597399), ('se', 0.022933801906127593), ('te', 0.0076786890772948336), ('be', 0.007371870040581034), ('exc', 0.0030875480998947746), ('message', 0.00247445142287432), ('i', 0.0008401699625636016), ('exception', 0.0007789725468740477)]
                    userAgent.displayError(e);
                }
            }
            eng.setAlternateStyleSheet(userAgent.getAlternateStyleSheet());
        }
    }

    public CSSEngine getCSSEngineForElement(Element e) {
// e                    0	: [('e', 0.7954806869002966), ('paintElement', 0.20144050021996193), ('document', 0.13580009868328125), ('filterElement', 0.10073125455841463), ('subCtx', 0.10072133375321578), ('patternElement', 0.10072001649966093), ('doc', 0.0729437054676726), ('n', 0.04348958324677866), ('refElement', 0.034771589518996913), ('altGlyphElement', 0.03477089853696396)]
        SVGOMDocument doc = (SVGOMDocument) e.getOwnerDocument();
// doc                  0	: [('doc', 0.795184733318929), ('report', 0.030232244651434117), ('document', 0.029039582080107033), ('d', 0.02838134397838), ('svgDoc', 0.0270170251370839), ('n', 0.02008329104233308), ('ret', 0.0176317641395032), ('result', 0.016088356807445563), ('v', 0.015773011864748675), ('ctx', 0.010726342329702031)]
        return doc.getCSSEngine();
    }

    public void setTextPainter(TextPainter textPainter) {
// textPainter          0	: [('textPainter', 0.9687513245766626), ('i', 0.0005025473531734412), ('e', 0.00048073670928962367), ('n', 0.00040837616375652), ('ctx', 0.0002796125454942746), ('x', 0.00024127393377829803), ('t', 0.00023954741543578606), ('raf', 0.0002339246980590429), ('y', 0.00021346177379430987), ('src', 0.00020247497248512388)]
        this.textPainter = textPainter;
    }

    public TextPainter getTextPainter() {
        return textPainter;
    }

    public Document getDocument() {
        return document;
    }

    protected void setDocument(Document document) {
// document             5	: [('doc', 0.8838792968415308), ('imgDocument', 0.10220530393496796), ('bc', 0.08235340017749654), ('e', 0.028499804362778436), ('ctx', 0.024156619245540457), ('document', 0.021924072910403547), ('node', 0.019939217172284508), ('resCtx', 0.019852860914304344), ('n', 0.01717379510345268), ('domFactory', 0.01468799301661355)]
        if (this.document != document) {
            fontFamilyMap = null;
        }
        this.document = document;
        this.isSVG12 = ((SVGOMDocument) document).isSVG12();
        registerSVGBridges();
    }

    public Map getFontFamilyMap() {
        if (fontFamilyMap == null) {
            fontFamilyMap = new HashMap();
        }
        return fontFamilyMap;
    }

    protected void setFontFamilyMap(Map fontFamilyMap) {
// fontFamilyMap        No	: [('ctx', 0.5022369003639542), ('attrMap', 0.11199043105028596), ('hints', 0.09846038313354204), ('map', 0.046889550541223665), ('filterMap', 0.03999240364202104), ('props', 0.03981865982605944), ('syntaxFontMap', 0.03851333153953106), ('attrs', 0.03034036434684237), ('groupContext', 0.025676958307312703), ('m', 0.020396667389712644)]
        this.fontFamilyMap = fontFamilyMap;
    }

    public void setElementData(Node n, Object data) {
// n                    0	: [('n', 0.4031456025157104), ('ns', 0.06897961362620995), ('syntaxName', 0.06736835197317192), ('node', 0.05529610703205493), ('key', 0.052685621060511736), ('parent', 0.02589216288090758), ('id', 0.017032385638680925), ('child', 0.016632363866494033), ('namespaceURI', 0.015435654668348273), ('fontKeyName', 0.014975388274269055)]
// data                 7	: [('evtListener', 0.392865314000208), ('gn', 0.12508418473694555), ('node', 0.09851501431900957), ('element', 0.09838024335463029), ('event', 0.07290088406211291), ('img', 0.0536620628546341), ('value', 0.045604233310018324), ('data', 0.040616203337336156), ('o2', 0.035211298282568256), ('o', 0.03326298033641119)]
        if (elementDataMap == null) {
            elementDataMap = new WeakHashMap();
        }
        elementDataMap.put(n, new SoftReference(data));
    }

    public Object getElementData(Node n) {
// n                    0	: [('n', 0.40314543527144847), ('i', 0.16122715073214788), ('l', 0.10474225200069047), ('node', 0.05529610882157382), ('mi', 0.05224901119838541), ('item', 0.03953158432550617), ('namespaceURI', 0.03865232477000724), ('key', 0.03642178892717276), ('ns', 0.0360103311679671), ('name', 0.03429036081523565)]
        if (elementDataMap == null)
            return null;
        Object o = elementDataMap.get(n);
// o                    7	: [('aci', 0.781297032595768), ('rec', 0.33970290348238386), ('data', 0.25592952501395805), ('baseline', 0.2519562554264323), ('result', 0.20159928071264419), ('attrs', 0.15626365545471374), ('t', 0.1015482217687691), ('o', 0.09574027786308062), ('l', 0.0625579832328264), ('p', 0.05973417127716158)]
        if (o == null)
            return null;
        SoftReference sr = (SoftReference) o;
// sr                   0	: [('sr', 0.8068315905088846), ('ip', 0.2555717207748065), ('e', 0.04636739128160294), ('rlms', 0.033392789188367515), ('map', 0.016741476029734942), ('at', 0.011823722018476342), ('wr', 0.011688586989560571), ('cl', 0.011158841699987387), ('aci', 0.006113409258869587), ('f', 0.005947744228505403)]
        o = sr.get();
        if (o == null) {
            elementDataMap.remove(n);
        }
        return o;
    }

    public UserAgent getUserAgent() {
        return userAgent;
    }

    protected void setUserAgent(UserAgent userAgent) {
// userAgent            0	: [('userAgent', 0.8942429073512638), ('ua', 0.33423579064492953), ('u', 0.010021467979158066), ('ctx', 0.009894997160878891), ('i', 0.0005025473531734412), ('e', 0.00048073670928962367), ('n', 0.00040837616375652), ('x', 0.00024127393377829803), ('t', 0.00023954741543578606), ('raf', 0.0002339246980590429)]
        this.userAgent = userAgent;
    }

    public GVTBuilder getGVTBuilder() {
        return gvtBuilder;
    }

    protected void setGVTBuilder(GVTBuilder gvtBuilder) {
// gvtBuilder           No	: [('builder', 0.4359603889738392), ('i', 0.00804075765077506), ('e', 0.007691787348633978), ('n', 0.006534018620104319), ('ctx', 0.004473800727908393), ('x', 0.0038603829404527685), ('t', 0.0038327586469725774), ('raf', 0.0037427951689446865), ('y', 0.0034153883807089584), ('src', 0.0032395995597619817)]
        this.gvtBuilder = gvtBuilder;
    }

    public InterpreterPool getInterpreterPool() {
        return interpreterPool;
    }

    public FocusManager getFocusManager() {
        return focusManager;
    }

    public CursorManager getCursorManager() {
        return cursorManager;
    }

    protected void setInterpreterPool(InterpreterPool interpreterPool) {
// interpreterPool      0	: [('interpreterPool', 0.8750045246448315), ('i', 0.0010050947063468825), ('ctx', 0.0009785636675295312), ('e', 0.0009614734185792473), ('n', 0.00081675232751304), ('x', 0.0004825478675565961), ('t', 0.0004790948308715721), ('raf', 0.0004678493961180858), ('node', 0.0004474197927676657), ('y', 0.00042692354758861974)]
        this.interpreterPool = interpreterPool;
    }

    public Interpreter getInterpreter(String language) {
// language             1	: [('lang', 0.8755470910843192), ('language', 0.5403505375583088), ('i', 0.16122719066262295), ('args', 0.13070052384917447), ('uri', 0.09015218186364996), ('namespaceURI', 0.09006398806887438), ('e', 0.08163016040395689), ('id', 0.053136605435413466), ('primitiveType', 0.043546833361651986), ('f', 0.03735314092748456)]
        if (document == null) {
            throw new RuntimeException("Unknown document");
        }
        Interpreter interpreter = (Interpreter) interpreterMap.get(language);
// interpreter          1	: [('factory', 0.9062597654383835), ('interpreter', 0.7718850440014154), ('h', 0.1883927855932317), ('result', 0.16661170988351287), ('at', 0.12115462147009365), ('sb', 0.06687628538846067), ('interp', 0.05938564799119928), ('top', 0.044980426176837125), ('ret', 0.04389234856464976), ('elt', 0.03466148506018803)]
        if (interpreter == null) {
            try {
                interpreter = interpreterPool.createInterpreter(document, language, null);
                String[] mimeTypes = interpreter.getMimeTypes();
// mimeTypes            0	: [('mimeTypes', 0.9375327488089119), ('variationURLs', 0.0628661020472635), ('ret', 0.06109571298170303), ('argsArray', 0.060289815780918), ('modifiers', 0.05756985245611606), ('urls', 0.05755790480597054), ('dirNfile', 0.04898205132518043), ('names', 0.03928309414514165), ('textArray', 0.03837227021201611), ('ztextArray', 0.03837227021201611)]
                for (String mimeType : mimeTypes) {
// mimeType             2	: [('variationURL', 0.1770043094362935), ('modifier', 0.13273229208913756), ('mimeType', 0.09054947161679039), ('fontFamily', 0.08853845817828407), ('ns', 0.06897961362620995), ('syntaxName', 0.06736835197317192), ('key', 0.052685621060511736), ('propertyName', 0.044721333953298435), ('source', 0.044672660736948845), ('ext', 0.044298502789395656)]
                    interpreterMap.put(mimeType, interpreter);
                }
            } catch (Exception e) {
// e                    0	: [('e', 0.6193078077573104), ('ex', 0.3029359837549238), ('de', 0.10109625109597399), ('exc', 0.03086532587767255), ('se', 0.022933801906127593), ('be', 0.007371870040581034), ('te', 0.003477008405025926), ('message', 0.00247445142287432), ('i', 0.0008401699625636016), ('exception', 0.0007789725468740477)]
                if (userAgent != null) {
                    userAgent.displayError(e);
                    return null;
                }
            }
        }
        if (interpreter == null) {
            if (userAgent != null) {
                userAgent.displayError(new Exception("Unknown language: " + language));
            }
        }
        return interpreter;
    }

    public DocumentLoader getDocumentLoader() {
        return documentLoader;
    }

    protected void setDocumentLoader(DocumentLoader newDocumentLoader) {
// newDocumentLoader    No	: [('documentLoader', 0.812506831539745), ('dl', 0.20002222859477767), ('loader', 0.17502567982203407), ('l', 0.025217568057490602), ('i', 0.0010050947063468825), ('ctx', 0.0009785636675295312), ('e', 0.0009614734185792473), ('n', 0.00081675232751304), ('x', 0.0004825478675565961), ('t', 0.0004790948308715721)]
        this.documentLoader = newDocumentLoader;
    }

    public Dimension2D getDocumentSize() {
        return documentSize;
    }

    protected void setDocumentSize(Dimension2D d) {
// d                    No	: [('docSize', 0.25001636150844175), ('i', 0.00804075765077506), ('e', 0.007691787348633978), ('n', 0.006534018620104319), ('ctx', 0.004473800727908393), ('x', 0.0038603829404527685), ('t', 0.0038327586469725774), ('raf', 0.0037427951689446865), ('y', 0.0034153883807089584), ('src', 0.0032395995597619817)]
        this.documentSize = d;
    }

    public boolean isDynamic() {
        return (dynamicStatus == DYNAMIC);
    }

    public boolean isInteractive() {
        return (dynamicStatus != STATIC);
    }

    public void setDynamicState(int status) {
// status               No	: [('i', 0.24091046157034474), ('x', 0.03561629276545434), ('y', 0.031235348015275992), ('index', 0.023302598554667965), ('j', 0.02002156102514255), ('glyphIndex', 0.015700295649726255), ('idx', 0.01431224192220356), ('srcIndex', 0.010340135932267428), ('charnum', 0.008191633638053841), ('m', 0.008146297992355759)]
        dynamicStatus = status;
    }

    public void setDynamic(boolean dynamic) {
// dynamic              No	: [('b', 0.06996231427621528), ('v', 0.05742458167771773), ('n', 0.05182696097603089), ('aSecure', 0.029843958457465264), ('node', 0.020933278818768615), ('useSVG11AccessKeys', 0.02015472159113517), ('mirror', 0.017411507227519097), ('i', 0.017022063684767122), ('e', 0.016360094434807602), ('state', 0.014933230902400126)]
        if (dynamic)
            setDynamicState(DYNAMIC);
        else
            setDynamicState(STATIC);
    }

    public void setInteractive(boolean interactive) {
// interactive          No	: [('b', 0.06996231427621528), ('v', 0.05742458167771773), ('n', 0.05182696097603089), ('aSecure', 0.029843958457465264), ('node', 0.020933278818768615), ('useSVG11AccessKeys', 0.02015472159113517), ('mirror', 0.017411507227519097), ('i', 0.017022063684767122), ('e', 0.016360094434807602), ('state', 0.014933230902400126)]
        if (interactive)
            setDynamicState(INTERACTIVE);
        else
            setDynamicState(STATIC);
    }

    public UpdateManager getUpdateManager() {
        return updateManager;
    }

    protected void setUpdateManager(UpdateManager um) {
// um                   0	: [('um', 0.7368594317696082), ('ctx', 0.2522369003639542), ('i', 0.00402037882538753), ('e', 0.0038458936743169894), ('n', 0.00326700931005216), ('x', 0.0019301914702263842), ('t', 0.0019163793234862887), ('raf', 0.0018713975844723433), ('y', 0.001707694190354479), ('src', 0.001619799779880991)]
        updateManager = um;
    }

    protected void setUpdateManager(BridgeContext ctx, UpdateManager um) {
// ctx                  0	: [('ctx', 0.7223229706029998), ('svgDocument', 0.25005578469717055), ('subCtx', 0.004471202714231642), ('bc', 0.003495984608602767), ('c', 0.0032052102671377594), ('sb', 0.003035970682249929), ('g', 0.00279727505563817), ('e', 0.0025294335564015704), ('i', 0.0024324343061249564), ('r', 0.0023559508412326316)]
// um                   0	: [('um', 0.7368596043309711), ('subCtx', 0.16672437274105564), ('i', 0.006721359700508813), ('e', 0.0038202496723848717), ('ctx', 0.0037899663900215594), ('n', 0.0034699488516274257), ('x', 0.0031158515066890945), ('s', 0.0029236180091478598), ('t', 0.0018586502454360381), ('prefix', 0.0017937675910926821)]
        ctx.setUpdateManager(um);
    }

    protected void setXBLManager(BridgeContext ctx, XBLManager xm) {
// ctx                  0	: [('ctx', 0.7223229706029998), ('shadowTree', 0.5000568200653475), ('subCtx', 0.004471202714231642), ('bc', 0.003495984608602767), ('c', 0.0032052102671377594), ('sb', 0.003035970682249929), ('g', 0.00279727505563817), ('e', 0.0025294335564015704), ('i', 0.0024324343061249564), ('r', 0.0023559508412326316)]
// xm                   0	: [('xm', 0.7143014877398993), ('m', 0.16746433123750354), ('i', 0.00402037882538753), ('e', 0.0038458936743169894), ('n', 0.00326700931005216), ('ctx', 0.0022365552412285552), ('x', 0.0019301914702263842), ('t', 0.0019163793234862887), ('raf', 0.0018713975844723433), ('y', 0.001707694190354479)]
        ctx.xblManager = xm;
    }

    public boolean isSVG12() {
        return isSVG12;
    }

    public BridgeContext getPrimaryBridgeContext() {
        if (primaryContext != null) {
            return primaryContext;
        }
        return this;
    }

    public BridgeContext[] getChildContexts() {
        BridgeContext[] res = new BridgeContext[childContexts.size()];
// res                  No	: [('ctxs', 0.25024687527586936), ('subCtxs', 0.25024678899375863), ('len', 0.12154715893447576), ('n', 0.11107854333964402), ('height', 0.06541768967501217), ('ret', 0.05810601308909718), ('h', 0.05569919474066642), ('sb', 0.0504361170066217), ('numGlyphs', 0.048938041402723315), ('report', 0.04286443856360441)]
        Iterator it = childContexts.iterator();
// it                   1	: [('key', 0.8750302188208885), ('it', 0.32259509655769325), ('iter', 0.2269592487341358), ('i', 0.19547688844967578), ('mindi', 0.16587802829143208), ('clIter', 0.03175509869909762), ('j', 0.005991149372645712), ('li', 0.003979111245459113), ('flowRectsIter', 0.0019893551042467266), ('kit', 0.0019892472533949634)]
        for (int i = 0; i < res.length; i++) {
// i                    0	: [('i', 0.8533983854580418), ('y', 0.06051212205741389), ('j', 0.05278132726325402), ('e', 0.02489721305409929), ('k', 0.018348770086789732), ('sp', 0.017621239113460196), ('m', 0.012460260244875123), ('x', 0.011595379710774338), ('index', 0.009666406175388973), ('idx', 0.009200368406079438)]
            WeakReference wr = (WeakReference) it.next();
// wr                   No	: [('gnWRef', 0.7867708669958172), ('newDoc', 0.5000054562653238), ('refDocument', 0.37500372915676916), ('i', 0.0005990931922278899), ('n', 0.0005380707443808663), ('e', 0.0003924665470922135), ('doc', 0.0002796889394562234), ('ctx', 0.0002446409168823828), ('o', 0.00017911791573568638), ('node', 0.00017605473901972988)]
            res[i] = (BridgeContext) wr.get();
        }
        return res;
    }

    public SVGAnimationEngine getAnimationEngine() {
        if (animationEngine == null) {
            animationEngine = new SVGAnimationEngine(document, this);
            setAnimationLimitingMode();
        }
        return animationEngine;
    }

    public URIResolver createURIResolver(SVGDocument doc, DocumentLoader dl) {
// doc                  0	: [('doc', 0.9070561723430283), ('svgDocument', 0.00454693011330956), ('i', 0.0033606798502544065), ('e', 0.0019101248361924358), ('ctx', 0.0018951557563736004), ('svgDoc', 0.0017772163110360163), ('n', 0.0017349744258137129), ('x', 0.0015579257533445473), ('s', 0.0014618090045739299), ('d', 0.0014322190845677389)]
// dl                   0	: [('dl', 0.9093763461468329), ('uri', 0.26291019314506514), ('durl', 0.09565198018060975), ('ctx', 0.07738978371597682), ('root', 0.047919961570152346), ('loader', 0.026564104988877128), ('node', 0.024366395422447448), ('stringWriter', 0.023917768990020252), ('fontFaces', 0.023914486086972397), ('view', 0.023914486086972397)]
        return new URIResolver(doc, dl);
    }

    public Node getReferencedNode(Element e, String uri) {
// e                    0	: [('e', 0.7625920395577002), ('ref', 0.5079961294864539), ('elt', 0.1351964831646335), ('content', 0.11151583269063296), ('doc', 0.10597821605251044), ('filterElement', 0.05466654355887891), ('element', 0.04655325784145335), ('n', 0.04557483509450389), ('o', 0.045215880711931604), ('ctx', 0.045195244284291294)]
// uri                  2	: [('pseudoE', 0.37701227168901624), ('uriStr', 0.15627850806312268), ('uri', 0.109690178493261), ('attr', 0.0958920613164147), ('type', 0.07204107668074633), ('attrName', 0.06476501901876383), ('name', 0.053833263322744886), ('u', 0.05296808554562075), ('id', 0.048570060434287195), ('url', 0.047370038113559214)]
        try {
            SVGDocument document = (SVGDocument) e.getOwnerDocument();
// document             No	: [('doc', 0.43301070188973617), ('svgDoc', 0.33337680308876844), ('ttdoc', 0.04130679677589021), ('d', 0.0249596459270869), ('svgDocument', 0.02491848970115941), ('i', 0.006721376512266962), ('e', 0.0038202558885331622), ('ctx', 0.003790315502002173), ('toValue', 0.0035844231966350414), ('before', 0.003560264405734208)]
            URIResolver ur = createURIResolver(document, documentLoader);
// ur                   5	: [('resolver', 0.9479179696731588), ('ctx', 0.05667400573753265), ('ref', 0.02790640401976136), ('defRef', 0.013900181476268616), ('marker', 0.013897765617189127), ('ur', 0.0034736330795661786), ('i', 0.002010189412693765), ('e', 0.0019229468371584947), ('n', 0.00163350465502608), ('x', 0.0009650957351131922)]
            Node ref = ur.getNode(uri, e);
// ref                  No	: [('refs', 0.5001367251172285), ('n', 0.1872958221073017), ('parent', 0.09912764686532463), ('bridge', 0.07798332431952146), ('result', 0.0698648630692018), ('ret', 0.06970039886941624), ('next', 0.06722809478882977), ('sb', 0.057606094674423784), ('r', 0.05693468147996909), ('gn', 0.04641601093508051)]
            if (ref == null) {
                throw new BridgeException(this, e, ERR_URI_BAD_TARGET, new Object[] { uri });
            } else {
                SVGOMDocument refDoc = (SVGOMDocument) (ref.getNodeType() == Node.DOCUMENT_NODE ? ref : ref.getOwnerDocument());
// refDoc               No	: [('doc', 0.42896316631800907), ('newDoc', 0.1250528743962512), ('document', 0.04472958458777297), ('d', 0.04209680520104198), ('tokens', 0.03939682120027432), ('v', 0.0392480506481952), ('n', 0.036682056579211905), ('svgDoc', 0.036639529165502685), ('ri', 0.034602423333756405), ('result', 0.0341301321751581)]
                if (refDoc != document) {
                    createSubBridgeContext(refDoc);
                }
                return ref;
            }
        } catch (MalformedURLException ex) {
// ex                   1	: [('e', 0.8651059056987452), ('ex', 0.7525460878897058), ('mue', 0.08828238044512937), ('node', 0.028548021654794344), ('shapeNode', 0.015798847205323076), ('img', 0.007537437496651076), ('f', 0.006297273683017683), ('listeners', 0.006285881455205825), ('gn', 0.006279571313288652), ('ctx', 0.005705892214817253)]
            throw new BridgeException(this, e, ex, ERR_URI_MALFORMED, new Object[] { uri });
        } catch (InterruptedIOException ex) {
// ex                   No	: [('iioe', 0.4750010010237499), ('e', 0.45634476577762423), ('ctx', 6.115973057198469e-05), ('node', 2.796351336182326e-05), ('ret', 1.9011966810675116e-05), ('uri', 1.4568548522635315e-05), ('chunk', 9.86629033432135e-06), ('ps', 8.572090832950533e-06), ('p1', 6.048401805277442e-06), ('filteredElement', 5.811131896692792e-06)]
            throw new InterruptedBridgeException();
        } catch (IOException ex) {
// ex                   0	: [('ex', 0.7525460878897058), ('e', 0.4283654177266617), ('ioe', 0.23224480689460106), ('ioEx', 0.04306623403059721), ('ignored', 0.032518282187674365), ('node', 0.028548021654794344), ('io', 0.02612996040680798), ('ioe2', 0.02148506032737621), ('shapeNode', 0.015798847205323076), ('img', 0.007537437496651076)]
            throw new BridgeException(this, e, ex, ERR_URI_IO, new Object[] { uri });
        } catch (SecurityException ex) {
// ex                   0	: [('ex', 0.7525460878897058), ('e', 0.33181324969403564), ('se', 0.317751876997868), ('secEx', 0.17249434859394266), ('se2', 0.03613228045895812), ('node', 0.028548021654794344), ('shapeNode', 0.015798847205323076), ('img', 0.007537437496651076), ('f', 0.006297273683017683), ('listeners', 0.006285881455205825)]
            throw new BridgeException(this, e, ex, ERR_URI_UNSECURE, new Object[] { uri });
        }
    }

    public Element getReferencedElement(Element e, String uri) {
// e                    0	: [('e', 0.7661689268386), ('imp', 0.12505728329544083), ('elt', 0.07547147477387528), ('filterElement', 0.054672704917456336), ('element', 0.046559222069489384), ('ns', 0.03831514510655532), ('imageElement', 0.018360560230991495), ('paintElement', 0.010946843966397307), ('charnum', 0.010236423959544274), ('elem', 0.009510125916115669)]
// uri                  1	: [('pseudoE', 0.37701227167079165), ('uri', 0.11069937022080777), ('attr', 0.09589206109182408), ('node', 0.09311517968761845), ('type', 0.0720410761382983), ('attrName', 0.06376076856700372), ('hints', 0.056297267794502885), ('charnum', 0.054232187760702044), ('name', 0.0529570694881886), ('u', 0.05213927643713306)]
        Node ref = getReferencedNode(e, uri);
// ref                  No	: [('n', 0.5233577285349515), ('refs', 0.21466260740550033), ('m', 0.19433563056472347), ('t', 0.14824998754452004), ('ret', 0.14710689579146088), ('next', 0.08697404684397855), ('attr', 0.06619702019685993), ('namespaceURI', 0.060334254548304156), ('child', 0.05194535967469112), ('node', 0.05153922653593833)]
        if (ref != null && ref.getNodeType() != Node.ELEMENT_NODE) {
            throw new BridgeException(this, e, ERR_URI_REFERENCE_A_DOCUMENT, new Object[] { uri });
        }
        return (Element) ref;
    }

    public Viewport getViewport(Element e) {
// e                    0	: [('e', 0.6150590287656506), ('child', 0.5173976293880787), ('fontFaceElement', 0.3072958925752962), ('i', 0.16122724740681887), ('elt', 0.07546447644525918), ('filterElement', 0.05466654355887891), ('n', 0.049787426610042075), ('ang', 0.04762711306219784), ('element', 0.04655325784145335), ('key', 0.03642179834819796)]
        if (viewportStack != null) {
            if (viewportStack.size() == 0) {
                return (Viewport) viewportMap.get(userAgent);
            } else {
                return (Viewport) viewportStack.get(0);
            }
        } else {
            e = SVGUtilities.getParentElement(e);
            while (e != null) {
                Viewport viewport = (Viewport) viewportMap.get(e);
// viewport             0	: [('viewport', 0.25001981277286073), ('bridge', 0.07798332384710281), ('t', 0.07151569373352974), ('n', 0.06981182882057521), ('attr', 0.05719903047338636), ('r', 0.056934676939158296), ('gn', 0.04641600984498574), ('svgGN', 0.0439760221497073), ('textRuns', 0.04261151609919033), ('msg', 0.04260596325926155)]
                if (viewport != null) {
                    return viewport;
                }
                e = SVGUtilities.getParentElement(e);
            }
            return (Viewport) viewportMap.get(userAgent);
        }
    }

    public void openViewport(Element e, Viewport viewport) {
// e                    1	: [('userAgent', 0.5009643015945352), ('e', 0.1819227476011865), ('elt', 0.0754644705991132), ('filterElement', 0.05466653948515861), ('element', 0.04655325393925812), ('ns', 0.03448980681310498), ('syntaxName', 0.03368417598658596), ('key', 0.026342810530255868), ('imageElement', 0.01835661459416222), ('paintElement', 0.010945660202595719)]
// viewport             3	: [('item', 0.750917865368881), ('ll', 0.375851899037296), ('ts', 0.37585010654210643), ('viewport', 0.25001981277286073), ('node', 0.0232787974491916), ('y', 0.016924732128903882), ('hints', 0.014074318396550557), ('charnum', 0.013558048203819003), ('idx', 0.010540480443049937), ('n', 0.009587988194448734)]
        viewportMap.put(e, viewport);
        if (viewportStack == null) {
            viewportStack = new LinkedList();
        }
        viewportStack.add(0, viewport);
    }

    public void removeViewport(Element e) {
// e                    0	: [('e', 0.1819227476011865), ('l', 0.1047422481849568), ('elt', 0.0754644705991132), ('i', 0.07049777662444795), ('filterElement', 0.05466653948515861), ('mi', 0.05224901084917774), ('element', 0.04655325393925812), ('item', 0.03953158256718701), ('namespaceURI', 0.03865231864605202), ('node', 0.030440166886867077)]
        viewportMap.remove(e);
    }

    public void closeViewport(Element e) {
// e                    0	: [('e', 0.18192291394793333), ('elt', 0.0754644694299421), ('filterElement', 0.05466653867045504), ('element', 0.04655325315885785), ('imageElement', 0.018356614312589233), ('paintElement', 0.010945660045372224), ('imp', 0.009548186214105631), ('elem', 0.009508794061420444), ('contextElement', 0.009202206918914302), ('filteredElement', 0.008552554942100048)]
        viewportStack.remove(0);
        if (viewportStack.size() == 0) {
            viewportStack = null;
        }
    }

    public void bind(Node node, GraphicsNode gn) {
// node                 7	: [('n', 0.4040934322521647), ('evtListener', 0.3928653140781521), ('gn', 0.1250841426173822), ('element', 0.09838024544069714), ('data', 0.0983385064408105), ('ns', 0.06897961953415373), ('syntaxName', 0.06736835282774419), ('node', 0.05434811184085756), ('img', 0.05366206399451736), ('key', 0.05268562534060545)]
// gn                   No	: [('evtListener', 0.3928653140781521), ('filteredNode', 0.2675797403194834), ('node', 0.15527794940225498), ('element', 0.09838024544069714), ('data', 0.0983385064408105), ('paintedNode', 0.08352298571405142), ('ns', 0.06897961953415373), ('syntaxName', 0.06736835282774419), ('img', 0.05366206399451736), ('key', 0.05268562534060545)]
        if (elementNodeMap == null) {
            elementNodeMap = new WeakHashMap();
            nodeElementMap = new WeakHashMap();
        }
        elementNodeMap.put(node, new SoftReference(gn));
        nodeElementMap.put(gn, new SoftReference(node));
    }

    public void unbind(Node node) {
// node                 0	: [('node', 0.7505937540791433), ('n', 0.4040934322521647), ('l', 0.10474225200069047), ('i', 0.07049781655393512), ('mi', 0.05224901119838541), ('item', 0.03953158432550617), ('namespaceURI', 0.03865232477000724), ('dependent', 0.02985738175197246), ('e', 0.0263725141437703), ('parent', 0.02589216345692684)]
        if (elementNodeMap == null) {
            return;
        }
        GraphicsNode gn = null;
// gn                   0	: [('gn', 0.8360353922435078), ('evtListener', 0.7503291823063577), ('l', 0.10474225241949058), ('ctx', 0.10194550717092381), ('n', 0.08420953451626267), ('h', 0.07774615531851073), ('lav', 0.0753736898903273), ('i', 0.07049782495995345), ('index', 0.0525635258305017), ('node', 0.052531096193216234)]
        SoftReference sr = (SoftReference) elementNodeMap.get(node);
// sr                   0	: [('sr', 0.9034157521534081), ('cgn', 0.2371405335529587), ('i', 0.2022001550630757), ('gnWRef', 0.2019027082205744), ('o', 0.1512591496974437), ('ctx', 0.10194550111826092), ('n', 0.08420952910668444), ('h', 0.07774615476282827), ('lav', 0.07537368987012656), ('index', 0.05256352470740065)]
        if (sr != null)
            gn = (GraphicsNode) sr.get();
        elementNodeMap.remove(node);
        if (gn != null)
            nodeElementMap.remove(gn);
    }

    public GraphicsNode getGraphicsNode(Node node) {
// node                 0	: [('node', 0.7505941944104662), ('n', 0.4040934269307759), ('i', 0.040306777700665174), ('parent', 0.02589216288090758), ('child', 0.016632363866494033), ('contextNode', 0.01390380366042633), ('m', 0.013061586799407098), ('newChild', 0.009991357598593001), ('key', 0.00910544616176976), ('ns', 0.009002581315005831)]
        if (elementNodeMap != null) {
            SoftReference sr = (SoftReference) elementNodeMap.get(node);
// sr                   0	: [('sr', 0.951707876076704), ('o', 0.6012591525703327), ('children', 0.24094336569810507), ('ctx', 0.10194550111826092), ('n', 0.08420952910668444), ('h', 0.07774615476282827), ('lav', 0.07537368987012656), ('index', 0.05256352470740065), ('gn', 0.027937749417010913), ('cm', 0.02763010817264771)]
            if (sr != null)
                return (GraphicsNode) sr.get();
        }
        return null;
    }

    public Element getElement(GraphicsNode gn) {
// gn                   2	: [('node', 0.2059632242451697), ('i', 0.1612271108026607), ('gn', 0.12649776769305818), ('source', 0.10775672968457814), ('filteredNode', 0.03812768518907076), ('key', 0.03642178464707904), ('ns', 0.036010325260023325), ('namespaceURI', 0.035319571048456025), ('name', 0.03429035415473933), ('s', 0.030969577990399125)]
        if (nodeElementMap != null) {
            SoftReference sr = (SoftReference) nodeElementMap.get(gn);
// sr                   0	: [('sr', 0.951707876076704), ('evt', 0.3690993727075607), ('i', 0.22523713354322686), ('r2d', 0.16776337044618606), ('n1', 0.11601323059989314), ('idx', 0.08840074866895274), ('cgn', 0.08358762174221572), ('isStatic', 0.0835827144969595), ('gnBridge', 0.08346674931260506), ('mevt', 0.07482267064459788)]
            if (sr != null) {
                Node n = (Node) sr.get();
// n                    0	: [('n', 0.6699753732422191), ('ns', 0.5042769972337107), ('delimiter', 0.06766904836737768), ('m', 0.05147848758340568), ('s', 0.02778186666840999), ('node', 0.024921618830165183), ('ln', 0.017647909519856383), ('attr', 0.016549254974352678), ('next', 0.015545475352694316), ('child', 0.012986339856674644)]
                if (n.getNodeType() == Node.ELEMENT_NODE) {
                    return (Element) n;
                }
            }
        }
        return null;
    }

    public boolean hasGraphicsNodeBridge(Element element) {
// element              0	: [('element', 0.7837140358315706), ('atr', 0.4013632002844879), ('e', 0.18262374023121372), ('elt', 0.07546447293751347), ('filterElement', 0.05466654111460624), ('attr', 0.03611070113737305), ('n', 0.03406223549643003), ('namespaceURI', 0.021772133571988223), ('imageElement', 0.01835661515732219), ('localName', 0.01684102445638191)]
        if (namespaceURIMap == null || element == null) {
            return false;
        }
        String localName = element.getLocalName();
// localName            0	: [('localName', 0.5009387561724741), ('ln', 0.3082338638219805), ('path', 0.3034495676501164), ('parentPath', 0.15170150099738758), ('i', 0.08061355540133035), ('s', 0.020453443544186795), ('key', 0.01821089232353952), ('ns', 0.018005162630011663), ('namespaceURI', 0.017659785524228012), ('name', 0.017145177077369664)]
        String namespaceURI = element.getNamespaceURI();
// namespaceURI         0	: [('namespaceURI', 0.7583846327870489), ('et', 0.3751818051060881), ('val', 0.31027463304901515), ('urlObj', 0.21611916690379845), ('nodeValue', 0.1566110587163885), ('attr', 0.14416601446270208), ('es', 0.09445005862531393), ('arg', 0.07205657992379605), ('fi', 0.0720561269389625), ('i', 0.04030680764852148)]
        namespaceURI = ((namespaceURI == null) ? "" : namespaceURI);
        HashMap localNameMap = (HashMap) namespaceURIMap.get(namespaceURI);
// localNameMap         0	: [('localNameMap', 0.8056103636497213), ('parent', 0.11821232381825589), ('c', 0.11110956464527275), ('sum', 0.10905461770187244), ('integer', 0.10905360389808157), ('count', 0.10059042028613838), ('ls', 0.10025137641699089), ('m', 0.055144154404539604), ('names', 0.027565664417641637), ('menuMap', 0.02756516829961421)]
        if (localNameMap == null) {
            return false;
        }
        return (localNameMap.get(localName) instanceof GraphicsNodeBridge);
    }

    public DocumentBridge getDocumentBridge() {
        return new SVGDocumentBridge();
    }

    public Bridge getBridge(Element element) {
// element              0	: [('element', 0.7837140358315706), ('atr', 0.4013632002844879), ('e', 0.18262374023121372), ('elt', 0.07546447293751347), ('filterElement', 0.05466654111460624), ('attr', 0.03611070113737305), ('n', 0.03406223549643003), ('namespaceURI', 0.021772133571988223), ('imageElement', 0.01835661515732219), ('localName', 0.01684102445638191)]
        if (namespaceURIMap == null || element == null) {
            return null;
        }
        String localName = element.getLocalName();
// localName            0	: [('localName', 0.282078412377802), ('s', 0.2079534435441868), ('value', 0.1912481660476246), ('baseURI', 0.0997237085779964), ('nsURI', 0.09725377065011175), ('href', 0.09465913172478786), ('bu', 0.09460996409331704), ('type', 0.07140310357505757), ('qualifiedName', 0.054865909001976), ('alias', 0.028410567227111123)]
        String namespaceURI = element.getNamespaceURI();
// namespaceURI         0	: [('namespaceURI', 0.7512899989747042), ('et', 0.3751818076812756), ('val', 0.31027463304901515), ('urlObj', 0.21611916690379845), ('nodeValue', 0.1566110587163885), ('attr', 0.14416601446270208), ('es', 0.09445006691051672), ('e', 0.07333876741512274), ('arg', 0.07205657992379605), ('fi', 0.0720561269389625)]
        namespaceURI = ((namespaceURI == null) ? "" : namespaceURI);
        return getBridge(namespaceURI, localName);
    }

    public Bridge getBridge(String namespaceURI, String localName) {
// namespaceURI         0	: [('namespaceURI', 0.7583846944607896), ('x', 0.15675026678120185), ('prefix', 0.11653713309290362), ('p', 0.0810313781993466), ('curr', 0.0513578695446983), ('name', 0.04820237165954441), ('s', 0.042199563303518595), ('i', 0.04030678768303697), ('ns', 0.03744529000712561), ('gnBounds', 0.031619678775305374)]
// localName            0	: [('localName', 0.5009387561724741), ('type', 0.2426819575542252), ('qualifiedName', 0.14863266268012257), ('value', 0.12250231241473282), ('i', 0.08061355540133035), ('typeArg', 0.06547828893352836), ('tagName', 0.03230442900254261), ('pseudoName', 0.032236315908871375), ('key', 0.01821089232353952), ('ns', 0.018005162630011663)]
        Bridge bridge = null;
// bridge               0	: [('bridge', 0.8493744542684453), ('localNameMap', 0.500600369726555), ('f', 0.22172488421386152), ('ae', 0.2098556658176843), ('url', 0.2098433079925866), ('defRec', 0.20984030961473057), ('um', 0.20983914477729013), ('c', 0.18079034971882646), ('v', 0.1424698803599386), ('message', 0.12002393590322116)]
        if (namespaceURIMap != null) {
            HashMap localNameMap = (HashMap) namespaceURIMap.get(namespaceURI);
// localNameMap         0	: [('localNameMap', 0.3004986888251825), ('count', 0.10059042028613838), ('ls', 0.10025137641699089), ('hints', 0.030929509654900603), ('s', 0.01734344907238836), ('m', 0.01563980380596706), ('n', 0.009209529106684437), ('i', 0.008385182385990396), ('names', 0.0077172973489328615), ('menuMap', 0.007709359460494017)]
            if (localNameMap != null) {
                bridge = (Bridge) localNameMap.get(localName);
            }
        }
        if (bridge == null && (reservedNamespaceSet == null || !reservedNamespaceSet.contains(namespaceURI))) {
            bridge = defaultBridge;
        }
        if (isDynamic()) {
            return bridge == null ? null : bridge.getInstance();
        } else {
            return bridge;
        }
    }

    public void putBridge(String namespaceURI, String localName, Bridge bridge) {
// namespaceURI         0	: [('namespaceURI', 0.7583837327136076), ('listeners', 0.5043390552461339), ('urlObj', 0.21611916691183894), ('e', 0.17014726793019333), ('segType', 0.16539244849317894), ('nodeValue', 0.15661105872978934), ('attr', 0.14416601468730014), ('prefix', 0.1165371419837668), ('type', 0.11254349046394017), ('unitsType', 0.1102531945068014)]
// localName            2	: [('bridge', 0.33383216528615783), ('fontWeight', 0.33382393488350426), ('localName', 0.26922424631787534), ('element', 0.2691631789391895), ('type', 0.2426819579158482), ('qualifiedName', 0.1486326627108534), ('value', 0.12250231283603079), ('w', 0.08438090388710869), ('h', 0.08411847512004912), ('ns', 0.06897962544225118)]
// bridge               3	: [('e', 0.7106496700978846), ('namespaceURI', 0.4586311698398668), ('cl', 0.43785319648295057), ('bridge', 0.3974978170737808), ('localName', 0.33382716845186206), ('fontWeight', 0.3338239543633689), ('dirNfile', 0.12512324894377744), ('nsURI', 0.1079026663176702), ('w', 0.08438165264458661), ('h', 0.08411887394644343)]
        if (!(namespaceURI.equals(bridge.getNamespaceURI()) && localName.equals(bridge.getLocalName()))) {
            throw new RuntimeException("Invalid Bridge: " + namespaceURI + "/" + bridge.getNamespaceURI() + " " + localName + "/" + bridge.getLocalName() + " " + bridge.getClass());
        }
        if (namespaceURIMap == null) {
            namespaceURIMap = new HashMap();
        }
        namespaceURI = ((namespaceURI == null) ? "" : namespaceURI);
        HashMap localNameMap = (HashMap) namespaceURIMap.get(namespaceURI);
// localNameMap         0	: [('localNameMap', 0.8056102773740426), ('localName', 0.572294555880309), ('ls', 0.25177770062963256), ('count', 0.10059042044336838), ('m', 0.05514415465289155), ('res', 0.041326234614017916), ('names', 0.02756566442764718), ('menuMap', 0.027565168301400912), ('hints', 0.026933094503514478), ('report', 0.021469081295406922)]
        if (localNameMap == null) {
            localNameMap = new HashMap();
            namespaceURIMap.put(namespaceURI, localNameMap);
        }
        localNameMap.put(localName, bridge);
    }

    public void putBridge(Bridge bridge) {
// bridge               1	: [('arg', 0.6254992140890752), ('bridge', 0.4487492530864267), ('attr', 0.2510474551471637), ('e', 0.17403294006634348), ('importedNode', 0.1671646717619108), ('other', 0.12599243809574953), ('root', 0.08352418979651872), ('n1ln', 0.08349929704820454), ('b', 0.03227447783406881), ('s', 0.00783408409837214)]
        putBridge(bridge.getNamespaceURI(), bridge.getLocalName(), bridge);
    }

    public void removeBridge(String namespaceURI, String localName) {
// namespaceURI         0	: [('namespaceURI', 0.7583841906932101), ('list', 0.25423293215499393), ('cr', 0.2542199273243269), ('urlObj', 0.21611916690647856), ('nodeValue', 0.15661105872085537), ('attr', 0.14416601453756686), ('prefix', 0.11653713842733315), ('l', 0.10474226005126698), ('arg', 0.07205657993076436), ('fi', 0.07205612694217865)]
// localName            1	: [('type', 0.2426819575542252), ('localName', 0.2327479010481386), ('qualifiedName', 0.14863266268012257), ('value', 0.12250231241473282), ('l', 0.1047422481849568), ('i', 0.07049777662444795), ('typeArg', 0.06547828893352836), ('mi', 0.05224901084917774), ('item', 0.03953158256718701), ('namespaceURI', 0.03865223236537062)]
        if (namespaceURIMap == null) {
            return;
        }
        namespaceURI = ((namespaceURI == null) ? "" : namespaceURI);
        HashMap localNameMap = (HashMap) namespaceURIMap.get(namespaceURI);
// localNameMap         0	: [('localNameMap', 0.8056103205122392), ('bridge', 0.5005067580950859), ('attr', 0.2668239139688823), ('defs', 0.13233467918487185), ('count', 0.1005905667402479), ('ls', 0.10025145381175331), ('n', 0.061843577224182884), ('m', 0.055144154528714545), ('result', 0.031427944582010044), ('val', 0.028559945634886577)]
        if (localNameMap != null) {
            localNameMap.remove(localName);
            if (localNameMap.isEmpty()) {
                namespaceURIMap.remove(namespaceURI);
                if (namespaceURIMap.isEmpty()) {
                    namespaceURIMap = null;
                }
            }
        }
    }

    public void setDefaultBridge(Bridge bridge) {
// bridge               0	: [('bridge', 0.44874959781751317), ('b', 0.03227447426357742), ('i', 0.00804075765077506), ('e', 0.007691787348633978), ('n', 0.006534018620104319), ('ctx', 0.004473800727908393), ('x', 0.0038603829404527685), ('t', 0.0038327586469725774), ('raf', 0.0037427951689446865), ('y', 0.0034153883807089584)]
        defaultBridge = bridge;
    }

    public void putReservedNamespaceURI(String namespaceURI) {
// namespaceURI         0	: [('namespaceURI', 0.5036420827862433), ('prefix', 0.11653713487101733), ('name', 0.04820237275156924), ('s', 0.042199566608180104), ('ns', 0.037445291043405715), ('n', 0.025913486562767284), ('uri', 0.024806478731234607), ('c', 0.023221966072007175), ('p', 0.02072323481468054), ('res', 0.02066314966506309)]
        if (namespaceURI == null) {
            namespaceURI = "";
        }
        if (reservedNamespaceSet == null) {
            reservedNamespaceSet = new HashSet();
        }
        reservedNamespaceSet.add(namespaceURI);
    }

    public void removeReservedNamespaceURI(String namespaceURI) {
// namespaceURI         0	: [('namespaceURI', 0.5036420827862433), ('prefix', 0.11653713487101733), ('l', 0.10474225241949058), ('i', 0.07049782495995345), ('mi', 0.05224901123054583), ('name', 0.04820237275156924), ('s', 0.042199566608180104), ('item', 0.0395315846571158), ('ns', 0.037445291043405715), ('node', 0.03044017526646542)]
        if (namespaceURI == null) {
            namespaceURI = "";
        }
        if (reservedNamespaceSet != null) {
            reservedNamespaceSet.remove(namespaceURI);
            if (reservedNamespaceSet.isEmpty()) {
                reservedNamespaceSet = null;
            }
        }
    }

    protected Set eventListenerSet = new HashSet();

    protected EventListener domCharacterDataModifiedEventListener;

    protected EventListener domAttrModifiedEventListener;

    protected EventListener domNodeInsertedEventListener;

    protected EventListener domNodeRemovedEventListener;

    protected CSSEngineListener cssPropertiesChangedListener;

    protected AnimatedAttributeListener animatedAttributeListener;

    protected FocusManager focusManager;

    protected CursorManager cursorManager = new CursorManager(this);

    public void addUIEventListeners(Document doc) {
// doc                  0	: [('doc', 0.9411707556763615), ('elt', 0.10408024660160868), ('e', 0.09246429140229827), ('nodeElement', 0.08598585570920549), ('defRef', 0.0695251792958476), ('imp', 0.06952474199978718), ('n', 0.06395180315652443), ('i', 0.05259616416488986), ('svgElement', 0.034761624103620624), ('evt', 0.026160292218177516)]
        NodeEventTarget evtTarget = (NodeEventTarget) doc.getDocumentElement();
// evtTarget            No	: [('ctx', 0.8844023729718052), ('target', 0.8237163778867086), ('node', 0.06399483903676802), ('i', 0.02662735835241479), ('tgt', 0.02009506467836136), ('root', 0.018073607774241687), ('g', 0.013059580539856716), ('n', 0.012466143817244797), ('eventTarget', 0.011773306686492655), ('e', 0.009406115272905394)]
        DOMMouseOverEventListener domMouseOverListener = new DOMMouseOverEventListener();
// domMouseOverListener 0	: [('domMouseOverListener', 0.906252836713007), ('l', 0.03131620753590088), ('ctx', 0.000978571646039477), ('y', 0.0007148577732539502), ('e', 0.0004711968567848692), ('node', 0.00044742337180544875), ('i', 0.00042066014362204714), ('w', 0.00031425061415318504), ('ret', 0.0003041962543219388), ('s', 0.0002830356594564104)]
        evtTarget.addEventListenerNS(XMLConstants.XML_EVENTS_NAMESPACE_URI, SVGConstants.SVG_EVENT_MOUSEOVER, domMouseOverListener, true, null);
        storeEventListenerNS(evtTarget, XMLConstants.XML_EVENTS_NAMESPACE_URI, SVGConstants.SVG_EVENT_MOUSEOVER, domMouseOverListener, true);
        DOMMouseOutEventListener domMouseOutListener = new DOMMouseOutEventListener();
// domMouseOutListener  0	: [('domMouseOutListener', 0.9375028367656649), ('ctx', 0.000978571646039477), ('y', 0.0007148764668175237), ('e', 0.0004712077833044227), ('node', 0.00044742337180544875), ('i', 0.00042066496182223574), ('w', 0.0003142589604343861), ('ret', 0.0003041962543219388), ('s', 0.00028304239967088195), ('x', 0.00024202498508466225)]
        evtTarget.addEventListenerNS(XMLConstants.XML_EVENTS_NAMESPACE_URI, SVGConstants.SVG_EVENT_MOUSEOUT, domMouseOutListener, true, null);
        storeEventListenerNS(evtTarget, XMLConstants.XML_EVENTS_NAMESPACE_URI, SVGConstants.SVG_EVENT_MOUSEOUT, domMouseOutListener, true);
    }

    public void removeUIEventListeners(Document doc) {
// doc                  0	: [('doc', 0.9411707556763615), ('o', 0.04292635035829657), ('targetElement', 0.0427467952795473), ('njo', 0.02849599654597021), ('relatedElement', 0.028494073854482143), ('document', 0.010198314465552749), ('domFactory', 0.007343996479005305), ('obj', 0.007140999135723412), ('imgElement', 0.0071241559883066284), ('ret', 0.0006142502115897548)]
        EventTarget evtTarget = (EventTarget) doc.getDocumentElement();
// evtTarget            0	: [('evtTarget', 0.7875457574840501), ('target', 0.5288322684345171), ('newTarget', 0.01635899848269103), ('et', 0.00867014682313464), ('eventTarget', 0.005489454835470078), ('t', 0.003952246399462734), ('relatedTarget', 0.002931076787173604), ('e', 0.001071329054629911), ('relatedTargetArg', 0.0007335091797886677), ('bufferHead', 0.0007148179451350895)]
        synchronized (eventListenerSet) {
            for (Object anEventListenerSet : eventListenerSet) {
// anEventListenerSet   0	: [('anEventListenerSet', 0.9375053418605906), ('o', 0.25548291748443874), ('component', 0.05881801530106031), ('o1', 0.05744492859167173), ('textRun1', 0.044936972696835366), ('listener', 0.04351950794150638), ('url', 0.03701537536670869), ('animatedAttributeListener', 0.03568908387743575), ('rule', 0.02543298738206261), ('aLl', 0.019826757563694685)]
                EventListenerMememto elm = (EventListenerMememto) anEventListenerSet;
// elm                  1	: [('m', 0.8752517919902787), ('elm', 0.7994882864082824), ('node', 0.12557300665057883), ('e', 0.11955603675085222), ('attributes', 0.11909845986324155), ('n', 0.08194122259946554), ('originalElement', 0.059544468699173896), ('alav', 0.042170638900299845), ('def', 0.03384107344299333), ('st', 0.026046663007399374)]
                NodeEventTarget et = elm.getTarget();
// et                   0	: [('et', 0.9131884201866309), ('namespaceURI', 0.1875840765370389), ('es', 0.09445016099293863), ('res', 0.050352122476515195), ('s', 0.027797907787364226), ('t', 0.023121453397186433), ('n', 0.02122873208884474), ('at', 0.019315250949249574), ('ln', 0.017663704193586885), ('result', 0.016879719685816193)]
                if (et == evtTarget) {
                    EventListener el = elm.getListener();
// el                   0	: [('el', 0.8226573419714871), ('evtListener', 0.08475534973732968), ('l', 0.02504573109313469), ('hints', 0.02503942928723071), ('s', 0.019965755537181232), ('prev', 0.016454277049200914), ('n', 0.014412655568890861), ('val', 0.013203287864211606), ('index', 0.01318365563059636), ('listener', 0.012435392499998784)]
                    boolean uc = elm.getUseCapture();
// uc                   0	: [('uc', 0.9134700765839456), ('expectNumber', 0.06302012873589266), ('isLocal', 0.013725889754625228), ('n', 0.010690818351511715), ('rgnBr', 0.01062664995280765), ('in', 0.010616551639840672), ('isEmpty', 0.01061085706769976), ('refPre', 0.010610770786303687), ('isGroup', 0.010610770786303687), ('preserve', 0.010243657700885467)]
                    String t = elm.getEventType();
// t                    0	: [('t', 0.8811078960026661), ('ln', 0.09661960020128632), ('s', 0.03830325079142955), ('type', 0.03755391503173323), ('namespaceURI', 0.01929042773806094), ('an', 0.01706323886917196), ('text', 0.016041462726951262), ('prefix', 0.011542057012617561), ('details', 0.011425088332303827), ('name', 0.010844690681893534)]
                    boolean n = elm.getNamespaced();
// n                    1	: [('in', 0.3439498849231475), ('n', 0.17299272393095355), ('curCls', 0.08024552925672171), ('expectNumber', 0.06302012872660202), ('isGroup', 0.0535071261360821), ('m', 0.027395648827869903), ('currentRegion', 0.026876628606675525), ('tcount', 0.026868241948476854), ('sr', 0.026839655801839408), ('y1', 0.02680386339068716)]
                    if (et == null || el == null || t == null) {
                        continue;
                    }
                    if (n) {
                        String ns = elm.getNamespaceURI();
// ns                   0	: [('ns', 0.8283359371449769), ('namespaceURI', 0.06716552345427938), ('s', 0.037723235877509004), ('eventNamespaceURI', 0.02235582753316056), ('msg', 0.013636523706377492), ('ln', 0.0084996120218107), ('m', 0.007144077831015809), ('name', 0.006431449382173932), ('uri', 0.005905716638822913), ('id', 0.004312660363123086)]
                        et.removeEventListenerNS(ns, t, el, uc);
                    } else {
                        et.removeEventListener(t, el, uc);
                    }
                }
            }
        }
    }

    public void addDOMListeners() {
        SVGOMDocument doc = (SVGOMDocument) document;
// doc                  0	: [('doc', 0.8922196778274907), ('svgDoc', 0.06350851259712947), ('i', 0.02663554870523112), ('eng', 0.02278898769065536), ('document', 0.014519791201751626), ('d', 0.014190672436763216), ('g', 0.013062286601963685), ('n', 0.011079769112982264), ('t', 0.007946531019209389), ('sb', 0.0063513427368512496)]
        domAttrModifiedEventListener = new DOMAttrModifiedEventListener();
        doc.addEventListenerNS(XMLConstants.XML_EVENTS_NAMESPACE_URI, "DOMAttrModified", domAttrModifiedEventListener, true, null);
        domNodeInsertedEventListener = new DOMNodeInsertedEventListener();
        doc.addEventListenerNS(XMLConstants.XML_EVENTS_NAMESPACE_URI, "DOMNodeInserted", domNodeInsertedEventListener, true, null);
        domNodeRemovedEventListener = new DOMNodeRemovedEventListener();
        doc.addEventListenerNS(XMLConstants.XML_EVENTS_NAMESPACE_URI, "DOMNodeRemoved", domNodeRemovedEventListener, true, null);
        domCharacterDataModifiedEventListener = new DOMCharacterDataModifiedEventListener();
        doc.addEventListenerNS(XMLConstants.XML_EVENTS_NAMESPACE_URI, "DOMCharacterDataModified", domCharacterDataModifiedEventListener, true, null);
        animatedAttributeListener = new AnimatedAttrListener();
        doc.addAnimatedAttributeListener(animatedAttributeListener);
        focusManager = new FocusManager(document);
        CSSEngine cssEngine = doc.getCSSEngine();
// cssEngine            0	: [('cssEngine', 0.8167071285936465), ('eng', 0.054568323378841356), ('e', 0.0388530986715958), ('engine', 0.02363778817432646), ('result', 0.01940316088418605), ('refEngine', 0.009530349213262672), ('i', 0.006656062160968446), ('g', 0.0032645539523919777), ('n', 0.0027685657723361106), ('t', 0.001986131196430087)]
        cssPropertiesChangedListener = new CSSPropertiesChangedListener();
        cssEngine.addCSSEngineListener(cssPropertiesChangedListener);
    }

    protected void removeDOMListeners() {
        SVGOMDocument doc = (SVGOMDocument) document;
// doc                  0	: [('doc', 0.8922196564538692), ('es', 0.3869968260273659), ('g', 0.24871945768815307), ('svgDoc', 0.06351581741818152), ('g2d', 0.06253803765225262), ('v', 0.06238154609124885), ('src', 0.06146819489726495), ('image', 0.0614405600659764), ('textureImage', 0.06141585566283876), ('target', 0.05056206352234494)]
        doc.removeEventListenerNS(XMLConstants.XML_EVENTS_NAMESPACE_URI, "DOMAttrModified", domAttrModifiedEventListener, true);
        doc.removeEventListenerNS(XMLConstants.XML_EVENTS_NAMESPACE_URI, "DOMNodeInserted", domNodeInsertedEventListener, true);
        doc.removeEventListenerNS(XMLConstants.XML_EVENTS_NAMESPACE_URI, "DOMNodeRemoved", domNodeRemovedEventListener, true);
        doc.removeEventListenerNS(XMLConstants.XML_EVENTS_NAMESPACE_URI, "DOMCharacterDataModified", domCharacterDataModifiedEventListener, true);
        doc.removeAnimatedAttributeListener(animatedAttributeListener);
        CSSEngine cssEngine = doc.getCSSEngine();
// cssEngine            0	: [('cssEngine', 0.8750259889434626), ('eng', 0.05456832344780701), ('e', 0.03885310022564575), ('s', 0.02778187131645921), ('engine', 0.023637788256156176), ('n', 0.02121371462612846), ('result', 0.019403161265819837), ('ln', 0.01764790990753103), ('aci', 0.012672392669061621), ('token', 0.012327360674013653)]
        if (cssEngine != null) {
            cssEngine.removeCSSEngineListener(cssPropertiesChangedListener);
            cssEngine.dispose();
            doc.setCSSEngine(null);
        }
    }

    protected void storeEventListener(EventTarget t, String s, EventListener l, boolean b) {
// t                    0	: [('t', 0.9127318700222522), ('target', 0.23884310261761407), ('relatedTarget', 0.023448614297388832), ('doc', 0.01997898973996764), ('obj', 0.00991627775655062), ('currentTarget', 0.009898676497542917), ('e', 0.008570632437039288), ('et', 0.005883086276874736), ('evtTarget', 0.005870834420114471), ('relatedTargetArg', 0.005868073438309342)]
// s                    1	: [('n', 0.7644126518165493), ('s', 0.27609223630524193), ('ns', 0.1631109286923675), ('el', 0.03302449822535395), ('val', 0.023316649453962366), ('prev', 0.01645427562725133), ('pn', 0.013400609792351572), ('index', 0.013183654079655521), ('type', 0.006671675255992395), ('params', 0.006608595313082453)]
// l                    0	: [('l', 0.9061426651147031), ('listener', 0.05232900943086962), ('ctx', 0.05085959230768376), ('nfEx', 0.03245845297334852), ('filteredElement', 0.011923749862752209), ('attr', 0.006894113364746599), ('pEx', 0.005910275694678356), ('el', 0.004263702011032753), ('preserve', 0.004111173906330695), ('val', 0.0030271687181981158)]
// b                    0	: [('b', 0.8516886303458372), ('c', 0.21921374186392628), ('clearPaintingTransform', 0.012695353277382204), ('a', 0.008054428928847227), ('canBubbleArg', 0.006212288919890578), ('cancelableArg', 0.005997840298622662), ('isCSS', 0.004958779157606282), ('sweepFlag', 0.0037700155631919335), ('largeArcFlag', 0.0037700155631919335), ('isBegin', 0.0031552099904439664)]
        synchronized (eventListenerSet) {
            eventListenerSet.add(new EventListenerMememto(t, s, l, b, this));
        }
    }

    protected void storeEventListenerNS(EventTarget t, String n, String s, EventListener l, boolean b) {
// t                    0	: [('t', 0.9127318700222522), ('target', 0.23884310261761407), ('relatedTarget', 0.023448614297388832), ('doc', 0.01997898973996764), ('obj', 0.00991627775655062), ('currentTarget', 0.009898676497542917), ('e', 0.008570632437039288), ('et', 0.005883086276874736), ('evtTarget', 0.005870834420114471), ('relatedTargetArg', 0.005868073438309342)]
// n                    1	: [('s', 0.7699657479715907), ('n', 0.2730743940262633), ('ns', 0.1631109286923675), ('el', 0.03302449822535395), ('val', 0.023316649453962366), ('prev', 0.01645427562725133), ('pn', 0.013400609792351572), ('index', 0.013183654079655521), ('type', 0.006671675255992395), ('params', 0.006608595313082453)]
// s                    0	: [('s', 0.7896422040945621), ('t', 0.2641563968141155), ('ns', 0.04568828614800875), ('ln', 0.03280201045549687), ('n', 0.025907810484497112), ('errorHandler', 0.025749690677548304), ('name', 0.01624582031371295), ('d', 0.015388100766742522), ('e', 0.013715243698813953), ('value', 0.0034719229274805525)]
// l                    0	: [('l', 0.9061426651147031), ('listener', 0.05232900943086962), ('ctx', 0.050859603339519166), ('nfEx', 0.03245845450042836), ('filteredElement', 0.011923753785767663), ('attr', 0.006894114918155403), ('pEx', 0.0059102761949286495), ('el', 0.004263702011032753), ('preserve', 0.004111174222278248), ('val', 0.0030271705875544734)]
// b                    0	: [('b', 0.8516886303458372), ('c', 0.21921374186392628), ('clearPaintingTransform', 0.012695353277382204), ('a', 0.008054428928847227), ('canBubbleArg', 0.006212288919890578), ('cancelableArg', 0.005997840298622662), ('isCSS', 0.004958779157606282), ('sweepFlag', 0.0037700155631919335), ('largeArcFlag', 0.0037700155631919335), ('isBegin', 0.0031552099904439664)]
        synchronized (eventListenerSet) {
            eventListenerSet.add(new EventListenerMememto(t, n, s, l, b, this));
        }
    }

    public static class SoftReferenceMememto extends CleanerThread.SoftReferenceCleared {

        Object mememto;

        Set set;

        SoftReferenceMememto(Object ref, Object mememto, Set set) {
// ref                  No	: [('prefix', 0.23901393704395496), ('o', 0.10140475046056016), ('target', 0.07483615958526198), ('listener', 0.06525253552521536), ('srcCM', 0.04636146610467278), ('src', 0.03895221453384134), ('source', 0.03607225758383337), ('generatorContext', 0.032559277206646635), ('o1', 0.03122007297810042), ('elt', 0.027607660605224855)]
// mememto              No	: [('event', 0.07290641746298536), ('value', 0.04560960861372298), ('data', 0.04309148201038493), ('o2', 0.035213985934420586), ('o', 0.0332857463285721), ('group', 0.015244434916667523), ('o3', 0.01256806808345956), ('listener', 0.010194519185618159), ('key', 0.008435838060380473), ('i', 0.00804075765077506)]
// set                  No	: [('ctx', 0.5022369003639542), ('elems', 0.36365282733599985), ('selected', 0.1742556092761669), ('attrSet', 0.0568305040451104), ('attributes', 0.037901636348848515), ('s', 0.011770261304610051), ('attrs', 0.011388900692776484), ('keys', 0.01137406041557391), ('ancestors', 0.0075986060458182085), ('ts', 0.007592393836756666)]
            super(ref);
            this.mememto = mememto;
            this.set = set;
        }

        public void cleared() {
            synchronized (set) {
                set.remove(mememto);
                mememto = null;
                set = null;
            }
        }
    }

    protected static class EventListenerMememto {

        public SoftReference target;

        public SoftReference listener;

        public boolean useCapture;

        public String namespaceURI;

        public String eventType;

        public boolean namespaced;

        public EventListenerMememto(EventTarget t, String s, EventListener l, boolean b, BridgeContext ctx) {
// t                    1	: [('l', 0.8126533821569945), ('t', 0.7832097760618948), ('target', 0.059710732514062816), ('relatedTarget', 0.005862153574347208), ('doc', 0.00499474743499191), ('obj', 0.002479069439137655), ('currentTarget', 0.0024746691243857292), ('e', 0.002142658109259822), ('i', 0.0016803399251272032), ('et', 0.001470771569218684)]
// s                    2	: [('et', 0.5625254381658741), ('n', 0.40947480693071847), ('s', 0.27609223630524193), ('ns', 0.1631109286923675), ('elt', 0.12542755484581417), ('root', 0.06270421785981263), ('val', 0.023316649453962366), ('pn', 0.013400609792351572), ('ln', 0.005021990820734289), ('value', 0.0034703893155818323)]
// l                    0	: [('l', 0.9061426651147031), ('t', 0.8129646575453451), ('listener', 0.05232898786069927), ('el', 0.004263702011032753), ('i', 0.0016803241637474492), ('evtListener', 0.0014220163939909697), ('e', 0.0009550521143206602), ('ctx', 0.0009475667704902923), ('n', 0.0008674777243750791), ('x', 0.0007789543166125796)]
// b                    0	: [('b', 0.6598652283351758), ('c', 0.21921374186392628), ('i', 0.008040901308338502), ('e', 0.007692011034611759), ('n', 0.006534208195610415), ('canBubbleArg', 0.006212288919890578), ('cancelableArg', 0.005997840298622662), ('isCSS', 0.004958779157606282), ('ctx', 0.004473927330235993), ('x', 0.003860481336044167)]
// ctx                  2	: [('c', 0.5844736819423102), ('set', 0.5000248284724762), ('ctx', 0.38053889181506917), ('i', 0.00402037882538753), ('e', 0.0038458936743169894), ('n', 0.00326700931005216), ('bc', 0.001949094850268894), ('x', 0.0019301914702263842), ('t', 0.0019163793234862887), ('raf', 0.0018713975844723433)]
            Set set = ctx.eventListenerSet;
// set                  6	: [('keys', 0.18624258577477712), ('value', 0.1177423663678673), ('elems', 0.10543752481555893), ('e', 0.10332785945844682), ('s', 0.09616291008238619), ('ts', 0.09407397473331865), ('set', 0.09407337076354612), ('ret', 0.09224776623529765), ('defs', 0.09218029418356748), ('lu', 0.08816171881988297)]
            target = new SoftReferenceMememto(t, this, set);
            listener = new SoftReferenceMememto(l, this, set);
            eventType = s;
            useCapture = b;
        }

        public EventListenerMememto(EventTarget t, String n, String s, EventListener l, boolean b, BridgeContext ctx) {
// t                    0	: [('t', 0.7832097760618948), ('s', 0.09724101539595949), ('name', 0.06901608703074165), ('tile', 0.06745757199396343), ('center', 0.0674281501509534), ('target', 0.05971077565440352), ('errorCode', 0.05686415142468206), ('prefFileName', 0.05685776662812788), ('l', 0.045097935320725764), ('start', 0.04500708131899369)]
// n                    2	: [('ns', 0.6155857080773649), ('s', 0.4124926492096971), ('n', 0.2730743940262633), ('namespaceURI', 0.043544265370358395), ('val', 0.023316649453962366), ('pn', 0.013400609792351572), ('nsURI', 0.01088450491456042), ('element', 0.005559858203456278), ('uri', 0.005552206359552295), ('ln', 0.005021990820734289)]
// s                    0	: [('s', 0.531143658244303), ('t', 0.2641563968141155), ('el', 0.1316958075265149), ('prev', 0.0657165609039432), ('n', 0.05752987430279274), ('val', 0.052732712808691844), ('index', 0.05265418379275848), ('ns', 0.04568828614800875), ('ln', 0.03280201045549687), ('type', 0.02664649244820236)]
// l                    0	: [('l', 0.9061426651147031), ('listener', 0.05232900943086962), ('ctx', 0.05085959230768376), ('nfEx', 0.03245845297334852), ('filteredElement', 0.011923749862752209), ('attr', 0.006894113364746599), ('pEx', 0.005910275694678356), ('el', 0.004263702011032753), ('preserve', 0.004111173906330695), ('val', 0.0030271687181981158)]
// b                    0	: [('b', 0.8516886303458372), ('c', 0.21921374186392628), ('clearPaintingTransform', 0.012695353277382204), ('a', 0.008054428928847227), ('canBubbleArg', 0.006212288919890578), ('cancelableArg', 0.005997840298622662), ('isCSS', 0.004958779157606282), ('sweepFlag', 0.0037700155631919335), ('largeArcFlag', 0.0037700155631919335), ('isBegin', 0.0031552099904439664)]
// ctx                  1	: [('c', 0.8091731868658119), ('ctx', 0.38053889181506917), ('ialpha', 0.01918488181747094), ('off', 0.018023870758510467), ('wrData', 0.010358882382109096), ('w', 0.009903165120041852), ('val', 0.009670380239177694), ('dstR', 0.009607312304950901), ('alpha', 0.009596190007968899), ('i', 0.005215116046819816)]
            this(t, s, l, b, ctx);
            namespaceURI = n;
            namespaced = true;
        }

        public EventListener getListener() {
            return (EventListener) listener.get();
        }

        public NodeEventTarget getTarget() {
            return (NodeEventTarget) target.get();
        }

        public boolean getUseCapture() {
            return useCapture;
        }

        public String getNamespaceURI() {
            return namespaceURI;
        }

        public String getEventType() {
            return eventType;
        }

        public boolean getNamespaced() {
            return namespaced;
        }
    }

    public void addGVTListener(Document doc) {
// doc                  0	: [('doc', 0.9411707556763615), ('ns', 0.03831514497702245), ('e', 0.017443492776342637), ('charnum', 0.010236423933280368), ('document', 0.010198314465552749), ('extension', 0.008922946453512444), ('namespaceURI', 0.007700018971266609), ('domFactory', 0.007343996479005305), ('name', 0.005882408675060372), ('element', 0.00517695276032183)]
        BridgeEventSupport.addGVTListener(this, doc);
    }

    protected void clearChildContexts() {
        childContexts.clear();
    }

    public void dispose() {
        clearChildContexts();
        synchronized (eventListenerSet) {
            for (Object anEventListenerSet : eventListenerSet) {
// anEventListenerSet   0	: [('anEventListenerSet', 0.9375053418605906), ('o', 0.25548291748443874), ('component', 0.05881801530106031), ('o1', 0.05744492859167173), ('textRun1', 0.044936972696835366), ('listener', 0.04351950794150638), ('url', 0.03701537536670869), ('animatedAttributeListener', 0.03568908387743575), ('rule', 0.02543298738206261), ('aLl', 0.019826757563694685)]
                EventListenerMememto m = (EventListenerMememto) anEventListenerSet;
// m                    1	: [('elm', 0.875168131359424), ('m', 0.7995719470391373), ('node', 0.12557300665057883), ('e', 0.11955603675085222), ('attributes', 0.11909845986324155), ('n', 0.08194122259946554), ('originalElement', 0.059544468699173896), ('alav', 0.042170638900299845), ('def', 0.03384107344299333), ('st', 0.026046663007399374)]
                NodeEventTarget et = m.getTarget();
// et                   0	: [('et', 0.9131884417414354), ('namespaceURI', 0.187584067721807), ('es', 0.09445006687210225), ('res', 0.05035212237324281), ('s', 0.02778186708149268), ('t', 0.02312145302411942), ('n', 0.021213709881607135), ('at', 0.01931525082221382), ('ln', 0.01764790961312159), ('result', 0.016879719494994557)]
                EventListener el = m.getListener();
// el                   0	: [('el', 0.8226573419714871), ('domMouseOverListener', 0.142022867609963), ('l', 0.0963914391220824), ('hints', 0.02503942928723071), ('s', 0.019965755537181232), ('domMouseOutListener', 0.017022867609962983), ('prev', 0.016454277049200914), ('n', 0.014412655568890861), ('val', 0.013203287864211606), ('index', 0.01318365563059636)]
                boolean uc = m.getUseCapture();
// uc                   0	: [('uc', 0.9134700765839456), ('expectNumber', 0.06302012873589266), ('isLocal', 0.013725889754625228), ('n', 0.010690818351511715), ('rgnBr', 0.01062664995280765), ('in', 0.010616551639840672), ('isEmpty', 0.01061085706769976), ('refPre', 0.010610770786303687), ('isGroup', 0.010610770786303687), ('preserve', 0.010243657700885467)]
                String t = m.getEventType();
// t                    0	: [('t', 0.8811078960026661), ('ln', 0.09661960020128632), ('s', 0.03830325079142955), ('type', 0.03755391503173323), ('namespaceURI', 0.01929042773806094), ('an', 0.01706323886917196), ('text', 0.016041462726951262), ('prefix', 0.011542057012617561), ('details', 0.011425088332303827), ('name', 0.010844690681893534)]
                boolean n = m.getNamespaced();
// n                    1	: [('in', 0.3439498849231475), ('n', 0.17299272393095355), ('curCls', 0.08024552925672171), ('expectNumber', 0.06302012872660202), ('isGroup', 0.0535071261360821), ('m', 0.027395648827869903), ('currentRegion', 0.026876628606675525), ('tcount', 0.026868241948476854), ('sr', 0.026839655801839408), ('y1', 0.02680386339068716)]
                if (et == null || el == null || t == null) {
                    continue;
                }
                if (n) {
                    String ns = m.getNamespaceURI();
// ns                   0	: [('ns', 0.8283359371449769), ('namespaceURI', 0.06716552345427938), ('s', 0.037723235877509004), ('eventNamespaceURI', 0.02235582753316056), ('msg', 0.013636523706377492), ('ln', 0.0084996120218107), ('m', 0.007144077831015809), ('name', 0.006431449382173932), ('uri', 0.005905716638822913), ('id', 0.004312660363123086)]
                    et.removeEventListenerNS(ns, t, el, uc);
                } else {
                    et.removeEventListener(t, el, uc);
                }
            }
        }
        if (document != null) {
            removeDOMListeners();
            AbstractGraphicsNodeBridge.disposeTree(document);
        }
        if (animationEngine != null) {
            animationEngine.dispose();
            animationEngine = null;
        }
        for (Object o : interpreterMap.values()) {
// o                    0	: [('o', 0.9375889312499819), ('aList', 0.10977635369824439), ('aDefSet', 0.10055326275599215), ('bridge1', 0.10052842173850407), ('hintKey', 0.0998681879668652), ('aDevRLM', 0.030420964958320956), ('k', 0.029767676781535617), ('textRun1', 0.010570047632235884), ('listener', 0.0091525828769069), ('source', 0.007881670220454185)]
            Interpreter interpreter = (Interpreter) o;
// interpreter          0	: [('interpreter', 0.9429713256599429), ('node', 0.12010226637455046), ('element', 0.11947470788720303), ('handler', 0.11910137305810059), ('ttdoc', 0.11908324730297704), ('prevLastTarget', 0.11908316102158095), ('interp', 0.014846411941339021), ('s', 0.006057175987283882), ('i', 0.0049813923450006264), ('n', 0.004103836968240347)]
            if (interpreter != null)
                interpreter.dispose();
        }
        interpreterMap.clear();
        if (focusManager != null) {
            focusManager.dispose();
        }
        if (elementDataMap != null) {
            elementDataMap.clear();
        }
        if (nodeElementMap != null) {
            nodeElementMap.clear();
        }
        if (elementNodeMap != null) {
            elementNodeMap.clear();
        }
    }

    protected static SVGContext getSVGContext(Node node) {
// node                 1	: [('e', 0.5284998089266715), ('node', 0.4419929205667961), ('n', 0.4040934428952069), ('doc', 0.1872529424481437), ('elt', 0.09286396806826895), ('chunkType', 0.08995166675307864), ('element', 0.07655206838104425), ('ctx', 0.0710576854396419), ('elem', 0.06953254461004416), ('document', 0.04177597653913192)]
        if (node instanceof SVGOMElement) {
            return ((SVGOMElement) node).getSVGContext();
        } else if (node instanceof SVGOMDocument) {
            return ((SVGOMDocument) node).getSVGContext();
        } else {
            return null;
        }
    }

    protected static BridgeUpdateHandler getBridgeUpdateHandler(Node node) {
// node                 2	: [('elem', 0.8769706272784614), ('n', 0.4040934269307759), ('node', 0.05434828261698948), ('parent', 0.02589216288090758), ('child', 0.016632363866494033), ('contextNode', 0.01390380366042633), ('m', 0.013061586799407098), ('newChild', 0.009991357598593001), ('ps', 0.008150755843201872), ('v', 0.007825459528180543)]
        SVGContext ctx = getSVGContext(node);
// ctx                  0	: [('ctx', 0.666981029087351), ('context', 0.45663777180094245), ('svgCtx', 0.17073222545227554), ('bridge', 0.09499471597194124), ('nodeName', 0.0706207303908413), ('b', 0.06491777600199548), ('s', 0.06164155824940086), ('t', 0.0441530940062083), ('v', 0.04158648999493199), ('i', 0.03827255489553524)]
        return (ctx == null) ? null : (BridgeUpdateHandler) ctx;
    }

    protected class DOMAttrModifiedEventListener implements EventListener {

        public DOMAttrModifiedEventListener() {
        }

        public void handleEvent(Event evt) {
// evt                  0	: [('evt', 0.8889013703292876), ('aNodeList', 0.1160111798879301), ('info', 0.11247524251210446), ('aPotentialChildren', 0.11247038695565757), ('e', 0.0779748613755356), ('mevt', 0.07482267051401445), ('target', 0.036579891304289645), ('i', 0.025237132705316502), ('n1', 0.01601323055636533), ('et', 0.01360927506549561)]
            Node node = (Node) evt.getTarget();
// node                 2	: [('e', 0.3989150260571603), ('n', 0.36923920167889496), ('node', 0.31740661490716926), ('bindableElement', 0.1328159492210226), ('me', 0.13281519427202815), ('childNode', 0.11952963926299989), ('parent', 0.033115310867812046), ('attr', 0.016549254899491616), ('child', 0.012986339794677532), ('t', 0.011667909177214168)]
            BridgeUpdateHandler h = getBridgeUpdateHandler(node);
// h                    0	: [('h', 0.9613296577335679), ('at', 0.12115462096194644), ('ctx', 0.10194550111826092), ('n', 0.08420952910668444), ('lav', 0.07537368987012656), ('index', 0.05256352470740065), ('sr', 0.051291955290395486), ('is', 0.0306757054483155), ('cl', 0.03055847619787017), ('r', 0.030553204914747268)]
            if (h != null) {
                try {
                    h.handleDOMAttrModifiedEvent((MutationEvent) evt);
                } catch (Exception e) {
// e                    0	: [('e', 0.6624917480000742), ('ex', 0.28332814061766887), ('de', 0.10109625109597399), ('se', 0.022933801906127593), ('te', 0.0076786890772948336), ('be', 0.007371870040581034), ('exc', 0.0030875480998947746), ('message', 0.00247445142287432), ('i', 0.0008401699625636016), ('exception', 0.0007789725468740477)]
                    userAgent.displayError(e);
                }
            }
        }
    }

    protected class DOMMouseOutEventListener implements EventListener {

        public DOMMouseOutEventListener() {
        }

        public void handleEvent(Event evt) {
// evt                  0	: [('evt', 0.9375802624605469), ('e', 0.07797486059851706), ('i', 0.0002995673284052374), ('n', 0.00026903186833254544), ('doc', 0.00013984232606350342), ('o', 8.955752150374016e-05), ('node', 8.802624871060273e-05), ('ctx', 6.11602292205957e-05), ('ret', 5.283372486070969e-05), ('result', 5.140199121905934e-05)]
            MouseEvent me = (MouseEvent) evt;
// me                   0	: [('me', 0.5946114810302928), ('mevt', 0.2971646827274861), ('n', 0.2687061432725025), ('node', 0.05323536522907036), ('evt', 0.04946644028138121), ('click', 0.04716274141215438), ('child', 0.038277952841514404), ('sr', 0.026162126322484314), ('next', 0.02328905083467338), ('ps', 0.02265000952288502)]
            Element newTarget = (Element) me.getRelatedTarget();
// newTarget            No	: [('targetElement', 0.7568055341077718), ('target', 0.42929714336750535), ('currentTarget', 0.42916863835422403), ('e', 0.05464419865273331), ('s', 0.024228687009725424), ('i', 0.019925537856394403), ('n', 0.016415328895386973), ('t', 0.008672764573588242), ('v', 0.008642522892330773), ('result', 0.008260748196587214)]
            Cursor cursor = CursorManager.DEFAULT_CURSOR;
// cursor               0	: [('cursor', 0.812507243819109), ('choice', 0.2676610589701409), ('helpCursor', 0.08593967461600942), ('moveCursor', 0.0859396314745967), ('cachedCursor', 0.08593954519177126), ('idx', 0.07173408556443918), ('family', 0.06691661949990826), ('c', 0.05736273811878841), ('qualifiedName', 0.03361180502961147), ('viewport', 0.0335828655290904)]
            if (newTarget != null)
                cursor = CSSUtilities.convertCursor(newTarget, BridgeContext.this);
            if (cursor == null)
                cursor = CursorManager.DEFAULT_CURSOR;
            userAgent.setSVGCursor(cursor);
        }
    }

    protected class DOMMouseOverEventListener implements EventListener {

        public DOMMouseOverEventListener() {
        }

        public void handleEvent(Event evt) {
// evt                  0	: [('evt', 0.8889013917499144), ('ae', 0.5026183329262262), ('n', 0.13435306813239334), ('e', 0.07797486059851706), ('node', 0.026617681493735914), ('child', 0.01913897611607391), ('sr', 0.01308106300890051), ('next', 0.011644525341165867), ('ps', 0.01132500467439014), ('element', 0.010905750574936889)]
            Element target = (Element) evt.getTarget();
// target               No	: [('elt', 0.4693861615877733), ('newTarget', 0.4291690805264802), ('currentTarget', 0.4291686383414719), ('elem', 0.07108545903922887), ('e', 0.05464419272382699), ('imageElement', 0.012058786067979187), ('root', 0.011255649969341126), ('def', 0.008350592986446652), ('element', 0.007163525515205115), ('glyphElement', 0.006966713880077765)]
            Cursor cursor = CSSUtilities.convertCursor(target, BridgeContext.this);
// cursor               0	: [('cursor', 0.8125082143617073), ('choice', 0.26766105887097597), ('helpCursor', 0.08593967461172132), ('moveCursor', 0.08593963147102328), ('cachedCursor', 0.08593954518962721), ('idx', 0.07173408306099921), ('family', 0.06691661946022003), ('c', 0.0573627369731534), ('qualifiedName', 0.033611804636525196), ('viewport', 0.03358286550072576)]
            if (cursor != null) {
                userAgent.setSVGCursor(cursor);
            }
        }
    }

    protected class DOMNodeInsertedEventListener implements EventListener {

        public DOMNodeInsertedEventListener() {
        }

        public void handleEvent(Event evt) {
// evt                  0	: [('evt', 0.9375802624605469), ('e', 0.07797486059851706), ('i', 0.0002995673284052374), ('n', 0.00026903186833254544), ('doc', 0.00013984232606350342), ('o', 8.955752150374016e-05), ('node', 8.802624871060273e-05), ('ctx', 6.11602292205957e-05), ('ret', 5.283372486070969e-05), ('result', 5.140199121905934e-05)]
            MutationEvent me = (MutationEvent) evt;
// me                   4	: [('mevt', 0.6032066018487756), ('evt', 0.5387682601215608), ('e', 0.3989150319860666), ('node', 0.39858786452605177), ('me', 0.342257657028574), ('bindableElement', 0.1328159492525457), ('i', 0.003360688256133481), ('ctx', 0.0018951577510010868), ('n', 0.001734977086508135), ('x', 0.0015579280902961348)]
            BridgeUpdateHandler h = getBridgeUpdateHandler(me.getRelatedNode());
// h                    0	: [('h', 0.6906372618685432), ('at', 0.12115462096194644), ('tokens', 0.039396820996463376), ('v', 0.03924804924982566), ('n', 0.036682051834946015), ('ri', 0.0346024230337014), ('is', 0.0306757054483155), ('cl', 0.03055847619787017), ('r', 0.030553204914747268), ('i', 0.0303631964833225)]
            if (h != null) {
                try {
                    h.handleDOMNodeInsertedEvent(me);
                } catch (InterruptedBridgeException ibe) {
// ibe                  1	: [('e', 0.6030709562538147), ('ibe', 0.3657748968275262), ('ctx', 6.115973057198469e-05), ('node', 2.796351336182326e-05), ('ret', 1.9011966810675116e-05), ('uri', 1.4568548522635315e-05), ('chunk', 9.86629033432135e-06), ('ps', 8.572090832950533e-06), ('p1', 6.048401805277442e-06), ('filteredElement', 5.811131896692792e-06)]
                } catch (Exception e) {
// e                    0	: [('e', 0.6275045044035097), ('ex', 0.2725386341765143), ('de', 0.10109625109597399), ('se', 0.022933801906127593), ('be', 0.007371870040581034), ('te', 0.006954016810051852), ('exc', 0.006175096199789549), ('message', 0.00247445142287432), ('exception', 0.0015579450937480955), ('i', 0.0008401699625636016)]
                    userAgent.displayError(e);
                }
            }
        }
    }

    protected class DOMNodeRemovedEventListener implements EventListener {

        public DOMNodeRemovedEventListener() {
        }

        public void handleEvent(Event evt) {
// evt                  0	: [('evt', 0.8889013703292876), ('aNodeList', 0.1160111798879301), ('info', 0.11247524251210446), ('aPotentialChildren', 0.11247038695565757), ('e', 0.0779748613755356), ('mevt', 0.07482267051401445), ('target', 0.036579891304289645), ('i', 0.025237132705316502), ('n1', 0.01601323055636533), ('et', 0.01360927506549561)]
            Node node = (Node) evt.getTarget();
// node                 2	: [('e', 0.3989150260571603), ('n', 0.36923920167889496), ('node', 0.31740661490716926), ('bindableElement', 0.1328159492210226), ('me', 0.13281519427202815), ('childNode', 0.11952963926299989), ('parent', 0.033115310867812046), ('attr', 0.016549254899491616), ('child', 0.012986339794677532), ('t', 0.011667909177214168)]
            BridgeUpdateHandler h = getBridgeUpdateHandler(node);
// h                    0	: [('h', 0.9613296577335679), ('at', 0.12115462096194644), ('ctx', 0.10194550111826092), ('n', 0.08420952910668444), ('lav', 0.07537368987012656), ('index', 0.05256352470740065), ('sr', 0.051291955290395486), ('is', 0.0306757054483155), ('cl', 0.03055847619787017), ('r', 0.030553204914747268)]
            if (h != null) {
                try {
                    h.handleDOMNodeRemovedEvent((MutationEvent) evt);
                } catch (Exception e) {
// e                    0	: [('e', 0.6624917480000742), ('ex', 0.28332814061766887), ('de', 0.10109625109597399), ('se', 0.022933801906127593), ('te', 0.0076786890772948336), ('be', 0.007371870040581034), ('exc', 0.0030875480998947746), ('message', 0.00247445142287432), ('i', 0.0008401699625636016), ('exception', 0.0007789725468740477)]
                    userAgent.displayError(e);
                }
            }
        }
    }

    protected class DOMCharacterDataModifiedEventListener implements EventListener {

        public DOMCharacterDataModifiedEventListener() {
        }

        public void handleEvent(Event evt) {
// evt                  0	: [('evt', 0.8889013703292876), ('aNodeList', 0.1160111798879301), ('info', 0.11247524251210446), ('aPotentialChildren', 0.11247038695565757), ('e', 0.0779748613755356), ('mevt', 0.07482267051401445), ('target', 0.036579891304289645), ('i', 0.025237132705316502), ('n1', 0.01601323055636533), ('et', 0.01360927506549561)]
            Node node = (Node) evt.getTarget();
// node                 2	: [('n', 0.47590378557075796), ('e', 0.39891505492613755), ('node', 0.31740652951698145), ('i', 0.1859862412820952), ('value', 0.15947348870551947), ('clip', 0.15884782720722443), ('bindableElement', 0.1328159493754299), ('me', 0.13281519437267694), ('childNode', 0.11952963931016888), ('iter', 0.07115224788635004)]
            while (node != null && !(node instanceof SVGOMElement)) {
                node = (Node) ((AbstractNode) node).getParentNodeEventTarget();
            }
            BridgeUpdateHandler h = getBridgeUpdateHandler(node);
// h                    0	: [('h', 0.9613296577335679), ('at', 0.12115462096194644), ('ctx', 0.10194550111826092), ('n', 0.08420952910668444), ('lav', 0.07537368987012656), ('index', 0.05256352470740065), ('sr', 0.051291955290395486), ('is', 0.0306757054483155), ('cl', 0.03055847619787017), ('r', 0.030553204914747268)]
            if (h != null) {
                try {
                    h.handleDOMCharacterDataModified((MutationEvent) evt);
                } catch (Exception e) {
// e                    0	: [('e', 0.6624917480000742), ('ex', 0.28332814061766887), ('de', 0.10109625109597399), ('se', 0.022933801906127593), ('te', 0.0076786890772948336), ('be', 0.007371870040581034), ('exc', 0.0030875480998947746), ('message', 0.00247445142287432), ('i', 0.0008401699625636016), ('exception', 0.0007789725468740477)]
                    userAgent.displayError(e);
                }
            }
        }
    }

    protected class CSSPropertiesChangedListener implements CSSEngineListener {

        public CSSPropertiesChangedListener() {
        }

        public void propertiesChanged(CSSEngineEvent evt) {
// evt                  0	: [('evt', 0.9286094102612552), ('doc', 0.8876755110129286), ('props', 0.02084989161527238), ('i', 0.003360696662151808), ('e', 0.0019101310523922145), ('ctx', 0.0018950734635508663), ('n', 0.0017349797472466348), ('x', 0.0015579304272864366), ('s', 0.001461812309208073), ('t', 0.0009293281071799963)]
            Element elem = evt.getElement();
// elem                 No	: [('node', 0.8771034951398251), ('childElt', 0.8317048684792303), ('element', 0.7236692915707198), ('pse', 0.2900116834208403), ('elt', 0.2721056576780996), ('idx', 0.26041165459683635), ('e', 0.20657874024988546), ('errCode', 0.17808291620460476), ('target', 0.13067795387156614), ('currentTarget', 0.12776587764172373)]
            SVGContext ctx = getSVGContext(elem);
// ctx                  0	: [('ctx', 0.6669807742239408), ('childNode', 0.500876237582682), ('svgctx', 0.41484576152136216), ('bbox', 0.21494264084486447), ('n', 0.10866142797021994), ('anim', 0.10783561365134793), ('namespaceURI', 0.10770852781102547), ('apre', 0.1074777142966718), ('context', 0.05771998837794175), ('top', 0.04498042654392104)]
            if (ctx == null) {
                GraphicsNode pgn = getGraphicsNode(elem.getParentNode());
// pgn                  No	: [('node', 0.5952559066466391), ('shadowNode', 0.5205162943239168), ('img', 0.5124705330892397), ('gn', 0.29328613277813276), ('bridge', 0.17335603739231362), ('i', 0.07189727180016575), ('result', 0.06172805979341866), ('rgb', 0.05420048908454737), ('ch1', 0.035602204210430914), ('o', 0.03097926814367513)]
                if ((pgn == null) || !(pgn instanceof CompositeGraphicsNode)) {
                    return;
                }
                CompositeGraphicsNode parent = (CompositeGraphicsNode) pgn;
// parent               3	: [('gn', 0.8756740014126919), ('line', 0.21448251037009464), ('cgn', 0.16019764485985882), ('parent', 0.14980686261201448), ('group', 0.14398997221193569), ('cgn2', 0.1439859169863202), ('comp', 0.07315810566233565), ('glyphContentNode', 0.021995003682787322), ('eng', 0.003967964825446748), ('props', 0.002620724471540248)]
                int[] properties = evt.getProperties();
// properties           0	: [('properties', 0.9375634180851988), ('dstPix', 0.05822014081953939), ('masks', 0.05226024031657712), ('pixels', 0.039893512127621995), ('tmp', 0.027462619622154964), ('samples', 0.02116874780479748), ('offsets', 0.017993191780847108), ('workTbl', 0.017399413150319065), ('glyphCodes', 0.016786336766122747), ('imgPix', 0.015879876272882752)]
                for (int property : properties) {
// property             No	: [('i', 0.7100727406482488), ('y', 0.06051202439076842), ('j', 0.05278128061857465), ('n', 0.05182696097603089), ('node', 0.020933278818768615), ('k', 0.01834875474028782), ('e', 0.016360094434807602), ('m', 0.012460245316303284), ('x', 0.011595299975904822), ('c', 0.010795888808016397)]
                    if (property == SVGCSSEngine.DISPLAY_INDEX) {
                        if (!CSSUtilities.convertDisplay(elem)) {
                            break;
                        }
                        GVTBuilder builder = getGVTBuilder();
// builder              0	: [('builder', 0.8958847636062963), ('childNode', 0.010423554120651704), ('clonedGlyphChildren', 0.005215101594724314), ('glyphChildren', 0.0052127946998108245), ('gvtBuilder', 0.0032092185822386447), ('i', 0.0005025473531734412), ('e', 0.00048073670928962367), ('n', 0.00040837616375652), ('ctx', 0.0002796125454942746), ('x', 0.00024127393377829803)]
                        GraphicsNode childNode = builder.build(BridgeContext.this, elem);
// childNode            0	: [('childNode', 0.7769380274468334), ('ctx', 0.5019455066722587), ('node', 0.19562475989112477), ('refNode', 0.1889763549308092), ('gn', 0.13701445191290182), ('sm', 0.022013242344798737), ('topNode', 0.018615874623089872), ('sd', 0.017966173818700318), ('origin', 0.017965504112420524), ('chAdv', 0.017958378719022883)]
                        if (childNode == null) {
                            break;
                        }
                        int idx = -1;
// idx                  2	: [('item', 0.7544855000021252), ('startIndex', 0.5433340159701433), ('idx', 0.5148694472650386), ('r2d', 0.16776337050836337), ('currRLM', 0.14992821854008365), ('sr', 0.08462528866410834), ('cgn', 0.08358762177794982), ('isStatic', 0.08358271450017557), ('gnBridge', 0.08346674932118124), ('got', 0.052307192973125395)]
                        for (Node ps = elem.getPreviousSibling(); ps != null; ps = ps.getPreviousSibling()) {
// ps                   0	: [('ps', 0.8817568966516215), ('n', 0.4685677628762928), ('i', 0.05262870306931126), ('report', 0.02905616369022446), ('node', 0.026617685751040874), ('e', 0.02544625157084028), ('evt', 0.024733244618210665), ('currRLM', 0.022593868471631456), ('child', 0.01913897727815251), ('m', 0.017808333556747245)]
                            if (ps.getNodeType() != Node.ELEMENT_NODE)
                                continue;
                            Element pse = (Element) ps;
// pse                  1	: [('idx', 0.6302058172848862), ('pse', 0.32278346652939255), ('e', 0.27019692470757295), ('elem', 0.13503500417194675), ('childElement', 0.10766516449834626), ('se', 0.10759675238514178), ('p', 0.06448318689993664), ('ref', 0.0259069376474946), ('element', 0.010165955749431922), ('curr', 0.010150317494379914)]
                            GraphicsNode gn = getGraphicsNode(pse);
// gn                   No	: [('psgn', 0.8780070013882607), ('val', 0.09421490013574174), ('c', 0.07112813763775787), ('scriptType', 0.07057153767908798), ('node', 0.054918346149747964), ('text', 0.047144223409671865), ('str', 0.047074680771668154), ('publicId', 0.04697705363853639), ('s', 0.03468689814477672), ('fontStyle', 0.023501680715122954)]
                            if (gn == null)
                                continue;
                            idx = parent.indexOf(gn);
                            if (idx == -1)
                                continue;
                            break;
                        }
                        idx++;
                        parent.add(idx, childNode);
                        break;
                    }
                }
            }
            if (ctx != null && (ctx instanceof BridgeUpdateHandler)) {
                ((BridgeUpdateHandler) ctx).handleCSSEngineEvent(evt);
            }
        }
    }

    protected class AnimatedAttrListener implements AnimatedAttributeListener {

        public AnimatedAttrListener() {
        }

        public void animatedAttributeChanged(Element e, AnimatedLiveAttributeValue alav) {
// e                    0	: [('e', 0.8977403434501483), ('node', 0.3985878643023619), ('bindableElement', 0.13281594924932968), ('me', 0.1328151942890124), ('elt', 0.00943305882488915), ('filterElement', 0.006833317435644826), ('element', 0.005819156742407265), ('imageElement', 0.0022945768242702775), ('paintElement', 0.0013682075253244649), ('imp', 0.0011935233003467283)]
// alav                 0	: [('alav', 0.96745051388338), ('i', 0.0033606798502544065), ('e', 0.0019100385555110254), ('ctx', 0.0018951557563736004), ('n', 0.0017349744258137129), ('x', 0.0015579257533445473), ('s', 0.0014618090045739299), ('t', 0.0009293251227180192), ('prefix', 0.000896883795546341), ('r', 0.00068920495858002)]
            BridgeUpdateHandler h = getBridgeUpdateHandler(e);
// h                    0	: [('h', 0.8453186309342716), ('at', 0.12115462413080831), ('bridge', 0.07798332384710281), ('r', 0.056934676939158296), ('gn', 0.04641600984498574), ('svgGN', 0.0439760221497073), ('offsets', 0.033526678577090685), ('ll', 0.03335544102992727), ('is', 0.030675705952452618), ('cl', 0.030558476557968107)]
            if (h != null) {
                try {
                    h.handleAnimatedAttributeChanged(alav);
                } catch (Exception ex) {
// ex                   1	: [('e', 0.6697800404586416), ('ex', 0.27603984815910143), ('de', 0.10109625109597399), ('se', 0.022933801906127593), ('te', 0.0076786890772948336), ('be', 0.007371870040581034), ('exc', 0.0030875480998947746), ('message', 0.00247445142287432), ('i', 0.0008401699625636016), ('exception', 0.0007789725468740477)]
                    userAgent.displayError(ex);
                }
            }
        }

        public void otherAnimationChanged(Element e, String type) {
// e                    0	: [('e', 0.8977403434501483), ('node', 0.3985878643023619), ('bindableElement', 0.13281594924932968), ('me', 0.1328151942890124), ('elt', 0.00943305882488915), ('filterElement', 0.006833317435644826), ('element', 0.005819156742407265), ('imageElement', 0.0022945768242702775), ('paintElement', 0.0013682075253244649), ('imp', 0.0011935233003467283)]
// type                 4	: [('pseudoE', 0.37701227166471707), ('attr', 0.09589206101696301), ('uri', 0.05872939320431706), ('id', 0.04857006012625932), ('type', 0.048480063294449045), ('url', 0.04737003793345939), ('value', 0.02699462543780228), ('namespaceURI', 0.025727993182450885), ('an', 0.024330515469564), ('desc', 0.023921602950009384)]
            BridgeUpdateHandler h = getBridgeUpdateHandler(e);
// h                    0	: [('h', 0.8453186309342716), ('at', 0.12115462413080831), ('bridge', 0.07798332384710281), ('r', 0.056934676939158296), ('gn', 0.04641600984498574), ('svgGN', 0.0439760221497073), ('offsets', 0.033526678577090685), ('ll', 0.03335544102992727), ('is', 0.030675705952452618), ('cl', 0.030558476557968107)]
            if (h != null) {
                try {
                    h.handleOtherAnimationChanged(type);
                } catch (Exception ex) {
// ex                   1	: [('e', 0.6697800404586416), ('ex', 0.27603984815910143), ('de', 0.10109625109597399), ('se', 0.022933801906127593), ('te', 0.0076786890772948336), ('be', 0.007371870040581034), ('exc', 0.0030875480998947746), ('message', 0.00247445142287432), ('i', 0.0008401699625636016), ('exception', 0.0007789725468740477)]
                    userAgent.displayError(ex);
                }
            }
        }
    }

    public Value getSystemColor(String ident) {
// ident                0	: [('ident', 0.7502732180893676), ('prefix', 0.029134282828704835), ('name', 0.01205059264188442), ('s', 0.010549889999727955), ('ns', 0.009361322242715669), ('namespaceURI', 0.007214661371011432), ('uri', 0.006201619231851873), ('key', 0.00494461303150107), ('message', 0.004083450696626676), ('text', 0.0035716088957523876)]
        return SystemColorSupport.getSystemColor(ident);
    }

    public Value getDefaultFontFamily() {
        SVGOMDocument doc = (SVGOMDocument) document;
// doc                  0	: [('doc', 0.6951846905752268), ('target', 0.6875828603369206), ('elt', 0.18758618970588142), ('svgDoc', 0.12701702514851854), ('report', 0.060464489515838125), ('n', 0.04016658341501337), ('ret', 0.035263528578093326), ('result', 0.03217671399652176), ('v', 0.031546024222258245), ('document', 0.029039582144784137)]
        SVGStylableElement root = (SVGStylableElement) doc.getRootElement();
// root                 No	: [('elt', 0.16132204163993485), ('value', 0.16126065245606463), ('attr', 0.0805317245575154), ('source', 0.08052352789278142), ('newValue', 0.08038163867637642), ('identValue', 0.08036494332097383), ('e', 0.02074703410484781), ('i', 0.0033606798502544065), ('ctx', 0.0018951557563736004), ('n', 0.0017349744258137129)]
        String str = userAgent.getDefaultFontFamily();
// str                  No	: [('fontFamily', 0.7500439039056289), ('viewBox', 0.2550074645926131), ('ver', 0.2522035301122404), ('s', 0.038303249965271007), ('namespaceURI', 0.01929042750686519), ('ln', 0.01840858666876086), ('text', 0.01604146259545198), ('prefix', 0.011542056568092814), ('details', 0.011425088318010427), ('name', 0.01084469040888959)]
        return doc.getCSSEngine().parsePropertyValue(root, SVGConstants.CSS_FONT_FAMILY_PROPERTY, str);
    }

    public float getLighterFontWeight(float f) {
// f                    0	: [('f', 0.8779215611127116), ('fw', 0.5357320084913186), ('x', 0.013116520691687086), ('y', 0.004542297961491113), ('x1', 0.004080270310239002), ('i', 0.0033606798502544065), ('offset', 0.0028344815908435505), ('v', 0.0028116179765280904), ('x2', 0.0023758419078067894), ('e', 0.0019101248361924358)]
        return userAgent.getLighterFontWeight(f);
    }

    public float getBolderFontWeight(float f) {
// f                    0	: [('f', 0.8779215611127116), ('fw', 0.5357320084913186), ('x', 0.013116520691687086), ('y', 0.004542297961491113), ('x1', 0.004080270310239002), ('i', 0.0033606798502544065), ('offset', 0.0028344815908435505), ('v', 0.0028116179765280904), ('x2', 0.0023758419078067894), ('e', 0.0019101248361924358)]
        return userAgent.getBolderFontWeight(f);
    }

    public float getPixelUnitToMillimeter() {
        return userAgent.getPixelUnitToMillimeter();
    }

    public float getPixelToMillimeter() {
        return getPixelUnitToMillimeter();
    }

    public float getMediumFontSize() {
        return userAgent.getMediumFontSize();
    }

    public float getBlockWidth(Element elt) {
// elt                  0	: [('elt', 0.8959848085539055), ('e', 0.045655931949703535), ('filterElement', 0.013666634871289652), ('element', 0.01163831348481453), ('imageElement', 0.004589153648540555), ('paintElement', 0.0027364150506489298), ('imp', 0.0023870466006934567), ('elem', 0.0023771985525170287), ('contextElement', 0.002300551750096165), ('filteredElement', 0.0021381388159233904)]
        return getViewport(elt).getWidth();
    }

    public float getBlockHeight(Element elt) {
// elt                  0	: [('elt', 0.8959848085539055), ('e', 0.045655931949703535), ('filterElement', 0.013666634871289652), ('element', 0.01163831348481453), ('imageElement', 0.004589153648540555), ('paintElement', 0.0027364150506489298), ('imp', 0.0023870466006934567), ('elem', 0.0023771985525170287), ('contextElement', 0.002300551750096165), ('filteredElement', 0.0021381388159233904)]
        return getViewport(elt).getHeight();
    }

    public void checkLoadExternalResource(ParsedURL resourceURL, ParsedURL docURL) throws SecurityException {
// resourceURL          0	: [('resourceURL', 0.8994202577972226), ('purl', 0.8812908952305507), ('uri', 0.01648559421816057), ('externalResourceURL', 0.004884921403275467), ('url', 0.004588477117382239), ('resourcePURL', 0.003908016534487851), ('docURL', 0.0029928467647304005), ('scriptURL', 0.0027029340884493817), ('base', 0.0024417230048984), ('u', 0.0010758664221035047)]
// docURL               0	: [('docURL', 0.8948956025309089), ('scriptURL', 0.01970158639480806), ('uri', 0.013575428620981293), ('purl', 0.012943981181590625), ('origURL', 0.004222488780877061), ('scriptPURL', 0.0035190297190474252), ('docPURL', 0.0031059261860881277), ('y', 0.002859279242017129), ('resourceURL', 0.00242068076175757), ('e', 0.0018846969145201093)]
        userAgent.checkLoadExternalResource(resourceURL, docURL);
    }

    public boolean isDynamicDocument(Document doc) {
// doc                  0	: [('doc', 0.5293660454108928), ('ns', 0.1532605799080898), ('document', 0.08158651572442199), ('e', 0.06977397110537055), ('domFactory', 0.05875197183204244), ('charnum', 0.04094569573312147), ('extension', 0.035691785814049776), ('namespaceURI', 0.03080007588506644), ('name', 0.023529634700241487), ('element', 0.02070781104128732)]
        return BaseScriptingEnvironment.isDynamicDocument(this, doc);
    }

    public boolean isInteractiveDocument(Document doc) {
// doc                  0	: [('doc', 0.5293660454108928), ('e', 0.4871753980468237), ('svgDoc', 0.3411480645937323), ('document', 0.08158651572442199), ('domFactory', 0.05875197183204244), ('elt', 0.023480593792724963), ('n', 0.008081531868332544), ('o', 0.00790205752150374), ('ctx', 0.007891737295837501), ('ref', 0.007821011862879979)]
        Element root = ((SVGDocument) doc).getRootElement();
// root                 0	: [('root', 0.5723737769375908), ('elt', 0.19855282840058883), ('ns', 0.1635333007882944), ('child', 0.1253535642985733), ('e', 0.11881434543009735), ('namespaceURI', 0.06784386863258976), ('eltNS', 0.06455659292062746), ('n', 0.06183508036068587), ('nameSpaceURI', 0.03227896953533817), ('cursorNS', 0.032278775402197)]
        if (!SVGConstants.SVG_NAMESPACE_URI.equals(root.getNamespaceURI()))
            return false;
        return checkInteractiveElement(root);
    }

    public boolean checkInteractiveElement(Element e) {
// e                    0	: [('e', 0.2920578390615764), ('doc', 0.21382135600383345), ('svgDoc', 0.18229612922066243), ('elt', 0.07546447176830365), ('filterElement', 0.05466654029987568), ('element', 0.046553254719671304), ('s', 0.03130944326757428), ('src', 0.027386965007863074), ('evt', 0.021607920691653078), ('wr', 0.020934847456367863)]
        return checkInteractiveElement((SVGDocument) e.getOwnerDocument(), e);
    }

    public boolean checkInteractiveElement(SVGDocument doc, Element e) {
// doc                  0	: [('doc', 0.25644920776941754), ('e', 0.255707169280211), ('parent', 0.25559448320790634), ('parentNode', 0.2527881858093124), ('svgDocument', 0.03637544094078035), ('before', 0.015440005076531885), ('svgDoc', 0.014217730534026632), ('d', 0.011457753392635333), ('oldVT', 0.011143182777754184), ('newSize', 0.009215158038708742)]
// e                    1	: [('val', 0.4387582785999099), ('e', 0.4145408605359353), ('element', 0.41412503999151573), ('s', 0.26298628343917124), ('returnBrokenLink', 0.26176063330754223), ('node', 0.12065335508730482), ('i', 0.09873141441205935), ('elt', 0.08212447059350758), ('curr', 0.07827516092027502), ('elems', 0.0729894891371499)]
        String tag = e.getLocalName();
// tag                  0	: [('tag', 0.7553264997369314), ('cursorElement', 0.7504140613056627), ('ns', 0.2245152934460793), ('s', 0.15242911946969825), ('uri', 0.12061159932998448), ('lang', 0.10835410995457492), ('m', 0.10273231349765828), ('msg', 0.09451887672956304), ('e', 0.042779307595307875), ('n', 0.031160857684263098)]
        if (SVGConstants.SVG_A_TAG.equals(tag))
            return true;
        if (SVGConstants.SVG_TITLE_TAG.equals(tag)) {
            return (e.getParentNode() != doc.getRootElement());
        }
        if (SVGConstants.SVG_DESC_TAG.equals(tag)) {
            return (e.getParentNode() != doc.getRootElement());
        }
        if (SVGConstants.SVG_CURSOR_TAG.equals(tag))
            return true;
        if (e.getAttribute(CSSConstants.CSS_CURSOR_PROPERTY).length() > 0)
            return true;
        final String svg_ns = SVGConstants.SVG_NAMESPACE_URI;
// svg_ns               No	: [('an', 0.305746168091586), ('n', 0.177714916355348), ('s', 0.14520475956197307), ('desc', 0.10306990772357996), ('errCode', 0.10244569092076213), ('uri', 0.08609625102731985), ('e', 0.07861339322653813), ('tmp', 0.07649484127730985), ('tn', 0.07642567113248183), ('localName', 0.06950967053593614)]
        for (Node n = e.getFirstChild(); n != null; n = n.getNextSibling()) {
// n                    0	: [('n', 0.8334664876630095), ('node', 0.27661768575104084), ('shadowChild', 0.1276155712447733), ('i', 0.05262870306931126), ('m', 0.051327866918792445), ('ps', 0.047509459075066506), ('report', 0.02905616369022446), ('e', 0.02544625157084028), ('evt', 0.024733244618210665), ('c', 0.024229749816462243)]
            if (n.getNodeType() == Node.ELEMENT_NODE) {
                Element child = (Element) n;
// child                No	: [('e', 0.23752536626642304), ('ref', 0.22005386229611565), ('root', 0.1251697022565258), ('childElement', 0.11175968538283847), ('profileNode', 0.1085257096780348), ('agc', 0.1085257096780348), ('s', 0.08265927606500738), ('n', 0.03096609039255524), ('tag', 0.027668293484649355), ('prefix', 0.018008992491300446)]
                if (svg_ns.equals(child.getNamespaceURI()))
                    if (checkInteractiveElement(child))
                        return true;
            }
        }
        return false;
    }

    public void setAnimationLimitingNone() {
        animationLimitingMode = 0;
        if (animationEngine != null) {
            setAnimationLimitingMode();
        }
    }

    public void setAnimationLimitingCPU(float pc) {
// pc                   0	: [('pc', 0.8750662056961107), ('fps', 0.11458677075537252), ('eng', 0.01568671684087236), ('x', 0.013115826093914332), ('y', 0.004541839003317543), ('x1', 0.00408013323883817), ('f', 0.0029784871270717083), ('offset', 0.0028343553003394137), ('v', 0.0028115009267925487), ('x2', 0.002375704836405958)]
        animationLimitingMode = 1;
        animationLimitingAmount = pc;
        if (animationEngine != null) {
            setAnimationLimitingMode();
        }
    }

    public void setAnimationLimitingFPS(float fps) {
// fps                  0	: [('fps', 0.8750650840472524), ('pc', 0.11458789240423085), ('eng', 0.01568671684087236), ('x', 0.013115826093914332), ('y', 0.004541839003317543), ('x1', 0.00408013323883817), ('f', 0.0029784871270717083), ('offset', 0.0028343553003394137), ('v', 0.0028115009267925487), ('x2', 0.002375704836405958)]
        animationLimitingMode = 2;
        animationLimitingAmount = fps;
        if (animationEngine != null) {
            setAnimationLimitingMode();
        }
    }

    protected void setAnimationLimitingMode() {
        switch(animationLimitingMode) {
            case 0:
                animationEngine.setAnimationLimitingNone();
                break;
            case 1:
                animationEngine.setAnimationLimitingCPU(animationLimitingAmount);
                break;
            case 2:
                animationEngine.setAnimationLimitingFPS(animationLimitingAmount);
                break;
        }
    }

    protected List extensions = null;

    public void registerSVGBridges() {
        UserAgent ua = getUserAgent();
// ua                   0	: [('ua', 0.9112562298016036), ('report', 0.29955825587516605), ('n', 0.14301146963232275), ('userAgent', 0.0237585593800017), ('e', 0.016702738678208654), ('r', 0.01625517219348508), ('c', 0.00850197543633915), ('doc', 0.008374926992226637), ('ret', 0.008205765487815282), ('p', 0.008165215868464989)]
        List ext = getBridgeExtensions(document);
// ext                  No	: [('list', 0.12344229222720185), ('layouts', 0.0592261978372531), ('l', 0.05620169781273946), ('ret', 0.03250762741213934), ('dest', 0.03248096668158356), ('rgns', 0.031043122212321583), ('chunkLayouts', 0.030192997777970687), ('lnLocs', 0.029904896995999283), ('paraElems', 0.02961412151290416), ('paraEnds', 0.029614035232222752)]
        for (Object anExt : ext) {
// anExt                8	: [('iter', 0.6000235442007783), ('li', 0.20000758590577053), ('o', 0.11700936545977086), ('bridgeExtension1', 0.10000221044857344), ('component', 0.05885395333131308), ('aList', 0.04666198678407422), ('source', 0.04354325408666176), ('textRun1', 0.037746633957863034), ('anExt', 0.03750158416616999), ('aChildren', 0.036704544713577876)]
            BridgeExtension be = (BridgeExtension) anExt;
// be                   0	: [('be', 0.3846223222807095), ('bridgeExtension', 0.38461956127603514), ('i', 0.03981479980767444), ('ext', 0.028854817139400225), ('lbe', 0.01923511845421189), ('g', 0.010011131777033642), ('svgBE', 0.009619820120223346), ('n', 0.007417047564273043), ('t', 0.005806344098482212), ('y', 0.005167018573723961)]
            be.registerTags(this);
            ua.registerExtension(be);
        }
    }

    public List getBridgeExtensions(Document doc) {
// doc                  0	: [('doc', 0.5293660454108928), ('document', 0.10427597587626371), ('imgDocument', 0.1022053039313946), ('bc', 0.08235340016641915), ('domFactory', 0.05875197183204244), ('e', 0.028499802808728488), ('ctx', 0.024156618248218454), ('node', 0.01993921672490108), ('resCtx', 0.019852860912160333), ('n', 0.01717379377309445)]
        Element root = ((SVGOMDocument) doc).getRootElement();
// root                 0	: [('root', 0.5723737984589854), ('elt', 0.19855282825444), ('e', 0.02323965120369021), ('imageElement', 0.012058786067979187), ('elem', 0.008585459039228876), ('def', 0.008350592986446652), ('i', 0.00804075765077506), ('element', 0.007163525515205115), ('glyphElement', 0.006966713880077765), ('n', 0.006534018620104319)]
        String ver = root.getAttributeNS(null, SVGConstants.SVG_VERSION_ATTRIBUTE);
// ver                  0	: [('ver', 0.8752139280788258), ('viewBox', 0.2550074646072638), ('str', 0.2548219372095424), ('rgb', 0.05520948880739262), ('i', 0.05336901168543786), ('s', 0.03830325079142955), ('ch1', 0.023332067995329928), ('ln', 0.02140055686151693), ('namespaceURI', 0.01929042773806094), ('r', 0.01870373858881414)]
        BridgeExtension svgBE;
// svgBE                No	: [('de', 0.4291733827883816), ('be', 0.42917186144680874), ('bounds', 0.16207168081247394), ('ext', 0.10717785593027337), ('at', 0.10000357178300132), ('ret', 0.08363016799337485), ('lbe', 0.07144596837379957), ('fSum', 0.06444098384504864), ('newRE', 0.05416884271125975), ('bridgeExtension', 0.035730992391192755)]
        if ((ver.length() == 0) || ver.equals("1.0") || ver.equals("1.1"))
            svgBE = new SVGBridgeExtension();
        else
            svgBE = new SVG12BridgeExtension();
        float priority = svgBE.getPriority();
// priority             0	: [('priority', 0.7646029690783394), ('y', 0.09916280103263928), ('x', 0.07938562330379799), ('h', 0.0332787182640097), ('w', 0.028203543206540078), ('offset', 0.02481739027044516), ('t', 0.022007514838722327), ('cn', 0.01831220247265407), ('y1', 0.01736032917769214), ('y2', 0.017338604161765934)]
        extensions = new LinkedList(getGlobalBridgeExtensions());
        ListIterator li = extensions.listIterator();
// li                   0	: [('li', 0.8438196537778078), ('kern', 0.3751031765062428), ('sb', 0.18221832463648419), ('anExt', 0.13750221060379655), ('errorHandler', 0.11221051559500907), ('currentRegion', 0.1060756396211715), ('iter', 0.10002354676868068), ('bridgeExtension1', 0.10000221060379656), ('cm', 0.03741628100549343), ('flowRectsIter', 0.0310037801202444)]
        for (; ; ) {
            if (!li.hasNext()) {
                li.add(svgBE);
                break;
            }
            BridgeExtension lbe = (BridgeExtension) li.next();
// lbe                  0	: [('lbe', 0.9397331870215908), ('s', 0.027781862020481617), ('n', 0.021213703806855295), ('ln', 0.017647909132191206), ('aci', 0.012672391157619714), ('token', 0.01232736028129498), ('o', 0.0121473916122882), ('i', 0.011516704292871358), ('at', 0.01135439498523673), ('lu', 0.01074497966443784)]
            if (lbe.getPriority() > priority) {
                li.previous();
                li.add(svgBE);
                break;
            }
        }
        return extensions;
    }

    protected static List globalExtensions = null;

    public static synchronized List getGlobalBridgeExtensions() {
        if (globalExtensions != null) {
            return globalExtensions;
        }
        globalExtensions = new LinkedList();
        Iterator iter = Service.providers(BridgeExtension.class);
// iter                 1	: [('anExt', 0.6375022104710519), ('iter', 0.587254500667099), ('li', 0.20000758600908478), ('i', 0.13302516743765772), ('bridgeExtension1', 0.10000221047105201), ('j', 0.06715425119150169), ('n', 0.04978741446006708), ('st', 0.030084066228957825), ('flowRectsIter', 0.02237705564150741), ('kit', 0.022376947789762316)]
        while (iter.hasNext()) {
            BridgeExtension be = (BridgeExtension) iter.next();
// be                   2	: [('de', 0.4291733827304914), ('svgBE', 0.4291689074214134), ('be', 0.3839354228375139), ('bridgeExtension', 0.38393274809208067), ('newRE', 0.054168842709830356), ('ext', 0.026794463901093264), ('priority', 0.025004257023668344), ('lbe', 0.017861492084873566), ('c', 0.005815704224533066), ('n', 0.005492672778267785)]
            float priority = be.getPriority();
// priority             0	: [('priority', 0.7646029690783394), ('t', 0.4110037574193612), ('y', 0.04958140051631964), ('x', 0.039692811651898995), ('cn', 0.01831220247265407), ('h', 0.01663935913200485), ('maxWidth', 0.015022685648102838), ('maxHeight', 0.01493047190101202), ('w', 0.014101771603270039), ('offset', 0.01240869513522258)]
            ListIterator li = globalExtensions.listIterator();
// li                   0	: [('li', 0.9562527914089944), ('kern', 0.3751031921001009), ('sb', 0.18221832463648419), ('anExt', 0.1375022105820278), ('errorHandler', 0.11221051559500907), ('currentRegion', 0.1060756396211715), ('iter', 0.10002354642038022), ('bridgeExtension1', 0.10000221058202778), ('cm', 0.03741628100549343), ('flowRectsIter', 0.0310037801202444)]
            for (; ; ) {
                if (!li.hasNext()) {
                    li.add(be);
                    break;
                }
                BridgeExtension lbe = (BridgeExtension) li.next();
// lbe                  0	: [('lbe', 0.9397331870215908), ('s', 0.027781862020481617), ('n', 0.021213703806855295), ('ln', 0.017647909132191206), ('aci', 0.012672391157619714), ('token', 0.01232736028129498), ('o', 0.0121473916122882), ('i', 0.011516704292871358), ('at', 0.01135439498523673), ('lu', 0.01074497966443784)]
                if (lbe.getPriority() > priority) {
                    li.previous();
                    li.add(be);
                    break;
                }
            }
        }
        return globalExtensions;
    }

    public static class CSSEngineUserAgentWrapper implements CSSEngineUserAgent {

        UserAgent ua;

        CSSEngineUserAgentWrapper(UserAgent ua) {
// ua                   2	: [('userAgent', 0.4371395006779015), ('ctx', 0.43080832893538273), ('ua', 0.30291931092579405), ('u', 0.07153153457234973), ('i', 0.00402037882538753), ('e', 0.0038458936743169894), ('n', 0.00326700931005216), ('x', 0.0019301914702263842), ('t', 0.0019163793234862887), ('raf', 0.0018713975844723433)]
            this.ua = ua;
        }

        public void displayError(Exception ex) {
// ex                   1	: [('e', 0.8324525272527259), ('ex', 0.610496308964483), ('de', 0.03204875063112951), ('be', 0.007395278280281409), ('message', 0.0024822541694411115), ('se', 0.0024709405650090205), ('i', 0.0008401699625636016), ('te', 0.000779166678407221), ('exception', 0.0007789725468740477), ('ctx', 0.00047378893909340005)]
            ua.displayError(ex);
        }

        public void displayMessage(String message) {
// message              0	: [('message', 0.8770136154906365), ('href', 0.5277947671603088), ('prefix', 0.014567141414352417), ('name', 0.00602529632094221), ('s', 0.005274944999863977), ('ns', 0.004680661121357834), ('namespaceURI', 0.003607330685505716), ('i', 0.0033606798502544065), ('uri', 0.0031008096159259365), ('key', 0.002472306515750535)]
            ua.displayMessage(message);
        }
    }
}
