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
        Option<String> maybeFoo = Option.of("foo");
    }

    private static void javaOptional() {
        Optional<String> maybeFoo = Optional.of("foo");
        p(maybeFoo); // Optional[foo]

        Optional<String> maybeNone = null;
        p(maybeNone); // null

        Optional<String> maybeFooBar = maybeFoo
                .map(foo -> (String) null)
                .map(definitelyNull -> definitelyNull.toUpperCase() + "bar");
        p(maybeFooBar); // Optional.empty
        p(maybeFooBar.isPresent()); // false
    }

    private static void p(Object... inputs) {
        Arrays.stream(inputs).forEach(System.out::println);
    }
}
