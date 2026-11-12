

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
}









