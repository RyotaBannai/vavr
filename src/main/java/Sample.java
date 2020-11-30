import io.vavr.collection.SortedSet;
import io.vavr.collection.Stream;
import io.vavr.collection.TreeSet;

import java.util.Comparator;
import java.util.Objects;

public class Sample {
    public static void main(String[] args) {
        myStream();
    }

    private static void myStream() {
        Stream.of(1, 2, 3).map(Objects::toString).forEach(Sample::p);
    }

    private static void myOderedSet() {
        SortedSet<Integer> sset = TreeSet.of(2, 3, 1, 4); // ordered by default.
        Comparator<Integer> comparator = (a, b) -> b - a;
        SortedSet<Integer> reversed = TreeSet.of(comparator, 2, 3, 1, 4);

        p(sset);
        p(reversed); // TreeSet(4, 3, 2, 1)

        SortedSet<Integer> ys = reversed.add(5);
        p(ys); // TreeSet(5, 4, 3, 2, 1)

        SortedSet<Integer> xs = sset.add(5);
        p(xs); // TreeSet(1, 2, 3, 4, 5)
    }

    private static void p(Object input) {
        System.out.println(input);
    }
}
