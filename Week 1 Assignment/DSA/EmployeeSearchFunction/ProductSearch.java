import java.util.Arrays;
import java.util.Comparator;

public class ProductSearch {
    static class Product {
        int productId;
        String productName;
        String category;

        Product(int productId, String productName, String category) {
            this.productId = productId;
            this.productName = productName;
            this.category = category;
        }
    }

    static int linearSearch(Product[] catalog, String nameToFind) {
        for (int i = 0; i < catalog.length; i++) {
            if (catalog[i].productName.equals(nameToFind)) {
                return i;
            }
        }
        return -1;
    }

    static int binarySearch(Product[] sortedCatalog, String nameToFind) {
        int low = 0;
        int high = sortedCatalog.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int comparison = sortedCatalog[mid].productName.compareTo(nameToFind);
            if (comparison == 0) {
                return mid;
            } else if (comparison < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Product[] catalog = {
            new Product(1, "Bluetooth Speaker", "Audio"),
            new Product(2, "Gaming Chair", "Furniture"),
            new Product(3, "Espresso Machine", "Kitchen"),
            new Product(4, "Running Shoes", "Footwear"),
            new Product(5, "Yoga Mat", "Fitness")
        };

        int foundAt = linearSearch(catalog, "Espresso Machine");
        System.out.println("Linear search found at index: " + foundAt);

        Product[] sortedCatalog = Arrays.copyOf(catalog, catalog.length);
        Arrays.sort(sortedCatalog, Comparator.comparing(p -> p.productName));

        int foundAtSorted = binarySearch(sortedCatalog, "Yoga Mat");
        System.out.println("Binary search found at index: " + foundAtSorted);
    }
}
