package values;

import io.vavr.control.Try;

import java.util.Arrays;

import static io.vavr.API.*;
import static io.vavr.Predicates.*;

public class myTry {
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
}
