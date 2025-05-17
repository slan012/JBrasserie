package org.cnam.jbrasserie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.cnam.jbrasserie.beans.Beer;
import org.cnam.jbrasserie.database.DBConnection;

public class BeerDaoImplDb implements BeerDao{
	
	@Override
	public List<Beer> findAll() {
		//TEST OK
		String query = "SELECT * FROM beer";
		
		List<Beer> beerList = new ArrayList<Beer>();

		try (Connection connection = DBConnection.getConnection();
			 Statement statement = connection.createStatement();){
			
			ResultSet results = statement.executeQuery(query);
			
			while(results.next()) {
				Beer beer = new Beer();
				beer = buildBeer(results, beer);
				beerList.add(beer);
			}
		} catch (SQLException e) {
			System.err.print("SQL request error : ");
			e.printStackTrace();
		}
		return beerList;
	}
	
	@Override
	public Beer getBeerById(int id) {
		// TEST OK
		String query = "SELECT * FROM beer WHERE idBeer=?";
		Beer beer = new Beer();
		
		try (Connection connection = DBConnection.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(query);){
			
			preparedStatement.setInt(1, id);
			ResultSet results = preparedStatement.executeQuery();
			
			if (results.next()) {
				beer = buildBeer(results, beer);
			}
			results.close();
		} catch (SQLException e) {
			System.err.print("SQL request error : ");
			e.printStackTrace();
		} 
		return beer;
	}

	@Override
	public List<Beer> findByName(String name) {
		// TEST OK
		String query = "SELECT * FROM beer WHERE name=?";
		List<Beer> beerList = new ArrayList<>();
		
		try (Connection connection = DBConnection.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(query);){
			
			preparedStatement.setString(1, name);
			ResultSet results = preparedStatement.executeQuery();
			
			while(results.next()) {
				Beer beer = new Beer();
				beer = buildBeer(results, beer);
				beerList.add(beer);
			}
			results.close();
		} catch (SQLException e) {
			System.err.print("SQL request error : ");
			e.printStackTrace();
		} 
		return beerList;
	}

	@Override
	public void insertBeer(Beer beer) {
		//TESTS OK
		int updatedRows = 0;
		String query = "INSERT INTO beer(name, brewer, style, alcohol, price, stock)"
				+ "VALUES(?, ?, ?, ?, ?, ?);";
		
		try(Connection connection = DBConnection.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
			
			connection.setAutoCommit(false);
			
			preparedStatement.setString(1, beer.getName());
			preparedStatement.setString(2, beer.getBrewer());
			preparedStatement.setString(3, beer.getStyle());
			preparedStatement.setFloat(4, beer.getAlcohol());
			preparedStatement.setFloat(5, beer.getPrice());
			preparedStatement.setInt(6, beer.getStock());
		
			updatedRows = preparedStatement.executeUpdate();
			connection.commit();
			System.out.print("commit OK");
			if (updatedRows == 0) {
				throw new SQLException("Inserting beer failed, no row added");
			}
		
			try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()){
				if (generatedKeys.next()) {
					beer.setId(generatedKeys.getInt(1));
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

	@Override
	public void deleteBeer(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateBeer(int id, Beer beer) {
		// TODO Auto-generated method stub
		
	}
	
	private static Beer buildBeer(ResultSet results, Beer beer) {
		try {
			beer.setId(results.getInt("idBeer"));
			beer.setName(results.getString("name"));
			beer.setBrewer(results.getString("brewer"));
			beer.setStyle(results.getString("style"));
			beer.setAlcohol(results.getFloat("alcohol"));
			beer.setPrice(results.getFloat("price"));
			beer.setStock(results.getInt("stock"));
		} catch (Exception e){
			System.err.print("Error while drinkin...building beer object !");
			e.printStackTrace();
		}
		return beer;
	}

}
