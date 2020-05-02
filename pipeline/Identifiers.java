import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import slp.core.lexing.Lexer;
import slp.core.lexing.code.JavaLexer;
import slp.core.lexing.runners.LexerRunner;
import slp.core.translating.Vocabulary;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;

public class Identifiers {
    private HashSet<String> tokens = new HashSet<>();
    private HashSet<Integer> indices = new HashSet<>();
    private LexerRunner lexerRunner;
    private Vocabulary vocabulary;

    public LexerRunner getLexerRunner() {
        return lexerRunner;
    }

    public Vocabulary getVocabulary() {
        return vocabulary;
    }

    public Identifiers(LexerRunner l, Vocabulary voc) {
        this.lexerRunner = l;
        this.vocabulary = voc;
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Please provide a test file/directory for this example");
            return;
        }
        File file = new File(args[0]);

        Lexer lexer = new JavaLexer();
        LexerRunner lexerRunner = new LexerRunner(lexer, false);

        lexerRunner.setSentenceMarkers(true);
        lexerRunner.setExtension("java");

        Vocabulary vocabulary = new Vocabulary();

        Identifiers identifiers = new Identifiers(lexerRunner, vocabulary);
        identifiers.learn(file);
        System.out.println(identifiers.getTokens());
    }

    public void learnFileIdentifiers(File file) {
        CompilationUnit cu = null;
        try {
            cu = StaticJavaParser.parse(file);
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + file);
        }
        catch (Exception e) {
            System.out.println("File with errors: " + file);
            return;
        }

        VoidVisitor<HashSet<String>> methodNameCollector = new IdentifierNameCollector();
        methodNameCollector.visit(cu, tokens);
        tokens.forEach(n -> indices.add(vocabulary.toIndex(n)));
    }

    public void learn(File file) {
        try {
            if (file.isFile() && lexerRunner.willLexFile(file)) {
                learnFileIdentifiers(file);
            }
            if (file.isDirectory()){
                Files.walk(file.toPath())
                        .map(Path::toFile)
                        .filter(File::isFile)
                        .filter(lexerRunner::willLexFile)
                        .forEach(this::learnFileIdentifiers);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HashSet<String> getTokens() {
        return tokens;
    }

    public HashSet<Integer> getIndices() {
        return indices;
    }

    private static class IdentifierNameCollector extends VoidVisitorAdapter<HashSet<String>> {

        @Override
        public void visit(VariableDeclarator name, HashSet<String> set) {
            super.visit(name, set);
            set.add(name.getNameAsString());
        }

        @Override
        public void visit(Parameter name, HashSet<String> set) {
            super.visit(name, set);
            set.add(name.getNameAsString());
        }

        @Override
        public void visit(FieldDeclaration name, HashSet<String> set) {
            super.visit(name, set);
            name.getVariables().forEach(n -> set.add(n.getNameAsString()));
        }
    }
}
