package library.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PSQLConnectionUtility {
   static private Connection connection = null;

   
  public static Connection getConnection(){
       // Example of accessing the properties
      if(connection != null) return connection;
      SetEnviornmentVariables.configureVariables();                 
      String url = System.getProperty("url"),user = System.getProperty("user_name"),password = System.getProperty("password") ;    
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
