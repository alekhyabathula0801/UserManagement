package com.bridgelabz.usermanagement.dao;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    final static Logger logger = Logger.getLogger(DatabaseConnection.class);
    public Connection getConnection() {
        try {
            Properties properties=new Properties();
            properties.load(getClass().getClassLoader().getResourceAsStream("database.properties"));
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
