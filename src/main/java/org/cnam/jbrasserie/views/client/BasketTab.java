package org.cnam.jbrasserie.views.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.cnam.jbrasserie.controlers.client.BasketControler;
import org.cnam.jbrasserie.observers.ClientOrderNotifier;
import org.cnam.jbrasserie.observers.ClientOrderObserver;
import org.cnam.jbrasserie.tablesModels.OrderLinesTableModel;

@SuppressWarnings("serial")
public class BasketTab extends JPanel implements ClientOrderObserver{
	
	private JTable basketTable;
	private OrderLinesTableModel basketTableModel;
	private BasketControler basketControler;
	
	JPanel buttonsPanel;
	
	JButton deleteButton;
	JButton submitButton;
	JButton emptyButton;
	JLabel message;
	
	public BasketTab() {
		
		basketTableModel = new OrderLinesTableModel();
		basketTableModel.init();
		basketTable = new JTable(basketTableModel);
		basketControler = new BasketControler(this, basketTableModel);
		ClientOrderNotifier.addObserver(this);
		
		// Table
		
		basketTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane basketScrollPane = new JScrollPane(basketTable);
		basketTable. setFillsViewportHeight(true);
		
		// Buttons
		 this.submitButton = new JButton(new AbstractAction("Confirmer la commande") {
			public void actionPerformed(ActionEvent e) {
				 basketControler.confirmCommand();
			 }
		 });
		 
		 this.deleteButton = new JButton(new AbstractAction("Supprimer article") {
				public void actionPerformed(ActionEvent e) {
					 basketControler.deleteLine();
				 }
			 });
		 this.emptyButton = new JButton(new AbstractAction("Vider le panier") {
				public void actionPerformed(ActionEvent e) {
					 basketControler.emptyBasket();
				 }
			 });
		 
		 this.setButtonsState(false);
		 this.setDeleteButtonState(false);
		
		// Buttons panel
			
		buttonsPanel = new JPanel();
		GroupLayout gl = new GroupLayout(this.buttonsPanel);
		this.buttonsPanel.setLayout(gl);
		
		message = new JLabel(" ");
		
		JPanel container = new JPanel(new FlowLayout(FlowLayout.CENTER));
		container.add(buttonsPanel);
		
		gl.setHorizontalGroup(
				gl.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addGroup(gl.createSequentialGroup()
						.addComponent(submitButton)
						.addComponent(deleteButton)
						.addComponent(emptyButton))
					.addComponent(this.message));
		
		gl.setVerticalGroup(
				gl.createSequentialGroup()
					.addGroup(gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(submitButton)
						.addComponent(deleteButton)
						.addComponent(emptyButton))
					.addComponent(message));
		
		gl.setAutoCreateGaps(true);
		gl.setAutoCreateContainerGaps(true);
		
		// Layout
				 
		this.setLayout(new BorderLayout());
		
		this.add(basketScrollPane, BorderLayout.NORTH);
		this.add(container, BorderLayout.CENTER);
		
		basketTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				setDeleteButtonState(true);
				clearMessage();
			}
		});
		
	}
	
	public int getSelectedRow() {
		int selectedRow = basketTable.getSelectedRow();
		if (selectedRow >= 0 && selectedRow < basketTableModel.getRowCount()) {
			return basketTable.convertRowIndexToModel(selectedRow);
		}
		return selectedRow;
	}
	
	public void setButtonsState(boolean state) {
		this.submitButton.setEnabled(state);
		this.emptyButton.setEnabled(state);
	}
	
	public void setDeleteButtonState(boolean state) {
		this.deleteButton.setEnabled(state);
	}
	
	public void showError(String message) {
		this.message.setForeground(Color.RED);
		showMessage(message);
	}
	
	public void showSuccess(String message) {
		this.message.setForeground(Color.BLUE);
		showMessage(message);
	}
	
	private void showMessage(String message) {
		this.clearMessage();
		this.message.setText(message);
	}
	
	public void clearMessage() {
		this.message.setText(" ");
	}

	@Override
	public void clientOrderUpdated() {
		this.basketControler.updateTable();
		
	}
}
