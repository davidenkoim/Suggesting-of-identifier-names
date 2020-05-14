// Path id: 18
// Path to file: C:\Users\Igor\IdeaProjects\SLP-Core-0.3\java-projects\elasticsearch-master\buildSrc\src\main\java\org\elasticsearch\gradle\ExportElasticsearchBuildResourcesTask.java
// Number of identifiers: 103	Accuracy: 53.40%	MRR: 59.95%
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

import org.gradle.api.DefaultTask;
// org                  0	: ['org', 'java', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'fixture']
// gradle               1	: ['elasticsearch', 'gradle', 'get', 'util', 'common', 'xpack', 'joda', 'xml', 'yaml']
// api                  0	: ['api', 'testkit', 'process', 'internal', 'test', 'util', 'workers', 'jvm', 'elasticsearch', 'authentication']
import org.gradle.api.GradleException;
// org                  0	: ['org', 'java', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'fixture']
// gradle               0	: ['gradle', 'elasticsearch', 'get', 'util', 'common', 'xpack', 'joda', 'xml', 'yaml']
// api                  0	: ['api', 'testkit', 'process', 'internal', 'test', 'util', 'workers', 'jvm', 'elasticsearch', 'authentication']
import org.gradle.api.file.DirectoryProperty;
// org                  0	: ['org', 'java', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'fixture']
// gradle               0	: ['gradle', 'elasticsearch', 'get', 'util', 'common', 'xpack', 'joda', 'xml', 'yaml']
// api                  0	: ['api', 'testkit', 'process', 'internal', 'test', 'util', 'workers', 'jvm', 'elasticsearch', 'authentication']
// file                 1	: ['tasks', 'file', 'provider', 'logging', 'services', 'plugins', 'internal', 'model', 'execution', 'publish']
import org.gradle.api.logging.Logger;
// org                  0	: ['org', 'java', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'fixture']
// gradle               0	: ['gradle', 'elasticsearch', 'get', 'util', 'common', 'xpack', 'joda', 'xml', 'yaml']
// api                  0	: ['api', 'testkit', 'process', 'internal', 'test', 'util', 'workers', 'jvm', 'elasticsearch', 'authentication']
// logging              3	: ['tasks', 'file', 'provider', 'logging', 'services', 'plugins', 'internal', 'model', 'execution', 'publish']
import org.gradle.api.logging.Logging;
// org                  0	: ['org', 'java', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'fixture']
// gradle               1	: ['elasticsearch', 'gradle', 'get', 'util', 'common', 'xpack', 'joda', 'xml', 'yaml']
// api                  0	: ['api', 'testkit', 'process', 'internal', 'test', 'util', 'workers', 'jvm', 'elasticsearch', 'authentication']
// logging              3	: ['tasks', 'file', 'provider', 'logging', 'services', 'plugins', 'internal', 'model', 'execution', 'publish']
import org.gradle.api.tasks.Classpath;
// org                  0	: ['org', 'java', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'fixture']
// gradle               0	: ['gradle', 'elasticsearch', 'get', 'util', 'common', 'xpack', 'joda', 'xml', 'yaml']
// api                  0	: ['api', 'testkit', 'process', 'internal', 'test', 'util', 'workers', 'jvm', 'elasticsearch', 'authentication']
// tasks                0	: ['tasks', 'file', 'provider', 'logging', 'services', 'plugins', 'internal', 'model', 'execution', 'publish']
import org.gradle.api.tasks.Input;
// org                  0	: ['org', 'java', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'fixture']
// gradle               0	: ['gradle', 'elasticsearch', 'get', 'util', 'common', 'xpack', 'joda', 'xml', 'yaml']
// api                  0	: ['api', 'testkit', 'process', 'internal', 'test', 'util', 'workers', 'jvm', 'elasticsearch', 'authentication']
// tasks                0	: ['tasks', 'file', 'provider', 'logging', 'services', 'plugins', 'internal', 'model', 'execution', 'publish']
import org.gradle.api.tasks.OutputDirectory;
// org                  0	: ['org', 'java', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'fixture']
// gradle               1	: ['elasticsearch', 'gradle', 'get', 'util', 'common', 'xpack', 'joda', 'xml', 'yaml']
// api                  0	: ['api', 'testkit', 'process', 'internal', 'test', 'util', 'workers', 'jvm', 'elasticsearch', 'authentication']
// tasks                0	: ['tasks', 'file', 'provider', 'logging', 'services', 'plugins', 'internal', 'model', 'execution', 'publish']
import org.gradle.api.tasks.StopExecutionException;
// org                  0	: ['org', 'java', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'fixture']
// gradle               0	: ['gradle', 'elasticsearch', 'get', 'util', 'common', 'xpack', 'joda', 'xml', 'yaml']
// api                  0	: ['api', 'testkit', 'process', 'internal', 'test', 'util', 'workers', 'jvm', 'elasticsearch', 'authentication']
// tasks                0	: ['tasks', 'file', 'provider', 'logging', 'services', 'plugins', 'internal', 'model', 'execution', 'publish']
import org.gradle.api.tasks.TaskAction;
// org                  0	: ['org', 'java', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'fixture']
// gradle               0	: ['gradle', 'elasticsearch', 'get', 'util', 'common', 'xpack', 'joda', 'xml', 'yaml']
// api                  0	: ['api', 'testkit', 'process', 'internal', 'test', 'util', 'workers', 'jvm', 'elasticsearch', 'authentication']
// tasks                0	: ['tasks', 'file', 'provider', 'logging', 'services', 'plugins', 'internal', 'model', 'execution', 'publish']

import java.io.File;
// java                 1	: ['org', 'java', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'fixture']
import java.io.IOException;
// java                 0	: ['java', 'org', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'fixture']
import java.io.InputStream;
// java                 0	: ['java', 'org', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'fixture']
import java.nio.file.Files;
// java                 0	: ['java', 'org', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'fixture']
// file                 0	: ['file', 'charset', 'channels', 'elasticsearch', 'client', 'entity', 'conn', 'util', 'protocol', 'get']
import java.nio.file.Path;
// java                 0	: ['java', 'org', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'fixture']
// file                 0	: ['file', 'charset', 'channels', 'elasticsearch', 'client', 'entity', 'conn', 'util', 'protocol', 'get']
import java.nio.file.StandardCopyOption;
// java                 0	: ['java', 'org', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'fixture']
// file                 0	: ['file', 'charset', 'channels', 'elasticsearch', 'client', 'entity', 'conn', 'util', 'protocol', 'get']
import java.util.Collections;
// java                 0	: ['java', 'org', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'fixture']
// util                 0	: ['util', 'security', 'time', 'lang', 'elasticsearch', 'sql', 'get', 'text', 'math']
import java.util.HashSet;
// java                 0	: ['java', 'org', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'fixture']
// util                 0	: ['util', 'time', 'security', 'lang', 'elasticsearch', 'sql', 'get', 'text', 'math']
import java.util.Set;
// java                 0	: ['java', 'org', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'fixture']
// util                 0	: ['util', 'time', 'security', 'lang', 'elasticsearch', 'sql', 'get', 'text', 'math']

/**
 * Export Elasticsearch build resources to configurable paths
 * <p>
 * Wil overwrite existing files and create missing directories.
 * Useful for resources that that need to be passed to other processes trough the filesystem or otherwise can't be
 * consumed from the classpath.
 */
public class ExportElasticsearchBuildResourcesTask extends DefaultTask {

    private final Logger logger = Logging.getLogger(ExportElasticsearchBuildResourcesTask.class);
// logger               0	: ['logger', 'LOGGER', 'log', 'parentLogger', 'textLogger', 'deletesLogger', 'mockLogger', 'tracerLog', 'indexLogger', 'get']

    private final Set<String> resources = new HashSet<>();
// resources            No	: ['roles', 'RESPONSE_PARAMS', 'names', 'responseParams', 'inSyncAllocationIds', 'remoteClusters', 'nodeIds', 'nodes', 'cluster', 'runAs']

    private DirectoryProperty outputDir;
// outputDir            No	: ['pluginsDir', 'extractPath', 'distributionsDir', 'upgradeDir', 'utilsDir', 'testsDir', 'dockerContext', 'get', 'core', 'lucene']

    public ExportElasticsearchBuildResourcesTask() {
        outputDir = getProject().getObjects().directoryProperty();
// outputDir            No	: ['builder', 'logger', 'listener', 'expectThrows', 'client', 'createIndex', 'threadPool', 'when', 'ingestDocument', 'lenient']
        outputDir.set(new File(getProject().getBuildDir(), "build-tools-exported"));
// outputDir            No	: ['builder', 'client', 'logger', 'i', 'request', 'verify', 'document', 'fail', 'refresh', 'clusterState']
// set                  No	: ['resolve', 'elasticsearch', 'get', 'util', 'common', 'xpack', 'put', 'action', 'index', 'field']
    }

    @OutputDirectory
    public DirectoryProperty getOutputDir() {
        return outputDir;
// outputDir            No	: ['NAME', 'getToken', 'name', 'id', 'in', 'field', 'delegate', 'TYPE', 'source', 'value']
    }

    @Input
    public Set<String> getResources() {
        return Collections.unmodifiableSet(resources);
// resources            No	: ['fields', 'VALUES', 'usedVariables', 'result', 'data', 'set', 'privileges', 'excludes', 'joins', 'detected']
    }

    @Classpath
    public String getResourcesClasspath() {
        // This will make sure the task is not considered up to date if the resources are changed.
        logger.info("Classpath: {}", System.getProperty("java.class.path"));
// logger               1	: ['builder', 'logger', 'listener', 'expectThrows', 'client', 'createIndex', 'threadPool', 'when', 'ingestDocument', 'lenient']
// info                 0	: ['info', 'debug', 'trace', 'warn', 'error', 'log', 'deprecated', 'elasticsearch', 'get', 'util']
        return System.getProperty("java.class.path");
    }

    public void setOutputDir(File outputDir) {
// outputDir            No	: ['file', 'from', 'dependency', 'plugin', 'module', 'licensesDir', 'javaHome', 'f', 'parent', 'target']
        this.outputDir.set(outputDir);
// outputDir            No	: ['name', 'id', 'jobId', 'index', 'settings', 'indices', 'client', 'version', 'type', 'value']
// set                  No	: ['resolve', 'elasticsearch', 'get', 'util', 'common', 'xpack', 'put', 'action', 'index', 'field']
// outputDir            No	: ['e', 'i', 'response', 'bucket', 'index', 'r', 'slot', 'engine', 'complete', 'securityContext']
    }

    public File copy(String resource) {
// copy                 No	: ['file', 'licensesDir', 'from', 'value', 'outputMarker', 'dependency', 'javaHome', 'shaFile', 'compilerJavaHome', 'f']
// resource             No	: ['id', 'key', 'in', 'name', 'index', 'field', 'jobId', 'source', 'toString', 'get']
        if (getState().getExecuted() || getState().getExecuting()) {
            throw new GradleException(
                "buildResources can't be configured after the task ran. " + "Make sure task is not used after configuration time"
            );
        }
        resources.add(resource);
// resources            No	: ['context', 'builder', 'token', 'logger', 'parser', 'fieldType', 'clauses', 'intervalType', 'id', 'keyStore']
// add                  0	: ['add', 'stream', 'isEmpty', 'get', 'elasticsearch', 'size', 'equals', 'length', 'resolve', 'iterator']
// resource             0	: ['resource', 'safeCommitRef', 'phase2Snapshot', 'retentionLock', 'releaseStore', 'multiFileSender', 'client', 'newNode', 'document', 'createIndexRequest']
        return outputDir.file(resource).get().getAsFile();
// outputDir            No	: ['matched', 'builder', 'request', 'channel', 'result', 'parser', 'sb', 'client', 'response', 'future']
// file                 No	: ['resolve', 'elasticsearch', 'get', 'util', 'common', 'xpack', 'put', 'action', 'index', 'field']
// resource             No	: ['dir', 'DOCKER_COMPOSE_YML', 'metadataFileName', 'fileness', 'request', 'response', 'e', 'client', 'settings', 'parser']
// get                  2	: ['toString', 'close', 'get', 'put', 'build', 'field', 'size', 'admin', 'indices', 'value']
    }

    @TaskAction
    public void doExport() {
        if (resources.isEmpty()) {
// resources            No	: ['randomBoolean', 'closed', 'enabled', 'isClosed', 'valuesSource', 'id', 'failure', 'state', 'source', 'matchedFormats']
// isEmpty              0	: ['isEmpty', 'add', 'stream', 'resolve', 'get', 'elasticsearch', 'size', 'equals', 'length', 'iterator']
            setDidWork(false);
            throw new StopExecutionException();
        }
        resources.stream().parallel().forEach(resourcePath -> {
// resources            No	: ['builder', 'logger', 'client', 'refresh', 'source', 'mapping', 'barrier', 'latch', 'sw', 'createIndex']
// stream               1	: ['add', 'stream', 'isEmpty', 'get', 'elasticsearch', 'size', 'equals', 'length', 'resolve', 'iterator']
// resourcePath         No	: ['p', 'e', 'agg', 'k', 'entry', 'c', 'n', 'alias', 'query', 'node']
            Path destination = outputDir.get().file(resourcePath).getAsFile().toPath();
// destination          1	: ['path', 'destination', 'blob', 'newPathSegments', 'relativeDestination', 'file', 'tempDir', 'pluginDir', 'dir', 'get']
// outputDir            No	: ['env', 'pluginPath', 'destinationPath', 'destinationRoot', 'destination', 'request', 'response', 'client', 'in', 'expectThrows']
// get                  2	: ['resolve', 'elasticsearch', 'get', 'util', 'common', 'xpack', 'put', 'action', 'index', 'field']
// file                 No	: ['get', 'size', 'isEmpty', 'isTimedOut', 'v1', 'isAcknowledged', 'getIndex', 'status', 'getResult', 'mappings']
// resourcePath         No	: ['dir', 'DOCKER_COMPOSE_YML', 'metadataFileName', 'fileness', 'request', 'response', 'e', 'client', 'settings', 'parser']
// toPath               1	: ['get', 'toPath', 'put', 'admin', 'size', 'field', 'indices', 'cluster', 'index', 'elasticsearch']
            try (InputStream is = getClass().getClassLoader().getResourceAsStream(resourcePath)) {
// is                   1	: ['stream', 'is', 'in', 'inputStream', 'input', 'content', 'jar', 'resourceInput', 'configStream', 'license']
// resourcePath         No	: ['json', 'yaml', 'path', 'name', 'file', 'MODEL_RESOURCE_PATH', 'TIKA_FILES', 'TASK_RESULT_INDEX_MAPPING_FILE', 'request', 'response']
                Files.createDirectories(destination.getParent());
// destination          3	: ['path', 'parent', 'xpackConf', 'destination', 'scripts', 'dst', 'f', 'installation', 'esStdoutFile', 'jarPath']
// getParent            0	: ['getParent', 'index', 'toString', 'routing', 'getName', 'remaining', 'version', 'equals', 'indexOf', 'versionType']
                if (is == null) {
// is                   No	: ['randomBoolean', 'request', 'i', 'in', 'rarely', 'response', 'node', 'version', 'shard', 'hasByteOrderMarker']
                    throw new GradleException("Can't export `" + resourcePath + "` from build-tools: not found");
// resourcePath         No	: ['tarFile', 'licensesDir', 'name', 'entry', 'refName', 'jarName', 'jdk', 'details', 'inputDir', 'exclusionsFile']
                }
                Files.copy(is, destination, StandardCopyOption.REPLACE_EXISTING);
// copy                 2	: ['write', 'delete', 'copy', 'exists', 'elasticsearch', 'isDirectory', 'get', 'util', 'list', 'lines']
// is                   No	: ['keystore', 'getDataPath', 'signingKeyStorePath', 'users', 'signingKeyPath', 'cert1', 'jar', 'signingCertPath', 'cert2', 'dict']
// destination          No	: ['out', 'outputStream', 'mockAppender', 'terminal', 'keyStorePass', 'trustStorePass', 'b', 'sb', 'equalTo', 'is']
            } catch (IOException e) {
// e                    0	: ['e', 'ex', 'ioe', 'e1', 'exception', 'inner', 'expected', 'e2', 'bogus', 'exc']
                throw new GradleException("Can't write resource `" + resourcePath + "` to " + destination, e);
// resourcePath         No	: ['tarFile', 'licensesDir', 'name', 'entry', 'refName', 'jarName', 'jdk', 'details', 'inputDir', 'exclusionsFile']
// destination          No	: ['i', 'name', 'index', 'value', 'id', 'type', 'jobId', 'e', 'token', 'fieldName']
// e                    No	: ['joinRequest', 'from', 'responseActionListener', 'parser', 'source', 'permissions', 'initialState', 'applyCommit', 'publishRequest', 'pluginName']
            }
        });
    }

}
