package com.cg.as.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "bookinginformation")
public class BookingInformation {

	@Id
	@Column(name = "booking_id")
	private String bookingId;

	@Column(name = "user_email")
	private String custEmail;

	@Column(name = "no_of_passengers")
	@NotEmpty(message = "provide passengers seat")
	private int noOfPassengers;

	@Column(name = "class_type")
	@NotEmpty(message = "Provide a class")
	private String classType;

	@Column(name = "total_fare")
	private double totalFare;

	@Column(name = "CreditCard_info")
	@NotEmpty(message = "Provide your credit card detail")
	private String creditcardInfo;

	@Column(name = "src_city")
	@Pattern(regexp = "[A-Za-z]{2,10}", message = "Please provide valid source city")
	@Size(min = 2, max = 10, message = "size of the source between 2 to 10")
	private String srcCity;

	@Column(name = "dest_city")
	@Pattern(regexp = "[A-Za-z]{2,10}", message = "Please provide valid destination city")
	@Size(min = 2, max = 10, message = "size of the destination city between 2 to 10")
	private String destCity;

	@Column(name = "flightNo")
	private String flightNo;

	@Column(name = "booking_date")
	@NotNull(message = "Provide a date please")
	@Future
	private Date bookingDate;

	@Column(name = "travel_date")
	private Date travelDate;

	public BookingInformation() {
		super();
	}

	public BookingInformation(String bookingId, String custEmail,
			int noOfPassengers, String classType, double totalFare,
			String creditcardInfo, String srcCity, String destCity,
			String flightNo, Date bookingDate, Date travelDate) {
		super();
		this.bookingId = bookingId;
		this.custEmail = custEmail;
		this.noOfPassengers = noOfPassengers;
		this.classType = classType;
		this.totalFare = totalFare;
		this.creditcardInfo = creditcardInfo;
		this.srcCity = srcCity;
		this.destCity = destCity;
		this.flightNo = flightNo;
		this.bookingDate = bookingDate;
		this.travelDate = travelDate;
	}

	public String getBookingId() {
		return bookingId;
	}

	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}

	public String getUserEmail() {
		return custEmail;
	}

	public void setUserEmail(String userEmail) {
		this.custEmail = userEmail;
	}

	public int getNoOfPassengers() {
		return noOfPassengers;
	}

	public void setNoOfPassengers(int noOfPassengers) {
		this.noOfPassengers = noOfPassengers;
	}

	public String getClassType() {
		return classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}

	public double getTotalFare() {
		return totalFare;
	}

	public void setTotalFare(double totalFare) {
		this.totalFare = totalFare;
	}

	public String getCreditcardInfo() {
		return creditcardInfo;
	}

	public void setCreditcardInfo(String creditcardInfo) {
		this.creditcardInfo = creditcardInfo;
	}

	public String getSrcCity() {
		return srcCity;
	}

	public void setSrcCity(String srcCity) {
		this.srcCity = srcCity;
	}

	public String getDestCity() {
		return destCity;
	}

	public void setDestCity(String destCity) {
		this.destCity = destCity;
	}

	public String getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}

	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	public Date getTravelDate() {
		return travelDate;
	}

	public void setTravelDate(Date travelDate) {
		this.travelDate = travelDate;
	}

	public void formattedString() {
		System.out
				.format("%15s%20s%15s%10s%15s%20s%20s%15s%15s", bookingId,
						custEmail, noOfPassengers, classType, totalFare,
						creditcardInfo, srcCity, destCity, flightNo,
						bookingDate + "\n");
	}
}
