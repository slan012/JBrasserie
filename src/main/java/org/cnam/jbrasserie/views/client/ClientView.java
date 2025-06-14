package org.cnam.jbrasserie.views.client;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;

public class ClientView{
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	JPanel profilePane;
	JPanel catalogPane;
	JPanel checkoutPane;
	JTabbedPane tabbedPane;
	
	public ClientView() {
		
		// Main Frame
		frame = new JFrame();
		frame.setTitle("JBrasserie Client");
		frame.setSize(800, 600);
		frame.setLocation(300,100);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		// JTabbedPane
		
		this.tabbedPane = new JTabbedPane();
		
		this.profilePane = new ProfileTab(this);
		this.tabbedPane.addTab("Profil", profilePane);
		
		frame.getContentPane().add(tabbedPane);
	}
	
	public void display() {
		frame.setVisible(true);
	}
	
	public boolean isTabsEnabled() {
		return this.catalogPane != null && this.tabbedPane != null;
	}
	
	public void newTabs() {
		this.tabbedPane.remove(checkoutPane);
		this.tabbedPane.remove(catalogPane);
		this.catalogPane = new ClientCatalogTab();
		this.checkoutPane = new BasketTab();
		this.tabbedPane.addTab("Catalogue", catalogPane);
		this.tabbedPane.addTab("Panier", checkoutPane);
	}
}