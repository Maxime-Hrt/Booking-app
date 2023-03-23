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

    public UsersDao(MongoClient mongoClient, String databaseName, String collectionName) {
        MongoDatabase database = mongoClient.getDatabase(databaseName);
        this.usersCollection = database.getCollection(collectionName);
    }

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

    @Override
    public void insertUser(Document user) {
        this.usersCollection.insertOne(user);
    }

    @Override
    public void updateUser(String id, Document user) {
        this.usersCollection.replaceOne(new Document("_id", id), user);
    }

    @Override
    public void deleteUser(String id) {
        this.usersCollection.deleteOne(new Document("_id", id));
    }
}
