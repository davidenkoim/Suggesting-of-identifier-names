import slp.core.counting.giga.GigaCounter;
import slp.core.lexing.Lexer;
import slp.core.lexing.code.JavaLexer;
import slp.core.lexing.runners.LexerRunner;
import slp.core.modeling.Model;
import slp.core.translating.Vocabulary;
import slp.core.util.Pair;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SelfPredictProject {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Please provide a project for this example");
            return;
        }
        File project = new File(args[0]);

        Lexer lexer = new JavaLexer();  // Use a Java lexer; if your code is already lexed, use whitespace or tokenized lexer
        LexerRunner lexerRunner = new LexerRunner(lexer, false);  // Don't model lines in isolation for code files.
        lexerRunner.setSentenceMarkers(true);  // Add start and end markers to the files
        lexerRunner.setExtension("java");  // We only lex Java files

        Vocabulary vocabulary = new Vocabulary();  // Create an empty vocabulary

        // Learn identifiers from project and feed them to the model
        Identifiers identifiers = new Identifiers(lexerRunner, vocabulary);
        IdModel model = new IdModel(identifiers, 6, new GigaCounter());

        IdModelRunner modelRunner = new IdModelRunner(model, lexerRunner, vocabulary); // Use above lexer and vocabulary
        modelRunner.learnDirectory(project);  // Teach the model all the data in "project"
        modelRunner.setSelfTesting(true);

        List<Pair<File, List<List<List<Integer>>>>> predictions = modelRunner.predict(project);

        List<Pair<File, List<List<Integer>>>> lexedFiles = lexerRunner.lexDirectory(project)
                .map(p -> Pair.of(p.left,
                        p.right.map(s -> vocabulary.toIndices(s).collect(Collectors.toList()))
                                .collect(Collectors.toList()))
                ).collect(Collectors.toList());

        try {
            String projectName = project.getName();
            new File("cash/" + projectName).mkdirs();
            File preds = new File("cash/" + projectName + "/predictions.json");
            File voc = new File("cash/" + projectName + "/vocabulary.json");
            File lexed = new File("cash/" + projectName + "/lexed.json");
            File ids = new File("cash/" + projectName + "/identifiers.json");
            preds.createNewFile();
            voc.createNewFile();
            lexed.createNewFile();
            ids.createNewFile();
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(preds, predictions);
            mapper.writeValue(voc, vocabulary.getWords());
            mapper.writeValue(lexed, lexedFiles);
            mapper.writeValue(ids, identifiers.getIndices());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
