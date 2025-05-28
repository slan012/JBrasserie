package org.cnam.jbrasserie.dao.order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.cnam.jbrasserie.beans.Order;
import org.cnam.jbrasserie.database.DBConnection;

public class OrderDaoImplDb implements OrderDao{

	@Override
	public List<Order> findAll() {
		// TEST OK
		String query = 
				  "SELECT c.idOrder , c.idClient, `date`, SUM(quantity * b.price) as total "
				+ "FROM clientorder c "
				+ "JOIN orderline ob "
				+ "ON ob.idOrder = c.idOrder "
				+ "JOIN beer b "
				+ "ON ob.idBeer = b.idBeer "
				+ "GROUP BY c.idOrder";
		
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
				  "SELECT c.idOrder , c.idClient, `date`, SUM(quantity * b.price) as total "
				+ "FROM clientorder c "
				+ "JOIN orderline ob "
				+ "ON ob.idOrder = c.idOrder "
				+ "JOIN beer b "
				+ "ON ob.idBeer = b.idBeer "
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
				  "select c.idClient, c2.idOrder, c2.`date`, c.firstName, c.lastName, c.adress, c.zipCode, c.city, c.phoneNumber,  SUM(quantity * b.price) as total "
				  + "from client c "
				  + "inner join clientorder c2 on c.idClient = c2.idClient "
				  + "inner join orderline ob on ob.idOrder = c2.idOrder "
				  + "inner join beer b on b.idBeer = ob.idBeer "
				  + "where c.idClient = ? "
				  + "group by c2.idOrder";
		
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
		String query = "INSERT INTO clientorder(date, idClient) "
				+ "VALUES(CURDATE(), ?);";
		
		try(Connection connection = DBConnection.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
			
			connection.setAutoCommit(false);
			
			preparedStatement.setInt(1,order.getIdClient());
		
			updatedRows = preparedStatement.executeUpdate();
			connection.commit();
			System.out.print("commit OK");
			if (updatedRows == 0) {
				throw new SQLException("Inserting order failed, no row added");
			}
		
			try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()){
				if (generatedKeys.next()) {
					order.setIdOrder(generatedKeys.getInt(1));
				} else {
					throw new SQLException("Inserting beer failed, no Id fetched");
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

		
	private Order builOrder(ResultSet results) {
		Order order = new Order();
		try {
			order.setIdOrder(results.getInt("idOrder"));
			order.setDate(results.getDate("date"));
			order.setIdClient(results.getInt("idClient"));
			order.setTotal(results.getDouble("total"));
		} catch (SQLException e) {
			System.err.print("Error building order object : ");
			e.printStackTrace();
		}
		return order;
	}

	

}
