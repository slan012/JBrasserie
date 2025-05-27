package org.cnam.jbrasserie;

import java.util.List;

import org.cnam.jbrasserie.beans.Client;
import org.cnam.jbrasserie.beans.Order;
import org.cnam.jbrasserie.dao.ClientDao;
import org.cnam.jbrasserie.dao.ClientDaoImplDb;
import org.cnam.jbrasserie.dao.OrderDao;
import org.cnam.jbrasserie.dao.OrderDaoImplDb;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
    	OrderDao orderDao = new OrderDaoImplDb();
    	List<Order> orderList = orderDao.findAll();
    	ClientDao clientDao = new ClientDaoImplDb();
    	Order order = orderDao.findById(2);
    	Client client = clientDao.findById(order.getIdClient());
    	
		System.out.println("Commande n° " + order.getIdOrder() +" || Nom : " + client.getLastName() + " || Montant : " + order.getTotal());

    }
}
