

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

NotificationService notificationService = new Notififcation();




