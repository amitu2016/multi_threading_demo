package Stamped_Lock;

import java.util.concurrent.locks.StampedLock;

public class SharedResource {
    int a = 10;
    StampedLock lock = new StampedLock();

    public void produce(){
        long stamp = lock.tryOptimisticRead();

        try {
            System.out.println("Taken Optimistic Read: "+Thread.currentThread().getName());
            a = 11;
            Thread.sleep(6000);
            if (lock.validate(stamp)){
                System.out.println("Updated value successfully: "+Thread.currentThread().getName());
            }else{
                System.out.println("Rollback of work: "+Thread.currentThread().getName());
                a = 10;
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void consume(){
        long stamp = lock.tryWriteLock();
        System.out.println("Write lock acquired by: "+Thread.currentThread().getName());

        try {
            System.out.println("Performing work: "+Thread.currentThread().getName());
            a = 9;
        }finally {
            lock.unlockWrite(stamp);
            System.out.println("Write Lock Released by: "+Thread.currentThread().getName());
        }
    }
}
