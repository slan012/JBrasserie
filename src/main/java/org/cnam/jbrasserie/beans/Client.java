package org.cnam.jbrasserie.beans;

import org.cnam.jbrasserie.exceptions.BeanException;

public class Client {
	private Integer id;
	private String firstName;
	private String lastName;
	private String adress;
	private Integer zipCode;
	private String city;
	private String phone;
	
	public Integer getId() {
		return id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public Integer getZipCode() {
		return zipCode;
	}
	public void setZipCode(Integer zipCode) {
		this.zipCode = zipCode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) throws BeanException {
		if (phone.matches("^[0-9]+$")) {
			this.phone = phone;
		} else {
			throw new BeanException("Le numéro de téléphone doit être un nombre");
		}
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
	
}
