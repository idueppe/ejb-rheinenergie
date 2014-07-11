package com.rheinenergie.jpa.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(
//		uniqueConstraints=@UniqueConstraint(columnNames={"name","description"})
		)
@SecondaryTables(
		{@SecondaryTable(name="ANLAGE_DESCRIPTION"),
		 @SecondaryTable(name="ANLAGE_IMAGE")}
)
public class Anlage extends AbstractEntity {

	private String name;
	
	@Column(table="ANLAGE_DESCRIPTION")
	private String description;
	
	@Column(table="ANLAGE_IMAGE")
	private byte[] image;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

}
