package com.rheinenergie.jpa.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@Entity
public class Ansprechpartner extends AbstractEntity {

	@OneToOne(mappedBy = "partner", fetch=FetchType.EAGER)
	private BetreiberBeziehung beziehung;

	private String name;
	
	@Embedded
	@AttributeOverrides(
			@AttributeOverride(
					name="webAddress",
					column=@Column(name="web_addr"))
	)
	private ContactData address;

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
