package org.cnam.jbrasserie.controlers.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import org.cnam.jbrasserie.beans.Beer;
import org.cnam.jbrasserie.beans.Order;
import org.cnam.jbrasserie.beans.OrderLine;
import org.cnam.jbrasserie.dao.beer.BeerDao;
import org.cnam.jbrasserie.dao.beer.BeerDaoImplDb;
import org.cnam.jbrasserie.session.Session;
import org.cnam.jbrasserie.views.client.catalog.ClientBeersTableModel;
import org.cnam.jbrasserie.views.client.catalog.ClientCatalogTab;

public class ClientCatalogControler implements ActionListener {

	private ClientCatalogTab view;
	private ClientBeersTableModel beerTableModel;
	private BeerDao beerDao;
	private List<Beer> beers;
	private Order order;


	public ClientCatalogControler(ClientCatalogTab clientCatalogTab, ClientBeersTableModel beerTableModel) {
		this.view = clientCatalogTab;
		this.beerTableModel = beerTableModel;
		this.beerDao = new BeerDaoImplDb();
		this.beers = beerDao.findAll();
		this.beerTableModel.update(beers);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (Session.getCurrentUser() != null) {
			if (Session.getCurrentOrder() == null) {
				order = new Order();
				Session.setCurrentOrder(order);
			} else {
				order = Session.getCurrentOrder();
			}
			int selectedRow = view.getSelectedRow();
			int quantity = view.getQuantity();
			
			Beer beer = beerDao.findById((int) beerTableModel.getValueAt(selectedRow, 0));
			
			OrderLine orderLine = new OrderLine();
			try {
				orderLine.setBeer(beer);
				orderLine.setQuantity(quantity);
				order.addLine(orderLine);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
			System.out.println(Session.getCurrentUser().getId());
		} else {
			System.out.println("Aucun utilisateur connecté");
		}
		for (OrderLine line : order.getLines()) {
			System.out.println("Beer : " + line.getBeer().getName());
		}

	}
}

