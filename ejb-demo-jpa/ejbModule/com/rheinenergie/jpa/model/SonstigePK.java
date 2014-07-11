package com.rheinenergie.jpa.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class SonstigePK implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long idA;
	
	private String idB;
	
	public SonstigePK() {}

	public SonstigePK(Long idA, String idB) {
		super();
		this.idA = idA;
		this.idB = idB;
	}

	public Long getIdA() {
		return idA;
	}

	public void setIdA(Long idA) {
		this.idA = idA;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idA == null) ? 0 : idA.hashCode());
		result = prime * result + ((idB == null) ? 0 : idB.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SonstigePK other = (SonstigePK) obj;
		if (idA == null) {
			if (other.idA != null)
				return false;
		} else if (!idA.equals(other.idA))
			return false;
		if (idB == null) {
			if (other.idB != null)
				return false;
		} else if (!idB.equals(other.idB))
			return false;
		return true;
	}

	public String getIdB() {
		return idB;
	}

	public void setIdB(String idB) {
		this.idB = idB;
	}

}
