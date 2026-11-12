

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WarehouseWorker{

    private final ExecutorService warehousePool = Executors.newFixedThreadPool(5);

    private final BlockingQueue<CustomerOrder> warehouseQueue;

    private final BlockingQueue<CustomerOrder> deliveryQueue;


    public WarehouseWorker( 

        BlockingQueue<CustomerOrder> warehouseQueue,
        BlockingQueue<CustomerOrder> deliveryQueue) {

            this.warehouseQueue = warehouseQueue;
            this.deliveryQueue = deliveryQueue;




    }

    public void startWorkers(){

        for (int i = 0; i < 5; i++){

            warehousePool.submit(() -> {

                while(true) {


                    try{


                        CustomerOrder order = warehouseQueue.take();

                        if(order.getOrderId().startsWith("STOP-")){


                        break;

                        }

                        order.setStatus(
                            CustomerOrder.OrderStatus.PROCESSING);


                        System.out.println("This Warehouse worker" + Thread.currentThread().getName() + "is preparing" + order.getOrderId());
                        )
                    }
                }
            })
        }
    }


}