package org.cnam.jbrasserie.dao.order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.cnam.jbrasserie.beans.Client;
import org.cnam.jbrasserie.beans.Order;
import org.cnam.jbrasserie.beans.OrderLine;
import org.cnam.jbrasserie.database.DBConnection;

public class OrderDaoImplDb implements OrderDao{

	@Override
	public List<Order> findAll() {
		// TEST OK
		String query = 
				  "SELECT c.idOrder , c.idClient, c2.*, `date`, SUM(quantity * b.price) as total "
				+ "FROM clientorder c "
				+ "JOIN orderline ob "
				+ "ON ob.idOrder = c.idOrder "
				+ "JOIN beer b "
				+ "ON ob.idBeer = b.idBeer "
				+ "join client c2 on c2.idClient = c.idClient "
				+ "GROUP BY c.idOrder;";
		
		List<Order> orderList = new ArrayList<>();

		try (Connection connection = DBConnection.getConnection();
			 Statement statement = connection.createStatement();){
			
			ResultSet results = statement.executeQuery(query);
			
			while(results.next()) {
				orderList.add(builOrder(results));
			}
		} catch (SQLException e) {
			System.err.print("SQL request error : ");
			e.printStackTrace();
		}
		return orderList;

	}

	@Override
	public Order findById(int id) {
		// TEST OK
		String query = 
				  "SELECT c.idOrder , c.idClient, c2.*, `date`, SUM(quantity * b.price) as total "
				+ "FROM clientorder c "
				+ "JOIN orderline ob "
				+ "ON ob.idOrder = c.idOrder "
				+ "JOIN beer b "
				+ "ON ob.idBeer = b.idBeer "
				+ "JOIN client c2 on c2.idClient = c.idClient"
				+ "WHERE c.idOrder = ? ";
		
		Order order = null;
		
		try (Connection connection = DBConnection.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);){
			
			preparedStatement.setInt(1, id);
			ResultSet results = preparedStatement.executeQuery();
			
			while(results.next()) {
				order = builOrder(results);
			}
		} catch (SQLException e) {
			System.err.print("SQL request error : ");
			e.printStackTrace();
		}
		return order;
	}
	
	@Override
	public List<Order> findByClientId(int id) {
		String query = 
				" SELECT c.idOrder , c2.*, `date`, SUM(quantity * b.price) as total "
				+ "FROM clientorder c "
				+ "JOIN orderline ob "
				+ "ON ob.idOrder = c.idOrder "
				+ "JOIN beer b "
				+ "ON ob.idBeer = b.idBeer "
				+ "JOIN client c2 on c2.idClient = c.idClient "
				+ "WHERE c.idClient  = ? "
				+ "GROUP BY c.idOrder ;";
		
		List<Order> orderList = new ArrayList<>();
		
		try (Connection connection = DBConnection.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);){
			
			preparedStatement.setInt(1, id);
			ResultSet results = preparedStatement.executeQuery();
			
			while(results.next()) {
				Order order = builOrder(results);
				orderList.add(order);
			}
		} catch (SQLException e) {
			System.err.print("SQL request error : ");
			e.printStackTrace();
		}
		return orderList;
	}
	
	
	@Override
	public void insertOrder(Order order) {
		int updatedRows = 0;
		Connection connection = DBConnection.getConnection();
		String queryOrder = "INSERT INTO clientorder(date, idClient) "
				+ "VALUES(CURDATE(), ?);";
		
		String queryLine = "INSERT INTO orderline(idOrder, idBeer, quantity) VALUES(?, ?, ?);";
		
		try(
			PreparedStatement preparedStatementOrder = connection.prepareStatement(queryOrder, Statement.RETURN_GENERATED_KEYS);
			PreparedStatement preparedStatementLine = connection.prepareStatement(queryLine, Statement.RETURN_GENERATED_KEYS)){
			
			connection.setAutoCommit(false);
			
			// Insert order 
			preparedStatementOrder.setInt(1,order.getClient().getId());
			updatedRows = preparedStatementOrder.executeUpdate();
			
			ResultSet generatedKeys = preparedStatementOrder.getGeneratedKeys();
			
			if (generatedKeys.next()) {
				order.setIdOrder(generatedKeys.getInt(1));
			} else {
				System.err.print("Inserting beer failed, no Id fetched");
			}
			
			// Insert lines
			
			for (OrderLine line : order.getLines()) {
				preparedStatementLine.setInt(1, order.getIdOrder());
				preparedStatementLine.setInt(2, line.getBeer().getId());
				preparedStatementLine.setInt(3, line.getQuantity());
				preparedStatementLine.addBatch();
			}
			
			preparedStatementLine.executeBatch();
			
			connection.commit();
			System.out.print("commit OK");
		
		}catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

		
	private Order builOrder(ResultSet results) {
		Client client = new Client();
		Order order = new Order();
		try {
			client.setId(results.getInt("idClient"));
			client.setFirstName(results.getString("firstName"));
			client.setLastName(results.getString("lastName"));
			client.setAdress(results.getString("adress"));
			client.setZipCode(results.getInt("zipCode"));
			client.setCity(results.getString("city"));
			client.setPhone(results.getString("phoneNumber"));
			
			order.setClient(client);
			order.setIdOrder(results.getInt("idOrder"));
			order.setDate(results.getDate("date"));
			order.setTotal(results.getDouble("total"));
		} catch (SQLException e) {
			System.err.print("Error building order object : ");
			e.printStackTrace();
		}
		return order;
	}

	

}
