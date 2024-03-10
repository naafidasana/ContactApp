package isen.contactapp.database;


import org.sqlite.SQLiteDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The DataSourceFactory class provides methods for creating and managing data sources and connections to the database.
 */
public class DataSourceFactory {

    private static SQLiteDataSource dataSource;
    private static Connection connection;

    /**
     * Private constructor to prevent instantiation of this class.
     */
    public DataSourceFactory() {
        // Do not allow the creation of an instance of this class.
        throw new IllegalStateException("This class should not be instantiated.");
    }

    /**
     * Returns a SQLiteDataSource instance for connecting to the SQLite database.
     *
     * @return A SQLiteDataSource object.
     */
    public static DataSource getDataSource() {
        if (dataSource == null) {
            dataSource = new SQLiteDataSource();
            dataSource.setUrl("jdbc:sqlite:sqlite.db");
        }
        return dataSource;
    }

    /**
     * Retrieves a connection to the SQLite database.
     *
     * @return A Connection object representing the connection to the database.
     * @throws SQLException if a database access error occurs.
     */
    public static synchronized Connection getConnection() throws SQLException {
        if (connection == null) {
            String dbUrl = "jdbc:sqlite:sqlite.db";
            connection = DriverManager.getConnection(dbUrl);
        }
        return connection;
    }
}
