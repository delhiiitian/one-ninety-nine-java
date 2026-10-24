// 9.5 CompletableFuture: chaining work that finishes later
// Run: java CompletableFutureDemo.java
import java.util.concurrent.CompletableFuture;

public class CompletableFutureDemo {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        // Ordering food online: three restaurants prepare their parts at the same time
        CompletableFuture<String> pizza = CompletableFuture.supplyAsync(() -> cook("pizza", 300));
        CompletableFuture<String> drinks = CompletableFuture.supplyAsync(() -> cook("drinks", 100));
        CompletableFuture<String> dessert = CompletableFuture.supplyAsync(() -> cook("dessert", 200));

        // Combine when all are done
        CompletableFuture<String> order = pizza
                .thenCombine(drinks, (p, d) -> p + " + " + d)
                .thenCombine(dessert, (pd, ds) -> pd + " + " + ds);
        System.out.println("Delivered: " + order.join());

        long elapsed = System.currentTimeMillis() - start;
        System.out.println("Took less than 600 ms (run in parallel)? " + (elapsed < 600));

        // Transform a result, then react to it, without blocking in between
        String receipt = CompletableFuture.supplyAsync(() -> 42.50)
                .thenApply(total -> total * 1.1)                      // add 10% service
                .thenApply(total -> String.format("Charged $%.2f", total))
                .join();
        System.out.println(receipt);

        // Handle failures as part of the chain
        String status = CompletableFuture.supplyAsync(() -> {
                    if (true) throw new IllegalStateException("payment gateway down");
                    return "paid";
                })
                .exceptionally(error -> "fallback: pay on delivery (" + error.getCause().getMessage() + ")")
                .join();
        System.out.println(status);
    }

    static String cook(String item, long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return item;
    }
}
