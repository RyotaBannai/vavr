package queue;

import io.vavr.Tuple2;
import io.vavr.collection.List;
import io.vavr.collection.Queue;

import java.util.Arrays;

public class Sample {
    public static void main(String[] args) {
        Queue<Integer> queue = Queue.of(1, 2);
        p(queue);

        Queue<Integer> newQueue = queue.enqueueAll(List.of(4, 5));
        p(newQueue); // Queue(1, 2, 4, 5)

        Tuple2<Integer, Queue<Integer>> dequeued = newQueue.dequeue();
        p(dequeued); // (1, Queue(2, 4, 5))

        Queue<Integer> taildQueue = dequeued._2;
        p(taildQueue); // goo. Queue(2, 4, 5)
        p(dequeued._1); // 1
    }


    private static void p(Object... inputs) {
        Arrays.stream(inputs).forEach(System.out::println);
    }
}
