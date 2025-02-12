package library.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class SetEnviornmentVariables {
    public static void configureVariables(){
        //get user home directory
        String userHome = System.getProperty("user.home");
        File config = new File(userHome, "/OneDrive/Desktop/JavaDataBaseProject/library/src/main/resources/library.properties");
        //System.out.println(userHome);
        //define property path
        Properties properties = new Properties();
 
        try(FileInputStream input = new FileInputStream(config)){
            properties.load(input);

             // Set the properties as system properties
             System.setProperty("url", properties.getProperty("URL"));
             System.setProperty("user_name", properties.getProperty("USER"));
             System.setProperty("password", properties.getProperty("PASSWORD"));

        }
        catch(IOException e){
            e.printStackTrace();
        }
        


   }
    

}
