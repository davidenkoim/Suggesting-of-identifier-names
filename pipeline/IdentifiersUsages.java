import com.github.javaparser.*;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.*;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.visitor.*;
import com.github.javaparser.printer.PrettyPrinterConfiguration;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class IdentifiersUsages {

    public static void setVerbose(boolean verbose) {
        UsagesCollector.setVerbose(verbose);
    }

    public static PrettyPrinterConfiguration PRINTER_CONFIGURATION = new PrettyPrinterConfiguration()
            .setPrintComments(false);
//            .setPrintJavadoc(false);

    public static ParserConfiguration CONFIG = new ParserConfiguration()
            .setLanguageLevel(ParserConfiguration.LanguageLevel.CURRENT);

    public static void setParserConfiguration(ParserConfiguration config) {
        CONFIG = config;
        StaticJavaParser.setConfiguration(config);
    }

    public static List<List<Range>> fromFile(File file) {
        StaticJavaParser.setConfiguration(CONFIG);
        List<List<Range>> identifiers = new ArrayList<>();
        try {
            CompilationUnit cu = StaticJavaParser.parse(file);
            VoidVisitor<List<List<Range>>> identifierUsagesCollector = new IdentifiersUsagesCollector();
            identifierUsagesCollector.visit(cu, identifiers);
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + file);
        } catch (Exception e) {
            System.out.println("File with errors: " + file);
        }
        return identifiers;
    }

    private static class IdentifiersUsagesCollector extends VoidVisitorAdapter<List<List<Range>>> {

        public static void setVerbose(boolean verbose) {
            UsagesCollector.setVerbose(verbose);
        }

        @Override
        public void visit(Parameter name, List<List<Range>> identifiersUsages) {
            super.visit(name, identifiersUsages);
            name.getParentNode().ifPresent(root -> identifiersUsages.add(UsagesCollector.get(name.getNameAsExpression(), root)));
        }

        @Override
        public void visit(VariableDeclarator name, List<List<Range>> identifiersUsages) {
            super.visit(name, identifiersUsages);
            name.findAncestor(BlockStmt.class).ifPresent(root -> identifiersUsages.add(UsagesCollector.get(name.getNameAsExpression(), root)));
        }

// TODO: Сделать нормальный поиск полей.
//        @Override
//        public void visit(FieldDeclaration name, List<List<Range>> identifiersUsages) {
//            super.visit(name, identifiersUsages);
//            name.findAncestor(ClassOrInterfaceDeclaration.class).ifPresent(root -> {
//                name.getVariables().forEach(var -> identifiersUsages.add(UsagesCollector.get(var.getNameAsExpression(), root)));
//            });
//        }
    }

    private static <T> ArrayList<T> getUnique(List<T> list) {
        HashSet<T> set = new HashSet<>(list);
        return new ArrayList<T>(set);
    }

    private static class UsagesCollector extends GenericListVisitorAdapter<Range, NameExpr> {

        private final static UsagesCollector SINGLETON = new UsagesCollector();

        private static boolean verbose = false;

        public static void setVerbose(boolean verbose) {
            UsagesCollector.verbose = verbose;
        }

        public static List<Range> get(NameExpr name, Node root) {
            List<Range> usages = new ArrayList<>();
            usages.add(name.getName().getRange().orElse(null));
            if (verbose) {
                System.out.printf("%s: %s\n", name, name.getRange().orElse(null));
            }
            usages.addAll(getUnique(root.accept(SINGLETON, name.getNameAsExpression())));
            if (verbose) {
                System.out.println();
            }
            return usages;
        }

        @Override
        public List<Range> visit(NameExpr ne, NameExpr name) {
            List<Range> result = super.visit(ne, name);
            if (NoCommentEqualsVisitor.equals(ne, name)) {
                if (verbose) {
                    System.out.printf("Name expr: %s\n", ne.getRange().orElse(null));
                }
                ne.getRange().ifPresent(result::add);
            }
            return result;
        }

        @Override
        public List<Range> visit(MethodReferenceExpr mre, NameExpr name) {
            List<Range> result = super.visit(mre, name);
            if (mre.getScope().toString().equals(name.toString())) {
                if (verbose) {
                    System.out.printf("Method ref scope: %s\n", mre.getScope().getRange().orElseThrow());
                }
                mre.getRange().ifPresent(result::add);
            }
            return result;
        }
    }

    public static void rewrite(File file) {
        try {
            CompilationUnit cu = StaticJavaParser.parse(file);
            PrintWriter writer = new PrintWriter(file, StandardCharsets.UTF_8);
            writer.print(cu.toString(PRINTER_CONFIGURATION));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseProblemException e) {
            System.out.println("Cannot rewrite file because of errors!");
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        if (args.length < 1) {
            System.err.println("Please provide a test file/directory for this example");
            return;
        }
        File file = new File(args[0]);
        IdentifiersUsages.setVerbose(true);
        System.out.println(IdentifiersUsages.fromFile(file));
    }
}
