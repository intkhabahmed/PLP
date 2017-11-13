package com.cg.ars.utility;

public interface QueryMapper {
	public static String query1 = "SELECT f FROM Flight f WHERE f.arrCity=:arrCity";
	public static String query2 = "SELECT f FROM Flight f WHERE f.deptDate=:deptDate";
	public static String query3 = "SELECT f FROM Flight f WHERE f.deptCity=:deptCity AND f.arrCity=:arrCity";
	public static String query4 = "SELECT f FROM Flight f WHERE f.flightNo=:flightNo";
	public static String query5 = "SELECT f FROM Flight f";
	public static String query6 = "SELECT f FROM Flight f where f.deptCity=:deptCity AND f.arrCity=:arrCity AND f.deptDate=:deptDate";
	public static String query7 = "SELECT b FROM BookingInformation b WHERE b.flightNo=:flightNo";
	public static String query8 = "SELECT u FROM User u WHERE u.username=:username";
	public static String query9 = "SELECT b FROM BookingInformation b WHERE b.userEmail=:email";
	public static String query10 = "SELECT u FROM User u WHERE u.username=:user AND u.password=:pass";
	public static String query11 = "SELECT f.firstSeats FROM Flight f where f.flightNo=:flightNo";
	public static String query12 = "SELECT f.bussSeats FROM Flight f where f.flightNo=:flightNo";
	public static String query13 = "SELECT f.noOfPassengers FROM BookingInformation f where f.flightNo=:flightNo AND f.classType='first'";
	public static String query14 = "SELECT f.noOfPassengers FROM BookingInformation f where f.flightNo=:flightNo AND f.classType='business'";
	public static String query15 = "Update User u Set u.password where u.username=:username";
	public static String query16 = "Select u.username from User u where u.username=:query";
	public static String query17 = "Select u.email from User u where u.email=:query";
}
