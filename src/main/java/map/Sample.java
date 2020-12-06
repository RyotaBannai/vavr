package map;

import io.vavr.Tuple;
import io.vavr.collection.HashMap;
import io.vavr.collection.Map;
import io.vavr.collection.SortedMap;
import io.vavr.collection.TreeMap;

import java.util.Arrays;
import java.util.function.Predicate;

public class Sample {
    public static void main(String[] args) {
        myTreeMap();
    }

    /**
     * String -> Integer に cast したい時は, Integer.valueOf を使い、
     * 型推論を補足する場合は、Integer.valueOf などの boxing はやりすぎなので、(int) を使う.
     */
    private static void mapBasics() {
        Map<String, String> map = HashMap.of("key1", "value1", "key2", "value2", "key3", "value3");
        p(map); // HashMap((key1, value1), (key2, value2), (key3, value3))

        Predicate<String> contains1Or2 = key -> key.contains("1") || key.contains("2");
        Map<String, String> fMap = map.filterKeys(contains1Or2);

        p(fMap); // HashMap((key1, value1), (key2, value2))

        Map<String, Integer> mappedMap = map.map((key, value) -> Tuple.of(key, Integer.valueOf(value.charAt(value.length() - 1) + ""))); // + "" -> cast
        Map<String, Integer> mappedMap2 = map.map((key, value) -> Tuple.of(key, Integer.valueOf(String.valueOf(value.charAt(value.length() - 1))))); // the same
        p(mappedMap);  // HashMap((key1, 1), (key2, 2), (key3, 3))


        Map<String, Integer> mappedMap3 = map.map((key, value) -> Tuple.of(key, (int) value.charAt(value.length() - 1))); // w/o cast
        p(mappedMap2); // HashMap((key1, 49), (key2, 50), (key3, 51))
    }

    /**
     * By default, entries of TreeMap are sorted in the natural order of the keys
     */
    private static void myTreeMap() {
        SortedMap<Integer, String> sortedMap = TreeMap.of(3, "Three", 2, "Two", 1, "One");
        p(sortedMap); // TreeMap((1, One), (2, Two), (3, Three))


    }

    private static void p(Object... inputs) {
        Arrays.stream(inputs).forEach(System.out::println);
    }
}
