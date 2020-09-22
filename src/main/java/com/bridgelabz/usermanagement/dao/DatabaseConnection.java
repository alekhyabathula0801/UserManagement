package com.bridgelabz.usermanagement.dao;

import org.apache.log4j.Logger;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    final static Logger logger = Logger.getLogger(DatabaseConnection.class);
    public Connection getConnection() {
        try {
            FileReader reader=new FileReader("C:\\Users\\arun kumar\\IdeaProjects\\UserManagementApp\\src\\main\\resources\\database.properties");
            Properties properties=new Properties();
            properties.load(reader);
            Class.forName(properties.getProperty("driverClassName"));
            return DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("userName"),
                    properties.getProperty("password"));
        } catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
            logger.error("exception "+e.getMessage());
        }
        return null;
    }
}
