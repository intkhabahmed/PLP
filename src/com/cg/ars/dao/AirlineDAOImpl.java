package com.cg.ars.dao;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.cg.ars.entity.BookingInformation;
import com.cg.ars.entity.Flight;
import com.cg.ars.entity.User;
import com.cg.ars.utility.QueryMapper;

/**
 * @author inahmed
 *
 */

/**
 * @author hisinha
 *
 */
/**
 * @author hisinha
 *
 */
@Repository
public class AirlineDAOImpl implements IAirlineDAO {
	/*
	 * private static Logger logger = Logger
	 * .getLogger(com.cg.as.dao.AirlineDAOImpl.class);
	 */

	@PersistenceContext
	private EntityManager entityManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cg.as.dao.IAirlineDAO#viewListOfFlights(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public List<Flight> viewListOfFlights(String query, String searchBasis)
			throws Exception {
		TypedQuery<Flight> sqlQuery = null;
		if (searchBasis.equals("dest")) {
			sqlQuery = entityManager.createQuery(QueryMapper.searchFlightByArrivalCity,
					Flight.class);
			sqlQuery.setParameter("arrCity", query);
		} else if (searchBasis.equals("day")) {
			sqlQuery = entityManager.createQuery(QueryMapper.searchFlightByDepartureDate,
					Flight.class);
			sqlQuery.setParameter("deptDate", Date.valueOf(query));
		} else if (searchBasis.equals("route")) {
			String route[] = query.split("=");
			sqlQuery = entityManager.createQuery(QueryMapper.searchFlightByDepartureAndArrivalCity,
					Flight.class);
			sqlQuery.setParameter("deptCity", route[0]);
			sqlQuery.setParameter("arrCity", route[1]);
		} else if (searchBasis.equals("flightNo")) {
			sqlQuery = entityManager.createQuery(QueryMapper.searchFlightByFlightNumber,
					Flight.class);
			sqlQuery.setParameter("flightNo", query);
		} else if (searchBasis.equals("all")) {
			sqlQuery = entityManager.createQuery(QueryMapper.flightInformation,
					Flight.class);
		} else if (searchBasis.equals("byUser")) {
			String route[] = query.split("=");
			sqlQuery = entityManager.createQuery(QueryMapper.searchFlightByArrivalAndDepartureCityAndDepartureDate,
					Flight.class);
			sqlQuery.setParameter("deptCity", route[0]);
			sqlQuery.setParameter("arrCity", route[1]);
			sqlQuery.setParameter("deptDate", Date.valueOf(route[2]));
		}

		return sqlQuery.getResultList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cg.as.dao.IAirlineDAO#viewBookings(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public List<BookingInformation> viewBookings(String query,
			String searchBasis) throws Exception {

		TypedQuery<BookingInformation> sqlQuery = null;

		if (searchBasis.equals("byFlight")) {
			sqlQuery = entityManager.createQuery(QueryMapper.BookingInformationOfAFlight,
					BookingInformation.class);
			sqlQuery.setParameter("flightNo", query);
		} else if (searchBasis.equals("byUser")) {
			TypedQuery<User> userQuery = entityManager.createQuery(
					QueryMapper.userInformation, User.class);
			userQuery.setParameter("username", query);
			User user = userQuery.getSingleResult();
			sqlQuery = entityManager.createQuery(QueryMapper.BookingInformationByEmail,
					BookingInformation.class);
			sqlQuery.setParameter("email", user.getEmail());
		}
		return sqlQuery.getResultList();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cg.as.dao.IAirlineDAO#validLogin(com.cg.as.entity.User)
	 */
	@Override
	public User validLogin(User user) throws Exception {
		TypedQuery<User> sqlQuery = entityManager.createQuery(
				QueryMapper.validateUsernameAndPassword, User.class);
		sqlQuery.setParameter("user", user.getUsername());
		sqlQuery.setParameter("pass", user.getPassword());
		return sqlQuery.getSingleResult();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cg.as.dao.IAirlineDAO#signUp(com.cg.as.entity.User)
	 */
	@Override
	public User signUp(User user) throws Exception {
		entityManager.persist(user);
		return user;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cg.as.dao.IAirlineDAO#bookingCancel(java.lang.String)
	 */
	@Override
	public BookingInformation bookingCancel(int bookingId) throws Exception {
		BookingInformation booking = entityManager.find(
				BookingInformation.class, bookingId);
		entityManager.remove(booking);
		return booking;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cg.as.dao.IAirlineDAO#flightOccupancyDetails(java.lang.String)
	 */
	@Override
	public int[] flightOccupancyDetails(String flightNo) throws Exception {
		int[] seatDetails = new int[4];
		TypedQuery<Integer> sqlQuery = null;
		sqlQuery = entityManager
				.createQuery(QueryMapper.firstSeatsOfAFlight, Integer.class);
		sqlQuery.setParameter("flightNo", flightNo);
		seatDetails[0] = sqlQuery.getSingleResult();
		sqlQuery = entityManager
				.createQuery(QueryMapper.businessSeatsOfAFlight, Integer.class);
		sqlQuery.setParameter("flightNo", flightNo);
		seatDetails[1] = sqlQuery.getSingleResult();
		sqlQuery = entityManager
				.createQuery(QueryMapper.passengersInFirstClassOfAFlight, Integer.class);
		sqlQuery.setParameter("flightNo", flightNo);
		seatDetails[2] = sqlQuery.getSingleResult();
		sqlQuery = entityManager
				.createQuery(QueryMapper.passengersInBusinessClassOfAFlight, Integer.class);
		sqlQuery.setParameter("flightNo", flightNo);
		seatDetails[3] = sqlQuery.getSingleResult();
		return seatDetails;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cg.as.dao.IAirlineDAO#modifyBookingInformation(com.cg.as.entity.
	 * BookingInformation)
	 */
	@Override
	public BookingInformation modifyBookingInformation(
			BookingInformation booking) throws Exception {
		booking = entityManager.merge(booking);
		return booking;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cg.as.dao.IAirlineDAO#confirmBooking(com.cg.as.entity.BookingInformation
	 * )
	 */
	@Override
	public BookingInformation confirmBooking(BookingInformation booking)
			throws Exception {
		entityManager.persist(booking);
		entityManager.flush();
		return booking;

	}

	@Override
	public String forgotPassword(String username, String password) {
		Query sqlQuery = entityManager.createQuery(QueryMapper.forgotPassword);
		sqlQuery.setParameter("username", username);
		return username;
	}

	@Override
	public String checkAvailabiltiy(String query, String searchBasis)
			throws Exception {
		TypedQuery<String> sqlQuery = null;
		String isAvail;
		if (searchBasis.equals("byUsername")) {
			sqlQuery = entityManager.createQuery(QueryMapper.checkUsernameAvailable,
					String.class);
			sqlQuery.setParameter("query", query);
		} else if (searchBasis.equals("byEmail")) {
			sqlQuery = entityManager.createQuery(QueryMapper.checkEmailAvailable,
					String.class);
			sqlQuery.setParameter("query", query);
		}
		isAvail = sqlQuery.getSingleResult();
		return isAvail;
	}

	@Override
	public User updateUser(User user) throws Exception {
		entityManager.merge(user);
		return user;
	}
}
