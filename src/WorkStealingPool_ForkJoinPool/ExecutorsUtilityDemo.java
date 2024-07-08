package WorkStealingPool_ForkJoinPool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

public class ExecutorsUtilityDemo {

    public static void main(String[] args) {
        ForkJoinPool pool = ForkJoinPool.commonPool();

        Future<Integer> futureObj = pool.submit(new ComputeSumTask(0, 100));

        try {
            System.out.println(futureObj.get());
        }catch (Exception e){

        }
    }
}
