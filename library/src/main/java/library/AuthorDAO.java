package library;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDAO {
    private final Connection connection;
    public AuthorDAO(Connection connection){
        this.connection = connection;
    }
    //fetch all authors
    public Author fetchAuthor(String name) throws SQLException{
        String query = "SELECT * FROM author WHERE author_name = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1, name);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()){
                    String authorName = resultSet.getString("Author_name");
                    return new Author(authorName);
                }
                else{
                    return null;
                }
            }
        }
    }
    
    public List<Author> fetchAuthors()throws SQLException{
        String query = "SELECT * FROM author";
        List<Author> authors = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery()){
                while(resultSet.next()){
                    String name = resultSet.getString("Author_name");
                    Author author = new Author(name);
                    authors.add(author);
                }
        }
        printAuthors(authors);
        return authors;
    }
    public void insertAuthor(Author newAuthor) throws SQLException{
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
    public void updateAuthor(Author oldAuthor, Author newAuthor) throws SQLException{
        String query = "UPDATE author SET Author_name = ? WHERE Author_name = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1, newAuthor.getName());
            preparedStatement.setString(2, oldAuthor.getName());
            int rowsUpdated = preparedStatement.executeUpdate();
            if(rowsUpdated > 0){
                System.err.println("Update Succesfully");
            }
            else{
                throw new SQLException("Failed to update Author_name "+ oldAuthor.getName() + " to new name "+ newAuthor.getName());
            }
        }
    }
    public void deleteAuthor(Author author) throws SQLException{
        String query = "DELETE FROM author WHERE Author_name = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1, author.getName());
            int rowsDeleted = preparedStatement.executeUpdate();
            if(rowsDeleted > 0 ){
               System.out.println("Row with name " + author.getName() + " deleted succeffuly");
            }
            else{
               throw new SQLException("FAILED TO DELETE ROW WITH name " + author.getName());
            }            
        }
     }
     public void printAuthors(List<Author> authors){
        for(Author author : authors){
           System.out.println(author.getName());
        }
     }
}
