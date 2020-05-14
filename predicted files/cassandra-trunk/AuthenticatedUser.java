// Path id: 24
// Path to file: C:\Users\Igor\IdeaProjects\SLP-Core-0.3\java-projects\cassandra-trunk\src\java\org\apache\cassandra\auth\AuthenticatedUser.java
// Number of identifiers: 57	Accuracy: 38.60%	MRR: 43.63%
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
// cassandra            0	: [('cassandra', 0.9348641624384896), ('commons', 0.00545779324188338), ('get', 0.000867026467084705), ('instance', 0.0007833881361183302), ('size', 0.0006203727251988352), ('db', 0.00060379320026365), ('add', 0.0005857334206920155), ('format', 0.00040269877486503146), ('utils', 0.00031013529265790053), ('key', 3.556713085411128e-05)]

import java.util.Set;

import com.google.common.base.Objects;
// base                 0	: [('base', 0.8993900292453327), ('hash', 0.028653893166780155), ('cache', 0.009559320552114867), ('cassandra', 0.0029135948198297673), ('get', 0.000867026467084705), ('instance', 0.0007833881361183302), ('size', 0.0006203727251988352), ('db', 0.00060379320026365), ('add', 0.0005857334206920155), ('format', 0.00040269877486503146)]

import org.apache.cassandra.config.DatabaseDescriptor;
// cassandra            0	: [('cassandra', 0.9279449836443108), ('commons', 0.012376972036062176), ('get', 0.000867026467084705), ('instance', 0.0007833881361183302), ('size', 0.0006203727251988352), ('db', 0.00060379320026365), ('add', 0.0005857334206920155), ('format', 0.00040269877486503146), ('utils', 0.00031013529265790053), ('key', 3.556713085411128e-05)]
// config               5	: [('db', 0.2031330920015156), ('utils', 0.10121821902638027), ('cql3', 0.07301002275722691), ('schema', 0.06443546977454097), ('service', 0.032186774722360666), ('config', 0.030352829282762128), ('exceptions', 0.03030087803090426), ('transport', 0.023603624707356788), ('index', 0.022596522699947146), ('stress', 0.01742285943723924)]
import org.apache.cassandra.dht.Datacenters;
// cassandra            0	: [('cassandra', 0.9279449836443108), ('commons', 0.012376972036062176), ('get', 0.000867026467084705), ('instance', 0.0007833881361183302), ('size', 0.0006203727251988352), ('db', 0.00060379320026365), ('add', 0.0005857334206920155), ('format', 0.00040269877486503146), ('utils', 0.00031013529265790053), ('key', 3.556713085411128e-05)]

/**
 * Returned from IAuthenticator#authenticate(), represents an authenticated user everywhere internally.
 *
 * Holds the name of the user and the roles that have been granted to the user. The roles will be cached
 * for roles_validity_in_ms.
 */
public class AuthenticatedUser
{
    public static final String SYSTEM_USERNAME = "system";
// SYSTEM_USERNAME      No	: [('KEYSPACE1', 0.1558437682325744), ('MBEAN_NAME', 0.0651754717290368), ('KEYSPACE', 0.04795306432793552), ('TYPE_NAME', 0.02350329448282017), ('CLASS', 0.016256369367470703), ('ID', 0.015467312786354883), ('INVALID_LEGACY_SSTABLE_ROOT_PROP', 0.015467288328905886), ('OBJECT_NAME', 0.008522884646876436), ('CLASS_NAME', 0.0077338443707266145), ('JAVA_UDF_NAME', 0.0077338443707266145)]
    public static final AuthenticatedUser SYSTEM_USER = new AuthenticatedUser(SYSTEM_USERNAME);
// SYSTEM_USER          No	: [('performer', 0.20770041000952885), ('user', 0.13847681496379832), ('key', 0.00023115425205975693), ('put', 0.00021380576823897901), ('result', 0.00017480428957422268), ('index', 0.0001617603167766453), ('id', 0.00011062794341014202), ('right', 8.936626775009091e-05), ('cell', 7.540921685668313e-05), ('E', 5.8060733035905234e-05)]
// SYSTEM_USERNAME      No	: [('username', 0.43751925915818823), ('roleName', 0.3437520478039213), ('execute', 0.001268158713928027), ('i', 0.0007599993568302636), ('cfs', 0.0006332338244446794), ('value', 0.0003291634712211688), ('name', 0.0003195367736320477), ('e', 0.0003152149238828457), ('metadata', 0.00030365140714843866), ('sstable', 0.00028242019607535633)]

    public static final String ANONYMOUS_USERNAME = "anonymous";
// ANONYMOUS_USERNAME   No	: [('CF_STANDARD', 0.03166412957515317), ('KEYSPACE1', 0.03039283028163647), ('KEYSPACE', 0.02297110184597304), ('CF_STANDARD2', 0.020207161316507974), ('CF_STANDARD1', 0.017802898455682153), ('MBEAN_NAME', 0.016113422666987735), ('KEYSPACE2', 0.015382563939462041), ('CF_STANDARDLOWINDEXINTERVAL', 0.012062362622934584), ('CF_INDEXED', 0.01206233001300259), ('CF_STANDARD3', 0.007842661367834717)]
    public static final AuthenticatedUser ANONYMOUS_USER = new AuthenticatedUser(ANONYMOUS_USERNAME);
// ANONYMOUS_USER       No	: [('performer', 0.20770041000952885), ('user', 0.13847681496379832), ('key', 0.00023115425205975693), ('put', 0.00021380576823897901), ('result', 0.00017480428957422268), ('index', 0.0001617603167766453), ('id', 0.00011062794341014202), ('right', 8.936626775009091e-05), ('cell', 7.540921685668313e-05), ('E', 5.8060733035905234e-05)]
// ANONYMOUS_USERNAME   No	: [('username', 0.43751925915818823), ('roleName', 0.3437520478039213), ('execute', 0.001268158713928027), ('i', 0.0007599993568302636), ('cfs', 0.0006332338244446794), ('value', 0.0003291634712211688), ('name', 0.0003195367736320477), ('e', 0.0003152149238828457), ('metadata', 0.00030365140714843866), ('sstable', 0.00028242019607535633)]

    // User-level permissions cache.
    private static final PermissionsCache permissionsCache = new PermissionsCache(DatabaseDescriptor.getAuthorizer());
// permissionsCache     No	: [('key', 0.00023115425205975693), ('put', 0.00021380576823897901), ('result', 0.00017480428957422268), ('index', 0.0001617603167766453), ('id', 0.00011062794341014202), ('right', 8.936626775009091e-05), ('cell', 7.540921685668313e-05), ('E', 5.8060733035905234e-05), ('limit', 5.558237820436553e-05), ('end', 5.3364902828777375e-05)]
    private static final NetworkAuthCache networkAuthCache = new NetworkAuthCache(DatabaseDescriptor.getNetworkAuthorizer());
// networkAuthCache     No	: [('key', 0.00023115425205975693), ('put', 0.00021380576823897901), ('result', 0.00017480428957422268), ('index', 0.0001617603167766453), ('id', 0.00011062794341014202), ('right', 8.936626775009091e-05), ('cell', 7.540921685668313e-05), ('E', 5.8060733035905234e-05), ('limit', 5.558237820436553e-05), ('end', 5.3364902828777375e-05)]

    private final String name;
// name                 0	: [('name', 0.16217156053888088), ('keyspaceName', 0.09871424280504773), ('description', 0.057341204240243956), ('string', 0.045609861033920555), ('columnDelimiter', 0.04552467523841493), ('archiveCommand', 0.04482345544320235), ('resource', 0.044769215687250515), ('loginConfigName', 0.04476406692418939), ('jmxObjectName', 0.04476192462425775), ('keyspace', 0.03605888305608886)]
    // primary Role of the logged in user
    private final RoleResource role;
// role                 1	: [('grantee', 0.6304732992467197), ('role', 0.19784792937911685), ('resource', 0.006906719363549505), ('ROLE_B_3', 0.006270424355758373), ('ROLE_C_2', 0.006270408050792376), ('ROLE_C_1', 0.006270408050792376), ('ROLE_C_3', 0.006270391745826379), ('ROOT_RESOURCE', 0.006270359135894386), ('key', 2.8894281507469616e-05), ('put', 2.6725721029872377e-05)]

    public AuthenticatedUser(String name)
// name                 0	: [('name', 0.051862465351503434), ('keyspace', 0.047576457660164044), ('keyspaceName', 0.02328181100870658), ('query', 0.016895033872806182), ('ksName', 0.011472841254951904), ('s', 0.009216603594709107), ('value', 0.008199687543519407), ('msg', 0.007973830321312613), ('key', 0.007599354342927692), ('str', 0.006485373852936805)]
    {
        this.name = name;
// name                 0	: [('name', 0.30895324856586), ('keyspace', 0.1560303365646933), ('pool', 0.05097781687050676), ('type', 0.012478197645888976), ('cfs', 0.006433863111832349), ('metadata', 0.006177390286417334), ('value', 0.005181342256500126), ('id', 0.004990043998428307), ('cassandra', 0.0029135948198297673), ('get', 0.0009188592751789163)]
// name                 0	: [('name', 0.8296818097184719), ('keepCase', 0.02143096664300653), ('keyspaceName', 0.006742732463643724), ('definition', 0.00673523026557143), ('row', 0.0019046330217424006), ('file', 0.0006987004534721565), ('column', 0.0006951416842288519), ('m', 0.0006872625582249412), ('options', 0.0005837213185095988), ('cfs', 0.00040083246124856907)]
        this.role = RoleResource.role(name);
// role                 No	: [('type', 0.10740389175329774), ('ifExists', 0.08497594037057334), ('ifNotExists', 0.04289100643811424), ('argTypes', 0.04228320569612279), ('fieldNames', 0.04226599923324041), ('arguments', 0.04226450574514159), ('options', 0.02233311574741974), ('id', 0.021960884594855543), ('scope', 0.02123320199329363), ('op', 0.021144111708038423)]
// role                 0	: [('role', 0.8984319877316554), ('fromName', 0.029091987896583557), ('root', 0.009989762480600179), ('cassandra', 0.0029135948198297673), ('get', 0.000867026467084705), ('instance', 0.0007833881361183302), ('size', 0.0006203727251988352), ('db', 0.00060379320026365), ('add', 0.0005857334206920155), ('format', 0.00040269877486503146)]
// name                 0	: [('name', 0.17520088680786866), ('grantee', 0.10003865595302534), ('state', 0.05006682794293669), ('roleName', 0.03500924758617121), ('principals', 0.03500891546025704), ('username', 0.030026076947515194), ('user', 0.0150132219313611), ('execute', 0.0006340793569640135), ('i', 0.0003799996784151318), ('cfs', 0.0003166169122223397)]
    }

    public String getName()
    {
        return name;
// name                 0	: [('name', 0.25418941416607294), ('metadata', 0.038252242189590814), ('sstable', 0.03271650324824777), ('level', 0.032655489645819026), ('stream', 0.03179386358650678), ('p', 0.03160453444054133), ('def', 0.03148142827862643), ('filename', 0.03146759566173367), ('logFile', 0.031467408154624704), ('conf', 0.01634997499955898)]
    }

    public RoleResource getPrimaryRole()
    {
        return role;
// role                 No	: [('conf', 0.03269994999911796), ('delegate', 0.02598768281059829), ('type', 0.0152978982710517), ('metadata', 0.014004484379181632), ('name', 0.008378828332145878), ('ssProxy', 0.0081010031305816), ('metric', 0.007262123708471132), ('kind', 0.006065675922796432), ('iterator', 0.005773995637960495), ('state', 0.005266554402015588)]
    }

    /**
     * Checks the user's superuser status.
     * Only a superuser is allowed to perform CREATE USER and DROP USER queries.
     * Im most cased, though not necessarily, a superuser will have Permission.ALL on every resource
     * (depends on IAuthorizer implementation).
     */
    public boolean isSuper()
// isSuper              No	: [('equals', 0.12192469572061766), ('isEmpty', 0.08325718339381108), ('hasNext', 0.05697972115128512), ('contains', 0.025795287831285856), ('isReverseOrder', 0.025438513373993665), ('isOpen', 0.019723127129010923), ('intersects', 0.01632794180905508), ('isDone', 0.016056091102586958), ('isGlobal', 0.015958382546644036), ('includes', 0.013593003414996656)]
    {
        return !isAnonymous() && Roles.hasSuperuserStatus(role);
// Roles                No	: [('cell', 0.013398383452781359), ('column', 0.01285019430478264), ('i', 0.010404549710055788), ('result', 0.008185857707838237), ('file', 0.008015651609393453), ('options', 0.0071163687326978985), ('type', 0.006623265517945568), ('rightState', 0.0063114000323316755), ('state', 0.005215687608603936), ('pathAsFile', 0.005048840333154527)]
// role                 0	: [('role', 0.7562650616291882), ('ROLE_A', 0.17083498072470737), ('execute', 0.0006340793569640135), ('i', 0.0003799996784151318), ('cfs', 0.0003166169122223397), ('value', 0.0001645817356105844), ('name', 0.00015976838681602384), ('e', 0.00015760746194142284), ('metadata', 0.00015182570357421933), ('sstable', 0.00014121009803767817)]
    }

    /**
     * If IAuthenticator doesn't require authentication, this method may return true.
     */
    public boolean isAnonymous()
    {
        return this == ANONYMOUS_USER;
// ANONYMOUS_USER       No	: [('o', 0.3491335225236574), ('otherType', 0.24139500312695933), ('that', 0.10458620275952282), ('previous', 0.07124264792574501), ('root', 0.0344690139518995), ('END', 0.03443006138813274), ('record', 0.03442944179942485), ('rightmostLeaf', 0.03442874883836998), ('key', 3.809749393234228e-05), ('put', 1.3362860514936188e-05)]
    }

    /**
     * Some internal operations are performed on behalf of Cassandra itself, in those cases
     * the system user should be used where an identity is required
     * see CreateRoleStatement#execute() and overrides of AlterSchemaStatement#createdResources()
     */
    public boolean isSystem()
    {
        return this == SYSTEM_USER;
// SYSTEM_USER          No	: [('o', 0.3491335225236574), ('otherType', 0.24139500312695933), ('that', 0.10458620275952282), ('previous', 0.07124264792574501), ('root', 0.0344690139518995), ('END', 0.03443006138813274), ('record', 0.03442944179942485), ('rightmostLeaf', 0.03442874883836998), ('key', 3.809749393234228e-05), ('put', 1.3362860514936188e-05)]
    }

    /**
     * Get the roles that have been granted to the user via the IRoleManager
     *
     * @return a set of identifiers for the roles that have been granted to the user
     */
    public Set<RoleResource> getRoles()
    {
        return Roles.getRoles(role);
// Roles                No	: [('conf', 0.03269994999911796), ('delegate', 0.02598768281059829), ('type', 0.0152978982710517), ('metadata', 0.014004484379181632), ('name', 0.008378828332145878), ('ssProxy', 0.0081010031305816), ('metric', 0.007262123708471132), ('kind', 0.006065675922796432), ('iterator', 0.005773995637960495), ('state', 0.005266554402015588)]
// role                 5	: [('ROLE_A', 0.5579578402372934), ('grantee', 0.09944334061418464), ('currentUser', 0.057955912701672496), ('resource', 0.028993612223875035), ('primaryRole', 0.028979603742210272), ('role', 0.006280123258376486), ('execute', 0.001268158713928027), ('i', 0.0007599993568302636), ('cfs', 0.0006332338244446794), ('value', 0.0003291634712211688)]
    }

    /**
     * Get the detailed info on roles granted to the user via IRoleManager
     *
     * @return a set of Role objects detailing the roles granted to the user
     */
    public Set<Role> getRoleDetails()
    {
       return Roles.getRoleDetails(role);
// Roles                No	: [('conf', 0.03269994999911796), ('delegate', 0.02598768281059829), ('type', 0.0152978982710517), ('metadata', 0.014004484379181632), ('name', 0.008378828332145878), ('ssProxy', 0.0081010031305816), ('metric', 0.007262123708471132), ('kind', 0.006065675922796432), ('iterator', 0.005773995637960495), ('state', 0.005266554402015588)]
// role                 2	: [('ROLE_A', 0.6944477392271925), ('primaryRole', 0.09722455323715976), ('role', 0.04169678992504315), ('execute', 0.001268158713928027), ('i', 0.0007599993568302636), ('cfs', 0.0006332338244446794), ('value', 0.0003291634712211688), ('name', 0.0003195367736320477), ('e', 0.0003152149238828457), ('metadata', 0.00030365140714843866)]
    }

    public Set<Permission> getPermissions(IResource resource)
// getPermissions       0	: [('getPermissions', 0.5115046983885406), ('permissions', 0.25304559974667606), ('perms', 0.03450400557493328), ('ALL', 0.011503799742087196), ('NONE', 0.01150337393964766), ('AGGREGATE_FUNCTION_PERMISSIONS', 0.011501539630973001), ('KEYSPACE_LEVEL_PERMISSIONS', 0.011501539630973001), ('TABLE_LEVEL_PERMISSIONS', 0.011501531478490002), ('SCALAR_FUNCTION_PERMISSIONS', 0.011501531478490002), ('COLLECTION_LEVEL_PERMISSIONS', 0.011501531478490002)]
// resource             0	: [('resource', 0.42158476627645863), ('droppedResource', 0.16947416148694064), ('r', 0.08478078181586388), ('fromName', 0.003910170718882617), ('key', 0.00011557712602987846), ('put', 0.00010690288411948951), ('result', 8.740214478711134e-05), ('index', 8.088015838832265e-05), ('id', 5.531397170507101e-05), ('right', 4.4683133875045454e-05)]
    {
        return permissionsCache.getPermissions(this, resource);
// permissionsCache     No	: [('get', 0.22994424125242013), ('user', 0.22926290531838478), ('resource', 0.22919999819861986), ('getPermissions', 0.0625861178634712), ('conf', 0.0048857118749542966), ('delegate', 0.00477119750903096), ('type', 0.0025888605839761987), ('value', 0.0024095051293324707), ('row', 0.00240527713997005), ('metadata', 0.0021793452888950538)]
// getPermissions       No	: [('cassandra', 0.046617517117276276), ('get', 0.01387242347335528), ('instance', 0.012534210177893284), ('size', 0.009925963603181364), ('db', 0.0096606912042184), ('add', 0.009371734731072247), ('format', 0.006443180397840503), ('utils', 0.0049621646825264085), ('put', 0.00458058681997879), ('build', 0.004372134952134838)]
// resource             No	: [('current', 0.034433171672828396), ('bytes', 0.029248998408513364), ('MBEAN_NAME', 0.025767311486383674), ('handler', 0.022987948213087996), ('t', 0.022169648252356178), ('endpoint', 0.020273546569839797), ('cur', 0.020107406013172235), ('metadata', 0.014941572749955646), ('n', 0.014368586265065866), ('keyspace', 0.011817353267447843)]
    }

    /**
     * Check whether this user has login privileges.
     * LOGIN is not inherited from granted roles, so must be directly granted to the primary role for this user
     *
     * @return true if the user is permitted to login, false otherwise.
     */
    public boolean canLogin()
// canLogin             No	: [('equals', 0.12659941349737447), ('isEmpty', 0.09761314768146152), ('hasNext', 0.05937789146367344), ('isReverseOrder', 0.03395477792251807), ('contains', 0.026411157429156444), ('isGlobal', 0.023423206717337466), ('includes', 0.01837951329050991), ('isLive', 0.016115258724329264), ('await', 0.014361826913035928), ('error', 0.010884053255558399)]
    {
        return Roles.canLogin(getPrimaryRole());
// Roles                No	: [('conf', 0.03269994999911796), ('delegate', 0.02598768281059829), ('type', 0.0152978982710517), ('metadata', 0.014004484379181632), ('name', 0.008378828332145878), ('ssProxy', 0.0081010031305816), ('metric', 0.007262123708471132), ('kind', 0.006065675922796432), ('iterator', 0.005773995637960495), ('state', 0.005266554402015588)]
// canLogin             0	: [('canLogin', 0.050009229950752514), ('cassandra', 0.011654379279319069), ('get', 0.00346810586833882), ('instance', 0.003133552544473321), ('size', 0.002481490900795341), ('db', 0.0024151728010546), ('add', 0.002342933682768062), ('format', 0.0016107950994601258), ('utils', 0.0012405411706316021), ('put', 0.0011451467049946975)]
    }

    /**
     * Verify that there is not DC level restriction on this user accessing this node.
     * Further extends the login privilege check by verifying that the primary role for this user is permitted
     * to perform operations in the local (to this node) datacenter. Like LOGIN, this is not inherited from
     * granted roles.
     * @return true if the user is permitted to access nodes in this node's datacenter, false otherwise
     */
    public boolean hasLocalAccess()
    {
        return networkAuthCache.get(this.getPrimaryRole()).canAccess(Datacenters.thisDatacenter());
// networkAuthCache     No	: [('conf', 0.03269994999911796), ('delegate', 0.02598768281059829), ('type', 0.0152978982710517), ('metadata', 0.014004484379181632), ('name', 0.008378828332145878), ('ssProxy', 0.0081010031305816), ('metric', 0.007262123708471132), ('kind', 0.006065675922796432), ('iterator', 0.005773995637960495), ('state', 0.005266554402015588)]
// get                  1	: [('cassandra', 0.046617517117276276), ('get', 0.01387242347335528), ('instance', 0.012534210177893284), ('size', 0.009925963603181364), ('db', 0.0096606912042184), ('add', 0.009371734731072247), ('format', 0.006443180397840503), ('utils', 0.0049621646825264085), ('put', 0.00458058681997879), ('build', 0.004372134952134838)]
    }

    @Override
    public String toString()
    {
        return String.format("#<User %s>", name);
// format               0	: [('format', 0.9220054037845852), ('join', 0.010576754669170052), ('cassandra', 0.0029135948198297673), ('get', 0.000867026467084705), ('instance', 0.0007833881361183302), ('size', 0.0006203727251988352), ('db', 0.00060379320026365), ('add', 0.0005857334206920155), ('utils', 0.00031013529265790053), ('key', 3.556713085411128e-05)]
// name                 No	: [('row', 0.03330254062875676), ('i', 0.0047850555616459735), ('version', 0.003770350814599432), ('map', 0.0035812237816568887), ('cfs', 0.0032791500332505614), ('list', 0.0029561354363527212), ('set', 0.0028981990424469793), ('e', 0.002572607368856608), ('bytes', 0.0025431949707284854), ('key', 0.002474604823777806)]
    }

    @Override
    public boolean equals(Object o)
// equals               0	: [('equals', 0.463816608375209), ('selectColumns', 0.045093976534650235), ('isEmpty', 0.04221621283873433), ('hasNext', 0.038161569176224354), ('contains', 0.030427909859828538), ('remove', 0.0241795917379166), ('isInclusive', 0.021291798842258534), ('accepts', 0.015262446701113274), ('isFrozen', 0.01507912229851154), ('test', 0.012118659013076133)]
// o                    0	: [('o', 0.6260492296056113), ('other', 0.12281028236377993), ('obj', 0.09631902876598637), ('that', 0.04358122785743045), ('oo', 0.011884944465112129), ('to', 0.003964214096941178), ('value', 0.0038130825684977994), ('parsed', 0.003726496693483809), ('key', 0.002335565546311907), ('put', 1.3362860514936188e-05)]
    {
        if (this == o)
// o                    0	: [('o', 0.7416713939394238), ('obj', 0.052477810898766006), ('other', 0.048607844871868616), ('previous', 0.04164956253729166), ('pos', 0.015050347472417057), ('that', 0.012558417876194954), ('otherType', 0.010269807032936003), ('ctx', 0.007498666408179161), ('key', 3.809749393234228e-05), ('put', 1.3362860514936188e-05)]
            return true;

        if (!(o instanceof AuthenticatedUser))
// o                    0	: [('o', 0.3776384960994833), ('other', 0.07805244306796504), ('receiver', 0.04982409666745746), ('obj', 0.04128047617674855), ('e', 0.04057666898777303), ('parsed', 0.036451167715592946), ('previous', 0.020684013271549343), ('val', 0.012817849358148772), ('p1', 0.012790708962277625), ('parsedNumber', 0.012788906727198701)]
            return false;

        AuthenticatedUser u = (AuthenticatedUser) o;
// u                    No	: [('user', 0.5692384074818992), ('performer', 0.10385020500476444), ('key', 0.00011557712602987846), ('put', 0.00010690288411948951), ('result', 8.740214478711134e-05), ('index', 8.088015838832265e-05), ('id', 5.531397170507101e-05), ('right', 4.4683133875045454e-05), ('cell', 3.7704608428341566e-05), ('E', 2.9030366517952613e-05)]
// o                    4	: [('execute', 0.001031885410207032), ('logger', 0.000999078101553483), ('builder', 0.0009157494276875069), ('size', 0.0006447626098854788), ('o', 0.0005764343814055423), ('value', 0.0005590446248781784), ('type', 0.00047283033963206145), ('sb', 0.00046379812367238567), ('result', 0.0004492175613955117), ('service', 0.00023872286153450367)]

        return Objects.equal(name, u.name);
// equal                1	: [('equals', 0.43480518516343747), ('equal', 0.3940907679408433), ('hash', 0.05661852804423735), ('hashCode', 0.05071063003886327), ('cassandra', 0.0029135948198297673), ('get', 0.000867026467084705), ('instance', 0.0007833881361183302), ('size', 0.0006203727251988352), ('db', 0.00060379320026365), ('add', 0.0005857334206920155)]
// name                 No	: [('level', 0.09877332071748834), ('change', 0.049383720610854866), ('resource', 0.0301571488556136), ('left', 0.02473529978925383), ('id', 0.024724194127872783), ('kind', 0.024703316282145327), ('grantee', 0.024695635385224072), ('status', 0.024695051770817378), ('cipher', 0.02469202701436077), ('class_name', 0.024690465101994262)]
// u                    No	: [('j', 0.15057426629043388), ('rs', 0.15056432158304853), ('f', 0.15055415257205254), ('other', 0.15055236144947215), ('scc', 0.1505378224872277), ('config', 0.010206510273818631), ('alias', 0.005900939331531022), ('argNames', 0.0059005832663196565), ('argTypes', 0.004691461832779989), ('kind', 0.004458902412809381)]
// name                 No	: [('clustering', 0.500653781044008), ('cassandra', 0.023308758558638138), ('get', 0.00693621173667764), ('instance', 0.006267105088946642), ('size', 0.004962981801590682), ('db', 0.0048303456021092), ('add', 0.004685867365536124), ('format', 0.0032215901989202517), ('utils', 0.0024810823412632042), ('put', 0.002290293409989395)]
    }

    @Override
    public int hashCode()
// hashCode             0	: [('hashCode', 0.5621127071418136), ('size', 0.0716780102319567), ('read', 0.046355237005388156), ('compare', 0.02983835419108469), ('rowCount', 0.025070045782037063), ('available', 0.024864530230610558), ('partitionCount', 0.0201704793764055), ('weight', 0.016550152233138694), ('write', 0.012981786631133795), ('byteSize', 0.01247322346671733)]
    {
        return Objects.hashCode(name);
// hashCode             1	: [('hash', 0.428240149665859), ('hashCode', 0.3750349543631876), ('equals', 0.06859104475574872), ('equal', 0.0643589624025862), ('cassandra', 0.0029135948198297673), ('get', 0.000867026467084705), ('instance', 0.0007833881361183302), ('size', 0.0006203727251988352), ('db', 0.00060379320026365), ('add', 0.0005857334206920155)]
// name                 0	: [('name', 0.0779432514848049), ('kind', 0.0673802585444267), ('level', 0.05182394385304942), ('column', 0.04675377695381616), ('keyspace', 0.031252019517002703), ('change', 0.03111736551196874), ('days', 0.031116297887768023), ('update', 0.015582246964227952), ('ksName', 0.015580159577508253), ('resource', 0.015564866367898928)]
    }

}
