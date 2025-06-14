package org.cnam.jbrasserie.controlers.shop;

import java.util.List;
import java.util.Map;

import org.cnam.jbrasserie.beans.Beer;
import org.cnam.jbrasserie.dao.beer.BeerDao;
import org.cnam.jbrasserie.dao.beer.BeerDaoImplDb;
import org.cnam.jbrasserie.tablesModels.BeersTableModel;
import org.cnam.jbrasserie.views.shop.CatalogTab;

public class CatalogControler{
	
	CatalogTab catalogView;
	BeerDao beerDao = new BeerDaoImplDb();
	Beer selectedBeer;
	Beer editedBeer;
	BeersTableModel beerTableModel;
	boolean isNewBeer = false;
	List<Beer> beerList;
		
	public CatalogControler(CatalogTab view, BeersTableModel beerTableModel) {
		this.catalogView = view;
		this.beerTableModel = beerTableModel;
		beerList = beerDao.findAll();
		this.beerTableModel.update(beerList);
	}
	
	// Add / Update
	public void updateChanges() {
		try {
			editedBeer = handleEditedBeer();
			if (!isNewBeer) {
				this.beerDao.update(editedBeer);
			} else {
				this.beerDao.add(editedBeer);
			}
		} catch (NullPointerException e1){
			System.out.println("Veuillez remplir tous les champs");
		} catch (Exception e2) {
			e2.printStackTrace();
			System.out.println("Veuillez remplir tous les champs");
		}
		
		this.catalogView.clearEditPanel();
		this.editedBeer = null;
		this.isNewBeer = false;
		this.catalogView.changeUpdateButtonName(isNewBeer);
		this.catalogView.setTextFieldsState(isNewBeer);
		this.catalogView.setDeleteButtonState(false);
		this.catalogView.setUpdateButtonState(false);
		this.
		updateTable();
	}
	
	public void newEntry() {
		this.isNewBeer = true;
		this.catalogView.clearEditPanel();
		this.catalogView.changeUpdateButtonName(isNewBeer);
		this.catalogView.setTextFieldsState(isNewBeer);
		this.catalogView.setUpdateButtonState(true);
		this.catalogView.setDeleteButtonState(false);
		this.updateTable();
	}
	
	public void deleteEntry() {
		if (catalogView.getSelectedRow() >= 0) {
			this.editedBeer = handleEditedBeer();
			this.beerDao.delete(editedBeer.getId());
			this.catalogView.clearEditPanel();
		} else {
			System.out.println("Sélectionnez une ligne");
		}
		this.updateTable();
	}
	
	private void updateTable() {
		this.editedBeer = null;
		this.beerList = beerDao.findAll();
		this.beerTableModel.update(beerList);
	}
	
	public Beer handleBeerRow(int id) {
		this.selectedBeer = beerDao.findById(id);
		return selectedBeer;
	}
	
	public Beer handleEditedBeer() {
		Map<String, String> editedBeerRaw = catalogView.getEditedBeerData();
		Beer editedBeer = new Beer();
		try {
		if (!this.isNewBeer) {
			editedBeer.setId(Integer.parseInt(editedBeerRaw.get("id")));
		}
		editedBeer.setName(editedBeerRaw.get("name"));
		editedBeer.setBrewer(editedBeerRaw.get("brewer"));
		editedBeer.setStyle(editedBeerRaw.get("style"));
		editedBeer.setAlcohol(Float.parseFloat(editedBeerRaw.get("alcohol")));
		editedBeer.setPrice(Float.parseFloat(editedBeerRaw.get("price")));
		editedBeer.setStock(Integer.parseInt(editedBeerRaw.get("stock")));
		} catch (NumberFormatException e){
			System.err.println("Parsing error");
			e.printStackTrace();
		}
		return editedBeer;
	}
}
