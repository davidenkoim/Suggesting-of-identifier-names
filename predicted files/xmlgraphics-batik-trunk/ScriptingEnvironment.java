// Type of training: selfTestingIdentifier
// Path to file: C:\Users\Igor\IdeaProjects\SLP-Core-0.3\java-projects\xmlgraphics-batik-trunk\batik-bridge\src\main\java\org\apache\batik\bridge\ScriptingEnvironment.java
// Number of identifiers: 138	Accuracy: 72.46%	MRR: 80.19%
// True 	Rank of true in predictions : [(predicted token, probability of a token), ...]

package org.apache.batik.bridge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.GZIPOutputStream;
import org.apache.batik.dom.AbstractElement;
import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.dom.events.NodeEventTarget;
import org.apache.batik.dom.util.DOMUtilities;
import org.apache.batik.dom.util.SAXDocumentFactory;
import org.apache.batik.dom.util.XLinkSupport;
import org.apache.batik.anim.dom.SAXSVGDocumentFactory;
import org.apache.batik.anim.dom.SVGOMDocument;
import org.apache.batik.anim.dom.SVGOMScriptElement;
import org.apache.batik.script.Interpreter;
import org.apache.batik.script.InterpreterException;
import org.apache.batik.script.ScriptEventWrapper;
import org.apache.batik.util.EncodingUtilities;
import org.apache.batik.util.ParsedURL;
import org.apache.batik.util.RunnableQueue;
import org.apache.batik.util.SVGConstants;
import org.apache.batik.constants.XMLConstants;
import org.apache.batik.util.XMLResourceDescriptor;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.MutationEvent;
import org.w3c.dom.svg.SVGDocument;

public class ScriptingEnvironment extends BaseScriptingEnvironment {

    public static final String[] SVG_EVENT_ATTRS = { "onabort", "onerror", "onresize", "onscroll", "onunload", "onzoom", "onbegin", "onend", "onrepeat", "onfocusin", "onfocusout", "onactivate", "onclick", "onmousedown", "onmouseup", "onmouseover", "onmouseout", "onmousemove", "onkeypress", "onkeydown", "onkeyup" };

    public static final String[] SVG_DOM_EVENT = { "SVGAbort", "SVGError", "SVGResize", "SVGScroll", "SVGUnload", "SVGZoom", "beginEvent", "endEvent", "repeatEvent", "DOMFocusIn", "DOMFocusOut", "DOMActivate", "click", "mousedown", "mouseup", "mouseover", "mouseout", "mousemove", "keypress", "keydown", "keyup" };

    protected Timer timer = new Timer(true);

    protected UpdateManager updateManager;

    protected RunnableQueue updateRunnableQueue;

    protected EventListener domNodeInsertedListener;

    protected EventListener domNodeRemovedListener;

    protected EventListener domAttrModifiedListener;

    protected EventListener svgAbortListener = new ScriptingEventListener("onabort");

    protected EventListener svgErrorListener = new ScriptingEventListener("onerror");

    protected EventListener svgResizeListener = new ScriptingEventListener("onresize");

    protected EventListener svgScrollListener = new ScriptingEventListener("onscroll");

    protected EventListener svgUnloadListener = new ScriptingEventListener("onunload");

    protected EventListener svgZoomListener = new ScriptingEventListener("onzoom");

    protected EventListener beginListener = new ScriptingEventListener("onbegin");

    protected EventListener endListener = new ScriptingEventListener("onend");

    protected EventListener repeatListener = new ScriptingEventListener("onrepeat");

    protected EventListener focusinListener = new ScriptingEventListener("onfocusin");

    protected EventListener focusoutListener = new ScriptingEventListener("onfocusout");

    protected EventListener activateListener = new ScriptingEventListener("onactivate");

    protected EventListener clickListener = new ScriptingEventListener("onclick");

    protected EventListener mousedownListener = new ScriptingEventListener("onmousedown");

    protected EventListener mouseupListener = new ScriptingEventListener("onmouseup");

    protected EventListener mouseoverListener = new ScriptingEventListener("onmouseover");

    protected EventListener mouseoutListener = new ScriptingEventListener("onmouseout");

    protected EventListener mousemoveListener = new ScriptingEventListener("onmousemove");

    protected EventListener keypressListener = new ScriptingEventListener("onkeypress");

    protected EventListener keydownListener = new ScriptingEventListener("onkeydown");

    protected EventListener keyupListener = new ScriptingEventListener("onkeyup");

    protected EventListener[] listeners = { svgAbortListener, svgErrorListener, svgResizeListener, svgScrollListener, svgUnloadListener, svgZoomListener, beginListener, endListener, repeatListener, focusinListener, focusoutListener, activateListener, clickListener, mousedownListener, mouseupListener, mouseoverListener, mouseoutListener, mousemoveListener, keypressListener, keydownListener, keyupListener };

    Map attrToDOMEvent = new HashMap(SVG_EVENT_ATTRS.length);

    Map attrToListener = new HashMap(SVG_EVENT_ATTRS.length);

    {
        for (int i = 0; i < SVG_EVENT_ATTRS.length; i++) {
// i                    0	: [('i', 0.8533982414257897), ('j', 0.03045696101677962), ('y', 0.02809854954477647), ('e', 0.024897209437121082), ('sp', 0.01762708861917727), ('x', 0.014609117124579338), ('index', 0.009669570936088867), ('idx', 0.009203346440135226), ('k', 0.008442718117446183), ('b', 0.007897326835750337)]
            attrToDOMEvent.put(SVG_EVENT_ATTRS[i], SVG_DOM_EVENT[i]);
            attrToListener.put(SVG_EVENT_ATTRS[i], listeners[i]);
        }
    }

    public ScriptingEnvironment(BridgeContext ctx) {
// ctx                  1	: [('um', 0.7500451422105991), ('ctx', 0.7223224569010196), ('doc', 0.4356208964317046), ('prefix', 0.11950696874423801), ('target', 0.037418079949142424), ('srcCM', 0.02318073310004022), ('src', 0.019476107445408026), ('source', 0.018036128849804475), ('generatorContext', 0.01627963865084848), ('elt', 0.013803830448761234)]
        super(ctx);
        updateManager = ctx.getUpdateManager();
        updateRunnableQueue = updateManager.getUpdateRunnableQueue();
        addScriptingListeners(document.getDocumentElement());
        addDocumentListeners();
    }

    protected void addDocumentListeners() {
        domNodeInsertedListener = new DOMNodeInsertedListener();
        domNodeRemovedListener = new DOMNodeRemovedListener();
        domAttrModifiedListener = new DOMAttrModifiedListener();
        NodeEventTarget et = (NodeEventTarget) document;
// et                   0	: [('et', 0.7626245679518249), ('target', 0.6098038883741117), ('ctx', 0.19130143287059478), ('g', 0.05155172307844519), ('t', 0.03453461168164156), ('e', 0.03167440380668336), ('root', 0.031223110490141415), ('tgt', 0.025903091009234942), ('doc', 0.025286183900007742), ('op', 0.01475938085170481)]
        et.addEventListenerNS(XMLConstants.XML_EVENTS_NAMESPACE_URI, "DOMNodeInserted", domNodeInsertedListener, false, null);
        et.addEventListenerNS(XMLConstants.XML_EVENTS_NAMESPACE_URI, "DOMNodeRemoved", domNodeRemovedListener, false, null);
        et.addEventListenerNS(XMLConstants.XML_EVENTS_NAMESPACE_URI, "DOMAttrModified", domAttrModifiedListener, false, null);
    }

    protected void removeDocumentListeners() {
        NodeEventTarget et = (NodeEventTarget) document;
// et                   0	: [('et', 0.7626245676350709), ('target', 0.6475053173798886), ('es', 0.3992066125478906), ('doc', 0.17887528960896784), ('node', 0.12808373937364753), ('tgt', 0.07172614773417699), ('root', 0.03616065284235732), ('n', 0.024945723696599163), ('eventTarget', 0.023551988326412216), ('evtTarget', 0.01177768544491404)]
        et.removeEventListenerNS(XMLConstants.XML_EVENTS_NAMESPACE_URI, "DOMNodeInserted", domNodeInsertedListener, false);
        et.removeEventListenerNS(XMLConstants.XML_EVENTS_NAMESPACE_URI, "DOMNodeRemoved", domNodeRemovedListener, false);
        et.removeEventListenerNS(XMLConstants.XML_EVENTS_NAMESPACE_URI, "DOMAttrModified", domAttrModifiedListener, false);
    }

    protected org.apache.batik.bridge.Window createWindow(Interpreter interp, String lang) {
// interp               0	: [('interp', 0.9442121776104131), ('interpreter', 0.006252661927224406), ('i', 0.0008401699625636016), ('e', 0.00047753120904810896), ('ctx', 0.00047378893909340005), ('n', 0.0004337436064534282), ('x', 0.0003894814383361368), ('s', 0.00036545225114348247), ('t', 0.0002323312806795048), ('prefix', 0.00022422094888658524)]
// lang                 0	: [('lang', 0.875607964854485), ('w', 0.024437040727260873), ('ln', 0.005021990820734289), ('ns', 0.0040200196014584435), ('value', 0.0034703893155818323), ('s', 0.003401740118788032), ('prefix', 0.002820868259720942), ('oldv', 0.0024772091132937266), ('uri', 0.002442399123370017), ('name', 0.0023562002371159906)]
        return new Window(interp, lang);
    }

    public void runEventHandler(String script, Event evt, String lang, String desc) {
// script               No	: [('s', 0.5410517309958), ('source', 0.1850906163196782), ('prefix', 0.11653713131481933), ('name', 0.04820237056753768), ('ns', 0.037445288970862675), ('rules', 0.03701962728645304), ('scriptStr', 0.037013015927332284), ('svgContent', 0.03701215310484008), ('xmlString', 0.03701124714200716), ('namespaceURI', 0.028858645484045726)]
// evt                  0	: [('evt', 0.9375891697785558), ('e', 0.15334435176599057), ('s', 0.006057180635333101), ('i', 0.004981402327619391), ('n', 0.004103842377946298), ('t', 0.002168196532674263), ('v', 0.0021606337663192354), ('result', 0.002065189278157029), ('x', 0.0018534776162089535), ('idx', 0.0018427669831918199)]
// lang                 2	: [('type', 0.8829732506007637), ('message', 0.502924330481845), ('lang', 0.2857560378340845), ('ln', 0.020087963655991813), ('ns', 0.016080078923965242), ('value', 0.013881558104916295), ('s', 0.013606962127455513), ('prefix', 0.0112834739279259), ('oldv', 0.009908836518209339), ('uri', 0.00976959694443311)]
// desc                 No	: [('ln', 0.04019386142175278), ('ns', 0.03217295585073671), ('value', 0.02777538341984442), ('s', 0.027221395001902907), ('prefix', 0.022571155720301026), ('oldv', 0.01982699685670954), ('uri', 0.01954651047624781), ('e', 0.01919100282117875), ('name', 0.018855451398592474), ('namespaceURI', 0.017636714883619967)]
        Interpreter interpreter = getInterpreter(lang);
// interpreter          1	: [('interp', 0.8750094096521177), ('interpreter', 0.7718852164641486), ('s', 0.03468689132764411), ('n', 0.018419051385597632), ('i', 0.016770357416688992), ('v', 0.012562033551131665), ('t', 0.01078568008872573), ('result', 0.010221379631060219), ('idx', 0.010134829325725963), ('o', 0.009681003794629668)]
        if (interpreter == null)
            return;
        try {
            checkCompatibleScriptURL(lang, docPURL);
            Object event;
// event                0	: [('event', 0.9375153151607218), ('o', 0.2921365418288871), ('val', 0.058338319546291456), ('res', 0.050352130181871284), ('rend', 0.03558707697983868), ('v', 0.027405981069804998), ('t', 0.023121458438073596), ('obj', 0.022998808131349885), ('n', 0.02114305715688531), ('at', 0.019315254117807216)]
            if (evt instanceof ScriptEventWrapper) {
                event = ((ScriptEventWrapper) evt).getEventObject();
            } else {
                event = evt;
            }
            interpreter.bindObject(EVENT_NAME, event);
            interpreter.bindObject(ALTERNATE_EVENT_NAME, event);
            interpreter.evaluate(new StringReader(script), desc);
        } catch (IOException ioe) {
// ioe                  1	: [('e', 0.4283654385198264), ('ioe', 0.22150277723635606), ('ex', 0.17193118904109658), ('ioEx', 0.04306623402898924), ('ignored', 0.032518282186423726), ('io', 0.026129960402341404), ('ioe2', 0.02148506032666156), ('e1', 0.004645555979562746), ('ctx', 6.115973057198469e-05), ('node', 2.796351336182326e-05)]
        } catch (InterpreterException ie) {
// ie                   0	: [('ie', 0.7750050670121061), ('e', 0.6730832723017564), ('i', 0.0008401699625636016), ('ctx', 0.00047378893909340005), ('n', 0.0004337436064534282), ('x', 0.0003894814383361368), ('s', 0.00036545225114348247), ('t', 0.0002323312806795048), ('prefix', 0.00022422094888658524), ('r', 0.000172301239645005)]
            handleInterpreterException(ie);
        } catch (SecurityException se) {
// se                   1	: [('e', 0.331813271264206), ('se', 0.2838207879545469), ('secEx', 0.17249434859394266), ('ex', 0.14013251101394192), ('se2', 0.03613228045895812), ('i', 0.006721359700508813), ('ctx', 0.0037903115127472004), ('n', 0.0034699488516274257), ('x', 0.0031158515066890945), ('s', 0.0029236180091478598)]
            handleSecurityException(se);
        }
    }

    public void interrupt() {
        timer.cancel();
        removeScriptingListeners(document.getDocumentElement());
        removeDocumentListeners();
    }

    public void addScriptingListeners(Node node) {
// node                 0	: [('node', 0.442000706577831), ('n', 0.4036911669417), ('e', 0.1997385048515055), ('target', 0.09132876196387406), ('elt', 0.08212446710334112), ('element', 0.08066542170292583), ('ctx', 0.07105765101695476), ('filterElement', 0.06453857138978386), ('doc', 0.048537235786437344), ('evt', 0.04183795997561023)]
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            addScriptingListenersOn((Element) node);
        }
        for (Node n = node.getFirstChild(); n != null; n = n.getNextSibling()) {
// n                    0	: [('n', 0.8334665085699385), ('shadowTree', 0.16671082718381744), ('m', 0.05132786679461339), ('node', 0.04906534753202145), ('currentNode', 0.04129790603470023), ('nd', 0.03885976392021433), ('e', 0.025446250793783126), ('c', 0.024229749530046373), ('child', 0.020497765732150914), ('localRefElement', 0.011361273005381747)]
            addScriptingListeners(n);
        }
    }

    protected void addScriptingListenersOn(Element elt) {
// elt                  0	: [('elt', 0.8843449220128281), ('e', 0.33207835936844576), ('doc', 0.2579378083729372), ('n', 0.17682267385693004), ('nodeElement', 0.16799292955743564), ('ns', 0.1635423110111485), ('realRoot', 0.1615313326601833), ('tag', 0.07836090834530207), ('attr', 0.07537352329747808), ('method', 0.07532446807245972)]
        NodeEventTarget target = (NodeEventTarget) elt;
// target               0	: [('target', 0.8178298393776835), ('node', 0.06399484284018873), ('root', 0.018073608612695455), ('a', 0.0152652354963554), ('g', 0.014817037044929407), ('b', 0.014810962027530804), ('r', 0.0147088485537197), ('n', 0.012466155127257026), ('tgt', 0.011978374885165436), ('eventTarget', 0.011773306716871415)]
        if (SVGConstants.SVG_NAMESPACE_URI.equals(elt.getNamespaceURI())) {
            if (SVGConstants.SVG_SVG_TAG.equals(elt.getLocalName())) {
                if (elt.hasAttributeNS(null, "onabort")) {
                    target.addEventListenerNS(XMLConstants.XML_EVENTS_NAMESPACE_URI, "SVGAbort", svgAbortListener, false, null);
                }
                if (elt.hasAttributeNS(null, "onerror")) {
                    target.addEventListenerNS(XMLConstants.XML_EVENTS_NAMESPACE_URI, "SVGError", svgErrorListener, false, null);
                }
                if (elt.hasAttributeNS(null, "onresize")) {
                    target.addEventListenerNS(XMLConstants.XML_EVENTS_NAMESPACE_URI, "SVGResize", svgResizeListener, false, null);
                }
                if (elt.hasAttributeNS(null, "onscroll")) {
                    target.addEventListenerNS(XMLConstants.XML_EVENTS_NAMESPACE_URI, "SVGScroll", svgScrollListener, false, null);
                }
                if (elt.hasAttributeNS(null, "onunload")) {
                    target.addEventListenerNS(XMLConstants.XML_EVENTS_NAMESPACE_URI, "SVGUnload", svgUnloadListener, false, null);
                }
                if (elt.hasAttributeNS(null, "onzoom")) {
                    target.addEventListenerNS(XMLConstants.XML_EVENTS_NAMESPACE_URI, "SVGZoom", svgZoomListener, false, null);
                }
            } else {
                String name = elt.getLocalName();
// name                 0	: [('name', 0.875358896707224), ('msg', 0.3725678131444548), ('prefix', 0.22937114466613584), ('ns', 0.06381571655270041), ('id', 0.04409304748078915), ('docHost', 0.0424003521058967), ('s', 0.03140120976307887), ('uri', 0.02370306400096322), ('v', 0.021825703358265777), ('glyphUnicode', 0.021218469425359897)]
                if (name.equals(SVGConstants.SVG_SET_TAG) || name.startsWith("animate")) {
                    if (elt.hasAttributeNS(null, "onbegin")) {
                        target.addEventListenerNS(XMLConstants.XML_EVENTS_NAMESPACE_URI, "beginEvent", beginListener, false, null);
                    }
                    if (elt.hasAttributeNS(null, "onend")) {
                        target.addEventListenerNS(XMLConstants.XML_EVENTS_NAMESPACE_URI, "endEvent", endListener, false, null);
                    }
                    if (elt.hasAttributeNS(null, "onrepeat")) {
                        target.addEventListenerNS(XMLConstants.XML_EVENTS_NAMESPACE_URI, "repeatEvent", repeatListener, false, null);
                    }
                    return;
                }
            }
        }
        if (elt.hasAttributeNS(null, "onfocusin")) {
            target.addEventListenerNS(XMLConstants.XML_EVENTS_NAMESPACE_URI, "DOMFocusIn", focusinListener, false, null);
        }
        if (elt.hasAttributeNS(null, "onfocusout")) {
            target.addEventListenerNS(XMLConstants.XML_EVENTS_NAMESPACE_URI, "DOMFocusOut", focusoutListener, false, null);
        }
        if (elt.hasAttributeNS(null, "onactivate")) {
            target.addEventListenerNS(XMLConstants.XML_EVENTS_NAMESPACE_URI, "DOMActivate", activateListener, false, null);
        }
        if (elt.hasAttributeNS(null, "onclick")) {
            target.addEventListenerNS(XMLConstants.XML_EVENTS_NAMESPACE_URI, "click", clickListener, false, null);
        }
        if (elt.hasAttributeNS(null, "onmousedown")) {
            target.addEventListenerNS(XMLConstants.XML_EVENTS_NAMESPACE_URI, "mousedown", mousedownListener, false, null);
        }
        if (elt.hasAttributeNS(null, "onmouseup")) {
            target.addEventListenerNS(XMLConstants.XML_EVENTS_NAMESPACE_URI, "mouseup", mouseupListener, false, null);
        }
        if (elt.hasAttributeNS(null, "onmouseover")) {
            target.addEventListenerNS(XMLConstants.XML_EVENTS_NAMESPACE_URI, "mouseover", mouseoverListener, false, null);
        }
        if (elt.hasAttributeNS(null, "onmouseout")) {
            target.addEventListenerNS(XMLConstants.XML_EVENTS_NAMESPACE_URI, "mouseout", mouseoutListener, false, null);
        }
        if (elt.hasAttributeNS(null, "onmousemove")) {
            target.addEventListenerNS(XMLConstants.XML_EVENTS_NAMESPACE_URI, "mousemove", mousemoveListener, false, null);
        }
        if (elt.hasAttributeNS(null, "onkeypress")) {
            target.addEventListenerNS(XMLConstants.XML_EVENTS_NAMESPACE_URI, "keypress", keypressListener, false, null);
        }
        if (elt.hasAttributeNS(null, "onkeydown")) {
            target.addEventListenerNS(XMLConstants.XML_EVENTS_NAMESPACE_URI, "keydown", keydownListener, false, null);
        }
        if (elt.hasAttributeNS(null, "onkeyup")) {
            target.addEventListenerNS(XMLConstants.XML_EVENTS_NAMESPACE_URI, "keyup", keyupListener, false, null);
        }
    }

    protected void removeScriptingListeners(Node node) {
// node                 0	: [('node', 0.442000706577831), ('n', 0.4036911669417), ('e', 0.1997385048515055), ('target', 0.09132876196387406), ('elt', 0.08212446710334112), ('element', 0.08066542170292583), ('ctx', 0.07105765101695476), ('filterElement', 0.06453857138978386), ('doc', 0.048537235786437344), ('evt', 0.04183795997561023)]
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            removeScriptingListenersOn((Element) node);
        }
        for (Node n = node.getFirstChild(); n != null; n = n.getNextSibling()) {
// n                    0	: [('n', 0.8334665085699385), ('m', 0.05132786679461339), ('node', 0.04906534753202145), ('currentNode', 0.04129790603470023), ('nd', 0.03885976392021433), ('e', 0.025446250793783126), ('c', 0.024229749530046373), ('child', 0.020497765732150914), ('localRefElement', 0.011361273005381747), ('out', 0.010423376324467288)]
            removeScriptingListeners(n);
        }
    }

    protected void removeScriptingListenersOn(Element elt) {
// elt                  0	: [('elt', 0.8843453720254483), ('e', 0.3320782348200249), ('doc', 0.25793775701696725), ('nodeElement', 0.16799292788783518), ('ns', 0.16354227998471413), ('realRoot', 0.16153133202799866), ('tag', 0.07836090443326293), ('attr', 0.07537352172505711), ('method', 0.0753244679448647), ('namespaceURI', 0.06785252238468298)]
        NodeEventTarget target = (NodeEventTarget) elt;
// target               0	: [('target', 0.8178298393776835), ('tgt', 0.16784713514025026), ('es', 0.1541086493288455), ('ps', 0.0830830446794739), ('node', 0.06399484284018873), ('src', 0.06245744572802535), ('tempLogicalBounds', 0.052563744834028536), ('ctx', 0.041897391125795845), ('absY', 0.04161089852222259), ('absX', 0.04160142667255979)]
        if (SVGConstants.SVG_NAMESPACE_URI.equals(elt.getNamespaceURI())) {
            if (SVGConstants.SVG_SVG_TAG.equals(elt.getLocalName())) {
                target.removeEventListenerNS(XMLConstants.XML_EVENTS_NAMESPACE_URI, "SVGAbort", svgAbortListener, false);
                target.removeEventListenerNS(XMLConstants.XML_EVENTS_NAMESPACE_URI, "SVGError", svgErrorListener, false);
                target.removeEventListenerNS(XMLConstants.XML_EVENTS_NAMESPACE_URI, "SVGResize", svgResizeListener, false);
                target.removeEventListenerNS(XMLConstants.XML_EVENTS_NAMESPACE_URI, "SVGScroll", svgScrollListener, false);
                target.removeEventListenerNS(XMLConstants.XML_EVENTS_NAMESPACE_URI, "SVGUnload", svgUnloadListener, false);
                target.removeEventListenerNS(XMLConstants.XML_EVENTS_NAMESPACE_URI, "SVGZoom", svgZoomListener, false);
            } else {
                String name = elt.getLocalName();
// name                 0	: [('name', 0.875358896707224), ('ns', 0.17095857369555753), ('prefix', 0.1341330494280406), ('id', 0.11552161890936058), ('docHost', 0.11382892353446813), ('s', 0.06711549547736459), ('uri', 0.05941734971524893), ('v', 0.057539989072551485), ('glyphUnicode', 0.05693275513964561), ('isWellFormedLabelVal', 0.0533523007160333)]
                if (name.equals(SVGConstants.SVG_SET_TAG) || name.startsWith("animate")) {
                    target.removeEventListenerNS(XMLConstants.XML_EVENTS_NAMESPACE_URI, "beginEvent", beginListener, false);
                    target.removeEventListenerNS(XMLConstants.XML_EVENTS_NAMESPACE_URI, "endEvent", endListener, false);
                    target.removeEventListenerNS(XMLConstants.XML_EVENTS_NAMESPACE_URI, "repeatEvent", repeatListener, false);
                    return;
                }
            }
        }
        target.removeEventListenerNS(XMLConstants.XML_EVENTS_NAMESPACE_URI, "DOMFocusIn", focusinListener, false);
        target.removeEventListenerNS(XMLConstants.XML_EVENTS_NAMESPACE_URI, "DOMFocusOut", focusoutListener, false);
        target.removeEventListenerNS(XMLConstants.XML_EVENTS_NAMESPACE_URI, "DOMActivate", activateListener, false);
        target.removeEventListenerNS(XMLConstants.XML_EVENTS_NAMESPACE_URI, "click", clickListener, false);
        target.removeEventListenerNS(XMLConstants.XML_EVENTS_NAMESPACE_URI, "mousedown", mousedownListener, false);
        target.removeEventListenerNS(XMLConstants.XML_EVENTS_NAMESPACE_URI, "mouseup", mouseupListener, false);
        target.removeEventListenerNS(XMLConstants.XML_EVENTS_NAMESPACE_URI, "mouseover", mouseoverListener, false);
        target.removeEventListenerNS(XMLConstants.XML_EVENTS_NAMESPACE_URI, "mouseout", mouseoutListener, false);
        target.removeEventListenerNS(XMLConstants.XML_EVENTS_NAMESPACE_URI, "mousemove", mousemoveListener, false);
        target.removeEventListenerNS(XMLConstants.XML_EVENTS_NAMESPACE_URI, "keypress", keypressListener, false);
        target.removeEventListenerNS(XMLConstants.XML_EVENTS_NAMESPACE_URI, "keydown", keydownListener, false);
        target.removeEventListenerNS(XMLConstants.XML_EVENTS_NAMESPACE_URI, "keyup", keyupListener, false);
    }

    protected void updateScriptingListeners(Element elt, String attr) {
// elt                  3	: [('doc', 0.25793775876291136), ('e', 0.18262373401496243), ('nodeElement', 0.16799292793096932), ('elt', 0.12237294729477002), ('refElement', 0.05466934317515888), ('filterElement', 0.05466654029987568), ('element', 0.046553254719671304), ('defRef', 0.03476258975193237), ('imp', 0.03476237107788524), ('n', 0.031975905747293705)]
// attr                 No	: [('an', 0.7520100175632818), ('str', 0.3128363709663317), ('pseudoElt', 0.27159063410203305), ('i', 0.1616018125107302), ('pseudo', 0.11200188961241217), ('nameSpaceURI', 0.0447107037668581), ('extension', 0.044663753695165335), ('toolTip', 0.04460063298858997), ('ns', 0.037750178590685045), ('key', 0.03650774747633109)]
        String domEvt = (String) attrToDOMEvent.get(attr);
// domEvt               No	: [('s', 0.5173434537203235), ('eventType', 0.38999037047063023), ('type', 0.318066916076342), ('evt', 0.07810881834872319), ('msg', 0.05454623771716862), ('ns', 0.03923834737973673), ('ln', 0.03399897278753984), ('m', 0.028576376274303004), ('name', 0.025726511985559627), ('uri', 0.02362337665211404)]
        if (domEvt == null) {
            return;
        }
        EventListener listener = (EventListener) attrToListener.get(attr);
// listener             0	: [('listener', 0.7983208564295393), ('l', 0.03556575606952945), ('el', 0.017054808089869516), ('y', 0.011437724372063203), ('e', 0.007539149708557907), ('ctx', 0.007059495830411938), ('i', 0.006730562297952754), ('evtListener', 0.005688065595974474), ('w', 0.005028009826450961), ('s', 0.004528570551302566)]
        NodeEventTarget target = (NodeEventTarget) elt;
// target               1	: [('et', 0.3952099014783945), ('target', 0.16931429629598793), ('tgt', 0.05179005711316441), ('res', 0.05035212997532822), ('t', 0.05033208423305162), ('node', 0.028706627084781427), ('root', 0.02585174295542679), ('n', 0.021143055826505042), ('at', 0.01931525386373782), ('result', 0.016879726793494147)]
        if (elt.hasAttributeNS(null, attr)) {
            target.addEventListenerNS(XMLConstants.XML_EVENTS_NAMESPACE_URI, domEvt, listener, false, null);
        } else {
            target.removeEventListenerNS(XMLConstants.XML_EVENTS_NAMESPACE_URI, domEvt, listener, false);
        }
    }

    protected class EvaluateRunnable implements Runnable {

        protected Interpreter interpreter;

        protected String script;

        public EvaluateRunnable(String s, Interpreter interp) {
// s                    0	: [('s', 0.882987795988582), ('prefix', 0.11653713131481933), ('name', 0.04820237056753768), ('ns', 0.037445288970862675), ('namespaceURI', 0.028858645484045726), ('uri', 0.024806476927407492), ('script', 0.02345056489348893), ('key', 0.01977845212600428), ('message', 0.016333802786506705), ('text', 0.014286435583009552)]
// interp               0	: [('interp', 0.9515650972167133), ('ri', 0.17115551958142686), ('interpreter', 0.013897188632374902), ('i', 0.003974769575395663), ('factory', 0.003481987623977975), ('e', 0.00048073670928962367), ('n', 0.00040837616375652), ('ctx', 0.0002796125454942746), ('x', 0.00024127393377829803), ('t', 0.00023954741543578606)]
            interpreter = interp;
            script = s;
        }

        public void run() {
            try {
                interpreter.evaluate(script);
            } catch (InterpreterException ie) {
// ie                   0	: [('ie', 0.7750050670121061), ('e', 0.339749938968423), ('i', 0.0008401699625636016), ('ctx', 0.00047378893909340005), ('n', 0.0004337436064534282), ('x', 0.0003894814383361368), ('s', 0.00036545225114348247), ('t', 0.0002323312806795048), ('prefix', 0.00022422094888658524), ('r', 0.000172301239645005)]
                handleInterpreterException(ie);
            }
        }
    }

    protected class EvaluateIntervalRunnable implements Runnable {

        public int count;

        public boolean error;

        protected Interpreter interpreter;

        protected String script;

        public EvaluateIntervalRunnable(String s, Interpreter interp) {
// s                    0	: [('s', 0.882987795988582), ('prefix', 0.11653713131481933), ('name', 0.04820237056753768), ('ns', 0.037445288970862675), ('namespaceURI', 0.028858645484045726), ('uri', 0.024806476927407492), ('script', 0.02345056489348893), ('key', 0.01977845212600428), ('message', 0.016333802786506705), ('text', 0.014286435583009552)]
// interp               0	: [('interp', 0.9515650972167133), ('ri', 0.17115551958142686), ('interpreter', 0.013897188632374902), ('i', 0.003974769575395663), ('factory', 0.003481987623977975), ('e', 0.00048073670928962367), ('n', 0.00040837616375652), ('ctx', 0.0002796125454942746), ('x', 0.00024127393377829803), ('t', 0.00023954741543578606)]
            interpreter = interp;
            script = s;
        }

        public void run() {
            synchronized (this) {
                if (error)
                    return;
                count--;
            }
            try {
                interpreter.evaluate(script);
            } catch (InterpreterException ie) {
// ie                   0	: [('ie', 0.7750050670121061), ('e', 0.339749938968423), ('i', 0.0008401699625636016), ('ctx', 0.00047378893909340005), ('n', 0.0004337436064534282), ('x', 0.0003894814383361368), ('s', 0.00036545225114348247), ('t', 0.0002323312806795048), ('prefix', 0.00022422094888658524), ('r', 0.000172301239645005)]
                handleInterpreterException(ie);
                synchronized (this) {
                    error = true;
                }
            } catch (Exception e) {
// e                    0	: [('e', 0.6193077869636227), ('ex', 0.30293598381245423), ('de', 0.1010962511249179), ('res', 0.05035212997532822), ('exc', 0.030865325877851214), ('t', 0.023121457691945742), ('se', 0.02293380192024221), ('n', 0.021143055826505042), ('at', 0.01931525386373782), ('result', 0.016879726793494147)]
                if (userAgent != null) {
                    userAgent.displayError(e);
                } else {
                    e.printStackTrace();
                }
                synchronized (this) {
                    error = true;
                }
            }
        }
    }

    protected class EvaluateRunnableRunnable implements Runnable {

        public int count;

        public boolean error;

        protected Runnable runnable;

        public EvaluateRunnableRunnable(Runnable r) {
// r                    0	: [('r', 0.5336176122680671), ('runnable', 0.06310462677899872), ('rable', 0.039293722478572345), ('i', 0.00804075765077506), ('e', 0.007691787348633978), ('n', 0.006534018620104319), ('setURI', 0.005963840372495423), ('setDoc', 0.005960906829327473), ('setTrans', 0.005960561706601831), ('doneRun', 0.005960561706601831)]
            runnable = r;
        }

        public void run() {
            synchronized (this) {
                if (error)
                    return;
                count--;
            }
            try {
                runnable.run();
            } catch (Exception e) {
// e                    0	: [('e', 0.6624917272063864), ('ex', 0.2833281406751994), ('de', 0.1010962511249179), ('res', 0.05035212997532822), ('t', 0.023121457691945742), ('se', 0.02293380192024221), ('n', 0.021143055826505042), ('at', 0.01931525386373782), ('result', 0.016879726793494147), ('sb', 0.016558037432778047)]
                if (userAgent != null) {
                    userAgent.displayError(e);
                } else {
                    e.printStackTrace();
                }
                synchronized (this) {
                    error = true;
                }
            }
        }
    }

    protected class Window implements org.apache.batik.bridge.Window {

        protected class IntervalScriptTimerTask extends TimerTask {

            protected EvaluateIntervalRunnable eir;

            public IntervalScriptTimerTask(String script) {
// script               No	: [('prefix', 0.11653713131481933), ('name', 0.04820237056753768), ('s', 0.04219955999891182), ('ns', 0.037445288970862675), ('namespaceURI', 0.028858645484045726), ('uri', 0.024806476927407492), ('key', 0.01977845212600428), ('message', 0.016333802786506705), ('text', 0.014286435583009552), ('value', 0.014221808254712703)]
                eir = new EvaluateIntervalRunnable(script, interpreter);
            }

            public void run() {
                synchronized (eir) {
                    if (eir.count > 1)
                        return;
                    eir.count++;
                }
                synchronized (updateRunnableQueue.getIteratorLock()) {
                    if (updateRunnableQueue.getThread() == null) {
                        cancel();
                        return;
                    }
                    updateRunnableQueue.invokeLater(eir);
                }
                synchronized (eir) {
                    if (eir.error)
                        cancel();
                }
            }
        }

        protected class IntervalRunnableTimerTask extends TimerTask {

            protected EvaluateRunnableRunnable eihr;

            public IntervalRunnableTimerTask(Runnable r) {
// r                    0	: [('r', 0.53361778482943), ('runnable', 0.06310479934036153), ('rable', 0.039293722478572345), ('i', 0.006721359700508813), ('setURI', 0.005963840372495423), ('setDoc', 0.005960906829327473), ('setTrans', 0.005960561706601831), ('doneRun', 0.005960561706601831), ('e', 0.0038202496723848717), ('ctx', 0.0037903115127472004)]
                eihr = new EvaluateRunnableRunnable(r);
            }

            public void run() {
                synchronized (eihr) {
                    if (eihr.count > 1)
                        return;
                    eihr.count++;
                }
                updateRunnableQueue.invokeLater(eihr);
                synchronized (eihr) {
                    if (eihr.error)
                        cancel();
                }
            }
        }

        protected class TimeoutScriptTimerTask extends TimerTask {

            private String script;

            public TimeoutScriptTimerTask(String script) {
// script               0	: [('script', 0.8906368895068784), ('prefix', 0.11653713131481933), ('name', 0.04820237056753768), ('s', 0.04219955999891182), ('ns', 0.037445288970862675), ('namespaceURI', 0.028858645484045726), ('uri', 0.024806476927407492), ('key', 0.01977845212600428), ('message', 0.016333802786506705), ('text', 0.014286435583009552)]
                this.script = script;
            }

            public void run() {
                updateRunnableQueue.invokeLater(new EvaluateRunnable(script, interpreter));
            }
        }

        protected class TimeoutRunnableTimerTask extends TimerTask {

            private Runnable r;

            public TimeoutRunnableTimerTask(Runnable r) {
// r                    0	: [('r', 0.7521080607769768), ('runnable', 0.06310479934036153), ('rable', 0.039293722478572345), ('r1', 0.020439657264697354), ('bufferR', 0.015568054688493552), ('rects', 0.006016521512009922), ('setURI', 0.005963840372495423), ('setDoc', 0.005960906829327473), ('setTrans', 0.005960561706601831), ('doneRun', 0.005960561706601831)]
                this.r = r;
            }

            public void run() {
                updateRunnableQueue.invokeLater(new Runnable() {

                    public void run() {
                        try {
                            r.run();
                        } catch (Exception e) {
// e                    0	: [('e', 0.6624917480000742), ('ex', 0.28332814061766887), ('de', 0.10109625109597399), ('se', 0.022933801906127593), ('te', 0.0076786890772948336), ('be', 0.007371870040581034), ('exc', 0.0030875480998947746), ('message', 0.00247445142287432), ('i', 0.0008401699625636016), ('exception', 0.0007789725468740477)]
                            if (userAgent != null) {
                                userAgent.displayError(e);
                            }
                        }
                    }
                });
            }
        }

        protected Interpreter interpreter;

        protected String language;

        protected Location location;

        public Window(Interpreter interp, String lang) {
// interp               0	: [('interp', 0.9442121776104131), ('ri', 0.17115551958142686), ('interpreter', 0.013897188632374902), ('i', 0.003974769575395663), ('factory', 0.003481987623977975), ('e', 0.00048073670928962367), ('n', 0.00040837616375652), ('ctx', 0.0002796125454942746), ('x', 0.00024127393377829803), ('t', 0.00023954741543578606)]
// lang                 0	: [('lang', 0.8958423019570911), ('language', 0.0416704923517722), ('ln', 0.005021990820734289), ('ns', 0.0040200196014584435), ('value', 0.0034703893155818323), ('s', 0.003401740118788032), ('prefix', 0.002820868259720942), ('oldv', 0.0024772091132937266), ('uri', 0.002442399123370017), ('name', 0.0023562002371159906)]
            interpreter = interp;
            language = lang;
        }

        public Object setInterval(final String script, long interval) {
// script               0	: [('script', 0.8119342057072643), ('uri', 0.042532466118174496), ('name', 0.02150428707644565), ('scriptStr', 0.019046264023116256), ('s', 0.006889425184610915), ('i', 0.006721359700508813), ('content', 0.004798934394325826), ('e', 0.0038202496723848717), ('ctx', 0.0037903115127472004), ('desc', 0.0037086534014832253)]
// interval             0	: [('interval', 0.8958428634811637), ('timeout', 0.5937550606678317), ('instance', 0.041671143830637046), ('when', 0.02345284849265282), ('free', 0.009009555024559898), ('resumeMilli', 0.002932454995051845), ('pos', 0.00278741793386328), ('t', 0.0021864077314215006), ('total', 0.0017136749171721745), ('end', 0.0015126623120889984)]
            IntervalScriptTimerTask tt = new IntervalScriptTimerTask(script);
// tt                   0	: [('tt', 0.8958403847501146), ('report', 0.02090388654896384), ('ret', 0.010169077721882483), ('result', 0.008625670431096698), ('n', 0.007023590214969372), ('v', 0.0064446539021739005), ('wr', 0.005417688328917586), ('sb', 0.004341527745739934), ('p', 0.003753911542852968), ('filter', 0.0037333457400072446)]
            timer.schedule(tt, interval, interval);
            return tt;
        }

        public Object setInterval(final Runnable r, long interval) {
// r                    0	: [('r', 0.9505563897703454), ('doneRun', 0.00855757021332523), ('i', 0.006721359700508813), ('e', 0.0038202496723848717), ('ctx', 0.0037903115127472004), ('runnable', 0.0037214332508785255), ('n', 0.0034699488516274257), ('x', 0.0031158515066890945), ('s', 0.0029236180091478598), ('t', 0.0018586502454360381)]
// interval             0	: [('interval', 0.8958428634811637), ('timeout', 0.5937550606678317), ('instance', 0.041671143830637046), ('when', 0.02345284849265282), ('free', 0.009009555024559898), ('resumeMilli', 0.002932454995051845), ('pos', 0.00278741793386328), ('t', 0.0021864077314215006), ('total', 0.0017136749171721745), ('end', 0.0015126623120889984)]
            IntervalRunnableTimerTask tt = new IntervalRunnableTimerTask(r);
// tt                   0	: [('tt', 0.8958403847501146), ('report', 0.02090388654896384), ('ret', 0.010169077721882483), ('result', 0.008625670431096698), ('n', 0.007023590214969372), ('v', 0.0064446539021739005), ('wr', 0.005417688328917586), ('sb', 0.004341527745739934), ('p', 0.003753911542852968), ('filter', 0.0037333457400072446)]
            timer.schedule(tt, interval, interval);
            return tt;
        }

        public void clearInterval(Object interval) {
// interval             1	: [('timeout', 0.9375020155735077), ('interval', 0.8753272861065086), ('n', 0.05182698128420797), ('node', 0.020933285856301668), ('i', 0.017022099411739076), ('e', 0.016360116597227935), ('o', 0.012675593903871008), ('c', 0.010795896650370247), ('value', 0.010711254577796219), ('s', 0.010312272814846129)]
            if (interval == null)
                return;
            ((TimerTask) interval).cancel();
        }

        public Object setTimeout(final String script, long timeout) {
// script               0	: [('script', 0.8119342057072643), ('uri', 0.042532466118174496), ('name', 0.02150428707644565), ('scriptStr', 0.019046264023116256), ('s', 0.006889425184610915), ('i', 0.006721359700508813), ('content', 0.004798934394325826), ('e', 0.0038202496723848717), ('ctx', 0.0037903115127472004), ('desc', 0.0037086534014832253)]
// timeout              1	: [('interval', 0.6500123370157165), ('timeout', 0.33218138233859307), ('when', 0.0234528484890795), ('free', 0.009009555022415905), ('resumeMilli', 0.002932454994515847), ('pos', 0.0027874179013460637), ('t', 0.002186407358366843), ('total', 0.001713674913956186), ('end', 0.001512662200958732), ('start', 0.0010812797731139657)]
            TimeoutScriptTimerTask tt = new TimeoutScriptTimerTask(script);
// tt                   0	: [('tt', 0.8958403847501146), ('report', 0.02090388654896384), ('ret', 0.010169077721882483), ('result', 0.008625670431096698), ('n', 0.007023590214969372), ('v', 0.0064446539021739005), ('wr', 0.005417688328917586), ('sb', 0.004341527745739934), ('p', 0.003753911542852968), ('filter', 0.0037333457400072446)]
            timer.schedule(tt, timeout);
            return tt;
        }

        public Object setTimeout(final Runnable r, long timeout) {
// r                    0	: [('r', 0.9505563897703454), ('doneRun', 0.00855757021332523), ('i', 0.006721359700508813), ('e', 0.0038202496723848717), ('ctx', 0.0037903115127472004), ('runnable', 0.0037214332508785255), ('n', 0.0034699488516274257), ('x', 0.0031158515066890945), ('s', 0.0029236180091478598), ('t', 0.0018586502454360381)]
// timeout              1	: [('interval', 0.6500123370157165), ('timeout', 0.33218138233859307), ('when', 0.0234528484890795), ('free', 0.009009555022415905), ('resumeMilli', 0.002932454994515847), ('pos', 0.0027874179013460637), ('t', 0.002186407358366843), ('total', 0.001713674913956186), ('end', 0.001512662200958732), ('start', 0.0010812797731139657)]
            TimeoutRunnableTimerTask tt = new TimeoutRunnableTimerTask(r);
// tt                   0	: [('tt', 0.8958403847501146), ('report', 0.02090388654896384), ('ret', 0.010169077721882483), ('result', 0.008625670431096698), ('n', 0.007023590214969372), ('v', 0.0064446539021739005), ('wr', 0.005417688328917586), ('sb', 0.004341527745739934), ('p', 0.003753911542852968), ('filter', 0.0037333457400072446)]
            timer.schedule(tt, timeout);
            return tt;
        }

        public void clearTimeout(Object timeout) {
// timeout              1	: [('interval', 0.9375029430985155), ('timeout', 0.8753263585815008), ('n', 0.05182698128420797), ('node', 0.020933285856301668), ('i', 0.017022099411739076), ('e', 0.016360116597227935), ('o', 0.012675593903871008), ('c', 0.010795896650370247), ('value', 0.010711254577796219), ('s', 0.010312272814846129)]
            if (timeout == null)
                return;
            ((TimerTask) timeout).cancel();
        }

        public Node parseXML(String text, Document doc) {
// text                 0	: [('text', 0.8767576515799286), ('toParse', 0.813892672550979), ('prefix', 0.014567141858877166), ('name', 0.006025296593946155), ('s', 0.005274945826022513), ('ns', 0.004680661380425714), ('namespaceURI', 0.0036073309167014653), ('uri', 0.003100809841404326), ('key', 0.002472306730866209), ('message', 0.002041725403700264)]
// doc                  0	: [('doc', 0.9545249226127314), ('impl', 0.6047704647118108), ('x', 0.1783079956413137), ('document', 0.1042759761026448), ('imgDocument', 0.10220530394390184), ('start', 0.08910247155331644), ('burl', 0.08868233303202079), ('bc', 0.08235340020519161), ('icon', 0.05297184812549055), ('index', 0.044582335941276786)]
            SAXSVGDocumentFactory df = new SAXSVGDocumentFactory(XMLResourceDescriptor.getXMLParserClassName());
// df                   0	: [('df', 0.9218795419540042), ('asb', 0.4140780152049834), ('bounds', 0.16479598683193059), ('f', 0.14022861375136078), ('defaultValue', 0.08181242905846547), ('sdf', 0.08181020618174965), ('eng', 0.02626049845231193), ('bidiLevel', 0.010038685162367023), ('x', 0.0052526499247156876), ('v', 0.005173762806843077)]
            URL urlObj = null;
// urlObj               0	: [('urlObj', 0.8333786476162524), ('url', 0.38034790383588335), ('namespaceURI', 0.28821855174697375), ('nodeValue', 0.15661105872085537), ('attr', 0.14416601453756686), ('arg', 0.07205657993076436), ('fi', 0.07205612694217865), ('lu', 0.047189522071354205), ('elt', 0.04218222124458015), ('res', 0.04132629974321913)]
            if (doc instanceof SVGOMDocument) {
                urlObj = ((SVGOMDocument) doc).getURLObject();
            }
            if (urlObj == null) {
                urlObj = ((SVGOMDocument) bridgeContext.getDocument()).getURLObject();
            }
            String uri = (urlObj == null) ? "" : urlObj.toString();
// uri                  0	: [('uri', 0.5519863060261275), ('s', 0.19943895161672212), ('type', 0.06024875325020392), ('dt', 0.055325789509030096), ('foundChar', 0.05447885528115782), ('descTip', 0.05447434235399393), ('pathString', 0.054474234501355495), ('ctx', 0.017637980082524443), ('baseURI', 0.016843273808966417), ('val', 0.01608335441011479)]
            Node res = DOMUtilities.parseXML(text, doc, uri, null, null, df);
// res                  No	: [('node', 0.5338383822096141), ('n', 0.09681609938314151), ('t', 0.07285640909632479), ('parent', 0.062281055957822726), ('attr', 0.05827170439716085), ('textRuns', 0.043415587881103834), ('msg', 0.043410033643177755), ('ti', 0.04340772555847907), ('next', 0.04180675714947531), ('s', 0.03469715787253031)]
            if (res != null) {
                return res;
            }
            if (doc instanceof SVGOMDocument) {
                Map prefixes = new HashMap();
// prefixes             7	: [('prefixMap', 0.5491213433847523), ('attrMap', 0.30087609631328704), ('p', 0.24564752214632615), ('hints', 0.1693034328798694), ('config', 0.12904666693126418), ('tmp', 0.043059418844146026), ('attributes', 0.0429827727695801), ('prefixes', 0.04297869046349401), ('props', 0.04297827193072079), ('doPut', 0.042975230486316844)]
                prefixes.put(XMLConstants.XMLNS_PREFIX, XMLConstants.XMLNS_NAMESPACE_URI);
                prefixes.put(XMLConstants.XMLNS_PREFIX + ':' + XMLConstants.XLINK_PREFIX, XLinkSupport.XLINK_NAMESPACE_URI);
                res = DOMUtilities.parseXML(text, doc, uri, prefixes, SVGConstants.SVG_SVG_TAG, df);
                if (res != null) {
                    return res;
                }
            }
            SAXDocumentFactory sdf;
// sdf                  7	: [('asb', 0.41407801391090704), ('bounds', 0.16479598025212688), ('df', 0.11767722548684331), ('defaultValue', 0.0818124289651235), ('f', 0.05905214492980437), ('documentFactory', 0.05884230683645956), ('res', 0.05035210850807121), ('sdf', 0.02942778110303319), ('eng', 0.026260498328825852), ('t', 0.023121458065006577)]
            if (doc != null) {
                sdf = new SAXDocumentFactory(doc.getImplementation(), XMLResourceDescriptor.getXMLParserClassName());
            } else {
                sdf = new SAXDocumentFactory(new GenericDOMImplementation(), XMLResourceDescriptor.getXMLParserClassName());
            }
            return DOMUtilities.parseXML(text, doc, uri, null, null, sdf);
        }

        public String printNode(Node n) {
// n                    0	: [('n', 0.9253932003144638), ('c', 0.023068380821545737), ('node', 0.006912013379006866), ('parent', 0.0032365203601134474), ('child', 0.002079045483311754), ('contextNode', 0.0017379754575532914), ('i', 0.0016803399251272032), ('m', 0.0016326983499258872), ('newChild', 0.0012489196998241251), ('ps', 0.001018844480400234)]
            try {
                Writer writer = new StringWriter();
// writer               0	: [('writer', 0.6563407316153642), ('tmpFile', 0.25416771250041836), ('imgData', 0.2520847558173823), ('w', 0.17482492824452675), ('d', 0.08954063615779441), ('report', 0.030232244864405778), ('out', 0.03005667130903861), ('n', 0.020083249231635934), ('ret', 0.0176317644385926), ('result', 0.01608835718907935)]
                DOMUtilities.writeNode(n, writer);
                writer.close();
                return writer.toString();
            } catch (IOException ex) {
// ex                   5	: [('e', 0.4283654392968321), ('ioe', 0.23224480689460106), ('msg', 0.20464327563560117), ('iae', 0.1993278014689999), ('ite', 0.1831987907811057), ('ex', 0.16118913787682124), ('ioEx', 0.04429591507129897), ('ignored', 0.032518282187674365), ('io', 0.02612996040680798), ('ie', 0.022152513250616828)]
                throw new RuntimeException(ex);
            }
        }

        public void getURL(String uri, org.apache.batik.bridge.Window.URLResponseHandler h) {
// uri                  0	: [('uri', 0.8941006195877552), ('prefix', 0.014567141414352417), ('name', 0.00602529632094221), ('s', 0.005274944999863977), ('ns', 0.004680661121357834), ('namespaceURI', 0.003607330685505716), ('key', 0.002472306515750535), ('message', 0.002041725348313338), ('text', 0.0017858044478761938), ('value', 0.0017777260318390879)]
// h                    0	: [('h', 0.8925677959887379), ('fw', 0.17463653408592678), ('content', 0.05575791278354011), ('urlHandler', 0.025402067885202665), ('name', 0.01601231209764672), ('document', 0.015948945801667405), ('is', 0.008988412986954915), ('r', 0.008151397784747142), ('state', 0.007964641240249653), ('inp', 0.005000830544507015)]
            getURL(uri, h, null);
        }

        static final String DEFLATE = "deflate";

        static final String GZIP = "gzip";

        static final String UTF_8 = "UTF-8";

        public void getURL(final String uri, final org.apache.batik.bridge.Window.URLResponseHandler h, final String enc) {
// uri                  2	: [('media', 0.7500339724158757), ('script', 0.32390008196705816), ('uri', 0.09396660533469726), ('name', 0.0860171483057826), ('scriptStr', 0.07618505609246502), ('s', 0.02755770073844366), ('content', 0.019195737577303303), ('desc', 0.014834613605932901), ('errCode', 0.014210396803115054), ('namespaceURI', 0.012684808310455733)]
// h                    0	: [('h', 0.8925677745774017), ('writer', 0.16985685205371914), ('fw', 0.050803479876885015), ('urlHandler', 0.025402067887882653), ('generatorContext', 0.018429069329698386), ('at', 0.016006329192557917), ('distream', 0.00614342762936009), ('s', 0.00611638523307328), ('is', 0.004389335745025667), ('cl', 0.004271905116419105)]
// enc                  0	: [('enc', 0.9375062857561999), ('mimeType', 0.7626500790116969), ('attr', 0.19409241250465592), ('node', 0.19388033313076758), ('ie', 0.0736667715945634), ('n', 0.06501868071034564), ('arg', 0.06461173318932764), ('content', 0.038889843519896404), ('s0', 0.03612482939395457), ('type', 0.031108590734941665)]
            Thread t = new Thread() {
// t                    0	: [('t', 0.8766288785646403), ('main', 0.2888576737842726), ('currentThread', 0.26389401464489626), ('script', 0.14351176031208185), ('toJoin', 0.01389315183093547), ('old', 0.006957508088890614), ('thisThread', 0.006948534823698876), ('g', 0.003584286771563362), ('i', 0.003058165131360718), ('r', 0.0019592503724588364)]

                public void run() {
                    try {
                        ParsedURL burl;
// burl                 No	: [('purl', 0.489269126270456), ('url', 0.4225482991699291), ('baseURI', 0.21332910768279065), ('uri', 0.1470193536803011), ('base', 0.10049832168063172), ('doc', 0.08907206438133988), ('uriStr', 0.08063370520279914), ('docPURL', 0.058565167496501015), ('script', 0.053767411078544665), ('i', 0.03981411983380513)]
                        burl = ((SVGOMDocument) document).getParsedURL();
                        final ParsedURL purl = new ParsedURL(burl, uri);
// purl                 3	: [('conn', 0.876304185471058), ('source', 0.7642430528181265), ('u', 0.5015646779700476), ('purl', 0.05406642814023279), ('uri', 0.02846780289725749), ('inIS', 0.02841474886479518), ('docURL', 0.023942774245053415), ('scriptURL', 0.02162347279764273), ('resourceURL', 0.020077674479217288), ('cls', 0.014224929166184416)]
                        String e = null;
// e                    0	: [('e', 0.5505560692621134), ('xslStyleSheetURI', 0.5006121528954318), ('enc', 0.4196513073475741), ('s', 0.3454477148493481), ('ex', 0.23336128347651647), ('g', 0.16732769411139536), ('de', 0.10109625276435875), ('rgb', 0.05147959551711065), ('purl', 0.04468324164295945), ('srcM', 0.03604497829226636)]
                        if (enc != null) {
                            e = EncodingUtilities.javaEncoding(enc);
                            e = ((e == null) ? enc : e);
                        }
                        InputStream is = purl.openStream();
// is                   0	: [('is', 0.7055929569129189), ('streamB', 0.3168355973343361), ('seqStream', 0.13367021521437802), ('gen', 0.12153108685254635), ('pbis', 0.04514096844183723), ('url', 0.035108226315579735), ('refStream', 0.023882554231285474), ('newStream', 0.0178862763487356), ('is1', 0.01736089370048048), ('refIS', 0.016835662045919183)]
                        Reader r;
// r                    0	: [('r', 0.9078842443671706), ('enc', 0.8750485830140886), ('report', 0.5107345723713199), ('s', 0.14971831302634053), ('st', 0.1475534161012687), ('file', 0.14670255242346167), ('orient', 0.1466907031470763), ('pbis', 0.03125350214648677), ('reader', 0.029580287534312907), ('in', 0.022608742017683137)]
                        if (e == null) {
                            r = new InputStreamReader(is);
                        } else {
                            try {
                                r = new InputStreamReader(is, e);
                            } catch (UnsupportedEncodingException uee) {
// uee                  1	: [('e', 0.6209280991109576), ('uee', 0.3479176892604082), ('ctx', 6.115973057198469e-05), ('node', 2.796351336182326e-05), ('ret', 1.9011966810675116e-05), ('uri', 1.4568548522635315e-05), ('chunk', 9.86629033432135e-06), ('ps', 8.572090832950533e-06), ('p1', 6.048401805277442e-06), ('filteredElement', 5.811131896692792e-06)]
                                r = new InputStreamReader(is);
                            }
                        }
                        r = new BufferedReader(r);
                        final StringBuffer sb = new StringBuffer();
// sb                   0	: [('sb', 0.9494768282259667), ('as', 0.08718022322146853), ('id', 0.04046650331621754), ('t', 0.036555924214271965), ('file', 0.036228342103685626), ('firstValue', 0.03622247305525386), ('value', 0.03497610681022821), ('sbuffer', 0.034842317423183805), ('result', 0.024967530788316514), ('i', 0.018911016788284166)]
                        int read;
// read                 0	: [('read', 0.5105219912104523), ('buf', 0.2518542138325859), ('imgDataTmp', 0.12776214821154583), ('imgData', 0.12730738397093677), ('n', 0.08159349434302077), ('c', 0.06429681809117793), ('k', 0.06352703545273626), ('offset', 0.0635262957210041), ('nAttr', 0.0633440486047284), ('textIndex', 0.06332852289967822)]
                        char[] buf = new char[4096];
// buf                  0	: [('buf', 0.7746870837565787), ('lv', 0.20273208222831607), ('read', 0.18888461710829765), ('n', 0.13109534072322557), ('imgDataTmp', 0.12776214821440451), ('imgData', 0.12730738397826222), ('buffer', 0.11085830643219674), ('iccc', 0.08507756973051492), ('nbytes', 0.0630466842706018), ('value', 0.060869563793596836)]
                        while ((read = r.read(buf, 0, buf.length)) != -1) {
                            sb.append(buf, 0, read);
                        }
                        r.close();
                        updateRunnableQueue.invokeLater(new Runnable() {

                            public void run() {
                                try {
                                    h.getURLDone(true, purl.getContentType(), sb.toString());
                                } catch (Exception e) {
// e                    0	: [('e', 0.6624917480000742), ('ex', 0.28332814061766887), ('de', 0.10109625109597399), ('se', 0.022933801906127593), ('te', 0.0076786890772948336), ('be', 0.007371870040581034), ('exc', 0.0030875480998947746), ('message', 0.00247445142287432), ('i', 0.0008401699625636016), ('exception', 0.0007789725468740477)]
                                    if (userAgent != null) {
                                        userAgent.displayError(e);
                                    }
                                }
                            }
                        });
                    } catch (Exception e) {
// e                    0	: [('e', 0.6624916848418264), ('ex', 0.28332814073273077), ('de', 0.10302788712002198), ('ctx', 0.09378491818993068), ('n', 0.02591349130728861), ('se', 0.02355587425456947), ('node', 0.010466643151844401), ('i', 0.008511051807374121), ('te', 0.007678689089801559), ('be', 0.007470731146394711)]
                        if (e instanceof SecurityException) {
                            userAgent.displayError(e);
                        }
                        updateRunnableQueue.invokeLater(new Runnable() {

                            public void run() {
                                try {
                                    h.getURLDone(false, null, null);
                                } catch (Exception e) {
// e                    0	: [('e', 0.6624917480000742), ('ex', 0.28332814061766887), ('de', 0.10109625109597399), ('se', 0.022933801906127593), ('te', 0.0076786890772948336), ('be', 0.007371870040581034), ('exc', 0.0030875480998947746), ('message', 0.00247445142287432), ('i', 0.0008401699625636016), ('exception', 0.0007789725468740477)]
                                    if (userAgent != null) {
                                        userAgent.displayError(e);
                                    }
                                }
                            }
                        });
                    }
                }
            };
            t.setPriority(Thread.MIN_PRIORITY);
            t.start();
        }

        public void postURL(String uri, String content, org.apache.batik.bridge.Window.URLResponseHandler h) {
// uri                  0	: [('uri', 0.9120106986391386), ('prefix', 0.014567141414352417), ('name', 0.00602529632094221), ('s', 0.005274944999863977), ('ns', 0.004680661121357834), ('namespaceURI', 0.003607330685505716), ('key', 0.002472306515750535), ('message', 0.002041725348313338), ('text', 0.0017858044478761938), ('value', 0.0017777260318390879)]
// content              0	: [('content', 0.8018307562106006), ('name', 0.3153946560902897), ('localName', 0.10553262747184008), ('rgb', 0.03816182996215111), ('message', 0.01957085208864041), ('rgbColor', 0.018914225114003817), ('media', 0.01733980984846459), ('document', 0.015965606462442453), ('is', 0.00900507317389116), ('r', 0.008159732287548578)]
// h                    0	: [('h', 0.8925677959887379), ('fw', 0.4035335037936001), ('bound', 0.08558274721890002), ('urlHandler', 0.025402067885202665), ('y', 0.0007148198105042823), ('e', 0.00047117422863002733), ('ctx', 0.0004411959289942069), ('i', 0.00042064840659096795), ('w', 0.0003142337097170159), ('s', 0.0002830217671253972)]
            postURL(uri, content, h, "text/plain", null);
        }

        public void postURL(String uri, String content, org.apache.batik.bridge.Window.URLResponseHandler h, String mimeType) {
// uri                  0	: [('uri', 0.9120106986391386), ('prefix', 0.014567141414352417), ('name', 0.00602529632094221), ('s', 0.005274944999863977), ('ns', 0.004680661121357834), ('namespaceURI', 0.003607330685505716), ('key', 0.002472306515750535), ('message', 0.002041725348313338), ('text', 0.0017858044478761938), ('value', 0.0017777260318390879)]
// content              0	: [('content', 0.8018307562106006), ('name', 0.3153946560902897), ('localName', 0.10553262747184008), ('rgb', 0.03816182996215111), ('message', 0.01957085208864041), ('rgbColor', 0.018914225114003817), ('media', 0.01733980984846459), ('document', 0.015965606462442453), ('is', 0.00900507317389116), ('r', 0.008159732287548578)]
// h                    0	: [('h', 0.8925677959887379), ('fw', 0.4035335037936001), ('bound', 0.08558274721890002), ('urlHandler', 0.025402067885202665), ('y', 0.0007148198105042823), ('e', 0.00047117422863002733), ('ctx', 0.0004411959289942069), ('i', 0.00042064840659096795), ('w', 0.0003142337097170159), ('s', 0.0002830217671253972)]
// mimeType             0	: [('mimeType', 0.7558933715094897), ('enc', 0.29190676689883244), ('data', 0.028571102940867666), ('ctx', 0.025209736509262393), ('b', 0.011901447731982179), ('m', 0.011823282543546709), ('bands', 0.011777317397073644), ('xloc', 0.011774076311811226), ('srcPixels', 0.011765613021439674), ('aPAR', 0.011762182252411707)]
            postURL(uri, content, h, mimeType, null);
        }

        public void postURL(final String uri, final String content, final org.apache.batik.bridge.Window.URLResponseHandler h, final String mimeType, final String fEnc) {
// uri                  4	: [('purl', 0.3291527378429979), ('script', 0.32390008203137793), ('svgDoc', 0.30970938113880453), ('TEST_URI', 0.11941771812115662), ('uri', 0.0939665195028249), ('url', 0.08737428657155116), ('name', 0.08601714885178598), ('scriptStr', 0.07618505609746767), ('flURL', 0.06940980172467562), ('s', 0.02755770239074705)]
// content              2	: [('tagName', 0.6738795982493552), ('data', 0.1101742735470585), ('content', 0.10537018259490427), ('mime', 0.10052388879995296), ('uri', 0.07271015350845822), ('name', 0.06004383569660476), ('mimeType', 0.05060037501527025), ('enc', 0.050355554598544204), ('description', 0.050281526064401505), ('fEnc', 0.05022839895270362)]
// h                    0	: [('h', 0.8925677745774017), ('writer', 0.16985685205371914), ('fw', 0.050803479876885015), ('urlHandler', 0.025402067887882653), ('generatorContext', 0.018429069329698386), ('at', 0.016006329192557917), ('distream', 0.00614342762936009), ('s', 0.00611638523307328), ('is', 0.004389335745025667), ('cl', 0.004271905116419105)]
// mimeType             No	: [('enc', 0.7625888824999391), ('content', 0.03888984348523491), ('mime', 0.025130966050291272), ('uri', 0.01818181676752515), ('name', 0.015010780582939158), ('description', 0.012570377826282196), ('fEnc', 0.012557097893266814), ('methodName', 0.012552692819098706), ('y', 0.011437116968068517), ('script', 0.009546449063193118)]
// fEnc                 No	: [('content', 0.1555594182187578), ('mime', 0.10052388879995296), ('enc', 0.10003526463764044), ('uri', 0.07272777626500944), ('name', 0.06004383569660476), ('mimeType', 0.050582752258719034), ('description', 0.050281526064401505), ('purl', 0.05025381737890135), ('methodName', 0.0502107761961524), ('script', 0.03818585774974214)]
            Thread t = new Thread() {
// t                    0	: [('t', 0.8766288785646403), ('main', 0.2888576737842726), ('currentThread', 0.26389401464489626), ('script', 0.14351176031208185), ('toJoin', 0.01389315183093547), ('old', 0.006957508088890614), ('thisThread', 0.006948534823698876), ('g', 0.003584286771563362), ('i', 0.003058165131360718), ('r', 0.0019592503724588364)]

                public void run() {
                    try {
                        String base = document.getDocumentURI();
// base                 1	: [('url', 0.5497485731431022), ('base', 0.5002927477746094), ('s', 0.18363786199973112), ('purl', 0.18330547568599592), ('ns', 0.15732221005919392), ('href', 0.07681721444240329), ('listenerNS', 0.07668403890788811), ('furl', 0.07668397419684105), ('uri', 0.07501980281770204), ('parser', 0.07349086400140634)]
                        URL url;
// url                  0	: [('url', 0.5441966026455288), ('base', 0.3351901749502417), ('classesURL', 0.2581677009982241), ('res', 0.05035213007859889), ('refImgURL', 0.03266557438296157), ('validRefImageURL', 0.03266505669029707), ('t', 0.023121458065006577), ('n', 0.021143056491689665), ('at', 0.019315253990771466), ('result', 0.016879726984312622)]
                        if (base == null) {
                            url = new URL(uri);
                        } else {
                            url = new URL(new URL(base), uri);
                        }
                        final URLConnection conn = url.openConnection();
// conn                 1	: [('purl', 0.8763424669566384), ('conn', 0.8750039178214652), ('uri', 0.3118011418169435), ('u', 0.15588408373556792), ('cls', 0.055891597877200924), ('report', 0.03988593139528294), ('dialog', 0.03282532782933205), ('fileChooser', 0.032732317389302205), ('ret', 0.029311915757159836), ('inIS', 0.028414749366814738)]
                        conn.setDoOutput(true);
                        conn.setDoInput(true);
                        conn.setUseCaches(false);
                        conn.setRequestProperty("Content-Type", mimeType);
                        OutputStream os = conn.getOutputStream();
// os                   3	: [('tos', 0.617264318733279), ('cs', 0.458370259821358), ('ios', 0.4166695963017564), ('os', 0.3515727166228146), ('out', 0.3437783364664248), ('newStream', 0.17810792488628718), ('bos', 0.11726840656995102), ('ostream', 0.1171951280737274), ('tmp', 0.05945413541396528), ('stream', 0.05942711956246452)]
                        String e = null, enc = fEnc;
// e                    0	: [('e', 0.5505551758356652), ('enc', 0.4196513505195422), ('params', 0.34394074726503837), ('w', 0.30027132065841533), ('r', 0.30027087726165036), ('buf', 0.28132403849650317), ('ex', 0.23336128411343263), ('s', 0.2121187183101853), ('x', 0.2037080888251449), ('de', 0.10109625359861796)]
// enc                  0	: [('enc', 0.9375039241336429), ('fSum', 0.12992704917164263), ('n', 0.07808835286050023), ('purl', 0.05580939925037601), ('fEnc', 0.0555729652701282), ('ret', 0.04998008679817803), ('lu', 0.047302465608727556), ('elt', 0.0422747009999573), ('c1', 0.039922387035617034), ('nextValue1', 0.03989579108593735)]
                        if (enc != null) {
                            if (enc.startsWith(DEFLATE)) {
                                os = new DeflaterOutputStream(os);
                                if (enc.length() > DEFLATE.length())
                                    enc = enc.substring(DEFLATE.length() + 1);
                                else
                                    enc = "";
                                conn.setRequestProperty("Content-Encoding", DEFLATE);
                            }
                            if (enc.startsWith(GZIP)) {
                                os = new GZIPOutputStream(os);
                                if (enc.length() > GZIP.length())
                                    enc = enc.substring(GZIP.length() + 1);
                                else
                                    enc = "";
                                conn.setRequestProperty("Content-Encoding", DEFLATE);
                            }
                            if (enc.length() != 0) {
                                e = EncodingUtilities.javaEncoding(enc);
                                if (e == null)
                                    e = UTF_8;
                            } else {
                                e = UTF_8;
                            }
                        }
                        Writer w;
// w                    7	: [('e', 0.3004169159541602), ('r', 0.3002708971265188), ('writer', 0.26005329089792545), ('securityEnforcer', 0.1364189702426276), ('bos', 0.13641598454237672), ('outputStream', 0.1364157256917563), ('fSum', 0.12888196769009727), ('w', 0.12042534552299927), ('os', 0.06826421462984052), ('ostream', 0.06823675866882495)]
                        if (e == null)
                            w = new OutputStreamWriter(os);
                        else
                            w = new OutputStreamWriter(os, e);
                        w.write(content);
                        w.flush();
                        w.close();
                        os.close();
                        InputStream is = conn.getInputStream();
// is                   0	: [('is', 0.7280652985214227), ('cis', 0.27790453291303646), ('istream', 0.1808068480736536), ('streamA', 0.1389532243402848), ('gen', 0.106690481545876), ('pbis', 0.041839627967121436), ('url', 0.03232119239799496), ('reference', 0.02790561143048741), ('infStream', 0.027904576053734517), ('iis', 0.027904446631640406)]
                        Reader r;
// r                    0	: [('r', 0.9064217168230595), ('e', 0.30041691673087284), ('w', 0.30027134093058294), ('fSum', 0.12888196780802466), ('report', 0.06463947683405923), ('reader', 0.05916057505468871), ('ret', 0.04961327776143377), ('in', 0.04521748393530501), ('elt', 0.03145716013158651), ('oldPI', 0.031395895980534196)]
                        e = UTF_8;
                        if (e == null)
                            r = new InputStreamReader(is);
                        else
                            r = new InputStreamReader(is, e);
                        r = new BufferedReader(r);
                        final StringBuffer sb = new StringBuffer();
// sb                   0	: [('sb', 0.9494768282259667), ('as', 0.08718022322146853), ('id', 0.04046650331621754), ('t', 0.036555924214271965), ('file', 0.036228342103685626), ('firstValue', 0.03622247305525386), ('value', 0.03497610681022821), ('sbuffer', 0.034842317423183805), ('result', 0.024967530788316514), ('i', 0.018911016788284166)]
                        int read;
// read                 0	: [('read', 0.5105219912104523), ('buf', 0.2518542138325859), ('imgDataTmp', 0.12776214821154583), ('imgData', 0.12730738397093677), ('n', 0.08159349434302077), ('c', 0.06429681809117793), ('k', 0.06352703545273626), ('offset', 0.0635262957210041), ('nAttr', 0.0633440486047284), ('textIndex', 0.06332852289967822)]
                        char[] buf = new char[4096];
// buf                  0	: [('buf', 0.7746870837565787), ('lv', 0.20273208222831607), ('read', 0.18888461710829765), ('n', 0.13109534072322557), ('imgDataTmp', 0.12776214821440451), ('imgData', 0.12730738397826222), ('buffer', 0.11085830643219674), ('iccc', 0.08507756973051492), ('nbytes', 0.0630466842706018), ('value', 0.060869563793596836)]
                        while ((read = r.read(buf, 0, buf.length)) != -1) {
                            sb.append(buf, 0, read);
                        }
                        r.close();
                        updateRunnableQueue.invokeLater(new Runnable() {

                            public void run() {
                                try {
                                    h.getURLDone(true, conn.getContentType(), sb.toString());
                                } catch (Exception e) {
// e                    0	: [('e', 0.6624917480000742), ('ex', 0.28332814061766887), ('de', 0.10109625109597399), ('se', 0.022933801906127593), ('te', 0.0076786890772948336), ('be', 0.007371870040581034), ('exc', 0.0030875480998947746), ('message', 0.00247445142287432), ('i', 0.0008401699625636016), ('exception', 0.0007789725468740477)]
                                    if (userAgent != null) {
                                        userAgent.displayError(e);
                                    }
                                }
                            }
                        });
                    } catch (Exception e) {
// e                    0	: [('e', 0.6624916848418264), ('ex', 0.28332814073273077), ('de', 0.10302788712002198), ('ctx', 0.09378491818993068), ('n', 0.02591349130728861), ('se', 0.02355587425456947), ('node', 0.010466643151844401), ('i', 0.008511051807374121), ('te', 0.007678689089801559), ('be', 0.007470731146394711)]
                        if (e instanceof SecurityException) {
                            userAgent.displayError(e);
                        }
                        updateRunnableQueue.invokeLater(new Runnable() {

                            public void run() {
                                try {
                                    h.getURLDone(false, null, null);
                                } catch (Exception e) {
// e                    0	: [('e', 0.6624917480000742), ('ex', 0.28332814061766887), ('de', 0.10109625109597399), ('se', 0.022933801906127593), ('te', 0.0076786890772948336), ('be', 0.007371870040581034), ('exc', 0.0030875480998947746), ('message', 0.00247445142287432), ('i', 0.0008401699625636016), ('exception', 0.0007789725468740477)]
                                    if (userAgent != null) {
                                        userAgent.displayError(e);
                                    }
                                }
                            }
                        });
                    }
                }
            };
            t.setPriority(Thread.MIN_PRIORITY);
            t.start();
        }

        public void alert(String message) {
// message              0	: [('message', 0.8770136154906365), ('prefix', 0.014567141414352417), ('name', 0.00602529632094221), ('s', 0.005274944999863977), ('ns', 0.004680661121357834), ('namespaceURI', 0.003607330685505716), ('i', 0.0033606798502544065), ('uri', 0.0031008096159259365), ('key', 0.002472306515750535), ('e', 0.0019101248361924358)]
            if (userAgent != null) {
                userAgent.showAlert(message);
            }
        }

        public boolean confirm(String message) {
// message              0	: [('message', 0.8770136154906365), ('prefix', 0.014567141414352417), ('name', 0.00602529632094221), ('s', 0.005274944999863977), ('ns', 0.004680661121357834), ('namespaceURI', 0.003607330685505716), ('i', 0.0033606798502544065), ('uri', 0.0031008096159259365), ('key', 0.002472306515750535), ('e', 0.0019101248361924358)]
            if (userAgent != null) {
                return userAgent.showConfirm(message);
            }
            return false;
        }

        public String prompt(String message) {
// message              0	: [('message', 0.8934013327784845), ('prefix', 0.014567483337503758), ('name', 0.00602547466215424), ('s', 0.005275242645197158), ('ns', 0.004680837002691078), ('namespaceURI', 0.0036074426099905075), ('uri', 0.003100936914653144), ('key', 0.0024723889216898866), ('text', 0.001785861025088286), ('value', 0.0017778188922632828)]
            if (userAgent != null) {
                return userAgent.showPrompt(message);
            }
            return null;
        }

        public String prompt(String message, String defVal) {
// message              0	: [('message', 0.8934013327784845), ('prefix', 0.014567483337503758), ('name', 0.00602547466215424), ('s', 0.005275242645197158), ('ns', 0.004680837002691078), ('namespaceURI', 0.0036074426099905075), ('uri', 0.003100936914653144), ('key', 0.0024723889216898866), ('text', 0.001785861025088286), ('value', 0.0017778188922632828)]
// defVal               1	: [('defaultValue', 0.7845436907822774), ('defVal', 0.218828896314311), ('lineno', 0.06908189182643718), ('ln', 0.005022121809279677), ('ns', 0.004020195482791687), ('value', 0.003470482176006027), ('s', 0.0034020377641212126), ('prefix', 0.002821210182872282), ('oldv', 0.0024772521611724926), ('uri', 0.002442526422097224)]
            if (userAgent != null) {
                return userAgent.showPrompt(message, defVal);
            }
            return null;
        }

        public BridgeContext getBridgeContext() {
            return bridgeContext;
        }

        public Interpreter getInterpreter() {
            return interpreter;
        }

        public org.apache.batik.w3c.dom.Window getParent() {
            return null;
        }

        public org.apache.batik.w3c.dom.Location getLocation() {
            if (location == null) {
                location = new Location(bridgeContext);
            }
            return location;
        }
    }

    protected class DOMNodeInsertedListener implements EventListener {

        protected LinkedList toExecute = new LinkedList();

        public void handleEvent(Event evt) {
// evt                  0	: [('evt', 0.8889013917499144), ('i', 0.2252371297659677), ('n1', 0.11601323050497785), ('sr', 0.11248034036833429), ('e', 0.07797486059851706), ('mevt', 0.0748226703666413), ('target', 0.036579890745161), ('aNodeList', 0.016011179865631), ('et', 0.013609275027116577), ('aChildren', 0.012475375546244831)]
            Node n = (Node) evt.getTarget();
// n                    1	: [('node', 0.4712181397436243), ('n', 0.33679590519175495), ('shadowTree', 0.16671082708090326), ('childNode', 0.11952963927479185), ('parent', 0.03311531093981446), ('attr', 0.016549254974352678), ('child', 0.012986339856674644), ('t', 0.011667909550268824), ('m', 0.010687882404985086), ('result', 0.010674997133782553)]
            addScriptingListeners(n);
            gatherScriptElements(n);
            while (!toExecute.isEmpty()) {
                loadScript((AbstractElement) toExecute.removeFirst());
            }
        }

        protected void gatherScriptElements(Node n) {
// n                    0	: [('n', 0.5034474391648449), ('next', 0.2890252323383843), ('t', 0.2745916915570734), ('ev', 0.2502969463314012), ('i', 0.1859862654524538), ('other', 0.1290662670264721), ('ns', 0.08898354212814696), ('p', 0.07292838795536864), ('iter', 0.071152248424113), ('node', 0.05529612134903618)]
            if (n.getNodeType() == Node.ELEMENT_NODE) {
                if (n instanceof SVGOMScriptElement) {
                    toExecute.add(n);
                } else {
                    n = n.getFirstChild();
                    while (n != null) {
                        gatherScriptElements(n);
                        n = n.getNextSibling();
                    }
                }
            }
        }
    }

    protected class DOMNodeRemovedListener implements EventListener {

        public void handleEvent(Event evt) {
// evt                  0	: [('evt', 0.8889013917499144), ('target', 0.3100018867534799), ('e', 0.07797486059851706), ('relatedTarget', 0.04275563326868499), ('mevt', 0.042502483590425445), ('node', 0.03357226155152499), ('i', 0.014759973817649636), ('n1', 0.014169318152812816), ('aNodeList', 0.014165216874119143), ('et', 0.0093614071970903)]
            removeScriptingListeners((Node) evt.getTarget());
        }
    }

    protected class DOMAttrModifiedListener implements EventListener {

        public void handleEvent(Event evt) {
// evt                  0	: [('evt', 0.9375802624605469), ('e', 0.07797486059851706), ('i', 0.0002995673284052374), ('n', 0.00026903186833254544), ('doc', 0.00013984232606350342), ('o', 8.955752150374016e-05), ('node', 8.802624871060273e-05), ('ctx', 6.11602292205957e-05), ('ret', 5.283372486070969e-05), ('result', 5.140199121905934e-05)]
            MutationEvent me = (MutationEvent) evt;
// me                   2	: [('mevt', 0.7519836292314979), ('n', 0.3677111350738132), ('me', 0.34225763546144106), ('node', 0.09378263428887727), ('target', 0.09132876357443306), ('evt', 0.04183671197987929), ('child', 0.03446369228508906), ('m', 0.0231589964442407), ('p', 0.015675392681887404), ('parentNode', 0.014114016852949017)]
            if (me.getAttrChange() != MutationEvent.MODIFICATION)
                updateScriptingListeners((Element) me.getTarget(), me.getAttrName());
        }
    }

    protected class ScriptingEventListener implements EventListener {

        protected String attribute;

        public ScriptingEventListener(String attr) {
// attr                 No	: [('prefix', 0.11653713131481933), ('name', 0.04820237056753768), ('s', 0.04219955999891182), ('ns', 0.037445288970862675), ('namespaceURI', 0.028858645484045726), ('uri', 0.024806476927407492), ('key', 0.01977845212600428), ('message', 0.016333802786506705), ('text', 0.014286435583009552), ('value', 0.014221808254712703)]
            attribute = attr;
        }

        public void handleEvent(Event evt) {
// evt                  0	: [('evt', 0.8889013703292876), ('n', 0.13435306879756695), ('to', 0.13136506129885506), ('e', 0.0779748613755356), ('node', 0.026617681717425776), ('child', 0.019138976178071018), ('sr', 0.01308106302908977), ('next', 0.011644525403877642), ('ps', 0.011325004737459246), ('element', 0.01090575067248854)]
            Element elt = (Element) evt.getCurrentTarget();
// elt                  2	: [('doc', 0.674831309872185), ('script', 0.4178734803897549), ('elt', 0.40907998237440624), ('e', 0.3401165609581789), ('target', 0.23200568043363598), ('currentTarget', 0.23054961165071672), ('ctx', 0.1892642888234623), ('s', 0.1264118511122936), ('elem', 0.07108562553964828), ('entry', 0.06258037363150809)]
            String script = elt.getAttributeNS(null, attribute);
// script               0	: [('script', 0.6875618131558361), ('value', 0.5015247145031164), ('s', 0.03830325037834686), ('namespaceURI', 0.019290427622462112), ('ln', 0.018408586762024528), ('text', 0.016041462661201076), ('prefix', 0.011542056790353347), ('details', 0.011425088325157068), ('name', 0.010844690545390431), ('val', 0.010274632782081539)]
            if (script.length() == 0)
                return;
            DocumentLoader dl = bridgeContext.getDocumentLoader();
// dl                   0	: [('dl', 0.9276055128134996), ('loader', 0.03177243832221046), ('line', 0.019560613780092356), ('ctx', 0.015904612545494273), ('br', 0.007815980480383568), ('reader', 0.003911444752269479), ('lineno', 0.003909644199702157), ('documentLoader', 0.003126173585470078), ('l', 0.0015760980035931626), ('newDocumentLoader', 0.0015635010241072573)]
            SVGDocument d = (SVGDocument) elt.getOwnerDocument();
// d                    1	: [('svgDoc', 0.36375638297770263), ('d', 0.3422228956411967), ('uri', 0.11551103764545498), ('doc', 0.11496341245306595), ('rdoc', 0.08759574113414494), ('attrName', 0.06376076854645712), ('name', 0.052957069351687765), ('u', 0.052139276422839775), ('attr', 0.03721802271285252), ('uriStr', 0.03368329931699386)]
            int line = dl.getLineNumber(elt);
// line                 0	: [('line', 0.5033748953836177), ('len', 0.025768876774471476), ('i', 0.02377202824637298), ('w', 0.01914880683154465), ('n', 0.019093493677847156), ('y0', 0.014604077125857103), ('h', 0.012370962956474808), ('length', 0.01223756324393363), ('y', 0.011437116968068517), ('y1', 0.011360669095949034)]
            final String desc = Messages.formatMessage(EVENT_SCRIPT_DESCRIPTION, new Object[] { d.getURL(), attribute, line });
// desc                 0	: [('desc', 0.8006140518797178), ('horizOrigin', 0.10119434930408375), ('uri', 0.07707961831238552), ('s', 0.06407896766827105), ('errCode', 0.053389200507968304), ('localName', 0.04515519541176181), ('title', 0.044967313464044374), ('content', 0.039929653348574186), ('script', 0.009546449063193118), ('docPURL', 0.008938231861520313)]
            Element e = elt;
// e                    0	: [('e', 0.7535292887565299), ('root', 0.45843544925095114), ('report', 0.3413444379751934), ('elt', 0.3244286885731596), ('fontFaceElement', 0.2312542259182784), ('glyphElement', 0.22921401286620396), ('ns', 0.1635422817208641), ('realRoot', 0.16153133206166836), ('tag', 0.078360904640943), ('namespaceURI', 0.06785252414696481)]
            while (e != null && (!SVGConstants.SVG_NAMESPACE_URI.equals(e.getNamespaceURI()) || !SVGConstants.SVG_SVG_TAG.equals(e.getLocalName()))) {
                e = SVGUtilities.getParentElement(e);
            }
            if (e == null)
                return;
            String lang = e.getAttributeNS(null, SVGConstants.SVG_CONTENT_SCRIPT_TYPE_ATTRIBUTE);
// lang                 0	: [('lang', 0.797942650860086), ('eNS', 0.5006545127429469), ('str', 0.20983786936347237), ('cancelable', 0.031951189489596875), ('s', 0.019183017601473763), ('lockState', 0.015977498377213552), ('ln', 0.007887011565355557), ('prefix', 0.007777760619416562), ('ns', 0.0050885184319560154), ('name', 0.004525748553532516)]
            runEventHandler(script, evt, lang, desc);
        }
    }
}
