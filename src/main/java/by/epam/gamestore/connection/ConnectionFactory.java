package by.epam.gamestore.connection;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private static final Logger logger = LogManager.getLogger();
    private static final Properties property = new Properties();
    private static final String DATABASE_URL;
    private static final String DATABASE_URL_PROP = "url";
    private static final String PATH = "config/database.properties";
    private static final String DRIVER_PROP = "driver";

    static {
        try {
            InputStream fileInputStream = ConnectionFactory.class.getClassLoader().getResourceAsStream(PATH);
            property.load(fileInputStream);
            Class.forName(property.getProperty(DRIVER_PROP));
        }catch (ClassNotFoundException | FileNotFoundException e){
            logger.error("Driver don't have registration ",e);
        } catch (IOException e) {
            logger.error("file is not exist ",e);
        }
        DATABASE_URL = property.getProperty(DATABASE_URL_PROP);
    }

    /**
     * Get connection.
     *
     * @return the connection
     * @throws SQLException the SQL exception
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL,property);
    }

    private ConnectionFactory() {

    }
}
