package archive;

import io.vavr.collection.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

public class Sample {
    public static void main(String[] args) {
        myCharSeq();
    }

    private static void myCharSeq() {
        CharSeq chars = CharSeq.of("vavr");
        CharSeq newChars = chars.replace("v", "V");
        p(newChars); // VaVr
        p(chars.charAt(1)); // a
        p(chars.mkString()); // vavr
    }

    private static void advancedUsage1() {
        String[] works = {"programmer", "engineer", "manager"};

        // these are all the same results.
        // programmer, engineer, manager
        p(myJoin(works));
        p(List.of(works).mkString(", "));
        p(List.of(works).reduce((first, second) -> first + ", " + second));
    }

    private static String myJoin(String... works) {
        // List in vavr library
        return List.of(works)
                .intersperse(", ")
                .foldLeft(new StringBuilder(), StringBuilder::append)
                .toString();
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

    private static void p(Object... inputs) {
        Arrays.stream(inputs).forEach(System.out::println);
    }
}
