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
	
	
	/**
	 * Retrieves all beers from the database.
	 *
	 * @return a list of all beers available in the database. If no beers are found, an empty list is returned.
	 */
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
				beer = getBeerFromResultSet(results, beer);
				beerList.add(beer);
			}
		} catch (SQLException e) {
			System.err.print("SQL request error : ");
			e.printStackTrace();
		}
		return beerList;
	}
	
	/**
	 * Retrieves a beer from the database by its ID.
	 *
	 * @param id the ID of the beer to retrieve.
	 * @return the beer with the specified ID, or null if no such beer exists.
	 */
	@Override
	public Beer findById(int id) {
		// TEST OK
		String query = "SELECT * FROM beer WHERE idBeer=?";
		Beer beer = new Beer();
		
		try (Connection connection = DBConnection.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(query);){
			
			preparedStatement.setInt(1, id);
			ResultSet results = preparedStatement.executeQuery();
			
			if (results.next()) {
				beer = getBeerFromResultSet(results, beer);
			}
			results.close();
		} catch (SQLException e) {
			System.err.print("SQL request error : ");
			e.printStackTrace();
		}
		System.out.print(beer.getId());
		return beer;
	}

	/**
	 * Retrieves a list of beers from the database by their name.
	 *
	 * @param name the name of the beers to retrieve.
	 * @return a list of beers with the specified name, or an empty list if no such beers exist.
	 */
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
				beer = getBeerFromResultSet(results, beer);
				beerList.add(beer);
			}
			results.close();
		} catch (SQLException e) {
			System.err.print("SQL request error : ");
			e.printStackTrace();
		}
		if (beerList.size() == 0) {
			System.out.print("Beer not found!");
		}
		return beerList;
	}

	/**
	 * Inserts a new beer into the database.
	 *
	 * @param beer the beer to insert.
	 */
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

	/**
	 * Updates an existing beer in the database.
	 *
	 * @param id the ID of the beer to update.
	 * @param beer the updated beer object.
	 */
	@Override
	public void updateBeer(int id, Beer beer) {
		// TESTS OK
		int updatedRows = 0;
		String query = "UPDATE beer "
					 + "SET name=?, brewer=?, style=?, alcohol=?, price=?, stock=? "
					 + "WHERE idBeer=? ;";
		
		try(Connection connection = DBConnection.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
			
			connection.setAutoCommit(false);
			
			preparedStatement.setString(1, beer.getName());
			preparedStatement.setString(2, beer.getBrewer());
			preparedStatement.setString(3, beer.getStyle());
			preparedStatement.setFloat(4, beer.getAlcohol());
			preparedStatement.setFloat(5, beer.getPrice());
			preparedStatement.setInt(6, beer.getStock());
			preparedStatement.setInt(7, beer.getId());
		
			updatedRows = preparedStatement.executeUpdate();
			
			connection.commit();
			System.out.print("commit OK");
			
			if (updatedRows == 0) {
				throw new SQLException("Updating failed, id " + beer.getId() + " not found");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Deletes a beer from the database by its ID.
	 *
	 * @param id the ID of the beer to delete.
	 */
	@Override
	public void deleteBeer(int id) {
		int deletedRows = 0;
		String query = "DELETE FROM beer "
					 + "WHERE idBeer=? ;";
		
		try(Connection connection = DBConnection.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
			
			preparedStatement.setInt(1, id);
			deletedRows = preparedStatement.executeUpdate();
			
			if (deletedRows == 0) {
				throw new SQLException("Deleting failed");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static Beer getBeerFromResultSet(ResultSet results, Beer beer) {
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
