package org.cnam.jbrasserie.views.shop.catalog;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import org.cnam.jbrasserie.views.common.CatalogPane;

public class CatalogTab extends CatalogPane {
	
	private static final long serialVersionUID = 1L;

	public CatalogTab() {
		super();
		
		JPanel beerEdit = new CatalogEditPanel();

		this.add(beerEdit, BorderLayout.SOUTH);
	}
}
