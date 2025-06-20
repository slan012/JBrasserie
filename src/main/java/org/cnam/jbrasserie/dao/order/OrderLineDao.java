package org.cnam.jbrasserie.dao.order;

import java.util.List;

import org.cnam.jbrasserie.beans.OrderLine;

public interface OrderLineDao {
	public List<OrderLine> findOrderLineById(Integer idOrder);
}
