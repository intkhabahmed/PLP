package com.cg.as.service;

import java.util.List;

import com.cg.as.entity.BookingInformation;
import com.cg.as.entity.Flight;
import com.cg.as.entity.LoginMaster;
import com.cg.as.exception.AirlineException;

public interface IAirlineService {
	
	public List<Flight> viewListOfFlights(String query, String searchBasis) throws AirlineException;
<<<<<<< HEAD
	public List<BookingInfo> viewBookings(String query, String searchBasis) throws AirlineException;
=======
	public List<BookingInformation> viewBookings(String query, String searchBasis) throws AirlineException;
	public List<BookingInformation> viewPassengersOfFlight(String flightNo) throws AirlineException;
>>>>>>> a9b8ec1d111f8d4fb46739f806b1c22c2abfd135
	public int signUp(LoginMaster login) throws AirlineException;
	public String validLogin(LoginMaster login) throws AirlineException;
	public int bookingCancel(String bookingId, String username) throws AirlineException;
	public int[] flightOccupancyDetails(String flightNo) throws AirlineException;
	public int bookingConfirm(String username,String flightno, int noOfPassengers, String classType,
			String creditCard) throws AirlineException;
	public int checkAvailability(String query, String type) throws AirlineException;
	public int checkTimeFormat(String newInput);
	public int checkDateFormat(String newInput) throws AirlineException;
	public void checkValidation(String query, String basis) throws AirlineException;
}
