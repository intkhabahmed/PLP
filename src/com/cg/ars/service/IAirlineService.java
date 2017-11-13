package com.cg.ars.service;

import java.util.List;

import com.cg.ars.entity.Airport;
import com.cg.ars.entity.BookingInformation;
import com.cg.ars.entity.Flight;
import com.cg.ars.entity.User;

public interface IAirlineService {

	/**
	 * @param query
	 * @param searchBasis
	 * @return
	 * @throws Exception
	 */
	public List<Flight> viewListOfFlights(String query, String searchBasis)
			throws Exception;

	/**
	 * @param query
	 * @param searchBasis
	 * @return
	 * @throws Exception
	 */
	public List<BookingInformation> viewBookings(String query,
			String searchBasis) throws Exception;

	/**
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public User signUp(User user) throws Exception;

	/**
	 * @param user
	 * @return
	 * @throws Exception
	 */

	/**
	 * @param bookingId
	 * @return
	 * @throws Exception
	 */

	public BookingInformation bookingCancel(int bookingId) throws Exception;

	/**
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public User validLogin(User user) throws Exception;

	/**
	 * @param flightNo
	 * @return
	 * @throws Exception
	 */
	public int[] flightOccupancyDetails(String flightNo) throws Exception;

	/**
	 * @param booking
	 * @return
	 * @throws Exception
	 */
	public BookingInformation modifyBookingInformation(
			BookingInformation booking) throws Exception;

	/**
	 * @param booking
	 * @return
	 * @throws Exception
	 */
	public BookingInformation confirmBooking(BookingInformation booking)
			throws Exception;

	/**
	 * @param query
	 * @param searchBasis
	 * @return
	 * @throws Exception
	 */
	public boolean checkAvailabiltiy(String query, String searchBasis)
			throws Exception;

	/**
	 * @param username
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public User forgotPassword(User user)	throws Exception;

	/**
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public User updateUser(User user) throws Exception;
	
	/**
	 * @return
	 * @throws Exception
	 */
	public List<Airport> getCities() throws Exception;
}
