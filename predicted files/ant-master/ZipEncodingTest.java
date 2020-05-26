// Type of training: selfTestingIdentifier
// Path to file: C:\Users\Igor\IdeaProjects\SLP-Core-0.3\java-projects\ant-master\src\tests\junit\org\apache\tools\zip\ZipEncodingTest.java
// Number of identifiers: 10	Accuracy: 20.00%	MRR: 33.94%
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
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.tools.zip;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test zip encodings.
 */
public class ZipEncodingTest {

    private static final String UNENC_STRING = "\u2016";

    // stress test for internal grow method.
    private static final String BAD_STRING = "\u2016\u2015\u2016\u2015\u2016\u2015\u2016\u2015\u2016\u2015\u2016";

    private static final String BAD_STRING_ENC = "%U2016%U2015%U2016%U2015%U2016%U2015%U2016%U2015%U2016%U2015%U2016";

    @Test
    public void testSimpleCp437Encoding() throws IOException {
        doSimpleEncodingTest("Cp437", null);
    }

    @Test
    public void testSimpleCp850Encoding() throws IOException {
        doSimpleEncodingTest("Cp850", null);
    }

    @Test
    public void testNioCp1252Encoding() throws IOException {
        // CP1252 has some undefined code points, these are
        // the defined ones
        // retrieved by
        // awk '/^0x/ && NF>2 {print $1;}' CP1252.TXT
        byte[] b = new byte[] { 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, 0x0F, 0x10, 0x11, 0x12, 0x13, 0x14, 0x15, 0x16, 0x17, 0x18, 0x19, 0x1A, 0x1B, 0x1C, 0x1D, 0x1E, 0x1F, 0x20, 0x21, 0x22, 0x23, 0x24, 0x25, 0x26, 0x27, 0x28, 0x29, 0x2A, 0x2B, 0x2C, 0x2D, 0x2E, 0x2F, 0x30, 0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38, 0x39, 0x3A, 0x3B, 0x3C, 0x3D, 0x3E, 0x3F, 0x40, 0x41, 0x42, 0x43, 0x44, 0x45, 0x46, 0x47, 0x48, 0x49, 0x4A, 0x4B, 0x4C, 0x4D, 0x4E, 0x4F, 0x50, 0x51, 0x52, 0x53, 0x54, 0x55, 0x56, 0x57, 0x58, 0x59, 0x5A, 0x5B, 0x5C, 0x5D, 0x5E, 0x5F, 0x60, 0x61, 0x62, 0x63, 0x64, 0x65, 0x66, 0x67, 0x68, 0x69, 0x6A, 0x6B, 0x6C, 0x6D, 0x6E, 0x6F, 0x70, 0x71, 0x72, 0x73, 0x74, 0x75, 0x76, 0x77, 0x78, 0x79, 0x7A, 0x7B, 0x7C, 0x7D, 0x7E, 0x7F, (byte) 0x80, (byte) 0x82, (byte) 0x83, (byte) 0x84, (byte) 0x85, (byte) 0x86, (byte) 0x87, (byte) 0x88, (byte) 0x89, (byte) 0x8A, (byte) 0x8B, (byte) 0x8C, (byte) 0x8E, (byte) 0x91, (byte) 0x92, (byte) 0x93, (byte) 0x94, (byte) 0x95, (byte) 0x96, (byte) 0x97, (byte) 0x98, (byte) 0x99, (byte) 0x9A, (byte) 0x9B, (byte) 0x9C, (byte) 0x9E, (byte) 0x9F, (byte) 0xA0, (byte) 0xA1, (byte) 0xA2, (byte) 0xA3, (byte) 0xA4, (byte) 0xA5, (byte) 0xA6, (byte) 0xA7, (byte) 0xA8, (byte) 0xA9, (byte) 0xAA, (byte) 0xAB, (byte) 0xAC, (byte) 0xAD, (byte) 0xAE, (byte) 0xAF, (byte) 0xB0, (byte) 0xB1, (byte) 0xB2, (byte) 0xB3, (byte) 0xB4, (byte) 0xB5, (byte) 0xB6, (byte) 0xB7, (byte) 0xB8, (byte) 0xB9, (byte) 0xBA, (byte) 0xBB, (byte) 0xBC, (byte) 0xBD, (byte) 0xBE, (byte) 0xBF, (byte) 0xC0, (byte) 0xC1, (byte) 0xC2, (byte) 0xC3, (byte) 0xC4, (byte) 0xC5, (byte) 0xC6, (byte) 0xC7, (byte) 0xC8, (byte) 0xC9, (byte) 0xCA, (byte) 0xCB, (byte) 0xCC, (byte) 0xCD, (byte) 0xCE, (byte) 0xCF, (byte) 0xD0, (byte) 0xD1, (byte) 0xD2, (byte) 0xD3, (byte) 0xD4, (byte) 0xD5, (byte) 0xD6, (byte) 0xD7, (byte) 0xD8, (byte) 0xD9, (byte) 0xDA, (byte) 0xDB, (byte) 0xDC, (byte) 0xDD, (byte) 0xDE, (byte) 0xDF, (byte) 0xE0, (byte) 0xE1, (byte) 0xE2, (byte) 0xE3, (byte) 0xE4, (byte) 0xE5, (byte) 0xE6, (byte) 0xE7, (byte) 0xE8, (byte) 0xE9, (byte) 0xEA, (byte) 0xEB, (byte) 0xEC, (byte) 0xED, (byte) 0xEE, (byte) 0xEF, (byte) 0xF0, (byte) 0xF1, (byte) 0xF2, (byte) 0xF3, (byte) 0xF4, (byte) 0xF5, (byte) 0xF6, (byte) 0xF7, (byte) 0xF8, (byte) 0xF9, (byte) 0xFA, (byte) 0xFB, (byte) 0xFC, (byte) 0xFD, (byte) 0xFE, (byte) 0xFF };
// b                    8	: [('buffer', 0.24544832302398245), ('buf', 0.12483825185942941), ('extra', 0.11446372517910958), ('centralFileHeader', 0.10605164054796588), ('data', 0.04380823644270802), ('result', 0.041230630070973225), ('bytes', 0.027811681388732204), ('local', 0.020724515667095576), ('b', 0.01664307329888357), ('len_t', 0.01374686540892908)]
        doSimpleEncodingTest("Cp1252", b);
    }

    private static void assertByteEquals(byte[] expected, ByteBuffer actual) {
// expected             5	: [('FOO', 0.5197004280114027), ('data', 0.15128021913491022), ('buffer', 0.10636762122963327), ('bytes', 0.10586980319697452), ('b', 0.10095461345637997), ('expected', 0.061876804675242536), ('expectedByte', 0.059098879963001914), ('buf', 0.05706971337978534), ('expectedFiles', 0.044333725007196255), ('expectedDirectories', 0.04394720158594389)]
// actual               3	: [('inResult', 0.9375094061289936), ('s', 0.6628975306011521), ('name', 0.5363065845898859), ('actual', 0.10839202520903257), ('en', 0.030623927723341136), ('b', 0.025764086745105674), ('bb', 0.0204192931230212), ('commentB', 0.020419016639187763), ('orig', 0.012568643390464327), ('out', 0.010489483453137933)]
        assertEquals(expected.length, actual.limit());
        for (byte expectedByte : expected) {
// expectedByte         4	: [('digestByte', 0.8263395626706213), ('expected', 0.16011913701290542), ('expectedFiles', 0.08866744959895513), ('expectedDirectories', 0.08789440299704818), ('expectedByte', 0.08183230000529829), ('c', 0.07310172172362532), ('fixture', 0.03940244924995284), ('FOO', 0.039400855951543225), ('fixtureBwt', 0.03939967737337992), ('b', 0.026454570772262424)]
            assertEquals(expectedByte, actual.get());
        }
    }

    private void doSimpleEncodingTest(String name, byte[] testBytes) throws IOException {
// name                 1	: [('encoding', 0.928602948921183), ('name', 0.06134126634346104), ('s', 0.02166488489538175), ('value', 0.020552717844961824), ('message', 0.016854965057537783), ('msg', 0.015278668810540214), ('uri', 0.01418545372823002), ('target', 0.01388785146416872), ('line', 0.011559443657118192), ('filename', 0.011435198447237532)]
// testBytes            0	: [('testBytes', 0.6250065397269797), ('result', 0.20196990055421488), ('buf', 0.19361589950027966), ('fast', 0.12662668641557273), ('args', 0.10834801625457664), ('fileName', 0.10806114463116655), ('value', 0.08612027959218281), ('f', 0.08608589095750153), ('b', 0.08571487068206653), ('comment', 0.08528383168713956)]
        ZipEncoding enc = ZipEncodingHelper.getZipEncoding(name);
// enc                  No	: [('zipEncoding', 0.9397337069330353), ('su', 0.3458944450831017), ('f', 0.19232214454522012), ('ca', 0.14537473688608016), ('index', 0.07819279198922241), ('testMailClient', 0.06716509379924022), ('dd', 0.06244503182697079), ('i', 0.043547921168365165), ('first', 0.04323769291026381), ('acl', 0.028829796953308392)]
        if (testBytes == null) {
            testBytes = new byte[256];
            for (int i = 0; i < 256; ++i) {
// i                    0	: [('i', 0.8524843042285956), ('j', 0.10989554189302413), ('b', 0.08028142350395921), ('v', 0.041513520568251076), ('ch', 0.039353237956439545), ('val', 0.03932526588866402), ('nInUseShadow', 0.039322293608562986), ('bt', 0.03932212080158037), ('res', 0.03743073895098373), ('name', 0.036699651096041376)]
                testBytes[i] = (byte) i;
            }
        }
        String decoded = enc.decode(testBytes);
// decoded              No	: [('name', 0.5342769633144484), ('ze', 0.41378347924000125), ('cb', 0.39047931504850836), ('lastpathelement', 0.2142673296564909), ('s2', 0.10718166349219779), ('ejbcClassName', 0.09523189493914157), ('antFileProp', 0.09523161845530813), ('pathURL', 0.09523148021339141), ('entryName', 0.03334789116935769), ('comm', 0.0333470688060501)]
        assertTrue(enc.canEncode(decoded));
        ByteBuffer encoded = enc.encode(decoded);
// encoded              5	: [('name', 0.30595164420736604), ('data', 0.29817928179202086), ('b', 0.1001910208689554), ('ne', 0.09630463230115967), ('out', 0.049497369941390396), ('encoded', 0.04815267057621594), ('l', 0.008115925703945242), ('offset', 0.007953177657533596), ('en', 0.0076559819170756716), ('e', 0.007255536086384289)]
        assertByteEquals(testBytes, encoded);
        assertFalse(enc.canEncode(UNENC_STRING));
        assertByteEquals("%U2016".getBytes(StandardCharsets.US_ASCII), enc.encode(UNENC_STRING));
        assertFalse(enc.canEncode(BAD_STRING));
        assertByteEquals(BAD_STRING_ENC.getBytes(StandardCharsets.US_ASCII), enc.encode(BAD_STRING));
    }
}
