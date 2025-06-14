package org.cnam.jbrasserie.controlers.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class BasketControler implements ActionListener, PropertyChangeListener {

	private BasketTab view;
	private OrderLinesTableModel basketTableModel;
	private OrderDao orderDao = new OrderDaoImplDb();	
	
	public BasketControler(BasketTab view, OrderLinesTableModel tableModel) {
		this.view = view;
		this.basketTableModel = tableModel;
		Session.getCurrentOrder().addPropertyChangeListener(this);
		System.out.println(Session.getCurrentOrder());
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Valider la commande":
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
			break;
		
		case "Supprimer" :
			int selectedLine = view.getSelectedRow();
			if (selectedLine != -1) {
				List<OrderLine> orderLines = Session.getCurrentOrder().getLines();
				orderLines.remove(selectedLine);
				basketTableModel.update(orderLines);
			}
			break;
			
		case "Vider le panier" :
			basketTableModel.init();
			Session.getCurrentOrder().setLines(new ArrayList<>());
			break;
		
		default:
			throw new IllegalArgumentException("Unexpected value: " + e.getActionCommand());
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		basketTableModel.update((List<OrderLine>) evt.getNewValue());
	}

}
