package com.cg.ars.service;

import java.util.List;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.ars.dao.IAirlineDAO;
import com.cg.ars.entity.Airport;
import com.cg.ars.entity.BookingInformation;
import com.cg.ars.entity.Flight;
import com.cg.ars.entity.User;
import com.cg.ars.exception.AirlineException;

@Service
@Transactional
public class AirlineServiceImpl implements IAirlineService {

	@Autowired
	private IAirlineDAO airlineDAO;

	
	/**
	 * description: It calls the function viewListOfFlights(query, searchBasis) of AirlineDaoImpl and returns the list of flights to AirlineController
	 */
	@Override
	public List<Flight> viewListOfFlights(String query, String searchBasis)
			throws Exception {
		List<Flight> flights = airlineDAO.viewListOfFlights(query, searchBasis);
		return flights;
	}

	/**
	 * description: It calls the function viewBookings(query, searchBasis) of AirlineDaoImpl and returns the result to AirlineController
	 */
	@Override
	public List<BookingInformation> viewBookings(String query,
			String searchBasis) throws Exception {
		return airlineDAO.viewBookings(query, searchBasis);
	}
	
	/**
	 * description: It calls the function signUp(user) of AirlineDaoImpl and returns the result to AirlineController
	 */
	@Override
	public User signUp(User user) throws Exception {
		return airlineDAO.signUp(user);
	}

	/**
	 * description: It calls the function validLogin(user) of AirlineDaoImpl and returns the result to AirlineController
	 */
	@Override
	public User validLogin(User user) throws Exception {
		return airlineDAO.validLogin(user);
	}

	/**
	 * description: It calls the function bookingCancel(bookingId), viewListOfFlights(booking.getFlightNo(),"flightNo") and updateFlight(flight)
	 * of AirlineDaoImpl and returns the result to AirlineController
	 */
	@Override
	public BookingInformation bookingCancel(int bookingId) throws Exception {
		BookingInformation booking = airlineDAO.bookingCancel(bookingId);
		Flight flight = airlineDAO.viewListOfFlights(booking.getFlightNo(),
				"flightNo").get(0);
		if ("First".equalsIgnoreCase(booking.getClassType())) {
			flight.setFirstSeats(flight.getFirstSeats()
					+ booking.getNoOfPassengers());
		} else if ("Business".equalsIgnoreCase(booking.getClassType())) {
			flight.setFirstSeats(flight.getBussSeats()
					+ booking.getNoOfPassengers());
		}
		airlineDAO.updateFlight(flight);
		return booking;
	}

	/**
	 * description: It calls the function flightOccupancyDetails(flightNo) of AirlineDaoImpl and returns the result to AirlineController
	 */
	@Override
	public int[] flightOccupancyDetails(String flightNo) throws Exception {
		return airlineDAO.flightOccupancyDetails(flightNo);
	}

	/**
	 * description: It calls modifyBookingInformation(booking) of AirlineDaoImpl and returns the result to AirlineController
	 */
	@Override
	public BookingInformation modifyBookingInformation(
			BookingInformation booking) throws Exception {
		return airlineDAO.modifyBookingInformation(booking);
	}

	/**
	 * It calls the function confirmBooking(booking), viewListOfFlights(booking.getFlightNo(),"flightNo") and updateFlight(flight)
	 * of AirlineDaoImpl and returns the result to AirlineController
	 */
	@Override
	public BookingInformation confirmBooking(BookingInformation booking)
			throws Exception {
		booking = airlineDAO.confirmBooking(booking);
		Flight flight = airlineDAO.viewListOfFlights(booking.getFlightNo(),
				"flightNo").get(0);
		if ("First".equalsIgnoreCase(booking.getClassType())) {
			flight.setFirstSeats(flight.getFirstSeats()
					- booking.getNoOfPassengers());
		} else if ("Business".equalsIgnoreCase(booking.getClassType())) {
			flight.setFirstSeats(flight.getBussSeats()
					- booking.getNoOfPassengers());
		}
		airlineDAO.updateFlight(flight);
		return booking;
	}

	/**
	 *description: It calls the function getUserDetails(user.getUsername()) of AirlineDaoImpl and returns the updated result to AirlineController
	 */
	@Override
	public User forgotPassword(User user) throws Exception {
		try {
			String password = user.getPassword();
			user = airlineDAO.getUserDetails(user.getUsername());
			if ("customer".equals(user.getRole())) {
				user.setPassword(password);
				return airlineDAO.updateUser(user);
			} else
				throw new AirlineException("Username does not exist");
		} catch (NoResultException nre) {
			throw new AirlineException("Username does not exist");
		} catch (Exception e) {
			throw new AirlineException(e.getMessage());
		}
	}

	/**
	 * description: It calls the function checkAvailabiltiy(query, searchBasis) of AirlineDaoImpl 
	 * and if user is not available then returns false otherwise it returns true
	 */
	@Override
	public boolean checkAvailabiltiy(String query, String searchBasis)
			throws Exception {
		try {
			String isAvail = airlineDAO.checkAvailabiltiy(query, searchBasis);
			if (isAvail.isEmpty())
				return true;
			else
				return false;
		} catch (NoResultException nre) {
			return true;
		} catch (Exception e) {
			throw new AirlineException("Server Error: " + e.getMessage());
		}

	}

	/**
	 * description: It calls the function of AirlineDaoImpl and returns the result to AirlineController
	 */
	@Override
	public User updateUser(User user) throws Exception {
		return airlineDAO.updateUser(user);
	}

	/**
	 * It calls the function getCities() of AirlineDaoImpl and returns the result to AirlineController
	 */
	@Override
	public List<Airport> getCities() throws Exception {
		return airlineDAO.getCities();
	}

}
