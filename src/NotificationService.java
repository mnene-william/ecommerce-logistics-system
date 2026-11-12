

public class NotificationService{


    public void sendNotification(CustomerOrder order, String message){



        System.out.println(


            "\nNotification" + "\nThe Customer: " + order.getCustomerName() + "\n The order made: " + order.getOrderId() + "\nMessage: " + message
        );


    }
}