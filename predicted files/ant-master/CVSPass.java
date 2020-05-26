// Type of training: selfTestingIdentifier
// Path to file: C:\Users\Igor\IdeaProjects\SLP-Core-0.3\java-projects\ant-master\src\main\org\apache\tools\ant\taskdefs\CVSPass.java
// Number of identifiers: 12	Accuracy: 33.33%	MRR: 48.61%
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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.util.FileUtils;

/**
 * Adds an new entry to a CVS password file.
 *
 * @since Ant 1.4
 *
 * @ant.task category="scm"
 */
public class CVSPass extends Task {

    /**
     * CVS Root
     */
    private String cvsRoot = null;

    /**
     * Password file to add password to
     */
    private File passFile = null;

    /**
     * Password to add to file
     */
    private String password = null;

    /**
     * Array contain char conversion data
     */
    private final char[] shifts = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 114, 120, 53, 79, 96, 109, 72, 108, 70, 64, 76, 67, 116, 74, 68, 87, 111, 52, 75, 119, 49, 34, 82, 81, 95, 65, 112, 86, 118, 110, 122, 105, 41, 57, 83, 43, 46, 102, 40, 89, 38, 103, 45, 50, 42, 123, 91, 35, 125, 55, 54, 66, 124, 126, 59, 47, 92, 71, 115, 78, 88, 107, 106, 56, 36, 121, 117, 104, 101, 100, 69, 73, 99, 63, 94, 93, 39, 37, 61, 48, 58, 113, 32, 90, 44, 98, 60, 51, 33, 97, 62, 77, 84, 80, 85, 223, 225, 216, 187, 166, 229, 189, 222, 188, 141, 249, 148, 200, 184, 136, 248, 190, 199, 170, 181, 204, 138, 232, 218, 183, 255, 234, 220, 247, 213, 203, 226, 193, 174, 172, 228, 252, 217, 201, 131, 230, 197, 211, 145, 238, 161, 179, 160, 212, 207, 221, 254, 173, 202, 146, 224, 151, 140, 196, 205, 130, 135, 133, 143, 246, 192, 159, 244, 239, 185, 168, 215, 144, 139, 165, 180, 157, 147, 186, 214, 176, 227, 231, 219, 169, 175, 156, 206, 198, 129, 164, 150, 210, 154, 177, 134, 127, 182, 128, 158, 208, 162, 132, 167, 209, 149, 241, 153, 251, 237, 236, 171, 195, 243, 233, 253, 240, 194, 250, 191, 155, 142, 137, 245, 235, 163, 242, 178, 152 };

    /**
     * Create a CVS task using the default cvspass file location.
     */
    public CVSPass() {
        passFile = new File(System.getProperty("cygwin.user.home", System.getProperty("user.home")) + File.separatorChar + ".cvspass");
    }

    /**
     * Does the work.
     *
     * @exception BuildException if something goes wrong with the build
     */
    @Override
    public final void execute() throws BuildException {
        if (cvsRoot == null) {
            throw new BuildException("cvsroot is required");
        }
        if (password == null) {
            throw new BuildException("password is required");
        }
        log("cvsRoot: " + cvsRoot, Project.MSG_DEBUG);
        log("password: " + password, Project.MSG_DEBUG);
        log("passFile: " + passFile, Project.MSG_DEBUG);
        BufferedReader reader = null;
// reader               1	: [('in', 0.522616889490009), ('reader', 0.30825839092045865), ('r', 0.2084291000789992), ('ejbFiles', 0.11191543155148659), ('in2', 0.10366755888727523), ('patternReader', 0.1036670059122697), ('br', 0.07622043127901296), ('fos', 0.059466103466623295), ('is', 0.05399805318790945), ('file', 0.051947361555565716)]
        BufferedWriter writer = null;
// writer               No	: [('bw', 0.790182598127126), ('env', 0.6252681409213232), ('out', 0.07734163741709671), ('process', 0.07108416875865967), ('f', 0.07079021337549299), ('commandList', 0.07058702213337943), ('commandIterator', 0.07058702213337943), ('newJarStream', 0.06492045101047864), ('fos', 0.05089817184683992), ('result', 0.047499209232673166)]
        try {
            StringBuilder buf = new StringBuilder();
// buf                  2	: [('sb', 0.6073385999779719), ('b', 0.19392579171369034), ('buf', 0.03999601582283837), ('cmd', 0.023463085295553472), ('result', 0.013817783133197939), ('log', 0.011358116538752792), ('out', 0.007920177823920762), ('name', 0.007508021861697821), ('i', 0.0072208984107825095), ('message', 0.006915190386856866)]
            if (passFile.exists()) {
                reader = new BufferedReader(new FileReader(passFile));
                String line = null;
// line                 0	: [('line', 0.2183362866914655), ('ch', 0.19906284443980507), ('connector', 0.1923247395873434), ('val', 0.19221195204668814), ('output', 0.1879161353851141), ('te', 0.14797877490531952), ('index', 0.14538015785063618), ('entry', 0.13271450914920205), ('file', 0.07133955338104306), ('delim', 0.05104787636212244)]
                while ((line = reader.readLine()) != null) {
                    if (!line.startsWith(cvsRoot)) {
                        buf.append(line).append(System.lineSeparator());
                    }
                }
            }
            String pwdfile = buf.toString() + cvsRoot + " A" + mangle(password);
// pwdfile              No	: [('rv', 0.470238093049741), ('className', 0.3142741232451317), ('name', 0.24557892186166752), ('s', 0.15857386652947827), ('sdf', 0.15710723197534948), ('sb', 0.0980657770712247), ('nodeText', 0.06844941904482083), ('chars', 0.032116451476845234), ('lastpathelement', 0.023800331494912123), ('b', 0.021688507690893664)]
            log("Writing -> " + pwdfile, Project.MSG_DEBUG);
            writer = new BufferedWriter(new FileWriter(passFile));
            writer.write(pwdfile);
            writer.newLine();
        } catch (IOException e) {
// e                    0	: [('e', 0.405729469964719), ('ioe', 0.31764641399466065), ('ex', 0.12990932333622068), ('msg', 0.10219735856518988), ('message', 0.07231955788507098), ('s', 0.023973260873358767), ('ioex', 0.020484517696587202), ('t', 0.018260886469848702), ('ie', 0.01784126892436277), ('exc', 0.01379596141978942)]
            throw new BuildException(e);
        } finally {
            FileUtils.close(reader);
            FileUtils.close(writer);
        }
    }

    private final String mangle(String password) {
// password             No	: [('name', 0.4157798963589559), ('s', 0.22819735693020804), ('jspUri', 0.20768774112305483), ('origin', 0.04032649177186163), ('value', 0.020553450700475925), ('patArr', 0.020164511739395433), ('escChs', 0.020163716858923337), ('message', 0.016855637108445908), ('msg', 0.015279308859024142), ('uri', 0.014185789753684083)]
        StringBuilder buf = new StringBuilder();
// buf                  1	: [('result', 0.42888748436213125), ('buf', 0.24036939013624278), ('sb', 0.2028847471257552), ('urls', 0.1264359526166586), ('cmd', 0.06536492714365529), ('message', 0.042132267566166566), ('b', 0.031719507955682624), ('sb1', 0.02735589297800532), ('cleanedBuffer', 0.02735589297800532), ('out', 0.01434080223998223)]
        for (final char ch : password.toCharArray()) {
// ch                   1	: [('c', 0.4727738175480176), ('ch', 0.3941880855845356), ('i', 0.08951977506681051), ('subChar', 0.032562904331113536), ('j', 0.012418135590539106), ('offset', 0.009479616208389843), ('counter', 0.007155222176126551), ('t', 0.006759880616915599), ('zz', 0.005807331008237539), ('index', 0.005064918862159472)]
            buf.append(shifts[ch]);
        }
        return buf.toString();
    }

    /**
     * The CVS repository to add an entry for.
     *
     * @param cvsRoot the CVS repository
     */
    public void setCvsroot(String cvsRoot) {
// cvsRoot              No	: [('root', 0.8750830765539778), ('name', 0.06155535684399712), ('s', 0.02166488489538175), ('value', 0.020552717844961824), ('message', 0.016854965057537783), ('msg', 0.015278668810540214), ('uri', 0.01418545372823002), ('target', 0.01388785146416872), ('line', 0.011559443657118192), ('filename', 0.011435198447237532)]
        this.cvsRoot = cvsRoot;
    }

    /**
     * Password file to add the entry to.
     *
     * @param passFile the password file.
     */
    public void setPassfile(File passFile) {
// passFile             0	: [('passFile', 0.9375036739465995), ('file', 0.014040441056111016), ('basedir', 0.0071160880036422085), ('f', 0.006126841810280782), ('dir', 0.0026799861745751934), ('src', 0.0024702739310603723), ('sourceFile', 0.0023132675695832746), ('srcDir', 0.002037521501216924), ('baseDir', 0.001733254704051029), ('destDir', 0.0017227423948636825)]
        this.passFile = passFile;
    }

    /**
     * Password to be added to the password file.
     *
     * @param password the password.
     */
    public void setPassword(String password) {
// password             0	: [('password', 0.7755388826887527), ('p', 0.16872419387040477), ('name', 0.00769441960549964), ('project', 0.004682804197908751), ('s', 0.0027081106119227188), ('value', 0.002569089730620228), ('message', 0.002106870632192223), ('msg', 0.0019098336013175267), ('uri', 0.0017731817160287525), ('target', 0.00173598143302109)]
        this.password = password;
    }
}
