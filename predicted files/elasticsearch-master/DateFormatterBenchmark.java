// Path id: 4
// Path to file: C:\Users\Igor\IdeaProjects\SLP-Core-0.3\java-projects\elasticsearch-master\benchmarks\src\main\java\org\elasticsearch\benchmark\time\DateFormatterBenchmark.java
// Number of identifiers: 45	Accuracy: 75.56%	MRR: 78.52%
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
package org.elasticsearch.benchmark.time;
// org                  0	: ['org', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'j', 'allocation']
// elasticsearch        0	: ['elasticsearch', 'get', 'util', 'common', 'xpack', 'gradle', 'joda', 'xml', 'yaml']
// benchmark            No	: ['xpack', 'common', 'action', 'search', 'index', 'client', 'cluster', 'test', 'rest', 'analysis']
// time                 2	: ['routing', 'indices', 'time', 'fs', 'metrics', 'ops', 'rest', 'run', 'elasticsearch', 'get']

import org.elasticsearch.common.joda.Joda;
// org                  0	: ['org', 'java', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'fixture']
// elasticsearch        0	: ['elasticsearch', 'joda', 'gradle', 'get', 'util', 'common', 'xpack', 'xml', 'yaml']
// common               0	: ['common', 'xpack', 'action', 'index', 'cluster', 'search', 'test', 'client', 'rest', 'transport']
// joda                 No	: ['xcontent', 'settings', 'unit', 'util', 'inject', 'bytes', 'collect', 'geo', 'lucene', 'logging']
import org.elasticsearch.common.time.DateFormatter;
// org                  0	: ['org', 'java', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'fixture']
// elasticsearch        1	: ['joda', 'elasticsearch', 'gradle', 'get', 'util', 'common', 'xpack', 'xml', 'yaml']
// common               0	: ['common', 'xpack', 'action', 'index', 'cluster', 'search', 'test', 'client', 'rest', 'transport']
// time                 No	: ['xcontent', 'settings', 'unit', 'util', 'inject', 'bytes', 'collect', 'geo', 'lucene', 'logging']
import org.openjdk.jmh.annotations.Benchmark;
// org                  0	: ['org', 'java', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'fixture']
// annotations          0	: ['annotations', 'elasticsearch', 'get', 'util', 'common', 'xpack', 'put', 'action', 'index', 'field']
import org.openjdk.jmh.annotations.BenchmarkMode;
// org                  0	: ['org', 'java', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'fixture']
// annotations          0	: ['annotations', 'elasticsearch', 'get', 'util', 'common', 'xpack', 'put', 'action', 'index', 'field']
import org.openjdk.jmh.annotations.Fork;
// org                  0	: ['org', 'java', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'fixture']
// annotations          0	: ['annotations', 'elasticsearch', 'get', 'util', 'common', 'xpack', 'put', 'action', 'index', 'field']
import org.openjdk.jmh.annotations.Measurement;
// org                  0	: ['org', 'java', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'fixture']
// annotations          0	: ['annotations', 'elasticsearch', 'get', 'util', 'common', 'xpack', 'put', 'action', 'index', 'field']
import org.openjdk.jmh.annotations.Mode;
// org                  0	: ['org', 'java', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'fixture']
// annotations          0	: ['annotations', 'elasticsearch', 'get', 'util', 'common', 'xpack', 'put', 'action', 'index', 'field']
import org.openjdk.jmh.annotations.OutputTimeUnit;
// org                  0	: ['org', 'java', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'fixture']
// annotations          0	: ['annotations', 'elasticsearch', 'get', 'util', 'common', 'xpack', 'put', 'action', 'index', 'field']
import org.openjdk.jmh.annotations.Scope;
// org                  0	: ['org', 'java', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'fixture']
// annotations          0	: ['annotations', 'elasticsearch', 'get', 'util', 'common', 'xpack', 'put', 'action', 'index', 'field']
import org.openjdk.jmh.annotations.State;
// org                  0	: ['org', 'java', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'fixture']
// annotations          0	: ['annotations', 'elasticsearch', 'get', 'util', 'common', 'xpack', 'put', 'action', 'index', 'field']
// State                0	: ['State', 'elasticsearch', 'get', 'length', 'add', 'util', 'common', 'size', 'textMinusMarkup']
import org.openjdk.jmh.annotations.Warmup;
// org                  0	: ['org', 'java', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'fixture']
// annotations          0	: ['annotations', 'elasticsearch', 'get', 'util', 'common', 'xpack', 'put', 'action', 'index', 'field']

import java.time.temporal.TemporalAccessor;
// java                 0	: ['java', 'org', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'fixture']
// time                 1	: ['util', 'time', 'security', 'lang', 'elasticsearch', 'sql', 'get', 'text', 'math']
// temporal             0	: ['temporal', 'format', 'zone', 'elasticsearch', 'chrono', 'get', 'field', 'equals', 'hour', 'minute']
import java.util.concurrent.TimeUnit;
// java                 0	: ['java', 'org', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'fixture']
// util                 0	: ['util', 'time', 'security', 'lang', 'elasticsearch', 'sql', 'get', 'text', 'math']

@Fork(3)
@Warmup(iterations = 10)
// iterations           0	: ['iterations', 'request', 'response', 'e', 'client', 'i', 'settings', 'parser', 'randomBoolean', 'in']
@Measurement(iterations = 10)
// iterations           0	: ['iterations', 'request', 'response', 'e', 'client', 'i', 'settings', 'parser', 'randomBoolean', 'in']
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
// State                0	: ['State', 'get', 'core', 'lucene', 'stream', 'id', 'type', 'token', 'j', 'GET']
@SuppressWarnings("unused") // invoked by benchmarking framework
public class DateFormatterBenchmark {

    private final DateFormatter javaFormatter = DateFormatter.forPattern("8year_month_day||ordinal_date||epoch_millis");
// javaFormatter        No	: ['formatter', 'dateTimeFormatter', 'DATE_TIME_FORMATTER', 'FORMATTER', 'DATE_FORMATTER', 'dateFormatter', 'UTC_DATE_TIME_FORMATTER', 'TIME', 'get', 'core']
    private final DateFormatter jodaFormatter = Joda.forPattern("year_month_day||ordinal_date||epoch_millis");
// jodaFormatter        No	: ['formatter', 'dateTimeFormatter', 'DATE_TIME_FORMATTER', 'FORMATTER', 'DATE_FORMATTER', 'dateFormatter', 'UTC_DATE_TIME_FORMATTER', 'TIME', 'get', 'core']

    @Benchmark
    public TemporalAccessor parseJavaDate() {
        return javaFormatter.parse("1234567890");
// javaFormatter        No	: ['NAME', 'getToken', 'name', 'id', 'in', 'field', 'delegate', 'TYPE', 'source', 'value']
// parse                0	: ['parse', 'format', 'elasticsearch', 'get', 'util', 'common', 'xpack', 'put', 'action', 'field']
    }

    @Benchmark
    public TemporalAccessor parseJodaDate() {
        return jodaFormatter.parse("1234567890");
// jodaFormatter        No	: ['NAME', 'getToken', 'name', 'id', 'in', 'field', 'delegate', 'TYPE', 'source', 'value']
// parse                No	: ['format', 'elasticsearch', 'get', 'util', 'common', 'xpack', 'put', 'action', 'index', 'field']
    }
}
