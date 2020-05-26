// Type of training: selfTestingIdentifier
// Path to file: C:\Users\Igor\IdeaProjects\SLP-Core-0.3\java-projects\elasticsearch-master\server\src\main\java\org\elasticsearch\action\ActionListener.java
// Number of identifiers: 59	Accuracy: 55.93%	MRR: 64.16%
// True 	Rank of true in predictions : [(predicted token, probability of a token), ...]

package org.elasticsearch.action;

import org.elasticsearch.ExceptionsHelper;
import org.elasticsearch.common.CheckedConsumer;
import org.elasticsearch.common.CheckedFunction;
import org.elasticsearch.common.CheckedRunnable;
import org.elasticsearch.common.CheckedSupplier;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public interface ActionListener<Response> {

    void onResponse(Response response);
// response             0	: [('response', 0.8471715166337358), ('serverTestInstance', 0.002720076414481268), ('instance', 0.0018288375828630675), ('response1', 0.0011902874578714015), ('read', 0.0009800127652937628), ('statsResponse', 0.00074278363769338), ('fetchResponse', 0.0005845747470326939), ('other', 0.0005153614444483144), ('putJobResponse', 0.0004029390397652044), ('newResponse', 0.0003823239878247092)]

    void onFailure(Exception e);
// e                    0	: [('e', 0.7790490801286818), ('exception', 0.07402491762984105), ('ex', 0.07012520238593306), ('t', 0.008410558135294595), ('exc', 0.008392110189492614), ('inner', 0.0021599409823343297), ('failure', 0.0016420614178603089), ('throwable', 0.000880759202898099), ('notificationException', 0.0004520947573500772), ('replicaException', 0.0004283873657044723)]

    static <Response> ActionListener<Response> wrap(CheckedConsumer<Response, ? extends Exception> onResponse, Consumer<Exception> onFailure) {
// onResponse           2	: [('handler', 0.2932600605084295), ('onAnswer', 0.18987930034551165), ('onResponse', 0.09597813288561693), ('supplier', 0.0952053672926965), ('actionListener', 0.07834527392643223), ('listener', 0.07021220425275283), ('channel', 0.0475013946482831), ('clazz', 0.0471605721742956), ('exceptionClass', 0.047102628805130185), ('delegate', 0.039922924315146324)]
// onFailure            6	: [('errorHandler', 0.30337373115214306), ('listener', 0.24894156343572685), ('handler', 0.14255568267715024), ('failureHandler', 0.09320581897906247), ('exceptionHandler', 0.07970031135729158), ('throwableRef', 0.07263750744350392), ('onFailure', 0.06607365810774021), ('failureConsumer', 0.031469519204171244), ('failure', 0.02236031357389716), ('exceptionConsumer', 0.019642882696646463)]
        return new ActionListener<Response>() {

            @Override
            public void onResponse(Response response) {
// response             0	: [('response', 0.923585756119041), ('task', 0.1892705542929043), ('e', 0.012529886919943197), ('indexWriter', 0.007103582396495093), ('result', 0.006353352763859722), ('value', 0.005983740539936304), ('ingestDocument', 0.003888970430275596), ('parser', 0.0037055970565340055), ('builder', 0.003518997054412715), ('channel', 0.003466414686911347)]
                try {
                    onResponse.accept(response);
                } catch (Exception e) {
// e                    0	: [('e', 0.7452682477355623), ('idx', 0.10674484721121855), ('ex', 0.08200041982595993), ('inner', 0.03591173111125328), ('shardRouting', 0.023130686677486315), ('exception', 0.018725555499597753), ('t', 0.01345888064544356), ('source', 0.010720439824504801), ('failure', 0.01069831869222645), ('exc', 0.010632552374820454)]
                    onFailure(e);
                }
            }

            @Override
            public void onFailure(Exception e) {
// e                    0	: [('e', 0.8707527405427835), ('slot', 0.09411846315279412), ('exception', 0.06226683367125835), ('task', 0.0482617823630797), ('checkRefreshResult', 0.0467057706437653), ('unknownLicense', 0.046705761715808626), ('nonCompliantLicense', 0.046705761715808626), ('t', 0.017844520404388726), ('ex', 0.01340973351623348), ('exc', 0.01338656080084562)]
                onFailure.accept(e);
            }
        };
    }

    static <T> ActionListener<T> delegateResponse(ActionListener<T> delegate, BiConsumer<ActionListener<T>, Exception> bc) {
// delegate             1	: [('bc', 0.7532074995989315), ('delegate', 0.7506491731076572), ('listener', 0.4707199571428966), ('e', 0.012529887271988657), ('rewriteResponse', 0.012488366565534325), ('type', 0.011615119585127728), ('key', 0.011604466707565436), ('visitor', 0.007123842548897178), ('indexWriter', 0.00710358240221305), ('shard', 0.006472567973257691)]
// bc                   No	: [('consumer', 0.5496702769772418), ('listener', 0.24894156343572685), ('throwableRef', 0.07263750744350392), ('body', 0.043383273224952036), ('action', 0.042361302360232465), ('inner', 0.04230953505043702), ('code', 0.04223468803176677), ('consumeLine', 0.042234377785272316), ('handler', 0.037812045219194866), ('failure', 0.02236031357389716)]
        return new ActionListener<T>() {

            @Override
            public void onResponse(T r) {
// r                    2	: [('response', 0.48136300246556574), ('t', 0.2394284771475337), ('r', 0.10657360128826728), ('mapped', 0.09942765712753393), ('result', 0.07725865308546265), ('element', 0.07689073031853533), ('value', 0.005830580604913959), ('instance', 0.00456890247625789), ('original', 0.003232362768144118), ('searchResponse', 0.0031917710383578515)]
                delegate.onResponse(r);
            }

            @Override
            public void onFailure(Exception e) {
// e                    0	: [('e', 0.8707527405427835), ('r', 0.7557748040203187), ('node', 0.034356011728628004), ('exception', 0.02317863572677368), ('consumer', 0.022861760292531383), ('t', 0.017844520404388726), ('threadContext', 0.017168395964359366), ('maxErrorsPerRequest', 0.017140869111249953), ('exc', 0.01338656080084562), ('ex', 0.011509775089247372)]
                bc.accept(delegate, e);
            }
        };
    }

    static <T, R> ActionListener<T> delegateFailure(ActionListener<R> delegate, BiConsumer<ActionListener<R>, T> bc) {
// delegate             0	: [('delegate', 0.7506491731076572), ('listener', 0.6399223168876438), ('throwableRef', 0.07263750752564822), ('requestReader', 0.02681568465028334), ('supplier', 0.02306554622249576), ('failure', 0.022360313652116263), ('onFailure', 0.01916700434565133), ('handler', 0.015680696149717288), ('logger', 0.015432201955459976), ('exception', 0.015178096177423335)]
// bc                   No	: [('delegate', 0.7532422863863968), ('objectParser', 0.1522169203100144), ('ctor', 0.11241967120595044), ('action', 0.09566228869500276), ('entry', 0.0580392702871783), ('result', 0.04899049376063676), ('value', 0.04848032239043608), ('parser', 0.02650869195644601), ('consumer', 0.023209359945316543), ('valueSerializer', 0.02057764445468758)]
        return new ActionListener<T>() {

            @Override
            public void onResponse(T r) {
// r                    4	: [('e', 0.7561903471201059), ('response', 0.4185875601087867), ('t', 0.2394284771475337), ('result', 0.07725865308546265), ('r', 0.07709808168644333), ('element', 0.07689073031853533), ('node', 0.034356011728628004), ('consumer', 0.022861760292531383), ('threadContext', 0.017168395964359366), ('maxErrorsPerRequest', 0.017140869111249953)]
                bc.accept(delegate, r);
            }

            @Override
            public void onFailure(Exception e) {
// e                    0	: [('e', 0.8707527405427835), ('ex', 0.07550365458533112), ('exception', 0.02317863572677368), ('inner', 0.01856005756898637), ('t', 0.017844520404388726), ('exc', 0.01338656080084562), ('failure', 0.0037189510367457343), ('source', 0.0031562239759382977), ('validationException', 0.0022877510778405697), ('error', 0.0022864521567123714)]
                delegate.onFailure(e);
            }
        };
    }

    static <Response> ActionListener<Response> wrap(Runnable runnable) {
// runnable             0	: [('runnable', 0.5897963992300606), ('e', 0.3557150699529926), ('listener', 0.1959078000826153), ('next', 0.11215825982577521), ('removeBansRunnable', 0.11215285893269354), ('r', 0.10159363564531537), ('command', 0.06708981455203628), ('latch', 0.0410879830240288), ('success', 0.04018839311184127), ('fail', 0.0362756465690236)]
        return wrap(r -> runnable.run(), e -> runnable.run());
// r                    6	: [('params', 0.4174072990034229), ('fieldData', 0.1781995796116192), ('bytes', 0.14045701165864216), ('in', 0.03936083982345415), ('outboundBuffer', 0.03913129235220492), ('page', 0.023542082028831058), ('r', 0.01636026498696691), ('index', 0.016178477599294614), ('delete', 0.00785156534186323), ('response', 0.0076060632920882006)]
// e                    0	: [('e', 0.3782237431728093), ('failureHandler', 0.18756299971217227), ('exc', 0.18754164463127537), ('equalTo', 0.022622938205997246), ('request', 0.004063169554331842), ('instance', 0.0031654684026265452), ('is', 0.0025711826224359222), ('response', 0.0019219517047003641), ('parser', 0.0019078322831481357), ('listener', 0.0015333323202152394)]
    }

    static <T, Response> ActionListener<Response> map(ActionListener<T> delegate, CheckedFunction<Response, T, Exception> fn) {
// delegate             2	: [('listener', 0.4707199571428966), ('channel', 0.20904000307691745), ('delegate', 0.1895992072018643), ('request', 0.13094533758036095), ('socket', 0.12685556822966823), ('trainedModelConfig', 0.12683531505628126), ('notified', 0.041052821468600134), ('ex', 0.040956526236579444), ('transportChannel', 0.04094414690096836), ('handler', 0.02215104363774327)]
// fn                   No	: [('parseLeniently', 0.3844686264182247), ('mapper', 0.3336947300844617), ('body', 0.19338327304674238), ('map', 0.16699445340318453), ('handler', 0.06281204385117375), ('consumer', 0.03300360880103459), ('onResponse', 0.03211442380527919), ('supplier', 0.026327438156615195), ('command', 0.02558211872973946), ('operation', 0.02557762893071411)]
        return new ActionListener<>() {

            @Override
            public void onResponse(Response response) {
// response             0	: [('response', 0.923585756119041), ('defaultEntry', 0.5417523636270803), ('channelBuffer', 0.08350509002373506), ('parser', 0.07325316913184576), ('indexMetadata', 0.0422256284300151), ('params', 0.041886458436865524), ('matcher', 0.04175862960570672), ('p', 0.006978368106733041), ('left', 0.004282650322246363), ('value', 0.004008811836739164)]
                T mapped;
// mapped               No	: [('response', 0.4813601383548325), ('r', 0.2059994721638236), ('value', 0.10285639744878733), ('t', 0.09989776091986967), ('beforePart', 0.04816922176037237), ('nodeValue', 0.032242206494355934), ('painlessObject', 0.03211292717626872), ('result', 0.026763080303997534), ('testInstance', 0.024603508654126773), ('factory', 0.02416400783697082)]
                try {
                    mapped = fn.apply(response);
                } catch (Exception e) {
// e                    0	: [('e', 0.7452682477355623), ('idx', 0.10674484721121855), ('ex', 0.08200041982595993), ('inner', 0.03591173111125328), ('shardRouting', 0.023130686677486315), ('exception', 0.018725555499597753), ('t', 0.01345888064544356), ('source', 0.010720439824504801), ('failure', 0.01069831869222645), ('exc', 0.010632552374820454)]
                    onFailure(e);
                    return;
                }
                try {
                    delegate.onResponse(mapped);
                } catch (RuntimeException e) {
// e                    0	: [('e', 0.7977833152762945), ('ex', 0.20164786059195242), ('failure', 0.05282562560010463), ('userException', 0.0333073426810714), ('assertion', 0.032007887374155726), ('t', 0.017803787419009703), ('throwable', 0.017522577220953763), ('exception1', 0.01746848572897434), ('inner', 0.017292316850195256), ('result', 0.01216532699720215)]
                    assert false : new AssertionError("map: listener.onResponse failed", e);
                    throw e;
                }
            }

            @Override
            public void onFailure(Exception e) {
// e                    0	: [('e', 0.8707527361767473), ('inner', 0.2881868682184341), ('ex', 0.0755036132314683), ('o', 0.023786030333006233), ('exception', 0.02317863573861901), ('t', 0.017844520414275138), ('obj', 0.014543728205031487), ('exc', 0.01338656080268593), ('failure', 0.013344498159655174), ('suppressed', 0.011108154864916639)]
                try {
                    delegate.onFailure(e);
                } catch (RuntimeException ex) {
// ex                   0	: [('ex', 0.700846051895866), ('e', 0.6697142533427083), ('isFiltered', 0.07512673406910704), ('listener', 0.064609961562301), ('eofException', 0.054594688249588755), ('failure', 0.052825625606023476), ('lenient', 0.05043294420569227), ('userException', 0.033307342681155566), ('response', 0.027551463617262382), ('command', 0.02540768710209647)]
                    if (ex != e) {
                        ex.addSuppressed(e);
                    }
                    assert false : new AssertionError("map: listener.onFailure failed", ex);
                    throw ex;
                }
            }
        };
    }

    static <Response> BiConsumer<Response, Exception> toBiConsumer(ActionListener<Response> listener) {
// listener             0	: [('listener', 0.6443522030487197), ('delegate', 0.17758464129187443), ('builder', 0.09006882998567735), ('out', 0.043814802395274514), ('validationException', 0.04168866373227123), ('action', 0.039979623814464606), ('metadata', 0.016291203657474877), ('parser', 0.012264091960333462), ('value', 0.01033442099223835), ('sb', 0.010209480909985724)]
        return (response, throwable) -> {
// response             0	: [('response', 0.221457218505349), ('field', 0.04176894962238023), ('r', 0.03598155894054498), ('value', 0.035441684308071826), ('request', 0.03384538784390054), ('version', 0.03282165274684986), ('parser', 0.019482752766513487), ('context', 0.01917800588093802), ('text', 0.01897053724019911), ('testSearchPhaseResult', 0.018855127773271776)]
// throwable            No	: [('e', 0.6272299345536028), ('action', 0.05802152613737974), ('ex', 0.05751561446393829), ('total', 0.04591503710137184), ('xContent', 0.04590495978131375), ('value', 0.04195240651479006), ('document', 0.03985147423422012), ('exception', 0.034364937899404834), ('p', 0.03312030621715593), ('entry', 0.025420647430008575)]
            if (throwable == null) {
                listener.onResponse(response);
            } else {
                listener.onFailure(throwable);
            }
        };
    }

    static <Response> void onResponse(Iterable<ActionListener<Response>> listeners, Response response) {
// listeners            0	: [('listeners', 0.8797054201708393), ('consumer', 0.3202146936392438), ('countingRetryHandler', 0.07568305544421233), ('retryHandler', 0.06909159564100842), ('listener', 0.06342923060577244), ('listenersToFire', 0.019161083013579928), ('modelAndListeners', 0.01916106069368824), ('transportAction', 0.013184385681428126), ('futures', 0.0067552564614509255), ('retryScrollHandler', 0.006591595641008413)]
// response             0	: [('response', 0.22145725515552628), ('r', 0.03598155887641464), ('version', 0.03282165271224748), ('context', 0.01917800576278248), ('testSearchPhaseResult', 0.018855127772834324), ('startContext', 0.018064841888926424), ('finalResponseIfSuccessful', 0.01711982004979938), ('result', 0.01641518602533101), ('connection', 0.014329674004631083), ('value', 0.013438935572146175)]
        List<Exception> exceptionList = new ArrayList<>();
// exceptionList        4	: [('exceptions', 0.5125039315843629), ('ex1', 0.5000045517427559), ('caughtExceptions', 0.256384274118181), ('failures', 0.21250507591659468), ('exceptionList', 0.21250017727524606), ('errorHandler', 0.020137824859622625), ('handler', 0.017317650706328305), ('exceptionHolder', 0.016407071936292286), ('builder', 0.015549418687679912), ('errors', 0.013735537710841399)]
        for (ActionListener<Response> listener : listeners) {
// listener             0	: [('listener', 0.6443522030487197), ('store', 0.3360425210884194), ('delegate', 0.17758464129187443), ('logger', 0.16732353929735883), ('action', 0.039979623814464606), ('builder', 0.012305217250919433), ('listener1', 0.011583755510850054), ('channel', 0.009050657129807972), ('barrier', 0.00903544246343515), ('latch', 0.008587137287255783)]
            try {
                listener.onResponse(response);
            } catch (Exception ex) {
// ex                   1	: [('e', 0.7460675471120065), ('ex', 0.081201122681505), ('inner', 0.03591173111125328), ('exception', 0.034364937847593445), ('validationException', 0.022012302356918482), ('exc', 0.021148375224813543), ('t', 0.019249807083873582), ('frozenSecurityIndex', 0.017859664841704002), ('failure', 0.016504855336152743), ('error', 0.015928278374963033)]
                try {
                    listener.onFailure(ex);
                } catch (Exception ex1) {
// ex1                  No	: [('ex', 0.7504734998408922), ('e', 0.7460675471120065), ('inner', 0.03591173111125328), ('exception', 0.018725555499597753), ('t', 0.01345888064544356), ('e1', 0.009991257401865968), ('ignored', 0.009936501507702302), ('exc', 0.009749411654083261), ('e2', 0.00807451980268206), ('document', 0.004122941566890369)]
                    exceptionList.add(ex1);
                }
            }
        }
        ExceptionsHelper.maybeThrowRuntimeAndSuppress(exceptionList);
    }

    static <Response> void onFailure(Iterable<ActionListener<Response>> listeners, Exception failure) {
// listeners            0	: [('listeners', 0.8797054201708393), ('consumer', 0.3202146936392438), ('countingRetryHandler', 0.07568305544421233), ('retryHandler', 0.06909159564100842), ('listener', 0.06342923060577244), ('listenersToFire', 0.019161083013579928), ('modelAndListeners', 0.01916106069368824), ('transportAction', 0.013184385681428126), ('futures', 0.0067552564614509255), ('retryScrollHandler', 0.006591595641008413)]
// failure              9	: [('e', 0.6272299338984912), ('ex', 0.05751561438658515), ('exception', 0.0492482169030141), ('cause', 0.027460981018199052), ('validationException', 0.02201230234602595), ('exc', 0.021148375216764444), ('t', 0.019249807054476868), ('frozenSecurityIndex', 0.017859664840041686), ('error', 0.015928278364376717), ('failure', 0.014719012642395954)]
        List<Exception> exceptionList = new ArrayList<>();
// exceptionList        4	: [('exceptions', 0.5125039315843629), ('listener', 0.4111595767270184), ('caughtExceptions', 0.256384274118181), ('failures', 0.21250507591659468), ('exceptionList', 0.21250017727524606), ('logger', 0.05216808312502518), ('e', 0.05123221098410791), ('ex', 0.025801192399736862), ('task', 0.025337721257202933), ('future', 0.02532690091535156)]
        for (ActionListener<Response> listener : listeners) {
// listener             0	: [('listener', 0.644352205250162), ('delegate', 0.1775846412901183), ('action', 0.03997962380453421), ('builder', 0.012292056691269931), ('listener1', 0.011583156134155122), ('channel', 0.009039856186744645), ('barrier', 0.009027269519448159), ('latch', 0.008577929266681479), ('responses', 0.00441030673114357), ('fetchData', 0.004183207806327195)]
            try {
                listener.onFailure(failure);
            } catch (Exception ex) {
// ex                   2	: [('ex1', 0.7500274330288554), ('e', 0.7460675471120065), ('ex', 0.081201122681505), ('inner', 0.03591173111125328), ('exception', 0.018725555499597753), ('t', 0.01345888064544356), ('e1', 0.009991257401865968), ('ignored', 0.009936501507702302), ('exc', 0.009749411654083261), ('e2', 0.00807451980268206)]
                exceptionList.add(ex);
            }
        }
        ExceptionsHelper.maybeThrowRuntimeAndSuppress(exceptionList);
    }

    static <Response> ActionListener<Response> runAfter(ActionListener<Response> delegate, Runnable runAfter) {
// delegate             2	: [('listener', 0.6877954455304167), ('channel', 0.209050657129808), ('delegate', 0.1341413988101775), ('actionListener', 0.0783470721150137), ('onResponse', 0.07787379446079526), ('notified', 0.04105407885471617), ('ex', 0.040957716117117224), ('transportChannel', 0.04094533675363158), ('handler', 0.040615097010886635), ('action', 0.039979623814464606)]
// runAfter             No	: [('onBackoff', 0.7531206412314956), ('latch', 0.14978169547519646), ('executor', 0.06705780786659447), ('response', 0.046900737178515124), ('context', 0.04658726731005118), ('threadPool', 0.04365098871218578), ('listener', 0.023535997155045355), ('store', 0.022230404713858752), ('runnable', 0.01942317574915264), ('timer', 0.017337000440361502)]
        return new ActionListener<Response>() {

            @Override
            public void onResponse(Response response) {
// response             0	: [('response', 0.923585756119041), ('r', 0.20600023676800075), ('t', 0.09989782647690913), ('mapped', 0.09942765712753393), ('result', 0.004322612680232997), ('searchResponse', 0.0031917710383578515), ('authentication', 0.0031120456107856746), ('context', 0.0029831803944606106), ('releasable', 0.0020575211937646286), ('getResponse', 0.0019976495704168875)]
                try {
                    delegate.onResponse(response);
                } finally {
                    runAfter.run();
                }
            }

            @Override
            public void onFailure(Exception e) {
// e                    0	: [('e', 0.8707527405427835), ('ex', 0.07550365458533112), ('exception', 0.02317863572677368), ('inner', 0.01856005756898637), ('t', 0.017844520404388726), ('exc', 0.01338656080084562), ('failure', 0.0037189510367457343), ('source', 0.0031562239759382977), ('validationException', 0.0022877510778405697), ('error', 0.0022864521567123714)]
                try {
                    delegate.onFailure(e);
                } finally {
                    runAfter.run();
                }
            }
        };
    }

    static <Response> ActionListener<Response> runBefore(ActionListener<Response> delegate, CheckedRunnable<?> runBefore) {
// delegate             4	: [('listener', 0.6877954455609694), ('notified', 0.13500657361267487), ('clusterStateLatch', 0.13500649995690606), ('getRepoLatch', 0.13500649995690606), ('delegate', 0.1341413965799349), ('logger', 0.05216808292090737), ('e', 0.051232210856551545), ('action', 0.03997962382439502), ('request', 0.02658973733698777), ('ex', 0.02580119013967508)]
// runBefore            No	: [('channel', 0.209050657129808), ('actionListener', 0.0783470721150137), ('onResponse', 0.07787379446079526), ('listener', 0.07025126501477509), ('delegate', 0.04146318872694674), ('notified', 0.04105407885471617), ('ex', 0.040957716117117224), ('transportChannel', 0.04094533675363158), ('handler', 0.040615097010886635), ('future', 0.039789463705197196)]
        return new ActionListener<>() {

            @Override
            public void onResponse(Response response) {
// response             0	: [('response', 0.923585756119041), ('r', 0.1262861878217158), ('t', 0.0569385100966754), ('mapped', 0.05599817139792501), ('result', 0.008645225360465993), ('searchResponse', 0.006383542076715703), ('authentication', 0.006224091221571349), ('context', 0.005966360788921221), ('releasable', 0.004115042387529257), ('getResponse', 0.003995299140833775)]
                try {
                    runBefore.run();
                } catch (Exception ex) {
// ex                   1	: [('e', 0.8288613051639053), ('ex', 0.081201122681505), ('inner', 0.03591173111125328), ('exception', 0.018725555499597753), ('t', 0.01345888064544356), ('e1', 0.009991257401865968), ('ignored', 0.009936501507702302), ('exc', 0.009749411654083261), ('e2', 0.00807451980268206), ('failure', 0.003718660243779752)]
                    delegate.onFailure(ex);
                    return;
                }
                delegate.onResponse(response);
            }

            @Override
            public void onFailure(Exception e) {
// e                    0	: [('e', 0.8707703955170831), ('listener', 0.41115957461231406), ('logger', 0.05216808291689962), ('ex', 0.051007304790344266), ('inner', 0.037120115144504896), ('failures', 0.02533841424380091), ('task', 0.025337721168935394), ('future', 0.025326900852170587), ('delegate', 0.02532512457557816), ('claimsListener', 0.025295251388439688)]
                try {
                    runBefore.run();
                } catch (Exception ex) {
// ex                   3	: [('e', 0.7460675448800174), ('inner', 0.3569926095719911), ('failure', 0.11221573558183358), ('ex', 0.081201122681505), ('e1', 0.052588071604014804), ('closeException', 0.05036414578930056), ('e2', 0.04096017359532609), ('error', 0.019374546487134714), ('exception', 0.018725555499597753), ('cause', 0.017918443702573665)]
                    e.addSuppressed(ex);
                }
                delegate.onFailure(e);
            }
        };
    }

    static <Response> ActionListener<Response> notifyOnce(ActionListener<Response> delegate) {
// delegate             3	: [('listener', 0.6877954455304167), ('latch', 0.32735037730623184), ('responses', 0.1587908467900976), ('delegate', 0.1341413988101775), ('responseRef', 0.08994015852430043), ('throwableRef', 0.07263750779802951), ('reference', 0.0465218118844943), ('action', 0.039979623814464606), ('actionLatch', 0.02325195076520525), ('failure', 0.022360313910878483)]
        return new NotifyOnceListener<Response>() {

            @Override
            protected void innerOnResponse(Response response) {
// response             0	: [('response', 0.923585756119041), ('r', 0.2059994722211647), ('t', 0.09989776094432318), ('mapped', 0.09942763528333862), ('result', 0.004322197640521997), ('searchResponse', 0.0031914652196234307), ('authentication', 0.0031117397920512537), ('context', 0.0029828964199215056), ('releasable', 0.0020572809076161553), ('getResponse', 0.0019974529726590456)]
                delegate.onResponse(response);
            }

            @Override
            protected void innerOnFailure(Exception e) {
// e                    0	: [('e', 0.9335798222822388), ('ex', 0.07550304656367497), ('inner', 0.01856000469753801), ('exception', 0.008792547662847466), ('failure', 0.003718660243779752), ('t', 0.0032082987215657547), ('source', 0.0031559860544206754), ('exc', 0.0030680986047363296), ('validationException', 0.0022875792456333983), ('error', 0.0022862803245052)]
                delegate.onFailure(e);
            }
        };
    }

    static <Response> void completeWith(ActionListener<Response> listener, CheckedSupplier<Response, ? extends Exception> supplier) {
// listener             0	: [('listener', 0.6443522030487197), ('channel', 0.20904000307691745), ('delegate', 0.17758464129187443), ('request', 0.13094533758036095), ('socket', 0.12685556822966823), ('trainedModelConfig', 0.12683531505628126), ('notified', 0.041052821468600134), ('ex', 0.040956526236579444), ('transportChannel', 0.04094414690096836), ('action', 0.039979623814464606)]
// supplier             No	: [('handler', 0.2932600605084295), ('onResponse', 0.19091771290748133), ('onAnswer', 0.18987930034551165), ('client', 0.17306365683996658), ('future', 0.15838172811558723), ('builder', 0.1573449769388346), ('cliClient', 0.15631152263500145), ('clazz', 0.0471605721742956), ('exceptionClass', 0.047102628805130185), ('runnable', 0.024028014633159962)]
        Response response;
// response             0	: [('response', 0.753514501482125), ('r', 0.03598155888320389), ('version', 0.032821652719874625), ('analyzeResponse', 0.0247403607281848), ('authenticateResponse', 0.02427320818268462), ('addRoleResponse', 0.024103373488165252), ('context', 0.019178005779926816), ('testSearchPhaseResult', 0.01885512777287641), ('startContext', 0.018064841889041203), ('result', 0.016415186045195637)]
        try {
            response = supplier.get();
        } catch (Exception e) {
// e                    0	: [('e', 0.7452682499675516), ('ex', 0.08200041982595993), ('inner', 0.03591173111125328), ('exception', 0.034364937847593445), ('validationException', 0.022012302356918482), ('exc', 0.021148375224813543), ('t', 0.019249807083873582), ('frozenSecurityIndex', 0.017859664841704002), ('failure', 0.016504855336152743), ('error', 0.015928278374963033)]
            try {
                listener.onFailure(e);
            } catch (RuntimeException ex) {
// ex                   0	: [('ex', 0.8811455999817458), ('e', 0.6697142554767457), ('message', 0.09990794935803834), ('fcr', 0.06982693816769077), ('node', 0.05246889739602835), ('r', 0.03492992199475415), ('userException', 0.0333073426810714), ('fallbackAddAlready', 0.017849154039811722), ('t', 0.017297233926548354), ('inner', 0.017292316850195256)]
                assert false : ex;
                throw ex;
            }
            return;
        }
        try {
            listener.onResponse(response);
        } catch (RuntimeException ex) {
// ex                   0	: [('ex', 0.8811455999817458), ('e', 0.6697142554767457), ('message', 0.09990794935803834), ('fcr', 0.06982693816769077), ('node', 0.05246889739602835), ('r', 0.03492992199475415), ('userException', 0.0333073426810714), ('fallbackAddAlready', 0.017849154039811722), ('t', 0.017297233926548354), ('inner', 0.017292316850195256)]
            assert false : ex;
            throw ex;
        }
    }
}
