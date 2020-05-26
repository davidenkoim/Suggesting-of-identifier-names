// Type of training: selfTestingIdentifier
// Path to file: C:\Users\Igor\IdeaProjects\SLP-Core-0.3\java-projects\elasticsearch-master\modules\lang-painless\src\main\java\org\elasticsearch\painless\SimpleChecksAdapter.java
// Number of identifiers: 14	Accuracy: 57.14%	MRR: 58.93%
// True 	Rank of true in predictions : [(predicted token, probability of a token), ...]

package org.elasticsearch.painless;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.util.CheckClassAdapter;
import org.objectweb.asm.util.CheckMethodAdapter;
import java.util.HashMap;

public class SimpleChecksAdapter extends CheckClassAdapter {

    public SimpleChecksAdapter(ClassVisitor cv) {
// cv                   No	: [('in', 0.9375589306092249), ('cw', 0.20000363758976678), ('classVisitor', 0.20000224482852547), ('visitor', 0.10004952728707615), ('e', 0.0009163961683903215), ('equalTo', 0.0005208163252309732), ('request', 0.0004748016139269682), ('i', 0.000330164996583893), ('context', 0.0003221156602767373), ('listener', 0.00027448141085569314)]
        super(WriterConstants.ASM_VERSION, cv, false);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
// access               0	: [('access', 0.8750473313811411), ('modifiers', 0.36364654337422436), ('i', 0.04630200279084061), ('j', 0.0037269810793415176), ('doc', 0.0020234582656691396), ('request', 0.0014360479775471623), ('e', 0.0012537073896977371), ('response', 0.001253044129369899), ('size', 0.001148151866984641), ('builder', 0.0011322324203660967)]
// name                 0	: [('name', 0.7873404902710268), ('method', 0.4392505036747888), ('delegatorMethodName', 0.09376045671270276), ('action', 0.0026743642059401554), ('message', 0.0021456505486165595), ('type', 0.0019447485167131172), ('field', 0.0018325698544126728), ('fieldName', 0.0018198692126168432), ('id', 0.0017125065414393783), ('index', 0.0016599106443753587)]
// desc                 0	: [('desc', 0.7515049355420471), ('qualifier', 0.07991857845525577), ('value', 0.07422054176363414), ('version', 0.06228860151150819), ('type', 0.05975552494680155), ('settings', 0.04748152329560249), ('bucketsPath', 0.04495292116196361), ('contentType', 0.03698175215155673), ('content', 0.03557035927136211), ('dynamicType', 0.0280071397092783)]
// signature            0	: [('signature', 0.8750720559069712), ('task', 0.03338998977048225), ('scoreExp', 0.029167047464966345), ('visible', 0.008333644382076854), ('name', 0.006095997503496414), ('request', 0.0046414723975473395), ('other', 0.004351826923427797), ('ex', 0.004228367947113895), ('action', 0.0026745488085744102), ('message', 0.0021457855666412403)]
// exceptions           0	: [('exceptions', 0.7745124395799594), ('credential', 0.052698677819028934), ('concreteIndices', 0.02799358836763405), ('indices', 0.024888285883122713), ('invalidCredential', 0.024510020668677045), ('bucketsPaths', 0.012323978951933934), ('issuer', 0.01103081313672768), ('fields', 0.01102370145954285), ('expected', 0.005763718690659045), ('args', 0.005619926156975908)]
        MethodVisitor in = cv.visitMethod(access, name, desc, signature, exceptions);
// in                   No	: [('cv', 0.937500214514525), ('e', 0.0009163961683903215), ('equalTo', 0.0005208163252309732), ('request', 0.0004748016139269682), ('i', 0.000330164996583893), ('context', 0.0003221156602767373), ('listener', 0.00027448141085569314), ('parser', 0.0002573494991770757), ('shard', 0.00021701575429883464), ('params', 0.00020900824723304806)]
        CheckMethodAdapter checker = new CheckMethodAdapter(WriterConstants.ASM_VERSION, in, new HashMap<Label, Integer>()) {
// checker              No	: [('builder', 0.09172696720773371), ('task', 0.04402161760591425), ('action', 0.03472553280946494), ('channel', 0.03257386556153733), ('runnable', 0.025554856650387245), ('autoFollower', 0.02529921728930199), ('cache', 0.024305863144860593), ('sortBuilder', 0.02414085848430237), ('indexer', 0.019309716053389856), ('asyncAction', 0.019305013540264752)]

            @Override
            public void visitJumpInsn(int opcode, Label label) {
// opcode               0	: [('opcode', 0.7500008659227173), ('i', 0.3704160223267249), ('j', 0.02981584863473214), ('doc', 0.016187666125353117), ('size', 0.009185214935877128), ('index', 0.008109386196149334), ('nodeOrdinal', 0.006723486785135556), ('docId', 0.006123773004490048), ('columnIndex', 0.005897547466133541), ('parameterIndex', 0.005237776259945252)]
// label                No	: [('end', 0.31073517462462685), ('handler', 0.25717153123141884), ('begin', 0.021431193739555546), ('fals', 0.02143005096110115), ('start', 0.014320048698807942), ('equalTo', 0.008333081531595201), ('e', 0.007629740277314425), ('request', 0.007596842290521928), ('jump', 0.00714435453130021), ('noFlip', 0.0071440331248599125)]
                mv.visitJumpInsn(opcode, label);
            }

            @Override
            public void visitTryCatchBlock(Label start, Label end, Label handler, String type) {
// start                0	: [('start', 0.750074975832145), ('end', 0.06073517462462684), ('begin', 0.021431193739555546), ('fals', 0.02143005096110115), ('handler', 0.007171531231418861), ('label', 0.007145461597927908), ('jump', 0.00714435453130021), ('noFlip', 0.0071440331248599125), ('exception', 0.0036277436138560364), ('nonNull', 0.00357280096847819)]
// end                  0	: [('end', 0.40451461398962135), ('handler', 0.2572750509001559), ('label', 0.13224898126666496), ('other', 0.1056351645320859), ('start', 0.03725335473947842), ('length', 0.022407119169968227), ('begin', 0.021741752745766726), ('fals', 0.02174060996731233), ('now', 0.016744423814953932), ('fileContents', 0.011044650613330969)]
// handler              3	: [('matchBytesRef', 0.28840435070551274), ('end', 0.2246585343579347), ('label', 0.1739156479333316), ('handler', 0.17031849344919878), ('term', 0.07636137212287729), ('hasAggregations', 0.07634528566900989), ('request', 0.07334737965754276), ('escapedValue', 0.07287286814834229), ('e', 0.0725778308977204), ('listener', 0.07237545323143783)]
// type                 No	: [('deprecationMessage', 0.3334285337340456), ('executor', 0.16789246118915058), ('errorHandler', 0.14630302920537766), ('localClusterStateSupplier', 0.08359556824111941), ('buffer', 0.06723115037497401), ('networkBuffer', 0.0626973599713528), ('channelBuffer', 0.04181230702973139), ('deprecatedMethod', 0.04179784822159748), ('name', 0.02438260729788204), ('onFailure', 0.023193487535687726)]
                mv.visitTryCatchBlock(start, end, handler, type);
            }
        };
        checker.version = WriterConstants.CLASS_VERSION;
        return checker;
    }
}
