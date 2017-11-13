package com.cg.ars.utility;

public interface QueryMapper {
<<<<<<< HEAD
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
	
	
	

=======
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
>>>>>>> 0794ad054a4461a4b3f39b34402fbfd7e813c08d
}
