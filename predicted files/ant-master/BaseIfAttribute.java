// Path id: 32
// Path to file: C:\Users\Igor\IdeaProjects\SLP-Core-0.3\java-projects\ant-master\src\main\org\apache\tools\ant\attribute\BaseIfAttribute.java
// Number of identifiers: 33	Accuracy: 36.36%	MRR: 42.95%
// True 	Rank of true in predictions : [(predicted token, probability of a token), ...]

/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package org.apache.tools.ant.attribute;
// ant                  0	: [('ant', 0.9196116854750406), ('zip', 0.020565263662061323), ('junit', 0.0008935936006135493), ('types', 0.0007476032133654589), ('length', 0.0006857790021004308), ('equals', 0.0005338424898166148), ('append', 0.0004877619428399717), ('add', 0.00046642865432687313), ('getProperty', 0.00044208123514173674), ('resources', 0.00015621090834775243)]
// attribute            7	: [('types', 0.27917897445755985), ('filters', 0.013964932341924202), ('input', 0.005215467037664922), ('property', 0.004595225783334795), ('ant', 0.0027546314955146974), ('helper', 0.0022948288389208317), ('listener', 0.0020847213634003162), ('attribute', 0.00146930464932513), ('junit', 0.0008935936006135493), ('loader', 0.0004209181745313921)]

import java.util.Map;
// java                 0	: [('java', 0.5058895972921971), ('junit', 0.008002968481622063), ('jdepend', 0.0001396363268355909), ('resources', 3.953068912229624e-05), ('srcDir', 2.9135255305992467e-05), ('separator', 2.8498800174382033e-05), ('expected', 2.7438041621697974e-05), ('values', 2.340715912149855e-05), ('os', 2.086133859505681e-05)]
import java.util.stream.Collectors;
// java                 0	: [('java', 0.9216073872020827), ('junit', 0.014005995113847576), ('jdepend', 1.7454540854448863e-05), ('resources', 4.94133614028703e-06), ('srcDir', 3.6419069132490584e-06), ('separator', 3.562350021797754e-06), ('expected', 3.429755202712247e-06), ('values', 2.925894890187319e-06), ('os', 2.6076673243821013e-06)]
// stream               0	: [('stream', 0.23009034856260907), ('zip', 0.11818608034445036), ('jar', 0.047997706444798054), ('regexp', 0.02168290969223407), ('regex', 0.020006621785662447), ('facade', 0.005913452756956908), ('depend', 0.005439904959347331), ('ant', 0.0027546314955146974), ('junit', 0.0008935936006135493), ('types', 0.0007476032133654589)]

import org.apache.tools.ant.ProjectComponent;
// ant                  0	: [('ant', 0.9196116854750406), ('zip', 0.020565263662061323), ('junit', 0.0008935936006135493), ('types', 0.0007476032133654589), ('length', 0.0006857790021004308), ('equals', 0.0005338424898166148), ('append', 0.0004877619428399717), ('add', 0.00046642865432687313), ('getProperty', 0.00044208123514173674), ('resources', 0.00015621090834775243)]
import org.apache.tools.ant.UnknownElement;
// ant                  0	: [('ant', 0.9196116854750406), ('zip', 0.020565263662061323), ('junit', 0.0008935936006135493), ('types', 0.0007476032133654589), ('length', 0.0006857790021004308), ('equals', 0.0005338424898166148), ('append', 0.0004877619428399717), ('add', 0.00046642865432687313), ('getProperty', 0.00044208123514173674), ('resources', 0.00015621090834775243)]


/**
 * An abstract class for if/unless attributes.
 * This contains a boolean flag to specify whether this is an
 * if or unless attribute.
 * @since Ant 1.9.1
 */
public abstract class BaseIfAttribute
    extends ProjectComponent implements EnableAttribute {
    private boolean positive = true;
// positive             No	: [('binary', 0.03136342345744367), ('reverse', 0.030742651759481657), ('echoString', 0.03021161609615639), ('failOnError', 0.020554635737057122), ('value', 0.016930110086468195), ('initialized', 0.016524769342816974), ('vmLauncher', 0.01550594589492575), ('editsBlocked', 0.015151302792049568), ('byLine', 0.015107151614009662), ('previousWasEOL', 0.015063212587680293)]
    /**
     * Set the positive flag.
     * @param positive the value to use.
     */
    protected void setPositive(boolean positive) {
// positive             No	: [('b', 0.08588455715592869), ('value', 0.022029364605959056), ('verbose', 0.0132376138425013), ('append', 0.011243951109223629), ('debug', 0.009675121343725335), ('enable', 0.008165495221947075), ('recursive', 0.008140188607416431), ('caseSensitive', 0.0073256767326414455), ('followSymlinks', 0.0069716187014800535), ('aBoolean', 0.006351804995237083)]
        this.positive = positive;
// positive             No	: [('name', 0.027525561427799997), ('value', 0.01744005796362841), ('classpath', 0.016615781136424177), ('file', 0.013938336297713844), ('project', 0.012640810046649005), ('encoding', 0.011715660719372864), ('destDir', 0.00807504061503292), ('task', 0.006885247811024794), ('dir', 0.006296233118421997), ('verbose', 0.006123216561120283)]
// positive             No	: [('buildRule', 0.005508019679417245), ('b', 0.004441199049973731), ('name', 0.004369875465611886), ('value', 0.004072290564695339), ('p', 0.003599852044674984), ('file', 0.003381581426283099), ('project', 0.0031391458177007538), ('line', 0.002763755035578428), ('FILE_UTILS', 0.002606190263616176), ('path', 0.0024556802946628705)]
    }

    /**
     * Get the positive flag.
     * @return the flag.
     */
    protected boolean isPositive() {
        return positive;
// positive             No	: [('name', 0.03089249956703641), ('value', 0.01829989054604035), ('file', 0.011100117098473092), ('project', 0.010822322658020645), ('mComment', 0.006062922504762423), ('VALUES', 0.006062869466834788), ('linkFlag', 0.0055255069789097635), ('classpath', 0.00523096048370582), ('pattern', 0.005169809965562078), ('fileset', 0.005154263495217092)]
    }

    /**
     * convert the result.
     * @param val the result to convert
     * @return val if positive or !val if not.
     */
    protected boolean convertResult(boolean val) {
// val                  No	: [('b', 0.08588455715592869), ('value', 0.022029364605959056), ('verbose', 0.0132376138425013), ('append', 0.011243951109223629), ('debug', 0.009675121343725335), ('enable', 0.008165495221947075), ('recursive', 0.008140188607416431), ('caseSensitive', 0.0073256767326414455), ('followSymlinks', 0.0069716187014800535), ('aBoolean', 0.006351804995237083)]
        return positive == val;
// positive             No	: [('name', 0.022084624602237615), ('value', 0.01337880730930917), ('file', 0.010964279140766411), ('project', 0.007412435578213577), ('map', 0.005009046427782911), ('line', 0.00455585611651502), ('mComment', 0.0038861446349930107), ('VALUES', 0.0038860385591377414), ('o', 0.0038859705588191054), ('fileset', 0.0035669539567263183)]
// val                  No	: [('other', 0.004111721445201123), ('block', 0.002933650345956623), ('sep', 0.0029124351749029416), ('DEFLATED', 0.0020378982981673816), ('SEND_FILES', 0.002037473994746308), ('s', 0.0019605177990906835), ('o', 0.0018175275461888725), ('name', 0.0016356111290867642), ('args', 0.0012825906827535094), ('sig', 0.0011680287590636312)]
    }

    /**
     * Get all the attributes in the ant-attribute:param
     * namespace and place them in a map.
     * @param el the element this attribute is in.
     * @return a map of attributes.
     */
    protected Map<String, String> getParams(UnknownElement el) {
// el                   1	: [('ue', 0.08436585304817881), ('el', 0.06921348928982152), ('child', 0.033733212778470724), ('u', 0.016053050674334456), ('owner', 0.01604817118499211), ('unknownElement2', 0.016044564605912984), ('ret', 0.007599376886589612), ('resources', 3.953068912229624e-05), ('junit', 3.634841346424407e-05), ('srcDir', 2.9135255305992467e-05)]
        // this makes a copy!
        return el.getWrapper().getAttributeMap().entrySet().stream()
// el                   No	: [('name', 0.022084624602237615), ('value', 0.01337880730930917), ('file', 0.010964279140766411), ('project', 0.007412435578213577), ('map', 0.005009046427782911), ('line', 0.00455585611651502), ('mComment', 0.0038861446349930107), ('VALUES', 0.0038860385591377414), ('o', 0.0038859705588191054), ('fileset', 0.0035669539567263183)]
// entrySet             No	: [('equals', 0.7592049013414589), ('setValue', 0.04391104877365739), ('getProperty', 0.033474620676495294), ('log', 0.0062758123556105145), ('map', 0.005345092696083296), ('length', 0.0032571130471183785), ('size', 0.0028910223413301343), ('ant', 0.0027546314955146974), ('iterator', 0.0025717775114403093), ('stream', 0.0023540736672712755)]
// stream               0	: [('stream', 0.5678302641434617), ('iterator', 0.11566701560667839), ('setValue', 0.04391104877365739), ('getProperty', 0.033474620676495294), ('equals', 0.009204901341458942), ('log', 0.0062758123556105145), ('map', 0.005345092696083296), ('length', 0.0032571130471183785), ('size', 0.0028910223413301343), ('ant', 0.0027546314955146974)]
                .filter(e -> e.getKey().startsWith("ant-attribute:param"))
// filter               1	: [('map', 0.3631907245875167), ('filter', 0.09397687245290781), ('collect', 0.0816563768343647), ('setValue', 0.04391104877365739), ('getProperty', 0.033474620676495294), ('limit', 0.020029782668984763), ('sorted', 0.020018849879510157), ('skip', 0.01338972336715869), ('equals', 0.009204901341458942), ('log', 0.0062758123556105145)]
// e                    0	: [('e', 0.12612267874043762), ('name', 0.05095878279127917), ('className', 0.050697927621531184), ('test', 0.04207977358298625), ('def', 0.04201342330826071), ('p', 0.01752208592253701), ('f', 0.017464770641509468), ('image', 0.011306978170843503), ('buildRule', 0.0003093288189381039), ('resources', 1.9719905498496668e-05)]
// e                    0	: [('e', 0.8579215380562701), ('log', 0.014191215127422519), ('forkedLaunch', 0.013585299755161552), ('msg', 0.013497078321035355), ('typeClassDefinitions', 0.01348944085945603), ('taskClassDefinitions', 0.01348944085945603), ('l', 0.004992520998847285), ('s', 0.0013563005512812075), ('p', 0.0008776160089213934), ('name', 0.000796925960519254)]
                .collect(Collectors.toMap(e -> e.getKey().substring(e.getKey().lastIndexOf(':') + 1),
// collect              9	: [('count', 0.5319319147262123), ('toList', 0.031851878030687494), ('password', 0.03184846391707567), ('subject', 0.031841035596805844), ('body', 0.03184022430762902), ('mimeType', 0.03184017126970139), ('ssl', 0.031839704727054184), ('toBccList', 0.03183853297108542), ('map', 0.018352737638659744), ('collect', 0.015159021972349175)]
// e                    3	: [('file', 0.3127069659640938), ('je', 0.3125077702790002), ('name', 0.15654988286767332), ('e', 0.1563843288550289), ('buildRule', 0.0003093288189381039), ('project', 0.00023834432504506352), ('p', 0.00019651933226453466), ('s', 0.00014632967954016706), ('resources', 1.9719905498496668e-05), ('junit', 7.880647989723007e-06)]
// e                    0	: [('e', 0.10792153805627022), ('log', 0.014191215127422519), ('forkedLaunch', 0.013585299755161552), ('msg', 0.013497078321035355), ('typeClassDefinitions', 0.01348944085945603), ('taskClassDefinitions', 0.01348944085945603), ('l', 0.004992520998847285), ('s', 0.0013563005512812075), ('p', 0.0008776160089213934), ('name', 0.000796925960519254)]
// substring            No	: [('setValue', 0.17564419509462956), ('getProperty', 0.13389848270598118), ('equals', 0.03681960536583577), ('log', 0.025103249422442058), ('map', 0.021380370784333184), ('length', 0.013028452188473514), ('size', 0.011564089365320537), ('add', 0.010465669942214897), ('iterator', 0.010287110045761237), ('stream', 0.009416294669085102)]
// e                    No	: [('i', 0.10800633784497357), ('rootLength', 0.10469697211082642), ('start', 0.027209452128769827), ('index', 0.009570446517046348), ('pos', 0.00425964711009405), ('startOfFileName', 0.0031793741682899154), ('filename', 0.0021572188227816725), ('windowStart', 0.002121219415772381), ('name', 0.0008289867628959455), ('buildRule', 0.0003093288189381039)]
                        e -> el.getProject().replaceProperties((String) e.getValue()), (a, b) -> b));
// e                    4	: [('s', 0.4177039456905407), ('zOut', 0.2089626452308896), ('result', 0.08492386028094988), ('HEX', 0.04175460911501251), ('e', 0.0034645804961346972), ('f', 0.0031666513380421103), ('name', 0.0029928846808207413), ('p', 0.0025331954570768384), ('selectorRule', 0.0023046715151801424), ('ioe', 0.0018610925034300602)]
// el                   No	: [('e', 0.14597186651079522), ('log', 0.021050574795404366), ('l', 0.01997008399538914), ('forkedLaunch', 0.0186269133063605), ('msg', 0.018274027569855716), ('typeClassDefinitions', 0.018243477723538414), ('taskClassDefinitions', 0.018243477723538414), ('s', 0.00542520220512483), ('p', 0.0035104640356855735), ('name', 0.003187703842077016)]
// e                    No	: [('arg1', 0.14653358745300182), ('arg2', 0.14653332226336366), ('k', 0.1347292658079673), ('entry', 0.09099029458210112), ('map', 0.06737044936592991), ('pair', 0.06736381962497563), ('o', 0.011870415219208957), ('t', 4.429963661596907e-05), ('task', 3.690551793215722e-05), ('instr', 3.402120202626253e-05)]
// a                    1	: [('k', 0.5656159476046648), ('a', 0.04366716261160895), ('i', 0.03460830757658408), ('ze', 0.006283943957695349), ('u', 0.0046579906468000205), ('currRecIdx', 0.004639449451104256), ('error', 0.0031330049649965597), ('buildRule', 0.0006186576378762078), ('name', 0.0005997657353467226), ('project', 0.000476688650090127)]
// b                    0	: [('b', 0.8103233869763061), ('result', 0.04448855915517553), ('v', 0.031750439753951844), ('s2', 0.006361541812590593), ('u', 0.005080640847934696), ('d', 0.0034003292598475633), ('dummy', 0.0033828922852092557), ('buildRule', 0.00045006142859175765), ('l', 0.0003765899855819971), ('e', 0.0003654910432959767)]
// b                    0	: [('b', 0.7852211099166073), ('a', 0.09815521547195721), ('l', 0.004992520998847285), ('name', 0.004130259293852588), ('cmdl', 0.0027885666976481035), ('p', 0.0017109493422547267), ('s', 0.0013563005512812075), ('o', 0.0011262491410474415), ('project', 0.0009482416539298399), ('props', 0.0009323567946033962)]
    }
}
