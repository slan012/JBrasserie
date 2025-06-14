package org.cnam.jbrasserie.controlers.client;

import java.util.List;

import org.cnam.jbrasserie.beans.Beer;
import org.cnam.jbrasserie.beans.Order;
import org.cnam.jbrasserie.beans.OrderLine;
import org.cnam.jbrasserie.dao.beer.BeerDao;
import org.cnam.jbrasserie.dao.beer.BeerDaoImplDb;
import org.cnam.jbrasserie.session.Session;
import org.cnam.jbrasserie.tablesModels.ClientBeersTableModel;
import org.cnam.jbrasserie.views.client.ClientCatalogTab;

public class ClientCatalogControler {

	private ClientCatalogTab view;
	private ClientBeersTableModel beerTableModel;
	private BeerDao beerDao;
	private List<Beer> beers;
	
	public ClientCatalogControler(ClientCatalogTab clientCatalogTab, ClientBeersTableModel beerTableModel) {
		this.view = clientCatalogTab;
		this.beerTableModel = beerTableModel;
		this.beerDao = new BeerDaoImplDb();
		this.beers = beerDao.findAll();
		this.beerTableModel.update(beers);
	}
	
	public void addLineToBasket() {
		int selectedRow = view.getSelectedRow();
		if (selectedRow != -1) {
			int quantity = view.getQuantity();
			Order order = Session.getCurrentOrder();
			Beer beer = beerDao.findById((int) beerTableModel.getValueAt(selectedRow, 0));
			System.out.print("Order :" + order);
			OrderLine orderLine = new OrderLine();
			try {
				orderLine.setBeer(beer);
				orderLine.setQuantity(quantity);
				order.addLine(orderLine);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}
