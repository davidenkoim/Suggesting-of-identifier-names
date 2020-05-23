package depricated;

import slp.core.counting.giga.GigaCounter;
import slp.core.lexing.Lexer;
import slp.core.lexing.code.JavaLexer;
import slp.core.lexing.runners.LexerRunner;
import slp.core.translating.Vocabulary;
import slp.core.util.Pair;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class SelfPredictProject {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Please provide a project for this example");
            return;
        }
        for (String projectPath : args) {
            System.out.println("Predicting project: " + projectPath);
            File project = new File(projectPath);
            // Use a Java lexer; if your code is already lexed, use whitespace or tokenized lexer
            Lexer lexer = new JavaLexer();
            // Don't model lines in isolation for code files.
            LexerRunner lexerRunner = new LexerRunner(lexer, false);
            // Add start and end markers to the files
            lexerRunner.setSentenceMarkers(true);
            // We only lex Java files
            lexerRunner.setExtension("java");
            // Create an empty vocabulary
            Vocabulary vocabulary = new Vocabulary();
            // Learn identifiers from project and feed them to the model
            Identifiers identifiers = new Identifiers(lexerRunner, vocabulary);
            IdModel model = new IdModel(identifiers, 6, new GigaCounter());
            // Use above lexer and vocabulary
            IdModelRunner modelRunner = new IdModelRunner(model, lexerRunner, vocabulary);
            // Teach the model all the data in "project"
            modelRunner.learnDirectory(project);
            modelRunner.setSelfTesting(true);
            List<Pair<File, List<List<List<Pair<Integer, Double>>>>>> predictions = modelRunner.predict(project);
            List<Pair<File, List<List<Integer>>>> lexedFiles = lexerRunner.lexDirectory(project).map(p -> Pair.of(p.left, p.right.map(s -> vocabulary.toIndices(s).collect(Collectors.toList())).collect(Collectors.toList()))).collect(Collectors.toList());
            try {
                String projectName = project.getName();
                new File("cache/" + projectName).mkdirs();
                File preds = new File("cache/" + projectName + "/predictions.json");
                File voc = new File("cache/" + projectName + "/vocabulary.json");
                File lexed = new File("cache/" + projectName + "/lexed.json");
                File ids = new File("cache/" + projectName + "/identifiers.json");
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
}
