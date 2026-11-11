

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

            
        })
    }
}