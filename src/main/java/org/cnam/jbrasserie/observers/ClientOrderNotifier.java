package org.cnam.jbrasserie.observers;

import java.util.ArrayList;
import java.util.List;

public class ClientOrderNotifier {
	
	private static List<ClientOrderObserver> observers = new ArrayList<>();
	
	private ClientOrderNotifier() {};
	
	public static void addObserver(ClientOrderObserver observer) {
		observers.add(observer);
	}
	
	public static void clientOrderUpdated() {
		for (ClientOrderObserver observer : observers) {
			observer.clientOrdeUpdated();
		}
	}
}
