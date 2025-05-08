package org.cnam.jbrasserie;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.cnam.jbrasserie.database.DBConnection;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
    	
    	Connection connection = DBConnection.getConnection();
		Statement statement;
		try {
			statement = connection.createStatement();
			String query = "SELECT * FROM Beer WHERE brewer = \"Brasserie du Nord\"; ";
			ResultSet results = statement.executeQuery(query);
			while (results.next()) {
				System.out.println(results.getString("name"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }
    
}
