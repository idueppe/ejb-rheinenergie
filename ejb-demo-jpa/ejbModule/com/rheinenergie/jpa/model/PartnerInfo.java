package com.rheinenergie.jpa.model;

public class PartnerInfo {

	private Long id;
	private String partnerName;
	private String betreiberName;


	public PartnerInfo() {
	}

	public PartnerInfo(
			Long id, 
			String partnerName, 
			String betreiberName) {
		this.id = id;
		this.partnerName = partnerName;
		this.betreiberName = betreiberName;
	}

	public String getPartnerName() {
		return partnerName;
	}

	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}

	public String getBetreiberName() {
		return betreiberName;
	}

	public void setBetreiberName(String betreiberName) {
		this.betreiberName = betreiberName;
	}

	@Override
	public String toString() {
		return "PartnerInfo [id=" + id + ", partnerName=" + partnerName
				+ ", betreiberName=" + betreiberName + "]";
	}

}
