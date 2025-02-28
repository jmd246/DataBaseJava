package library;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.javalin.Javalin;
import io.javalin.http.Context;
import library.DAOs.AuthorDAO;
import library.DAOs.BookDAO;
import library.DAOs.UserDAO;
import library.Model.Author;
import library.Model.Book;
import library.Model.User;
import library.utilities.PSQLConnectionUtility;

public class Main {
  
    public static void javalinSetup(){
      //javalin test 
      Javalin app = Javalin.create().start(7000);
      //endpoints
      app.get("/hello-world", ctx->{
         ctx.result("Hello world");
      });
      app.post("/joshua", ctx->{
        ctx.result("Hello joshua");
      });
      //static method reference in error mapping
      app.error(404,Main::NotFoundHandler);

      //static method reference exception mapping
      app.exception(Exception.class, Main::genericExceptionHandler);
    }
    private static void genericExceptionHandler(Exception e,Context ctx){
       ctx.result("Oops something went wrong: "+e.getClass() + "\n" + e.getMessage());
    }
    private static void NotFoundHandler(Context ctx){
       ctx.result("Resource not found");
    }

    public static void main(String[] args) {
      Logger log = LoggerFactory.getLogger(Main.class);
      //javalin test
      javalinSetup();
      // try to open connection
       Connection connection = PSQLConnectionUtility.getConnection();
       try {
         System.out.println(connection.isValid(5));
         System.out.println("Connected to the database successfully!");
         AuthorDAO authorDAO = new AuthorDAO(connection);
         UserDAO userDAO = new UserDAO(connection);
         BookDAO bookDAO = new BookDAO(connection);

         //fetch
         //authorDAO.fetchAuthors();
         User mike = new User("Mike");
         userDAO.insert(mike);
         mike = userDAO.findByName(mike.getName());

         Author author = authorDAO.findByName("Stephen King");
         List<User> users = new ArrayList<>();
         users = userDAO.findAll();
         for(User user : users){
            System.out.println(user);
         }
         System.err.println("author name " + author.toString());

         mike.setName("Mike");
         
         userDAO.update(mike);

         userDAO.delete(mike.getID());
         List<Book> books = bookDAO.findAll();
         for(Book book : books){
          System.out.println(book);
         }
         Book salemsLot = bookDAO.findByName("Salems Lot"); 
         System.out.println(salemsLot);
         bookDAO.delete(salemsLot.getID());
         //bookDAO.insert(salemsLot);

         


         //Author goku = new Author("Goku");
         //insert
         //authorDAO.insertAuthor(goku);
         //authorDAO.fetchAuthors();
         //delete

         //authorDAO.deleteAuthor(goku);

         //authorDAO.fetchAuthors();
         // Author trunks = authorDAO.fetchAuthor("Trunks");
         // Author vegeta = new Author("Vegeta");
         //update
       
          connection.close();
        } 
        catch (SQLException e) {
           System.out.println("Failed to connect to the database.");
           log.error("Sql Exception occured",e);
           //e.printStackTrace();
        }
        catch(NullPointerException e){
         System.out.println("Failed to get data.");
         log.error("Exception occured",e);
         //e.printStackTrace();
        }
    }
}
