package WorkStealingPool_ForkJoinPool;

import java.util.concurrent.RecursiveTask;

public class ComputeSumTask extends RecursiveTask<Integer> {

    int start;
    int end;

    public ComputeSumTask(int start, int end){
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if (end - start <= 4){
            int totalSum = 0;
            for (int i = start; i <= end; i++) {
                totalSum += i;
            }
            return totalSum;
        }else {
            //Split the task
            int mid = (start + end) / 2;
            ComputeSumTask leftTask = new ComputeSumTask(start, mid);
            ComputeSumTask rightTask = new ComputeSumTask(mid+1, end);

            //Fork the subtasks for parallel execution
            leftTask.fork();
            rightTask.fork();

            //Combine the result of subtask
            int leftResult = leftTask.join();
            int rightResult = rightTask.join();

            return leftResult + rightResult;
        }
    }
}
