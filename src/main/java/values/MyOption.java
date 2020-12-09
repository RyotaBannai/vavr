package values;

import io.vavr.control.Option;

import java.util.Arrays;
import java.util.Optional;

public class MyOption {
    public static void main(String[] args) {
        javaOptional();
        vavrOption();
    }

    private static void vavrOption() {
        System.out.println("vavr Option");
        Option<String> maybeFoo = Option.of("foo");
        try {
            maybeFoo.map(foo -> (String) null)
                    .map(definitelyNull -> definitelyNull.toUpperCase() + "bar");
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        // Correct way to deal with the occurrences of null is to use flatMap
        Option<String> maybeFooBar = maybeFoo
                .map(foo -> (String) null)
                .flatMap(definitelyNull -> Option.of(definitelyNull) // Option.of(null) will be translated to None
                        .map(optionDefinitelyNull -> optionDefinitelyNull.toUpperCase() + "bar")); // Value が None だと処理されない.
        p(1, ":", maybeFooBar); // None


        // Or as another way to deal with null in map
        Option<String> maybeFooBar2 = maybeFoo
                .flatMap(foo -> Option.of((String) null))
                .map(definitelyNone -> definitelyNone.toUpperCase() + "bar");
        p(2, ":", maybeFooBar2);
    }

    private static void javaOptional() {
        System.out.println("Java Optional");
        Optional<String> maybeFoo = Optional.of("foo");
        p(1, ":", maybeFoo); // Optional[foo]

        Optional<String> maybeNone = null;
        p(2, ":", maybeNone); // null

        Optional<String> maybeFooBar = maybeFoo
                .map(foo -> (String) null)
                .map(definitelyNull -> definitelyNull.toUpperCase() + "bar");
        p(3, ":", maybeFooBar); // Optional.empty
        p(4, ":", maybeFooBar.isPresent()); // false
    }

    private static void p(Object... inputs) {
        Arrays.stream(inputs).forEach(System.out::print);
        System.out.println("");
    }
}
