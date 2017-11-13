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

	User user;
	private String flightList = "flightList";
	private String index = "index";
	private String login = "login";
	private String signup = "signup";
	private String message = "message";
	private String bookingSuccess = "bookingSuccess";
	private String userProfile = "userProfile";

	@RequestMapping(value = "/listOfFlights", method = RequestMethod.POST)
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
						"Source and Destination cannot be same");
			}
		} catch (Exception e) {

			model.addAttribute(message, "Server Error: " + e.getMessage());
			return index;
		}
		return flightList;
	}

	/**
	 * @param model
	 * @return
	 */
	@RequestMapping("/index")
	public String showHome(Model model, HttpSession session) {
		try {
			if (session.getAttribute(ARSConstants.USER) == null) {
				model.addAttribute(ARSConstants.USER, new User());
			} else {
				model.addAttribute(ARSConstants.USER,
						session.getAttribute("user"));
			}
			model.addAttribute(ARSConstants.BOOKING, new BookingInformation());
			model.addAttribute(ARSConstants.CLASSTYPEOPTION, new String[] {
					ARSConstants.FIRST, ARSConstants.BUSINESS });

			model.addAttribute(ARSConstants.AIRPORT, airlineService.getCities());
			model.addAttribute(ARSConstants.DATE, Date.valueOf(LocalDate.now()));
		} catch (Exception e) {
			model.addAttribute(message, "Server Error: " + e.getMessage());
		}
		return index;
	}

	@RequestMapping(value = "/showLogin")
	public String showLogin(Model model) {
		model.addAttribute(ARSConstants.BOOKING, new BookingInformation());
		model.addAttribute(ARSConstants.USER, new User());
		return login;
	}

	@RequestMapping(value = "/showLoginAfterSearch", method = RequestMethod.POST)
	public String showLoginAfterSearch(
			Model model,
			@ModelAttribute(ARSConstants.BOOKING) BookingInformation bookingInformation) {
		model.addAttribute(ARSConstants.USER, new User());
		model.addAttribute(ARSConstants.BOOKING, bookingInformation);
		return login;
	}

	@RequestMapping("/showSignup")
	public String showSignup(Model model) {
		model.addAttribute(ARSConstants.USEROBJ, new User());
		return signup;
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(Model model,
			@Valid @ModelAttribute(ARSConstants.USEROBJ) User user,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			model.addAttribute(ARSConstants.USEROBJ, user);
			return signup;
		} else {
			try {
				user.setRole(ARSConstants.CUSTOMER);
				if (!airlineService.checkAvailabiltiy(user.getUsername(),
						ARSConstants.BYUSERNAME)) {
					throw new AirlineException("This Username is already taken");
				}
				if (!airlineService.checkAvailabiltiy(user.getEmail(),
						ARSConstants.BYEMAIL)) {
					throw new AirlineException("This Email is already taken");
				}
				airlineService.signUp(user);
				model.addAttribute(message, "Signup successful, Login here");
				model.addAttribute(ARSConstants.USER, new User());
				return login;
			} catch (Exception e) {
				model.addAttribute(message, e.getMessage());
				model.addAttribute(ARSConstants.BYUSER, user);
				return signup;
			}
		}

	}

	@RequestMapping("/logout")
	public String logout(Model model, SessionStatus status) {
		status.setComplete();
		model.addAttribute(ARSConstants.BOOKING, new BookingInformation());
		model.addAttribute(ARSConstants.CLASSTYPEOPTION, new String[] {
				ARSConstants.FIRST, ARSConstants.BUSINESS });
		model.addAttribute(ARSConstants.DATE, Date.valueOf(LocalDate.now()));
		return index;
	}

	@RequestMapping("/login")
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
				returnPage = index;
			}

		} catch (Exception e) {
			model.addAttribute(message, "Invalid Username/Password, Try Again");
			model.addAttribute(ARSConstants.USER, new User());
			returnPage = login;
		}
		return returnPage;
	}

	@RequestMapping("/loginAfterSearch")
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
			model.addAttribute(message, "Invalid Username/Password, Try Again");
			model.addAttribute("user", new User());

			returnPage = login;
		}
		model.addAttribute(ARSConstants.USER, user);
		model.addAttribute(ARSConstants.BOOKING, bookingInformation);
		return returnPage;
	}

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

	@RequestMapping(value = "/showBooking", method = RequestMethod.POST)
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

	@RequestMapping(value = "/confirmBooking", method = RequestMethod.POST)
	public String confirmBooking(
			@ModelAttribute(ARSConstants.BOOKING) BookingInformation bookingInformation,
			Model model) throws Exception {
		try {
			airlineService.confirmBooking(bookingInformation);
			model.addAttribute(
					message,
					"Your flight booking is successful with bookingId-"
							+ bookingInformation.getBookingId()
							+ " for Flight No-"
							+ bookingInformation.getFlightNo());
		} catch (Exception e) {
			model.addAttribute(message, "Server Error: " + e.getMessage());
		}
		return bookingSuccess;
	}

	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
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

				model.addAttribute(message, "Information updated successfully");
				model.addAttribute(ARSConstants.USEROBJ, user);

			}
		} catch (Exception e) {
			model.addAttribute(message, "Server Error: " + e.getMessage());
		}
		return userProfile;
	}

	@RequestMapping(value = "/showUserProfile")
	public String showUserProfile(Model model, HttpSession session) {
		User user = (User) session.getAttribute(ARSConstants.USER);
		model.addAttribute(ARSConstants.USEROBJ, user);
		try {
			model.addAttribute(ARSConstants.BOOKINGS, airlineService
					.viewBookings(user.getUsername(), ARSConstants.BYUSER));
		} catch (Exception e) {
			model.addAttribute(message, "Server Error: " + e.getMessage());
		}
		return userProfile;
	}

	@RequestMapping(value = "/cancelBooking", method = RequestMethod.GET)
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
			model.addAttribute("message", "Your booking with booking Id-"
					+ booking.getBookingId() + " is successfully cancelled");
		} catch (Exception e) {
			model.addAttribute(message, "Server Error: " + e.getMessage());
		}
		return userProfile;
	}

	@RequestMapping(value = "/viewBooking", method = RequestMethod.GET)
	public String viewBooking(@RequestParam("bookingId") String bookingId,
			Model model, HttpSession session) {
		try {
			model.addAttribute("booking",
					airlineService.viewBookings(bookingId, "byBookingId")
							.get(0));
		} catch (Exception e) {
			model.addAttribute("message", "Server Error: " + e.getMessage());
		}
		return "bookingDetails";
	}

	@RequestMapping("/showForgotPassword")
	public String showForgotPassword(Model model) {
		model.addAttribute("userObj", new User());
		return "forgotPassword";
	}

	@RequestMapping(value = "/forgotPassword", method = RequestMethod.POST)
	public String forgotPassword(@ModelAttribute("userObj") User user,
			Model model, HttpSession session) {
		String returnPage;
		try {
			user = airlineService.forgotPassword(user);
			model.addAttribute("message",
					"Password changed successfully, Login here");
			model.addAttribute("user", new User());
			returnPage = "login";
		} catch (Exception e) {
			model.addAttribute("message", "Server Error: " + e.getMessage());
			model.addAttribute("userObj", new User());
			returnPage = "forgotPassword";
		}
		return returnPage;
	}
}
