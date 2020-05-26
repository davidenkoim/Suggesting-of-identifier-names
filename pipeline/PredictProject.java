import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import slp.core.counting.giga.GigaCounter;
import slp.core.lexing.Lexer;
import slp.core.lexing.code.JavaLexer;
import slp.core.modeling.Model;
import slp.core.modeling.ngram.JMModel;
import slp.core.translating.Vocabulary;

import java.io.File;
import java.io.IOException;
import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.List;

public class PredictProject {
    public static void predictions2json(HashMap<File, List<Prediction>> predictions, File file) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode root = mapper.createObjectNode();
        predictions.forEach((f, preds) -> {
            ObjectNode child = root.putObject(f.getAbsolutePath());
            ArrayNode array = child.putArray("identifiers");
            preds.forEach(p -> array.add(p.toJson(mapper)));
        });
        mapper.writeValue(file, root);
    }
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Please provide a project path for this example");
            return;
        }
        for (String projectPath : args) {
            File project = new File(projectPath);

            Lexer lexer = new JavaLexer();
            Vocabulary vocabulary = new Vocabulary();// Create an empty vocabulary
            int order = 6; // Order of ngram
            IdLexerRunner idLexerRunner = new IdLexerRunner(lexer, order);
            idLexerRunner.setExtension("java"); // We only lex Java files

            Model model = new JMModel(order, new GigaCounter());
            IdRunner idRunner = new IdRunner(model, idLexerRunner, vocabulary);

//            System.out.println("Learning project directory...");
//            idRunner.learnDirectory(project); // Teach the model all the data in a project

//            idRunner.selfTestingIdentifier();
//            idRunner.selfTesting();
            idRunner.selfTraining(); // Learn one file, make predictions on it, then forget it. (It works!)

            System.out.println("Making predictions...");
            DoubleSummaryStatistics stats;
            HashMap<File, List<Prediction>> predictions = idRunner.predict(project);
            stats = idRunner.getStats(predictions);
            System.out.printf("Count of identifiers: %d\nAvr. MRR: %.4f\n", stats.getCount(), stats.getAverage());
            try {
                String projectName = project.getName();
                String dir = "selfTraining";
                new File(dir).mkdirs();
                File file = new File(dir + "/predictions_" + projectName + ".json");
                file.createNewFile();
                predictions2json(predictions, file);
                System.out.println(projectName + " predictions saved!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
