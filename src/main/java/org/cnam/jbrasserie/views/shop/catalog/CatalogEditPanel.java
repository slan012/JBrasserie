package org.cnam.jbrasserie.views.shop.catalog;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JButton;

public class CatalogEditPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public CatalogEditPanel() {

		// Labels
		JLabel nameLabel =   new JLabel("Nom :");
		JLabel brewerLabel = new JLabel("Brasseur :");
		JLabel styleLabel =  new JLabel("Style :");
		JLabel alcoolLabel = new JLabel("Alcool :");
		JLabel priceLabel =  new JLabel("Prix :");
		JLabel stockLabel =  new JLabel("Stock :");
		
		// Fields		
		JTextField nameField =   new JTextField();
		JTextField brewerField = new JTextField();
		JTextField styleField =  new JTextField();
		JTextField alcoolField = new JTextField();
		JTextField priceField =  new JTextField();
		JTextField stockField =  new JTextField();
		
		// Buttons
		JButton submitButton = new JButton("Valider");
		JButton deleteButton = new JButton("Supprimer");
		JButton newButton =    new JButton("Nouveau");
		

		GroupLayout gl = new GroupLayout(this);
		gl.setHorizontalGroup(
			gl.createParallelGroup(Alignment.LEADING)
				.addGroup(gl.createSequentialGroup()
					.addGroup(gl.createParallelGroup(Alignment.LEADING)
						.addComponent(nameLabel)
						.addComponent(brewerLabel)
						.addComponent(styleLabel)
						.addComponent(alcoolLabel)
						.addComponent(priceLabel)
						.addComponent(stockLabel))
					.addGroup(gl.createParallelGroup(Alignment.LEADING)
						.addGroup(gl.createParallelGroup(Alignment.LEADING)
							.addComponent(styleField)
							.addComponent(brewerField)
							.addComponent(nameField)
							.addGroup(gl.createParallelGroup(Alignment.TRAILING)
								.addComponent(priceField, Alignment.LEADING)
								.addComponent(alcoolField, Alignment.LEADING)
								.addComponent(stockField, Alignment.LEADING)))
						.addGroup(gl.createSequentialGroup()
							.addComponent(newButton)
							.addComponent(submitButton)
							.addComponent(deleteButton)))));
		gl.setVerticalGroup(
			gl.createParallelGroup(Alignment.LEADING)
				.addGroup(gl.createSequentialGroup()
					.addGroup(gl.createParallelGroup(Alignment.BASELINE)
						.addComponent(nameLabel)
						.addComponent(nameField))
					.addGroup(gl.createParallelGroup(Alignment.BASELINE)
						.addComponent(brewerLabel)
						.addComponent(brewerField))
					.addGroup(gl.createParallelGroup(Alignment.BASELINE)
						.addComponent(styleLabel)
						.addComponent(styleField))
					.addGroup(gl.createParallelGroup(Alignment.BASELINE)
						.addComponent(alcoolLabel)
						.addComponent(alcoolField))
					.addGroup(gl.createParallelGroup(Alignment.BASELINE)
						.addComponent(priceLabel)
						.addComponent(priceField))
					.addGroup(gl.createParallelGroup(Alignment.BASELINE)
						.addComponent(stockLabel)
						.addComponent(stockField))

					.addGroup(gl.createParallelGroup(Alignment.BASELINE)
						.addComponent(newButton)
						.addComponent(submitButton)
						.addComponent(deleteButton))));
		
		this.setLayout(gl);
		gl.setAutoCreateGaps(true);
		gl.setAutoCreateContainerGaps(true);
		
		
		
		
		
	}
}
