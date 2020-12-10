package values;

import io.vavr.control.Either;

import java.util.Arrays;

/**
 * Either represents a value of two possible data types.
 * An Either is either a Left or a Right. By convention,
 * ・the Left signifies a failure case result and,
 * ・the Right signifies a success.
 */
public class myEither {
    public static void main(String[] args) {
        playWithEither();
        wowWithEither();
    }

    private static void wowWithEither() {
        Either<String, Integer> value = someComputation().right().map(i -> i * 2).toEither();
        p(value); // Right(20)
    }

    private static void playWithEither() {
        Either<String, Integer> result = computeWithEither(85);
        p(result); // Right(85)
        p(result.isLeft()); // false
        p(result.swap()); // Left(85)
    }

    private static Either<String, Integer> computeWithEither(int marks) {
        if (marks < 85) {
            return Either.left("Marks not acceptable.");
        } else {
            return Either.right(marks);
        }
    }

    private static Either<String, Integer> someComputation() {
        // ... did some works
        return Either.right(10);
    }

    private static void p(Object... inputs) {
        Arrays.stream(inputs).forEach(System.out::println);
    }
}
