package CompletableFuture;

import java.util.concurrent.*;

public class CompletableFutureDemo3 {



        public static void main(String[] args) {
            try {
            ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(3,4,1,
                    TimeUnit.HOURS, new ArrayBlockingQueue<>(10), Executors.defaultThreadFactory(),
                    new ThreadPoolExecutor.AbortPolicy());

            CompletableFuture<String> asyncTask = CompletableFuture.supplyAsync(() -> {
                        System.out.println("Thread running supplyAsync: "+Thread.currentThread().getName());
                        return "Concept ";
                    },poolExecutor)
                    .thenCompose((val) -> {
                        return CompletableFuture.supplyAsync(() -> {
                            System.out.println("Thread running thenCompose: "+Thread.currentThread().getName());
                            return val+"And ";
                        });
                    }).thenComposeAsync((val) -> {
                        return CompletableFuture.supplyAsync(() -> {
                            System.out.println("Thread running thenComposeAsync: "+Thread.currentThread().getName());
                            return val+"Coding";
                        });
                    },poolExecutor);
            System.out.println(asyncTask.get());

            poolExecutor.shutdown();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
