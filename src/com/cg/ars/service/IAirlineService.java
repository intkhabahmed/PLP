package com.cg.ars.service;

import java.util.List;

import com.cg.ars.entity.BookingInformation;
import com.cg.ars.entity.Flight;
import com.cg.ars.entity.User;

public interface IAirlineService {

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
	 * @param bookingId
	 * @return type BookingInformation
	 * @throws Exception
	 */
	public BookingInformation bookingCancel(int bookingId) throws Exception;

	/**
	 * @param user
	 * @return type User
	 * @throws Exception
	 */
	public User validLogin(User user) throws Exception;

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
	 * @return type boolean
	 * @throws Exception
	 */
	public boolean checkAvailabiltiy(String query, String searchBasis)
			throws Exception;

	/**
	 * @param username
	 * @param password
	 * @return type User
	 * @throws Exception
	 */
	public User forgotPassword(User user)	throws Exception;

	/**
	 * @param user
	 * @return type User
	 * @throws Exception
	 */
	public User updateUser(User user) throws Exception;
	
	/**
	 * @return type List
	 * @throws Exception
	 */
	public List<String> getCities() throws Exception;

	/**
	 * @param cityName
	 * @return
	 * @throws Exception
	 */
	public String getAbbreviation(String cityName) throws Exception;
}
