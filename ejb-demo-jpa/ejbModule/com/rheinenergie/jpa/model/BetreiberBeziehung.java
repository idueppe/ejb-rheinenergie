package com.rheinenergie.jpa.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class BetreiberBeziehung  extends AbstractEntity  {
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Anlage anlage;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private Betreiber betreiber;
	
	@OneToOne(cascade={CascadeType.PERSIST}, orphanRemoval=true)
	private Ansprechpartner partner;
	
	public Anlage getAnlage() {
		return anlage;
	}

	public void setAnlage(Anlage anlage) {
		this.anlage = anlage;
	}

	public Ansprechpartner getPartner() {
		return partner;
	}

	public void setPartner(Ansprechpartner partner) {
		this.partner = partner;
	}

	public Betreiber getBetreiber() {
		return betreiber;
	}

	public void setBetreiber(Betreiber betreiber) {
		this.betreiber = betreiber;
	}
	
}
