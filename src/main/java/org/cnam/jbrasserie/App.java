package org.cnam.jbrasserie;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cnam.jbrasserie.beans.Client;
import org.cnam.jbrasserie.beans.Order;
import org.cnam.jbrasserie.dao.client.ClientDao;
import org.cnam.jbrasserie.dao.client.ClientDaoImplDb;
import org.cnam.jbrasserie.dao.order.OrderDao;
import org.cnam.jbrasserie.dao.order.OrderDaoImplDb;
import org.cnam.jbrasserie.views.client.ClientView;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

    	OrderDao orderDao = new OrderDaoImplDb();
    	ClientDao clientDao = new ClientDaoImplDb();
    	
    	List<Client> clientList = clientDao.findAll();
    	Map<Client, List<Order>> orderList = new HashMap<>();
    	
    	for (Client client : clientList) {
    		List<Order> orders = orderDao.findByClientId(client.getId());
    		orderList.put(client, orders);
    	}
    	
    	    	
    	ClientView view = new ClientView();
    	view.display();
    	
    	
    	
    	
//    	Map<Integer, Integer> orderList = orderBeerDao.findBeersByIdOrder(2);
//    	
//    	for (Map.Entry<Integer, Integer> order : orderList.entrySet()) {
//    		O
//    		System.out.println("Commande n° 2 || Bière : " + order.getKey() + " || Quantité : " + order.getValue());
// 
//    	}
    	

    }
}
