
public class CancellationTest {

    public static void main(String[] args) {

        InventoryManager inventory = new InventoryManager();

        CustomerOrder order =


                new CustomerOrder("TEST-001", "Test Customer", "A", 10);

        System.out.println("Initial stock: " + inventory.getAvailableStock("A"));

        inventory.reserveStock(order);


        System.out.println("Stock after reservation: " + inventory.getAvailableStock("A"));

        boolean cancelled = inventory.cancelOrder(order);


        System.out.println("Cancellation successful: " + cancelled);

        System.out.println("Stock after cancellation: " + inventory.getAvailableStock("A"));

        System.out.println("Order status: " + order.getStatus());
    }
}

