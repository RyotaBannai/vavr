package function;

import io.vavr.Function0;
import io.vavr.Function1;
import io.vavr.Function2;
import io.vavr.control.Option;

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
        myLifting();
        myCurrying();
        myMemorization();
    }

    /**
     * The functions f : X → Y and g : Y → Z can be composed to yield a function h: g(f(x)) which maps X → Z.
     * You can use either andThen:
     */
    private static void myComposition() {
        Function1<Integer, Integer> plusOne = x -> x + 1;
        Function1<Integer, Integer> multiplyByTwo = x -> x * 2;
        Function1<Integer, Integer> add1AndMultiplyBy2 = plusOne.andThen(multiplyByTwo);
        Function1<Integer, Integer> lessIntuitiveWayOfAdd1AndMultiplyBy2 = multiplyByTwo.compose(plusOne); // the same as above. // but looks like real function composition.

        p(add1AndMultiplyBy2.apply(1)); // 4
    }

    /**
     * A function f: X → Y by not forcing f to map every element of X to an element of Y.
     * That means a partial function works properly **only for some input values**.
     * If the function is called with a disallowed input value, it will typically throw an exception.
     */
    private static void myLifting() {
        Function2<Integer, Integer, Integer> divide = (a, b) -> a / b;
        Function2<Integer, Integer, Option<Integer>> safeDivide = Function2.lift(divide); // <- A lifted function

        Option<Integer> i1 = safeDivide.apply(1, 0); // = None // called with a disallowed input value.
        Option<Integer> i2 = safeDivide.apply(4, 2); // = Some // works properly for some input value.

        p(i1, i2);

        Function2<Integer, Integer, Option<Integer>> safeSum = Function2.lift(Sample::sum);
        Option<Integer> optionalResult = safeSum.apply(-1, 2); // = None

        p(optionalResult);


        // Partial Application
        Function1<Integer, Integer> partiallyApplied = divide.apply(1);
        p(partiallyApplied.apply(1)); // = 1
        // end of PA
    }

    // custom function in real world for lifting.
    private static int sum(int first, int second) {
        if (first < 0 || second < 0) {
            throw new IllegalArgumentException("Only positive integers are allowed.");
        }
        return first + second;
    }

    /**
     * Currying is a technique to partially apply a function by fixing a value for one of the parameters,
     * resulting in a Function1 function that returns a Function1
     * *
     * When a Function2 is curried, the result is indistinguishable from the partial application of a Function2,
     * because both result in a 1 arity function.
     * *
     * An arity function: one or more of arguments are taken by function
     * -> 1 arity function = one argument is taken by function.
     */
    private static void myCurrying() {
        Function2<Integer, Integer, Integer> sum = (first, second) -> first + second;
        Function1<Integer, Integer> add2 = sum.curried().apply(2); // 1 arity function.

        p(add2.apply(2)); // 4
    }

    private static void myMemorization() {
        // Function0 takes zero argument and return one result, just like Consumer.
        Function0<Double> hashCache = Function0.of(Math::random).memoized();
        double randomValue1 = hashCache.apply();
        double randomValue2 = hashCache.apply();

        // These are the same value.
        p(randomValue1);
        p(randomValue2);
    }

    private static void p(Object... inputs) {
        Arrays.stream(inputs).forEach(System.out::println);
    }
}
