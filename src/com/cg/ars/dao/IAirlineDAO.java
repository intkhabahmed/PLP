package com.cg.ars.dao;

import java.util.List;

import com.cg.ars.entity.BookingInformation;
import com.cg.ars.entity.Flight;
import com.cg.ars.entity.User;
import com.cg.ars.exception.AirlineException;

/**
 * @author inahmed
 *
 */
public interface IAirlineDAO {

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
	 * @param user
	 * @return type User
	 * @throws AirlineException
	 */
	public User validLogin(User user) throws AirlineException;

	/**
	 * @param bookingId
	 * @return type BookingInformation
	 * @throws AirlineException
	 */
	public BookingInformation bookingCancel(int bookingId)
			throws AirlineException;

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
	 * @return type String
	 * @throws AirlineException
	 */
	public String checkAvailabiltiy(String query, String searchBasis)
			throws AirlineException;

	/**
	 * @param user
	 * @return type User
	 * @throws AirlineException
	 */
	public User updateUser(User user) throws AirlineException;


	/**
	 * @param flight
	 * @return type void
	 * @throws AirlineException
	 */
	public void updateFlight(Flight flight) throws AirlineException;
	
	/**
	 * @param username
	 * @return type User
	 * @throws AirlineException
	 */
	public User getUserDetails(String username) throws AirlineException;

	/**
	 * @return type List
	 * @throws AirlineException
	 */
	public List<String> getCities() throws AirlineException;

	/**
	 * @param cityName
	 * @return type String
	 * @throws AirlineException
	 */
	public String getAbbreviation(String cityName) throws AirlineException;

}
