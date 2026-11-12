
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;



public class Main {
   
   public static void main(String[] args){ 
    InventoryManager inventory = new InventoryManager();

    BlockingQueue<CustomerOrder> warehouseQueue = new LinkedBlockingQueue<>();

    BlockingQueue<CustomerOrder> deliveryQueue = new LinkedBlockingQueue<>();

    PaymentProcessor paymentProcessor = new PaymentProcessor(inventory, warehouseQueue);

    WarehouseWorker warehouseWorker = new WarehouseWorker(warehouseQueue, deliveryQueue);

    DeliveryManager deliveryManager = new DeliveryManager(deliveryQueue);

    NotificationService notificationService = new NotificationService();

    List<CustomerOrder> orders = new ArrayList<>();

    List<CustomerOrder> submittedOrders = new ArrayList<>();

    int rejectedStock = 0;

    System.out.println("===THE ZODIACS LOGISTICS SYSTEM IS STARTING===");

    inventory.displayStock();

    deliveryManager.startDelivering();warehouseWorker.startWorkers();

    for(int i = 1;i<=1000; i++){

    String productId = (i % 2 == 0) ? "A" : "B";


    CustomerOrder order = new CustomerOrder(

        "ORDER-" + i,

        "Customer-" + i,

        productId, 1
    );

    orders.add(order);

    boolean reserved = inventory.reserveStock(order);

    if(reserved){

        submittedOrders.add(order);

        paymentProcessor.submitPayment(order);
    }

    else {

        order.setStatus(CustomerOrder.OrderStatus.FAILED);
        rejectedStock++;
    }
}
   


    paymentProcessor.shutdown();
    warehouseWorker.shutdown();
    deliveryManager.shutdown();

    int paymentFailures = 0;

    for(CustomerOrder order: submittedOrders){

        if(order.getStatus() == CustomerOrder.OrderStatus.FAILED){

            paymentFailures++;
        }
    }

    deliveryManager.displayVehicleUtilization();

    int delivered = 0;
    


    for(CustomerOrder order : orders){

        if (order.getStatus() == CustomerOrder.OrderStatus.DELIVERED){

            delivered++;

            notificationService.sendNotification(order, "Dear Zodiacs customer, your order has been delivered successfully.");
        }

        else if (order.getStatus() == CustomerOrder.OrderStatus.FAILED){

            
        }
    }

        System.out.println("\n===THE ZODIACS FINAL SYSTEM REPORT===");

        System.out.println("Total number of orders received: " + orders.size());

        System.out.println("Number of orders rejected due to insufficient stock: " + rejectedStock);

        System.out.println("Payment failures: " + paymentFailures);

        System.out.println("Number of Successfully delivered orders: " + delivered);

        System.out.println("Total unsuccessful orders: " + (rejectedStock + paymentFailures));

        inventory.displayStock();


    


}
}
