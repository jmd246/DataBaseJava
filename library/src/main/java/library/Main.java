package library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Main {
   private static void fetchAuthors(Connection connection) throws SQLException{
      // Execute a query
      String fetchQuery = "SELECT * FROM author ORDER BY Author_name";
      try(PreparedStatement preparedStatement = connection.prepareStatement(fetchQuery)){
         ResultSet resultSet = preparedStatement.executeQuery();
         // Process the result set  fetching author table which has only author name
         while (resultSet.next()) {
            System.out.println("Author Name: " + resultSet.getString("Author_name"));
         }
      }
   }
   public static void updateAuthor(Connection connection, String oldName, String newName) throws SQLException {
      String updateQuery = "UPDATE author SET Author_name = ? WHERE Author_name = ?" ;
      try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)){
         preparedStatement.setString(1, newName);
         preparedStatement.setString(2, oldName);

         int rowsUpdated = preparedStatement.executeUpdate();
         if (rowsUpdated > 0) {
            System.out.println("Author updated successfully.");
         }
         else { 
            throw new SQLException("UPDATE FAILED, NO AUTHOR FOUND WITH THE NAME: "+oldName);
         }
      }
  }
  public static void insertAuthor(Connection connection,String name) throws SQLException{
     String query = "INSERT INTO author (Author_name) VALUES (?)";
     try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
         preparedStatement.setString(1, name);
         int rowsInserted = preparedStatement.executeUpdate();
         if(rowsInserted > 0){
            System.out.println("inserted successfully");
         }
         else{
            throw new SQLException("faled to insert "+ name + " into author table");
         }
     }

  }
    public static void main(String[] args) {
       // Database URL, username, and password
       String url = "jdbc:mysql://localhost:3306/library";
       String user = "root";
       String password = "";
       // try to open connection
       try (Connection connection = DriverManager.getConnection(url, user, password)) {
         System.out.println("Connected to the database successfully!");
         // Create a Statement object
         //fetch
         fetchAuthors(connection);
         //insert
         insertAuthor(connection, "Goku");

         //update
         //updateAuthor(connection, "Goku", "Vegeta");
         //fetch
         fetchAuthors(connection);
          
          //create
          //join
          //inner join
          //outer join
        } 
        catch (SQLException e) {
           System.out.println("Failed to connect to the database.");
           e.printStackTrace();
        }
    }
}
