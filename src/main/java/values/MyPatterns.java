package values;

import io.vavr.API.*;
import io.vavr.CheckedFunction2;
import io.vavr.control.Either;
import io.vavr.control.Option;
import io.vavr.control.Try;

import java.util.Arrays;


import static io.vavr.Patterns.*;
import static io.vavr.API.*;
import static io.vavr.Predicates.*;

public class MyPatterns {
    public static void main(String[] args) {
        myPatterns();
        tryAndRecover();
    }

    private static CheckedFunction2<Double, Double, Option<Double>> divide =
            (Double first, Double second) -> Option.of(first / second);

    private static void myPatterns() {
        Either<Double, Throwable> matchResult =
                Match(Try.of(() -> 1.0 / 0.0)).of(
                        Case($Failure($()), Either::right),
                        Case($Success($()), Either::left));
        p(matchResult);  // Left(Infinity) // why..?
    }

    private static void tryAndRecover() {
        Try<Option<Double>> result = Try.of(() -> divide.apply(1.0, 0.0))
                .recover(x -> Match(x).of(
                        Case($(instanceOf(ArithmeticException.class)), t -> Option.of(0.0)))); // この段階で Some(Infinity) だから Error にはマッチしない.
        p(result);  // Success(Some(Infinity))
    }


    private static void p(Object... inputs) {
        Arrays.stream(inputs).forEach(System.out::println);
    }
}
