import slp.core.counting.giga.GigaCounter;
import slp.core.lexing.Lexer;
import slp.core.lexing.code.JavaLexer;
import slp.core.lexing.runners.LexerRunner;
import slp.core.modeling.Model;
import slp.core.modeling.runners.ModelRunner;
import slp.core.translating.Vocabulary;

import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        if (args.length < 2 || !new File(args[1]).isFile()) {
            System.err.println("Please provide a train file/directory and a test file for this example");
            return;
        }
        File train = new File(args[0]);
        File test = new File(args[1]);

        Lexer lexer = new JavaLexer();  // Use a Java lexer; if your code is already lexed, use whitespace or tokenized lexer
        LexerRunner lexerRunner = new LexerRunner(lexer, false);  // Don't model lines in isolation for code files.
        lexerRunner.setSentenceMarkers(true);  // Add start and end markers to the files
        lexerRunner.setExtension("java");  // We only lex Java files

        Vocabulary vocabulary = new Vocabulary();  // Create an empty vocabulary

        // Learn identifiers from train and feed them to the model
        Identifiers identifiers = new Identifiers(lexerRunner, vocabulary);
        identifiers.learn(train);
        identifiers.setClosed(true);
        Model model = new IdModel(identifiers, 6, new GigaCounter());

        ModelRunner modelRunner = new ModelRunner(model, lexerRunner, vocabulary); // Use above lexer and vocabulary
        modelRunner.learnDirectory(train);  // Teach the model all the data in "train"
        modelRunner.forgetFile(test); // Forget test file

        List<String> fileTokens = lexerRunner.lexFile(test).flatMap(s -> s).collect(Collectors.toList());
        List<Integer> fileIndices = vocabulary.toIndices(fileTokens);
//        System.out.println(model.predict(fileIndices)); // Example of predictions
//        Let's count MRR score
        List<Double> fileMRRs = modelRunner.predictFile(test).stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        System.out.printf("MRR on identifiers: %.4f", IntStream.range(0, fileMRRs.size())
                .filter(ind -> identifiers.getIndices().contains(fileIndices.get(ind)))
                .mapToDouble(fileMRRs::get)
                .summaryStatistics()
                .getAverage());
    }
}
