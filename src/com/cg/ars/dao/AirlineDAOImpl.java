package com.cg.ars.dao;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.cg.ars.entity.BookingInformation;
import com.cg.ars.entity.Flight;
import com.cg.ars.entity.User;

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
/**
 * @author inahmed
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
			sqlQuery = entityManager.createQuery(
					"SELECT f FROM Flight f WHERE f.arrCity=:arrCity",
					Flight.class);
			sqlQuery.setParameter("arrCity", query);
		} else if (searchBasis.equals("day")) {
			sqlQuery = entityManager.createQuery(
					"SELECT f FROM Flight f WHERE f.deptDate=:deptDate",
					Flight.class);
			sqlQuery.setParameter("deptDate", Date.valueOf(query));
		} else if (searchBasis.equals("route")) {
			String route[] = query.split("=");
			sqlQuery = entityManager
					.createQuery(
							"SELECT f FROM Flight f WHERE f.deptCity=:deptCity AND f.arrCity=:arrCity",
							Flight.class);
			sqlQuery.setParameter("deptCity", route[0]);
			sqlQuery.setParameter("arrCity", route[1]);
		} else if (searchBasis.equals("flightNo")) {
			sqlQuery = entityManager.createQuery(
					"SELECT f FROM Flight f WHERE f.flightNo=:flightNo",
					Flight.class);
			sqlQuery.setParameter("flightNo", query);
		} else if (searchBasis.equals("all")) {
			sqlQuery = entityManager.createQuery("SELECT f FROM Flight f",
					Flight.class);
		} else if (searchBasis.equals("byUser")) {
			String route[] = query.split("=");
			sqlQuery = entityManager
					.createQuery(
							"SELECT f FROM Flight f where f.deptCity=:deptCity AND f.arrCity=:arrCity AND f.deptDate=:deptDate",
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
			sqlQuery = entityManager
					.createQuery(
							"SELECT b FROM BookingInformation b WHERE b.flightNo=:flightNo",
							BookingInformation.class);
			sqlQuery.setParameter("flightNo", query);
		} else if (searchBasis.equals("byUser")) {

			TypedQuery<User> userQuery = entityManager.createQuery(
					"SELECT u FROM User u WHERE u.username=:username",
					User.class);
			userQuery.setParameter("username", query);
			User user = userQuery.getSingleResult();
			sqlQuery = entityManager
					.createQuery(
							"SELECT b FROM BookingInformation b WHERE b.userEmail=:email",
							BookingInformation.class);
			sqlQuery.setParameter("email", user.getEmail());
		} else if (searchBasis.equals("byBookingId")) {
			sqlQuery = entityManager
					.createQuery(
							"SELECT b FROM BookingInformation b WHERE b.bookingId=:bookingId",
							BookingInformation.class);
			sqlQuery.setParameter("bookingId", Integer.parseInt(query));
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
		TypedQuery<User> sqlQuery = entityManager
				.createQuery(
						"SELECT u FROM User u WHERE u.username=:user AND u.password=:pass",
						User.class);
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
		sqlQuery = entityManager.createQuery(
				"SELECT f.firstSeats FROM Flight f where f.flightNo=:flightNo",
				Integer.class);
		sqlQuery.setParameter("flightNo", flightNo);
		seatDetails[0] = sqlQuery.getSingleResult();
		sqlQuery = entityManager.createQuery(
				"SELECT f.bussSeats FROM Flight f where f.flightNo=:flightNo",
				Integer.class);
		sqlQuery.setParameter("flightNo", flightNo);
		seatDetails[1] = sqlQuery.getSingleResult();
		sqlQuery = entityManager
				.createQuery(
						"SELECT f.noOfPassengers FROM BookingInformation f where f.flightNo=:flightNo AND f.classType='first'",
						Integer.class);
		sqlQuery.setParameter("flightNo", flightNo);
		seatDetails[2] = sqlQuery.getSingleResult();
		sqlQuery = entityManager
				.createQuery(
						"SELECT f.noOfPassengers FROM BookingInformation f where f.flightNo=:flightNo AND f.classType='business'",
						Integer.class);
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

	/* (non-Javadoc)
	 * @see com.cg.ars.dao.IAirlineDAO#checkAvailabiltiy(java.lang.String, java.lang.String)
	 */
	@Override
	public String checkAvailabiltiy(String query, String searchBasis)
			throws Exception {
		TypedQuery<String> sqlQuery = null;
		String isAvail;
		if (searchBasis.equals("byUsername")) {
			sqlQuery = entityManager.createQuery(
					"Select u.username from User u where u.username=:query",
					String.class);
			sqlQuery.setParameter("query", query);
		} else if (searchBasis.equals("byEmail")) {
			sqlQuery = entityManager.createQuery(
					"Select u.email from User u where u.email=:query",
					String.class);
			sqlQuery.setParameter("query", query);
		}
		isAvail = sqlQuery.getSingleResult();
		return isAvail;
	}

	/* (non-Javadoc)
	 * @see com.cg.ars.dao.IAirlineDAO#updateUser(com.cg.ars.entity.User)
	 */
	@Override
	public User updateUser(User user) throws Exception {
		entityManager.merge(user);
		return user;
	}

	/* (non-Javadoc)
	 * @see com.cg.ars.dao.IAirlineDAO#updateFlight(com.cg.ars.entity.Flight)
	 */
	@Override
	public void updateFlight(Flight flight) throws Exception {
		entityManager.merge(flight);
	}

	/* (non-Javadoc)
	 * @see com.cg.ars.dao.IAirlineDAO#getUserDetails(java.lang.String)
	 */
	@Override
	public User getUserDetails(String username) throws Exception {
		TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.username=:username",User.class);
		query.setParameter("username", username);
		return query.getSingleResult();
	}

}
