package library;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AuthorDAO implements MemberDAO <Author,Long>{
    private final Connection connection;
    public AuthorDAO(Connection connection){
        this.connection = connection;
    }
    //fetch all authors
    @Override
    public Author findByID(Long id) throws SQLException {
        String query = "SELECT * FROM author WHERE author_id = ?";
        try(PreparedStatement prepStatement = connection.prepareStatement(query)){
            prepStatement.setLong(1, id);
            try(ResultSet resultSet = prepStatement.executeQuery()){
                if(resultSet.next()){
                    String authorName = resultSet.getString("author_name");
                    return new Author(authorName,id);
                }
                else{
                    return null;
                }
            }
        }
    }
    @Override
    public Author findByName(String name) throws SQLException{
        String query = "SELECT * FROM author WHERE author_name = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1, name);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()){
                    String authorName = resultSet.getString("author_name");
                    Long id = resultSet.getLong("author_id");
                    return new Author(authorName,id);
                }
                else{
                    return null;
                }
            }
        }
    }
    @Override
    public List<Author> findAll()throws SQLException{
        String query = "SELECT * FROM author";
        List<Author> authors = new ArrayList<>();
        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query)){
                while(resultSet.next()){
                    String name = resultSet.getString("author_name");
                    Long id = resultSet.getLong("author_id");
                    Author author = new Author(name,id);
                    authors.add(author);
                }
        }
        return authors;
    }
    
    @Override
    public void insert(Author newAuthor) throws SQLException{
        String query = "INSERT INTO author (Author_name) VALUES (?)";
        try( PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1, newAuthor.getName());
            int rowsInserted = preparedStatement.executeUpdate();
            if(rowsInserted > 0){
                System.out.println("Inserted " + newAuthor.getName() + " successfully");
                
            }
            else{
                throw new SQLException("failed to insert author " + newAuthor.getName());
            }
        }
    }
    @Override
    public void update(Author author) throws SQLException{
        String query = "UPDATE author SET author_name = ? WHERE author_id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1, author.getName());
            preparedStatement.setLong(2, author.getID());
            int rowsUpdated = preparedStatement.executeUpdate();
            if(rowsUpdated > 0){
                System.err.println("Update Succesfully");
            }
            else{
                throw new SQLException("Failed to update Author_name to new name "+ author.getName());
            }
        }
    }
    @Override
    public void delete(Long id) throws SQLException{
        if(this.findByID(id) == null) return;
        String query = "DELETE FROM author WHERE author_id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setLong(1, id);
            int rowsDeleted = preparedStatement.executeUpdate();
            if(rowsDeleted > 0 ){
               System.out.println(" deleted succeffuly");
            }
            else{
               throw new SQLException("FAILED TO DELETE ROW WITH id " + id);
            }            
        }
     }
}
