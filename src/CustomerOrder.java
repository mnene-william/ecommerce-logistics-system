

public class CustomerOrder {

    private final String orderId;
    private final String customerName;
    private final String productId;
    private final int quantity;


    private volatile OrderStatus status;


    
}