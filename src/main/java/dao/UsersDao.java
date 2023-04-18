/**
 * Data Access Object for Users collection in MongoDB.
 */

package dao;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
public class UsersDao implements IUsersDao{
    private final MongoCollection<Document> usersCollection;

    /**
     * Constructor for UsersDao class.
     *
     * @param mongoClient   the MongoClient instance used to connect to MongoDB.
     * @param databaseName  the name of the database containing the Users collection.
     * @param collectionName    the name of the Users collection.
     */
    public UsersDao(MongoClient mongoClient, String databaseName, String collectionName) {
        MongoDatabase database = mongoClient.getDatabase(databaseName);
        this.usersCollection = database.getCollection(collectionName);
    }

    /**
     * Retrieves all users in the Users collection.
     *
     * @return  a list of all users in the collection.
     */
    @Override
    public List<Document> getAllUsers() {
        List<Document> users = new ArrayList<>();

        try (MongoCursor<Document> cursor = this.usersCollection.find().iterator()) {
            while (cursor.hasNext()) {
                Document document = cursor.next();
                users.add(document);
            }
        }
        return users;
    }

    /**
     * Inserts a new user document into the Users collection.
     *
     * @param user  the user document to be inserted.
     */
    @Override
    public void insertUser(Document user) {
        this.usersCollection.insertOne(user);
    }

    /**
     * Updates a user document in the Users collection.
     *
     * @param id    the ID of the user to be updated.
     * @param user  the updated user document.
     */
    @Override
    public void updateUser(Object id, Document user) {
        this.usersCollection.replaceOne(new Document("_id", id), user);
    }

    /**
     * Updates the email field of a user document in the Users collection.
     *
     * @param email the email of the user to be updated.
     * @param user  the updated user document.
     */
    public void updateUserEmail(String email, Document user) {
        this.usersCollection.replaceOne(new Document("email", email), user);
    }

    /**
     * Deletes a user document from the Users collection.
     *
     * @param id    the ID of the user to be deleted.
     */
    @Override
    public void deleteUser(Object id) {
        this.usersCollection.deleteOne(new Document("_id", id));
    }
}