// Path id: 3
// Path to file: C:\Users\Igor\IdeaProjects\SLP-Core-0.3\java-projects\elasticsearch-master\benchmarks\src\main\java\org\elasticsearch\benchmark\routing\allocation\Allocators.java
// Number of identifiers: 126	Accuracy: 68.25%	MRR: 76.97%
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
package org.elasticsearch.benchmark.routing.allocation;
// org                  0	: ['org', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'j', 'allocation']
// elasticsearch        0	: ['elasticsearch', 'get', 'util', 'common', 'xpack', 'gradle', 'joda', 'xml', 'yaml']
// benchmark            No	: ['xpack', 'common', 'action', 'search', 'index', 'client', 'cluster', 'test', 'rest', 'analysis']
// routing              2	: ['time', 'indices', 'routing', 'fs', 'metrics', 'ops', 'rest', 'run', 'elasticsearch', 'get']
// allocation           0	: ['allocation', 'elasticsearch', 'primary', 'toString', 'shardId', 'currentNodeId', 'state', 'allocationId', 'initializing', 'operation']

import org.elasticsearch.Version;
// org                  0	: ['org', 'java', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'fixture']
// elasticsearch        0	: ['elasticsearch', 'gradle', 'get', 'util', 'common', 'xpack', 'joda', 'xml', 'yaml']
import org.elasticsearch.cluster.ClusterModule;
// org                  0	: ['org', 'java', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'fixture']
// elasticsearch        0	: ['elasticsearch', 'gradle', 'get', 'util', 'common', 'xpack', 'joda', 'xml', 'yaml']
// cluster              4	: ['common', 'xpack', 'action', 'index', 'cluster', 'search', 'test', 'client', 'rest', 'transport']
import org.elasticsearch.cluster.EmptyClusterInfoService;
// org                  0	: ['org', 'java', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'fixture']
// elasticsearch        0	: ['elasticsearch', 'gradle', 'get', 'util', 'common', 'xpack', 'joda', 'xml', 'yaml']
// cluster              4	: ['common', 'xpack', 'action', 'index', 'cluster', 'search', 'test', 'client', 'rest', 'transport']
import org.elasticsearch.cluster.node.DiscoveryNode;
// org                  0	: ['org', 'java', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'fixture']
// elasticsearch        0	: ['elasticsearch', 'gradle', 'get', 'util', 'common', 'xpack', 'joda', 'xml', 'yaml']
// cluster              4	: ['common', 'xpack', 'action', 'index', 'cluster', 'search', 'test', 'client', 'rest', 'transport']
// node                 2	: ['metadata', 'routing', 'node', 'service', 'block', 'health', 'action', 'ack', 'shards', 'allocation']
import org.elasticsearch.cluster.node.DiscoveryNodeRole;
// org                  0	: ['org', 'java', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'fixture']
// elasticsearch        0	: ['elasticsearch', 'gradle', 'get', 'util', 'common', 'xpack', 'joda', 'xml', 'yaml']
// cluster              4	: ['common', 'xpack', 'action', 'index', 'cluster', 'search', 'test', 'client', 'rest', 'transport']
// node                 2	: ['metadata', 'routing', 'node', 'service', 'block', 'health', 'action', 'ack', 'shards', 'allocation']
import org.elasticsearch.cluster.routing.ShardRouting;
// org                  0	: ['org', 'java', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'fixture']
// elasticsearch        0	: ['elasticsearch', 'gradle', 'get', 'util', 'common', 'xpack', 'joda', 'xml', 'yaml']
// cluster              4	: ['common', 'xpack', 'action', 'index', 'cluster', 'search', 'test', 'client', 'rest', 'transport']
// routing              1	: ['metadata', 'routing', 'node', 'service', 'block', 'health', 'action', 'ack', 'shards', 'allocation']
import org.elasticsearch.cluster.routing.allocation.AllocationService;
// org                  0	: ['org', 'java', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'fixture']
// elasticsearch        0	: ['elasticsearch', 'gradle', 'get', 'util', 'common', 'xpack', 'joda', 'xml', 'yaml']
// cluster              4	: ['common', 'xpack', 'action', 'index', 'cluster', 'search', 'test', 'client', 'rest', 'transport']
// routing              1	: ['metadata', 'routing', 'node', 'service', 'block', 'health', 'action', 'ack', 'shards', 'allocation']
// allocation           0	: ['allocation', 'elasticsearch', 'primary', 'toString', 'shardId', 'operation', 'currentNodeId', 'state', 'allocationId', 'initializing']
import org.elasticsearch.cluster.routing.allocation.FailedShard;
// org                  0	: ['org', 'java', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'fixture']
// elasticsearch        0	: ['elasticsearch', 'gradle', 'get', 'util', 'common', 'xpack', 'joda', 'xml', 'yaml']
// cluster              4	: ['common', 'xpack', 'action', 'index', 'cluster', 'search', 'test', 'client', 'rest', 'transport']
// routing              1	: ['metadata', 'routing', 'node', 'service', 'block', 'health', 'action', 'ack', 'shards', 'allocation']
// allocation           0	: ['allocation', 'elasticsearch', 'primary', 'toString', 'shardId', 'operation', 'currentNodeId', 'state', 'allocationId', 'initializing']
import org.elasticsearch.cluster.routing.allocation.RoutingAllocation;
// org                  0	: ['org', 'java', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'fixture']
// elasticsearch        0	: ['elasticsearch', 'gradle', 'get', 'util', 'common', 'xpack', 'joda', 'xml', 'yaml']
// cluster              4	: ['common', 'xpack', 'action', 'index', 'cluster', 'search', 'test', 'client', 'rest', 'transport']
// routing              1	: ['metadata', 'routing', 'node', 'service', 'block', 'health', 'action', 'ack', 'shards', 'allocation']
// allocation           0	: ['allocation', 'elasticsearch', 'primary', 'toString', 'shardId', 'operation', 'currentNodeId', 'state', 'allocationId', 'initializing']
import org.elasticsearch.cluster.routing.allocation.allocator.BalancedShardsAllocator;
// org                  0	: ['org', 'java', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'fixture']
// elasticsearch        0	: ['elasticsearch', 'gradle', 'get', 'util', 'common', 'xpack', 'joda', 'xml', 'yaml']
// cluster              4	: ['common', 'xpack', 'action', 'index', 'cluster', 'search', 'test', 'client', 'rest', 'transport']
// routing              1	: ['metadata', 'routing', 'node', 'service', 'block', 'health', 'action', 'ack', 'shards', 'allocation']
// allocation           0	: ['allocation', 'elasticsearch', 'primary', 'toString', 'shardId', 'operation', 'currentNodeId', 'state', 'allocationId', 'initializing']
// allocator            2	: ['decider', 'command', 'allocator', 'routingNodes', 'decision', 'reroute', 'elasticsearch', 'metadata', 'changes', 'get']
import org.elasticsearch.cluster.routing.allocation.decider.AllocationDecider;
// org                  0	: ['org', 'java', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'fixture']
// elasticsearch        0	: ['elasticsearch', 'gradle', 'get', 'util', 'common', 'xpack', 'joda', 'xml', 'yaml']
// cluster              4	: ['common', 'xpack', 'action', 'index', 'cluster', 'search', 'test', 'client', 'rest', 'transport']
// routing              1	: ['metadata', 'routing', 'node', 'service', 'block', 'health', 'action', 'ack', 'shards', 'allocation']
// allocation           0	: ['allocation', 'elasticsearch', 'primary', 'toString', 'shardId', 'operation', 'currentNodeId', 'state', 'allocationId', 'initializing']
// decider              0	: ['decider', 'command', 'allocator', 'routingNodes', 'decision', 'reroute', 'elasticsearch', 'metadata', 'changes', 'get']
import org.elasticsearch.cluster.routing.allocation.decider.AllocationDeciders;
// org                  0	: ['org', 'java', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'fixture']
// elasticsearch        0	: ['elasticsearch', 'gradle', 'get', 'util', 'common', 'xpack', 'joda', 'xml', 'yaml']
// cluster              4	: ['common', 'xpack', 'action', 'index', 'cluster', 'search', 'test', 'client', 'rest', 'transport']
// routing              1	: ['metadata', 'routing', 'node', 'service', 'block', 'health', 'action', 'ack', 'shards', 'allocation']
// allocation           0	: ['allocation', 'elasticsearch', 'primary', 'toString', 'shardId', 'operation', 'currentNodeId', 'state', 'allocationId', 'initializing']
// decider              0	: ['decider', 'command', 'allocator', 'routingNodes', 'decision', 'reroute', 'elasticsearch', 'metadata', 'changes', 'get']
import org.elasticsearch.common.settings.ClusterSettings;
// org                  0	: ['org', 'java', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'fixture']
// elasticsearch        0	: ['elasticsearch', 'gradle', 'get', 'util', 'common', 'xpack', 'joda', 'xml', 'yaml']
// common               0	: ['common', 'xpack', 'action', 'index', 'cluster', 'search', 'test', 'client', 'rest', 'transport']
// settings             1	: ['xcontent', 'settings', 'unit', 'util', 'inject', 'bytes', 'collect', 'geo', 'lucene', 'logging']
import org.elasticsearch.common.settings.Settings;
// org                  0	: ['org', 'java', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'fixture']
// elasticsearch        0	: ['elasticsearch', 'gradle', 'get', 'util', 'common', 'xpack', 'joda', 'xml', 'yaml']
// common               0	: ['common', 'xpack', 'action', 'index', 'cluster', 'search', 'test', 'client', 'rest', 'transport']
// settings             1	: ['xcontent', 'settings', 'unit', 'util', 'inject', 'bytes', 'collect', 'geo', 'lucene', 'logging']
import org.elasticsearch.common.transport.TransportAddress;
// org                  0	: ['org', 'java', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'fixture']
// elasticsearch        0	: ['elasticsearch', 'gradle', 'get', 'util', 'common', 'xpack', 'joda', 'xml', 'yaml']
// common               0	: ['common', 'xpack', 'action', 'index', 'cluster', 'search', 'test', 'client', 'rest', 'transport']
// transport            No	: ['xcontent', 'settings', 'unit', 'util', 'inject', 'bytes', 'collect', 'geo', 'lucene', 'logging']
import org.elasticsearch.common.util.set.Sets;
// org                  0	: ['org', 'java', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'fixture']
// elasticsearch        0	: ['elasticsearch', 'gradle', 'get', 'util', 'common', 'xpack', 'joda', 'xml', 'yaml']
// common               0	: ['common', 'xpack', 'action', 'index', 'cluster', 'search', 'test', 'client', 'rest', 'transport']
// util                 3	: ['xcontent', 'settings', 'unit', 'util', 'inject', 'bytes', 'collect', 'geo', 'lucene', 'logging']
// set                  0	: ['set', 'function', 'iterable', 'stream', 'elasticsearch', 'regex', 'get', 'automaton', 'zip', 'UUID']
import org.elasticsearch.gateway.GatewayAllocator;
// org                  0	: ['org', 'java', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'fixture']
// elasticsearch        0	: ['elasticsearch', 'joda', 'gradle', 'get', 'util', 'common', 'xpack', 'xml', 'yaml']
// gateway              No	: ['common', 'xpack', 'action', 'index', 'cluster', 'search', 'test', 'client', 'rest', 'transport']

import java.util.Collection;
// java                 1	: ['org', 'java', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'fixture']
// util                 0	: ['util', 'time', 'security', 'lang', 'elasticsearch', 'sql', 'get', 'text', 'math']
import java.util.Collections;
// java                 0	: ['java', 'org', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'fixture']
// util                 0	: ['util', 'time', 'security', 'lang', 'elasticsearch', 'sql', 'get', 'text', 'math']
import java.util.List;
// java                 0	: ['java', 'org', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'fixture']
// util                 0	: ['util', 'time', 'security', 'lang', 'elasticsearch', 'sql', 'get', 'text', 'math']
import java.util.Map;
// java                 0	: ['java', 'org', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'fixture']
// util                 0	: ['util', 'time', 'security', 'lang', 'elasticsearch', 'sql', 'get', 'text', 'math']
import java.util.concurrent.atomic.AtomicInteger;
// java                 0	: ['java', 'org', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'fixture']
// util                 0	: ['util', 'time', 'security', 'lang', 'elasticsearch', 'sql', 'get', 'text', 'math']
// atomic               0	: ['atomic', 'locks', 'elasticsearch', 'get', 'util', 'common', 'xpack', 'put', 'action', 'field']

public final class Allocators {
    private static class NoopGatewayAllocator extends GatewayAllocator {
        public static final NoopGatewayAllocator INSTANCE = new NoopGatewayAllocator();
// INSTANCE             No	: ['get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'j', 'allocation', 'getValue']

        @Override
        public void applyStartedShards(List<ShardRouting> startedShards, RoutingAllocation allocation) {
// startedShards        1	: ['shards', 'startedShards', 'localShards', 'shardRoutings', 'assignedShards', 'allShards', 'ignored', 'unassignedShards', 'listener', 'metadata']
// allocation           0	: ['allocation', 'routingAllocation', 'allocationWithMissingSourceIndex', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token']
            // noop
        }

        @Override
        public void applyFailedShards(List<FailedShard> failedShards, RoutingAllocation allocation) {
// failedShards         0	: ['failedShards', 'shardsToFail', 'failedShardsToBeApplied', 'listener', 'metadata', 'PARSER', 'entry', 'map', 'buckets', 'future']
// allocation           0	: ['allocation', 'routingAllocation', 'allocationWithMissingSourceIndex', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token']
            // noop
        }

        @Override
        public void allocateUnassigned(
            ShardRouting shardRouting,
// shardRouting         0	: ['shardRouting', 'shard', 'routing', 'unassignedShard', 'replica', 'startedShard', 'shardEntry', 'replicaShard', 'initializingShard', 'get']
            RoutingAllocation allocation,
// allocation           0	: ['allocation', 'routingAllocation', 'allocationWithMissingSourceIndex', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token']
            UnassignedAllocationHandler unassignedAllocationHandler
// unassignedAllocationHandler 0	: ['unassignedAllocationHandler', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'j', 'allocation']
        ) {
            // noop
        }
    }

    private Allocators() {
        throw new AssertionError("Do not instantiate");
    }

    public static AllocationService createAllocationService(Settings settings) {
// settings             0	: ['settings', 'indexSettings', 'nodeSettings', 'current', 'newSettings', 'globalSettings', 'baseSettings', 'source', 'value', 'get']
        return createAllocationService(settings, new ClusterSettings(Settings.EMPTY, ClusterSettings.BUILT_IN_CLUSTER_SETTINGS));
// settings             0	: ['settings', 'build', 'allocationServiceSettings', 'request', 'response', 'e', 'client', 'i', 'parser', 'get']
// EMPTY                0	: ['EMPTY', 'builder', 'elasticsearch', 'get', 'util', 'common', 'xpack', 'fromXContent', 'toString', 'FORMAT_PARAMS']
// BUILT_IN_CLUSTER_SETTINGS 0	: ['BUILT_IN_CLUSTER_SETTINGS', 'elasticsearch', 'get', 'util', 'common', 'xpack', 'put', 'action', 'field', 'BUILT_IN_SETTING_UPGRADERS']
    }

    public static AllocationService createAllocationService(Settings settings, ClusterSettings clusterSettings) {
// settings             0	: ['settings', 'indexSettings', 'nodeSettings', 'current', 'newSettings', 'globalSettings', 'baseSettings', 'source', 'value', 'get']
// clusterSettings      0	: ['clusterSettings', 'settings', 'nss', 'service', 'settingsService', 'EMPTY_CLUSTER_SETTINGS', 'clusterSettings2', 'clusterSettingsA', 'emptyClusterSettings', 'get']
        return new AllocationService(
            defaultAllocationDeciders(settings, clusterSettings),
// settings             5	: ['request', 'response', 'e', 'client', 'i', 'settings', 'parser', 'index', 'randomBoolean', 'in']
// clusterSettings      2	: ['clusterService', 'threadPool', 'clusterSettings', 'licenseState', 'clientName', 'transportService', 'environment', 'configPath', 'version', 'client']
            NoopGatewayAllocator.INSTANCE,
// INSTANCE             No	: ['elasticsearch', 'get', 'util', 'common', 'xpack', 'put', 'action', 'index', 'field', 'add']
            new BalancedShardsAllocator(settings),
// settings             0	: ['settings', 'SETTINGS', 'balancerSettings', 'request', 'response', 'e', 'client', 'i', 'parser', 'get']
            EmptyClusterInfoService.INSTANCE
// INSTANCE             0	: ['INSTANCE', 'elasticsearch', 'get', 'util', 'common', 'xpack', 'put', 'action', 'index', 'field']
        );
    }

    public static AllocationDeciders defaultAllocationDeciders(Settings settings, ClusterSettings clusterSettings) {
// settings             0	: ['settings', 'indexSettings', 'nodeSettings', 'current', 'newSettings', 'globalSettings', 'baseSettings', 'source', 'value', 'toApply']
// clusterSettings      0	: ['clusterSettings', 'settings', 'nss', 'service', 'settingsService', 'EMPTY_CLUSTER_SETTINGS', 'clusterSettings2', 'clusterSettingsA', 'emptyClusterSettings', 'get']
        Collection<AllocationDecider> deciders = ClusterModule.createAllocationDeciders(settings, clusterSettings, Collections.emptyList());
// deciders             1	: ['allocations', 'deciders', 'deciderList', 'allocationDeciders', 'iter', 'listener', 'metadata', 'PARSER', 'entry', 'map']
// settings             0	: ['settings', 'request', 'response', 'e', 'client', 'i', 'parser', 'randomBoolean', 'in', 'get']
// clusterSettings      0	: ['clusterSettings', 'clusterService', 'threadPool', 'licenseState', 'clientName', 'transportService', 'configPath', 'version', 'equalTo', 'is']
// emptyList            0	: ['emptyList', 'emptyMap', 'singleton', 'elasticsearch', 'shuffle', 'sort', 'get', 'util', 'reverse', 'list']
        return new AllocationDeciders(deciders);
// deciders             0	: ['deciders', 'allocationDeciders', 'deciderList', 'request', 'response', 'e', 'client', 'i', 'settings', 'parser']
    }

    private static final AtomicInteger portGenerator = new AtomicInteger();
// portGenerator        5	: ['nodeIdGenerator', 'nextUniqueValue', 'counter', 'count', 'randomInt', 'portGenerator', 'nextUniqueId', 'numInvalidation', 'get', 'core']

    public static DiscoveryNode newNode(String nodeId, Map<String, String> attributes) {
// newNode              0	: ['newNode', 'localNode', 'node', 'discoveryNode', 'node1', 'sourceNode', 'otherNode', 'node2', 'masterNode', 'get']
// nodeId               0	: ['nodeId', 'nodeName', 'name', 'index', 'id', 'field', 'jobId', 'source', 'key', 'fieldName']
// attributes           4	: ['headers', 'params', 'expectedParams', 'entry', 'attributes', 'map', 'options', 'parameters', 'userData', 'checkedFields']
        return new DiscoveryNode(
            "",
            nodeId,
// nodeId               No	: ['handleB', 'transportAddress', 'id', 'clusterAlias', 'otherNode', 'e', 'ex', 'request', 'result', 'validationException']
            new TransportAddress(TransportAddress.META_ADDRESS, portGenerator.incrementAndGet()),
// META_ADDRESS         0	: ['META_ADDRESS', 'elasticsearch', 'get', 'util', 'common', 'xpack', 'put', 'action', 'index', 'field']
// portGenerator        2	: ['nodePort', 'randomInt', 'portGenerator', 'equalTo', 'is', 'e', 'request', 'exec', 'listener', 'parser']
            attributes,
// attributes           No	: ['connection', 'request', 'equalTo', 'e', 'is', 'entry', 'nullValue', 'randomBoolean', 'eq', 'threadPool']
            Sets.newHashSet(DiscoveryNodeRole.MASTER_ROLE, DiscoveryNodeRole.DATA_ROLE),
// MASTER_ROLE          0	: ['MASTER_ROLE', 'DATA_ROLE', 'BUILT_IN_ROLES', 'INGEST_ROLE', 'REMOTE_CLUSTER_CLIENT_ROLE', 'elasticsearch', 'LEGACY_ROLES', 'get', 'util', 'common']
// DATA_ROLE            0	: ['DATA_ROLE', 'REMOTE_CLUSTER_CLIENT_ROLE', 'INGEST_ROLE', 'BUILT_IN_ROLES', 'MASTER_ROLE', 'elasticsearch', 'get', 'util', 'common', 'xpack']
            Version.CURRENT
// CURRENT              0	: ['CURRENT', 'V_7_0_0', 'V_8_0_0', 'fromId', 'V_7_7_0', 'V_7_6_0', 'V_7_4_0', 'elasticsearch', 'V_7_2_0', 'min']
        );
    }
}
