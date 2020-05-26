// Type of training: selfTestingIdentifier
// Path to file: C:\Users\Igor\IdeaProjects\SLP-Core-0.3\java-projects\ant-master\src\main\org\apache\tools\ant\taskdefs\JDBCTask.java
// Number of identifiers: 29	Accuracy: 55.17%	MRR: 60.15%
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
package org.apache.tools.ant.taskdefs;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import org.apache.tools.ant.AntClassLoader;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.types.Reference;

/**
 *  Handles JDBC configuration needed by SQL type tasks.
 *  <p>
 *  The following example class prints the contents of the first column of each row in TableName.
 * </p>
 * <pre>
 * package examples;
 *
 * import java.sql.Connection;
 * import java.sql.ResultSet;
 * import java.sql.SQLException;
 * import java.sql.Statement;
 *
 * import org.apache.tools.ant.BuildException;
 * import org.apache.tools.ant.taskdefs.JDBCTask;
 *
 * public class SQLExampleTask extends JDBCTask {
 *
 *     private String tableName;
 *
 *     public void execute() throws BuildException {
 *         Connection conn = getConnection();
 *         Statement stmt=null;
 *         try {
 *             if (tableName == null) {
 *                 throw new BuildException("TableName must be specified", location);
 *             }
 *             String sql = "SELECT * FROM "+tableName;
 *             stmt= conn.createStatement();
 *             ResultSet rs = stmt.executeQuery(sql);
 *             while (rs.next()) {
 *                 log(rs.getObject(1).toString());
 *             }
 *         } catch (SQLException e) {
 *
 *         } finally {
 *             if (stmt != null) {
 *                 try {stmt.close();}catch (SQLException ignore) {}
 *             }
 *             if (conn != null) {
 *                 try {conn.close();}catch (SQLException ignore) {}
 *             }
 *         }
 *     }
 *     public void setTableName(String tableName) {
 *         this.tableName = tableName;
 *     }
 *
 * }
 * </pre>
 *
 *  @since Ant 1.5
 */
public abstract class JDBCTask extends Task {

    private static final int HASH_TABLE_SIZE = 3;

    /**
     * Used for caching loaders / driver. This is to avoid
     * getting an OutOfMemoryError when calling this task
     * multiple times in a row.
     */
    private static final Hashtable<String, AntClassLoader> LOADER_MAP = new Hashtable<>(HASH_TABLE_SIZE);

    private boolean caching = true;

    private Path classpath;

    private AntClassLoader loader;

    /**
     * Autocommit flag. Default value is false
     */
    private boolean autocommit = false;

    /**
     * DB driver.
     */
    private String driver = null;

    /**
     * DB url.
     */
    private String url = null;

    /**
     * User name.
     */
    private String userId = null;

    /**
     * Password
     */
    private String password = null;

    /**
     * RDBMS Product needed for this SQL.
     */
    private String rdbms = null;

    /**
     * RDBMS Version needed for this SQL.
     */
    private String version = null;

    /**
     * whether the task fails when ant fails to connect to the database.
     * @since Ant 1.8.0
     */
    private boolean failOnConnectionError = true;

    /**
     * Additional properties to put into the JDBC connection string.
     *
     * @since Ant 1.8.0
     */
    private List<Property> connectionProperties = new ArrayList<>();

    /**
     * Sets the classpath for loading the driver.
     * @param classpath The classpath to set
     */
    public void setClasspath(Path classpath) {
// classpath            0	: [('classpath', 0.8465376079524405), ('path', 0.06174712952202506), ('cp', 0.05945044962931311), ('p', 0.036183656994823746), ('s', 0.030543111570369853), ('src', 0.029871202777082977), ('mainSection', 0.005220272401816832), ('args', 0.003578806595662007), ('localPath', 0.002826270126376525), ('trackerFile', 0.0021692718516213294)]
        this.classpath = classpath;
    }

    /**
     * Caching loaders / driver. This is to avoid
     * getting an OutOfMemoryError when calling this task
     * multiple times in a row; default: true
     * @param enable a <code>boolean</code> value
     */
    public void setCaching(boolean enable) {
// enable               8	: [('value', 0.8756774565392944), ('b', 0.08840525847196394), ('verbose', 0.016829026586382915), ('append', 0.012448459200976782), ('debug', 0.008734879145209597), ('caseSensitive', 0.00873460266504539), ('recursive', 0.008621098130951438), ('preserveLastModified', 0.007973671083471783), ('enable', 0.007348744835986029), ('overwrite', 0.00728360869636253)]
        caching = enable;
    }

    /**
     * Add a path to the classpath for loading the driver.
     * @return a path to be configured
     */
    public Path createClasspath() {
        if (this.classpath == null) {
            this.classpath = new Path(getProject());
        }
        return this.classpath.createPath();
    }

    /**
     * Set the classpath for loading the driver
     * using the classpath reference.
     * @param r a reference to a classpath
     */
    public void setClasspathRef(Reference r) {
// r                    0	: [('r', 0.8440531090252853), ('ref', 0.17230839024713054), ('pathRef', 0.07545356887384995), ('reference', 0.004463138766447228), ('value', 0.0024529790608626696), ('from', 0.002259151206398789), ('name', 0.0004718140205048085), ('i', 0.0003162175661928699), ('p', 0.00027913272921411614), ('project', 0.0002738104860531295)]
        createClasspath().setRefid(r);
    }

    /**
     * Class name of the JDBC driver; required.
     * @param driver The driver to set
     */
    public void setDriver(String driver) {
// driver               No	: [('name', 0.06155535684399712), ('s', 0.02166488489538175), ('value', 0.020552717844961824), ('message', 0.016854965057537783), ('msg', 0.015278668810540214), ('uri', 0.01418545372823002), ('target', 0.01388785146416872), ('line', 0.011559443657118192), ('filename', 0.011435198447237532), ('classname', 0.010569983305680035)]
        this.driver = driver.trim();
    }

    /**
     * Sets the database connection URL; required.
     * @param url The url to set
     */
    public void setUrl(String url) {
// url                  0	: [('url', 0.8864260992839788), ('name', 0.00769441960549964), ('c', 0.0030190040436309093), ('uri', 0.00291657921607069), ('u', 0.002888848007666494), ('loader', 0.0028874638478526542), ('up', 0.0028531246395567967), ('systemid', 0.0028474878382254687), ('urls', 0.0028455852781037404), ('filesetURL', 0.002844548477487964)]
        this.url = url;
    }

    /**
     * Sets the password; required.
     * @param password The password to set
     */
    public void setPassword(String password) {
// password             0	: [('password', 0.7755388826887527), ('p', 0.16872419387040477), ('name', 0.00769441960549964), ('project', 0.004682804197908751), ('s', 0.0027081106119227188), ('value', 0.002569089730620228), ('message', 0.002106870632192223), ('msg', 0.0019098336013175267), ('uri', 0.0017731817160287525), ('target', 0.00173598143302109)]
        this.password = password;
    }

    /**
     * Auto commit flag for database connection;
     * optional, default false.
     * @param autocommit The autocommit to set
     */
    public void setAutocommit(boolean autocommit) {
// autocommit           No	: [('b', 0.08840525847196394), ('value', 0.022603553559821576), ('verbose', 0.016829026586382915), ('append', 0.012448459200976782), ('debug', 0.008734879145209597), ('caseSensitive', 0.00873460266504539), ('recursive', 0.008621098130951438), ('enable', 0.008266223194396603), ('preserveLastModified', 0.007973671083471783), ('overwrite', 0.00728360869636253)]
        this.autocommit = autocommit;
    }

    /**
     * Execute task only if the lower case product name
     * of the DB matches this
     * @param rdbms The rdbms to set
     */
    public void setRdbms(String rdbms) {
// rdbms                No	: [('name', 0.06155535684399712), ('s', 0.02166488489538175), ('value', 0.020552717844961824), ('message', 0.016854965057537783), ('msg', 0.015278668810540214), ('uri', 0.01418545372823002), ('target', 0.01388785146416872), ('line', 0.011559443657118192), ('filename', 0.011435198447237532), ('classname', 0.010569983305680035)]
        this.rdbms = rdbms;
    }

    /**
     * Sets the version string, execute task only if
     * rdbms version match; optional.
     * @param version The version to set
     */
    public void setVersion(String version) {
// version              0	: [('version', 0.8753328483436463), ('b', 0.15188266362475394), ('buffer', 0.011431661688290524), ('manifest', 0.01141012375953168), ('name', 0.00769441960549964), ('text', 0.005742203982943317), ('s', 0.0027081106119227188), ('value', 0.002569089730620228), ('message', 0.002106870632192223), ('msg', 0.0019098336013175267)]
        this.version = version;
    }

    /**
     * whether the task should cause the build to fail if it cannot
     * connect to the database.
     * @param b boolean
     * @since Ant 1.8.0
     */
    public void setFailOnConnectionError(boolean b) {
// b                    0	: [('b', 0.08748778011355336), ('value', 0.022603553559821576), ('verbose', 0.016829026586382915), ('append', 0.012448459200976782), ('debug', 0.008734879145209597), ('caseSensitive', 0.00873460266504539), ('recursive', 0.008621098130951438), ('enable', 0.008266223194396603), ('preserveLastModified', 0.007973671083471783), ('overwrite', 0.00728360869636253)]
        failOnConnectionError = b;
    }

    /**
     * Verify we are connected to the correct RDBMS
     * @param conn the jdbc connection
     * @return true if we are connected to the correct RDBMS
     */
    protected boolean isValidRdbms(Connection conn) {
// conn                 0	: [('conn', 0.2500438250728383), ('i', 0.007220869255079404), ('name', 0.00615307471702672), ('b', 0.005880193753638788), ('value', 0.005419652314355413), ('file', 0.004260412397551352), ('path', 0.0036403468622049576), ('line', 0.003639793901876543), ('p', 0.003621845876403473), ('project', 0.003496295737968582)]
        if (rdbms == null && version == null) {
            return true;
        }
        try {
            DatabaseMetaData dmd = conn.getMetaData();
// dmd                  0	: [('dmd', 0.8750039060258569), ('i', 0.007221303103901564), ('name', 0.0061534916673352995), ('b', 0.005880634358213717), ('value', 0.005420040438239479), ('file', 0.004260708764856733), ('path', 0.003640615907470202), ('line', 0.0036400629398033275), ('p', 0.003622090127980325), ('project', 0.0034965355957683486)]
            if (rdbms != null) {
                String theVendor = dmd.getDatabaseProductName().toLowerCase();
// theVendor            No	: [('s', 0.10738689653049079), ('obj', 0.10442069740010528), ('genericJarFile', 0.0718849848594927), ('systemId', 0.07187607815453051), ('name', 0.0675258284231773), ('msg', 0.06429936459610468), ('home', 0.05225895840812151), ('antHome', 0.052257645092483374), ('fsDir', 0.052257092117477845), ('myfile', 0.05220956259914541)]
                log("RDBMS = " + theVendor, Project.MSG_VERBOSE);
                if (theVendor == null || !theVendor.contains(rdbms)) {
                    log("Not the required RDBMS: " + rdbms, Project.MSG_VERBOSE);
                    return false;
                }
            }
            if (version != null) {
                String theVersion = dmd.getDatabaseProductVersion().toLowerCase(Locale.ENGLISH);
// theVersion           No	: [('another', 0.2690712703825488), ('errorProperty', 0.26906589745799064), ('outputProperty', 0.26906219055073094), ('genericJarFile', 0.07188498532556221), ('systemId', 0.071876078300727), ('name', 0.06752582939328153), ('msg', 0.0642993648722289), ('arg', 0.04554081861624797), ('path', 0.04100447741692225), ('file', 0.03971010623172956)]
                log("Version = " + theVersion, Project.MSG_VERBOSE);
                if (theVersion == null || !(theVersion.startsWith(version) || theVersion.contains(" " + version))) {
                    log("Not the required version: \"" + version + "\"", Project.MSG_VERBOSE);
                    return false;
                }
            }
        } catch (SQLException e) {
// e                    0	: [('e', 0.45465564810437), ('ex', 0.4488990430306342), ('b', 6.346903124789803e-05), ('length', 6.139545753484082e-05), ('classpath', 2.628294266040538e-05), ('block', 1.5569478476276455e-05), ('be', 1.0454663317402004e-05), ('jmod', 9.763472079716267e-06), ('publicId', 9.486995584641973e-06), ('elementName', 8.519327851881942e-06)]
            // Could not get the required information
            log("Failed to obtain required RDBMS information", Project.MSG_ERR);
            return false;
        }
        return true;
    }

    /**
     * Get the cache of loaders and drivers.
     * @return a hashtable
     */
    protected static Hashtable<String, AntClassLoader> getLoaderMap() {
        return LOADER_MAP;
    }

    /**
     * Get the classloader used to create a driver.
     * @return the classloader
     */
    protected AntClassLoader getLoader() {
        return loader;
    }

    /**
     * Additional properties to put into the JDBC connection string.
     *
     * @param var Property
     * @since Ant 1.8.0
     */
    public void addConnectionProperty(Property var) {
// var                  No	: [('p', 0.37848173001527396), ('to', 0.23129845463218304), ('rc', 0.0374268335659816), ('from', 0.03129679575119781), ('prop', 0.03126887125461289), ('name', 0.022366558994697822), ('instr', 0.021552902159280914), ('r', 0.019661231884219622), ('c', 0.018706438541045547), ('fileNameMapper', 0.01833788771824547)]
        connectionProperties.add(var);
    }

    /**
     * Creates a new Connection as using the driver, url, userid and password
     * specified.
     *
     * The calling method is responsible for closing the connection.
     *
     * @return Connection the newly created connection or null if the
     * connection failed and failOnConnectionError is false.
     * @throws BuildException if the UserId/Password/Url is not set or there
     * is no suitable driver or the driver fails to load.
     */
    protected Connection getConnection() throws BuildException {
        if (userId == null) {
            throw new BuildException("UserId attribute must be set!", getLocation());
        }
        if (password == null) {
            throw new BuildException("Password attribute must be set!", getLocation());
        }
        if (url == null) {
            throw new BuildException("Url attribute must be set!", getLocation());
        }
        try {
            log("connecting to " + getUrl(), Project.MSG_VERBOSE);
            Properties info = new Properties();
// info                 No	: [('props', 0.3406339931126187), ('p', 0.12005592419303648), ('ftp', 0.08631533199744258), ('afterUpdate', 0.08248378271843362), ('rebuild', 0.06469463134139601), ('exe', 0.05427525731756936), ('commandLine', 0.0491538358884884), ('execTask', 0.04330843452420972), ('genericJarFile', 0.043124102972242166), ('links', 0.042208216870033365)]
            info.put("user", getUserId());
            info.put("password", getPassword());
            for (Property p : connectionProperties) {
// p                    0	: [('p', 0.8157139762995028), ('attrs', 0.1833763399937448), ('key', 0.10934778302423066), ('def', 0.10902219881075995), ('event', 0.08952190967310356), ('vpath', 0.08742145309523713), ('field', 0.08238826793204415), ('line', 0.08189011215884592), ('result', 0.08174947155164404), ('h', 0.08171847080181988)]
                String name = p.getName();
// name                 0	: [('name', 0.15729314832780575), ('msg', 0.06154528447137524), ('file', 0.04972582974886224), ('key', 0.04883865791051299), ('e', 0.04841493079185729), ('message', 0.02729920109441357), ('f', 0.026700026803743497), ('propName', 0.026284287118841718), ('s', 0.02433942081278235), ('dir', 0.0239240522015199)]
                String value = p.getValue();
// value                0	: [('value', 0.5045615286923829), ('name', 0.3357678399133976), ('toFile', 0.21479492941151512), ('r', 0.17817007251196815), ('attr2', 0.14724931659926638), ('dest', 0.10744834989331802), ('destFile', 0.08971173113028176), ('methodName', 0.07370525970828744), ('otherName', 0.073632407573879), ('iDir', 0.07345914779493531)]
                if (name == null || value == null) {
                    log("Only name/value pairs are supported as connection properties.", Project.MSG_WARN);
                } else {
                    log("Setting connection property " + name + " to " + value, Project.MSG_VERBOSE);
                    info.put(name, value);
                }
            }
            Connection conn = getDriver().connect(getUrl(), info);
// conn                 1	: [('libPathURLs', 0.6251459942253387), ('conn', 0.25004271988603727), ('msg', 0.12537981309691484), ('result', 0.060528358907966956), ('newFilter', 0.0218625508549038), ('p', 0.018508007031492697), ('cmd', 0.016660490627492794), ('f', 0.0133887824215085), ('c', 0.013333147301140453), ('buf', 0.013182914514445395)]
            if (conn == null) {
                // Driver doesn't understand the URL
                throw new SQLException("No suitable Driver for " + url);
            }
            conn.setAutoCommit(autocommit);
            return conn;
        } catch (SQLException e) {
// e                    1	: [('ex', 0.5577828552935388), ('e', 0.39399442303529514), ('msg', 0.10219736146386868), ('ioe', 0.08419998398464977), ('message', 0.07231956046709365), ('file', 0.03474703808372972), ('link', 0.029303907004578446), ('d', 0.028626754748121114), ('prefix', 0.026469407885625355), ('s', 0.02397326429075022)]
            // failed to connect
            if (failOnConnectionError) {
                throw new BuildException(e, getLocation());
            }
            log("Failed to connect: " + e.getMessage(), Project.MSG_WARN);
            return null;
        }
    }

    /**
     * Gets an instance of the required driver.
     * Uses the ant class loader and the optionally the provided classpath.
     * @return Driver
     * @throws BuildException if something goes wrong
     */
    private Driver getDriver() throws BuildException {
        if (driver == null) {
            throw new BuildException("Driver attribute must be set!", getLocation());
        }
        Driver driverInstance;
// driverInstance       No	: [('result', 0.11870229917694605), ('sb', 0.039301607456016546), ('ret', 0.036311383873995025), ('o', 0.02704890354120375), ('buf', 0.025579894310023072), ('string', 0.021230344462581683), ('returnValue', 0.021143540629038407), ('checksumSb', 0.02114333326616333), ('p', 0.017306024810502852), ('f', 0.016131753735482885)]
        try {
            Class<? extends Driver> dc;
// dc                   No	: [('exe', 0.3363773220803911), ('execute', 0.1684608282388902), ('ftp', 0.08618204583886595), ('rebuild', 0.06459466875337119), ('execTask', 0.043241782194538114), ('genericJarFile', 0.04305746165905174), ('cmd', 0.032957549137364095), ('genicTask', 0.03233777978281522), ('je', 0.02286376892089702), ('javaTask', 0.02164276624459492)]
            if (classpath != null) {
                // check first that it is not already loaded otherwise
                // consecutive runs seems to end into an OutOfMemoryError
                // or it fails when there is a native library to load
                // several times.
                // this is far from being perfect but should work
                // in most cases.
                synchronized (LOADER_MAP) {
                    if (caching) {
                        loader = LOADER_MAP.get(driver);
                    }
                    if (loader == null) {
                        log("Loading " + driver + " using AntClassLoader with classpath " + classpath, Project.MSG_VERBOSE);
                        loader = getProject().createClassLoader(classpath);
                        if (caching) {
                            LOADER_MAP.put(driver, loader);
                        }
                    } else {
                        log("Loading " + driver + " using a cached AntClassLoader.", Project.MSG_VERBOSE);
                    }
                }
                dc = loader.loadClass(driver).asSubclass(Driver.class);
            } else {
                log("Loading " + driver + " using system loader.", Project.MSG_VERBOSE);
                dc = Class.forName(driver).asSubclass(Driver.class);
            }
            driverInstance = dc.newInstance();
        } catch (ClassNotFoundException e) {
// e                    0	: [('e', 0.770118902161771), ('cnfe', 0.24094409057221153), ('ex', 0.14789177277894697), ('x', 0.022887449910836494), ('cnf1', 0.022883233588332334), ('cnf2', 0.022883233588332334), ('cnf3', 0.022883233588332334), ('j', 0.010488682177625192), ('cnfexcept', 0.009725338851490233), ('cnfe2', 0.009725338851490233)]
            throw new BuildException("Class Not Found: JDBC driver " + driver + " could not be loaded", e, getLocation());
        } catch (IllegalAccessException e) {
// e                    0	: [('e', 0.770118902161771), ('ie', 0.10665879385182621), ('iaexcept', 0.10284650496129012), ('j', 0.010488682177625192), ('tmpdir', 0.009143480971084294), ('reader', 0.007887604329591265), ('collectorFile', 0.007824462475810099), ('result', 0.005520881392228762), ('files', 0.004996060493384351), ('ret', 0.003923482879586936)]
            throw new BuildException("Illegal Access: JDBC driver " + driver + " could not be loaded", e, getLocation());
        } catch (InstantiationException e) {
// e                    0	: [('e', 0.770118902161771), ('ie', 0.18246541090479798), ('nsme', 0.1770294186953839), ('iexcept', 0.17702931501532235), ('j', 0.010488682177625192), ('tmpdir', 0.009143480971084294), ('reader', 0.007887604329591265), ('collectorFile', 0.007824462475810099), ('result', 0.005520881392228762), ('files', 0.004996060493384351)]
            throw new BuildException("Instantiation Exception: JDBC driver " + driver + " could not be loaded", e, getLocation());
        }
        return driverInstance;
    }

    /**
     * Set the caching attribute.
     * @param value a <code>boolean</code> value
     */
    public void isCaching(boolean value) {
// value                2	: [('enable', 0.8750495392621253), ('b', 0.08840525847196394), ('value', 0.021686075201411), ('verbose', 0.016829026586382915), ('append', 0.012448459200976782), ('debug', 0.008734879145209597), ('caseSensitive', 0.00873460266504539), ('recursive', 0.008621098130951438), ('preserveLastModified', 0.007973671083471783), ('overwrite', 0.00728360869636253)]
        caching = value;
    }

    /**
     * Gets the classpath.
     * @return Returns a Path
     */
    public Path getClasspath() {
        return classpath;
    }

    /**
     * Gets the autocommit.
     * @return Returns a boolean
     */
    public boolean isAutocommit() {
        return autocommit;
    }

    /**
     * Gets the url.
     * @return Returns a String
     */
    public String getUrl() {
        return url;
    }

    /**
     * Gets the userId.
     * @return Returns a String
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Set the user name for the connection; required.
     * @param userId The userId to set
     */
    public void setUserid(String userId) {
// userId               0	: [('userId', 0.9062538813067226), ('u', 0.43768285052262834), ('userid', 0.4375546719312422), ('name', 0.00769441960549964), ('s', 0.0027081106119227188), ('value', 0.002569089730620228), ('message', 0.002106870632192223), ('msg', 0.0019098336013175267), ('uri', 0.0017731817160287525), ('target', 0.00173598143302109)]
        this.userId = userId;
    }

    /**
     * Gets the password.
     * @return Returns a String
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets the rdbms.
     * @return Returns a String
     */
    public String getRdbms() {
        return rdbms;
    }

    /**
     * Gets the version.
     * @return Returns a String
     */
    public String getVersion() {
        return version;
    }
}
