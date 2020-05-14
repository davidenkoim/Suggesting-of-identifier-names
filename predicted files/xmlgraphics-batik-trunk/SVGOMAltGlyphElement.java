// Path id: 26
// Path to file: C:\Users\Igor\IdeaProjects\SLP-Core-0.3\java-projects\xmlgraphics-batik-trunk\batik-anim\src\main\java\org\apache\batik\anim\dom\SVGOMAltGlyphElement.java
// Number of identifiers: 23	Accuracy: 95.65%	MRR: 97.83%
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
// anim                 1	: [('ext', 0.2818533704563163), ('anim', 0.2098494349520878), ('bridge', 0.17425950358041126), ('parser', 0.06179751089255182), ('test', 0.059315144843341314), ('transcoder', 0.042134625401522685), ('script', 0.020047944075598643), ('extension', 0.01677391143266508), ('length', 0.0008584638621620006), ('image', 0.0007310864863360604)]

import org.apache.batik.dom.AbstractDocument;
import org.apache.batik.dom.util.XLinkSupport;
import org.apache.batik.dom.util.XMLSupport;

import org.w3c.dom.DOMException;
import org.w3c.dom.Node;
import org.w3c.dom.svg.SVGAltGlyphElement;
// svg                  0	: [('svg', 0.9248768498913694), ('xpath', 0.010531498645479356), ('svg12', 0.002513328700244661), ('length', 0.0008584638621620006), ('image', 0.0007310864863360604), ('x', 0.0005055485025997889), ('engine', 0.0004671991072603017), ('y', 0.0004655335202393455), ('ext', 0.0004528701133288052), ('res', 8.754790215750783e-06)]

/**
 * This class implements {@link SVGAltGlyphElement}.
 *
 * @author <a href="mailto:stephane@hillion.org">Stephane Hillion</a>
 * @version $Id$
 */
public class SVGOMAltGlyphElement
    extends    SVGURIReferenceTextPositioningElement
    implements SVGAltGlyphElement {

    /**
     * The attribute initializer.
     */
    protected static final AttributeInitializer attributeInitializer;
// attributeInitializer 0	: [('attributeInitializer', 0.9520364256321159), ('ai', 0.0014542014741976057), ('res', 8.754790215750783e-06), ('xmlTraitInformation', 5.730757082024543e-06), ('d', 5.6411561002845055e-06), ('document', 3.6699345020036984e-06), ('x1', 3.62513401113368e-06), ('rect', 3.4235318022185975e-06), ('pd', 3.2219295933035145e-06), ('raf', 3.2219295933035145e-06)]
    static {
        attributeInitializer = new AttributeInitializer(4);
// attributeInitializer 0	: [('attributeInitializer', 0.772009620878718), ('values', 0.036341407126641564), ('bundle', 0.01980863378000134), ('szAtts', 0.0022015657993486905), ('defaultRenderingHints', 0.0022015433991032555), ('factories', 0.0011075545177623698), ('xblFactories', 0.0011010722499903314), ('svg11Factories', 0.0011010722499903314), ('svg12Factories', 0.0011010498497448964), ('sb', 0.00015566032176132427)]
        attributeInitializer.addAttribute(XMLSupport.XMLNS_NAMESPACE_URI,
// attributeInitializer 0	: [('attributeInitializer', 0.6603561414407367), ('bounds', 0.017824843000114325), ('types', 0.017820825932611743), ('orientation', 0.017811023190579468), ('bitDepth', 0.017803370872108953), ('ip', 0.017801224395330863), ('g', 0.01349841562057959), ('crc', 0.007820446453144933), ('t', 0.001341561981191993), ('i', 0.0012610779207192203)]
// XMLNS_NAMESPACE_URI  0	: [('XMLNS_NAMESPACE_URI', 0.858672160937137), ('XML_NAMESPACE_URI', 0.06370883473829048), ('length', 0.0008584638621620006), ('image', 0.0007310864863360604), ('svg', 0.0005552189716093287), ('x', 0.0005055485025997889), ('engine', 0.0004671991072603017), ('y', 0.0004655335202393455), ('ext', 0.0004528701133288052), ('value', 0.0003898346666533304)]
                                          null, "xmlns:xlink",
                                          XLinkSupport.XLINK_NAMESPACE_URI);
// XLINK_NAMESPACE_URI  0	: [('XLINK_NAMESPACE_URI', 0.9206461854851863), ('length', 0.0008584638621620006), ('image', 0.0007310864863360604), ('svg', 0.0005552189716093287), ('x', 0.0005055485025997889), ('engine', 0.0004671991072603017), ('y', 0.0004655335202393455), ('ext', 0.0004528701133288052), ('value', 0.0003898346666533304), ('io', 0.0003727730879954146)]
        attributeInitializer.addAttribute(XLinkSupport.XLINK_NAMESPACE_URI,
// attributeInitializer 0	: [('attributeInitializer', 0.7885577345779916), ('res', 0.05644830947930974), ('g', 0.0017796656205795885), ('t', 0.001341561981191993), ('values', 0.0009585168461962165), ('sb', 0.0008998793037493963), ('report', 0.0007914374294402211), ('n', 0.0005869658330691598), ('computedValues', 0.0004663585014291371), ('p', 0.0004565070603670084)]
// XLINK_NAMESPACE_URI  0	: [('XLINK_NAMESPACE_URI', 0.9206461854851863), ('length', 0.0008584638621620006), ('image', 0.0007310864863360604), ('svg', 0.0005552189716093287), ('x', 0.0005055485025997889), ('engine', 0.0004671991072603017), ('y', 0.0004655335202393455), ('ext', 0.0004528701133288052), ('value', 0.0003898346666533304), ('io', 0.0003727730879954146)]
                                          "xlink", "type", "simple");
        attributeInitializer.addAttribute(XLinkSupport.XLINK_NAMESPACE_URI,
// attributeInitializer 0	: [('attributeInitializer', 0.8751998914407367), ('g', 0.0017796656205795885), ('t', 0.001341561981191993), ('values', 0.0009585168461962165), ('sb', 0.0008998793037493963), ('report', 0.0007914374294402211), ('n', 0.0005869658330691598), ('computedValues', 0.0004663585014291371), ('p', 0.0004565070603670084), ('g2d', 0.00043795263738370975)]
// XLINK_NAMESPACE_URI  0	: [('XLINK_NAMESPACE_URI', 0.9206461854851863), ('length', 0.0008584638621620006), ('image', 0.0007310864863360604), ('svg', 0.0005552189716093287), ('x', 0.0005055485025997889), ('engine', 0.0004671991072603017), ('y', 0.0004655335202393455), ('ext', 0.0004528701133288052), ('value', 0.0003898346666533304), ('io', 0.0003727730879954146)]
                                          "xlink", "show", "other");
        attributeInitializer.addAttribute(XLinkSupport.XLINK_NAMESPACE_URI,
// attributeInitializer 0	: [('attributeInitializer', 0.8751998914407367), ('g', 0.0017796656205795885), ('t', 0.001341561981191993), ('values', 0.0009585168461962165), ('sb', 0.0008998793037493963), ('report', 0.0007914374294402211), ('n', 0.0005869658330691598), ('computedValues', 0.0004663585014291371), ('p', 0.0004565070603670084), ('g2d', 0.00043795263738370975)]
// XLINK_NAMESPACE_URI  0	: [('XLINK_NAMESPACE_URI', 0.9206461854851863), ('length', 0.0008584638621620006), ('image', 0.0007310864863360604), ('svg', 0.0005552189716093287), ('x', 0.0005055485025997889), ('engine', 0.0004671991072603017), ('y', 0.0004655335202393455), ('ext', 0.0004528701133288052), ('value', 0.0003898346666533304), ('io', 0.0003727730879954146)]
                                          "xlink", "actuate", "onLoad");
    }

//     /**
//      * Table mapping XML attribute names to TraitInformation objects.
//      */
//     protected static DoublyIndexedTable xmlTraitInformation;
//     static {
//         DoublyIndexedTable t =
//             new DoublyIndexedTable(SVGURIReferenceTextPositioningElement.xmlTraitInformation);
//         t.put(null, SVG_FORMAT_ATTRIBUTE,
//                 new TraitInformation(false, SVGTypes.TYPE_CDATA));
//         t.put(null, SVG_GLYPH_REF_ATTRIBUTE,
//                 new TraitInformation(false, SVGTypes.TYPE_CDATA));
//         xmlTraitInformation = t;
//     }

    /**
     * Creates a new SVGOMAltGlyphElement object.
     */
    protected SVGOMAltGlyphElement() {
    }

    /**
     * Creates a new SVGOMAltGlyphElement object.
     * @param prefix The namespace prefix.
     * @param owner The owner document.
     */
    public SVGOMAltGlyphElement(String prefix, AbstractDocument owner) {
// prefix               0	: [('prefix', 0.08256008483325974), ('name', 0.04405917352702062), ('s', 0.03822715501363837), ('ns', 0.025426302028420267), ('key', 0.025280368226413136), ('namespaceURI', 0.021541708716916075), ('message', 0.021085343523647403), ('uri', 0.02016002419956762), ('text', 0.017469248343950416), ('value', 0.014071736978424636)]
// owner                0	: [('owner', 0.9382944594702803), ('d', 0.01504723373928545), ('doc', 0.002633023501808419), ('ad', 0.00041117649290818304), ('document', 0.00033175392400331603), ('ownerDocument', 8.340610684296084e-05), ('res', 8.754790215750783e-06), ('xmlTraitInformation', 5.730757082024543e-06)]
        super(prefix, owner);
// prefix               0	: [('prefix', 0.5834449969385219), ('target', 0.04151646284761358), ('name', 0.02504525237190797), ('srcCM', 0.02074322417212464), ('src', 0.019955649372837198), ('source', 0.019084358624031534), ('generatorContext', 0.01908357603331128), ('owner', 0.016233245479962938), ('nsURI', 0.004972728960288431), ('i', 0.00042929555389151106)]
// owner                0	: [('owner', 0.8300310284738214), ('document', 0.002523420027321854), ('ns', 0.0013053779593530657), ('maxId', 0.0012509971896920036), ('a', 0.0006866485549788872), ('ans', 0.0006281121603471238), ('y', 0.00043297735694655356), ('ctx', 0.00039387796733350203), ('e', 0.00038957560243995217), ('CHAR_CLASS_AL', 0.00026041033441784084)]
    }

    /**
     * <b>DOM</b>: Implements {@link Node#getLocalName()}.
     */
    public String getLocalName() {
        return SVG_ALT_GLYPH_TAG;
    }

    /**
     * <b>DOM</b>: Implements {@link SVGAltGlyphElement#getGlyphRef()}.
     */
    public String getGlyphRef() {
        return getAttributeNS(null, SVG_GLYPH_REF_ATTRIBUTE);
    }

    /**
     * <b>DOM</b>: Implements {@link SVGAltGlyphElement#setGlyphRef(String)}.
     */
    public void setGlyphRef(String glyphRef) throws DOMException {
// glyphRef             0	: [('glyphRef', 0.8750395604611545), ('prefix', 0.010320010604157468), ('name', 0.005507396690877578), ('s', 0.004778394376704796), ('ns', 0.0031782877535525334), ('key', 0.003160046028301642), ('namespaceURI', 0.0026927135896145094), ('message', 0.0026356679404559253), ('uri', 0.0025200030249459527), ('text', 0.002183656042993802)]
        setAttributeNS(null, SVG_GLYPH_REF_ATTRIBUTE, glyphRef);
// glyphRef             0	: [('glyphRef', 0.9375017240080404), ('y', 0.00043297735694655356), ('ctx', 0.00039387796733350203), ('e', 0.00038957560243995217), ('CHAR_CLASS_AL', 0.00026041033441784084), ('s', 0.00023475825896968506), ('current', 0.00022712267016765307), ('w', 0.00017823285552947442), ('owner', 0.00015801260080555143), ('CHAR_CLASS_AI', 0.00014813622857500446)]
    }

    /**
     * <b>DOM</b>: Implements {@link SVGAltGlyphElement#getFormat()}.
     */
    public String getFormat() {
        return getAttributeNS(null, SVG_FORMAT_ATTRIBUTE);
    }

    /**
     * <b>DOM</b>: Implements {@link SVGAltGlyphElement#setFormat(String)}.
     */
    public void setFormat(String format) throws DOMException {
// format               0	: [('format', 0.8750491772705424), ('prefix', 0.010320010604157468), ('name', 0.005507396690877578), ('s', 0.004778394376704796), ('ns', 0.0031782877535525334), ('key', 0.003160046028301642), ('namespaceURI', 0.0026927135896145094), ('message', 0.0026356679404559253), ('uri', 0.0025200030249459527), ('text', 0.002183656042993802)]
        setAttributeNS(null, SVG_FORMAT_ATTRIBUTE, format);
// format               0	: [('format', 0.9375023064144218), ('y', 0.00043297735694655356), ('ctx', 0.00039387796733350203), ('e', 0.00038957560243995217), ('CHAR_CLASS_AL', 0.00026041033441784084), ('s', 0.00023475825896968506), ('current', 0.00022712267016765307), ('w', 0.00017823285552947442), ('owner', 0.00015801260080555143), ('CHAR_CLASS_AI', 0.00014813622857500446)]
    }

    /**
     * Returns the AttributeInitializer for this element type.
     * @return null if this element has no attribute with a default value.
     */
    protected AttributeInitializer getAttributeInitializer() {
        return attributeInitializer;
// attributeInitializer 0	: [('attributeInitializer', 0.5035448989595744), ('xmlTraitInformation', 0.0102814661974095), ('x', 0.006979432350360321), ('y', 0.0067786921348568455), ('localizableSupport', 0.006106712812941107), ('values', 0.0051073435441756024), ('type', 0.0038699269368740875), ('valueProvider', 0.0036780761028016554), ('ctx', 0.003676512590074965), ('ret', 0.0005998294365526791)]
    }

    /**
     * Returns a new uninitialized instance of this object's class.
     */
    protected Node newNode() {
// newNode              0	: [('newNode', 0.9039765236912188), ('n', 0.008732902672630154), ('node', 0.002500056008020326), ('parentNode', 0.002460102113419885), ('nextSibling', 0.0017592781424285338), ('previousSibling', 0.0017096751381788433), ('contextNode', 0.0017025316183383711), ('lastChild', 0.0011828536863565224), ('firstChild', 0.0011827640853747823), ('res', 3.347789148157357e-05)]
        return new SVGOMAltGlyphElement();
    }

//     /**
//      * Returns the table of TraitInformation objects for this element.
//      */
//     protected DoublyIndexedTable getTraitInformationTable() {
//         return xmlTraitInformation;
//     }
}
