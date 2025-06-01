package org.cnam.jbrasserie.views.shop;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;

import org.cnam.jbrasserie.controlers.shop.ClientController;
import org.cnam.jbrasserie.views.common.BeersTableModel;
import org.cnam.jbrasserie.views.common.CatalogPane;
import org.cnam.jbrasserie.views.shop.catalog.CatalogTab;
import org.cnam.jbrasserie.views.shop.clients.ClientTab;

public class ShopView extends JFrame{

	private static final long serialVersionUID = 1L;
	
	JFrame frame;
	
	
	public ShopView() {
		
		buildFrame();
		
	}
	
	private void buildFrame() {
	
		// Main Frame
		frame = new JFrame();
		frame.setTitle("JBrasserie Shop");
		frame.setSize(800, 600);
		frame.setLocation(900, 400);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		// JTabbedPane
		
		JTabbedPane tabbedPane = new JTabbedPane();
		
		JPanel clientPane = new ClientTab();
		JPanel catalogPane = new CatalogTab();
		JPanel checkoutPane = new JPanel();
		
		tabbedPane.addTab("Client", clientPane);
		tabbedPane.addTab("Catalogue", catalogPane);
		tabbedPane.addTab("Commandes", checkoutPane);

		frame.getContentPane().add(tabbedPane);
		

//		BeersTableModel model = new BeersTableModel();
//			
//		JTable beerTable = new JTable(model);
//		
//		
//		JScrollPane scrollPane = new JScrollPane(beerTable);
//		beerTable.setFillsViewportHeight(true);
//		
//		catalogPane.add(scrollPane, BorderLayout.NORTH);
//		
//		
//		JLabel label2 = new JLabel("Commandes");
//		catalogPane.add(label2);
//		JLabel label3 = new JLabel("Clients");
//		checkoutPane.add(label3);
		

		
		}
	
	public void display() {
		frame.setVisible(true);
	}
	
	public void setRows(String[][] data) {
		
	};
}
