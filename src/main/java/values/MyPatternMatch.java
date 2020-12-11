package values;

import io.vavr.control.Option;

import java.util.Arrays;

import static io.vavr.API.*;
import static io.vavr.Predicates.*;

public class MyPatternMatch {
    public static void main(String[] args) {
        sideEffect("-h");
        myNamedParameters(2);
    }

    private static void sideEffect(String arg) {
        Match(arg).of(
                Case($(isIn("-h", "--help")), o -> run(() -> System.out.println("Help: ...."))),
                Case($(isIn("-v", "--version")), o -> run(() -> System.out.println("Version: ...."))),
                Case($(), o -> run(() -> {
                    throw new IllegalArgumentException("error");
                })));

    }

    private static void myNamedParameters(Object obj) {
       Object result = Match(obj).of(
                Case($(instanceOf(Integer.class)), i -> i * i),
                Case($(instanceOf(String.class)), s -> s + s),
                Case($(), o -> {
                    throw new NumberFormatException();
                })
        );
       p(result);
    }

    private static void basics() {
        Integer i = 2;
        String myString = Match(i).of(
                Case($(1), "one"),
                Case($(2), "two"),
                Case($(), "?")
        );
        p(myString); // two


        Option<String> myOptionString = Match(i).option(
                Case($(0), "zero")
        );

        p(myOptionString); // None
    }

    private static void p(Object... inputs) {
        Arrays.stream(inputs).forEach(System.out::println);
    }
}
