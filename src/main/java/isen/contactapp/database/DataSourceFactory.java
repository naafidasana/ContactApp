package isen.contactapp.database;


import org.sqlite.SQLiteDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSourceFactory {

    private static SQLiteDataSource dataSource;
    private static Connection connection;

    public DataSourceFactory() {
        // Do not allow the creation of an instance of this class.
        throw new IllegalStateException("This class should not be instantiated.");
    }

    public static DataSource getDataSource() {
        if (dataSource == null) {
            dataSource = new SQLiteDataSource();
            dataSource.setUrl("jdbc:sqlite:sqlite.db");
        }
        return dataSource;
    }

    public static synchronized Connection getConnection() throws SQLException {
        if (connection == null) {
            String dbUrl = "jdbc:sqlite:sqlite.db";
            connection = DriverManager.getConnection(dbUrl);
        }
        return connection;
    }
}
