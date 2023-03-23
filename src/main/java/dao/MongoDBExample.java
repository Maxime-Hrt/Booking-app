package dao;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoDBExample {
    public static void main(String[] args) {
        // Connexion à la base de données
        MongoClient mongoClient = MongoClients.create("mongodb+srv://Maxime:lOQWdn8hDNv94JFz@ece-booking.h35vdkg.mongodb.net/ECE-BOOKING");
        MongoDatabase database = mongoClient.getDatabase("ECE-BOOKING");
        MongoCollection<Document> collection = database.getCollection("Accomodations");

        // Récupération du document
        Document document = collection.find().first();

        // Affichage des informations de contact
        assert document != null;
        Document contact = (Document) document.get("contact");
        System.out.println("Phone: " + contact.getString("phone"));
        System.out.println("Email: " + contact.getString("email"));
        System.out.println("Website: " + contact.getString("website"));

        // Fermeture de la connexion
        mongoClient.close();
    }
}
