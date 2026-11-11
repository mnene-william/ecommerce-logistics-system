

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent. Executors;

public class PaymentProcessor {

    private final ExecutorService paymentPool = Executors.newFixedThreadPool(3);

    private final InventoryManager inventory;

    private final BlockingQueue<CustomerOrder> warehouseQueue;


    public PaymentProcessor(InventoryManager inventory, BlockingQueue<CustomerOrder> warehouseQueue){

        this.inventory = inventory;
        this.warehouseQueue = warehouseQueue;





    }

    public void submitPayment(CustomerOrder order){


        paymentPool.submit(() -> {

            try{

                System.out.println("Currently processing payment for " + order.getOrderId() + "using " + Thread.currentThread().getName());


                Thread.sleep(600);

                boolean paymentSuccessful = Math.random() < 0.9;


                if(paymentSuccessful){


                    order.setStatus(CustomerOrder.OrderStatus.PAID);



                    warehouseQueue.put(order);

                    System.out.println("The Payment made had been successful: " + order.getOrderId());
                } 
                
                else{

                    order.setStatus( CustomerOrder.OrderStatus.FAILED);

                    inventory.releaseStock(order);

                    System.out.println("The Payment made had failed: " + order.getOrderId());




            
                }


            }

            catch(InterruptedException e) {

                Thread.currentThread(). interrupt();


                order.setStatus(CustomerOrder.OrderStatus.FAILED);

                inventory.releaseStock(order);


            }
        });
    }


    public voide
}