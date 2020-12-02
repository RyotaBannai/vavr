package tuple;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.List;
import io.vavr.collection.Map;

import java.util.Arrays;

public class Sample {
    public static void main(String[] args) {
        // LinkedHashMap((1, List(1, 3)), (0, List(2, 4)))
        Map<Integer, List<Integer>> myMap = List.of(1, 2, 3, 4).groupBy(i -> i % 2);
        myMap.forEach(System.out::println);
    }

    private static void myEntry() {
        Tuple2<Integer, String> entry = Tuple.of(1, "A");
        Integer key = entry._1;
        String value = entry._2;
        p(key, value);
    }

    private static void p(Object... inputs) {
        Arrays.stream(inputs).forEach(System.out::println);
    }
}
