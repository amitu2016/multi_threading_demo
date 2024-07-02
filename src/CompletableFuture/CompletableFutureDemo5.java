package CompletableFuture;

import java.util.concurrent.*;

public class CompletableFutureDemo5 {

    public static void main(String[] args) {
        try {
            ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(3,4,1,
                    TimeUnit.HOURS, new ArrayBlockingQueue<>(10), Executors.defaultThreadFactory(),
                    new ThreadPoolExecutor.AbortPolicy());

            CompletableFuture<Integer> asyncTask1 = CompletableFuture.supplyAsync(() -> {
                return 10;
            },poolExecutor);

            CompletableFuture<String> asyncTask2 = CompletableFuture.supplyAsync(() -> {
                return "K";
            },poolExecutor);




        }catch (Exception e){

        }
    }
}
