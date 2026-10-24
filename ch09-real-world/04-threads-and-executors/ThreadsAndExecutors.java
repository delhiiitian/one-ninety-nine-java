// 9.4 Threads and executors: doing several things at once
// Run: java ThreadsAndExecutors.java
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadsAndExecutors {
    public static void main(String[] args) throws Exception {
        // A raw thread: a second worker running alongside main
        Thread kettle = new Thread(() -> {
            sleep(100);
            System.out.println("Kettle: water boiled");
        });
        kettle.start();
        System.out.println("Main: making toast while the kettle boils");
        kettle.join();                      // wait for the kettle before pouring
        System.out.println("Main: pouring tea");

        // An executor is a team of workers with a shared to-do list
        ExecutorService kitchen = Executors.newFixedThreadPool(3);
        List<Future<String>> dishes = new ArrayList<>();
        for (String dish : List.of("soup", "pasta", "salad", "cake")) {
            dishes.add(kitchen.submit(() -> {
                sleep(50);
                return dish + " ready";      // a Callable returns a result
            }));
        }
        for (Future<String> f : dishes) {
            System.out.println("Served: " + f.get());   // get() waits for that result
        }

        // Shared counters must be thread-safe: AtomicInteger never loses an update
        AtomicInteger visits = new AtomicInteger();
        List<Future<?>> jobs = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            jobs.add(kitchen.submit(() -> {
                for (int j = 0; j < 1000; j++) visits.incrementAndGet();
            }));
        }
        for (Future<?> j : jobs) j.get();
        System.out.println("Visits counted: " + visits.get() + " (expected 4000)");

        kitchen.shutdown();                  // always shut executors down

        // Java 21 virtual threads: cheap enough to start thousands
        try (ExecutorService many = Executors.newVirtualThreadPerTaskExecutor()) {
            AtomicInteger done = new AtomicInteger();
            for (int i = 0; i < 10_000; i++) {
                many.submit(() -> { sleep(10); done.incrementAndGet(); });
            }
            many.close();                    // waits for every task
            System.out.println("Virtual threads finished: " + done.get());
        }
    }

    static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
