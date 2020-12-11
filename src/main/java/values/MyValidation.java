package values;

import io.vavr.collection.CharSeq;
import io.vavr.collection.Seq;
import io.vavr.control.Validation;

import java.util.Arrays;

public class MyValidation {
    public static void main(String[] args) {
        PersonValidator personValidator = (new MyValidation()).new PersonValidator();

        Validation<Seq<String>, Person> valid = personValidator.validatePerson("John Doe", 30);
        p(valid); // Valid(Person(John Doe, 30))

        Validation<Seq<String>, Person> invalid = personValidator.validatePerson("John? Doe!4", -1);
        p(invalid); // Invalid(List(Name contains invalid characters:'!4?', Age must be at least 0))

    }

    private class PersonValidator {
        private static final String VALID_NAME_CHARS = "[a-zA-Z ]";
        private static final int MIN_INT = 0;

        public Validation<Seq<String>, Person> validatePerson(String name, int age) {
            return Validation.combine(validateName(name), validateAge(age)).ap(Person::new);
        }

        // Validation の type は Either と同じように指定
        private Validation<String, String> validateName(String name) {
            return CharSeq.of(name).replaceAll(VALID_NAME_CHARS, "")
                    .transform(seq -> seq.isEmpty()
                            ? Validation.valid(name)
                            : Validation.invalid("Name contains invalid characters:'" + seq.distinct().sorted() + "'"));
        }

        private Validation<String, Integer> validateAge(int age) {
            return age < MIN_INT
                    ? Validation.invalid("Age must be at least " + MIN_INT)
                    : Validation.valid(age);
        }


    }

    private class Person {
        private final String name;
        private final Integer age;

        public Person(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return String.format("Person(%s, %d)", name, age);
        }
    }

    private static void p(Object... inputs) {
        Arrays.stream(inputs).forEach(System.out::println);
    }
}

