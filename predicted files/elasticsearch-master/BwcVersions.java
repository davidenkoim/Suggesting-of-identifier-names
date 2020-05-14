// Path id: 9
// Path to file: C:\Users\Igor\IdeaProjects\SLP-Core-0.3\java-projects\elasticsearch-master\buildSrc\src\main\java\org\elasticsearch\gradle\BwcVersions.java
// Number of identifiers: 344	Accuracy: 21.80%	MRR: 26.96%
// True 	Rank of true in predictions : [(predicted token, probability of a token), ...]

/*
 * Licensed to Elasticsearch under one or more contributor
 * license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright
 * ownership. Elasticsearch licenses this file to you under
 * the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.elasticsearch.gradle;
// org                  0	: ['org', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'j', 'allocation']
// elasticsearch        0	: ['elasticsearch', 'get', 'util', 'common', 'xpack', 'gradle', 'joda', 'xml', 'yaml']
// gradle               No	: ['xpack', 'common', 'action', 'search', 'index', 'client', 'cluster', 'test', 'rest', 'analysis']

import java.util.ArrayList;
// java                 1	: ['org', 'java', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'fixture']
// util                 0	: ['util', 'time', 'security', 'lang', 'elasticsearch', 'sql', 'get', 'text', 'math']
import java.util.Collection;
// java                 0	: ['java', 'org', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'fixture']
// util                 0	: ['util', 'time', 'security', 'lang', 'elasticsearch', 'sql', 'get', 'text', 'math']
import java.util.Collections;
// java                 0	: ['java', 'org', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'fixture']
// util                 0	: ['util', 'time', 'security', 'lang', 'elasticsearch', 'sql', 'get', 'text', 'math']
import java.util.HashMap;
// java                 0	: ['java', 'org', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'fixture']
// util                 0	: ['util', 'time', 'security', 'lang', 'elasticsearch', 'sql', 'get', 'text', 'math']
import java.util.HashSet;
// java                 0	: ['java', 'org', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'fixture']
// util                 0	: ['util', 'time', 'security', 'lang', 'elasticsearch', 'sql', 'get', 'text', 'math']
import java.util.List;
// java                 0	: ['java', 'org', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'fixture']
// util                 0	: ['util', 'time', 'security', 'lang', 'elasticsearch', 'sql', 'get', 'text', 'math']
import java.util.Map;
// java                 0	: ['java', 'org', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'fixture']
// util                 0	: ['util', 'time', 'security', 'lang', 'elasticsearch', 'sql', 'get', 'text', 'math']
import java.util.Set;
// java                 0	: ['java', 'org', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'fixture']
// util                 0	: ['util', 'time', 'security', 'lang', 'elasticsearch', 'sql', 'get', 'text', 'math']
import java.util.SortedSet;
// java                 0	: ['java', 'org', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'fixture']
// util                 0	: ['util', 'time', 'security', 'lang', 'elasticsearch', 'sql', 'get', 'text', 'math']
import java.util.TreeSet;
// java                 0	: ['java', 'org', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'fixture']
// util                 0	: ['util', 'time', 'security', 'lang', 'elasticsearch', 'sql', 'get', 'text', 'math']
import java.util.function.Consumer;
// java                 0	: ['java', 'org', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'fixture']
// util                 0	: ['util', 'time', 'security', 'lang', 'elasticsearch', 'sql', 'get', 'text', 'math']
// function             0	: ['function', 'stream', 'regex', 'elasticsearch', 'zip', 'UUID', 'get', 'util', 'jar', 'logging']
import java.util.regex.Matcher;
// java                 0	: ['java', 'org', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'fixture']
// util                 0	: ['util', 'time', 'security', 'lang', 'elasticsearch', 'sql', 'get', 'text', 'math']
// regex                2	: ['function', 'stream', 'regex', 'elasticsearch', 'zip', 'UUID', 'get', 'util', 'jar', 'logging']
import java.util.regex.Pattern;
// java                 0	: ['java', 'org', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'fixture']
// util                 0	: ['util', 'time', 'lang', 'security', 'elasticsearch', 'sql', 'get', 'text', 'math']
// regex                2	: ['function', 'stream', 'regex', 'elasticsearch', 'zip', 'UUID', 'get', 'util', 'jar', 'logging']
import java.util.stream.Collectors;
// java                 0	: ['java', 'org', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'fixture']
// util                 0	: ['util', 'time', 'security', 'lang', 'elasticsearch', 'sql', 'get', 'text', 'math']
// stream               1	: ['function', 'stream', 'regex', 'elasticsearch', 'zip', 'UUID', 'get', 'util', 'jar', 'logging']
import java.util.stream.Stream;
// java                 0	: ['java', 'org', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'fixture']
// util                 0	: ['util', 'time', 'security', 'lang', 'elasticsearch', 'sql', 'get', 'text', 'math']
// stream               1	: ['function', 'stream', 'regex', 'elasticsearch', 'zip', 'UUID', 'get', 'util', 'jar', 'logging']

import static java.util.Collections.emptyList;
// java                 1	: ['org', 'java', 'fixture', 'get', 'core', 'Type', 'lucene', 'stream', 'State', 'id']
// util                 0	: ['util', 'lang', 'time', 'sql', 'elasticsearch', 'security', 'get', 'common', 'text']
// emptyList            1	: ['emptyMap', 'emptyList', 'singleton', 'shuffle', 'elasticsearch', 'sort', 'get', 'util', 'common', 'xpack']
import static java.util.Collections.unmodifiableList;
// java                 0	: ['java', 'org', 'fixture', 'get', 'core', 'Type', 'lucene', 'stream', 'State', 'id']
// util                 0	: ['util', 'lang', 'time', 'sql', 'elasticsearch', 'security', 'get', 'common', 'text']

/**
 * A container for elasticsearch supported version information used in BWC testing.
 *
 * Parse the Java source file containing the versions declarations and use the known rules to figure out which are all
 * the version the current one is wire and index compatible with.
 * On top of this, figure out which of these are unreleased and provide the branch they can be built from.
 *
 * Note that in this context, currentVersion is the unreleased version this build operates on.
 * At any point in time there will surely be four such unreleased versions being worked on,
 * thus currentVersion will be one of these.
 *
 * Considering:
 * <dl>
 *     <dt>M, M &gt; 0</dt>
 *     <dd>last released major</dd>
 *     <dt>N, N &gt; 0</dt>
 *     <dd>last released minor</dd>
 * </dl>
 *
 * <ul>
 * <li>the unreleased <b>major</b>, M+1.0.0 on the `master` branch</li>
 * <li>the unreleased <b>minor</b>,  M.N.0 on the `M.x` (x is literal) branch</li>
 * <li>the unreleased <b>bugfix</b>, M.N.c (c &gt; 0) on the `M.N` branch</li>
 * <li>the unreleased <b>maintenance</b>, M-1.d.e ( d &gt; 0, e &gt; 0) on the `(M-1).d` branch</li>
 * </ul>
 * In addition to these, there will be a fifth one when a minor reaches feature freeze, we call this the <i>staged</i>
 * version:
 * <ul>
 * <li>the unreleased <b>staged</b>, M.N-2.0 (N &gt; 2) on the `M.(N-2)` branch</li>
 * </ul>
 *
 * Each build is only concerned with versions before it, as those are the ones that need to be tested
 * for backwards compatibility. We never look forward, and don't add forward facing version number to branches of previous
 * version.
 *
 * Each branch has a current version, and expected compatible versions are parsed from the server code's Version` class.
 * We can reliably figure out which the unreleased versions are due to the convention of always adding the next unreleased
 * version number to server in all branches when a version is released.
 * E.x when M.N.c is released M.N.c+1 is added to the Version class mentioned above in all the following branches:
 *  `M.N`, `M.x` and `master` so we can reliably assume that the leafs of the version tree are unreleased.
 * This convention is enforced by checking the versions we consider to be unreleased against an
 * authoritative source (maven central).
 * We are then able to map the unreleased version to branches in git and Gradle projects that are capable of checking
 * out and building them, so we can include these in the testing plan as well.
 */
public class BwcVersions {

    private static final Pattern LINE_PATTERN = Pattern.compile(
// LINE_PATTERN         No	: ['NO_WS_PATTERN', 'PATTERN', 'START', 'LEFT_MODIFIER_PATTERN', 'MISSING_CLASS_PATTERN', 'USERPASS_REGEX', 'lineRegex', 'JDK_HOME_LOGLINE', 'LEADING_DELIMITER_PATTERN', 'VAGRANT_VERSION']
        "\\W+public static final Version V_(\\d+)_(\\d+)_(\\d+)(_alpha\\d+|_beta\\d+|_rc\\d+)? .*"
    );

    private final Version currentVersion;
// currentVersion       No	: ['version', 'indexVersionCreated', 'esVersion', 'minimumVersion', 'minVersion', 'indexVersion', 'elasticsearchVersion', 'modelSnapshotMinVersion', 'nodeVersion', 'get']
    private final Map<Integer, List<Version>> groupByMajor;
// groupByMajor         No	: ['t', 'versions', 'loadedAnalyzers', 'entry', 'stableVersions', 'oldVersions', 'preBuiltAnalyzerEntry', 'majorVersions', 'byMinor', 'expectedLoadedAnalyzers']
    private final Map<Version, UnreleasedVersionInfo> unreleased;
// unreleased           No	: ['listener', 'metadata', 'PARSER', 'entry', 'map', 'buckets', 'future', 'params', 'fields', 'indices']

    public class UnreleasedVersionInfo {
        public final Version version;
// version              0	: ['version', 'mappingVersion', 'nodeVersion', 'esVersion', 'masterVersion', 'CURRENT', 'indexVersionCreated', 'minVersion', 'minNodeVersion', 'get']
        public final String branch;
// branch               No	: ['feature', 'name', 'type', 'methodName', 'email', 'returnCanonicalTypeName', 'url', 'uid', 'issuer', 'expiryDate']
        public final String gradleProjectPath;
// gradleProjectPath    No	: ['name', 'type', 'methodName', 'email', 'returnCanonicalTypeName', 'url', 'feature', 'uid', 'issuer', 'expiryDate']

        UnreleasedVersionInfo(Version version, String branch, String gradleProjectPath) {
// version              0	: ['version', 'indexCreatedVersion', 'indexVersion', 'indexVersionCreated', 'indexCreated', 'other', 'compareTo', 'version1', 'v', 'currentVersion']
// branch               No	: ['accessToken', 'tokenizer', 'payload', 'description', 'clusterName', 'action', 'failure', 'versionProperty', 'uid', 'type']
// gradleProjectPath    No	: ['name', 'action', 'message', 'fieldName', 'id', 'type', 'field', 'index', 'value', 'reason']
            this.version = version;
// version              8	: ['name', 'id', 'jobId', 'index', 'settings', 'indices', 'client', 'type', 'version', 'value']
// version              0	: ['version', 'in', 'input', 'versionSupplier', 'config', 'objectFactory', 'clusterState', 'metadata', 'client', 'get']
            this.branch = branch;
// branch               No	: ['modifiedDate', 'seqNo', 'clusterName', 'feature', 'requestId', 'found', 'allowLazyStart', 'metadata', 'patterns', 'lastChecked']
// branch               No	: ['randomInt', 'client', 'in', 'expectThrows', 'mock', 'parser', 'randomBoolean', 'randomFrom', 'response', 'createParser']
            this.gradleProjectPath = gradleProjectPath;
// gradleProjectPath    No	: ['client', 'name', 'threadPool', 'id', 'version', 'type', 'clusterService', 'source', 'value', 'licenseState']
// gradleProjectPath    No	: ['client', 'in', 'expectThrows', 'mock', 'parser', 'randomBoolean', 'randomFrom', 'response', 'createParser', 'request']
        }
    }

    public BwcVersions(List<String> versionLines) {
// versionLines         No	: ['explanation', 'indices', 'path', 'fields', 'featureNames', 'fieldNames', 'blobNames', 'phase1FileNames', 'command', 'warnings']
        this(versionLines, Version.fromString(VersionProperties.getElasticsearch()));
// versionLines         No	: ['name', 'id', 'source', 'settings', 'client', 'fieldName', 'shardId', 'index', 'status', 'key']
    }

    protected BwcVersions(List<String> versionLines, Version currentVersionProperty) {
// versionLines         No	: ['explanation', 'indices', 'path', 'fields', 'featureNames', 'fieldNames', 'blobNames', 'phase1FileNames', 'command', 'warnings']
// currentVersionProperty No	: ['version', 'repositoryMetaVersion', 'indexCreatedVersion', 'indexVersion', 'indexVersionCreated', 'minVersion', 'minimumIndexCompatibilityVersion', 'remoteVersion', 'upgradeVersion', 'mostRecentTrialVersion']
        this(
            versionLines.stream()
// versionLines         No	: ['name', 'id', 'source', 'settings', 'client', 'fieldName', 'shardId', 'index', 'status', 'key']
// stream               No	: ['elasticsearch', 'get', 'util', 'common', 'xpack', 'put', 'action', 'index', 'field', 'add']
                .map(LINE_PATTERN::matcher)
// map                  0	: ['map', 'filter', 'collect', 'allMatch', 'sorted', 'limit', 'distinct', 'min', 'max', 'skip']
// LINE_PATTERN         No	: ['s', 'e', 'p', 't', 'o', 'entry', 'v', 'f', 'i', 'a']
// matcher              No	: ['fromXContent', 'onFailure', 'get', 'parse', 'getName', 'build', 'set', 'add', 'getKey', 'getValue']
                .filter(Matcher::matches)
// filter               0	: ['filter', 'get', 'put', 'build', 'field', 'size', 'admin', 'indices', 'value', 'execute']
// matches              0	: ['matches', 'fromXContent', 'onFailure', 'get', 'parse', 'getName', 'build', 'set', 'add', 'getKey']
                .map(
// map                  0	: ['map', 'collect', 'get', 'put', 'build', 'field', 'size', 'admin', 'indices', 'value']
                    match -> new Version(
// match                No	: ['m', 's', 'e', 'p', 't', 'o', 'entry', 'f', 'n', 'request']
                        Integer.parseInt(match.group(1)),
// match                No	: ['matcher', 'retryEndpoint', 'value', 'id', 'fieldNode', 's', 'next', 'm', 'sValue', 'indexRequest']
// group                6	: ['v2', 'timestampFormat', 'firstIndeterminateDateNumber', 'secondIndeterminateDateNumber', 'next', 'substring', 'group', 'getName', 'matches', 'isPresent']
                        Integer.parseInt(match.group(2)),
// match                No	: ['next', 'args', 'url', 'parts', 'id', 'shard', 'op', 'actualVersion', 'requiredVersion', 'targetCompatibility']
// group                6	: ['v2', 'timestampFormat', 'firstIndeterminateDateNumber', 'secondIndeterminateDateNumber', 'next', 'substring', 'group', 'getName', 'matches', 'isPresent']
                        Integer.parseInt(match.group(3))
// match                No	: ['next', 'args', 'url', 'parts', 'id', 'shard', 'op', 'actualVersion', 'requiredVersion', 'targetCompatibility']
// group                6	: ['v2', 'timestampFormat', 'firstIndeterminateDateNumber', 'secondIndeterminateDateNumber', 'next', 'substring', 'group', 'getName', 'matches', 'isPresent']
                    )
                )
                .collect(Collectors.toCollection(TreeSet::new)),
// collect              3	: ['get', 'build', 'actionGet', 'collect', 'put', 'admin', 'execute', 'numberOfShards', 'incrementVersion', 'setActionMode']
            currentVersionProperty
// currentVersionProperty No	: ['listener', 'nodeStats', 'equalTo', 'is', 'e', 'isDate', 'entry', 'randomBoolean', 'createDocument', 'get']
        );
    }

    // for testkit tests, until BwcVersions is extracted into an extension
    public BwcVersions(SortedSet<Version> allVersions, Version currentVersionProperty) {
// allVersions          8	: ['listener', 'versions', 'released', 'unreleased', 'predicate', 'nodeVersions', 'versionFuture', 'bwcVersions', 'allVersions', 'authoritativeReleasedVersions']
// currentVersionProperty No	: ['version', 'repositoryMetaVersion', 'indexCreatedVersion', 'indexVersion', 'indexVersionCreated', 'minVersion', 'minimumIndexCompatibilityVersion', 'remoteVersion', 'upgradeVersion', 'mostRecentTrialVersion']
        if (allVersions.isEmpty()) {
// allVersions          No	: ['randomBoolean', 'token', 'obj', 'listener', 'visitor', 'value', 'e', 'request', 'i', 'logger']
// isEmpty              No	: ['get', 'size', 'elasticsearch', 'util', 'common', 'xpack', 'put', 'action', 'field']
            throw new IllegalArgumentException("Could not parse any versions");
        }

        currentVersion = allVersions.last();
// currentVersion       No	: ['builder', 'listener', 'terminal', 'logger', 'sendingUser', 'request', 'delegatedRealms', 'out', 'action', 'settings']
// allVersions          No	: ['versionValue', 'currentState', 'currentVersion', 'client', 'in', 'expectThrows', 'mock', 'parser', 'randomBoolean', 'randomFrom']
// last                 No	: ['get', 'size', 'elasticsearch', 'util', 'common', 'xpack', 'put', 'action', 'field']

        groupByMajor = allVersions.stream()
// groupByMajor         No	: ['builder', 'client', 'logger', 'i', 'request', 'verify', 'document', 'fail', 'refresh', 'clusterState']
// allVersions          No	: ['client', 'in', 'expectThrows', 'mock', 'parser', 'randomBoolean', 'randomFrom', 'response', 'createParser', 'request']
// stream               No	: ['get', 'size', 'elasticsearch', 'util', 'common', 'xpack', 'put', 'action', 'field']
            // We only care about the last 2 majors when it comes to BWC.
            // It might take us time to remove the older ones from versionLines, so we allow them to exist.
            .filter(version -> version.getMajor() > currentVersion.getMajor() - 2)
// filter               1	: ['map', 'filter', 'collect', 'allMatch', 'sorted', 'limit', 'distinct', 'min', 'max', 'skip']
// version              No	: ['e', 'n', 's', 'r', 'entry', 't', 'c', 'p', 'f', 'v']
// version              0	: ['version', 'create', 'exec', 'client', 'builder', 'listener', 'p', 'r', 'fail', 's']
// currentVersion       No	: ['maxValue', 'status', 'i', 'value', 'right', 'ignoreAbove', 'prevParentDoc', 'builder', 'top', 'now']
            .collect(Collectors.groupingBy(Version::getMajor, Collectors.toList()));
// collect              No	: ['coordinate', 'put', 'build', 'get', 'field', 'getKey', 'numberOfReplicas', 'elasticsearch', 'corrections']

        assertCurrentVersionMatchesParsed(currentVersionProperty);
// currentVersionProperty No	: ['request', 'response', 'e', 'client', 'i', 'settings', 'parser', 'index', 'randomBoolean', 'in']

        assertNoOlderThanTwoMajors();

        Map<Version, UnreleasedVersionInfo> unreleased = new HashMap<>();
// unreleased           No	: ['listener', 'metadata', 'PARSER', 'entry', 'map', 'buckets', 'future', 'params', 'fields', 'indices']
        for (Version unreleasedVersion : getUnreleased()) {
// unreleasedVersion    No	: ['bwcVersion', 'v', 'version', 'indexVersion', 'other', 'createdVersion', 'indexCreatedVersion', 'indexCreated', 'repositoryMetaVersion', 'get']
            unreleased.put(
// unreleased           No	: ['builder', 'logger', 'request', 'out', 'listener', 'factory', 'settings', 'sb', 'engine', 'result']
// put                  6	: ['add', 'elasticsearch', 'get', 'util', 'common', 'xpack', 'put', 'action', 'index', 'field']
                unreleasedVersion,
// unreleasedVersion    No	: ['key', 'settings', 'indexMetadata', 'indexBuilder', 'indexSettings', 'SETTING_NUMBER_OF_SHARDS', 'SETTING_NUMBER_OF_REPLICAS', 'name', 'index', 'entry']
                new UnreleasedVersionInfo(unreleasedVersion, getBranchFor(unreleasedVersion), getGradleProjectPathFor(unreleasedVersion))
// unreleasedVersion    No	: ['request', 'response', 'e', 'client', 'i', 'settings', 'parser', 'index', 'randomBoolean', 'in']
// unreleasedVersion    No	: ['request', 'response', 'e', 'client', 'i', 'settings', 'parser', 'index', 'randomBoolean', 'in']
// unreleasedVersion    No	: ['request', 'response', 'e', 'client', 'i', 'settings', 'parser', 'index', 'randomBoolean', 'in']
            );
        }
        this.unreleased = Collections.unmodifiableMap(unreleased);
// unreleased           No	: ['fieldName', 'size', 'format', 'name', 'indices', 'field', 'index', 'interval', 'params', 'id']
// unreleased           No	: ['map', 'in', 'metadata', 'headers', 'attributes', 'contexts', 'scripts', 'context', 'builder', 'messages']
    }

    private void assertNoOlderThanTwoMajors() {
        Set<Integer> majors = groupByMajor.keySet();
// majors               No	: ['notifications', 'values', 'ignoreErrorCodes', 'expected', 'visited', 'expectedIgnores', 'ignores', 'shardIds', 'keys', 'intSetting']
// groupByMajor         No	: ['client', 'in', 'expectThrows', 'mock', 'parser', 'randomBoolean', 'randomFrom', 'response', 'createParser', 'request']
// keySet               No	: ['elasticsearch', 'get', 'util', 'common', 'xpack', 'put', 'action', 'index', 'field', 'add']
        if (majors.size() != 2 && currentVersion.getMinor() != 0 && currentVersion.getRevision() != 0) {
// majors               No	: ['randomBoolean', 'token', 'in', 'request', 'i', 'value', 'parser', 'size', 'logger', 'exception']
// size                 No	: ['elasticsearch', 'get', 'util', 'common', 'xpack', 'put', 'action', 'index', 'field', 'add']
// currentVersion       No	: ['response', '_alt', 'typeArguments', 'recycler', 'argumentTypes', 'first', 'parameterTypes', 'version', 'values', 'randomBoolean']
// currentVersion       No	: ['iterations', 'flavor', 'response', 'handedness', 'format', 'address', 'b', 'bogousExcludesCount', 'tookInNanos', 'randomBoolean']
            throw new IllegalStateException("Expected exactly 2 majors in parsed versions but found: " + majors);
// majors               No	: ['type', 'operation', 'name', 'index', 'path', 'id', 'keyStore', 'result', 'state', 'url']
        }
    }

    private void assertCurrentVersionMatchesParsed(Version currentVersionProperty) {
// currentVersionProperty No	: ['version', 'indexCreatedVersion', 'indexVersion', 'indexVersionCreated', 'indexCreated', 'other', 'compareTo', 'version1', 'v', 'currentVersion']
        if (currentVersionProperty.equals(currentVersion) == false) {
// currentVersionProperty No	: ['randomBoolean', 'token', 'obj', 'listener', 'visitor', 'value', 'e', 'request', 'i', 'logger']
// equals               No	: ['elasticsearch', 'get', 'util', 'common', 'xpack', 'put', 'action', 'index', 'field', 'add']
// currentVersion       No	: ['that', 'currentFieldName', 'input', 'other', 'name', 'obj', 'index', 'id', 'type', 'o']
            throw new IllegalStateException(
                "Parsed versions latest version does not match the one configured in build properties. "
                    + "Parsed latest version is "
                    + currentVersion
// currentVersion       No	: ['name', 'state', 'token', 'stage', 'otherNode', 'type', 'request', 'key', 'm', 'n']
                    + " but the build has "
                    + currentVersionProperty
// currentVersionProperty No	: ['expectedVersion', 'name', 'jobId', 'token', 'type', 'fieldName', 'path', 'request', 'currentFieldName', 'localNode']
            );
        }
    }

    /**
      * Returns info about the unreleased version, or {@code null} if the version is released.
      */
    public UnreleasedVersionInfo unreleasedInfo(Version version) {
// unreleasedInfo       0	: ['unreleasedInfo', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'j', 'allocation']
// version              0	: ['version', 'indexCreatedVersion', 'indexVersion', 'indexVersionCreated', 'indexCreated', 'other', 'compareTo', 'version1', 'v', 'currentVersion']
        return unreleased.get(version);
// unreleased           No	: ['instance', 'version', 'startTransport', 'mapModel', 'model', 'STANDARD', 'ALL_VERSIONS', 'NAME', 'getToken', 'name']
// get                  2	: ['add', 'elasticsearch', 'get', 'util', 'common', 'xpack', 'put', 'action', 'index', 'field']
// version              No	: ['i', 'settings', 'index', 'key', 'name', 'bucket', 'fieldName', 'field', 'indexName', 'j']
    }

    public void forPreviousUnreleased(Consumer<UnreleasedVersionInfo> consumer) {
// consumer             No	: ['listener', 'metadata', 'PARSER', 'entry', 'map', 'buckets', 'future', 'params', 'fields', 'indices']
        List<UnreleasedVersionInfo> collect = getUnreleased().stream()
// collect              No	: ['listener', 'metadata', 'PARSER', 'entry', 'map', 'buckets', 'future', 'params', 'fields', 'indices']
// stream               No	: ['get', 'put', 'admin', 'size', 'field', 'indices', 'cluster', 'index', 'value', 'toString']
            .filter(version -> version.equals(currentVersion) == false)
// filter               1	: ['map', 'filter', 'collect', 'allMatch', 'sorted', 'limit', 'distinct', 'min', 'max', 'skip']
// version              No	: ['e', 'n', 's', 'r', 'entry', 't', 'c', 'p', 'f', 'v']
// version              0	: ['version', 'create', 'exec', 'client', 'builder', 'listener', 'p', 'r', 'fail', 's']
// equals               6	: ['compareTo', 'toString', 'luceneVersion', 'id', 'before', 'get', 'equals', 'size', 'major', 'minor']
// currentVersion       No	: ['that', 'entry', 'in', 'build', 'currentFieldName', 'input', 'other', 'name', 'obj', 'request']
            .map(version -> new UnreleasedVersionInfo(version, getBranchFor(version), getGradleProjectPathFor(version)))
// map                  1	: ['collect', 'map', 'filter', 'count', 'allMatch', 'sorted', 'test', 'start', 'client', 'all']
// version              No	: ['n', 'o', 'e', 'node', 'c', 'caps', 'imd', 'name', 'each', 'nodePath']
// version              No	: ['request', 'response', 'e', 'client', 'i', 'settings', 'parser', 'index', 'randomBoolean', 'in']
// version              No	: ['request', 'response', 'e', 'client', 'i', 'settings', 'parser', 'index', 'randomBoolean', 'in']
// version              No	: ['request', 'response', 'e', 'client', 'i', 'settings', 'parser', 'index', 'randomBoolean', 'in']
            .collect(Collectors.toList());
// collect              3	: ['get', 'build', 'put', 'collect', 'add', 'actionGet', 'subAggregation', 'input', 'map', 'order']

        collect.forEach(uvi -> consumer.accept(uvi));
// collect              No	: ['logger', 'listener', 'aggs', 'builder', 'settingsBuilder', 'request', 'metrics', 'patterns', 'threads', 'normalizer']
// uvi                  No	: ['e', 'p', 'i', 't', 'c', 'bucket', 'agg', 's', 'mapper', 'setting']
// consumer             No	: ['exec', 'client', 'builder', 'listener', 'p', 'r', 'fail', 's', 'e', 'parser']
// accept               0	: ['accept', 'current', 'get', 'reduce', 'elasticsearch', 'add', 'set', 'getResult', 'end']
// uvi                  No	: ['parser', 'v', 'm', 'state', 'value', 'namespace', 'events', 'historyUUIDs', 'token', 'authenticationToken']
    }

    private String getGradleProjectPathFor(Version version) {
// version              0	: ['version', 'indexCreatedVersion', 'indexVersion', 'indexVersionCreated', 'indexCreated', 'other', 'compareTo', 'version1', 'v', 'currentVersion']
        // We have Gradle projects set up to check out and build unreleased versions based on the our branching
        // conventions described in this classes javadoc
        if (version.equals(currentVersion)) {
// version              0	: ['version', 'useFilterForMultitermQueries', 'id', 'nodeName', 'appliedStateTrackersByNode', 'VERSION_PATTERN', 'randomBoolean', 'token', 'obj', 'listener']
// equals               5	: ['before', 'compareTo', 'id', 'isPresent', 'luceneVersion', 'equals', 'toString', 'size', 'get', 'elasticsearch']
// currentVersion       No	: ['entry', 'build', 'that', 'in', 'currentFieldName', 'input', 'other', 'name', 'obj', 'request']
            return ":distribution";
        }

        Map<Integer, List<Version>> releasedMajorGroupedByMinor = getReleasedMajorGroupedByMinor();
// releasedMajorGroupedByMinor No	: ['t', 'versions', 'loadedAnalyzers', 'entry', 'stableVersions', 'oldVersions', 'preBuiltAnalyzerEntry', 'majorVersions', 'byMinor', 'expectedLoadedAnalyzers']

        if (version.getRevision() == 0) {
// version              No	: ['randomBoolean', 'token', 'in', 'request', 'i', 'value', 'parser', 'size', 'logger', 'exception']
            List<Version> unreleasedStagedOrMinor = getUnreleased().stream().filter(v -> v.getRevision() == 0).collect(Collectors.toList());
// unreleasedStagedOrMinor No	: ['allVersions', 'lastMinor', 'versionsByMinCompat', 'released', 'unreleased', 'versions', 'listener', 'bwcVersions', 'authoritativeReleasedVersions', 'compatible']
// stream               No	: ['get', 'put', 'admin', 'size', 'field', 'indices', 'cluster', 'index', 'value', 'toString']
// filter               1	: ['map', 'filter', 'collect', 'allMatch', 'sorted', 'limit', 'distinct', 'min', 'max', 'skip']
// v                    9	: ['e', 'n', 's', 'r', 'entry', 't', 'c', 'p', 'f', 'v']
// v                    0	: ['v', 'defaultFailureResponseHeaders', 'quote', 'listener', 'client', 'exec', 'e', 'indices', 'executor', 'get']
// collect              No	: ['map', 'get', 'put', 'build', 'state', 'shards', 'getFields', 'primaryShard', 'elasticsearch']
            if (unreleasedStagedOrMinor.size() > 2) {
// unreleasedStagedOrMinor No	: ['randomBoolean', 'request', 'i', 'in', 'rarely', 'response', 'node', 'version', 'shard', 'hasByteOrderMarker']
// size                 No	: ['elasticsearch', 'get', 'util', 'common', 'xpack', 'put', 'action', 'index', 'field', 'add']
                if (unreleasedStagedOrMinor.get(unreleasedStagedOrMinor.size() - 2).equals(version)) {
// unreleasedStagedOrMinor No	: ['queryText', 'first', 'xsize', 'objects', 'processors', 'randomBoolean', 'token', 'obj', 'listener', 'visitor']
// get                  1	: ['elasticsearch', 'get', 'util', 'common', 'xpack', 'put', 'action', 'index', 'field', 'add']
// unreleasedStagedOrMinor No	: ['i', 'settings', 'index', 'key', 'name', 'bucket', 'fieldName', 'field', 'indexName', 'j']
// size                 No	: ['elasticsearch', 'get', 'util', 'common', 'xpack', 'put', 'action', 'index', 'field', 'add']
// equals               No	: ['coordinate', 'put', 'build', 'get', 'field', 'getKey', 'numberOfReplicas', 'elasticsearch', 'corrections']
// version              No	: ['currentFieldName', 'that', 'index', 'name', 'other', 'request', 'id', 'shardId', 'COMMA', 'current']
                    return ":distribution:bwc:minor";
                } else {
                    return ":distribution:bwc:staged";
                }
            } else {
                return ":distribution:bwc:minor";
            }
        } else {
            if (releasedMajorGroupedByMinor.getOrDefault(version.getMinor(), emptyList()).contains(version)) {
// releasedMajorGroupedByMinor No	: ['randomBoolean', 'token', 'logger', 'value', 'result', 'fieldName', 'opType', 'response', 'config', 'line']
// version              No	: ['bucket', 'e', 'field', 'index', 'attr', 'a', 'nodeId', 'address', 'hash', 'PREFACE']
// emptyList            No	: ['equalTo', 'is', 'nullValue', 'randomBoolean', 'request', 'any', 'e', 'instance', 'response', 'emptyMap']
// version              No	: ['name', 'actionName', 'v', 'aPathPart', 'jobId', 'datafeedId', 'request', 'job', 'bitsetKey', 'temporaryFileName']
                return ":distribution:bwc:bugfix";
            } else {
                return ":distribution:bwc:maintenance";
            }
        }
    }

    private String getBranchFor(Version version) {
// version              0	: ['version', 'indexCreatedVersion', 'indexVersion', 'indexVersionCreated', 'indexCreated', 'other', 'compareTo', 'version1', 'v', 'currentVersion']
        // based on the rules described in this classes javadoc, figure out the branch on which an unreleased version
        // lives.
        // We do this based on the Gradle project path because there's a direct correlation, so we dont have to duplicate
        // the logic from there
        switch (getGradleProjectPathFor(version)) {
// version              No	: ['request', 'response', 'e', 'client', 'i', 'settings', 'parser', 'index', 'randomBoolean', 'in']
            case ":distribution":
                return "master";
            case ":distribution:bwc:minor":
                // The .x branch will always point to the latest minor (for that major), so a "minor" project will be on the .x branch
                // unless there is more recent (higher) minor.
                final Version latestInMajor = getLatestVersionByKey(groupByMajor, version.getMajor());
// latestInMajor        No	: ['version', 'nodeVersion', 'esVersion', 'masterVersion', 'CURRENT', 'indexVersionCreated', 'indexCreatedVersion', 'minVersion', 'minNodeVersion', 'V_6_0_0']
// groupByMajor         No	: ['request', 'response', 'e', 'client', 'i', 'settings', 'parser', 'index', 'randomBoolean', 'in']
// version              No	: ['equalTo', 'is', 'e', 'request', 'exec', 'listener', 'parser', 'that', 'other', 'randomBoolean']
                if (latestInMajor.getMinor() == version.getMinor()) {
// latestInMajor        No	: ['randomBoolean', 'request', 'i', 'in', 'rarely', 'response', 'node', 'version', 'shard', 'hasByteOrderMarker']
// version              No	: ['Type', 'that', 'State', 'e', 'response', 'state', 'shardId', 'responses', 'o2', 'maxValue']
                    return version.getMajor() + ".x";
// version              No	: ['resolution', 'allocation', 'decision', 'currentState', 'plan', 'sourceResolution', 'defaultValue', 'array', 'emptyList', 'clusterState']
                } else {
                    return version.getMajor() + "." + version.getMinor();
// version              No	: ['currentState', 'allocation', 'value', 'query', 'values', 'p', 'validate', 'builder', 'format', 'missing']
// version              No	: ['name', 'member', 'AT', 'type', 'v', 'level', 'source', 'p', 'feature', 'caller']
                }
            case ":distribution:bwc:staged":
            case ":distribution:bwc:maintenance":
            case ":distribution:bwc:bugfix":
                return version.getMajor() + "." + version.getMinor();
// version              No	: ['s', 'DATA', 'NO_NODE_FOUND', 'majorDeviceNumber', 'minorDeviceNumber', 'TIMEOUT', 'SECURITY', 'response', 'Type', 'random']
// version              No	: ['name', 'member', 'AT', 'type', 'v', 'level', 'source', 'p', 'feature', 'caller']
            default:
                throw new IllegalStateException("Unexpected Gradle project name");
        }
    }

    public List<Version> getUnreleased() {
        List<Version> unreleased = new ArrayList<>();
// unreleased           4	: ['allVersions', 'lastMinor', 'versionsByMinCompat', 'released', 'unreleased', 'versions', 'listener', 'bwcVersions', 'authoritativeReleasedVersions', 'compatible']
        // The current version is being worked, is always unreleased
        unreleased.add(currentVersion);
// unreleased           No	: ['config', 'params', 'map', 'expected', 'realm', 'action', 'client', 'document', 'scripts', 'metadata']
// add                  0	: ['add', 'elasticsearch', 'get', 'util', 'common', 'xpack', 'put', 'action', 'index', 'field']
// currentVersion       No	: ['version', 'client', 'newNode', 'document', 'value', 'createIndexRequest', 'node', 'request', 'bucket', 'indexRequest']

        // the tip of the previous major is unreleased for sure, be it a minor or a bugfix
        final Version latestOfPreviousMajor = getLatestVersionByKey(this.groupByMajor, currentVersion.getMajor() - 1);
// latestOfPreviousMajor No	: ['version', 'minIndexCompatibilityVersion', 'minNodeVersion', 'maxNodeVersion', 'streamVersion', 'connectionVersion', 'esVersion', 'masterVersion', 'indexVersionCreated', 'minVersion']
// groupByMajor         No	: ['jobId', 'settings', 'id', 'name', 'field', 'value', 'fieldName', 'metadata', 'description', 'timestamp']
// currentVersion       No	: ['equalTo', 'is', 'e', 'request', 'exec', 'listener', 'parser', 'that', 'other', 'randomBoolean']
        unreleased.add(latestOfPreviousMajor);
// unreleased           No	: ['logger', 'sameKeyedBuckets', 'mutated', 'query', 'array', 'name', 'globalCheckpoint', 'builder', 't', 'LOGGER']
// add                  0	: ['add', 'elasticsearch', 'get', 'util', 'common', 'xpack', 'put', 'action', 'index', 'field']
// latestOfPreviousMajor No	: ['version', 'client', 'newNode', 'document', 'value', 'createIndexRequest', 'node', 'request', 'bucket', 'indexRequest']
        if (latestOfPreviousMajor.getRevision() == 0) {
// latestOfPreviousMajor No	: ['randomBoolean', 'in', 'token', 'out', 'i', 'request', 'value', 'type', 'response', 'fieldType']
            // if the previous major is a x.y.0 release, then the tip of the minor before that (y-1) is also unreleased
            final Version previousMinor = getLatestInMinor(latestOfPreviousMajor.getMajor(), latestOfPreviousMajor.getMinor() - 1);
// previousMinor        No	: ['version', 'oldVersion', 'nodeVersion', 'tokenVersion', 'minNodeVersion', 'indexVersion', 'compatibilityVersion', 'unreleased', 'known', 'candidateVersion']
// latestOfPreviousMajor No	: ['request', 'response', 'e', 'client', 'i', 'settings', 'parser', 'index', 'randomBoolean', 'in']
// latestOfPreviousMajor No	: ['equalTo', 'is', 'nullValue', 'randomBoolean', 'request', 'any', 'e', 'instance', 'response', 'get']
            if (previousMinor != null) {
// previousMinor        No	: ['_la', 'randomBoolean', 'values', 'i', 'fromPage', 'start', 'topDocs', 'docDeleted', 'name', 'counter']
                unreleased.add(previousMinor);
// unreleased           No	: ['builder', 'out', 'logger', 'params', 'listener', 'request', 'operation', 'sb', 'result', 'factory']
// add                  0	: ['add', 'elasticsearch', 'get', 'util', 'common', 'xpack', 'put', 'action', 'index', 'field']
// previousMinor        No	: ['version', 'client', 'newNode', 'document', 'value', 'createIndexRequest', 'node', 'request', 'bucket', 'indexRequest']
            }
        }

        final Map<Integer, List<Version>> groupByMinor = getReleasedMajorGroupedByMinor();
// groupByMinor         No	: ['t', 'versions', 'loadedAnalyzers', 'entry', 'stableVersions', 'oldVersions', 'preBuiltAnalyzerEntry', 'majorVersions', 'byMinor', 'expectedLoadedAnalyzers']
        int greatestMinor = groupByMinor.keySet().stream().max(Integer::compareTo).orElse(0);
// greatestMinor        No	: ['size', 'numDocs', 'i', 'count', 'index', 'totalShards', 'max', 'numShards', 'numValues', 'sigDigits']
// groupByMinor         No	: ['client', 'in', 'expectThrows', 'mock', 'parser', 'randomBoolean', 'randomFrom', 'response', 'createParser', 'request']
// keySet               No	: ['elasticsearch', 'get', 'util', 'common', 'xpack', 'put', 'action', 'index', 'field', 'add']
// stream               0	: ['stream', 'iterator', 'size', 'toString', 'removeIf', 'get', 'containsAll', 'equals', 'remove', 'isEmpty']
// max                  8	: ['map', 'filter', 'collect', 'allMatch', 'sorted', 'limit', 'distinct', 'min', 'max', 'skip']
// compareTo            0	: ['compareTo', 'toString', 'intValue', 'sum', 'max', 'doubleValue', 'longValue', 'fromXContent', 'onFailure', 'get']
// orElse               1	: ['get', 'orElse', 'put', 'build', 'field', 'elasticsearch', 'size', 'admin', 'indices', 'value']

        // the last bugfix for this minor series is always unreleased
        unreleased.add(getLatestVersionByKey(groupByMinor, greatestMinor));
// unreleased           No	: ['logger', 'client', 'internalCluster', 'searchRequest', 'builder', 'shard', 'forgedAssertion', 'clusterState', 'i', 'results']
// add                  0	: ['add', 'elasticsearch', 'get', 'util', 'common', 'xpack', 'put', 'action', 'index', 'field']
// groupByMinor         No	: ['request', 'response', 'e', 'client', 'i', 'settings', 'parser', 'index', 'randomBoolean', 'in']
// greatestMinor        No	: ['equalTo', 'is', 'e', 'request', 'exec', 'listener', 'parser', 'that', 'other', 'randomBoolean']

        if (groupByMinor.get(greatestMinor).size() == 1) {
// groupByMinor         No	: ['randomBoolean', 'request', 'i', 'in', 'rarely', 'response', 'node', 'version', 'shard', 'hasByteOrderMarker']
// get                  1	: ['elasticsearch', 'get', 'util', 'common', 'xpack', 'put', 'action', 'index', 'field', 'add']
// greatestMinor        No	: ['i', 'settings', 'index', 'key', 'name', 'bucket', 'fieldName', 'field', 'indexName', 'j']
// size                 4	: ['get', 'put', 'build', 'field', 'size', 'admin', 'indices', 'value', 'toString', 'execute']
            // we found an unreleased minor
            unreleased.add(getLatestVersionByKey(groupByMinor, greatestMinor - 1));
// unreleased           No	: ['builder', 'listener', 'message', 'buf', 'terms', 'geometry', 'publishPort', 'hitName', 'result', 'index']
// add                  0	: ['add', 'elasticsearch', 'get', 'util', 'common', 'xpack', 'put', 'action', 'index', 'field']
// groupByMinor         No	: ['request', 'response', 'e', 'client', 'i', 'settings', 'parser', 'index', 'randomBoolean', 'in']
// greatestMinor        No	: ['equalTo', 'is', 'e', 'request', 'exec', 'listener', 'parser', 'that', 'other', 'randomBoolean']
            if (groupByMinor.getOrDefault(greatestMinor - 1, emptyList()).size() == 1) {
// groupByMinor         No	: ['randomBoolean', 'request', 'i', 'in', 'rarely', 'response', 'node', 'version', 'shard', 'hasByteOrderMarker']
// greatestMinor        No	: ['bucket', 'e', 'field', 'index', 'attr', 'a', 'nodeId', 'address', 'hash', 'PREFACE']
// emptyList            No	: ['exec', 'maxValue', 'randomBoolean', 'maxValues', 'sigDigits', 'allocation', 'settings', 'reader', 'input', 'decl']
// size                 1	: ['build', 'size', 'get', 'add', 'when', 'asMap', 'scoreDocs', 'from', 'allowRestrictedIndices', 'deniedFields']
                // we found that the previous minor is staged but not yet released
                // in this case, the minor before that has a bugfix, should there be such a minor
                if (greatestMinor >= 2) {
// greatestMinor        No	: ['i', 'states', 'logger', 'isLastPos', 'type', 'shard', 'keys', 'jobId', 'nodeIds', 'kv']
                    unreleased.add(getLatestVersionByKey(groupByMinor, greatestMinor - 2));
// unreleased           No	: ['latch', 'fieldName', 'h', 'major', 'positionGaps', 'builder', 'fail', 'logger', 'document', 'methodName']
// add                  0	: ['add', 'elasticsearch', 'get', 'util', 'common', 'xpack', 'put', 'action', 'index', 'field']
// groupByMinor         No	: ['request', 'response', 'e', 'client', 'i', 'settings', 'parser', 'index', 'randomBoolean', 'in']
// greatestMinor        No	: ['equalTo', 'is', 'e', 'request', 'exec', 'listener', 'parser', 'that', 'other', 'randomBoolean']
                }
            }
        }

        return unmodifiableList(unreleased.stream().sorted().distinct().collect(Collectors.toList()));
// unreleased           No	: ['builder', 'asList', 'buckets', 'snapshotList', 'result', 'in', 'settings', 'results', 'entries', 'request']
// stream               No	: ['add', 'elasticsearch', 'get', 'util', 'common', 'xpack', 'put', 'action', 'index', 'field']
// sorted               4	: ['map', 'filter', 'collect', 'allMatch', 'sorted', 'limit', 'distinct', 'min', 'max', 'skip']
// distinct             No	: ['collect', 'get', 'put', 'admin', 'size', 'field', 'indices', 'cluster', 'index', 'value']
// collect              0	: ['collect', 'count', 'map', 'limit', 'get', 'filter', 'put', 'admin', 'size', 'indices']
    }

    private Version getLatestInMinor(int major, int minor) {
// major                No	: ['i', 'j', 'doc', 'size', 'index', 'nodeOrdinal', 'docId', 'columnIndex', 'target', 'runs']
// minor                0	: ['minor', 'length', 'offset', 'invokingState', 'size', 'len', 'shardId', 'value', 'bucketSize', 'failedShards']
        return groupByMajor.get(major).stream().filter(v -> v.getMinor() == minor).max(Version::compareTo).orElse(null);
// groupByMajor         No	: ['NAME', 'getToken', 'name', 'PARSER', 'restHighLevelClient', 'in', 'state', 'delegate', 'id', 'field']
// get                  1	: ['elasticsearch', 'get', 'util', 'common', 'xpack', 'put', 'action', 'index', 'field', 'add']
// major                No	: ['i', 'settings', 'index', 'key', 'name', 'bucket', 'fieldName', 'field', 'indexName', 'j']
// stream               No	: ['get', 'put', 'build', 'field', 'size', 'admin', 'indices', 'value', 'toString', 'execute']
// filter               1	: ['map', 'filter', 'collect', 'allMatch', 'sorted', 'limit', 'distinct', 'min', 'max', 'skip']
// v                    9	: ['e', 'n', 's', 'r', 'entry', 't', 'c', 'p', 'f', 'v']
// v                    0	: ['v', 'defaultFailureResponseHeaders', 'quote', 'listener', 'client', 'exec', 'e', 'indices', 'executor', 'get']
// minor                No	: ['Type', 'that', 'State', 'e', 'response', 'state', 'shardId', 'responses', 'o2', 'maxValue']
// max                  No	: ['get', 'put', 'build', 'field', 'size', 'admin', 'indices', 'value', 'toString', 'execute']
// compareTo            0	: ['compareTo', 'minimumIndexCompatibilityVersion', 'fromXContent', 'onFailure', 'get', 'parse', 'getName', 'build', 'set', 'add']
// orElse               0	: ['orElse', 'get', 'put', 'build', 'field', 'elasticsearch', 'size', 'admin', 'indices', 'value']
    }

    private Version getLatestVersionByKey(Map<Integer, List<Version>> groupByMajor, int key) {
// groupByMajor         No	: ['t', 'versions', 'loadedAnalyzers', 'entry', 'stableVersions', 'oldVersions', 'preBuiltAnalyzerEntry', 'majorVersions', 'byMinor', 'expectedLoadedAnalyzers']
// key                  No	: ['length', 'offset', 'invokingState', 'size', 'len', 'numDocs', 'shardId', 'value', 'bucketSize', 'failedShards']
        return groupByMajor.getOrDefault(key, emptyList())
// groupByMajor         No	: ['map', 'clusterState', 'key', 'get', 'binder', 'delegate', 'settings', 'params', 'percent', 'NAME']
// key                  No	: ['bucket', 'e', 'field', 'index', 'attr', 'a', 'nodeId', 'address', 'hash', 'PREFACE']
// emptyList            No	: ['defaultValue', 'value', 'nextStepKey', 'nextKey', 'from', 'other', 'valueSupplier', 'equalTo', 'oldValue', 's']
            .stream()
// stream               No	: ['build', 'size', 'get', 'add', 'when', 'asMap', 'scoreDocs', 'from', 'allowRestrictedIndices', 'deniedFields']
            .max(Version::compareTo)
// max                  8	: ['map', 'filter', 'collect', 'allMatch', 'sorted', 'limit', 'distinct', 'min', 'max', 'skip']
// compareTo            0	: ['compareTo', 'minimumIndexCompatibilityVersion', 'fromXContent', 'onFailure', 'get', 'parse', 'getName', 'build', 'set', 'add']
            .orElseThrow(() -> new IllegalStateException("Unexpected number of versions in collection"));
    }

    private Map<Integer, List<Version>> getReleasedMajorGroupedByMinor() {
        List<Version> currentMajorVersions = groupByMajor.get(currentVersion.getMajor());
// currentMajorVersions No	: ['allVersions', 'lastMinor', 'versionsByMinCompat', 'released', 'unreleased', 'versions', 'listener', 'bwcVersions', 'authoritativeReleasedVersions', 'compatible']
// groupByMajor         No	: ['client', 'in', 'expectThrows', 'mock', 'parser', 'randomBoolean', 'randomFrom', 'response', 'createParser', 'request']
// get                  1	: ['elasticsearch', 'get', 'util', 'common', 'xpack', 'put', 'action', 'index', 'field', 'add']
// currentVersion       No	: ['i', 'settings', 'index', 'key', 'name', 'bucket', 'fieldName', 'field', 'indexName', 'j']
        List<Version> previousMajorVersions = groupByMajor.get(currentVersion.getMajor() - 1);
// previousMajorVersions No	: ['released', 'unreleased', 'authoritativeReleasedVersions', 'versions', 'allVersions', 'unreleasedVersions', 'listener', 'metadata', 'PARSER', 'get']
// groupByMajor         No	: ['client', 'in', 'expectThrows', 'mock', 'parser', 'randomBoolean', 'randomFrom', 'response', 'createParser', 'request']
// get                  1	: ['elasticsearch', 'get', 'util', 'common', 'xpack', 'put', 'action', 'index', 'field', 'add']
// currentVersion       No	: ['i', 'settings', 'index', 'key', 'name', 'bucket', 'fieldName', 'field', 'indexName', 'j']

        final Map<Integer, List<Version>> groupByMinor;
// groupByMinor         No	: ['t', 'versions', 'loadedAnalyzers', 'entry', 'stableVersions', 'oldVersions', 'preBuiltAnalyzerEntry', 'majorVersions', 'byMinor', 'expectedLoadedAnalyzers']
        if (currentMajorVersions.size() == 1) {
// currentMajorVersions No	: ['randomBoolean', 'o', 'token', 'in', 'request', 'i', 'value', 'obj', 'out', 'type']
// size                 No	: ['elasticsearch', 'get', 'util', 'common', 'xpack', 'put', 'action', 'index', 'field', 'add']
            // Current is an unreleased major: x.0.0 so we have to look for other unreleased versions in the previous major
            groupByMinor = previousMajorVersions.stream().collect(Collectors.groupingBy(Version::getMinor, Collectors.toList()));
// groupByMinor         No	: ['builder', 'listener', 'message', 'buf', 'terms', 'geometry', 'publishPort', 'hitName', 'result', 'index']
// previousMajorVersions No	: ['client', 'in', 'expectThrows', 'mock', 'parser', 'randomBoolean', 'randomFrom', 'response', 'createParser', 'request']
// stream               No	: ['elasticsearch', 'get', 'util', 'common', 'xpack', 'put', 'action', 'index', 'field', 'add']
// collect              2	: ['map', 'filter', 'collect', 'allMatch', 'sorted', 'limit', 'distinct', 'min', 'max', 'skip']
        } else {
            groupByMinor = currentMajorVersions.stream().collect(Collectors.groupingBy(Version::getMinor, Collectors.toList()));
// groupByMinor         No	: ['listener', 'logger', 'builder', 'out', 'fail', 'metadata', 'parser', 'request', 'sb', 'value']
// currentMajorVersions No	: ['client', 'in', 'expectThrows', 'mock', 'parser', 'randomBoolean', 'randomFrom', 'response', 'createParser', 'request']
// stream               No	: ['elasticsearch', 'get', 'util', 'common', 'xpack', 'put', 'action', 'index', 'field', 'add']
// collect              2	: ['map', 'filter', 'collect', 'allMatch', 'sorted', 'limit', 'distinct', 'min', 'max', 'skip']
        }
        return groupByMinor;
// groupByMinor         No	: ['builder', 'request', 'list', 'result', 'channel', 'factory', 'query', 'value', 'map', 'allocation']
    }

    public void compareToAuthoritative(List<Version> authoritativeReleasedVersions) {
// authoritativeReleasedVersions 6	: ['versions', 'released', 'unreleased', 'listener', 'bwcVersions', 'allVersions', 'authoritativeReleasedVersions', 'unreleasedVersions', 'ALL_VERSIONS', 'compatible']
        Set<Version> notReallyReleased = new HashSet<>(getReleased());
// notReallyReleased    No	: ['nodeVersions', 'versions', 'unreleasedVersions', 'releasedVersions', 'listener', 'subset', 'testNodeVersions', 'released', 'unreleased', 'get']
        notReallyReleased.removeAll(authoritativeReleasedVersions);
// notReallyReleased    No	: ['builder', 'table', 'logger', 'request', 'client', 'out', 'verify', 'e', 'listener', 'settings']
// authoritativeReleasedVersions No	: ['predicate', 'response', 'container', 'dataNodes', 'masterNodes', 'excluded', 'ingestNodes', 'masterCopy', 'overridden', 'keysInRequest']
        if (notReallyReleased.isEmpty() == false) {
// notReallyReleased    No	: ['randomBoolean', 'in', 'token', 'out', 'i', 'request', 'value', 'type', 'response', 'fieldType']
// isEmpty              No	: ['elasticsearch', 'get', 'util', 'common', 'xpack', 'put', 'action', 'index', 'field', 'add']
            throw new IllegalStateException(
                "out-of-date released versions"
                    + "\nFollowing versions are not really released, but the build thinks they are: "
                    + notReallyReleased
// notReallyReleased    No	: ['name', 'state', 'token', 'stage', 'otherNode', 'type', 'request', 'key', 'm', 'n']
            );
        }

        Set<Version> incorrectlyConsideredUnreleased = new HashSet<>(authoritativeReleasedVersions);
// incorrectlyConsideredUnreleased No	: ['versions', 'nodeVersions', 'unreleasedVersions', 'releasedVersions', 'listener', 'subset', 'testNodeVersions', 'released', 'unreleased', 'get']
// authoritativeReleasedVersions No	: ['response', 'instance', 'size', 'META_FIELDS', 'nodes', 'asList', 'searchResponse', 'nodeIds', 'clusters', 'fields']
        incorrectlyConsideredUnreleased.retainAll(getUnreleased());
// incorrectlyConsideredUnreleased No	: ['builder', 'out', 'logger', 'when', 'client', 'request', 'PARSER', 'verify', 'parser', 'listener']
        if (incorrectlyConsideredUnreleased.isEmpty() == false) {
// incorrectlyConsideredUnreleased No	: ['randomBoolean', 'request', 'i', 'in', 'rarely', 'response', 'node', 'version', 'shard', 'hasByteOrderMarker']
// isEmpty              No	: ['elasticsearch', 'get', 'util', 'common', 'xpack', 'put', 'action', 'index', 'field', 'add']
            throw new IllegalStateException(
                "out-of-date released versions"
                    + "\nBuild considers versions unreleased, "
                    + "but they are released according to an authoritative source: "
                    + incorrectlyConsideredUnreleased
// incorrectlyConsideredUnreleased No	: ['localNode', 'DATE_TIME_FORMATTER', 'name', 'allocationId', 'policy', 'DATE_FIELD_NAME', 'type', 'exception', 'section', 'value']
                    + "\nThe next versions probably needs to be added to Version.java (CURRENT doesn't count)."
            );
        }
    }

    private List<Version> getReleased() {
        List<Version> unreleased = getUnreleased();
// unreleased           4	: ['allVersions', 'lastMinor', 'versionsByMinCompat', 'released', 'unreleased', 'versions', 'listener', 'bwcVersions', 'authoritativeReleasedVersions', 'compatible']
        return groupByMajor.values()
// groupByMajor         No	: ['builder', 'result', 'channel', 'in', 'table', 'response', 'state', 'values', 'testName', 'delegate']
// values               No	: ['elasticsearch', 'get', 'util', 'common', 'xpack', 'put', 'action', 'index', 'field', 'add']
            .stream()
// stream               0	: ['stream', 'iterator', 'length', 'size', 'get', 'spliterator', 'containsAll', 'removeIf', 'isEmpty', 'values']
            .flatMap(Collection::stream)
// stream               0	: ['stream', 'isEmpty', 'fromXContent', 'onFailure', 'get', 'parse', 'getName', 'build', 'set', 'add']
            .filter(each -> unreleased.contains(each) == false)
// filter               1	: ['collect', 'filter', 'map', 'equals', 'iterator', 'get', 'orElse', 'distinct', 'stream', 'put']
// each                 No	: ['s', 'f', 'e', 'n', 'r', 'termQuery', 'entry', 't', 'p', 'request']
// unreleased           No	: ['each', 'templates', 'missingClasses', 'violationsClasses', 'problematic', 'installation', 'exec', 'client', 'builder', 'listener']
// each                 No	: ['e', 'name', 'key', 'i', 'field', 's', 'type', 'node', 'index', 'entry']
            .collect(Collectors.toList());
// collect              0	: ['collect', 'map', 'filter', 'count', 'allMatch', 'sorted', 'test', 'start', 'client', 'all']
    }

    public List<Version> getIndexCompatible() {
        return unmodifiableList(
            Stream.concat(groupByMajor.get(currentVersion.getMajor() - 1).stream(), groupByMajor.get(currentVersion.getMajor()).stream())
// concat               0	: ['concat', 'elasticsearch', 'get', 'empty', 'util', 'common', 'xpack', 'put', 'action', 'field']
// groupByMajor         No	: ['translog', 'discoveryNodes', 'testLoggingMap', 'errors', 'readers', 'request', 'entries', 'retentionLeases', 'map', 'settings']
// get                  1	: ['elasticsearch', 'get', 'util', 'common', 'xpack', 'put', 'action', 'index', 'field', 'add']
// currentVersion       No	: ['i', 'settings', 'index', 'key', 'name', 'bucket', 'fieldName', 'field', 'indexName', 'j']
// stream               No	: ['build', 'settings', 'size', 'trim', 'process', 'key', 'seqNo', 'watchId', 'filter', 'docCount']
// groupByMajor         No	: ['pluginsService', 'invalidSettings', 'newNodes', 'searchModule', 'count', 'secureSettings', 'violationsExcludes', 'namedXContentEntries', 'versionMap', 'baseConfig']
// get                  1	: ['elasticsearch', 'get', 'util', 'common', 'xpack', 'put', 'action', 'index', 'field', 'add']
// currentVersion       No	: ['i', 'settings', 'index', 'key', 'name', 'bucket', 'fieldName', 'field', 'indexName', 'j']
// stream               No	: ['build', 'get', 'put', 'field', 'add', 'collect', 'execute', 'append', 'filter', 'doubleValue']
                .collect(Collectors.toList())
// collect              0	: ['collect', 'filter', 'map', 'build', 'put', 'sorted', 'count', 'distinct', 'reduce', 'min']
        );
    }

    public List<Version> getWireCompatible() {
        List<Version> wireCompat = new ArrayList<>();
// wireCompat           No	: ['allVersions', 'lastMinor', 'versionsByMinCompat', 'released', 'unreleased', 'versions', 'listener', 'bwcVersions', 'authoritativeReleasedVersions', 'compatible']

        List<Version> prevMajors = groupByMajor.get(currentVersion.getMajor() - 1);
// prevMajors           No	: ['released', 'unreleased', 'authoritativeReleasedVersions', 'versions', 'allVersions', 'unreleasedVersions', 'listener', 'metadata', 'PARSER', 'get']
// groupByMajor         No	: ['client', 'in', 'expectThrows', 'mock', 'parser', 'randomBoolean', 'randomFrom', 'response', 'createParser', 'request']
// get                  1	: ['elasticsearch', 'get', 'util', 'common', 'xpack', 'put', 'action', 'index', 'field', 'add']
// currentVersion       No	: ['i', 'settings', 'index', 'key', 'name', 'bucket', 'fieldName', 'field', 'indexName', 'j']
        int minor = prevMajors.get(prevMajors.size() - 1).getMinor();
// minor                No	: ['max', 'value', 'read', 'addedBucketsRight', 'expectedResults', 'childDocId', 'size', 'totalShards', 'j', 'nodeIndex']
// prevMajors           No	: ['minor', 'matcher', 'parts', 'salt', 'v2Replacement', 'client', 'in', 'expectThrows', 'mock', 'parser']
// get                  1	: ['elasticsearch', 'get', 'util', 'common', 'xpack', 'put', 'action', 'index', 'field', 'add']
// prevMajors           No	: ['i', 'settings', 'index', 'key', 'name', 'bucket', 'fieldName', 'field', 'indexName', 'j']
// size                 No	: ['elasticsearch', 'get', 'util', 'common', 'xpack', 'put', 'action', 'index', 'field', 'add']
        for (int i = prevMajors.size() - 1; i > 0 && prevMajors.get(i).getMinor() == minor; i--) {
// i                    0	: ['i', 'j', 'index', 'k', 'doc', 'docId', 'iter', 't', 'op', 'id']
// prevMajors           No	: ['numPages', 'ordered', 'from', 'numTag1Docs', 'nodes', 'numberOfEntries', 'fromPage', 'offset', 'docs', 'off']
// size                 No	: ['elasticsearch', 'get', 'util', 'common', 'xpack', 'put', 'action', 'index', 'field', 'add']
// i                    0	: ['i', 'firstBatchNumDocsPerShard', 'bucket', 'builder', 'info', 'terms', 'selector', 'offsetIndex', 'endArrayPosition', 'classAnnotations']
// prevMajors           No	: ['randomBoolean', 'previous', 'i', 'rarely', 'perNodeLock', 'len', 'output', 'data', 'tookInNanos', 'get']
// get                  1	: ['elasticsearch', 'get', 'util', 'common', 'xpack', 'put', 'action', 'index', 'field', 'add']
// i                    0	: ['i', 'settings', 'index', 'key', 'name', 'bucket', 'fieldName', 'field', 'indexName', 'j']
// minor                No	: ['Type', 'that', 'State', 'e', 'response', 'state', 'shardId', 'responses', 'o2', 'maxValue']
// i                    1	: ['major', 'i', 'builder', 'out', 'logger', 'client', 'request', 'when', 'PARSER', 'verify']
            wireCompat.add(prevMajors.get(i));
// wireCompat           No	: ['list', 'metadata', 'indexRequestBuilders', 'request', 'result', 'index', 'source', 'entries', 'exceptions', 'x']
// add                  9	: ['elasticsearch', 'get', 'util', 'common', 'xpack', 'put', 'action', 'index', 'field', 'add']
// prevMajors           No	: ['client', 'newNode', 'document', 'value', 'createIndexRequest', 'node', 'request', 'bucket', 'indexRequest', 'parser']
// get                  1	: ['elasticsearch', 'get', 'util', 'common', 'xpack', 'put', 'action', 'index', 'field', 'add']
// i                    0	: ['i', 'settings', 'index', 'key', 'name', 'bucket', 'fieldName', 'field', 'indexName', 'j']
        }
        wireCompat.addAll(groupByMajor.get(currentVersion.getMajor()));
// wireCompat           No	: ['array', 'now', 'builder', 'logger', 'searchResponse', 'clusterState', 'sb', 'size', 'document', 'queryBuilder']
// groupByMajor         No	: ['asList', 'p', 'indexDoc', 'response', 'other', 'searchModule', 'boolQueryBuilder', 'tasks', 'paths', 'aliases']
// get                  1	: ['elasticsearch', 'get', 'util', 'common', 'xpack', 'put', 'action', 'index', 'field', 'add']
// currentVersion       No	: ['i', 'settings', 'index', 'key', 'name', 'bucket', 'fieldName', 'field', 'indexName', 'j']
        wireCompat.sort(Version::compareTo);
// wireCompat           No	: ['builder', 'logger', 'request', 'client', 'builders', 'analysisConfig', 'table', 'verify', 'fail', 'context']
// sort                 No	: ['elasticsearch', 'get', 'util', 'common', 'xpack', 'put', 'action', 'index', 'field', 'add']
// compareTo            0	: ['compareTo', 'minimumIndexCompatibilityVersion', 'fromXContent', 'onFailure', 'get', 'parse', 'getName', 'build', 'set', 'add']

        return unmodifiableList(wireCompat);
// wireCompat           No	: ['snapshotList', 'result', 'asList', 'buckets', 'builder', 'in', 'settings', 'results', 'entries', 'request']
    }

    public List<Version> getUnreleasedIndexCompatible() {
        List<Version> unreleasedIndexCompatible = new ArrayList<>(getIndexCompatible());
// unreleasedIndexCompatible No	: ['allVersions', 'lastMinor', 'versionsByMinCompat', 'released', 'unreleased', 'versions', 'listener', 'bwcVersions', 'authoritativeReleasedVersions', 'compatible']
        unreleasedIndexCompatible.retainAll(getUnreleased());
// unreleasedIndexCompatible No	: ['builder', 'table', 'logger', 'request', 'client', 'out', 'verify', 'e', 'listener', 'settings']
        return unmodifiableList(unreleasedIndexCompatible);
// unreleasedIndexCompatible No	: ['snapshotList', 'result', 'asList', 'buckets', 'builder', 'in', 'settings', 'results', 'entries', 'request']
    }

    public List<Version> getUnreleasedWireCompatible() {
        List<Version> unreleasedWireCompatible = new ArrayList<>(getWireCompatible());
// unreleasedWireCompatible No	: ['allVersions', 'lastMinor', 'versionsByMinCompat', 'released', 'unreleased', 'versions', 'listener', 'bwcVersions', 'authoritativeReleasedVersions', 'compatible']
        unreleasedWireCompatible.retainAll(getUnreleased());
// unreleasedWireCompatible No	: ['builder', 'table', 'logger', 'request', 'client', 'out', 'verify', 'e', 'listener', 'settings']
        return unmodifiableList(unreleasedWireCompatible);
// unreleasedWireCompatible No	: ['snapshotList', 'result', 'asList', 'buckets', 'builder', 'in', 'settings', 'results', 'entries', 'request']
    }

}
