

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


InventoryManager inventory = new InventoryManager();

BlockingQueue<CustomerOrder> warehouseQueue = new LinkedBlockingQueue<>();

BlockingQueue<CustomerOrder> deliveryQueue = new LinkedListBlockingQueue<>();


PaymentProcessor paymentProcessor = new PaymentProcessor(inventory, warehouseQueue);

WarehouseWorker warehouseWorker = new WarehouseWorker(warehouseQueue, deliveryQueue);

DeliveryManager deliveryManager = new DeliveryManager(deliveryQueue);

NotificationService notificationService = new Notification();

List<CustomerOrder> orders = new ArrayList<>();

int rejectedStock = 0;


System.out.println("===THE ZODIACS LOGISTICS SYSTEM IS STARTING===");

inventory.displayStock();

deliveryManager.startDelivering();
warehouseWorker.startWorkers();


for (int i = 1; i <= 1000; i++){

    String productId = (i % 2 == 0) ? "A" : "B";


    CustomerOrder order = newCustomerOrder(

        "ORDER-" + i,

        "Customer-" + i,

        productId, 1
    );

    orders.add(order);

    boolean reserved = inventory.reserveStock(order);

    if(reserved){

        paymentProcessor.submitPayment(order);
    }

    else {

        order.setStatus(CustomerOrder.OrderStatus.FAILED);
        rejectedStock++;
    }

    paymentProcessor.shutdown();
    warehouseWorker.shutdown();
    deliveryManager.shutdown();

    int delivered = 0;
    int failed = 0;


    for(CustomerOrder order : orders){

        if (order.getStatus() == CustomerOrder.OrderStatus.DELIVERED){

            delivered++;

            notificationService.sendNotification(order, "Dear Zodiacs customer, your order has been delivered successfully.")
        }

        else if (order.getStatus() == CustomerOrder.OrderStatus.FAILED){

            failed++;
        }

        System.out.println("\===THE ZODIACS FINAL SYSTEM REPORT===");

        System.out.println("Total number of orders received: " + orders.size());

        System.out.println("Number of orders rejected due to insufficient stock: " + rejectedStock);

        System.out.println("Number of Successfully delivered orders: " + delivered);

        System.out.println("Number of failed orders: " + failed);

        inventory.displayStock();


    }


}









