

public class DeliveryVehicle{


    private final String vehicleId;

  

    public DeliveryVehicle(String vehicleId){


        this.vehicleId = vehicleId;
    }



    public void deliver(CustomerOrder order)

        throws InterruptedException{

            order.setStatus(CustomerOrder.OrderStatus.DISPATCHED);

            System.out.println(vehicleId + " is currently delivering order " + order.getOrderId() + "to a customer");



            Thread.sleep(2000);

            order.setStatus(CustomerOrder.OrderStatus.DELIVERED);

            System.out.println("The Order " + order.getOrderId() + " has been successfully delivered by " + vehicleId);
        }
        


        public String getVehicleId() {

            return vehicleId;
        }



    
}