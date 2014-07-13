package com.rheinenergie.jpa.model;

import javax.persistence.Embeddable;

@Embeddable
public class ContactData {

	private String webAddress;
	private String telephone;
	private String telefax;
	private String mobile;

	public String getWebAddress() {
		return webAddress;
	}

	public void setWebAddress(String webAddress) {
		this.webAddress = webAddress;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getTelefax() {
		return telefax;
	}

	public void setTelefax(String telefax) {
		this.telefax = telefax;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String email) {
		this.mobile = email;
	}

}
