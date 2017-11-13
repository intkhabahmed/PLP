package com.cg.ars.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Airport")
public class Airport {

	@Id
	@Column(name = "Airportid")
	private int airportId;

	@Column(name = "Airportname")
	private String airpotName;

	@Column(name = "Abbreviation")
	private String abbreviation;

	@Column(name = "Location")
	private String location;

	public Airport() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Airport(int airportId, String airpotName, String abbreviation,
			String location) {
		super();
		this.airportId = airportId;
		this.airpotName = airpotName;
		this.abbreviation = abbreviation;
		this.location = location;
	}

	public int getAirportId() {
		return airportId;
	}

	public void setAirportId(int airportId) {
		this.airportId = airportId;
	}

	public String getAirpotName() {
		return airpotName;
	}

	public void setAirpotName(String airpotName) {
		this.airpotName = airpotName;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "Airport [airportId=" + airportId + ", airpotName=" + airpotName
				+ ", abbreviation=" + abbreviation + ", location=" + location
				+ "]";
	}

}
