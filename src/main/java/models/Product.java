package models;

public class Product {
    public String id;
    public String name;
    public String category;
    public double price;
    public int quantity;
    public int threshold;

    public Product(String id, String name, String category, double price, int quantity, int threshold) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.threshold = threshold;
    }
}
