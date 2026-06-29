public class OrderSorting {
    static class Order {
        int orderId;
        String customerName;
        double totalPrice;

        Order(int orderId, String customerName, double totalPrice) {
            this.orderId = orderId;
            this.customerName = customerName;
            this.totalPrice = totalPrice;
        }
    }

    static void bubbleSort(Order[] orders) {
        int n = orders.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (orders[j].totalPrice > orders[j + 1].totalPrice) {
                    Order temp = orders[j];
                    orders[j] = orders[j + 1];
                    orders[j + 1] = temp;
                }
            }
        }
    }

    static void quickSort(Order[] orders, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(orders, low, high);
            quickSort(orders, low, pivotIndex - 1);
            quickSort(orders, pivotIndex + 1, high);
        }
    }

    static int partition(Order[] orders, int low, int high) {
        double pivotValue = orders[high].totalPrice;
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (orders[j].totalPrice < pivotValue) {
                i++;
                Order temp = orders[i];
                orders[i] = orders[j];
                orders[j] = temp;
            }
        }
        Order temp = orders[i + 1];
        orders[i + 1] = orders[high];
        orders[high] = temp;
        return i + 1;
    }

    static void printOrders(Order[] orders) {
        for (Order o : orders) {
            System.out.println(o.orderId + " " + o.customerName + " " + o.totalPrice);
        }
    }

    public static void main(String[] args) {
        Order[] bubbleBatch = {
            new Order(1, "Rohan Mehta", 2500.0),
            new Order(2, "Sneha Kapoor", 800.0),
            new Order(3, "Aditya Verma", 4200.0),
            new Order(4, "Priya Nair", 1500.0)
        };
        bubbleSort(bubbleBatch);
        System.out.println("Sorted with bubble sort:");
        printOrders(bubbleBatch);

        Order[] quickBatch = {
            new Order(5, "Karan Joshi", 3000.0),
            new Order(6, "Anjali Rao", 600.0),
            new Order(7, "Vikram Singh", 5400.0),
            new Order(8, "Meera Iyer", 1200.0)
        };
        quickSort(quickBatch, 0, quickBatch.length - 1);
        System.out.println("Sorted with quick sort:");
        printOrders(quickBatch);
    }
}
