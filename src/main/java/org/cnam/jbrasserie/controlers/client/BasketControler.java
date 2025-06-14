package org.cnam.jbrasserie.controlers.client;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import org.cnam.jbrasserie.beans.OrderLine;
import org.cnam.jbrasserie.dao.order.OrderDao;
import org.cnam.jbrasserie.dao.order.OrderDaoImplDb;
import org.cnam.jbrasserie.observers.OrderNotifier;
import org.cnam.jbrasserie.session.Session;
import org.cnam.jbrasserie.tablesModels.OrderLinesTableModel;
import org.cnam.jbrasserie.views.client.BasketTab;

public class BasketControler implements PropertyChangeListener {

	private BasketTab view;
	private OrderLinesTableModel basketTableModel;
	private OrderDao orderDao = new OrderDaoImplDb();
	
	public BasketControler(BasketTab view, OrderLinesTableModel tableModel) {
		this.view = view;
		this.basketTableModel = tableModel;
		Session.getCurrentOrder().addPropertyChangeListener(this);
		System.out.println(Session.getCurrentOrder());
	}
	
	public void confirmCommand() {
		if (!Session.getCurrentOrder().getLines().isEmpty()) {
			System.out.println("Valider");
			Session.getCurrentOrder().setClient(Session.getCurrentUser());
			orderDao.insertOrder(Session.getCurrentOrder());
			OrderNotifier.newOrderSubmitted();
			basketTableModel.init();
			Session.getCurrentOrder().setLines(new ArrayList<>());
		} else {
			System.out.println("Panier vide !");
		}
	}
	
	public void deleteLine() {
		int selectedLine = view.getSelectedRow();
		if (selectedLine != -1) {
			Session.getCurrentOrder().removeLine(selectedLine);;
		}
	}
	
	public void emptyBasket() {
		Session.getCurrentOrder().setLines(new ArrayList<>());
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		boolean buttonsEnabled = false;
		basketTableModel.update((List<OrderLine>) evt.getNewValue());
		if (!Session.getCurrentOrder().getLines().isEmpty()) {
			buttonsEnabled = true;
		}
		this.view.setButtonsState(buttonsEnabled);
	}

}
