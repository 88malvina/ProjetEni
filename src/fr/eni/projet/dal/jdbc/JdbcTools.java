package fr.eni.projet.dal.jdbc;

import java.sql.Connection;


import java.sql.DriverManager;
import java.sql.SQLException;

import fr.eni.projet.dal.Settings;

public class JdbcTools {
	
	private static String urldb;
	private static String userdb;
	private static String passworddb;
	
	// Chargement du driver
    static {
		try {
			Class.forName(Settings.getProperty("driver"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("driver failed");
		}
		urldb=Settings.getProperty("url");
		userdb=Settings.getProperty("user");
		passworddb=Settings.getProperty("password");
	}
	
	public static Connection getConnection() {
		Connection connection=null;
		try {
			connection = DriverManager.getConnection(urldb,userdb,passworddb);
		} catch (SQLException e) {
			// TODO PRISCILA gérer exception
			System.out.println("connection failed");
			e.printStackTrace();
		}
		return connection;
	}
	
	public static void closeConnection(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO PRISCILA gérer exception
			System.out.println("close connection failed");
			e.printStackTrace();
		}
	}
}
