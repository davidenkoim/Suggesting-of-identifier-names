import com.github.javaparser.Range;
import slp.core.io.Reader;
import slp.core.io.Writer;
import slp.core.lexing.Lexer;
import slp.core.modeling.Model;
import slp.core.translating.Vocabulary;
import slp.core.util.Pair;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class can be used to run a {@linkplain Lexer} over bodies of code.
 * It differentiates between lexing each line separately or each file as a whole,
 * and adds several options, such as adding markers at the start and end of every sentence,
 * and only lexing files that match some extension/regular expression.
 * It also provides some util methods like {@link #lexDirectory(File)} and variants.
 *
 * @author Vincent Hellendoorn
 */
public class IdLexerRunner {

    private final Lexer lexer;

    private String regex = ".*";

    int order = 6;

    private boolean rewriteFiles = false;

    public void setRewriteFiles(boolean rewriteFiles) {
        this.rewriteFiles = rewriteFiles;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    /**
     * Create a LexerRunner that wraps a {@link Lexer} and adds line separation if needed.
     * <br />
     * In some tasks (especially in NLP), a file with unrelated individual sentences on each line tends to be used,
     * whereas in most code applications, we tend to use a complete code file in which the lines should be treated as a continuous block.
     * The LexerRunner (and ModelRunner, which uses this class) need to know this to allow appropriate training.
     *
     * @param lexer A {@link Lexer} that can produce a stream of tokens for each line in a File, or for single-line inputs.
     * @param order Order of ngrams.
     */
    public IdLexerRunner(Lexer lexer, int order) {
        this.lexer = lexer;
        this.order = order;
    }

    public IdLexerRunner(Lexer lexer) {
        this.lexer = lexer;
    }

    /**
     * Returns the lexer currently used by this class
     */
    public Lexer getLexer() {
        return lexer;
    }

    /**
     * Specify regex for file extensions to be kept.
     * <br />
     * <em>Note:</em> to just specify the extension, use the more convenient {@link #setExtension(String)}.
     *
     * @param regex Regular expression to match file name against. E.g. ".*\\.(c|h)" for C source and header files.
     */
    public void setRegex(String regex) {
        this.regex = regex;
    }

    /**
     * Alternative to {@link #setRegex(String)} that allows you to specify just the extension.
     * <br />
     * <em>Note:</em> this prepends <code>.*\\.</code> to the provided regex!
     *
     * @param regex Regular expression to match against extension of files. E.g. "(c|h)" for C source and header files.
     */
    public void setExtension(String regex) {
        this.regex = ".*\\." + regex;
    }

    /**
     * Returns the regex currently used to filter input files to lex.
     */
    public String getRegex() {
        return this.regex;
    }

    /**
     * Returns whether the file matches the regex and will thus be lexed by this class
     */
    public boolean willLexFile(File file) {
        return file.getName().matches(this.regex);
    }

    /**
     * Lex each file in this directory to a their tokens grouped by lines, subject to the underlying {@link Lexer}
     * and whether this Lexer is configured to work per line
     *
     * @param directory
     * @return
     */
    public Stream<Pair<File, Stream<Identifier>>> lexDirectory(File directory) {
        try {
            return Files.walk(directory.toPath())
                    .map(Path::toFile)
                    .filter(File::isFile)
                    .filter(this::willLexFile)
                    .peek(file -> {
                        if (this.rewriteFiles) IdentifiersUsages.rewrite(file);
                    })
                    .map(fIn -> Pair.of(fIn, lexFile(fIn)));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * From range get ngram of length {@code order}
     */
    private List<String> range2ngram(Range range, List<String> lines) {
        int line = range.end.line - 1;
        int column = range.end.column;
        List<String> tokens = this.lexLine(lines.get(line--).substring(0, column)).collect(Collectors.toList());
        while (line >= 0 && tokens.size() < this.order) {
            tokens.addAll(0, this.lexLine(lines.get(line--)).collect(Collectors.toList()));
        }
        return tokens;
    }

    /**
     * Get token from Range
     */
    private String range2token(Range range, List<String> lines) {
        int line = range.end.line - 1;
        int begin = range.begin.column - 1;
        int end = range.end.column;
        return lines.get(line).substring(begin, end);
    }

    /**
     * Lex the provided file to a stream of tokens per line. Note that this is preferred over lex(lines),
     * since knowing the file location/context can be helpful for most lexers!
     * <br />
     * <em>Note:</em> returns empty stream if the file does not match this builder's regex
     * (which accepts everything unless set otherwise in {@link #setRegex(String)}).
     *
     * @param file File to lex
     */
    public Stream<Identifier> lexFile(File file) {
        if (!willLexFile(file))
            return Stream.empty();
        List<List<Range>> identifiersUsages = IdentifiersUsages.fromFile(file);
        List<String> lines = Reader.readLines(file);
        assert lines != null;
        assert identifiersUsages.size() > 0;
        return identifiersUsages.stream().map(i -> {
            Identifier res = null;
            try {
                res = new Identifier(range2token(i.get(0), lines), i.get(0),
                        i.stream().map(x -> range2ngram(x, lines)).collect(Collectors.toList()));
            } catch (Exception e) {
                IdentifiersUsages.setVerbose(true);
                IdentifiersUsages.fromFile(file);
                System.out.println(file);
                System.out.println(i);
                e.printStackTrace();
            }
            return res;
        });
    }

    public Stream<String> lexLine(String line) {
        return this.lexer.lexLine(line);
    }
}
