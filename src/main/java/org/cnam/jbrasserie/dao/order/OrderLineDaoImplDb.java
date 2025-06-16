package org.cnam.jbrasserie.dao.order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.cnam.jbrasserie.beans.Beer;
import org.cnam.jbrasserie.beans.OrderLine;
import org.cnam.jbrasserie.database.DBConnection;

public class OrderLineDaoImplDb implements OrderLineDao {

	@Override
	public List<OrderLine> findOrderLineById(Integer idOrder){
		String query = 
				"SELECT idOrder, b.*, quantity "
				+ "FROM orderline ol "
				+ "join beer b on b.idBeer = ol.idBeer "
				+ "WHERE idOrder = ?";
		
		List<OrderLine> orderLineList = new ArrayList<>();
		
		try (Connection connection = DBConnection.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);){
			
			preparedStatement.setInt(1, idOrder);
			ResultSet results = preparedStatement.executeQuery();
			
			while (results.next()) {
				OrderLine orderLine = new OrderLine();
				Beer beer = new Beer();
				
				beer.setId(results.getInt("idBeer"));
				beer.setName(results.getString("name"));
				beer.setBrewer(results.getString("brewer"));
				beer.setStyle(results.getString("style"));
				beer.setAlcohol(results.getFloat("alcohol"));
				beer.setPrice(results.getFloat("price"));
				beer.setStock(results.getInt("stock"));
				orderLine.setIdOrder(results.getInt("idOrder"));
				orderLine.setBeer(beer);
				try {
					orderLine.setQuantity(results.getInt("quantity"));
				} catch (Exception ignore) {
					// Ignore exception
				}
				orderLineList.add(orderLine);
			}
			results.close();
		} catch (SQLException e) {
			System.err.print("SQL request error : ");
			e.printStackTrace();
		}

		return orderLineList;

	}

	@Override
	public void addLineToOrder(OrderLine orderLine) {
		int updatedRows = 0;
		String query = "INSERT INTO orderline(idOrder, idBeer, quantity) VALUES(?, ?, ?);";
		
		try(Connection connection = DBConnection.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
			
			connection.setAutoCommit(false);
			
			preparedStatement.setInt(1, orderLine.getIdOrder());
			preparedStatement.setInt(2, orderLine.getBeer().getId());
			preparedStatement.setInt(3, orderLine.getQuantity());
		
			updatedRows = preparedStatement.executeUpdate();
			connection.commit();
			System.out.print("commit OK");
			if (updatedRows == 0) {
				throw new SQLException("Inserting orderLine failed, no row added");
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
