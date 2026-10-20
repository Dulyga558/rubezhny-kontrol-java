// 1. Product класы (public сөзінсіз)
class Product {
    private static int totalProductsCount = 0;

    private String name;
    private double price;
    private int stockQuantity;

    public Product(String name, double price, int stockQuantity) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        totalProductsCount++;
    }

    public boolean reduceStock(int count) {
        if (count > 0 && count <= stockQuantity) {
            this.stockQuantity -= count;
            return true;
        }
        return false;
    }

    public void restock(int count) {
        if (count > 0) {
            this.stockQuantity += count;
        }
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStockQuantity() { return stockQuantity; }
    public static int getTotalProductsCount() { return totalProductsCount; }
}

// 2. Order класы (public сөзінсіз)
class Order {
    private static double totalStoreRevenue = 0.0;

    private Product product;
    private int quantity;
    private boolean isPaid;

    public Order(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.isPaid = false;
    }

    public double calculateTotal() {
        return product.getPrice() * quantity;
    }

    public boolean processPayment() {
        if (!isPaid && product.reduceStock(quantity)) {
            this.isPaid = true;
            totalStoreRevenue += calculateTotal();
            return true;
        }
        return false;
    }

    public void printOrderSummary() {
        System.out.println("Тауар: " + product.getName() +
                " | Саны: " + quantity +
                " | Жалпы сомасы: " + calculateTotal() + " тг" +
                " | Төленді ме: " + isPaid);
    }

    public static double getTotalStoreRevenue() {
        return totalStoreRevenue;
    }
}

// 3. Негізгі public класс (Файл атымен бірдей)
public class Practice4 {
    public static void main(String[] args) {
        Product laptop = new Product("Ноутбук", 350000.0, 5);
        Product phone = new Product("Смартфон", 180000.0, 10);

        System.out.println("Құрылған тауарлар саны (static): " + Product.getTotalProductsCount());

        Order myOrder = new Order(laptop, 2);
        myOrder.printOrderSummary();

        if (myOrder.processPayment()) {
            System.out.println(">>> Төлем сәтті өтті!");
        } else {
            System.out.println(">>> Төлем қабылданбады!");
        }

        myOrder.printOrderSummary();
        System.out.println("Ноутбук қалдығы: " + laptop.getStockQuantity());
        System.out.println("Дүкеннің жалпы табысы (static): " + Order.getTotalStoreRevenue() + " тг");
    }
}
