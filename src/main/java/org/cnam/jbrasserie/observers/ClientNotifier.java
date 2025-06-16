package org.cnam.jbrasserie.observers;

import java.util.ArrayList;
import java.util.List;

public class ClientNotifier {
	
	private static List<ClientObserver> clientObservers = new ArrayList<>();
	
	public static void addObserver(ClientObserver observer) {
		clientObservers.add(observer);
	}
	
	public static void clientUpdated() {
		for (ClientObserver observer : clientObservers) {
			observer.clientUpdated();
		}
	}
}
