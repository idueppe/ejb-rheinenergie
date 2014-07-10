package com.rheinenergie.jpa.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

@Entity
public class Betreiber extends AbstractEntity  {
	
	private String name;
	
	@OneToMany(mappedBy="betreiber")
	@OrderBy(value="anlage.name")
	private List<BetreiberBeziehung> beziehungen;

	public List<BetreiberBeziehung> getBeziehungen() {
		return beziehungen;
	}

	public void setBeziehungen(List<BetreiberBeziehung> beziehungen) {
		this.beziehungen = beziehungen;
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Betreiber withName(String name) {
		setName(name);
		return this;
	}

}