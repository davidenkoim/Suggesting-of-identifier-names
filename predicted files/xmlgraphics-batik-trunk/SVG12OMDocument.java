// Path id: 18
// Path to file: C:\Users\Igor\IdeaProjects\SLP-Core-0.3\java-projects\xmlgraphics-batik-trunk\batik-anim\src\main\java\org\apache\batik\anim\dom\SVG12OMDocument.java
// Number of identifiers: 67	Accuracy: 67.16%	MRR: 73.69%
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
// anim                 1	: [('ext', 0.2818643101151741), ('anim', 0.2098575782705254), ('bridge', 0.17426626544636997), ('parser', 0.06179991816775569), ('test', 0.05931744676001227), ('transcoder', 0.0421362605782654), ('script', 0.02004872302392767), ('extension', 0.01677456285217173), ('length', 0.0008587761453745481), ('image', 0.0007313533337086982)]

import org.apache.batik.css.engine.CSSNavigableDocumentListener;
// engine               0	: [('engine', 0.8859522026578778), ('parser', 0.052140860437135936), ('length', 0.0008587761453745481), ('image', 0.0007313533337086982), ('svg', 0.0005554217406255912), ('x', 0.000505729392072771), ('y', 0.00046570006984685843), ('ext', 0.00045303418515636566), ('value', 0.0003899757041451048), ('res', 8.757011204714464e-06)]
import org.apache.batik.constants.XMLConstants;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Node;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.svg.SVGDocument;
// svg                  0	: [('svg', 0.9248770526603857), ('xpath', 0.0105315012477973), ('svg12', 0.0025133435922439157), ('length', 0.0008587761453745481), ('image', 0.0007313533337086982), ('x', 0.000505729392072771), ('engine', 0.0004667337014708756), ('y', 0.00046570006984685843), ('ext', 0.00045303418515636566), ('res', 8.757011204714464e-06)]

/**
 * This class implements {@link SVGDocument} and provides support for
 * SVG 1.2 specifics.
 *
 * @author <a href="mailto:cam%40mcc%2eid%2eau">Cameron McCormack</a>
 * @version $Id$
 */
public class SVG12OMDocument extends SVGOMDocument {

    /**
     * Creates a new uninitialized document.
     */
    protected SVG12OMDocument() {
    }

    /**
     * Creates a new SVG12OMDocument.
     */
    public SVG12OMDocument(DocumentType dt, DOMImplementation impl) {
// dt                   0	: [('dt', 0.34921635619554037), ('doctype', 0.047625648838362705), ('docType', 0.011910108366754232), ('dt2', 0.011910108366754232), ('dt1', 0.011909929115708123), ('res', 7.005608963771572e-05), ('xmlTraitInformation', 4.585719841298652e-05), ('d', 4.5140194228550105e-05), ('document', 2.9366102170948856e-05), ('x1', 2.900760007873065e-05)]
// impl                 0	: [('impl', 0.9583347410516475), ('DOM_IMPLEMENTATION', 0.002841577398658132), ('implementation', 0.0018949662037806533), ('di', 0.0009479741004301927), ('domImpl', 0.0009476155983379745), ('res', 8.757011204714464e-06), ('xmlTraitInformation', 5.732149801623315e-06), ('d', 5.642524278568763e-06), ('document', 3.670762771368607e-06), ('x1', 3.625950009841331e-06)]
        super(dt, impl);
// dt                   0	: [('dt', 0.5024908487467986), ('prefix', 0.10700106529847656), ('target', 0.041516485722212164), ('srcCM', 0.020743232133268064), ('src', 0.01995566658024392), ('source', 0.01908436609454707), ('generatorContext', 0.019083583381252517), ('elt', 0.012517721772908073), ('owner', 0.012445393892066869), ('type', 0.009227002608423128)]
// impl                 0	: [('impl', 0.9017935962279849), ('y', 0.00043332831429266874), ('ctx', 0.0003942010606684882), ('e', 0.00038988735153292403), ('CHAR_CLASS_AL', 0.0002606314289399905), ('s', 0.00023494691289326094), ('current', 0.0002273098681022649), ('w', 0.00017837934205542892), ('owner', 0.0001592467330519546), ('CHAR_CLASS_AI', 0.00014826176446329912)]
    }

    // AbstractDocument ///////////////////////////////////////////////

    /**
     * Returns a new uninitialized instance of this object's class.
     */
    protected Node newNode() {
// newNode              0	: [('newNode', 0.9039765237034896), ('n', 0.008732910814211245), ('node', 0.002500059112496612), ('parentNode', 0.0024601024201863163), ('nextSibling', 0.0017592782589997778), ('previousSibling', 0.001709675211802787), ('contextNode', 0.0017025318944281593), ('lastChild', 0.0011828538274690809), ('firstChild', 0.0011827642019460263), ('res', 3.348011247053725e-05)]
        return new SVG12OMDocument();
    }

    // CSSNavigableDocument ///////////////////////////////////////////

    /**
     * Adds an event listener for mutations on the
     * CSSNavigableDocument tree.
     */
    public void addCSSNavigableDocumentListener
            (CSSNavigableDocumentListener l) {
// l                    0	: [('l', 0.9397386144175096), ('listener', 0.007444258985151377), ('res', 8.757011204714464e-06), ('xmlTraitInformation', 5.732149801623315e-06), ('d', 5.642524278568763e-06), ('document', 3.670762771368607e-06), ('x1', 3.625950009841331e-06), ('rect', 3.4242925829685876e-06), ('pd', 3.2226351560958442e-06), ('raf', 3.2226351560958442e-06)]
        if (cssNavigableDocumentListeners.containsKey(l)) {
// cssNavigableDocumentListeners 8	: [('glisteners', 0.16795768351867782), ('o', 0.08496523020943009), ('listeners', 0.05832706988330263), ('isCSS', 0.056047253405783266), ('targetListeners', 0.028423528120091253), ('locale', 0.028101927415785023), ('elementsByTagNamesNS', 0.028100975899618654), ('elementsByTagNames', 0.028100975899618654), ('cssNavigableDocumentListeners', 0.0279397333989535), ('n', 0.014406162956490592)]
// l                    0	: [('l', 0.7599261751062757), ('purl', 0.019765221637827887), ('TEXTPATH', 0.0197401857104652), ('ANCHOR_TYPE', 0.019739279007060358), ('KERNING', 0.01973839471003628), ('WORD_SPACING', 0.01973839471003628), ('LETTER_SPACING', 0.019738372303655514), ('s', 0.010153460459863395), ('attrName', 0.009878442453291824), ('i', 0.0004293947420351002)]
            return;
        }

        DOMNodeInsertedListenerWrapper nodeInserted
// nodeInserted         0	: [('nodeInserted', 0.9479174470063195), ('res', 8.757011204714464e-06), ('xmlTraitInformation', 5.732149801623315e-06), ('d', 5.642524278568763e-06), ('document', 3.670762771368607e-06), ('x1', 3.625950009841331e-06), ('rect', 3.4242925829685876e-06), ('pd', 3.2226351560958442e-06), ('raf', 3.2226351560958442e-06), ('iter', 2.7072883985321675e-06)]
            = new DOMNodeInsertedListenerWrapper(l);
// l                    0	: [('l', 0.906307754053644), ('i', 0.0004293947420351002), ('e', 0.00036930768499723247), ('current', 0.000299163427162614), ('n', 0.00029411770549606916), ('s', 0.000285039407231817), ('ctx', 0.0002529299197180285), ('x', 0.00019674508120093968), ('prefix', 0.00015219389018376124), ('t', 0.00013945302573163074)]
        DOMNodeRemovedListenerWrapper nodeRemoved
// nodeRemoved          0	: [('nodeRemoved', 0.9479173797871773), ('res', 8.757011204714464e-06), ('xmlTraitInformation', 5.732149801623315e-06), ('d', 5.642524278568763e-06), ('document', 3.670762771368607e-06), ('x1', 3.625950009841331e-06), ('rect', 3.4242925829685876e-06), ('pd', 3.2226351560958442e-06), ('raf', 3.2226351560958442e-06), ('iter', 2.7072883985321675e-06)]
            = new DOMNodeRemovedListenerWrapper(l);
// l                    0	: [('l', 0.906307754053644), ('i', 0.0004293947420351002), ('e', 0.00036930768499723247), ('current', 0.000299163427162614), ('n', 0.00029411770549606916), ('s', 0.000285039407231817), ('ctx', 0.0002529299197180285), ('x', 0.00019674508120093968), ('prefix', 0.00015219389018376124), ('t', 0.00013945302573163074)]
        DOMSubtreeModifiedListenerWrapper subtreeModified
// subtreeModified      0	: [('subtreeModified', 0.9479174245999388), ('res', 8.757011204714464e-06), ('xmlTraitInformation', 5.732149801623315e-06), ('d', 5.642524278568763e-06), ('document', 3.670762771368607e-06), ('x1', 3.625950009841331e-06), ('rect', 3.4242925829685876e-06), ('pd', 3.2226351560958442e-06), ('raf', 3.2226351560958442e-06), ('iter', 2.7072883985321675e-06)]
            = new DOMSubtreeModifiedListenerWrapper(l);
// l                    0	: [('l', 0.906307754053644), ('i', 0.0004293947420351002), ('e', 0.00036930768499723247), ('current', 0.000299163427162614), ('n', 0.00029411770549606916), ('s', 0.000285039407231817), ('ctx', 0.0002529299197180285), ('x', 0.00019674508120093968), ('prefix', 0.00015219389018376124), ('t', 0.00013945302573163074)]
        DOMCharacterDataModifiedListenerWrapper cdataModified
// cdataModified        0	: [('cdataModified', 0.9479173573807965), ('res', 8.757011204714464e-06), ('xmlTraitInformation', 5.732149801623315e-06), ('d', 5.642524278568763e-06), ('document', 3.670762771368607e-06), ('x1', 3.625950009841331e-06), ('rect', 3.4242925829685876e-06), ('pd', 3.2226351560958442e-06), ('raf', 3.2226351560958442e-06), ('iter', 2.7072883985321675e-06)]
            = new DOMCharacterDataModifiedListenerWrapper(l);
// l                    0	: [('l', 0.906307754053644), ('i', 0.0004293947420351002), ('e', 0.00036930768499723247), ('current', 0.000299163427162614), ('n', 0.00029411770549606916), ('s', 0.000285039407231817), ('ctx', 0.0002529299197180285), ('x', 0.00019674508120093968), ('prefix', 0.00015219389018376124), ('t', 0.00013945302573163074)]
        DOMAttrModifiedListenerWrapper attrModified
// attrModified         0	: [('attrModified', 0.9479179175403155), ('res', 8.757011204714464e-06), ('xmlTraitInformation', 5.732149801623315e-06), ('d', 5.642524278568763e-06), ('document', 3.670762771368607e-06), ('x1', 3.625950009841331e-06), ('rect', 3.4242925829685876e-06), ('pd', 3.2226351560958442e-06), ('raf', 3.2226351560958442e-06), ('iter', 2.7072883985321675e-06)]
            = new DOMAttrModifiedListenerWrapper(l);
// l                    0	: [('l', 0.906307754053644), ('i', 0.0004293947420351002), ('e', 0.00036930768499723247), ('current', 0.000299163427162614), ('n', 0.00029411770549606916), ('s', 0.000285039407231817), ('ctx', 0.0002529299197180285), ('x', 0.00019674508120093968), ('prefix', 0.00015219389018376124), ('t', 0.00013945302573163074)]

        cssNavigableDocumentListeners.put
// cssNavigableDocumentListeners 0	: [('cssNavigableDocumentListeners', 0.5095032409192419), ('resourceManager', 0.019000106197898985), ('l', 0.009545897381469026), ('ry', 0.009508452920338948), ('localizableSupport', 0.009507931908882444), ('animationLimitFPS', 0.009503285732003446), ('animationLimitUnlimited', 0.00950324091924192), ('fy', 0.009501006260644483), ('g', 0.0017805473279469887), ('t', 0.0013422276241723877)]
            (l, new EventListener[] { nodeInserted,
// l                    0	: [('l', 0.750447910116069), ('key', 0.008640846107194813), ('ns', 0.007208551877142675), ('syntaxName', 0.007031817013088192), ('id', 0.0023819492697593715), ('XLINK_NAMESPACE_URI', 0.0023609591431312436), ('n', 0.002244898017620919), ('name', 0.0017813355693025025), ('namespaceURI', 0.0016476064279322541), ('args', 0.0016088735898545559)]
// nodeInserted         0	: [('nodeInserted', 0.750509501763939), ('uri', 0.01222453009864854), ('current', 0.007223834881400838), ('attrName', 0.006106490530982537), ('name', 0.005612899280628167), ('u', 0.005091354014979162), ('attr', 0.0035738011839000073), ('uriStr', 0.003562076779844301), ('url', 0.003063757783148804), ('e', 0.00268935992847793)]
                                      nodeRemoved,
// nodeRemoved          0	: [('nodeRemoved', 0.9375028711951626), ('y', 0.00043332831429266874), ('ctx', 0.0003942010606684882), ('e', 0.00038988735153292403), ('CHAR_CLASS_AL', 0.0002606314289399905), ('s', 0.00023494691289326094), ('current', 0.0002273098681022649), ('w', 0.00017837934205542892), ('owner', 0.0001592467330519546), ('CHAR_CLASS_AI', 0.00014826176446329912)]
                                      subtreeModified,
// subtreeModified      0	: [('subtreeModified', 0.8750029160079242), ('y', 0.00043332831429266874), ('ctx', 0.0003942010606684882), ('e', 0.00038988735153292403), ('CHAR_CLASS_AL', 0.0002606314289399905), ('s', 0.00023494691289326094), ('current', 0.0002273098681022649), ('w', 0.00017837934205542892), ('owner', 0.0001592467330519546), ('CHAR_CLASS_AI', 0.00014826176446329912)]
                                      cdataModified,
// cdataModified        0	: [('cdataModified', 0.8750028487887819), ('y', 0.00043332831429266874), ('ctx', 0.0003942010606684882), ('e', 0.00038988735153292403), ('CHAR_CLASS_AL', 0.0002606314289399905), ('s', 0.00023494691289326094), ('current', 0.0002273098681022649), ('w', 0.00017837934205542892), ('owner', 0.0001592467330519546), ('CHAR_CLASS_AI', 0.00014826176446329912)]
                                      attrModified });
// attrModified         0	: [('attrModified', 0.8750034089483011), ('y', 0.00043332831429266874), ('ctx', 0.0003942010606684882), ('e', 0.00038988735153292403), ('CHAR_CLASS_AL', 0.0002606314289399905), ('s', 0.00023494691289326094), ('current', 0.0002273098681022649), ('w', 0.00017837934205542892), ('owner', 0.0001592467330519546), ('CHAR_CLASS_AI', 0.00014826176446329912)]

        XBLEventSupport es = (XBLEventSupport) initializeEventSupport();
// es                   0	: [('es', 0.5923372377148327), ('support', 0.14346903044952053), ('evtSupport', 0.0738665784712017), ('res', 3.502804481885786e-05), ('xmlTraitInformation', 2.292859920649326e-05), ('d', 2.2570097114275052e-05), ('document', 1.4683051085474428e-05), ('x1', 1.4503800039365324e-05), ('rect', 1.369717033187435e-05), ('pd', 1.2890540624383377e-05)]

        es.addImplementationEventListenerNS
// es                   0	: [('es', 0.278770291659669), ('evtSupport', 0.0556836374669974), ('domAttrModifiedEventListener', 0.05563885831785962), ('mouseclickListener', 0.02782064925535703), ('shadowTreeNodeInsertedListener', 0.027819456052477615), ('i', 0.006219301348914976), ('g', 0.004977427390452496), ('n', 0.00376531449225274), ('current', 0.0026513183494569475), ('type', 0.0015594223848388999)]
            (XMLConstants.XML_EVENTS_NAMESPACE_URI,
// XML_EVENTS_NAMESPACE_URI 0	: [('XML_EVENTS_NAMESPACE_URI', 0.9102075852942845), ('XMLNS_PREFIX', 0.006703521806389183), ('XML_NAMESPACE_URI', 0.005197007876001383), ('XMLNS_NAMESPACE_URI', 0.004963633085547557), ('XML_PREFIX', 0.002416670880889149), ('XML_VERSION_11', 0.00241649162984304), ('XML_CDATA_END', 0.001208613578367186), ('length', 0.0008587761453745481), ('XLINK_NAMESPACE_URI', 0.0006439795417870944), ('XML_PRESERVE_VALUE', 0.000604674552629259)]
             "DOMNodeInserted",
             nodeInserted, false);
// nodeInserted         No	: [('ir', 0.15047286050275424), ('l', 0.12041866771012136), ('childNodeRemovedEventListener', 0.04513757218631159), ('domNodeRemovedEventListener', 0.04513757218631159), ('domAttrModifiedEventListener', 0.04513757218631159), ('domNodeInsertedEventListener', 0.04513757218631159), ('subtreeModifiedEventListener', 0.04513748256078854), ('listeners', 0.03762838732578601), ('domNodeInsertedListener', 0.030558824462407614), ('domCharacterDataModifiedEventListener', 0.030091915153743317)]
        es.addImplementationEventListenerNS
// es                   2	: [('tgt', 0.08664395888358106), ('target', 0.06527314465986918), ('es', 0.05074816194487067), ('y', 0.03659502673321192), ('width', 0.03253419470704125), ('et', 0.018133042863242865), ('doc', 0.014837069631037545), ('parser', 0.010949452802129516), ('owner', 0.010850780896312074), ('cy', 0.010839187484424334)]
            (XMLConstants.XML_EVENTS_NAMESPACE_URI,
// XML_EVENTS_NAMESPACE_URI 0	: [('XML_EVENTS_NAMESPACE_URI', 0.9102075852942845), ('XMLNS_PREFIX', 0.006703521806389183), ('XML_NAMESPACE_URI', 0.005197007876001383), ('XMLNS_NAMESPACE_URI', 0.004963633085547557), ('XML_PREFIX', 0.002416670880889149), ('XML_VERSION_11', 0.00241649162984304), ('XML_CDATA_END', 0.001208613578367186), ('length', 0.0008587761453745481), ('XLINK_NAMESPACE_URI', 0.0006439795417870944), ('XML_PRESERVE_VALUE', 0.000604674552629259)]
             "DOMNodeRemoved",
             nodeRemoved, false);
// nodeRemoved          No	: [('ir', 0.15047286050275424), ('l', 0.12041866771012136), ('childNodeRemovedEventListener', 0.04513757218631159), ('domNodeRemovedEventListener', 0.04513757218631159), ('domAttrModifiedEventListener', 0.04513757218631159), ('domNodeInsertedEventListener', 0.04513757218631159), ('subtreeModifiedEventListener', 0.04513748256078854), ('listeners', 0.03762838732578601), ('domNodeInsertedListener', 0.030558824462407614), ('domCharacterDataModifiedEventListener', 0.030091915153743317)]
        es.addImplementationEventListenerNS
// es                   2	: [('tgt', 0.08664395888358106), ('target', 0.06527314465986918), ('es', 0.05074816194487067), ('y', 0.03659502673321192), ('width', 0.03253419470704125), ('et', 0.018133042863242865), ('doc', 0.014837069631037545), ('parser', 0.010949452802129516), ('owner', 0.010850780896312074), ('cy', 0.010839187484424334)]
            (XMLConstants.XML_EVENTS_NAMESPACE_URI,
// XML_EVENTS_NAMESPACE_URI 0	: [('XML_EVENTS_NAMESPACE_URI', 0.9102075852942845), ('XMLNS_PREFIX', 0.006703521806389183), ('XML_NAMESPACE_URI', 0.005197007876001383), ('XMLNS_NAMESPACE_URI', 0.004963633085547557), ('XML_PREFIX', 0.002416670880889149), ('XML_VERSION_11', 0.00241649162984304), ('XML_CDATA_END', 0.001208613578367186), ('length', 0.0008587761453745481), ('XLINK_NAMESPACE_URI', 0.0006439795417870944), ('XML_PRESERVE_VALUE', 0.000604674552629259)]
             "DOMSubtreeModified",
             subtreeModified, false);
// subtreeModified      No	: [('ir', 0.15047286050275424), ('l', 0.12041866771012136), ('childNodeRemovedEventListener', 0.04513757218631159), ('domNodeRemovedEventListener', 0.04513757218631159), ('domAttrModifiedEventListener', 0.04513757218631159), ('domNodeInsertedEventListener', 0.04513757218631159), ('subtreeModifiedEventListener', 0.04513748256078854), ('listeners', 0.03762838732578601), ('domNodeInsertedListener', 0.030558824462407614), ('domCharacterDataModifiedEventListener', 0.030091915153743317)]
        es.addImplementationEventListenerNS
// es                   2	: [('tgt', 0.08664395888358106), ('target', 0.06527314465986918), ('es', 0.05074816194487067), ('y', 0.03659502673321192), ('width', 0.03253419470704125), ('et', 0.018133042863242865), ('doc', 0.014837069631037545), ('parser', 0.010949452802129516), ('owner', 0.010850780896312074), ('cy', 0.010839187484424334)]
            (XMLConstants.XML_EVENTS_NAMESPACE_URI,
// XML_EVENTS_NAMESPACE_URI 0	: [('XML_EVENTS_NAMESPACE_URI', 0.9102075852942845), ('XMLNS_PREFIX', 0.006703521806389183), ('XML_NAMESPACE_URI', 0.005197007876001383), ('XMLNS_NAMESPACE_URI', 0.004963633085547557), ('XML_PREFIX', 0.002416670880889149), ('XML_VERSION_11', 0.00241649162984304), ('XML_CDATA_END', 0.001208613578367186), ('length', 0.0008587761453745481), ('XLINK_NAMESPACE_URI', 0.0006439795417870944), ('XML_PRESERVE_VALUE', 0.000604674552629259)]
             "DOMCharacterDataModified",
             cdataModified, false);
// cdataModified        No	: [('ir', 0.15047286050275424), ('l', 0.12041866771012136), ('childNodeRemovedEventListener', 0.04513757218631159), ('domNodeRemovedEventListener', 0.04513757218631159), ('domAttrModifiedEventListener', 0.04513757218631159), ('domNodeInsertedEventListener', 0.04513757218631159), ('subtreeModifiedEventListener', 0.04513748256078854), ('listeners', 0.03762838732578601), ('domNodeInsertedListener', 0.030558824462407614), ('domCharacterDataModifiedEventListener', 0.030091915153743317)]
        es.addImplementationEventListenerNS
// es                   2	: [('tgt', 0.08664395888358106), ('target', 0.06527314465986918), ('es', 0.05074816194487067), ('y', 0.03659502673321192), ('width', 0.03253419470704125), ('et', 0.018133042863242865), ('doc', 0.014837069631037545), ('parser', 0.010949452802129516), ('owner', 0.010850780896312074), ('cy', 0.010839187484424334)]
            (XMLConstants.XML_EVENTS_NAMESPACE_URI,
// XML_EVENTS_NAMESPACE_URI 0	: [('XML_EVENTS_NAMESPACE_URI', 0.9102075852942845), ('XMLNS_PREFIX', 0.006703521806389183), ('XML_NAMESPACE_URI', 0.005197007876001383), ('XMLNS_NAMESPACE_URI', 0.004963633085547557), ('XML_PREFIX', 0.002416670880889149), ('XML_VERSION_11', 0.00241649162984304), ('XML_CDATA_END', 0.001208613578367186), ('length', 0.0008587761453745481), ('XLINK_NAMESPACE_URI', 0.0006439795417870944), ('XML_PRESERVE_VALUE', 0.000604674552629259)]
             "DOMAttrModified",
             attrModified, false);
// attrModified         No	: [('ir', 0.15047286050275424), ('l', 0.12041866771012136), ('childNodeRemovedEventListener', 0.04513757218631159), ('domNodeRemovedEventListener', 0.04513757218631159), ('domAttrModifiedEventListener', 0.04513757218631159), ('domNodeInsertedEventListener', 0.04513757218631159), ('subtreeModifiedEventListener', 0.04513748256078854), ('listeners', 0.03762838732578601), ('domNodeInsertedListener', 0.030558824462407614), ('domCharacterDataModifiedEventListener', 0.030091915153743317)]
    }

    /**
     * Removes an event listener for mutations on the
     * CSSNavigableDocument tree.
     */
    public void removeCSSNavigableDocumentListener
            (CSSNavigableDocumentListener l) {
// l                    0	: [('l', 0.9397386144175096), ('listener', 0.007444258985151377), ('res', 8.757011204714464e-06), ('xmlTraitInformation', 5.732149801623315e-06), ('d', 5.642524278568763e-06), ('document', 3.670762771368607e-06), ('x1', 3.625950009841331e-06), ('rect', 3.4242925829685876e-06), ('pd', 3.2226351560958442e-06), ('raf', 3.2226351560958442e-06)]
        EventListener[] listeners
// listeners            0	: [('listeners', 0.8006600878295895), ('args', 0.0031922848600955396), ('colors', 0.0008664688449053374), ('ret', 0.0008127363308934642), ('pixels', 0.0008077397079831729), ('data', 0.0007797643351294362), ('values', 0.0006610961167238706), ('srcPixels', 0.000627541555059988), ('fractions', 0.0005683866969033144), ('res', 3.854730090030587e-05)]
            = (EventListener[]) cssNavigableDocumentListeners.get(l);
// cssNavigableDocumentListeners 1	: [('array', 0.3803485592400281), ('cssNavigableDocumentListeners', 0.38034844720812433), ('it', 0.021398646779498664), ('data', 0.010706513673638928), ('values', 0.010704559318962005), ('v', 0.005370242727062445), ('listeners', 0.005367096907841801), ('a', 0.005366628337027389), ('targets', 0.005348891854373516), ('n', 0.00012484475771533388)]
// l                    0	: [('l', 0.750513710928204), ('i', 0.026874893466510175), ('key', 0.009321561825971849), ('name', 0.007844427166713014), ('s', 0.007580349400190457), ('namespaceURI', 0.006159433148439344), ('index', 0.0055404321885840265), ('type', 0.004675427280446923), ('ns', 0.004617760478684203), ('e', 0.004472919556036467)]
        if (listeners == null) {
// listeners            0	: [('listeners', 0.5002827821382541), ('current', 0.02911007993550057), ('s', 0.01439306070555447), ('n', 0.007028872326540469), ('i', 0.0055786063930947484), ('v', 0.005120392636851221), ('idx', 0.004248690751953887), ('result', 0.004181692180482232), ('t', 0.003996619153103521), ('o', 0.0037785836704763488)]
            return;
        }

        XBLEventSupport es = (XBLEventSupport) initializeEventSupport();
// es                   0	: [('es', 0.1818489508593307), ('evtSupport', 0.045466313884806764), ('support', 0.011376121798082114), ('res', 0.00014011217927543143), ('xmlTraitInformation', 9.171439682597304e-05), ('d', 9.028038845710021e-05), ('document', 5.8732204341897704e-05), ('x1', 5.80152001574613e-05), ('rect', 5.47886813274974e-05), ('pd', 5.156216249753351e-05)]

        es.removeImplementationEventListenerNS
// es                   0	: [('es', 0.278770291659669), ('evtSupport', 0.0556836374669974), ('domAttrModifiedEventListener', 0.05563885831785962), ('mouseclickListener', 0.02782064925535703), ('shadowTreeNodeInsertedListener', 0.027819456052477615), ('i', 0.006219301348914976), ('g', 0.004977427390452496), ('n', 0.00376531449225274), ('current', 0.0026513183494569475), ('type', 0.0015594223848388999)]
            (XMLConstants.XML_EVENTS_NAMESPACE_URI,
// XML_EVENTS_NAMESPACE_URI 0	: [('XML_EVENTS_NAMESPACE_URI', 0.9102075852942845), ('XMLNS_PREFIX', 0.006703521806389183), ('XML_NAMESPACE_URI', 0.005197007876001383), ('XMLNS_NAMESPACE_URI', 0.004963633085547557), ('XML_PREFIX', 0.002416670880889149), ('XML_VERSION_11', 0.00241649162984304), ('XML_CDATA_END', 0.001208613578367186), ('length', 0.0008587761453745481), ('XLINK_NAMESPACE_URI', 0.0006439795417870944), ('XML_PRESERVE_VALUE', 0.000604674552629259)]
             "DOMNodeInserted",
             listeners[0], false);
// listeners            7	: [('ir', 0.15047286050275424), ('l', 0.12041866771012136), ('childNodeRemovedEventListener', 0.04513757218631159), ('domNodeRemovedEventListener', 0.04513757218631159), ('domAttrModifiedEventListener', 0.04513757218631159), ('domNodeInsertedEventListener', 0.04513757218631159), ('subtreeModifiedEventListener', 0.04513748256078854), ('listeners', 0.03762838732578601), ('domNodeInsertedListener', 0.030558824462407614), ('domCharacterDataModifiedEventListener', 0.030091915153743317)]
        es.removeImplementationEventListenerNS
// es                   4	: [('preventDefault', 0.25361868679534716), ('cssNavigableDocumentListeners', 0.25180814988962574), ('tgt', 0.04332197944179053), ('target', 0.03263657232993459), ('es', 0.025374080972435334), ('y', 0.01829751336660596), ('width', 0.016267097353520624), ('et', 0.009066521431621432), ('doc', 0.007418534815518772), ('owner', 0.005425390448156037)]
            (XMLConstants.XML_EVENTS_NAMESPACE_URI,
// XML_EVENTS_NAMESPACE_URI 0	: [('XML_EVENTS_NAMESPACE_URI', 0.9102075852942845), ('XMLNS_PREFIX', 0.006703521806389183), ('XML_NAMESPACE_URI', 0.005197007876001383), ('XMLNS_NAMESPACE_URI', 0.004963633085547557), ('XML_PREFIX', 0.002416670880889149), ('XML_VERSION_11', 0.00241649162984304), ('XML_CDATA_END', 0.001208613578367186), ('length', 0.0008587761453745481), ('XLINK_NAMESPACE_URI', 0.0006439795417870944), ('XML_PRESERVE_VALUE', 0.000604674552629259)]
             "DOMNodeRemoved",
             listeners[1], false);
// listeners            7	: [('ir', 0.15047286050275424), ('l', 0.12041866771012136), ('childNodeRemovedEventListener', 0.04513757218631159), ('domNodeRemovedEventListener', 0.04513757218631159), ('domAttrModifiedEventListener', 0.04513757218631159), ('domNodeInsertedEventListener', 0.04513757218631159), ('subtreeModifiedEventListener', 0.04513748256078854), ('listeners', 0.03762838732578601), ('domNodeInsertedListener', 0.030558824462407614), ('domCharacterDataModifiedEventListener', 0.030091915153743317)]
        es.removeImplementationEventListenerNS
// es                   4	: [('preventDefault', 0.25361868679534716), ('cssNavigableDocumentListeners', 0.25180814988962574), ('tgt', 0.04332197944179053), ('target', 0.03263657232993459), ('es', 0.025374080972435334), ('y', 0.01829751336660596), ('width', 0.016267097353520624), ('et', 0.009066521431621432), ('doc', 0.007418534815518772), ('owner', 0.005425390448156037)]
            (XMLConstants.XML_EVENTS_NAMESPACE_URI,
// XML_EVENTS_NAMESPACE_URI 0	: [('XML_EVENTS_NAMESPACE_URI', 0.9102075852942845), ('XMLNS_PREFIX', 0.006703521806389183), ('XML_NAMESPACE_URI', 0.005197007876001383), ('XMLNS_NAMESPACE_URI', 0.004963633085547557), ('XML_PREFIX', 0.002416670880889149), ('XML_VERSION_11', 0.00241649162984304), ('XML_CDATA_END', 0.001208613578367186), ('length', 0.0008587761453745481), ('XLINK_NAMESPACE_URI', 0.0006439795417870944), ('XML_PRESERVE_VALUE', 0.000604674552629259)]
             "DOMSubtreeModified",
             listeners[2], false);
// listeners            7	: [('ir', 0.15047286050275424), ('l', 0.12041866771012136), ('childNodeRemovedEventListener', 0.04513757218631159), ('domNodeRemovedEventListener', 0.04513757218631159), ('domAttrModifiedEventListener', 0.04513757218631159), ('domNodeInsertedEventListener', 0.04513757218631159), ('subtreeModifiedEventListener', 0.04513748256078854), ('listeners', 0.03762838732578601), ('domNodeInsertedListener', 0.030558824462407614), ('domCharacterDataModifiedEventListener', 0.030091915153743317)]
        es.removeImplementationEventListenerNS
// es                   4	: [('preventDefault', 0.25361868679534716), ('cssNavigableDocumentListeners', 0.25180814988962574), ('tgt', 0.04332197944179053), ('target', 0.03263657232993459), ('es', 0.025374080972435334), ('y', 0.01829751336660596), ('width', 0.016267097353520624), ('et', 0.009066521431621432), ('doc', 0.007418534815518772), ('owner', 0.005425390448156037)]
            (XMLConstants.XML_EVENTS_NAMESPACE_URI,
// XML_EVENTS_NAMESPACE_URI 0	: [('XML_EVENTS_NAMESPACE_URI', 0.9102075852942845), ('XMLNS_PREFIX', 0.006703521806389183), ('XML_NAMESPACE_URI', 0.005197007876001383), ('XMLNS_NAMESPACE_URI', 0.004963633085547557), ('XML_PREFIX', 0.002416670880889149), ('XML_VERSION_11', 0.00241649162984304), ('XML_CDATA_END', 0.001208613578367186), ('length', 0.0008587761453745481), ('XLINK_NAMESPACE_URI', 0.0006439795417870944), ('XML_PRESERVE_VALUE', 0.000604674552629259)]
             "DOMCharacterDataModified",
             listeners[3], false);
// listeners            7	: [('ir', 0.15047286050275424), ('l', 0.12041866771012136), ('childNodeRemovedEventListener', 0.04513757218631159), ('domNodeRemovedEventListener', 0.04513757218631159), ('domAttrModifiedEventListener', 0.04513757218631159), ('domNodeInsertedEventListener', 0.04513757218631159), ('subtreeModifiedEventListener', 0.04513748256078854), ('listeners', 0.03762838732578601), ('domNodeInsertedListener', 0.030558824462407614), ('domCharacterDataModifiedEventListener', 0.030091915153743317)]
        es.removeImplementationEventListenerNS
// es                   4	: [('preventDefault', 0.25361868679534716), ('cssNavigableDocumentListeners', 0.25180814988962574), ('tgt', 0.04332197944179053), ('target', 0.03263657232993459), ('es', 0.025374080972435334), ('y', 0.01829751336660596), ('width', 0.016267097353520624), ('et', 0.009066521431621432), ('doc', 0.007418534815518772), ('owner', 0.005425390448156037)]
            (XMLConstants.XML_EVENTS_NAMESPACE_URI,
// XML_EVENTS_NAMESPACE_URI 0	: [('XML_EVENTS_NAMESPACE_URI', 0.9102075852942845), ('XMLNS_PREFIX', 0.006703521806389183), ('XML_NAMESPACE_URI', 0.005197007876001383), ('XMLNS_NAMESPACE_URI', 0.004963633085547557), ('XML_PREFIX', 0.002416670880889149), ('XML_VERSION_11', 0.00241649162984304), ('XML_CDATA_END', 0.001208613578367186), ('length', 0.0008587761453745481), ('XLINK_NAMESPACE_URI', 0.0006439795417870944), ('XML_PRESERVE_VALUE', 0.000604674552629259)]
             "DOMAttrModified",
             listeners[4], false);
// listeners            7	: [('ir', 0.15047286050275424), ('l', 0.12041866771012136), ('childNodeRemovedEventListener', 0.04513757218631159), ('domNodeRemovedEventListener', 0.04513757218631159), ('domAttrModifiedEventListener', 0.04513757218631159), ('domNodeInsertedEventListener', 0.04513757218631159), ('subtreeModifiedEventListener', 0.04513748256078854), ('listeners', 0.03762838732578601), ('domNodeInsertedListener', 0.030558824462407614), ('domCharacterDataModifiedEventListener', 0.030091915153743317)]

        cssNavigableDocumentListeners.remove(l);
// cssNavigableDocumentListeners 1	: [('preventDefault', 0.25361868679534716), ('cssNavigableDocumentListeners', 0.25180814988962574), ('tgt', 0.04332197944179053), ('target', 0.03263657232993459), ('es', 0.025374080972435334), ('y', 0.01829751336660596), ('width', 0.016267097353520624), ('et', 0.009066521431621432), ('doc', 0.007418534815518772), ('owner', 0.005425390448156037)]
// l                    0	: [('l', 0.7770480858750811), ('node', 0.012094306571437674), ('i', 0.009426172015847466), ('namespaceURI', 0.00696511538424813), ('e', 0.0063671592008721425), ('id', 0.006038864411084462), ('item', 0.006025277328250461), ('dependent', 0.006002034605339503), ('index', 0.0050074791758416524), ('res', 2.9442386644278694e-05)]
    }
}
