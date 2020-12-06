package array;

import io.vavr.collection.Array;

import java.util.Arrays;

public class Sample {
    public static void main(String[] args) {
        Array<Integer> myArray = Array.rangeClosedBy(1, 6, 2); // Array(1, 3, 5)
        p(myArray);

        p(myArray.removeAt(1)); // Array(1,5)
        p(myArray.replace(1, 10)); // Array(10, 3, 5) // replace the value which equals to 1, not nth nor index.
    }

    private static void p(Object... inputs) {
        Arrays.stream(inputs).forEach(System.out::println);
    }
}
