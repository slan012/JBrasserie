package org.cnam.jbrasserie.controlers.client;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import org.cnam.jbrasserie.beans.OrderLine;
import org.cnam.jbrasserie.dao.order.OrderDao;
import org.cnam.jbrasserie.dao.order.OrderDaoImplDb;
import org.cnam.jbrasserie.observers.CatalogNotifier;
import org.cnam.jbrasserie.observers.OrderNotifier;
import org.cnam.jbrasserie.session.Session;
import org.cnam.jbrasserie.tablesModels.OrderLinesTableModel;
import org.cnam.jbrasserie.views.client.BasketTab;

public class BasketControler {

	private BasketTab view;
	private OrderLinesTableModel basketTableModel;
	private OrderDao orderDao = new OrderDaoImplDb();
	
	public BasketControler(BasketTab view, OrderLinesTableModel tableModel) {
		this.view = view;
		this.basketTableModel = tableModel;
	}
	
	public void confirmCommand() {
		if (!Session.getCurrentOrder().getLines().isEmpty()) {
			Session.getCurrentOrder().setClient(Session.getCurrentUser());
			orderDao.insertOrder(Session.getCurrentOrder());
			OrderNotifier.newOrderSubmitted();
			CatalogNotifier.catalogUpdated();
			this.view.showSuccess("Commande validée !");
			basketTableModel.init();
			Session.getCurrentOrder().setLines(new ArrayList<>());
		} else {
			this.view.showError("Panier vide!");
		}
	}
	
	public void deleteLine() {
		int selectedLine = view.getSelectedRow();
		if (selectedLine != -1) {
			Session.getCurrentOrder().removeLine(selectedLine);
			updateTable();
		}
	}
	
	public void emptyBasket() {
		Session.getCurrentOrder().setLines(new ArrayList<>());
		updateTable();
	}


	public void updateTable() {
		boolean buttonsEnabled = false;
		basketTableModel.update(Session.getCurrentOrder().getLines());
		if (!Session.getCurrentOrder().getLines().isEmpty()) {
			buttonsEnabled = true;
			this.view.clearMessage();
		}
		this.view.setButtonsState(buttonsEnabled);
	}
}
