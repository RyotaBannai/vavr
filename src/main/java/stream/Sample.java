package stream;

import io.vavr.collection.Stream;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Supplier;

public class Sample {
    public static void main(String[] args) {
        produceStream();
    }

    private static void produceStream() {
        Function<Integer, String> of = i -> String.valueOf(i * i);
        Stream<String> myStream = Stream.tabulate(5, of);
        myStream.intersperse(", ").forEach(System.out::print); // 0, 1, 4, 9, 16
    }

    private static void calculate() {
        Function<Integer, Integer> of = i -> i + 1;
        Stream<Integer> myStream = Stream.iterate(0, of);
        Stream<Integer> tenElements = myStream.take(10);
        Stream<Integer> evenNumbers = tenElements.filter(i -> i % 2 == 0);
        p(evenNumbers.sum().longValue()); // Integer を longValue -> long, doubleValue -> double へ cast // displays 20
    }

    private static void basics() {
        Stream<Integer> myStream = Stream.of(4, 1, 2, 3);

        p(myStream); // Stream(4, ?)
        p(myStream.get(3)); // 3
        p(myStream.tail()); // Stream(1, 2, 3, ?) // ひとつ上で index 3 まで evaluate しているため、全部表示される. tail -> drop first element of a non-empty Traversable.
    }

    private static void p(Object... inputs) {
        Arrays.stream(inputs).forEach(System.out::println);
    }
}
