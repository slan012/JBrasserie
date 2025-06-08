package org.cnam.jbrasserie.views.client;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;

public class ClientView{
	private static final long serialVersionUID = 1L;
	JFrame frame;
	
	public ClientView() {
		// Main Frame
		frame = new JFrame();
		frame.setTitle("JBrasserie Shop");
		frame.setSize(800, 600);
		frame.setLocation(900, 400);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		// JTabbedPane
		
		JTabbedPane tabbedPane = new JTabbedPane();
		
		JPanel profilePane = new JPanel();
		JPanel catalogPane = new JPanel();
		JPanel checkoutPane = new JPanel();
		
		tabbedPane.addTab("Profil", profilePane);
		tabbedPane.addTab("Catalogue", catalogPane);
		tabbedPane.addTab("Panier", checkoutPane);

		frame.getContentPane().add(tabbedPane);

	}
	
	public void display() {
		frame.setVisible(true);
	}
	
	public void setRows(String[][] data) {
		
	};
	
}
