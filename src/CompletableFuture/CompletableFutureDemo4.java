package CompletableFuture;

import java.util.concurrent.*;

public class CompletableFutureDemo4 {

    public static void main(String[] args) {
       try {
           ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(3,4,1,
                   TimeUnit.HOURS, new ArrayBlockingQueue<>(10), Executors.defaultThreadFactory(),
                   new ThreadPoolExecutor.AbortPolicy());
           CompletableFuture<Void> asyncTask = CompletableFuture.supplyAsync(() -> {
               System.out.println("Thread :"+Thread.currentThread().getName());
               return "Concept and";
           },poolExecutor).thenComposeAsync((val) -> {
               return CompletableFuture.supplyAsync(() -> {
                   System.out.println("Thread :"+Thread.currentThread().getName());
                   return "Coding";
               },poolExecutor);
           }).thenAccept((String val) -> {
               System.out.println("All step completed");
           });

           System.out.println(asyncTask.get());

           poolExecutor.shutdown();
       }catch (Exception e){

       }

    }
}
