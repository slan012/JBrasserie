package org.cnam.jbrasserie.observers;

import java.util.ArrayList;
import java.util.List;

public class CatalogNotifier {
	
	private static List<CatalogObserver> catalogObservers = new ArrayList<>();
	
	public static void addObserver(CatalogObserver observer) {
		catalogObservers.add(observer);
	}
	
	public static void catalogUpdated() {
		for (CatalogObserver observer : catalogObservers) {
			observer.catalogUpdated();
		}
	}
}
