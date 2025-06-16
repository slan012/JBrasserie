package org.cnam.jbrasserie;

import org.cnam.jbrasserie.views.client.ClientView;
import org.cnam.jbrasserie.views.shop.ShopView;


public class App {
    public static void main(String[] args) {
    	ShopView shopView = new ShopView();
    	shopView.display();
 
    	ClientView clientView = new ClientView();
    	clientView.display();
    }
}
