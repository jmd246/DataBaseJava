package library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Main {
    public static void main(String[] args) {
       // Database URL, username, and password
       String url = "jdbc:mysql://localhost:3306/library";
       String user = "root";
       String password = "";
       // try to open connection
       try (Connection connection = DriverManager.getConnection(url, user, password)) {
          System.out.println("Connected to the database successfully!");
          // Create a Statement object
          Statement statement = connection.createStatement();

          // Execute a query
          String query = "SELECT * FROM author";
          ResultSet resultSet = statement.executeQuery(query);

          // Process the result set
          while (resultSet.next()) {
            System.out.println("Author Name: " + resultSet.getString("Author_name"));
          }


        } 
        catch (SQLException e) {
           System.out.println("Failed to connect to the database.");
           e.printStackTrace();
        }
    }
}
