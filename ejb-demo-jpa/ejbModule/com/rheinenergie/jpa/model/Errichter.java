package com.rheinenergie.jpa.model;

import javax.persistence.Entity;

@Entity
//@DiscriminatorValue("E")
public class Errichter extends Kontakt{

	private String errichterInfo;

	public String getErrichterInfo() {
		return errichterInfo;
	}

	public void setErrichterInfo(String errichterInfo) {
		this.errichterInfo = errichterInfo;
	}
}
