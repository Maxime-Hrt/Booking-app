package dao;

import com.mongodb.client.MongoClients;
import org.bson.Document;
import java.util.ArrayList;

public class MongoDBExample {
    public static void main(String[] args) {
        UsersDao tabOfUsers = new UsersDao(MongoClients.create("mongodb+srv://Maxime:lOQWdn8hDNv94JFz@ece-booking.h35vdkg.mongodb.net/ECE-BOOKING"), "ECE-BOOKING", "Users");

        //Example of how to insert a user
        /*
        tabOfUsers.insertUser(new Document()
                .append("admin", false)
                .append("country", "Indonesia")
                .append("username", "Supriyanto")
                .append("password", "Supriyanto")
                .append("createdAt", Date.from(LocalDateTime.now().toInstant(java.time.ZoneOffset.UTC)))
                .append("reviews", Arrays.asList(
                        new Document("comment", "Great place to stay")
                                .append("rating", 4)
                                .append("destination", "Bintaro Parkview - Jakarta"),
                        new Document("rating", 2)
                                .append("destination", "Kebayoran Icon - Jakarta")
                                .append("comment", "Wifi was broken, the gas stove wasn't working")
                ))
                .append("email", "supriyanto.aflah@yahoo.com")
        );*/

        //How to get all users
        ArrayList<Document> users = new ArrayList<>(tabOfUsers.getAllUsers());

        // If you want some specific users
        for (Document user : users) {
            if (user.containsKey("reviews")) {
                ArrayList<Document> reviews = (ArrayList<Document>) user.get("reviews");
                System.out.println(user.get("username") + ": ");
                for (Document review : reviews) {
                    if (review.containsKey("comment")) {
                        System.out.println("Commented: " + review.getString("comment"));
                    }
                    if (review.containsKey("rating")) {
                        System.out.println("Gived a " + review.getInteger("rating") + "/5 rating");
                    }
                    if (review.containsKey("destination")) {
                        System.out.println("Destination: " + review.getString("destination"));
                    }
                    System.out.println();
                }
            }
        }



        //How to update a user
        for (Document user : users) {
            if (user.getString("username").equals("Supriyanto")) {
                user.put("username", "Supriyanto Aflah");
                System.out.println(user.getObjectId("_id"));
                tabOfUsers.updateUser(user.getObjectId("_id"), user);
            }
        }

        //How to delete a user
        /*
        for (Document user : users) {
            if (user.getString("username").equals("Supriyanto Aflah")) {
                tabOfUsers.deleteUser(user.getObjectId("_id"));
            }
        }*/
    }
}

/*tabOfAccommodations.updateAccommodation("5f9f1b9b9c9c1b0b8c1c1c1c", new Document("name", "Piroozy"));
        tabOfAccommodations.deleteAccommodation("5f9f1b9b9c9c1b0b8c1c1c1c");

        // Connexion à la base de données
        MongoClient mongoClient = MongoClients.create("mongodb+srv://Maxime:lOQWdn8hDNv94JFz@ece-booking.h35vdkg.mongodb.net/ECE-BOOKING");
        MongoDatabase database = mongoClient.getDatabase("ECE-BOOKING");
        MongoCollection<Document> collection = database.getCollection("Accomodations");

        // Récupération du document
        Document document = collection.find(eq("name", "Piroozy"))
                .first();

        // Affichage des informations de contact
        assert document != null;
        Document contact = (Document) document.get("contact");
        System.out.println("Phone: " + contact.getString("phone"));
        System.out.println("Email: " + contact.getString("email"));
        System.out.println("Website: " + contact.getString("website"));*/

// Close the client
// mongoClient.close();