// Path id: 25
// Path to file: C:\Users\Igor\IdeaProjects\SLP-Core-0.3\java-projects\elasticsearch-master\buildSrc\src\main\java\org\elasticsearch\gradle\LazyPropertyMap.java
// Number of identifiers: 202	Accuracy: 43.56%	MRR: 51.59%
// True 	Rank of true in predictions : [(predicted token, probability of a token), ...]

package org.elasticsearch.gradle;
// org                  0	: ['org', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'j', 'allocation']
// elasticsearch        0	: ['elasticsearch', 'get', 'util', 'common', 'xpack', 'gradle', 'joda', 'xml', 'yaml']
// gradle               No	: ['xpack', 'common', 'action', 'search', 'index', 'client', 'cluster', 'test', 'rest', 'analysis']

import org.gradle.api.Named;
// org                  0	: ['org', 'java', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'fixture']
// gradle               1	: ['elasticsearch', 'gradle', 'get', 'util', 'common', 'xpack', 'joda', 'xml', 'yaml']
// api                  0	: ['api', 'testkit', 'process', 'internal', 'test', 'util', 'workers', 'jvm', 'elasticsearch', 'authentication']
import org.gradle.api.tasks.Input;
// org                  0	: ['org', 'java', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'fixture']
// gradle               0	: ['gradle', 'elasticsearch', 'get', 'util', 'common', 'xpack', 'joda', 'xml', 'yaml']
// api                  0	: ['api', 'testkit', 'process', 'internal', 'test', 'util', 'workers', 'jvm', 'elasticsearch', 'authentication']
// tasks                0	: ['tasks', 'file', 'logging', 'provider', 'services', 'plugins', 'internal', 'model', 'execution', 'publish']

import java.util.Collection;
// java                 1	: ['org', 'java', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'fixture']
// util                 0	: ['util', 'time', 'security', 'lang', 'elasticsearch', 'sql', 'get', 'text', 'math']
import java.util.LinkedHashMap;
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
import java.util.function.BiFunction;
// java                 0	: ['java', 'org', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'fixture']
// util                 0	: ['util', 'time', 'security', 'lang', 'elasticsearch', 'sql', 'get', 'text', 'math']
// function             0	: ['function', 'stream', 'regex', 'elasticsearch', 'zip', 'UUID', 'get', 'util', 'jar', 'logging']
import java.util.function.Supplier;
// java                 0	: ['java', 'org', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'fixture']
// util                 0	: ['util', 'time', 'security', 'lang', 'elasticsearch', 'sql', 'get', 'text', 'math']
// function             0	: ['function', 'stream', 'regex', 'elasticsearch', 'zip', 'UUID', 'get', 'util', 'jar', 'logging']
import java.util.stream.Collectors;
// java                 0	: ['java', 'org', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'fixture']
// util                 0	: ['util', 'time', 'security', 'lang', 'elasticsearch', 'sql', 'get', 'text', 'math']
// stream               1	: ['function', 'stream', 'regex', 'elasticsearch', 'zip', 'UUID', 'get', 'util', 'jar', 'logging']

public class LazyPropertyMap<K, V> extends AbstractLazyPropertyCollection implements Map<K, V> {

    private final Map<K, PropertyMapEntry<K, V>> delegate = new LinkedHashMap<>();
// delegate             No	: ['future', 'entries', 'nodes', 'mapKey', 'onExpiration', 'map', 'iterator', 'tuple', 'matcher', 'put']
    private final BiFunction<K, V, ?> normalizationMapper;
// normalizationMapper  No	: ['doc', 'parse', 'source', 'entry', 'builder', 'response', 'map', 'properties', 'that', 'parser']

    public LazyPropertyMap(String name) {
// name                 0	: ['name', 'index', 'id', 'field', 'jobId', 'source', 'key', 'fieldName', 'message', 'value']
        this(name, null);
// name                 0	: ['name', 'source', 'settings', 'nodeId', 'id', 'client', 'fieldName', 'in', 'request', 'response']
    }

    public LazyPropertyMap(String name, Object owner) {
// name                 0	: ['name', 'index', 'id', 'field', 'jobId', 'source', 'key', 'fieldName', 'message', 'value']
// owner                2	: ['value', 'text', 'owner', 'context', 'constant', 'aggregation', 'timeValue', 'p0', 'p1', 'p2']
        this(name, owner, null);
// name                 0	: ['name', 'id', 'source', 'settings', 'client', 'fieldName', 'shardId', 'index', 'status', 'key']
// owner                No	: ['type', 'clusterPrivileges', 'properties', 'ignoreUnknownFields', 'fullFieldName', 'context', 'values', 'enabled', 'origin', 'patterns']
    }

    public LazyPropertyMap(String name, Object owner, BiFunction<K, V, ?> normalizationMapper) {
// name                 0	: ['name', 'index', 'id', 'field', 'jobId', 'source', 'key', 'fieldName', 'message', 'value']
// owner                2	: ['value', 'text', 'owner', 'context', 'constant', 'aggregation', 'timeValue', 'p0', 'p1', 'p2']
// normalizationMapper  No	: ['doc', 'parse', 'source', 'entry', 'builder', 'response', 'map', 'properties', 'that', 'parser']
        super(name, owner);
// name                 1	: ['NAME', 'name', 'source', 'client', 'indexSettings', 'parent', 'settings', 'message', 'testCandidate', 'location']
// owner                No	: ['metadata', 'factories', 'config', 'context', 'queryShardContext', 'NAME', 'bucketsPaths', 'reader', 'transportService', 'docCount']
        this.normalizationMapper = normalizationMapper;
// normalizationMapper  No	: ['client', 'name', 'threadPool', 'licenseState', 'clusterService', 'source', 'field', 'settings', 'version', 'enabled']
// normalizationMapper  No	: ['client', 'in', 'expectThrows', 'mock', 'parser', 'randomBoolean', 'randomFrom', 'response', 'createParser', 'request']
    }

    @Override
    public int size() {
// size                 1	: ['hashCode', 'size', 'read', 'compareTo', 'docID', 'docValueCount', 'nextDoc', 'length', 'available', 'indexOf']
        return delegate.size();
// delegate             3	: ['size', 'count', 'map', 'delegate', 'shards', 'in', 'values', 'data', 'requests', 'counters']
// size                 2	: ['stream', 'keySet', 'size', 'toString', 'hashCode', 'isEmpty', 'get', 'add', 'values', 'executor']
    }

    @Override
    public boolean isEmpty() {
// isEmpty              6	: ['equals', 'hasNext', 'matches', 'resolved', 'foldable', 'isCacheable', 'isEmpty', 'needsScores', 'verify', 'match']
        return delegate.isEmpty();
// delegate             1	: ['map', 'delegate', 'EMPTY', 'data', 'scope', 'executable', 'local', 'httpHeaders', 'empty', 'nodes']
// isEmpty              5	: ['stream', 'keySet', 'size', 'toString', 'hashCode', 'isEmpty', 'get', 'add', 'values', 'executor']
    }

    @Override
    public boolean containsKey(Object key) {
// key                  0	: ['key', 'o', 'obj', 'value', 'other', 'object', 'input', 'left', 'source', 'get']
        return delegate.containsKey(key);
// delegate             3	: ['map', 'key', 'get', 'delegate', 'data', 'sysprops', 'keyedFields', 'NAME', 'getToken', 'name']
// key                  1	: ['o', 'key', 'theKey', 'entry', 'aw', 'name', 'index', 'field', 'fieldName', 'request']
    }

    @Override
    public boolean containsValue(Object value) {
// value                0	: ['value', 'o', 'obj', 'other', 'key', 'object', 'input', 'left', 'source', 'get']
        return delegate.values().stream().map(PropertyMapEntry::getValue).anyMatch(v -> v.equals(value));
// delegate             2	: ['value', 'map', 'delegate', 'INTEGER', 'format', 'literal', 'data', 'set', 'payload', 'addField']
// values               8	: ['stream', 'keySet', 'size', 'toString', 'hashCode', 'isEmpty', 'get', 'add', 'values', 'executor']
// stream               0	: ['stream', 'iterator', 'length', 'size', 'get', 'spliterator', 'containsAll', 'removeIf', 'isEmpty', 'values']
// map                  0	: ['map', 'filter', 'collect', 'allMatch', 'sorted', 'limit', 'distinct', 'max', 'min', 'skip']
// getValue             9	: ['fromXContent', 'onFailure', 'get', 'parse', 'getName', 'build', 'set', 'add', 'getKey', 'getValue']
// v                    No	: ['s', 'n', 'p', 'bucket', 'entry', 'includes', 'hit', 'name', 'shardRouting', 'request']
// v                    0	: ['v', 'quote', 'listener', 'defaultFailureResponseHeaders', 'client', 'exec', 'e', 'indices', 'executor', 'writeUpdatedRepoDataStep']
// equals               4	: ['dataType', 'before', 'keyAndTimestamp', 'seqNo', 'equals', 'major', 'name', 'value', 'after', 'isCompatible']
// value                No	: ['that', 'currentFieldName', 'input', 'other', 'name', 'obj', 'index', 'id', 'type', 'o']
    }

    @Override
    public V get(Object key) {
// get                  0	: ['get', 'read', 'getValue', 'next', 'put', 'value', 'v', 'core', 'lucene']
// key                  0	: ['key', 'o', 'obj', 'value', 'other', 'object', 'input', 'left', 'source', 'get']
        PropertyMapEntry<K, V> entry = delegate.get(key);
// entry                1	: ['map', 'entry', 'receiver', 'kvPair', 'segment', 'newMap', 'removalListener', 'weigher', 'predicate', 'put']
// delegate             9	: ['future', 'current', 'f', 'segment', 'iterator', 'map', 'ingestDocument', 'status', 'it', 'delegate']
// get                  0	: ['get', 'nextOrd', 'next', 'entrySet', 'field', 'slice', 'leaves', 'load', 'loadDirect', 'onFailure']
// key                  2	: ['index', 'i', 'key', 'theKey', 'settings', 'name', 'bucket', 'response', 'request', 'e']
        if (entry != null) {
// entry                No	: ['value', 'setting', 'values', 'sValue', 'out', 'node', 'matcher', 'existing', 'beforePart', 'existingValue']
            V value = entry.getValue();
// value                0	: ['value', 'term', 'rv', 'read', 'run', 'load', 'get', 'v', 'core']
// entry                0	: ['entry', 'get', 'valueReader', 'value', 'parser', 'in', 'values', 'randomFrom', 'context', 'randomInt']
// getValue             0	: ['getValue', 'getKey', 'get', 'snapshot', 'elasticsearch', 'value', 'next', 'categoryClass', 'replace', 'split']
            assertNotNull(value, "value for key '" + key + "'");
// value                No	: ['response', 've', 'filterPath', 'stats', 'e', 'reference', 'searchResponse', 'sourceDocMap', 'enrichDocument', 'nested']
// key                  0	: ['key', 'i', 'value', 'token', 'currentFieldName', 'name', 'parser', 'destIndex', 'targetNode', 'get']
            return value;
// value                8	: ['builder', 'request', 'channel', 'result', 'parser', 'sb', 'client', 'response', 'value', 'future']
        } else {
            return null;
        }
    }

    @Override
    public V put(K key, V value) {
// put                  4	: ['get', 'read', 'getValue', 'next', 'put', 'value', 'v', 'core', 'lucene']
// key                  0	: ['key', 'part', 'key1', 'delete', 'getKey', 'key2', 'next', 'get', 'core', 'lucene']
// value                0	: ['value', 'get', 'beforePart', 'v', 'value1', 'value2', 'val', 'defaultValue', 'value3']
        return put(key, value, PropertyNormalization.DEFAULT);
// put                  No	: ['value', 'map', 'dt', 'in', 'delegate', 'format', 'apply', 'INTEGER', 'clusterState', 'bucketOrds']
// key                  0	: ['key', 'settings', 'template', 'indexMetadata', 'indexBuilder', 'indexSettings', 'SETTING_NUMBER_OF_SHARDS', 'SETTING_NUMBER_OF_REPLICAS', 'request', 'response']
// value                0	: ['value', 'level', 'path', 'timeValue', 'luceneVersion', 'byteSizeValue', 'enumValue', 'valueSupplier', 'filteredValue', 'values']
// DEFAULT              0	: ['DEFAULT', 'elasticsearch', 'get', 'util', 'common', 'xpack', 'put', 'action', 'index', 'field']
    }

    public V put(K key, V value, PropertyNormalization normalization) {
// put                  3	: ['get', 'getValue', 'read', 'put', 'next', 'value', 'v', 'core', 'lucene']
// key                  0	: ['key', 'part', 'key1', 'delete', 'getKey', 'key2', 'next', 'get', 'core', 'lucene']
// value                0	: ['value', 'get', 'beforePart', 'v', 'value1', 'value2', 'val', 'defaultValue', 'value3']
// normalization        0	: ['normalization', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'j', 'allocation']
        assertNotNull(value, "value for key '" + key + "'");
// value                No	: ['result', 'illegalArgumentException', 'response', 'context', 'shardsStats', 'translogGeneration', 'e', 'request', 'analyzer', 'searchContext']
// key                  0	: ['key', 'i', 'value', 'token', 'currentFieldName', 'name', 'parser', 'destIndex', 'targetNode', 'get']
        return put(key, () -> value, normalization);
// put                  No	: ['builder', 'request', 'channel', 'result', 'parser', 'sb', 'client', 'response', 'value', 'future']
// key                  0	: ['key', 'settings', 'template', 'indexMetadata', 'indexBuilder', 'indexSettings', 'SETTING_NUMBER_OF_SHARDS', 'SETTING_NUMBER_OF_REPLICAS', 'request', 'response']
// value                No	: ['keyPassword', 'exec', 'client', 'builder', 'randomFrom', 'parser', 'execute', 'ft', 'indexNameExpressionResolver', 'results']
// normalization        No	: ['equalTo', 'is', 'other', 'context', 'value', 'that', 'formatter', 'fe', 'method', 'bytes']
    }

    public V put(K key, Supplier<V> supplier) {
// put                  3	: ['get', 'getValue', 'read', 'put', 'next', 'value', 'v', 'core', 'lucene']
// key                  0	: ['key', 'part', 'key1', 'delete', 'getKey', 'key2', 'next', 'get', 'core', 'lucene']
// supplier             No	: ['valueGenerator', 'valueSupplier', 'valueType', 'visitor', 'listener', 'value', 'type', 'verify', 'diff', 'valueMatcher']
        return put(key, supplier, PropertyNormalization.DEFAULT);
// put                  No	: ['delegate', 'NAME', 'getToken', 'name', 'PARSER', 'restHighLevelClient', 'in', 'state', 'id', 'field']
// key                  0	: ['key', 'settings', 'template', 'indexMetadata', 'indexBuilder', 'indexSettings', 'SETTING_NUMBER_OF_SHARDS', 'SETTING_NUMBER_OF_REPLICAS', 'request', 'response']
// supplier             No	: ['value', 'level', 'path', 'timeValue', 'luceneVersion', 'byteSizeValue', 'enumValue', 'valueSupplier', 'filteredValue', 'values']
// DEFAULT              0	: ['DEFAULT', 'elasticsearch', 'get', 'util', 'common', 'xpack', 'put', 'action', 'index', 'field']
    }

    public V put(K key, Supplier<V> supplier, PropertyNormalization normalization) {
// put                  3	: ['get', 'getValue', 'read', 'put', 'next', 'value', 'v', 'core', 'lucene']
// key                  0	: ['key', 'part', 'key1', 'delete', 'getKey', 'key2', 'next', 'get', 'core', 'lucene']
// supplier             No	: ['valueGenerator', 'valueSupplier', 'valueType', 'visitor', 'listener', 'value', 'type', 'verify', 'diff', 'valueMatcher']
// normalization        0	: ['normalization', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'j', 'allocation']
        assertNotNull(supplier, "supplier for key '" + key + "'");
// supplier             No	: ['result', 'illegalArgumentException', 'response', 'context', 'shardsStats', 'translogGeneration', 'e', 'request', 'analyzer', 'searchContext']
// key                  No	: ['token', 'currentFieldName', 'i', 'name', 'parser', 'index', 'value', 'destIndex', 'fieldName', 'targetNode']
        PropertyMapEntry<K, V> previous = delegate.put(key, new PropertyMapEntry<>(key, supplier, normalization));
// previous             No	: ['map', 'entry', 'receiver', 'kvPair', 'segment', 'newMap', 'removalListener', 'weigher', 'predicate', 'put']
// delegate             No	: ['copy', 'mapBuilder', 'seenSequenceNumbers', 'current', 'asc', 'p99', 'val', 'values', 'build', 'deadHostState']
// put                  No	: ['nextOrd', 'get', 'next', 'entrySet', 'field', 'slice', 'leaves', 'load', 'loadDirect', 'hasSource']
// key                  1	: ['entry', 'key', 'settings', 'indexMetadata', 'indexBuilder', 'indexSettings', 'SETTING_NUMBER_OF_SHARDS', 'SETTING_NUMBER_OF_REPLICAS', 'name', 'request']
// key                  5	: ['channel', 'listener', 'NAME', 'size', 'instance', 'key', 'request', 'result', 'buckets', 'in']
// supplier             No	: ['s', 'fallbackSetting', 'value', 'defaultValue', 'delegate', 'key', 'fallback', 'method', 'loaded', 'binder']
// normalization        6	: ['reader', 'listener', 'other', 'equalTo', 'times', 'v', 'normalization', 'reindexCompletedListener', 'is', 'e']
        return previous == null ? null : previous.getValue();
// previous             No	: ['builder', 'request', 'channel', 'result', 'randomFrom', 'response', 'client', 'scripts', 'settings', 'state']
// previous             No	: ['primaryStats', 'totalStats', 'node', 'modelSizeStats', 'stats', 'searchStats', 'transformIndexerStats', 'indicesStats', 'info', 'mergeStats']
// getValue             No	: ['keySet', 'get', 'clear', 'metadata', 'filter', 'v1', 'intValue', 'copyBytes', 'routingTable', 'configuration']
    }

    @Override
    public V remove(Object key) {
// remove               No	: ['get', 'read', 'getValue', 'next', 'put', 'value', 'v', 'core', 'lucene']
// key                  0	: ['key', 'o', 'obj', 'value', 'other', 'object', 'input', 'left', 'get', 'core']
        PropertyMapEntry<K, V> previous = delegate.remove(key);
// previous             No	: ['map', 'entry', 'receiver', 'kvPair', 'segment', 'newMap', 'removalListener', 'weigher', 'predicate', 'put']
// delegate             No	: ['copy', 'mapBuilder', 'seenSequenceNumbers', 'current', 'asc', 'p99', 'val', 'values', 'build', 'deadHostState']
// remove               No	: ['nextOrd', 'get', 'next', 'entrySet', 'field', 'slice', 'leaves', 'load', 'loadDirect', 'hasSource']
// key                  0	: ['key', 'index', 'listener', 'node', 'name', 'id', 'i', 'shard', 'request', 'response']
        return previous == null ? null : previous.getValue();
// previous             No	: ['setting', 'value', 'binding', 'params', 'delegateFactory', 'groupOrJob', 'secStr', 'builder', 'request', 'channel']
// previous             No	: ['primaryStats', 'totalStats', 'node', 'modelSizeStats', 'stats', 'searchStats', 'transformIndexerStats', 'indicesStats', 'info', 'mergeStats']
// getValue             No	: ['keySet', 'get', 'clear', 'metadata', 'filter', 'v1', 'intValue', 'copyBytes', 'routingTable', 'configuration']
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
// m                    No	: ['property', 'map', 'handler', 'other', 'visitor', 'entry', 'receiver', 'listener', 'valueType', 'kvPair']
        throw new UnsupportedOperationException(this.getClass().getName() + " does not support putAll()");
// getName              0	: ['getName', 'toString', 'equals', 'isArray', 'hashCode', 'get', 'put', 'admin', 'size', 'indices']
    }

    @Override
    public void clear() {
// clear                5	: ['onFailure', 'close', 'run', 'handleException', 'error', 'clear', 'info', 'log', 'debug', 'trace']
        delegate.clear();
// delegate             1	: ['map', 'delegate', 'logger', 'value', 'terminal', 'in', 'cache', 'counter', 'requests', 'queue']
// clear                1	: ['close', 'clear', 'shutdown', 'start', 'stop', 'onFailure', 'onResponse', 'put', 'elasticsearch', 'get']
    }

    @Override
    public Set<K> keySet() {
// keySet               No	: ['keySerializer', 'keyType', 'deletes', 'keyGenerator', 'keyWriter', 'keyReader', 'iterator', 'key', 'keys', 'removeIf']
        return delegate.keySet();
// delegate             1	: ['httpHeaders', 'delegate', 'map', 'NAME', 'getToken', 'name', 'id', 'in', 'field', 'TYPE']
// keySet               1	: ['stream', 'keySet', 'size', 'toString', 'hashCode', 'isEmpty', 'get', 'add', 'values', 'executor']
    }

    @Override
    public Collection<V> values() {
// values               No	: ['valueType', 'visitor', 'listener', 'map', 'value', 'type', 'verify', 'diff', 'valueMatcher', 'reader']
        return delegate.values().stream().peek(this::validate).map(PropertyMapEntry::getValue).collect(Collectors.toList());
// delegate             4	: ['values', 'map', 'httpHeaders', 'mapModel', 'delegate', 'model', 'preBuiltAnalyzer', 'VALUES', 'NAME', 'getToken']
// values               8	: ['stream', 'keySet', 'size', 'toString', 'hashCode', 'isEmpty', 'get', 'add', 'values', 'executor']
// stream               0	: ['stream', 'iterator', 'length', 'size', 'get', 'spliterator', 'containsAll', 'removeIf', 'isEmpty', 'values']
// validate             0	: ['validate', 'createParser', 'rule', 'add', 'unwrap', 'onException', 'match', 'close', 'getDataPath', 'fromXContent']
// map                  0	: ['map', 'filter', 'get', 'put', 'build', 'field', 'size', 'admin', 'indices', 'value']
// getValue             9	: ['fromXContent', 'onFailure', 'get', 'parse', 'getName', 'build', 'set', 'add', 'getKey', 'getValue']
// collect              0	: ['collect', 'map', 'sum', 'filter', 'iterator', 'min', 'get', 'put', 'build', 'field']
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
// entrySet             No	: ['future', 'entries', 'nodes', 'mapKey', 'onExpiration', 'map', 'iterator', 'tuple', 'matcher', 'put']
        return delegate.entrySet()
// delegate             1	: ['httpHeaders', 'delegate', 'map', 'data', 'innerMap', 'NAME', 'getToken', 'name', 'id', 'field']
// entrySet             No	: ['stream', 'keySet', 'size', 'toString', 'hashCode', 'isEmpty', 'get', 'add', 'values', 'executor']
            .stream()
// stream               0	: ['stream', 'iterator', 'get', 'put', 'size', 'removeIf', 'admin', 'indices', 'elasticsearch', 'containsAll']
            .peek(this::validate)
// validate             0	: ['validate', 'createParser', 'rule', 'add', 'unwrap', 'onException', 'match', 'close', 'getDataPath', 'fromXContent']
            .collect(Collectors.toMap(Entry::getKey, entry -> entry.getValue().getValue()))
// collect              No	: ['map', 'filter', 'get', 'put', 'build', 'field', 'size', 'admin', 'indices', 'value']
// getKey               0	: ['getKey', 'getValue', 'fromXContent', 'onFailure', 'get', 'parse', 'getName', 'snapshot', 'build']
// entry                1	: ['e', 'entry', 'entryValueFunction', 'keyComparator', 'item', 'fn', 'equalTo', 'is', 'exec', 'get']
// entry                0	: ['entry', 'exec', 'builder', 'client', 'instance', 'jobs', 'DATA_NODE_PREDICATE', 'resolvedIndexNames', 'classSamples', 'enableFilter']
// getValue             0	: ['getValue', 'getKey', 'state', 'categoryClass', 'length', 'elasticsearch', 'text', 'get', 'value', 'key']
// getValue             No	: ['toString', 'get', 'floatValue', 'size', 'v2', 'stream', 'v1', 'onResponse', 'toXContent', 'accept']
            .entrySet();
// entrySet             No	: ['get', 'build', 'collect', 'put', 'actionGet', 'order', 'add', 'map', 'subAggregation', 'value']
    }

    @Override
    public List<? extends Object> getNormalizedCollection() {
        return delegate.values()
// delegate             0	: ['delegate', 'NAME', 'getToken', 'name', 'id', 'in', 'field', 'TYPE', 'source', 'restHighLevelClient']
// values               8	: ['stream', 'keySet', 'size', 'toString', 'hashCode', 'isEmpty', 'get', 'add', 'values', 'executor']
            .stream()
// stream               0	: ['stream', 'iterator', 'length', 'size', 'get', 'spliterator', 'containsAll', 'removeIf', 'isEmpty', 'values']
            .peek(this::validate)
// validate             0	: ['validate', 'createParser', 'rule', 'add', 'unwrap', 'onException', 'match', 'close', 'getDataPath', 'fromXContent']
            .filter(entry -> entry.getNormalization() != PropertyNormalization.IGNORE_VALUE)
// filter               1	: ['map', 'filter', 'get', 'put', 'build', 'field', 'size', 'admin', 'indices', 'value']
// entry                0	: ['entry', 'e', 's', 'n', 'r', 'termQuery', 't', 'p', 'c', 'request']
// entry                0	: ['entry', 'DATA_NODE_PREDICATE', 'resolvedIndexNames', 'enableFilter', 'builder', 'instance', 'jobs', 'exec', 'client', 'get']
            .map(entry -> normalizationMapper == null ? entry : normalizationMapper.apply(entry.getKey(), entry.getValue()))
// map                  No	: ['collect', 'get', 'put', 'build', 'field', 'size', 'admin', 'indices', 'value', 'execute']
// entry                5	: ['s', 'e', 'p', 't', 'o', 'entry', 'f', 'm', 'n', 'v']
// normalizationMapper  No	: ['entry', 'builder', 'instance', 'jobs', 'DATA_NODE_PREDICATE', 'resolvedIndexNames', 'classSamples', 'enableFilter', 'exec', 'client']
// entry                No	: ['context', 'defaultValue', 'type', 'emptyMap', 'script', 'queryBuilder', 'defaultFieldType', 'DEFAULT_K', 'emptyList', 'EMPTY']
// normalizationMapper  No	: ['map', 'entries', 'response', 'source', 'fields', 'node', 'headers', 'acknowledgeMessages', 'metadata', 'settings']
// apply                No	: ['elasticsearch', 'get', 'util', 'common', 'xpack', 'put', 'action', 'index', 'field', 'add']
// entry                No	: ['parser', 'p', 'value', 'vars', 'response', 'left', 'realmType', 'channelBuffer', 'group1', 'node']
// getKey               0	: ['getKey', 'getValue', 'getName', 'key', 'snapshot', 'value', 'get', 'elasticsearch', 'util']
// entry                1	: ['equalTo', 'entry', 'e', 'randomBoolean', 'instance', 'localNode', 'nodeName', 'randomFrom', 'home', 'value']
// getValue             0	: ['getValue', 'getKey', 'includeGlobalState', 'startTime', 'snapshot', 'state', 'type', 'indices', 'version', 'failure']
            .collect(Collectors.toList());
// collect              2	: ['get', 'build', 'collect', 'put', 'actionGet', 'order', 'add', 'map', 'subAggregation', 'value']
    }

    private void validate(Map.Entry<K, PropertyMapEntry<K, V>> entry) {
// validate             No	: ['testCase', 'toXContent', 'ensureOpen', 'createIndex', 'start', 'updateSettings', 'execute', 'close', 'check', 'createTestIndex']
// entry                No	: ['future', 'entries', 'nodes', 'mapKey', 'onExpiration', 'map', 'iterator', 'tuple', 'matcher', 'put']
        validate(entry.getValue());
// validate             No	: ['sb', 'entries', 'builder', 'logger', 'listener', 'request', 'out', 'fail', 'validationException', 'get']
// entry                No	: ['coordinates', 'settings', 'key', 'repositoryName', 'stream', 'client', 'request', 'CLUSTER_STATE', 'aggBuilders', 'get']
// getValue             1	: ['getKey', 'getValue', 'key', 'snapshot', 'value', 'get', 'state', 'getName', 'shards', 'uuid']
    }

    private void validate(PropertyMapEntry<K, V> supplier) {
// validate             No	: ['testCase', 'toXContent', 'ensureOpen', 'createIndex', 'start', 'updateSettings', 'execute', 'close', 'check', 'createTestIndex']
// supplier             No	: ['map', 'entry', 'receiver', 'kvPair', 'segment', 'newMap', 'removalListener', 'weigher', 'predicate', 'put']
        assertNotNull(supplier, "key '" + supplier.getKey() + "' supplier value");
// supplier             No	: ['result', 'illegalArgumentException', 'response', 'context', 'shardsStats', 'translogGeneration', 'e', 'request', 'analyzer', 'searchContext']
// supplier             No	: ['i', 'name', 'index', 'value', 'id', 'type', 'jobId', 'e', 'token', 'fieldName']
// getKey               No	: ['get', 'elasticsearch', 'set', 'apply', 'util', 'common', 'xpack', 'put', 'action']
    }

    private static class PropertyMapEntry<K, V> implements Named {
        private final K key;
// key                  0	: ['key', 'previousKey', 'getKey', 'part', 'key1', 'key2', 'next', 'delete', 'get', 'core']
        private final Supplier<V> value;
// value                5	: ['valueGenerator', 'valueSupplier', 'valueType', 'visitor', 'listener', 'value', 'type', 'verify', 'diff', 'valueMatcher']
        private final PropertyNormalization normalization;
// normalization        0	: ['normalization', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'j', 'allocation']

        PropertyMapEntry(K key, Supplier<V> value, PropertyNormalization normalization) {
// key                  0	: ['key', 'part', 'key1', 'delete', 'getKey', 'key2', 'next', 'get', 'core', 'lucene']
// value                5	: ['valueGenerator', 'valueSupplier', 'valueType', 'visitor', 'listener', 'value', 'type', 'verify', 'diff', 'valueMatcher']
// normalization        0	: ['normalization', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'j', 'allocation']
            this.key = key;
// key                  No	: ['supplier', 'name', 'id', 'jobId', 'index', 'settings', 'indices', 'client', 'version', 'type']
// key                  0	: ['key', 'randomStringSupplier', 'in', 'p', 'instance', 'ref', 'parser', 'currentFieldName', 'client', 'get']
            this.value = value;
// value                0	: ['value', 'docCount', 'from', 'filter', 'delegate', 'count', 'expiration', 'property', 'settings', 'validator']
// value                0	: ['value', 'in', 'randomStringSupplier', 'ref', 'src', 'watchId', 'updatedValue', 'parser', 'values', 'client']
            this.normalization = normalization;
// normalization        No	: ['completed', 'type', 'attr', 'delta', 'featureImportance', 'isShort', 'id', 'fieldName', 'weight', 'children']
// normalization        0	: ['normalization', 'settings', 'client', 'in', 'expectThrows', 'mock', 'parser', 'randomBoolean', 'randomFrom', 'response']
        }

        public PropertyNormalization getNormalization() {
            return normalization;
// normalization        0	: ['normalization', 'NAME', 'getToken', 'name', 'id', 'in', 'field', 'delegate', 'TYPE', 'source']
        }

        @Override
        public String getName() {
// getName              1	: ['toString', 'getName', 'executor', 'type', 'name', 'typeName', 'format', 'getKey', 'innerName', 'id']
            return getKey().toString();
// getKey               No	: ['name', 'NAME', 'policy', 'field', 'delegate', 'AGGREGATION_NAME', 'clusterName', 'parseField', 'suffix', 'nameCustomization']
// toString             2	: ['equals', 'getName', 'toString', 'get', 'substring', 'v2', 'v1', 'replace', 'name', 'compareTo']
        }

        @Input
        public K getKey() {
// getKey               0	: ['getKey', 'next', 'key', 'part', 'key1', 'key2', 'delete', 'previousKey', 'get', 'core']
            return key;
// key                  0	: ['key', 'term', 'current', 'next', 'entry', 'lookup', 'NAME', 'getToken', 'name', 'id']
        }

        @Input
        public V getValue() {
// getValue             1	: ['get', 'getValue', 'read', 'put', 'next', 'value', 'v', 'core', 'lucene']
            return value.get();
// value                0	: ['value', 'get', 'sum', 'state', 'settings', 'current', 'max', 'min', 'next', 'counts']
// get                  No	: ['hashCode', 'matches', 'toString', 'equals', 'doubleValue', 'stream', 'replace', 'trim', 'numberValue', 'diff']
        }
    }
}
