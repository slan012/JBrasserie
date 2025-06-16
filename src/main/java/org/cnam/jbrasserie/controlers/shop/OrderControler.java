package org.cnam.jbrasserie.controlers.shop;

import java.util.List;

import org.cnam.jbrasserie.beans.Order;
import org.cnam.jbrasserie.beans.OrderLine;
import org.cnam.jbrasserie.dao.order.OrderDao;
import org.cnam.jbrasserie.dao.order.OrderDaoImplDb;
import org.cnam.jbrasserie.dao.order.OrderLineDao;
import org.cnam.jbrasserie.dao.order.OrderLineDaoImplDb;
import org.cnam.jbrasserie.tablesModels.OrderLinesTableModel;
import org.cnam.jbrasserie.tablesModels.OrderTableModel;
import org.cnam.jbrasserie.views.shop.OrderTab;

public class OrderControler {

	OrderTab orderView;
	OrderDao orderDao = new OrderDaoImplDb();
	OrderLineDao orderLineDao = new OrderLineDaoImplDb();
	private OrderTableModel orderTableModel;
	private OrderLinesTableModel orderLinesTableModel;
	List<Order> orders;
	List<OrderLine> orderLines;
		
	public OrderControler(OrderTab view, OrderTableModel orderTableModel, OrderLinesTableModel orderLineTableModel) {
		this.orderView = view;
		this.orderTableModel = orderTableModel;
		orders = orderDao.findAll();
		this.orderTableModel.update(orders);

		this.orderLinesTableModel = orderLineTableModel;
		this.orderLinesTableModel.init();
	}
	
	public List<OrderLine> getOrderLines(int idOrder) {
		orderLines = orderLineDao.findOrderLineById(idOrder);
		return orderLines;
	}
	
	public void reloadOrders(){
		orders = orderDao.findAll();
		this.orderTableModel.update(orders);
	}
}
