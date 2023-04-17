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

    /**
     * Constructs an AccommodationsDao object with a MongoDB client, database name and collection name.
     *
     * @param mongoClient the MongoDB client to use
     * @param databaseName the name of the database to use
     * @param collectionName the name of the collection to use
     */
    public AccommodationsDao(MongoClient mongoClient, String databaseName, String collectionName) {
        MongoDatabase database = mongoClient.getDatabase(databaseName);
        this.accommodationsCollection = database.getCollection(collectionName);
    }

    /**
     * Returns a list of all the documents in the Accommodations collection.
     *
     * @return a list of Document objects representing the accommodations in the collection
     */
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

    /**
     * Inserts an accommodation document into the Accommodations collection.
     *
     * @param accommodation the Document object representing the accommodation to insert
     */
    @Override
    public void insertAccommodation(Document accommodation) {
        this.accommodationsCollection.insertOne(accommodation);
    }

    /**
     * Updates the accommodation document with the given id in the Accommodations collection.
     *
     * @param id the id of the accommodation document to update
     * @param accommodation the Document object representing the updated accommodation
     */
    @Override
    public void updateAccommodation(Object id, Document accommodation) {
        this.accommodationsCollection.replaceOne(new Document("_id", id), accommodation);
    }

    /**
     * Updates the accommodation document with the given name in the Accommodations collection.
     *
     * @param name the name of the accommodation document to update
     * @param accommodation the Document object representing the updated accommodation
     */
    public void updateAccommodationName(String name, Document accommodation) {
        this.accommodationsCollection.replaceOne(new Document("name", name), accommodation);
    }

    /**
     * Deletes the accommodation document with the given id from the Accommodations collection.
     *
     * @param id the id of the accommodation document to delete
     */
    @Override
    public void deleteAccommodation(Object id) {
        this.accommodationsCollection.deleteOne(new Document("_id", id));
    }

    public void deleteAccommodationName(String name) {
        this.accommodationsCollection.deleteOne(new Document("name", name));
        System.out.println("Successfully deleted accommodation with name " + name);
    }
}

/* Cette classe dispose de quatre méthodes principales :

getAllAccommodations(): récupère tous les documents de la collection Accommodations et les retourne sous forme de liste de Document.
insertAccommodation(Document accommodation): insère un document accommodation dans la collection Accommodations.
updateAccommodation(String id, Document accommodation): met à jour le document avec l'ID id dans la collection Accommodations avec le document accommodation.
deleteAccommodation(String id): supprime le document avec l'ID id de la collection Accommodations.
 */
