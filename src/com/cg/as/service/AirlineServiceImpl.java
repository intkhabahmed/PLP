package com.cg.as.service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.regex.Pattern;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.as.dao.IAirlineDAO;
import com.cg.as.entity.BookingInformation;
import com.cg.as.entity.Flight;
import com.cg.as.entity.LoginMaster;
import com.cg.as.exception.AirlineException;

@Service
@Transactional
public class AirlineServiceImpl implements IAirlineService {

	@Autowired
	IAirlineDAO dao;

	

	@Override
	public List<Flight> viewListOfFlights(String query, String searchBasis)
			throws AirlineException {
		List<Flight> flights = dao.viewListOfFlights(query, searchBasis);
		return flights;
	}

	@Override
	public List<BookingInformation> viewBookings(String query, String searchBasis)
			throws AirlineException {
		return dao.viewBookings(query, searchBasis);
	}

<<<<<<< HEAD
=======
	@Override
	public List<BookingInformation> viewPassengersOfFlight(String flightNo)
			throws AirlineException {
		return dao.viewPassengersOfFlight(flightNo);
	}
>>>>>>> a9b8ec1d111f8d4fb46739f806b1c22c2abfd135

	@Override
	public int signUp(LoginMaster login) throws AirlineException {
		return dao.signUp(login);
	}

	@Override
	public String validLogin(LoginMaster login) throws AirlineException {
		return dao.validLogin(login);
	}

	@Override
	public int bookingCancel(String bookingId, String username)
			throws AirlineException {
		return dao.bookingCancel(bookingId, username);

	}

	@Override
	public int[] flightOccupancyDetails(String flightNo)
			throws AirlineException {
		int[] seats = dao.flightOccupancyDetails(flightNo);

		return new int[] { (seats[0] - seats[2]), (seats[1] - seats[3]) };
	}

	@Override
	public int bookingConfirm(String username, String flightno,
			int noOfPassengers, String classType, String creditCard)
			throws AirlineException {
		return dao.bookingConfirm(username, flightno, noOfPassengers,
				classType, creditCard);
	}

	@Override
	public int checkAvailability(String query, String type)
			throws AirlineException {
		return dao.checkAvailability(query, type);
	}

	@Override
	public int checkTimeFormat(String newInput) {
		String validTime = "[0-9]{2}[:]{1}[0-9]{2}";

		if (Pattern.matches(validTime, newInput)) {
			int hours = Integer.parseInt(newInput.substring(0, 2));
			int mins = Integer.parseInt(newInput.substring(3));
			if ((hours >= 0 && hours < 24) && (mins >= 0 && mins < 60)) {
				return 1;
			} else {
				System.out
						.println("Hours should be from 0 to 23 and minutes should be from 0 to 59");
				return 2;
			}
		} else {
			return 2;
		}
	}

	@Override
	public int checkDateFormat(String newInput) throws AirlineException {
		String validDate = "[20]{2}[0-9]{2}[-]{1}[0-9]{1,2}[-]{1}[0-9]{1,2}";

		if (Pattern.matches(validDate, newInput)) {
			try {
				LocalDate date = LocalDate.parse(newInput);
				if (date.compareTo(LocalDate.now()) < 0) {
					throw new AirlineException(
							"Date should be current date or later");
				} else {
					return 1;
				}

			} catch (DateTimeParseException dtpe) {
				throw new AirlineException("Wrong Date Format, Try again", dtpe);
			}
		}
		return 2;
	}

	@Override
	public void checkValidation(String query, String basis)
			throws AirlineException {
		switch (basis) {
		case "cityName":
			if (!query.matches("[a-zA-Z]{2,}")) {
				throw new AirlineException(
						"City Name should have only alphabets");
			}
			break;
		case "":
			break;
		default:
		}
	}

}
