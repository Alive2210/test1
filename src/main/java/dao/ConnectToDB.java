package dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectToDB {
    private static final Logger logger = LoggerFactory.getLogger(ConnectToDB.class);
    public  Connection getConnection(String driver, String url,String user,String pass){
       try {
           Class.forName(driver);
           logger.info("Driver is loaded");
       } catch (ClassNotFoundException e) {
           throw new IllegalStateException("Cannot find the driver in the classpath!", e);
       }
        Connection con;
        try {
            con = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            con = null;
        logger.error("Can't get connection.. ", e);
        }
        return con;
    }
}
