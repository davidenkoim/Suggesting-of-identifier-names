//java-projects\ant-master\src\main\org\apache\tools\ant\Target.java
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.StringTokenizer;
import org.apache.tools.ant.property.LocalProperties;
import org.apache.tools.ant.taskdefs.condition.And;
import org.apache.tools.ant.taskdefs.condition.Condition;
import org.apache.tools.ant.taskdefs.condition.Or;

/**
 * Class to implement a target object with required parameters.
 *
 * <p>If you are creating Targets programmatically, make sure you set
 * the Location to a useful value.  In particular all targets should
 * have different location values.</p>
 */
public class Target implements TaskContainer {

    /**
     * Name of this target.
     */
    private String name;

    /**
     * The "if" condition to test on execution.
     */
    private String ifString = "";

    /**
     * The "unless" condition to test on execution.
     */
    private String unlessString = "";

    private Condition ifCondition;

    private Condition unlessCondition;

    /**
     * List of targets this target is dependent on.
     */
    private List<String> dependencies = null;

    /**
     * Children of this target (tasks and data types).
     */
    private List<Object> children = new ArrayList<>();

    /**
     * Since Ant 1.6.2
     */
    private Location location = Location.UNKNOWN_LOCATION;

    /**
     * Project this target belongs to.
     */
    private Project project;

    /**
     * Description of this target, if any.
     */
    private String description = null;

    /**
     * Default constructor.
     */
    public Target() {
    // empty
    }

    /**
     * Cloning constructor.
     * @param other the Target to clone.
     */
    public Target(Target other) {
        this.name = other.name;
        this.ifString = other.ifString;
        this.unlessString = other.unlessString;
        this.ifCondition = other.ifCondition;
        this.unlessCondition = other.unlessCondition;
        this.dependencies = other.dependencies;
        this.location = other.location;
        this.project = other.project;
        this.description = other.description;
        // The children are added to after this cloning
        this.children = other.children;
    }

    /**
     * Sets the project this target belongs to.
     *
     * @param project The project this target belongs to.
     *                Must not be <code>null</code>.
     */
    public void setProject(Project project) {
        this.project = project;
    }

    /**
     * Returns the project this target belongs to.
     *
     * @return The project this target belongs to, or <code>null</code> if
     *         the project has not been set yet.
     */
    public Project getProject() {
        return project;
    }

    /**
     * Sets the location of this target's definition.
     *
     * @param location   <code>Location</code>
     * @since 1.6.2
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Get the location of this target's definition.
     *
     * @return <code>Location</code>
     * @since 1.6.2
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Sets the list of targets this target is dependent on.
     * The targets themselves are not resolved at this time.
     *
     * @param depS A comma-separated list of targets this target
     *             depends on. Must not be <code>null</code>.
     */
    public void setDepends(String depS) {
        for (String dep : parseDepends(depS, getName(), "depends")) {
            addDependency(dep);
        }
    }

    public static List<String> parseDepends(String depends, String targetName, String attributeName) {
        if (depends.isEmpty()) {
            return new ArrayList<>();
        }
        List<String> list = new ArrayList<>();
        StringTokenizer tok = new StringTokenizer(depends, ",", true);
        while (tok.hasMoreTokens()) {
            String token = tok.nextToken().trim();
            // Make sure the dependency is not empty string
            if (token.isEmpty() || ",".equals(token)) {
                throw new BuildException("Syntax Error: " + attributeName + " attribute of target \"" + targetName + "\" contains an empty string.");
            }
            list.add(token);
            // Make sure that depends attribute does not
            // end in a ,
            if (tok.hasMoreTokens()) {
                token = tok.nextToken();
                if (!tok.hasMoreTokens() || !",".equals(token)) {
                    throw new BuildException("Syntax Error: " + attributeName + " attribute for target \"" + targetName + "\" ends with a \",\" " + "character");
                }
            }
        }
        return list;
    }

    /**
     * Sets the name of this target.
     *
     * @param name The name of this target. Should not be <code>null</code>.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the name of this target.
     *
     * @return the name of this target, or <code>null</code> if the
     *         name has not been set yet.
     */
    public String getName() {
        return name;
    }

    /**
     * Adds a task to this target.
     *
     * @param task The task to be added. Must not be <code>null</code>.
     */
    public void addTask(Task task) {
        children.add(task);
    }

    /**
     * Adds the wrapper for a data type element to this target.
     *
     * @param r The wrapper for the data type element to be added.
     *          Must not be <code>null</code>.
     */
    public void addDataType(RuntimeConfigurable r) {
        children.add(r);
    }

    /**
     * Returns the current set of tasks to be executed by this target.
     *
     * @return an array of the tasks currently within this target
     */
    public Task[] getTasks() {
        List<Task> tasks = new ArrayList<>(children.size());
        for (Object o : children) {
            if (o instanceof Task) {
                tasks.add((Task) o);
            }
        }
        return tasks.toArray(new Task[tasks.size()]);
    }

    /**
     * Adds a dependency to this target.
     *
     * @param dependency The name of a target this target is dependent on.
     *                   Must not be <code>null</code>.
     */
    public void addDependency(String dependency) {
        if (dependencies == null) {
            dependencies = new ArrayList<>(2);
        }
        dependencies.add(dependency);
    }

    /**
     * Returns an enumeration of the dependencies of this target.
     *
     * @return an enumeration of the dependencies of this target (enumeration of String)
     */
    public Enumeration<String> getDependencies() {
        return dependencies == null ? Collections.emptyEnumeration() : Collections.enumeration(dependencies);
    }

    /**
     * Does this target depend on the named target?
     * @param other the other named target.
     * @return true if the target does depend on the named target
     * @since Ant 1.6
     */
    public boolean dependsOn(String other) {
        Project p = getProject();
        Hashtable<String, Target> t = p == null ? null : p.getTargets();
        return p != null && p.topoSort(getName(), t, false).contains(t.get(other));
    }

    /**
     * Sets the "if" condition to test on execution. This is the
     * name of a property to test for existence - if the property
     * is not set, the task will not execute. The property goes
     * through property substitution once before testing, so if
     * property <code>foo</code> has value <code>bar</code>, setting
     * the "if" condition to <code>${foo}_x</code> will mean that the
     * task will only execute if property <code>bar_x</code> is set.
     *
     * @param property The property condition to test on execution.
     *                 May be <code>null</code>, in which case
     *                 no "if" test is performed.
     */
    public void setIf(String property) {
        ifString = property == null ? "" : property;
        setIf(() -> {
            PropertyHelper propertyHelper = PropertyHelper.getPropertyHelper(getProject());
            Object o = propertyHelper.parseProperties(ifString);
            return propertyHelper.testIfCondition(o);
        });
    }

    /**
     * Returns the "if" property condition of this target.
     *
     * @return the "if" property condition or <code>null</code> if no
     *         "if" condition had been defined.
     * @since 1.6.2
     */
    public String getIf() {
        return ifString.isEmpty() ? null : ifString;
    }

    /**
     * Same as {@link #setIf(String)} but requires a {@link Condition} instance
     *
     * @param condition Condition
     * @since 1.9
     */
    public void setIf(Condition condition) {
        if (ifCondition == null) {
            ifCondition = condition;
        } else {
            And andCondition = new And();
            andCondition.setProject(getProject());
            andCondition.setLocation(getLocation());
            andCondition.add(ifCondition);
            andCondition.add(condition);
            ifCondition = andCondition;
        }
    }

    /**
     * Sets the "unless" condition to test on execution. This is the
     * name of a property to test for existence - if the property
     * is set, the task will not execute. The property goes
     * through property substitution once before testing, so if
     * property <code>foo</code> has value <code>bar</code>, setting
     * the "unless" condition to <code>${foo}_x</code> will mean that the
     * task will only execute if property <code>bar_x</code> isn't set.
     *
     * @param property The property condition to test on execution.
     *                 May be <code>null</code>, in which case
     *                 no "unless" test is performed.
     */
    public void setUnless(String property) {
        unlessString = property == null ? "" : property;
        setUnless(() -> {
            PropertyHelper propertyHelper = PropertyHelper.getPropertyHelper(getProject());
            Object o = propertyHelper.parseProperties(unlessString);
            return !propertyHelper.testUnlessCondition(o);
        });
    }

    /**
     * Returns the "unless" property condition of this target.
     *
     * @return the "unless" property condition or <code>null</code>
     *         if no "unless" condition had been defined.
     * @since 1.6.2
     */
    public String getUnless() {
        return unlessString.isEmpty() ? null : unlessString;
    }

    /**
     * Same as {@link #setUnless(String)} but requires a {@link Condition} instance
     *
     * @param condition Condition
     * @since 1.9
     */
    public void setUnless(Condition condition) {
        if (unlessCondition == null) {
            unlessCondition = condition;
        } else {
            Or orCondition = new Or();
            orCondition.setProject(getProject());
            orCondition.setLocation(getLocation());
            orCondition.add(unlessCondition);
            orCondition.add(condition);
            unlessCondition = orCondition;
        }
    }

    /**
     * Sets the description of this target.
     *
     * @param description The description for this target.
     *                    May be <code>null</code>, indicating that no
     *                    description is available.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the description of this target.
     *
     * @return the description of this target, or <code>null</code> if no
     *         description is available.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the name of this target.
     *
     * @return the name of this target, or <code>null</code> if the
     *         name has not been set yet.
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * Executes the target if the "if" and "unless" conditions are
     * satisfied. Dependency checking should be done before calling this
     * method, as it does no checking of its own. If either the "if"
     * or "unless" test prevents this target from being executed, a verbose
     * message is logged giving the reason. It is recommended that clients
     * of this class call performTasks rather than this method so that
     * appropriate build events are fired.
     *
     * @exception BuildException if any of the tasks fail or if a data type
     *                           configuration fails.
     *
     * @see #performTasks()
     * @see #setIf(String)
     * @see #setUnless(String)
     */
    public void execute() throws BuildException {
        if (ifCondition != null && !ifCondition.eval()) {
            project.log(this, "Skipped because property '" + project.replaceProperties(ifString) + "' not set.", Project.MSG_VERBOSE);
            return;
        }
        if (unlessCondition != null && unlessCondition.eval()) {
            project.log(this, "Skipped because property '" + project.replaceProperties(unlessString) + "' set.", Project.MSG_VERBOSE);
            return;
        }
        LocalProperties localProperties = LocalProperties.get(getProject());
        localProperties.enterScope();
        try {
            // use index-based approach to avoid ConcurrentModificationExceptions;
            // also account for growing target children
            // do not optimize this loop by replacing children.size() by a variable
            // as children can be added dynamically as in RhinoScriptTest where a target is adding work for itself
            for (int i = 0; i < children.size(); i++) {
                Object o = children.get(i);
                if (o instanceof Task) {
                    Task task = (Task) o;
                    task.perform();
                } else {
                    ((RuntimeConfigurable) o).maybeConfigure(project);
                }
            }
        } finally {
            localProperties.exitScope();
        }
    }

    /**
     * Performs the tasks within this target (if the conditions are met),
     * firing target started/target finished messages around a call to
     * execute.
     *
     * @see #execute()
     */
    public final void performTasks() {
        RuntimeException thrown = null;
        project.fireTargetStarted(this);
        try {
            execute();
        } catch (RuntimeException exc) {
            thrown = exc;
            throw exc;
        } finally {
            project.fireTargetFinished(this, thrown);
        }
    }

    /**
     * Replaces all occurrences of the given task in the list
     * of children with the replacement data type wrapper.
     *
     * @param el The task to replace.
     *           Must not be <code>null</code>.
     * @param o  The data type wrapper to replace <code>el</code> with.
     */
    void replaceChild(Task el, RuntimeConfigurable o) {
        int index;
        while ((index = children.indexOf(el)) >= 0) {
            children.set(index, o);
        }
    }

    /**
     * Replaces all occurrences of the given task in the list
     * of children with the replacement task.
     *
     * @param el The task to replace.
     *           Must not be <code>null</code>.
     * @param o  The task to replace <code>el</code> with.
     */
    void replaceChild(Task el, Task o) {
        int index;
        while ((index = children.indexOf(el)) >= 0) {
            children.set(index, o);
        }
    }
}
/**[
        Ground truth: name
        Range: (line 43,col 20)-(line 43,col 23)
        0,8622: name
        0,0623: value
        0,0542: buffer
        0,0542: text
        0,0357: encoding
        0,0290: map
        0,0284: pattern
        0,0278: path
        0,0278: location
        0,0274: element
        ,
        Ground truth: ifString
        Range: (line 48,col 20)-(line 48,col 27)
        0,5559: unlessString
        0,4064: line
        0,1314: value
        0,1127: base
        0,1095: pattern
        0,1094: encoding
        0,1094: matches
        0,1094: tmpString
        0,0559: fileProperties
        0,0559: descComp
        ,
        Ground truth: unlessString
        Range: (line 53,col 20)-(line 53,col 31)
        0,5559: ifString
        0,4064: line
        0,1314: value
        0,1127: base
        0,1095: pattern
        0,1094: encoding
        0,1094: matches
        0,1094: tmpString
        0,0559: fileProperties
        0,0559: descComp
        ,
        Ground truth: ifCondition
        Range: (line 55,col 23)-(line 55,col 33)
        0,8125: unlessCondition
        0,7506: condition
        0,0314: mapperElement
        0,0313: ifCondition
        0,0299: file
        0,0269: property
        0,0266: prefix
        0,0255: classpath
        0,0247: parent
        0,0203: name
        ,
        Ground truth: unlessCondition
        Range: (line 57,col 23)-(line 57,col 37)
        0,8125: ifCondition
        0,7506: condition
        0,0313: unlessCondition
        0,0269: error
        0,0269: s
        0,0258: inFile
        0,0255: classpath
        0,0243: file
        0,0201: c
        0,0190: task
        ,
        Ground truth: dependencies
        Range: (line 62,col 26)-(line 62,col 37)
        0,5241: children
        0,0606: foundFiles
        0,0606: includeTags
        0,0606: excludeTags
        0,0483: attributes
        0,0483: params
        0,0482: selectorsList
        0,0482: warnings
        0,0456: lines
        0,0360: targets
        ,
        Ground truth: children
        Range: (line 67,col 26)-(line 67,col 33)
        0,8760: origString
        0,5277: delegates
        0,3929: nested
        0,3306: task
        0,2474: r
        0,2383: event
        0,2382: refid
        0,1931: c
        0,1839: filterReaders
        0,1839: toAdd
        ,
        Ground truth: location
        Range: (line 72,col 22)-(line 72,col 29)
        0,9005: location
        0,4389: firstLocation
        0,0175: other
        0,0166: name
        0,0097: value
        0,0057: project
        0,0050: file
        0,0037: attributes
        0,0031: mComment
        0,0031: VALUES
        ,
        Ground truth: project
        Range: (line 77,col 21)-(line 77,col 27)
        0,8832: project
        0,1089: source
        0,0737: s
        0,0730: task
        0,0724: p
        0,0693: testMailServer
        0,0529: cmd
        0,0406: dir
        0,0367: sourceFile
        0,0362: buildRule
        ,
        Ground truth: description
        Range: (line 82,col 20)-(line 82,col 30)
        0,8021: description
        0,1007: other
        0,1007: runner
        0,0502: name
        0,0357: encoding
        0,0278: desc
        0,0250: jarSuffix
        0,0239: line
        0,0158: prefix
        0,0156: property
        ,
        Ground truth: other
        Range: (line 95,col 26)-(line 95,col 30)
        0,9179: location
        0,8700: name
        0,8056: description
        0,7813: ifProperty
        0,7813: unlessProperty
        0,6806: project
        0,5006: property
        0,3458: target
        0,1929: t
        0,1163: dir
        ,
        Ground truth: project
        Range: (line 115,col 36)-(line 115,col 42)
        0,8542: project
        0,2023: p
        0,0157: task
        0,0135: target
        0,0067: other
        0,0022: event
        0,0018: buildRule
        0,0015: attributes
        0,0015: context
        0,0014: component
        ,
        Ground truth: location
        Range: (line 135,col 38)-(line 135,col 45)
        0,9517: location
        0,0175: other
        0,0037: attributes
        0,0019: classname
        0,0018: resourceDtds
        0,0018: fileDtds
        0,0007: newLocation
        0,0007: firstLocation
        0,0007: UNKNOWN_LOCATION
        0,0007: expectedLocation
        ,
        Ground truth: dep
        Range: (line 157,col 21)-(line 157,col 23)
        0,2006: prefix
        0,1003: targetName
        0,0555: value
        0,0548: element
        0,0541: name
        0,0415: filename
        0,0375: include
        0,0375: includedFile
        0,0375: variable
        0,0369: exclude
        ,
        Ground truth: depS
        Range: (line 156,col 35)-(line 156,col 38)
        0,2000: depends
        0,2000: extensionPoint
        0,0553: name
        0,0215: s
        0,0197: value
        0,0152: message
        0,0136: target
        0,0136: msg
        0,0125: uri
        0,0102: line
        ,
        Ground truth: list
        Range: (line 166,col 22)-(line 166,col 25)
        0,2561: uris
        0,2525: relativePathStack
        0,1615: index
        0,0770: name
        0,0680: ch
        0,0594: sb
        0,0383: cmdl
        0,0380: out
        0,0376: result
        0,0371: entry
        ,
        Ground truth: tok
        Range: (line 167,col 25)-(line 167,col 27)
        0,6954: tokenizer
        0,5029: stDirectory
        0,5002: recurse
        0,2916: st
        0,2581: tok
        0,2267: tokBase
        0,2267: itok
        0,1953: lookahead
        0,1205: line
        0,0601: tokFile
        ,
        Ground truth: token
        Range: (line 169,col 20)-(line 169,col 24)
        0,5281: def
        0,1121: msg
        0,0859: message
        0,0638: mailMessage
        0,0586: i
        0,0582: name
        0,0573: result
        0,0397: filename
        0,0370: out
        0,0331: path
        ,
        Ground truth: depends
        Range: (line 162,col 52)-(line 162,col 58)
        0,5094: file
        0,1017: src
        0,0754: filenames
        0,0678: packages
        0,0567: classpath
        0,0553: name
        0,0415: excludes
        0,0415: includes
        0,0349: packageName
        0,0339: source
        ,
        Ground truth: targetName
        Range: (line 162,col 68)-(line 162,col 77)
        0,0486: name
        0,0441: filename
        0,0358: e
        0,0281: value
        0,0170: encoding
        0,0163: classname
        0,0136: prefix
        0,0131: remoteClass
        0,0128: tag
        0,0119: qname
        ,
        Ground truth: attributeName
        Range: (line 162,col 87)-(line 162,col 99)
        0,0563: name
        0,0500: commandLine
        0,0484: e
        0,0442: filename
        0,0281: value
        0,0254: manifestFile
        0,0200: className
        0,0192: src
        0,0170: encoding
        0,0169: file
        ,
        Ground truth: name
        Range: (line 192,col 32)-(line 192,col 35)
        0,8622: name
        0,0330: s
        0,0239: value
        0,0224: pattern
        0,0218: n
        0,0215: fname
        0,0187: normalizedName
        0,0079: other
        0,0020: vpath
        0,0019: message
        ,
        Ground truth: task
        Range: (line 211,col 30)-(line 211,col 33)
        0,6432: nestedTask
        0,3166: r
        0,3134: t
        0,1786: task
        0,1256: child
        0,0087: rc
        0,0048: O
        0,0047: name
        0,0046: instr
        0,0039: c
        ,
        Ground truth: r
        Range: (line 221,col 49)-(line 221,col 49)
        0,8001: r
        0,3145: task
        0,3134: t
        0,1256: child
        0,0587: wrapper
        0,0154: childWrapper
        0,0097: parentWrapper
        0,0087: rc
        0,0048: O
        0,0047: name
        ,
        Ground truth: tasks
        Range: (line 231,col 20)-(line 231,col 24)
        0,5424: children
        0,1270: result
        0,1256: task
        0,1250: dispatchable
        0,1139: nestedTasks
        0,0848: tasks
        0,0848: nested
        0,0847: unknownElements
        0,0533: ch
        0,0481: sb
        ,
        Ground truth: o
        Range: (line 232,col 21)-(line 232,col 21)
        0,6358: e
        0,5543: realThing
        0,5094: file
        0,2577: o
        0,0706: proxy
        0,0301: obj
        0,0301: realChild
        0,0301: component
        0,0082: value
        0,0071: name
        ,
        Ground truth: dependency
        Range: (line 246,col 38)-(line 246,col 47)
        0,3361: classname
        0,0849: extensionSet
        0,0839: jarFile
        0,0553: name
        0,0215: s
        0,0197: value
        0,0174: rc
        0,0152: message
        0,0136: target
        0,0136: msg
        ,
        Ground truth: p
        Range: (line 269,col 17)-(line 269,col 17)
        0,3729: p
        0,2758: project
        0,1831: value
        0,1099: nGroups
        0,1078: sections
        0,1073: prj
        0,0719: def
        0,0560: e
        0,0427: f
        0,0393: o
        ,
        Ground truth: t
        Range: (line 270,col 35)-(line 270,col 35)
        0,2483: targetTable
        0,1992: targets
        0,1947: currentTargets
        0,1660: exclusionLogMsg
        0,1411: projectTargets
        0,0978: name
        0,0621: ptargets
        0,0621: targetsMap
        0,0485: up
        0,0454: subString
        ,
        Ground truth: other
        Range: (line 268,col 37)-(line 268,col 41)
        0,0623: name
        0,0571: i
        0,0294: key
        0,0215: s
        0,0197: value
        0,0195: publicId
        0,0170: index
        0,0170: processor
        0,0154: testIdentifier
        0,0152: f
        ,
        Ground truth: propertyHelper
        Range: (line 290,col 28)-(line 290,col 41)
        0,8949: propertyHelper
        0,1432: r
        0,1343: p
        0,1341: v
        0,1341: helper
        0,1341: props
        0,0472: result
        0,0309: ph
        0,0168: newFilter
        0,0128: cmd
        ,
        Ground truth: o
        Range: (line 291,col 20)-(line 291,col 20)
        0,4364: ifCond
        0,2682: ifCondition
        0,1907: ref
        0,1615: o
        0,0954: v
        0,0954: expanded
        0,0953: realChild
        0,0953: oldvalue
        0,0937: element
        0,0127: a
        ,
        Ground truth: property
        Range: (line 287,col 30)-(line 287,col 37)
        0,5004: other
        0,1753: c
        0,1752: propertyName
        0,1750: cond
        0,1750: ifProperty
        0,1750: ifCond
        0,0891: pattern
        0,0870: message
        0,0870: namespace
        0,0445: startTag
        ,
        Ground truth: andCondition
        Range: (line 317,col 17)-(line 317,col 28)
        0,3335: a
        0,1261: c
        0,1254: other
        0,1251: ifProperty
        0,1250: condition
        0,1039: task
        0,1006: source
        0,0509: move
        0,0509: antlib
        0,0506: replacement
        ,
        Ground truth: condition
        Range: (line 313,col 33)-(line 313,col 41)
        0,7815: c
        0,7503: ifCondition
        0,1763: condition
        0,0313: other
        0,0313: ifProperty
        0,0313: andCondition
        0,0096: unlessCondition
        0,0087: rc
        0,0048: O
        0,0047: name
        ,
        Ground truth: propertyHelper
        Range: (line 342,col 28)-(line 342,col 41)
        0,8949: propertyHelper
        0,1432: r
        0,1343: p
        0,1341: v
        0,1341: helper
        0,1341: props
        0,1046: exe
        0,1046: selectorsList
        0,0714: encodable
        0,0529: s
        ,
        Ground truth: o
        Range: (line 343,col 20)-(line 343,col 20)
        0,4250: unlessCond
        0,2834: unlessCondition
        0,1907: ref
        0,1615: o
        0,0954: v
        0,0954: expanded
        0,0953: realChild
        0,0953: oldvalue
        0,0937: element
        0,0127: a
        ,
        Ground truth: property
        Range: (line 339,col 34)-(line 339,col 41)
        0,5004: other
        0,1753: c
        0,1752: propertyName
        0,1750: cond
        0,1750: unlessProperty
        0,1750: unlessCond
        0,0891: pattern
        0,0870: message
        0,0870: namespace
        0,0445: startTag
        ,
        Ground truth: orCondition
        Range: (line 369,col 16)-(line 369,col 26)
        0,3335: o
        0,1261: c
        0,1254: other
        0,1251: unlessProperty
        0,1250: condition
        0,1039: task
        0,1006: source
        0,0509: move
        0,0509: antlib
        0,0506: replacement
        ,
        Ground truth: condition
        Range: (line 365,col 37)-(line 365,col 45)
        0,7815: c
        0,7503: unlessCondition
        0,1763: condition
        0,0313: other
        0,0313: unlessProperty
        0,0313: orCondition
        0,0192: ifCondition
        0,0087: rc
        0,0048: O
        0,0047: name
        ,
        Ground truth: description
        Range: (line 385,col 39)-(line 385,col 49)
        0,8752: desc
        0,8021: description
        0,1007: other
        0,0073: value
        0,0069: name
        0,0027: s
        0,0019: message
        0,0017: target
        0,0017: msg
        0,0016: uri
        ,
        Ground truth: localProperties
        Range: (line 435,col 25)-(line 435,col 39)
        0,1669: l
        0,1667: localProperties
        0,1479: path
        0,1468: newProject
        0,1468: filecopyList
        0,1468: singleResource
        0,0835: get
        0,0357: ds
        0,0331: buildRule
        0,0319: exe
        ,
        Ground truth: o
        Range: (line 443,col 24)-(line 443,col 24)
        0,2577: realThing
        0,2126: o
        0,1901: obj
        0,1900: component
        0,1695: element
        0,0957: c
        0,0650: realChild
        0,0558: categoryObject
        0,0522: value
        0,0488: ch
        ,
        Ground truth: task
        Range: (line 445,col 26)-(line 445,col 29)
        0,7690: task
        0,2727: childTask
        0,0378: o
        0,0189: ue
        0,0033: set
        0,0022: nestedTask
        0,0012: i
        0,0010: t
        0,0005: buildRule
        0,0005: owner
        ,
        Ground truth: i
        Range: (line 442,col 22)-(line 442,col 22)
        0,8424: i
        0,1981: entries
        0,1980: as
        0,1980: pcount
        0,0876: index
        0,0745: j
        0,0388: k
        0,0307: t
        0,0156: name
        0,0109: icounter
        ,
        Ground truth: thrown
        Range: (line 464,col 26)-(line 464,col 31)
        0,1005: error
        0,1001: exc
        0,0660: msg
        0,0550: thrown
        0,0503: reader
        0,0457: ex
        0,0332: message
        0,0332: output
        0,0331: type
        0,0330: srcFile
        ,
        Ground truth: exc
        Range: (line 468,col 35)-(line 468,col 37)
        0,7868: ex
        0,7512: exc
        0,1257: t
        0,1250: cnfe
        0,0080: e
        0,0040: be
        0,0033: name
        0,0028: thrown
        0,0028: buildRule
        0,0028: err
        ,
        Ground truth: index
        Range: (line 485,col 13)-(line 485,col 17)
        0,7725: index
        0,0950: line
        0,0292: read
        0,0219: count
        0,0219: te
        0,0219: bytesRead
        0,0209: length
        0,0197: ftab
        0,0183: numRead
        0,0153: i
        ,
        Ground truth: el
        Range: (line 484,col 28)-(line 484,col 29)
        0,8773: el
        0,0428: task
        0,0124: nestedTask
        0,0100: ch
        0,0079: sep
        0,0079: endToken
        0,0079: REVISION
        0,0060: t
        0,0060: from
        0,0059: beginToken
        ,
        Ground truth: o
        Range: (line 484,col 52)-(line 484,col 52)
        0,8750: o
        0,3513: parentWrapper
        0,1380: w
        0,0884: childWrapper
        0,0561: wrapper
        0,0313: dirEnd
        0,0260: r
        0,0087: child
        0,0044: rc
        0,0043: copyRC
        ,
        Ground truth: index
        Range: (line 500,col 13)-(line 500,col 17)
        0,7725: index
        0,0950: line
        0,0292: read
        0,0219: count
        0,0219: te
        0,0219: bytesRead
        0,0209: length
        0,0197: ftab
        0,0183: numRead
        0,0153: i
        ,
        Ground truth: el
        Range: (line 499,col 28)-(line 499,col 29)
        0,8773: el
        0,0428: task
        0,0124: nestedTask
        0,0100: ch
        0,0079: sep
        0,0079: endToken
        0,0079: REVISION
        0,0060: t
        0,0060: from
        0,0059: beginToken
        ,
        Ground truth: o
        Range: (line 499,col 37)-(line 499,col 37)
        0,8750: o
        0,5028: task
        0,0404: child
        0,0313: dirEnd
        0,0177: nestedTask
        0,0076: t
        0,0037: owner
        0,0028: el
        0,0019: helper
        0,0019: owningTask
        ]
Count of identifiers: 53
Avr. MRR: 0,4503
 */