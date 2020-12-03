package list;

import io.vavr.collection.List;

import java.util.Arrays;

public class Sample {
    public static void main(String[] args) {
        mimicLIFO();
    }

    private static void mimicLIFO() {
        List<String> list = createList();

        // there are API methods for manipulating a stack such as peek(), pop() and push()
        List<String> newList = list.pushAll(List.of("Python", "Javascript"));
        p(newList); // pushAll methods push all before first element. // -> List(Javascript, Python, Java, Kotlin, Go, Scala, C++, Julia, Rust)

        p(newList.peek()); // first element of list

        List<String> anotherNewList = list.pop(); // remove first element
        p(anotherNewList);
        // -> last in first out. LIFO
    }

    private static void myGroupMethod() {
        List<String> list = createList();
        p(list.grouped(2).head()); // List(Java, Kotlin)
        p(list.groupBy(lang -> lang.charAt(0)));
    }

    private static void prettyBasics() {
        List<String> list = createList();
        p(list.drop(1)); // 1 is the first element. not 0. so the result is List(Kotlin, Go, Scala, C++, Julia, Rust)
        p(list.drop(1).contains("Java")); // false

        p(list.dropRight(1)); // List(Java, Kotlin, Go, Scala, C++, Julia)
        p(list.dropUntil(lang -> lang.contains("Scala"))); // List(Scala, C++, Julia, Rust)
        p(list.dropWhile(lang -> lang.length() > 0).isEmpty()); // true

        p(list.take(1).single());
    }

    private static List<String> createList() {
        List<String> list1 = List.of("Java", "Kotlin", "Go", "Scala", "C++", "Julia", "Rust");
        return list1;
    }

    private static void p(Object... inputs) {
        Arrays.stream(inputs).forEach(System.out::println);
    }
}
