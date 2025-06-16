package org.cnam.jbrasserie.controlers.shop;

import java.util.List;
import java.util.Map;

import org.cnam.jbrasserie.beans.Beer;
import org.cnam.jbrasserie.dao.beer.BeerDao;
import org.cnam.jbrasserie.dao.beer.BeerDaoImplDb;
import org.cnam.jbrasserie.exceptions.DaoException;
import org.cnam.jbrasserie.exceptions.FormException;
import org.cnam.jbrasserie.observers.CatalogNotifier;
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
		this.catalogView.clearMessage();
		try {
			editedBeer = handleEditedBeer();
			if (!isNewBeer) {
				this.beerDao.update(editedBeer);
				this.catalogView.showSuccess("Informations mises à jour");
			} else {
				this.beerDao.add(editedBeer);
				this.catalogView.showSuccess("Article enregistré");
			}
			this.catalogView.clearEditPanel();
			this.editedBeer = null;
			this.isNewBeer = false;
			this.catalogView.changeUpdateButtonName(isNewBeer);
			this.catalogView.setTextFieldsState(isNewBeer);
			this.catalogView.setDeleteButtonState(false);
			this.catalogView.setUpdateButtonState(false);
//			this.catalogView.clearMessage();
			updateTable();
			CatalogNotifier.catalogUpdated();
		} catch (FormException e){
			this.catalogView.showError(e.getMessage());
		} 
		
	}
	
	public void newEntry() {
		this.isNewBeer = true;
		this.catalogView.clearMessage();
		this.catalogView.clearEditPanel();
		this.catalogView.changeUpdateButtonName(isNewBeer);
		this.catalogView.setTextFieldsState(isNewBeer);
		this.catalogView.setUpdateButtonState(true);
		this.catalogView.setDeleteButtonState(false);
		this.updateTable();
	}
	
	public void deleteEntry(){
		isNewBeer = false;
		try {
			if (catalogView.getSelectedRow() >= 0) {
				this.editedBeer = handleEditedBeer();
				this.beerDao.delete(editedBeer.getId());
				this.catalogView.clearEditPanel();			
				this.catalogView.showSuccess("Article supprimé");
			} 
			CatalogNotifier.catalogUpdated();
			this.updateTable();
			this.catalogView.setUpdateButtonState(false);
			this.catalogView.setDeleteButtonState(false);
			this.catalogView.setTextFieldsState(false);
		} catch (FormException | DaoException e) {
			this.catalogView.showError("Impossible de supprimer cet article (présent dans une commande)");
		}
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
	
	public Beer handleEditedBeer() throws FormException {
		Map<String, String> editedBeerRaw = catalogView.getEditedBeerData();
		for (Map.Entry<String, String> field : editedBeerRaw.entrySet()) {
			if (!field.getKey().equals("id") && field.getValue().isBlank()) {
					throw new FormException("Tous les champs doivent être remplis");
				}
		}
		Beer editedBeer = new Beer();

		if (!this.isNewBeer) {
			editedBeer.setId(Integer.parseInt(editedBeerRaw.get("id")));
		}
		editedBeer.setName(editedBeerRaw.get("name"));
		editedBeer.setBrewer(editedBeerRaw.get("brewer"));
		editedBeer.setStyle(editedBeerRaw.get("style"));
		try {
			editedBeer.setAlcohol(Float.parseFloat(editedBeerRaw.get("alcohol")));
		} catch (NumberFormatException e) {
			throw new FormException("Le champ \"Alcool\" doit être un nombre");
		}
		try {
			editedBeer.setPrice(Float.parseFloat(editedBeerRaw.get("price")));
		} catch (NumberFormatException e) {
			throw new FormException("Le champ \"Prix\" doit être un nombre");
		}
		try {
			editedBeer.setStock(Integer.parseInt(editedBeerRaw.get("stock")));
		} catch (NumberFormatException e) {
			throw new FormException("Le champ \"Stock\" doit être un nombre entier");
		}
		return editedBeer;
	}
}
