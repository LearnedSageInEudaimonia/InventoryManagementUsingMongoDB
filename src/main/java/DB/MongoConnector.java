package DB;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoConnector {
    private static final String URL = "mongodb://localhost:27017";
    private static final String DB_Name = "inventoryDB";

    public static MongoDatabase database;

    public static MongoDatabase getDatabase(){
        if(database == null){
            MongoClient mongoClient = MongoClients.create(URL);
            database = mongoClient.getDatabase(DB_Name);
        }
        return database;
    }
}
