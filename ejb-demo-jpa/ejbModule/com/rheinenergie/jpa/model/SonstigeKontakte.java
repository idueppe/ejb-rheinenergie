package com.rheinenergie.jpa.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
//@IdClass(SonstigePK.class)
public class SonstigeKontakte {
	
	@EmbeddedId
	private SonstigePK pk;

//	@Id
//	@GeneratedValue(strategy=GenerationType.SEQUENCE)
//	private Long idA;
//	@Id
//	@Column(length=40)
//	private String idB;
	
	private String name;

	public Long getIdA() {
		return (pk != null) ? pk.getIdA() : null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdB() {
		return (pk != null) ? pk.getIdB() : null;
	}

	@Override
	public String toString() {
		return "SonstigeKontakte [pk=" + pk + ", name=" + name + "]";
	}

	public SonstigePK getPk() {
		if (pk == null)
			pk = new SonstigePK();
		return pk;
	}

	public void setPk(SonstigePK pk) {
		this.pk = pk;
	}

	
	
	
	
}
