// Path id: 37
// Path to file: C:\Users\Igor\IdeaProjects\SLP-Core-0.3\java-projects\xmlgraphics-batik-trunk\batik-anim\src\main\java\org\apache\batik\anim\dom\SVGOMAnimatedPathData.java
// Number of identifiers: 490	Accuracy: 43.06%	MRR: 46.79%
// True 	Rank of true in predictions : [(predicted token, probability of a token), ...]

/*

   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

 */
package org.apache.batik.anim.dom;
// anim                 1	: [('ext', 0.28252243799479004), ('anim', 0.20952748302621826), ('bridge', 0.17467316126185284), ('parser', 0.061124162852223315), ('test', 0.05945594651073573), ('transcoder', 0.04223464371832826), ('script', 0.020095533351089198), ('extension', 0.016813728417937577), ('length', 0.0008587452372703243), ('image', 0.0007329902323780028)]

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.batik.anim.values.AnimatablePathDataValue;
// anim                 1	: [('ext', 0.28252243799479004), ('anim', 0.20952748302621826), ('bridge', 0.17467316126185284), ('parser', 0.061124162852223315), ('test', 0.05945594651073573), ('transcoder', 0.04223464371832826), ('script', 0.020095533351089198), ('extension', 0.016813728417937577), ('length', 0.0008587452372703243), ('image', 0.0007329902323780028)]
// values               0	: [('values', 0.21257137935065937), ('lowerAnimation', 0.009294580914364142), ('higherAnimation', 0.006466774805569997), ('beginTime', 0.0016175468273531331), ('isDirty', 0.0012156386157317004), ('isActive', 0.0012133627515742522), ('isFrozen', 0.0012132280045106603), ('value', 0.0011972552689712655), ('length', 0.0008587452372703243), ('image', 0.0007329902323780028)]
import org.apache.batik.anim.values.AnimatableValue;
// anim                 1	: [('ext', 0.28252243799479004), ('anim', 0.20952748302621826), ('bridge', 0.17467316126185284), ('parser', 0.061124162852223315), ('test', 0.05945594651073573), ('transcoder', 0.04223464371832826), ('script', 0.020095533351089198), ('extension', 0.016813728417937577), ('length', 0.0008587452372703243), ('image', 0.0007329902323780028)]
// values               0	: [('values', 0.21257137935065937), ('lowerAnimation', 0.009294580914364142), ('higherAnimation', 0.006466774805569997), ('beginTime', 0.0016175468273531331), ('isDirty', 0.0012156386157317004), ('isActive', 0.0012133627515742522), ('isFrozen', 0.0012132280045106603), ('value', 0.0011972552689712655), ('length', 0.0008587452372703243), ('image', 0.0007329902323780028)]
import org.apache.batik.dom.svg.AbstractSVGNormPathSegList;
// svg                  0	: [('svg', 0.8914385697812067), ('svg12', 0.04476787808401432), ('xpath', 0.0017004064347546915), ('length', 0.0008587452372703243), ('image', 0.0007329902323780028), ('x', 0.000506863066592495), ('engine', 0.0004684147278846425), ('y', 0.00046674389978305306), ('ext', 0.00045404830989593744), ('res', 8.775640871660868e-06)]
import org.apache.batik.dom.svg.AbstractSVGPathSegList;
// svg                  0	: [('svg', 0.8914385697812067), ('svg12', 0.04476787808401432), ('xpath', 0.0017004064347546915), ('length', 0.0008587452372703243), ('image', 0.0007329902323780028), ('x', 0.000506863066592495), ('engine', 0.0004684147278846425), ('y', 0.00046674389978305306), ('ext', 0.00045404830989593744), ('res', 8.775640871660868e-06)]
import org.apache.batik.dom.svg.ListBuilder;
// svg                  0	: [('svg', 0.8914385697812067), ('svg12', 0.04476787808401432), ('xpath', 0.0017004064347546915), ('length', 0.0008587452372703243), ('image', 0.0007329902323780028), ('x', 0.000506863066592495), ('engine', 0.0004684147278846425), ('y', 0.00046674389978305306), ('ext', 0.00045404830989593744), ('res', 8.775640871660868e-06)]
import org.apache.batik.dom.svg.LiveAttributeException;
// svg                  0	: [('svg', 0.8914385697812067), ('svg12', 0.04476787808401432), ('xpath', 0.0017004064347546915), ('length', 0.0008587452372703243), ('image', 0.0007329902323780028), ('x', 0.000506863066592495), ('engine', 0.0004684147278846425), ('y', 0.00046674389978305306), ('ext', 0.00045404830989593744), ('res', 8.775640871660868e-06)]
import org.apache.batik.dom.svg.SVGAnimatedPathDataSupport;
// svg                  0	: [('svg', 0.8914385697812067), ('svg12', 0.04476787808401432), ('xpath', 0.0017004064347546915), ('length', 0.0008587452372703243), ('image', 0.0007329902323780028), ('x', 0.000506863066592495), ('engine', 0.0004684147278846425), ('y', 0.00046674389978305306), ('ext', 0.00045404830989593744), ('res', 8.775640871660868e-06)]
import org.apache.batik.dom.svg.SVGItem;
// svg                  0	: [('svg', 0.8914385697812067), ('svg12', 0.04476787808401432), ('xpath', 0.0017004064347546915), ('length', 0.0008587452372703243), ('image', 0.0007329902323780028), ('x', 0.000506863066592495), ('engine', 0.0004684147278846425), ('y', 0.00046674389978305306), ('ext', 0.00045404830989593744), ('res', 8.775640871660868e-06)]
import org.apache.batik.dom.svg.AbstractSVGPathSegList.SVGPathSegArcItem;
// svg                  0	: [('svg', 0.8914385697812067), ('svg12', 0.04476787808401432), ('xpath', 0.0017004064347546915), ('length', 0.0008587452372703243), ('image', 0.0007329902323780028), ('x', 0.000506863066592495), ('engine', 0.0004684147278846425), ('y', 0.00046674389978305306), ('ext', 0.00045404830989593744), ('res', 8.775640871660868e-06)]
import org.apache.batik.dom.svg.AbstractSVGPathSegList.SVGPathSegCurvetoCubicItem;
// svg                  0	: [('svg', 0.8914385697812067), ('svg12', 0.04476787808401432), ('xpath', 0.0017004064347546915), ('length', 0.0008587452372703243), ('image', 0.0007329902323780028), ('x', 0.000506863066592495), ('engine', 0.0004684147278846425), ('y', 0.00046674389978305306), ('ext', 0.00045404830989593744), ('res', 8.775640871660868e-06)]
import org.apache.batik.dom.svg.AbstractSVGPathSegList.SVGPathSegCurvetoCubicSmoothItem;
// svg                  0	: [('svg', 0.8914385697812067), ('svg12', 0.04476787808401432), ('xpath', 0.0017004064347546915), ('length', 0.0008587452372703243), ('image', 0.0007329902323780028), ('x', 0.000506863066592495), ('engine', 0.0004684147278846425), ('y', 0.00046674389978305306), ('ext', 0.00045404830989593744), ('res', 8.775640871660868e-06)]
import org.apache.batik.dom.svg.AbstractSVGPathSegList.SVGPathSegCurvetoQuadraticItem;
// svg                  0	: [('svg', 0.8914385697812067), ('svg12', 0.04476787808401432), ('xpath', 0.0017004064347546915), ('length', 0.0008587452372703243), ('image', 0.0007329902323780028), ('x', 0.000506863066592495), ('engine', 0.0004684147278846425), ('y', 0.00046674389978305306), ('ext', 0.00045404830989593744), ('res', 8.775640871660868e-06)]
import org.apache.batik.dom.svg.AbstractSVGPathSegList.SVGPathSegCurvetoQuadraticSmoothItem;
// svg                  0	: [('svg', 0.8914385697812067), ('svg12', 0.04476787808401432), ('xpath', 0.0017004064347546915), ('length', 0.0008587452372703243), ('image', 0.0007329902323780028), ('x', 0.000506863066592495), ('engine', 0.0004684147278846425), ('y', 0.00046674389978305306), ('ext', 0.00045404830989593744), ('res', 8.775640871660868e-06)]
import org.apache.batik.dom.svg.AbstractSVGPathSegList.SVGPathSegLinetoHorizontalItem;
// svg                  0	: [('svg', 0.8914385697812067), ('svg12', 0.04476787808401432), ('xpath', 0.0017004064347546915), ('length', 0.0008587452372703243), ('image', 0.0007329902323780028), ('x', 0.000506863066592495), ('engine', 0.0004684147278846425), ('y', 0.00046674389978305306), ('ext', 0.00045404830989593744), ('res', 8.775640871660868e-06)]
import org.apache.batik.dom.svg.AbstractSVGPathSegList.SVGPathSegLinetoVerticalItem;
// svg                  0	: [('svg', 0.8914385697812067), ('svg12', 0.04476787808401432), ('xpath', 0.0017004064347546915), ('length', 0.0008587452372703243), ('image', 0.0007329902323780028), ('x', 0.000506863066592495), ('engine', 0.0004684147278846425), ('y', 0.00046674389978305306), ('ext', 0.00045404830989593744), ('res', 8.775640871660868e-06)]
import org.apache.batik.dom.svg.AbstractSVGPathSegList.SVGPathSegMovetoLinetoItem;
// svg                  0	: [('svg', 0.8914385697812067), ('svg12', 0.04476787808401432), ('xpath', 0.0017004064347546915), ('length', 0.0008587452372703243), ('image', 0.0007329902323780028), ('x', 0.000506863066592495), ('engine', 0.0004684147278846425), ('y', 0.00046674389978305306), ('ext', 0.00045404830989593744), ('res', 8.775640871660868e-06)]
import org.apache.batik.dom.svg.SVGPathSegItem;
// svg                  0	: [('svg', 0.8914385697812067), ('svg12', 0.04476787808401432), ('xpath', 0.0017004064347546915), ('length', 0.0008587452372703243), ('image', 0.0007329902323780028), ('x', 0.000506863066592495), ('engine', 0.0004684147278846425), ('y', 0.00046674389978305306), ('ext', 0.00045404830989593744), ('res', 8.775640871660868e-06)]

import org.apache.batik.parser.ParseException;
// parser               3	: [('ext', 0.28252243799479004), ('anim', 0.20952748302621826), ('bridge', 0.17467316126185284), ('parser', 0.061124162852223315), ('test', 0.05945594651073573), ('transcoder', 0.04223464371832826), ('script', 0.020095533351089198), ('extension', 0.016813728417937577), ('length', 0.0008587452372703243), ('image', 0.0007329902323780028)]
import org.apache.batik.parser.PathArrayProducer;
// parser               3	: [('ext', 0.28252243799479004), ('anim', 0.20952748302621826), ('bridge', 0.17467316126185284), ('parser', 0.061124162852223315), ('test', 0.05945594651073573), ('transcoder', 0.04223464371832826), ('script', 0.020095533351089198), ('extension', 0.016813728417937577), ('length', 0.0008587452372703243), ('image', 0.0007329902323780028)]

import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.svg.SVGAnimatedPathData;
// svg                  0	: [('svg', 0.9246974983526353), ('xpath', 0.010628977863326118), ('svg12', 0.0025803780840143132), ('length', 0.0008587452372703243), ('image', 0.0007329902323780028), ('x', 0.000506863066592495), ('engine', 0.0004684147278846425), ('y', 0.00046674389978305306), ('ext', 0.00045404830989593744), ('res', 8.775640871660868e-06)]
import org.w3c.dom.svg.SVGException;
// svg                  0	: [('svg', 0.9246974983526353), ('xpath', 0.010628977863326118), ('svg12', 0.0025803780840143132), ('length', 0.0008587452372703243), ('image', 0.0007329902323780028), ('x', 0.000506863066592495), ('engine', 0.0004684147278846425), ('y', 0.00046674389978305306), ('ext', 0.00045404830989593744), ('res', 8.775640871660868e-06)]
import org.w3c.dom.svg.SVGPathSeg;
// svg                  0	: [('svg', 0.9246974983526353), ('xpath', 0.010628977863326118), ('svg12', 0.0025803780840143132), ('length', 0.0008587452372703243), ('image', 0.0007329902323780028), ('x', 0.000506863066592495), ('engine', 0.0004684147278846425), ('y', 0.00046674389978305306), ('ext', 0.00045404830989593744), ('res', 8.775640871660868e-06)]
import org.w3c.dom.svg.SVGPathSegList;
// svg                  0	: [('svg', 0.9246974983526353), ('xpath', 0.010628977863326118), ('svg12', 0.0025803780840143132), ('length', 0.0008587452372703243), ('image', 0.0007329902323780028), ('x', 0.000506863066592495), ('engine', 0.0004684147278846425), ('y', 0.00046674389978305306), ('ext', 0.00045404830989593744), ('res', 8.775640871660868e-06)]

/**
 * This class is the implementation of the {@link SVGAnimatedPathData}
 * interface.
 *
 * @author <a href="mailto:nicolas.socheleau@bitflash.com">Nicolas Socheleau</a>
 * @author <a href="mailto:andrest@world-affair.com">Andres Toussaint</a>
 * @version $Id$
 */
public class SVGOMAnimatedPathData
    extends AbstractSVGAnimatedValue
    implements SVGAnimatedPathData {

    /**
     * Whether the list is changing.
     */
    protected boolean changing;
// changing             No	: [('readonly', 0.13018322590330939), ('valid', 0.05438854006356018), ('missing', 0.05143601495347475), ('finished', 0.0514358352907233), ('isIdent', 0.03796677261295091), ('isProcessing', 0.02541168517957662), ('malformed', 0.01655112621535773), ('mutate', 0.01556778960483605), ('value', 0.012670870116326251), ('defaultValue', 0.012618498424276831)]

    /**
     * The base path data value.
     */
    protected BaseSVGPathSegList pathSegs;
// pathSegs             No	: [('res', 0.00028082050789314777), ('xmlTraitInformation', 0.0001838026221069148), ('d', 0.000180928018083619), ('document', 0.00011768672957111162), ('x1', 0.00011624942755946372), ('rect', 0.0001097815685070482), ('pd', 0.00010331370945463267), ('raf', 0.00010331370945463267), ('iter', 8.678473632068188e-05), ('info', 7.744227324497055e-05)]

    /**
     * The normalized base path data value.
     */
    protected NormalizedBaseSVGPathSegList normalizedPathSegs;
// normalizedPathSegs   No	: [('res', 0.00028082050789314777), ('xmlTraitInformation', 0.0001838026221069148), ('d', 0.000180928018083619), ('document', 0.00011768672957111162), ('x1', 0.00011624942755946372), ('rect', 0.0001097815685070482), ('pd', 0.00010331370945463267), ('raf', 0.00010331370945463267), ('iter', 8.678473632068188e-05), ('info', 7.744227324497055e-05)]

    /**
     * The animated path data value.
     */
    protected AnimSVGPathSegList animPathSegs;
// animPathSegs         No	: [('res', 0.00028082050789314777), ('xmlTraitInformation', 0.0001838026221069148), ('d', 0.000180928018083619), ('document', 0.00011768672957111162), ('x1', 0.00011624942755946372), ('rect', 0.0001097815685070482), ('pd', 0.00010331370945463267), ('raf', 0.00010331370945463267), ('iter', 8.678473632068188e-05), ('info', 7.744227324497055e-05)]

//     /**
//      * The normalized animated base path data value.
//      */
//     protected NormalizedAnimSVGPathSegList normalizedPathSegs;

    /**
     * Default value for the 'd' attribute.
     */
    protected String defaultValue;
// defaultValue         6	: [('refImageURI', 0.043429587585127), ('namespaceURI', 0.038265151432917414), ('targetId', 0.029897332428389933), ('rootTag', 0.029897242597014203), ('localName', 0.02645155768611806), ('testFileName', 0.021461128611714717), ('defaultValue', 0.020982742345899), ('property', 0.018143608199483012), ('attributeName', 0.013445812432833135), ('language', 0.013426713595147244)]

    /**
     * Creates a new SVGOMAnimatedPathData.
     * @param elt The associated element.
     * @param ns The attribute's namespace URI.
     * @param ln The attribute's local name.
     * @param defaultValue The default value if the attribute is not specified.
     */
    public SVGOMAnimatedPathData(AbstractElement elt,
// elt                  0	: [('elt', 0.2819459487129913), ('script', 0.018322410819351282), ('ae', 0.017377237749896145), ('v', 0.014934091102059009), ('e', 0.014220143993727724), ('element', 0.00700710373585658), ('tgt', 0.006952486259413959), ('a', 0.003533623874374166), ('res', 7.020512697328694e-05), ('xmlTraitInformation', 4.59506555267287e-05)]
                                 String ns,
// ns                   0	: [('ns', 0.7839288369195517), ('pseudo', 0.042119967917292375), ('pseudoElt', 0.016045731887720294), ('str', 0.014480666435298557), ('name', 0.0059097065073097895), ('value', 0.005339342247949624), ('ln', 0.0041233546375442605), ('pn', 0.0033717821397110503), ('attr', 0.0032830702201490577), ('type', 0.003184554410232146)]
                                 String ln,
// ln                   0	: [('ln', 0.6653276229302272), ('an', 0.058470238187975594), ('root', 0.04123226198415517), ('nm', 0.032613936883413464), ('prefix', 0.023925514734870198), ('s', 0.01832221832237399), ('qn', 0.015596610790276723), ('value', 0.003386217247949624), ('newv', 0.0022581757645146606), ('res', 8.775640871660868e-06)]
                                 String defaultValue) {
// defaultValue         1	: [('def', 0.3607164440416983), ('defaultValue', 0.2066026291366272), ('value', 0.10632739371853786), ('val', 0.10342019845738043), ('ln', 0.0041233546375442605), ('s', 0.002778882085440189), ('ns', 0.002678836919551747), ('newv', 0.0022581757645146606), ('oldv', 0.002210681147464401), ('key', 0.0021850056080401437)]
        super(elt, ns, ln);
// elt                  0	: [('elt', 0.5116891614292972), ('prefix', 0.10700139585584506), ('target', 0.04151623039298041), ('srcCM', 0.020743309372719267), ('src', 0.01995583280228288), ('source', 0.019084438392713864), ('generatorContext', 0.01908365410066864), ('owner', 0.012445438970009973), ('type', 0.009224393313911169), ('i', 0.000427952045857157)]
// ns                   0	: [('ns', 0.7212295487041873), ('pseudo', 0.051185647193520895), ('data', 0.00711295370110763), ('props', 0.0071043352808475985), ('href', 0.005652009974443702), ('pn', 0.005313232472129762), ('pseudoElt', 0.004533189968839583), ('userAgent', 0.0038739185183798994), ('style', 0.0035423392003484195), ('y', 0.0004342760042854519)]
// ln                   0	: [('ln', 0.8775094708324159), ('an', 0.0060721074688480856), ('nm', 0.005488736842918843), ('s', 0.003994065430852693), ('prefix', 0.0037851374851225724), ('t', 0.0017788104954346198), ('name', 0.0012145469934861637), ('y', 0.0004342760042854519), ('ctx', 0.0003950622835718969), ('e', 0.000390695766227908)]
        this.defaultValue = defaultValue;
// defaultValue         0	: [('defaultValue', 0.5032529826625685), ('value', 0.005109746752178427), ('node', 0.00460515316586751), ('image', 0.003958392602578874), ('mimeTypes', 0.003957030655009894), ('generatorContext', 0.0039052752894742524), ('height', 0.003465182671816647), ('parent', 0.0032104379403761715), ('listenerMap', 0.0031217443019569456), ('view', 0.0031214074342979653)]
// defaultValue         0	: [('defaultValue', 0.9010537826004666), ('val', 0.01569997811060545), ('def', 0.015646009621659927), ('v', 0.005374346744906686), ('reader', 0.00048194278701482214), ('e', 0.00040012036455088905), ('n', 0.00031112910904361826), ('raf', 0.00023266541984860278), ('ctx', 0.00022063910381039912), ('t', 0.00017942112606357793)]
    }

    /**
     * <b>DOM</b>: Implements {@link
     * SVGAnimatedPathData#getAnimatedNormalizedPathSegList()}.
     */
    public SVGPathSegList getAnimatedNormalizedPathSegList() {
        throw new UnsupportedOperationException
            ("SVGAnimatedPathData.getAnimatedNormalizedPathSegList is not implemented"); // XXX
    }

    /**
     * <b>DOM</b>: Implements {@link
     * SVGAnimatedPathData#getAnimatedPathSegList()}.
     */
    public SVGPathSegList getAnimatedPathSegList() {
        if (animPathSegs == null) {
// animPathSegs         No	: [('hasAnimVal', 0.041679593158570255), ('baseVal', 0.01381739292695273), ('value', 0.01147528803467745), ('animVal', 0.011373883286121704), ('valid', 0.011165353702706594), ('size', 0.010631257862340168), ('resultType', 0.00973244190997635), ('selectedContent', 0.00957019332477716), ('parent', 0.008414898023702102), ('se', 0.007997704005813174)]
            animPathSegs = new AnimSVGPathSegList();
// animPathSegs         No	: [('res', 0.02446916867862338), ('baseVal', 0.01895359149653405), ('report', 0.015627371197867734), ('animVal', 0.014230113287627171), ('hasAnimVal', 0.011451408304241337), ('result', 0.011277583751916172), ('outline', 0.007100129283156945), ('sandwich', 0.00589709387981645), ('param', 0.005727108098647692), ('selectedContent', 0.005692416155502025)]
        }
        return animPathSegs;
// animPathSegs         No	: [('baseVal', 0.0728745944270723), ('n', 0.044870349305303106), ('animVal', 0.031487225534044934), ('size', 0.024887487455616364), ('animatedAngle', 0.019902146440472897), ('report', 0.01985473080118974), ('value', 0.017512357932732564), ('selectedContent', 0.015399231772484217), ('argsArray', 0.014924126682451256), ('prefix', 0.009975011300890057)]
    }

    /**
     * <b>DOM</b>: Implements {@link
     * SVGAnimatedPathData#getNormalizedPathSegList()}.
     * <p>
     *   Returns the SVGPathSegList mapping the normalized static 'd' attribute
     *   of the element.
     * </p>
     * <p>
     *   A normalized path is composed only of absolute moveto, lineto and
     *   cubicto path segments (M, L and C). Using this subset, the path
     *   description can be represented with fewer segment types. Be aware that
     *   the normalized 'd' attribute will be a larger String that the original.
     * </p>
     * <p>
     *   Relative values are transformed into absolute, quadratic curves are
     *   promoted to cubic curves, and arcs are converted into one or more
     *   cubic curves (one per quadrant).
     * </p>
     * <p>
     *   Modifications to the normalized SVGPathSegList will result
     *   in substituting the original path with a set of normalized path
     *   segments.
     * </p>
     * @return a path segment list containing the normalized version of the path.
     */
    public SVGPathSegList getNormalizedPathSegList() {
        if (normalizedPathSegs == null) {
// normalizedPathSegs   No	: [('hasAnimVal', 0.041679593158570255), ('baseVal', 0.01381739292695273), ('value', 0.01147528803467745), ('animVal', 0.011373883286121704), ('valid', 0.011165353702706594), ('size', 0.010631257862340168), ('resultType', 0.00973244190997635), ('selectedContent', 0.00957019332477716), ('parent', 0.008414898023702102), ('se', 0.007997704005813174)]
            normalizedPathSegs = new NormalizedBaseSVGPathSegList();
// normalizedPathSegs   No	: [('res', 0.02446916867862338), ('baseVal', 0.01895359149653405), ('report', 0.015627371197867734), ('animVal', 0.014230113287627171), ('hasAnimVal', 0.011451408304241337), ('result', 0.011277583751916172), ('outline', 0.007100129283156945), ('sandwich', 0.00589709387981645), ('param', 0.005727108098647692), ('selectedContent', 0.005692416155502025)]
        }
        return normalizedPathSegs;
// normalizedPathSegs   No	: [('baseVal', 0.0728745944270723), ('n', 0.044870349305303106), ('animVal', 0.031487225534044934), ('size', 0.024887487455616364), ('animatedAngle', 0.019902146440472897), ('report', 0.01985473080118974), ('value', 0.017512357932732564), ('selectedContent', 0.015399231772484217), ('argsArray', 0.014924126682451256), ('prefix', 0.009975011300890057)]
    }

    /**
     * <b>DOM</b>: Implements {@link
     * SVGAnimatedPathData#getPathSegList()}.
     */
    public SVGPathSegList getPathSegList() {
        if (pathSegs == null) {
// pathSegs             No	: [('hasAnimVal', 0.041679593158570255), ('baseVal', 0.01381739292695273), ('value', 0.01147528803467745), ('animVal', 0.011373883286121704), ('valid', 0.011165353702706594), ('size', 0.010631257862340168), ('resultType', 0.00973244190997635), ('selectedContent', 0.00957019332477716), ('parent', 0.008414898023702102), ('se', 0.007997704005813174)]
            pathSegs = new BaseSVGPathSegList();
// pathSegs             No	: [('res', 0.02446916867862338), ('baseVal', 0.01895359149653405), ('report', 0.015627371197867734), ('animVal', 0.014230113287627171), ('hasAnimVal', 0.011451408304241337), ('result', 0.011277583751916172), ('outline', 0.007100129283156945), ('sandwich', 0.00589709387981645), ('param', 0.005727108098647692), ('selectedContent', 0.005692416155502025)]
        }
        return pathSegs;
// pathSegs             No	: [('baseVal', 0.0728745944270723), ('n', 0.044870349305303106), ('animVal', 0.031487225534044934), ('size', 0.024887487455616364), ('animatedAngle', 0.019902146440472897), ('report', 0.01985473080118974), ('value', 0.017512357932732564), ('selectedContent', 0.015399231772484217), ('argsArray', 0.014924126682451256), ('prefix', 0.009975011300890057)]
    }

    /**
     * Throws an exception if the path data is malformed.
     */
    public void check() {
// check                No	: [('run', 0.12788979364561012), ('rotate', 0.045746655022790184), ('scale', 0.03863661440203593), ('paint', 0.03853912427881691), ('stream', 0.025847372981796613), ('cm', 0.021751763407362488), ('in', 0.021745879452252304), ('ex', 0.021744307403177062), ('pt', 0.02174417265611347), ('px', 0.021740983642275127)]
        if (!hasAnimVal) {
// hasAnimVal           3	: [('changing', 0.12935053060053558), ('mutate', 0.045653403710065876), ('errorHandler', 0.03424070833045798), ('hasAnimVal', 0.029314977269016412), ('valid', 0.024218165012047235), ('initialised', 0.022826890058653487), ('glyphVisible', 0.019022987647161857), ('SVG_NAMESPACE_URI', 0.01722995954606696), ('isActive', 0.011822291423445106), ('cm', 0.011419494730213541)]
            if (pathSegs == null) {
// pathSegs             No	: [('baseVal', 0.4190227415420435), ('animVal', 0.08467856332432609), ('n', 0.01449289281552511), ('node', 0.006268314312389989), ('current', 0.005922476612200984), ('e', 0.004910471786953795), ('hasAnimVal', 0.004430309490948475), ('value', 0.004311964252442492), ('userAgent', 0.003346222110439982), ('listeners', 0.0027952438417346947)]
                pathSegs = new BaseSVGPathSegList();
// pathSegs             No	: [('res', 0.02446916867862338), ('baseVal', 0.01895359149653405), ('report', 0.015627371197867734), ('animVal', 0.014230113287627171), ('hasAnimVal', 0.011451408304241337), ('result', 0.011277583751916172), ('outline', 0.007100129283156945), ('sandwich', 0.00589709387981645), ('param', 0.005727108098647692), ('selectedContent', 0.005692416155502025)]
            }
            pathSegs.revalidate();
// pathSegs             No	: [('c', 0.009823694540214058), ('animVal', 0.0066613883095537054), ('listeners', 0.006594758990324623), ('baseVal', 0.005349442329187578), ('transcoder', 0.00534890334093321), ('rgb', 0.00533035062023332), ('baseAngleVal', 0.005255274089126294), ('e', 0.00456875221186446), ('lu', 0.0043887475506830175), ('initialAttributes', 0.003942474710690751)]
            if (pathSegs.missing) {
// pathSegs             No	: [('current', 0.12683723479812664), ('s', 0.020760948423860087), ('n', 0.01441836590493764), ('ln', 0.012860173739934973), ('at', 0.009703797446828548), ('aci', 0.009342064203630977), ('token', 0.008975899795115748), ('o', 0.008947959095418312), ('lu', 0.007917251996990407), ('doc', 0.007863333469835396)]
// missing              No	: [('length', 0.01373992379632519), ('image', 0.011727843718048045), ('svg', 0.008723364916398439), ('x', 0.00810980906547992), ('engine', 0.00749463564615428), ('y', 0.007467902396528849), ('ext', 0.007264772958334998), ('value', 0.0062528584970886385), ('io', 0.005979888170596395), ('read', 0.005490698103719049)]
                throw new LiveAttributeException
                    (element, localName,
// element              0	: [('element', 0.9338991670416882), ('i', 0.000427952045857157), ('e', 0.000370067851647241), ('current', 0.0002998129311121156), ('n', 0.00029475824829224234), ('s', 0.00028318556097628846), ('ctx', 0.00025348084412036946), ('x', 0.00019717577420903107), ('prefix', 0.00015252444755225168), ('t', 0.00013975634544862955)]
// localName            0	: [('localName', 0.7866465074913538), ('ctx', 0.01578372300680139), ('pseudoElement', 0.01143316721036133), ('uri', 0.006932224375107392), ('deltaGC', 0.006862740547375073), ('animationTarget', 0.006859586209604147), ('node', 0.005909809858485052), ('rect', 0.00460938599270777), ('method', 0.004573415006537094), ('y', 0.0004342760042854519)]
                     LiveAttributeException.ERR_ATTRIBUTE_MISSING, null);
// ERR_ATTRIBUTE_MISSING 1	: [('ERR_ATTRIBUTE_MALFORMED', 0.5250894482597805), ('ERR_ATTRIBUTE_MISSING', 0.2931794872993174), ('ERR_ATTRIBUTE_NEGATIVE', 0.11924604334624513), ('length', 0.0008587452372703243), ('image', 0.0007329902323780028), ('svg', 0.0005452103072749024), ('x', 0.000506863066592495), ('engine', 0.0004684147278846425), ('y', 0.00046674389978305306), ('ext', 0.00045404830989593744)]
            }
            if (pathSegs.malformed) {
// pathSegs             No	: [('lu', 0.03307691771181952), ('emitProperties', 0.03248890124522566), ('elt', 0.031114138226087915), ('current', 0.029448474304032808), ('value', 0.0115347282429864), ('node', 0.010132867385256527), ('index', 0.009400233733210063), ('tokens', 0.008168269961960219), ('baseVal', 0.0079475980166574), ('gcDefaults', 0.007810370254280342)]
// malformed            No	: [('length', 0.01373992379632519), ('image', 0.011727843718048045), ('svg', 0.008723364916398439), ('x', 0.00810980906547992), ('engine', 0.00749463564615428), ('y', 0.007467902396528849), ('ext', 0.007264772958334998), ('value', 0.0062528584970886385), ('io', 0.005979888170596395), ('read', 0.005490698103719049)]
                throw new LiveAttributeException
                    (element, localName,
// element              0	: [('element', 0.9338991670416882), ('i', 0.000427952045857157), ('e', 0.000370067851647241), ('current', 0.0002998129311121156), ('n', 0.00029475824829224234), ('s', 0.00028318556097628846), ('ctx', 0.00025348084412036946), ('x', 0.00019717577420903107), ('prefix', 0.00015252444755225168), ('t', 0.00013975634544862955)]
// localName            0	: [('localName', 0.7866465074913538), ('ctx', 0.01578372300680139), ('pseudoElement', 0.01143316721036133), ('uri', 0.006932224375107392), ('deltaGC', 0.006862740547375073), ('animationTarget', 0.006859586209604147), ('node', 0.005909809858485052), ('rect', 0.00460938599270777), ('method', 0.004573415006537094), ('y', 0.0004342760042854519)]
                     LiveAttributeException.ERR_ATTRIBUTE_MALFORMED,
// ERR_ATTRIBUTE_MALFORMED 0	: [('ERR_ATTRIBUTE_MALFORMED', 0.5250894482597805), ('ERR_ATTRIBUTE_MISSING', 0.2931794872993174), ('ERR_ATTRIBUTE_NEGATIVE', 0.11924604334624513), ('length', 0.0008587452372703243), ('image', 0.0007329902323780028), ('svg', 0.0005452103072749024), ('x', 0.000506863066592495), ('engine', 0.0004684147278846425), ('y', 0.00046674389978305306), ('ext', 0.00045404830989593744)]
                     pathSegs.getValueAsString());
// pathSegs             No	: [('baseVal', 0.6302193820642527), ('s', 0.21030459040536847), ('y', 0.0004342760042854519), ('ctx', 0.0003950622835718969), ('e', 0.000390695766227908), ('CHAR_CLASS_AL', 0.0002611988410941879), ('current', 0.00022780565916510052), ('w', 0.00017876816503208098), ('owner', 0.00015959312352023446), ('res', 2.067092774272461e-05)]
            }
        }
    }

    /**
     * Returns the base value of the attribute as an {@link AnimatableValue}.
     */
    public AnimatableValue getUnderlyingValue(AnimationTarget target) {
// target               0	: [('target', 0.9610183195666228), ('t', 0.0036178863126811968), ('animationTarget', 0.0002210082758746047), ('res', 8.775640871660868e-06), ('xmlTraitInformation', 5.743831940841088e-06), ('d', 5.654000565113094e-06), ('document', 3.6777102990972383e-06), ('x1', 3.6327946112332413e-06), ('rect', 3.430674015845256e-06), ('pd', 3.228553420457271e-06)]
        SVGPathSegList psl = getPathSegList();
// psl                  No	: [('p', 0.5001493933915194), ('res', 0.00014041025394657389), ('xmlTraitInformation', 9.19013110534574e-05), ('d', 9.04640090418095e-05), ('document', 5.884336478555581e-05), ('x1', 5.812471377973186e-05), ('rect', 5.48907842535241e-05), ('pd', 5.165685472731634e-05), ('raf', 5.165685472731634e-05), ('iter', 4.339236816034094e-05)]
        PathArrayProducer pp = new PathArrayProducer();
// pp                   No	: [('producer', 0.5000103344218925), ('res', 0.00014041025394657389), ('xmlTraitInformation', 9.19013110534574e-05), ('d', 9.04640090418095e-05), ('document', 5.884336478555581e-05), ('x1', 5.812471377973186e-05), ('rect', 5.48907842535241e-05), ('pd', 5.165685472731634e-05), ('raf', 5.165685472731634e-05), ('iter', 4.339236816034094e-05)]
        SVGAnimatedPathDataSupport.handlePathSegList(psl, pp);
// psl                  No	: [('p', 0.4584048611939565), ('pathElt', 0.45833443357291814), ('i', 0.000427952045857157), ('e', 0.000370067851647241), ('current', 0.0002998129311121156), ('n', 0.00029475824829224234), ('s', 0.00028318556097628846), ('ctx', 0.00025348084412036946), ('x', 0.00019717577420903107), ('prefix', 0.00015252444755225168)]
// pp                   No	: [('y', 0.006948416068567231), ('ctx', 0.00632099653715035), ('e', 0.006251132259646529), ('CHAR_CLASS_AL', 0.004179181457507007), ('s', 0.003762335374784778), ('current', 0.0036448905466416083), ('w', 0.0028602906405132958), ('owner', 0.0025534899763237514), ('CHAR_CLASS_AI', 0.002377342914927434), ('h', 0.0022591881397666757)]
        return new AnimatablePathDataValue(target, pp.getPathCommands(),
// target               0	: [('target', 0.9126019391494561), ('i', 0.000427952045857157), ('e', 0.000370067851647241), ('current', 0.0002998129311121156), ('n', 0.00029475824829224234), ('s', 0.00028318556097628846), ('ctx', 0.00025348084412036946), ('x', 0.00019717577420903107), ('prefix', 0.00015252444755225168), ('t', 0.00013975634544862955)]
// pp                   No	: [('producer', 0.38087033439634), ('cmds', 0.37646867609350076), ('v', 0.02210201167504649), ('data', 0.007711750050466199), ('r', 0.007475922791579283), ('fv', 0.005870491601247526), ('e', 0.004788095883736721), ('type', 0.004465821615866803), ('ns', 0.004453805234759408), ('y', 0.0004342760042854519)]
                                           pp.getPathParameters());
// pp                   No	: [('producer', 0.7503163525877813), ('s', 0.007192558793618716), ('e', 0.007005955314251042), ('src', 0.0064555259633768385), ('reader', 0.005110239308052504), ('evt', 0.005096482588315924), ('wr', 0.004933427830333835), ('at', 0.003610413959311106), ('r', 0.0030844969028690095), ('bi', 0.002900926170601697)]
    }

    /**
     * Updates the animated value with the given {@link AnimatableValue}.
     */
    protected void updateAnimatedValue(AnimatableValue val) {
// val                  0	: [('val', 0.8912967203457219), ('other', 0.026590741374758968), ('result', 0.024473833802428686), ('to', 0.003967354093191719), ('accumulation', 0.002684053561986348), ('v', 0.0021394274136883273), ('from', 0.0010506476459740401), ('res', 8.775640871660868e-06), ('xmlTraitInformation', 5.743831940841088e-06), ('d', 5.654000565113094e-06)]
        if (val == null) {
// val                  0	: [('val', 0.41430062627918074), ('type', 0.06089738275773002), ('ns', 0.030216375512928526), ('n', 0.01449289281552511), ('node', 0.006268314312389989), ('current', 0.005922476612200984), ('e', 0.004910471786953795), ('hasAnimVal', 0.004430309490948475), ('value', 0.004311964252442492), ('userAgent', 0.003346222110439982)]
            hasAnimVal = false;
// hasAnimVal           0	: [('hasAnimVal', 0.40572570415212067), ('motionTransform', 0.06856435401895677), ('over', 0.03388800048260859), ('res', 0.01223458433931169), ('baseVal', 0.009476795748267024), ('report', 0.007813685598933867), ('animVal', 0.007115056643813586), ('result', 0.005638791875958086), ('outline', 0.0035500646415784726), ('sb', 0.002353457302921534)]
        } else {
            hasAnimVal = true;
// hasAnimVal           0	: [('hasAnimVal', 0.3393434011925679), ('rotated', 0.05755909121425874), ('report', 0.031373837903035204), ('image', 0.028285589669704896), ('outlinesPositioned', 0.028281356018033853), ('overlay', 0.028277795405501047), ('res', 0.01007639746008716), ('pd', 0.008008453078411847), ('postProcess', 0.006013122822189078), ('sb', 0.003140645446727452)]
            AnimatablePathDataValue animPath = (AnimatablePathDataValue) val;
// animPath             No	: [('res', 0.18757020512697328), ('base', 0.1875227741605889), ('accValue', 0.18750858080322388), ('toValue', 0.020851256599632925), ('xmlTraitInformation', 4.59506555267287e-05), ('d', 4.523200452090475e-05), ('document', 2.9421682392777906e-05), ('x1', 2.906235688986593e-05), ('rect', 2.744539212676205e-05)]
// val                  No	: [('result', 0.29171640279815503), ('to', 0.2916958086486704), ('accumulation', 0.2916837177184909), ('n', 0.00025018186777496217), ('e', 0.00023287026806546646), ('doc', 0.00019720289414255), ('current', 0.00012271610194743341), ('node', 0.00011509520250721353), ('o', 9.432077707328703e-05), ('evt', 9.378178881891907e-05)]
            if (animPathSegs == null) {
// animPathSegs         No	: [('animVal', 0.583595756442839), ('val', 0.056562638658758206), ('emitProperties', 0.05591870662875933), ('g', 0.028000440583019436), ('name', 0.014496933590480165), ('pos', 0.014078762458229978), ('current', 0.009433952063504357), ('s', 0.005146205116135011), ('n', 0.0031687437377837533), ('i', 0.002831783178177898)]
                animPathSegs = new AnimSVGPathSegList();
// animPathSegs         No	: [('res', 0.02446916867862338), ('baseVal', 0.01895359149653405), ('report', 0.015627371197867734), ('animVal', 0.014230113287627171), ('hasAnimVal', 0.011451408304241337), ('result', 0.011277583751916172), ('outline', 0.007100129283156945), ('sandwich', 0.00589709387981645), ('param', 0.005727108098647692), ('selectedContent', 0.005692416155502025)]
            }
            animPathSegs.setAnimatedValue(animPath.getCommands(),
// animPathSegs         No	: [('c', 0.009823694540214058), ('animVal', 0.0066613883095537054), ('listeners', 0.006594758990324623), ('baseVal', 0.005349442329187578), ('transcoder', 0.00534890334093321), ('rgb', 0.00533035062023332), ('baseAngleVal', 0.005255274089126294), ('e', 0.00456875221186446), ('lu', 0.0043887475506830175), ('initialAttributes', 0.003942474710690751)]
// animPath             No	: [('unitType', 0.07643796356717159), ('animRect', 0.0763932898472281), ('aval', 0.0763932898472281), ('animPointList', 0.0763932898472281), ('animNumList', 0.0763932898472281), ('animPAR', 0.0763932898472281), ('animLengths', 0.0763932898472281), ('animLength', 0.0763932898472281), ('i', 0.001711808183428628), ('e', 0.001480271406588964)]
                                          animPath.getParameters());
// animPath             No	: [('s', 0.028770235174474863), ('e', 0.02802382125700417), ('src', 0.025822103853507354), ('reader', 0.020440957232210014), ('evt', 0.020385930353263696), ('wr', 0.01973371132133534), ('at', 0.014441655837244424), ('r', 0.012337987611476038), ('bi', 0.011603704682406788), ('bounds', 0.008715203360475127)]
        }
        fireAnimatedAttributeListeners();
    }

    /**
     * Called when an Attr node has been added.
     */
    public void attrAdded(Attr node, String newv) {
// node                 0	: [('node', 0.9244665412534698), ('attr', 0.009052712047940925), ('a', 0.003026141115609902), ('newAttr', 0.0015350272785947137), ('oldAttr', 0.0007678253610595296), ('a2', 0.0001787866234923276), ('res', 8.775640871660868e-06), ('xmlTraitInformation', 5.743831940841088e-06), ('d', 5.654000565113094e-06), ('document', 3.6777102990972383e-06)]
// newv                 1	: [('oldv', 0.5869301031904751), ('newv', 0.2886325037215039), ('ln', 0.0041233546375442605), ('value', 0.003386217247949624), ('s', 0.002778882085440189), ('ns', 0.002678836919551747), ('text', 0.0026503198841601604), ('key', 0.0021850056080401437), ('styleAttribute', 0.002010859852949347), ('prefix', 0.0016857499265078256)]
        if (!changing) {
// changing             0	: [('changing', 0.12935053060053558), ('mutate', 0.045653403710065876), ('errorHandler', 0.03424070833045798), ('hasAnimVal', 0.029314977269016412), ('valid', 0.024218165012047235), ('initialised', 0.022826890058653487), ('glyphVisible', 0.019022987647161857), ('SVG_NAMESPACE_URI', 0.01722995954606696), ('isActive', 0.011822291423445106), ('cm', 0.011419494730213541)]
            if (pathSegs != null) {
// pathSegs             No	: [('n', 0.02898578563105022), ('node', 0.012536628624779979), ('current', 0.011844953224401968), ('e', 0.00982094357390759), ('hasAnimVal', 0.00886061898189695), ('value', 0.008623928504884984), ('i', 0.007390769561188091), ('userAgent', 0.006692444220879964), ('c', 0.006353580018328708), ('listeners', 0.0055904876834693895)]
                pathSegs.invalidate();
// pathSegs             No	: [('encodeParam', 0.019706096408927346), ('baseVal', 0.015875636802319643), ('userAgent', 0.01311319967747684), ('result', 0.011835190684384371), ('n', 0.011584185127315655), ('g2d', 0.009929255291564028), ('currentNode', 0.00913422801638335), ('t', 0.008081009752248637), ('s', 0.00674495152274963), ('node', 0.0066270585977595085)]
            }
            if (normalizedPathSegs != null) {
// normalizedPathSegs   No	: [('lu', 0.03307691771181952), ('emitProperties', 0.03248890124522566), ('elt', 0.031114138226087915), ('current', 0.029448474304032808), ('value', 0.0115347282429864), ('node', 0.010132867385256527), ('index', 0.009400233733210063), ('tokens', 0.008168269961960219), ('baseVal', 0.0079475980166574), ('gcDefaults', 0.007810370254280342)]
                normalizedPathSegs.invalidate();
// normalizedPathSegs   No	: [('encodeParam', 0.019706096408927346), ('baseVal', 0.015875636802319643), ('userAgent', 0.01311319967747684), ('result', 0.011835190684384371), ('n', 0.011584185127315655), ('g2d', 0.009929255291564028), ('currentNode', 0.00913422801638335), ('t', 0.008081009752248637), ('s', 0.00674495152274963), ('node', 0.0066270585977595085)]
            }
        }
        fireBaseAttributeListeners();
        if (!hasAnimVal) {
// hasAnimVal           0	: [('hasAnimVal', 0.14491290110497732), ('rect', 0.025806382930610022), ('suppressAlpha', 0.016279262176863672), ('r', 0.013092620596457853), ('ctx', 0.011997020559246578), ('bounds', 0.011751687428384018), ('val', 0.011644260764927155), ('sm', 0.011642217101129343), ('purl', 0.011639791653984686), ('inputRgn', 0.011637860279406534)]
            fireAnimatedAttributeListeners();
        }
    }

    /**
     * Called when an Attr node has been modified.
     */
    public void attrModified(Attr node, String oldv, String newv) {
// attrModified         0	: [('attrModified', 0.5010709133187344), ('run', 0.0859862686246559), ('paint', 0.08274805968976229), ('stream', 0.03666892329002733), ('numberValue', 0.0355497499836953), ('nodeRemoved', 0.03329094816892411), ('rotate', 0.007903267631155567), ('scale', 0.007342259296826345), ('res', 8.775640871660868e-06), ('xmlTraitInformation', 5.743831940841088e-06)]
// node                 0	: [('node', 0.9244665412534698), ('attr', 0.009052712047940925), ('a', 0.003026141115609902), ('newAttr', 0.0015350272785947137), ('oldAttr', 0.0007678253610595296), ('a2', 0.0001787866234923276), ('res', 8.775640871660868e-06), ('xmlTraitInformation', 5.743831940841088e-06), ('d', 5.654000565113094e-06), ('document', 3.6777102990972383e-06)]
// oldv                 0	: [('oldv', 0.5869301031904751), ('newv', 0.2886325037215039), ('ln', 0.0041233546375442605), ('value', 0.003386217247949624), ('s', 0.002778882085440189), ('ns', 0.002678836919551747), ('text', 0.0026503198841601604), ('key', 0.0021850056080401437), ('styleAttribute', 0.002010859852949347), ('prefix', 0.0016857499265078256)]
// newv                 0	: [('newv', 0.8772581757645147), ('ln', 0.0041233546375442605), ('value', 0.003386217247949624), ('s', 0.002778882085440189), ('ns', 0.002678836919551747), ('oldv', 0.002210681147464401), ('key', 0.0021850056080401437), ('uri', 0.0020341143176321234), ('namespaceURI', 0.0018084085368918212), ('res', 8.775640871660868e-06)]
        if (!changing) {
// changing             0	: [('changing', 0.12935053060053558), ('mutate', 0.045653403710065876), ('errorHandler', 0.03424070833045798), ('hasAnimVal', 0.029314977269016412), ('valid', 0.024218165012047235), ('initialised', 0.022826890058653487), ('glyphVisible', 0.019022987647161857), ('SVG_NAMESPACE_URI', 0.01722995954606696), ('isActive', 0.011822291423445106), ('cm', 0.011419494730213541)]
            if (pathSegs != null) {
// pathSegs             No	: [('n', 0.02898578563105022), ('node', 0.012536628624779979), ('current', 0.011844953224401968), ('e', 0.00982094357390759), ('hasAnimVal', 0.00886061898189695), ('value', 0.008623928504884984), ('i', 0.007390769561188091), ('userAgent', 0.006692444220879964), ('c', 0.006353580018328708), ('listeners', 0.0055904876834693895)]
                pathSegs.invalidate();
// pathSegs             No	: [('encodeParam', 0.019706096408927346), ('baseVal', 0.015875636802319643), ('userAgent', 0.01311319967747684), ('result', 0.011835190684384371), ('n', 0.011584185127315655), ('g2d', 0.009929255291564028), ('currentNode', 0.00913422801638335), ('t', 0.008081009752248637), ('s', 0.00674495152274963), ('node', 0.0066270585977595085)]
            }
            if (normalizedPathSegs != null) {
// normalizedPathSegs   No	: [('lu', 0.03307691771181952), ('emitProperties', 0.03248890124522566), ('elt', 0.031114138226087915), ('current', 0.029448474304032808), ('value', 0.0115347282429864), ('node', 0.010132867385256527), ('index', 0.009400233733210063), ('tokens', 0.008168269961960219), ('baseVal', 0.0079475980166574), ('gcDefaults', 0.007810370254280342)]
                normalizedPathSegs.invalidate();
// normalizedPathSegs   No	: [('encodeParam', 0.019706096408927346), ('baseVal', 0.015875636802319643), ('userAgent', 0.01311319967747684), ('result', 0.011835190684384371), ('n', 0.011584185127315655), ('g2d', 0.009929255291564028), ('currentNode', 0.00913422801638335), ('t', 0.008081009752248637), ('s', 0.00674495152274963), ('node', 0.0066270585977595085)]
            }
        }
        fireBaseAttributeListeners();
        if (!hasAnimVal) {
// hasAnimVal           0	: [('hasAnimVal', 0.14491290110497732), ('rect', 0.025806382930610022), ('suppressAlpha', 0.016279262176863672), ('r', 0.013092620596457853), ('ctx', 0.011997020559246578), ('bounds', 0.011751687428384018), ('val', 0.011644260764927155), ('sm', 0.011642217101129343), ('purl', 0.011639791653984686), ('inputRgn', 0.011637860279406534)]
            fireAnimatedAttributeListeners();
        }
    }

    /**
     * Called when an Attr node has been removed.
     */
    public void attrRemoved(Attr node, String oldv) {
// node                 0	: [('node', 0.9244665412534698), ('attr', 0.009052712047940925), ('a', 0.003026141115609902), ('newAttr', 0.0015350272785947137), ('oldAttr', 0.0007678253610595296), ('a2', 0.0001787866234923276), ('res', 8.775640871660868e-06), ('xmlTraitInformation', 5.743831940841088e-06), ('d', 5.654000565113094e-06), ('document', 3.6777102990972383e-06)]
// oldv                 0	: [('oldv', 0.5869301031904751), ('newv', 0.2886325037215039), ('ln', 0.0041233546375442605), ('value', 0.003386217247949624), ('s', 0.002778882085440189), ('ns', 0.002678836919551747), ('text', 0.0026503198841601604), ('key', 0.0021850056080401437), ('styleAttribute', 0.002010859852949347), ('prefix', 0.0016857499265078256)]
        if (!changing) {
// changing             0	: [('changing', 0.12935053060053558), ('mutate', 0.045653403710065876), ('errorHandler', 0.03424070833045798), ('hasAnimVal', 0.029314977269016412), ('valid', 0.024218165012047235), ('initialised', 0.022826890058653487), ('glyphVisible', 0.019022987647161857), ('SVG_NAMESPACE_URI', 0.01722995954606696), ('isActive', 0.011822291423445106), ('cm', 0.011419494730213541)]
            if (pathSegs != null) {
// pathSegs             No	: [('n', 0.02898578563105022), ('node', 0.012536628624779979), ('current', 0.011844953224401968), ('e', 0.00982094357390759), ('hasAnimVal', 0.00886061898189695), ('value', 0.008623928504884984), ('i', 0.007390769561188091), ('userAgent', 0.006692444220879964), ('c', 0.006353580018328708), ('listeners', 0.0055904876834693895)]
                pathSegs.invalidate();
// pathSegs             No	: [('encodeParam', 0.019706096408927346), ('baseVal', 0.015875636802319643), ('userAgent', 0.01311319967747684), ('result', 0.011835190684384371), ('n', 0.011584185127315655), ('g2d', 0.009929255291564028), ('currentNode', 0.00913422801638335), ('t', 0.008081009752248637), ('s', 0.00674495152274963), ('node', 0.0066270585977595085)]
            }
            if (normalizedPathSegs != null) {
// normalizedPathSegs   No	: [('lu', 0.03307691771181952), ('emitProperties', 0.03248890124522566), ('elt', 0.031114138226087915), ('current', 0.029448474304032808), ('value', 0.0115347282429864), ('node', 0.010132867385256527), ('index', 0.009400233733210063), ('tokens', 0.008168269961960219), ('baseVal', 0.0079475980166574), ('gcDefaults', 0.007810370254280342)]
                normalizedPathSegs.invalidate();
// normalizedPathSegs   No	: [('encodeParam', 0.019706096408927346), ('baseVal', 0.015875636802319643), ('userAgent', 0.01311319967747684), ('result', 0.011835190684384371), ('n', 0.011584185127315655), ('g2d', 0.009929255291564028), ('currentNode', 0.00913422801638335), ('t', 0.008081009752248637), ('s', 0.00674495152274963), ('node', 0.0066270585977595085)]
            }
        }
        fireBaseAttributeListeners();
        if (!hasAnimVal) {
// hasAnimVal           0	: [('hasAnimVal', 0.14491290110497732), ('rect', 0.025806382930610022), ('suppressAlpha', 0.016279262176863672), ('r', 0.013092620596457853), ('ctx', 0.011997020559246578), ('bounds', 0.011751687428384018), ('val', 0.011644260764927155), ('sm', 0.011642217101129343), ('purl', 0.011639791653984686), ('inputRgn', 0.011637860279406534)]
            fireAnimatedAttributeListeners();
        }
    }

    /**
     * {@link SVGPathSegList} implementation for the base path data value.
     */
    public class BaseSVGPathSegList extends AbstractSVGPathSegList {

        /**
         * Whether the attribute is missing.
         */
        protected boolean missing;
// missing              2	: [('readonly', 0.13018322590330939), ('valid', 0.05438854006356018), ('missing', 0.05143601495347475), ('finished', 0.0514358352907233), ('isIdent', 0.03796677261295091), ('isProcessing', 0.02541168517957662), ('malformed', 0.01655112621535773), ('mutate', 0.01556778960483605), ('value', 0.012670870116326251), ('defaultValue', 0.012618498424276831)]

        /**
         * Whether the attribute is malformed.
         */
        protected boolean malformed;
// malformed            0	: [('malformed', 0.7539335075123561), ('changing', 0.011062087601943363), ('readonly', 0.006178741736669581), ('valid', 0.004671930741848561), ('isSVG12', 0.0037186260406947514), ('mutate', 0.003011997684049993), ('flowRegionBreak', 0.002812076022462761), ('isFrozen', 0.0019059302454215465), ('isActive', 0.001844518956049885), ('b', 0.0009048537977437534)]

        /**
         * Create a DOMException.
         */
        protected DOMException createDOMException(short type, String key,
// type                 0	: [('type', 0.9014291205701125), ('unit', 0.010807088828643497), ('unitType', 0.007490812594873277), ('t', 0.0034194927601987274), ('direction', 0.0017502368897129982), ('align', 0.0015549054404826144), ('val', 0.0013043126227463278), ('stringType', 0.001224565685737778), ('code', 0.0006868056431103741), ('res', 8.775640871660868e-06)]
// key                  0	: [('key', 0.36648013077273056), ('value', 0.27660756112146745), ('letter', 0.1306362106613112), ('rgb', 0.05237716085830452), ('uri', 0.02805519468653858), ('s', 0.015789422269893418), ('ns', 0.01354840213694305), ('ln', 0.0041233546375442605), ('newv', 0.0022581757645146606), ('res', 8.775640871660868e-06)]
                                                  Object[] args) {
// args                 0	: [('args', 0.7213934982923208), ('params', 0.047582116617635235), ('errorArgs', 0.008642886351779651), ('errorInfo', 0.006117633826527125), ('attValues', 0.005762154506871815), ('listeners', 0.004599489426115023), ('p', 0.00365371379051789), ('spec', 0.001067173691709939), ('colors', 0.0008706996439658261), ('res', 3.871166934970669e-05)]
            return element.createDOMException(type, key, args);
// element              0	: [('element', 0.44335901963525326), ('xmlTraitInformation', 0.0035252143758210275), ('x', 0.0025534523586752712), ('localizableSupport', 0.002496798552693972), ('y', 0.0023522980223133097), ('userAgent', 0.0021935219182553627), ('value', 0.001872914115284058), ('values', 0.0018460137786853932), ('src', 0.0015208762922494772), ('type', 0.0014237426302014882)]
// type                 0	: [('type', 0.9022871622838141), ('i', 0.000427952045857157), ('e', 0.000370067851647241), ('current', 0.0002998129311121156), ('n', 0.00029475824829224234), ('s', 0.00028318556097628846), ('ctx', 0.00025348084412036946), ('x', 0.00019717577420903107), ('prefix', 0.00015252444755225168), ('t', 0.00013975634544862955)]
// key                  0	: [('key', 0.7977861280839235), ('letter', 0.020472384125779654), ('floatValue', 0.009115264854786618), ('useCapture', 0.006835724379549183), ('listener', 0.006352823555236167), ('val', 0.005187159031674679), ('data', 0.005168737317236074), ('purl', 0.0045801956432863795), ('list', 0.004555009270013653), ('y', 0.0004342760042854519)]
// args                 0	: [('args', 0.8639007137627005), ('value', 0.018782494530234003), ('related', 0.005589997266452388), ('e', 0.001905847281379423), ('params', 0.000794128684789731), ('y', 0.0004342760042854519), ('data', 0.00042186382036257764), ('ctx', 0.0003950622835718969), ('list', 0.00038683947684386034), ('handler', 0.00038386480182439056)]
        }

        /**
         * Create a SVGException.
         */
        protected SVGException createSVGException(short type, String key,
// type                 0	: [('type', 0.9014291205701125), ('unit', 0.010807088828643497), ('unitType', 0.007490812594873277), ('t', 0.0034194927601987274), ('direction', 0.0017502368897129982), ('align', 0.0015549054404826144), ('val', 0.0013043126227463278), ('stringType', 0.001224565685737778), ('code', 0.0006868056431103741), ('res', 8.775640871660868e-06)]
// key                  0	: [('key', 0.36648013077273056), ('value', 0.27660756112146745), ('letter', 0.1306362106613112), ('rgb', 0.05237716085830452), ('uri', 0.02805519468653858), ('s', 0.015789422269893418), ('ns', 0.01354840213694305), ('ln', 0.0041233546375442605), ('newv', 0.0022581757645146606), ('res', 8.775640871660868e-06)]
                                                  Object[] args) {
// args                 0	: [('args', 0.7213934982923208), ('params', 0.047582116617635235), ('errorArgs', 0.008642886351779651), ('errorInfo', 0.006117633826527125), ('attValues', 0.005762154506871815), ('listeners', 0.004599489426115023), ('p', 0.00365371379051789), ('spec', 0.001067173691709939), ('colors', 0.0008706996439658261), ('res', 3.871166934970669e-05)]
            return ((SVGOMElement)element).createSVGException(type, key, args);
// element              0	: [('element', 0.5771161440626797), ('e', 0.17381449339369115), ('node', 0.06953654332186082), ('elt', 0.03810873938676004), ('elem', 0.021990458812750215), ('n', 0.011842939005926272), ('parent', 0.011001949304005172), ('theElt', 0.0109952042604783), ('doc', 9.8601447071275e-05), ('res', 1.0737163641017174e-05)]
// type                 0	: [('type', 0.8978405219676086), ('i', 0.000427952045857157), ('e', 0.000370067851647241), ('current', 0.0002998129311121156), ('n', 0.00029475824829224234), ('s', 0.00028318556097628846), ('ctx', 0.00025348084412036946), ('x', 0.00019717577420903107), ('prefix', 0.00015252444755225168), ('t', 0.00013975634544862955)]
// key                  0	: [('key', 0.7977861280839235), ('letter', 0.020472384125779654), ('floatValue', 0.009115264854786618), ('useCapture', 0.006835724379549183), ('listener', 0.006352823555236167), ('val', 0.005187159031674679), ('data', 0.005168737317236074), ('purl', 0.0045801956432863795), ('list', 0.004555009270013653), ('y', 0.0004342760042854519)]
// args                 0	: [('args', 0.8639007137627005), ('value', 0.018782494530234003), ('related', 0.005589997266452388), ('e', 0.001905847281379423), ('params', 0.000794128684789731), ('y', 0.0004342760042854519), ('data', 0.00042186382036257764), ('ctx', 0.0003950622835718969), ('list', 0.00038683947684386034), ('handler', 0.00038386480182439056)]
        }

        /**
         * Returns the value of the DOM attribute containing the path data.
         */
        protected String getValueAsString() {
            Attr attr = element.getAttributeNodeNS(namespaceURI, localName);
// attr                 0	: [('attr', 0.8564741275242224), ('a', 0.0814674366621686), ('node', 0.011730692196866073), ('otherAttr', 0.005112435703709816), ('atr', 0.005112435703709816), ('newAttr', 0.00035578199557584583), ('a2', 0.0001787866234923276), ('oldAttr', 0.00017820271955009564), ('res', 8.775640871660868e-06), ('xmlTraitInformation', 5.743831940841088e-06)]
// element              0	: [('element', 0.2996913681505582), ('elt', 0.07250441688772453), ('e', 0.011709644174074699), ('attrs', 0.005578130918810404), ('ati', 0.004184358834904974), ('nnm', 0.0041734727064154506), ('n', 0.003088906886821396), ('reader', 0.00048194278701482214), ('res', 2.5080798891935167e-05), ('xmlTraitInformation', 5.743831940841088e-06)]
// namespaceURI         0	: [('namespaceURI', 0.8308145198805507), ('XMLNS_NAMESPACE_URI', 0.042975369671714884), ('XML_NAMESPACE_URI', 0.03973292772413208), ('EX_NAMESPACE_URI', 0.00716344479150725), ('ns', 0.0020113975836440335), ('i', 0.000427952045857157), ('e', 0.000370067851647241), ('current', 0.0002998129311121156), ('n', 0.00029475824829224234), ('s', 0.00028318556097628846)]
// localName            0	: [('localName', 0.8128863150127332), ('qualifiedName', 0.031173983062349506), ('type', 0.009427153763337064), ('attributeName', 0.009373095951783723), ('value', 0.004806557632420508), ('name', 0.003509500372773776), ('listener', 0.003189332193827075), ('tagName', 0.00312484083386129), ('y', 0.0004342760042854519), ('ctx', 0.0003950622835718969)]
            if (attr == null) {
// attr                 0	: [('attr', 0.22501585462335322), ('defs', 0.13950665579298638), ('n', 0.06260472470293976), ('result', 0.031971422183109166), ('val', 0.029575317908862003), ('current', 0.02919537776054374), ('localNameMap', 0.028287349272126734), ('s', 0.014128671342062001), ('i', 0.005490593181052287), ('v', 0.005135346634457331)]
                return defaultValue;
// defaultValue         0	: [('defaultValue', 0.08998905962518404), ('n', 0.07786622113149666), ('result', 0.04781812125048072), ('d', 0.04507197328014297), ('EMPTY_NODE_LIST', 0.04481238448438558), ('base', 0.0447617880953524), ('node', 0.025530852411215974), ('v', 0.025262167802157648), ('value', 0.024187728930098875), ('index', 0.022566604514132088)]
            }
            return attr.getValue();
// attr                 0	: [('attr', 0.7519089492475503), ('result', 0.011396534319465686), ('ret', 0.01002806805666126), ('sb', 0.005490905486207771), ('n', 0.005244109793480074), ('value', 0.004602365560497626), ('baseVal', 0.004243778256210353), ('res', 0.00389211262319871), ('v', 0.0030227309353424974), ('report', 0.0027780717622881363)]
        }

        /**
         * Sets the DOM attribute value containing the path data.
         */
        protected void setAttributeValue(String value) {
// value                0	: [('value', 0.8766433561649148), ('prefix', 0.010362094174213578), ('name', 0.005509479186441095), ('s', 0.004772371349240631), ('ns', 0.003175262339743329), ('key', 0.0031339555989801492), ('namespaceURI', 0.002693922724336991), ('message', 0.00263659376421406), ('uri', 0.0025213794282886626), ('text', 0.002184491250743182)]
            try {
                changing = true;
// changing             0	: [('changing', 0.5150553777216377), ('h', 0.02061222767298882), ('parser', 0.012596859799263163), ('generatorContext', 0.012362361172816892), ('debuggerMethods', 0.012357363954452161), ('interpreter', 0.010532730771634422), ('reader', 0.009734123849850089), ('at', 0.008591527103237698), ('inverseTransform', 0.006819667420918737), ('current', 0.006294028513042763)]
                element.setAttributeNS(namespaceURI, localName, value);
// element              0	: [('element', 0.5892543252190309), ('valid', 0.16785505806951256), ('scale', 0.0029598756640877894), ('current', 0.0024268976883849726), ('l', 0.0023850582457922327), ('missing', 0.002368944041224024), ('changing', 0.0023653091210941875), ('partial', 0.001774067114616046), ('drawGlyphVectorWorks', 0.0017739323675524542), ('i', 0.0009321514814498308)]
// namespaceURI         0	: [('namespaceURI', 0.7525785689533684), ('XLINK_NAMESPACE_URI', 0.04544425978951796), ('XML_NAMESPACE_URI', 0.028426691634841694), ('XMLNS_NAMESPACE_URI', 0.017671584322146935), ('attr', 0.007602354057703405), ('nsURI', 0.0025354308070130475), ('EX_NAMESPACE_URI', 0.0025255528368070667), ('i', 0.000427952045857157), ('e', 0.000370067851647241), ('current', 0.0002998129311121156)]
// localName            0	: [('localName', 0.5574038588723823), ('attributeName', 0.167267832793889), ('newAttrName', 0.08206986681273136), ('qualifiedName', 0.04981433393954248), ('type', 0.009427153763337064), ('value', 0.004806557632420508), ('name', 0.003509500372773776), ('defRec', 0.002591387909255583), ('y', 0.0004342760042854519), ('ctx', 0.0003950622835718969)]
// value                0	: [('value', 0.22622067853952946), ('document', 0.04123084288453769), ('e', 0.038072943606160994), ('def', 0.0377051693998796), ('values', 0.03769770172146995), ('baseVal', 0.037693296570852464), ('factory', 0.03768760108351041), ('av', 0.037686362488887334), ('defs', 0.03768471623986667), ('namespaceURI', 0.007957398952376089)]
            } finally {
                changing = false;
// changing             0	: [('changing', 0.33270319806529935), ('element', 0.14938164489141126), ('ase', 0.07307886478171767), ('out', 0.05844473425886741), ('os', 0.05842984122744189), ('lastTargetElement', 0.05842558442174719), ('scanner', 0.029231243514868912), ('shapeNode', 0.029216125208824342), ('delegate', 0.02921492336608412), ('pathShape', 0.029213115161557734)]
            }
        }

        /**
         * Resets the value of the associated attribute.
         */
        protected void resetAttribute() {
            super.resetAttribute();
            missing = false;
// missing              0	: [('missing', 0.6250954653828074), ('i', 0.003111050316993428), ('g', 0.0024923101170123974), ('n', 0.0018853360768889512), ('current', 0.0013275233534668647), ('g2d', 0.0009058123722534283), ('p', 0.0008552184853681933), ('type', 0.0007805405816660658), ('e', 0.0007507724998203555), ('handler', 0.0005168244650244814)]
            malformed = false;
// malformed            0	: [('malformed', 0.738089412991985), ('valid', 0.01984768916241059), ('buffer', 0.004344001248594392), ('current', 0.0025022967430745465), ('anim', 0.0018446733165745598), ('isFrozen', 0.0018312813129363187), ('sb', 0.001397841313691344), ('handler', 0.0012455713363750663), ('root', 0.0012381803788808652), ('i', 0.0009321514814498308)]
        }

        /**
         * Appends the string representation of the given {@link SVGItem} to
         * the DOM attribute.  This is called in response to an append to
         * the list.
         */
        protected void resetAttribute(SVGItem item) {
// item                 0	: [('item', 0.916107787784012), ('res', 8.775640871660868e-06), ('xmlTraitInformation', 5.743831940841088e-06), ('d', 5.654000565113094e-06), ('document', 3.6777102990972383e-06), ('x1', 3.6327946112332413e-06), ('rect', 3.430674015845256e-06), ('pd', 3.228553420457271e-06), ('raf', 3.228553420457271e-06), ('iter', 2.7120230100213087e-06)]
            super.resetAttribute(item);
// item                 0	: [('item', 0.8171566599842153), ('elt', 0.002407974695925175), ('namespaceURI', 0.0024001680286988222), ('i', 0.000427952045857157), ('e', 0.000370067851647241), ('current', 0.0002998129311121156), ('n', 0.00029475824829224234), ('s', 0.00028318556097628846), ('ctx', 0.00025348084412036946), ('x', 0.00019717577420903107)]
            missing = false;
// missing              0	: [('missing', 0.4583596137154489), ('item', 0.07089865892152461), ('parent', 0.020869196034702345), ('g', 0.0017831638599209347), ('t', 0.0013442038628092273), ('values', 0.0009603997273332548), ('sb', 0.000898746272013353), ('report', 0.0007929941637721452), ('n', 0.0005881173139167638), ('computedValues', 0.00046727341681281954)]
            malformed = false;
// malformed            0	: [('malformed', 0.738089412991985), ('valid', 0.01984768916241059), ('buffer', 0.004344001248594392), ('current', 0.0025022967430745465), ('anim', 0.0018446733165745598), ('isFrozen', 0.0018312813129363187), ('sb', 0.001397841313691344), ('handler', 0.0012455713363750663), ('root', 0.0012381803788808652), ('i', 0.0009321514814498308)]
        }

        /**
         * Initializes the list, if needed.
         */
        protected void revalidate() {
            if (valid) {
// valid                4	: [('hasAnimVal', 0.041679593158570255), ('baseVal', 0.01381739292695273), ('value', 0.01147528803467745), ('animVal', 0.011373883286121704), ('valid', 0.011165353702706594), ('size', 0.010631257862340168), ('resultType', 0.00973244190997635), ('selectedContent', 0.00957019332477716), ('parent', 0.008414898023702102), ('se', 0.007997704005813174)]
                return;
            }

            valid = true;
// valid                0	: [('valid', 0.07772505566342021), ('shorthandManagers', 0.07767697003242775), ('attributes', 0.03883997577478247), ('chromaticity', 0.03883921220808879), ('fileGamma', 0.038838942713961606), ('node', 0.019494665916090795), ('list', 0.019467171910946456), ('namespaceURI', 0.019464339420525237), ('missing', 0.019420063801792127), ('animatedAttributeListeners', 0.019419794307664943)]
            missing = false;
// missing              0	: [('missing', 0.27929202096430095), ('changing', 0.07928838604417111), ('malformed', 0.03965231117145467), ('element', 0.0059209918856975895), ('scale', 0.0029598756640877894), ('current', 0.0024268976883849726), ('l', 0.0023850582457922327), ('partial', 0.001774067114616046), ('i', 0.0009321514814498308), ('g', 0.0003795848498282009)]
            malformed = false;
// malformed            0	: [('malformed', 0.738089412991985), ('valid', 0.01984768916241059), ('buffer', 0.004344001248594392), ('current', 0.0025022967430745465), ('anim', 0.0018446733165745598), ('isFrozen', 0.0018312813129363187), ('sb', 0.001397841313691344), ('handler', 0.0012455713363750663), ('root', 0.0012381803788808652), ('i', 0.0009321514814498308)]

            String s = getValueAsString();
// s                    0	: [('s', 0.6701313562051227), ('fileName', 0.08199906437770298), ('tzn', 0.04094759600677267), ('xmlAreaText', 0.04094757354892874), ('nodeName', 0.03897991114973916), ('attr', 0.009823144484562629), ('prefix', 0.002273631624295046), ('ln', 0.00206529612157694), ('type', 0.00116674588339563), ('res', 8.775640871660868e-06)]
            if (s == null) {
// s                    1	: [('current', 0.12683723479812664), ('s', 0.020760948423860087), ('n', 0.01441836590493764), ('ln', 0.012860173739934973), ('at', 0.009703797446828548), ('aci', 0.009342064203630977), ('token', 0.008975899795115748), ('o', 0.008947959095418312), ('lu', 0.007917251996990407), ('doc', 0.007863333469835396)]
                missing = true;
// missing              0	: [('missing', 0.3764331379664239), ('fileTextField', 0.12547750084125261), ('res', 0.01223458433931169), ('baseVal', 0.009476795748267024), ('report', 0.007813685598933867), ('animVal', 0.007115056643813586), ('hasAnimVal', 0.005725704152120668), ('result', 0.005638791875958086), ('outline', 0.0035500646415784726), ('sandwich', 0.002948546939908225)]
                return;
            }
            try {
                ListBuilder builder = new ListBuilder(this);
// builder              0	: [('builder', 0.963543637580827), ('res', 8.775640871660868e-06), ('xmlTraitInformation', 5.743831940841088e-06), ('d', 5.654000565113094e-06), ('document', 3.6777102990972383e-06), ('x1', 3.6327946112332413e-06), ('rect', 3.430674015845256e-06), ('pd', 3.228553420457271e-06), ('raf', 3.228553420457271e-06), ('iter', 2.7120230100213087e-06)]

                doParse(s, builder);
// s                    0	: [('s', 0.898010458288249), ('i', 0.000427952045857157), ('e', 0.000370067851647241), ('current', 0.0002998129311121156), ('n', 0.00029475824829224234), ('ctx', 0.00025348084412036946), ('x', 0.00019717577420903107), ('prefix', 0.00015252444755225168), ('t', 0.00013975634544862955), ('res', 2.9505898756068954e-05)]
// builder              0	: [('builder', 0.7571313794872134), ('attr', 0.02853570693647299), ('preserve', 0.01069358583325337), ('ctx', 0.007172170717306837), ('s', 0.005578147179280621), ('m', 0.005392087057088376), ('map', 0.005351868125207736), ('v', 0.0018960114936212788), ('filter', 0.0018103361044132533), ('cls', 0.00178344634820851)]

                if (builder.getList() != null) {
// builder              0	: [('builder', 0.5004111236049054), ('current', 0.02919537776054374), ('s', 0.014128671342062001), ('n', 0.0070491691473842134), ('i', 0.005490593181052287), ('v', 0.005135346634457331), ('idx', 0.004261013290934659), ('result', 0.004193644405331386), ('t', 0.004008200986245653), ('o', 0.003789521365130913)]
                    clear(itemList);
// itemList             0	: [('itemList', 0.8856815924465503), ('purl', 0.004601610732983508), ('i', 0.000427952045857157), ('e', 0.000370067851647241), ('current', 0.0002998129311121156), ('n', 0.00029475824829224234), ('s', 0.00028318556097628846), ('ctx', 0.00025348084412036946), ('x', 0.00019717577420903107), ('prefix', 0.00015252444755225168)]
                }
                itemList = builder.getList();
// itemList             0	: [('itemList', 0.5657053175039369), ('current', 0.0022754018685349313), ('sb', 0.0015844248011119754), ('s', 0.000828146881761369), ('c', 0.0006331186637500609), ('report', 0.0006267958550305384), ('srcSp', 0.0005923028237885062), ('g', 0.0005548202981396104), ('child', 0.0005058943744328185), ('w', 0.000428346150135395)]
// builder              0	: [('builder', 0.7417012478968675), ('parsedList', 0.1854184996800232), ('reader', 0.00048194278701482214), ('e', 0.00040012036455088905), ('n', 0.00031112910904361826), ('raf', 0.00023266541984860278), ('ctx', 0.00022063910381039912), ('t', 0.00017942112606357793), ('v', 0.00016601341157335315), ('src', 0.00016203837319738942)]
            } catch (ParseException e) {
// e                    0	: [('e', 0.5393774270080377), ('pEx', 0.27594780215894527), ('ex', 0.12515556398176833), ('res', 8.775640871660868e-06), ('xmlTraitInformation', 5.743831940841088e-06), ('d', 5.654000565113094e-06), ('document', 3.6777102990972383e-06), ('x1', 3.6327946112332413e-06), ('rect', 3.430674015845256e-06), ('pd', 3.228553420457271e-06)]
                itemList = new ArrayList(1);
// itemList             0	: [('itemList', 0.1512838629703779), ('errorHandler', 0.12201995468957441), ('unitType', 0.030261146960566823), ('e', 0.010706613671223462), ('report', 0.0034877753238257304), ('eventDispatcher', 0.002830668379898392), ('returnCode', 0.002755024228782136), ('resetTransform', 0.0024990919724157306), ('userAgent', 0.0022832641205434534), ('sb', 0.0005316995152546552)]
                malformed = true;
// malformed            1	: [('l', 0.18063304828208313), ('malformed', 0.18060078393556173), ('valid', 0.18060073028854132), ('sb', 0.12615178270926033), ('attributeInitializer', 0.041962861580296024), ('transformString', 0.014030542243249719), ('result', 0.00718469387202741), ('n', 0.0071670646823378155), ('g', 0.004524391930096373), ('t', 0.0013442038628092273)]
            }
        }
    }

    /**
     * {@link SVGPathSegList} implementation for the normalized version of the
     * base path data value.
     */
    public class NormalizedBaseSVGPathSegList
            extends AbstractSVGNormPathSegList {

        /**
         * Whether the attribute is missing.
         */
        protected boolean missing;
// missing              2	: [('readonly', 0.13018322590330939), ('valid', 0.05438854006356018), ('missing', 0.05143601495347475), ('finished', 0.0514358352907233), ('isIdent', 0.03796677261295091), ('isProcessing', 0.02541168517957662), ('malformed', 0.01655112621535773), ('mutate', 0.01556778960483605), ('value', 0.012670870116326251), ('defaultValue', 0.012618498424276831)]

        /**
         * Whether the attribute is malformed.
         */
        protected boolean malformed;
// malformed            0	: [('malformed', 0.7539335075123561), ('changing', 0.011062087601943363), ('readonly', 0.006178741736669581), ('valid', 0.004671930741848561), ('isSVG12', 0.0037186260406947514), ('mutate', 0.003011997684049993), ('flowRegionBreak', 0.002812076022462761), ('isFrozen', 0.0019059302454215465), ('isActive', 0.001844518956049885), ('b', 0.0009048537977437534)]

        /**
         * Create a DOMException.
         */
        protected DOMException createDOMException(short type, String key,
// type                 0	: [('type', 0.9014291205701125), ('unit', 0.010807088828643497), ('unitType', 0.007490812594873277), ('t', 0.0034194927601987274), ('direction', 0.0017502368897129982), ('align', 0.0015549054404826144), ('val', 0.0013043126227463278), ('stringType', 0.001224565685737778), ('code', 0.0006868056431103741), ('res', 8.775640871660868e-06)]
// key                  0	: [('key', 0.36648013077273056), ('value', 0.27660756112146745), ('letter', 0.1306362106613112), ('rgb', 0.05237716085830452), ('uri', 0.02805519468653858), ('s', 0.015789422269893418), ('ns', 0.01354840213694305), ('ln', 0.0041233546375442605), ('newv', 0.0022581757645146606), ('res', 8.775640871660868e-06)]
                                                  Object[] args) {
// args                 0	: [('args', 0.7213934982923208), ('params', 0.047582116617635235), ('errorArgs', 0.008642886351779651), ('errorInfo', 0.006117633826527125), ('attValues', 0.005762154506871815), ('listeners', 0.004599489426115023), ('p', 0.00365371379051789), ('spec', 0.001067173691709939), ('colors', 0.0008706996439658261), ('res', 3.871166934970669e-05)]
            return element.createDOMException(type, key, args);
// element              0	: [('element', 0.44335901963525326), ('xmlTraitInformation', 0.0035252143758210275), ('x', 0.0025534523586752712), ('localizableSupport', 0.002496798552693972), ('y', 0.0023522980223133097), ('userAgent', 0.0021935219182553627), ('value', 0.001872914115284058), ('values', 0.0018460137786853932), ('src', 0.0015208762922494772), ('type', 0.0014237426302014882)]
// type                 0	: [('type', 0.9022871622838141), ('i', 0.000427952045857157), ('e', 0.000370067851647241), ('current', 0.0002998129311121156), ('n', 0.00029475824829224234), ('s', 0.00028318556097628846), ('ctx', 0.00025348084412036946), ('x', 0.00019717577420903107), ('prefix', 0.00015252444755225168), ('t', 0.00013975634544862955)]
// key                  0	: [('key', 0.7977861280839235), ('letter', 0.020472384125779654), ('floatValue', 0.009115264854786618), ('useCapture', 0.006835724379549183), ('listener', 0.006352823555236167), ('val', 0.005187159031674679), ('data', 0.005168737317236074), ('purl', 0.0045801956432863795), ('list', 0.004555009270013653), ('y', 0.0004342760042854519)]
// args                 0	: [('args', 0.8639007137627005), ('value', 0.018782494530234003), ('related', 0.005589997266452388), ('e', 0.001905847281379423), ('params', 0.000794128684789731), ('y', 0.0004342760042854519), ('data', 0.00042186382036257764), ('ctx', 0.0003950622835718969), ('list', 0.00038683947684386034), ('handler', 0.00038386480182439056)]
        }

        /**
         * Create a SVGException.
         */
        protected SVGException createSVGException(short type, String key,
// type                 0	: [('type', 0.9014291205701125), ('unit', 0.010807088828643497), ('unitType', 0.007490812594873277), ('t', 0.0034194927601987274), ('direction', 0.0017502368897129982), ('align', 0.0015549054404826144), ('val', 0.0013043126227463278), ('stringType', 0.001224565685737778), ('code', 0.0006868056431103741), ('res', 8.775640871660868e-06)]
// key                  0	: [('key', 0.36648013077273056), ('value', 0.27660756112146745), ('letter', 0.1306362106613112), ('rgb', 0.05237716085830452), ('uri', 0.02805519468653858), ('s', 0.015789422269893418), ('ns', 0.01354840213694305), ('ln', 0.0041233546375442605), ('newv', 0.0022581757645146606), ('res', 8.775640871660868e-06)]
                                                  Object[] args) {
// args                 0	: [('args', 0.7213934982923208), ('params', 0.047582116617635235), ('errorArgs', 0.008642886351779651), ('errorInfo', 0.006117633826527125), ('attValues', 0.005762154506871815), ('listeners', 0.004599489426115023), ('p', 0.00365371379051789), ('spec', 0.001067173691709939), ('colors', 0.0008706996439658261), ('res', 3.871166934970669e-05)]
            return ((SVGOMElement)element).createSVGException(type, key, args);
// element              0	: [('element', 0.5771161440626797), ('e', 0.17381449339369115), ('node', 0.06953654332186082), ('elt', 0.03810873938676004), ('elem', 0.021990458812750215), ('n', 0.011842939005926272), ('parent', 0.011001949304005172), ('theElt', 0.0109952042604783), ('doc', 9.8601447071275e-05), ('res', 1.0737163641017174e-05)]
// type                 0	: [('type', 0.8978405219676086), ('i', 0.000427952045857157), ('e', 0.000370067851647241), ('current', 0.0002998129311121156), ('n', 0.00029475824829224234), ('s', 0.00028318556097628846), ('ctx', 0.00025348084412036946), ('x', 0.00019717577420903107), ('prefix', 0.00015252444755225168), ('t', 0.00013975634544862955)]
// key                  0	: [('key', 0.7977861280839235), ('letter', 0.020472384125779654), ('floatValue', 0.009115264854786618), ('useCapture', 0.006835724379549183), ('listener', 0.006352823555236167), ('val', 0.005187159031674679), ('data', 0.005168737317236074), ('purl', 0.0045801956432863795), ('list', 0.004555009270013653), ('y', 0.0004342760042854519)]
// args                 0	: [('args', 0.8639007137627005), ('value', 0.018782494530234003), ('related', 0.005589997266452388), ('e', 0.001905847281379423), ('params', 0.000794128684789731), ('y', 0.0004342760042854519), ('data', 0.00042186382036257764), ('ctx', 0.0003950622835718969), ('list', 0.00038683947684386034), ('handler', 0.00038386480182439056)]
        }

        /**
         * Returns the value of the DOM attribute containing the path data.
         */
        protected String getValueAsString() throws SVGException {
            Attr attr = element.getAttributeNodeNS(namespaceURI, localName);
// attr                 0	: [('attr', 0.3740602266715115), ('a', 0.18210386576698448), ('node', 0.09384553757492858), ('otherAttr', 0.014583696155994327), ('atr', 0.014583696155994327), ('newAttr', 0.0028462559646067667), ('a2', 0.0014302929879386208), ('oldAttr', 0.0014256217564007651), ('res', 7.020512697328694e-05), ('xmlTraitInformation', 4.59506555267287e-05)]
// element              0	: [('element', 0.266049402967783), ('elt', 0.06167550044211574), ('e', 0.023419288348149398), ('attrs', 0.011156261837620808), ('ati', 0.008368717669809948), ('nnm', 0.008346945412830901), ('n', 0.006177813773642792), ('reader', 0.0009638855740296444), ('res', 5.016159778387033e-05), ('xmlTraitInformation', 1.1487663881682176e-05)]
// namespaceURI         0	: [('namespaceURI', 0.8308145198805507), ('XMLNS_NAMESPACE_URI', 0.042975369671714884), ('XML_NAMESPACE_URI', 0.03973292772413208), ('EX_NAMESPACE_URI', 0.00716344479150725), ('ns', 0.0020113975836440335), ('i', 0.000427952045857157), ('e', 0.000370067851647241), ('current', 0.0002998129311121156), ('n', 0.00029475824829224234), ('s', 0.00028318556097628846)]
// localName            0	: [('localName', 0.8128863150127332), ('qualifiedName', 0.031173983062349506), ('type', 0.009427153763337064), ('attributeName', 0.009373095951783723), ('value', 0.004806557632420508), ('name', 0.003509500372773776), ('listener', 0.003189332193827075), ('tagName', 0.00312484083386129), ('y', 0.0004342760042854519), ('ctx', 0.0003950622835718969)]
            if (attr == null) {
// attr                 0	: [('attr', 0.22501585462335322), ('defs', 0.13950665579298638), ('n', 0.06260472470293976), ('result', 0.031971422183109166), ('val', 0.029575317908862003), ('current', 0.02919537776054374), ('localNameMap', 0.028287349272126734), ('s', 0.014128671342062001), ('i', 0.005490593181052287), ('v', 0.005135346634457331)]
                return defaultValue;
// defaultValue         0	: [('defaultValue', 0.08998905962518404), ('n', 0.07786622113149666), ('result', 0.04781812125048072), ('d', 0.04507197328014297), ('EMPTY_NODE_LIST', 0.04481238448438558), ('base', 0.0447617880953524), ('node', 0.025530852411215974), ('v', 0.025262167802157648), ('value', 0.024187728930098875), ('index', 0.022566604514132088)]
            }
            return attr.getValue();
// attr                 0	: [('attr', 0.7519089492475503), ('result', 0.011396534319465686), ('ret', 0.01002806805666126), ('sb', 0.005490905486207771), ('n', 0.005244109793480074), ('value', 0.004602365560497626), ('baseVal', 0.004243778256210353), ('res', 0.00389211262319871), ('v', 0.0030227309353424974), ('report', 0.0027780717622881363)]
        }

        /**
         * Sets the DOM attribute value containing the path data.
         */
        protected void setAttributeValue(String value) {
// value                0	: [('value', 0.8766433561649148), ('prefix', 0.010362094174213578), ('name', 0.005509479186441095), ('s', 0.004772371349240631), ('ns', 0.003175262339743329), ('key', 0.0031339555989801492), ('namespaceURI', 0.002693922724336991), ('message', 0.00263659376421406), ('uri', 0.0025213794282886626), ('text', 0.002184491250743182)]
            try {
                changing = true;
// changing             0	: [('changing', 0.5150553777216377), ('h', 0.02061222767298882), ('parser', 0.012596859799263163), ('generatorContext', 0.012362361172816892), ('debuggerMethods', 0.012357363954452161), ('interpreter', 0.010532730771634422), ('reader', 0.009734123849850089), ('at', 0.008591527103237698), ('inverseTransform', 0.006819667420918737), ('current', 0.006294028513042763)]
                element.setAttributeNS(namespaceURI, localName, value);
// element              0	: [('element', 0.5892543252190309), ('valid', 0.16785505806951256), ('scale', 0.0029598756640877894), ('current', 0.0024268976883849726), ('l', 0.0023850582457922327), ('missing', 0.002368944041224024), ('changing', 0.0023653091210941875), ('partial', 0.001774067114616046), ('drawGlyphVectorWorks', 0.0017739323675524542), ('i', 0.0009321514814498308)]
// namespaceURI         0	: [('namespaceURI', 0.7525785689533684), ('XLINK_NAMESPACE_URI', 0.04544425978951796), ('XML_NAMESPACE_URI', 0.028426691634841694), ('XMLNS_NAMESPACE_URI', 0.017671584322146935), ('attr', 0.007602354057703405), ('nsURI', 0.0025354308070130475), ('EX_NAMESPACE_URI', 0.0025255528368070667), ('i', 0.000427952045857157), ('e', 0.000370067851647241), ('current', 0.0002998129311121156)]
// localName            0	: [('localName', 0.5574038588723823), ('attributeName', 0.167267832793889), ('newAttrName', 0.08206986681273136), ('qualifiedName', 0.04981433393954248), ('type', 0.009427153763337064), ('value', 0.004806557632420508), ('name', 0.003509500372773776), ('defRec', 0.002591387909255583), ('y', 0.0004342760042854519), ('ctx', 0.0003950622835718969)]
// value                0	: [('value', 0.22622067853952946), ('document', 0.04123084288453769), ('e', 0.038072943606160994), ('def', 0.0377051693998796), ('values', 0.03769770172146995), ('baseVal', 0.037693296570852464), ('factory', 0.03768760108351041), ('av', 0.037686362488887334), ('defs', 0.03768471623986667), ('namespaceURI', 0.007957398952376089)]
            } finally {
                changing = false;
// changing             0	: [('changing', 0.33270319806529935), ('element', 0.14938164489141126), ('ase', 0.07307886478171767), ('out', 0.05844473425886741), ('os', 0.05842984122744189), ('lastTargetElement', 0.05842558442174719), ('scanner', 0.029231243514868912), ('shapeNode', 0.029216125208824342), ('delegate', 0.02921492336608412), ('pathShape', 0.029213115161557734)]
            }
        }

        /**
         * Initializes the list, if needed.
         */
        protected void revalidate() {
            if (valid) {
// valid                4	: [('hasAnimVal', 0.041679593158570255), ('baseVal', 0.01381739292695273), ('value', 0.01147528803467745), ('animVal', 0.011373883286121704), ('valid', 0.011165353702706594), ('size', 0.010631257862340168), ('resultType', 0.00973244190997635), ('selectedContent', 0.00957019332477716), ('parent', 0.008414898023702102), ('se', 0.007997704005813174)]
                return;
            }

            valid = true;
// valid                0	: [('valid', 0.07772505566342021), ('shorthandManagers', 0.07767697003242775), ('attributes', 0.03883997577478247), ('chromaticity', 0.03883921220808879), ('fileGamma', 0.038838942713961606), ('node', 0.019494665916090795), ('list', 0.019467171910946456), ('namespaceURI', 0.019464339420525237), ('missing', 0.019420063801792127), ('animatedAttributeListeners', 0.019419794307664943)]
            missing = false;
// missing              0	: [('missing', 0.27929202096430095), ('changing', 0.07928838604417111), ('malformed', 0.03965231117145467), ('element', 0.0059209918856975895), ('scale', 0.0029598756640877894), ('current', 0.0024268976883849726), ('l', 0.0023850582457922327), ('partial', 0.001774067114616046), ('i', 0.0009321514814498308), ('g', 0.0003795848498282009)]
            malformed = false;
// malformed            0	: [('malformed', 0.738089412991985), ('valid', 0.01984768916241059), ('buffer', 0.004344001248594392), ('current', 0.0025022967430745465), ('anim', 0.0018446733165745598), ('isFrozen', 0.0018312813129363187), ('sb', 0.001397841313691344), ('handler', 0.0012455713363750663), ('root', 0.0012381803788808652), ('i', 0.0009321514814498308)]

            String s = getValueAsString();
// s                    0	: [('s', 0.6701313562051227), ('fileName', 0.08199906437770298), ('tzn', 0.04094759600677267), ('xmlAreaText', 0.04094757354892874), ('nodeName', 0.03897991114973916), ('attr', 0.009823144484562629), ('prefix', 0.002273631624295046), ('ln', 0.00206529612157694), ('type', 0.00116674588339563), ('res', 8.775640871660868e-06)]
            if (s == null) {
// s                    1	: [('current', 0.12683723479812664), ('s', 0.020760948423860087), ('n', 0.01441836590493764), ('ln', 0.012860173739934973), ('at', 0.009703797446828548), ('aci', 0.009342064203630977), ('token', 0.008975899795115748), ('o', 0.008947959095418312), ('lu', 0.007917251996990407), ('doc', 0.007863333469835396)]
                missing = true;
// missing              0	: [('missing', 0.3764331379664239), ('fileTextField', 0.12547750084125261), ('res', 0.01223458433931169), ('baseVal', 0.009476795748267024), ('report', 0.007813685598933867), ('animVal', 0.007115056643813586), ('hasAnimVal', 0.005725704152120668), ('result', 0.005638791875958086), ('outline', 0.0035500646415784726), ('sandwich', 0.002948546939908225)]
                return;
            }
            try {
                ListBuilder builder = new ListBuilder(this);
// builder              0	: [('builder', 0.963543637580827), ('res', 8.775640871660868e-06), ('xmlTraitInformation', 5.743831940841088e-06), ('d', 5.654000565113094e-06), ('document', 3.6777102990972383e-06), ('x1', 3.6327946112332413e-06), ('rect', 3.430674015845256e-06), ('pd', 3.228553420457271e-06), ('raf', 3.228553420457271e-06), ('iter', 2.7120230100213087e-06)]

                doParse(s, builder);
// s                    0	: [('s', 0.898010458288249), ('i', 0.000427952045857157), ('e', 0.000370067851647241), ('current', 0.0002998129311121156), ('n', 0.00029475824829224234), ('ctx', 0.00025348084412036946), ('x', 0.00019717577420903107), ('prefix', 0.00015252444755225168), ('t', 0.00013975634544862955), ('res', 2.9505898756068954e-05)]
// builder              0	: [('builder', 0.7571313794872134), ('attr', 0.02853570693647299), ('preserve', 0.01069358583325337), ('ctx', 0.007172170717306837), ('s', 0.005578147179280621), ('m', 0.005392087057088376), ('map', 0.005351868125207736), ('v', 0.0018960114936212788), ('filter', 0.0018103361044132533), ('cls', 0.00178344634820851)]

                if (builder.getList() != null) {
// builder              0	: [('builder', 0.5004111236049054), ('current', 0.02919537776054374), ('s', 0.014128671342062001), ('n', 0.0070491691473842134), ('i', 0.005490593181052287), ('v', 0.005135346634457331), ('idx', 0.004261013290934659), ('result', 0.004193644405331386), ('t', 0.004008200986245653), ('o', 0.003789521365130913)]
                    clear(itemList);
// itemList             0	: [('itemList', 0.8856815924465503), ('purl', 0.004601610732983508), ('i', 0.000427952045857157), ('e', 0.000370067851647241), ('current', 0.0002998129311121156), ('n', 0.00029475824829224234), ('s', 0.00028318556097628846), ('ctx', 0.00025348084412036946), ('x', 0.00019717577420903107), ('prefix', 0.00015252444755225168)]
                }
                itemList = builder.getList();
// itemList             0	: [('itemList', 0.5657053175039369), ('current', 0.0022754018685349313), ('sb', 0.0015844248011119754), ('s', 0.000828146881761369), ('c', 0.0006331186637500609), ('report', 0.0006267958550305384), ('srcSp', 0.0005923028237885062), ('g', 0.0005548202981396104), ('child', 0.0005058943744328185), ('w', 0.000428346150135395)]
// builder              0	: [('builder', 0.7417012478968675), ('parsedList', 0.1854184996800232), ('reader', 0.00048194278701482214), ('e', 0.00040012036455088905), ('n', 0.00031112910904361826), ('raf', 0.00023266541984860278), ('ctx', 0.00022063910381039912), ('t', 0.00017942112606357793), ('v', 0.00016601341157335315), ('src', 0.00016203837319738942)]
            } catch (ParseException e) {
// e                    0	: [('e', 0.5393774270080377), ('pEx', 0.27594780215894527), ('ex', 0.12515556398176833), ('res', 8.775640871660868e-06), ('xmlTraitInformation', 5.743831940841088e-06), ('d', 5.654000565113094e-06), ('document', 3.6777102990972383e-06), ('x1', 3.6327946112332413e-06), ('rect', 3.430674015845256e-06), ('pd', 3.228553420457271e-06)]
                itemList = new ArrayList(1);
// itemList             0	: [('itemList', 0.1512838629703779), ('errorHandler', 0.12201995468957441), ('unitType', 0.030261146960566823), ('e', 0.010706613671223462), ('report', 0.0034877753238257304), ('eventDispatcher', 0.002830668379898392), ('returnCode', 0.002755024228782136), ('resetTransform', 0.0024990919724157306), ('userAgent', 0.0022832641205434534), ('sb', 0.0005316995152546552)]
                malformed = true;
// malformed            1	: [('l', 0.18063304828208313), ('malformed', 0.18060078393556173), ('valid', 0.18060073028854132), ('sb', 0.12615178270926033), ('attributeInitializer', 0.041962861580296024), ('transformString', 0.014030542243249719), ('result', 0.00718469387202741), ('n', 0.0071670646823378155), ('g', 0.004524391930096373), ('t', 0.0013442038628092273)]
            }
        }
    }

    /**
     * {@link SVGPathSegList} implementation for the animated path data value.
     */
    public class AnimSVGPathSegList extends AbstractSVGPathSegList {

        /**
         * Creates a new AnimSVGPathSegList.
         */
        public AnimSVGPathSegList() {
            itemList = new ArrayList(1);
// itemList             No	: [('result', 0.004996467948743233), ('userAgent', 0.002890151988400745), ('x', 0.0025886703224037564), ('in', 0.0023820728982213465), ('eventDispatcher', 0.002284068260789435), ('valueProvider', 0.0017039645622492973), ('valid', 0.0016020673843157628), ('index', 0.0014463502422404778), ('parser', 0.0013370932894150181), ('size', 0.0011997351632664548)]
        }

        /**
         * Create a DOMException.
         */
        protected DOMException createDOMException(short type, String key,
// type                 0	: [('type', 0.9014291205701125), ('unit', 0.010807088828643497), ('unitType', 0.007490812594873277), ('t', 0.0034194927601987274), ('direction', 0.0017502368897129982), ('align', 0.0015549054404826144), ('val', 0.0013043126227463278), ('stringType', 0.001224565685737778), ('code', 0.0006868056431103741), ('res', 8.775640871660868e-06)]
// key                  0	: [('key', 0.36648013077273056), ('value', 0.27660756112146745), ('letter', 0.1306362106613112), ('rgb', 0.05237716085830452), ('uri', 0.02805519468653858), ('s', 0.015789422269893418), ('ns', 0.01354840213694305), ('ln', 0.0041233546375442605), ('newv', 0.0022581757645146606), ('res', 8.775640871660868e-06)]
                                                  Object[] args) {
// args                 0	: [('args', 0.7213934982923208), ('params', 0.047582116617635235), ('errorArgs', 0.008642886351779651), ('errorInfo', 0.006117633826527125), ('attValues', 0.005762154506871815), ('listeners', 0.004599489426115023), ('p', 0.00365371379051789), ('spec', 0.001067173691709939), ('colors', 0.0008706996439658261), ('res', 3.871166934970669e-05)]
            return element.createDOMException(type, key, args);
// element              0	: [('element', 0.44335901963525326), ('xmlTraitInformation', 0.0035252143758210275), ('x', 0.0025534523586752712), ('localizableSupport', 0.002496798552693972), ('y', 0.0023522980223133097), ('userAgent', 0.0021935219182553627), ('value', 0.001872914115284058), ('values', 0.0018460137786853932), ('src', 0.0015208762922494772), ('type', 0.0014237426302014882)]
// type                 0	: [('type', 0.9022871622838141), ('i', 0.000427952045857157), ('e', 0.000370067851647241), ('current', 0.0002998129311121156), ('n', 0.00029475824829224234), ('s', 0.00028318556097628846), ('ctx', 0.00025348084412036946), ('x', 0.00019717577420903107), ('prefix', 0.00015252444755225168), ('t', 0.00013975634544862955)]
// key                  0	: [('key', 0.7977861280839235), ('letter', 0.020472384125779654), ('floatValue', 0.009115264854786618), ('useCapture', 0.006835724379549183), ('listener', 0.006352823555236167), ('val', 0.005187159031674679), ('data', 0.005168737317236074), ('purl', 0.0045801956432863795), ('list', 0.004555009270013653), ('y', 0.0004342760042854519)]
// args                 0	: [('args', 0.8639007137627005), ('value', 0.018782494530234003), ('related', 0.005589997266452388), ('e', 0.001905847281379423), ('params', 0.000794128684789731), ('y', 0.0004342760042854519), ('data', 0.00042186382036257764), ('ctx', 0.0003950622835718969), ('list', 0.00038683947684386034), ('handler', 0.00038386480182439056)]
        }

        /**
         * Create a SVGException.
         */
        protected SVGException createSVGException(short type, String key,
// type                 0	: [('type', 0.9014291205701125), ('unit', 0.010807088828643497), ('unitType', 0.007490812594873277), ('t', 0.0034194927601987274), ('direction', 0.0017502368897129982), ('align', 0.0015549054404826144), ('val', 0.0013043126227463278), ('stringType', 0.001224565685737778), ('code', 0.0006868056431103741), ('res', 8.775640871660868e-06)]
// key                  0	: [('key', 0.36648013077273056), ('value', 0.27660756112146745), ('letter', 0.1306362106613112), ('rgb', 0.05237716085830452), ('uri', 0.02805519468653858), ('s', 0.015789422269893418), ('ns', 0.01354840213694305), ('ln', 0.0041233546375442605), ('newv', 0.0022581757645146606), ('res', 8.775640871660868e-06)]
                                                  Object[] args) {
// args                 0	: [('args', 0.7213934982923208), ('params', 0.047582116617635235), ('errorArgs', 0.008642886351779651), ('errorInfo', 0.006117633826527125), ('attValues', 0.005762154506871815), ('listeners', 0.004599489426115023), ('p', 0.00365371379051789), ('spec', 0.001067173691709939), ('colors', 0.0008706996439658261), ('res', 3.871166934970669e-05)]
            return ((SVGOMElement)element).createSVGException(type, key, args);
// element              0	: [('element', 0.5771161440626797), ('e', 0.17381449339369115), ('node', 0.06953654332186082), ('elt', 0.03810873938676004), ('elem', 0.021990458812750215), ('n', 0.011842939005926272), ('parent', 0.011001949304005172), ('theElt', 0.0109952042604783), ('doc', 9.8601447071275e-05), ('res', 1.0737163641017174e-05)]
// type                 0	: [('type', 0.8978405219676086), ('i', 0.000427952045857157), ('e', 0.000370067851647241), ('current', 0.0002998129311121156), ('n', 0.00029475824829224234), ('s', 0.00028318556097628846), ('ctx', 0.00025348084412036946), ('x', 0.00019717577420903107), ('prefix', 0.00015252444755225168), ('t', 0.00013975634544862955)]
// key                  0	: [('key', 0.7977861280839235), ('letter', 0.020472384125779654), ('floatValue', 0.009115264854786618), ('useCapture', 0.006835724379549183), ('listener', 0.006352823555236167), ('val', 0.005187159031674679), ('data', 0.005168737317236074), ('purl', 0.0045801956432863795), ('list', 0.004555009270013653), ('y', 0.0004342760042854519)]
// args                 0	: [('args', 0.8639007137627005), ('value', 0.018782494530234003), ('related', 0.005589997266452388), ('e', 0.001905847281379423), ('params', 0.000794128684789731), ('y', 0.0004342760042854519), ('data', 0.00042186382036257764), ('ctx', 0.0003950622835718969), ('list', 0.00038683947684386034), ('handler', 0.00038386480182439056)]
        }

        /**
         * <b>DOM</b>: Implements {@link SVGPathSegList#getNumberOfItems()}.
         */
        public int getNumberOfItems() {
            if (hasAnimVal) {
// hasAnimVal           0	: [('hasAnimVal', 0.041679593158570255), ('baseVal', 0.01381739292695273), ('value', 0.01147528803467745), ('animVal', 0.011373883286121704), ('valid', 0.011165353702706594), ('size', 0.010631257862340168), ('resultType', 0.00973244190997635), ('selectedContent', 0.00957019332477716), ('parent', 0.008414898023702102), ('se', 0.007997704005813174)]
                return super.getNumberOfItems();
            }
            return getPathSegList().getNumberOfItems();
        }

        /**
         * <b>DOM</b>: Implements {@link SVGPathSegList#getItem(int)}.
         */
        public SVGPathSeg getItem(int index) throws DOMException {
// index                0	: [('index', 0.8780562633517446), ('i', 0.029491405622856834), ('y', 0.0030655298791671323), ('x', 0.003002616094420728), ('j', 0.00235729661569971), ('glyphIndex', 0.001960365137255936), ('idx', 0.0016175678008303106), ('charnum', 0.000944011822357618), ('m', 0.0008958489608996401), ('res', 8.775640871660868e-06)]
            if (hasAnimVal) {
// hasAnimVal           3	: [('handler', 0.3226589418656163), ('type', 0.11942439121072637), ('lu', 0.06876090844497676), ('hasAnimVal', 0.03530195425756042), ('namespaceURI', 0.034811732857327124), ('ns', 0.01733452509407206), ('attributes', 0.0172536132970342), ('arg', 0.01697284414098963), ('newAttr', 0.016965307852065785), ('values', 0.00870391716933896)]
                return super.getItem(index);
// index                0	: [('index', 0.8150370501727028), ('i', 0.09307348219138731), ('n', 0.006791639745173739), ('j', 0.00654259328035518), ('e', 0.000370067851647241), ('current', 0.0002998129311121156), ('s', 0.00028318556097628846), ('ctx', 0.00025348084412036946), ('res', 2.9505898756068954e-05), ('xmlTraitInformation', 5.743831940841088e-06)]
            }
            return getPathSegList().getItem(index);
// index                0	: [('index', 0.8150370501727028), ('i', 0.09307348219138731), ('n', 0.006791639745173739), ('j', 0.00654259328035518), ('e', 0.000370067851647241), ('current', 0.0002998129311121156), ('s', 0.00028318556097628846), ('ctx', 0.00025348084412036946), ('res', 2.9505898756068954e-05), ('xmlTraitInformation', 5.743831940841088e-06)]
        }

        /**
         * Returns the value of the DOM attribute containing the point list.
         */
        protected String getValueAsString() {
            if (itemList.size() == 0) {
// itemList             No	: [('hasAnimVal', 0.041679593158570255), ('baseVal', 0.01381739292695273), ('value', 0.01147528803467745), ('animVal', 0.011373883286121704), ('valid', 0.011165353702706594), ('size', 0.010631257862340168), ('resultType', 0.00973244190997635), ('selectedContent', 0.00957019332477716), ('parent', 0.008414898023702102), ('se', 0.007997704005813174)]
// size                 0	: [('size', 0.7564991114123445), ('iterator', 0.006821612999242008), ('set', 0.0017091198783465336), ('length', 0.0008587452372703243), ('image', 0.0007329902323780028), ('svg', 0.0005452103072749024), ('x', 0.000506863066592495), ('engine', 0.0004684147278846425), ('y', 0.00046674389978305306), ('ext', 0.00045404830989593744)]
                return "";
            }
            StringBuffer sb = new StringBuffer( itemList.size() * 8 );
// sb                   0	: [('sb', 0.8876687647409356), ('result', 0.016007591699681606), ('value', 0.015882090059098953), ('ident', 0.015355043000784167), ('lastVisitedBuffer', 0.015353650614460384), ('key', 0.0005207085702202112), ('buffer', 0.0005179462554165753), ('d', 0.0003914564697009155), ('res', 8.775640871660868e-06), ('xmlTraitInformation', 5.743831940841088e-06)]
// itemList             1	: [('value', 0.16911253429551315), ('itemList', 0.09256727232208313), ('len', 0.06945926482933658), ('count', 0.06945097963355737), ('lu', 0.046399463803443196), ('strings', 0.046279984947665595), ('s', 0.030432359094336718), ('n', 0.023433861144004718), ('size', 0.02317178224815393), ('length', 0.023168722832319903)]
// size                 0	: [('size', 0.8981657780790111), ('iterator', 0.006821612999242008), ('set', 0.0017091198783465336), ('length', 0.0008587452372703243), ('image', 0.0007329902323780028), ('svg', 0.0005452103072749024), ('x', 0.000506863066592495), ('engine', 0.0004684147278846425), ('y', 0.00046674389978305306), ('ext', 0.00045404830989593744)]
            Iterator i = itemList.iterator();
// i                    0	: [('i', 0.8310979215797022), ('iter', 0.04840365583726029), ('j', 0.0164859377327762), ('endIterator', 0.005492471819238156), ('ait', 0.005492426903550292), ('vit', 0.005492426903550292), ('ei', 0.005492426903550292), ('maxdi', 0.005492426903550292), ('trI', 0.005492426903550292), ('res', 8.775640871660868e-06)]
// itemList             0	: [('itemList', 0.2519850721318298), ('selectedNodes', 0.1260592520241405), ('srcs', 0.10253194243905461), ('children', 0.06310373018027873), ('transforms', 0.06299821704486518), ('modifierKeys', 0.06299469747084817), ('preInfo', 0.06299445043456492), ('l', 0.03511539639506457), ('idx', 0.0013922389448463306), ('oldTable', 0.0005064066946611059)]
// iterator             0	: [('iterator', 0.8022761584537875), ('size', 0.11521123262446564), ('set', 0.0017091198783465336), ('length', 0.0008587452372703243), ('image', 0.0007329902323780028), ('svg', 0.0005452103072749024), ('x', 0.000506863066592495), ('engine', 0.0004684147278846425), ('y', 0.00046674389978305306), ('ext', 0.00045404830989593744)]
            if (i.hasNext()) {
// i                    No	: [('current', 0.12683723479812664), ('s', 0.020760948423860087), ('n', 0.01441836590493764), ('ln', 0.012860173739934973), ('at', 0.009703797446828548), ('aci', 0.009342064203630977), ('token', 0.008975899795115748), ('o', 0.008947959095418312), ('lu', 0.007917251996990407), ('doc', 0.007863333469835396)]
                sb.append(((SVGItem) i.next()).getValueAsString());
// sb                   0	: [('sb', 0.15038507194927225), ('currentRegion', 0.1049581633272748), ('li', 0.044837431331582266), ('nextBeginInstanceTime', 0.03043110139905442), ('tr', 0.029836101825859003), ('gncl', 0.02981151558834429), ('ret', 0.016329363185469917), ('l', 0.015566187353482017), ('vms', 0.014912648991978089), ('sms', 0.014912401955694836)]
// i                    0	: [('i', 0.8548785260353273), ('it', 0.026200251971900183), ('itemList', 0.026195700106731376), ('aList', 0.013100166367647997), ('newItem', 0.013099859341581277), ('n', 0.00012509093388748108), ('e', 0.00011643513403273323), ('doc', 9.8601447071275e-05), ('current', 6.135805097371671e-05), ('node', 5.754760125360677e-05)]
// next                 0	: [('next', 0.9363925559999247), ('length', 0.0008587452372703243), ('image', 0.0007329902323780028), ('svg', 0.0005452103072749024), ('x', 0.000506863066592495), ('engine', 0.0004684147278846425), ('y', 0.00046674389978305306), ('ext', 0.00045404830989593744), ('value', 0.00039080365606803985), ('io', 0.00037374301066227467)]
            }
            while (i.hasNext()) {
// i                    1	: [('current', 0.3221802875329818), ('i', 0.15613686015340847), ('n', 0.06777340373497412), ('b', 0.03791813236483646), ('lu', 0.029466443873200986), ('c', 0.026124210217294892), ('p', 0.025589178800106346), ('m', 0.025550475401663976), ('node', 0.025519801400359206), ('v', 0.025323915723648064)]
                sb.append(getItemSeparator());
// sb                   0	: [('sb', 0.15038507194927225), ('currentRegion', 0.1049581633272748), ('li', 0.044837431331582266), ('nextBeginInstanceTime', 0.03043110139905442), ('tr', 0.029836101825859003), ('gncl', 0.02981151558834429), ('ret', 0.016329363185469917), ('l', 0.015566187353482017), ('vms', 0.014912648991978089), ('sms', 0.014912401955694836)]
                sb.append(((SVGItem) i.next()).getValueAsString());
// sb                   0	: [('sb', 0.4261515912095488), ('buf', 0.10148073370725313), ('svg11Factories', 0.022912742081476367), ('ctx', 0.02199116063971121), ('listeners', 0.013457877266887441), ('t', 0.009306836796930944), ('report', 0.007471985679706778), ('g', 0.006629025031480072), ('resources', 0.004583824212514797), ('model', 0.004027225189453635)]
// i                    0	: [('i', 0.8548785260353273), ('it', 0.026200251971900183), ('itemList', 0.026195700106731376), ('aList', 0.013100166367647997), ('newItem', 0.013099859341581277), ('n', 0.00012509093388748108), ('e', 0.00011643513403273323), ('doc', 9.8601447071275e-05), ('current', 6.135805097371671e-05), ('node', 5.754760125360677e-05)]
// next                 0	: [('next', 0.9363925559999247), ('length', 0.0008587452372703243), ('image', 0.0007329902323780028), ('svg', 0.0005452103072749024), ('x', 0.000506863066592495), ('engine', 0.0004684147278846425), ('y', 0.00046674389978305306), ('ext', 0.00045404830989593744), ('value', 0.00039080365606803985), ('io', 0.00037374301066227467)]
            }
            return sb.toString();
// sb                   0	: [('sb', 0.09284710121674913), ('result', 0.08505409991302074), ('ret', 0.04316182561912012), ('baseVal', 0.02823989123072526), ('values', 0.01712026499991737), ('doc', 0.0162056765530043), ('value', 0.01602833966789238), ('animVal', 0.013336540602538081), ('node', 0.008857620524463148), ('d', 0.0076984615977198426)]
        }

        /**
         * Sets the DOM attribute value containing the point list.
         */
        protected void setAttributeValue(String value) {
// value                0	: [('value', 0.8766433561649148), ('prefix', 0.010362094174213578), ('name', 0.005509479186441095), ('s', 0.004772371349240631), ('ns', 0.003175262339743329), ('key', 0.0031339555989801492), ('namespaceURI', 0.002693922724336991), ('message', 0.00263659376421406), ('uri', 0.0025213794282886626), ('text', 0.002184491250743182)]
        }

        /**
         * <b>DOM</b>: Implements {@link SVGPathSegList#clear()}.
         */
        public void clear() throws DOMException {
            throw element.createDOMException
// element              0	: [('element', 0.5743824656526116), ('svgelt', 0.2607068684989976), ('path', 0.052074423457536274), ('timedElement', 0.0020007367561957886), ('ex', 0.0011558575713081685), ('e', 0.0009946646232774831), ('se', 0.0009035889139185999), ('de', 0.000666819615493678), ('res', 8.775640871660868e-06), ('xmlTraitInformation', 5.743831940841088e-06)]
                (DOMException.NO_MODIFICATION_ALLOWED_ERR,
                 "readonly.pathseg.list", null);
        }

        /**
         * <b>DOM</b>: Implements {@link SVGPathSegList#initialize(SVGPathSeg)}.
         */
        public SVGPathSeg initialize(SVGPathSeg newItem)
// newItem              0	: [('newItem', 0.8818072345177447), ('pathSeg', 0.01732728182654042), ('seg', 0.0003145832033799254), ('res', 8.775640871660868e-06), ('xmlTraitInformation', 5.743831940841088e-06), ('d', 5.654000565113094e-06), ('document', 3.6777102990972383e-06), ('x1', 3.6327946112332413e-06), ('rect', 3.430674015845256e-06), ('pd', 3.228553420457271e-06)]
                throws DOMException, SVGException {
            throw element.createDOMException
// element              0	: [('element', 0.8832059950643764), ('svgelt', 0.0033539273225269783), ('timedElement', 0.0020007367561957886), ('ex', 0.0011558575713081685), ('td', 0.0010101259210011843), ('e', 0.0009946646232774831), ('se', 0.0009035889139185999), ('path', 0.0006038352222421563), ('doc', 0.0005105730986910946), ('res', 8.775640871660868e-06)]
                (DOMException.NO_MODIFICATION_ALLOWED_ERR,
                 "readonly.pathseg.list", null);
        }

        /**
         * <b>DOM</b>: Implements {@link
         * SVGPathSegList#insertItemBefore(SVGPathSeg, int)}.
         */
        public SVGPathSeg insertItemBefore(SVGPathSeg newItem, int index)
// newItem              0	: [('newItem', 0.8818072345177447), ('pathSeg', 0.01732728182654042), ('seg', 0.0003145832033799254), ('res', 8.775640871660868e-06), ('xmlTraitInformation', 5.743831940841088e-06), ('d', 5.654000565113094e-06), ('document', 3.6777102990972383e-06), ('x1', 3.6327946112332413e-06), ('rect', 3.430674015845256e-06), ('pd', 3.228553420457271e-06)]
// index                0	: [('index', 0.877456270166126), ('y', 0.005843930124484866), ('i', 0.003960768425769196), ('height', 0.0032506413717833488), ('width', 0.0030547455878784375), ('x', 0.0025595830132195095), ('idx', 0.0021579679098604147), ('multiplier', 0.0016823898455836238), ('off', 0.0014949048229239044), ('offset', 0.001422465309883121)]
                throws DOMException, SVGException {
            throw element.createDOMException
// element              0	: [('element', 0.8832059950643764), ('svgelt', 0.0033539273225269783), ('timedElement', 0.0020007367561957886), ('ex', 0.0011558575713081685), ('td', 0.0010101259210011843), ('e', 0.0009946646232774831), ('se', 0.0009035889139185999), ('path', 0.0006038352222421563), ('doc', 0.0005105730986910946), ('res', 8.775640871660868e-06)]
                (DOMException.NO_MODIFICATION_ALLOWED_ERR,
                 "readonly.pathseg.list", null);
        }

        /**
         * <b>DOM</b>: Implements {@link
         * SVGPathSegList#replaceItem(SVGPathSeg, int)}.
         */
        public SVGPathSeg replaceItem(SVGPathSeg newItem, int index)
// newItem              0	: [('newItem', 0.8818072345177447), ('pathSeg', 0.01732728182654042), ('seg', 0.0003145832033799254), ('res', 8.775640871660868e-06), ('xmlTraitInformation', 5.743831940841088e-06), ('d', 5.654000565113094e-06), ('document', 3.6777102990972383e-06), ('x1', 3.6327946112332413e-06), ('rect', 3.430674015845256e-06), ('pd', 3.228553420457271e-06)]
// index                0	: [('index', 0.877456270166126), ('y', 0.005843930124484866), ('i', 0.003960768425769196), ('height', 0.0032506413717833488), ('width', 0.0030547455878784375), ('x', 0.0025595830132195095), ('idx', 0.0021579679098604147), ('multiplier', 0.0016823898455836238), ('off', 0.0014949048229239044), ('offset', 0.001422465309883121)]
                throws DOMException, SVGException {
            throw element.createDOMException
// element              0	: [('element', 0.8832059950643764), ('svgelt', 0.0033539273225269783), ('timedElement', 0.0020007367561957886), ('ex', 0.0011558575713081685), ('td', 0.0010101259210011843), ('e', 0.0009946646232774831), ('se', 0.0009035889139185999), ('path', 0.0006038352222421563), ('doc', 0.0005105730986910946), ('res', 8.775640871660868e-06)]
                (DOMException.NO_MODIFICATION_ALLOWED_ERR,
                 "readonly.pathseg.list", null);
        }

        /**
         * <b>DOM</b>: Implements {@link SVGPathSegList#removeItem(int)}.
         */
        public SVGPathSeg removeItem(int index) throws DOMException {
// index                0	: [('index', 0.8780562633517446), ('i', 0.029491405622856834), ('y', 0.0030655298791671323), ('x', 0.003002616094420728), ('j', 0.00235729661569971), ('glyphIndex', 0.001960365137255936), ('idx', 0.0016175678008303106), ('charnum', 0.000944011822357618), ('m', 0.0008958489608996401), ('res', 8.775640871660868e-06)]
            throw element.createDOMException
// element              0	: [('element', 0.5743824656526116), ('svgelt', 0.2607068684989976), ('path', 0.052074423457536274), ('timedElement', 0.0020007367561957886), ('ex', 0.0011558575713081685), ('e', 0.0009946646232774831), ('se', 0.0009035889139185999), ('de', 0.000666819615493678), ('res', 8.775640871660868e-06), ('xmlTraitInformation', 5.743831940841088e-06)]
                (DOMException.NO_MODIFICATION_ALLOWED_ERR,
                 "readonly.pathseg.list", null);
        }

        /**
         * <b>DOM</b>: Implements {@link SVGPathSegList#appendItem(SVGPathSeg)}.
         */
        public SVGPathSeg appendItem(SVGPathSeg newItem) throws DOMException {
// newItem              0	: [('newItem', 0.8818072345177447), ('pathSeg', 0.01732728182654042), ('seg', 0.0003145832033799254), ('res', 8.775640871660868e-06), ('xmlTraitInformation', 5.743831940841088e-06), ('d', 5.654000565113094e-06), ('document', 3.6777102990972383e-06), ('x1', 3.6327946112332413e-06), ('rect', 3.430674015845256e-06), ('pd', 3.228553420457271e-06)]
            throw element.createDOMException
// element              0	: [('element', 0.5743824656526116), ('svgelt', 0.2607068684989976), ('path', 0.052074423457536274), ('timedElement', 0.0020007367561957886), ('ex', 0.0011558575713081685), ('e', 0.0009946646232774831), ('se', 0.0009035889139185999), ('de', 0.000666819615493678), ('res', 8.775640871660868e-06), ('xmlTraitInformation', 5.743831940841088e-06)]
                (DOMException.NO_MODIFICATION_ALLOWED_ERR,
                 "readonly.pathseg.list", null);
        }

        /**
         * Pass by reference integer for use by newItem.
         */
        private int[] parameterIndex = new int[1];
// parameterIndex       No	: [('transparency', 0.20507784678616653), ('significantBits', 0.08068507920479764), ('palette', 0.0687533317923648), ('physicalDimension', 0.06866323175495954), ('paletteHistogram', 0.06866320929711561), ('backgroundRGB', 0.0683596829191758), ('gammaLut', 0.011719393672301261), ('grayLut', 0.011718944515422621), ('pixels', 0.0076497553833286035), ('srcPixels', 0.006374587548884836)]

        /**
         * Creates a new SVGPathSegItem from the given path command and array
         * of parameter values.
         */
        protected SVGPathSegItem newItem(short command, float[] parameters,
// newItem              No	: [('pathSegItem', 0.05000696383846078), ('res', 7.020512697328694e-05), ('xmlTraitInformation', 4.59506555267287e-05), ('d', 4.523200452090475e-05), ('document', 2.9421682392777906e-05), ('x1', 2.906235688986593e-05), ('rect', 2.744539212676205e-05), ('pd', 2.582842736365817e-05), ('raf', 2.582842736365817e-05), ('iter', 2.169618408017047e-05)]
// command              No	: [('type', 0.21143296456089938), ('unit', 0.08645671062914798), ('unitType', 0.059926500758986215), ('t', 0.027355942081589823), ('direction', 0.014001895117703986), ('align', 0.012439243523860916), ('val', 0.010434500981970622), ('stringType', 0.009796525485902223), ('code', 0.0054944451448829925), ('baseVal', 0.005206718715602134)]
// parameters           No	: [('fractions', 0.1683282615663474), ('keyTimes', 0.05573884896364776), ('keySplines', 0.05573709725182106), ('positionReturn', 0.04369433834460618), ('vb', 0.04172571864140465), ('offsets', 0.03972803141109255), ('whitepoint', 0.03388992270341223), ('values', 0.0336483355452624), ('numbers', 0.02520636303352363), ('ypoints', 0.02420736221346006)]
                                         int[] j) {
// j                    No	: [('charMap', 0.09848833682942185), ('codeReturn', 0.04894081632341681), ('glyphCodes', 0.03919551172034288), ('props', 0.03852926064282511), ('xOff', 0.036708057459498324), ('yOff', 0.036707832881059006), ('yTile', 0.03670635066335949), ('xTile', 0.03670635066335949), ('yPoints', 0.03670603625354445), ('ypoints', 0.024712136797820222)]
            switch (command) {
// command              No	: [('current', 0.18530143114913591), ('value', 0.06686182806094812), ('lu', 0.06503297379321768), ('type', 0.05719565816259727), ('property', 0.04535202182392213), ('nextChar', 0.044671806593022126), ('n', 0.04350376536828017), ('bands', 0.03114833956769532), ('val', 0.017166628112649445), ('state', 0.015122021942497177)]
                case SVGPathSeg.PATHSEG_ARC_ABS:
                case SVGPathSeg.PATHSEG_ARC_REL:
                    return new SVGPathSegArcItem(command, PATHSEG_LETTERS[command],
// command              No	: [('pathSeg', 0.5833541802988431), ('i', 0.001711808183428628), ('e', 0.001480271406588964), ('current', 0.0011992517244484623), ('n', 0.0011790329931689694), ('s', 0.0011327422439051538), ('ctx', 0.0010139233764814778), ('x', 0.0007887030968361243), ('prefix', 0.0006100977902090067), ('t', 0.0005590253817945182)]
// PATHSEG_LETTERS      No	: [('y', 0.006948416068567231), ('ctx', 0.00632099653715035), ('e', 0.006251132259646529), ('CHAR_CLASS_AL', 0.004179181457507007), ('s', 0.003762335374784778), ('current', 0.0036448905466416083), ('w', 0.0028602906405132958), ('owner', 0.0025534899763237514), ('CHAR_CLASS_AI', 0.002377342914927434), ('h', 0.0022591881397666757)]
// command              No	: [('i', 0.08152940309803465), ('sp', 0.011727792351290853), ('idx', 0.009298469700217582), ('index', 0.008131658666692484), ('j', 0.006614624592834143), ('count', 0.0047342722103681165), ('b0', 0.0037206538413320092), ('glyphIndex', 0.003687316344646003), ('b1', 0.003467289136615386), ('p', 0.0028339571499086348)]
                            parameters[j[0]++],
// parameters           No	: [('rx1', 0.04254970222961504), ('dirNfile', 0.022584168414292973), ('masks', 0.02131642727576168), ('seg', 0.018728553915407314), ('vals', 0.01728134986898993), ('coords', 0.015983343471250466), ('ypoints', 0.01462768115911795), ('pts', 0.011971317507974127), ('paramTypes', 0.01062990316451568), ('gc', 0.009354381916362736)]
// j                    5	: [('i', 0.457431368215684), ('k', 0.08446852060129857), ('sp', 0.0058638961756454265), ('idx', 0.004649234850108791), ('index', 0.004065829333346242), ('j', 0.0033073122964170716), ('count', 0.0023671361051840582), ('b0', 0.0018603269206660046), ('glyphIndex', 0.0018436581723230015), ('res', 7.020512697328694e-05)]
                            parameters[j[0]++],
// parameters           No	: [('rx1', 0.04254970222961504), ('dirNfile', 0.022584168414292973), ('masks', 0.02131642727576168), ('seg', 0.018728553915407314), ('vals', 0.01728134986898993), ('coords', 0.015983343471250466), ('ypoints', 0.01462768115911795), ('pts', 0.011971317507974127), ('paramTypes', 0.01062990316451568), ('gc', 0.009354381916362736)]
// j                    5	: [('i', 0.457431368215684), ('k', 0.08446852060129857), ('sp', 0.0058638961756454265), ('idx', 0.004649234850108791), ('index', 0.004065829333346242), ('j', 0.0033073122964170716), ('count', 0.0023671361051840582), ('b0', 0.0018603269206660046), ('glyphIndex', 0.0018436581723230015), ('res', 7.020512697328694e-05)]
                            parameters[j[0]++],
// parameters           No	: [('rx1', 0.04254970222961504), ('dirNfile', 0.022584168414292973), ('masks', 0.02131642727576168), ('seg', 0.018728553915407314), ('vals', 0.01728134986898993), ('coords', 0.015983343471250466), ('ypoints', 0.01462768115911795), ('pts', 0.011971317507974127), ('paramTypes', 0.01062990316451568), ('gc', 0.009354381916362736)]
// j                    5	: [('i', 0.457431368215684), ('k', 0.08446852060129857), ('sp', 0.0058638961756454265), ('idx', 0.004649234850108791), ('index', 0.004065829333346242), ('j', 0.0033073122964170716), ('count', 0.0023671361051840582), ('b0', 0.0018603269206660046), ('glyphIndex', 0.0018436581723230015), ('res', 7.020512697328694e-05)]
                            parameters[j[0]++] != 0,
// parameters           No	: [('rx1', 0.04254970222961504), ('dirNfile', 0.022584168414292973), ('masks', 0.02131642727576168), ('seg', 0.018728553915407314), ('vals', 0.01728134986898993), ('coords', 0.015983343471250466), ('ypoints', 0.01462768115911795), ('pts', 0.011971317507974127), ('paramTypes', 0.01062990316451568), ('gc', 0.009354381916362736)]
// j                    5	: [('i', 0.457431368215684), ('k', 0.08446852060129857), ('sp', 0.0058638961756454265), ('idx', 0.004649234850108791), ('index', 0.004065829333346242), ('j', 0.0033073122964170716), ('count', 0.0023671361051840582), ('b0', 0.0018603269206660046), ('glyphIndex', 0.0018436581723230015), ('res', 7.020512697328694e-05)]
                            parameters[j[0]++] != 0,
// parameters           No	: [('endPt', 0.5003261460408587), ('ps', 0.009079723863092361), ('ctx', 0.008914432807756976), ('i', 0.006460587708045773), ('tmp', 0.004588496650500409), ('w', 0.0042227257213528135), ('t', 0.004061038649029248), ('n', 0.003914929787194176), ('idx', 0.0038315526176913375), ('size', 0.0034052957046034125)]
// j                    5	: [('i', 0.457431368215684), ('k', 0.08446852060129857), ('sp', 0.0058638961756454265), ('idx', 0.004649234850108791), ('index', 0.004065829333346242), ('j', 0.0033073122964170716), ('count', 0.0023671361051840582), ('b0', 0.0018603269206660046), ('glyphIndex', 0.0018436581723230015), ('res', 7.020512697328694e-05)]
                            parameters[j[0]++],
// parameters           No	: [('endPt', 0.5003261460408587), ('ps', 0.009079723863092361), ('ctx', 0.008914432807756976), ('i', 0.006460587708045773), ('tmp', 0.004588496650500409), ('w', 0.0042227257213528135), ('t', 0.004061038649029248), ('n', 0.003914929787194176), ('idx', 0.0038315526176913375), ('size', 0.0034052957046034125)]
// j                    5	: [('i', 0.457431368215684), ('k', 0.08446852060129857), ('sp', 0.0058638961756454265), ('idx', 0.004649234850108791), ('index', 0.004065829333346242), ('j', 0.0033073122964170716), ('count', 0.0023671361051840582), ('b0', 0.0018603269206660046), ('glyphIndex', 0.0018436581723230015), ('res', 7.020512697328694e-05)]
                            parameters[j[0]++]);
// parameters           No	: [('rx1', 0.04254970222961504), ('dirNfile', 0.022584168414292973), ('masks', 0.02131642727576168), ('seg', 0.018728553915407314), ('vals', 0.01728134986898993), ('coords', 0.015983343471250466), ('ypoints', 0.01462768115911795), ('pts', 0.011971317507974127), ('paramTypes', 0.01062990316451568), ('gc', 0.009354381916362736)]
// j                    5	: [('i', 0.457431368215684), ('k', 0.08446852060129857), ('sp', 0.0058638961756454265), ('idx', 0.004649234850108791), ('index', 0.004065829333346242), ('j', 0.0033073122964170716), ('count', 0.0023671361051840582), ('b0', 0.0018603269206660046), ('glyphIndex', 0.0018436581723230015), ('res', 7.020512697328694e-05)]
                case SVGPathSeg.PATHSEG_CLOSEPATH:
                    return new SVGPathSegItem
                        (command, PATHSEG_LETTERS[command]);
// command              No	: [('pathSeg', 0.5833541802988431), ('i', 0.001711808183428628), ('e', 0.001480271406588964), ('current', 0.0011992517244484623), ('n', 0.0011790329931689694), ('s', 0.0011327422439051538), ('ctx', 0.0010139233764814778), ('x', 0.0007887030968361243), ('prefix', 0.0006100977902090067), ('t', 0.0005590253817945182)]
// PATHSEG_LETTERS      No	: [('y', 0.006948416068567231), ('ctx', 0.00632099653715035), ('e', 0.006251132259646529), ('CHAR_CLASS_AL', 0.004179181457507007), ('s', 0.003762335374784778), ('current', 0.0036448905466416083), ('w', 0.0028602906405132958), ('owner', 0.0025534899763237514), ('CHAR_CLASS_AI', 0.002377342914927434), ('h', 0.0022591881397666757)]
// command              No	: [('i', 0.08152940309803465), ('sp', 0.011727792351290853), ('idx', 0.009298469700217582), ('index', 0.008131658666692484), ('j', 0.006614624592834143), ('count', 0.0047342722103681165), ('b0', 0.0037206538413320092), ('glyphIndex', 0.003687316344646003), ('b1', 0.003467289136615386), ('p', 0.0028339571499086348)]
                case SVGPathSeg.PATHSEG_CURVETO_CUBIC_ABS:
                case SVGPathSeg.PATHSEG_CURVETO_CUBIC_REL:
                    return new SVGPathSegCurvetoCubicItem(command, PATHSEG_LETTERS[command],
// command              No	: [('pathSeg', 0.5833541802988431), ('i', 0.001711808183428628), ('e', 0.001480271406588964), ('current', 0.0011992517244484623), ('n', 0.0011790329931689694), ('s', 0.0011327422439051538), ('ctx', 0.0010139233764814778), ('x', 0.0007887030968361243), ('prefix', 0.0006100977902090067), ('t', 0.0005590253817945182)]
// PATHSEG_LETTERS      No	: [('y', 0.006948416068567231), ('ctx', 0.00632099653715035), ('e', 0.006251132259646529), ('CHAR_CLASS_AL', 0.004179181457507007), ('s', 0.003762335374784778), ('current', 0.0036448905466416083), ('w', 0.0028602906405132958), ('owner', 0.0025534899763237514), ('CHAR_CLASS_AI', 0.002377342914927434), ('h', 0.0022591881397666757)]
// command              No	: [('i', 0.08152940309803465), ('sp', 0.011727792351290853), ('idx', 0.009298469700217582), ('index', 0.008131658666692484), ('j', 0.006614624592834143), ('count', 0.0047342722103681165), ('b0', 0.0037206538413320092), ('glyphIndex', 0.003687316344646003), ('b1', 0.003467289136615386), ('p', 0.0028339571499086348)]
                            parameters[j[0]++],
// parameters           No	: [('rx1', 0.04254970222961504), ('dirNfile', 0.022584168414292973), ('masks', 0.02131642727576168), ('seg', 0.018728553915407314), ('vals', 0.01728134986898993), ('coords', 0.015983343471250466), ('ypoints', 0.01462768115911795), ('pts', 0.011971317507974127), ('paramTypes', 0.01062990316451568), ('gc', 0.009354381916362736)]
// j                    5	: [('i', 0.457431368215684), ('k', 0.08446852060129857), ('sp', 0.0058638961756454265), ('idx', 0.004649234850108791), ('index', 0.004065829333346242), ('j', 0.0033073122964170716), ('count', 0.0023671361051840582), ('b0', 0.0018603269206660046), ('glyphIndex', 0.0018436581723230015), ('res', 7.020512697328694e-05)]
                            parameters[j[0]++],
// parameters           No	: [('rx1', 0.04254970222961504), ('dirNfile', 0.022584168414292973), ('masks', 0.02131642727576168), ('seg', 0.018728553915407314), ('vals', 0.01728134986898993), ('coords', 0.015983343471250466), ('ypoints', 0.01462768115911795), ('pts', 0.011971317507974127), ('paramTypes', 0.01062990316451568), ('gc', 0.009354381916362736)]
// j                    5	: [('i', 0.457431368215684), ('k', 0.08446852060129857), ('sp', 0.0058638961756454265), ('idx', 0.004649234850108791), ('index', 0.004065829333346242), ('j', 0.0033073122964170716), ('count', 0.0023671361051840582), ('b0', 0.0018603269206660046), ('glyphIndex', 0.0018436581723230015), ('res', 7.020512697328694e-05)]
                            parameters[j[0]++],
// parameters           No	: [('rx1', 0.04254970222961504), ('dirNfile', 0.022584168414292973), ('masks', 0.02131642727576168), ('seg', 0.018728553915407314), ('vals', 0.01728134986898993), ('coords', 0.015983343471250466), ('ypoints', 0.01462768115911795), ('pts', 0.011971317507974127), ('paramTypes', 0.01062990316451568), ('gc', 0.009354381916362736)]
// j                    5	: [('i', 0.457431368215684), ('k', 0.08446852060129857), ('sp', 0.0058638961756454265), ('idx', 0.004649234850108791), ('index', 0.004065829333346242), ('j', 0.0033073122964170716), ('count', 0.0023671361051840582), ('b0', 0.0018603269206660046), ('glyphIndex', 0.0018436581723230015), ('res', 7.020512697328694e-05)]
                            parameters[j[0]++],
// parameters           No	: [('rx1', 0.04254970222961504), ('dirNfile', 0.022584168414292973), ('masks', 0.02131642727576168), ('seg', 0.018728553915407314), ('vals', 0.01728134986898993), ('coords', 0.015983343471250466), ('ypoints', 0.01462768115911795), ('pts', 0.011971317507974127), ('paramTypes', 0.01062990316451568), ('gc', 0.009354381916362736)]
// j                    5	: [('i', 0.457431368215684), ('k', 0.08446852060129857), ('sp', 0.0058638961756454265), ('idx', 0.004649234850108791), ('index', 0.004065829333346242), ('j', 0.0033073122964170716), ('count', 0.0023671361051840582), ('b0', 0.0018603269206660046), ('glyphIndex', 0.0018436581723230015), ('res', 7.020512697328694e-05)]
                            parameters[j[0]++],
// parameters           No	: [('rx1', 0.04254970222961504), ('dirNfile', 0.022584168414292973), ('masks', 0.02131642727576168), ('seg', 0.018728553915407314), ('vals', 0.01728134986898993), ('coords', 0.015983343471250466), ('ypoints', 0.01462768115911795), ('pts', 0.011971317507974127), ('paramTypes', 0.01062990316451568), ('gc', 0.009354381916362736)]
// j                    5	: [('i', 0.457431368215684), ('k', 0.08446852060129857), ('sp', 0.0058638961756454265), ('idx', 0.004649234850108791), ('index', 0.004065829333346242), ('j', 0.0033073122964170716), ('count', 0.0023671361051840582), ('b0', 0.0018603269206660046), ('glyphIndex', 0.0018436581723230015), ('res', 7.020512697328694e-05)]
                            parameters[j[0]++]);
// parameters           No	: [('rx1', 0.04254970222961504), ('dirNfile', 0.022584168414292973), ('masks', 0.02131642727576168), ('seg', 0.018728553915407314), ('vals', 0.01728134986898993), ('coords', 0.015983343471250466), ('ypoints', 0.01462768115911795), ('pts', 0.011971317507974127), ('paramTypes', 0.01062990316451568), ('gc', 0.009354381916362736)]
// j                    5	: [('i', 0.457431368215684), ('k', 0.08446852060129857), ('sp', 0.0058638961756454265), ('idx', 0.004649234850108791), ('index', 0.004065829333346242), ('j', 0.0033073122964170716), ('count', 0.0023671361051840582), ('b0', 0.0018603269206660046), ('glyphIndex', 0.0018436581723230015), ('res', 7.020512697328694e-05)]
                case SVGPathSeg.PATHSEG_CURVETO_CUBIC_SMOOTH_ABS:
                case SVGPathSeg.PATHSEG_CURVETO_CUBIC_SMOOTH_REL:
                    return new SVGPathSegCurvetoCubicSmoothItem(command, PATHSEG_LETTERS[command],
// command              No	: [('pathSeg', 0.5833541802988431), ('i', 0.001711808183428628), ('e', 0.001480271406588964), ('current', 0.0011992517244484623), ('n', 0.0011790329931689694), ('s', 0.0011327422439051538), ('ctx', 0.0010139233764814778), ('x', 0.0007887030968361243), ('prefix', 0.0006100977902090067), ('t', 0.0005590253817945182)]
// PATHSEG_LETTERS      No	: [('y', 0.006948416068567231), ('ctx', 0.00632099653715035), ('e', 0.006251132259646529), ('CHAR_CLASS_AL', 0.004179181457507007), ('s', 0.003762335374784778), ('current', 0.0036448905466416083), ('w', 0.0028602906405132958), ('owner', 0.0025534899763237514), ('CHAR_CLASS_AI', 0.002377342914927434), ('h', 0.0022591881397666757)]
// command              No	: [('i', 0.08152940309803465), ('sp', 0.011727792351290853), ('idx', 0.009298469700217582), ('index', 0.008131658666692484), ('j', 0.006614624592834143), ('count', 0.0047342722103681165), ('b0', 0.0037206538413320092), ('glyphIndex', 0.003687316344646003), ('b1', 0.003467289136615386), ('p', 0.0028339571499086348)]
                            parameters[j[0]++],
// parameters           No	: [('rx1', 0.04254970222961504), ('dirNfile', 0.022584168414292973), ('masks', 0.02131642727576168), ('seg', 0.018728553915407314), ('vals', 0.01728134986898993), ('coords', 0.015983343471250466), ('ypoints', 0.01462768115911795), ('pts', 0.011971317507974127), ('paramTypes', 0.01062990316451568), ('gc', 0.009354381916362736)]
// j                    5	: [('i', 0.457431368215684), ('k', 0.08446852060129857), ('sp', 0.0058638961756454265), ('idx', 0.004649234850108791), ('index', 0.004065829333346242), ('j', 0.0033073122964170716), ('count', 0.0023671361051840582), ('b0', 0.0018603269206660046), ('glyphIndex', 0.0018436581723230015), ('res', 7.020512697328694e-05)]
                            parameters[j[0]++],
// parameters           No	: [('rx1', 0.04254970222961504), ('dirNfile', 0.022584168414292973), ('masks', 0.02131642727576168), ('seg', 0.018728553915407314), ('vals', 0.01728134986898993), ('coords', 0.015983343471250466), ('ypoints', 0.01462768115911795), ('pts', 0.011971317507974127), ('paramTypes', 0.01062990316451568), ('gc', 0.009354381916362736)]
// j                    5	: [('i', 0.457431368215684), ('k', 0.08446852060129857), ('sp', 0.0058638961756454265), ('idx', 0.004649234850108791), ('index', 0.004065829333346242), ('j', 0.0033073122964170716), ('count', 0.0023671361051840582), ('b0', 0.0018603269206660046), ('glyphIndex', 0.0018436581723230015), ('res', 7.020512697328694e-05)]
                            parameters[j[0]++],
// parameters           No	: [('rx1', 0.04254970222961504), ('dirNfile', 0.022584168414292973), ('masks', 0.02131642727576168), ('seg', 0.018728553915407314), ('vals', 0.01728134986898993), ('coords', 0.015983343471250466), ('ypoints', 0.01462768115911795), ('pts', 0.011971317507974127), ('paramTypes', 0.01062990316451568), ('gc', 0.009354381916362736)]
// j                    5	: [('i', 0.457431368215684), ('k', 0.08446852060129857), ('sp', 0.0058638961756454265), ('idx', 0.004649234850108791), ('index', 0.004065829333346242), ('j', 0.0033073122964170716), ('count', 0.0023671361051840582), ('b0', 0.0018603269206660046), ('glyphIndex', 0.0018436581723230015), ('res', 7.020512697328694e-05)]
                            parameters[j[0]++]);
// parameters           No	: [('rx1', 0.04254970222961504), ('dirNfile', 0.022584168414292973), ('masks', 0.02131642727576168), ('seg', 0.018728553915407314), ('vals', 0.01728134986898993), ('coords', 0.015983343471250466), ('ypoints', 0.01462768115911795), ('pts', 0.011971317507974127), ('paramTypes', 0.01062990316451568), ('gc', 0.009354381916362736)]
// j                    5	: [('i', 0.457431368215684), ('k', 0.08446852060129857), ('sp', 0.0058638961756454265), ('idx', 0.004649234850108791), ('index', 0.004065829333346242), ('j', 0.0033073122964170716), ('count', 0.0023671361051840582), ('b0', 0.0018603269206660046), ('glyphIndex', 0.0018436581723230015), ('res', 7.020512697328694e-05)]
                case SVGPathSeg.PATHSEG_CURVETO_QUADRATIC_ABS:
                case SVGPathSeg.PATHSEG_CURVETO_QUADRATIC_REL:
                    return new SVGPathSegCurvetoQuadraticItem(command, PATHSEG_LETTERS[command],
// command              No	: [('pathSeg', 0.5833541802988431), ('i', 0.001711808183428628), ('e', 0.001480271406588964), ('current', 0.0011992517244484623), ('n', 0.0011790329931689694), ('s', 0.0011327422439051538), ('ctx', 0.0010139233764814778), ('x', 0.0007887030968361243), ('prefix', 0.0006100977902090067), ('t', 0.0005590253817945182)]
// PATHSEG_LETTERS      No	: [('y', 0.006948416068567231), ('ctx', 0.00632099653715035), ('e', 0.006251132259646529), ('CHAR_CLASS_AL', 0.004179181457507007), ('s', 0.003762335374784778), ('current', 0.0036448905466416083), ('w', 0.0028602906405132958), ('owner', 0.0025534899763237514), ('CHAR_CLASS_AI', 0.002377342914927434), ('h', 0.0022591881397666757)]
// command              No	: [('i', 0.08152940309803465), ('sp', 0.011727792351290853), ('idx', 0.009298469700217582), ('index', 0.008131658666692484), ('j', 0.006614624592834143), ('count', 0.0047342722103681165), ('b0', 0.0037206538413320092), ('glyphIndex', 0.003687316344646003), ('b1', 0.003467289136615386), ('p', 0.0028339571499086348)]
                            parameters[j[0]++],
// parameters           No	: [('rx1', 0.04254970222961504), ('dirNfile', 0.022584168414292973), ('masks', 0.02131642727576168), ('seg', 0.018728553915407314), ('vals', 0.01728134986898993), ('coords', 0.015983343471250466), ('ypoints', 0.01462768115911795), ('pts', 0.011971317507974127), ('paramTypes', 0.01062990316451568), ('gc', 0.009354381916362736)]
// j                    5	: [('i', 0.457431368215684), ('k', 0.08446852060129857), ('sp', 0.0058638961756454265), ('idx', 0.004649234850108791), ('index', 0.004065829333346242), ('j', 0.0033073122964170716), ('count', 0.0023671361051840582), ('b0', 0.0018603269206660046), ('glyphIndex', 0.0018436581723230015), ('res', 7.020512697328694e-05)]
                            parameters[j[0]++],
// parameters           No	: [('rx1', 0.04254970222961504), ('dirNfile', 0.022584168414292973), ('masks', 0.02131642727576168), ('seg', 0.018728553915407314), ('vals', 0.01728134986898993), ('coords', 0.015983343471250466), ('ypoints', 0.01462768115911795), ('pts', 0.011971317507974127), ('paramTypes', 0.01062990316451568), ('gc', 0.009354381916362736)]
// j                    5	: [('i', 0.457431368215684), ('k', 0.08446852060129857), ('sp', 0.0058638961756454265), ('idx', 0.004649234850108791), ('index', 0.004065829333346242), ('j', 0.0033073122964170716), ('count', 0.0023671361051840582), ('b0', 0.0018603269206660046), ('glyphIndex', 0.0018436581723230015), ('res', 7.020512697328694e-05)]
                            parameters[j[0]++],
// parameters           No	: [('rx1', 0.04254970222961504), ('dirNfile', 0.022584168414292973), ('masks', 0.02131642727576168), ('seg', 0.018728553915407314), ('vals', 0.01728134986898993), ('coords', 0.015983343471250466), ('ypoints', 0.01462768115911795), ('pts', 0.011971317507974127), ('paramTypes', 0.01062990316451568), ('gc', 0.009354381916362736)]
// j                    5	: [('i', 0.457431368215684), ('k', 0.08446852060129857), ('sp', 0.0058638961756454265), ('idx', 0.004649234850108791), ('index', 0.004065829333346242), ('j', 0.0033073122964170716), ('count', 0.0023671361051840582), ('b0', 0.0018603269206660046), ('glyphIndex', 0.0018436581723230015), ('res', 7.020512697328694e-05)]
                            parameters[j[0]++]);
// parameters           No	: [('rx1', 0.04254970222961504), ('dirNfile', 0.022584168414292973), ('masks', 0.02131642727576168), ('seg', 0.018728553915407314), ('vals', 0.01728134986898993), ('coords', 0.015983343471250466), ('ypoints', 0.01462768115911795), ('pts', 0.011971317507974127), ('paramTypes', 0.01062990316451568), ('gc', 0.009354381916362736)]
// j                    5	: [('i', 0.457431368215684), ('k', 0.08446852060129857), ('sp', 0.0058638961756454265), ('idx', 0.004649234850108791), ('index', 0.004065829333346242), ('j', 0.0033073122964170716), ('count', 0.0023671361051840582), ('b0', 0.0018603269206660046), ('glyphIndex', 0.0018436581723230015), ('res', 7.020512697328694e-05)]
                case SVGPathSeg.PATHSEG_CURVETO_QUADRATIC_SMOOTH_ABS:
                case SVGPathSeg.PATHSEG_CURVETO_QUADRATIC_SMOOTH_REL:
                    return new SVGPathSegCurvetoQuadraticSmoothItem(command, PATHSEG_LETTERS[command],
// command              No	: [('pathSeg', 0.5833541802988431), ('i', 0.001711808183428628), ('e', 0.001480271406588964), ('current', 0.0011992517244484623), ('n', 0.0011790329931689694), ('s', 0.0011327422439051538), ('ctx', 0.0010139233764814778), ('x', 0.0007887030968361243), ('prefix', 0.0006100977902090067), ('t', 0.0005590253817945182)]
// PATHSEG_LETTERS      No	: [('y', 0.006948416068567231), ('ctx', 0.00632099653715035), ('e', 0.006251132259646529), ('CHAR_CLASS_AL', 0.004179181457507007), ('s', 0.003762335374784778), ('current', 0.0036448905466416083), ('w', 0.0028602906405132958), ('owner', 0.0025534899763237514), ('CHAR_CLASS_AI', 0.002377342914927434), ('h', 0.0022591881397666757)]
// command              No	: [('i', 0.08152940309803465), ('sp', 0.011727792351290853), ('idx', 0.009298469700217582), ('index', 0.008131658666692484), ('j', 0.006614624592834143), ('count', 0.0047342722103681165), ('b0', 0.0037206538413320092), ('glyphIndex', 0.003687316344646003), ('b1', 0.003467289136615386), ('p', 0.0028339571499086348)]
                            parameters[j[0]++],
// parameters           No	: [('rx1', 0.04254970222961504), ('dirNfile', 0.022584168414292973), ('masks', 0.02131642727576168), ('seg', 0.018728553915407314), ('vals', 0.01728134986898993), ('coords', 0.015983343471250466), ('ypoints', 0.01462768115911795), ('pts', 0.011971317507974127), ('paramTypes', 0.01062990316451568), ('gc', 0.009354381916362736)]
// j                    5	: [('i', 0.457431368215684), ('k', 0.08446852060129857), ('sp', 0.0058638961756454265), ('idx', 0.004649234850108791), ('index', 0.004065829333346242), ('j', 0.0033073122964170716), ('count', 0.0023671361051840582), ('b0', 0.0018603269206660046), ('glyphIndex', 0.0018436581723230015), ('res', 7.020512697328694e-05)]
                            parameters[j[0]++]);
// parameters           No	: [('rx1', 0.04254970222961504), ('dirNfile', 0.022584168414292973), ('masks', 0.02131642727576168), ('seg', 0.018728553915407314), ('vals', 0.01728134986898993), ('coords', 0.015983343471250466), ('ypoints', 0.01462768115911795), ('pts', 0.011971317507974127), ('paramTypes', 0.01062990316451568), ('gc', 0.009354381916362736)]
// j                    5	: [('i', 0.457431368215684), ('k', 0.08446852060129857), ('sp', 0.0058638961756454265), ('idx', 0.004649234850108791), ('index', 0.004065829333346242), ('j', 0.0033073122964170716), ('count', 0.0023671361051840582), ('b0', 0.0018603269206660046), ('glyphIndex', 0.0018436581723230015), ('res', 7.020512697328694e-05)]
                case SVGPathSeg.PATHSEG_LINETO_ABS:
                case SVGPathSeg.PATHSEG_LINETO_REL:
                case SVGPathSeg.PATHSEG_MOVETO_ABS:
                case SVGPathSeg.PATHSEG_MOVETO_REL:
                    return new SVGPathSegMovetoLinetoItem(command, PATHSEG_LETTERS[command],
// command              No	: [('pathSeg', 0.5833541802988431), ('i', 0.001711808183428628), ('e', 0.001480271406588964), ('current', 0.0011992517244484623), ('n', 0.0011790329931689694), ('s', 0.0011327422439051538), ('ctx', 0.0010139233764814778), ('x', 0.0007887030968361243), ('prefix', 0.0006100977902090067), ('t', 0.0005590253817945182)]
// PATHSEG_LETTERS      No	: [('y', 0.006948416068567231), ('ctx', 0.00632099653715035), ('e', 0.006251132259646529), ('CHAR_CLASS_AL', 0.004179181457507007), ('s', 0.003762335374784778), ('current', 0.0036448905466416083), ('w', 0.0028602906405132958), ('owner', 0.0025534899763237514), ('CHAR_CLASS_AI', 0.002377342914927434), ('h', 0.0022591881397666757)]
// command              No	: [('i', 0.08152940309803465), ('sp', 0.011727792351290853), ('idx', 0.009298469700217582), ('index', 0.008131658666692484), ('j', 0.006614624592834143), ('count', 0.0047342722103681165), ('b0', 0.0037206538413320092), ('glyphIndex', 0.003687316344646003), ('b1', 0.003467289136615386), ('p', 0.0028339571499086348)]
                            parameters[j[0]++],
// parameters           No	: [('rx1', 0.04254970222961504), ('dirNfile', 0.022584168414292973), ('masks', 0.02131642727576168), ('seg', 0.018728553915407314), ('vals', 0.01728134986898993), ('coords', 0.015983343471250466), ('ypoints', 0.01462768115911795), ('pts', 0.011971317507974127), ('paramTypes', 0.01062990316451568), ('gc', 0.009354381916362736)]
// j                    5	: [('i', 0.457431368215684), ('k', 0.08446852060129857), ('sp', 0.0058638961756454265), ('idx', 0.004649234850108791), ('index', 0.004065829333346242), ('j', 0.0033073122964170716), ('count', 0.0023671361051840582), ('b0', 0.0018603269206660046), ('glyphIndex', 0.0018436581723230015), ('res', 7.020512697328694e-05)]
                            parameters[j[0]++]);
// parameters           No	: [('rx1', 0.04254970222961504), ('dirNfile', 0.022584168414292973), ('masks', 0.02131642727576168), ('seg', 0.018728553915407314), ('vals', 0.01728134986898993), ('coords', 0.015983343471250466), ('ypoints', 0.01462768115911795), ('pts', 0.011971317507974127), ('paramTypes', 0.01062990316451568), ('gc', 0.009354381916362736)]
// j                    5	: [('i', 0.457431368215684), ('k', 0.08446852060129857), ('sp', 0.0058638961756454265), ('idx', 0.004649234850108791), ('index', 0.004065829333346242), ('j', 0.0033073122964170716), ('count', 0.0023671361051840582), ('b0', 0.0018603269206660046), ('glyphIndex', 0.0018436581723230015), ('res', 7.020512697328694e-05)]
                case SVGPathSeg.PATHSEG_LINETO_HORIZONTAL_REL:
                case SVGPathSeg.PATHSEG_LINETO_HORIZONTAL_ABS:
                    return new SVGPathSegLinetoHorizontalItem(command, PATHSEG_LETTERS[command],
// command              No	: [('pathSeg', 0.5833541802988431), ('i', 0.001711808183428628), ('e', 0.001480271406588964), ('current', 0.0011992517244484623), ('n', 0.0011790329931689694), ('s', 0.0011327422439051538), ('ctx', 0.0010139233764814778), ('x', 0.0007887030968361243), ('prefix', 0.0006100977902090067), ('t', 0.0005590253817945182)]
// PATHSEG_LETTERS      No	: [('y', 0.006948416068567231), ('ctx', 0.00632099653715035), ('e', 0.006251132259646529), ('CHAR_CLASS_AL', 0.004179181457507007), ('s', 0.003762335374784778), ('current', 0.0036448905466416083), ('w', 0.0028602906405132958), ('owner', 0.0025534899763237514), ('CHAR_CLASS_AI', 0.002377342914927434), ('h', 0.0022591881397666757)]
// command              No	: [('i', 0.08152940309803465), ('sp', 0.011727792351290853), ('idx', 0.009298469700217582), ('index', 0.008131658666692484), ('j', 0.006614624592834143), ('count', 0.0047342722103681165), ('b0', 0.0037206538413320092), ('glyphIndex', 0.003687316344646003), ('b1', 0.003467289136615386), ('p', 0.0028339571499086348)]
                            parameters[j[0]++]);
// parameters           No	: [('rx1', 0.04254970222961504), ('dirNfile', 0.022584168414292973), ('masks', 0.02131642727576168), ('seg', 0.018728553915407314), ('vals', 0.01728134986898993), ('coords', 0.015983343471250466), ('ypoints', 0.01462768115911795), ('pts', 0.011971317507974127), ('paramTypes', 0.01062990316451568), ('gc', 0.009354381916362736)]
// j                    5	: [('i', 0.457431368215684), ('k', 0.08446852060129857), ('sp', 0.0058638961756454265), ('idx', 0.004649234850108791), ('index', 0.004065829333346242), ('j', 0.0033073122964170716), ('count', 0.0023671361051840582), ('b0', 0.0018603269206660046), ('glyphIndex', 0.0018436581723230015), ('res', 7.020512697328694e-05)]
                case SVGPathSeg.PATHSEG_LINETO_VERTICAL_REL:
                case SVGPathSeg.PATHSEG_LINETO_VERTICAL_ABS:
                    return new SVGPathSegLinetoVerticalItem(command, PATHSEG_LETTERS[command],
// command              No	: [('pathSeg', 0.5833541802988431), ('i', 0.001711808183428628), ('e', 0.001480271406588964), ('current', 0.0011992517244484623), ('n', 0.0011790329931689694), ('s', 0.0011327422439051538), ('ctx', 0.0010139233764814778), ('x', 0.0007887030968361243), ('prefix', 0.0006100977902090067), ('t', 0.0005590253817945182)]
// PATHSEG_LETTERS      No	: [('y', 0.006948416068567231), ('ctx', 0.00632099653715035), ('e', 0.006251132259646529), ('CHAR_CLASS_AL', 0.004179181457507007), ('s', 0.003762335374784778), ('current', 0.0036448905466416083), ('w', 0.0028602906405132958), ('owner', 0.0025534899763237514), ('CHAR_CLASS_AI', 0.002377342914927434), ('h', 0.0022591881397666757)]
// command              No	: [('i', 0.08152940309803465), ('sp', 0.011727792351290853), ('idx', 0.009298469700217582), ('index', 0.008131658666692484), ('j', 0.006614624592834143), ('count', 0.0047342722103681165), ('b0', 0.0037206538413320092), ('glyphIndex', 0.003687316344646003), ('b1', 0.003467289136615386), ('p', 0.0028339571499086348)]
                            parameters[j[0]++]);
// parameters           No	: [('rx1', 0.04254970222961504), ('dirNfile', 0.022584168414292973), ('masks', 0.02131642727576168), ('seg', 0.018728553915407314), ('vals', 0.01728134986898993), ('coords', 0.015983343471250466), ('ypoints', 0.01462768115911795), ('pts', 0.011971317507974127), ('paramTypes', 0.01062990316451568), ('gc', 0.009354381916362736)]
// j                    5	: [('i', 0.457431368215684), ('k', 0.08446852060129857), ('sp', 0.0058638961756454265), ('idx', 0.004649234850108791), ('index', 0.004065829333346242), ('j', 0.0033073122964170716), ('count', 0.0023671361051840582), ('b0', 0.0018603269206660046), ('glyphIndex', 0.0018436581723230015), ('res', 7.020512697328694e-05)]
            }
            return null;
        }

        /**
         * Sets the animated value.
         */
        protected void setAnimatedValue(short[] commands, float[] parameters) {
// commands             2	: [('types', 0.6013417454636798), ('a', 0.009175734862914788), ('commands', 0.008989605917306234), ('shortData', 0.00898913430258366), ('values', 0.005128599714899631), ('c', 0.0045089487122774), ('u', 0.004495878247108977), ('lengthTypes', 0.004495294343166745), ('baseLengthTypes', 0.004494979933351698), ('args', 0.003028204731714733)]
// parameters           0	: [('parameters', 0.5061719300758053), ('fractions', 0.0841641307831737), ('keyTimes', 0.02786942448182388), ('keySplines', 0.02786854862591053), ('positionReturn', 0.02184716917230309), ('vb', 0.020862859320702323), ('offsets', 0.019864015705546276), ('whitepoint', 0.016944961351706114), ('values', 0.0168241677726312), ('numbers', 0.012603181516761815)]
            int size = itemList.size();
// size                 8	: [('idx', 0.054045215903301), ('i', 0.04857599741838572), ('len', 0.04305162370600343), ('n', 0.025820604641513367), ('hash', 0.02532079447928939), ('sp', 0.016424549989132536), ('x', 0.015435746441813756), ('type', 0.014024152866085167), ('size', 0.012392239174933338), ('raw', 0.010556200427271666)]
// itemList             0	: [('itemList', 0.45858467820457194), ('userListModel', 0.09032748993497869), ('atomCommands', 0.0903227864027136), ('languageListModel', 0.022260526545004867), ('pl', 0.013979155397409375), ('orderXY', 0.006989408153692402), ('icm', 0.00698584681772769), ('j', 0.006549097839423739), ('reader', 0.00048194278701482214), ('e', 0.00040012036455088905)]
// size                 0	: [('size', 0.8652112326244656), ('iterator', 0.05227615845378747), ('set', 0.0017091198783465336), ('length', 0.0008587452372703243), ('image', 0.0007329902323780028), ('svg', 0.0005452103072749024), ('x', 0.000506863066592495), ('engine', 0.0004684147278846425), ('y', 0.00046674389978305306), ('ext', 0.00045404830989593744)]
            int i = 0;
// i                    0	: [('i', 0.27266834913438204), ('newSize', 0.1523734443966546), ('cn', 0.05114726518039478), ('toSize', 0.050774919359079816), ('len', 0.023681175131125408), ('n', 0.01822211781986551), ('w', 0.017673251259181857), ('y0', 0.013537887896107154), ('length', 0.011326023357424537), ('res', 8.775640871660868e-06)]
            int[] j = parameterIndex;
// j                    No	: [('glyphs', 0.1278246295868874), ('firstGlyphSet', 0.12745965910291626), ('rangeGG', 0.12745936715094514), ('bins', 0.12745932223525727), ('dstPix', 0.027048578394823636), ('masks', 0.02246331820498367), ('pixels', 0.014115272624707914), ('tmp', 0.012655326034191528), ('samples', 0.009835508441831767), ('off', 0.007409507993380525)]
// parameterIndex       No	: [('i', 0.037665346806537874), ('info', 0.022690958821304844), ('latticeSelector', 0.018128369734934945), ('w', 0.009470254292729774), ('minIdx', 0.009081373142302342), ('ts', 0.00907475085041974), ('startY', 0.009063098001496368), ('radiusX', 0.009058451999879782), ('n', 0.006993537376853451), ('size', 0.004751891494242386)]
            j[0] = 0;
// j                    9	: [('i', 0.014914423703197293), ('g', 0.006073357597251214), ('t', 0.004524654080840524), ('n', 0.0034138247341128592), ('values', 0.003150835695430472), ('sb', 0.002871989065483172), ('report', 0.002520687522369146), ('y', 0.002490278433300689), ('p', 0.0017320915824688152), ('j', 0.0016659508703002267)]
            while (i < size && i < commands.length) {
// i                    0	: [('i', 0.1973273186423571), ('keyTimeIndex', 0.07335792875667414), ('n', 0.05752487928052321), ('iter', 0.05141457565332138), ('last', 0.044582847182356944), ('sp', 0.036459031209350526), ('it', 0.03350483354661162), ('l', 0.030140162695434496), ('gradIdx', 0.029712044703810288), ('len', 0.015177675764102455)]
// size                 0	: [('size', 0.22450337131262904), ('len', 0.20974947264061655), ('data', 0.11157554806706016), ('count', 0.088703686419501), ('numGlyphs', 0.05782453348084533), ('args', 0.03071897687235991), ('arguments', 0.027933873638427684), ('QUICK_LUT_SIZE', 0.027830198436574593), ('nTransforms', 0.02783017597873066), ('ggSz', 0.02781207053261247)]
// i                    0	: [('i', 0.7036270214407317), ('it', 0.2344004657937528), ('e', 0.000986281519373291), ('n', 0.0005374533599710541), ('baseVal', 0.0004863577901861238), ('namespaceURI', 0.000469824842212539), ('charVal', 0.0004162324769115229), ('current', 0.00033741200319560474), ('updateManager', 0.0003238699233046097), ('res', 8.775640871660868e-06)]
// commands             No	: [('values', 0.2587692558510647), ('types', 0.2586439942372839), ('pts', 0.25857702400023375), ('count', 0.05578701975283432), ('size', 0.011170037979295703), ('treePaths', 0.008437227737519993), ('toSize', 0.008437070532612468), ('len', 0.00829113930728324), ('res', 0.00011179956525072278), ('xmlTraitInformation', 5.743831940841088e-06)]
// length               0	: [('length', 0.5068699618981626), ('image', 0.005863921859024022), ('svg', 0.004361682458199219), ('x', 0.00405490453273996), ('engine', 0.00374731782307714), ('y', 0.0037339511982644245), ('ext', 0.0036323864791675), ('value', 0.003126429248544319), ('io', 0.0029899440852981973), ('res', 7.020512697328694e-05)]
                SVGPathSeg s = (SVGPathSeg) itemList.get(i);
// s                    No	: [('pathSeg', 0.3887535717506061), ('seg', 0.3762583328135197), ('newItem', 0.005006715848756597), ('res', 3.510256348664347e-05), ('xmlTraitInformation', 2.297532776336435e-05), ('d', 2.2616002260452376e-05), ('document', 1.4710841196388953e-05), ('x1', 1.4531178444932965e-05), ('rect', 1.3722696063381025e-05), ('pd', 1.2914213681829084e-05)]
// itemList             No	: [('newItem', 0.5535806710641149), ('n', 0.00025018186777496217), ('i', 0.00023324254684508754), ('e', 0.00023287026806546646), ('doc', 0.00019720289414255), ('current', 0.00012271610194743341), ('node', 0.00011509520250721353), ('o', 9.432077707328703e-05), ('evt', 9.378178881891907e-05), ('aci', 8.964093539133091e-05)]
// i                    0	: [('i', 0.5597508272291056), ('index', 0.13887305103194017), ('key', 0.009321416254131352), ('name', 0.007844628869224305), ('s', 0.0075784955539349285), ('namespaceURI', 0.006157804184090977), ('type', 0.004672817985934965), ('ns', 0.004617841329243184), ('e', 0.004473679722686476), ('res', 2.9505898756068954e-05)]
                if (s.getPathSegType() != commands[i]) {
// s                    No	: [('c', 0.08420025678304885), ('n', 0.040832952931168), ('i', 0.02576086345132256), ('a', 0.022545324922346052), ('glyphTransform', 0.021290175287937342), ('sp', 0.02101947896883812), ('currentChar', 0.020578445786762333), ('attr', 0.016307145914644522), ('ns', 0.014709733206767597), ('it', 0.014058924077101504)]
// commands             No	: [('viewBox', 0.055357140290075484), ('before', 0.034752475191552695), ('oldVT', 0.02767878397126556), ('srcCM', 0.02092313356778995), ('boundElement', 0.020811819911018156), ('gn', 0.020773410706772036), ('newSize', 0.02076191229067885), ('nodesSize', 0.02076020549454002), ('doc', 0.013922436631404213), ('element', 0.013869384912914428)]
// i                    0	: [('i', 0.08152940309803465), ('sp', 0.011727792351290853), ('idx', 0.009298469700217582), ('index', 0.008131658666692484), ('j', 0.006614624592834143), ('count', 0.0047342722103681165), ('b0', 0.0037206538413320092), ('glyphIndex', 0.003687316344646003), ('b1', 0.003467289136615386), ('p', 0.0028339571499086348)]
                    s = newItem(commands[i], parameters, j);
// s                    No	: [('count', 0.17241797258712246), ('props', 0.12927962041677266), ('tempLogicalBounds', 0.08617931626882833), ('high', 0.043094471633566535), ('glyphs', 0.043091642965549265), ('useHinting', 0.043091003265087066), ('keyTimeIndex', 0.016921614579243117), ('destPixels', 0.006796179192050273), ('sb', 0.0005316995152546552), ('c', 0.0004537775855257714)]
// newItem              No	: [('parentFontElement', 0.12281361867600896), ('s', 0.12263385166684661), ('at', 0.03368914221040205), ('gc', 0.030947292266832283), ('gradient', 0.030538914932301063), ('e', 0.015014287551844252), ('filterElement', 0.01180577682895371), ('attr', 0.009259008680688652), ('r', 0.008965228460812978), ('v', 0.005766809431842358)]
// commands             No	: [('i', 0.006847232733714512), ('e', 0.005921085626355856), ('current', 0.004797006897793849), ('n', 0.0047161319726758775), ('s', 0.004530968975620615), ('ctx', 0.004055693505925911), ('x', 0.003154812387344497), ('prefix', 0.002440391160836027), ('t', 0.002236101527178073), ('lu', 0.0019401281922919846)]
// i                    0	: [('i', 0.08152940309803465), ('sp', 0.011727792351290853), ('idx', 0.009298469700217582), ('index', 0.008131658666692484), ('j', 0.006614624592834143), ('count', 0.0047342722103681165), ('b0', 0.0037206538413320092), ('glyphIndex', 0.003687316344646003), ('b1', 0.003467289136615386), ('p', 0.0028339571499086348)]
// parameters           No	: [('paramTypes', 0.10157465460295216), ('ypoints', 0.065480157047169), ('scriptOrigin', 0.06348549695094072), ('node', 0.05094015289695464), ('toLengthList', 0.038093780042063555), ('accLengthList', 0.03809369021068783), ('aci', 0.025486993503076583), ('glyphForms', 0.02539927247146771), ('values', 0.013385001024841883), ('listeners', 0.012744977006338868)]
// j                    No	: [('y', 0.006948416068567231), ('ctx', 0.00632099653715035), ('e', 0.006251132259646529), ('CHAR_CLASS_AL', 0.004179181457507007), ('s', 0.003762335374784778), ('current', 0.0036448905466416083), ('w', 0.0028602906405132958), ('owner', 0.0025534899763237514), ('CHAR_CLASS_AI', 0.002377342914927434), ('h', 0.0022591881397666757)]
                } else {
                    switch (commands[i]) {
// commands             No	: [('idx', 0.23716571003278944), ('current', 0.11596900485030404), ('s', 0.08261904414683488), ('rule', 0.08075367847150158), ('count', 0.08066498407773308), ('application', 0.07977830352094552), ('function', 0.07903262501231442), ('sm', 0.04719955945752988), ('lu', 0.025424288315048553), ('i', 0.0016274469953521066)]
// i                    0	: [('i', 0.08152940309803465), ('sp', 0.011727792351290853), ('idx', 0.009298469700217582), ('index', 0.008131658666692484), ('j', 0.006614624592834143), ('count', 0.0047342722103681165), ('b0', 0.0037206538413320092), ('glyphIndex', 0.003687316344646003), ('b1', 0.003467289136615386), ('p', 0.0028339571499086348)]
                        case SVGPathSeg.PATHSEG_ARC_ABS:
                        case SVGPathSeg.PATHSEG_ARC_REL: {
                            SVGPathSegArcItem ps = (SVGPathSegArcItem) s;
// ps                   No	: [('res', 0.00014041025394657389), ('xmlTraitInformation', 9.19013110534574e-05), ('d', 9.04640090418095e-05), ('document', 5.884336478555581e-05), ('x1', 5.812471377973186e-05), ('rect', 5.48907842535241e-05), ('pd', 5.165685472731634e-05), ('raf', 5.165685472731634e-05), ('iter', 4.339236816034094e-05), ('info', 3.8721136622485275e-05)]
// s                    No	: [('n', 0.0020014549421996973), ('i', 0.0018659403747607003), ('e', 0.0018629621445237316), ('doc', 0.0015776231531404), ('current', 0.0009817288155794675), ('node', 0.0009207616200577083), ('o', 0.0007545662165862962), ('evt', 0.0007502543105513524), ('aci', 0.0007171274831306473), ('pathSeg', 0.0004693602849151342)]
                            ps.setR1(parameters[j[0]++]);
// ps                   No	: [('s', 0.0641534830998309), ('i', 0.03472993912432592), ('L', 0.01825929905769324), ('condition', 0.018196725595856594), ('panel', 0.00925292690372019), ('iter', 0.009156814721663825), ('gradient', 0.009148184704685742), ('message', 0.009127234786884335), ('sl', 0.009109409759976622), ('useCapture', 0.009105168478060623)]
// parameters           No	: [('r1', 0.5833831059372703), ('i', 0.001711808183428628), ('e', 0.001480271406588964), ('current', 0.0011992517244484623), ('n', 0.0011790329931689694), ('s', 0.0011327422439051538), ('ctx', 0.0010139233764814778), ('x', 0.0007887030968361243), ('prefix', 0.0006100977902090067), ('t', 0.0005590253817945182)]
// j                    5	: [('k', 0.2922342603006493), ('i', 0.22871568410784202), ('sp', 0.0029319480878227133), ('idx', 0.0023246174250543955), ('index', 0.002032914666673121), ('j', 0.0016536561482085358), ('count', 0.0011835680525920291), ('b0', 0.0009301634603330022), ('glyphIndex', 0.0009218290861615008), ('res', 3.510256348664347e-05)]
                            ps.setR2(parameters[j[0]++]);
// ps                   No	: [('properties', 0.07295246227866684), ('cs', 0.02411025608909785), ('g', 0.017495349740201872), ('sb', 0.01654835296370108), ('i', 0.015404100494463896), ('table', 0.01312817952534436), ('segments', 0.013040965694205415), ('pix', 0.007903043364867374), ('r', 0.005973887659677079), ('x', 0.005768798663920356)]
// parameters           No	: [('r2', 0.5833439050012766), ('i', 0.001711808183428628), ('e', 0.001480271406588964), ('current', 0.0011992517244484623), ('n', 0.0011790329931689694), ('s', 0.0011327422439051538), ('ctx', 0.0010139233764814778), ('x', 0.0007887030968361243), ('prefix', 0.0006100977902090067), ('t', 0.0005590253817945182)]
// j                    5	: [('k', 0.2922342603006493), ('i', 0.22871568410784202), ('sp', 0.0029319480878227133), ('idx', 0.0023246174250543955), ('index', 0.002032914666673121), ('j', 0.0016536561482085358), ('count', 0.0011835680525920291), ('b0', 0.0009301634603330022), ('glyphIndex', 0.0009218290861615008), ('res', 3.510256348664347e-05)]
                            ps.setAngle(parameters[j[0]++]);
// ps                   No	: [('properties', 0.07295246227866684), ('cs', 0.02411025608909785), ('g', 0.017495349740201872), ('sb', 0.01654835296370108), ('i', 0.015404100494463896), ('table', 0.01312817952534436), ('segments', 0.013040965694205415), ('pix', 0.007903043364867374), ('r', 0.005973887659677079), ('x', 0.005768798663920356)]
// parameters           No	: [('angle', 0.5833973959914234), ('i', 0.001711808183428628), ('e', 0.001480271406588964), ('current', 0.0011992517244484623), ('n', 0.0011790329931689694), ('s', 0.0011327422439051538), ('ctx', 0.0010139233764814778), ('x', 0.0007887030968361243), ('prefix', 0.0006100977902090067), ('t', 0.0005590253817945182)]
// j                    5	: [('k', 0.2922342603006493), ('i', 0.22871568410784202), ('sp', 0.0029319480878227133), ('idx', 0.0023246174250543955), ('index', 0.002032914666673121), ('j', 0.0016536561482085358), ('count', 0.0011835680525920291), ('b0', 0.0009301634603330022), ('glyphIndex', 0.0009218290861615008), ('res', 3.510256348664347e-05)]
                            ps.setLargeArcFlag(parameters[j[0]++] != 0);
// ps                   No	: [('properties', 0.07295246227866684), ('cs', 0.02411025608909785), ('g', 0.017495349740201872), ('sb', 0.01654835296370108), ('i', 0.015404100494463896), ('table', 0.01312817952534436), ('segments', 0.013040965694205415), ('pix', 0.007903043364867374), ('r', 0.005973887659677079), ('x', 0.005768798663920356)]
// parameters           No	: [('largeArcFlag', 0.5833510638560138), ('i', 0.001711808183428628), ('e', 0.001480271406588964), ('current', 0.0011992517244484623), ('n', 0.0011790329931689694), ('s', 0.0011327422439051538), ('ctx', 0.0010139233764814778), ('x', 0.0007887030968361243), ('prefix', 0.0006100977902090067), ('t', 0.0005590253817945182)]
// j                    5	: [('k', 0.2922342603006493), ('i', 0.22871568410784202), ('sp', 0.0029319480878227133), ('idx', 0.0023246174250543955), ('index', 0.002032914666673121), ('j', 0.0016536561482085358), ('count', 0.0011835680525920291), ('b0', 0.0009301634603330022), ('glyphIndex', 0.0009218290861615008), ('res', 3.510256348664347e-05)]
                            ps.setSweepFlag(parameters[j[0]++] != 0);
// ps                   No	: [('vNeeded', 0.500869471371398), ('p', 0.03259289165456094), ('g', 0.029251259226691185), ('cs', 0.004677918874274243), ('t', 0.004400736492741742), ('param', 0.003793176287818548), ('dy', 0.003586700497055928), ('inputMap', 0.0034898095284830503), ('target', 0.0029823258093298493), ('gbc', 0.0026473220171075854)]
// parameters           No	: [('sweepFlag', 0.5833510638560138), ('i', 0.001711808183428628), ('e', 0.001480271406588964), ('current', 0.0011992517244484623), ('n', 0.0011790329931689694), ('s', 0.0011327422439051538), ('ctx', 0.0010139233764814778), ('x', 0.0007887030968361243), ('prefix', 0.0006100977902090067), ('t', 0.0005590253817945182)]
// j                    5	: [('k', 0.2922342603006493), ('i', 0.22871568410784202), ('sp', 0.0029319480878227133), ('idx', 0.0023246174250543955), ('index', 0.002032914666673121), ('j', 0.0016536561482085358), ('count', 0.0011835680525920291), ('b0', 0.0009301634603330022), ('glyphIndex', 0.0009218290861615008), ('res', 3.510256348664347e-05)]
                            ps.setX(parameters[j[0]++]);
// ps                   No	: [('vNeeded', 0.500869471371398), ('p', 0.03259289165456094), ('g', 0.029251259226691185), ('cs', 0.004677918874274243), ('t', 0.004400736492741742), ('param', 0.003793176287818548), ('dy', 0.003586700497055928), ('inputMap', 0.0034898095284830503), ('target', 0.0029823258093298493), ('gbc', 0.0026473220171075854)]
// parameters           No	: [('x', 0.531145845953979), ('value', 0.02997925926579725), ('pts', 0.029497653761755182), ('i', 0.001711808183428628), ('e', 0.001480271406588964), ('current', 0.0011992517244484623), ('n', 0.0011790329931689694), ('s', 0.0011327422439051538), ('ctx', 0.0010139233764814778), ('res', 0.00011802359502427581)]
// j                    5	: [('k', 0.2922342603006493), ('i', 0.22871568410784202), ('sp', 0.0029319480878227133), ('idx', 0.0023246174250543955), ('index', 0.002032914666673121), ('j', 0.0016536561482085358), ('count', 0.0011835680525920291), ('b0', 0.0009301634603330022), ('glyphIndex', 0.0009218290861615008), ('res', 3.510256348664347e-05)]
                            ps.setY(parameters[j[0]++]);
// ps                   No	: [('properties', 0.07295246227866684), ('cs', 0.02411025608909785), ('g', 0.017495349740201872), ('sb', 0.01654835296370108), ('i', 0.015404100494463896), ('table', 0.01312817952534436), ('segments', 0.013040965694205415), ('pix', 0.007903043364867374), ('r', 0.005973887659677079), ('x', 0.005768798663920356)]
// parameters           No	: [('y', 0.5307736755972535), ('value', 0.02997925926579725), ('pts', 0.029497653761755182), ('i', 0.001711808183428628), ('e', 0.001480271406588964), ('current', 0.0011992517244484623), ('n', 0.0011790329931689694), ('s', 0.0011327422439051538), ('ctx', 0.0010139233764814778), ('x', 0.0007887030968361243)]
// j                    5	: [('k', 0.2922342603006493), ('i', 0.22871568410784202), ('sp', 0.0029319480878227133), ('idx', 0.0023246174250543955), ('index', 0.002032914666673121), ('j', 0.0016536561482085358), ('count', 0.0011835680525920291), ('b0', 0.0009301634603330022), ('glyphIndex', 0.0009218290861615008), ('res', 3.510256348664347e-05)]
                            break;
                        }
                        case SVGPathSeg.PATHSEG_CLOSEPATH:
                            // Nothing to update.
                            break;
                        case SVGPathSeg.PATHSEG_CURVETO_CUBIC_ABS:
                        case SVGPathSeg.PATHSEG_CURVETO_CUBIC_REL: {
                            SVGPathSegCurvetoCubicItem ps =
// ps                   No	: [('res', 0.00014041025394657389), ('xmlTraitInformation', 9.19013110534574e-05), ('d', 9.04640090418095e-05), ('document', 5.884336478555581e-05), ('x1', 5.812471377973186e-05), ('rect', 5.48907842535241e-05), ('pd', 5.165685472731634e-05), ('raf', 5.165685472731634e-05), ('iter', 4.339236816034094e-05), ('info', 3.8721136622485275e-05)]
                                (SVGPathSegCurvetoCubicItem) s;
// s                    No	: [('n', 0.0020014549421996973), ('i', 0.0018659403747607003), ('e', 0.0018629621445237316), ('doc', 0.0015776231531404), ('current', 0.0009817288155794675), ('node', 0.0009207616200577083), ('o', 0.0007545662165862962), ('evt', 0.0007502543105513524), ('aci', 0.0007171274831306473), ('pathSeg', 0.0004693602849151342)]
                            ps.setX1(parameters[j[0]++]);
// ps                   No	: [('s', 0.0641534830998309), ('i', 0.03472993912432592), ('L', 0.01825929905769324), ('condition', 0.018196725595856594), ('panel', 0.00925292690372019), ('iter', 0.009156814721663825), ('gradient', 0.009148184704685742), ('message', 0.009127234786884335), ('sl', 0.009109409759976622), ('useCapture', 0.009105168478060623)]
// parameters           No	: [('x1', 0.44650702090216), ('x2', 0.17861891394026927), ('i', 0.001711808183428628), ('e', 0.001480271406588964), ('current', 0.0011992517244484623), ('n', 0.0011790329931689694), ('s', 0.0011327422439051538), ('ctx', 0.0010139233764814778), ('x', 0.0007887030968361243), ('prefix', 0.0006100977902090067)]
// j                    5	: [('k', 0.2922342603006493), ('i', 0.22871568410784202), ('sp', 0.0029319480878227133), ('idx', 0.0023246174250543955), ('index', 0.002032914666673121), ('j', 0.0016536561482085358), ('count', 0.0011835680525920291), ('b0', 0.0009301634603330022), ('glyphIndex', 0.0009218290861615008), ('res', 3.510256348664347e-05)]
                            ps.setY1(parameters[j[0]++]);
// ps                   No	: [('properties', 0.07295246227866684), ('cs', 0.02411025608909785), ('g', 0.017495349740201872), ('sb', 0.01654835296370108), ('i', 0.015404100494463896), ('table', 0.01312817952534436), ('segments', 0.013040965694205415), ('pix', 0.007903043364867374), ('r', 0.005973887659677079), ('x', 0.005768798663920356)]
// parameters           No	: [('y1', 0.44649125209529217), ('y2', 0.17860946785043588), ('i', 0.001711808183428628), ('e', 0.001480271406588964), ('current', 0.0011992517244484623), ('n', 0.0011790329931689694), ('s', 0.0011327422439051538), ('ctx', 0.0010139233764814778), ('x', 0.0007887030968361243), ('prefix', 0.0006100977902090067)]
// j                    5	: [('k', 0.2922342603006493), ('i', 0.22871568410784202), ('sp', 0.0029319480878227133), ('idx', 0.0023246174250543955), ('index', 0.002032914666673121), ('j', 0.0016536561482085358), ('count', 0.0011835680525920291), ('b0', 0.0009301634603330022), ('glyphIndex', 0.0009218290861615008), ('res', 3.510256348664347e-05)]
                            ps.setX2(parameters[j[0]++]);
// ps                   No	: [('properties', 0.07295246227866684), ('cs', 0.02411025608909785), ('g', 0.017495349740201872), ('sb', 0.01654835296370108), ('i', 0.015404100494463896), ('table', 0.01312817952534436), ('segments', 0.013040965694205415), ('pix', 0.007903043364867374), ('r', 0.005973887659677079), ('x', 0.005768798663920356)]
// parameters           No	: [('x2', 0.6154321007534561), ('i', 0.001711808183428628), ('e', 0.001480271406588964), ('current', 0.0011992517244484623), ('n', 0.0011790329931689694), ('s', 0.0011327422439051538), ('ctx', 0.0010139233764814778), ('x', 0.0007887030968361243), ('prefix', 0.0006100977902090067), ('t', 0.0005590253817945182)]
// j                    5	: [('k', 0.2922342603006493), ('i', 0.22871568410784202), ('sp', 0.0029319480878227133), ('idx', 0.0023246174250543955), ('index', 0.002032914666673121), ('j', 0.0016536561482085358), ('count', 0.0011835680525920291), ('b0', 0.0009301634603330022), ('glyphIndex', 0.0009218290861615008), ('res', 3.510256348664347e-05)]
                            ps.setY2(parameters[j[0]++]);
// ps                   No	: [('properties', 0.07295246227866684), ('cs', 0.02411025608909785), ('g', 0.017495349740201872), ('sb', 0.01654835296370108), ('i', 0.015404100494463896), ('table', 0.01312817952534436), ('segments', 0.013040965694205415), ('pix', 0.007903043364867374), ('r', 0.005973887659677079), ('x', 0.005768798663920356)]
// parameters           No	: [('y2', 0.6154226546636228), ('i', 0.001711808183428628), ('e', 0.001480271406588964), ('current', 0.0011992517244484623), ('n', 0.0011790329931689694), ('s', 0.0011327422439051538), ('ctx', 0.0010139233764814778), ('x', 0.0007887030968361243), ('prefix', 0.0006100977902090067), ('t', 0.0005590253817945182)]
// j                    5	: [('k', 0.2922342603006493), ('i', 0.22871568410784202), ('sp', 0.0029319480878227133), ('idx', 0.0023246174250543955), ('index', 0.002032914666673121), ('j', 0.0016536561482085358), ('count', 0.0011835680525920291), ('b0', 0.0009301634603330022), ('glyphIndex', 0.0009218290861615008), ('res', 3.510256348664347e-05)]
                            ps.setX(parameters[j[0]++]);
// ps                   No	: [('properties', 0.07295246227866684), ('cs', 0.02411025608909785), ('g', 0.017495349740201872), ('sb', 0.01654835296370108), ('i', 0.015404100494463896), ('table', 0.01312817952534436), ('segments', 0.013040965694205415), ('pix', 0.007903043364867374), ('r', 0.005973887659677079), ('x', 0.005768798663920356)]
// parameters           No	: [('x', 0.531145845953979), ('value', 0.02997925926579725), ('pts', 0.029497653761755182), ('i', 0.001711808183428628), ('e', 0.001480271406588964), ('current', 0.0011992517244484623), ('n', 0.0011790329931689694), ('s', 0.0011327422439051538), ('ctx', 0.0010139233764814778), ('res', 0.00011802359502427581)]
// j                    5	: [('k', 0.2922342603006493), ('i', 0.22871568410784202), ('sp', 0.0029319480878227133), ('idx', 0.0023246174250543955), ('index', 0.002032914666673121), ('j', 0.0016536561482085358), ('count', 0.0011835680525920291), ('b0', 0.0009301634603330022), ('glyphIndex', 0.0009218290861615008), ('res', 3.510256348664347e-05)]
                            ps.setY(parameters[j[0]++]);
// ps                   No	: [('properties', 0.07295246227866684), ('cs', 0.02411025608909785), ('g', 0.017495349740201872), ('sb', 0.01654835296370108), ('i', 0.015404100494463896), ('table', 0.01312817952534436), ('segments', 0.013040965694205415), ('pix', 0.007903043364867374), ('r', 0.005973887659677079), ('x', 0.005768798663920356)]
// parameters           No	: [('y', 0.5307736755972535), ('value', 0.02997925926579725), ('pts', 0.029497653761755182), ('i', 0.001711808183428628), ('e', 0.001480271406588964), ('current', 0.0011992517244484623), ('n', 0.0011790329931689694), ('s', 0.0011327422439051538), ('ctx', 0.0010139233764814778), ('x', 0.0007887030968361243)]
// j                    5	: [('k', 0.2922342603006493), ('i', 0.22871568410784202), ('sp', 0.0029319480878227133), ('idx', 0.0023246174250543955), ('index', 0.002032914666673121), ('j', 0.0016536561482085358), ('count', 0.0011835680525920291), ('b0', 0.0009301634603330022), ('glyphIndex', 0.0009218290861615008), ('res', 3.510256348664347e-05)]
                            break;
                        }
                        case SVGPathSeg.PATHSEG_CURVETO_CUBIC_SMOOTH_ABS:
                        case SVGPathSeg.PATHSEG_CURVETO_CUBIC_SMOOTH_REL: {
                            SVGPathSegCurvetoCubicSmoothItem ps =
// ps                   No	: [('res', 0.00014041025394657389), ('xmlTraitInformation', 9.19013110534574e-05), ('d', 9.04640090418095e-05), ('document', 5.884336478555581e-05), ('x1', 5.812471377973186e-05), ('rect', 5.48907842535241e-05), ('pd', 5.165685472731634e-05), ('raf', 5.165685472731634e-05), ('iter', 4.339236816034094e-05), ('info', 3.8721136622485275e-05)]
                                (SVGPathSegCurvetoCubicSmoothItem) s;
// s                    No	: [('n', 0.0020014549421996973), ('i', 0.0018659403747607003), ('e', 0.0018629621445237316), ('doc', 0.0015776231531404), ('current', 0.0009817288155794675), ('node', 0.0009207616200577083), ('o', 0.0007545662165862962), ('evt', 0.0007502543105513524), ('aci', 0.0007171274831306473), ('pathSeg', 0.0004693602849151342)]
                            ps.setX2(parameters[j[0]++]);
// ps                   No	: [('s', 0.0641534830998309), ('i', 0.03472993912432592), ('L', 0.01825929905769324), ('condition', 0.018196725595856594), ('panel', 0.00925292690372019), ('iter', 0.009156814721663825), ('gradient', 0.009148184704685742), ('message', 0.009127234786884335), ('sl', 0.009109409759976622), ('useCapture', 0.009105168478060623)]
// parameters           No	: [('x2', 0.6154321007534561), ('i', 0.001711808183428628), ('e', 0.001480271406588964), ('current', 0.0011992517244484623), ('n', 0.0011790329931689694), ('s', 0.0011327422439051538), ('ctx', 0.0010139233764814778), ('x', 0.0007887030968361243), ('prefix', 0.0006100977902090067), ('t', 0.0005590253817945182)]
// j                    5	: [('k', 0.2922342603006493), ('i', 0.22871568410784202), ('sp', 0.0029319480878227133), ('idx', 0.0023246174250543955), ('index', 0.002032914666673121), ('j', 0.0016536561482085358), ('count', 0.0011835680525920291), ('b0', 0.0009301634603330022), ('glyphIndex', 0.0009218290861615008), ('res', 3.510256348664347e-05)]
                            ps.setY2(parameters[j[0]++]);
// ps                   No	: [('properties', 0.07295246227866684), ('cs', 0.02411025608909785), ('g', 0.017495349740201872), ('sb', 0.01654835296370108), ('i', 0.015404100494463896), ('table', 0.01312817952534436), ('segments', 0.013040965694205415), ('pix', 0.007903043364867374), ('r', 0.005973887659677079), ('x', 0.005768798663920356)]
// parameters           No	: [('y2', 0.6154226546636228), ('i', 0.001711808183428628), ('e', 0.001480271406588964), ('current', 0.0011992517244484623), ('n', 0.0011790329931689694), ('s', 0.0011327422439051538), ('ctx', 0.0010139233764814778), ('x', 0.0007887030968361243), ('prefix', 0.0006100977902090067), ('t', 0.0005590253817945182)]
// j                    5	: [('k', 0.2922342603006493), ('i', 0.22871568410784202), ('sp', 0.0029319480878227133), ('idx', 0.0023246174250543955), ('index', 0.002032914666673121), ('j', 0.0016536561482085358), ('count', 0.0011835680525920291), ('b0', 0.0009301634603330022), ('glyphIndex', 0.0009218290861615008), ('res', 3.510256348664347e-05)]
                            ps.setX(parameters[j[0]++]);
// ps                   No	: [('properties', 0.07295246227866684), ('cs', 0.02411025608909785), ('g', 0.017495349740201872), ('sb', 0.01654835296370108), ('i', 0.015404100494463896), ('table', 0.01312817952534436), ('segments', 0.013040965694205415), ('pix', 0.007903043364867374), ('r', 0.005973887659677079), ('x', 0.005768798663920356)]
// parameters           No	: [('x', 0.531145845953979), ('value', 0.02997925926579725), ('pts', 0.029497653761755182), ('i', 0.001711808183428628), ('e', 0.001480271406588964), ('current', 0.0011992517244484623), ('n', 0.0011790329931689694), ('s', 0.0011327422439051538), ('ctx', 0.0010139233764814778), ('res', 0.00011802359502427581)]
// j                    5	: [('k', 0.2922342603006493), ('i', 0.22871568410784202), ('sp', 0.0029319480878227133), ('idx', 0.0023246174250543955), ('index', 0.002032914666673121), ('j', 0.0016536561482085358), ('count', 0.0011835680525920291), ('b0', 0.0009301634603330022), ('glyphIndex', 0.0009218290861615008), ('res', 3.510256348664347e-05)]
                            ps.setY(parameters[j[0]++]);
// ps                   No	: [('properties', 0.07295246227866684), ('cs', 0.02411025608909785), ('g', 0.017495349740201872), ('sb', 0.01654835296370108), ('i', 0.015404100494463896), ('table', 0.01312817952534436), ('segments', 0.013040965694205415), ('pix', 0.007903043364867374), ('r', 0.005973887659677079), ('x', 0.005768798663920356)]
// parameters           No	: [('y', 0.5307736755972535), ('value', 0.02997925926579725), ('pts', 0.029497653761755182), ('i', 0.001711808183428628), ('e', 0.001480271406588964), ('current', 0.0011992517244484623), ('n', 0.0011790329931689694), ('s', 0.0011327422439051538), ('ctx', 0.0010139233764814778), ('x', 0.0007887030968361243)]
// j                    5	: [('k', 0.2922342603006493), ('i', 0.22871568410784202), ('sp', 0.0029319480878227133), ('idx', 0.0023246174250543955), ('index', 0.002032914666673121), ('j', 0.0016536561482085358), ('count', 0.0011835680525920291), ('b0', 0.0009301634603330022), ('glyphIndex', 0.0009218290861615008), ('res', 3.510256348664347e-05)]
                            break;
                        }
                        case SVGPathSeg.PATHSEG_CURVETO_QUADRATIC_ABS:
                        case SVGPathSeg.PATHSEG_CURVETO_QUADRATIC_REL: {
                            SVGPathSegCurvetoQuadraticItem ps =
// ps                   No	: [('res', 0.00014041025394657389), ('xmlTraitInformation', 9.19013110534574e-05), ('d', 9.04640090418095e-05), ('document', 5.884336478555581e-05), ('x1', 5.812471377973186e-05), ('rect', 5.48907842535241e-05), ('pd', 5.165685472731634e-05), ('raf', 5.165685472731634e-05), ('iter', 4.339236816034094e-05), ('info', 3.8721136622485275e-05)]
                                (SVGPathSegCurvetoQuadraticItem) s;
// s                    No	: [('n', 0.0020014549421996973), ('i', 0.0018659403747607003), ('e', 0.0018629621445237316), ('doc', 0.0015776231531404), ('current', 0.0009817288155794675), ('node', 0.0009207616200577083), ('o', 0.0007545662165862962), ('evt', 0.0007502543105513524), ('aci', 0.0007171274831306473), ('pathSeg', 0.0004693602849151342)]
                            ps.setX1(parameters[j[0]++]);
// ps                   No	: [('s', 0.0641534830998309), ('i', 0.03472993912432592), ('L', 0.01825929905769324), ('condition', 0.018196725595856594), ('panel', 0.00925292690372019), ('iter', 0.009156814721663825), ('gradient', 0.009148184704685742), ('message', 0.009127234786884335), ('sl', 0.009109409759976622), ('useCapture', 0.009105168478060623)]
// parameters           No	: [('x1', 0.44650702090216), ('x2', 0.17861891394026927), ('i', 0.001711808183428628), ('e', 0.001480271406588964), ('current', 0.0011992517244484623), ('n', 0.0011790329931689694), ('s', 0.0011327422439051538), ('ctx', 0.0010139233764814778), ('x', 0.0007887030968361243), ('prefix', 0.0006100977902090067)]
// j                    5	: [('k', 0.2922342603006493), ('i', 0.22871568410784202), ('sp', 0.0029319480878227133), ('idx', 0.0023246174250543955), ('index', 0.002032914666673121), ('j', 0.0016536561482085358), ('count', 0.0011835680525920291), ('b0', 0.0009301634603330022), ('glyphIndex', 0.0009218290861615008), ('res', 3.510256348664347e-05)]
                            ps.setY1(parameters[j[0]++]);
// ps                   No	: [('properties', 0.07295246227866684), ('cs', 0.02411025608909785), ('g', 0.017495349740201872), ('sb', 0.01654835296370108), ('i', 0.015404100494463896), ('table', 0.01312817952534436), ('segments', 0.013040965694205415), ('pix', 0.007903043364867374), ('r', 0.005973887659677079), ('x', 0.005768798663920356)]
// parameters           No	: [('y1', 0.44649125209529217), ('y2', 0.17860946785043588), ('i', 0.001711808183428628), ('e', 0.001480271406588964), ('current', 0.0011992517244484623), ('n', 0.0011790329931689694), ('s', 0.0011327422439051538), ('ctx', 0.0010139233764814778), ('x', 0.0007887030968361243), ('prefix', 0.0006100977902090067)]
// j                    5	: [('k', 0.2922342603006493), ('i', 0.22871568410784202), ('sp', 0.0029319480878227133), ('idx', 0.0023246174250543955), ('index', 0.002032914666673121), ('j', 0.0016536561482085358), ('count', 0.0011835680525920291), ('b0', 0.0009301634603330022), ('glyphIndex', 0.0009218290861615008), ('res', 3.510256348664347e-05)]
                            ps.setX(parameters[j[0]++]);
// ps                   No	: [('properties', 0.07295246227866684), ('cs', 0.02411025608909785), ('g', 0.017495349740201872), ('sb', 0.01654835296370108), ('i', 0.015404100494463896), ('table', 0.01312817952534436), ('segments', 0.013040965694205415), ('pix', 0.007903043364867374), ('r', 0.005973887659677079), ('x', 0.005768798663920356)]
// parameters           No	: [('x', 0.531145845953979), ('value', 0.02997925926579725), ('pts', 0.029497653761755182), ('i', 0.001711808183428628), ('e', 0.001480271406588964), ('current', 0.0011992517244484623), ('n', 0.0011790329931689694), ('s', 0.0011327422439051538), ('ctx', 0.0010139233764814778), ('res', 0.00011802359502427581)]
// j                    5	: [('k', 0.2922342603006493), ('i', 0.22871568410784202), ('sp', 0.0029319480878227133), ('idx', 0.0023246174250543955), ('index', 0.002032914666673121), ('j', 0.0016536561482085358), ('count', 0.0011835680525920291), ('b0', 0.0009301634603330022), ('glyphIndex', 0.0009218290861615008), ('res', 3.510256348664347e-05)]
                            ps.setY(parameters[j[0]++]);
// ps                   No	: [('properties', 0.07295246227866684), ('cs', 0.02411025608909785), ('g', 0.017495349740201872), ('sb', 0.01654835296370108), ('i', 0.015404100494463896), ('table', 0.01312817952534436), ('segments', 0.013040965694205415), ('pix', 0.007903043364867374), ('r', 0.005973887659677079), ('x', 0.005768798663920356)]
// parameters           No	: [('y', 0.5307736755972535), ('value', 0.02997925926579725), ('pts', 0.029497653761755182), ('i', 0.001711808183428628), ('e', 0.001480271406588964), ('current', 0.0011992517244484623), ('n', 0.0011790329931689694), ('s', 0.0011327422439051538), ('ctx', 0.0010139233764814778), ('x', 0.0007887030968361243)]
// j                    5	: [('k', 0.2922342603006493), ('i', 0.22871568410784202), ('sp', 0.0029319480878227133), ('idx', 0.0023246174250543955), ('index', 0.002032914666673121), ('j', 0.0016536561482085358), ('count', 0.0011835680525920291), ('b0', 0.0009301634603330022), ('glyphIndex', 0.0009218290861615008), ('res', 3.510256348664347e-05)]
                            break;
                        }
                        case SVGPathSeg.PATHSEG_CURVETO_QUADRATIC_SMOOTH_ABS:
                        case SVGPathSeg.PATHSEG_CURVETO_QUADRATIC_SMOOTH_REL: {
                            SVGPathSegCurvetoQuadraticSmoothItem ps =
// ps                   No	: [('res', 0.00014041025394657389), ('xmlTraitInformation', 9.19013110534574e-05), ('d', 9.04640090418095e-05), ('document', 5.884336478555581e-05), ('x1', 5.812471377973186e-05), ('rect', 5.48907842535241e-05), ('pd', 5.165685472731634e-05), ('raf', 5.165685472731634e-05), ('iter', 4.339236816034094e-05), ('info', 3.8721136622485275e-05)]
                                (SVGPathSegCurvetoQuadraticSmoothItem) s;
// s                    No	: [('n', 0.0020014549421996973), ('i', 0.0018659403747607003), ('e', 0.0018629621445237316), ('doc', 0.0015776231531404), ('current', 0.0009817288155794675), ('node', 0.0009207616200577083), ('o', 0.0007545662165862962), ('evt', 0.0007502543105513524), ('aci', 0.0007171274831306473), ('pathSeg', 0.0004693602849151342)]
                            ps.setX(parameters[j[0]++]);
// ps                   No	: [('s', 0.0641534830998309), ('i', 0.03472993912432592), ('L', 0.01825929905769324), ('condition', 0.018196725595856594), ('panel', 0.00925292690372019), ('iter', 0.009156814721663825), ('gradient', 0.009148184704685742), ('message', 0.009127234786884335), ('sl', 0.009109409759976622), ('useCapture', 0.009105168478060623)]
// parameters           No	: [('x', 0.531145845953979), ('value', 0.02997925926579725), ('pts', 0.029497653761755182), ('i', 0.001711808183428628), ('e', 0.001480271406588964), ('current', 0.0011992517244484623), ('n', 0.0011790329931689694), ('s', 0.0011327422439051538), ('ctx', 0.0010139233764814778), ('res', 0.00011802359502427581)]
// j                    5	: [('k', 0.2922342603006493), ('i', 0.22871568410784202), ('sp', 0.0029319480878227133), ('idx', 0.0023246174250543955), ('index', 0.002032914666673121), ('j', 0.0016536561482085358), ('count', 0.0011835680525920291), ('b0', 0.0009301634603330022), ('glyphIndex', 0.0009218290861615008), ('res', 3.510256348664347e-05)]
                            ps.setY(parameters[j[0]++]);
// ps                   No	: [('properties', 0.07295246227866684), ('cs', 0.02411025608909785), ('g', 0.017495349740201872), ('sb', 0.01654835296370108), ('i', 0.015404100494463896), ('table', 0.01312817952534436), ('segments', 0.013040965694205415), ('pix', 0.007903043364867374), ('r', 0.005973887659677079), ('x', 0.005768798663920356)]
// parameters           No	: [('y', 0.5307736755972535), ('value', 0.02997925926579725), ('pts', 0.029497653761755182), ('i', 0.001711808183428628), ('e', 0.001480271406588964), ('current', 0.0011992517244484623), ('n', 0.0011790329931689694), ('s', 0.0011327422439051538), ('ctx', 0.0010139233764814778), ('x', 0.0007887030968361243)]
// j                    5	: [('k', 0.2922342603006493), ('i', 0.22871568410784202), ('sp', 0.0029319480878227133), ('idx', 0.0023246174250543955), ('index', 0.002032914666673121), ('j', 0.0016536561482085358), ('count', 0.0011835680525920291), ('b0', 0.0009301634603330022), ('glyphIndex', 0.0009218290861615008), ('res', 3.510256348664347e-05)]
                            break;
                        }
                        case SVGPathSeg.PATHSEG_LINETO_ABS:
                        case SVGPathSeg.PATHSEG_LINETO_REL:
                        case SVGPathSeg.PATHSEG_MOVETO_ABS:
                        case SVGPathSeg.PATHSEG_MOVETO_REL: {
                            SVGPathSegMovetoLinetoItem ps =
// ps                   No	: [('res', 0.00014041025394657389), ('xmlTraitInformation', 9.19013110534574e-05), ('d', 9.04640090418095e-05), ('document', 5.884336478555581e-05), ('x1', 5.812471377973186e-05), ('rect', 5.48907842535241e-05), ('pd', 5.165685472731634e-05), ('raf', 5.165685472731634e-05), ('iter', 4.339236816034094e-05), ('info', 3.8721136622485275e-05)]
                                (SVGPathSegMovetoLinetoItem) s;
// s                    No	: [('n', 0.0020014549421996973), ('i', 0.0018659403747607003), ('e', 0.0018629621445237316), ('doc', 0.0015776231531404), ('current', 0.0009817288155794675), ('node', 0.0009207616200577083), ('o', 0.0007545662165862962), ('evt', 0.0007502543105513524), ('aci', 0.0007171274831306473), ('pathSeg', 0.0004693602849151342)]
                            ps.setX(parameters[j[0]++]);
// ps                   No	: [('s', 0.0641534830998309), ('i', 0.03472993912432592), ('L', 0.01825929905769324), ('condition', 0.018196725595856594), ('panel', 0.00925292690372019), ('iter', 0.009156814721663825), ('gradient', 0.009148184704685742), ('message', 0.009127234786884335), ('sl', 0.009109409759976622), ('useCapture', 0.009105168478060623)]
// parameters           No	: [('x', 0.531145845953979), ('value', 0.02997925926579725), ('pts', 0.029497653761755182), ('i', 0.001711808183428628), ('e', 0.001480271406588964), ('current', 0.0011992517244484623), ('n', 0.0011790329931689694), ('s', 0.0011327422439051538), ('ctx', 0.0010139233764814778), ('res', 0.00011802359502427581)]
// j                    5	: [('k', 0.2922342603006493), ('i', 0.22871568410784202), ('sp', 0.0029319480878227133), ('idx', 0.0023246174250543955), ('index', 0.002032914666673121), ('j', 0.0016536561482085358), ('count', 0.0011835680525920291), ('b0', 0.0009301634603330022), ('glyphIndex', 0.0009218290861615008), ('res', 3.510256348664347e-05)]
                            ps.setY(parameters[j[0]++]);
// ps                   No	: [('properties', 0.07295246227866684), ('cs', 0.02411025608909785), ('g', 0.017495349740201872), ('sb', 0.01654835296370108), ('i', 0.015404100494463896), ('table', 0.01312817952534436), ('segments', 0.013040965694205415), ('pix', 0.007903043364867374), ('r', 0.005973887659677079), ('x', 0.005768798663920356)]
// parameters           No	: [('y', 0.5307736755972535), ('value', 0.02997925926579725), ('pts', 0.029497653761755182), ('i', 0.001711808183428628), ('e', 0.001480271406588964), ('current', 0.0011992517244484623), ('n', 0.0011790329931689694), ('s', 0.0011327422439051538), ('ctx', 0.0010139233764814778), ('x', 0.0007887030968361243)]
// j                    5	: [('k', 0.2922342603006493), ('i', 0.22871568410784202), ('sp', 0.0029319480878227133), ('idx', 0.0023246174250543955), ('index', 0.002032914666673121), ('j', 0.0016536561482085358), ('count', 0.0011835680525920291), ('b0', 0.0009301634603330022), ('glyphIndex', 0.0009218290861615008), ('res', 3.510256348664347e-05)]
                            break;
                        }
                        case SVGPathSeg.PATHSEG_LINETO_HORIZONTAL_REL:
                        case SVGPathSeg.PATHSEG_LINETO_HORIZONTAL_ABS: {
                            SVGPathSegLinetoHorizontalItem ps =
// ps                   No	: [('res', 0.00014041025394657389), ('xmlTraitInformation', 9.19013110534574e-05), ('d', 9.04640090418095e-05), ('document', 5.884336478555581e-05), ('x1', 5.812471377973186e-05), ('rect', 5.48907842535241e-05), ('pd', 5.165685472731634e-05), ('raf', 5.165685472731634e-05), ('iter', 4.339236816034094e-05), ('info', 3.8721136622485275e-05)]
                                (SVGPathSegLinetoHorizontalItem) s;
// s                    No	: [('n', 0.0020014549421996973), ('i', 0.0018659403747607003), ('e', 0.0018629621445237316), ('doc', 0.0015776231531404), ('current', 0.0009817288155794675), ('node', 0.0009207616200577083), ('o', 0.0007545662165862962), ('evt', 0.0007502543105513524), ('aci', 0.0007171274831306473), ('pathSeg', 0.0004693602849151342)]
                            ps.setX(parameters[j[0]++]);
// ps                   No	: [('s', 0.0641534830998309), ('i', 0.03472993912432592), ('L', 0.01825929905769324), ('condition', 0.018196725595856594), ('panel', 0.00925292690372019), ('iter', 0.009156814721663825), ('gradient', 0.009148184704685742), ('message', 0.009127234786884335), ('sl', 0.009109409759976622), ('useCapture', 0.009105168478060623)]
// parameters           No	: [('x', 0.531145845953979), ('value', 0.02997925926579725), ('pts', 0.029497653761755182), ('i', 0.001711808183428628), ('e', 0.001480271406588964), ('current', 0.0011992517244484623), ('n', 0.0011790329931689694), ('s', 0.0011327422439051538), ('ctx', 0.0010139233764814778), ('res', 0.00011802359502427581)]
// j                    5	: [('k', 0.2922342603006493), ('i', 0.22871568410784202), ('sp', 0.0029319480878227133), ('idx', 0.0023246174250543955), ('index', 0.002032914666673121), ('j', 0.0016536561482085358), ('count', 0.0011835680525920291), ('b0', 0.0009301634603330022), ('glyphIndex', 0.0009218290861615008), ('res', 3.510256348664347e-05)]
                            break;
                        }
                        case SVGPathSeg.PATHSEG_LINETO_VERTICAL_REL:
                        case SVGPathSeg.PATHSEG_LINETO_VERTICAL_ABS: {
                            SVGPathSegLinetoVerticalItem ps =
// ps                   No	: [('res', 0.00014041025394657389), ('xmlTraitInformation', 9.19013110534574e-05), ('d', 9.04640090418095e-05), ('document', 5.884336478555581e-05), ('x1', 5.812471377973186e-05), ('rect', 5.48907842535241e-05), ('pd', 5.165685472731634e-05), ('raf', 5.165685472731634e-05), ('iter', 4.339236816034094e-05), ('info', 3.8721136622485275e-05)]
                                (SVGPathSegLinetoVerticalItem) s;
// s                    No	: [('n', 0.0020014549421996973), ('i', 0.0018659403747607003), ('e', 0.0018629621445237316), ('doc', 0.0015776231531404), ('current', 0.0009817288155794675), ('node', 0.0009207616200577083), ('o', 0.0007545662165862962), ('evt', 0.0007502543105513524), ('aci', 0.0007171274831306473), ('pathSeg', 0.0004693602849151342)]
                            ps.setY(parameters[j[0]++]);
// ps                   No	: [('s', 0.0641534830998309), ('i', 0.03472993912432592), ('L', 0.01825929905769324), ('condition', 0.018196725595856594), ('panel', 0.00925292690372019), ('iter', 0.009156814721663825), ('gradient', 0.009148184704685742), ('message', 0.009127234786884335), ('sl', 0.009109409759976622), ('useCapture', 0.009105168478060623)]
// parameters           No	: [('y', 0.5307736755972535), ('value', 0.02997925926579725), ('pts', 0.029497653761755182), ('i', 0.001711808183428628), ('e', 0.001480271406588964), ('current', 0.0011992517244484623), ('n', 0.0011790329931689694), ('s', 0.0011327422439051538), ('ctx', 0.0010139233764814778), ('x', 0.0007887030968361243)]
// j                    5	: [('k', 0.2922342603006493), ('i', 0.22871568410784202), ('sp', 0.0029319480878227133), ('idx', 0.0023246174250543955), ('index', 0.002032914666673121), ('j', 0.0016536561482085358), ('count', 0.0011835680525920291), ('b0', 0.0009301634603330022), ('glyphIndex', 0.0009218290861615008), ('res', 3.510256348664347e-05)]
                            break;
                        }
                    }
                }
                i++;
// i                    No	: [('h', 0.16974438980386214), ('firstEnd', 0.1697335757463818), ('cssProceedElement', 0.16973355328853787), ('nextChar', 0.015512579116997782), ('tempLogicalBounds', 0.012265968108882919), ('st', 0.006164660013064353), ('rq', 0.006163784157151005), ('scripts', 0.006133733704160305), ('s', 0.0031729371192289186), ('target', 0.0030802727081088626)]
            }
            while (i < commands.length) {
// i                    0	: [('i', 0.2517481454198661), ('size', 0.198017005665376), ('current', 0.10666304615367146), ('off', 0.06368164776799687), ('it', 0.050718522316090066), ('chunk', 0.04966241083581835), ('n', 0.033290645114284464), ('e', 0.013315773595200182), ('res', 2.9505898756068954e-05), ('xmlTraitInformation', 5.743831940841088e-06)]
// commands             No	: [('count', 0.213703686419501), ('len', 0.20974947264061655), ('data', 0.13240888140039347), ('size', 0.05783670464596237), ('numGG', 0.05230602670775417), ('values', 0.04897758918439801), ('types', 0.04885232757061728), ('pts', 0.04878535733356707), ('res', 0.00011179956525072278), ('xmlTraitInformation', 5.743831940841088e-06)]
// length               0	: [('length', 0.5068699618981626), ('image', 0.005863921859024022), ('svg', 0.004361682458199219), ('x', 0.00405490453273996), ('engine', 0.00374731782307714), ('y', 0.0037339511982644245), ('ext', 0.0036323864791675), ('value', 0.003126429248544319), ('io', 0.0029899440852981973), ('res', 7.020512697328694e-05)]
                appendItemImpl(newItem(commands[i], parameters, j));
// newItem              0	: [('newItem', 0.25013435820183894), ('i', 0.003423616366857256), ('e', 0.002960542813177928), ('current', 0.0023985034488969246), ('n', 0.0023580659863379387), ('s', 0.0022654844878103077), ('ctx', 0.0020278467529629557), ('x', 0.0015774061936722485), ('prefix', 0.0012201955804180134), ('t', 0.0011180507635890364)]
// commands             No	: [('i', 0.006847232733714512), ('e', 0.005921085626355856), ('current', 0.004797006897793849), ('n', 0.0047161319726758775), ('s', 0.004530968975620615), ('ctx', 0.004055693505925911), ('x', 0.003154812387344497), ('prefix', 0.002440391160836027), ('t', 0.002236101527178073), ('lu', 0.0019401281922919846)]
// i                    0	: [('i', 0.08152940309803465), ('sp', 0.011727792351290853), ('idx', 0.009298469700217582), ('index', 0.008131658666692484), ('j', 0.006614624592834143), ('count', 0.0047342722103681165), ('b0', 0.0037206538413320092), ('glyphIndex', 0.003687316344646003), ('b1', 0.003467289136615386), ('p', 0.0028339571499086348)]
// parameters           No	: [('paramTypes', 0.10157465460295216), ('ypoints', 0.065480157047169), ('scriptOrigin', 0.06348549695094072), ('node', 0.05094015289695464), ('toLengthList', 0.038093780042063555), ('accLengthList', 0.03809369021068783), ('aci', 0.025486993503076583), ('glyphForms', 0.02539927247146771), ('values', 0.013385001024841883), ('listeners', 0.012744977006338868)]
// j                    No	: [('y', 0.006948416068567231), ('ctx', 0.00632099653715035), ('e', 0.006251132259646529), ('CHAR_CLASS_AL', 0.004179181457507007), ('s', 0.003762335374784778), ('current', 0.0036448905466416083), ('w', 0.0028602906405132958), ('owner', 0.0025534899763237514), ('CHAR_CLASS_AI', 0.002377342914927434), ('h', 0.0022591881397666757)]
                i++;
// i                    No	: [('specificities', 0.5001098017257036), ('t', 0.01767293982151946), ('sb', 0.011851630208373187), ('ctx', 0.008234437930410189), ('svg11Factories', 0.008196133269255649), ('g', 0.007143280542734368), ('xmlTraitInformation', 0.006055150120119713), ('listeners', 0.0057492446560702725), ('report', 0.005066266749818072), ('p', 0.003138320677010583)]
            }
            while (size > commands.length) {
// size                 1	: [('i', 0.2517481454198661), ('size', 0.198017005665376), ('current', 0.10666304615367146), ('off', 0.06368164776799687), ('it', 0.050718522316090066), ('chunk', 0.04966241083581835), ('n', 0.033290645114284464), ('e', 0.013315773595200182), ('res', 2.9505898756068954e-05), ('xmlTraitInformation', 5.743831940841088e-06)]
// commands             No	: [('i', 0.20019821086527473), ('values', 0.20011673380748946), ('types', 0.2000570953475097), ('pts', 0.20005693814260217), ('glyphs', 0.0006132354909811272), ('width', 0.0005139011364332264), ('bounds', 0.00045179231362094883), ('t2', 0.00044581852713503723), ('w', 0.0004007776657110785), ('height', 0.00039947511076302255)]
// length               0	: [('length', 0.5068699618981626), ('image', 0.005863921859024022), ('svg', 0.004361682458199219), ('x', 0.00405490453273996), ('engine', 0.00374731782307714), ('y', 0.0037339511982644245), ('ext', 0.0036323864791675), ('value', 0.003126429248544319), ('io', 0.0029899440852981973), ('res', 7.020512697328694e-05)]
                removeItemImpl(--size);
// size                 0	: [('size', 0.8999761513531022), ('i', 0.008429116930050698), ('count', 0.005186178600162783), ('index', 0.005002771805296989), ('i1', 0.00499491155992079), ('cursor', 0.004994844186388994), ('i2', 0.00499479927070113), ('cbraces', 0.00499435011382249), ('brackets', 0.00499435011382249), ('res', 8.775640871660868e-06)]
            }
        }

        /**
         * Resets the value of the associated attribute.  Does nothing, since
         * there is no attribute for an animated value.
         */
        protected void resetAttribute() {
        }

        /**
         * Resets the value of the associated attribute.  Does nothing, since
         * there is no attribute for an animated value.
         */
        protected void resetAttribute(SVGItem item) {
// item                 0	: [('item', 0.916107787784012), ('res', 8.775640871660868e-06), ('xmlTraitInformation', 5.743831940841088e-06), ('d', 5.654000565113094e-06), ('document', 3.6777102990972383e-06), ('x1', 3.6327946112332413e-06), ('rect', 3.430674015845256e-06), ('pd', 3.228553420457271e-06), ('raf', 3.228553420457271e-06), ('iter', 2.7120230100213087e-06)]
        }

        /**
         * Initializes the list, if needed.  Does nothing, since there is no
         * attribute to read the list from.
         */
        protected void revalidate() {
            valid = true;
// valid                0	: [('valid', 0.600400516846079), ('usedDefault', 0.15008283646580592), ('result', 0.0012491169871858083), ('userAgent', 0.0007225379971001863), ('x', 0.0006471675806009391), ('in', 0.0005955182245553366), ('eventDispatcher', 0.0005710170651973587), ('sb', 0.0005316995152546552), ('valueProvider', 0.00042599114056232433), ('index', 0.00036158756056011945)]
        }
    }
}
