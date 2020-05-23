import depricated.IdModel;
import depricated.Identifiers;
import slp.core.counting.Counter;
import slp.core.counting.giga.GigaCounter;
import slp.core.lexing.Lexer;
import slp.core.lexing.code.JavaLexer;
import slp.core.lexing.runners.LexerRunner;
import slp.core.modeling.Model;
import slp.core.modeling.ngram.JMModel;
import slp.core.modeling.runners.ModelRunner;
import slp.core.translating.Vocabulary;
import slp.core.util.Pair;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    public void foo() {
        int train;
        train = 1;
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Please provide a train file/directory and a test file for this example");
            return;
        }
        File train = new File(args[0]);
        File test = new File(args[1]);

        Lexer lexer = new JavaLexer();
        Vocabulary vocabulary = new Vocabulary();// Create an empty vocabulary
        IdLexerRunner idLexerRunner = new IdLexerRunner(lexer, 6);
        idLexerRunner.setExtension("java"); // We only lex Java files

        Counter counter = new GigaCounter();
        Model model = new JMModel(6, counter);
        IdRunner idRunner = new IdRunner(model, idLexerRunner, vocabulary);

        System.out.println("Learning train directory...");
        idRunner.learnDirectory(train); // Teach the model all the data in "train"

//        List<Integer> list = vocabulary.toIndices(List.of("public", "IdRunner", "(", "Model", "model"));
//        System.out.println(Arrays.toString(counter.getCounts(list)));

        idRunner.setSelfTestingIdentifier(true);

        System.out.println("Predicting test file...");
        DoubleSummaryStatistics stats;
        if (test.isDirectory()) {
            List<Pair<File, List<Prediction>>> predictions = idRunner.predict(test);
            stats = idRunner.getStats(predictions.stream());
        } else {
            List<Prediction> predictions = idRunner.predictFile(test);
            stats = idRunner.getStats(predictions);
            System.out.println(predictions);
        }
        System.out.printf("Count of identifiers: %d\nAvr. MRR: %.4f", stats.getCount(), stats.getAverage());
    }
}
