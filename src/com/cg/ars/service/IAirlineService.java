package com.cg.ars.service;

import java.util.List;

import com.cg.ars.entity.BookingInformation;
import com.cg.ars.entity.Flight;
import com.cg.ars.entity.User;
import com.cg.ars.exception.AirlineException;

public interface IAirlineService {

	/**
	 * @param query
	 * @param searchBasis
	 * @return type List
	 * @throws AirlineException
	 */
	public List<Flight> viewListOfFlights(String query, String searchBasis)
			throws AirlineException;

	/**
	 * @param query
	 * @param searchBasis
	 * @return type List
	 * @throws AirlineException
	 */
	public List<BookingInformation> viewBookings(String query,
			String searchBasis) throws AirlineException;

	/**
	 * @param user
	 * @return type User
	 * @throws AirlineException
	 */
	public User signUp(User user) throws AirlineException;

	/**
	 * @param bookingId
	 * @return type BookingInformation
	 * @throws AirlineException
	 */
	public BookingInformation bookingCancel(int bookingId)
			throws AirlineException;

	/**
	 * @param user
	 * @return type User
	 * @throws AirlineException
	 */
	public User validLogin(User user) throws AirlineException;

	/**
	 * @param flightNo
	 * @return type Integer Array
	 * @throws AirlineException
	 */
	public int[] flightOccupancyDetails(String flightNo)
			throws AirlineException;

	/**
	 * @param booking
	 * @return type BookingInformation
	 * @throws AirlineException
	 */
	public BookingInformation modifyBookingInformation(
			BookingInformation booking) throws AirlineException;

	/**
	 * @param booking
	 * @return type BookingInformation
	 * @throws AirlineException
	 */
	public BookingInformation confirmBooking(BookingInformation booking)
			throws AirlineException;

	/**
	 * @param query
	 * @param searchBasis
	 * @return type boolean
	 * @throws AirlineException
	 */
	public boolean checkAvailabiltiy(String query, String searchBasis)
			throws AirlineException;

	/**
	 * @param username
	 * @param password
	 * @return type User
	 * @throws AirlineException
	 */
	public User forgotPassword(User user) throws AirlineException;

	/**
	 * @param user
	 * @return type User
	 * @throws AirlineException
	 */
	public User updateUser(User user) throws AirlineException;
	
	/**
	 * @return type List
	 * @throws AirlineException
	 */
	public List<String> getCities() throws AirlineException;

	/**
	 * @param cityName
	 * @return Type String
	 * @throws AirlineException
	 */
	public String getAbbreviation(String cityName) throws AirlineException;
}
