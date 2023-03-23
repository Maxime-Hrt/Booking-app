package dao;

import org.bson.Document;

import java.util.List;

public interface IUsersDao {
    List<Document> getAllUsers();
    void insertUser(Document user);
    void updateUser(Object id, Document user);
    void deleteUser(Object id);
}
