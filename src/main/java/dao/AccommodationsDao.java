package dao;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class AccommodationsDao implements IAccommodationsDao {

    private final MongoCollection<Document> accommodationsCollection;

    public AccommodationsDao(MongoClient mongoClient, String databaseName, String collectionName) {
        MongoDatabase database = mongoClient.getDatabase(databaseName);
        this.accommodationsCollection = database.getCollection(collectionName);
    }

    @Override
    public List<Document> getAllAccommodations() {
        List<Document> accommodations = new ArrayList<>();

        try (MongoCursor<Document> cursor = this.accommodationsCollection.find().iterator()) {
            while (cursor.hasNext()) {
                Document document = cursor.next();
                accommodations.add(document);
            }
        }
        return accommodations;
    }

    @Override
    public void insertAccommodation(Document accommodation) {
        this.accommodationsCollection.insertOne(accommodation);
    }

    @Override
    public void updateAccommodation(Object id, Document accommodation) {
        this.accommodationsCollection.replaceOne(new Document("_id", id), accommodation);
    }

    @Override
    public void deleteAccommodation(Object id) {
        this.accommodationsCollection.deleteOne(new Document("_id", id));
    }
}

/* Cette classe dispose de quatre méthodes principales :

getAllAccommodations(): récupère tous les documents de la collection Accommodations et les retourne sous forme de liste de Document.
insertAccommodation(Document accommodation): insère un document accommodation dans la collection Accommodations.
updateAccommodation(String id, Document accommodation): met à jour le document avec l'ID id dans la collection Accommodations avec le document accommodation.
deleteAccommodation(String id): supprime le document avec l'ID id de la collection Accommodations.
 */
