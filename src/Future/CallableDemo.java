package Future;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CallableDemo {

    public static void main(String[] args) {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(3,4,1,
                TimeUnit.HOURS, new ArrayBlockingQueue<>(10), Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        //Using Runnable, returns nothing
        Future<?> futureObj1 = poolExecutor.submit(() -> {
            System.out.println("Task 1 with Runnable");
        });

        try {
            Object object = futureObj1.get();
            System.out.println(object == null);
        }catch (Exception e){

        }

        //Using runnable to return value, but it is work around, not a cleaner way
        List<Integer> output = new ArrayList<>();
        Future<List<Integer>> futureObj2 = poolExecutor.submit(() -> {
            output.add(100);
            System.out.println("Task 2 with runnable object and return");
        }, output);

        try {
            List<Integer> outputFromFutureObj2 = futureObj2.get();
            System.out.println(outputFromFutureObj2.get(0));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //Using callable to return, more cleaner way
        Future<List<Integer>> futureObj3 = poolExecutor.submit(() -> {
            System.out.println("Task 3 with callable");
            List<Integer> listObj = new ArrayList<>();
            listObj.add(10);
            return listObj;
        });

        try {
            List<Integer> outputFromFutureObj3 = futureObj3.get();
            System.out.println(outputFromFutureObj3.get(0));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        poolExecutor.shutdown();


    }
}
