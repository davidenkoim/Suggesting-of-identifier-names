// Path id: 28
// Path to file: C:\Users\Igor\IdeaProjects\SLP-Core-0.3\java-projects\ant-master\src\main\org\apache\tools\ant\AntTypeDefinition.java
// Number of identifiers: 211	Accuracy: 28.44%	MRR: 35.35%
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

package org.apache.tools.ant;
// ant                  0	: [('ant', 0.9196221304565559), ('zip', 0.020557180517216726), ('junit', 0.0008939559022265902), ('types', 0.0007479158730941966), ('length', 0.0006860708516996915), ('equals', 0.0005306652343615748), ('append', 0.00048797343340415816), ('add', 0.0004666356828995815), ('getProperty', 0.00044226346411966184), ('resources', 0.00015627705064836595)]

import java.lang.reflect.Constructor;
// java                 0	: [('java', 0.6107362474862659), ('junit', 0.001000375882905895), ('jdepend', 1.7454772079941744e-05), ('resources', 4.946654326623327e-06), ('srcDir', 3.6456065211351777e-06), ('separator', 3.5659505330440663e-06), ('expected', 3.4331905528922146e-06), ('values', 2.9287026283151774e-06), ('os', 2.6100786759507327e-06)]
import java.lang.reflect.InvocationTargetException;
// java                 0	: [('java', 0.938236247486266), ('junit', 0.001000375882905895), ('jdepend', 1.7454772079941744e-05), ('resources', 4.946654326623327e-06), ('srcDir', 3.6456065211351777e-06), ('separator', 3.5659505330440663e-06), ('expected', 3.4331905528922146e-06), ('values', 2.9287026283151774e-06), ('os', 2.6100786759507327e-06)]


/**
 * This class contains all the information
 * on a particular ant type,
 * the classname, adapter and the class
 * it should be assignable from.
 * This type replaces the task/datatype split
 * of pre ant 1.6.
 *
 */
public class AntTypeDefinition {
    private String      name;
// name                 0	: [('name', 0.14841707080575836), ('value', 0.020937721892671485), ('key', 0.018881483245033973), ('property', 0.018509721414947163), ('pattern', 0.016506381192455892), ('from', 0.015097121340495252), ('className', 0.01358718971209244), ('filename', 0.012769340945389496), ('refid', 0.010973625117921663), ('file', 0.00874731759139547)]
    private Class<?>       clazz;
// clazz                0	: [('clazz', 0.17228709750390703), ('adapterClass', 0.13207252344347048), ('adaptToClass', 0.13207252344347048), ('jaasProviderClass', 0.1273580087737927), ('c', 0.06334481181623017), ('testClass', 0.025943968778842516), ('theClass', 0.014144720279923624), ('taskClass', 0.014144640623935533), ('proxyClass', 0.011787117769136343), ('resources', 3.0498575831120466e-05)]
    private Class<?>       adapterClass;
// adapterClass         1	: [('clazz', 0.17228709750390703), ('adapterClass', 0.13207252344347048), ('adaptToClass', 0.13207252344347048), ('jaasProviderClass', 0.1273580087737927), ('c', 0.06334481181623017), ('testClass', 0.025943968778842516), ('theClass', 0.014144720279923624), ('taskClass', 0.014144640623935533), ('proxyClass', 0.011787117769136343), ('resources', 3.0498575831120466e-05)]
    private Class<?>       adaptToClass;
// adaptToClass         2	: [('clazz', 0.17228709750390703), ('adapterClass', 0.13207252344347048), ('adaptToClass', 0.13207252344347048), ('jaasProviderClass', 0.1273580087737927), ('c', 0.06334481181623017), ('testClass', 0.025943968778842516), ('theClass', 0.014144720279923624), ('taskClass', 0.014144640623935533), ('proxyClass', 0.011787117769136343), ('resources', 3.0498575831120466e-05)]
    private String      className;
// className            7	: [('name', 0.03051720751115822), ('encoding', 0.024486540413391798), ('value', 0.01816943754543977), ('property', 0.014442735769014151), ('prefix', 0.01188380764713464), ('password', 0.010531988238613496), ('mComment', 0.01002805786640267), ('className', 0.008913573854266056), ('type', 0.008798070273725662), ('mCfile', 0.008205263187395447)]
    private ClassLoader classLoader;
// classLoader          8	: [('scriptLoader', 0.19263644345741954), ('mirrorLoader', 0.12842536836220755), ('parent', 0.07503274320942135), ('coreLoader', 0.06871069614469338), ('myClassLoader', 0.06421397464304318), ('savedContextLoader', 0.06421386843505907), ('antlibClassLoader', 0.06421386843505907), ('classpathLoader', 0.06421376222707494), ('classLoader', 0.035850114651098504), ('loader', 0.020696123919780426)]
    private boolean     restrict = false;
// restrict             No	: [('verbose', 0.016156202188413803), ('failOnError', 0.015134892136429184), ('preserveLastModified', 0.012060989114701768), ('debug', 0.009351160880239379), ('quiet', 0.008864263610916148), ('caseSensitive', 0.007970180545899902), ('force', 0.007792557332412984), ('append', 0.007677683687071003), ('followSymlinks', 0.006768628960777405), ('cache', 0.006464028056242437)]

    /**
     * Set the restrict attribute.
     * @param restrict the value to set.
     */
    public void setRestrict(boolean restrict) {
// restrict             0	: [('restrict', 0.750250555327233), ('b', 0.021442200676578975), ('value', 0.005499961407678611), ('verbose', 0.0033065117305871774), ('append', 0.002807947268997664), ('debug', 0.002415978088012594), ('enable', 0.002038435827366788), ('recursive', 0.002032513388750782), ('caseSensitive', 0.0018292208945948035), ('followSymlinks', 0.0017406748078116485)]
         this.restrict = restrict;
// restrict             0	: [('restrict', 0.5002393477634551), ('name', 0.013573724928790267), ('value', 0.00874895467817877), ('classpath', 0.008334752967917079), ('file', 0.00699189924639317), ('project', 0.006340821635640338), ('encoding', 0.005877194284104091), ('destDir', 0.004050957347901424), ('task', 0.003453976722165639), ('verbose', 0.0030718182735366313)]
// restrict             0	: [('restrict', 0.9375031860093301), ('buildRule', 0.0003447498504056685), ('b', 0.00027800173773589066), ('name', 0.00027117843913279166), ('value', 0.0002548837268286694), ('p', 0.0002253135808860682), ('file', 0.00021164999278611358), ('project', 0.00019597227742876034), ('line', 0.00017298263768407527), ('FILE_UTILS', 0.0001631217216778279)]
    }

    /**
     * Get the restrict attribute.
     * @return the restrict attribute.
     */
    public boolean isRestrict() {
        return restrict;
// restrict             No	: [('name', 0.030419554693607725), ('value', 0.01834742953492868), ('file', 0.011130254047683873), ('project', 0.010849147228936986), ('mComment', 0.00607842313545328), ('VALUES', 0.0060783700314612195), ('linkFlag', 0.005539642092360932), ('classpath', 0.005244512200849215), ('pattern', 0.005183151644162599), ('fileset', 0.005167559164493323)]
    }

    /**
     * Set the definition's name.
     * @param name the name of the definition.
     */
    public void setName(String name) {
// name                 0	: [('name', 0.7653173029139694), ('s', 0.032786278930271924), ('value', 0.024027019450322134), ('pattern', 0.022441206806693818), ('n', 0.021707506853236684), ('fname', 0.02146006252974499), ('message', 0.0015872846047830366), ('target', 0.0013593510036411954), ('resources', 4.946654326623327e-06), ('junit', 4.548374386167771e-06)]
        this.name = name;
// name                 0	: [('name', 0.4475359890797337), ('taskName', 0.019572036367376314), ('baseDir', 0.01094582287802851), ('resourceName', 0.009924216860297087), ('parentName', 0.009669140617815604), ('rootClass', 0.009669087513823543), ('currentProjectName', 0.009669087513823543), ('value', 0.00874895467817877), ('classpath', 0.008334752967917079), ('file', 0.00699189924639317)]
// name                 0	: [('name', 0.7969506944881386), ('normalizedName', 0.034576120782970836), ('value', 0.015970250824776586), ('other', 0.014491685404043516), ('trim', 0.005688565761781659), ('event', 0.001680890953738125), ('file', 0.0014806855257810374), ('key', 0.00129683276604801), ('buildRule', 0.0003447498504056685), ('b', 0.00027800173773589066)]
    }

    /**
     * Return the definition's name.
     * @return the name of the definition.
     */
    public String getName() {
        return name;
// name                 0	: [('name', 0.4131689610202733), ('ftpFile', 0.022894992709973416), ('parts', 0.02160951390412473), ('pattern', 0.012795657454734358), ('f', 0.01104542557848598), ('attributeName', 0.010481833969015484), ('resourceName', 0.010481276377098846), ('testCase', 0.010481223273106785), ('archiveName', 0.010481010857138543), ('result', 0.0009699694382947875)]
    }

    /**
     * Set the class of the definition.
     * As a side-effect may set the classloader and classname.
     * @param clazz the class of this definition.
     */
    public void setClass(Class<?> clazz) {
// clazz                0	: [('clazz', 0.19728709750390702), ('c', 0.18001147848289684), ('proxyClass', 0.09512045110246967), ('testClass', 0.07594396877884252), ('adapterClass', 0.04040585677680381), ('adaptToClass', 0.04040585677680381), ('ifc', 0.01902467544045938), ('resolverImplClass', 0.019024622336467317), ('files', 0.0005551626305462837), ('resources', 3.0498575831120466e-05)]
        this.clazz = clazz;
// clazz                No	: [('name', 0.027147449857580535), ('value', 0.01749790935635754), ('classpath', 0.016669505935834158), ('file', 0.01398379849278634), ('project', 0.012681643271280676), ('encoding', 0.011754388568208181), ('destDir', 0.008101914695802848), ('task', 0.006907953444331278), ('dir', 0.00631707030081454), ('verbose', 0.006143636547073263)]
// clazz                No	: [('cl', 0.1250754211249415), ('c', 0.06353008143000259), ('helper', 0.06285560856980393), ('def', 0.06266876803659562), ('classLoader', 0.06261373700305706), ('buildRule', 0.002757998803245348), ('b', 0.0022240139018871253), ('name', 0.0021694275130623333), ('value', 0.0020390698146293554), ('p', 0.0018025086470885455)]
        if (clazz == null) {
// clazz                No	: [('type', 0.6256694043710542), ('cl', 0.1253059396940238), ('file', 0.0022936366834872303), ('c', 0.001872760828304337), ('task', 0.001814313244621639), ('f', 0.0016828366848850404), ('line', 0.001551602465983014), ('value', 0.0015009540015446191), ('ch', 0.001366287146242296), ('index', 0.001293331879944451)]
            return;
        }
        this.classLoader = (classLoader == null)
// classLoader          No	: [('curpwd', 0.15272755450283718), ('timestampGranularity', 0.07629011987915355), ('classPath', 0.040678337347832216), ('testMethods', 0.040678098379867945), ('fileOutputStream', 0.04059621797267818), ('executable', 0.03819715191046641), ('sourcePath', 0.03815585325492512), ('outfile', 0.03815582670292909), ('closables', 0.03815561428696084), ('qualifiedName', 0.03813533668713462)]
// classLoader          No	: [('bsBuffShadow', 0.004517052189409788), ('i', 0.004512757332117899), ('bits24', 0.004485857979183238), ('message', 0.0023944342813055626), ('classpath', 0.0017874261029237296), ('basedir', 0.0015839379589081114), ('octetString', 0.0015072269291511937), ('zvec', 0.001503514069293394), ('ftab', 0.0015030914471501342), ('length1', 0.0015016045353724334)]
            ? clazz.getClassLoader() : classLoader;
// clazz                No	: [('EOF', 0.06582536579322348), ('f', 0.0657769678619803), ('r', 0.06576719672744112), ('myfile', 0.06575248692164029), ('outputLog', 0.06575184967373557), ('SYSTEM_LOADER_REF', 0.0657517965697435), ('errorLog', 0.06575174346575144), ('keepAliveOutput', 0.06575169036175939), ('NULL_URL', 0.06575158415377526), ('message', 0.021343769928901083)]
// classLoader          No	: [('parent', 0.7543381685621654), ('resources', 0.009901271085793403), ('name', 0.0075586454141027765), ('FILE_UTILS', 0.00723654248882073), ('mapperElement', 0.007214874359220345), ('s', 0.005953253577829437), ('ds', 0.004998570139363016), ('result', 0.004951169723929995), ('message', 0.004855706794002796), ('mapper', 0.004811520870929053)]
        this.className = (className == null) ? clazz.getName() : className;
// className            No	: [('name', 0.013731594641402903), ('file', 0.013273621537013112), ('writer', 0.01326787307941848), ('value', 0.010593267078984267), ('bsLive', 0.009357330036873366), ('project', 0.008564012392202995), ('task', 0.008514829862322796), ('target', 0.007442976561963357), ('currentState', 0.006711504937676005), ('crc', 0.006468234856560098)]
// className            No	: [('bsBuffShadow', 0.009034104378819576), ('i', 0.009025514664235798), ('bits24', 0.008971715958366476), ('message', 0.004788868562611125), ('classpath', 0.003574852205847459), ('basedir', 0.003167875917816223), ('octetString', 0.0030144538583023873), ('zvec', 0.003007028138586788), ('ftab', 0.0030061828943002684), ('length1', 0.0030032090707448667)]
// clazz                No	: [('EOF', 0.06582536579322348), ('f', 0.0657769678619803), ('r', 0.06576719672744112), ('myfile', 0.06575248692164029), ('outputLog', 0.06575184967373557), ('SYSTEM_LOADER_REF', 0.0657517965697435), ('errorLog', 0.06575174346575144), ('keepAliveOutput', 0.06575169036175939), ('NULL_URL', 0.06575158415377526), ('message', 0.021343769928901083)]
// className            No	: [('name', 0.5075586454141028), ('FILE_UTILS', 0.2572365424888207), ('resources', 0.009901271085793403), ('mapperElement', 0.007214874359220345), ('s', 0.005953253577829437), ('result', 0.004951169723929995), ('message', 0.004855706794002796), ('mapper', 0.004811520870929053), ('parent', 0.004338168562165408), ('files', 0.0008950641447450019)]
    }

    /**
     * Set the classname of the definition.
     * @param className the classname of this definition.
     */
    public void setClassName(String className) {
// className            0	: [('className', 0.700905533372378), ('type', 0.1754465484648802), ('name', 0.006388731485397978), ('value', 0.0025984480217507034), ('s', 0.0024291360731290646), ('message', 0.0015872846047830366), ('msg', 0.0014255603542670425), ('target', 0.0013593510036411954), ('line', 0.0012998412890643546), ('uri', 0.0012219908899137215)]
        this.className = className;
// className            0	: [('className', 0.33459874458986333), ('mainClass', 0.16713688263095086), ('name', 0.013573724928790267), ('value', 0.00874895467817877), ('classpath', 0.008334752967917079), ('file', 0.00699189924639317), ('project', 0.006340821635640338), ('encoding', 0.005877194284104091), ('destDir', 0.004050957347901424), ('task', 0.003453976722165639)]
// className            0	: [('className', 0.7147025074017304), ('fcqn', 0.16142051009514252), ('name', 0.00405905722701158), ('e', 0.003921489768644146), ('test', 0.0038236700839574702), ('qualifiedName', 0.0038011665933998574), ('line', 0.002066922031623469), ('param', 0.0019409495822627822), ('filePath', 0.0018991882457013315), ('buildRule', 0.0003447498504056685)]
    }

    /**
     * Get the classname of the definition.
     * @return the name of the class of this definition.
     */
    public String getClassName() {
        return className;
// className            0	: [('className', 0.3140759920257034), ('type', 0.06471643941467019), ('parent', 0.064293665102667), ('test', 0.06335493043471528), ('name', 0.015209777346803862), ('value', 0.00917371476746434), ('file', 0.005565127023841937), ('project', 0.005424573614468493), ('mComment', 0.00303921156772664), ('VALUES', 0.0030391850157306098)]
    }

    /**
     * Set the adapter class for this definition.
     * This class is used to adapt the definitions class if
     * required.
     * @param adapterClass the adapterClass.
     */
    public void setAdapterClass(Class<?> adapterClass) {
// adapterClass         4	: [('clazz', 0.19728709750390702), ('c', 0.18001147848289684), ('proxyClass', 0.09512045110246967), ('testClass', 0.07594396877884252), ('adapterClass', 0.04040585677680381), ('adaptToClass', 0.04040585677680381), ('ifc', 0.01902467544045938), ('resolverImplClass', 0.019024622336467317), ('files', 0.0005551626305462837), ('resources', 3.0498575831120466e-05)]
        this.adapterClass = adapterClass;
// adapterClass         0	: [('adapterClass', 0.5002351518016686), ('name', 0.013573724928790267), ('value', 0.00874895467817877), ('classpath', 0.008334752967917079), ('file', 0.00699189924639317), ('project', 0.006340821635640338), ('encoding', 0.005877194284104091), ('destDir', 0.004050957347901424), ('task', 0.003453976722165639), ('verbose', 0.0030718182735366313)]
// adapterClass         0	: [('adapterClass', 0.9375028142813857), ('buildRule', 0.0003447498504056685), ('b', 0.00027800173773589066), ('name', 0.00027117843913279166), ('value', 0.0002548837268286694), ('p', 0.0002253135808860682), ('file', 0.00021164999278611358), ('project', 0.00019597227742876034), ('line', 0.00017298263768407527), ('FILE_UTILS', 0.0001631217216778279)]
    }

    /**
     * Set the assignable class for this definition.
     * @param adaptToClass the assignable class.
     */

    public void setAdaptToClass(Class<?> adaptToClass) {
// adaptToClass         5	: [('clazz', 0.19728709750390702), ('c', 0.18001147848289684), ('proxyClass', 0.09512045110246967), ('testClass', 0.07594396877884252), ('adapterClass', 0.04040585677680381), ('adaptToClass', 0.04040585677680381), ('ifc', 0.01902467544045938), ('resolverImplClass', 0.019024622336467317), ('files', 0.0005551626305462837), ('resources', 3.0498575831120466e-05)]
        this.adaptToClass = adaptToClass;
// adaptToClass         0	: [('adaptToClass', 0.5002351518016686), ('name', 0.013573724928790267), ('value', 0.00874895467817877), ('classpath', 0.008334752967917079), ('file', 0.00699189924639317), ('project', 0.006340821635640338), ('encoding', 0.005877194284104091), ('destDir', 0.004050957347901424), ('task', 0.003453976722165639), ('verbose', 0.0030718182735366313)]
// adaptToClass         0	: [('adaptToClass', 0.9375028142813857), ('buildRule', 0.0003447498504056685), ('b', 0.00027800173773589066), ('name', 0.00027117843913279166), ('value', 0.0002548837268286694), ('p', 0.0002253135808860682), ('file', 0.00021164999278611358), ('project', 0.00019597227742876034), ('line', 0.00017298263768407527), ('FILE_UTILS', 0.0001631217216778279)]
    }

    /**
     * Set the classloader to use to create an instance
     * of the definition.
     * @param classLoader the ClassLoader.
     */
    public void setClassLoader(ClassLoader classLoader) {
// classLoader          1	: [('loader', 0.5421363965713429), ('classLoader', 0.37277781284096206), ('parent', 0.010994779853465515), ('cl', 0.008297764806513425), ('c', 0.004715833813874197), ('al', 0.004707947871053177), ('origLoader', 0.0031409280866442893), ('l', 0.002698456837802705), ('resources', 4.946654326623327e-06), ('junit', 4.548374386167771e-06)]
        this.classLoader = classLoader;
// classLoader          2	: [('manager', 0.1254904670121144), ('scriptLoader', 0.1252557480254472), ('classLoader', 0.12523624043350587), ('antlibClassLoader', 0.1252351252496726), ('name', 0.013573724928790267), ('value', 0.00874895467817877), ('classpath', 0.008334752967917079), ('file', 0.00699189924639317), ('project', 0.006340821635640338), ('encoding', 0.005877194284104091)]
// classLoader          0	: [('classLoader', 0.8906392171253821), ('project', 0.01582097227742876), ('buildRule', 0.0003447498504056685), ('b', 0.00027800173773589066), ('name', 0.00027117843913279166), ('value', 0.0002548837268286694), ('p', 0.0002253135808860682), ('file', 0.00021164999278611358), ('line', 0.00017298263768407527), ('resources', 1.5260866485841756e-05)]
    }

    /**
     * Get the classloader for this definition.
     * @return the classloader for this definition.
     */
    public ClassLoader getClassLoader() {
        return classLoader;
// classLoader          No	: [('parent', 0.501793665102667), ('name', 0.015209777346803862), ('value', 0.00917371476746434), ('file', 0.005565127023841937), ('project', 0.005424573614468493), ('mComment', 0.00303921156772664), ('VALUES', 0.0030391850157306098), ('linkFlag', 0.002769821046180466), ('classpath', 0.0026222561004246073), ('fileset', 0.0025837795822466615)]
    }

    /**
     * Get the exposed class for this
     * definition. This will be a proxy class
     * (adapted class) if there is an adapter
     * class and the definition class is not
     * assignable from the assignable class.
     * @param project the current project.
     * @return the exposed class - may return null if unable to load the class
     */
    public Class<?> getExposedClass(Project project) {
// project              0	: [('project', 0.9083177188602521), ('p', 0.025605910977361583), ('other', 0.0006310538105524873), ('containingProject', 0.0005944989072992068), ('fallback', 0.00033088762887203033), ('newProject', 0.00031481399291591084), ('owner', 0.00031465468093972863), ('aProj', 0.00021658567335524397), ('resources', 4.946654326623327e-06), ('junit', 4.548374386167771e-06)]
        if (adaptToClass != null) {
// adaptToClass         No	: [('project', 0.25509323978022397), ('src', 0.17051676506770921), ('implementationClasspath', 0.08353124248343209), ('file', 0.009770410219790792), ('name', 0.006912461048513364), ('line', 0.0051044700337186215), ('classpath', 0.004636347124888669), ('instr', 0.0036574291928180257), ('resources', 0.0012777127834154474), ('buildRule', 0.0005217275572520232)]
            Class<?> z = getTypeClass(project);
// z                    No	: [('c', 0.168607969710967), ('clazz', 0.13939236066180175), ('testClass', 0.07857554772621092), ('theClass', 0.05361840449044994), ('expect', 0.03103153351824435), ('parentClass', 0.017873160845473704), ('cl', 0.015529104303323547), ('useType', 0.015515903510634813), ('elementClass', 0.015515876958638784), ('which', 0.015515823854646723)]
// project              0	: [('project', 0.41852615168616897), ('buildRule', 0.00247802374395415), ('name', 0.0024001993953840103), ('file', 0.0016579727032044243), ('p', 0.0015742940704760535), ('s', 0.0011722254476466665), ('value', 0.0011215287446198333), ('f', 0.001115143006813188), ('e', 0.0010881838571921403), ('resources', 0.00015796433771254206)]
            if (z == null || adaptToClass.isAssignableFrom(z)) {
// z                    No	: [('o', 0.09447999661548975), ('project', 0.09347670533275106), ('bootclasspath', 0.09145498693075604), ('launcher', 0.09123231109556168), ('destDir', 0.046096957762371396), ('requiredClass', 0.0456161329873173), ('addedObject', 0.045614621180888486), ('f', 0.00476925643797146), ('c', 0.004518263473806982), ('index', 0.00404906380234304)]
// adaptToClass         No	: [('value', 0.04709542678509009), ('line', 0.01920988638303221), ('name', 0.013324755028949732), ('other', 0.011488795254194243), ('timeout', 0.011332892955087166), ('rhs', 0.011328644635722307), ('o', 0.008013066766281303), ('result', 0.00789391242968026), ('ex', 0.00786035070669787), ('args', 0.007729407283859341)]
// z                    No	: [('clazz', 0.11724662790520525), ('bean', 0.09376085630746739), ('type', 0.07048525953715837), ('testClass', 0.07036759642139508), ('taskClass', 0.07034361993450233), ('e', 0.047419091928596065), ('c', 0.023909335004792576), ('o', 0.023672216496295292), ('element', 0.023585116972233502), ('oldClass', 0.023442309081752138)]
                return z;
// z                    No	: [('expected', 0.025834478706776784), ('name', 0.025751520322603052), ('value', 0.016611782997301944), ('i', 0.01630698145751278), ('string', 0.015766365660605658), ('s', 0.015691702501623394), ('file', 0.015400613516063142), ('executableFile', 0.015384194133446624), ('result', 0.011840928975599477), ('type', 0.011706241300722668)]
            }
        }
        return (adapterClass == null) ? getTypeClass(project) :  adapterClass;
// adapterClass         No	: [('textBuffer', 0.7580000648062327), ('p', 0.00819405783218731), ('type', 0.008040460957667393), ('count', 0.008015874879762566), ('outputDir', 0.008009878548246155), ('watchdog', 0.008002320068550403), ('o1', 0.00800001170224068), ('numToSkip', 0.00800001170224068), ('destOffs', 0.00799948121476838), ('file', 0.001495906381714986)]
// project              0	: [('project', 0.41852615168616897), ('buildRule', 0.00247802374395415), ('name', 0.0024001993953840103), ('file', 0.0016579727032044243), ('p', 0.0015742940704760535), ('s', 0.0011722254476466665), ('value', 0.0011215287446198333), ('f', 0.001115143006813188), ('e', 0.0010881838571921403), ('resources', 0.00015796433771254206)]
// adapterClass         No	: [('parent', 0.019320733112707877), ('resources', 0.01767170714788569), ('s', 0.01685679785340472), ('name', 0.014315317158976059), ('FILE_UTILS', 0.011738493756719686), ('characters', 0.011565361135884843), ('mapperElement', 0.011565148719916601), ('path', 0.009261176291417223), ('message', 0.008076423582791602), ('val', 0.0080131236242552)]
    }

    /**
     * Get the definition class.
     * @param project the current project.
     * @return the type of the definition.
     */
    public Class<?> getTypeClass(Project project) {
// project              0	: [('project', 0.9083177188602521), ('p', 0.025605910977361583), ('other', 0.0006310538105524873), ('containingProject', 0.0005944989072992068), ('fallback', 0.00033088762887203033), ('newProject', 0.00031481399291591084), ('owner', 0.00031465468093972863), ('aProj', 0.00021658567335524397), ('resources', 4.946654326623327e-06), ('junit', 4.548374386167771e-06)]
        try {
            return innerGetTypeClass();
        } catch (NoClassDefFoundError ncdfe) {
// ncdfe                1	: [('e', 0.6588728340132173), ('ncdfe', 0.2836452585104304), ('resources', 4.946654326623327e-06), ('junit', 4.548374386167771e-06), ('srcDir', 3.6456065211351777e-06), ('separator', 3.5659505330440663e-06), ('expected', 3.4331905528922146e-06), ('values', 2.9287026283151774e-06), ('os', 2.6100786759507327e-06), ('token', 2.371110711677399e-06)]
            project.log("Could not load a dependent class ("
// project              6	: [('jars', 0.6250199274813462), ('buildRule', 0.005258872012270151), ('log', 0.002859308658957507), ('thrown', 0.001351228587323563), ('cmd', 0.0008915811775913027), ('out', 0.0004766831388858396), ('project', 0.00045767388212270026), ('sb', 0.0004199361162764642), ('result', 0.0002888497281449081), ('s', 0.0002812550499061138)]
// log                  0	: [('log', 0.3697863321871986), ('getProperty', 0.024365189911664736), ('ant', 0.00551376978684161), ('junit', 0.0017879118044531804), ('types', 0.0014958317461883932), ('length', 0.001372141703399383), ('get', 0.0011551658122948218), ('equals', 0.0010613304687231496), ('append', 0.0009759468668083163), ('add', 0.000933271365799163)]
                        + ncdfe.getMessage() + ") for type "
// ncdfe                No	: [('name', 0.05527516093244534), ('dir', 0.054245148456539376), ('file', 0.04590330656752895), ('target', 0.025362058055002183), ('classname', 0.01896455816782748), ('publicId', 0.015719786496086977), ('classpath', 0.010973073836863535), ('source', 0.010772639950823339), ('p', 0.010699282539043417), ('arg', 0.010083505819634464)]
                        + name, Project.MSG_DEBUG);
// name                 0	: [('name', 0.009863495582160941), ('e', 0.009125470487126803), ('file', 0.00850564498612388), ('i', 0.004393766302370884), ('value', 0.004310875916912064), ('f', 0.004004431911503565), ('ioe', 0.003920503525125562), ('dir', 0.0038270886569427218), ('classname', 0.0034235946329692194), ('WORD', 0.0027412706794588022)]
// MSG_DEBUG            1	: [('MSG_VERBOSE', 0.49311592014723293), ('MSG_DEBUG', 0.28241131880327025), ('MSG_WARN', 0.11942907355803502), ('MSG_INFO', 0.020783163235953923), ('MSG_ERR', 0.019130190948931952), ('ant', 0.002756884893420805), ('junit', 0.0008939559022265902), ('types', 0.0007479158730941966), ('length', 0.0006860708516996915), ('equals', 0.0005306652343615748)]
        } catch (ClassNotFoundException cnfe) {
// cnfe                 1	: [('e', 0.48210811570938444), ('cnfe', 0.21562603153215978), ('ex', 0.14893812031415835), ('x', 0.02303038734587315), ('cnf3', 0.023029165954055752), ('cnf1', 0.023029165954055752), ('cnf2', 0.023029165954055752), ('resources', 4.946654326623327e-06), ('junit', 4.548374386167771e-06), ('srcDir', 3.6456065211351777e-06)]
            project.log("Could not load class (" + className
// project              7	: [('c', 0.08313027913478764), ('theClass', 0.03752736569577809), ('buildRule', 0.005258872012270151), ('log', 0.002859308658957507), ('thrown', 0.001351228587323563), ('cmd', 0.0008915811775913027), ('out', 0.0004766831388858396), ('project', 0.00045767388212270026), ('sb', 0.0004199361162764642), ('s', 0.0002812550499061138)]
// log                  0	: [('log', 0.3697863321871986), ('getProperty', 0.024365189911664736), ('ant', 0.00551376978684161), ('junit', 0.0017879118044531804), ('types', 0.0014958317461883932), ('length', 0.001372141703399383), ('get', 0.0011551658122948218), ('equals', 0.0010613304687231496), ('append', 0.0009759468668083163), ('add', 0.000933271365799163)]
// className            No	: [('name', 0.05527516093244534), ('dir', 0.054245148456539376), ('file', 0.04590330656752895), ('target', 0.025362058055002183), ('classname', 0.01896455816782748), ('publicId', 0.015719786496086977), ('classpath', 0.010973073836863535), ('source', 0.010772639950823339), ('p', 0.010699282539043417), ('arg', 0.010083505819634464)]
                        + ") for type " + name, Project.MSG_DEBUG);
// name                 0	: [('name', 0.009863495582160941), ('e', 0.009125470487126803), ('file', 0.00850564498612388), ('i', 0.004393766302370884), ('value', 0.004310875916912064), ('f', 0.004004431911503565), ('ioe', 0.003920503525125562), ('dir', 0.0038270886569427218), ('classname', 0.0034235946329692194), ('WORD', 0.0027412706794588022)]
// MSG_DEBUG            1	: [('MSG_VERBOSE', 0.49311592014723293), ('MSG_DEBUG', 0.28241131880327025), ('MSG_WARN', 0.11942907355803502), ('MSG_INFO', 0.020783163235953923), ('MSG_ERR', 0.019130190948931952), ('ant', 0.002756884893420805), ('junit', 0.0008939559022265902), ('types', 0.0007479158730941966), ('length', 0.0006860708516996915), ('equals', 0.0005306652343615748)]
        }
        return null;
    }

    /**
     * Try and load a class, with no attempt to catch any fault.
     * @return the class that implements this component
     * @throws ClassNotFoundException if the class cannot be found.
     * @throws NoClassDefFoundError   if the there is an error
     *                                finding the class.
     */
    public Class<?> innerGetTypeClass() throws ClassNotFoundException {
        if (clazz != null) {
// clazz                No	: [('file', 0.01485628876648711), ('name', 0.010748435743349232), ('project', 0.007978592923712686), ('classpath', 0.007277782865751856), ('line', 0.006896753374311105), ('value', 0.00605837337334488), ('task', 0.0054504115984367755), ('src', 0.00525954853374775), ('out', 0.0050647518372618685), ('instr', 0.004488871700849569)]
            return clazz;
// clazz                No	: [('file', 0.054431060636301536), ('read', 0.041760606333805614), ('ret', 0.03138075280133921), ('theClass', 0.03130781877724086), ('val', 0.03128234249704884), ('name', 0.02612251715815407), ('element', 0.021132462266778257), ('jExecutable', 0.020850363965731887), ('className', 0.010973803372819911), ('container', 0.01042636753822178)]
        }
        if (classLoader == null) {
// classLoader          No	: [('file', 0.011639609017192647), ('name', 0.008338779382251514), ('value', 0.007676322291271083), ('c', 0.006920508068453244), ('resource', 0.006497953250218753), ('action', 0.0060090648989705835), ('t', 0.005973591505771741), ('destDir', 0.00593028187317228), ('type', 0.005772936578198437), ('o', 0.005546385029726813)]
            clazz = Class.forName(className);
// clazz                No	: [('classLoader', 0.5008597629565679), ('log', 0.025050826818217603), ('classpath', 0.01149965644329688), ('message', 0.005059941251699527), ('compileClasspath', 0.004540879166685315), ('path', 0.004326130857967653), ('msg', 0.003620016213099889), ('bootclasspath', 0.0030316725360566648), ('ret', 0.0028251508696892262), ('name', 0.0028147596527104764)]
// className            1	: [('classname', 0.23433841890383436), ('className', 0.08460967311381026), ('factoryName', 0.03568253359943083), ('junitTest', 0.03568157883247034), ('readerClassName', 0.035680173234025656), ('JUNIT_4_TEST_ADAPTER', 0.035680093578037565), ('analyzerClassName', 0.035679748954537474), ('APACHE_RESOLVER', 0.035679616194557326), ('WLRMIC_CLASSNAME', 0.035679589642561294), ('name', 0.020443804488317865)]
        } else {
            clazz = classLoader.loadClass(className);
// clazz                No	: [('log', 0.11107992186224148), ('cmd', 0.04155394855648807), ('sb', 0.024246620134255728), ('out', 0.018441434941204822), ('buf', 0.01763225580431031), ('classpath', 0.015153110560199835), ('result', 0.010163014563382254), ('task', 0.00996245434277144), ('commandLine', 0.0072384435135498144), ('message', 0.007083271523244881)]
// classLoader          2	: [('cl', 0.03125942764061768), ('def', 0.023458596004574454), ('classLoader', 0.023451717125382132), ('c', 0.007941260178750324), ('helper', 0.007856951071225492), ('buildRule', 0.0003447498504056685), ('b', 0.00027800173773589066), ('name', 0.00027117843913279166), ('value', 0.0002548837268286694), ('p', 0.0002253135808860682)]
// className            No	: [('helperClass', 0.7582697182200973), ('classname', 0.07646047488104171), ('suiteName', 0.016535396420871158), ('name', 0.008566153956681066), ('driver', 0.008269453252585301), ('engineClassName', 0.008267782134180309), ('MODERN_COMPILER', 0.008267755582184278), ('mainClassname', 0.008267357854692129), ('buildRule', 0.0003097529679942687), ('resources', 1.9745542214067758e-05)]
        }
        return clazz;
// clazz                No	: [('result', 0.06674429447194709), ('classpath', 0.02770434317827081), ('buf', 0.020169609813804782), ('sb', 0.014333836237047856), ('path', 0.013636947660591406), ('ret', 0.010575306609013504), ('o', 0.010428168863253956), ('pos', 0.010256479358916841), ('f', 0.008440357172110929), ('theClass', 0.008139695938876368)]
    }

    /**
     * Create an instance of the definition.
     * The instance may be wrapped in a proxy class.
     * @param project the current project.
     * @return the created object.
     */
    public Object create(Project project) {
// create               2	: [('get', 0.2580089754845067), ('parseProperties', 0.25395569519907574), ('create', 0.1313474069050577), ('getProperty', 0.13119176727417262), ('clone', 0.05766354612237636), ('o', 0.005647029123109663), ('value', 0.0049217715638462615), ('parent', 0.003413744821917676), ('parseNextProperty', 0.0020613309731998654), ('resources', 9.893308653246653e-06)]
// project              0	: [('project', 0.9083177188602521), ('p', 0.025605910977361583), ('other', 0.0006310538105524873), ('containingProject', 0.0005944989072992068), ('fallback', 0.00033088762887203033), ('newProject', 0.00031481399291591084), ('owner', 0.00031465468093972863), ('aProj', 0.00021658567335524397), ('resources', 4.946654326623327e-06), ('junit', 4.548374386167771e-06)]
        return icreate(project);
// project              2	: [('buildRule', 0.0049560474879083), ('name', 0.0048003987907680205), ('project', 0.0037189700390045984), ('file', 0.0033159454064088487), ('p', 0.003148588140952107), ('s', 0.002344450895293333), ('value', 0.0022430574892396667), ('f', 0.002230286013626376), ('e', 0.0021763677143842807), ('r', 0.0019993542236361197)]
    }

    /**
     * Create a component object based on
     * its definition.
     * @return the component as an <code>Object</code>.
     */
    private Object icreate(Project project) {
// project              0	: [('project', 0.26654175088201754), ('p', 0.20484728781889266), ('other', 0.005048430484419899), ('containingProject', 0.004755991258393655), ('fallback', 0.0026471010309762426), ('newProject', 0.0025185119433272867), ('owner', 0.002517237447517829), ('aProj', 0.0017326853868419518), ('resources', 3.957323461298661e-05), ('junit', 3.638699508934217e-05)]
        Class<?> c = getTypeClass(project);
// c                    0	: [('c', 0.168607969710967), ('clazz', 0.13939236066180175), ('testClass', 0.07857554772621092), ('theClass', 0.05361840449044994), ('expect', 0.03103153351824435), ('parentClass', 0.017873160845473704), ('cl', 0.015529104303323547), ('useType', 0.015515903510634813), ('elementClass', 0.015515876958638784), ('which', 0.015515823854646723)]
// project              0	: [('project', 0.41852615168616897), ('buildRule', 0.00247802374395415), ('name', 0.0024001993953840103), ('file', 0.0016579727032044243), ('p', 0.0015742940704760535), ('s', 0.0011722254476466665), ('value', 0.0011215287446198333), ('f', 0.001115143006813188), ('e', 0.0010881838571921403), ('resources', 0.00015796433771254206)]
        if (c == null) {
// c                    8	: [('o', 0.09447999661548975), ('project', 0.09347670533275106), ('bootclasspath', 0.09145498693075604), ('launcher', 0.09123231109556168), ('destDir', 0.046096957762371396), ('requiredClass', 0.0456161329873173), ('addedObject', 0.045614621180888486), ('f', 0.00476925643797146), ('c', 0.004518263473806982), ('index', 0.00404906380234304)]
            return null;
        }
        Object o = createAndSet(project, c);
// o                    1	: [('obj', 0.7636208984530257), ('o', 0.07782351456155483), ('value', 0.014960885781923129), ('target', 0.012713953754771532), ('expanded', 0.012583695789630235), ('instance', 0.01254225016628806), ('masterValue', 0.012542170510299969), ('currValue', 0.012542170510299969), ('nodeObject', 0.012542143958303938), ('resources', 4.946654326623327e-06)]
// project              2	: [('buildRule', 0.0049560474879083), ('name', 0.0048003987907680205), ('project', 0.0037189700390045984), ('file', 0.0033159454064088487), ('p', 0.003148588140952107), ('s', 0.002344450895293333), ('value', 0.0022430574892396667), ('f', 0.002230286013626376), ('e', 0.0021763677143842807), ('r', 0.0019993542236361197)]
// c                    No	: [('parent', 0.040627722869511655), ('cmd', 0.03899807558574541), ('target', 0.02461548933496371), ('LOCAL_PATH', 0.02432901006932968), ('newcmd', 0.019482815037417157), ('date', 0.014599290295019322), ('message', 0.009897477829719815), ('source', 0.009802214059829607), ('basedir', 0.009797965740464747), ('buildFile', 0.009737504889411122)]
        if (adapterClass == null
// adapterClass         No	: [('c', 0.11562937458491808), ('p', 0.1134444303165695), ('f', 0.06032481199352701), ('r', 0.057708942706622496), ('current', 0.05615444586399228), ('insertAtStart', 0.055875035225275885), ('inToken', 0.05571563128189858), ('file', 0.004828910052093932), ('task', 0.004349586613228341), ('index', 0.00404906380234304)]
            || (adaptToClass != null && adaptToClass.isAssignableFrom(o.getClass()))) {
// adaptToClass         No	: [('children', 0.1770406558398912), ('sourceFileName', 0.17703805484917684), ('name', 0.05261617888110407), ('file', 0.0064628802725753), ('t', 0.0062130677001822725), ('AUTO', 0.006054876225452487), ('newerResources', 0.004035935996305043), ('effectiveInputEncoding', 0.004035882892312982), ('even', 0.00403567047634474), ('d', 0.0020644210157123133)]
// adaptToClass         No	: [('file', 0.028837062500028928), ('value', 0.011544365929278693), ('other', 0.008450463526665982), ('parent', 0.007617453152648468), ('watchdog', 0.006754089695124772), ('oldValue', 0.0067512220795534926), ('defaultValue', 0.006629835720768246), ('password', 0.0061116018048386605), ('property', 0.005598996912067512), ('queuedData', 0.005065150422472068)]
// o                    7	: [('clazz', 0.11724662790520525), ('bean', 0.09376085630746739), ('type', 0.07048525953715837), ('testClass', 0.07036759642139508), ('taskClass', 0.07034361993450233), ('e', 0.047419091928596065), ('c', 0.023909335004792576), ('o', 0.023672216496295292), ('element', 0.023585116972233502), ('oldClass', 0.023442309081752138)]
            return o;
// o                    5	: [('expected', 0.1379172393533884), ('INCOMPATIBLE', 0.05512825323614275), ('value', 0.03330589149865098), ('i', 0.03315349072875639), ('s', 0.0328458512508117), ('o', 0.028354733227996844), ('c', 0.028093582415464873), ('parts', 0.027925847412820337), ('buf', 0.027733533413282642), ('reader', 0.02759296571729373)]
        }
        TypeAdapter adapterObject = (TypeAdapter) createAndSet(
// adapterObject        No	: [('resources', 7.914646922597323e-05), ('junit', 7.277399017868434e-05), ('srcDir', 5.832970433816285e-05), ('separator', 5.7055208528705054e-05), ('expected', 5.493104884627543e-05), ('values', 4.685924205304284e-05), ('os', 4.176125881521172e-05), ('token', 3.7937771386838384e-05), ('entries', 3.751293945035246e-05), ('localFile', 3.1140460403063564e-05)]
            project, adapterClass);
// project              2	: [('buildRule', 0.0049560474879083), ('name', 0.0048003987907680205), ('project', 0.0037189700390045984), ('file', 0.0033159454064088487), ('p', 0.003148588140952107), ('s', 0.002344450895293333), ('value', 0.0022430574892396667), ('f', 0.002230286013626376), ('e', 0.0021763677143842807), ('r', 0.0019993542236361197)]
// adapterClass         No	: [('parent', 0.040627722869511655), ('cmd', 0.03899807558574541), ('target', 0.02461548933496371), ('LOCAL_PATH', 0.02432901006932968), ('newcmd', 0.019482815037417157), ('date', 0.014599290295019322), ('message', 0.009897477829719815), ('source', 0.009802214059829607), ('basedir', 0.009797965740464747), ('buildFile', 0.009737504889411122)]
        adapterObject.setProxy(o);
// adapterObject        No	: [('def', 0.5006748839991005), ('buildRule', 0.007124879156580718), ('thrown', 0.004979931670527074), ('ds', 0.003325385183707367), ('log', 0.0028246125036738646), ('out', 0.0027084009208847865), ('s', 0.0022890205949719733), ('cmd', 0.0019858724970792654), ('project', 0.001938860764984733), ('p', 0.0018979068543294097)]
// o                    No	: [('realThing', 0.14586434705985155), ('realChild', 0.14584631159068992), ('replacement', 0.14584079540489528), ('childElement', 0.1458402643649747), ('proxy', 0.04169598327523215), ('buildRule', 0.001239011871977075), ('name', 0.0012000996976920051), ('project', 0.0009297425097511496), ('file', 0.0008289863516022122), ('p', 0.0007871470352380268)]
        return adapterObject;
// adapterObject        No	: [('o', 0.6263535982624513), ('result', 0.13580971211308485), ('newFilter', 0.003984870487312785), ('p', 0.0033098298169857987), ('cmd', 0.003020304480519916), ('commandLine', 0.0025039689022136494), ('buf', 0.002358818244167315), ('mapperElement', 0.002254885124702627), ('exe', 0.0020021472187377047), ('resources', 1.1415294359742763e-05)]
    }

    /**
     * Checks if the attributes are correct.
     * <ul>
     *   <li>if the class can be created.</li>
     *   <li>if an adapter class can be created</li>
     *   <li>if the type is assignable from adapter</li>
     *   <li>if the type can be used with the adapter class</li>
     * </ul>
     * @param project the current project.
     */
    public void checkClass(Project project) {
// project              0	: [('project', 0.9083177188602521), ('p', 0.025605910977361583), ('other', 0.0006310538105524873), ('containingProject', 0.0005944989072992068), ('fallback', 0.00033088762887203033), ('newProject', 0.00031481399291591084), ('owner', 0.00031465468093972863), ('aProj', 0.00021658567335524397), ('resources', 4.946654326623327e-06), ('junit', 4.548374386167771e-06)]
        if (clazz == null) {
// clazz                No	: [('project', 0.25509323978022397), ('src', 0.17051676506770921), ('implementationClasspath', 0.08353124248343209), ('file', 0.009770410219790792), ('name', 0.006912461048513364), ('line', 0.0051044700337186215), ('classpath', 0.004636347124888669), ('instr', 0.0036574291928180257), ('resources', 0.0012777127834154474), ('buildRule', 0.0005217275572520232)]
            clazz = getTypeClass(project);
// clazz                0	: [('clazz', 0.501584676417999), ('log', 0.025050826818217603), ('classpath', 0.01149965644329688), ('message', 0.005059941251699527), ('compileClasspath', 0.004540879166685315), ('path', 0.004326130857967653), ('msg', 0.003620016213099889), ('bootclasspath', 0.0030316725360566648), ('ret', 0.0028251508696892262), ('name', 0.0028147596527104764)]
// project              0	: [('project', 0.41852615168616897), ('buildRule', 0.00247802374395415), ('name', 0.0024001993953840103), ('file', 0.0016579727032044243), ('p', 0.0015742940704760535), ('s', 0.0011722254476466665), ('value', 0.0011215287446198333), ('f', 0.001115143006813188), ('e', 0.0010881838571921403), ('resources', 0.00015796433771254206)]
            if (clazz == null) {
// clazz                No	: [('o', 0.09447999661548975), ('project', 0.09347670533275106), ('bootclasspath', 0.09145498693075604), ('launcher', 0.09123231109556168), ('destDir', 0.046096957762371396), ('requiredClass', 0.0456161329873173), ('addedObject', 0.045614621180888486), ('f', 0.00476925643797146), ('c', 0.004518263473806982), ('index', 0.00404906380234304)]
                throw new BuildException(
                    "Unable to create class for " + getName());
            }
        }
        // check adapter
        if (adapterClass != null && (adaptToClass == null
// adapterClass         No	: [('name', 0.016126490879881744), ('task', 0.015379547578539729), ('line', 0.015210716240375417), ('attributes', 0.014934596522517456), ('len', 0.014847333592459787), ('classpath', 0.01182248166271438), ('verbose', 0.011627347335440788), ('i', 0.011291102461940284), ('file', 0.009543846084938244), ('url', 0.008829213608567575)]
// adaptToClass         No	: [('key', 0.0917387022967838), ('path', 0.0737274590104517), ('o', 0.07369827612254377), ('userInfo', 0.07358846797801903), ('superGrammar', 0.07358565678112965), ('expandingLHS', 0.07358342862325633), ('parent', 0.018130314120985046), ('weight', 0.005447744899922291), ('file', 0.003131884480148932), ('len', 0.00275253117611682)]
            || !adaptToClass.isAssignableFrom(clazz))) {
// adaptToClass         No	: [('targetFile', 0.1042964678136838), ('s', 0.053850044503380864), ('FILE_UTILS', 0.05236431324219251), ('obj', 0.05209319958597525), ('file', 0.033687432307024194), ('name', 0.026598907134874204), ('home', 0.026085232090385223), ('antHome', 0.026084594842480494), ('myfile', 0.02604697950399702), ('lastLine', 0.026046236048108167)]
// clazz                0	: [('clazz', 0.11724662790520525), ('bean', 0.09376085630746739), ('type', 0.07048525953715837), ('testClass', 0.07036759642139508), ('taskClass', 0.07034361993450233), ('e', 0.047419091928596065), ('c', 0.023909335004792576), ('o', 0.023672216496295292), ('element', 0.023585116972233502), ('oldClass', 0.023442309081752138)]
            TypeAdapter adapter = (TypeAdapter) createAndSet(
// adapter              No	: [('resources', 7.914646922597323e-05), ('junit', 7.277399017868434e-05), ('srcDir', 5.832970433816285e-05), ('separator', 5.7055208528705054e-05), ('expected', 5.493104884627543e-05), ('values', 4.685924205304284e-05), ('os', 4.176125881521172e-05), ('token', 3.7937771386838384e-05), ('entries', 3.751293945035246e-05), ('localFile', 3.1140460403063564e-05)]
                project, adapterClass);
// project              2	: [('buildRule', 0.0049560474879083), ('name', 0.0048003987907680205), ('project', 0.0037189700390045984), ('file', 0.0033159454064088487), ('p', 0.003148588140952107), ('s', 0.002344450895293333), ('value', 0.0022430574892396667), ('f', 0.002230286013626376), ('e', 0.0021763677143842807), ('r', 0.0019993542236361197)]
// adapterClass         No	: [('parent', 0.040627722869511655), ('cmd', 0.03899807558574541), ('target', 0.02461548933496371), ('LOCAL_PATH', 0.02432901006932968), ('newcmd', 0.019482815037417157), ('date', 0.014599290295019322), ('message', 0.009897477829719815), ('source', 0.009802214059829607), ('basedir', 0.009797965740464747), ('buildFile', 0.009737504889411122)]
            adapter.checkProxyClass(clazz);
// adapter              No	: [('def', 0.5006748839991005), ('buildRule', 0.007124879156580718), ('thrown', 0.004979931670527074), ('ds', 0.003325385183707367), ('log', 0.0028246125036738646), ('out', 0.0027084009208847865), ('s', 0.0022890205949719733), ('cmd', 0.0019858724970792654), ('project', 0.001938860764984733), ('p', 0.0018979068543294097)]
// clazz                No	: [('buildRule', 0.0049560474879083), ('name', 0.0048003987907680205), ('project', 0.0037189700390045984), ('file', 0.0033159454064088487), ('p', 0.003148588140952107), ('s', 0.002344450895293333), ('value', 0.0022430574892396667), ('f', 0.002230286013626376), ('e', 0.0021763677143842807), ('r', 0.0019993542236361197)]
        }
    }

    /**
     * Get the constructor of the definition
     * and invoke it.
     * @return the instantiated <code>Object</code>, will never be null.
     */
    private Object createAndSet(Project project, Class<?> c) {
// project              0	: [('project', 0.26654175088201754), ('p', 0.20484728781889266), ('other', 0.005048430484419899), ('containingProject', 0.004755991258393655), ('fallback', 0.0026471010309762426), ('newProject', 0.0025185119433272867), ('owner', 0.002517237447517829), ('aProj', 0.0017326853868419518), ('resources', 3.957323461298661e-05), ('junit', 3.638699508934217e-05)]
// c                    5	: [('methodArg', 0.1142154602423157), ('element', 0.07850478559949012), ('argType1', 0.07614386408919904), ('argType2', 0.07614383753720301), ('argType', 0.07614383753720301), ('c', 0.06334481181623017), ('taskClass', 0.04985892633822125), ('clazz', 0.047287097503907034), ('typeClass', 0.042786835709752224), ('returnType', 0.0404296314789054)]
        try {
            return innerCreateAndSet(c, project);
// c                    No	: [('clazz', 0.5001182558104104), ('buildRule', 0.00247802374395415), ('name', 0.0024001993953840103), ('project', 0.0018594850195022992), ('file', 0.0016579727032044243), ('p', 0.0015742940704760535), ('s', 0.0011722254476466665), ('value', 0.0011215287446198333), ('f', 0.001115143006813188), ('e', 0.0010881838571921403)]
// project              No	: [('DECIMAL', 0.18327186699498593), ('method', 0.12219614502786885), ('o', 0.06122839848878129), ('O', 0.06115307961993575), ('resource', 0.06114895970852518), ('buildRule', 0.0018013327461122476), ('l', 0.0015072842055170877), ('e', 0.0014707201508907947), ('p', 0.001422484317996227), ('name', 0.0011809865295710237)]
        } catch (InvocationTargetException ex) {
// ex                   1	: [('e', 0.6318075381979216), ('ex', 0.2553610274316283), ('ie', 0.05520921753226731), ('ite', 0.003472920557183986), ('resources', 4.946654326623327e-06), ('junit', 4.548374386167771e-06), ('srcDir', 3.6456065211351777e-06), ('separator', 3.5659505330440663e-06), ('expected', 3.4331905528922146e-06), ('values', 2.9287026283151774e-06)]
            Throwable t = ex.getTargetException();
// t                    0	: [('t', 0.8780652104712959), ('error', 0.03293692767917511), ('cause', 0.01322502123705822), ('te', 0.010792280298535177), ('thrownException', 0.01079182891460266), ('t2', 0.010604650062107606), ('throwable', 0.0020595064004311843), ('e', 0.0018831607623165205), ('resources', 4.946654326623327e-06), ('junit', 4.548374386167771e-06)]
// ex                   2	: [('e', 0.39133005821169536), ('exc', 0.16449713798221585), ('ex', 0.08228483108106363), ('ie', 0.08224676238417085), ('be', 0.07521954841922199), ('runnable', 0.07521459962248135), ('cause', 0.01407171687356087), ('nGroups', 0.004326226515459727), ('buildRule', 0.0003447498504056685), ('b', 0.00027800173773589066)]
            throw new BuildException(
                "Could not create type " + name + " due to " + t, t);
// name                 2	: [('commandLine', 0.051978330465563), ('e', 0.04998357843050872), ('name', 0.0412415201111044), ('className', 0.020138241537179365), ('src', 0.0196552141572771), ('srcFile', 0.019625202878058204), ('manifestFile', 0.019423701055683692), ('r', 0.017257217656285948), ('dir', 0.01611667101927084), ('ioe', 0.01408695741108733)]
// t                    1	: [('ioe', 0.5202108951120507), ('t', 0.058043485151830214), ('dependency', 0.057763534018729516), ('name', 0.0024658738955402352), ('e', 0.0022813676217817006), ('file', 0.00212641124653097), ('i', 0.001098441575592721), ('value', 0.001077718979228016), ('f', 0.0010011079778758912), ('resources', 1.9786617306493306e-05)]
// t                    No	: [('e', 0.026750964111305398), ('f', 0.025542840953777783), ('location', 0.024164683605538438), ('msgLevel', 0.024012581156039825), ('methods', 0.023972713553920676), ('result', 0.013960675337369539), ('buf', 0.012827569832838524), ('b', 0.012525555693434253), ('pos', 0.012369160524630425), ('level', 0.012144947645780085)]
        } catch (NoClassDefFoundError ncdfe) {
// ncdfe                1	: [('e', 0.6588728340132173), ('ncdfe', 0.2836452585104304), ('resources', 4.946654326623327e-06), ('junit', 4.548374386167771e-06), ('srcDir', 3.6456065211351777e-06), ('separator', 3.5659505330440663e-06), ('expected', 3.4331905528922146e-06), ('values', 2.9287026283151774e-06), ('os', 2.6100786759507327e-06), ('token', 2.371110711677399e-06)]
            String msg = "Type " + name + ": A class needed by class "
// msg                  0	: [('msg', 0.07502284766928563), ('name', 0.04695597186536529), ('message', 0.029224101359536753), ('s', 0.02580020155110395), ('className', 0.0149712851936042), ('path', 0.01328406472712311), ('key', 0.013122256969734857), ('value', 0.012466957368013358), ('result', 0.011771755879237185), ('uri', 0.011293928173381231)]
// name                 0	: [('name', 0.009863495582160941), ('e', 0.009125470487126803), ('file', 0.00850564498612388), ('i', 0.004393766302370884), ('value', 0.004310875916912064), ('f', 0.004004431911503565), ('ioe', 0.003920503525125562), ('dir', 0.0038270886569427218), ('classname', 0.0034235946329692194), ('WORD', 0.0027412706794588022)]
                + c + " cannot be found: " + ncdfe.getMessage();
// c                    No	: [('home', 0.5005571986337712), ('e', 0.009952086509761971), ('name', 0.004290029624964196), ('ex', 0.002906136103587037), ('classname', 0.002715660815639713), ('matchingEntry', 0.0021572575677759396), ('homeClass', 0.0019895135633600436), ('remoteClass', 0.0019895135633600436), ('thrownException', 0.001978285078904451), ('nameAndTypeIndex', 0.001978285078904451)]
// ncdfe                No	: [('e', 0.039808346039047884), ('name', 0.017160118499856783), ('ex', 0.011624544414348148), ('classname', 0.010862643262558851), ('matchingEntry', 0.008629030271103759), ('homeClass', 0.007958054253440174), ('remoteClass', 0.007958054253440174), ('thrownException', 0.007913140315617804), ('nameAndTypeIndex', 0.007913140315617804), ('path', 0.0070664621871600435)]
            throw new BuildException(msg, ncdfe);
// msg                  0	: [('msg', 0.34706444920344964), ('message', 0.12587002169272396), ('e', 0.11759874360140506), ('ex', 0.05444000808036508), ('err', 0.01500249316961093), ('buf', 0.006792475898831179), ('ref', 0.006767856087102336), ('npe', 0.006760350158950339), ('od', 0.006759846223474069), ('cnfmsg', 0.006759819671478038)]
// ncdfe                No	: [('ioe', 0.2727981457625159), ('e', 0.022298164457060815), ('exc', 0.01821110128493256), ('exception', 0.018188591940258042), ('reason', 0.018186576763556006), ('aIOException', 0.01818233399418369), ('out', 0.00948429913925964), ('msgLevel', 0.008164652728023533), ('shouldBeEqual', 0.007509743459517516), ('buildRule', 0.0004503331865280619)]
        } catch (NoSuchMethodException nsme) {
// nsme                 3	: [('e', 0.7012180436281638), ('ex', 0.1354814674204893), ('ignored', 0.06658216200654439), ('nsme', 0.049830106480821486), ('nse', 0.0023154865977805484), ('nme2', 0.001158079190373141), ('resources', 4.946654326623327e-06), ('junit', 4.548374386167771e-06), ('srcDir', 3.6456065211351777e-06), ('separator', 3.5659505330440663e-06)]
            throw new BuildException("Could not create type " + name
// name                 2	: [('commandLine', 0.051978330465563), ('e', 0.04998357843050872), ('name', 0.0412415201111044), ('className', 0.020138241537179365), ('src', 0.0196552141572771), ('srcFile', 0.019625202878058204), ('manifestFile', 0.019423701055683692), ('r', 0.017257217656285948), ('dir', 0.01611667101927084), ('ioe', 0.01408695741108733)]
                    + " as the class " + c + " has no compatible constructor");
// c                    No	: [('home', 0.5005571986337712), ('e', 0.009952086509761971), ('name', 0.004290029624964196), ('ex', 0.002906136103587037), ('classname', 0.002715660815639713), ('matchingEntry', 0.0021572575677759396), ('homeClass', 0.0019895135633600436), ('remoteClass', 0.0019895135633600436), ('thrownException', 0.001978285078904451), ('nameAndTypeIndex', 0.001978285078904451)]
        } catch (InstantiationException nsme) {
// nsme                 No	: [('e', 0.5355773794677627), ('ie', 0.18329701515131494), ('iexcept', 0.17808846940201337), ('ex', 0.003910134574485409), ('resources', 4.946654326623327e-06), ('junit', 4.548374386167771e-06), ('srcDir', 3.6456065211351777e-06), ('separator', 3.5659505330440663e-06), ('expected', 3.4331905528922146e-06), ('values', 2.9287026283151774e-06)]
            throw new BuildException("Could not create type "
                    + name + " as the class " + c + " is abstract");
// name                 2	: [('commandLine', 0.051978330465563), ('e', 0.04998357843050872), ('name', 0.0412415201111044), ('className', 0.020138241537179365), ('src', 0.0196552141572771), ('srcFile', 0.019625202878058204), ('manifestFile', 0.019423701055683692), ('r', 0.017257217656285948), ('dir', 0.01611667101927084), ('ioe', 0.01408695741108733)]
// c                    No	: [('home', 0.5005571986337712), ('e', 0.009952086509761971), ('name', 0.004290029624964196), ('ex', 0.002906136103587037), ('classname', 0.002715660815639713), ('matchingEntry', 0.0021572575677759396), ('homeClass', 0.0019895135633600436), ('remoteClass', 0.0019895135633600436), ('thrownException', 0.001978285078904451), ('nameAndTypeIndex', 0.001978285078904451)]
        } catch (IllegalAccessException e) {
// e                    0	: [('e', 0.7109916330070163), ('ie', 0.10734885717190695), ('iaexcept', 0.10397026637756032), ('resources', 4.946654326623327e-06), ('junit', 4.548374386167771e-06), ('srcDir', 3.6456065211351777e-06), ('separator', 3.5659505330440663e-06), ('expected', 3.4331905528922146e-06), ('values', 2.9287026283151774e-06), ('os', 2.6100786759507327e-06)]
            throw new BuildException("Could not create type "
                    + name + " as the constructor " + c + " is not accessible");
// name                 2	: [('commandLine', 0.051978330465563), ('e', 0.04998357843050872), ('name', 0.0412415201111044), ('className', 0.020138241537179365), ('src', 0.0196552141572771), ('srcFile', 0.019625202878058204), ('manifestFile', 0.019423701055683692), ('r', 0.017257217656285948), ('dir', 0.01611667101927084), ('ioe', 0.01408695741108733)]
// c                    No	: [('home', 0.5005571986337712), ('e', 0.009952086509761971), ('name', 0.004290029624964196), ('ex', 0.002906136103587037), ('classname', 0.002715660815639713), ('matchingEntry', 0.0021572575677759396), ('homeClass', 0.0019895135633600436), ('remoteClass', 0.0019895135633600436), ('thrownException', 0.001978285078904451), ('nameAndTypeIndex', 0.001978285078904451)]
        } catch (Throwable t) {
// t                    0	: [('t', 0.5135287053097908), ('e', 0.21604399992315568), ('ex', 0.12319169975331855), ('ignored', 0.04336329122621758), ('ioe', 0.03061230536490109), ('thrown', 0.015915371046441355), ('cause', 0.007616046878083863), ('throwable', 0.0020595064004311843), ('resources', 4.946654326623327e-06), ('junit', 4.548374386167771e-06)]
            throw new BuildException(
                "Could not create type " + name + " due to " + t, t);
// name                 2	: [('commandLine', 0.051978330465563), ('e', 0.04998357843050872), ('name', 0.0412415201111044), ('className', 0.020138241537179365), ('src', 0.0196552141572771), ('srcFile', 0.019625202878058204), ('manifestFile', 0.019423701055683692), ('r', 0.017257217656285948), ('dir', 0.01611667101927084), ('ioe', 0.01408695741108733)]
// t                    1	: [('ioe', 0.5202108951120507), ('t', 0.058043485151830214), ('dependency', 0.057763534018729516), ('name', 0.0024658738955402352), ('e', 0.0022813676217817006), ('file', 0.00212641124653097), ('i', 0.001098441575592721), ('value', 0.001077718979228016), ('f', 0.0010011079778758912), ('resources', 1.9786617306493306e-05)]
// t                    No	: [('e', 0.026750964111305398), ('f', 0.025542840953777783), ('location', 0.024164683605538438), ('msgLevel', 0.024012581156039825), ('methods', 0.023972713553920676), ('result', 0.013960675337369539), ('buf', 0.012827569832838524), ('b', 0.012525555693434253), ('pos', 0.012369160524630425), ('level', 0.012144947645780085)]
        }
    }

    /**
     * Inner implementation of the {@link #createAndSet(Project, Class)} logic, with no
     * exception catching.
     * @param <T> return type of the method
     * @param newclass class to create
     * @param project the project to use
     * @return a newly constructed and bound instance.
     * @throws NoSuchMethodException  no good constructor.
     * @throws InstantiationException cannot initialize the object.
     * @throws IllegalAccessException cannot access the object.
     * @throws InvocationTargetException error in invocation.
     */
    public <T> T innerCreateAndSet(Class<T> newclass, Project project)
// newclass             No	: [('clazz', 0.5891658250790955), ('ofClass', 0.1314748668052009), ('requiredClass', 0.09442330995366327), ('expectedType', 0.03147489335719693), ('e', 0.00896321439228896), ('i', 0.008855607749753526), ('l', 0.008844194842122108), ('action', 0.004338453723774594), ('c', 0.0005968095123068258), ('files', 0.0005551626305462837)]
// project              0	: [('project', 0.04038268776820515), ('p', 0.038522194241455025), ('aProj', 0.0017326853868419518), ('prj', 0.0014708340602426948), ('fallback', 0.0008181717051043973), ('proj', 0.0006846970501858534), ('other', 0.0005641255516844722), ('resources', 3.957323461298661e-05), ('junit', 3.638699508934217e-05), ('srcDir', 2.916485216908142e-05)]
            throws NoSuchMethodException,
            InstantiationException,
            IllegalAccessException,
            InvocationTargetException {
        Constructor<T> ctor;
// ctor                 No	: [('con', 0.7587476206299242), ('clazz', 0.052802188715459226), ('requiredClass', 0.0262414917718451), ('e', 0.00896321439228896), ('i', 0.008855607749753526), ('l', 0.008844194842122108), ('expectedType', 0.008747620629924197), ('ofClass', 0.008747594077928167), ('c', 0.0005968095123068258), ('files', 0.0005551626305462837)]
        boolean noArg = false;
// noArg                No	: [('success', 0.03557914160015306), ('execute', 0.011368475580613014), ('found', 0.009946215376908218), ('debug', 0.008522151299803389), ('matches', 0.007463421745833903), ('traversesSymlinks', 0.006571163567018578), ('ok', 0.006395345889261725), ('hasCR', 0.006394496225388754), ('isOK', 0.006393646561515782), ('selfMove', 0.0063932217295792956)]
        // DataType can have a "no arg" constructor or take a single
        // Project argument.
        try {
            ctor = newclass.getConstructor();
// ctor                 No	: [('log', 0.13155454060004068), ('r', 0.12658556422259098), ('comeback', 0.12575212045419656), ('result', 0.06533373978044346), ('loader', 0.06423505608965825), ('ftp', 0.06355101085097101), ('rexec', 0.06332676064845974), ('telnet', 0.06332541199454686), ('matches', 0.062882985574054), ('buildRule', 0.008715745595736563)]
// newclass             No	: [('buildRule', 0.002757998803245348), ('b', 0.0022240139018871253), ('name', 0.0021694275130623333), ('value', 0.0020390698146293554), ('p', 0.0018025086470885455), ('file', 0.0016931999422889086), ('project', 0.0015677782194300828), ('line', 0.0013838611014726021), ('FILE_UTILS', 0.0013049737734226231), ('path', 0.0012296012152372966)]
            noArg = true;
// noArg                No	: [('obj', 0.750034069438066), ('buildRule', 0.0030648802181730203), ('project', 0.001996581918767863), ('compareFiles', 0.0016812657477548508), ('log', 0.0016189783198938199), ('out', 0.0015613996368848207), ('i', 0.0013796311802362318), ('ds', 0.0013448104675379783), ('p', 0.001130569821752138), ('fs', 0.0007952683813874839)]
        } catch (NoSuchMethodException nse) {
// nse                  4	: [('e', 0.7012180436281638), ('ex', 0.1354814674204893), ('ignored', 0.06658216200654439), ('nsme', 0.049830106480821486), ('nse', 0.0023154865977805484), ('nme2', 0.001158079190373141), ('resources', 4.946654326623327e-06), ('junit', 4.548374386167771e-06), ('srcDir', 3.6456065211351777e-06), ('separator', 3.5659505330440663e-06)]
            //can throw the same exception, if there is no this(Project) ctor.
            ctor = newclass.getConstructor(Project.class);
// ctor                 No	: [('buildRule', 0.04207097609816121), ('log', 0.022874469271660057), ('thrown', 0.010809828698588504), ('cmd', 0.0071326494207304214), ('out', 0.003813465111086717), ('project', 0.003661391056981602), ('sb', 0.0033594889302117135), ('result', 0.002310797825159265), ('s', 0.0022500403992489104), ('buf', 0.0020222569667712937)]
// newclass             No	: [('buildRule', 0.002757998803245348), ('b', 0.0022240139018871253), ('name', 0.0021694275130623333), ('value', 0.0020390698146293554), ('p', 0.0018025086470885455), ('file', 0.0016931999422889086), ('project', 0.0015677782194300828), ('line', 0.0013838611014726021), ('FILE_UTILS', 0.0013049737734226231), ('path', 0.0012296012152372966)]
            noArg = false;
// noArg                No	: [('setter', 0.5008389152044823), ('thrown', 0.2565491751466707), ('buildRule', 0.03181700711020857), ('tried', 0.010895074855094588), ('attrMap', 0.006741637003033149), ('runner', 0.005101311720585203), ('def', 0.0026717032765304362), ('elemMap', 0.002525822435508631), ('project', 0.002153370042416372), ('ih', 0.0018071556526581627)]
        }
        //now we instantiate
        T o = ctor.newInstance(
// o                    0	: [('o', 0.7561683896807542), ('as', 0.008201638811004896), ('get', 0.0020867036785236336), ('ret', 0.002061001346366235), ('nextElement', 0.002053991619414217), ('resources', 1.9786617306493306e-05), ('junit', 1.8193497544671086e-05), ('srcDir', 1.458242608454071e-05), ('separator', 1.4263802132176265e-05), ('expected', 1.3732762211568858e-05)]
// ctor                 No	: [('project', 0.7939459722774287), ('ref', 0.0458509326876903), ('clazz', 0.04377616934803671), ('propertyHelper', 0.006265536511925126), ('r', 0.004307166652103325), ('parseNextProperty', 0.004171596894476239), ('event', 0.0021779298708278034), ('buildRule', 0.0003447498504056685), ('b', 0.00027800173773589066), ('name', 0.00027117843913279166)]
            ((noArg) ? new Object[0] : new Object[] {project}));
// noArg                No	: [('weight_n1', 0.04995666347889419), ('a', 0.03356964616973551), ('b', 0.017539088738999918), ('encoding', 0.016677231965698525), ('dayOfTheYear', 0.01652264382815258), ('p', 0.013617629150323564), ('line', 0.005459225669544196), ('value', 0.005226394232341022), ('base', 0.004404709547021122), ('block', 0.004359194937283981)]
// project              7	: [('testClass', 0.11010835731843213), ('href', 0.1101017468088479), ('publicId', 0.11010156644276037), ('value', 0.06249186115758831), ('name', 0.06104017580417814), ('p', 0.05660513788584419), ('cmd', 0.05531738298510134), ('project', 0.05519134022923531), ('file', 0.05510849007149197), ('checksum', 0.05505438179071878)]

        //set up project references.
        project.setProjectReference(o);
// project              No	: [('buildRule', 0.025818041837618103), ('commandLine', 0.013880583731948289), ('ds', 0.01376661118572825), ('out', 0.008771048276896912), ('f', 0.008145772954478426), ('s', 0.007004412982981404), ('log', 0.006866377407681948), ('p', 0.00661329924233884), ('exe', 0.006380981893810756), ('newFilter', 0.005009928666769215)]
// o                    No	: [('listener', 0.3152334123488974), ('handler', 0.3152311056399326), ('obj', 0.065241979251994), ('nestedElement', 0.06522977859257938), ('nestedObject', 0.06522614483626137), ('c', 0.015342317725557117), ('m', 0.015263226960226984), ('instance', 0.01522707305122582), ('buildRule', 0.0003097529679942687), ('name', 0.0003000249244230013)]
        return o;
// o                    0	: [('o', 0.6263535982624513), ('result', 0.13580971211308485), ('newFilter', 0.003984870487312785), ('p', 0.0033098298169857987), ('cmd', 0.003020304480519916), ('commandLine', 0.0025039689022136494), ('buf', 0.002358818244167315), ('mapperElement', 0.002254885124702627), ('exe', 0.0020021472187377047), ('resources', 1.1415294359742763e-05)]
    }

    /**
     * Equality method for this definition (assumes the names are the same).
     *
     * @param other another definition.
     * @param project the project the definition.
     * @return true if the definitions are the same.
     */
    public boolean sameDefinition(AntTypeDefinition other, Project project) {
// other                0	: [('other', 0.771250995253481), ('def', 0.17455277856164567), ('parent', 0.005823358677751001), ('d', 0.0010112056347255905), ('old', 0.0010090549230471304), ('current', 0.0005059785368397076), ('definition', 0.0005049961129865839), ('resources', 4.946654326623327e-06), ('junit', 4.548374386167771e-06), ('srcDir', 3.6456065211351777e-06)]
// project              0	: [('project', 0.8550478359710256), ('p', 0.02981527428018188), ('aProj', 0.00021658567335524397), ('prj', 0.00018385425753033685), ('fallback', 0.00010227146313804966), ('proj', 8.558713127323167e-05), ('other', 7.051569396055902e-05), ('resources', 4.946654326623327e-06), ('junit', 4.548374386167771e-06), ('srcDir', 3.6456065211351777e-06)]
        return (other != null && other.getClass() == getClass()
// other                0	: [('other', 0.5299452992790219), ('value', 0.015093129048592753), ('t', 0.015035278257219313), ('a', 0.014989231502664952), ('options', 0.014970055955018947), ('children', 0.014965201359331841), ('millis', 0.014959312340696166), ('env', 0.014959127581620565), ('characters', 0.014956501143806785), ('file', 0.001495906381714986)]
// other                0	: [('other', 0.7521126158816664), ('file', 0.007209265625007232), ('value', 0.0028860914823196733), ('parent', 0.001904363288162117), ('watchdog', 0.001688522423781193), ('oldValue', 0.0016878055198883731), ('defaultValue', 0.0016574589301920615), ('password', 0.0015279004512096651), ('queuedData', 0.001266287605618017), ('resources', 0.0009877732972357538)]
            && other.getTypeClass(project).equals(getTypeClass(project))
// other                No	: [('parent', 0.626830116722519), ('equals', 0.12578924350901913), ('file', 0.008313691895549648), ('f', 0.0031375119625112317), ('targetFile', 0.0026417748073932308), ('dir', 0.002425673441709483), ('path', 0.002367340769706884), ('count', 0.002302131130732419), ('filesize', 0.0022994493791333517), ('choice', 0.0022992104111690783)]
// project              0	: [('project', 0.7092630758430845), ('buildRule', 0.001239011871977075), ('name', 0.0012000996976920051), ('file', 0.0008289863516022122), ('p', 0.0007871470352380268), ('s', 0.0005861127238233333), ('value', 0.0005607643723099167), ('f', 0.000557571503406594), ('e', 0.0005440919285960702), ('resources', 7.898216885627103e-05)]
// equals               4	: [('getProperty', 0.10016439890547972), ('map', 0.08613870778783848), ('add', 0.08353462271715052), ('setValue', 0.0247584937482771), ('equals', 0.006395612118794273), ('append', 0.005670114718870905), ('ant', 0.00551376978684161), ('log', 0.0037034534831460342), ('collect', 0.0030828493482071037), ('junit', 0.0017879118044531804)]
// project              0	: [('project', 0.41852615168616897), ('buildRule', 0.00247802374395415), ('name', 0.0024001993953840103), ('file', 0.0016579727032044243), ('p', 0.0015742940704760535), ('s', 0.0011722254476466665), ('value', 0.0011215287446198333), ('f', 0.001115143006813188), ('e', 0.0010881838571921403), ('resources', 0.00015796433771254206)]
            && other.getExposedClass(project).equals(getExposedClass(project))
// other                No	: [('specification', 0.06439624077135321), ('config', 0.0257682866024364), ('application', 0.025760958251532018), ('ph', 0.015617558502205225), ('parent', 0.014753684079390844), ('m', 0.013663509492008062), ('uri', 0.013660429460468538), ('name', 0.013634850693501491), ('optional', 0.013594406248041183), ('t', 0.013311986674876685)]
// project              0	: [('project', 0.6638085303885389), ('helper', 0.06633311775488959), ('buildRule', 0.001239011871977075), ('name', 0.0012000996976920051), ('file', 0.0008289863516022122), ('p', 0.0007871470352380268), ('s', 0.0005861127238233333), ('value', 0.0005607643723099167), ('f', 0.000557571503406594), ('resources', 7.898216885627103e-05)]
// equals               4	: [('getProperty', 0.10016439890547972), ('map', 0.08613870778783848), ('add', 0.08353462271715052), ('setValue', 0.0247584937482771), ('equals', 0.006395612118794273), ('append', 0.005670114718870905), ('ant', 0.00551376978684161), ('log', 0.0037034534831460342), ('collect', 0.0030828493482071037), ('junit', 0.0017879118044531804)]
// project              0	: [('project', 0.41852615168616897), ('helper', 0.04175714460068827), ('buildRule', 0.00247802374395415), ('name', 0.0024001993953840103), ('file', 0.0016579727032044243), ('p', 0.0015742940704760535), ('s', 0.0011722254476466665), ('value', 0.0011215287446198333), ('f', 0.001115143006813188), ('resources', 0.00015796433771254206)]
            && other.restrict == restrict
// other                No	: [('specification', 0.06439624077135321), ('config', 0.0257682866024364), ('application', 0.025760958251532018), ('ph', 0.015617558502205225), ('parent', 0.014753684079390844), ('m', 0.013663509492008062), ('uri', 0.013660429460468538), ('name', 0.013634850693501491), ('optional', 0.013594406248041183), ('t', 0.013311986674876685)]
// restrict             No	: [('components', 0.046190738227905745), ('name', 0.023438792264762424), ('children', 0.023109914521142166), ('text', 0.007869691723412788), ('defaultValue', 0.0077086826141636875), ('nested', 0.007704843101121935), ('manifestVersion', 0.007704691801952443), ('mainSection', 0.007703993437248963), ('project', 0.004055912301147845), ('location', 0.0038886749433718762)]
// restrict             No	: [('other', 0.004132954520123037), ('block', 0.0029521468655277668), ('sep', 0.0029309052687034706), ('DEFLATED', 0.0020508223429313347), ('SEND_FILES', 0.0020503975109948487), ('s', 0.0019718598849538535), ('o', 0.0018265673626756667), ('name', 0.0016393290431031757), ('args', 0.0012901171913117441), ('sig', 0.001175412568460544)]
            && other.adapterClass == adapterClass
// other                No	: [('file', 0.011210926781520417), ('c', 0.005241386484528148), ('index', 0.004229469824236667), ('value', 0.004071160126712244), ('rc', 0.003890606553705725), ('out', 0.0037157284106583504), ('name', 0.003343456031929846), ('args', 0.002994244180138415), ('dir', 0.002992969684328957), ('parent', 0.002987871701091126)]
// adapterClass         No	: [('components', 0.046190738227905745), ('name', 0.023438792264762424), ('children', 0.023109914521142166), ('text', 0.007869691723412788), ('defaultValue', 0.0077086826141636875), ('nested', 0.007704843101121935), ('manifestVersion', 0.007704691801952443), ('mainSection', 0.007703993437248963), ('project', 0.004055912301147845), ('location', 0.0038886749433718762)]
// adapterClass         No	: [('other', 0.004132954520123037), ('block', 0.0029521468655277668), ('sep', 0.0029309052687034706), ('DEFLATED', 0.0020508223429313347), ('SEND_FILES', 0.0020503975109948487), ('s', 0.0019718598849538535), ('o', 0.0018265673626756667), ('name', 0.0016393290431031757), ('args', 0.0012901171913117441), ('sig', 0.001175412568460544)]
            && other.adaptToClass == adaptToClass);
// other                No	: [('file', 0.011210926781520417), ('c', 0.005241386484528148), ('index', 0.004229469824236667), ('value', 0.004071160126712244), ('rc', 0.003890606553705725), ('out', 0.0037157284106583504), ('name', 0.003343456031929846), ('args', 0.002994244180138415), ('dir', 0.002992969684328957), ('parent', 0.002987871701091126)]
// adaptToClass         No	: [('components', 0.046190738227905745), ('name', 0.023438792264762424), ('children', 0.023109914521142166), ('text', 0.007869691723412788), ('defaultValue', 0.0077086826141636875), ('nested', 0.007704843101121935), ('manifestVersion', 0.007704691801952443), ('mainSection', 0.007703993437248963), ('project', 0.004055912301147845), ('location', 0.0038886749433718762)]
// adaptToClass         No	: [('other', 0.004132954520123037), ('block', 0.0029521468655277668), ('sep', 0.0029309052687034706), ('DEFLATED', 0.0020508223429313347), ('SEND_FILES', 0.0020503975109948487), ('s', 0.0019718598849538535), ('o', 0.0018265673626756667), ('name', 0.0016393290431031757), ('args', 0.0012901171913117441), ('sig', 0.001175412568460544)]
    }

    /**
     * Similar definition;
     * used to compare two definitions defined twice with the same
     * name and the same types.
     * The classloader may be different but have the same
     * path so #sameDefinition cannot
     * be used.
     * @param other the definition to compare to.
     * @param project the current project.
     * @return true if the definitions are the same.
     */
    public boolean similarDefinition(AntTypeDefinition other, Project project) {
// other                0	: [('other', 0.896250995253481), ('def', 0.04955277856164568), ('parent', 0.005823358677751001), ('d', 0.0010112056347255905), ('old', 0.0010090549230471304), ('current', 0.0005059785368397076), ('definition', 0.0005049961129865839), ('resources', 4.946654326623327e-06), ('junit', 4.548374386167771e-06), ('srcDir', 3.6456065211351777e-06)]
// project              0	: [('project', 0.8550478359710256), ('p', 0.02981527428018188), ('aProj', 0.00021658567335524397), ('prj', 0.00018385425753033685), ('fallback', 0.00010227146313804966), ('proj', 8.558713127323167e-05), ('other', 7.051569396055902e-05), ('resources', 4.946654326623327e-06), ('junit', 4.548374386167771e-06), ('srcDir', 3.6456065211351777e-06)]
        if (other == null
// other                No	: [('project', 0.25509323978022397), ('src', 0.17051676506770921), ('implementationClasspath', 0.08353124248343209), ('file', 0.009770410219790792), ('name', 0.006912461048513364), ('line', 0.0051044700337186215), ('classpath', 0.004636347124888669), ('instr', 0.0036574291928180257), ('resources', 0.0012777127834154474), ('buildRule', 0.0005217275572520232)]
            || getClass() != other.getClass()
// other                No	: [('obj', 0.7542202609006605), ('destfile', 0.008469995632749156), ('loader', 0.00845589197340743), ('file2', 0.00843801793062939), ('DEFLATED', 0.008437858618653208), ('wasEntry', 0.008437486890708782), ('wlEntry', 0.008437486890708782), ('c', 0.004259381089712546), ('size', 0.004224031284096923), ('sep', 0.000969905430582971)]
            || !getClassName().equals(other.getClassName())
// equals               0	: [('equals', 0.5179925702936993), ('setValue', 0.08778130743649003), ('getProperty', 0.06691820118684855), ('log', 0.01254375766565554), ('map', 0.01068718687529095), ('length', 0.006512793662142196), ('size', 0.005779876179597987), ('ant', 0.00551376978684161), ('iterator', 0.005141553308163721), ('stream', 0.0048392117779151745)]
// other                0	: [('other', 0.06847184537868714), ('another', 0.018348758284227766), ('name', 0.018310528127218277), ('o', 0.014718727529288126), ('attribute', 0.012383729073171555), ('dir', 0.012231471609475101), ('obj', 0.012174815105474723), ('r', 0.01207310773913997), ('className', 0.011985609935137727), ('fileName', 0.011964389883797385)]
            || !extractClassname(adapterClass).equals(
// adapterClass         No	: [('buildRule', 0.0049560474879083), ('name', 0.0048003987907680205), ('project', 0.0037189700390045984), ('file', 0.0033159454064088487), ('p', 0.003148588140952107), ('s', 0.002344450895293333), ('value', 0.0022430574892396667), ('f', 0.002230286013626376), ('e', 0.0021763677143842807), ('r', 0.0019993542236361197)]
// equals               2	: [('setValue', 0.0990339749931084), ('getProperty', 0.07565759562191889), ('equals', 0.025582448475177093), ('append', 0.02268045887548362), ('map', 0.01955483115135392), ('log', 0.014813813932584137), ('exists', 0.012992045179351331), ('collect', 0.012331397392828415), ('replace', 0.011073112199072753), ('filter', 0.007664201477811047)]
            extractClassname(other.adapterClass))
// other                No	: [('buildRule', 0.0049560474879083), ('name', 0.0048003987907680205), ('project', 0.0037189700390045984), ('file', 0.0033159454064088487), ('p', 0.003148588140952107), ('s', 0.002344450895293333), ('value', 0.0022430574892396667), ('f', 0.002230286013626376), ('e', 0.0021763677143842807), ('r', 0.0019993542236361197)]
// adapterClass         No	: [('components', 0.09619073822790575), ('name', 0.060938792264762426), ('children', 0.04810991452114217), ('text', 0.03286969172341279), ('defaultValue', 0.020208682614163687), ('mainSection', 0.020203993437248963), ('elements', 0.016372393070574528), ('attributes', 0.016371875070612534), ('nestedSequential', 0.01636445653733741), ('qname', 0.016355441898645694)]
            || !extractClassname(adaptToClass).equals(
// adaptToClass         No	: [('buildRule', 0.0049560474879083), ('name', 0.0048003987907680205), ('project', 0.0037189700390045984), ('file', 0.0033159454064088487), ('p', 0.003148588140952107), ('s', 0.002344450895293333), ('value', 0.0022430574892396667), ('f', 0.002230286013626376), ('e', 0.0021763677143842807), ('r', 0.0019993542236361197)]
// equals               2	: [('setValue', 0.0990339749931084), ('getProperty', 0.07565759562191889), ('equals', 0.025582448475177093), ('append', 0.02268045887548362), ('map', 0.01955483115135392), ('log', 0.014813813932584137), ('exists', 0.012992045179351331), ('collect', 0.012331397392828415), ('replace', 0.011073112199072753), ('filter', 0.007664201477811047)]
            extractClassname(other.adaptToClass))
// other                No	: [('buildRule', 0.0049560474879083), ('name', 0.0048003987907680205), ('project', 0.0037189700390045984), ('file', 0.0033159454064088487), ('p', 0.003148588140952107), ('s', 0.002344450895293333), ('value', 0.0022430574892396667), ('f', 0.002230286013626376), ('e', 0.0021763677143842807), ('r', 0.0019993542236361197)]
// adaptToClass         No	: [('components', 0.09619073822790575), ('name', 0.060938792264762426), ('children', 0.04810991452114217), ('text', 0.03286969172341279), ('defaultValue', 0.020208682614163687), ('mainSection', 0.020203993437248963), ('elements', 0.016372393070574528), ('attributes', 0.016371875070612534), ('nestedSequential', 0.01636445653733741), ('qname', 0.016355441898645694)]
            || restrict != other.restrict) {
// restrict             No	: [('additionalPatterns', 0.04040008050419714), ('jspUri', 0.0394318344248469), ('arg', 0.011620896628176915), ('OS_NAME', 0.006773931000283175), ('action', 0.006107505968639675), ('name', 0.005544315097562253), ('uri', 0.005001519155467135), ('parent', 0.004193529902572957), ('dir', 0.002932915286102798), ('best', 0.0029047701703106056)]
// other                No	: [('extension', 0.0020704912531478627), ('specificationVersion', 0.002061994614418144), ('implementationVersion', 0.0018061729974600115), ('sep', 0.0013004774106545496), ('implementationVendor', 0.0012953794274167185), ('start', 0.0011143282312801091), ('destfile', 0.0010412571382045297), ('specificationVendor', 0.001039557810458586), ('CODE_553', 0.0010323356675383253), ('CODE_521', 0.0010306363397923817)]
// restrict             No	: [('children', 0.27310991452114214), ('nested', 0.2577048431011219), ('components', 0.046190738227905745), ('name', 0.023438792264762424), ('ant', 0.01102753957368322), ('text', 0.007869691723412788), ('defaultValue', 0.0077086826141636875), ('manifestVersion', 0.007704691801952443), ('mainSection', 0.007703993437248963), ('junit', 0.0035758236089063608)]
            return false;
        }
        // all the names are the same: check if the class path of the loader
        // is the same
        ClassLoader oldLoader = other.getClassLoader();
// oldLoader            No	: [('loader', 0.20819612391978043), ('l', 0.19022931122217857), ('myLoader', 0.19020105989840225), ('preLoadClass', 0.18840218012337076), ('classLoader', 0.017992971793955648), ('parent', 0.01172105489773303), ('cl', 0.008997510838956927), ('coreLoader', 0.005399007833005075), ('origLoader', 0.004499196217544898), ('resources', 1.9786617306493306e-05)]
// other                No	: [('buildRule', 0.005515997606490696), ('b', 0.0044480278037742505), ('name', 0.004338855026124667), ('value', 0.004078139629258711), ('p', 0.003605017294177091), ('file', 0.0033863998845778173), ('project', 0.0031355564388601655), ('line', 0.0027677222029452043), ('FILE_UTILS', 0.0026099475468452462), ('path', 0.002459202430474593)]
        ClassLoader newLoader = getClassLoader();
// newLoader            No	: [('origLoader', 0.3888042199575119), ('loader', 0.10698671742207509), ('acl', 0.0970902234597988), ('caller', 0.09708963931588611), ('parent', 0.05628069551609341), ('orig', 0.026112407502830424), ('cL', 0.02611097369504478), ('genericLoader', 0.015606825118364576), ('classLoader', 0.008996485896977824), ('resources', 9.893308653246653e-06)]
        return oldLoader == newLoader
// oldLoader            No	: [('scriptLoader', 0.5033853398364447), ('result', 0.015120056940671063), ('p', 0.0076201746445720055), ('name', 0.007461707681275315), ('selectorsList', 0.006999499883363362), ('w', 0.006751557841681645), ('matchDirEntries', 0.005058135183104856), ('matchFileEntries', 0.005058108631108825), ('classpath', 0.0037957248298456352), ('resource', 0.003420975484118185)]
// newLoader            No	: [('other', 0.004132954520123037), ('block', 0.0029521468655277668), ('sep', 0.0029309052687034706), ('DEFLATED', 0.0020508223429313347), ('SEND_FILES', 0.0020503975109948487), ('s', 0.0019718598849538535), ('o', 0.0018265673626756667), ('name', 0.0016393290431031757), ('args', 0.0012901171913117441), ('sig', 0.001175412568460544)]
            || (oldLoader instanceof AntClassLoader
// oldLoader            No	: [('file', 0.0258515210903012), ('t', 0.02485227080072909), ('AUTO', 0.02421950490180995), ('newerResources', 0.01614374398522017), ('effectiveInputEncoding', 0.01614353156925193), ('even', 0.01614268190537896), ('name', 0.010464715524416269), ('entry', 0.008536922945178244), ('src', 0.008530342469749165), ('d', 0.008257684062849253)]
            && newLoader instanceof AntClassLoader
// newLoader            No	: [('file', 0.011210926781520417), ('c', 0.005241386484528148), ('index', 0.004229469824236667), ('value', 0.004071160126712244), ('rc', 0.003890606553705725), ('out', 0.0037157284106583504), ('name', 0.003343456031929846), ('args', 0.002994244180138415), ('dir', 0.002992969684328957), ('parent', 0.002987871701091126)]
            && ((AntClassLoader) oldLoader).getClasspath()
// oldLoader            No	: [('loader', 0.24723086575286357), ('cL', 0.2069472076250643), ('createdLoader', 0.20694699520909604), ('delegate', 0.08055968524687177), ('genericLoader', 0.08055952593489558), ('obj', 0.040296514254817214), ('o', 6.466680079433231e-05), ('t', 4.419067024030629e-05), ('task', 3.694747791937015e-05), ('instr', 3.4058332457042505e-05)]
            .equals(((AntClassLoader) newLoader).getClasspath()));
// equals               2	: [('setValue', 0.17556261487298006), ('getProperty', 0.1338364023736971), ('equals', 0.03598514058739843), ('log', 0.02508751533131108), ('map', 0.0213743737505819), ('length', 0.013025587324284393), ('size', 0.011559752359195975), ('add', 0.010462781555974032), ('iterator', 0.010283106616327442), ('stream', 0.009678423555830349)]
// newLoader            No	: [('loader', 0.24723086575286357), ('cL', 0.2069472076250643), ('createdLoader', 0.20694699520909604), ('delegate', 0.08055968524687177), ('genericLoader', 0.08055952593489558), ('obj', 0.040296514254817214), ('o', 6.466680079433231e-05), ('t', 4.419067024030629e-05), ('task', 3.694747791937015e-05), ('instr', 3.4058332457042505e-05)]
    }

    private String extractClassname(Class<?> c) {
// c                    1	: [('clazz', 0.19728709750390702), ('c', 0.18001147848289684), ('proxyClass', 0.09512045110246967), ('testClass', 0.07594396877884252), ('adapterClass', 0.04040585677680381), ('adaptToClass', 0.04040585677680381), ('ifc', 0.01902467544045938), ('resolverImplClass', 0.019024622336467317), ('files', 0.0005551626305462837), ('resources', 3.0498575831120466e-05)]
        return (c == null) ? "<null>" : c.getName();
// c                    No	: [('a', 0.514989231502665), ('other', 0.029945299279021974), ('value', 0.015093129048592753), ('t', 0.015035278257219313), ('options', 0.014970055955018947), ('children', 0.014965201359331841), ('millis', 0.014959312340696166), ('env', 0.014959127581620565), ('characters', 0.014956501143806785), ('file', 0.001495906381714986)]
// c                    No	: [('files', 0.006913618908512621), ('name', 0.006408412095729894), ('resources', 0.00571378466614175), ('log', 0.005545071960489529), ('instructions', 0.005367094592535581), ('cpInfo', 0.004756556672937997), ('filesets', 0.004185528123433604), ('s', 0.004083966077179814), ('state', 0.003891092378015204), ('params', 0.0038859943947773725)]
    }
}
