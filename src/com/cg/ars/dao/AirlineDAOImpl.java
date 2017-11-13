package com.cg.ars.dao;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.cg.ars.entity.BookingInformation;
import com.cg.ars.entity.Flight;
import com.cg.ars.entity.User;
import com.cg.ars.exception.AirlineException;
import com.cg.ars.utility.ARSConstants;
import com.cg.ars.utility.QueryMapper;

/**
 * @author prasrani
 *
 */

@Repository
public class AirlineDAOImpl implements IAirlineDAO {

	@PersistenceContext
	private EntityManager entityManager;

	private static Logger logger = Logger
			.getLogger(com.cg.ars.dao.AirlineDAOImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cg.ars.dao.IAirlineDAO#viewListOfFlights(java.lang.String,
	 * java.lang.String) It returns the list of flights on search basis to
	 * service layer
	 */
	@Override
	public String getAbbreviation(String cityName) throws AirlineException {
		String result = "";
		try {
			TypedQuery<String> sqlQuery = entityManager.createQuery(
					QueryMapper.GETABBREVIATION, String.class);
			sqlQuery.setParameter("location", cityName.toUpperCase());
			result = sqlQuery.getSingleResult();
		} catch (Exception e) {
			throw new AirlineException(e.getMessage());
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cg.as.dao.IAirlineDAO#viewListOfFlights(java.lang.String,
	 * java.lang.String) It returns the list of flight to service layer
	 */
	@Override
	public List<Flight> viewListOfFlights(String query, String searchBasis)
			throws AirlineException {
		TypedQuery<Flight> sqlQuery = null;
		List<Flight> flights = null;
		try {
			if ("dest".equals(searchBasis)) {
				sqlQuery = entityManager.createQuery(
						QueryMapper.SEARCHFLIGHTBYARRIVALCITY, Flight.class);
				sqlQuery.setParameter(ARSConstants.ARRCITY, query);
			} else if ("day".equals(searchBasis)) {
				sqlQuery = entityManager.createQuery(
						QueryMapper.SEARCHFLIGHTBYDEPARTUREDATE, Flight.class);
				sqlQuery.setParameter(ARSConstants.DEPDATE, Date.valueOf(query));
			} else if ("route".equals(searchBasis)) {
				String[] route = query.split("=");
				sqlQuery = entityManager.createQuery(
						QueryMapper.SEARCHFLIGHTBYDEPARTUREANDARRIVALCITY,
						Flight.class);
				sqlQuery.setParameter(ARSConstants.DEPCITY, route[0]);
				sqlQuery.setParameter(ARSConstants.ARRCITY, route[1]);
			} else if (searchBasis.equals(ARSConstants.FLIGHTNO)) {
				sqlQuery = entityManager.createQuery(
						QueryMapper.SEARCHFLIGHTBYFLIGHTNUMBER, Flight.class);
				sqlQuery.setParameter(ARSConstants.FLIGHTNO, query);
			} else if ("all".equals(searchBasis)) {
				sqlQuery = entityManager.createQuery(
						QueryMapper.FLIGHTINFORMATION, Flight.class);
			} else if (ARSConstants.BYUSER.equals(searchBasis)) {
				String[] route = query.split("=");
				sqlQuery = entityManager
						.createQuery(
								QueryMapper.SEARCHFLIGHTBYARRIVALANDDEPARTURECITYANDDEPARTUREDATE,
								Flight.class);
				sqlQuery.setParameter(ARSConstants.DEPCITY, route[0]);
				sqlQuery.setParameter(ARSConstants.ARRCITY, route[1]);
				sqlQuery.setParameter(ARSConstants.DEPDATE,
						Date.valueOf(route[2]));
			}
			flights = sqlQuery.getResultList();
			logger.info("List of flights retrieved");
		} catch (Exception e) {
			throw new AirlineException(e.getMessage());
		}
		return flights;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cg.as.dao.IAirlineDAO#viewBookings(java.lang.String,
	 * java.lang.String) It views the booking information and returns the result
	 * to service layer
	 */
	@Override
	public List<BookingInformation> viewBookings(String query,
			String searchBasis) throws AirlineException {

		TypedQuery<BookingInformation> sqlQuery = null;
		List<BookingInformation> bookings = null;

		try {
			if (ARSConstants.BYFLIGHT.equals(searchBasis)) {
				sqlQuery = entityManager.createQuery(
						QueryMapper.BOOKINGINFORMATIONOFAFLIGHT,
						BookingInformation.class);
				sqlQuery.setParameter(ARSConstants.FLIGHTNO, query);
			} else if (ARSConstants.BYUSER.equals(searchBasis)) {
				TypedQuery<User> userQuery = entityManager.createQuery(
						QueryMapper.USERINFORMATION, User.class);
				userQuery.setParameter(ARSConstants.USERNAME, query);
				User user = userQuery.getSingleResult();
				sqlQuery = entityManager.createQuery(
						QueryMapper.BOOKINGINFORMATIONBYEMAIL,
						BookingInformation.class);
				sqlQuery.setParameter(ARSConstants.EMAIL, user.getEmail());
			} else if (ARSConstants.BYBOOKINGID.equals(searchBasis)) {
				sqlQuery = entityManager.createQuery(
						QueryMapper.BOOKINGINFOBYBOOKINGID,
						BookingInformation.class);
				sqlQuery.setParameter(ARSConstants.BOOKINGID,
						Integer.parseInt(query));
			}
			bookings = sqlQuery.getResultList();
			logger.info("List of Bookings retrieved");
		} catch (Exception e) {
			throw new AirlineException(e.getMessage());
		}
		return bookings;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cg.as.dao.IAirlineDAO#validLogin(com.cg.as.entity.User) This
	 * function checks that user is valid or not and returns the result to
	 * service layer
	 */
	@Override
	public User validLogin(User user) throws AirlineException {
		TypedQuery<User> sqlQuery = null;
		try {
			sqlQuery = entityManager.createQuery(
					QueryMapper.VALIDATEUSERNAMEANDPASSWORD, User.class);
			sqlQuery.setParameter(ARSConstants.USER, user.getUsername());
			sqlQuery.setParameter(ARSConstants.PASS, user.getPassword());
			user = sqlQuery.getSingleResult();
			logger.info("Following User Logged in:" + user.getUsername());
		} catch (Exception e) {
			throw new AirlineException(e.getMessage());
		}
		return user;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cg.as.dao.IAirlineDAO#signUp(com.cg.as.entity.User) This
	 * function does sign up and return user object to service layer
	 */
	@Override
	public User signUp(User user) throws AirlineException {
		try {
			entityManager.persist(user);
			entityManager.flush();
			logger.info("New User signed in with following username:"
					+ user.getUsername());
		} catch (Exception e) {
			throw new AirlineException(e.getMessage());
		}
		return user;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cg.as.dao.IAirlineDAO#bookingCancel(java.lang.String) This
	 * function cancels the booking and returns the booking object to service
	 * layer
	 */
	@Override
	public BookingInformation bookingCancel(int bookingId)
			throws AirlineException {
		BookingInformation booking = new BookingInformation();
		try {
			booking = entityManager.find(BookingInformation.class, bookingId);
			entityManager.remove(booking);
			entityManager.flush();
			logger.info("Booking cancelled for booking id: " + bookingId);
		} catch (Exception e) {
			throw new AirlineException(e.getMessage());
		}
		return booking;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cg.as.dao.IAirlineDAO#flightOccupancyDetails(java.lang.String)
	 * It returns the total first seats, total business seats, no. of passengers
	 * in class type First and in class type Business to service layer
	 */
	@Override
	public int[] flightOccupancyDetails(String flightNo)
			throws AirlineException {
		int[] seatDetails = new int[4];
		TypedQuery<Integer> sqlQuery = null;
		try {
			sqlQuery = entityManager.createQuery(
					QueryMapper.FIRSTSEATSOFAFLIGHT, Integer.class);
			sqlQuery.setParameter(ARSConstants.FLIGHTNO, flightNo);
			seatDetails[0] = sqlQuery.getSingleResult();
			sqlQuery = entityManager.createQuery(
					QueryMapper.BUSINESSSEATSOFAFLIGHT, Integer.class);
			sqlQuery.setParameter(ARSConstants.FLIGHTNO, flightNo);
			seatDetails[1] = sqlQuery.getSingleResult();
			sqlQuery = entityManager.createQuery(
					QueryMapper.PASSENGERSINFIRSTCLASSOFAFLIGHT, Integer.class);
			sqlQuery.setParameter(ARSConstants.FLIGHTNO, flightNo);
			seatDetails[2] = sqlQuery.getSingleResult();
			sqlQuery = entityManager.createQuery(
					QueryMapper.PASSENGERSINBUSINESSCLASSOFAFLIGHT,
					Integer.class);
			sqlQuery.setParameter(ARSConstants.FLIGHTNO, flightNo);
			seatDetails[3] = sqlQuery.getSingleResult();
			logger.info("Flight occupancy details retrieved for flight: "
					+ flightNo);
		} catch (Exception e) {
			throw new AirlineException(e.getMessage());
		}
		return seatDetails;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cg.as.dao.IAirlineDAO#modifyBookingInformation(com.cg.as.entity.
	 * BookingInformation) It modifies the booking information and returns
	 * booking object to service layer
	 */
	@Override
	public BookingInformation modifyBookingInformation(
			BookingInformation booking) throws AirlineException {
		try {
			booking = entityManager.merge(booking);
			entityManager.flush();
			logger.info("Booking modified for booking id: "
					+ booking.getBookingId());
		} catch (Exception e) {
			throw new AirlineException(e.getMessage());
		}
		return booking;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cg.as.dao.IAirlineDAO#confirmBooking(com.cg.as.entity.BookingInformation
	 * ) It confirms the booking and returns booking object to service layer
	 */
	@Override
	public BookingInformation confirmBooking(BookingInformation booking)
			throws AirlineException {
		try {
			entityManager.persist(booking);
			entityManager.flush();
			logger.info("Booking confirmed for booking id: "
					+ booking.getBookingId());
		} catch (Exception e) {
			throw new AirlineException(e.getMessage());
		}
		return booking;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cg.ars.dao.IAirlineDAO#checkAvailabiltiy(java.lang.String,
	 * java.lang.String) It checks the availability of user in database and
	 * returns the result to service layer
	 */
	@Override
	public String checkAvailabiltiy(String query, String searchBasis)
			throws AirlineException {
		TypedQuery<String> sqlQuery = null;
		String isAvail;
		try {
			if (ARSConstants.BYUSERNAME.equals(searchBasis)) {

				sqlQuery = entityManager.createQuery(
						QueryMapper.CHECKUSERNAMEISAVAILABLE, String.class);
				sqlQuery.setParameter(ARSConstants.QUERY, query);
			} else if (ARSConstants.BYEMAIL.equals(searchBasis)) {
				sqlQuery = entityManager.createQuery(
						QueryMapper.CHECKEMAILISAVAILABLE, String.class);
				sqlQuery.setParameter(ARSConstants.QUERY, query);
			}
			isAvail = sqlQuery.getSingleResult();
		} catch (Exception e) {
			throw new AirlineException(e.getMessage());
		}
		return isAvail;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cg.ars.dao.IAirlineDAO#updateUser(com.cg.ars.entity.User) It
	 * updates the details of user and returns User object to service layer
	 */
	@Override
	public User updateUser(User user) throws AirlineException {
		try {
			entityManager.merge(user);
			entityManager.flush();
		} catch (Exception e) {
			throw new AirlineException(e.getMessage());
		}
		return user;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cg.ars.dao.IAirlineDAO#updateFlight(com.cg.ars.entity.Flight) It
	 * updates the flight details
	 */
	@Override
	public void updateFlight(Flight flight) throws AirlineException {
		try {
			entityManager.merge(flight);
			entityManager.flush();
		} catch (Exception e) {
			throw new AirlineException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cg.ars.dao.IAirlineDAO#getUserDetails(java.lang.String) It
	 * returns the details of given user to service layer
	 */
	@Override
	public User getUserDetails(String username) throws AirlineException {
		User user = new User();
		TypedQuery<User> query = null;
		try {
			query = entityManager.createQuery(QueryMapper.USERINFORMATION,
					User.class);
			query.setParameter(ARSConstants.USERNAME, username);
			user = query.getSingleResult();
		} catch (Exception e) {
			throw new AirlineException(e.getMessage());
		}
		return user;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cg.ars.dao.IAirlineDAO#getCities() It returns the list of all
	 * cities present in the database to service layer
	 */
	@Override
	public List<String> getCities() throws AirlineException {
		TypedQuery<String> query = null;
		List<String> cities = null;
		try {
			query = entityManager.createQuery(QueryMapper.GETALLCITIES,
					String.class);
			cities = query.getResultList();
		} catch (Exception e) {
			throw new AirlineException(e.getMessage());
		}
		return cities;
	}

}
