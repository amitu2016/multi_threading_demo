package Monitor_Lock;

public class MonitorLockExample {

    public synchronized void task1(){
        try {
            System.out.println("Inside Task 1");
            Thread.sleep(10000);
            System.out.println("Task 1 completed");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void task2() {
        System.out.println("Inside Task 2, before Synchronization");
        synchronized (this){
            System.out.println("Inside Task 2, after Synchronization");
        }
    }

    public void task3(){
        System.out.println("Inside Task 3");
    }
}
