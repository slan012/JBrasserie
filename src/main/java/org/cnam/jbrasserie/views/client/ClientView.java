package org.cnam.jbrasserie.views.client;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;

import org.cnam.jbrasserie.beans.Beer;
import org.cnam.jbrasserie.dao.beer.BeerDao;
import org.cnam.jbrasserie.dao.beer.BeerDaoImplDb;

public class ClientView{
	
	JFrame frame;
	
	public ClientView() {
		buildFrame();
		
	}
	
	private void buildFrame() {
		frame = new JFrame();
		frame.setTitle("JBrasserie Client");
		frame.setSize(800, 600);
		frame.setLocation(900, 400);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		JTabbedPane tabbedPane = new JTabbedPane();
		
		JPanel profilePane = new JPanel();
		JPanel catalogPane = new JPanel();
		catalogPane.setLayout(new BorderLayout());
		JPanel checkoutPane = new JPanel();

		BeersTableModel model = new BeersTableModel();
		
		JTable beerTable = new JTable(model);
		
		
		JScrollPane scrollPane = new JScrollPane(beerTable);
		beerTable.setFillsViewportHeight(true);
		
		catalogPane.add(scrollPane, BorderLayout.NORTH);
		
		
		JLabel label2 = new JLabel("TEXT 2");
		catalogPane.add(label2);
		JLabel label3 = new JLabel("TEXT 3");
		checkoutPane.add(label3);
		
		tabbedPane.addTab("Profil", profilePane);
		tabbedPane.addTab("Catalogue", catalogPane);
		tabbedPane.addTab("Panier", checkoutPane);

		frame.add(tabbedPane);
		
		}
	
	public void display() {
		frame.setVisible(true);
	}
	
	public void setRows(String[][] data) {
		
	};
}
