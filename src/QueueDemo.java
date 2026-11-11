import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class QueueDemo {

    public static void main(String[] args){

        BlockingQueue<String> orderQueue = new LinkedBlockingQueue<>();


        orderQueue.add("Order 1");
        orderQueue.add("Order 2");
        orderQueue.add("Order 3");

        while (!orderQueue.isEmpty()){

            try{
                String order = orderQueue.take();

                System.out.println("Warehouse processing: " + order);


            }catch (InterruptedException e){

                Thread.currentThread().interrupt();
                break;
            }
        }

     }
}
    

