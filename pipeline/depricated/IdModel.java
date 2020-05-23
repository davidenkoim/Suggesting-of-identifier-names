package depricated;

import slp.core.counting.Counter;
import slp.core.modeling.ngram.JMModel;
import slp.core.modeling.runners.ModelRunner;
import slp.core.sequencing.NGramSequencer;
import slp.core.util.Pair;
import java.io.File;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class IdModel extends JMModel {

    private Identifiers identifiers;

    public void setClosed(boolean closed) {
        identifiers.setClosed(closed);
    }

    public IdModel(Identifiers identifiers) {
        super();
        this.identifiers = identifiers;
    }

    public IdModel(Identifiers identifiers, double lambda, int order, Counter counter) {
        super(lambda, order, counter);
        this.identifiers = identifiers;
    }

    public IdModel(Identifiers identifiers, int order, Counter counter) {
        super(order, counter);
        this.identifiers = identifiers;
    }

    public void setIdentifiers(Identifiers identifiers) {
        this.identifiers = identifiers;
    }

    @Override
    public List<Map<Integer, Pair<Double, Double>>> predict(List<Integer> input) {
        return IntStream.range(0, input.size()).mapToObj(i -> identifiers.contains(input.get(i)) ? predictToken(input, i) : new HashMap<Integer, Pair<Double, Double>>()).collect(Collectors.toList());
    }

    @Override
    public Map<Integer, Pair<Double, Double>> predictAtIndex(List<Integer> input, int index) {
        List<Integer> sequence = NGramSequencer.sequenceAt(input, index - 1, this.order);
        Set<Integer> predictions = new HashSet<>();
        for (int i = 0; i <= sequence.size(); i++) {
            int limit = ModelRunner.GLOBAL_PREDICTION_CUTOFF - predictions.size();
            if (limit <= 0)
                break;
            predictions.addAll(this.counter.getTopSuccessors(sequence.subList(i, sequence.size()), limit + 50).stream().filter(identifiers::contains).limit(limit).collect(Collectors.toList()));
        }
        return predictions.stream().collect(Collectors.toMap(p -> p, p -> prob(input, index, p)));
    }

    private Pair<Double, Double> prob(List<Integer> input, int index, int prediction) {
        boolean added = index == input.size();
        if (added)
            input.add(0);
        int prev = input.set(index, prediction);
        Pair<Double, Double> prob = this.modelAtIndex(input, index);
        if (added)
            input.remove(input.size() - 1);
        else
            input.set(index, prev);
        return prob;
    }

    @Override
    public List<Pair<Double, Double>> model(List<Integer> input) {
        return IntStream.range(0, input.size()).mapToObj(i -> identifiers.contains(input.get(i)) ? modelToken(input, i) : new Pair<Double, Double>(null, null)).collect(Collectors.toList());
    }

    public void learnIdentifiers(File file) {
        this.identifiers.learn(file);
    }

    @Override
    public void learn(List<Integer> input) {
        this.counter.countBatch(this.sequenceForwardAround(input, this.order));
    }

    @Override
    public void learnToken(List<Integer> input, int index) {
        if (identifiers.contains(input.get(index))) {
            List<Integer> sequence = NGramSequencer.sequenceAt(input, index, this.order);
            for (int i = 0; i < sequence.size(); i++) {
                this.counter.count(sequence.subList(i, sequence.size()));
            }
        }
    }

    @Override
    public void forget(List<Integer> input) {
        this.counter.unCountBatch(this.sequenceForwardAround(input, this.order));
    }

    @Override
    public void forgetToken(List<Integer> input, int index) {
        if (identifiers.contains(input.get(index))) {
            List<Integer> sequence = NGramSequencer.sequenceAt(input, index, this.order);
            for (int i = 0; i < sequence.size(); i++) {
                this.counter.unCount(sequence.subList(i, sequence.size()));
            }
        }
    }

    public List<List<Integer>> sequenceForward(List<Integer> tokens, int maxOrder) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        for (int start = 0; start < tokens.size(); start++) {
            int end = Math.min(tokens.size(), start + maxOrder);
            if (identifiers.contains(tokens.get(end - 1))) {
                result.add(tokens.subList(start, end));
            }
        }
        return result;
    }

    public List<List<Integer>> sequenceForwardAround(List<Integer> tokens, int maxOrder) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        int rightBound = 0;
        for (int start = 0; start < tokens.size(); start++) {
            int end = Math.min(tokens.size(), start + maxOrder);
            if (identifiers.contains(tokens.get(end - 1))) {
                rightBound = end + maxOrder - 1;
                result.add(tokens.subList(start, end));
            } else if (end < rightBound) {
                result.add(tokens.subList(start, end));
            }
        }
        return result;
    }
}
