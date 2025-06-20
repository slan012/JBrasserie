package org.cnam.jbrasserie.controlers.client;

import java.util.List;
import org.cnam.jbrasserie.beans.Beer;
import org.cnam.jbrasserie.beans.Order;
import org.cnam.jbrasserie.beans.OrderLine;
import org.cnam.jbrasserie.dao.FactoryDao;
import org.cnam.jbrasserie.dao.beer.BeerDao;
import org.cnam.jbrasserie.exceptions.BeanException;
import org.cnam.jbrasserie.observers.ClientOrderNotifier;
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
		this.beerDao = FactoryDao.getBeerDao();
		this.updateTable();
	}
	
	public void addLineToBasket() {
		int selectedRow = view.getSelectedRow();
		OrderLine line = null;
		if (selectedRow != -1) {
			this.view.clearMessage();
			int quantity = view.getQuantity();
			Order order = Session.getCurrentOrder();
			Beer beer = beerDao.findById((int) beerTableModel.getValueAt(selectedRow, 0));
			// If beer present in order, add quantity
			try {
				if (isBeerPresent(order, beer)) {
					line = order.getLines().get(beerIndex(order, beer));
					line.setQuantity(line.getQuantity() + quantity);
				} else {
					line = new OrderLine();
						line.setBeer(beer);
						line.setQuantity(quantity);
						order.addLine(line);
				}
				ClientOrderNotifier.clientOrderUpdated();
				this.view.showSuccess("Article ajouté au panier");
			} catch (BeanException e) {
				this.view.showError(e.getMessage());
			}
		}
	}
	
	private boolean isBeerPresent(Order order, Beer beer) {
		for (OrderLine line : order.getLines()) {
			if (line.getBeer().getId().equals(beer.getId())) {
				return true;
			}
		}
		return false;
	}
	
	private int beerIndex(Order order, Beer beer) {
		for (OrderLine line : order.getLines()) {
			if (line.getBeer().getId().equals(beer.getId())) {
				return order.getLines().indexOf(line);
			}
		}
		return -1;
	}
	
	public void updateTable() {
		this.beers = beerDao.findAllWithStock();
		this.beerTableModel.update(beers);
	}
}
