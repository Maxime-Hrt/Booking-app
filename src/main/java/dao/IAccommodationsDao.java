package dao;

import org.bson.Document;

import java.util.List;

public interface IAccommodationsDao {
    List<Document> getAllAccommodations();
    void insertAccommodation(Document accommodation);
    void updateAccommodation(String id, Document accommodation);
    void deleteAccommodation(String id);
}