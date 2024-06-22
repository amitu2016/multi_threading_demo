package Monitor_Lock;

public class ProduceTask implements  Runnable{

    SharedResource sharedResource;

    ProduceTask(SharedResource resource){
        this.sharedResource = resource;
    }

    @Override
    public void run() {
        System.out.println("Producer Thread: "+Thread.currentThread().getName());
        try {
            Thread.sleep(2000L);
        }catch (Exception e){

        }
        sharedResource.addItem();
    }
}
