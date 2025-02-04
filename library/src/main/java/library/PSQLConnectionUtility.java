package library;  

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PSQLConnectionUtility {

   
  static Connection getConnection(){
       // Example of accessing the properties
      SetEnviornmentVariables.configureVariables();                 
      String url=System.getProperty("url"),user = System.getProperty("user_name"),password = System.getProperty("password") ;    
      Connection connection = null;
      try{
         connection = DriverManager.getConnection(url, user, password);
         System.out.println("Connected to the database successfully!");
      }
      catch(SQLException e){
         e.printStackTrace();
      }
      return connection;
   }

    
}
