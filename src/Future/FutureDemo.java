package Future;

import java.util.concurrent.*;

public class FutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {

        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(1,1,1,
                TimeUnit.HOURS, new ArrayBlockingQueue<>(10), Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        Future<?> futureObj = poolExecutor.submit(() -> {
            try {
                Thread.sleep(7000);
                System.out.println("This task will be executed by the thread");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        System.out.println(futureObj.isDone());

        try {
            futureObj.get(2, TimeUnit.SECONDS);
        }catch (TimeoutException e){
            System.out.println("Timeout Exception Happened");
        }catch (Exception e){

        }

        try {
            futureObj.get();
        }catch (Exception e){

        }

        System.out.println("is Done: "+futureObj.isDone());
        System.out.println("is Cancelled: "+ futureObj.isCancelled());

    }
}
