package CompletableFuture;

import java.util.concurrent.*;

public class CompletableFutureDemo {
    public static void main(String[] args) {

        try {

            ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(3,4,1,
                    TimeUnit.HOURS, new ArrayBlockingQueue<>(10), Executors.defaultThreadFactory(),
                    new ThreadPoolExecutor.AbortPolicy());

            CompletableFuture<String> asyncTask = CompletableFuture.supplyAsync(() -> {
                //The task to be completed
                return "Task Completed";
            }, poolExecutor);


            System.out.println(asyncTask.get());

            poolExecutor.shutdown();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
