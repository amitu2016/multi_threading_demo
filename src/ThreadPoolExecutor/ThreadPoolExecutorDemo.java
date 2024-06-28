package ThreadPoolExecutor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorDemo {
    public static void main(String[] args) {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(2, 5, 10,
                TimeUnit.MINUTES, new ArrayBlockingQueue<>(10), new CustomThreadFactory(),
                new CustomRejectHandler());

        poolExecutor.allowCoreThreadTimeOut(true);

        for (int i = 0; i < 7; i++) {
            poolExecutor.submit(() ->{
                try {
                    Thread.sleep(8000);
                    System.out.println("Task Processed By Thread Name: "+Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        poolExecutor.shutdown();
    }
}
