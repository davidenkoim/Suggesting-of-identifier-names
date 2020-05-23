import slp.core.modeling.Model;
import slp.core.modeling.runners.ModelRunner;
import slp.core.translating.Vocabulary;
import slp.core.util.Pair;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class can be used to run {@link Model}-related functions to predict identifiers of a file.
 */
public class IdRunner {

    private static final double INV_NEG_LOG_2 = -1.0 / Math.log(2);

    public static final int DEFAULT_NGRAM_ORDER = 6;

    public static int GLOBAL_PREDICTION_CUTOFF = 10;

    protected final IdLexerRunner idLexerRunner;

    protected final Vocabulary vocabulary;

    protected final Model model;

    private final HashSet<Integer> identifiers = new HashSet<>();

    public HashSet<Integer> getIdentifiers() {
        return identifiers;
    }

    private boolean selfTesting = false;

    private boolean selfTestingIdentifier = false;

    public void setSelfTesting(boolean selfTesting) {
        this.selfTesting = selfTesting;
    }

    public void setSelfTestingIdentifier(boolean selfTestingIdentifier) {
        this.selfTestingIdentifier = selfTestingIdentifier;
    }

    public IdRunner(Model model, IdLexerRunner lexer, Vocabulary vocabulary) {
        this.idLexerRunner = lexer;
        this.vocabulary = vocabulary;
        this.model = model;
        this.identifiers.addAll(List.of(-1, 0));
    }


    public IdRunner copyForModel(Model model) {
        return new IdRunner(model, this.idLexerRunner, this.vocabulary);
    }

    public IdLexerRunner getIdLexerRunner() {
        return this.idLexerRunner;
    }

    public Model getModel() {
        return this.model;
    }

    public Vocabulary getVocabulary() {
        return this.vocabulary;
    }

    public static int getPredictionCutoff() {
        return GLOBAL_PREDICTION_CUTOFF;
    }

    public static void setPredictionCutoff(int cutoff) {
        GLOBAL_PREDICTION_CUTOFF = cutoff;
    }

    private final long LEARN_PRINT_INTERVAL = 10000;

    private long[] learnStats = new long[2];

    public void learnDirectory(File file) {
        this.learnStats = new long[]{0, -System.currentTimeMillis()};
        this.idLexerRunner.lexDirectory(file).forEach(p -> {
            this.model.notify(p.left);
            this.learnIdentifiers(p.right);
        });
        if (this.learnStats[0] > LEARN_PRINT_INTERVAL && this.learnStats[1] != 0) {
            System.out.printf("Counting complete: %d tokens processed in %ds\n", this.learnStats[0], (System.currentTimeMillis() + this.learnStats[1]) / 1000);
        }
    }

    public void learnFile(File f) {
        if (!this.idLexerRunner.willLexFile(f))
            return;
        this.model.notify(f);
        learnIdentifiers(this.idLexerRunner.lexFile(f));
    }

    public void learnIdentifiers(Stream<Identifier> ids) {
        ids.forEach(id -> {
            this.identifiers.add(vocabulary.toIndex(id.getName()));
            id.usages.forEach(usage -> {
                this.model.learnToken(this.vocabulary.toIndices(usage), usage.size() - 1);
                logLearningProgress();
            });
        });
    }

    public void learnIdentifier(Identifier id){
        this.identifiers.add(vocabulary.toIndex(id.getName()));
        id.usages.forEach(usage -> {
            this.model.learnToken(this.vocabulary.toIndices(usage), usage.size() - 1);
        });
    }

    private void logLearningProgress() {
        if (++this.learnStats[0] % this.LEARN_PRINT_INTERVAL == 0 && this.learnStats[1] != 0) {
            System.out.printf("Counting: %dK usages processed in %ds\n", Math.round(this.learnStats[0] / 1e3), (System.currentTimeMillis() + this.learnStats[1]) / 1000);
        }
    }

    public void forgetDirectory(File file) {
        try {
            Files.walk(file.toPath()).map(Path::toFile).filter(File::isFile).forEach(this::forgetFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void forgetFile(File f) {
        if (!this.idLexerRunner.willLexFile(f))
            return;
        this.model.notify(f);
        forgetIdentifiers(this.idLexerRunner.lexFile(f));
    }

    public void forgetIdentifiers(List<Identifier> ids) {
        this.forgetIdentifiers(ids.stream());
    }

    public void forgetIdentifiers(Stream<Identifier> ids) {
        ids.forEach(id -> {
            id.usages.stream().map(this.vocabulary::toIndices)
                    .forEach(usage -> this.model.forgetToken(usage, usage.size() - 1));
        });
    }

    public void forgetIdentifier(Identifier id) {
        id.usages.stream().map(this.vocabulary::toIndices)
                .forEach(usage -> this.model.forgetToken(usage, usage.size() - 1));
    }

    private final int PREDICT_PRINT_INTERVAL = 10000;

    private long[] modelStats = new long[2];

    private double mrr = 0.0;

    public List<Pair<File, List<Prediction>>> predict(File file) {
        this.modelStats = new long[]{0, -System.currentTimeMillis()};
        return this.idLexerRunner.lexDirectory(file).map(p -> {
            this.model.notify(p.left);
            if (selfTesting)
                this.forgetFile(p.left);
            Pair<File, List<Prediction>> res = Pair.of(p.left, this.predictIdentifiers(p.right));
            if (selfTesting)
                this.learnFile(p.left);
            return res;
        }).collect(Collectors.toList());
    }

    public List<Prediction> predictFile(File f) {
        if (!this.idLexerRunner.willLexFile(f))
            return null;
        this.model.notify(f);
        if (this.selfTesting) this.forgetFile(f);
        List<Prediction> res = predictIdentifiers(this.idLexerRunner.lexFile(f));
        if (this.selfTesting) this.learnFile(f);
        return res;
    }

    public List<Prediction> predictIdentifiers(Stream<Identifier> ids) {
        this.getVocabulary().setCheckpoint();
        List<Prediction> preds = ids.map(this::predictIdentifier).collect(Collectors.toList());
        logPredictionProgress(preds);
        this.getVocabulary().restoreCheckpoint();
        return preds;
    }

    protected Prediction predictIdentifier(Identifier id) {
        if (!selfTesting && this.selfTestingIdentifier)
            this.forgetIdentifier(id);
        // It might be that predictions contain not only identifiers.
        ModelRunner.GLOBAL_PREDICTION_CUTOFF = GLOBAL_PREDICTION_CUTOFF * 5;
        List<Map<Integer, Pair<Double, Double>>> preds = id.usages.stream().map(this.vocabulary::toIndices).map(usage -> this.model.predictToken(usage, usage.size() - 1)).collect(Collectors.toList());
        if (!selfTesting && this.selfTestingIdentifier)
            this.learnIdentifier(id);
        return new Prediction(id.getName(), id.getRange(), toPredictions(preds));
    }

    private void logPredictionProgress(List<Prediction> preds) {
        long prevCount = this.modelStats[0];
        this.modelStats[0] += preds.size();
        this.mrr += preds.stream().mapToDouble(IdRunner::toMRR).sum();
        if (this.modelStats[0] / this.PREDICT_PRINT_INTERVAL > prevCount / this.PREDICT_PRINT_INTERVAL && this.modelStats[1] != 0) {
            System.out.printf("Predicting: %dK tokens processed in %ds, avg. MRR: %.4f\n", Math.round(this.modelStats[0] / 1e3), (System.currentTimeMillis() + this.modelStats[1]) / 1000, this.mrr / this.modelStats[0]);
        }
    }

    public List<Double> toProb(List<Pair<Double, Double>> probConfs) {
        return probConfs.stream().map(this::toProb).collect(Collectors.toList());
    }

    public double toProb(Pair<Double, Double> probConf) {
        double prob = probConf.left;
        double conf = probConf.right;
        return prob * conf + (1 - conf) / this.getVocabulary().size();
    }

    public static double toMRR(Prediction pred) {
        int ix = pred.stream().map(x -> x.left).collect(Collectors.toList()).indexOf(pred.getGroundTruth());
        return toMRR(ix);
    }

    public static double toMRR(Integer ix) {
        return ix >= 0 ? 1.0 / (ix + 1) : 0.0;
    }

    public List<Pair<String, Double>> toPredictions(List<Map<Integer, Pair<Double, Double>>> probConfs) {
        Map<Integer, Double> predsMaxProb = new HashMap<>();
        // TODO: Реализовать что-нибудь более продвинутое, чем просто максимум одинаковых предсказаний
        probConfs.stream().map(this::toPredictions).flatMap(Collection::stream).forEach(pair ->
                predsMaxProb.compute(pair.left, (k, v) ->
                        (v == null) ? pair.right : Math.max(v, pair.right)));
        return predsMaxProb.entrySet().stream().map(e -> Pair.of(this.vocabulary.toWord(e.getKey()), e.getValue())).sorted((p1, p2) -> -Double.compare(p1.right, p2.right)).limit(GLOBAL_PREDICTION_CUTOFF).collect(Collectors.toList());
    }

    public List<Pair<Integer, Double>> toPredictions(Map<Integer, Pair<Double, Double>> probConf) {
        return probConf.entrySet().stream().filter(e -> identifiers.contains(e.getKey())).map(e -> Pair.of(e.getKey(), toProb(e.getValue()))).sorted((p1, p2) -> -Double.compare(p1.right, p2.right)).limit(GLOBAL_PREDICTION_CUTOFF).collect(Collectors.toList());
    }

    private <K> List<List<K>> toLines(List<K> modeled, List<Integer> lineLengths) {
        List<List<K>> perLine = new ArrayList<>();
        int ix = 0;
        for (Integer lineLength : lineLengths) {
            List<K> line = new ArrayList<>();
            for (int j = 0; j < lineLength; j++) {
                line.add(modeled.get(ix++));
            }
            perLine.add(line);
        }
        return perLine;
    }

    public DoubleSummaryStatistics getStats(Map<File, List<Prediction>> filePreds) {
        return getStats(filePreds.entrySet().stream().map(e -> Pair.of(e.getKey(), e.getValue())));
    }

    public DoubleSummaryStatistics getStats(Stream<Pair<File, List<Prediction>>> filePreds) {
        return getFileStats(filePreds.map(p -> p.right));
    }

    public DoubleSummaryStatistics getStats(List<Prediction> filePreds) {
        return getFileStats(Stream.of(filePreds));
    }

    private DoubleSummaryStatistics getFileStats(Stream<List<Prediction>> filePreds) {
        return filePreds.flatMapToDouble(x -> x.stream().mapToDouble(IdRunner::toMRR)).summaryStatistics();
    }
}
