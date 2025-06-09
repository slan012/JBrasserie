package org.cnam.jbrasserie.views.client;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;

import org.cnam.jbrasserie.views.client.catalog.ClientCatalogTab;
import org.cnam.jbrasserie.views.client.profile.ProfileTab;

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
		frame.setTitle("JBrasserie Shop");
		frame.setSize(800, 600);
		frame.setLocation(900, 400);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		// JTabbedPane
		
		this.tabbedPane = new JTabbedPane();
		
		this.profilePane = new ProfileTab();
		this.catalogPane = new ClientCatalogTab();
		this.checkoutPane = new JPanel();
		
		this.tabbedPane.addTab("Profil", profilePane);
		this.tabbedPane.addTab("Catalogue", catalogPane);
		this.tabbedPane.addTab("Panier", checkoutPane);
		
		frame.getContentPane().add(tabbedPane);

	}
	
	public void display() {
		frame.setVisible(true);
	}
	
	public void enableTabs() {
		this.tabbedPane.setEnabledAt(tabbedPane.indexOfComponent(catalogPane), false);
		this.tabbedPane.setEnabledAt(tabbedPane.indexOfComponent(checkoutPane), false);
	}
	
}
