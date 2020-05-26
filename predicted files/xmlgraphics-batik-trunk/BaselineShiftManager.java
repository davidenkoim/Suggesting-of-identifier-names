// Type of training: selfTestingIdentifier
// Path to file: C:\Users\Igor\IdeaProjects\SLP-Core-0.3\java-projects\xmlgraphics-batik-trunk\batik-css\src\main\java\org\apache\batik\css\engine\value\svg\BaselineShiftManager.java
// Number of identifiers: 18	Accuracy: 72.22%	MRR: 76.39%
// True 	Rank of true in predictions : [(predicted token, probability of a token), ...]

package org.apache.batik.css.engine.value.svg;

import org.apache.batik.css.engine.CSSEngine;
import org.apache.batik.css.engine.CSSStylableElement;
import org.apache.batik.css.engine.StyleMap;
import org.apache.batik.css.engine.value.FloatValue;
import org.apache.batik.css.engine.value.LengthManager;
import org.apache.batik.css.engine.value.StringMap;
import org.apache.batik.css.engine.value.Value;
import org.apache.batik.css.engine.value.ValueManager;
import org.apache.batik.util.CSSConstants;
import org.apache.batik.util.SVGTypes;
import org.w3c.css.sac.LexicalUnit;
import org.w3c.dom.DOMException;
import org.w3c.dom.css.CSSPrimitiveValue;

public class BaselineShiftManager extends LengthManager {

    protected static final StringMap values = new StringMap();

    static {
        values.put(CSSConstants.CSS_BASELINE_VALUE, SVGValueConstants.BASELINE_VALUE);
        values.put(CSSConstants.CSS_SUB_VALUE, SVGValueConstants.SUB_VALUE);
        values.put(CSSConstants.CSS_SUPER_VALUE, SVGValueConstants.SUPER_VALUE);
    }

    public boolean isInheritedProperty() {
        return false;
    }

    public boolean isAnimatableProperty() {
        return true;
    }

    public boolean isAdditiveProperty() {
        return false;
    }

    public int getPropertyType() {
        return SVGTypes.TYPE_BASELINE_SHIFT_VALUE;
    }

    public String getPropertyName() {
        return CSSConstants.CSS_BASELINE_SHIFT_PROPERTY;
    }

    public Value getDefaultValue() {
        return SVGValueConstants.BASELINE_VALUE;
    }

    public Value createValue(LexicalUnit lu, CSSEngine engine) throws DOMException {
// lu                   0	: [('lu', 0.9405147092413735), ('value', 0.5338865967675088), ('s', 0.42137877207811103), ('type', 0.19270874771641594), ('id', 0.06995574412302119), ('i', 0.04030680764852148), ('ns', 0.03172985847335165), ('name', 0.03129986626146004), ('val', 0.024290448343283817), ('ident', 0.02406893172958513)]
// engine               0	: [('engine', 0.9466926880779221), ('v', 0.05398954286763933), ('eng', 0.013131741497085396), ('imp', 0.003808773670725394), ('important', 0.0030349457332528623), ('srceng', 0.0017261376476646652), ('e', 0.001376817836681193), ('cssEngine', 0.001283936683142995), ('y', 0.0007148198105042823), ('result', 0.0006650204667287459)]
        switch(lu.getLexicalUnitType()) {
            case LexicalUnit.SAC_INHERIT:
                return SVGValueConstants.INHERIT_VALUE;
            case LexicalUnit.SAC_IDENT:
                Object v = values.get(lu.getStringValue().toLowerCase().intern());
// v                    0	: [('v', 0.7649502299673689), ('old', 0.10192867544596723), ('o', 0.05743505312272908), ('e', 0.048753928251716656), ('tokens', 0.03939682120027432), ('n', 0.036682056579211905), ('ri', 0.034602423333756405), ('i', 0.030363204364012377), ('obj', 0.022133877214105133), ('c', 0.02040385236979874)]
                if (v == null) {
                    throw createInvalidIdentifierDOMException(lu.getStringValue());
                }
                return (Value) v;
        }
        return super.createValue(lu, engine);
    }

    public Value createStringValue(short type, String value, CSSEngine engine) throws DOMException {
// type                 0	: [('type', 0.9116397960934237), ('lu', 0.153220052007189), ('namespaceURI', 0.07716189549488384), ('ns', 0.0385617679248065), ('arg', 0.03800930552637076), ('baseVal', 0.03800906825449688), ('newAttr', 0.03800001291666231), ('v', 0.020493612116631105), ('uri', 0.019203811268350385), ('root', 0.01912991625391491)]
// value                0	: [('value', 0.42583600447162284), ('s', 0.42137876742994096), ('key', 0.4137262954413951), ('lu', 0.3576758758930882), ('letter', 0.12179775049562412), ('id', 0.07594204884679359), ('rgb', 0.04892167627240635), ('i', 0.04030679766565574), ('ns', 0.031729856996288865), ('name', 0.03129986459624913)]
// engine               0	: [('engine', 0.9466927096073572), ('eng', 0.013131741462603425), ('srceng', 0.0017261376462353606), ('e', 0.0013768170596755289), ('cssEngine', 0.0012839366754604832), ('result', 0.0006650202759165938), ('source', 0.00032845938158677517), ('ctx', 0.00022141614082839495), ('refEngine', 0.00016127900399803103), ('node', 2.796351336182326e-05)]
        if (type != CSSPrimitiveValue.CSS_IDENT) {
            throw createInvalidIdentifierDOMException(value);
        }
        Object v = values.get(value.toLowerCase().intern());
// v                    0	: [('v', 0.7649502302611926), ('o', 0.1761187755797351), ('val', 0.12850417607429523), ('old', 0.10192867548949666), ('value', 0.0793176822747677), ('nodeInfo', 0.07777726429715603), ('oldSelection', 0.06388933118815635), ('e', 0.04875393014524704), ('tokens', 0.03939682120027432), ('n', 0.036682056579211905)]
        if (v == null) {
            throw createInvalidIdentifierDOMException(value);
        }
        return (Value) v;
    }

    public Value computeValue(CSSStylableElement elt, String pseudo, CSSEngine engine, int idx, StyleMap sm, Value value) {
// elt                  0	: [('elt', 0.9305568185707369), ('list', 0.328421484239579), ('node', 0.20285462706235802), ('parent', 0.18162795796572356), ('ss', 0.16422100287648186), ('e', 0.16110675536947783), ('src', 0.0929441427675128), ('dest', 0.09288600077768522), ('t', 0.08852217252602874), ('target', 0.0884757169875414)]
// pseudo               0	: [('pseudo', 0.7922881763516183), ('title', 0.18152038492696124), ('child', 0.17547895294080848), ('sibling', 0.0712161731439131), ('name', 0.06980271521143602), ('ns', 0.03943236853425644), ('value', 0.037193646945138666), ('pseudoElt', 0.03617267744193426), ('pn', 0.03576023087871268), ('text', 0.03439423473075116)]
// engine               0	: [('engine', 0.9466926450179817), ('ctx', 0.1808084002151226), ('result', 0.11863906135365973), ('fsidx', 0.10820041268116525), ('value', 0.07226450660892761), ('idx', 0.03680479742500658), ('ci', 0.036073229202322234), ('eng', 0.013131741566051052), ('i', 0.00804096855592811), ('e', 0.007692035899410875)]
// idx                  0	: [('idx', 0.917223969050009), ('y', 0.00684876076087054), ('i', 0.006721313466747945), ('e', 0.0038202146734309305), ('width', 0.0038111040686297634), ('ctx', 0.003790271071216143), ('x', 0.003532046371620897), ('n', 0.0034699162188891607), ('height', 0.003184207609390184), ('s', 0.0029235874358464232)]
// sm                   0	: [('sm', 0.9513233636015925), ('important', 0.015292785729644418), ('style', 0.011655104448785298), ('v', 0.008449122715696901), ('size', 0.0070290364329190615), ('c', 0.006410422825488604), ('sb', 0.006071942650895228), ('g', 0.005594552662627155), ('e', 0.005058873328951431), ('i', 0.0048648854240080615)]
// value                0	: [('value', 0.8914056566564972), ('rect', 0.2011053800485923), ('v', 0.10069198517326992), ('lv', 0.05026886730035036), ('lv1', 0.05026735736341223), ('lv2', 0.05026735736341223), ('lv3', 0.05026735736341223), ('lv4', 0.050266428707458864), ('tileGridXOff', 0.047472452664820856), ('name', 0.03541671093824915)]
        if (value.getPrimitiveType() == CSSPrimitiveValue.CSS_PERCENTAGE) {
            sm.putLineHeightRelative(idx, true);
            int fsi = engine.getLineHeightIndex();
// fsi                  No	: [('engine', 0.4686485340563602), ('ci', 0.18799458924738724), ('nSlots', 0.18764349915681658), ('skipX', 0.18763492801364665), ('imageIndex', 0.18763151733155098), ('result', 0.08294133147438955), ('idx', 0.06277728835942736), ('fsidx', 0.04134202306667516), ('i', 0.01189554368580291), ('len', 0.010329446845730384)]
            CSSStylableElement parent;
// parent               2	: [('p', 0.6315569108761709), ('elt', 0.3248694473819892), ('parent', 0.2858616345291113), ('e', 0.0625719238498573), ('element', 0.0451758420919183), ('res', 0.041326234614017916), ('s', 0.02778187172954874), ('report', 0.021469081295406922), ('n', 0.0212137152913241), ('i', 0.019908113540871127)]
            parent = (CSSStylableElement) elt.getParentNode();
            if (parent == null) {
                parent = elt;
            }
            Value fs = engine.getComputedStyle(parent, pseudo, fsi);
// fs                   5	: [('v', 0.35238702938485955), ('value', 0.2270730063519695), ('nv', 0.0810651895951923), ('val', 0.05570125005789208), ('t', 0.04146133145737957), ('fs', 0.04053985996737669), ('cursorValue', 0.02323966057535501), ('i', 0.008040901308338502), ('e', 0.007692011034611759), ('n', 0.006534208195610415)]
            float fsv = fs.getFloatValue();
// fsv                  3	: [('v', 0.8338634979672418), ('y', 0.06435431019924985), ('x', 0.058292777708074464), ('fsv', 0.041865873115738444), ('h', 0.023495235571081602), ('w', 0.022914263446572977), ('t', 0.020091584701368098), ('offset', 0.016141957574383144), ('f', 0.014190184281203213), ('cy', 0.011675902998517075)]
            float v = value.getFloatValue();
// v                    No	: [('y', 0.2162480671829863), ('h', 0.1833060257986715), ('w', 0.1807684382699367), ('x', 0.039692811651898995), ('norm', 0.032150948405708306), ('gradient', 0.021658091882948368), ('i', 0.017897990724894296), ('ratio', 0.017261969801976834), ('srcM', 0.015420735173575473), ('dstM', 0.013896982966535429)]
            return new FloatValue(CSSPrimitiveValue.CSS_NUMBER, (fsv * v) / 100f);
        }
        return super.computeValue(elt, pseudo, engine, idx, sm, value);
    }

    protected int getOrientation() {
        return BOTH_ORIENTATION;
    }
}
