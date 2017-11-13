package com.cg.ars.utility;

public interface QueryMapper {
	public String query1 = "SELECT f FROM Flight f WHERE f.arrCity=:arrCity";
	public String query2 = "SELECT f FROM Flight f WHERE f.deptDate=:deptDate";
	public String query3 = "SELECT f FROM Flight f WHERE f.deptCity=:deptCity AND f.arrCity=:arrCity";
	public String query4 = "SELECT f FROM Flight f WHERE f.flightNo=:flightNo";
	public String query5 = "SELECT f FROM Flight f";
	public String query6 = "SELECT f FROM Flight f where f.deptCity=:deptCity AND f.arrCity=:arrCity AND f.deptDate=:deptDate";
	public String query7 = "SELECT b FROM BookingInformation b WHERE b.flightNo=:flightNo";
	public String query8 = "SELECT u FROM User u WHERE u.username=:username";
	public String query9 = "SELECT b FROM BookingInformation b WHERE b.userEmail=:email";
	public String query10 = "SELECT u FROM User u WHERE u.username=:user AND u.password=:pass";
	public String query11 = "SELECT f.firstSeats FROM Flight f where f.flightNo=:flightNo";
	public String query12 = "SELECT f.bussSeats FROM Flight f where f.flightNo=:flightNo";
	public String query13 = "SELECT f.noOfPassengers FROM BookingInformation f where f.flightNo=:flightNo AND f.classType='first'";
	public String query14 = "SELECT f.noOfPassengers FROM BookingInformation f where f.flightNo=:flightNo AND f.classType='business'";
	public String query15 = "Update User u Set u.password where u.username=:username";
	public String query16 = "Select u.username from User u where u.username=:query";
	public String query17 = "Select u.email from User u where u.email=:query";
}
