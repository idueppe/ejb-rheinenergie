package com.rheinenergie.jpa.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Anlage extends AbstractEntity {

	private String name;

	@OneToMany(mappedBy = "anlage", 
			cascade={
				CascadeType.PERSIST,	//
				CascadeType.MERGE,		//
				CascadeType.REMOVE,		//
				CascadeType.REFRESH })
	private List<BetreiberBeziehung> betreiberBeziehungen = new ArrayList<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Anlage withName(String name) {
		setName(name);
		return this;
	}

	public List<BetreiberBeziehung> getBetreiberBeziehungen() {
		return betreiberBeziehungen;
	}

	public void setBetreiberBeziehungen(
			List<BetreiberBeziehung> betreiberBeziehungen) {
		this.betreiberBeziehungen = betreiberBeziehungen;
	}

}
