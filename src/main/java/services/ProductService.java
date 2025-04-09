package services;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import DB.MongoConnector;
import models.Product;
import org.bson.Document;

import static com.mongodb.client.model.Filters.*;

public class ProductService {
    private final MongoCollection<Document> productCollection;

    public ProductService() {
        MongoDatabase db = MongoConnector.getDatabase();
        productCollection = db.getCollection("products");
    }

    public void addProduct(Product product) {
        Document doc = new Document("_id", product.id)
                .append("name", product.name)
                .append("category", product.category)
                .append("price", product.price)
                .append("quantity", product.quantity)
                .append("threshold", product.threshold);
        productCollection.insertOne(doc);
        System.out.println("Product added successfully.");
    }

    public void updateQuantity(String productId, int newQuantity) {
        productCollection.updateOne(eq("_id", productId),
                new Document("$set", new Document("quantity", newQuantity)));
        System.out.println("Product quantity updated.");
    }

    public void deleteProduct(String productId) {
        productCollection.deleteOne(eq("_id", productId));
        System.out.println("Product deleted.");
    }

    public void viewAllProducts() {
        for (Document doc : productCollection.find()) {
            System.out.println(doc.toJson());
        }
    }

    public void searchProduct(String keyword) {
        System.out.println("\n--- Search Results ---");
        for (Document doc : productCollection.find(or(
                regex("name", ".*" + keyword + ".*", "i"),
                regex("category", ".*" + keyword + ".*", "i")
        ))) {
            System.out.println(doc.toJson());
        }
    }
    public void showLowStockAlerts() {
        System.out.println("\n--- Low Stock Alerts ---");
        for (Document doc : productCollection.find()) {
            int qty = doc.getInteger("quantity");
            int threshold = doc.getInteger("threshold");
            if (qty < threshold) {
                System.out.println("LOW STOCK: " + doc.getString("name") + " | Qty: " + qty + " | Threshold: " + threshold);
            }
        }
    }


}
