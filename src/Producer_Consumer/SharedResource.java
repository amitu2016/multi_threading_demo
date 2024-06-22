package Producer_Consumer;

import java.util.LinkedList;
import java.util.Queue;

public class SharedResource {
    private Queue<Integer> sharedBuffer;
    private int bufferSize;

    public SharedResource(int bufferSize){
        this.bufferSize = bufferSize;
        sharedBuffer = new LinkedList<>();
    }

    public synchronized void produce(int item) throws InterruptedException {
        while (sharedBuffer.size() == bufferSize){
            System.out.println("Buffer is full! Producer is waiting for consumer to consume");
            wait();
        }
        sharedBuffer.add(item);
        System.out.println("Produced item "+item);
        notify();
    }

    public synchronized int consume() throws InterruptedException {
       while (sharedBuffer.isEmpty()){
           System.out.println("Buffer is empty. Waiting for producer to produce item!");
           wait();
       }


       int item = sharedBuffer.poll();

       System.out.println("Consumed item "+item);

       notify();

       return item;
    }
}
