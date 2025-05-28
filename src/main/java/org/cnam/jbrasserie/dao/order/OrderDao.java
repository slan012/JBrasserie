package org.cnam.jbrasserie.dao.order;

import java.util.List;

import org.cnam.jbrasserie.beans.Order;

public interface OrderDao {
	public List<Order> findAll();
	public Order findById(int id);
	public List<Order> findByClientId(int id);
	void insertOrder(Order order);
}