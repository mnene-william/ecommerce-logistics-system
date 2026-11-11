


public class Main {
    public static void main(String[] args) {

        InventoryManager inventory = new InventoryManager();

        CustomerOrder order = new CustomerOrder("ORD001", "William", "A", 20);

        System.out.println("Initial stock:");

        inventory.displayStock();

        System.out.println("\nReserving stock...");

        boolean reserved = inventory.reserveStock(order);

        System.out.println("Reservation successful: " + reserved);

        inventory.displayStock();

        System.out.println("\nReleasing stock...");

        boolean released = inventory.releaseStock(order);

        System.out.println("Stock released: " + released);

        inventory.displayStock();
    }
}