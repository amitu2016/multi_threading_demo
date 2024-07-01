package CompletableFuture;

import java.util.concurrent.*;

public class CompletableFutureDemo2 {

    public static void main(String[] args) {
        try {

            ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(3,4,1,
                    TimeUnit.HOURS, new ArrayBlockingQueue<>(10), Executors.defaultThreadFactory(),
                    new ThreadPoolExecutor.AbortPolicy());

            CompletableFuture<String> asyncTask1 = CompletableFuture.supplyAsync(() -> {
                System.out.println("Thread name inside supplyAsync: "+Thread.currentThread().getName());
                return "Concept ";
            },poolExecutor).thenApply((val) -> {
                System.out.println("Thread name inside thenApply: "+Thread.currentThread().getName());
                return val+"And ";
            }).thenApplyAsync((val) -> {
                System.out.println("Thread name inside thenApplyAsync: "+Thread.currentThread().getName());
                return val+"Coding";
            },poolExecutor);

            System.out.println(asyncTask1.get());

            poolExecutor.shutdown();

        }catch (Exception e){

        }
    }
}
