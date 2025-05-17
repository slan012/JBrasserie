package org.cnam.jbrasserie.dao;

import java.util.List;

import org.cnam.jbrasserie.beans.Order;

public interface OrderDao {
	public List<Order> findAll();
	public Order findByName(String clientName);
	public Order findById(String clientName);
	public void createOrder(Order order);
	public void updateOrder(int id, Order order);
}