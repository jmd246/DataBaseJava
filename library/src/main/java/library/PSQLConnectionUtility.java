package library;  

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PSQLConnectionUtility {
   static String url="jdbc:mysql://localhost:3306/library",user = "root",password = "";    
   
  static Connection getConnection(){
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
