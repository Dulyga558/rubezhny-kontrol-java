// ==========================================
// CONSPECT 5: MINI-PRACTICE (Conspect5.java)
// ==========================================

enum ProductStatus {
    ACTIVE,
    DISCONTINUED
}

class Product5 {
    private final String code;
    private String name;
    private double price;
    private ProductStatus status;

    public Product5(String code, String name, double price) {
        if (code == null || code.isBlank()) {
            throw new IllegalArgumentException("Product code cannot be empty!");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Product name cannot be empty!");
        }
        if (!Double.isFinite(price) || price <= 0) {
            throw new IllegalArgumentException("Price must be a finite positive number!");
        }

        this.code = code.strip();
        this.name = name.strip();
        this.price = price;
        this.status = ProductStatus.ACTIVE;
    }

    public String getCode() { return code; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public ProductStatus getStatus() { return status; }

    public void discontinue() {
        if (this.status == ProductStatus.DISCONTINUED) {
            throw new IllegalStateException("Product is already discontinued!");
        }
        this.status = ProductStatus.DISCONTINUED;
    }

    public void changePrice(double newPrice) {
        if (this.status != ProductStatus.ACTIVE) {
            throw new IllegalStateException("Cannot change price of a discontinued product!");
        }
        if (!Double.isFinite(newPrice) || newPrice <= 0) {
            throw new IllegalArgumentException("New price must be a positive number!");
        }
        this.price = newPrice;
    }

    @Override
    public String toString() {
        return "Product5{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", status=" + status +
                '}';
    }
}

public class Conspect5 {
    public static void main(String[] args) {
        System.out.println("=== 1. Creating a valid product ===");
        Product5 laptop = new Product5("P1001", "Laptop", 450000.0);
        System.out.println("Initial product: " + laptop);

        System.out.println("\n=== 2. Changing price successfully ===");
        laptop.changePrice(420000.0);
        System.out.println("New price: " + laptop.getPrice() + " KZT");

        System.out.println("\n=== 3. Discontinuing product ===");
        laptop.discontinue();
        System.out.println("New status: " + laptop.getStatus());

        System.out.println("\n=== 4. Error scenario: Changing price after discontinue ===");
        try {
            laptop.changePrice(400000.0);
        } catch (IllegalStateException e) {
            System.out.println("Caught state exception: " + e.getMessage());
        }

        System.out.println("\n=== 5. Error scenario: Passing negative price ===");
        try {
            Product5 phone = new Product5("P1002", "Smartphone", -50000.0);
        } catch (IllegalArgumentException e) {
            System.out.println("Caught argument exception: " + e.getMessage());
        }
    }
}