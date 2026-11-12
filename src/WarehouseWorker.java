

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import java.util.concurrent.TimeUnit;

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

                        Thread.sleep(1000);

                        order.setStatus(CustomerOrder.OrderStatus.READY_TO_BE_DELIVERED);

                        deliveryQueue.put(order);



                        System.out.println("The order is ready to be delivered: " + order.getOrderId());
                        
                    }

                    catch(InterruptedException e){

                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            });
        }
    }



    public void shutdown(){

        try{

            for (int i = 0; i < 5; i++){

                warehouseQueue.put(
                    new CustomerOrder("STOP-" + i, "SYSTEM", "STOP", 1)
                );
            }

            warehousePool.shutdown();


            if (!warehousePool.awaitTermination(2, TimeUnit.MINUTES)){

                warehousePool.shutdownNow();
            }
        }

        catch(InterruptedException e){

            Thread.currentThread().interrupt();
            warehousePool.shutdownNow();
        }
    }


}
