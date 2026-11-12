

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.List;


public class DeliveryManager{

    private final BlockingQueue<CustomerOrder> deliveryQueue;

    private final List<DeliveryVehicle> vehicles = new ArrayList<>();

    private final ExecutorService deliveryPool = Executors.newFixedThreadPool(4);

    private final Semaphore vehicleSemaphore = new Semaphore(4);

    private final InventoryManager inventory;


    public DeliveryManager(BlockingQueue<CustomerOrder> deliveryQueue, InventoryManager inventory){

        this.deliveryQueue = deliveryQueue;

        this.inventory = inventory;
    }


    public void startDelivering(){


        for (int i = 1; i <= 4; i++){


            final DeliveryVehicle vehicle = new DeliveryVehicle("Vehicle-" + i);

            vehicles.add(vehicle);

            deliveryPool.submit(() -> {

                while(true) {


                    try{

                        CustomerOrder order = deliveryQueue.take();

                        if(order.getOrderId().startsWith("STOP-")){


                            break;
                        }

                        vehicleSemaphore.acquire();

                        try{

                            vehicle.deliver(order);
                        } finally{

                            vehicleSemaphore.release();
                        } 
                        
                    }  catch(InterruptedException e){

                            Thread.currentThread().interrupt();
                            break;


                        }
                    
                    }
                });
                
            }



    }
            

            public void shutdown(){

                try{

                    for(int i = 0; i < 4; i++){

                        deliveryQueue.put(new CustomerOrder("STOP-" + i, "SYSTEM", "STOP", 1));
                    }

                    deliveryPool.shutdown();

                    if(!deliveryPool.awaitTermination(2, TimeUnit.MINUTES)){

                        deliveryPool.shutdownNow();
                    }
                } 

                catch (InterruptedException e){

                    Thread.currentThread().interrupt();

                    deliveryPool.shutdown();
                }
        
    }


    public void displayVehicleUtilization(){

        System.out.println("\nHow vehicles were utilized: ");


        for (DeliveryVehicle vehicle: vehicles){
            
        

        System.out.println(vehicle.getVehicleId() + " completed " + vehicle.getDeliveriesCompleted() + " deliveries.");
        }
    }
}