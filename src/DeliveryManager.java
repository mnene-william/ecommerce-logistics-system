

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;


public class DeliveryManager{

    private final BlockingQueue<CustomerOrder> deliveryQueue;

    private final ExecutorService deliveryPool = Executors.newFixedThreadPool(4);

    private final Semaphore vehicleSemaphore = new Semaphore(4);


    public DeliveryManager(BlockingQueue<CustomerOrder> deliveryQueue){

        this.deliveryQueue = deliveryQueue;
    }
}