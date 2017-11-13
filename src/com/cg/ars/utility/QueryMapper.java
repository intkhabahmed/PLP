package com.cg.ars.utility;

public interface QueryMapper {
	public static String searchFlightByArrivalCity = "SELECT f FROM Flight f WHERE f.arrCity=:arrCity";
	public static String searchFlightByDepartureDate = "SELECT f FROM Flight f WHERE f.deptDate=:deptDate";
	public static String searchFlightByDepartureAndArrivalCity = "SELECT f FROM Flight f WHERE f.deptCity=:deptCity AND f.arrCity=:arrCity";
	public static String searchFlightByFlightNumber = "SELECT f FROM Flight f WHERE f.flightNo=:flightNo";
	public static String flightInformation = "SELECT f FROM Flight f";
	public static String searchFlightByArrivalAndDepartureCityAndDepartureDate = "SELECT f FROM Flight f where f.deptCity=:deptCity AND f.arrCity=:arrCity AND f.deptDate=:deptDate";
	public static String BookingInformationOfAFlight = "SELECT b FROM BookingInformation b WHERE b.flightNo=:flightNo";
	public static String userInformation = "SELECT u FROM User u WHERE u.username=:username";
	public static String BookingInformationByEmail = "SELECT b FROM BookingInformation b WHERE b.userEmail=:email";
	public static String validateUsernameAndPassword = "SELECT u FROM User u WHERE u.username=:user AND u.password=:pass";
	public static String firstSeatsOfAFlight = "SELECT f.firstSeats FROM Flight f where f.flightNo=:flightNo";
	public static String businessSeatsOfAFlight = "SELECT f.bussSeats FROM Flight f where f.flightNo=:flightNo";
	public static String passengersInFirstClassOfAFlight = "SELECT f.noOfPassengers FROM BookingInformation f where f.flightNo=:flightNo AND f.classType='first'";
	public static String passengersInBusinessClassOfAFlight = "SELECT f.noOfPassengers FROM BookingInformation f where f.flightNo=:flightNo AND f.classType='business'";
	public static String forgotPassword = "Update User u Set u.password where u.username=:username";
	public static String checkUsernameAvailable = "Select u.username from User u where u.username=:query";
	public static String checkEmailAvailable = "Select u.email from User u where u.email=:query";
	public static String getAllCities = "Select a.location from Airport a";

}
