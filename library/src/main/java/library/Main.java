package library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Main {
    public static void main(String[] args) {
       // Database URL, username, and password
       String url = "jdbc:mysql://localhost:3306/library";
       String user = "root";
       String password = "";
       // try to open connection
       try (Connection connection = DriverManager.getConnection(url, user, password)) {
         System.out.println("Connected to the database successfully!");
         AuthorDAO authorDAO = new AuthorDAO(connection);
         //fetch
         authorDAO.fetchAuthors();
         Author goku = new Author("Goku");
         //insert
         authorDAO.insertAuthor(goku);
         authorDAO.fetchAuthors();
         //delete

         authorDAO.deleteAuthor(goku);

         authorDAO.fetchAuthors();
         Author trunks = authorDAO.fetchAuthor("Trunks");
         Author vegeta = new Author("Vegeta");
         //update
         authorDAO.updateAuthor(trunks,vegeta);
         
         authorDAO.fetchAuthors();
         
         //fetch
         //fetchAuthors(connection);
          
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
