package org.cnam.jbrasserie.observers;

import java.util.ArrayList;
import java.util.List;

public class OrderNotifier {
	
	private static List<OrderObserver> observers = new ArrayList<>();	
	
	public static void addObserver(OrderObserver observer) {
		observers.add(observer);
	}
	
	public static void newOrderSubmitted() {
		for (OrderObserver observer : observers) {
			observer.newOrderSubmitted();
		}
	}
}
