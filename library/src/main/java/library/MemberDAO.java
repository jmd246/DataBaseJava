package library;

import java.sql.SQLException;
import java.util.List;


public interface MemberDAO<T,ID> {
    void insert(T entity) throws SQLException;
    T findByID(ID id) throws SQLException;
    T findByName(String name) throws SQLException;
    List<T> findAll() throws SQLException;
    void update(T entity) throws SQLException;
    void delete(ID id) throws SQLException;    
}
