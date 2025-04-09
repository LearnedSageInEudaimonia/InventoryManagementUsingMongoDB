package services;

import DB.MongoConnector;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import javax.print.Doc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import static com.mongodb.client.model.Filters.eq;

public class SalesService {
    private final MongoCollection<Document> saleCollection;
    private final MongoCollection<Document> productCollection;

    public SalesService() {
        MongoDatabase db = MongoConnector.getDatabase();
        saleCollection = db.getCollection("sales");
        productCollection = db.getCollection("products");
    }
    public void recordSale(String productId, int quantitySold) {
        Document product = productCollection.find(eq("_id", productId)).first();

        if (product == null || product.getInteger("quantity") < quantitySold) {
            System.out.println("Insufficient stock or product not found.");
            return;
        }

    int newQty = product.getInteger("quantity") - quantitySold;
    productCollection.updateOne(eq("_id",productId),
                    new Document("$set", new Document("quantity", newQty)));

        Document saleDoc = new Document("_id", UUID.randomUUID().toString())
                .append("productId", productId)
                .append("quantitySold", quantitySold)
                .append("timestamp", LocalDateTime.now().toString());

        saleCollection.insertOne(saleDoc);
        System.out.println("Sale recorded successfully.");
    }

    public void viewSalesByDateRange(String from, String to) {
        System.out.println("\n--- Sales From " + from + " to " + to + " ---");
        LocalDate start = LocalDate.parse(from);
        LocalDate end = LocalDate.parse(to);

        for (Document doc : saleCollection.find()) {
            LocalDate saleDate = LocalDate.parse(doc.getString("timestamp").substring(0, 10));
            if ((saleDate.isEqual(start) || saleDate.isAfter(start)) &&
                    (saleDate.isEqual(end) || saleDate.isBefore(end))) {
                System.out.println(doc.toJson());
            }
        }
    }

}
