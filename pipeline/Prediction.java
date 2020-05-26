import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.javaparser.Range;
import org.jetbrains.annotations.NotNull;
import slp.core.util.Pair;
import java.util.*;
import static java.lang.String.format;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Prediction implements List<Pair<String, Double>> {

    private final String gt;
    private final Range range;
    private List<Pair<String, Double>> prediction = new ArrayList<>();

    public Prediction(String gt, Range range) {
        this.gt = gt;
        this.range = range;
    }

    public Prediction(String gt, Range range, List<Pair<String, Double>> prediction) {
        this.gt = gt;
        this.range = range;
        this.prediction = prediction;
    }

    public ObjectNode toJson(ObjectMapper mapper){
        ObjectNode root = mapper.createObjectNode();
        root.put("gt", this.gt);
        root.set("range", mapper.valueToTree(this.range));
        root.set("prediction", mapper.valueToTree(this.prediction));
        return root;
    }

    static Prediction fromIdentifier(Identifier id) {
        return new Prediction(id.getName(), id.getRange());
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(format("\nGround truth: %s\nRange: %s\n", this.gt, this.range));
        for (Pair<String, Double> pred : this.prediction) {
            result.append('\t').append(format("%.4f", pred.right)).append(": ").append(pred.left).append('\n');
        }
        return result.toString();
    }

    public String getGroundTruth() {
        return this.gt;
    }

    public Range getRange() {
        return this.range;
    }

    @Override
    public int size() {
        return this.prediction.size();
    }

    @Override
    public boolean isEmpty() {
        return this.prediction.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return this.prediction.contains(o);
    }

    @NotNull
    @Override
    public Iterator<Pair<String, Double>> iterator() {
        return this.prediction.iterator();
    }

    @NotNull
    @Override
    public Object[] toArray() {
        return this.prediction.toArray();
    }

    @NotNull
    @Override
    public <T> T[] toArray(@NotNull T[] a) {
        return this.prediction.toArray(a);
    }

    @Override
    public boolean add(Pair<String, Double> prediction) {
        return this.prediction.add(prediction);
    }

    @Override
    public boolean remove(Object o) {
        return this.prediction.remove(o);
    }

    @Override
    public boolean containsAll(@NotNull Collection<?> c) {
        return this.prediction.containsAll(c);
    }

    @Override
    public boolean addAll(@NotNull Collection<? extends Pair<String, Double>> c) {
        return this.prediction.addAll(c);
    }

    @Override
    public boolean addAll(int index, @NotNull Collection<? extends Pair<String, Double>> c) {
        return this.prediction.addAll(c);
    }

    @Override
    public boolean removeAll(@NotNull Collection<?> c) {
        return this.prediction.removeAll(c);
    }

    @Override
    public boolean retainAll(@NotNull Collection<?> c) {
        return this.prediction.retainAll(c);
    }

    @Override
    public void clear() {
        this.prediction.clear();
    }

    @Override
    public Pair<String, Double> get(int index) {
        return this.prediction.get(index);
    }

    @Override
    public Pair<String, Double> set(int index, Pair<String, Double> element) {
        return this.prediction.set(index, element);
    }

    @Override
    public void add(int index, Pair<String, Double> element) {
        this.prediction.add(index, element);
    }

    @Override
    public Pair<String, Double> remove(int index) {
        return this.prediction.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return this.prediction.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return this.prediction.lastIndexOf(o);
    }

    @NotNull
    @Override
    public ListIterator<Pair<String, Double>> listIterator() {
        return this.prediction.listIterator();
    }

    @NotNull
    @Override
    public ListIterator<Pair<String, Double>> listIterator(int index) {
        return this.prediction.listIterator(index);
    }

    @NotNull
    @Override
    public List<Pair<String, Double>> subList(int fromIndex, int toIndex) {
        return this.prediction.subList(fromIndex, toIndex);
    }
}

