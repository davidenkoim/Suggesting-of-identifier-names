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
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Please provide a train file/directory and a test file for this example");
            return;
        }
        File train = new File(args[0]);
        File test = new File(args[1]);

        Lexer lexer = new JavaLexer();
        Vocabulary vocabulary = new Vocabulary();// Create an empty vocabulary
        int order = 6; // Order of ngram
        IdLexerRunner idLexerRunner = new IdLexerRunner(lexer, order);
        idLexerRunner.setExtension("java"); // We only lex Java files

        Model model = new JMModel(order, new GigaCounter());
        IdRunner idRunner = new IdRunner(model, idLexerRunner, vocabulary);

//        System.out.println("Learning train directory...");
//        idRunner.learnDirectory(train); // Teach the model all the data in "train"

//        idRunner.selfTestingIdentifier();
//        idRunner.selfTesting();
        idRunner.selfTraining();

        System.out.println("Predicting test file...");
        DoubleSummaryStatistics stats;
        if (test.isDirectory()) {
            HashMap<File, List<Prediction>> predictions = idRunner.predict(test);
            stats = idRunner.getStats(predictions);
        } else {
            List<Prediction> predictions = idRunner.predictFile(test);
            stats = idRunner.getStats(predictions);
            System.out.println(predictions);
        }
        System.out.printf("Count of identifiers: %d\nAvr. MRR: %.4f", stats.getCount(), stats.getAverage());
    }
}
