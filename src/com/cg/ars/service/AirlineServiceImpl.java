package com.cg.ars.service;

import java.util.List;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.ars.dao.IAirlineDAO;
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
		List<Flight> flights = airlineDAO.viewListOfFlights(query, searchBasis);
		return flights;
	}

	@Override
	public List<BookingInformation> viewBookings(String query, String searchBasis)
			throws Exception {
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
	public BookingInformation bookingCancel(int bookingId)
			throws Exception {
		return airlineDAO.bookingCancel(bookingId);

	}
	
	
	@Override
	public int[] flightOccupancyDetails(String flightNo) throws Exception{
		return airlineDAO.flightOccupancyDetails(flightNo);
	}
	
	
	@Override
	public BookingInformation modifyBookingInformation(BookingInformation booking) throws Exception{
		return airlineDAO.modifyBookingInformation(booking);
	}
	
	@Override
	public BookingInformation confirmBooking(BookingInformation booking) throws Exception{
		return airlineDAO.confirmBooking(booking);
	}
	
	@Override
	public String forgotPassword(String username, String password) throws Exception{
		String isAvail = airlineDAO.checkAvailabiltiy(username, "byUsername");
		if(!isAvail.isEmpty())
			return airlineDAO.forgotPassword(username, password);
		else
			return "Invalid Username";
	}
	
	@Override
	public boolean checkAvailabiltiy(String query, String searchBasis) throws Exception{
		try{
			String isAvail = airlineDAO.checkAvailabiltiy(query, searchBasis);
			if(isAvail.isEmpty())
				return true;
			else
				return false;
		}catch(NoResultException nre){
			return true;
		}catch(Exception e){
			throw new AirlineException("Server Error: "+e.getMessage());
		}
		
	}

	@Override
	public User updateUser(User user) throws Exception {
		return airlineDAO.updateUser(user);
	}

}
