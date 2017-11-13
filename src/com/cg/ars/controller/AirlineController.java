package com.cg.ars.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.cg.ars.entity.BookingInformation;
import com.cg.ars.entity.Flight;
import com.cg.ars.entity.User;
import com.cg.ars.exception.AirlineException;
import com.cg.ars.service.IAirlineService;
import com.cg.ars.utility.ARSConstants;
import com.cg.ars.utility.MyUtil;

/**
 * @author inahmed
 *
 */
@Controller
@SessionAttributes("user")
public class AirlineController {

	@Autowired
	IAirlineService airlineService;

<<<<<<< HEAD
	@RequestMapping(value = ARSConstants.URLLISTOFFLIGHT, method = RequestMethod.POST)
=======
	User user;
	private String flightList = "flightList";
	private String index = "index";
	private String login = "login";
	private String signup = "signup";
	private String message = "message";
	private String bookingSuccess = "bookingSuccess";
	private String userProfile = "userProfile"; 

	
	/**
	 * @description It calls the function viewListOfFlights of AirlineServiceImpl 
	 * @param bookingInformation
	 * @param model
	 * @return type String
	 */
	@RequestMapping(value = "/listOfFlights", method = RequestMethod.POST)
>>>>>>> 4645ab32cc6d8bba835d3446134e5292e7ee89aa
	public String getAllFlights(
			@ModelAttribute(ARSConstants.BOOKING) BookingInformation bookingInformation,
			Model model) {
		try {
			String src = airlineService.getAbbreviation(bookingInformation
					.getSrcCity());
			String dest = airlineService.getAbbreviation(bookingInformation
					.getDestCity());
			String str = src + "=" + dest + "="
					+ bookingInformation.getTravelDate();

			List<Flight> flights = airlineService.viewListOfFlights(str,
					ARSConstants.BYUSER);
			if (flights.size() == 0) {
				model.addAttribute(ARSConstants.FLIGHTS, null);
			} else {
				model.addAttribute(ARSConstants.FLIGHTS, flights);
			}
			model.addAttribute(ARSConstants.BOOKING, bookingInformation);

			model.addAttribute(ARSConstants.CLASSTYPEOPTION, new String[] {
					ARSConstants.FIRST, ARSConstants.BUSINESS });
			model.addAttribute(ARSConstants.AIRPORT, airlineService.getCities());
			model.addAttribute(ARSConstants.DATE, Date.valueOf(LocalDate.now()));
			if (bookingInformation.getSrcCity().equals(
					bookingInformation.getDestCity())) {
				throw new AirlineException(
						ARSConstants.SOURCEDESTINATIONCANNOTSAME);
			}
		} catch (Exception e) {

			model.addAttribute(ARSConstants.MESSAGE, ARSConstants.SERVERERROR + e.getMessage());
			return ARSConstants.INDEX;
		}
		return ARSConstants.FLIGHTLIST;
	}

	/**
	 * @description It calls the function getCities() of AirlineServiceImpl 
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value=ARSConstants.URLINDEX)
	public String showHome(Model model, HttpSession session) {
		try {
			if (session.getAttribute(ARSConstants.USER) == null) {
				model.addAttribute(ARSConstants.USER, new User());
			} else {
				model.addAttribute(ARSConstants.USER,
						session.getAttribute(ARSConstants.USER));
			}
			model.addAttribute(ARSConstants.BOOKING, new BookingInformation());
			model.addAttribute(ARSConstants.CLASSTYPEOPTION, new String[] {
					ARSConstants.FIRST, ARSConstants.BUSINESS });

			model.addAttribute(ARSConstants.AIRPORT, airlineService.getCities());
			model.addAttribute(ARSConstants.DATE, Date.valueOf(LocalDate.now()));
		} catch (Exception e) {
			model.addAttribute(ARSConstants.MESSAGE, ARSConstants.SERVERERROR + e.getMessage());
		}
		return ARSConstants.INDEX;
	}

<<<<<<< HEAD
	@RequestMapping(value = ARSConstants.URLSHOWLOGIN)
=======
	/**
	 * @description It adds booking object and user object to model
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/showLogin")
>>>>>>> 4645ab32cc6d8bba835d3446134e5292e7ee89aa
	public String showLogin(Model model) {
		model.addAttribute(ARSConstants.BOOKING, new BookingInformation());
		model.addAttribute(ARSConstants.USER, new User());
		return ARSConstants.LOGIN;
	}

<<<<<<< HEAD
	@RequestMapping(value = ARSConstants.URLSHOWLOGINAFTERSEARCH, method = RequestMethod.POST)
=======
	/**
	 * @description It adds booking object and user object to model
	 * @param model
	 * @param bookingInformation
	 * @return
	 */
	@RequestMapping(value = "/showLoginAfterSearch", method = RequestMethod.POST)
>>>>>>> 4645ab32cc6d8bba835d3446134e5292e7ee89aa
	public String showLoginAfterSearch(
			Model model,
			@ModelAttribute(ARSConstants.BOOKING) BookingInformation bookingInformation) {
		model.addAttribute(ARSConstants.USER, new User());
		model.addAttribute(ARSConstants.BOOKING, bookingInformation);
		return ARSConstants.LOGIN;
	}

<<<<<<< HEAD
	@RequestMapping(value=ARSConstants.URLSHOWSIGNUP)
=======
	/**
	 * @description It adds user object to model
	 * @param model
	 * @return
	 */
	@RequestMapping("/showSignup")
>>>>>>> 4645ab32cc6d8bba835d3446134e5292e7ee89aa
	public String showSignup(Model model) {
		model.addAttribute(ARSConstants.USEROBJ, new User());
		return ARSConstants.SIGNUP;
	}

<<<<<<< HEAD
	@RequestMapping(value = ARSConstants.URLSIGNUP, method = RequestMethod.POST)
=======
	/**
	 * @description signUp 
	 * @param model
	 * @param user
	 * @param bindingResult
	 * @return
	 */
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
>>>>>>> 4645ab32cc6d8bba835d3446134e5292e7ee89aa
	public String signup(Model model,
			@Valid @ModelAttribute(ARSConstants.USEROBJ) User user,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			model.addAttribute(ARSConstants.USEROBJ, user);
			return ARSConstants.SIGNUP;
		} else {
			try {
				user.setRole(ARSConstants.CUSTOMER);
				if (!airlineService.checkAvailabiltiy(user.getUsername(),
						ARSConstants.BYUSERNAME)) {
					throw new AirlineException(ARSConstants.USERNAMETAKEN);
				}
				if (!airlineService.checkAvailabiltiy(user.getEmail(),
						ARSConstants.BYEMAIL)) {
					throw new AirlineException(ARSConstants.EMAILTAKEN);
				}
				airlineService.signUp(user);
				model.addAttribute(ARSConstants.MESSAGE, ARSConstants.SIGNUPSUCCESS);
				model.addAttribute(ARSConstants.USER, new User());
				return ARSConstants.LOGIN;
			} catch (Exception e) {
				model.addAttribute(ARSConstants.MESSAGE, e.getMessage());
				model.addAttribute(ARSConstants.BYUSER, user);
				return ARSConstants.SIGNUP;
			}
		}

	}
<<<<<<< HEAD

	@RequestMapping(value=ARSConstants.URLLOGOUT)
=======
	
    /** 
     * @description logout
     * @param model
     * @param status
     * @return
     */
	@RequestMapping("/logout")
>>>>>>> 4645ab32cc6d8bba835d3446134e5292e7ee89aa
	public String logout(Model model, SessionStatus status) {
		status.setComplete();
		model.addAttribute(ARSConstants.BOOKING, new BookingInformation());
		model.addAttribute(ARSConstants.CLASSTYPEOPTION, new String[] {
				ARSConstants.FIRST, ARSConstants.BUSINESS });
		model.addAttribute(ARSConstants.DATE, Date.valueOf(LocalDate.now()));
		return ARSConstants.INDEX;
	}

<<<<<<< HEAD
	@RequestMapping(value=ARSConstants.URLLOGIN)
=======
	/**
	 * @description It calls validLogin(user) of AirlineServiceImpl 
	 * @param user
	 * @param model
	 * @param req
	 * @return
	 */
	@RequestMapping("/login")
>>>>>>> 4645ab32cc6d8bba835d3446134e5292e7ee89aa
	public String loginValidation(@ModelAttribute(ARSConstants.USER) User user,
			Model model, HttpServletRequest req) {
		String returnPage = "";
		try {
			user = airlineService.validLogin(user);
			if (user != null) {
				model.addAttribute(ARSConstants.BOOKING,
						new BookingInformation());
				model.addAttribute(ARSConstants.CLASSTYPEOPTION, new String[] {
						ARSConstants.FIRST, ARSConstants.BUSINESS });
				model.addAttribute(ARSConstants.USER, user);
				model.addAttribute(ARSConstants.DATE,
						Date.valueOf(LocalDate.now()));
				returnPage = ARSConstants.INDEX;
			}

		} catch (Exception e) {
			model.addAttribute(ARSConstants.MESSAGE, ARSConstants.INVALIDUSERNAMEPWD);
			model.addAttribute(ARSConstants.USER, new User());
			returnPage = ARSConstants.LOGIN;
		}
		return returnPage;
	}

<<<<<<< HEAD
	@RequestMapping(value=ARSConstants.URLLOGINAFTERSEARCH)
=======
	/**
	 * @description this function validates the login details after searching the flight
	 * @param user
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/loginAfterSearch")
>>>>>>> 4645ab32cc6d8bba835d3446134e5292e7ee89aa
	public String loginValidationAfterSearch(
			@ModelAttribute(ARSConstants.USER) User user, Model model,
			HttpSession session) {
		String returnPage = "";
		BookingInformation bookingInformation = (BookingInformation) session
				.getAttribute(ARSConstants.BOOKINGINFO);

		try {
			user = airlineService.validLogin(user);
			if (user != null) {
				session.removeAttribute(ARSConstants.BOOKINGINFO);
				List<Flight> flights = airlineService
						.viewListOfFlights(bookingInformation.getFlightNo(),
								ARSConstants.FLIGHTNO);
				if (ARSConstants.FIRST.equalsIgnoreCase(bookingInformation
						.getClassType())) {
					bookingInformation.setTotalFare(MyUtil.calculatefare(
							bookingInformation.getNoOfPassengers(), flights
									.get(0).getFirstSeatsFare()));
				} else if (ARSConstants.BUSINESS
						.equalsIgnoreCase(bookingInformation.getClassType())) {
					bookingInformation.setTotalFare(MyUtil.calculatefare(
							bookingInformation.getNoOfPassengers(), flights
									.get(0).getBussSeatsFare()));
				}

				bookingInformation.setUserEmail(user.getEmail());
				bookingInformation
						.setBookingDate(Date.valueOf(LocalDate.now()));
				model.addAttribute(ARSConstants.FLIGHT, flights.get(0));
				returnPage = ARSConstants.BOOKING;
			}

		} catch (Exception e) {
			model.addAttribute(ARSConstants.MESSAGE, ARSConstants.INVALIDUSERNAMEPWD);
			model.addAttribute(ARSConstants.USER, new User());

			returnPage = ARSConstants.LOGIN;
		}
		model.addAttribute(ARSConstants.USER, user);
		model.addAttribute(ARSConstants.BOOKING, bookingInformation);
		return returnPage;
	}

<<<<<<< HEAD
	@RequestMapping(value = ARSConstants.URLSHOWBOOKING, method = RequestMethod.POST)
=======
	/**
	 * @description It checks the flight occupancy details
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "viewOccupancyDetails.html")
	public String viewOccupancyDetails(Model model) {
		int a[];
		try {
			a = airlineService.flightOccupancyDetails("SG-3309");
			int b = a[0];
			int c = a[1];
			int d = a[2];
			int e = a[3];
			model.addAttribute("message1", b);
			model.addAttribute("message2", c);
			model.addAttribute("message3", d);
			model.addAttribute("message4", e);
		} catch (Exception e1) {
			e1.getMessage();
		}

		return "success";
	}

	/**
	 * @description It calls viewListOfFlights function of AirlineServiceImpl then book the flights
	 * @param bookingInformation
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/showBooking", method = RequestMethod.POST)
>>>>>>> 4645ab32cc6d8bba835d3446134e5292e7ee89aa
	public String bookFlight(
			@ModelAttribute(ARSConstants.BOOKING) BookingInformation bookingInformation,
			Model model, HttpSession session) {
		try {
			List<Flight> flights = airlineService.viewListOfFlights(
					bookingInformation.getFlightNo(), ARSConstants.FLIGHTNO);
			if (ARSConstants.FIRST.equalsIgnoreCase(bookingInformation
					.getClassType())) {
				bookingInformation.setTotalFare(MyUtil.calculatefare(
						bookingInformation.getNoOfPassengers(), flights.get(0)
								.getFirstSeatsFare()));
			} else if (ARSConstants.BUSINESS
					.equalsIgnoreCase(bookingInformation.getClassType())) {
				bookingInformation.setTotalFare(MyUtil.calculatefare(
						bookingInformation.getNoOfPassengers(), flights.get(0)
								.getBussSeatsFare()));
			}
			bookingInformation.setUserEmail(((User) session
					.getAttribute(ARSConstants.USER)).getEmail());
			bookingInformation.setBookingDate(Date.valueOf(LocalDate.now()));
			model.addAttribute(ARSConstants.FLIGHT, flights.get(0));
			model.addAttribute(ARSConstants.BOOKING, bookingInformation);
		} catch (Exception e) {
			e.getMessage();
		}
		return ARSConstants.BOOKING;

	}

<<<<<<< HEAD
	@RequestMapping(value = ARSConstants.URLCONFIRMBOOKING, method = RequestMethod.POST)
=======
	/**
	 * @description It checks that flight is booked or not
	 * @param bookingInformation
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/confirmBooking", method = RequestMethod.POST)
>>>>>>> 4645ab32cc6d8bba835d3446134e5292e7ee89aa
	public String confirmBooking(
			@ModelAttribute(ARSConstants.BOOKING) BookingInformation bookingInformation,
			Model model) throws Exception {
		try {
			airlineService.confirmBooking(bookingInformation);
			model.addAttribute(
					ARSConstants.MESSAGE,
					"Your flight booking is successful with bookingId-"
							+ bookingInformation.getBookingId()
							+ " for Flight No-"
							+ bookingInformation.getFlightNo());
		} catch (Exception e) {
			model.addAttribute(ARSConstants.MESSAGE, ARSConstants.SERVERERROR + e.getMessage());
		}
		return ARSConstants.BOOKINGSUCCESS;
	}

<<<<<<< HEAD
	@RequestMapping(value = ARSConstants.URLUPDATEUSER, method = RequestMethod.POST)
=======
	/**
	 * @description It updates the user details
	 * @param user
	 * @param bindingResult
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
>>>>>>> 4645ab32cc6d8bba835d3446134e5292e7ee89aa
	public String updateUser(
			@ModelAttribute(ARSConstants.USEROBJ) @Valid User user,
			BindingResult bindingResult, Model model) {
		try {
			model.addAttribute(ARSConstants.BOOKINGS, airlineService
					.viewBookings(user.getUsername(), ARSConstants.BYUSER));
			if (bindingResult.hasErrors()) {

				model.addAttribute(ARSConstants.USEROBJ, user);
			} else {

				user.setRole(ARSConstants.CUSTOMER);
				airlineService.updateUser(user);

				model.addAttribute(ARSConstants.MESSAGE, "Information updated successfully");
				model.addAttribute(ARSConstants.USEROBJ, user);

			}
		} catch (Exception e) {
			model.addAttribute(ARSConstants.MESSAGE, ARSConstants.SERVERERROR + e.getMessage());
		}
		return ARSConstants.USERPROFILE;
	}

<<<<<<< HEAD
	@RequestMapping(value = ARSConstants.URLSHOWPROFILE)
=======
	/**
	 * @description It shows user profile
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/showUserProfile")
>>>>>>> 4645ab32cc6d8bba835d3446134e5292e7ee89aa
	public String showUserProfile(Model model, HttpSession session) {
		User user = (User) session.getAttribute(ARSConstants.USER);
		model.addAttribute(ARSConstants.USEROBJ, user);
		try {
			model.addAttribute(ARSConstants.BOOKINGS, airlineService
					.viewBookings(user.getUsername(), ARSConstants.BYUSER));
		} catch (Exception e) {
			model.addAttribute(ARSConstants.MESSAGE, ARSConstants.SERVERERROR + e.getMessage());
		}
		return ARSConstants.USERPROFILE;
	}

<<<<<<< HEAD
	@RequestMapping(value = ARSConstants.URLCANCELBOOKING, method = RequestMethod.GET)
=======
	/**
	 * @description It cancels the booking of flight
	 * @param bookingId
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/cancelBooking", method = RequestMethod.GET)
>>>>>>> 4645ab32cc6d8bba835d3446134e5292e7ee89aa
	public String cancelBooking(
			@RequestParam(ARSConstants.BOOKINGID) int bookingId, Model model,
			HttpSession session) {
		User user = (User) session.getAttribute(ARSConstants.USER);
		model.addAttribute(ARSConstants.USEROBJ, user);

		try {
			BookingInformation booking = airlineService
					.bookingCancel(bookingId);
			model.addAttribute(ARSConstants.BOOKINGS, airlineService
					.viewBookings(user.getUsername(), ARSConstants.BYUSER));
			model.addAttribute(ARSConstants.MESSAGE, ARSConstants.TICKETCANCEL+
					+ booking.getBookingId());
		} catch (Exception e) {
			model.addAttribute(ARSConstants.MESSAGE, ARSConstants.SERVERERROR + e.getMessage());
		}
		return ARSConstants.USERPROFILE;
	}

<<<<<<< HEAD
	@RequestMapping(value = ARSConstants.URLVIEWBOOKING, method = RequestMethod.GET)
	public String viewBooking(@RequestParam(ARSConstants.BOOKINGID) String bookingId,
=======
	/**
	 * @description It gives the booking details of flight
	 * @param bookingId
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/viewBooking", method = RequestMethod.GET)
	public String viewBooking(@RequestParam("bookingId") String bookingId,
>>>>>>> 4645ab32cc6d8bba835d3446134e5292e7ee89aa
			Model model, HttpSession session) {
		try {
			model.addAttribute(ARSConstants.BOOKING,
					airlineService.viewBookings(bookingId, ARSConstants.BYBOOKINGID)
							.get(0));
		} catch (Exception e) {
			model.addAttribute(ARSConstants.MESSAGE, ARSConstants.SERVERERROR + e.getMessage());
		}
		return ARSConstants.BOOKINGDETAILS;
	}

<<<<<<< HEAD
	@RequestMapping(value = ARSConstants.URLSHOWFORGOTPWD)
=======
	/**
	 * @description it adds user object to model
	 * @param model
	 * @return
	 */
	@RequestMapping("/showForgotPassword")
>>>>>>> 4645ab32cc6d8bba835d3446134e5292e7ee89aa
	public String showForgotPassword(Model model) {
		model.addAttribute(ARSConstants.USEROBJ, new User());
		return ARSConstants.FORGOTPWD;
	}

<<<<<<< HEAD
	@RequestMapping(value = ARSConstants.URLFORGOTPWD, method = RequestMethod.POST)
	public String forgotPassword(@ModelAttribute(ARSConstants.USEROBJ) User user,
=======
	/**
	 * @description 
	 * @param user
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/forgotPassword", method = RequestMethod.POST)
	public String forgotPassword(@ModelAttribute("userObj") User user,
>>>>>>> 4645ab32cc6d8bba835d3446134e5292e7ee89aa
			Model model, HttpSession session) {
		String returnPage;
		try {
			user = airlineService.forgotPassword(user);
			model.addAttribute(ARSConstants.MESSAGE,
					ARSConstants.PWDCHANGED);
			model.addAttribute(ARSConstants.USER, new User());
			returnPage = ARSConstants.LOGIN;
		} catch (Exception e) {
			model.addAttribute(ARSConstants.MESSAGE, ARSConstants.SERVERERROR + e.getMessage());
			model.addAttribute(ARSConstants.USEROBJ, new User());
			returnPage = ARSConstants.FORGOTPWD;
		}
		return returnPage;
	}
}
