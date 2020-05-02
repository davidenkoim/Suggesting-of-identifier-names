import slp.core.counting.Counter;
import slp.core.modeling.ngram.JMModel;
import slp.core.sequencing.NGramSequencer;
import slp.core.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class IdModel extends JMModel {
    private Identifiers identifiers;

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
        return IntStream.range(0, input.size())
                .mapToObj(i -> identifiers.getIndices().contains(input.get(i)) ? predictToken(input, i) :
                        new HashMap<Integer, Pair<Double, Double>>())
                .collect(Collectors.toList());
    }

    @Override
    public List<Pair<Double, Double>> model(List<Integer> input) {
        return IntStream.range(0, input.size())
                .mapToObj(i -> identifiers.getIndices().contains(input.get(i)) ? modelToken(input, i) :
                        new Pair<Double, Double>(null, null))
                .collect(Collectors.toList());
    }

    @Override
    public void learn(List<Integer> input) {
        this.counter.countBatch(this.sequenceForwardAround(input, this.order));
    }

    @Override
    public void learnToken(List<Integer> input, int index) {
        if (identifiers.getIndices().contains(input.get(index))) {
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
        if (identifiers.getIndices().contains(input.get(index))) {
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
            if (identifiers.getIndices().contains(tokens.get(end - 1))) {
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
            if (identifiers.getIndices().contains(tokens.get(end - 1))) {
                rightBound = end + maxOrder - 1;
                result.add(tokens.subList(start, end));
            } else if (end < rightBound) {
                result.add(tokens.subList(start, end));
            }
        }
        return result;
    }
}
