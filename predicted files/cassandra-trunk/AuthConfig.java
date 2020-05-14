// Path id: 23
// Path to file: C:\Users\Igor\IdeaProjects\SLP-Core-0.3\java-projects\cassandra-trunk\src\java\org\apache\cassandra\auth\AuthConfig.java
// Number of identifiers: 72	Accuracy: 22.22%	MRR: 23.86%
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
// cassandra            0	: [('cassandra', 0.934863539807143), ('commons', 0.0054583661771297285), ('get', 0.0008672591694142206), ('instance', 0.0007834646226736653), ('size', 0.0006204330788324667), ('db', 0.0006038525823555616), ('add', 0.0005857900685274517), ('format', 0.0004028945298295453), ('utils', 0.00031001731666390543), ('key', 3.556955803732753e-05)]

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.cassandra.config.Config;
// cassandra            0	: [('cassandra', 0.9279434618344334), ('commons', 0.01237844414983928), ('get', 0.0008672591694142206), ('instance', 0.0007834646226736653), ('size', 0.0006204330788324667), ('db', 0.0006038525823555616), ('add', 0.0005857900685274517), ('format', 0.0004028945298295453), ('utils', 0.00031001731666390543), ('key', 3.556955803732753e-05)]
// config               5	: [('db', 0.20315650737106353), ('utils', 0.10117879409776094), ('cql3', 0.07301843853904111), ('schema', 0.06444289724198579), ('service', 0.032190484913230295), ('config', 0.030305235376919943), ('exceptions', 0.030253278308795404), ('transport', 0.02360634551469105), ('index', 0.022599126514574488), ('stress', 0.017424867667050127)]
import org.apache.cassandra.config.DatabaseDescriptor;
// cassandra            0	: [('cassandra', 0.9279434618344334), ('commons', 0.01237844414983928), ('get', 0.0008672591694142206), ('instance', 0.0007834646226736653), ('size', 0.0006204330788324667), ('db', 0.0006038525823555616), ('add', 0.0005857900685274517), ('format', 0.0004028945298295453), ('utils', 0.00031001731666390543), ('key', 3.556955803732753e-05)]
// config               5	: [('db', 0.20315650737106353), ('utils', 0.10117879409776094), ('cql3', 0.07301843853904111), ('schema', 0.06444289724198579), ('service', 0.032190484913230295), ('config', 0.030305235376919943), ('exceptions', 0.030253278308795404), ('transport', 0.02360634551469105), ('index', 0.022599126514574488), ('stress', 0.017424867667050127)]
import org.apache.cassandra.exceptions.ConfigurationException;
// cassandra            0	: [('cassandra', 0.9279434618344334), ('commons', 0.01237844414983928), ('get', 0.0008672591694142206), ('instance', 0.0007834646226736653), ('size', 0.0006204330788324667), ('db', 0.0006038525823555616), ('add', 0.0005857900685274517), ('format', 0.0004028945298295453), ('utils', 0.00031001731666390543), ('key', 3.556955803732753e-05)]
// exceptions           6	: [('db', 0.20315650737106353), ('utils', 0.10117879409776094), ('cql3', 0.07301843853904111), ('schema', 0.06444289724198579), ('service', 0.032190484913230295), ('config', 0.030305235376919943), ('exceptions', 0.030253278308795404), ('transport', 0.02360634551469105), ('index', 0.022599126514574488), ('stress', 0.017424867667050127)]
import org.apache.cassandra.utils.FBUtilities;
// cassandra            0	: [('cassandra', 0.9279434618344334), ('commons', 0.01237844414983928), ('get', 0.0008672591694142206), ('instance', 0.0007834646226736653), ('size', 0.0006204330788324667), ('db', 0.0006038525823555616), ('add', 0.0005857900685274517), ('format', 0.0004028945298295453), ('utils', 0.00031001731666390543), ('key', 3.556955803732753e-05)]
// utils                1	: [('db', 0.20315650737106353), ('utils', 0.10117879409776094), ('cql3', 0.07301843853904111), ('schema', 0.06444289724198579), ('service', 0.032190484913230295), ('config', 0.030305235376919943), ('exceptions', 0.030253278308795404), ('transport', 0.02360634551469105), ('index', 0.022599126514574488), ('stress', 0.017424867667050127)]

/**
 * Only purpose is to Initialize authentication/authorization via {@link #applyAuth()}.
 * This is in this separate class as it implicitly initializes schema stuff (via classes referenced in here).
 */
public final class AuthConfig
{
    private static final Logger logger = LoggerFactory.getLogger(AuthConfig.class);
// logger               0	: [('logger', 0.9639892280396303), ('log', 0.003478850064303441), ('wrapped', 0.0003389009373035826), ('logBackLogger', 0.0002407768761806015), ('logbackLogger', 0.00016062421305467023), ('l', 8.34065096558738e-05), ('mock', 8.069982457418279e-05), ('key', 1.4447456822598469e-05), ('put', 1.3363152256740321e-05), ('result', 1.0925505150036663e-05)]

    private static boolean initialized;
// initialized          No	: [('available', 0.2429472453667546), ('enabled', 0.12003359647911031), ('printingHeapHistogram', 0.11987145409857033), ('isServerPrepared', 0.11987143779323851), ('isEnabled', 0.0356273836608243), ('isClientMode', 0.020168888830823777), ('daemonInitialized', 0.019871462251236236), ('toolInitialized', 0.01987145409857033), ('hasLoggedConfig', 0.01987145409857033), ('equals', 0.0016872124305873824)]

    public static void applyAuth()
    {
        // some tests need this
        if (initialized)
// initialized          No	: [('logger', 0.017168419489745217), ('current', 0.01119497466286112), ('size', 0.009996941045000258), ('level', 0.009482712480223357), ('state', 0.009192795164802944), ('conf', 0.008779635956966701), ('next', 0.008290357105405478), ('buffer', 0.00816011064147954), ('idx', 0.006753895750288634), ('iterator', 0.006668046087103231)]
            return;

        initialized = true;
// initialized          No	: [('logger', 0.034684762238731306), ('count', 0.012104111612336666), ('state', 0.01017628949124867), ('conf', 0.008077347726686727), ('readLock', 0.008039206138257439), ('updateTimestamp', 0.006019789675487324), ('sb', 0.004389380577514646), ('in', 0.004110924789575118), ('start', 0.004056957100320829), ('delete', 0.004018347877070812)]

        Config conf = DatabaseDescriptor.getRawConfig();
// conf                 4	: [('config', 0.02614865170421953), ('testConfig', 0.024511713511056), ('result', 0.010847014408992883), ('loadConfig', 0.0036775323004681624), ('conf', 0.0016477994098186627), ('key', 2.8894913645196938e-05), ('put', 2.6726304513480642e-05), ('index', 2.022047711833175e-05), ('id', 1.3828787045904771e-05)]

        IAuthenticator authenticator = new AllowAllAuthenticator();
// authenticator        0	: [('authenticator', 0.8214319014475916), ('key', 5.7789827290393876e-05), ('put', 5.3452609026961284e-05), ('result', 4.370202060014665e-05), ('index', 4.04409542366635e-05), ('id', 2.7657574091809542e-05), ('right', 2.2342035919332003e-05), ('cell', 1.885269491040503e-05), ('E', 1.4515476646972437e-05), ('limit', 1.3895874037910637e-05)]

        /* Authentication, authorization and role management backend, implementing IAuthenticator, IAuthorizer & IRoleMapper*/
        if (conf.authenticator != null)
// conf                 No	: [('logger', 0.017341287606497436), ('size', 0.015833016256992936), ('next', 0.010087533376287585), ('i', 0.009820276589572482), ('state', 0.009548084524453238), ('type', 0.007323279192971929), ('savedEndpoints', 0.005747598185107028), ('length', 0.00566760340000691), ('buffer', 0.0051442313607940274), ('args', 0.0049207460634090065)]
// authenticator        No	: [('commitlog_directory', 0.06921212357567999), ('memtable_cleanup_threshold', 0.05373531259393254), ('repair_session_max_tree_depth', 0.0500409705849816), ('memtable_offheap_space_in_mb', 0.04994177270942228), ('hints_directory', 0.04036528505770847), ('memtable_heap_space_in_mb', 0.03342690972729261), ('concurrent_compactors', 0.03342690972729261), ('user_defined_function_fail_timeout', 0.03322849767084215), ('max_mutation_size_in_kb', 0.017599004238950295), ('native_transport_max_negotiable_protocol_version', 0.016515230784927927)]
            authenticator = FBUtilities.newAuthenticator(conf.authenticator);
// authenticator        No	: [('ret', 0.023894897235196083), ('builder', 0.019370392582128703), ('logger', 0.012799391901806808), ('sb', 0.005553396907467305), ('query', 0.005263616292736968), ('finishedWriters', 0.004400065897806045), ('store', 0.003792382191724705), ('next', 0.003622071688664075), ('c', 0.0032845236944875605), ('listener', 0.003166757078082525)]
// conf                 No	: [('execute', 0.00507289291982377), ('i', 0.0030401403035509775), ('cfs', 0.002533062806790327), ('value', 0.0013167191684485182), ('name', 0.001282130587162332), ('e', 0.001260923582172696), ('metadata', 0.001214663942582595), ('sstable', 0.0011297363947642355), ('keyspace', 0.0011090640993833701), ('key', 0.0010968457394369407)]
// authenticator        No	: [('get', 0.056260353246146554), ('commitlog_directory', 0.026776468949795722), ('saved_caches_directory', 0.0255859640004293), ('hints_directory', 0.025189172498192008), ('memtable_cleanup_threshold', 0.016151276304857208), ('cdc_raw_directory', 0.015755210234041066), ('commitlog_segment_size_in_mb', 0.015754419581292645), ('server_encryption_options', 0.012622219649856365), ('memtable_offheap_space_in_mb', 0.012213071822995943), ('commitlog_sync', 0.01141945620785772)]

        // the configuration options regarding credentials caching are only guaranteed to
        // work with PasswordAuthenticator, so log a message if some other authenticator
        // is in use and non-default values are detected
        if (!(authenticator instanceof PasswordAuthenticator)
// authenticator        No	: [('o', 0.38408543228816283), ('other', 0.07807053804807833), ('receiver', 0.049834856490223074), ('obj', 0.04128683393181206), ('e', 0.04058596839967083), ('parsed', 0.036455080152198775), ('previous', 0.020685969838875436), ('val', 0.012818828896232365), ('p1', 0.012791687129574084), ('parsedNumber', 0.01278988480417618)]
            && (conf.credentials_update_interval_in_ms != -1
// conf                 No	: [('now', 0.04077076900358502), ('javaType', 0.032277819726038555), ('r', 0.020563019151225204), ('right', 0.020462115590546264), ('cmp', 0.02043019839179295), ('toKey', 0.02036463832099886), ('f', 0.010369315958186109), ('from', 0.01027036543319357), ('operation', 0.010224152865648192), ('user', 0.010195250437954474)]
// credentials_update_interval_in_ms No	: [('get', 0.056260353246146554), ('commitlog_directory', 0.026776468949795722), ('saved_caches_directory', 0.0255859640004293), ('hints_directory', 0.025189172498192008), ('memtable_cleanup_threshold', 0.016151276304857208), ('cdc_raw_directory', 0.015755210234041066), ('commitlog_segment_size_in_mb', 0.015754419581292645), ('server_encryption_options', 0.012622219649856365), ('memtable_offheap_space_in_mb', 0.012213071822995943), ('commitlog_sync', 0.01141945620785772)]
                || conf.credentials_validity_in_ms != 2000
// conf                 No	: [('clock', 0.17080543333549433), ('halfByte2', 0.17080337886368532), ('comparator', 0.0865117116574358), ('key', 0.08604280688707382), ('expectedModCount', 0.08540237612144375), ('args', 0.014483603235432392), ('level', 0.01397970722299022), ('value', 0.00482342565547839), ('bytes', 0.0022230120215770283), ('c', 0.0015293015270187956)]
// credentials_validity_in_ms No	: [('data_file_directories', 0.1285740782048346), ('server_encryption_options', 0.1281882573857054), ('phi_convict_threshold', 0.12698546133304314), ('commitlog_sync_group_window_in_ms', 0.12658866983080583), ('set', 0.020053325107051448), ('get', 0.018524504189542778), ('cassandra', 0.0116542201129086), ('commitlog_directory', 0.0047638903334435235), ('disk_access_mode', 0.004366373399785078), ('memtable_cleanup_threshold', 0.0035726599526559517)]
                || conf.credentials_cache_max_entries != 1000))
// conf                 No	: [('value', 0.01929370262191356), ('bytes', 0.008892048086308113), ('c', 0.006117206108075183), ('state', 0.005775862062950353), ('comparator', 0.004777005359901954), ('cqlType', 0.00468869568277883), ('e', 0.004505448134955663), ('i', 0.004481780717243267), ('r', 0.0034281953965291305), ('otherType', 0.003012629480971424)]
// credentials_cache_max_entries No	: [('data_file_directories', 0.1285740782048346), ('server_encryption_options', 0.1281882573857054), ('phi_convict_threshold', 0.12698546133304314), ('commitlog_sync_group_window_in_ms', 0.12658866983080583), ('set', 0.020053325107051448), ('get', 0.018524504189542778), ('cassandra', 0.0116542201129086), ('commitlog_directory', 0.0047638903334435235), ('disk_access_mode', 0.004366373399785078), ('memtable_cleanup_threshold', 0.0035726599526559517)]
        {
            logger.info("Configuration options credentials_update_interval_in_ms, credentials_validity_in_ms and " +
// logger               0	: [('logger', 0.03753543036187663), ('out', 0.007998482998588718), ('result', 0.007310128618462387), ('sb', 0.006324014447941565), ('writer', 0.005099953926990576), ('builder', 0.00505167034018566), ('cluster', 0.004394650321628913), ('verifier', 0.004247276467194739), ('size', 0.003481622732197092), ('rewriter', 0.0032929151532366183)]
// info                 0	: [('info', 0.23678372639745063), ('debug', 0.19876569153491305), ('trace', 0.1808477971733763), ('warn', 0.17098958517930185), ('error', 0.15029954177926597), ('cassandra', 0.00291355502822715), ('get', 0.0008672591694142206), ('instance', 0.0007834646226736653), ('size', 0.0006204330788324667), ('db', 0.0006038525823555616)]
                        "credentials_cache_max_entries may not be applicable for the configured authenticator ({})",
                        authenticator.getClass().getName());
// authenticator        No	: [('KS_NAME', 0.04375537317908869), ('option', 0.033793830900514306), ('type', 0.02654704770728261), ('keyspace', 0.02638606231969196), ('id', 0.026133017023641576), ('session', 0.02293045071675689), ('record', 0.022532051150709113), ('cl', 0.022499158348876978), ('leftState', 0.02248337740428671), ('latestBucket', 0.022482366280047194)]
        }

        DatabaseDescriptor.setAuthenticator(authenticator);
// authenticator        0	: [('authenticator', 0.8125109504944401), ('execute', 0.0012682232299559425), ('i', 0.0007600350758877444), ('cfs', 0.0006332657016975817), ('value', 0.00032917979211212955), ('name', 0.000320532646790583), ('e', 0.000315230895543174), ('metadata', 0.00030366598564564876), ('sstable', 0.0002824340986910589), ('key', 0.00027421143485923516)]

        // authorizer

        IAuthorizer authorizer = new AllowAllAuthorizer();
// authorizer           0	: [('authorizer', 0.428578096072128), ('key', 0.0002311593091615755), ('put', 0.00021381043610784514), ('result', 0.0001748080824005866), ('index', 0.000161763816946654), ('id', 0.00011063029636723817), ('right', 8.936814367732801e-05), ('cell', 7.541077964162011e-05), ('E', 5.8061906587889746e-05), ('limit', 5.558349615164256e-05)]

        if (conf.authorizer != null)
// conf                 No	: [('logger', 0.017341287606497436), ('size', 0.015833016256992936), ('next', 0.010087533376287585), ('i', 0.009820276589572482), ('state', 0.009548084524453238), ('type', 0.007323279192971929), ('savedEndpoints', 0.005747598185107028), ('length', 0.00566760340000691), ('buffer', 0.0051442313607940274), ('args', 0.0049207460634090065)]
// authorizer           No	: [('commitlog_directory', 0.06921212357567999), ('memtable_cleanup_threshold', 0.05373531259393254), ('repair_session_max_tree_depth', 0.0500409705849816), ('memtable_offheap_space_in_mb', 0.04994177270942228), ('hints_directory', 0.04036528505770847), ('memtable_heap_space_in_mb', 0.03342690972729261), ('concurrent_compactors', 0.03342690972729261), ('user_defined_function_fail_timeout', 0.03322849767084215), ('max_mutation_size_in_kb', 0.017599004238950295), ('native_transport_max_negotiable_protocol_version', 0.016515230784927927)]
            authorizer = FBUtilities.newAuthorizer(conf.authorizer);
// authorizer           No	: [('ret', 0.023894897235196083), ('builder', 0.019370392582128703), ('logger', 0.012799391901806808), ('sb', 0.005553396907467305), ('query', 0.005263616292736968), ('finishedWriters', 0.004400065897806045), ('store', 0.003792382191724705), ('next', 0.003622071688664075), ('c', 0.0032845236944875605), ('listener', 0.003166757078082525)]
// conf                 No	: [('execute', 0.00507289291982377), ('i', 0.0030401403035509775), ('cfs', 0.002533062806790327), ('value', 0.0013167191684485182), ('name', 0.001282130587162332), ('e', 0.001260923582172696), ('metadata', 0.001214663942582595), ('sstable', 0.0011297363947642355), ('keyspace', 0.0011090640993833701), ('key', 0.0010968457394369407)]
// authorizer           No	: [('get', 0.056260353246146554), ('commitlog_directory', 0.026776468949795722), ('saved_caches_directory', 0.0255859640004293), ('hints_directory', 0.025189172498192008), ('memtable_cleanup_threshold', 0.016151276304857208), ('cdc_raw_directory', 0.015755210234041066), ('commitlog_segment_size_in_mb', 0.015754419581292645), ('server_encryption_options', 0.012622219649856365), ('memtable_offheap_space_in_mb', 0.012213071822995943), ('commitlog_sync', 0.01141945620785772)]

        if (!authenticator.requireAuthentication() && authorizer.requireAuthorization())
// authenticator        No	: [('matcher', 0.01731818284586652), ('dir', 0.013940035628938105), ('isEmpty', 0.011569762573949927), ('options', 0.009700247206019969), ('result', 0.009292544968327624), ('type', 0.007282854196390685), ('deletion', 0.007141487752059403), ('tokens', 0.006415309449876715), ('isStatic', 0.006156157423515255), ('thisType', 0.006146867231602862)]
// authorizer           No	: [('cell', 0.006711899447831435), ('column', 0.006437297946645661), ('i', 0.0052115680627316105), ('result', 0.004100601271935671), ('file', 0.0040154161281285685), ('options', 0.003564846744502588), ('type', 0.0033177023223820374), ('rightState', 0.003161745672961075), ('state', 0.002612762380278086), ('pathAsFile', 0.0025292566856481777)]
            throw new ConfigurationException(conf.authenticator + " can't be used with " + conf.authorizer, false);
// conf                 No	: [('format', 0.46764461612577785), ('REPLICATION_FACTOR', 0.08724612546388212), ('strategy', 0.043638222342588774), ('CHUNK_LENGTH_IN_KB', 0.04362303013717714), ('MIN_COMPRESS_RATIO', 0.043622880371291174), ('errMsgType', 0.04362257268685333), ('e', 0.03178869306330516), ('keyspace', 0.008046402416306313), ('execute', 0.0006341116149779712), ('i', 0.0003800175379438722)]
// authenticator        No	: [('get', 0.056260353246146554), ('commitlog_directory', 0.026776468949795722), ('saved_caches_directory', 0.0255859640004293), ('hints_directory', 0.025189172498192008), ('memtable_cleanup_threshold', 0.016151276304857208), ('cdc_raw_directory', 0.015755210234041066), ('commitlog_segment_size_in_mb', 0.015754419581292645), ('server_encryption_options', 0.012622219649856365), ('memtable_offheap_space_in_mb', 0.012213071822995943), ('commitlog_sync', 0.01141945620785772)]
// conf                 No	: [('table', 0.033875702638901314), ('uuid', 0.024858406796135057), ('myType', 0.018505786191937535), ('type', 0.014483560078245515), ('keyspace', 0.01378865575848291), ('fIntMax', 0.013500487048585008), ('f', 0.01107336547507928), ('def', 0.008562113290138337), ('str', 0.005868376788248089), ('rs', 0.004692739865999903)]
// authorizer           No	: [('server_encryption_options', 0.5156047566541186), ('repair_session_space_in_mb', 0.04531625006390682), ('memtable_cleanup_threshold', 0.030508549603548606), ('repair_session_max_tree_depth', 0.03040935988065519), ('commitlog_segment_size_in_mb', 0.030409335422657464), ('memtable_heap_space_in_mb', 0.015403271821844235), ('phi_convict_threshold', 0.015304057640953094), ('index_summary_capacity_in_mb', 0.015105661889834447), ('key_cache_size_in_mb', 0.015105653737168538), ('set', 0.005013331276762862)]

        DatabaseDescriptor.setAuthorizer(authorizer);
// authorizer           0	: [('authorizer', 0.9062505664846796), ('execute', 0.0006341116149779712), ('i', 0.0003800175379438722), ('cfs', 0.00031663285084879085), ('value', 0.00016458989605606478), ('name', 0.0001602663233952915), ('e', 0.000157615447771587), ('metadata', 0.00015183299282282438), ('sstable', 0.00014121704934552944), ('key', 0.00013710571742961758)]

        // role manager

        IRoleManager roleManager;
// roleManager          0	: [('roleManager', 0.8238660858947384), ('manager', 0.005684202491592997), ('key', 5.7789827290393876e-05), ('put', 5.3452609026961284e-05), ('result', 4.370202060014665e-05), ('index', 4.04409542366635e-05), ('id', 2.7657574091809542e-05), ('right', 2.2342035919332003e-05), ('cell', 1.885269491040503e-05), ('E', 1.4515476646972437e-05)]
        if (conf.role_manager != null)
// conf                 No	: [('value', 0.013758163514363686), ('i', 0.01020573260469836), ('logger', 0.009533322648617403), ('flush', 0.00866370006644257), ('size', 0.006955312487852871), ('options', 0.006940074066814547), ('version', 0.00692995297346855), ('type', 0.006633418369615056), ('o', 0.00549868828703157), ('cmd', 0.005397349701764888)]
// role_manager         No	: [('commitlog_directory', 0.06921212357567999), ('memtable_cleanup_threshold', 0.05373531259393254), ('repair_session_max_tree_depth', 0.0500409705849816), ('memtable_offheap_space_in_mb', 0.04994177270942228), ('hints_directory', 0.04036528505770847), ('memtable_heap_space_in_mb', 0.03342690972729261), ('concurrent_compactors', 0.03342690972729261), ('user_defined_function_fail_timeout', 0.03322849767084215), ('max_mutation_size_in_kb', 0.017599004238950295), ('native_transport_max_negotiable_protocol_version', 0.016515230784927927)]
            roleManager = FBUtilities.newRoleManager(conf.role_manager);
// roleManager          No	: [('ret', 0.023894897235196083), ('builder', 0.019370392582128703), ('logger', 0.012799391901806808), ('sb', 0.005553396907467305), ('query', 0.005263616292736968), ('finishedWriters', 0.004400065897806045), ('store', 0.003792382191724705), ('next', 0.003622071688664075), ('c', 0.0032845236944875605), ('listener', 0.003166757078082525)]
// conf                 No	: [('execute', 0.00507289291982377), ('i', 0.0030401403035509775), ('cfs', 0.002533062806790327), ('value', 0.0013167191684485182), ('name', 0.001282130587162332), ('e', 0.001260923582172696), ('metadata', 0.001214663942582595), ('sstable', 0.0011297363947642355), ('keyspace', 0.0011090640993833701), ('key', 0.0010968457394369407)]
// role_manager         No	: [('get', 0.056260353246146554), ('commitlog_directory', 0.026776468949795722), ('saved_caches_directory', 0.0255859640004293), ('hints_directory', 0.025189172498192008), ('memtable_cleanup_threshold', 0.016151276304857208), ('cdc_raw_directory', 0.015755210234041066), ('commitlog_segment_size_in_mb', 0.015754419581292645), ('server_encryption_options', 0.012622219649856365), ('memtable_offheap_space_in_mb', 0.012213071822995943), ('commitlog_sync', 0.01141945620785772)]
        else
            roleManager = new CassandraRoleManager();
// roleManager          No	: [('logger', 0.0381021334418701), ('sb', 0.02181506061919423), ('bb', 0.020065193542906243), ('builder', 0.014203054500263023), ('execute', 0.01143209821665446), ('out', 0.011326504887804874), ('size', 0.007954933347576623), ('params', 0.007597150835343045), ('r', 0.006288562106923063), ('buffer', 0.005054806225310493)]

        if (authenticator instanceof PasswordAuthenticator && !(roleManager instanceof CassandraRoleManager))
// authenticator        No	: [('logger', 0.017341287606497436), ('size', 0.015833016256992936), ('next', 0.010087533376287585), ('i', 0.009820276589572482), ('state', 0.009548084524453238), ('type', 0.007323279192971929), ('savedEndpoints', 0.005747598185107028), ('length', 0.00566760340000691), ('buffer', 0.0051442313607940274), ('args', 0.0049207460634090065)]
// roleManager          No	: [('cause', 0.08109128956901793), ('receiver', 0.06814603204514612), ('th', 0.05404151657977748), ('column', 0.02875139455647799), ('left', 0.02859834094254382), ('command', 0.027903125036554094), ('expression', 0.027756577706138326), ('dir', 0.027101101780564147), ('runnable', 0.027041825841181453), ('callable', 0.027026482067818013)]
            throw new ConfigurationException("CassandraRoleManager must be used with PasswordAuthenticator", false);

        DatabaseDescriptor.setRoleManager(roleManager);
// roleManager          0	: [('roleManager', 0.812506017273902), ('execute', 0.0012682232299559425), ('i', 0.0007600350758877444), ('cfs', 0.0006332657016975817), ('value', 0.00032917979211212955), ('name', 0.000320532646790583), ('e', 0.000315230895543174), ('metadata', 0.00030366598564564876), ('sstable', 0.0002824340986910589), ('key', 0.00027421143485923516)]

        // authenticator

        if (conf.internode_authenticator != null)
// conf                 No	: [('value', 0.015231183859283945), ('flush', 0.013810078261449511), ('logger', 0.012180324987972365), ('i', 0.010639653089135965), ('options', 0.009569886883257122), ('size', 0.00826369227996247), ('version', 0.008157168678926466), ('type', 0.007445838313936657), ('cmd', 0.007297023199230792), ('cmp', 0.0068895450259725865)]
// internode_authenticator No	: [('commitlog_directory', 0.06921212357567999), ('memtable_cleanup_threshold', 0.05373531259393254), ('repair_session_max_tree_depth', 0.0500409705849816), ('memtable_offheap_space_in_mb', 0.04994177270942228), ('hints_directory', 0.04036528505770847), ('memtable_heap_space_in_mb', 0.03342690972729261), ('concurrent_compactors', 0.03342690972729261), ('user_defined_function_fail_timeout', 0.03322849767084215), ('max_mutation_size_in_kb', 0.017599004238950295), ('native_transport_max_negotiable_protocol_version', 0.016515230784927927)]
            DatabaseDescriptor.setInternodeAuthenticator(FBUtilities.construct(conf.internode_authenticator, "internode_authenticator"));
// conf                 No	: [('className', 0.4698616135919875), ('cfs', 0.08729993236836424), ('customHandlerClass', 0.06712256309568433), ('authzProxyClass', 0.06712253048502069), ('snitchClassName', 0.06712227986924421), ('customTracingClass', 0.06712227986924421), ('cls', 0.010004639788164259), ('execute', 0.0012682232299559425), ('i', 0.0007600350758877444), ('key', 0.00027421143485923516)]
// internode_authenticator No	: [('get', 0.056260353246146554), ('commitlog_directory', 0.026776468949795722), ('saved_caches_directory', 0.0255859640004293), ('hints_directory', 0.025189172498192008), ('memtable_cleanup_threshold', 0.016151276304857208), ('cdc_raw_directory', 0.015755210234041066), ('commitlog_segment_size_in_mb', 0.015754419581292645), ('server_encryption_options', 0.012622219649856365), ('memtable_offheap_space_in_mb', 0.012213071822995943), ('commitlog_sync', 0.01141945620785772)]

        // network authorizer
        INetworkAuthorizer networkAuthorizer = FBUtilities.newNetworkAuthorizer(conf.network_authorizer);
// networkAuthorizer    0	: [('networkAuthorizer', 0.819446046098292), ('authorizer', 0.013890555764063737), ('key', 5.7789827290393876e-05), ('put', 5.3452609026961284e-05), ('result', 4.370202060014665e-05), ('index', 4.04409542366635e-05), ('id', 2.7657574091809542e-05), ('right', 2.2342035919332003e-05), ('cell', 1.885269491040503e-05), ('E', 1.4515476646972437e-05)]
// conf                 No	: [('execute', 0.00507289291982377), ('i', 0.0030401403035509775), ('cfs', 0.002533062806790327), ('value', 0.0013167191684485182), ('name', 0.001282130587162332), ('e', 0.001260923582172696), ('metadata', 0.001214663942582595), ('sstable', 0.0011297363947642355), ('keyspace', 0.0011090640993833701), ('key', 0.0010968457394369407)]
// network_authorizer   No	: [('get', 0.056260353246146554), ('commitlog_directory', 0.026776468949795722), ('saved_caches_directory', 0.0255859640004293), ('hints_directory', 0.025189172498192008), ('memtable_cleanup_threshold', 0.016151276304857208), ('cdc_raw_directory', 0.015755210234041066), ('commitlog_segment_size_in_mb', 0.015754419581292645), ('server_encryption_options', 0.012622219649856365), ('memtable_offheap_space_in_mb', 0.012213071822995943), ('commitlog_sync', 0.01141945620785772)]
        DatabaseDescriptor.setNetworkAuthorizer(networkAuthorizer);
// networkAuthorizer    0	: [('networkAuthorizer', 0.9062508497111198), ('execute', 0.0006341116149779712), ('i', 0.0003800175379438722), ('cfs', 0.00031663285084879085), ('value', 0.00016458989605606478), ('name', 0.0001602663233952915), ('e', 0.000157615447771587), ('metadata', 0.00015183299282282438), ('sstable', 0.00014121704934552944), ('key', 0.00013710571742961758)]
        if (networkAuthorizer.requireAuthorization() && !authenticator.requireAuthentication())
// networkAuthorizer    No	: [('value', 0.015231183859283945), ('flush', 0.013810078261449511), ('logger', 0.012180324987972365), ('i', 0.010639653089135965), ('options', 0.009569886883257122), ('size', 0.00826369227996247), ('version', 0.008157168678926466), ('type', 0.007445838313936657), ('cmd', 0.007297023199230792), ('cmp', 0.0068895450259725865)]
// authenticator        No	: [('hasNext', 0.033230499448609), ('def', 0.03226602127702003), ('restrictions', 0.025813405925583812), ('sstable', 0.02333694577156988), ('rowFilter', 0.019334986928327694), ('keyspaceName', 0.019294262987457175), ('s', 0.014608936012557895), ('isMultiCell', 0.014260858804093574), ('columns', 0.013586139393918308), ('cfstore', 0.012842993284114732)]
        {
            throw new ConfigurationException(conf.network_authorizer + " can't be used with " + conf.authenticator, false);
// conf                 No	: [('format', 0.505459742176198), ('e', 0.14943575188683456), ('keyspace', 0.03745816712218866), ('bind', 0.03732147416125062), ('REPLICATION_FACTOR', 0.015817554035310686), ('strategy', 0.00792393662830306), ('errMsgType', 0.007908286972567619), ('execute', 0.0006341116149779712), ('i', 0.0003800175379438722), ('cfs', 0.00031663285084879085)]
// network_authorizer   No	: [('get', 0.056260353246146554), ('commitlog_directory', 0.026776468949795722), ('saved_caches_directory', 0.0255859640004293), ('hints_directory', 0.025189172498192008), ('memtable_cleanup_threshold', 0.016151276304857208), ('cdc_raw_directory', 0.015755210234041066), ('commitlog_segment_size_in_mb', 0.015754419581292645), ('server_encryption_options', 0.012622219649856365), ('memtable_offheap_space_in_mb', 0.012213071822995943), ('commitlog_sync', 0.01141945620785772)]
// conf                 No	: [('table', 0.033875702638901314), ('uuid', 0.024858406796135057), ('myType', 0.018505786191937535), ('type', 0.014483560078245515), ('keyspace', 0.01378865575848291), ('fIntMax', 0.013500487048585008), ('f', 0.01107336547507928), ('def', 0.008562113290138337), ('str', 0.005868376788248089), ('rs', 0.004692739865999903)]
// authenticator        No	: [('server_encryption_options', 0.5156047566541186), ('repair_session_space_in_mb', 0.04531625006390682), ('memtable_cleanup_threshold', 0.030508549603548606), ('repair_session_max_tree_depth', 0.03040935988065519), ('commitlog_segment_size_in_mb', 0.030409335422657464), ('memtable_heap_space_in_mb', 0.015403271821844235), ('phi_convict_threshold', 0.015304057640953094), ('index_summary_capacity_in_mb', 0.015105661889834447), ('key_cache_size_in_mb', 0.015105653737168538), ('set', 0.005013331276762862)]
        }

        // Validate at last to have authenticator, authorizer, role-manager and internode-auth setup
        // in case these rely on each other.

        authenticator.validateConfiguration();
// authenticator        No	: [('logger', 0.20744520217752915), ('success', 0.13748550859429237), ('snitch', 0.06874064481099781), ('completed', 0.06873949528510469), ('previousSSTables', 0.06873926701045925), ('indexSummaryCapacityInMB', 0.06873926701045925), ('cfs', 0.0016979173202043257), ('sb', 0.0012506025133295145), ('out', 0.0007510649951852976), ('key', 6.812402696175381e-05)]
        authorizer.validateConfiguration();
// authorizer           No	: [('i', 0.02014531894104314), ('execute', 0.01920658274017118), ('cfs', 0.011278996057701123), ('logger', 0.00644266675299684), ('builder', 0.005875522568042387), ('sb', 0.004197089983023207), ('sessions', 0.0032433960678641223), ('result', 0.003207054760344207), ('fail', 0.0026214760034024794), ('metadata', 0.002463934766911584)]
        roleManager.validateConfiguration();
// roleManager          No	: [('i', 0.02014531894104314), ('execute', 0.01920658274017118), ('cfs', 0.011278996057701123), ('logger', 0.00644266675299684), ('builder', 0.005875522568042387), ('sb', 0.004197089983023207), ('sessions', 0.0032433960678641223), ('result', 0.003207054760344207), ('fail', 0.0026214760034024794), ('metadata', 0.002463934766911584)]
        networkAuthorizer.validateConfiguration();
// networkAuthorizer    No	: [('i', 0.02014531894104314), ('execute', 0.01920658274017118), ('cfs', 0.011278996057701123), ('logger', 0.00644266675299684), ('builder', 0.005875522568042387), ('sb', 0.004197089983023207), ('sessions', 0.0032433960678641223), ('result', 0.003207054760344207), ('fail', 0.0026214760034024794), ('metadata', 0.002463934766911584)]
        DatabaseDescriptor.getInternodeAuthenticator().validateConfiguration();
    }
}
