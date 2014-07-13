package com.rheinenergie.jpa.model;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;

@Entity
public class BetreiberGruppe extends AbstractEntity {
	
	@OneToMany(cascade=CascadeType.PERSIST)
	@MapKey(name="name")
	private Map<String, Betreiber> betreiberMap;
	
	private String name;

	public Map<String, Betreiber> getBetreiberMap() {
		if (betreiberMap == null)
			betreiberMap = new HashMap<>();
		return betreiberMap;
	}

	public void setBetreiberMap(Map<String, Betreiber> betreiberMap) {
		this.betreiberMap = betreiberMap;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public BetreiberGruppe withName(String name) {
		setName(name);
		return this;
	}
	
	
	
}
