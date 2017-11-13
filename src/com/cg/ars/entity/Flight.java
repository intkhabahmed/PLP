package com.cg.ars.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "flightinformation")
public class Flight {

	@Id
	@Column(name = "Flightno")
	private String flightNo;

	@Column(name = "Airline")
	private String flightName;

	@Column(name = "Dep_city")
	private String deptCity;

	@Column(name = "Arr_city")
	private String arrCity;

	@Column(name = "Dep_date")
	private Date deptDate;

	@Column(name = "Arr_date")
	private Date arrDate;

	@Column(name = "Dep_time")
	private String deptTime;

	@Column(name = "Arr_time")
	private String arrTime;

	@Column(name = "FirstSeats")
	private int firstSeats;

	@Column(name = "FirstSeatFare")
	private double firstSeatsFare;

	@Column(name = "BussSeats")
	private int bussSeats;

	@Column(name = "BussSeatsFare")
	private double bussSeatsFare;

	@Column(name = "duration")
	private String duration;

	public Flight(String flightNo, String flightName, String deptCity,
			String arrCity, Date deptDate, Date arrDate, String deptTime,
			String arrTime, int firstSeats, double firstSeatsFare,
			int bussSeats, double bussSeatsFare, String duration) {
		super();
		this.flightNo = flightNo;
		this.flightName = flightName;
		this.arrCity = arrCity;
		this.deptCity = deptCity;
		this.arrDate = arrDate;
		this.deptDate = deptDate;
		this.arrTime = arrTime;
		this.deptTime = deptTime;
		this.firstSeats = firstSeats;
		this.firstSeatsFare = firstSeatsFare;
		this.bussSeats = bussSeats;
		this.duration = duration;
		this.bussSeatsFare = bussSeatsFare;
	}

	public Flight() {
		System.out.println("Empty constructor is called");
	}

	public String getFlightNo() {
		return flightNo;
	}

	public String getFlightName() {
		return flightName;
	}

	public String getArrCity() {
		return arrCity;
	}

	public String getDeptCity() {
		return deptCity;
	}

	public Date getArrDate() {
		return arrDate;
	}

	public Date getDeptDate() {
		return deptDate;
	}

	public String getArrTime() {
		return arrTime;
	}

	public String getDeptTime() {
		return deptTime;
	}

	public int getFirstSeats() {
		return firstSeats;
	}

	public double getFirstSeatsFare() {
		return firstSeatsFare;
	}

	public int getBussSeats() {
		return bussSeats;
	}

	public double getBussSeatsFare() {
		return bussSeatsFare;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public void formattedString() {
		System.out.format("%10s%10s%10s%10s%30s%30s%10s%10s%15s%20s%10s%15s",
				flightNo, flightName, deptCity, arrCity, arrDate, deptDate,
				arrTime, deptTime, firstSeats, firstSeatsFare, bussSeats,
				bussSeatsFare + "\n");
	}

	@Override
	public String toString() {
		return "Flight [flightNo=" + flightNo + ", flightName=" + flightName
				+ ", deptCity=" + deptCity + ", arrCity=" + arrCity
				+ ", deptDate=" + deptDate + ", arrDate=" + arrDate
				+ ", deptTime=" + deptTime + ", arrTime=" + arrTime
				+ ", firstSeats=" + firstSeats + ", firstSeatsFare="
				+ firstSeatsFare + ", bussSeats=" + bussSeats
				+ ", bussSeatsFare=" + bussSeatsFare + "]";
	}

}
