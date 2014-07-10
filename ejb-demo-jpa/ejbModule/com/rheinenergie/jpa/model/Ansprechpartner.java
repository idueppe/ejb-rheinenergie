package com.rheinenergie.jpa.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Ansprechpartner extends AbstractEntity {

	@OneToOne(mappedBy = "partner")
	private BetreiberBeziehung beziehung;

	private String name;

	public Anlage getAnlage() {
		return beziehung.getAnlage();
	}

	public Betreiber getBetreiber() {
		return beziehung.getBetreiber();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Ansprechpartner withName(String name) {
		setName(name);
		return this;
	}

	public BetreiberBeziehung getBeziehung() {
		return beziehung;
	}

	public void setBeziehung(BetreiberBeziehung beziehung) {
		this.beziehung = beziehung;
	}

}
