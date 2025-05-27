package org.cnam.jbrasserie.database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
	private static String url;
	private static String password;
	private static String user;
	
	public static Connection getConnection() {

		setDbConfig();
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			System.err.print("Database connection error, please check database config");
			e.printStackTrace();
		}
		return connection;
	}
	
	private static void setDbConfig() {
		BufferedReader br;
		Properties prop = new Properties();
		String propFilePath = new File("src/main/java/org/cnam/jbrasserie/config/config.properties").getAbsolutePath();
		
		try (FileReader fr = new FileReader(propFilePath)){
			
			br = new BufferedReader(fr);
			prop.load(br);
			
			url =      prop.getProperty("dburl");
			user =     prop.getProperty("dbuser");
			password = prop.getProperty("dbpassword");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
