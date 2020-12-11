package values;

import io.vavr.Function0;
import io.vavr.Lazy;
import io.vavr.control.Try;

import java.util.Arrays;

import static io.vavr.API.*;
import static io.vavr.Predicates.*;

public class MyTry {
    public static void main(String[] args) {
        tryAndRecover();
    }

    /**
     * try と recover で、
     * 0 で割って ArithmeticException が発生する場合に、0.0 を返すようにする。
     */
    private static void tryAndRecover() {
        Try<Double> result = Try.of(() -> divide(1, 0))
                .recover(x -> Match(x).of(
                        Case($(instanceOf(ArithmeticException.class)), t -> 0.0)
                ));
        p(result);
    }

    private static double divide(int first, int second) {
        return first / second;
    }

    private static void p(Object... inputs) {
        Arrays.stream(inputs).forEach(System.out::print);
        System.out.println("");
    }

    /**
     * While `memorization` is used to memo an evaluated value,
     * `lazy` is used to postpone an evaluation until it's actually used.
     */
    private static void lazyAndMemorization() {
        // memorization
        Function0<Double> hashCache = Function0.of(Math::random).memoized();
        double randomValue1 = hashCache.apply();
        double randomValue2 = hashCache.apply();

        // lazy
        Lazy<Double> lazy = Lazy.of(Math::random);
        lazy.isEvaluated(); // = false
        lazy.get();         // = 0.123 (random generated)
        lazy.isEvaluated(); // = true
        lazy.get();
    }
}
