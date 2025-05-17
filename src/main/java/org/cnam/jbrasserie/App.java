package org.cnam.jbrasserie;

import java.util.List;

import org.cnam.jbrasserie.beans.Beer;
import org.cnam.jbrasserie.dao.BeerDao;
import org.cnam.jbrasserie.dao.BeerDaoImplDb;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
    	BeerDao beerDao = new BeerDaoImplDb();
//    	Beer beer = new Beer();
//    	beer.setName("Beer test");
//    	beer.setBrewer("Brasserie test");
//    	beer.setAlcohol(5.5f);
//    	beer.setPrice(3.70f);
//    	beer.setStock(20);
//    	beer.setStyle("IPA");
//    	beerDao.insertBeer(beer);
//    	System.out.print(beer.getId());
//		TEST ADDING BEER
//    	List <Beer> beerList = beerDao.findAll();
//    	for (Beer beer : beerList) {
//    		System.out.println("Bière : " + beer.getName() + " || Brasserie : " + beer.getBrewer());
//    	}
    	Beer beer = beerDao.getBeerById(25);
    	System.out.println("Bière : " + beer.getName() + " || Brasserie : " + beer.getBrewer());
    }
}
