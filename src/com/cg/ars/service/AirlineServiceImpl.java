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

	@Override
	public List<Flight> viewListOfFlights(String query, String searchBasis)
			throws Exception {
		return airlineDAO.viewListOfFlights(query, searchBasis);
	}

	@Override
	public List<BookingInformation> viewBookings(String query,
			String searchBasis) throws Exception {
		return airlineDAO.viewBookings(query, searchBasis);
	}

	@Override
	public User signUp(User user) throws Exception {
		return airlineDAO.signUp(user);
	}

	@Override
	public User validLogin(User user) throws Exception {
		return airlineDAO.validLogin(user);
	}

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

	@Override
	public int[] flightOccupancyDetails(String flightNo) throws Exception {
		return airlineDAO.flightOccupancyDetails(flightNo);
	}

	@Override
	public BookingInformation modifyBookingInformation(
			BookingInformation booking) throws Exception {
		return airlineDAO.modifyBookingInformation(booking);
	}

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

	@Override
	public boolean checkAvailabiltiy(String query, String searchBasis)
			throws Exception {
		try {
			String isAvail = airlineDAO.checkAvailabiltiy(query, searchBasis);
			return isAvail.isEmpty();
		} catch (NoResultException nre) {
			return true;
		} catch (Exception e) {
			throw new AirlineException("Server Error: " + e.getMessage());
		}

	}

	@Override
	public User updateUser(User user) throws Exception {
		return airlineDAO.updateUser(user);
	}

	@Override
	public List<Airport> getCities() throws Exception {
		return airlineDAO.getCities();
	}

}
