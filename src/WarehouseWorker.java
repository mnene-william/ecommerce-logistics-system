

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WarehouseWorker{

    private final ExecutorService warehousePool = Executors.newFixedThreadPool(5);

    private final BlockingQueue<CustomerOrder> warehouseQueue;

    private final BlockingQueue<CustomerOrder> deliveryQueue;

    
}