// Type of training: selfTestingIdentifier
// Path to file: C:\Users\Igor\IdeaProjects\SLP-Core-0.3\java-projects\xmlgraphics-batik-trunk\batik-swing\src\main\java\org\apache\batik\swing\svg\SVGUserAgentAdapter.java
// Number of identifiers: 29	Accuracy: 100.00%	MRR: 100.00%
// True 	Rank of true in predictions : [(predicted token, probability of a token), ...]

package org.apache.batik.swing.svg;

import org.apache.batik.util.XMLResourceDescriptor;
import org.apache.batik.bridge.ExternalResourceSecurity;
import org.apache.batik.bridge.RelaxedExternalResourceSecurity;
import org.apache.batik.bridge.RelaxedScriptSecurity;
import org.apache.batik.bridge.ScriptSecurity;
import org.apache.batik.util.ParsedURL;
import org.w3c.dom.Element;

public class SVGUserAgentAdapter implements SVGUserAgent {

    public SVGUserAgentAdapter() {
    }

    public void displayError(String message) {
// message              0	: [('message', 0.8770136154906365), ('e', 0.22023451294911253), ('s', 0.11115582103023407), ('ex', 0.10899176188006773), ('sw', 0.10896940431793033), ('msg', 0.031037550462769986), ('prefix', 0.014567141414352417), ('name', 0.00602529632094221), ('ns', 0.004680661121357834), ('namespaceURI', 0.003607330685505716)]
        System.err.println(message);
    }

    public void displayError(Exception ex) {
// ex                   0	: [('ex', 0.6102135139408894), ('e', 0.34515723332762294), ('report', 0.23484265963882742), ('helpCursor', 0.07817202577271065), ('ctx', 0.0035418338127237866), ('userAgent', 0.0034768386167313525), ('reference', 0.003432460857140882), ('dim', 0.0033868226352441926), ('c', 0.0008013025667844398), ('te', 0.0007825475021425021)]
        ex.printStackTrace();
    }

    public void displayMessage(String message) {
// message              0	: [('message', 0.8770136154906365), ('msg', 0.54103755046277), ('prefix', 0.06751924422135347), ('e', 0.035948798663398264), ('s', 0.03401296388737692), ('code', 0.03183316924350125), ('nde', 0.031832392717368554), ('chars', 0.03182879048149979), ('brkStr', 0.031825242173234396), ('name', 0.00602529632094221)]
        System.out.println(message);
    }

    public void showAlert(String message) {
// message              0	: [('message', 0.8770136154906365), ('e', 0.22023451294911253), ('s', 0.11115582103023407), ('ex', 0.10899176188006773), ('sw', 0.10896940431793033), ('msg', 0.031037550462769986), ('prefix', 0.014567141414352417), ('name', 0.00602529632094221), ('ns', 0.004680661121357834), ('namespaceURI', 0.003607330685505716)]
        System.err.println(message);
    }

    public String showPrompt(String message) {
// message              0	: [('message', 0.8770136929955351), ('prefix', 0.014567483115246907), ('name', 0.006025474525655659), ('s', 0.005275242232128155), ('ns', 0.0046808368731603555), ('namespaceURI', 0.003607442494395505), ('uri', 0.0031009368019167508), ('key', 0.002472388814134722), ('text', 0.0017858609593402787), ('value', 0.0017778186816195306)]
        return "";
    }

    public String showPrompt(String message, String defaultValue) {
// message              0	: [('message', 0.8770136929955351), ('prefix', 0.014567483115246907), ('name', 0.006025474525655659), ('s', 0.005275242232128155), ('ns', 0.0046808368731603555), ('namespaceURI', 0.003607442494395505), ('uri', 0.0031009368019167508), ('key', 0.002472388814134722), ('text', 0.0017858609593402787), ('value', 0.0017778186816195306)]
// defaultValue         0	: [('defaultValue', 0.5473960079370396), ('defVal', 0.3282406759306685), ('n', 0.02774671238151037), ('x', 0.024927572656572), ('y', 0.020817810515299535), ('value', 0.019563327093650818), ('e', 0.015062213325116895), ('c', 0.01290937256686518), ('f', 0.012821134571001785), ('i', 0.011632953596088947)]
        return defaultValue;
    }

    public boolean showConfirm(String message) {
// message              0	: [('message', 0.8770136370332927), ('prefix', 0.014567141192095567), ('name', 0.006025296184443628), ('s', 0.005274944586794974), ('ns', 0.004680660991827113), ('namespaceURI', 0.003607330569910714), ('uri', 0.0031008095031895434), ('key', 0.0024723064081953704), ('text', 0.0017858043821281863), ('value', 0.0017777258211953357)]
        return false;
    }

    public float getPixelUnitToMillimeter() {
        return 0.26458333333333333333333333333333f;
    }

    public float getPixelToMM() {
        return getPixelUnitToMillimeter();
    }

    public String getDefaultFontFamily() {
        return "Serif";
    }

    public float getMediumFontSize() {
        return 9f * 25.4f / (72f * getPixelUnitToMillimeter());
    }

    public float getLighterFontWeight(float f) {
// f                    0	: [('f', 0.8779215396524208), ('base', 0.1617303673235345), ('numEntries', 0.12818832147701578), ('evt', 0.12812707589760672), ('dt', 0.06409213228307364), ('delay', 0.06407740035771743), ('wmfFile', 0.06407679638794492), ('kk1', 0.056181371922169696), ('delta', 0.055141585903001306), ('srcP', 0.05475054460084702)]
        int weight = ((int) ((f + 50) / 100)) * 100;
// weight               0	: [('weight', 0.7565360466613786), ('type', 0.01736609712711753), ('lu', 0.015303188790104649), ('s', 0.014386746190847146), ('i', 0.014231285852671981), ('len', 0.012543692691342692), ('idx', 0.01172959193910133), ('c', 0.010251154343626024), ('primitiveType', 0.007393521215450125), ('hash', 0.007222747346269908)]
        switch(weight) {
            case 100:
                return 100;
            case 200:
                return 100;
            case 300:
                return 200;
            case 400:
                return 300;
            case 500:
                return 400;
            case 600:
                return 400;
            case 700:
                return 400;
            case 800:
                return 400;
            case 900:
                return 400;
            default:
                throw new IllegalArgumentException("Bad Font Weight: " + f);
        }
    }

    public float getBolderFontWeight(float f) {
// f                    0	: [('f', 0.8779215396524208), ('base', 0.1617303673235345), ('numEntries', 0.12818832147701578), ('evt', 0.12812707589760672), ('dt', 0.06409213228307364), ('delay', 0.06407740035771743), ('wmfFile', 0.06407679638794492), ('kk1', 0.056181371922169696), ('delta', 0.055141585903001306), ('srcP', 0.05475054460084702)]
        int weight = ((int) ((f + 50) / 100)) * 100;
// weight               0	: [('weight', 0.7565360466613786), ('type', 0.01736609712711753), ('lu', 0.015303188790104649), ('s', 0.014386746190847146), ('i', 0.014231285852671981), ('len', 0.012543692691342692), ('idx', 0.01172959193910133), ('c', 0.010251154343626024), ('primitiveType', 0.007393521215450125), ('hash', 0.007222747346269908)]
        switch(weight) {
            case 100:
                return 600;
            case 200:
                return 600;
            case 300:
                return 600;
            case 400:
                return 600;
            case 500:
                return 600;
            case 600:
                return 700;
            case 700:
                return 800;
            case 800:
                return 900;
            case 900:
                return 900;
            default:
                throw new IllegalArgumentException("Bad Font Weight: " + f);
        }
    }

    public String getLanguages() {
        return "en";
    }

    public String getUserStyleSheetURI() {
        return null;
    }

    public String getXMLParserClassName() {
        return XMLResourceDescriptor.getXMLParserClassName();
    }

    public boolean isXMLParserValidating() {
        return false;
    }

    public String getMedia() {
        return "screen";
    }

    public String getAlternateStyleSheet() {
        return null;
    }

    public void openLink(String uri, boolean newc) {
// uri                  0	: [('uri', 0.44057272121586166), ('url', 0.4380624307883463), ('prefix', 0.014567141192095567), ('name', 0.006025296184443628), ('s', 0.005274944586794974), ('ns', 0.004680660991827113), ('namespaceURI', 0.003607330569910714), ('key', 0.0024723064081953704), ('message', 0.0020417253206205634), ('text', 0.0017858043821281863)]
// newc                 0	: [('newc', 0.8752151476622756), ('canBubbleArg', 0.006212288900594967), ('cancelableArg', 0.005997840282185661), ('isCSS', 0.004958779144742542), ('sweepFlag', 0.0037700155487202253), ('largeArcFlag', 0.0037700155487202253), ('b', 0.0037223230016374836), ('isBegin', 0.003155209974542954), ('useCapture', 0.0026943602726472323), ('specified', 0.0025712352258749375)]
    }

    public boolean supportExtension(String s) {
// s                    0	: [('s', 0.8802468562994671), ('prefix', 0.014567141192095567), ('name', 0.006025296184443628), ('ns', 0.004680660991827113), ('namespaceURI', 0.003607330569910714), ('uri', 0.0031008095031895434), ('key', 0.0024723064081953704), ('message', 0.0020417253206205634), ('text', 0.0017858043821281863), ('value', 0.0017777258211953357)]
        return false;
    }

    public void handleElement(Element elt, Object data) {
// elt                  0	: [('elt', 0.8843454577243883), ('e', 0.022827965197846103), ('filterElement', 0.00683331733380688), ('element', 0.005819156644857232), ('imageElement', 0.002294576789073654), ('paintElement', 0.001368207505671528), ('imp', 0.0011935232767632039), ('elem', 0.0011885992576775555), ('contextElement', 0.0011502758648642877), ('filteredElement', 0.001069069367762506)]
// data                 0	: [('data', 0.8800770469130135), ('event', 0.009112610478106044), ('value', 0.005700528953108538), ('o2', 0.004401412277995846), ('o', 0.004157872445752007), ('group', 0.0019053962577678509), ('o3', 0.0015708899339833458), ('listener', 0.0012733069835558911), ('key', 0.0010541041692189075), ('obj', 0.0009938096983315142)]
    }

    public ScriptSecurity getScriptSecurity(String scriptType, ParsedURL scriptURL, ParsedURL docURL) {
// scriptType           0	: [('scriptType', 0.9312809405642706), ('prefix', 0.014567141414352417), ('name', 0.00602529632094221), ('s', 0.005274944999863977), ('ns', 0.004680661121357834), ('namespaceURI', 0.003607330685505716), ('uri', 0.0031008096159259365), ('key', 0.002472306515750535), ('message', 0.002041725348313338), ('text', 0.0017858044478761938)]
// scriptURL            0	: [('scriptURL', 0.7299274354764558), ('scriptPURL', 0.4323029467440359), ('docURL', 0.020589934143371177), ('uri', 0.013569157837814224), ('purl', 0.01292277996231149), ('origURL', 0.004220697128543613), ('docPURL', 0.0031038359250324383), ('resourceURL', 0.002509709301326192), ('key', 0.0014204365938964136), ('base', 0.001287955741121564)]
// docURL               0	: [('docURL', 0.9021967028321931), ('scriptURL', 0.019158620413384064), ('uri', 0.013688459270134158), ('purl', 0.01300347099693424), ('origURL', 0.0042581826700832295), ('scriptPURL', 0.003548774626719232), ('docPURL', 0.003129722112225573), ('resourceURL', 0.002517473128104467), ('key', 0.001432931774409619), ('base', 0.0012959949837116145)]
        return new RelaxedScriptSecurity(scriptType, scriptURL, docURL);
    }

    public void checkLoadScript(String scriptType, ParsedURL scriptURL, ParsedURL docURL) throws SecurityException {
// scriptType           0	: [('scriptType', 0.8990694021027321), ('prefix', 0.014567141414352417), ('name', 0.00602529632094221), ('s', 0.005274923429693625), ('ns', 0.004680661121357834), ('namespaceURI', 0.003607330685505716), ('uri', 0.0031008096159259365), ('key', 0.002472306515750535), ('message', 0.002041725348313338), ('text', 0.0017858044478761938)]
// scriptURL            0	: [('scriptURL', 0.8593963207994688), ('scriptPURL', 0.16758003667543622), ('docURL', 0.020589934143371177), ('uri', 0.013569157837814224), ('purl', 0.01292277996231149), ('origURL', 0.004220697128543613), ('docPURL', 0.0031038359250324383), ('resourceURL', 0.002509709301326192), ('key', 0.0014204365938964136), ('base', 0.001287955741121564)]
// docURL               0	: [('docURL', 0.9021967028321931), ('scriptURL', 0.019158620413384064), ('uri', 0.013688459270134158), ('purl', 0.01300347099693424), ('origURL', 0.0042581826700832295), ('scriptPURL', 0.003548774626719232), ('docPURL', 0.003129722112225573), ('resourceURL', 0.002517473128104467), ('key', 0.001432931774409619), ('base', 0.0012959949837116145)]
        ScriptSecurity s = getScriptSecurity(scriptType, scriptURL, docURL);
// s                    0	: [('s', 0.9167174089159681), ('paintedArea', 0.12599411280657294), ('b', 0.06392835604259492), ('result', 0.01773232834985175), ('scriptSecurity', 0.017709550061288108), ('n', 0.009209524362418547), ('i', 0.008385174505300519), ('g2d', 0.0070419764382906695), ('v', 0.006281016282792694), ('t', 0.005740243606390893)]
        if (s != null) {
            s.checkLoadScript();
        }
    }

    public ExternalResourceSecurity getExternalResourceSecurity(ParsedURL resourceURL, ParsedURL docURL) {
// resourceURL          0	: [('resourceURL', 0.6618163386976448), ('resourcePURL', 0.31105087367734496), ('purl', 0.030622424431065765), ('externalResourceURL', 0.004884921403275467), ('url', 0.004588477117382239), ('uri', 0.0038005950045036816), ('docURL', 0.0029928467647304005), ('scriptURL', 0.0027029340884493817), ('base', 0.0024417230048984), ('u', 0.0010758664221035047)]
// docURL               0	: [('docURL', 0.9018085029160557), ('scriptURL', 0.01970158639480806), ('uri', 0.013575428620981293), ('purl', 0.012943981181590625), ('origURL', 0.004222488780877061), ('scriptPURL', 0.0035190297190474252), ('docPURL', 0.0031059261860881277), ('resourceURL', 0.00242068076175757), ('key', 0.0014210338113408963), ('base', 0.0012900460021772534)]
        return new RelaxedExternalResourceSecurity(resourceURL, docURL);
    }

    public void checkLoadExternalResource(ParsedURL resourceURL, ParsedURL docURL) throws SecurityException {
// resourceURL          0	: [('resourceURL', 0.8994202577972226), ('purl', 0.030622424431065765), ('externalResourceURL', 0.004884921403275467), ('url', 0.004588477117382239), ('resourcePURL', 0.003908016534487851), ('uri', 0.0038005950045036816), ('docURL', 0.0029928467647304005), ('scriptURL', 0.0027029340884493817), ('base', 0.0024417230048984), ('u', 0.0010758664221035047)]
// docURL               0	: [('docURL', 0.9018085029160557), ('scriptURL', 0.01970158639480806), ('uri', 0.013575428620981293), ('purl', 0.012943981181590625), ('origURL', 0.004222488780877061), ('scriptPURL', 0.0035190297190474252), ('docPURL', 0.0031059261860881277), ('resourceURL', 0.00242068076175757), ('key', 0.0014210338113408963), ('base', 0.0012900460021772534)]
        ExternalResourceSecurity s = getExternalResourceSecurity(resourceURL, docURL);
// s                    0	: [('s', 0.9495699730185323), ('paintedArea', 0.12599411280657294), ('b', 0.06392835604259492), ('n', 0.009209524362418547), ('result', 0.008412140714118114), ('i', 0.008385174505300519), ('g2d', 0.0070419764382906695), ('v', 0.006281016282792694), ('t', 0.005740243606390893), ('idx', 0.005067414276934782)]
        if (s != null) {
            s.checkLoadExternalResource();
        }
    }
}
