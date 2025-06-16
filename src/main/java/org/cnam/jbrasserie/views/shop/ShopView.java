package org.cnam.jbrasserie.views.shop;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;

public class ShopView extends JFrame {

	private static final long serialVersionUID = 1L;
	
	JFrame frame;
	
	public ShopView() {
		
		// Main Frame
		frame = new JFrame();
		frame.setTitle("JBrasserie Shop");
		frame.setSize(800, 600);
		frame.setLocation(850, 400);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		// JTabbedPane
		
		JTabbedPane tabbedPane = new JTabbedPane();
		
		JPanel clientPane = new ClientTab();
		JPanel catalogPane = new CatalogTab();
		JPanel orderPane = new OrderTab();
		
		tabbedPane.addTab("Clients", clientPane);
		tabbedPane.addTab("Catalogue", catalogPane);
		tabbedPane.addTab("Commandes", orderPane);

		frame.getContentPane().add(tabbedPane);
	}
	
	public void display() {
		frame.setVisible(true);
	}
}
