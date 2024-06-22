package Monitor_Lock;

public class SharedResource {

    boolean itemAvailable = false;

    public synchronized void addItem(){
        itemAvailable = true;
        System.out.println("Item added by "+Thread.currentThread().getName()+", and invoking all threads which are waiting.");
        notifyAll();
    }

    public synchronized void consumeItem(){
        System.out.println("consumeItem method invoked by "+Thread.currentThread().getName());

        if (!itemAvailable){
            try {
                System.out.println("Thread "+Thread.currentThread().getName()+" is waiting now.");
                wait();
            }catch (Exception e){

            }

            System.out.println("Item is consumed by "+Thread.currentThread().getName());
            itemAvailable = false;
        }
    }

}
