import java.util.HashMap;
import java.util.Map;


public class InventoryManager {

    private final Map<String, Integer> stock = new HashMap<>();


    private final Map<String, CustomerOrder> reservations = new HashMap<>();

    public InventoryManager() {

        stock.put("A", 100);
        stock.put("B", 50);
    }

    public synchronized boolean reserveStock(CustomerOrder order){

        String orderId = order.getOrderId();
        String productId = order.getProductId();
        int quantity = order.getQuantity();

        if (reservations.containsKey(orderId)){

            return false;


        }


        int available = stock.get(productId);


        if(available < quantity){

            return false;
        }


        reservations.put(orderId, order);

        order.setStatus(CustomerOrder.OrderStatus.RESERVED);

        System.out.println("Stock reserved for " + orderId);

        return true;
    }


    public synchronized boolean releaseStock(CustomerOrder order){

        CustomerOrder reservation = reservations.remove(order.getOrderId());


        if(reservation == null){

            return false
        }

        String productId = reservation.getProductId();

        int quantity = reservation.getQuantity()


        stock.put( productId, stock.get(productId) + quantity);

        return true;
    }

    

    public synchronized boolean confirmDispacth(CustomerOrder order){

        return reservations.remove(order.getOrderId()) != null;
    }


    public


}