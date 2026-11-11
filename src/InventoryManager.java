import java.util.HashMap;
import java.util.Map;


public class InventoryManager {

    private final Map<String, Integer> stock = new HashMap<>();


    private final Map<String, CustomerOrder> reservations = new HashMap<>();

    public InventoryManager() {

        stock.put("A", 100);
        stock.put("B", 50);
    }

    


}