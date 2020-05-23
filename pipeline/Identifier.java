import com.github.javaparser.Range;
import org.jetbrains.annotations.NotNull;
import slp.core.util.Pair;
import java.util.*;
import static java.lang.String.format;
import static java.lang.String.join;

public class Identifier implements List<List<String>> {

    private String name;

    private Range range;

    public List<List<String>> usages = new ArrayList<>();

    public Identifier(String name, Range range) {
        this.name = name;
        this.range = range;
    }

    public Identifier(String name, Range range, List<List<String>> usages) {
        this.name = name;
        this.range = range;
        this.usages = usages;
    }

    public String getName() {
        return this.name;
    }

    public Range getRange() {
        return this.range;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(format("Name: %s\nRange: %s\n", this.name, this.range));
        this.usages.forEach(x -> result.append(join(" ", x)).append('\n'));
        return result.toString();
    }

    @Override
    public int size() {
        return this.usages.size();
    }

    @Override
    public boolean isEmpty() {
        return this.usages.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return this.usages.contains(o);
    }

    @Override
    public Iterator<List<String>> iterator() {
        return this.usages.iterator();
    }

    @NotNull
    @Override
    public Object[] toArray() {
        return this.usages.toArray();
    }

    @NotNull
    @Override
    public <T> T[] toArray(@NotNull T[] a) {
        return this.usages.toArray(a);
    }

    @Override
    public boolean add(List<String> usage) {
        return this.usages.add(usage);
    }

    @Override
    public boolean remove(Object o) {
        return this.usages.remove(o);
    }

    @Override
    public boolean containsAll(@NotNull Collection<?> c) {
        return this.usages.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends List<String>> c) {
        return this.usages.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends List<String>> c) {
        return this.usages.addAll(c);
    }

    @Override
    public boolean removeAll(@NotNull Collection<?> c) {
        return this.usages.removeAll(c);
    }

    @Override
    public boolean retainAll(@NotNull Collection<?> c) {
        return this.usages.retainAll(c);
    }

    @Override
    public void clear() {
        this.usages.clear();
    }

    @Override
    public List<String> get(int index) {
        return this.usages.get(index);
    }

    @Override
    public List<String> set(int index, List<String> element) {
        return this.usages.set(index, element);
    }

    @Override
    public void add(int index, List<String> element) {
        this.usages.add(index, element);
    }

    @Override
    public List<String> remove(int index) {
        return this.usages.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return this.usages.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return this.usages.lastIndexOf(o);
    }

    @Override
    @NotNull
    public ListIterator<List<String>> listIterator() {
        return this.usages.listIterator();
    }

    @Override
    @NotNull
    public ListIterator<List<String>> listIterator(int index) {
        return this.usages.listIterator(index);
    }

    @Override
    @NotNull
    public List<List<String>> subList(int fromIndex, int toIndex) {
        return this.usages.subList(fromIndex, toIndex);
    }
}
