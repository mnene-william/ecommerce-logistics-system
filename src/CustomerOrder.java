

public class CustomerOrder {

    private final String orderId;
    private final String customerName;
    private final String productId;
    private final int quantity;


    private volatile OrderStatus status;


    public enum OrderStatus {

        PENDING,
        RESERVED,
        PAID,
        PROCESSING,
        READY_TO_BE_DELIVERED,
        DISPATCHED,
        DELIVERED,
        CANCELLED,
        FAILED,


    }


    public CustomerOrder(String orderId, String customerName, String productId, int quantity){

        if (quantity <= 0){

            throw new IllegalArgumentException("The quantity you enter must always be positive.");



        }


        this.orderId = orderId;
        this.customerName = customerName;
        this.productId = productId;
        this.quantity = quantity;
        this.status = OrderStatus.PENDING;
    }


    
}