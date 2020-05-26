// Type of training: selfTestingIdentifier
// Path to file: C:\Users\Igor\IdeaProjects\SLP-Core-0.3\java-projects\cassandra-trunk\src\java\org\apache\cassandra\tools\LoaderOptions.java
// Number of identifiers: 57	Accuracy: 54.39%	MRR: 59.27%
// True 	Rank of true in predictions : [(predicted token, probability of a token), ...]

package org.apache.cassandra.tools;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.*;
import java.util.HashSet;
import java.util.Set;
import com.google.common.base.Throwables;
import com.google.common.net.HostAndPort;
import org.apache.cassandra.config.*;
import org.apache.cassandra.exceptions.ConfigurationException;
import org.apache.cassandra.locator.InetAddressAndPort;
import org.apache.cassandra.tools.BulkLoader.CmdLineOptions;
import com.datastax.driver.core.AuthProvider;
import com.datastax.driver.core.PlainTextAuthProvider;
import org.apache.commons.cli.*;
import org.apache.commons.lang3.StringUtils;

public class LoaderOptions {

    public static final String HELP_OPTION = "help";

    public static final String VERBOSE_OPTION = "verbose";

    public static final String NOPROGRESS_OPTION = "no-progress";

    public static final String NATIVE_PORT_OPTION = "port";

    public static final String STORAGE_PORT_OPTION = "storage-port";

    public static final String SSL_STORAGE_PORT_OPTION = "ssl-storage-port";

    public static final String USER_OPTION = "username";

    public static final String PASSWD_OPTION = "password";

    public static final String AUTH_PROVIDER_OPTION = "auth-provider";

    public static final String INITIAL_HOST_ADDRESS_OPTION = "nodes";

    public static final String IGNORE_NODES_OPTION = "ignore";

    public static final String CONNECTIONS_PER_HOST = "connections-per-host";

    public static final String CONFIG_PATH = "conf-path";

    public static final String THROTTLE_MBITS = "throttle";

    public static final String INTER_DC_THROTTLE_MBITS = "inter-dc-throttle";

    public static final String TOOL_NAME = "sstableloader";

    public static final String TARGET_KEYSPACE = "target-keyspace";

    public static final String SSL_TRUSTSTORE = "truststore";

    public static final String SSL_TRUSTSTORE_PW = "truststore-password";

    public static final String SSL_KEYSTORE = "keystore";

    public static final String SSL_KEYSTORE_PW = "keystore-password";

    public static final String SSL_PROTOCOL = "ssl-protocol";

    public static final String SSL_ALGORITHM = "ssl-alg";

    public static final String SSL_STORE_TYPE = "store-type";

    public static final String SSL_CIPHER_SUITES = "ssl-ciphers";

    public final File directory;

    public final boolean debug;

    public final boolean verbose;

    public final boolean noProgress;

    public final int nativePort;

    public final String user;

    public final String passwd;

    public final AuthProvider authProvider;

    public final int throttle;

    public final int interDcThrottle;

    public final int storagePort;

    public final int sslStoragePort;

    public final EncryptionOptions clientEncOptions;

    public final int connectionsPerHost;

    public final EncryptionOptions.ServerEncryptionOptions serverEncOptions;

    public final Set<InetSocketAddress> hosts;

    public final Set<InetAddressAndPort> ignores;

    public final String targetKeyspace;

    LoaderOptions(Builder builder) {
// builder              3	: [('cmd', 0.7003853858100308), ('config', 0.6250844667006945), ('opts', 0.5500383071565595), ('builder', 0.5281763133432826), ('interDcThrottle', 0.5000105578705961), ('sslStoragePort', 0.333357563772844), ('storagePort', 0.333351655338743), ('serverEncryptionOptions', 0.3333395014585562), ('entry', 0.2644156915241195), ('userPassword', 0.26389750864555256)]
        directory = builder.directory;
        debug = builder.debug;
        verbose = builder.verbose;
        noProgress = builder.noProgress;
        nativePort = builder.nativePort;
        user = builder.user;
        passwd = builder.passwd;
        authProvider = builder.authProvider;
        throttle = builder.throttle;
        interDcThrottle = builder.interDcThrottle;
        storagePort = builder.storagePort;
        sslStoragePort = builder.sslStoragePort;
        clientEncOptions = builder.clientEncOptions;
        connectionsPerHost = builder.connectionsPerHost;
        serverEncOptions = builder.serverEncOptions;
        hosts = builder.hosts;
        ignores = builder.ignores;
        targetKeyspace = builder.targetKeyspace;
    }

    static class Builder {

        File directory;

        boolean debug;

        boolean verbose;

        boolean noProgress;

        int nativePort = 9042;

        String user;

        String passwd;

        String authProviderName;

        AuthProvider authProvider;

        int throttle = 0;

        int interDcThrottle = 0;

        int storagePort;

        int sslStoragePort;

        EncryptionOptions clientEncOptions = new EncryptionOptions();

        int connectionsPerHost = 1;

        EncryptionOptions.ServerEncryptionOptions serverEncOptions = new EncryptionOptions.ServerEncryptionOptions();

        Set<InetAddress> hostsArg = new HashSet<>();

        Set<InetAddress> ignoresArg = new HashSet<>();

        Set<InetSocketAddress> hosts = new HashSet<>();

        Set<InetAddressAndPort> ignores = new HashSet<>();

        String targetKeyspace;

        Builder() {
        }

        public LoaderOptions build() {
            constructAuthProvider();
            try {
                for (InetAddress host : hostsArg) {
// host                 1	: [('addr', 0.6557871229158124), ('host', 0.6041944251925428), ('node', 0.3715735321481769), ('address', 0.1250642776071907), ('endpoint', 0.12166213592048919), ('bind', 0.1041686610785602), ('remoteAddress', 0.10416800575178191), ('value', 0.011342311322874983), ('serverAddress', 0.008799091828913173), ('clientAddress', 0.008447755613660114)]
                    hosts.add(new InetSocketAddress(host, nativePort));
                }
                for (InetAddress host : ignoresArg) {
// host                 1	: [('addr', 0.8278935614579062), ('host', 0.6041944251925428), ('node', 0.3715735321481769), ('address', 0.1250642776071907), ('endpoint', 0.12166213592048919), ('bind', 0.1041686610785602), ('remoteAddress', 0.10416800575178191), ('name', 0.007141564649501691), ('value', 0.005671155661437491), ('serverAddress', 0.004399545914456586)]
                    ignores.add(InetAddressAndPort.getByNameOverrideDefaults(host.getHostAddress(), storagePort));
                }
            } catch (UnknownHostException e) {
// e                    0	: [('e', 0.836766828787303), ('throwable', 0.1805988530998261), ('ex', 0.11221130397380336), ('t', 0.06976396356626097), ('fail', 0.013939334654726045), ('e1', 0.00610500511609776), ('config', 0.005610264753946556), ('writeToConfig', 0.003379551294465337), ('i', 0.0008610996640773172), ('cfs', 0.00045615316684532406)]
                Throwables.propagate(e);
            }
            return new LoaderOptions(this);
        }

        public Builder directory(File directory) {
// directory            0	: [('directory', 0.8906350395323883), ('file', 0.1600067231705592), ('f', 0.12241012839875588), ('dir', 0.06797503825066502), ('sstable', 0.02102272477326461), ('dataPath', 0.01296278810542623), ('path', 0.011941590481924183), ('dataDir', 0.010933368558875665), ('dataFolder', 0.009932943001115822), ('cfDir', 0.009071569580935647)]
            this.directory = directory;
            return this;
        }

        public Builder debug(boolean debug) {
// debug                0	: [('debug', 0.7996078995540213), ('cmd', 0.12410424283107889), ('reversed', 0.04945167323495747), ('forceFlush', 0.025832645044122915), ('isSelfContained', 0.018802367513403345), ('flush', 0.018503324318409453), ('withPort', 0.016484777329677174), ('isStart', 0.015996467524781233), ('value', 0.015156571404857835), ('disableSnapshot', 0.014883312476289571)]
            this.debug = debug;
            return this;
        }

        public Builder verbose(boolean verbose) {
// verbose              No	: [('cmd', 0.7003852252925695), ('builder', 0.050871805087194966), ('reversed', 0.04945167323495747), ('forceFlush', 0.025832645044122915), ('isSelfContained', 0.018802367513403345), ('flush', 0.018503324318409453), ('withPort', 0.016484777329677174), ('isStart', 0.015996467524781233), ('value', 0.015156571404857835), ('disableSnapshot', 0.014883312476289571)]
            this.verbose = verbose;
            return this;
        }

        public Builder noProgress(boolean noProgress) {
// noProgress           No	: [('builder', 0.2517436101743899), ('cmd', 0.2507704505851389), ('reversed', 0.04945167323495747), ('forceFlush', 0.025832645044122915), ('isSelfContained', 0.018802367513403345), ('flush', 0.018503324318409453), ('withPort', 0.016484777329677174), ('isStart', 0.015996467524781233), ('value', 0.015156571404857835), ('disableSnapshot', 0.014883312476289571)]
            this.noProgress = noProgress;
            return this;
        }

        public Builder nativePort(int nativePort) {
// nativePort           No	: [('i', 0.29786017593296465), ('config', 0.2501688694612106), ('builder', 0.12674361017438993), ('serverSocket', 0.12501063219756095), ('j', 0.027243589926990684), ('size', 0.017131988575360822), ('version', 0.011065782890632163), ('pageSize', 0.010645833937491059), ('index', 0.008851039033573847), ('count', 0.008760652811978697)]
            this.nativePort = nativePort;
            return this;
        }

        public Builder user(String user) {
// user                 0	: [('user', 0.8138944657130182), ('source', 0.07848953962671722), ('keyspace', 0.07699626143564575), ('name', 0.07208138888185983), ('keyspaceName', 0.03923364404692141), ('query', 0.02391906033110023), ('ksName', 0.021855523865860413), ('key', 0.01205623552446137), ('s', 0.011743108470294703), ('msg', 0.01142949479799336)]
            this.user = user;
            return this;
        }

        public Builder password(String passwd) {
// passwd               No	: [('builder', 0.2517436101743899), ('cmd', 0.2507704505851389), ('keyspace', 0.07699626143564575), ('name', 0.07208138888185983), ('keyspaceName', 0.03923364404692141), ('query', 0.02391906033110023), ('ksName', 0.021855523865860413), ('key', 0.01205623552446137), ('s', 0.011743108470294703), ('msg', 0.01142949479799336)]
            this.passwd = passwd;
            return this;
        }

        public Builder authProvider(AuthProvider authProvider) {
// authProvider         1	: [('settings', 0.3907084031117952), ('authProvider', 0.390628083287307), ('builder', 0.01606090254359748), ('i', 0.0008677148786675303), ('cfs', 0.0008360105384320299), ('restrictions', 0.0006602698241948982), ('keyspace', 0.0006100358520579693), ('sstable', 0.0004924365231481126), ('row', 0.0004438772696305296), ('in', 0.00044219106722636546)]
            this.authProvider = authProvider;
            return this;
        }

        public Builder throttle(int throttle) {
// throttle             0	: [('throttle', 0.7750038951051481), ('i', 0.29786017593296465), ('j', 0.027243589926990684), ('builder', 0.025435902543597483), ('config', 0.025042217365302658), ('size', 0.017131988575360822), ('version', 0.011065782890632163), ('pageSize', 0.010645833937491059), ('index', 0.008851039033573847), ('count', 0.008760652811978697)]
            this.throttle = throttle;
            return this;
        }

        public Builder interDcThrottle(int interDcThrottle) {
// interDcThrottle      No	: [('builder', 0.50174361017439), ('i', 0.29786017593296465), ('j', 0.027243589926990684), ('size', 0.017131988575360822), ('version', 0.011065782890632163), ('pageSize', 0.010645833937491059), ('index', 0.008851039033573847), ('count', 0.008760652811978697), ('nowInSec', 0.008237082840024324), ('idx', 0.007655716625544738)]
            this.interDcThrottle = interDcThrottle;
            return this;
        }

        public Builder storagePort(int storagePort) {
// storagePort          0	: [('storagePort', 0.7916695566640115), ('i', 0.29786017593296465), ('builder', 0.042102569210264146), ('config', 0.04170888403196932), ('j', 0.027243589926990684), ('size', 0.017131988575360822), ('version', 0.011065782890632163), ('pageSize', 0.010645833937491059), ('index', 0.008851039033573847), ('count', 0.008760652811978697)]
            this.storagePort = storagePort;
            return this;
        }

        public Builder sslStoragePort(int sslStoragePort) {
// sslStoragePort       0	: [('sslStoragePort', 0.7916710330423553), ('i', 0.29786017593296465), ('builder', 0.042102569210264146), ('config', 0.04170888403196932), ('j', 0.027243589926990684), ('size', 0.017131988575360822), ('version', 0.011065782890632163), ('pageSize', 0.010645833937491059), ('index', 0.008851039033573847), ('count', 0.008760652811978697)]
            this.sslStoragePort = sslStoragePort;
            return this;
        }

        public Builder encOptions(EncryptionOptions encOptions) {
// encOptions           7	: [('options', 0.31349240315821475), ('builder', 0.2517436101743899), ('config', 0.2501688694612106), ('encryptionOptions', 0.07648430330858592), ('clientEncryptionOptions', 0.02400176650371084), ('clientOpts', 0.015828207338176043), ('encryption', 0.006336237403767086), ('encOptions', 0.006333995240206049), ('value', 0.003491072775212417), ('i', 0.0034708595146701214)]
            this.clientEncOptions = encOptions;
            return this;
        }

        public Builder connectionsPerHost(int connectionsPerHost) {
// connectionsPerHost   0	: [('connectionsPerHost', 0.5500047916136054), ('i', 0.29786017593296465), ('settings', 0.27508340311179524), ('j', 0.027243589926990684), ('builder', 0.025435902543597483), ('opts', 0.025019146693225035), ('size', 0.017131988575360822), ('version', 0.011065782890632163), ('pageSize', 0.010645833937491059), ('index', 0.008851039033573847)]
            this.connectionsPerHost = connectionsPerHost;
            return this;
        }

        public Builder serverEncOptions(EncryptionOptions.ServerEncryptionOptions serverEncOptions) {
// serverEncOptions     No	: [('serverOpts', 0.4112187450362734), ('encryptionOptions', 0.27664988746515895), ('builder', 0.16841027684105658), ('config', 0.16683553612787727), ('serverEncryptionOptions', 0.1666789998158946), ('server', 0.033747406625767876), ('options', 0.005647330904800238), ('encryption', 0.005598795795302248), ('i', 0.0034708595146701214), ('cfs', 0.0033440421537281194)]
            this.serverEncOptions = serverEncOptions;
            return this;
        }

        @Deprecated
        public Builder hosts(Set<InetAddress> hosts) {
// hosts                No	: [('ignores', 0.6385451627240007), ('r', 0.13865507168713267), ('endpoints', 0.040744274675405055), ('replicas', 0.02943666512588344), ('addresses', 0.027096076603496066), ('addrs', 0.027091322526357296), ('inetList', 0.02708681972616933), ('canonical1', 0.0249135662181604), ('builder', 0.019927693513659066), ('range', 0.017010089369960367)]
            this.hostsArg.addAll(hosts);
            return this;
        }

        public Builder hostsAndNativePort(Set<InetSocketAddress> hosts) {
// hosts                0	: [('hosts', 0.7065192451658916), ('r', 0.10996188986895085), ('addresses', 0.10986122811864757), ('replicas', 0.02943666512588344), ('canonical1', 0.0249135662181604), ('builder', 0.019927693513659066), ('range', 0.017010089369960367), ('entry', 0.014146114618859075), ('sstables', 0.014118610221313308), ('list', 0.013924780853900592)]
            this.hosts.addAll(hosts);
            return this;
        }

        public Builder host(InetAddress host) {
// host                 7	: [('address', 0.11545333504695642), ('endpoint', 0.06770513282574181), ('addr', 0.06759293605059441), ('remoteAddress', 0.06758079744097086), ('value', 0.04536924409297821), ('serverAddress', 0.03519636731077482), ('clientAddress', 0.03379102245291885), ('host', 0.025373886571972294), ('inetAddr', 0.025344197233784754), ('sstable', 0.02475684660484894)]
            hostsArg.add(host);
            return this;
        }

        public Builder hostAndNativePort(InetSocketAddress host) {
// host                 4	: [('ep', 0.37651389639638067), ('tokenizer', 0.2830707044772627), ('endpoint', 0.13067918673981788), ('inet', 0.07832113878924496), ('host', 0.06321814605697954), ('addr', 0.03625973906618679), ('remoteAddress', 0.032869222078184865), ('config', 0.03152365649531581), ('addressAndPort', 0.029492621967458346), ('node', 0.016955146776619166)]
            hosts.add(host);
            return this;
        }

        public Builder ignore(Set<InetAddress> ignores) {
// ignores              No	: [('hosts', 0.6386230284910585), ('r', 0.13865507168713267), ('endpoints', 0.040744274675405055), ('replicas', 0.02943666512588344), ('addresses', 0.027096076603496066), ('addrs', 0.027091322526357296), ('inetList', 0.02708681972616933), ('canonical1', 0.0249135662181604), ('builder', 0.019927693513659066), ('range', 0.017010089369960367)]
            this.ignoresArg.addAll(ignores);
            return this;
        }

        public Builder ignoresAndInternalPorts(Set<InetAddressAndPort> ignores) {
// ignores              No	: [('peers', 0.09817243289205221), ('endpoints', 0.09347519777070001), ('schemaDestinationEndpoints', 0.06248855824766333), ('dc1', 0.05500033472175663), ('hintedNodes', 0.04248675287801022), ('toStream', 0.030540308135595176), ('replicas', 0.02943666512588344), ('addresses', 0.02913776939268483), ('streams', 0.028068309769783438), ('options', 0.027737572512710848)]
            this.ignores.addAll(ignores);
            return this;
        }

        public Builder ignore(InetAddress ignore) {
// ignore               No	: [('address', 0.11545333504695642), ('endpoint', 0.06770513282574181), ('addr', 0.06759293605059441), ('remoteAddress', 0.06758079744097086), ('value', 0.04536924409297821), ('serverAddress', 0.03519636731077482), ('host', 0.033820866423076114), ('clientAddress', 0.03379102245291885), ('inetAddr', 0.025344197233784754), ('sstable', 0.02475684660484894)]
            ignoresArg.add(ignore);
            return this;
        }

        public Builder ignoreAndInternalPorts(InetAddressAndPort ignore) {
// ignore               No	: [('endpoint', 0.2593915691763801), ('ep', 0.054185183307226686), ('from', 0.04852960288612729), ('address', 0.033638509286940954), ('peer', 0.03259519563737876), ('target', 0.027495434626858172), ('sstable', 0.02475684660484894), ('i', 0.019856926039819423), ('host', 0.018678692167325706), ('participant', 0.018356137675835008)]
            ignores.add(ignore);
            return this;
        }

        public Builder parseArgs(String[] cmdArgs) {
// cmdArgs              0	: [('cmdArgs', 0.5223929732730127), ('args', 0.210195540836485), ('partitioner', 0.1401680885659022), ('params', 0.021233473923102174), ('columnFilter', 0.015050643113715402), ('inputs', 0.014955608588310087), ('arguments', 0.014909972002539624), ('options', 0.01394828800292272), ('data', 0.012700170666097146), ('hosts', 0.012490709827352526)]
            CommandLineParser parser = new GnuParser();
// parser               0	: [('parser', 0.9687517312462999), ('cmd', 0.008429629991977593), ('cmdBuilder', 0.004168150323331514), ('i', 0.00043385743933376517), ('cfs', 0.0004180052692160149), ('restrictions', 0.0003301349120974491), ('keyspace', 0.00030501792602898465), ('sstable', 0.0002462182615740563), ('row', 0.0002219386348152648), ('in', 0.00022109553361318273)]
            CmdLineOptions options = getCmdLineOptions();
// options              0	: [('options', 0.963093215173151), ('e', 0.5169560338363551), ('str', 0.14734962886972097), ('args', 0.11818073819618533), ('i', 0.09699341149474289), ('timeAsString', 0.07224815229099257), ('pageSize', 0.0517774340357143), ('deserializedEx', 0.03955831569285114), ('partition', 0.035622698120042144), ('v', 0.022721506904629145)]
            try {
                CommandLine cmd = parser.parse(options, cmdArgs, false);
// cmd                  0	: [('cmd', 0.9687575215673627), ('clientState', 0.7569619691218765), ('builder', 0.6258723021785227), ('encOptions', 0.583337189833588), ('testKeystoreFile', 0.31250339251997195), ('clientEncryptionOptions', 0.3125032378598777), ('css', 0.3125027426561834), ('doneWork', 0.2512079478242595), ('charRunLength', 0.25077066707060264), ('nodes', 0.25002427904572694)]
                if (cmd.hasOption(HELP_OPTION)) {
                    printUsage(options);
                    System.exit(0);
                }
                String[] args = cmd.getArgs();
// args                 0	: [('args', 0.28241769676108125), ('pieces', 0.09217424486037325), ('percentiles', 0.0891399106426563), ('hosts', 0.04633438432317678), ('token', 0.04491026779898573), ('keys', 0.044627090846328904), ('css', 0.044419505696523975), ('ciphers', 0.04441949603195413), ('cfnSources', 0.04441944770910492), ('javascript', 0.04441944770910492)]
                if (args.length == 0) {
                    System.err.println("Missing sstable directory argument");
                    printUsage(options);
                    System.exit(1);
                }
                if (args.length > 1) {
                    System.err.println("Too many arguments");
                    printUsage(options);
                    System.exit(1);
                }
                String dirname = args[0];
// dirname              No	: [('e', 0.7559056884433902), ('keyspaceName', 0.40495649011783713), ('dirName', 0.319091795324237), ('keyspace', 0.13946040086317377), ('dirPath', 0.07091034665430394), ('directory', 0.05508315464193024), ('path', 0.04500903023564659), ('dataDir', 0.04500273005802686), ('descriptor', 0.03556630460567021), ('dir', 0.03552629025192619)]
                File dir = new File(dirname);
// dir                  No	: [('directory', 0.880020989580504), ('cfs', 0.0533440454379825), ('table', 0.05050211862052539), ('equal', 0.0343322554745591), ('sstable', 0.026969748044896033), ('builder', 0.02674361196715783), ('file', 0.02539269362455542), ('txn', 0.025346329490764614), ('desc', 0.02520097444813485), ('partition', 0.023989102526783625)]
                if (!dir.exists()) {
                    errorMsg("Unknown directory: " + dirname, options);
                }
                if (!dir.isDirectory()) {
                    errorMsg(dirname + " is not a directory", options);
                }
                directory = dir;
                verbose = cmd.hasOption(VERBOSE_OPTION);
                noProgress = cmd.hasOption(NOPROGRESS_OPTION);
                if (cmd.hasOption(USER_OPTION)) {
                    user = cmd.getOptionValue(USER_OPTION);
                }
                if (cmd.hasOption(PASSWD_OPTION)) {
                    passwd = cmd.getOptionValue(PASSWD_OPTION);
                }
                if (cmd.hasOption(AUTH_PROVIDER_OPTION)) {
                    authProviderName = cmd.getOptionValue(AUTH_PROVIDER_OPTION);
                }
                Config config;
// config               No	: [('builder', 0.6258719489819228), ('i', 0.5698335169367569), ('testConfig', 0.5003684012475249), ('sslStoragePort', 0.33335763584382344), ('storagePort', 0.333351729141031), ('serverEncryptionOptions', 0.33333950056635775), ('overrides', 0.2500163290301993), ('merged', 0.18481459337524184), ('nativePort', 0.16667884649385908), ('serverSocket', 0.16667730010529494)]
                if (cmd.hasOption(CONFIG_PATH)) {
                    File configFile = new File(cmd.getOptionValue(CONFIG_PATH));
// configFile           No	: [('dir', 0.18034615555927147), ('file1', 0.08833719498480183), ('target', 0.08830879121699022), ('directory', 0.07216825675521615), ('file', 0.06071957317087122), ('movedDir', 0.049118177435621885), ('sstable', 0.04546397917032197), ('toDelete', 0.04415120761177224), ('lib', 0.04415118828270429), ('f', 0.0364512065300574)]
                    if (!configFile.exists()) {
                        errorMsg("Config file not found", options);
                    }
                    config = new YamlConfigurationLoader().loadConfig(configFile.toURI().toURL());
                } else {
                    config = new Config();
                    config.stream_throughput_outbound_megabits_per_sec = 0;
                    config.inter_dc_stream_throughput_outbound_megabits_per_sec = 0;
                }
                if (cmd.hasOption(INITIAL_HOST_ADDRESS_OPTION)) {
                    String[] nodes = cmd.getOptionValue(INITIAL_HOST_ADDRESS_OPTION).split(",");
// nodes                0	: [('nodes', 0.8980824102391004), ('params', 0.1443384342888987), ('parts', 0.06550376497570137), ('split', 0.054390751196240955), ('args', 0.03853302432818398), ('info', 0.032001743003167354), ('locations', 0.024959671239679928), ('children', 0.023564809972108438), ('splits', 0.022931496881481755), ('names', 0.022657530249250296)]
                    try {
                        for (String node : nodes) {
// node                 0	: [('node', 0.3786868218512791), ('keyspace', 0.22770829630538802), ('name', 0.19101114224209453), ('host', 0.18951234949998852), ('tokenRangeString', 0.10352183710956708), ('hostToIgnore', 0.10326717041744393), ('table', 0.024185697816243024), ('legacyVersion', 0.023763816745509342), ('keyspaceName', 0.017118778081883536), ('viewName', 0.016465695043913268)]
                            HostAndPort hap = HostAndPort.fromString(node);
// hap                  0	: [('hap', 0.6000045720559462), ('port', 0.1680997961051056), ('config', 0.08060023088809115), ('host', 0.04361964536329686), ('value', 0.04281131141738062), ('e', 0.025329367500178217), ('entry', 0.02251304389686634), ('cmd', 0.020185876679785525), ('source', 0.020174519231277985), ('address', 0.013799527763760944)]
                            hosts.add(new InetSocketAddress(InetAddress.getByName(hap.getHost()), hap.getPortOrDefault(nativePort)));
                        }
                    } catch (UnknownHostException e) {
// e                    0	: [('e', 0.836766828787303), ('dirname', 0.12528692551576642), ('ex', 0.11221130397380336), ('i', 0.022059723060543213), ('type', 0.007654275618489236), ('e1', 0.00610500511609776), ('myType', 0.004289266760081524), ('name', 0.003188036525939031), ('fIntMax', 0.0030259584097200344), ('uuid', 0.0028745167387156803)]
                        errorMsg("Unknown host: " + e.getMessage(), options);
                    }
                } else {
                    System.err.println("Initial hosts must be specified (-d)");
                    printUsage(options);
                    System.exit(1);
                }
                if (cmd.hasOption(STORAGE_PORT_OPTION))
                    storagePort = Integer.parseInt(cmd.getOptionValue(STORAGE_PORT_OPTION));
                else
                    storagePort = config.storage_port;
                if (cmd.hasOption(IGNORE_NODES_OPTION)) {
                    String[] nodes = cmd.getOptionValue(IGNORE_NODES_OPTION).split(",");
// nodes                0	: [('nodes', 0.8980824102391004), ('params', 0.1443384342888987), ('parts', 0.06550376497570137), ('split', 0.054390751196240955), ('args', 0.03853302432818398), ('info', 0.032001743003167354), ('locations', 0.024959671239679928), ('children', 0.023564809972108438), ('splits', 0.022931496881481755), ('names', 0.022657530249250296)]
                    try {
                        for (String node : nodes) {
// node                 3	: [('host', 0.7430836676838346), ('keyspace', 0.22770829630538802), ('endpoint', 0.12166213465150275), ('node', 0.10429553276601987), ('tokenRangeString', 0.10352183710956708), ('hostToIgnore', 0.10326717041744393), ('name', 0.03604689191201102), ('table', 0.024185697816243024), ('legacyVersion', 0.023763816745509342), ('keyspaceName', 0.017118778081883536)]
                            ignores.add(InetAddressAndPort.getByNameOverrideDefaults(node.trim(), storagePort));
                        }
                    } catch (UnknownHostException e) {
// e                    0	: [('e', 0.836766828787303), ('dirname', 0.12528692551576642), ('ex', 0.11221130397380336), ('i', 0.022059723060543213), ('type', 0.007654275618489236), ('e1', 0.00610500511609776), ('myType', 0.004289266760081524), ('name', 0.003188036525939031), ('fIntMax', 0.0030259584097200344), ('uuid', 0.0028745167387156803)]
                        errorMsg("Unknown host: " + e.getMessage(), options);
                    }
                }
                if (cmd.hasOption(CONNECTIONS_PER_HOST)) {
                    connectionsPerHost = Integer.parseInt(cmd.getOptionValue(CONNECTIONS_PER_HOST));
                }
                if (cmd.hasOption(SSL_STORAGE_PORT_OPTION))
                    sslStoragePort = Integer.parseInt(cmd.getOptionValue(SSL_STORAGE_PORT_OPTION));
                else
                    sslStoragePort = config.ssl_storage_port;
                throttle = config.stream_throughput_outbound_megabits_per_sec;
                clientEncOptions = config.client_encryption_options;
                serverEncOptions = config.server_encryption_options;
                if (cmd.hasOption(THROTTLE_MBITS)) {
                    throttle = Integer.parseInt(cmd.getOptionValue(THROTTLE_MBITS));
                }
                if (cmd.hasOption(INTER_DC_THROTTLE_MBITS)) {
                    interDcThrottle = Integer.parseInt(cmd.getOptionValue(INTER_DC_THROTTLE_MBITS));
                }
                if (cmd.hasOption(SSL_TRUSTSTORE) || cmd.hasOption(SSL_TRUSTSTORE_PW) || cmd.hasOption(SSL_KEYSTORE) || cmd.hasOption(SSL_KEYSTORE_PW)) {
                    clientEncOptions = clientEncOptions.withEnabled(true);
                }
                if (cmd.hasOption(NATIVE_PORT_OPTION)) {
                    nativePort = Integer.parseInt(cmd.getOptionValue(NATIVE_PORT_OPTION));
                } else {
                    if (config.native_transport_port_ssl != null && (config.client_encryption_options.enabled || clientEncOptions.enabled))
                        nativePort = config.native_transport_port_ssl;
                    else
                        nativePort = config.native_transport_port;
                }
                if (cmd.hasOption(SSL_TRUSTSTORE)) {
                    clientEncOptions = clientEncOptions.withTrustStore(cmd.getOptionValue(SSL_TRUSTSTORE));
                }
                if (cmd.hasOption(SSL_TRUSTSTORE_PW)) {
                    clientEncOptions = clientEncOptions.withTrustStorePassword(cmd.getOptionValue(SSL_TRUSTSTORE_PW));
                }
                if (cmd.hasOption(SSL_KEYSTORE)) {
                    clientEncOptions = clientEncOptions.withKeyStore(cmd.getOptionValue(SSL_KEYSTORE)).withRequireClientAuth(true);
                }
                if (cmd.hasOption(SSL_KEYSTORE_PW)) {
                    clientEncOptions = clientEncOptions.withKeyStorePassword(cmd.getOptionValue(SSL_KEYSTORE_PW));
                }
                if (cmd.hasOption(SSL_PROTOCOL)) {
                    clientEncOptions = clientEncOptions.withProtocol(cmd.getOptionValue(SSL_PROTOCOL));
                }
                if (cmd.hasOption(SSL_ALGORITHM)) {
                    clientEncOptions = clientEncOptions.withAlgorithm(cmd.getOptionValue(SSL_ALGORITHM));
                }
                if (cmd.hasOption(SSL_STORE_TYPE)) {
                    clientEncOptions = clientEncOptions.withStoreType(cmd.getOptionValue(SSL_STORE_TYPE));
                }
                if (cmd.hasOption(SSL_CIPHER_SUITES)) {
                    clientEncOptions = clientEncOptions.withCipherSuites(cmd.getOptionValue(SSL_CIPHER_SUITES).split(","));
                }
                if (cmd.hasOption(TARGET_KEYSPACE)) {
                    targetKeyspace = cmd.getOptionValue(TARGET_KEYSPACE);
                    if (StringUtils.isBlank(targetKeyspace)) {
                        errorMsg("Empty keyspace is not supported.", options);
                    }
                }
                return this;
            } catch (ParseException | ConfigurationException | MalformedURLException e) {
// e                    0	: [('e', 0.7763836026652573), ('dirname', 0.04602778653032904), ('msg', 0.005066454999572598), ('i', 0.0008610965469250904), ('cfs', 0.0004561510856480762), ('range', 0.00023366972447519678), ('value', 0.00022939822407546203), ('key', 0.00021633059992795517), ('sstable', 0.0002020915135059877), ('name', 0.0001971166535771557)]
                errorMsg(e.getMessage(), options);
                return null;
            }
        }

        private void constructAuthProvider() {
            if ((user != null) != (passwd != null))
                errorMsg("Username and password must both be provided", getCmdLineOptions());
            if (user != null) {
                if (authProviderName != null) {
                    try {
                        Class authProviderClass = Class.forName(authProviderName);
// authProviderClass    No	: [('c', 0.8396026379705535), ('constructor', 0.16669258911029702), ('strategyClass', 0.16669173863446352), ('compactionParams', 0.1666772988642276), ('clazz', 0.044943438161395885), ('i', 0.0034708595146701214), ('cfs', 0.0033440421537281194), ('restrictions', 0.002641079296779593), ('keyspace', 0.002440143408231877), ('sstable', 0.0019697460925924503)]
                        Constructor constructor = authProviderClass.getConstructor(String.class, String.class);
// constructor          2	: [('clazz', 0.8437520513581794), ('ctor', 0.5785739078874987), ('constructor', 0.16250302052796417), ('c', 0.09377455484658007), ('i', 0.00028193305513063053), ('builder', 0.00012382019440477262), ('value', 7.803611016983134e-05), ('size', 7.69185787151777e-05), ('execute', 6.527601489606235e-05), ('o', 6.205398973779177e-05)]
                        authProvider = (AuthProvider) constructor.newInstance(user, passwd);
                    } catch (ClassNotFoundException e) {
// e                    0	: [('e', 0.7256733377345441), ('dirname', 0.12528692551576642), ('cnfe', 0.08597735595335032), ('ex', 0.08208473456057502), ('i', 0.022059723060543213), ('type', 0.007654275618489236), ('myType', 0.004289266760081524), ('name', 0.003188036525939031), ('fIntMax', 0.0030259584097200344), ('uuid', 0.0028745167387156803)]
                        errorMsg("Unknown auth provider: " + e.getMessage(), getCmdLineOptions());
                    } catch (NoSuchMethodException e) {
// e                    0	: [('e', 0.7789121332527513), ('nme', 0.1417911398002224), ('dirname', 0.12528692551576642), ('i', 0.022059723060543213), ('type', 0.007654275618489236), ('myType', 0.004289266760081524), ('name', 0.003188036525939031), ('fIntMax', 0.0030259584097200344), ('uuid', 0.0028745167387156803), ('keyspace', 0.002849072797174148)]
                        errorMsg("Auth provider does not support plain text credentials: " + e.getMessage(), getCmdLineOptions());
                    } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
// e                    0	: [('e', 0.6307624627996475), ('dirname', 0.12528692551576642), ('ign', 0.09226554902230236), ('i', 0.022059723060543213), ('ite', 0.01786101906549505), ('type', 0.007654275618489236), ('myType', 0.004289266760081524), ('name', 0.003188036525939031), ('fIntMax', 0.0030259584097200344), ('uuid', 0.0028745167387156803)]
                        errorMsg("Could not create auth provider with plain text credentials: " + e.getMessage(), getCmdLineOptions());
                    }
                } else {
                    this.authProvider = new PlainTextAuthProvider(user, passwd);
                }
            } else if (authProviderName != null) {
                try {
                    authProvider = (AuthProvider) Class.forName(authProviderName).newInstance();
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
// e                    0	: [('e', 0.6307624627996475), ('dirname', 0.12528692551576642), ('npe', 0.04504234661815075), ('i', 0.022059723060543213), ('type', 0.007654275618489236), ('myType', 0.004289266760081524), ('name', 0.003188036525939031), ('fIntMax', 0.0030259584097200344), ('uuid', 0.0028745167387156803), ('keyspace', 0.002849072797174148)]
                    errorMsg("Unknown auth provider: " + e.getMessage(), getCmdLineOptions());
                }
            }
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private static void errorMsg(String msg, CmdLineOptions options) {
// msg                  0	: [('msg', 0.8764121057312232), ('e', 0.4118237444177501), ('e1', 0.048114012506540946), ('message', 0.025424725588676048), ('t', 0.02479357273806819), ('directory', 0.02408431527991303), ('loader', 0.024056662535207288), ('indent', 0.019169463607248367), ('v', 0.01120462052881003), ('keyspace', 0.009624532679455719)]
// options              0	: [('options', 0.9630933204788992), ('i', 0.0008610996640773172), ('cfs', 0.00045615316684532406), ('value', 0.00022939920430670128), ('e', 0.00022308074068912265), ('key', 0.00021633154132143646), ('sstable', 0.00020209239073273842), ('name', 0.00019711751222932652), ('metadata', 0.00018897956506812435), ('keyspace', 0.0001886606366305629)]
        System.err.println(msg);
        printUsage(options);
        System.exit(1);
    }

    private static CmdLineOptions getCmdLineOptions() {
        CmdLineOptions options = new CmdLineOptions();
// options              0	: [('options', 0.9638920963916358), ('type1', 0.13721913201262773), ('out', 0.12814173231232046), ('f', 0.12661759390311653), ('json', 0.1265533655869937), ('p', 0.0490724521794589), ('values', 0.048872169708752536), ('task', 0.04866020238092392), ('filtered', 0.04851545228117113), ('log', 0.0485145246681965)]
        options.addOption("v", VERBOSE_OPTION, "verbose output");
        options.addOption("h", HELP_OPTION, "display this help message");
        options.addOption(null, NOPROGRESS_OPTION, "don't display progress");
        options.addOption("i", IGNORE_NODES_OPTION, "NODES", "don't stream to this (comma separated) list of nodes");
        options.addOption("d", INITIAL_HOST_ADDRESS_OPTION, "initial hosts", "Required. try to connect to these hosts (comma separated) initially for ring information");
        options.addOption("p", NATIVE_PORT_OPTION, "native transport port", "port used for native connection (default 9042)");
        options.addOption("sp", STORAGE_PORT_OPTION, "storage port", "port used for internode communication (default 7000)");
        options.addOption("ssp", SSL_STORAGE_PORT_OPTION, "ssl storage port", "port used for TLS internode communication (default 7001)");
        options.addOption("t", THROTTLE_MBITS, "throttle", "throttle speed in Mbits (default unlimited)");
        options.addOption("idct", INTER_DC_THROTTLE_MBITS, "inter-dc-throttle", "inter-datacenter throttle speed in Mbits (default unlimited)");
        options.addOption("u", USER_OPTION, "username", "username for cassandra authentication");
        options.addOption("pw", PASSWD_OPTION, "password", "password for cassandra authentication");
        options.addOption("ap", AUTH_PROVIDER_OPTION, "auth provider", "custom AuthProvider class name for cassandra authentication");
        options.addOption("cph", CONNECTIONS_PER_HOST, "connectionsPerHost", "number of concurrent connections-per-host.");
        options.addOption("ts", SSL_TRUSTSTORE, "TRUSTSTORE", "Client SSL: full path to truststore");
        options.addOption("tspw", SSL_TRUSTSTORE_PW, "TRUSTSTORE-PASSWORD", "Client SSL: password of the truststore");
        options.addOption("ks", SSL_KEYSTORE, "KEYSTORE", "Client SSL: full path to keystore");
        options.addOption("kspw", SSL_KEYSTORE_PW, "KEYSTORE-PASSWORD", "Client SSL: password of the keystore");
        options.addOption("prtcl", SSL_PROTOCOL, "PROTOCOL", "Client SSL: connections protocol to use (default: TLS)");
        options.addOption("alg", SSL_ALGORITHM, "ALGORITHM", "Client SSL: algorithm");
        options.addOption("st", SSL_STORE_TYPE, "STORE-TYPE", "Client SSL: type of store");
        options.addOption("ciphers", SSL_CIPHER_SUITES, "CIPHER-SUITES", "Client SSL: comma-separated list of encryption suites to use");
        options.addOption("f", CONFIG_PATH, "path to config file", "cassandra.yaml file path for streaming throughput and client/server SSL.");
        options.addOption("k", TARGET_KEYSPACE, "target keyspace name", "target keyspace name");
        return options;
    }

    public static void printUsage(Options options) {
// options              0	: [('options', 0.9223508598145636), ('timeElapsed', 0.12494925193279502), ('indexes', 0.09370779502522308), ('helper', 0.08553784158497663), ('out', 0.07080140957055281), ('previousUnfilteredSize', 0.05204988827136974), ('observers', 0.0321601067574113), ('version', 0.025169561277331165), ('wasCorrupt', 0.020831068466178982), ('bytesReceived', 0.020822828265570494)]
        String usage = String.format("%s [options] <dir_path>", TOOL_NAME);
// usage                0	: [('usage', 0.8884288778041088), ('optionValue', 0.055257231970333075), ('enabled', 0.03655463813963853), ('ratio', 0.018280496596498125), ('loggerName', 0.01808151651780248), ('assignments', 0.018069734716701553), ('query', 0.011943469676265042), ('type', 0.01169411530462348), ('keyspaceName', 0.010966222769904216), ('errWriter', 0.01064878544270669)]
        String header = System.lineSeparator() + "Bulk load the sstables found in the directory <dir_path> to the configured cluster." + "The parent directories of <dir_path> are used as the target keyspace/table name. " + "So for instance, to load an sstable named Standard1-g-1-Data.db into Keyspace1/Standard1, " + "you will need to have the files Standard1-g-1-Data.db and Standard1-g-1-Index.db into a directory /path/to/Keyspace1/Standard1/.";
// header               0	: [('header', 0.9376247127034381), ('cql', 0.030456492505680072), ('key', 0.023229950498353216), ('name', 0.0218846508763047), ('indexName', 0.021399680347858503), ('keyspace', 0.018497840411666563), ('query', 0.016995773288261577), ('cfName', 0.013757291445015656), ('table', 0.013297962403145918), ('tableName', 0.011772801286960202)]
        String footer = System.lineSeparator() + "You can provide cassandra.yaml file with -f command line option to set up streaming throughput, client and server encryption options. " + "Only stream_throughput_outbound_megabits_per_sec, server_encryption_options and client_encryption_options are read from yaml. " + "You can override options read from cassandra.yaml with corresponding command line options.";
// footer               No	: [('fmtQry', 0.1735415085856866), ('cql3', 0.17348785423519125), ('queryWithoutNewColumns', 0.17348782524169692), ('selectors', 0.10546915412063038), ('customPayload', 0.08802197275449429), ('queryStartNanoTime', 0.0808923920569525), ('state', 0.04610721172057816), ('timestamp', 0.04436318559080486), ('version', 0.03828111911798763), ('fName', 0.037400009606396084)]
        new HelpFormatter().printHelp(usage, header, options, footer);
    }
}
