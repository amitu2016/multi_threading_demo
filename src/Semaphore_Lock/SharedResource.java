package Semaphore_Lock;

import java.util.concurrent.Semaphore;

public class SharedResource {
    boolean isAvailable = false;

    Semaphore lock = new Semaphore(1);

    public void produce(){

        try {
            lock.acquire();
            System.out.println("Lock acquired by: "+Thread.currentThread().getName());
            isAvailable = true;
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            lock.release();
            System.out.println("Lock is released by: "+Thread.currentThread().getName());
        }
    }
}
