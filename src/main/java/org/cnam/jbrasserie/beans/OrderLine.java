package org.cnam.jbrasserie.beans;

public class OrderLine {
	private Integer idOrder;
	private Integer idBeer;
	private Integer quantity;
	
	public Integer getIdOrder() {
		return idOrder;
	}
	public void setIdOrder(Integer idOrder) {
		this.idOrder = idOrder;
	}
	public Integer getIdBeer() {
		return idBeer;
	}
	public void setIdBeer(Integer idBeer) {
		this.idBeer = idBeer;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	
}
