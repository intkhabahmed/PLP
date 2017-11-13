package com.cg.ars.dao;

import java.util.List;

import com.cg.ars.entity.BookingInformation;
import com.cg.ars.entity.Flight;
import com.cg.ars.entity.User;

/**
 * @author inahmed
 *
 */
public interface IAirlineDAO {

	/**
	 * @param query
	 * @param searchBasis
	 * @return type List
	 * @throws Exception
	 */
	public List<Flight> viewListOfFlights(String query, String searchBasis)
			throws Exception;

	/**
	 * @param query
	 * @param searchBasis
	 * @return type List
	 * @throws Exception
	 */
	public List<BookingInformation> viewBookings(String query,
			String searchBasis) throws Exception;

	/**
	 * @param user
	 * @return type User
	 * @throws Exception
	 */
	public User signUp(User user) throws Exception;

	/**
	 * @param user
	 * @return type User
	 * @throws Exception
	 */
	public User validLogin(User user) throws Exception;

	/**
	 * @param bookingId
	 * @return type BookingInformation
	 * @throws Exception
	 */
	public BookingInformation bookingCancel(int bookingId) throws Exception;

	/**
	 * @param flightNo
	 * @return type Integer Array
	 * @throws Exception
	 */
	public int[] flightOccupancyDetails(String flightNo) throws Exception;

	/**
	 * @param booking
	 * @return type BookingInformation
	 * @throws Exception
	 */
	public BookingInformation modifyBookingInformation(
			BookingInformation booking) throws Exception;

	/**
	 * @param booking
	 * @return type BookingInformation
	 * @throws Exception
	 */
	public BookingInformation confirmBooking(BookingInformation booking)
			throws Exception;

	/**
	 * @param query
	 * @param searchBasis
	 * @return type String
	 * @throws Exception
	 */
	public String checkAvailabiltiy(String query, String searchBasis)
			throws Exception;

	/**
	 * @param user
	 * @return type User
	 * @throws Exception
	 */
	public User updateUser(User user) throws Exception;


	/**
	 * @param flight
	 * @return type void
	 * @throws Exception
	 */
	public void updateFlight(Flight flight) throws Exception;
	
	/**
	 * @param username
	 * @return type User
	 * @throws Exception
	 */
	public User getUserDetails(String username) throws Exception;

	/**
	 * @return type List
	 * @throws Exception
	 */
	public List<String> getCities() throws Exception;

	/**
	 * @param cityName
	 * @return type String
	 * @throws Exception
	 */
	public String getAbbreviation(String cityName) throws Exception;

}
