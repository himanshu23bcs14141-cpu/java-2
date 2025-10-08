import java.util.*;
import java.util.stream.*;

class Product {
    String name;
    double price;
    String category;

    Product(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public String toString() {
        return name + " | " + price;
    }
}

public class ProductStream {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
            new Product("Mouse", 799, "Electronics"),
            new Product("Keyboard", 2499, "Electronics"),
            new Product("USB-C Cable", 299, "Accessories"),
            new Product("Laptop Stand", 999, "Accessories"),
            new Product("Desk", 8999, "Furniture")
        );

        Map<String, List<Product>> grouped = products.stream()
            .collect(Collectors.groupingBy(p -> p.category));
        System.out.println("Grouped by category: " + grouped);

        Map<String, Optional<Product>> maxPricePerCategory = products.stream()
            .collect(Collectors.groupingBy(
                p -> p.category,
                Collectors.maxBy(Comparator.comparingDouble(p -> p.price))
            ));
        System.out.println("Most expensive per category:");
        maxPricePerCategory.forEach((cat, prod) -> System.out.println(cat + ": " + prod.get()));

        double avgPrice = products.stream()
            .collect(Collectors.averagingDouble(p -> p.price));
        System.out.println("Average price: " + avgPrice);
    }
}