import io.vavr.collection.SortedSet;
import io.vavr.collection.TreeSet;

import java.util.Comparator;

public class Sample {
    public static void main(String[] args) {
        SortedSet<Integer> sset = TreeSet.of(2, 3, 1, 5);
        Comparator<Integer> comparator = (a, b) -> b - a;
        SortedSet<Integer> reversed = TreeSet.of(comparator, 2, 3, 1, 5);

        System.out.println(reversed);
    }
}
