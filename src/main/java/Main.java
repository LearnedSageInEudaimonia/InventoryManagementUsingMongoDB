import models.Product;
import services.ProductService;
import services.SalesService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ProductService productService = new ProductService();
        SalesService salesService = new SalesService();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Inventory Management CLI ---");
            System.out.println("1. Add Product");
            System.out.println("2. Update Quantity");
            System.out.println("3. Delete Product");
            System.out.println("4. View All Products");
            System.out.println("5. Record Sale");
            System.out.println("6. Show Low Stock Alerts");
            System.out.println("7. Search Product");
            System.out.println("8. View Sales by Date Range");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Product ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Category: ");
                    String category = scanner.nextLine();
                    System.out.print("Price: ");
                    double price = scanner.nextDouble();
                    System.out.print("Quantity: ");
                    int qty = scanner.nextInt();
                    System.out.print("Threshold: ");
                    int threshold = scanner.nextInt();
                    scanner.nextLine();

                    productService.addProduct(new Product(id, name, category, price, qty, threshold));
                }
                case 2 -> {
                    System.out.print("Product ID: ");
                    String pid = scanner.nextLine();
                    System.out.print("New Quantity: ");
                    int newQty = scanner.nextInt();
                    scanner.nextLine();
                    productService.updateQuantity(pid, newQty);
                }
                case 3 -> {
                    System.out.print("Product ID to delete: ");
                    String pid = scanner.nextLine();
                    productService.deleteProduct(pid);
                }
                case 4 -> productService.viewAllProducts();
                case 5 -> {
                    System.out.print("Product ID: ");
                    String pid = scanner.nextLine();
                    System.out.print("Quantity Sold: ");
                    int qty = scanner.nextInt();
                    scanner.nextLine();
                    salesService.recordSale(pid, qty);
                }
                case 6 -> productService.showLowStockAlerts();
                case 7 -> {
                    System.out.print("Enter product name or category keyword: ");
                    String keyword = scanner.nextLine();
                    productService.searchProduct(keyword);
                }
                case 8 -> {
                    System.out.print("From Date (yyyy-MM-dd): ");
                    String from = scanner.nextLine();
                    System.out.print("To Date (yyyy-MM-dd): ");
                    String to = scanner.nextLine();
                    salesService.viewSalesByDateRange(from, to);
                }

                case 0 -> {
                    System.out.println("Exiting... Bye!");
                    System.exit(0);
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }
}
