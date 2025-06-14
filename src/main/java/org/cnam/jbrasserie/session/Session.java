package org.cnam.jbrasserie.session;

import org.cnam.jbrasserie.beans.Client;
import org.cnam.jbrasserie.beans.Order;

public class Session{

	private static Client currentUser;
	private static Order currentOrder;
	
	private Session() {}
	
	public static Client getCurrentUser() {
		return Session.currentUser;
	}
	
	public static void setCurrentUser(Client currentUser) {
		Session.currentUser = currentUser;
	}
	
	public static Order getCurrentOrder() {
		return currentOrder;
	}
	
	public static void setCurrentOrder(Order currentOrder) {
		Session.currentOrder = currentOrder;
	}
	
	public static void setNewOrder() {
		Session.currentOrder = new Order();
	}
}
