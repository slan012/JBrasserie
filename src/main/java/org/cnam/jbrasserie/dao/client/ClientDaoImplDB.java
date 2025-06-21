package org.cnam.jbrasserie.dao.client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.cnam.jbrasserie.beans.Client;
import org.cnam.jbrasserie.database.DBConnection;
import org.cnam.jbrasserie.exceptions.DaoException;

public class ClientDaoImplDB implements ClientDao {


	@Override
	public List<Client> findAll() {

		String query = "SELECT * FROM client";
		List<Client> clientList = new ArrayList<>();

		try (Connection connection = DBConnection.getConnection();
				Statement statement = connection.createStatement();) {

			ResultSet results = statement.executeQuery(query);

			while (results.next()) {
				Client client = new Client();
				client = getClientFromResultSet(results, client);
				clientList.add(client);
			}
		} catch (SQLException e) {
			System.err.print("SQL request error : ");
			e.printStackTrace();
		}
		return clientList;
	}

	
	@Override
	public Client findById(int id) {

		String query = "SELECT * FROM client WHERE idClient=?";
		Client client = new Client();

		try (Connection connection = DBConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);) {

			preparedStatement.setInt(1, id);
			ResultSet results = preparedStatement.executeQuery();

			if (results.next()) {
				client = getClientFromResultSet(results, client);
			}
			results.close();
		} catch (SQLException e) {
			System.err.print("SQL request error : ");
			e.printStackTrace();
		}
		return client;
	}

	@Override
	public List<Client> findByName(String name) {

		String query = "SELECT * FROM client WHERE lastName=?";
		List<Client> clientList = new ArrayList<>();
		
		try (Connection connection = DBConnection.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(query);){
			
			preparedStatement.setString(1, name);
			ResultSet results = preparedStatement.executeQuery();
			
			while(results.next()) {
				Client client = new Client();
				client = getClientFromResultSet(results, client);
				clientList.add(client);
			}
			results.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clientList;
	}


	@Override
	public void add(Client client) {

		int updatedRows = 0;
		String query = "INSERT INTO client(firstName, lastName, adress, zipCode, city, phoneNumber)"
				+ "VALUES(?, ?, ?, ?, ?, ?);";
		
		try(Connection connection = DBConnection.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
			
			connection.setAutoCommit(false);
			
			preparedStatement.setString(1, client.getFirstName());
			preparedStatement.setString(2, client.getLastName());
			preparedStatement.setString(3, client.getAdress());
			preparedStatement.setInt(4, client.getZipCode());
			preparedStatement.setString(5, client.getCity());
			preparedStatement.setString(6, client.getPhone());
		
			updatedRows = preparedStatement.executeUpdate();
			connection.commit();

			if (updatedRows == 0) {
				throw new SQLException("Inserting client failed, no row added");
			}
		
			try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()){
				if (generatedKeys.next()) {
					client.setId(generatedKeys.getInt(1));
				} else {
					
					throw new SQLException("Inserting client failed, no Id fetched");
				}
			} catch (SQLException e) {
				System.err.print("SQL request error : ");
				e.printStackTrace();
				connection.rollback();
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	@Override
	public void update(Client client) {

		int updatedRows = 0;
		String query = "UPDATE client "
					 + "SET firstName=?, lastName=?, adress=?, zipCode=?, city=?, phoneNumber=? "
					 + "WHERE idClient=? ;";
		
		try(Connection connection = DBConnection.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
			
			connection.setAutoCommit(false);
			
			preparedStatement.setString(1, client.getFirstName());
			preparedStatement.setString(2, client.getLastName());
			preparedStatement.setString(3, client.getAdress());
			preparedStatement.setInt(4, client.getZipCode());
			preparedStatement.setString(5, client.getCity());
			preparedStatement.setString(6, client.getPhone());
			preparedStatement.setInt(7, client.getId());
		
			updatedRows = preparedStatement.executeUpdate();
			connection.commit();
		
			if (updatedRows == 0) {
				throw new SQLException("Updating failed, id " + client.getId() + " not found");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Deletes a client from the database.
	 *
	 * @param id the ID of the client to delete.
	 * @throws Exception 
	 */
	@Override
	public void delete(int id) throws DaoException {
		// TEST OK
		int deletedRows = 0;
		String query = "DELETE FROM client "
					 + "WHERE idClient=? ;";
		
		try(Connection connection = DBConnection.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
			
			preparedStatement.setInt(1, id);
			deletedRows = preparedStatement.executeUpdate();
			
			if (deletedRows == 0) {
				throw new SQLException("Deleting failed");
			}
		} catch (Exception e) {
			throw new DaoException(e.getMessage());
		}

	}

	
	private static Client getClientFromResultSet(ResultSet results, Client client) {
		try {
			client.setId(results.getInt("idClient"));
			client.setFirstName(results.getString("firstName"));
			client.setLastName(results.getString("lastName"));
			client.setAdress(results.getString("adress"));
			client.setZipCode(results.getInt("zipCode"));
			client.setCity(results.getString("city"));
			client.setPhone(results.getString("phoneNumber"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return client;
	}

}
