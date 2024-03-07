package isen.contactapp;

//import java.sql.Connection;
//import java.sql.DriverManager;
//
//public class SplConnection {
//
//	public static Connection  SplConnection() {
//		// TODO Auto-generated constructor stub
//		try {
//			Class.forName("org.sqlite.JDBC");
//			Connection connect = DriverManager.getConnection("jdbc:sqlite:Contacts.sqlite")
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			return null;
//		}
//	}
//
//}


import javax.sql.DataSource;

import org.sqlite.SQLiteDataSource;

import javax.sql.DataSource;



 class DataSourceFactory {

	private static SQLiteDataSource dataSource;

	private DataSourceFactory() {
		// This is a static class that should not be instantiated.
		// Here's a way to remember it when this class will have 2K lines and you come
		// back to it in 2 years
		throw new IllegalStateException("This is a static class that should not be instantiated");
	}

	/**
	 * @return a connection to the SQLite Database
	 * 
	 */
	public static DataSource getDataSource() {
		if (dataSource == null) {
			dataSource = new SQLiteDataSource();
			dataSource.setUrl("jdbc:sqlite:sqlite.db");
		}
		return dataSource;
	}
}