package function;

import io.vavr.Function1;

import java.util.Arrays;

public class Sample {
    /**
     * Composition
     * Lifting
     * Currying
     * Memoization
     */
    public static void main(String[] args) {
        myComposition();
    }

    /**
     * The functions f : X → Y and g : Y → Z can be composed to yield a function h: g(f(x)) which maps X → Z.
     * You can use either andThen:
     */
    private static void myComposition() {
        Function1<Integer, Integer> plusOne = x -> x + 1;
        Function1<Integer, Integer> multiplyByTwo = x -> x * 2;
        Function1<Integer, Integer> add1AndMultiplyBy2 = plusOne.andThen(multiplyByTwo);
        Function1<Integer, Integer> lessIntuitiveWayOfAdd1AndMultiplyBy2 = multiplyByTwo.compose(plusOne); // the same as above.

        p(add1AndMultiplyBy2.apply(1)); // 4
    }

    private static void myLifting() {

    }

    private static void p(Object... inputs) {
        Arrays.stream(inputs).forEach(System.out::println);
    }
}
