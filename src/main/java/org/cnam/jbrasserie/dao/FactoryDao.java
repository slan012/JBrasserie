package org.cnam.jbrasserie.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Paths;
import java.util.Properties;

import org.cnam.jbrasserie.dao.beer.BeerDao;
import org.cnam.jbrasserie.dao.client.ClientDao;
import org.cnam.jbrasserie.dao.order.OrderDao;
import org.cnam.jbrasserie.dao.order.OrderLineDao;

public class FactoryDao {
	
	private static Class<?> daoClass = null;
	private static Constructor<?> constructor = null;
	
	private static String getDaoProps() {

		String dao = null;
		Properties props = new Properties();
		
		try {
			String filePath = Paths.get("",
					"src",
					"main",
					"java",
					"org",
					"cnam",
					"jbrasserie",
					"dao",
					"datasource.properties").toAbsolutePath().toString();
			InputStream is = new FileInputStream(new File(filePath));
			props.load(is);
			dao = props.getProperty("datasource");
			is.close();
			
		} catch (Exception e) {
			System.out.print("Fichier datasource injoignable");
			e.printStackTrace();
		}
		return dao;
	}

	public static BeerDao getBeerDao() {
		
		try {
			daoClass = Class.forName("org.cnam.jbrasserie.dao.beer.BeerDaoImpl" + getDaoProps());
		} catch (ClassNotFoundException e) {
			System.out.print("Class not found ");
			e.printStackTrace();
		}
		try {
			constructor = daoClass.getConstructor();
		} catch (NoSuchMethodException e) {
			System.out.print("Constructor not found ");
			e.printStackTrace();
		}
		try {
			return (BeerDao) constructor.newInstance();
		} catch (InstantiationException | IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
			System.out.print("Instanciation error ");
			e.printStackTrace();
		}
		return null;
	}
	
	public static ClientDao getClientDao() {
		
		try {
			daoClass = Class.forName("org.cnam.jbrasserie.dao.client.ClientDaoImpl" + getDaoProps());
		} catch (ClassNotFoundException e) {
			System.out.print("Class not found ");
			e.printStackTrace();
		}
		try {
			constructor = daoClass.getConstructor();
		} catch (NoSuchMethodException e) {
			System.out.print("Constructor not found ");
			e.printStackTrace();
		}
		try {
			return (ClientDao) constructor.newInstance();
		} catch (InstantiationException | IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
			System.out.print("Instanciation error ");
			e.printStackTrace();
		}
		return null;
	}

	public static OrderDao getOrderDao() {
		
		try {
			daoClass = Class.forName("org.cnam.jbrasserie.dao.order.OrderDaoImpl" + getDaoProps());
		} catch (ClassNotFoundException e) {
			System.out.print("Class not found ");
			e.printStackTrace();
		}
		try {
			constructor = daoClass.getConstructor();
		} catch (NoSuchMethodException e) {
			System.out.print("Constructor not found ");
			e.printStackTrace();
		}
		try {
			return (OrderDao) constructor.newInstance();
		} catch (InstantiationException | IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
			System.out.print("Instanciation error ");
			e.printStackTrace();
		}
		return null;
	}
	
	public static OrderLineDao getOrderLineDao() {
		
		try {
			daoClass = Class.forName("org.cnam.jbrasserie.dao.order.OrderLineDaoImpl" + getDaoProps());
		} catch (ClassNotFoundException e) {
			System.out.print("Class not found ");
			e.printStackTrace();
		}
		try {
			constructor = daoClass.getConstructor();
		} catch (NoSuchMethodException e) {
			System.out.print("Constructor not found ");
			e.printStackTrace();
		}
		try {
			return (OrderLineDao) constructor.newInstance();
		} catch (InstantiationException | IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
			System.out.print("Instanciation error ");
			e.printStackTrace();
		}
		return null;
	}
}
