package stream;

import io.vavr.Tuple2;
import io.vavr.collection.List;
import io.vavr.collection.Stream;

import java.util.Arrays;
import java.util.function.Function;

public class Sample {
    public static void main(String[] args) {
        makeZip();
    }

    private static void makeZip() {
        Stream<String> keyStream = Stream.of("langName", "difficulty");
        Stream<String> valueStream = Stream.of("Java", "3");
        Stream<Tuple2<String, String>> langTuple = keyStream.zip(valueStream); // zip can take Iterable (Stream or List)
        langTuple.forEach(System.out::println); // (langName, Java) \n (difficulty, 3)

        // how to access to element...
        p(langTuple.get(0)._1); // -> langName
    }

    private static void displayOnlyPrime() {
        Stream.iterate(2L, Sample::nextPrimeFrom) //
                .take(10)
                .forEach(prime -> System.out.print(prime + ", ")); // 2, 3, 5, 7, 11, 13, 17, 19, 23, 29
    }

    private static long nextPrimeFrom(long num) {
        return Stream.from(num + 1).find(Sample::isPrime).get();
    }

    private static boolean isPrime(long num) {
        // prime is the number cannot be divided by less than the sqrt root of the number
        return !Stream.rangeClosed(2L, (long) Math.sqrt(num))
                .exists(d -> num % d == 0);
    }

    private static void createStreamInOtherMeans() {
        p(List.Nil.instance());
        p(Stream.from(0)); // Stream(0, ?) // = 0, 1, 2, 3, ...
        p(Stream.of(new Integer[]{1, 2, 3})); // Stream(1, ?)
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
