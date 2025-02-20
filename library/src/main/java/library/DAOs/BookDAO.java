package library.DAOs;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import library.Model.Book;


public class BookDAO implements MemberDAO<Book, Long> {
    private final Connection connection;

    public BookDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Book insert(Book entity) throws SQLException {
        String query = "INSERT INTO book (title, isbn, author_id) VALUES(?, ?, (SELECT author_id FROM author WHERE author_name = ?))";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getISBN());
            preparedStatement.setString(3, entity.getAuthorName());
            int rowsInserted = preparedStatement.executeUpdate();
            if(rowsInserted > 0){
                try(ResultSet generatedKeys = preparedStatement.getGeneratedKeys()){
                    if(generatedKeys.next()){
                        System.out.println("Inserted " + entity + " Successfully");
                        return new Book(entity.getName(), entity.getAuthorName(), entity.getISBN(), entity.getAuthorID(),generatedKeys.getLong(1));
                    }
                }
            }
        }
        return null;
    }

    @Override
    public Book findByID(Long id) throws SQLException {
        if(id == null ) return null;
        String query = """
        SELECT b.book_id, b.title, b.author_id, b.isbn,a.author_name, 
        FROM book AS b 
        INNER JOIN author a ON b.author_id = a.author_id
        WHERE b.book_id = ? 
        """;
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
           preparedStatement.setLong(1, id);
           ResultSet resultSet = preparedStatement.executeQuery();
           if(resultSet.next()){
              long bookID = resultSet.getLong("book_id");
              long authorID = resultSet.getLong("author_id");
              String authorName = resultSet.getString("author_name");
              String isbn = resultSet.getString("isbn");
              String title = resultSet.getString("title");
              resultSet.close();
              return new Book(title,authorName,isbn,authorID,bookID); 
           }
           else{
            resultSet.close();
            return null;
           }
        }
    }

    @Override
    public Book findByName(String name) throws SQLException {
        String query = """
                SELECT b.title, b.isbn, b.book_id, b.author_id, a.author_name
                FROM book AS b
                INNER JOIN author AS a ON b.author_id = a.author_id
                WHERE title = ?
                """;
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                String title = resultSet.getString("title");
                String isbn = resultSet.getString("isbn");
                String authorName = resultSet.getString("author_name");
                long authorID = resultSet.getLong("author_id");
                long bookID = resultSet.getLong("book_id");
                resultSet.close();
                return new Book(title,authorName,isbn,authorID,bookID);
            }
            else{
                resultSet.close();
                return null;
            }
        }
    }

    @Override
    public List<Book> findAll() throws SQLException {
        String query = """
                SELECT b.*,a.author_name
                FROM book AS b
                INNER JOIN author AS a ON b.author_id = a.author_id
                """;
        List<Book> books = new ArrayList<>();
        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query)){
            while(resultSet.next()){
                String title = resultSet.getString("title");
                String isbn = resultSet.getString("isbn");
                String authorName = resultSet.getString("author_name");
                long bookID = resultSet.getLong("book_id");
                long authorID = resultSet.getLong("author_id");
                books.add(new Book(title, authorName, isbn, authorID,bookID));
            }
            return books;
        }
    }

    @Override
    public void update(Book entity) throws SQLException {
        String query = "UPDATE book SET title = ? , author_id, isbn = ? WHERE book_id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setLong(2, entity.getAuthorID());
            preparedStatement.setString(3, entity.getISBN());
            int rowsUpdated = preparedStatement.executeUpdate();
            if(rowsUpdated > 0){
               System.out.println("Updated " + entity + " Successfully");
            }
            else{
               throw new SQLException("Failed to update " + entity);
            }
        }
    }

    @Override
    public void delete(Long id) throws SQLException {
        if(id == null) return;
        String query = "DELETE FROM book WHERE book_id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setLong(1, id);
            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Deleted row with id " + id +" from books");
            }
            else{
                throw new SQLException("Failed to delete row with id " + id +" from books");
            }
        }
    }
}
