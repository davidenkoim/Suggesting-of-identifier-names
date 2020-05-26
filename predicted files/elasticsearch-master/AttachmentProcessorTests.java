// Type of training: selfTestingIdentifier
// Path to file: C:\Users\Igor\IdeaProjects\SLP-Core-0.3\java-projects\elasticsearch-master\plugins\ingest-attachment\src\test\java\org\elasticsearch\ingest\attachment\AttachmentProcessorTests.java
// Number of identifiers: 54	Accuracy: 70.37%	MRR: 75.75%
// True 	Rank of true in predictions : [(predicted token, probability of a token), ...]

package org.elasticsearch.ingest.attachment;

import org.apache.commons.io.IOUtils;
import org.elasticsearch.ElasticsearchParseException;
import org.elasticsearch.ingest.IngestDocument;
import org.elasticsearch.ingest.Processor;
import org.elasticsearch.ingest.RandomDocumentPicks;
import org.elasticsearch.test.ESTestCase;
import org.junit.Before;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import static org.elasticsearch.ingest.IngestDocumentMatcher.assertIngestDocument;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

public class AttachmentProcessorTests extends ESTestCase {

    private AttachmentProcessor processor;

    @Before
    public void createStandardProcessor() {
        processor = new AttachmentProcessor(randomAlphaOfLength(10), "source_field", "target_field", EnumSet.allOf(AttachmentProcessor.Property.class), 10000, false, null);
    }

    public void testEnglishTextDocument() throws Exception {
        Map<String, Object> attachmentData = parseDocument("text-in-english.txt", processor);
// attachmentData       0	: [('attachmentData', 0.469478353850854), ('metadata', 0.14310652634391274), ('response', 0.06895355009748097), ('e', 0.05276332261841379), ('map', 0.048774924777421486), ('params', 0.040474711413158775), ('config', 0.035525160533601614), ('source', 0.033757827724932556), ('result', 0.0316642401937532), ('compoundProcessor', 0.03132066323946717)]
        assertThat(attachmentData.keySet(), containsInAnyOrder("language", "content", "content_type", "content_length"));
        assertThat(attachmentData.get("language"), is("en"));
        assertThat(attachmentData.get("content"), is("\"God Save the Queen\" (alternatively \"God Save the King\""));
        assertThat(attachmentData.get("content_type").toString(), containsString("text/plain"));
        assertThat(attachmentData.get("content_length"), is(notNullValue()));
    }

    public void testHtmlDocumentWithRandomFields() throws Exception {
        ArrayList<AttachmentProcessor.Property> fieldsList = new ArrayList<>(EnumSet.complementOf(EnumSet.of(AttachmentProcessor.Property.DATE)));
// fieldsList           No	: [('properties', 0.4821841660214051), ('selectedProperties', 0.39881006882542613), ('length', 0.08533325080495217), ('numDocs', 0.06349809024455262), ('possibleList', 0.03008837412243002), ('filteredRoles', 0.027608870366067535), ('transport', 0.025086239735719013), ('numNodes', 0.024511690284791086), ('asnOnlyProperties', 0.023810064361444002), ('cityOnlyProperties', 0.023810064361444002)]
        Set<AttachmentProcessor.Property> selectedProperties = new HashSet<>();
// selectedProperties   No	: [('parser', 0.7681144935087549), ('properties', 0.482184166022706), ('fieldsList', 0.3988105181794056), ('field', 0.10723043131630146), ('builder', 0.09234749383672486), ('request', 0.05673649847835067), ('searchResponse', 0.03977885716599715), ('targetField', 0.03125817709266553), ('_la', 0.0270766332947567), ('response', 0.02680985380654441)]
        int numFields = randomIntBetween(1, fieldsList.size());
// numFields            3	: [('numDocs', 0.08196557977226429), ('size', 0.0666868693193145), ('numIndices', 0.04836314020177121), ('numFields', 0.030790521731359675), ('i', 0.02975667073287018), ('count', 0.029488364440489766), ('fieldNames', 0.02198322915655376), ('nMessages', 0.02197950112451939), ('translogOperations', 0.021320733893308446), ('clusterState', 0.020571642072301617)]
        String[] selectedFieldNames = new String[numFields];
// selectedFieldNames   No	: [('map', 0.11114929957098348), ('params', 0.11108670924294942), ('t', 0.11099431868646513), ('ingestDocument', 0.11095902440865993), ('roles', 0.11094760743602763), ('indices', 0.09834457264745627), ('index', 0.08033036120506852), ('visibleIndex', 0.07175938375290096), ('createdConfigs', 0.06409443826082654), ('expectedIndices', 0.053189798810721854)]
        for (int i = 0; i < numFields; i++) {
// i                    0	: [('i', 0.9324151211627207), ('j', 0.07259031548958861), ('fieldIndex', 0.005599160941388192), ('docId', 0.00409642139328349), ('index', 0.0038195180145625027), ('k', 0.0037645746625113792), ('doc', 0.0037240574838577896), ('t', 0.002214519182329058), ('offset', 0.0019571707314652766), ('d', 0.0016232283038170182)]
            AttachmentProcessor.Property property;
// property             0	: [('property', 0.8213486176536134), ('bucket', 0.04909013255933451), ('i', 0.03013500819776763), ('values', 0.027897992220727644), ('in', 0.02774309365590293), ('line', 0.02622723996904594), ('result', 0.01783544132635581), ('name', 0.017673162992148918), ('token', 0.017600990628303777), ('current', 0.01748371191259958)]
            do {
                property = randomFrom(fieldsList);
            } while (selectedProperties.add(property) == false);
            selectedFieldNames[i] = property.toLowerCase();
        }
        if (randomBoolean()) {
            selectedProperties.add(AttachmentProcessor.Property.DATE);
        }
        processor = new AttachmentProcessor(randomAlphaOfLength(10), "source_field", "target_field", selectedProperties, 10000, false, null);
        Map<String, Object> attachmentData = parseDocument("htmlWithEmptyDateMeta.html", processor);
// attachmentData       0	: [('attachmentData', 0.4695082274610184), ('metadata', 0.1431065263048414), ('response', 0.06894737706796666), ('e', 0.0527585284879323), ('map', 0.04877492475092438), ('params', 0.04047471136260366), ('config', 0.035525160500424285), ('source', 0.033757827664139005), ('result', 0.03166141997309151), ('compoundProcessor', 0.03132065848465165)]
        assertThat(attachmentData.keySet(), hasSize(selectedFieldNames.length));
        assertThat(attachmentData.keySet(), containsInAnyOrder(selectedFieldNames));
    }

    public void testFrenchTextDocument() throws Exception {
        Map<String, Object> attachmentData = parseDocument("text-in-french.txt", processor);
// attachmentData       0	: [('attachmentData', 0.4695082274610184), ('metadata', 0.1431065263048414), ('response', 0.06894737706796666), ('e', 0.0527585284879323), ('map', 0.04877492475092438), ('params', 0.04047471136260366), ('config', 0.035525160500424285), ('source', 0.033757827664139005), ('result', 0.03166141997309151), ('compoundProcessor', 0.03132065848465165)]
        assertThat(attachmentData.keySet(), hasItem("language"));
        assertThat(attachmentData.get("language"), is("fr"));
    }

    public void testUnknownLanguageDocument() throws Exception {
        Map<String, Object> attachmentData = parseDocument("text-gibberish.txt", processor);
// attachmentData       0	: [('attachmentData', 0.4695082274610184), ('metadata', 0.1431065263048414), ('response', 0.06894737706796666), ('e', 0.0527585284879323), ('map', 0.04877492475092438), ('params', 0.04047471136260366), ('config', 0.035525160500424285), ('source', 0.033757827664139005), ('result', 0.03166141997309151), ('compoundProcessor', 0.03132065848465165)]
        assertThat(attachmentData.keySet(), hasItem("language"));
        assertThat(attachmentData.get("language"), is("lt"));
    }

    public void testEmptyTextDocument() throws Exception {
        Map<String, Object> attachmentData = parseDocument("text-empty.txt", processor);
// attachmentData       0	: [('attachmentData', 0.4695181844274355), ('metadata', 0.1431065262918177), ('map', 0.048774924742092045), ('params', 0.04047471134575201), ('config', 0.03552516048936521), ('source', 0.03375782764387456), ('compoundProcessor', 0.031320656899857795), ('response', 0.030850932806674978), ('entry', 0.022127030398800686), ('e', 0.021544917776939947)]
        assertThat(attachmentData.keySet(), not(hasItem("language")));
    }

    public void testWordDocument() throws Exception {
        Map<String, Object> attachmentData = parseDocument("issue-104.docx", processor);
// attachmentData       0	: [('attachmentData', 0.4694584358513406), ('metadata', 0.14310652636996038), ('response', 0.06895766609626476), ('e', 0.052766519214088585), ('map', 0.04877492479508629), ('params', 0.04047471144686233), ('config', 0.035525160555719935), ('source', 0.03375782776546176), ('result', 0.0316661206345674), ('compoundProcessor', 0.03132066640970583)]
        assertThat(attachmentData.keySet(), containsInAnyOrder("content", "language", "date", "author", "content_type", "content_length"));
        assertThat(attachmentData.get("content"), is(notNullValue()));
        assertThat(attachmentData.get("language"), is("en"));
        assertThat(attachmentData.get("date"), is("2012-10-12T11:17:00Z"));
        assertThat(attachmentData.get("author"), is("Windows User"));
        assertThat(attachmentData.get("content_length"), is(notNullValue()));
        assertThat(attachmentData.get("content_type").toString(), is("application/vnd.openxmlformats-officedocument.wordprocessingml.document"));
    }

    public void testWordDocumentWithVisioSchema() throws Exception {
        Map<String, Object> attachmentData = parseDocument("issue-22077.docx", processor);
// attachmentData       0	: [('attachmentData', 0.4694584358513406), ('metadata', 0.14310652636996038), ('response', 0.06895766609626476), ('e', 0.052766519214088585), ('map', 0.04877492479508629), ('params', 0.04047471144686233), ('config', 0.035525160555719935), ('source', 0.03375782776546176), ('result', 0.0316661206345674), ('compoundProcessor', 0.03132066640970583)]
        assertThat(attachmentData.keySet(), containsInAnyOrder("content", "language", "date", "author", "content_type", "content_length"));
        assertThat(attachmentData.get("content").toString(), containsString("Table of Contents"));
        assertThat(attachmentData.get("language"), is("en"));
        assertThat(attachmentData.get("date"), is("2015-01-06T18:07:00Z"));
        assertThat(attachmentData.get("author"), is(notNullValue()));
        assertThat(attachmentData.get("content_length"), is(notNullValue()));
        assertThat(attachmentData.get("content_type").toString(), is("application/vnd.openxmlformats-officedocument.wordprocessingml.document"));
    }

    public void testLegacyWordDocumentWithVisioSchema() throws Exception {
        Map<String, Object> attachmentData = parseDocument("issue-22077.doc", processor);
// attachmentData       0	: [('attachmentData', 0.4694584358513406), ('metadata', 0.14310652636996038), ('response', 0.06895766609626476), ('e', 0.052766519214088585), ('map', 0.04877492479508629), ('params', 0.04047471144686233), ('config', 0.035525160555719935), ('source', 0.03375782776546176), ('result', 0.0316661206345674), ('compoundProcessor', 0.03132066640970583)]
        assertThat(attachmentData.keySet(), containsInAnyOrder("content", "language", "date", "author", "content_type", "content_length"));
        assertThat(attachmentData.get("content").toString(), containsString("Table of Contents"));
        assertThat(attachmentData.get("language"), is("en"));
        assertThat(attachmentData.get("date"), is("2016-12-16T15:04:00Z"));
        assertThat(attachmentData.get("author"), is(notNullValue()));
        assertThat(attachmentData.get("content_length"), is(notNullValue()));
        assertThat(attachmentData.get("content_type").toString(), is("application/msword"));
    }

    public void testPdf() throws Exception {
        Map<String, Object> attachmentData = parseDocument("test.pdf", processor);
// attachmentData       0	: [('attachmentData', 0.4694982700428031), ('metadata', 0.14310652631786516), ('response', 0.06894943461533341), ('e', 0.05276012642970212), ('map', 0.048774924759756734), ('params', 0.04047471137945533), ('config', 0.03552516051148338), ('source', 0.03375782768440349), ('result', 0.03166235998791176), ('compoundProcessor', 0.031320660069517836)]
        assertThat(attachmentData.get("content"), is("This is a test, with umlauts, from München\n\nAlso contains newlines for testing.\n\nAnd one more."));
        assertThat(attachmentData.get("content_type").toString(), is("application/pdf"));
        assertThat(attachmentData.get("content_length"), is(notNullValue()));
    }

    public void testVisioIsExcluded() throws Exception {
        Map<String, Object> attachmentData = parseDocument("issue-22077.vsdx", processor);
// attachmentData       0	: [('attachmentData', 0.4694982700428031), ('metadata', 0.14310652631786516), ('response', 0.06894943461533341), ('e', 0.05276012642970212), ('map', 0.048774924759756734), ('params', 0.04047471137945533), ('config', 0.03552516051148338), ('source', 0.03375782768440349), ('result', 0.03166235998791176), ('compoundProcessor', 0.031320660069517836)]
        assertThat(attachmentData.get("content"), nullValue());
        assertThat(attachmentData.get("content_type"), is("application/vnd.ms-visio.drawing"));
        assertThat(attachmentData.get("content_length"), is(0L));
    }

    public void testEncryptedPdf() throws Exception {
        ElasticsearchParseException e = expectThrows(ElasticsearchParseException.class, () -> parseDocument("encrypted.pdf", processor));
// e                    0	: [('e', 0.9182240248344689), ('response', 0.0689466816714896), ('result', 0.031661092319603686), ('clusterState', 0.02660130132501769), ('bucket', 0.022970926258385926), ('searchResponse', 0.019661491013526086), ('exception', 0.018789520362453016), ('stats', 0.018619782173907314), ('request', 0.018474170976750675), ('terms', 0.009629565422281468)]
        assertThat(e.getDetailedMessage(), containsString("document is encrypted"));
    }

    public void testHtmlDocument() throws Exception {
        Map<String, Object> attachmentData = parseDocument("htmlWithEmptyDateMeta.html", processor);
// attachmentData       0	: [('attachmentData', 0.46944847617366997), ('metadata', 0.14310652638298424), ('response', 0.06895972428942683), ('e', 0.05276811766456232), ('map', 0.04877492480391873), ('params', 0.04047471146371415), ('config', 0.035525160566779124), ('source', 0.03375782778572641), ('result', 0.0316670609431036), ('compoundProcessor', 0.031320667994933674)]
        assertThat(attachmentData.keySet(), containsInAnyOrder("language", "content", "author", "keywords", "title", "content_type", "content_length"));
        assertThat(attachmentData.get("language"), is("en"));
        assertThat(attachmentData.get("content"), is(notNullValue()));
        assertThat(attachmentData.get("content_length"), is(notNullValue()));
        assertThat(attachmentData.get("author"), is("kimchy"));
        assertThat(attachmentData.get("keywords"), is("elasticsearch,cool,bonsai"));
        assertThat(attachmentData.get("title"), is("Hello"));
        assertThat(attachmentData.get("content_type").toString(), containsString("text/html"));
    }

    public void testXHtmlDocument() throws Exception {
        Map<String, Object> attachmentData = parseDocument("testXHTML.html", processor);
// attachmentData       0	: [('attachmentData', 0.4695082274610184), ('metadata', 0.1431065263048414), ('response', 0.06894737706796666), ('e', 0.0527585284879323), ('map', 0.04877492475092438), ('params', 0.04047471136260366), ('config', 0.035525160500424285), ('source', 0.033757827664139005), ('result', 0.03166141997309151), ('compoundProcessor', 0.03132065848465165)]
        assertThat(attachmentData.keySet(), containsInAnyOrder("language", "content", "author", "title", "content_type", "content_length"));
        assertThat(attachmentData.get("content_type").toString(), containsString("application/xhtml+xml"));
    }

    public void testEpubDocument() throws Exception {
        Map<String, Object> attachmentData = parseDocument("testEPUB.epub", processor);
// attachmentData       0	: [('attachmentData', 0.4695082274610184), ('metadata', 0.1431065263048414), ('response', 0.06894737706796666), ('e', 0.0527585284879323), ('map', 0.04877492475092438), ('params', 0.04047471136260366), ('config', 0.035525160500424285), ('source', 0.033757827664139005), ('result', 0.03166141997309151), ('compoundProcessor', 0.03132065848465165)]
        assertThat(attachmentData.keySet(), containsInAnyOrder("language", "content", "author", "title", "content_type", "content_length", "date", "keywords"));
        assertThat(attachmentData.get("content_type").toString(), containsString("application/epub+zip"));
    }

    public void testAsciidocDocument() throws Exception {
        Map<String, Object> attachmentData = parseDocument("asciidoc.asciidoc", processor);
// attachmentData       0	: [('attachmentData', 0.4695082274610184), ('metadata', 0.1431065263048414), ('response', 0.06894737706796666), ('e', 0.0527585284879323), ('map', 0.04877492475092438), ('params', 0.04047471136260366), ('config', 0.035525160500424285), ('source', 0.033757827664139005), ('result', 0.03166141997309151), ('compoundProcessor', 0.03132065848465165)]
        assertThat(attachmentData.keySet(), containsInAnyOrder("language", "content_type", "content", "content_length"));
        assertThat(attachmentData.get("content_type").toString(), containsString("text/plain"));
    }

    public void testZipFileDoesNotHang() throws Exception {
        parseDocument("bad_tika.zip", processor);
    }

    public void testParseAsBytesArray() throws Exception {
        String path = "/org/elasticsearch/ingest/attachment/test/sample-files/text-in-english.txt";
// path                 0	: [('path', 0.30217333391699697), ('mapping', 0.1850792576956236), ('name', 0.1285786132036572), ('resource', 0.12439215495651877), ('json', 0.11068236047507976), ('jobId', 0.07487282941534026), ('resourcePath', 0.04542392064384708), ('url', 0.04147098051163257), ('resourcePathToStore', 0.04146192241536601), ('resourcePathToFile', 0.041461830612632845)]
        byte[] bytes;
// bytes                6	: [('array', 0.5066580947591447), ('address', 0.2916798070673269), ('ip', 0.29167043391536174), ('randomObject', 0.29166696208990894), ('keyStore', 0.08188390848897545), ('certificates', 0.08184275941381151), ('bytes', 0.06606832646419378), ('terminal', 0.04124416032546639), ('path', 0.041079831934246365), ('description', 0.041043778598353814)]
        try (InputStream is = AttachmentProcessorTests.class.getResourceAsStream(path)) {
// is                   0	: [('is', 0.7651887843221257), ('stream', 0.3426450847782359), ('in', 0.10409332683646348), ('inputStream', 0.07517066983170544), ('input', 0.0440596602968816), ('content', 0.041390336224642325), ('response', 0.02129540388948533), ('actualEntity', 0.020982403007243324), ('executeRepsonse', 0.02098235487384383), ('jar', 0.014192880791680752)]
            bytes = IOUtils.toByteArray(is);
        }
        Map<String, Object> document = new HashMap<>();
// document             4	: [('directory', 0.22565177400085157), ('dir', 0.15711991006668757), ('metadata', 0.14310653151485286), ('ingestDocument', 0.1022183395679911), ('document', 0.08747358921033452), ('map', 0.04877492808973455), ('params', 0.040474713915194784), ('config', 0.0355251617374089), ('source', 0.03375782944099402), ('xContentType', 0.026116468351476272)]
        document.put("source_field", bytes);
        IngestDocument ingestDocument = RandomDocumentPicks.randomIngestDocument(random(), document);
// ingestDocument       0	: [('ingestDocument', 0.6934353399179165), ('document', 0.1036126601152311), ('originalIngestDocument', 0.05714426214291775), ('entry', 0.05040778307116393), ('doc', 0.045985405941098435), ('value', 0.03613135893460643), ('source', 0.029991685624632586), ('map', 0.02717043564408782), ('vars', 0.026375367289046527), ('args', 0.026108830690915664)]
        processor.execute(ingestDocument);
        @SuppressWarnings("unchecked")
        Map<String, Object> attachmentData = (Map<String, Object>) ingestDocument.getSourceAndMetadata().get("target_field");
// attachmentData       No	: [('geoData', 0.30034948132481065), ('target', 0.2003332798670875), ('metadata', 0.14310652634391274), ('response', 0.06895355009748097), ('e', 0.05276332261841379), ('map', 0.048774924777421486), ('params', 0.040474711413158775), ('config', 0.035525160533601614), ('source', 0.033757827724932556), ('result', 0.0316642401937532)]
        assertThat(attachmentData.keySet(), containsInAnyOrder("language", "content", "content_type", "content_length"));
        assertThat(attachmentData.get("language"), is("en"));
        assertThat(attachmentData.get("content"), is("\"God Save the Queen\" (alternatively \"God Save the King\""));
        assertThat(attachmentData.get("content_type").toString(), containsString("text/plain"));
        assertThat(attachmentData.get("content_length"), is(notNullValue()));
    }

    public void testNullValueWithIgnoreMissing() throws Exception {
        IngestDocument originalIngestDocument = RandomDocumentPicks.randomIngestDocument(random(), Collections.singletonMap("source_field", null));
// originalIngestDocument 0	: [('originalIngestDocument', 0.8753991753038242), ('ingestDocument', 0.8629212453923625), ('source', 0.21915438281368763), ('map', 0.09797359759353697), ('index', 0.07552215778362278), ('sourceAndMetadata', 0.04929354652631786), ('simulateVerboseIngestDocument', 0.018577367758403403), ('otherWriteableIngestDocument', 0.01472349296936666), ('otherSourceAndMetadata', 0.009968587461253594), ('toXContentSource', 0.006629088447677312)]
        IngestDocument ingestDocument = new IngestDocument(originalIngestDocument);
// ingestDocument       0	: [('ingestDocument', 0.9375197860981286), ('document', 0.0591704260132053), ('doc', 0.04973164560292167), ('originalIngestDocument', 0.023109592715503058), ('document2', 0.02248656561781716), ('request', 0.011259892717219725), ('ctx', 0.011173804470671402), ('context', 0.010001593089065768), ('terminal', 0.00805061632242848), ('copy', 0.007484384218089868)]
        Processor processor = new AttachmentProcessor(randomAlphaOfLength(10), "source_field", "randomTarget", null, 10, true, null);
// processor            0	: [('processor', 0.9168411631834268), ('objectParser', 0.10805193419963581), ('listener', 0.040292624370217696), ('context', 0.03732311931274744), ('parser', 0.0369624442122447), ('roleNames', 0.03630214694106994), ('shardsParser', 0.036009704459142595), ('fieldNamesFieldType', 0.036008721800595486), ('delayedDataDetector', 0.03600813403390407), ('internalParser', 0.03600808046616402)]
        processor.execute(ingestDocument);
        assertIngestDocument(originalIngestDocument, ingestDocument);
    }

    public void testNonExistentWithIgnoreMissing() throws Exception {
        IngestDocument originalIngestDocument = RandomDocumentPicks.randomIngestDocument(random(), Collections.emptyMap());
// originalIngestDocument 0	: [('originalIngestDocument', 0.8753991753038242), ('ingestDocument', 0.8629212453923625), ('source', 0.21915438281368763), ('map', 0.09797359759353697), ('index', 0.07552215778362278), ('sourceAndMetadata', 0.04929354652631786), ('simulateVerboseIngestDocument', 0.018577367758403403), ('otherWriteableIngestDocument', 0.01472349296936666), ('otherSourceAndMetadata', 0.009968587461253594), ('toXContentSource', 0.006629088447677312)]
        IngestDocument ingestDocument = new IngestDocument(originalIngestDocument);
// ingestDocument       0	: [('ingestDocument', 0.9375197860981286), ('doc', 0.19103599342900862), ('document2', 0.07683439170477369), ('document', 0.06823053075770803), ('originalIngestDocument', 0.05571828836767697), ('copy', 0.029223514652872477), ('testIngestDocument', 0.01785037323443049), ('request', 0.011259893063900534), ('ctx', 0.011173804536332965), ('context', 0.010001593207221588)]
        Processor processor = new AttachmentProcessor(randomAlphaOfLength(10), "source_field", "randomTarget", null, 10, true, null);
// processor            0	: [('processor', 0.9168411631834268), ('objectParser', 0.10805193419963581), ('listener', 0.040292624370217696), ('context', 0.03732311931274744), ('parser', 0.0369624442122447), ('roleNames', 0.03630214694106994), ('shardsParser', 0.036009704459142595), ('fieldNamesFieldType', 0.036008721800595486), ('delayedDataDetector', 0.03600813403390407), ('internalParser', 0.03600808046616402)]
        processor.execute(ingestDocument);
        assertIngestDocument(originalIngestDocument, ingestDocument);
    }

    public void testNullWithoutIgnoreMissing() throws Exception {
        IngestDocument originalIngestDocument = RandomDocumentPicks.randomIngestDocument(random(), Collections.singletonMap("source_field", null));
// originalIngestDocument 1	: [('ingestDocument', 0.8629212476207189), ('originalIngestDocument', 0.44533055183051984), ('source', 0.21915438264009657), ('map', 0.09797359755360185), ('index', 0.07552215754240094), ('sourceAndMetadata', 0.04929354652552611), ('otherSourceAndMetadata', 0.009968587461055656), ('toXContentSource', 0.006629088447584083), ('original', 0.00498523269837942), ('originalDoc', 0.004959429627586875)]
        IngestDocument ingestDocument = new IngestDocument(originalIngestDocument);
// ingestDocument       0	: [('ingestDocument', 0.7749224243185311), ('doc', 0.08669246166573777), ('document', 0.04649140031706971), ('originalIngestDocument', 0.02310959494725119), ('document2', 0.02248656561772151), ('request', 0.01125989231408509), ('ctx', 0.01117380439582375), ('context', 0.010001592953765893), ('terminal', 0.008050616291879458), ('copy', 0.007484384216157735)]
        Processor processor = new AttachmentProcessor(randomAlphaOfLength(10), "source_field", "randomTarget", null, 10, false, null);
// processor            0	: [('processor', 0.9168411631834268), ('builder', 0.04572366823472917), ('ft', 0.029722704851209425), ('results', 0.02869878023825216), ('conversion', 0.02714752573160644), ('parser', 0.02007207702686726), ('factory', 0.018967745296768423), ('jps', 0.017415308179537112), ('mapper', 0.01659315817273186), ('service', 0.01601644141622422)]
        Exception exception = expectThrows(Exception.class, () -> processor.execute(ingestDocument));
// exception            2	: [('e', 0.49858011003736497), ('expectedException', 0.19557202638312823), ('exception', 0.11882206250549052), ('exc', 0.09850528085892106), ('response', 0.0689466816714896), ('result', 0.031661092319603686), ('clusterState', 0.02660130132501769), ('bucket', 0.022970926258385926), ('searchResponse', 0.019661491013526086), ('stats', 0.018619782173907314)]
        assertThat(exception.getMessage(), equalTo("field [source_field] is null, cannot parse."));
    }

    public void testNonExistentWithoutIgnoreMissing() throws Exception {
        IngestDocument originalIngestDocument = RandomDocumentPicks.randomIngestDocument(random(), Collections.emptyMap());
// originalIngestDocument 1	: [('ingestDocument', 0.8629212476207189), ('originalIngestDocument', 0.44533055183051984), ('source', 0.21915438264009657), ('map', 0.09797359755360185), ('index', 0.07552215754240094), ('sourceAndMetadata', 0.04929354652552611), ('otherSourceAndMetadata', 0.009968587461055656), ('toXContentSource', 0.006629088447584083), ('original', 0.00498523269837942), ('originalDoc', 0.004959429627586875)]
        IngestDocument ingestDocument = new IngestDocument(originalIngestDocument);
// ingestDocument       0	: [('ingestDocument', 0.6929564149748375), ('doc', 0.19103599341763772), ('document2', 0.07683439170467804), ('document', 0.06823053075185233), ('originalIngestDocument', 0.055718290599425103), ('copy', 0.029223514650940342), ('testIngestDocument', 0.01785037323437884), ('request', 0.011259892660765079), ('ctx', 0.011173804461485158), ('context', 0.010001593071921432)]
        Processor processor = new AttachmentProcessor(randomAlphaOfLength(10), "source_field", "randomTarget", null, 10, false, null);
// processor            0	: [('processor', 0.9168411631834268), ('builder', 0.04572366823472917), ('ft', 0.029722704851209425), ('results', 0.02869878023825216), ('conversion', 0.02714752573160644), ('parser', 0.02007207702686726), ('factory', 0.018967745296768423), ('jps', 0.017415308179537112), ('mapper', 0.01659315817273186), ('service', 0.01601644141622422)]
        Exception exception = expectThrows(Exception.class, () -> processor.execute(ingestDocument));
// exception            2	: [('e', 0.49858011003736497), ('expectedException', 0.19557202638312823), ('exception', 0.11882206250549052), ('exc', 0.09850528085892106), ('response', 0.0689466816714896), ('result', 0.031661092319603686), ('clusterState', 0.02660130132501769), ('bucket', 0.022970926258385926), ('searchResponse', 0.019661491013526086), ('stats', 0.018619782173907314)]
        assertThat(exception.getMessage(), equalTo("field [source_field] not present as part of path [source_field]"));
    }

    private Map<String, Object> parseDocument(String file, AttachmentProcessor processor) throws Exception {
// file                 0	: [('file', 0.8127016610095462), ('xml', 0.06257714906467918), ('mapper', 0.023484458695340323), ('p', 0.01617313310870149), ('indexShard', 0.015718625206036885), ('name', 0.01018024043328481), ('source', 0.008425958232605064), ('str', 0.00784195677886698), ('sourceToParse', 0.007815205860579192), ('emptySource', 0.007813191245032618)]
// processor            0	: [('processor', 0.9500034860535468), ('logger', 0.1747416694483298), ('other', 0.10986761998405356), ('watcherService', 0.0585496429560297), ('lines', 0.0327477004093692), ('attrs', 0.03274443948718595), ('exc', 0.023829125984726227), ('length', 0.022012124872534903), ('input', 0.021931675502689863), ('timeout', 0.021920935148422197)]
        return parseDocument(file, processor, new HashMap<>());
    }

    private Map<String, Object> parseDocument(String file, AttachmentProcessor processor, Map<String, Object> optionalFields) throws Exception {
// file                 0	: [('file', 0.8127016610095462), ('xml', 0.06257714906467918), ('name', 0.01018024043328481), ('i', 0.0044907362468101075), ('index', 0.0033440461152247822), ('id', 0.002986230150855919), ('request', 0.0028720987285408006), ('field', 0.002763519292534929), ('e', 0.002507417203930135), ('response', 0.002506090845306726)]
// processor            0	: [('processor', 0.9500034860535468), ('dateProcessor', 0.22663237245694498), ('jsonProcessor', 0.1510871973991816), ('ingestDocument', 0.1159388575530359), ('document', 0.0564430485103094), ('iw', 0.006483235018289828), ('ingestDocumentList', 0.005372511715430632), ('indexWriter', 0.004522133798027173), ('i', 0.0027894486086800334), ('writer', 0.002003641273010618)]
// optionalFields       No	: [('metadata', 0.1431065262918177), ('map', 0.048774924742092045), ('params', 0.04047471134575201), ('config', 0.03552516048936521), ('source', 0.03375782764387456), ('entry', 0.022127030398800686), ('p', 0.021764431541599932), ('fields', 0.01839089986970999), ('parameters', 0.018317594323880724), ('response', 0.01822608205177577)]
        Map<String, Object> document = new HashMap<>();
// document             6	: [('keystore', 0.31252658039516545), ('cacheFile', 0.3125038496372784), ('directory', 0.22565177400331934), ('dir', 0.15711991006912668), ('metadata', 0.1431065315278766), ('ingestDocument', 0.10221833957162774), ('document', 0.08747358698419148), ('map', 0.0487749280985669), ('params', 0.040474713932046456), ('config', 0.03552516174846799)]
        document.put("source_field", getAsBinaryOrBase64(file));
        document.putAll(optionalFields);
        IngestDocument ingestDocument = RandomDocumentPicks.randomIngestDocument(random(), document);
// ingestDocument       0	: [('ingestDocument', 0.6934353399495443), ('document', 0.1036126601152311), ('originalIngestDocument', 0.05714426214291775), ('entry', 0.05040778307116393), ('doc', 0.045985405941098435), ('value', 0.03613135893460643), ('source', 0.029991685624632586), ('map', 0.02717043564408782), ('vars', 0.026375367289046527), ('args', 0.026108830690915664)]
        processor.execute(ingestDocument);
        @SuppressWarnings("unchecked")
        Map<String, Object> attachmentData = (Map<String, Object>) ingestDocument.getSourceAndMetadata().get("target_field");
// attachmentData       No	: [('builder', 0.1516369518068288), ('metadata', 0.1431065262918177), ('channel', 0.05433599989453003), ('map', 0.048774924742092045), ('params', 0.04047471134575201), ('config', 0.03552516048936521), ('source', 0.03375782764387456), ('request', 0.029158130287808594), ('result', 0.024839909112455358), ('entry', 0.022127030398800686)]
        return attachmentData;
    }

    public void testIndexedChars() throws Exception {
        processor = new AttachmentProcessor(randomAlphaOfLength(10), "source_field", "target_field", EnumSet.allOf(AttachmentProcessor.Property.class), 19, false, null);
        Map<String, Object> attachmentData = parseDocument("text-in-english.txt", processor);
// attachmentData       1	: [('iw', 0.5008351338709498), ('attachmentData', 0.46724558475411504), ('stats', 0.39605455792122285), ('deleteResponse', 0.2500063630295113), ('metadata', 0.14310652657834486), ('builder', 0.10306640770324908), ('scannedDocs', 0.09896176008763255), ('response', 0.06898306966097294), ('e', 0.05278619037635744), ('map', 0.048774924936406956)]
        assertThat(attachmentData.keySet(), containsInAnyOrder("language", "content", "content_type", "content_length"));
        assertThat(attachmentData.get("language"), is("en"));
        assertThat(attachmentData.get("content"), is("\"God Save the Queen"));
        assertThat(attachmentData.get("content_type").toString(), containsString("text/plain"));
        assertThat(attachmentData.get("content_length"), is(19L));
        processor = new AttachmentProcessor(randomAlphaOfLength(10), "source_field", "target_field", EnumSet.allOf(AttachmentProcessor.Property.class), 19, false, "max_length");
        attachmentData = parseDocument("text-in-english.txt", processor);
        assertThat(attachmentData.keySet(), containsInAnyOrder("language", "content", "content_type", "content_length"));
        assertThat(attachmentData.get("language"), is("en"));
        assertThat(attachmentData.get("content"), is("\"God Save the Queen"));
        assertThat(attachmentData.get("content_type").toString(), containsString("text/plain"));
        assertThat(attachmentData.get("content_length"), is(19L));
        attachmentData = parseDocument("text-in-english.txt", processor, Collections.singletonMap("max_length", 10));
        assertThat(attachmentData.keySet(), containsInAnyOrder("language", "content", "content_type", "content_length"));
        assertThat(attachmentData.get("language"), is("sk"));
        assertThat(attachmentData.get("content"), is("\"God Save"));
        assertThat(attachmentData.get("content_type").toString(), containsString("text/plain"));
        assertThat(attachmentData.get("content_length"), is(10L));
        attachmentData = parseDocument("text-in-english.txt", processor, Collections.singletonMap("max_length", 100));
        assertThat(attachmentData.keySet(), containsInAnyOrder("language", "content", "content_type", "content_length"));
        assertThat(attachmentData.get("language"), is("en"));
        assertThat(attachmentData.get("content"), is("\"God Save the Queen\" (alternatively \"God Save the King\""));
        assertThat(attachmentData.get("content_type").toString(), containsString("text/plain"));
        assertThat(attachmentData.get("content_length"), is(56L));
    }

    private Object getAsBinaryOrBase64(String filename) throws Exception {
// filename             No	: [('name', 0.08144209422538547), ('i', 0.03769487644690186), ('index', 0.026752429496678668), ('id', 0.02388990121758452), ('field', 0.022108201975124267), ('createTime', 0.02195542175957781), ('jobId', 0.021736456740387076), ('source', 0.01967729823471593), ('fieldNames', 0.017383624149279377), ('key', 0.01718514891727035)]
        String path = "/org/elasticsearch/ingest/attachment/test/sample-files/" + filename;
// path                 0	: [('path', 0.30217333391699697), ('mapping', 0.1850792576956236), ('name', 0.1285786132036572), ('resource', 0.12439215495651877), ('json', 0.11068236047507976), ('jobId', 0.07487282941534026), ('resourcePath', 0.04542392064384708), ('url', 0.04147098051163257), ('resourcePathToStore', 0.04146192241536601), ('resourcePathToFile', 0.041461830612632845)]
        try (InputStream is = AttachmentProcessorTests.class.getResourceAsStream(path)) {
// is                   0	: [('is', 0.7651887843221257), ('stream', 0.3426450847782359), ('in', 0.10409332683646348), ('inputStream', 0.07517066983170544), ('input', 0.0440596602968816), ('content', 0.041390336224642325), ('response', 0.02129540388948533), ('actualEntity', 0.020982403007243324), ('executeRepsonse', 0.02098235487384383), ('jar', 0.014192880791680752)]
            byte[] bytes = IOUtils.toByteArray(is);
// bytes                0	: [('bytes', 0.1820114202392405), ('md', 0.1013913680506124), ('resolution', 0.10023263029165562), ('salt', 0.06336984539922394), ('outToken', 0.06336811899960708), ('key', 0.05600275989248795), ('charBytes', 0.05069491484923397), ('input', 0.03957283910423635), ('allocation', 0.03402010717029736), ('buffer', 0.03312090741638473)]
            if (rarely()) {
                return bytes;
            } else {
                return Base64.getEncoder().encodeToString(bytes);
            }
        }
    }
}
