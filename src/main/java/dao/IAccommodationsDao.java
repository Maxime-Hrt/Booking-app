package dao;

import org.bson.Document;

import java.io.ObjectInputValidation;
import java.util.List;

public interface IAccommodationsDao {
    List<Document> getAllAccommodations();
    void insertAccommodation(Document accommodation);
    void updateAccommodation(Object id, Document accommodation);
    void deleteAccommodation(Object id);
}