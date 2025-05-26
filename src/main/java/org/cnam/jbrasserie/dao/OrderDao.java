package org.cnam.jbrasserie.dao;

import java.util.List;

import org.cnam.jbrasserie.beans.Order;

public interface OrderDao {
	public List<Order> findAll();
	public Order findById(int id);
	public Order findByName(String clientName);
	public void updateOrder(int id, Order order);
	void insertOrder(Order order);
}