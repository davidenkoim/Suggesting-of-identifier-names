// Type of training: selfTestingIdentifier
// Path to file: C:\Users\Igor\IdeaProjects\SLP-Core-0.3\java-projects\xmlgraphics-batik-trunk\batik-awt-util\src\main\java\org\apache\batik\ext\awt\image\renderable\TileRable8Bit.java
// Number of identifiers: 106	Accuracy: 48.11%	MRR: 53.74%
// True 	Rank of true in predictions : [(predicted token, probability of a token), ...]

package org.apache.batik.ext.awt.image.renderable;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderContext;
import org.apache.batik.ext.awt.RenderingHintsKeyExt;
import org.apache.batik.ext.awt.image.GraphicsUtil;
import org.apache.batik.ext.awt.image.rendered.AffineRed;
import org.apache.batik.ext.awt.image.rendered.BufferedImageCachableRed;
import org.apache.batik.ext.awt.image.rendered.CachableRed;
import org.apache.batik.ext.awt.image.rendered.TileRed;

public class TileRable8Bit extends AbstractColorInterpolationRable implements TileRable {

    private Rectangle2D tileRegion;

    private Rectangle2D tiledRegion;

    private boolean overflow;

    public Rectangle2D getTileRegion() {
        return tileRegion;
    }

    public void setTileRegion(Rectangle2D tileRegion) {
// tileRegion           0	: [('tileRegion', 0.7520611302921925), ('n', 0.05182696230637811), ('r', 0.03218995235754341), ('node', 0.02093327926614834), ('i', 0.01702206788770666), ('e', 0.016360095988844674), ('filterRegion', 0.01178950840250629), ('c', 0.010795889380819667), ('value', 0.010711248644393566), ('s', 0.01031225587520802)]
        if (tileRegion == null) {
            throw new IllegalArgumentException();
        }
        touch();
        this.tileRegion = tileRegion;
    }

    public Rectangle2D getTiledRegion() {
        return tiledRegion;
    }

    public void setTiledRegion(Rectangle2D tiledRegion) {
// tiledRegion          0	: [('tiledRegion', 0.8750054306023489), ('n', 0.05182696230637811), ('r', 0.03218995235754341), ('node', 0.02093327926614834), ('i', 0.01702206788770666), ('e', 0.016360095988844674), ('filterRegion', 0.01178950840250629), ('c', 0.010795889380819667), ('value', 0.010711248644393566), ('s', 0.01031225587520802)]
        if (tiledRegion == null) {
            throw new IllegalArgumentException();
        }
        touch();
        this.tiledRegion = tiledRegion;
    }

    public boolean isOverflow() {
        return overflow;
    }

    public void setOverflow(boolean overflow) {
// overflow             0	: [('overflow', 0.8906276290153117), ('b', 0.01749057856905382), ('v', 0.014356145419429433), ('aSecure', 0.007460989614366316), ('useSVG11AccessKeys', 0.005038680397783792), ('mirror', 0.004352876806879774), ('state', 0.0037333077256000314), ('stillActive', 0.003731236989246184), ('sweepFlag', 0.0037031864154536717), ('largeArcFlag', 0.0037031864154536717)]
        touch();
        this.overflow = overflow;
    }

    public TileRable8Bit(Filter source, Rectangle2D tiledRegion, Rectangle2D tileRegion, boolean overflow) {
// source               5	: [('src', 0.40834080137849355), ('prefix', 0.23901393704395496), ('target', 0.07483615958526198), ('filter', 0.05270526380144952), ('srcCM', 0.04636146610467278), ('source', 0.03465837635034633), ('generatorContext', 0.032559277206646635), ('inputFilter', 0.03109035308653657), ('elt', 0.027607660605224855), ('in', 0.026683199186633436)]
// tiledRegion          No	: [('filterRegion', 0.9024816498031629), ('bounds', 0.007503037048848237), ('userBounds', 0.006798719531403494), ('i', 0.006721359700508813), ('defaultRegion', 0.004680181016604306), ('inputRgn', 0.004574586719549107), ('outputRgn', 0.004533251955768584), ('e', 0.0038202496723848717), ('ctx', 0.0037903115127472004), ('n', 0.0034699488516274257)]
// tileRegion           No	: [('filterRegion', 0.22158631946762947), ('bounds', 0.06037395461133856), ('userBounds', 0.054822274058994425), ('defaultRegion', 0.03767121334372737), ('inputRgn', 0.0368854746488934), ('outputRgn', 0.03655436085132632), ('primitiveRegion', 0.024233208774928388), ('rect', 0.022955517713984474), ('litRegion', 0.019606243677130365), ('patternRegion', 0.018611349232163753)]
// overflow             No	: [('canBubbleArg', 0.04969831135912462), ('cancelableArg', 0.0479827223889813), ('isCSS', 0.03967023326085026), ('sweepFlag', 0.03016012450553547), ('largeArcFlag', 0.03016012450553547), ('b', 0.029778585798301203), ('isBegin', 0.02524167992355173), ('useCapture', 0.021554882258360303), ('specified', 0.020569881875606117), ('important', 0.012552734286961496)]
        super(source);
        setTileRegion(tileRegion);
        setTiledRegion(tiledRegion);
        setOverflow(overflow);
    }

    public void setSource(Filter src) {
// src                  0	: [('src', 0.8685282048404989), ('srcs', 0.08969242052385229), ('chainSource', 0.04579671106643114), ('documentURL', 0.02753528717308735), ('source', 0.01655750283197854), ('filter', 0.00658815797518119), ('inputFilter', 0.0038862941358170713), ('in', 0.0033353998983291794), ('cr', 0.0017106535834941598), ('image', 0.0017073856961532883)]
        init(src);
    }

    public Filter getSource() {
        return (Filter) srcs.get(0);
    }

    public Rectangle2D getBounds2D() {
        return (Rectangle2D) tiledRegion.clone();
    }

    public RenderedImage createRendering(RenderContext rc) {
// rc                   0	: [('rc', 0.9241647541117668), ('renderContext', 0.11321913513165931), ('gn', 0.1115590816142568), ('clipNode', 0.06901929424790576), ('context', 0.03745068855353777), ('node2dev', 0.01995443712842803), ('tn', 0.01902797293137464), ('childGN', 0.01902055563035757), ('node', 0.013520433744429131), ('g2d', 0.012982428122626205)]
        RenderingHints rh = rc.getRenderingHints();
// rh                   0	: [('rh', 0.9309506801961773), ('b', 0.3505687565687824), ('wr', 0.1754468337071762), ('dst', 0.17510056416936293), ('s', 0.02778187596462928), ('hints', 0.023638273142021927), ('n', 0.021213720035973157), ('ln', 0.017647910295215147), ('aci', 0.01267239342481129), ('token', 0.012327360870380685)]
        if (rh == null)
            rh = new RenderingHints(null);
        AffineTransform at = rc.getTransform();
// at                   0	: [('at', 0.9376712391886669), ('t', 0.14329513989483533), ('dim', 0.1430656891371658), ('af', 0.1294628659565681), ('pt0', 0.12868098690043842), ('canvasSize', 0.10139452609932907), ('tileGridYOff', 0.018392249241085887), ('tileGridXOff', 0.014891107533626342), ('tr', 0.014274480891010976), ('scrnTrans', 0.011953831261465044)]
        double sx = at.getScaleX();
// sx                   0	: [('sx', 0.9230533986868724), ('dx', 0.14230819680359688), ('sy', 0.13551233506444318), ('scx', 0.10240592713647498), ('txf', 0.06949655444773457), ('currentToFocusSq', 0.06460400816760001), ('n', 0.05964003625934288), ('at', 0.05907272892843415), ('usr2dev', 0.05240028452881546), ('w', 0.04532514115678093)]
        double sy = at.getScaleY();
// sy                   0	: [('sy', 0.9230623601138739), ('dx', 0.14230819638462963), ('sx', 0.13555092450373143), ('tx', 0.08207662104470156), ('currentToFocusSq', 0.06460400812230625), ('scaleY', 0.06399775594114308), ('n', 0.05964003151482156), ('sinTheta', 0.058397257750845054), ('w', 0.04532514042641905), ('at', 0.0355830802191222)]
        double shx = at.getShearX();
// shx                  0	: [('shx', 0.9376078349579745), ('tdx', 0.07561366976176347), ('shy', 0.06070544299872587), ('sy', 0.02923607003839163), ('sx', 0.025328578630259214), ('ty', 0.02520335486521811), ('tx', 0.023306130030020595), ('y', 0.021482093738940423), ('r', 0.020418091166851948), ('scaleY', 0.018467180395519648)]
        double shy = at.getShearY();
// shy                  0	: [('shy', 0.9376393782305197), ('sy', 0.13516217993400167), ('shx', 0.06070373628566563), ('sx', 0.025328492348148463), ('ty', 0.02520335486521811), ('tx', 0.023306130030020595), ('y', 0.021482093738940423), ('r', 0.020418086665383313), ('scaleY', 0.018467179816383333), ('x', 0.015274980481036681)]
        double tx = at.getTranslateX();
// tx                   0	: [('tx', 0.8252374355351915), ('sy', 0.08210637962736175), ('sx', 0.02532857853806678), ('ty', 0.025203354817335218), ('y', 0.021482092581175), ('shx', 0.019956843165734185), ('shy', 0.019921742879450536), ('x', 0.015274979312551208), ('scaleX', 0.014694974593903736), ('width', 0.013916274783218866)]
        double ty = at.getTranslateY();
// ty                   0	: [('ty', 0.9195175882952455), ('sy', 0.029236134683867713), ('sx', 0.02532857853806678), ('tx', 0.023306108391241923), ('y', 0.021482092581175), ('shx', 0.019956843165734185), ('shy', 0.019921742879450536), ('x', 0.015274979312551208), ('scaleX', 0.014694974593903736), ('width', 0.013916274783218866)]
        double scaleX = Math.sqrt(sx * sx + shy * shy);
// scaleX               0	: [('scaleX', 0.9112767485231914), ('workTileWidth', 0.22933008452278889), ('sx', 0.18601053913155566), ('scx', 0.1555367465380595), ('atScaleX', 0.15025053026791949), ('baseFrequencyX', 0.14846790307311727), ('sf', 0.10133308120268605), ('bounds', 0.07834630606578669), ('srcSM', 0.07412017496745257), ('histRI', 0.07411905329072188)]
        double scaleY = Math.sqrt(sy * sy + shx * shx);
// scaleY               0	: [('scaleY', 0.9130937596231888), ('workTileHeight', 0.278018752451203), ('baseFrequencyY', 0.18356888424664453), ('atScaleY', 0.12543722906650462), ('tileScaleY', 0.12501203690830026), ('scaleFactor', 0.10442786769047782), ('scy', 0.09885400155810617), ('bounds', 0.09589683954415149), ('scale', 0.041694979123934446), ('norm', 0.014363543437426038)]
        Rectangle2D tiledRect = getBounds2D();
// tiledRect            0	: [('tiledRect', 0.8958375730747954), ('aoi', 0.2502001621256727), ('aoiShape', 0.2500271536319217), ('srect', 0.1974142470227469), ('aoiR', 0.13899083219962952), ('bounds', 0.10552499740971906), ('rect', 0.09876673282412292), ('r', 0.06365484440762047), ('primitiveRegion', 0.06198589677221117), ('r2d', 0.0581931621583931)]
        Rectangle2D aoiRect;
// aoiRect              0	: [('aoiRect', 0.9375051682181385), ('primitiveRegion', 0.25803141168826466), ('aoiR', 0.10478614852739022), ('bounds', 0.0832632555695097), ('rect', 0.0772498222693491), ('in', 0.07173003343792737), ('r', 0.056853251093406096), ('s', 0.05683207736768697), ('aoi', 0.04754775136857989), ('x', 0.03666695940847252)]
        Shape aoiShape = rc.getAreaOfInterest();
// aoiShape             2	: [('aoi', 0.7044685905124521), ('tiledRect', 0.12508562522581027), ('aoiShape', 0.1250175809023843), ('s', 0.02778186243355746), ('n', 0.021213704472028905), ('ln', 0.01764790922545487), ('clip', 0.014585905899918343), ('aci', 0.012672391273573964), ('token', 0.01232736029648159), ('o', 0.012147391708589188)]
        if (aoiShape == null)
            aoiRect = tiledRect;
        else {
            aoiRect = aoiShape.getBounds2D();
            if (!tiledRect.intersects(aoiRect))
                return null;
            Rectangle2D.intersect(tiledRect, aoiRect, tiledRect);
        }
        Rectangle2D tileRect = tileRegion;
// tileRect             No	: [('gnDevX1', 0.2735616217022433), ('gnDevY1', 0.2735616001306435), ('p', 0.2498813339793136), ('defaultRegion', 0.1506768889840527), ('bounds', 0.12495524320370414), ('image', 0.0943028498133579), ('r', 0.08463245731922145), ('wr', 0.08448844157767356), ('source', 0.08364143091017628), ('srcRect', 0.08347926486131485)]
        int dw = (int) (Math.ceil(tileRect.getWidth() * scaleX));
// dw                   0	: [('dw', 0.7500280229658867), ('i', 0.06644176614700986), ('len', 0.022922801827916298), ('index', 0.018508237298290795), ('w', 0.01585053754637217), ('n', 0.015192589499625422), ('x', 0.014868564561904943), ('idx', 0.014742579186690337), ('end', 0.014255343209452447), ('y', 0.012913907582878836)]
        int dh = (int) (Math.ceil(tileRect.getHeight() * scaleY));
// dh                   0	: [('dh', 0.7500187945648401), ('region', 0.12504321897609608), ('i', 0.05106381884727263), ('w', 0.050908281332239985), ('n', 0.04961000747248074), ('len', 0.04793162122932842), ('choice', 0.03930882273458722), ('b', 0.021213421745210608), ('numGlyphs', 0.02120851811285837), ('g', 0.020542405896930314)]
        double tileScaleX = dw / tileRect.getWidth();
// tileScaleX           0	: [('tileScaleX', 0.7503360005015817), ('scx', 0.38517604029205976), ('bounds', 0.3806723627435415), ('sx', 0.19039947778936786), ('maxX', 0.16928633895486633), ('dy', 0.11706055827948023), ('sf', 0.10372608293779306), ('dx', 0.0644421161530938), ('cx1', 0.05730221092273918), ('deltaY', 0.05666646687993203)]
        double tileScaleY = dh / tileRect.getHeight();
// tileScaleY           0	: [('tileScaleY', 0.5003407037761227), ('scaleY', 0.5001890311819664), ('scy', 0.38365652655519833), ('bounds', 0.3806723627435415), ('sy', 0.10066470621160042), ('tileHeight', 0.07711179448295688), ('imageHeight', 0.0771114709223615), ('tiledHeight', 0.07711110422035337), ('y', 0.05719638003212323), ('h', 0.0492697217908898)]
        int dx = (int) Math.floor(tileRect.getX() * tileScaleX);
// dx                   0	: [('dx', 0.5003709347960104), ('y', 0.116328914977619), ('height', 0.06097260342885879), ('x1', 0.06018767317737915), ('b', 0.05885282979818341), ('minX', 0.057579853420332165), ('iw', 0.05642106538789239), ('leftAndRight', 0.056272067129920875), ('kx', 0.05627204555975052), ('len', 0.025768876774471476)]
        int dy = (int) Math.floor(tileRect.getY() * tileScaleY);
// dy                   0	: [('dy', 0.5002438194172089), ('i', 0.04758217474321164), ('len', 0.041317787382921536), ('w', 0.02908928908389832), ('n', 0.02649273955699433), ('y0', 0.018036260997490165), ('idx', 0.01795329940351853), ('val', 0.016528385172940953), ('h', 0.016229695056303034), ('length', 0.015696096206138325)]
        double ttx = dx - (tileRect.getX() * tileScaleX);
// ttx                  No	: [('x', 0.3702968894299814), ('vb', 0.2649092341383296), ('tx', 0.23189134047362353), ('dx', 0.11792326035454365), ('gpos', 0.09300172762102238), ('y', 0.04655829390005419), ('minX', 0.04009228130953865), ('wr', 0.03860553474217584), ('r', 0.03832469040274986), ('sy', 0.03820438138736876)]
        double tty = dy - (tileRect.getY() * tileScaleY);
// tty                  0	: [('tty', 0.5000156093728481), ('vb', 0.055991568904244275), ('y', 0.04655829390005419), ('sy', 0.03820438138736876), ('x', 0.037477872343061935), ('width', 0.03204305257628783), ('sx', 0.030448172604366996), ('tx', 0.030232393998612637), ('ty', 0.0299472776328224), ('height', 0.028534677768398846)]
        AffineTransform tileAt;
// tileAt               No	: [('at', 0.2805092292239332), ('srcAt', 0.16840244943651447), ('usr2dev', 0.12268715700889891), ('tr', 0.05752177035529812), ('ATxf', 0.056758962834817545), ('BTxf', 0.056758962834817545), ('CTxf', 0.056758962834817545), ('DTxf', 0.056758962834817545), ('scrnTrans', 0.04818267658590203), ('resUsr2Dev', 0.04761169385890158)]
        tileAt = AffineTransform.getTranslateInstance(ttx, tty);
        tileAt.scale(tileScaleX, tileScaleY);
        Filter source = getSource();
// source               No	: [('filter', 0.33729611873732224), ('tileRect', 0.16675125913960648), ('in', 0.1221778066899744), ('r', 0.0846323310306182), ('wr', 0.08448832318025865), ('srcRect', 0.08347925087732519), ('filt', 0.07663331908093156), ('src', 0.058217336269096594), ('f', 0.047348380851746655), ('ret', 0.041676119344022114)]
        Rectangle2D srcRect;
// srcRect              0	: [('srcRect', 0.750079142828481), ('r', 0.531853250519507), ('bounds', 0.45603584019111415), ('ret', 0.1668150836975881), ('primitiveRegion', 0.05803141166575179), ('at', 0.05000178563742705), ('fSum', 0.032220491863562094), ('aoiR', 0.029786148503090634), ('rect', 0.027249822157142178), ('r2d', 0.02517011739664028)]
        if (overflow)
            srcRect = source.getBounds2D();
        else
            srcRect = tileRect;
        RenderContext tileRc = new RenderContext(tileAt, srcRect, rh);
// tileRc               No	: [('rc', 0.2645219444420462), ('arc', 0.19374024453948116), ('crc', 0.19373842187053816), ('brc', 0.1937343667063833), ('drc', 0.1937343667063833), ('srcRc', 0.05234549215916583), ('newRC', 0.052344801913714545), ('srcRC', 0.05234462935235172), ('renderContext', 0.014028943079359773), ('context', 0.006242272128526448)]
        RenderedImage tileRed = source.createRendering(tileRc);
// tileRed              1	: [('tmpR2', 0.5627249849739426), ('tileRed', 0.5500092241802991), ('ri', 0.45843928003833206), ('mapRed', 0.38541779711608404), ('displacedRed', 0.3854177108332586), ('srcRI', 0.08335351536974908), ('gi', 0.0626671108578075), ('shapeNode', 0.06257198792427505), ('s', 0.034686908266826216), ('red', 0.02118323909583087)]
        if (tileRed == null)
            return null;
        Rectangle tiledArea = tileAt.createTransformedShape(aoiRect).getBounds();
// tiledArea            0	: [('tiledArea', 0.7500113462244945), ('charVal', 0.08212734694385918), ('i', 0.07189727390170514), ('rgb', 0.05420048913064473), ('args', 0.045036967379501386), ('r', 0.0394732021491795), ('ch1', 0.03560220421954319), ('bounds', 0.029444652058512776), ('srcR', 0.024428153933758953), ('rect', 0.02193184419827631)]
        if ((tiledArea.width == Integer.MAX_VALUE) || (tiledArea.height == Integer.MAX_VALUE)) {
            tiledArea = new Rectangle(Integer.MIN_VALUE / 4, Integer.MIN_VALUE / 4, Integer.MAX_VALUE / 2, Integer.MAX_VALUE / 2);
        }
        tileRed = convertSourceCS(tileRed);
        TileRed tiledRed = new TileRed(tileRed, tiledArea, dw, dh);
// tiledRed             3	: [('cr', 0.9247369263069521), ('rects', 0.010128285185865124), ('tmpRect', 0.010013145725530874), ('tiledRed', 0.009705832774582401), ('tr', 0.005146609481752508), ('ar', 0.005129230080910571), ('comp', 0.005064289591846141), ('i', 0.0020102337329637), ('e', 0.001923005866727085), ('n', 0.001633554709597026)]
        AffineTransform shearAt = new AffineTransform(sx / scaleX, shy / scaleX, shx / scaleY, sy / scaleY, tx, ty);
// shearAt              0	: [('shearAt', 0.750023180150008), ('at', 0.29977185941172196), ('outsetX', 0.29961303061517164), ('tr', 0.057097923373932234), ('scrnTrans', 0.047815324974389624), ('glyphTransform', 0.04066686292819465), ('ati', 0.030250254460139653), ('rect', 0.028404533711412362), ('xform', 0.025220564241530852), ('t', 0.02190372289979048)]
        shearAt.scale(scaleX / tileScaleX, scaleY / tileScaleY);
        shearAt.translate(-ttx, -tty);
        CachableRed cr = tiledRed;
// cr                   0	: [('cr', 0.601563975370325), ('ret', 0.1601690777218825), ('src', 0.1577783651430366), ('alphaRed', 0.06438842440753179), ('report', 0.02090388654896384), ('result', 0.008625670431096698), ('maskCr', 0.0071957828457147), ('srcCR', 0.007195696564318625), ('n', 0.007023590214969372), ('v', 0.0064446539021739005)]
        if (!shearAt.isIdentity())
            cr = new AffineRed(tiledRed, shearAt, rh);
        return cr;
    }

    public Rectangle2D getActualTileBounds(Rectangle2D tiledRect) {
// tiledRect            0	: [('tiledRect', 0.9375083318025456), ('r', 0.1287763701395598), ('cmp', 0.0723002013353711), ('a', 0.06925507954919752), ('h', 0.06921087067133967), ('tv', 0.06916841967278291), ('tileRect', 0.06916737349613755), ('filterRegion', 0.04718199682729976), ('bounds', 0.03673111759559745), ('th', 0.03459354351142599)]
        Rectangle2D tileRect = (Rectangle2D) tileRegion.clone();
// tileRect             0	: [('tileRect', 0.8958414925770177), ('x', 0.17883628254876394), ('tiledRect', 0.1314598084083405), ('r2d', 0.12267142126682314), ('s', 0.11837792180245249), ('tileX', 0.10586956827566582), ('bounds', 0.09986885028039316), ('r', 0.07502342243636095), ('p', 0.07259413077094615), ('i', 0.07189728598621473)]
        if ((tileRect.getWidth() <= 0) || (tileRect.getHeight() <= 0) || (tiledRect.getWidth() <= 0) || (tiledRect.getHeight() <= 0))
            return null;
        double tileWidth = tileRect.getWidth();
// tileWidth            7	: [('tileX', 0.4002382738701557), ('t', 0.38888874410947916), ('decorationRect', 0.13823366246904598), ('sx', 0.1026307734736463), ('tileHeight', 0.10256861950507372), ('srcRect', 0.09215518835002422), ('len', 0.08098640853926017), ('tileWidth', 0.056495322171193706), ('i', 0.052122213363925), ('sX', 0.05128393667617513)]
        double tileHeight = tileRect.getHeight();
// tileHeight           9	: [('decorationRect', 0.13823366261058134), ('sx', 0.10263077428322866), ('tileWidth', 0.10256950392038164), ('sy', 0.10066470611243913), ('srcRect', 0.09215518840663836), ('len', 0.08098640907709462), ('imageHeight', 0.07711147090467327), ('tiledHeight', 0.07711110421177728), ('y', 0.05719637829546071), ('tileHeight', 0.056494437829484184)]
        double tiledWidth = tiledRect.getWidth();
// tiledWidth           0	: [('tiledWidth', 0.8281363013579714), ('w', 0.17908257275647987), ('sx', 0.10866191187140012), ('tiledX', 0.0861751865176713), ('scale', 0.08601927783140566), ('s', 0.08595923118995441), ('tileHeight', 0.07812790023206248), ('sy', 0.029236134683867713), ('ty', 0.025203354817335218), ('tx', 0.023306129961412275)]
        double tiledHeight = tiledRect.getHeight();
// tiledHeight          0	: [('tiledHeight', 0.9375112366474604), ('sy', 0.10066470611243913), ('tileHeight', 0.0771117728870582), ('imageHeight', 0.07711147090467327), ('y', 0.05719637829546071), ('h', 0.0492697213127716), ('height', 0.048753466836804184), ('scaleY', 0.04415650761001131), ('tileScaleY', 0.03857360276108408), ('highFreq', 0.038119997142267784)]
        double w = Math.min(tileWidth, tiledWidth);
// w                    2	: [('sx', 0.10866191187140012), ('r', 0.09421282647748115), ('w', 0.0933446368701235), ('svgRect', 0.0928879058271539), ('tiledWidth', 0.08901593082705023), ('tiledX', 0.0861751865176713), ('scale', 0.08601927783140566), ('s', 0.08595923118995441), ('ang', 0.06867763397976778), ('rect', 0.046812938187855015)]
        double h = Math.min(tileHeight, tiledHeight);
// h                    0	: [('h', 0.8480412031311989), ('y', 0.011639572896135632), ('sy', 0.009551095313788975), ('x', 0.009369467501527586), ('width', 0.008010762972195244), ('sx', 0.007612043104995915), ('tx', 0.007558098465349284), ('ty', 0.007486819384264353), ('height', 0.007133669311494849), ('NR', 0.006813698166875852)]
        Rectangle2D realTileRect = new Rectangle2D.Double(tileRect.getX(), tileRect.getY(), w, h);
// realTileRect         No	: [('bounds', 0.10539344608899973), ('report', 0.08361554576991558), ('r', 0.06358321254846562), ('primitiveRegion', 0.06191288061551196), ('r2d', 0.05812727784849424), ('glyphBounds', 0.04307074872577449), ('ret', 0.04067631028935608), ('result', 0.03450268096112554), ('aoiR', 0.03403821093644401), ('n', 0.028094358199183066)]
        return realTileRect;
    }

    public RenderedImage createTile(RenderContext rc) {
// rc                   0	: [('rc', 0.87511517942501), ('realTileRectDev', 0.7500194648937009), ('renderContext', 0.427089142614838), ('context', 0.019605508594109727), ('usr2dev', 0.010458699650909767), ('i', 0.008041145848529217), ('e', 0.007692272034302169), ('n', 0.006534429713712384), ('ctx', 0.00447407787766171), ('x', 0.0038606077826475)]
        AffineTransform usr2dev = rc.getTransform();
// usr2dev              3	: [('anchorRect', 0.8819479260191184), ('y', 0.5147363621690948), ('at', 0.36997984317644417), ('usr2dev', 0.3171843620273316), ('i', 0.2253158077897223), ('scaleX', 0.15631526417978203), ('origDev', 0.15369131439903175), ('txf', 0.1463717955038887), ('newPositions', 0.14291651524898774), ('sx', 0.1389591054510808)]
        RenderingHints rcHints = rc.getRenderingHints();
// rcHints              No	: [('rh', 0.7435753423312947), ('style', 0.15463526833442737), ('hints', 0.12918075351014444), ('width', 0.07758958863741061), ('origRH', 0.06867636214644182), ('n', 0.047671072312616504), ('o', 0.04330204146838954), ('c', 0.0423818761651839), ('r2d', 0.039558242490166824), ('offsets', 0.03937952156762296)]
        RenderingHints hints = new RenderingHints(null);
// hints                2	: [('rh', 0.48715068505282194), ('width', 0.3530266696109824), ('hints', 0.13968578022798928), ('rcHints', 0.13735281058678717), ('origRH', 0.1373527243003881), ('n', 0.01763881097499712), ('result', 0.016824269121191508), ('g2d', 0.014083948317343761), ('t', 0.011480482354118767), ('e', 0.01005596434739009)]
        if (rcHints != null) {
            hints.add(rcHints);
        }
        Rectangle2D tiledRect = getBounds2D();
// tiledRect            0	: [('tiledRect', 0.9375058957521114), ('srect', 0.19741424741249528), ('x', 0.17883629403138893), ('defaultRegion', 0.15068495994624392), ('aoiR', 0.13899083269234327), ('s', 0.11837793734654398), ('bounds', 0.10774944925049774), ('tileX', 0.10586956871608158), ('rect', 0.09876673622902653), ('r', 0.07502342755158117)]
        Shape aoiShape = rc.getAreaOfInterest();
// aoiShape             No	: [('aoi', 0.9062750201740519), ('devShape', 0.27716781628658144), ('s', 0.06723270272966392), ('clip', 0.05604295292887657), ('shape', 0.021670518252767768), ('ellipse', 0.016169522126578018), ('glyph', 0.016169241714363434), ('selectionHighlight', 0.016167753372609107), ('layoutHighlightedShape', 0.0161677102322684), ('origClip', 0.016167688662098046)]
        Rectangle2D aoiRect = aoiShape.getBounds2D();
// aoiRect              0	: [('aoiRect', 0.9375052329147131), ('aoiR', 0.5297861484909411), ('bounds', 0.05826325524682815), ('primitiveRegion', 0.05803141165449565), ('x', 0.036666949095064946), ('r', 0.03185325023256467), ('rect', 0.027249822101040117), ('r2d', 0.025170117365551876), ('gnBounds', 0.017361834348000173), ('defaultRegion', 0.01152081962716575)]
        if (!tiledRect.intersects(aoiRect))
            return null;
        Rectangle2D.intersect(tiledRect, aoiRect, tiledRect);
        Rectangle2D tileRect = (Rectangle2D) tileRegion.clone();
// tileRect             0	: [('tileRect', 0.8958392290684696), ('tiledRect', 0.13145980828378098), ('bounds', 0.10539344824020992), ('i', 0.07189727022377419), ('cmp', 0.06885540348115567), ('a', 0.0658206293591965), ('h', 0.06577642050849747), ('tv', 0.06573396921083643), ('r', 0.06358321828740844), ('primitiveRegion', 0.061912880840638604)]
        if ((tileRect.getWidth() <= 0) || (tileRect.getHeight() <= 0) || (tiledRect.getWidth() <= 0) || (tiledRect.getHeight() <= 0))
            return null;
        double tileX = tileRect.getX();
// tileX                0	: [('tileX', 0.5001085011519378), ('tileWidth', 0.4004739936996729), ('t', 0.3888887456017349), ('x', 0.1943224146109108), ('bounds', 0.10971732105142926), ('r', 0.08240977530835443), ('r2d', 0.08226068666783486), ('minMaxX', 0.05482712256006508), ('yLoc', 0.03790802925980682), ('glyphX', 0.02896523695229698)]
        double tileY = tileRect.getY();
// tileY                0	: [('tileY', 0.6111805054240601), ('dy', 0.1726417503470359), ('h', 0.16486761760432703), ('y', 0.10481542823007754), ('tdx13', 0.08901565041912374), ('tdy12', 0.08901565041912374), ('B', 0.07678160886568329), ('D', 0.07675993005860335), ('ty', 0.06687002157976926), ('w', 0.05408257360981001)]
        double tileWidth = tileRect.getWidth();
// tileWidth            0	: [('tileWidth', 0.6667879481881127), ('boxSz', 0.21561411545335019), ('tdy13', 0.2056823170868625), ('dy', 0.17587784354948122), ('dx', 0.15671755042792973), ('decorationRect', 0.13823366307809162), ('screenSize', 0.13792367294204977), ('count', 0.12477646790727438), ('h', 0.11355543639536277), ('w', 0.11241590715648472)]
        double tileHeight = tileRect.getHeight();
// tileHeight           0	: [('tileHeight', 0.7501210226972704), ('screenSize', 0.3022485476362711), ('boxSz', 0.2156141154905158), ('dy', 0.18822385807842903), ('decorationRect', 0.1382336628035785), ('count', 0.1247764685362309), ('tileWidth', 0.1044388959896941), ('sx', 0.10263077536941928), ('sy', 0.10066470631076664), ('srcRect', 0.09215518859189892)]
        double tiledX = tiledRect.getX();
// tiledX               4	: [('fr', 0.3381198099832781), ('w', 0.1790845547761851), ('npd', 0.13524381016276057), ('gp', 0.13477540609962865), ('tiledX', 0.12509616826121964), ('sx', 0.10866493755297671), ('srcR', 0.10039626457488628), ('tiledWidth', 0.08901661732308154), ('scale', 0.08601970955423134), ('s', 0.08595960552355217)]
        double tiledY = tiledRect.getY();
// tiledY               2	: [('fr', 0.3381198099832781), ('npd', 0.13524381016276057), ('tiledY', 0.12509616826121964), ('y', 0.10482131990523949), ('tdx13', 0.08901633690157609), ('tdy12', 0.08901633690157609), ('srcR', 0.07714352408208061), ('gp', 0.06810873943296197), ('ty', 0.0668729783835613), ('bounds', 0.06004789047727212)]
        double tiledWidth = tiledRect.getWidth();
// tiledWidth           0	: [('tiledWidth', 0.8281325667031484), ('w', 0.25615906681549266), ('dx', 0.21086861754986488), ('tdy13', 0.2056823170868625), ('h', 0.11355539325323534), ('tileWidth', 0.10307860839007262), ('r', 0.09230703771974155), ('pt0', 0.08713364042541927), ('tileHeight', 0.07812790043291293), ('visRect', 0.05598320658759004)]
        double tiledHeight = tiledRect.getHeight();
// tiledHeight          0	: [('tiledHeight', 0.9375075239279146), ('sy', 0.10066470624465529), ('tileHeight', 0.07711177292064825), ('imageHeight', 0.07711147092825776), ('y', 0.057196337469259846), ('h', 0.04926972195026782), ('height', 0.048753467359236614), ('scaleY', 0.04415650773079254), ('tileScaleY', 0.038573602768945586), ('highFreq', 0.03811999714869992)]
        double w = Math.min(tileWidth, tiledWidth);
// w                    1	: [('dx', 0.7961520273126742), ('w', 0.7522394421827977), ('sx', 0.10866191205578804), ('r', 0.09421284113045006), ('svgRect', 0.09288790680275912), ('tiledWidth', 0.08901590926974415), ('tiledX', 0.08617518652696216), ('scale', 0.08601927799292376), ('s', 0.08595923284229887), ('tileGridXOff', 0.08342757982934958)]
        double h = Math.min(tileHeight, tiledHeight);
// h                    0	: [('h', 0.8480393267206721), ('dy', 0.6242616527935851), ('width', 0.2612839474568092), ('B', 0.10029659342988645), ('D', 0.10029117320149136), ('tileGridYOffset', 0.08336620379403582), ('tileY', 0.06783932865262324), ('y', 0.04541275608512725), ('sy', 0.009551095446005117), ('x', 0.009369469838537247)]
        double dx = (tiledX - tileX) % tileWidth;
// dx                   1	: [('w', 0.789528064773668), ('dx', 0.751091508070636), ('textureImage', 0.5417562822119226), ('dy', 0.5025651294202461), ('x', 0.34714625664596366), ('offsets', 0.33483872263728265), ('minX', 0.16684767525621252), ('tiledWidth', 0.09805144754484471), ('npX', 0.07597166582141766), ('radiusX', 0.06878671035428928)]
        double dy = (tiledY - tileY) % tileHeight;
// dy                   0	: [('dy', 0.751457501724444), ('h', 0.6041498488155064), ('textureImage', 0.535803872831682), ('offsets', 0.5348387226372826), ('width', 0.345090119190391), ('y', 0.34015713679209364), ('minY', 0.14303550580599989), ('B', 0.10258783825166552), ('D', 0.10258241779635027), ('count', 0.10122865284519614)]
        if (dx > 0) {
            dx = tileWidth - dx;
        } else {
            dx *= -1;
        }
        if (dy > 0) {
            dy = tileHeight - dy;
        } else {
            dy *= -1;
        }
        double scaleX = usr2dev.getScaleX();
// scaleX               0	: [('scaleX', 0.7607956556623056), ('redVec', 0.3890264327782218), ('tile', 0.1296935735335457), ('sx', 0.09722497168707413), ('p', 0.07435393870993107), ('scaleY', 0.06857048030956017), ('x', 0.057638912876700095), ('y', 0.05707610034518201), ('dx', 0.05039081374721239), ('w', 0.050316935555592496)]
        double scaleY = usr2dev.getScaleY();
// scaleY               6	: [('sy', 0.4736805791613653), ('redVec', 0.3890264328461588), ('tile', 0.1296935737600023), ('p', 0.07435393983089125), ('scaleX', 0.06858056446862956), ('sinTheta', 0.058397257749773036), ('scaleY', 0.0391972854222715), ('r', 0.03729648453311859), ('bounds', 0.037191545043830494), ('sx', 0.025328578584162615)]
        double tdx = Math.floor(scaleX * dx);
// tdx                  2	: [('shx', 0.5199568431657342), ('currEndPoint', 0.19598898902210013), ('tdx', 0.12462190568595062), ('X', 0.02933553930328841), ('sy', 0.029236134683867713), ('invokerScreenLocation', 0.02712237607421194), ('sx', 0.02532857853806678), ('ty', 0.025203354817335218), ('tx', 0.023306129961412275), ('y', 0.021482092581175)]
        double tdy = Math.floor(scaleY * dy);
// tdy                  2	: [('firstLine', 0.1289907995046723), ('currEndPoint', 0.09675839499566984), ('tdy', 0.0967445536261889), ('arc', 0.06454755175050604), ('y', 0.04655829158454253), ('sy', 0.0382043812551559), ('x', 0.037477870006110345), ('next', 0.03241653232981734), ('mi', 0.03228599773095771), ('width', 0.032043051888780975)]
        dx = tdx / scaleX;
        dy = tdy / scaleY;
        Rectangle2D.Double A = new Rectangle2D.Double(tileX + tileWidth - dx, tileY + tileHeight - dy, dx, dy);
// A                    No	: [('C', 0.8760125429434442), ('rc', 0.6472315538989046), ('vb', 0.2649093508016796), ('y', 0.20967954731264307), ('rect', 0.1824200536090322), ('ellipse', 0.18241520003928988), ('x', 0.17883624913560078), ('B', 0.17484156603572498), ('D', 0.17483979717919668), ('tileX', 0.1058695686828453)]
        Rectangle2D.Double B = new Rectangle2D.Double(tileX, tileY + tileHeight - dy, w - dx, dy);
// B                    No	: [('D', 0.7688577538883614), ('rc', 0.6472315538989046), ('C', 0.3374204378751252), ('vb', 0.26490946730772325), ('rect', 0.1824200536090322), ('ellipse', 0.18241520003928988), ('x', 0.1788362837173462), ('dy', 0.12373527416911319), ('h', 0.1199751274178411), ('tileX', 0.10586956829782243)]
        Rectangle2D.Double C = new Rectangle2D.Double(tileX + tileWidth - dx, tileY, dx, h - dy);
// C                    No	: [('A', 0.8760241596062139), ('rc', 0.6472315538989046), ('B', 0.3374207830178625), ('D', 0.3374198985895983), ('vb', 0.2649093508016796), ('y', 0.20967954731264307), ('rect', 0.1824200536090322), ('ellipse', 0.18241520003928988), ('x', 0.1788362448549948), ('tileX', 0.10586956849033127)]
        Rectangle2D.Double D = new Rectangle2D.Double(tileX, tileY, w - dx, h - dy);
// D                    No	: [('B', 0.768961284302027), ('rc', 0.6472315538989046), ('C', 0.3374204378751252), ('vb', 0.26490946730772325), ('rect', 0.1824200536090322), ('ellipse', 0.18241520003928988), ('x', 0.1788362837173462), ('dy', 0.12373527416911319), ('h', 0.1199751274178411), ('tileX', 0.10586956829782243)]
        Rectangle2D realTileRect = new Rectangle2D.Double(tiledRect.getX(), tiledRect.getY(), w, h);
// realTileRect         No	: [('bounds', 0.10539344608899973), ('aoi', 0.07657225147673949), ('rect', 0.06870400244274277), ('aoiRect', 0.06864536437013051), ('B', 0.06666999226969766), ('arect', 0.06666667048436695), ('A', 0.06666649792997198), ('C', 0.06666628222826845), ('imageRect', 0.0666658076845207), ('D', 0.06666574297400964)]
        RenderedImage ARed = null, BRed = null, CRed = null, DRed = null;
// ARed                 No	: [('ri', 0.44654455173665974), ('image', 0.27095863138983395), ('BRed', 0.19391819925434162), ('CRed', 0.19391819925434162), ('DRed', 0.19391819925434162), ('hints', 0.09627993557542752), ('red', 0.08473295638332348), ('tileRed', 0.04167136102998725), ('mapRed', 0.04167118846433638), ('displacedRed', 0.04167084333303463)]
// BRed                 No	: [('CRed', 0.45833679233536984), ('DRed', 0.45833679233536984), ('image', 0.38547931728308393), ('boxes', 0.25524836354896097), ('ARed', 0.1939181555992094), ('hints', 0.09627992710521886), ('c1', 0.0399223879544146), ('nextValue1', 0.0398957919685719), ('enc', 0.03989224613998343), ('l1', 0.03988620215507416)]
// CRed                 No	: [('DRed', 0.45833679233536984), ('BRed', 0.4583367707643061), ('image', 0.38547931728308393), ('boxes', 0.25524836354896097), ('ARed', 0.19391819898848103), ('hints', 0.09627993118309806), ('lu', 0.04718952407323936), ('elt', 0.04218222318550386), ('c1', 0.03992238942866534), ('nextValue1', 0.039895792916304525)]
// DRed                 No	: [('BRed', 0.45833679233536984), ('CRed', 0.4583367707643061), ('image', 0.38547931728308393), ('boxes', 0.25524836354896097), ('ARed', 0.19391819898848103), ('hints', 0.09627993118309806), ('lu', 0.04718952407323936), ('elt', 0.04218222318550386), ('c1', 0.03992238942866534), ('nextValue1', 0.039895792916304525)]
        Filter source = getSource();
// source               7	: [('ret', 0.5158140515748824), ('filter', 0.11315818819596991), ('in', 0.0445916001936445), ('src', 0.04097595838668611), ('filt', 0.0249091812156478), ('f', 0.02148631276957618), ('inputFilter', 0.015545176761959123), ('source', 0.010304202567689316), ('i', 0.00804143321569727), ('e', 0.007692719487289869)]
        if (A.getWidth() > 0 && A.getHeight() > 0) {
            Rectangle devA = usr2dev.createTransformedShape(A).getBounds();
// devA                 No	: [('devB', 0.25637683493632685), ('devC', 0.25637683493632685), ('devD', 0.25637683493632685), ('preferredSize', 0.19485575139703978), ('r', 0.06911693826903234), ('s', 0.02778186243355746), ('n', 0.021213704472028905), ('ln', 0.01764790922545487), ('rect', 0.01575338660573923), ('ellipse', 0.015748533277210045)]
            if (devA.width > 0 && devA.height > 0) {
                AffineTransform ATxf = new AffineTransform(usr2dev);
// ATxf                 No	: [('BTxf', 0.2916711491030588), ('CTxf', 0.2916711491030588), ('DTxf', 0.2916711491030588), ('at', 0.24358207593408138), ('srcAt', 0.16840244958277242), ('usr2dev', 0.12268627288041059), ('inverseTransform', 0.11955093720755457), ('tileAt', 0.056759092285877374), ('tr', 0.05225710245988995), ('resUsr2Dev', 0.04761169387797383)]
                ATxf.translate(-A.x + tiledX, -A.y + tiledY);
                Shape aoi = A;
// aoi                  0	: [('aoi', 0.7995675534225093), ('curClip', 0.23437790131981942), ('s', 0.029732703555822457), ('clip', 0.018542952971042113), ('devShape', 0.014667816291226828), ('shape', 0.009170518305296013), ('outline', 0.006289062907120791), ('aoiShape', 0.004834419443288222), ('glyphOutline', 0.0041918199497855396), ('ellipse', 0.0036695221583808316)]
                if (overflow) {
                    aoi = new Rectangle2D.Double(A.x, A.y, tiledWidth, tiledHeight);
                }
                hints.put(RenderingHintsKeyExt.KEY_AREA_OF_INTEREST, aoi);
                RenderContext arc = new RenderContext(ATxf, aoi, hints);
// arc                  No	: [('crc', 0.2704326578033841), ('brc', 0.2704319675579328), ('drc', 0.2704319675579328), ('tileRc', 0.19373438827655368), ('rc', 0.08830010927349813), ('srcRc', 0.020432075408784615), ('newRC', 0.020431989128103206), ('srcRC', 0.02043196755793285), ('context', 0.006242272128526448), ('renderContext', 0.006235466778103437)]
                ARed = source.createRendering(arc);
            }
        }
        if (B.getWidth() > 0 && B.getHeight() > 0) {
            Rectangle devB = usr2dev.createTransformedShape(B).getBounds();
// devB                 No	: [('devA', 0.25637683493632685), ('devC', 0.25637683493632685), ('devD', 0.25637683493632685), ('preferredSize', 0.19485575139703978), ('r', 0.06911693826903234), ('s', 0.02778186243355746), ('n', 0.021213704472028905), ('ln', 0.01764790922545487), ('rect', 0.01575338660573923), ('ellipse', 0.015748533277210045)]
            if (devB.width > 0 && devB.height > 0) {
                AffineTransform BTxf = new AffineTransform(usr2dev);
// BTxf                 No	: [('ATxf', 0.2916711491030588), ('CTxf', 0.2916711491030588), ('DTxf', 0.2916711491030588), ('at', 0.24358207593408138), ('srcAt', 0.16840244958277242), ('usr2dev', 0.12268627288041059), ('inverseTransform', 0.11955093720755457), ('tileAt', 0.056759092285877374), ('tr', 0.05225710245988995), ('resUsr2Dev', 0.04761169387797383)]
                BTxf.translate(-B.x + (tiledX + dx), -B.y + tiledY);
                Shape aoi = B;
// aoi                  0	: [('aoi', 0.7995675534225093), ('curClip', 0.23437790131981942), ('s', 0.029732703555822457), ('clip', 0.018542952971042113), ('devShape', 0.014667816291226828), ('shape', 0.009170518305296013), ('outline', 0.006289062907120791), ('aoiShape', 0.004834419443288222), ('glyphOutline', 0.0041918199497855396), ('ellipse', 0.0036695221583808316)]
                if (overflow) {
                    aoi = new Rectangle2D.Double(B.x - tiledWidth + w - dx, B.y, tiledWidth, tiledHeight);
                }
                hints.put(RenderingHintsKeyExt.KEY_AREA_OF_INTEREST, aoi);
                RenderContext brc = new RenderContext(BTxf, aoi, hints);
// brc                  No	: [('arc', 0.2704332186278133), ('crc', 0.2704326578033841), ('drc', 0.2704319675579328), ('tileRc', 0.19373438827655368), ('rc', 0.08830010927349813), ('srcRc', 0.020432075408784615), ('newRC', 0.020431989128103206), ('srcRC', 0.02043196755793285), ('context', 0.006242272128526448), ('renderContext', 0.006235466778103437)]
                BRed = source.createRendering(brc);
            }
        }
        if (C.getWidth() > 0 && C.getHeight() > 0) {
            Rectangle devC = usr2dev.createTransformedShape(C).getBounds();
// devC                 No	: [('devA', 0.25637683493632685), ('devB', 0.25637683493632685), ('devD', 0.25637683493632685), ('preferredSize', 0.19485575139703978), ('r', 0.06911693826903234), ('s', 0.02778186243355746), ('n', 0.021213704472028905), ('ln', 0.01764790922545487), ('rect', 0.01575338660573923), ('ellipse', 0.015748533277210045)]
            if (devC.width > 0 && devC.height > 0) {
                AffineTransform CTxf = new AffineTransform(usr2dev);
// CTxf                 No	: [('ATxf', 0.2916711491030588), ('BTxf', 0.2916711491030588), ('DTxf', 0.2916711491030588), ('at', 0.24358207593408138), ('srcAt', 0.16840244958277242), ('usr2dev', 0.12268627288041059), ('inverseTransform', 0.11955093720755457), ('tileAt', 0.056759092285877374), ('tr', 0.05225710245988995), ('resUsr2Dev', 0.04761169387797383)]
                CTxf.translate(-C.x + tiledX, -C.y + (tiledY + dy));
                Shape aoi = C;
// aoi                  0	: [('aoi', 0.7501234533949929), ('curClip', 0.23437790131981942), ('s', 0.15473270355582247), ('shearBounds', 0.0661676670929997), ('clip', 0.018542952971042113), ('devShape', 0.014667816291226828), ('shape', 0.009170518305296013), ('outline', 0.006289062907120791), ('aoiShape', 0.004834419443288222), ('glyphOutline', 0.0041918199497855396)]
                if (overflow) {
                    aoi = new Rectangle2D.Double(C.x, C.y - tileHeight + h - dy, tiledWidth, tiledHeight);
                }
                hints.put(RenderingHintsKeyExt.KEY_AREA_OF_INTEREST, aoi);
                RenderContext crc = new RenderContext(CTxf, aoi, hints);
// crc                  No	: [('arc', 0.2704332186278133), ('brc', 0.2704319675579328), ('drc', 0.2704319675579328), ('tileRc', 0.19373438827655368), ('rc', 0.08830010927349813), ('srcRc', 0.020432075408784615), ('newRC', 0.020431989128103206), ('srcRC', 0.02043196755793285), ('context', 0.006242272128526448), ('renderContext', 0.006235466778103437)]
                CRed = source.createRendering(crc);
            }
        }
        if (D.getWidth() > 0 && D.getHeight() > 0) {
            Rectangle devD = usr2dev.createTransformedShape(D).getBounds();
// devD                 No	: [('devA', 0.25637683493632685), ('devB', 0.25637683493632685), ('devC', 0.25637683493632685), ('preferredSize', 0.19485575139703978), ('r', 0.06911693826903234), ('s', 0.02778186243355746), ('n', 0.021213704472028905), ('ln', 0.01764790922545487), ('rect', 0.01575338660573923), ('ellipse', 0.015748533277210045)]
            if (devD.width > 0 && devD.height > 0) {
                AffineTransform DTxf = new AffineTransform(usr2dev);
// DTxf                 No	: [('ATxf', 0.2916711491030588), ('BTxf', 0.2916711491030588), ('CTxf', 0.2916711491030588), ('at', 0.24358207593408138), ('srcAt', 0.16840244958277242), ('usr2dev', 0.12268627288041059), ('inverseTransform', 0.11955093720755457), ('tileAt', 0.056759092285877374), ('tr', 0.05225710245988995), ('resUsr2Dev', 0.04761169387797383)]
                DTxf.translate(-D.x + (tiledX + dx), -D.y + (tiledY + dy));
                Shape aoi = D;
// aoi                  0	: [('aoi', 0.7501234533949929), ('curClip', 0.23437790131981942), ('s', 0.15473270355582247), ('shearBounds', 0.0661676670929997), ('clip', 0.018542952971042113), ('devShape', 0.014667816291226828), ('shape', 0.009170518305296013), ('outline', 0.006289062907120791), ('aoiShape', 0.004834419443288222), ('glyphOutline', 0.0041918199497855396)]
                if (overflow) {
                    aoi = new Rectangle2D.Double(D.x - tileWidth + w - dx, D.y - tileHeight + h - dy, tiledWidth, tiledHeight);
                }
                hints.put(RenderingHintsKeyExt.KEY_AREA_OF_INTEREST, aoi);
                RenderContext drc = new RenderContext(DTxf, aoi, hints);
// drc                  No	: [('arc', 0.2704332186278133), ('crc', 0.2704326578033841), ('brc', 0.2704319675579328), ('tileRc', 0.19373438827655368), ('rc', 0.08830010927349813), ('srcRc', 0.020432075408784615), ('newRC', 0.020431989128103206), ('srcRC', 0.02043196755793285), ('context', 0.006242272128526448), ('renderContext', 0.006235466778103437)]
                DRed = source.createRendering(drc);
            }
        }
        final Rectangle realTileRectDev = usr2dev.createTransformedShape(realTileRect).getBounds();
// realTileRectDev      9	: [('rc', 0.7500288674124008), ('devRect', 0.5515987476250509), ('renderedArea', 0.43390715866450996), ('r', 0.30248036611438894), ('devR', 0.30200124856155897), ('minX', 0.27004617818195964), ('wr', 0.26930290951033364), ('cm', 0.25286056074366964), ('height', 0.23220027664489865), ('realTileRectDev', 0.21695532252841945)]
        if (realTileRectDev.width == 0 || realTileRectDev.height == 0) {
            return null;
        }
        BufferedImage realTileBI = new BufferedImage(realTileRectDev.width, realTileRectDev.height, BufferedImage.TYPE_INT_ARGB);
// realTileBI           5	: [('img', 0.768169270544568), ('destBI', 0.5216537320542045), ('bi', 0.33762397055384746), ('tmpBI', 0.22285456173055365), ('offScreen', 0.2228545185898556), ('realTileBI', 0.04708164974954673), ('srcBI', 0.03260032194397753), ('diff', 0.016104348369125807), ('cmp', 0.01610396010284347), ('result', 0.0157283097373306)]
        Graphics2D g = GraphicsUtil.createGraphics(realTileBI, rc.getRenderingHints());
// g                    1	: [('g2d', 0.5059017958296776), ('g', 0.3886802055041158), ('ig', 0.15732398270068768), ('destPt', 0.1254819103130689), ('descent', 0.12542281013688003), ('srcPt', 0.12540943723972506), ('srcGraphics', 0.06788797050009648), ('dstGraphics', 0.06788797050009648), ('sb', 0.03746280447121685), ('ctx', 0.03132045840341204)]
        g.translate(-realTileRectDev.x, -realTileRectDev.y);
        AffineTransform redTxf = new AffineTransform();
// redTxf               0	: [('redTxf', 0.5000154372398588), ('at', 0.27854630069660624), ('tr', 0.057097923373932234), ('scrnTrans', 0.047815324974389624), ('glyphTransform', 0.04066686292819465), ('ati', 0.030250254460139653), ('xform', 0.025220564241530852), ('t', 0.02190372289979048), ('srcAt', 0.02102037823519976), ('af', 0.01785146379911362)]
        Point2D.Double redVec = new Point2D.Double();
// redVec               No	: [('x', 0.4206866823078005), ('vecX', 0.37501108042358233), ('vecY', 0.37501108042358233), ('tile', 0.27079332142178547), ('i', 0.22531678511565773), ('scaleX', 0.13954142291996102), ('scaleY', 0.1395313370440656), ('p1', 0.13857758899258843), ('y', 0.133018931124755), ('np2', 0.1326202321286786)]
        RenderedImage refRed = null;
// refRed               2	: [('ri', 0.723272276350784), ('wr', 0.25269887008778263), ('refRed', 0.250017930340139), ('radX', 0.08481443633673086), ('radY', 0.08481443633673086), ('off', 0.08436795126464287), ('src', 0.08423475238191616), ('dest', 0.08419509075552985), ('dst', 0.08419459459229542), ('n', 0.07904696853111667)]
        if (ARed != null) {
            g.drawRenderedImage(ARed, redTxf);
            refRed = ARed;
        }
        if (BRed != null) {
            if (refRed == null) {
                refRed = BRed;
            }
            redVec.x = dx;
            redVec.y = 0;
            usr2dev.deltaTransform(redVec, redVec);
            redVec.x = Math.floor(redVec.x) - (BRed.getMinX() - refRed.getMinX());
            redVec.y = Math.floor(redVec.y) - (BRed.getMinY() - refRed.getMinY());
            g.drawRenderedImage(BRed, redTxf);
        }
        if (CRed != null) {
            if (refRed == null) {
                refRed = CRed;
            }
            redVec.x = 0;
            redVec.y = dy;
            usr2dev.deltaTransform(redVec, redVec);
            redVec.x = Math.floor(redVec.x) - (CRed.getMinX() - refRed.getMinX());
            redVec.y = Math.floor(redVec.y) - (CRed.getMinY() - refRed.getMinY());
            g.drawRenderedImage(CRed, redTxf);
        }
        if (DRed != null) {
            if (refRed == null) {
                refRed = DRed;
            }
            redVec.x = dx;
            redVec.y = dy;
            usr2dev.deltaTransform(redVec, redVec);
            redVec.x = Math.floor(redVec.x) - (DRed.getMinX() - refRed.getMinX());
            redVec.y = Math.floor(redVec.y) - (DRed.getMinY() - refRed.getMinY());
            g.drawRenderedImage(DRed, redTxf);
        }
        CachableRed realTile;
// realTile             No	: [('cr', 0.8925692096630564), ('report', 0.08361554619585536), ('ret', 0.04067631088752993), ('i', 0.03981615983303861), ('result', 0.034502681724386794), ('src', 0.028370136477581628), ('n', 0.028094360859877487), ('v', 0.025778615608695602), ('wr', 0.021670753315670345), ('sb', 0.017366110982959735)]
        realTile = new BufferedImageCachableRed(realTileBI, realTileRectDev.x, realTileRectDev.y);
        return realTile;
    }
}
