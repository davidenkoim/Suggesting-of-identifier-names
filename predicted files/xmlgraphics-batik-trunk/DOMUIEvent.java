// Type of training: selfTestingIdentifier
// Path to file: C:\Users\Igor\IdeaProjects\SLP-Core-0.3\java-projects\xmlgraphics-batik-trunk\batik-dom\src\main\java\org\apache\batik\dom\events\DOMUIEvent.java
// Number of identifiers: 17	Accuracy: 88.24%	MRR: 89.41%
// True 	Rank of true in predictions : [(predicted token, probability of a token), ...]

package org.apache.batik.dom.events;

import java.util.ArrayList;
import java.util.List;
import org.apache.batik.xml.XMLUtilities;
import org.w3c.dom.events.UIEvent;
import org.w3c.dom.views.AbstractView;

public class DOMUIEvent extends AbstractEvent implements UIEvent {

    private AbstractView view;

    private int detail;

    public AbstractView getView() {
        return view;
    }

    public int getDetail() {
        return detail;
    }

    public void initUIEvent(String typeArg, boolean canBubbleArg, boolean cancelableArg, AbstractView viewArg, int detailArg) {
// typeArg              0	: [('typeArg', 0.9125146873559881), ('prefix', 0.11653713131481933), ('name', 0.04820237056753768), ('s', 0.04219955999891182), ('ns', 0.037445288970862675), ('namespaceURI', 0.028858645484045726), ('uri', 0.024806476927407492), ('key', 0.01977845212600428), ('message', 0.016333802786506705), ('text', 0.014286435583009552)]
// canBubbleArg         0	: [('canBubbleArg', 0.9103272246827352), ('cancelableArg', 0.005997840298622662), ('isCSS', 0.004958779157606282), ('sweepFlag', 0.0037700155631919335), ('largeArcFlag', 0.0037700155631919335), ('b', 0.00372232322478765), ('isBegin', 0.0031552099904439664), ('useCapture', 0.002694360282295038), ('specified', 0.0025712352344507646), ('important', 0.001569091785870187)]
// cancelableArg        0	: [('cancelableArg', 0.9062801109719997), ('canBubbleArg', 0.0061132391799185145), ('isCSS', 0.004965288012076248), ('sweepFlag', 0.0037748795346513276), ('largeArcFlag', 0.0037748795346513276), ('b', 0.0037267175060978477), ('isBegin', 0.003159327553103457), ('useCapture', 0.0026978969954822934), ('specified', 0.0025746226658780397), ('important', 0.0015710161711822482)]
// viewArg              0	: [('viewArg', 0.9678050019848035), ('av', 0.0009484021244840059), ('i', 0.0005025473531734412), ('e', 0.00048073670928962367), ('n', 0.00040837616375652), ('ctx', 0.0002796125454942746), ('x', 0.00024127393377829803), ('t', 0.00023954741543578606), ('raf', 0.0002339246980590429), ('y', 0.00021346177379430987)]
// detailArg            0	: [('detailArg', 0.9375059815068302), ('wheelDeltaArg', 0.25009137590060326), ('y', 0.006848760181992624), ('i', 0.006523713324354609), ('width', 0.0038111038967530487), ('x', 0.0035320457873829997), ('height', 0.0031842074787853205), ('idx', 0.0024959098860809466), ('index', 0.002077828701542701), ('off', 0.0020052853243117488)]
        initEvent(typeArg, canBubbleArg, cancelableArg);
        this.view = viewArg;
        this.detail = detailArg;
    }

    public void initUIEventNS(String namespaceURIArg, String typeArg, boolean canBubbleArg, boolean cancelableArg, AbstractView viewArg, int detailArg) {
// namespaceURIArg      0	: [('namespaceURIArg', 0.9312625087644275), ('prefix', 0.11653713131481933), ('name', 0.04820237056753768), ('s', 0.04219955999891182), ('ns', 0.037445288970862675), ('namespaceURI', 0.028858645484045726), ('uri', 0.024806476927407492), ('key', 0.01977845212600428), ('message', 0.016333802786506705), ('text', 0.014286435583009552)]
// typeArg              0	: [('typeArg', 0.9134893582380148), ('eventTypeArg', 0.0875833451437895), ('ln', 0.005022121809279677), ('ns', 0.004020195482791687), ('value', 0.003470482176006027), ('s', 0.0034020377641212126), ('prefix', 0.002821210182872282), ('oldv', 0.0024772521611724926), ('uri', 0.002442526422097224), ('name', 0.0023563785783280206)]
// canBubbleArg         0	: [('canBubbleArg', 0.9103272271047889), ('cancelableArg', 0.005997840298622662), ('isCSS', 0.004958779157606282), ('sweepFlag', 0.0037700155631919335), ('largeArcFlag', 0.0037700155631919335), ('b', 0.00372232322478765), ('isBegin', 0.0031552099904439664), ('useCapture', 0.002694360282295038), ('specified', 0.0025712352344507646), ('important', 0.001569091785870187)]
// cancelableArg        0	: [('cancelableArg', 0.9062801117618686), ('canBubbleArg', 0.0061132391799185145), ('isCSS', 0.004965288012076248), ('sweepFlag', 0.0037748795346513276), ('largeArcFlag', 0.0037748795346513276), ('b', 0.0037267175060978477), ('isBegin', 0.003159327553103457), ('useCapture', 0.0026978969954822934), ('specified', 0.0025746226658780397), ('important', 0.0015710161711822482)]
// viewArg              0	: [('viewArg', 0.9678050019848035), ('av', 0.0009484021244840059), ('i', 0.0005025473531734412), ('e', 0.00048073670928962367), ('n', 0.00040837616375652), ('ctx', 0.0002796125454942746), ('x', 0.00024127393377829803), ('t', 0.00023954741543578606), ('raf', 0.0002339246980590429), ('y', 0.00021346177379430987)]
// detailArg            0	: [('detailArg', 0.9375059815068302), ('wheelDeltaArg', 0.25009137590060326), ('y', 0.006848760181992624), ('i', 0.006523713324354609), ('width', 0.0038111038967530487), ('x', 0.0035320457873829997), ('height', 0.0031842074787853205), ('idx', 0.0024959098860809466), ('index', 0.002077828701542701), ('off', 0.0020052853243117488)]
        initEventNS(namespaceURIArg, typeArg, canBubbleArg, cancelableArg);
        this.view = viewArg;
        this.detail = detailArg;
    }

    protected String[] split(String s) {
// s                    0	: [('s', 0.3580835463260193), ('data', 0.35554688583930805), ('valuesString', 0.1308478420620125), ('prefix', 0.11653713487101733), ('ffname', 0.09475089999768684), ('aci', 0.09263700628229835), ('attr', 0.06655773084417896), ('attrs', 0.0665168881067685), ('lv', 0.06649543431324174), ('keySplinesString', 0.0658180584988025)]
        List a = new ArrayList(8);
// a                    No	: [('v', 0.7615701079188577), ('prevEndsWithSpace', 0.7533438627077251), ('substString', 0.583443297014722), ('textRuns', 0.10361160951807304), ('numGlyphs', 0.09094301085682402), ('c', 0.07316980746339148), ('st', 0.07252225270631145), ('ret', 0.06605567278529655), ('platforms', 0.054382746459281116), ('defSet', 0.05050749227398321)]
        StringBuffer sb;
// sb                   0	: [('sb', 0.4765618271209675), ('hasChildren', 0.17080184298295115), ('node', 0.1139787367152422), ('value', 0.08140624604223855), ('currentRegion', 0.06007611968221489), ('loBound', 0.059432340857831646), ('hiBound', 0.05943231928694661), ('glBounds', 0.058182960486553435), ('i', 0.058097547443070684), ('fontFamilyList', 0.05693556387707527)]
        int i = 0;
// i                    0	: [('i', 0.8420237690290421), ('n', 0.15646997087484024), ('iter', 0.07127516496740835), ('st', 0.06042433935759837), ('s', 0.0499170190468132), ('lu', 0.0471895276086654), ('elt', 0.042182226629020776), ('it', 0.039707240620979474), ('j', 0.038613843990459175), ('next', 0.037217375225443494)]
        int len = s.length();
// len                  4	: [('rlm', 0.2960739030889438), ('ggSz', 0.29144297922765294), ('aciStart', 0.2546443151041772), ('transparentStart', 0.2546443151041772), ('len', 0.24280977734910694), ('size', 0.22456359449796706), ('data', 0.16425913489470614), ('numGlyphs', 0.08958566277811608), ('end', 0.05784292951886972), ('args', 0.04535673367273383)]
        while (i < len) {
            char c = s.charAt(i++);
// c                    0	: [('c', 0.8753375009292428), ('i', 0.26750001150143166), ('text', 0.26694358742198826), ('extensions', 0.2524298039585633), ('iccc', 0.25223183793229365), ('value', 0.1802654731251411), ('f', 0.07303946680384986), ('lv', 0.05021463053728067), ('prevChar', 0.04513615960506055), ('b', 0.044853311677442165)]
            if (XMLUtilities.isXMLSpace(c)) {
                continue;
            }
            sb = new StringBuffer();
            sb.append(c);
            while (i < len) {
                c = s.charAt(i++);
                if (XMLUtilities.isXMLSpace(c)) {
                    a.add(sb.toString());
                    break;
                }
                sb.append(c);
            }
            if (i == len) {
                a.add(sb.toString());
            }
        }
        return (String[]) a.toArray(new String[a.size()]);
    }
}
