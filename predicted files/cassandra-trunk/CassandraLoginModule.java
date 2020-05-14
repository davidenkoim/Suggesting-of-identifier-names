// Path id: 28
// Path to file: C:\Users\Igor\IdeaProjects\SLP-Core-0.3\java-projects\cassandra-trunk\src\java\org\apache\cassandra\auth\CassandraLoginModule.java
// Number of identifiers: 112	Accuracy: 27.68%	MRR: 32.39%
// True 	Rank of true in predictions : [(predicted token, probability of a token), ...]

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.cassandra.auth;
// cassandra            0	: [('cassandra', 0.9348640646840355), ('commons', 0.005458082365631626), ('get', 0.0008672819336833296), ('instance', 0.0007833277778742879), ('size', 0.0006204491290237848), ('db', 0.0006038676914666704), ('add', 0.0005856485840216505), ('format', 0.0004027482919851435), ('utils', 0.00031017359140200323), ('key', 3.5571266347964865e-05)]

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.security.auth.Subject;
import javax.security.auth.callback.*;
// callback             1	: [('login', 0.5437512847679778), ('callback', 0.36250330224393007), ('cassandra', 0.0029137884699450426), ('get', 0.0008672819336833296), ('instance', 0.0007833277778742879), ('size', 0.0006204491290237848), ('db', 0.0006038676914666704), ('add', 0.0005856485840216505), ('format', 0.0004027482919851435), ('utils', 0.00031017359140200323)]
import javax.security.auth.login.FailedLoginException;
// login                0	: [('login', 0.5437512847679778), ('callback', 0.36250330224393007), ('cassandra', 0.0029137884699450426), ('get', 0.0008672819336833296), ('instance', 0.0007833277778742879), ('size', 0.0006204491290237848), ('db', 0.0006038676914666704), ('add', 0.0005856485840216505), ('format', 0.0004027482919851435), ('utils', 0.00031017359140200323)]
import javax.security.auth.login.LoginException;
// login                0	: [('login', 0.5437512847679778), ('callback', 0.36250330224393007), ('cassandra', 0.0029137884699450426), ('get', 0.0008672819336833296), ('instance', 0.0007833277778742879), ('size', 0.0006204491290237848), ('db', 0.0006038676914666704), ('add', 0.0005856485840216505), ('format', 0.0004027482919851435), ('utils', 0.00031017359140200323)]
import javax.security.auth.spi.LoginModule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.cassandra.config.DatabaseDescriptor;
// cassandra            0	: [('cassandra', 0.9279444363298025), ('commons', 0.01237771071986462), ('get', 0.0008672819336833296), ('instance', 0.0007833277778742879), ('size', 0.0006204491290237848), ('db', 0.0006038676914666704), ('add', 0.0005856485840216505), ('format', 0.0004027482919851435), ('utils', 0.00031017359140200323), ('key', 3.5571266347964865e-05)]
// config               5	: [('db', 0.2031454515286003), ('utils', 0.10122437821448092), ('cql3', 0.07301446513434233), ('schema', 0.06443939101640948), ('service', 0.0321376432957094), ('config', 0.030354676387617633), ('exceptions', 0.030251632159957662), ('transport', 0.023605060831813685), ('index', 0.022597898334428403), ('stress', 0.017423919544712615)]
import org.apache.cassandra.exceptions.AuthenticationException;
// cassandra            0	: [('cassandra', 0.9279444363298025), ('commons', 0.01237771071986462), ('get', 0.0008672819336833296), ('instance', 0.0007833277778742879), ('size', 0.0006204491290237848), ('db', 0.0006038676914666704), ('add', 0.0005856485840216505), ('format', 0.0004027482919851435), ('utils', 0.00031017359140200323), ('key', 3.5571266347964865e-05)]
// exceptions           6	: [('db', 0.2031454515286003), ('utils', 0.10122437821448092), ('cql3', 0.07301446513434233), ('schema', 0.06443939101640948), ('service', 0.0321376432957094), ('config', 0.030354676387617633), ('exceptions', 0.030251632159957662), ('transport', 0.023605060831813685), ('index', 0.022597898334428403), ('stress', 0.017423919544712615)]
import org.apache.cassandra.service.StorageService;
// cassandra            0	: [('cassandra', 0.9279444363298025), ('commons', 0.01237771071986462), ('get', 0.0008672819336833296), ('instance', 0.0007833277778742879), ('size', 0.0006204491290237848), ('db', 0.0006038676914666704), ('add', 0.0005856485840216505), ('format', 0.0004027482919851435), ('utils', 0.00031017359140200323), ('key', 3.5571266347964865e-05)]
// service              4	: [('db', 0.2031454515286003), ('utils', 0.10122437821448092), ('cql3', 0.07301446513434233), ('schema', 0.06443939101640948), ('service', 0.0321376432957094), ('config', 0.030354676387617633), ('exceptions', 0.030251632159957662), ('transport', 0.023605060831813685), ('index', 0.022597898334428403), ('stress', 0.017423919544712615)]

/**
 * LoginModule which authenticates a user towards the Cassandra database using
 * the internal authentication mechanism.
 */
public class CassandraLoginModule implements LoginModule
{
    private static final Logger logger = LoggerFactory.getLogger(CassandraLoginModule.class);
// logger               0	: [('logger', 0.9639892211295439), ('log', 0.0034788502080268457), ('wrapped', 0.0003389010426542339), ('logBackLogger', 0.00024077687897134723), ('logbackLogger', 0.00016062421375235666), ('l', 8.340675942761658e-05), ('mock', 8.069984271403002e-05), ('key', 1.4448662424753566e-05), ('put', 1.3347958339409634e-05), ('result', 1.0926409351652986e-05)]

    // initial state
    private Subject subject;
// subject              0	: [('subject', 0.8295479367765242), ('key', 5.779464969901426e-05), ('put', 5.3391833357638537e-05), ('result', 4.3705637406611944e-05), ('index', 4.044429196855585e-05), ('id', 2.7659817851375975e-05), ('right', 2.2343824787344545e-05), ('cell', 1.8854185168624526e-05), ('E', 1.4516595736009924e-05), ('limit', 1.3896940102779266e-05)]
    private CallbackHandler callbackHandler;
// callbackHandler      0	: [('callbackHandler', 0.5000060153097526), ('key', 0.00023117859879605702), ('put', 0.00021356733343055417), ('result', 0.00017482254962644778), ('index', 0.0001617771678742234), ('id', 0.0001106392714055039), ('right', 8.937529914937818e-05), ('cell', 7.54167406744981e-05), ('E', 5.806638294403969e-05), ('limit', 5.5587760411117064e-05)]

    // the authentication status
    private boolean succeeded = false;
// succeeded            No	: [('hasNext', 0.015876166478787545), ('isSet', 0.014466446228305598), ('caseSensitive', 0.011554775979064619), ('initialized', 0.010920067200943292), ('closed', 0.010899124790831409), ('isClosed', 0.010855674524797373), ('returned', 0.010790988327562031), ('startInclusive', 0.0073530066711041476), ('sorted', 0.007202626639612818), ('overflow', 0.0071800861702362865)]
    private boolean commitSucceeded = false;
// commitSucceeded      No	: [('extendedVerify', 0.038811931597396473), ('quick', 0.03878500347530587), ('checkVersion', 0.038610293338647815), ('verifySSTables', 0.03843016007209911), ('verifyTokens', 0.03843014376537192), ('invalidateCaches', 0.038424712482117665), ('clearRepaired', 0.03842470432875407), ('compressed', 0.038409127974987675), ('extendedVerification', 0.038408565392899614), ('modified', 0.038397670212936726)]

    // username and password
    private String username;
// username             1	: [('keyspace', 0.3985667764729367), ('username', 0.38191897525355534), ('name', 0.011704951244269781), ('password', 0.0069081644581777), ('cfName', 0.006682999240713692), ('scope', 0.003278428167337911), ('user', 0.00324892109511312), ('operation', 0.0032389337894586233), ('key', 0.0003185073138502145), ('put', 1.3347958339409634e-05)]
    private char[] password;
// password             0	: [('password', 0.6283440992399223), ('username', 0.1283442786139213), ('chars', 0.029847560901418338), ('c', 0.013299039463260593), ('result', 0.007556973882250091), ('array', 0.006875437637251652), ('ch', 0.006633263096316144), ('byteToChar', 0.006633100029044241), ('btree', 0.0023129209792411127), ('bytes', 0.0022967950673048743)]

    private CassandraPrincipal principal;
// principal            0	: [('principal', 0.541669674321543), ('that', 0.041702483456649835), ('key', 0.00011558929939802853), ('put', 0.00010678366671527707), ('result', 8.741127481322389e-05), ('index', 8.08885839371117e-05), ('id', 5.531963570275195e-05), ('right', 4.468764957468909e-05), ('cell', 3.770837033724905e-05), ('E', 2.9033191472019847e-05)]

    /**
     * Initialize this {@code}LoginModule{@code}.
     *
     * @param subject the {@code}Subject{@code} to be authenticated. <p>
     * @param callbackHandler a {@code}CallbackHandler{@code} for communicating
     *        with the end user (prompting for user names and passwords, for example)
     * @param sharedState shared {@code}LoginModule{@code} state. This param is unused.
     * @param options options specified in the login {@code}Configuration{@code} for this particular
     *        {@code}LoginModule{@code}. This param is unused
     */
    @Override
    public void initialize(Subject subject,
// subject              0	: [('subject', 0.957386984194131), ('key', 1.4448662424753566e-05), ('put', 1.3347958339409634e-05), ('result', 1.0926409351652986e-05), ('index', 1.0111072992138963e-05), ('id', 6.914954462843994e-06), ('right', 5.585956196836136e-06), ('cell', 4.7135462921561315e-06), ('E', 3.629148934002481e-06), ('limit', 3.4742350256948165e-06)]
                           CallbackHandler callbackHandler,
// callbackHandler      0	: [('callbackHandler', 0.9687503759568595), ('key', 1.4448662424753566e-05), ('put', 1.3347958339409634e-05), ('result', 1.0926409351652986e-05), ('index', 1.0111072992138963e-05), ('id', 6.914954462843994e-06), ('right', 5.585956196836136e-06), ('cell', 4.7135462921561315e-06), ('E', 3.629148934002481e-06), ('limit', 3.4742350256948165e-06)]
                           Map<java.lang.String, ?> sharedState,
// sharedState          2	: [('options', 0.16933823207534504), ('p', 0.1689122809377481), ('sharedState', 0.168856723889395), ('type', 0.01810538962090685), ('action', 0.01758654050954755), ('map', 0.013386583298846871), ('replicaPlan', 0.013272079632672433), ('mapType', 0.01313689359559109), ('mt', 0.008763244061210505), ('resolver', 0.006582625446782608)]
                           Map<java.lang.String, ?> options)
// options              0	: [('options', 0.16933823207534504), ('p', 0.1689122809377481), ('sharedState', 0.168856723889395), ('type', 0.01810538962090685), ('action', 0.01758654050954755), ('map', 0.013386583298846871), ('replicaPlan', 0.013272079632672433), ('mapType', 0.01313689359559109), ('mt', 0.008763244061210505), ('resolver', 0.006582625446782608)]
    {
        this.subject = subject;
// subject              5	: [('options', 0.20133566243174308), ('cfs', 0.10643386694384102), ('out', 0.05185333512628119), ('klass', 0.05049147142913544), ('visits', 0.05026668535300776), ('subject', 0.05024481114409682), ('type', 0.012478210450663512), ('name', 0.009197653065344822), ('keyspace', 0.006030348114259009), ('cassandra', 0.0029137884699450426)]
// subject              0	: [('subject', 0.9062521227810578), ('loginContext', 0.031251127068504775), ('cfs', 0.00040089838099981683), ('keyspace', 0.0002334900353042313), ('restrictions', 0.00022979528778567317), ('builder', 0.0002121103189920258), ('in', 0.00018620134129885052), ('sstable', 0.00016924088386294984), ('row', 0.00016854898595958372), ('metadata', 0.0001660470413380965)]
        this.callbackHandler = callbackHandler;
// callbackHandler      No	: [('type', 0.008766197382868225), ('metadata', 0.007639352899542257), ('name', 0.007224171358505402), ('keyspace', 0.006592607519266215), ('comparator', 0.005588789775441642), ('options', 0.005162564204522691), ('values', 0.0048779325421604985), ('key', 0.0040698071973340275), ('version', 0.0037244083044475082), ('ifNotExists', 0.0032242589528728507)]
// callbackHandler      No	: [('cfs', 0.0032071870479985346), ('keyspace', 0.00186792028243385), ('restrictions', 0.0018383623022853854), ('builder', 0.0016968825519362064), ('in', 0.0014896107303908042), ('sstable', 0.0013539270709035987), ('row', 0.0013483918876766698), ('metadata', 0.001328376330704772), ('options', 0.0011976787271211066), ('iter', 0.0010991711462420102)]
    }

    /**
     * Authenticate the user, obtaining credentials from the CallbackHandler
     * supplied in {@code}initialize{@code}. As long as the configured
     * {@code}IAuthenticator{@code} supports the optional
     * {@code}legacyAuthenticate{@code} method, it can be used here.
     *
     * @return true in all cases since this {@code}LoginModule{@code}
     *         should not be ignored.
     * @exception FailedLoginException if the authentication fails.
     * @exception LoginException if this {@code}LoginModule{@code} is unable to
     * perform the authentication.
     */
    @Override
    public boolean login() throws LoginException
// login                No	: [('equals', 0.4699182152450069), ('selectColumns', 0.04539549876802495), ('isEmpty', 0.042477690659027435), ('hasNext', 0.038397115288066344), ('contains', 0.030624439034242844), ('remove', 0.024340401305644247), ('isInclusive', 0.021432555714679124), ('accepts', 0.01536296217283138), ('isFrozen', 0.015179635888619742), ('test', 0.012199069845052516)]
    {
        // prompt for a user name and password
        if (callbackHandler == null)
// callbackHandler      No	: [('value', 0.007565999023320909), ('i', 0.007295440430120028), ('logger', 0.0049240358421853364), ('isEnabled', 0.004717818983446651), ('bytes', 0.0036674716973354744), ('type', 0.0035024542547803976), ('buffer', 0.0026537536589241175), ('options', 0.002615453633825947), ('sstable', 0.0025681730334697235), ('e', 0.0024427733117151932)]
        {
            logger.info("No CallbackHandler available for authentication");
// logger               0	: [('logger', 0.1032795541882877), ('result', 0.013774408258535308), ('conf', 0.012403576135436937), ('builder', 0.01181738703293489), ('type', 0.011688284453112072), ('sb', 0.009762977478952705), ('results', 0.009372584112212659), ('i', 0.004865482194266783), ('value', 0.004769801651581131), ('error', 0.004741621637146196)]
// info                 0	: [('info', 0.22408954956773125), ('warn', 0.2202222904078663), ('error', 0.17585627976059143), ('trace', 0.1699789332423694), ('debug', 0.14753905947831614), ('cassandra', 0.0029137884699450426), ('get', 0.0008672819336833296), ('instance', 0.0007833277778742879), ('size', 0.0006204491290237848), ('db', 0.0006038676914666704)]
            throw new LoginException("Authentication failed");
        }

        NameCallback nc = new NameCallback("username: ");
// nc                   No	: [('key', 0.00023117859879605702), ('put', 0.00021356733343055417), ('result', 0.00017482254962644778), ('index', 0.0001617771678742234), ('id', 0.0001106392714055039), ('right', 8.937529914937818e-05), ('cell', 7.54167406744981e-05), ('E', 5.806638294403969e-05), ('limit', 5.5587760411117064e-05), ('end', 5.337004551323892e-05)]
        PasswordCallback pc = new PasswordCallback("password: ", false);
// pc                   No	: [('key', 0.00023117859879605702), ('put', 0.00021356733343055417), ('result', 0.00017482254962644778), ('index', 0.0001617771678742234), ('id', 0.0001106392714055039), ('right', 8.937529914937818e-05), ('cell', 7.54167406744981e-05), ('E', 5.806638294403969e-05), ('limit', 5.5587760411117064e-05), ('end', 5.337004551323892e-05)]
        try
        {
            callbackHandler.handle(new Callback[]{nc, pc});
// callbackHandler      No	: [('comparator', 0.17151335792907907), ('client', 0.09902060172319388), ('iterator', 0.08412764706156331), ('dataFreeBytes', 0.0841095226417212), ('preparedStatementsCacheSizeInMB', 0.0841095144883576), ('execute', 0.01071337337777855), ('buffer', 0.010209704343022088), ('test', 0.009625068134926829), ('executor', 0.006491220159032974), ('logger', 0.003974852557069569)]
// handle               No	: [('cassandra', 0.04662061551912068), ('get', 0.013876510938933273), ('instance', 0.012533244445988606), ('size', 0.009927186064380557), ('db', 0.009661883063466726), ('add', 0.009370377344346408), ('format', 0.006443972671762295), ('utils', 0.004962777462432052), ('put', 0.0045761287972329555), ('build', 0.004372674257775966)]
// nc                   No	: [('objectName', 0.06412572491138974), ('i', 0.02838561145999612), ('dataFolder1', 0.016033031993514572), ('name', 0.012118722471624752), ('key', 0.01210515527460244), ('p', 0.01204760722971531), ('addr1', 0.012026218042626437), ('TEST_PROP', 0.012024717823724931), ('type', 0.00811223364042066), ('host', 0.008025786865687098)]
// pc                   No	: [('row', 0.03330555866161867), ('i', 0.004784986100274604), ('version', 0.0037706945167299837), ('map', 0.003581549277207161), ('cfs', 0.0032794529789768943), ('list', 0.0029564036501344483), ('set', 0.002898462426690808), ('e', 0.0025680580064829607), ('bytes', 0.0025434281831648625), ('key', 0.0024748321352644743)]
            username = nc.getName();
// username             No	: [('execute', 0.03375491736077095), ('t', 0.022681738159663477), ('tokenFactory', 0.010339354143705026), ('add', 0.009094549330443579), ('n2', 0.008617091151414525), ('rangeB', 0.008616014907419967), ('builder', 0.0058043704330062295), ('logger', 0.0056107604122614145), ('outbound', 0.0052967792510269325), ('input', 0.0036464753887992807)]
// nc                   No	: [('opts', 0.2596313361316185), ('username', 0.028852814808282702), ('credentials', 0.019237644184740527), ('settings', 0.009704382626571225), ('EMPTY', 0.009648857302561266), ('strings', 0.009619157282411828), ('cfs', 0.0008017967619996337), ('keyspace', 0.0004669800706084626), ('restrictions', 0.00045959057557134634), ('builder', 0.0004242206379840516)]
            char[] tmpPassword = pc.getPassword();
// tmpPassword          No	: [('chars', 0.5548475609014183), ('fileName', 0.12831714566113164), ('result', 0.05755697388225009), ('array', 0.031875437637251654), ('ch', 0.031633263096316144), ('c', 0.013299039463260593), ('byteToChar', 0.006633100029044241), ('btree', 0.0023129209792411127), ('bytes', 0.0022967950673048743), ('values', 0.0019417592791416334)]
// pc                   No	: [('cfs', 0.006414374095997069), ('keyspace', 0.0037358405648677006), ('restrictions', 0.0036767246045707708), ('builder', 0.003393765103872413), ('in', 0.0029792214607816084), ('sstable', 0.0027078541418071975), ('row', 0.0026967837753533396), ('metadata', 0.002656752661409544), ('options', 0.002395357454242213), ('iter', 0.0021983422924840203)]
            if (tmpPassword == null)
// tmpPassword          No	: [('logger', 0.01731815916711384), ('size', 0.01581002692441237), ('next', 0.010072597135344261), ('i', 0.009809953000293806), ('state', 0.009534626697980412), ('type', 0.007315116942671569), ('savedEndpoints', 0.005738317264257729), ('length', 0.005658941579963507), ('buffer', 0.0051371273790170174), ('args', 0.0049136573025532065)]
                tmpPassword = new char[0];
// tmpPassword          No	: [('savedEndpoints', 0.009326817998754943), ('params', 0.00777548636495745), ('logger', 0.007308301143213827), ('conf', 0.0059624342953999016), ('builder', 0.005890998437663383), ('t', 0.004609663145920071), ('sb', 0.004336170768144946), ('next', 0.003975653795199976), ('current', 0.002679228492263294), ('keyspace', 0.002625134941052406)]
            password = new char[tmpPassword.length];
// password             4	: [('execute', 0.022852262825583093), ('node', 0.021708603218673267), ('view', 0.02109753770387648), ('nextLogicalStart', 0.021093568428423134), ('password', 0.02109341884264345), ('uncompressedBuffer', 0.021091410289964075), ('hash1', 0.021090862601985717), ('remainingPart', 0.021090781068349766), ('random', 0.0027896511627996316), ('reader', 0.002525867172762816)]
// tmpPassword          No	: [('length', 0.25323658045484165), ('newLength', 0.12657599068256728), ('size', 0.06389864044295926), ('bytes', 0.06332477089657179), ('sigchars', 0.06327786678249271), ('indent', 0.06326781070184), ('readahead', 0.06326722365966114), ('izer', 0.06326720735293395), ('i', 0.011609001157028571), ('rows', 0.0012232760473571019)]
// length               No	: [('cassandra', 0.04662061551912068), ('get', 0.013876510938933273), ('instance', 0.012533244445988606), ('size', 0.009927186064380557), ('db', 0.009661883063466726), ('add', 0.009370377344346408), ('format', 0.006443972671762295), ('utils', 0.004962777462432052), ('put', 0.0045761287972329555), ('build', 0.004372674257775966)]
            System.arraycopy(tmpPassword, 0, password, 0, tmpPassword.length);
// tmpPassword          No	: [('a', 0.1640812735790241), ('src', 0.06759429441925495), ('data', 0.04826827108733738), ('tmp', 0.04555196372171094), ('from', 0.03872240845796404), ('bounds', 0.03310627787456175), ('buildKeys', 0.03304900993585142), ('buffer', 0.02628327768076414), ('memBuffer', 0.022033619133840898), ('primaryKeyValues', 0.022032647123799216)]
// password             No	: [('map', 0.0379626044712647), ('set', 0.026756473735988457), ('i', 0.019836240006163824), ('tuple', 0.019567179098876654), ('size', 0.01698354594185232), ('list', 0.01676172392668205), ('key', 0.015224374652194601), ('bytes', 0.014907278891365604), ('value1', 0.013566930933617832), ('tmd', 0.00935099692514491)]
// tmpPassword          No	: [('initialResponse', 0.5006621217216625), ('map', 0.01898130223563235), ('set', 0.013378236867994229), ('i', 0.009918120003081912), ('tuple', 0.009783589549438327), ('size', 0.00849177297092616), ('list', 0.008380861963341025), ('key', 0.0076121873260973005), ('bytes', 0.007453639445682802), ('value1', 0.006783465466808916)]
// length               No	: [('cassandra', 0.04662061551912068), ('get', 0.013876510938933273), ('instance', 0.012533244445988606), ('size', 0.009927186064380557), ('db', 0.009661883063466726), ('add', 0.009370377344346408), ('format', 0.006443972671762295), ('utils', 0.004962777462432052), ('put', 0.0045761287972329555), ('build', 0.004372674257775966)]
            pc.clearPassword();
// pc                   No	: [('out', 0.035800224978258084), ('cb', 0.01621835759932443), ('buf', 0.00897508365261529), ('rows', 0.007684428261065685), ('bytes', 0.0074536723468547955), ('memory', 0.007359497266243526), ('res', 0.007344745021496356), ('uncompressed', 0.007335226576017351), ('retBuf', 0.007328026511034336), ('ret', 0.004007301936387283)]
        }
        catch (IOException | UnsupportedCallbackException e)
// e                    No	: [('key', 0.00023117859879605702), ('put', 0.00021356733343055417), ('result', 0.00017482254962644778), ('index', 0.0001617771678742234), ('id', 0.0001106392714055039), ('right', 8.937529914937818e-05), ('cell', 7.54167406744981e-05), ('E', 5.806638294403969e-05), ('limit', 5.5587760411117064e-05), ('end', 5.337004551323892e-05)]
        {
            logger.info("Unexpected exception processing authentication callbacks", e);
// logger               0	: [('logger', 0.1309134236463632), ('e', 0.023340033287831626), ('fail', 0.016777963532135627), ('errorMsg', 0.012063374935610499), ('sstable', 0.008848950679138882), ('options', 0.0061496853026345825), ('readMetrics', 0.005162825662289359), ('onException', 0.005151951450321073), ('writeMetrics', 0.004307191276806227), ('failure', 0.0034912568912532907)]
// info                 2	: [('error', 0.3235574291858788), ('warn', 0.2558544743159123), ('info', 0.14190564152175425), ('trace', 0.10934674933432346), ('debug', 0.10702181809900582), ('cassandra', 0.0029137884699450426), ('get', 0.0008672819336833296), ('instance', 0.0007833277778742879), ('size', 0.0006204491290237848), ('db', 0.0006038676914666704)]
// e                    8	: [('sessionID', 0.043243530442936724), ('value', 0.031243975726053707), ('endpoint', 0.030512643765926464), ('mvPrimaryKeys', 0.022421199355802664), ('legacyVersion', 0.0213951429763391), ('id', 0.01929746375764434), ('session', 0.019057426209983016), ('keyspace', 0.01781808137739811), ('e', 0.016656949655156492), ('descriptor', 0.014378043939067634)]
            throw new LoginException("Authentication failed");
        }

        // verify the credentials
        try
        {
            authenticate();
        }
        catch (AuthenticationException e)
// e                    0	: [('e', 0.9423180724416831), ('key', 1.4448662424753566e-05), ('put', 1.3347958339409634e-05), ('result', 1.0926409351652986e-05), ('index', 1.0111072992138963e-05), ('id', 6.914954462843994e-06), ('right', 5.585956196836136e-06), ('cell', 4.7135462921561315e-06), ('E', 3.629148934002481e-06), ('limit', 3.4742350256948165e-06)]
        {
            // authentication failed -- clean up
            succeeded = false;
// succeeded            No	: [('authFailed', 0.4587629078231907), ('logger', 0.0327283559115908), ('e', 0.0058350083219579066), ('fail', 0.004194490883033907), ('errorMsg', 0.0030158437339026247), ('sstable', 0.0022122376697847205), ('options', 0.0015374213256586456), ('readMetrics', 0.0012907064155723397), ('onException', 0.0012879878625802683), ('writeMetrics', 0.0010767978192015568)]
            cleanUpInternalState();
            throw new FailedLoginException(e.getMessage());
// e                    5	: [('execute', 0.010146203330982304), ('i', 0.006080045464720288), ('cfs', 0.005066337948297919), ('value', 0.0026335514896604797), ('name', 0.0025643724819004315), ('e', 0.0025192939664653376), ('metadata', 0.0024294381721171473), ('sstable', 0.002259571030848279), ('keyspace', 0.002218226863556609), ('key', 0.0021937860150078176)]
        }

        succeeded = true;
// succeeded            No	: [('sb', 0.05328257454693066), ('out', 0.035638979155466315), ('writer', 0.029700370717701267), ('logger', 0.020403403964161557), ('metrics', 0.01775870829963991), ('buffer', 0.017564566196906804), ('result', 0.01746174538396067), ('expected', 0.0170513302047091), ('option', 0.016969926507813742), ('cfs', 0.0162686705549443)]
        return true;
    }

    private void authenticate()
    {
        if (!StorageService.instance.isAuthSetupComplete())
// instance             0	: [('instance', 0.9367514650327762), ('cassandra', 0.0029137884699450426), ('RING_DELAY', 0.001380431950852435), ('get', 0.0008672819336833296), ('size', 0.0006204491290237848), ('db', 0.0006038676914666704), ('add', 0.0005856485840216505), ('format', 0.0004027482919851435), ('useStrictConsistency', 0.00015401464674136032), ('key', 3.5571266347964865e-05)]
// isAuthSetupComplete  8	: [('isShutdown', 0.046211751507873176), ('valueFactory', 0.030197108072140773), ('serialize', 0.005581014396756803), ('isBootstrapMode', 0.005284937288221022), ('repair', 0.0015592527771351725), ('isSurveyMode', 0.0015101889178691773), ('onChange', 0.0007627221567592114), ('isMoving', 0.0007560669578416031), ('isAuthSetupComplete', 0.000755885593655369), ('rebuild', 0.0007556696258276131)]
            throw new AuthenticationException("Cannot login as server authentication setup is not yet completed");

        IAuthenticator authenticator = DatabaseDescriptor.getAuthenticator();
// authenticator        0	: [('authenticator', 0.8214319668363637), ('key', 5.779464969901426e-05), ('put', 5.3391833357638537e-05), ('result', 4.3705637406611944e-05), ('index', 4.044429196855585e-05), ('id', 2.7659817851375975e-05), ('right', 2.2343824787344545e-05), ('cell', 1.8854185168624526e-05), ('E', 1.4516595736009924e-05), ('limit', 1.3896940102779266e-05)]
        Map<String, String> credentials = new HashMap<>();
// credentials          9	: [('options', 0.24644310586202978), ('configOptions', 0.052465812039957184), ('entry', 0.04597004316643021), ('map', 0.034328516481862255), ('asMap', 0.018253927512364165), ('uncheckedOptions', 0.018249388484347217), ('parameters', 0.016033408170144342), ('builder', 0.014100561237611215), ('s', 0.011648663218111721), ('credentials', 0.011405872217699118)]
        credentials.put(PasswordAuthenticator.USERNAME_KEY, username);
// credentials          No	: [('options', 0.033588523051010295), ('repairs', 0.027774744588878564), ('ranges', 0.026824548583924978), ('tokenizer', 0.02369706531916828), ('ret', 0.01991790379916763), ('configOptions', 0.017093114631281117), ('map', 0.014984870045941153), ('excludeSet', 0.01493814079559763), ('datacenters', 0.014933715807000997), ('includeSet', 0.012818073042102225)]
// put                  1	: [('get', 0.40693825546946666), ('put', 0.10228806439861649), ('cassandra', 0.02331030775956034), ('instance', 0.006266622222994303), ('size', 0.004963593032190279), ('db', 0.004830941531733363), ('add', 0.004685188672173204), ('format', 0.003221986335881148), ('utils', 0.002481388731216026), ('key', 0.0002845701307837189)]
// USERNAME_KEY         0	: [('USERNAME_KEY', 0.33333605978008074), ('PASSWORD_KEY', 0.33333602716662636), ('NUL', 0.08333599455317195), ('cassandra', 0.01165515387978017), ('get', 0.0034691277347333183), ('instance', 0.0031333111114971515), ('size', 0.0024817965160951394), ('db', 0.0024154707658666816), ('add', 0.002342594336086602), ('format', 0.001610993167940574)]
// username             No	: [('row', 0.03330555866161867), ('i', 0.004784986100274604), ('version', 0.0037706945167299837), ('map', 0.003581549277207161), ('cfs', 0.0032794529789768943), ('list', 0.0029564036501344483), ('set', 0.002898462426690808), ('e', 0.0025680580064829607), ('bytes', 0.0025434281831648625), ('key', 0.0024748321352644743)]
        credentials.put(PasswordAuthenticator.PASSWORD_KEY, String.valueOf(password));
// credentials          No	: [('out', 0.06316578001666422), ('conf', 0.06210517647286541), ('execute', 0.007427286859079635), ('i', 0.002823933187803644), ('cfs', 0.001328876101215926), ('builder', 0.001183972158083871), ('logger', 0.0010871671477114633), ('sb', 0.0007673470998103243), ('flush', 0.0006890605449586765), ('key', 0.0001353479627064208)]
// put                  1	: [('get', 0.40693825546946666), ('put', 0.10228806439861649), ('cassandra', 0.02331030775956034), ('instance', 0.006266622222994303), ('size', 0.004963593032190279), ('db', 0.004830941531733363), ('add', 0.004685188672173204), ('format', 0.003221986335881148), ('utils', 0.002481388731216026), ('key', 0.0002845701307837189)]
// PASSWORD_KEY         1	: [('USERNAME_KEY', 0.33333605978008074), ('PASSWORD_KEY', 0.33333602716662636), ('NUL', 0.08333599455317195), ('cassandra', 0.01165515387978017), ('get', 0.0034691277347333183), ('instance', 0.0031333111114971515), ('size', 0.0024817965160951394), ('db', 0.0024154707658666816), ('add', 0.002342594336086602), ('format', 0.001610993167940574)]
// password             No	: [('i', 0.22289220979849947), ('j', 0.1518221714520618), ('r', 0.08354975925516088), ('tokens', 0.020480323315853706), ('ss', 0.02045594417083645), ('JMX_PORT', 0.02045121846541599), ('size', 0.01031739872059795), ('protocolVersion', 0.010273476940269786), ('k', 0.010244039424657032), ('unit', 0.010233942509740952)]
        AuthenticatedUser user = authenticator.legacyAuthenticate(credentials);
// user                 0	: [('user', 0.8649666821170168), ('u', 0.0258839362012998), ('performer', 0.02376861845515694), ('SYSTEM_USER', 0.0008811477509502601), ('ANONYMOUS_USER', 0.0008810662173143087), ('key', 2.889732484950713e-05), ('put', 2.6695916678819268e-05), ('result', 2.1852818703305972e-05), ('index', 2.0222145984277927e-05), ('id', 1.3829908925687988e-05)]
// authenticator        No	: [('state', 0.7495215602046419), ('conn', 0.10403642282202454), ('negotiator', 0.02069994643492915), ('user', 0.014209998816728065), ('builder', 0.0030530194099011167), ('entry', 0.0029371695731148463), ('cmd', 0.002913132998272846), ('source', 0.0028620685663439107), ('clientState', 0.002848729935117739), ('cfs', 0.00040089838099981683)]
// credentials          No	: [('execute', 0.010146203330982304), ('i', 0.006080045464720288), ('cfs', 0.005066337948297919), ('value', 0.0026335514896604797), ('name', 0.0025643724819004315), ('e', 0.0025192939664653376), ('metadata', 0.0024294381721171473), ('sstable', 0.002259571030848279), ('keyspace', 0.002218226863556609), ('key', 0.0021937860150078176)]
        // Only actual users should be allowed to authenticate for JMX
        if (user.isAnonymous() || user.isSystem())
// user                 No	: [('value', 0.015224204320406632), ('flush', 0.01380298741133507), ('logger', 0.012174503419582577), ('i', 0.010635180034729571), ('options', 0.009565239739334664), ('size', 0.00825976633759383), ('version', 0.008153274958055689), ('type', 0.007442453112656705), ('cmd', 0.007293455380848369), ('cmp', 0.006886097804692008)]
// user                 No	: [('canLogin', 0.7507207969543728), ('value', 0.017991312697385513), ('rightState', 0.002881644125368942), ('entry', 0.0023452798126114894), ('restrictions', 0.002324269513851928), ('right', 0.0021872558985899216), ('e', 0.0019580259320697503), ('str', 0.0016651590528988492), ('cfs', 0.001548528846624693), ('row', 0.0014819756972729086)]
            throw new AuthenticationException(String.format("Invalid user %s", user.getName()));
// format               0	: [('format', 0.927704007522), ('cassandra', 0.0029137884699450426), ('get', 0.0008672819336833296), ('instance', 0.0007833277778742879), ('join', 0.0006893864320826738), ('size', 0.0006204491290237848), ('db', 0.0006038676914666704), ('add', 0.0005856485840216505), ('utils', 0.00031017359140200323), ('key', 3.5571266347964865e-05)]
// user                 No	: [('KEYSPACE', 0.056150999561618134), ('value', 0.030165135908236173), ('KS_NAME', 0.02766433546038286), ('keyspace', 0.027603651732150594), ('name', 0.02267367262452515), ('i', 0.020330701325819562), ('source', 0.015536848268578462), ('parsed', 0.015344292210707431), ('indexName', 0.01485755728719007), ('legacyVersion', 0.013484801273073494)]

        // The LOGIN privilege is required to authenticate - c.f. ClientState::login
        if (!DatabaseDescriptor.getRoleManager().canLogin(user.getPrimaryRole()))
// canLogin             No	: [('setup', 0.3752005292094774), ('supportedOptions', 0.18752407796225518), ('alterableOptions', 0.18752392921152333), ('size', 0.013657306139657838), ('get', 0.009977928444968844), ('iterator', 0.006742765448890258), ('next', 0.0066576064998755315), ('equals', 0.006223236602629174), ('map', 0.005741353181300753), ('contains', 0.0052545669965397696)]
// user                 3	: [('role', 0.24525445471660337), ('ROLE_A', 0.16346812850216913), ('resource', 0.08176348374469074), ('user', 0.01924893407632328), ('execute', 0.002536550832745576), ('i', 0.001520011366180072), ('cfs', 0.0012665844870744798), ('value', 0.0006583878724151199), ('name', 0.0006410931204751079), ('e', 0.0006298234916163344)]
            throw new AuthenticationException(user.getName() + " is not permitted to log in");
// user                 No	: [('msg', 0.07495847989188326), ('execute', 0.002536550832745576), ('i', 0.001520011366180072), ('cfs', 0.0012665844870744798), ('value', 0.0006583878724151199), ('name', 0.0006410931204751079), ('e', 0.0006298234916163344), ('metadata', 0.0006073595430292868), ('sstable', 0.0005648927577120697), ('key', 0.0005484465037519544)]
    }

    /**
     * This method is called if the LoginContext's overall authentication succeeded
     * (the relevant REQUIRED, REQUISITE, SUFFICIENT and OPTIONAL LoginModules
     * succeeded).
     *
     * If this LoginModule's own authentication attempt succeeded (checked by
     * retrieving the private state saved by the {@code}login{@code} method),
     * then this method associates a {@code}CassandraPrincipal{@code}
     * with the {@code}Subject{@code}.
     * If this LoginModule's own authentication attempted failed, then this
     * method removes any state that was originally saved.
     *
     * @return true if this LoginModule's own login and commit attempts succeeded, false otherwise.
     * @exception LoginException if the commit fails.
     */
    @Override
    public boolean commit() throws LoginException
// commit               No	: [('equals', 0.4699182152450069), ('selectColumns', 0.04539549876802495), ('isEmpty', 0.042477690659027435), ('hasNext', 0.038397115288066344), ('contains', 0.030624439034242844), ('remove', 0.024340401305644247), ('isInclusive', 0.021432555714679124), ('accepts', 0.01536296217283138), ('isFrozen', 0.015179635888619742), ('test', 0.012199069845052516)]
    {
        if (!succeeded)
// succeeded            No	: [('subject', 0.5005251216443607), ('iter', 0.005722164485194213), ('hasNext', 0.005293497570980902), ('o1', 0.005124971599574597), ('cfs', 0.004464608971075251), ('sstable', 0.004051330130827929), ('condition', 0.0032662024469416557), ('buffer', 0.003148842030456622), ('directory', 0.0030602972157654554), ('input', 0.0027614631142647765)]
        {
            return false;
        }
        else
        {
            // add a Principal (authenticated identity)
            // to the Subject
            principal = new CassandraPrincipal(username);
// principal            No	: [('sb', 0.1754573976350631), ('builder', 0.08874204635722165), ('isActive', 0.08397924119368674), ('firstFound', 0.08397309854280584), ('openSslIsAvailable', 0.08397309854280584), ('logger', 0.03336510365365401), ('out', 0.008458878543959438), ('result', 0.006365370212133935), ('key', 0.0006605815490472095), ('put', 7.471502782581448e-05)]
// username             No	: [('roleName', 0.5833374291875493), ('execute', 0.002536550832745576), ('i', 0.001520011366180072), ('cfs', 0.0012665844870744798), ('value', 0.0006583878724151199), ('name', 0.0006410931204751079), ('e', 0.0006298234916163344), ('metadata', 0.0006073595430292868), ('sstable', 0.0005648927577120697), ('key', 0.0005484465037519544)]
            if (!subject.getPrincipals().contains(principal))
// subject              No	: [('matcher', 0.017315456955511104), ('dir', 0.01393831348586858), ('isEmpty', 0.01156785575696894), ('options', 0.009699411947848176), ('result', 0.009291197479578088), ('type', 0.007282533037587321), ('deletion', 0.0071403242245751085), ('tokens', 0.006414566432786104), ('isStatic', 0.0061551394265635904), ('thisType', 0.0061458467637236225)]
// contains             1	: [('add', 0.5048004063247445), ('contains', 0.25525456699653976), ('size', 0.013657306139657838), ('get', 0.009977928444968844), ('iterator', 0.006742765448890258), ('next', 0.0066576064998755315), ('equals', 0.006223236602629174), ('build', 0.0059083601304208856), ('map', 0.005741353181300753), ('stream', 0.0038136923651707487)]
// principal            No	: [('hosts', 0.10148648553062452), ('sstable', 0.021758135038829157), ('keyspace', 0.018228835628494866), ('replica', 0.016411850191718324), ('ep', 0.014950470714816656), ('indexName', 0.014532053926242376), ('r', 0.012442405127147725), ('a', 0.01180709248535409), ('hostAddress', 0.01093883961677494), ('errorMessage', 0.010731924771607471)]
                subject.getPrincipals().add(principal);
// subject              0	: [('subject', 0.8750030705779748), ('logger', 0.0005070703232552923), ('service', 0.0001882163187017522), ('builder', 0.00018493157303864), ('sb', 0.00014528045414550174), ('size', 0.00012238902624655073), ('r', 9.319763254541333e-05), ('tokens', 5.906560252207274e-05), ('original', 5.823340375407234e-05), ('flags', 4.5789783473090466e-05)]
// add                  0	: [('add', 0.5048004063247445), ('contains', 0.25525456699653976), ('size', 0.013657306139657838), ('get', 0.009977928444968844), ('iterator', 0.006742765448890258), ('next', 0.0066576064998755315), ('equals', 0.006223236602629174), ('build', 0.0059083601304208856), ('map', 0.005741353181300753), ('stream', 0.0038136923651707487)]
// principal            No	: [('metadata', 0.02484557525042462), ('filter', 0.0166793375052198), ('columnName', 0.01658785952747928), ('types', 0.016585089175867925), ('single', 0.016519465915437737), ('indexes', 0.016471461879057216), ('tables', 0.01646865485374241), ('transform', 0.016468313292358344), ('triggers', 0.01646440380318433), ('udfs', 0.016463535655672806)]

            cleanUpInternalState();
            commitSucceeded = true;
// commitSucceeded      No	: [('i', 0.020141232385917435), ('execute', 0.019206808992392344), ('cfs', 0.011277713328097363), ('logger', 0.0064420601468553615), ('builder', 0.005875085362394396), ('sb', 0.00419674101784088), ('sessions', 0.003242983941985282), ('result', 0.0032067514061910026), ('fail', 0.0026212014157010717), ('metadata', 0.0024636613975817963)]
            return true;
        }
    }

    /**
     * This method is called if the LoginContext's  overall authentication failed.
     * (the relevant REQUIRED, REQUISITE, SUFFICIENT and OPTIONAL LoginModules
     * did not succeed).
     *
     * If this LoginModule's own authentication attempt succeeded (checked by
     * retrieving the private state saved by the {@code}login{@code} and
     * {@code}commit{@code} methods), then this method cleans up any state that
     * was originally saved.
     *
     * @return false if this LoginModule's own login and/or commit attempts failed, true otherwise.
     * @throws LoginException if the abort fails.
     */
    @Override
    public boolean abort() throws LoginException
    {
        if (!succeeded)
// succeeded            No	: [('subject', 0.5005251216443607), ('iter', 0.005722164485194213), ('hasNext', 0.005293497570980902), ('o1', 0.005124971599574597), ('cfs', 0.004464608971075251), ('sstable', 0.004051330130827929), ('condition', 0.0032662024469416557), ('buffer', 0.003148842030456622), ('directory', 0.0030602972157654554), ('input', 0.0027614631142647765)]
        {
            return false;
        }
        else if (!commitSucceeded)
// commitSucceeded      No	: [('components', 0.08274258326998309), ('h', 0.08223662111313537), ('allowFiltering', 0.046441550516596514), ('dir', 0.04241420203095033), ('success', 0.0417865091609248), ('metadata', 0.041752528567974644), ('tokens', 0.04167958070652456), ('key', 0.04138819062598625), ('expression', 0.041191741383704815), ('user', 0.041038520835740236)]
        {
            // login succeeded but overall authentication failed
            succeeded = false;
// succeeded            No	: [('logger', 0.016064135428319623), ('out', 0.003268644160215751), ('conf', 0.0032462519479454152), ('execute', 0.0024581176312778496), ('builder', 0.002342207884373936), ('result', 0.002202165296735768), ('sb', 0.002092697158485035), ('size', 0.001375161130625804), ('fail', 0.0013525372337627858), ('writer', 0.001196717767926418)]
            cleanUpInternalState();
            principal = null;
// principal            No	: [('i', 0.020141232385917435), ('execute', 0.019206808992392344), ('cfs', 0.011277713328097363), ('logger', 0.0064420601468553615), ('builder', 0.005875085362394396), ('sb', 0.00419674101784088), ('sessions', 0.003242983941985282), ('result', 0.0032067514061910026), ('fail', 0.0026212014157010717), ('metadata', 0.0024636613975817963)]
        }
        else
        {
            // overall authentication succeeded and commit succeeded,
            // but someone else's commit failed
            logout();
// logout               No	: [('logger', 0.183365103653654), ('expected', 0.10257184209817483), ('gen', 0.05064059448678176), ('viewLockAcquireTime', 0.05063979782292689), ('nextKey', 0.05063978966956329), ('reducer', 0.050639343600343), ('nextb', 0.05063913976625312), ('sb', 0.008790730968396446), ('out', 0.008458878543959438), ('key', 0.0006605815490472095)]
        }
        return true;
    }

    /**
     * Logout the user.
     *
     * This method removes the principal that was added by the
     * {@code}commit{@code} method.
     *
     * @return true in all cases since this {@code}LoginModule{@code}
     *         should not be ignored.
     * @throws LoginException if the logout fails.
     */
    @Override
    public boolean logout() throws LoginException
// logout               No	: [('equals', 0.4699182152450069), ('selectColumns', 0.04539549876802495), ('isEmpty', 0.042477690659027435), ('hasNext', 0.038397115288066344), ('contains', 0.030624439034242844), ('remove', 0.024340401305644247), ('isInclusive', 0.021432555714679124), ('accepts', 0.01536296217283138), ('isFrozen', 0.015179635888619742), ('test', 0.012199069845052516)]
    {
        subject.getPrincipals().remove(principal);
// subject              No	: [('logger', 0.0044889211637151516), ('out', 0.001314813667325067), ('execute', 0.0010296869347947446), ('conf', 0.0008655341204735733), ('builder', 0.0007495897515134623), ('result', 0.0006916408820639791), ('cfs', 0.0006175343360893544), ('sb', 0.0005985914874509827), ('put', 0.0005977202226065158), ('buffer', 0.00048330783582851283)]
// remove               No	: [('add', 0.5048004063247445), ('contains', 0.25525456699653976), ('size', 0.013657306139657838), ('get', 0.009977928444968844), ('iterator', 0.006742765448890258), ('next', 0.0066576064998755315), ('equals', 0.006223236602629174), ('build', 0.0059083601304208856), ('map', 0.005741353181300753), ('stream', 0.0038136923651707487)]
// principal            No	: [('key', 0.179670515721545), ('EP5', 0.0865190667610414), ('R5', 0.08651749566448684), ('m', 0.049894451676653566), ('elements', 0.030194605176866035), ('factory', 0.02412842102916098), ('table', 0.015920624706019952), ('token', 0.015524637395398659), ('current', 0.01549965689563044), ('metric', 0.015094329173133556)]
        succeeded = false;
// succeeded            No	: [('execute', 0.029709147436318542), ('i', 0.011295732751214576), ('cfs', 0.005315504404863704), ('builder', 0.004735888632335484), ('out', 0.004542819314777236), ('logger', 0.004348668590845853), ('sb', 0.0030693883992412973), ('flush', 0.002756242179834706), ('put', 0.0024997390180176793), ('options', 0.0020176530742227546)]
        cleanUpInternalState();
        principal = null;
// principal            No	: [('i', 0.020141232385917435), ('execute', 0.019206808992392344), ('cfs', 0.011277713328097363), ('logger', 0.0064420601468553615), ('builder', 0.005875085362394396), ('sb', 0.00419674101784088), ('sessions', 0.003242983941985282), ('result', 0.0032067514061910026), ('fail', 0.0026212014157010717), ('metadata', 0.0024636613975817963)]
        return true;
    }

    private void cleanUpInternalState()
    {
        username = null;
// username             No	: [('logger', 0.010088114013276474), ('queue', 0.0017650004297186546), ('lock', 0.00171794522178588), ('delegate', 0.0015355283763267512), ('cfs', 0.001415059413739618), ('test', 0.0012374317087728765), ('ssProxy', 0.0011927156980311262), ('buffer', 0.0011874370659298355), ('state', 0.0011381559419008109), ('sum', 0.0007710203720127328)]
        if (password != null)
// password             No	: [('version', 0.04029180316006593), ('flags', 0.023563635281000448), ('in', 0.02350176585509085), ('javaType', 0.023203980118874062), ('settings', 0.016084801222624925), ('args', 0.016040310772568202), ('begin', 0.015525783053130076), ('globalTablesSpec', 0.015500899123819625), ('stringConstructor', 0.01546622982482106), ('type', 0.009369812121723739)]
        {
            for (int i = 0; i < password.length; i++)
// i                    0	: [('i', 0.6519819198099175), ('j', 0.1085661570687903), ('ii', 0.021872311987539535), ('n', 0.018953378124422878), ('perUnitCount', 0.010396855705242599), ('p', 0.010330321211500853), ('size', 0.008189031859438077), ('ck2', 0.007910203968844776), ('start', 0.005758660422800708), ('row', 0.005454599649161132)]
// i                    0	: [('i', 0.8257033482200926), ('j', 0.011765230996653777), ('ii', 0.002882165552691586), ('k', 0.0012527790722870764), ('row', 0.0009953886563495921), ('next', 0.0005630965699583348), ('bb1', 0.0005424054141616665), ('st', 0.0005422538706264139), ('rtm', 0.0005417435790929751), ('key', 2.7998884329564054e-05)]
// password             No	: [('size', 0.049089614651667966), ('count', 0.03799220905861999), ('values', 0.01978887949087293), ('length', 0.013852355943602254), ('n', 0.0092526886376789), ('mvPrimaryKeys', 0.005992996377062622), ('types', 0.005396178713448137), ('bytes', 0.004988327949562049), ('prefix', 0.0049049478087744725), ('elements', 0.00479634430530345)]
// length               0	: [('length', 0.16882354896935906), ('isEmpty', 0.16826408289215217), ('get', 0.09027158880279997), ('value', 0.08399587490127226), ('cassandra', 0.02331030775956034), ('instance', 0.006266622222994303), ('size', 0.004963593032190279), ('db', 0.004830941531733363), ('add', 0.004685188672173204), ('key', 0.0002845701307837189)]
// i                    0	: [('i', 0.47960494848458196), ('j', 0.024816662035615777), ('n', 0.006131364423702513), ('index', 0.004205594313672589), ('out', 0.0026149770311223617), ('size', 0.0023802522357895565), ('b', 0.0020923344033531464), ('level', 0.0020485015224874985), ('holder', 0.002042782867031021), ('mergeCounters', 0.002034194227589054)]
                password[i] = ' ';
// password             No	: [('builder', 0.018300904561126707), ('values', 0.010478681078085039), ('clusteringByteBuffers', 0.009956153451094196), ('r', 0.008442311710721444), ('bbs', 0.0074676343488444385), ('params', 0.004989685051378229), ('hash', 0.004981240494213176), ('arr', 0.0049783217775220346), ('estimatedColumnCountPercentiles', 0.0049782810107040585), ('estimatedRowSizePercentiles', 0.004978272857340463)]
// i                    0	: [('i', 0.09287200925622857), ('rows', 0.009786208378856815), ('j', 0.006942025503685422), ('size', 0.005141711835885088), ('idx', 0.004349844417026726), ('index', 0.00344681759914107), ('depth', 0.002038667226560115), ('length', 0.001702996807577039), ('keyEnd', 0.0016108550522168843), ('dest', 0.0015457999574627348)]
            password = null;
// password             No	: [('i', 0.016791687682972484), ('execute', 0.014102120417628632), ('cfs', 0.0027825532530497267), ('builder', 0.0024456782644834128), ('logger', 0.0024358855059598804), ('out', 0.002333658481530293), ('j', 0.0017516524269532588), ('sb', 0.0015220382357310186), ('put', 0.001306087185834098), ('flush', 0.0012997104211585992)]
        }
    }
}
