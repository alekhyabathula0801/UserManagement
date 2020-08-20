package usermanagement.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
   public Connection getConnection() {
       try {
           FileReader reader=new FileReader("C:\\Users\\arun kumar\\IdeaProjects\\UserManagementApp\\src\\main\\resources\\database.properties");
           Properties properties=new Properties();
           properties.load(reader);
           Class.forName(properties.getProperty("driverClassName"));
       return DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("userName"),
               properties.getProperty("password"));
       } catch (ClassNotFoundException e) {
           e.printStackTrace();
       } catch (SQLException throwables) {
           throwables.printStackTrace();
       } catch (FileNotFoundException e) {
           e.printStackTrace();
       } catch (IOException e) {
           e.printStackTrace();
       }
       return null;
   }
}
