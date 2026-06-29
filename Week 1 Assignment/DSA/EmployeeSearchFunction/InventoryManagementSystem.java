import java.util.HashMap;

public class InventoryManagementSystem {
    static class Product {
        int productId;
        String productName;
        int quantity;
        double price;

        Product(int productId, String productName, int quantity, double price) {
            this.productId = productId;
            this.productName = productName;
            this.quantity = quantity;
            this.price = price;
        }
    }

    static HashMap<Integer, Product> warehouseStock = new HashMap<>();

    static void addProduct(Product p) {
        warehouseStock.put(p.productId, p);
    }

    static void updateProduct(int productId, int newQuantity, double newPrice) {
        Product existing = warehouseStock.get(productId);
        if (existing != null) {
            existing.quantity = newQuantity;
            existing.price = newPrice;
        }
    }

    static void deleteProduct(int productId) {
        warehouseStock.remove(productId);
    }

    static void printInventory() {
        for (Product p : warehouseStock.values()) {
            System.out.println(p.productId + " " + p.productName + " " + p.quantity + " " + p.price);
        }
    }

    public static void main(String[] args) {
        addProduct(new Product(101, "Wireless Mouse", 50, 499.0));
        addProduct(new Product(102, "Mechanical Keyboard", 30, 1899.0));
        addProduct(new Product(103, "USB-C Hub", 75, 999.0));
        printInventory();
        updateProduct(102, 25, 1799.0);
        deleteProduct(101);
        System.out.println("After update and delete:");
        printInventory();
    }
}
