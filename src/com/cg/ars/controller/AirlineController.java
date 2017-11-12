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

	@RequestMapping(value = "/listOfFlights", method = RequestMethod.POST)
	public String getAllFlights(
			@ModelAttribute("booking") BookingInformation bookingInformation,
			Model model) {
		try {
			String str = bookingInformation.getSrcCity() + "="
					+ bookingInformation.getDestCity() + "="
					+ bookingInformation.getTravelDate();
			List<Flight> flights = airlineService.viewListOfFlights(str,
					"byUser");
			if (flights.size() == 0) {
				model.addAttribute("flights", null);
			} else {
				model.addAttribute("flights", flights);
			}
			model.addAttribute("booking", bookingInformation);


			model.addAttribute("classTypeOptions", new String[] { "First",
					"Business" });
		} catch (Exception e) {
			e.getMessage();
		}
		return "flightList";
	}

	/**
	 * @param model
	 * @return
	 */
	@RequestMapping("/index")
	public String showHome(Model model, HttpSession session) {
		if (session.getAttribute("user") == null) {
			model.addAttribute("user", new User());
		} else {
			model.addAttribute("user", session.getAttribute("user"));
		}
		model.addAttribute("booking", new BookingInformation());
		model.addAttribute("classTypeOptions", new String[] { "First",
				"Business" });
		model.addAttribute("date", Date.valueOf(LocalDate.now()));
		return "index";
	}

	@RequestMapping(value = "/showLogin")
	public String showLogin(Model model) {
		model.addAttribute("booking", new BookingInformation());
		model.addAttribute("user", new User());
		return "login";
	}

	@RequestMapping(value = "/showLoginAfterSearch", method = RequestMethod.POST)
	public String showLoginAfterSearch(Model model,
			@ModelAttribute("booking") BookingInformation bookingInformation) {
		model.addAttribute("user", new User());
		model.addAttribute("booking", bookingInformation);
		return "login";
	}


	@RequestMapping("/showSignup")
	public String showSignup(Model model) {
		model.addAttribute("userObj", new User());
		return "signup";
	}
	
	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public String signup(Model model,@Valid @ModelAttribute("userObj") User user,BindingResult bindingResult) {
		if(bindingResult.hasErrors()){
			model.addAttribute("userObj", user);
			return "signup";
		}else{
			try {
				user.setRole("customer");
				if(!airlineService.checkAvailabiltiy(user.getUsername(), "byUsername")){
					throw new AirlineException("This Username is already taken");
				}
				if(!airlineService.checkAvailabiltiy(user.getEmail(), "byEmail")){
					throw new AirlineException("This Email is already taken");
				}
				airlineService.signUp(user);
				model.addAttribute("message", "Signup successful, Login here");
				model.addAttribute("user", new User());
				return "login";
			} catch (Exception e) {
				model.addAttribute("message", e.getMessage());
				model.addAttribute("userObj", user);
				return "signup";
			}
		}
		
	}
	
	@RequestMapping("/logout")
	public String logout(Model model, SessionStatus status) {
		status.setComplete();
		model.addAttribute("booking", new BookingInformation());
		model.addAttribute("classTypeOptions", new String[] { "First",
				"Business" });
		model.addAttribute("date", Date.valueOf(LocalDate.now()));
		return "index";
	}

	@RequestMapping("/login")
	public String loginValidation(@ModelAttribute("user") User user,
			Model model, HttpServletRequest req) {
		String returnPage = "";
		try {
			user = airlineService.validLogin(user);
			if (user != null) {
				model.addAttribute("booking", new BookingInformation());
				model.addAttribute("classTypeOptions", new String[] { "First",
						"Business" });
				model.addAttribute("user", user);
				model.addAttribute("date", Date.valueOf(LocalDate.now()));
				returnPage = "index";
			}

		} catch (Exception e) {
			model.addAttribute("message", "Invalid Username/Password, Try Again");
			model.addAttribute("user", new User());
			returnPage = "login";
		}
		return returnPage;
	}

	@RequestMapping("/loginAfterSearch")
	public String loginValidationAfterSearch(@ModelAttribute("user") User user,
			Model model,
			HttpSession session) {
		String returnPage = "";
		BookingInformation bookingInformation = (BookingInformation)session.getAttribute("bookingInfo");
		

		try {
			user = airlineService.validLogin(user);
			if (user != null) {
				session.removeAttribute("bookingInfo");
				List<Flight> flights = airlineService.viewListOfFlights(
					bookingInformation.getFlightNo(), "flightNo");
				if("First".equalsIgnoreCase(bookingInformation.getClassType())) {
				    bookingInformation.setTotalFare(MyUtil.calculatefare(bookingInformation.getNoOfPassengers(), flights.get(0).getFirstSeatsFare()));
		} else if ("Business".equalsIgnoreCase(bookingInformation
			.getClassType())) {
		    bookingInformation.setTotalFare(MyUtil.calculatefare(
			    bookingInformation.getNoOfPassengers(), flights
				    .get(0).getBussSeatsFare()));
				}
		bookingInformation.setCustEmail(user.getEmail());
		bookingInformation
			.setBookingDate(Date.valueOf(LocalDate.now()));
		model.addAttribute("flight", flights.get(0));
				returnPage = "booking";
			}

		} catch (Exception e) {
			model.addAttribute("message", "Invalid Username/Password, Try Again");
			model.addAttribute("user", new User());

			returnPage = "login";
		}
		model.addAttribute("user", user);
	model.addAttribute("booking", bookingInformation);
		return returnPage;
	}
	
	@RequestMapping(value="viewOccupancyDetails.html")
	public String viewOccupancyDetails(Model model){
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
	    @ModelAttribute("booking") BookingInformation bookingInformation,
	    Model model, HttpSession session) {
	try {
			List<Flight> flights = airlineService.viewListOfFlights(
			bookingInformation.getFlightNo(), "flightNo");
		if ("First".equalsIgnoreCase(bookingInformation.getClassType())) {
		    bookingInformation.setTotalFare(MyUtil.calculatefare(
			    bookingInformation.getNoOfPassengers(), flights.get(0)
				    .getFirstSeatsFare()));
		} else if ("Business".equalsIgnoreCase(bookingInformation
			.getClassType())) {
		    bookingInformation.setTotalFare(MyUtil.calculatefare(
			    bookingInformation.getNoOfPassengers(), flights.get(0)
				    .getBussSeatsFare()));
		}
	    bookingInformation.setCustEmail(((User) session
		    .getAttribute("user")).getEmail());
		bookingInformation.setBookingDate(Date.valueOf(LocalDate.now()));
		model.addAttribute("flight", flights.get(0));
	    model.addAttribute("booking", bookingInformation);
	} catch (Exception e) {
	    e.getMessage();
	}
	return "booking";
	}
    	
    @RequestMapping(value = "/confirmBooking", method = RequestMethod.POST)
    public String confirmBooking(
	    @ModelAttribute("booking") BookingInformation bookingInformation,
			Model model) throws Exception {
		try {
	    airlineService.confirmBooking(bookingInformation);
			model.addAttribute(
					"message",
					"Your flight booking is successful with bookingId-"
							+ bookingInformation.getBookingId()
							+ " for Flight No-"
							+ bookingInformation.getFlightNo());
		} catch (Exception e) {
			model.addAttribute("message", "Server Error: " + e.getMessage());
		}
	return "bookingSuccess";
    }

	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	public String updateUser(@ModelAttribute("userObj") @Valid User user,
			BindingResult bindingResult, Model model) {
		try {
			model.addAttribute("bookings",
					airlineService.viewBookings(user.getUsername(), "byUser"));
		if (bindingResult.hasErrors()) {

			model.addAttribute("userObj", user);
		} else {

				user.setRole("customer");
				airlineService.updateUser(user);

				model.addAttribute("message",
						"Information updated successfully");
				model.addAttribute("userObj", user);



			}
		} catch (Exception e) {
			model.addAttribute("message", "Server Error: " + e.getMessage());
		}
		return "userProfile";
	}

	@RequestMapping(value = "/showUserProfile")
	public String showUserProfile(Model model,HttpSession session) {
			User user = (User)session.getAttribute("user");
			model.addAttribute("userObj", user);
		try {
			List<BookingInformation> bookings = airlineService.viewBookings(
					user.getUsername(), "byUser");
			model.addAttribute("bookings", bookings);
		} catch (Exception e) {
			model.addAttribute("message", "Server Error: " + e.getMessage());
		}
			return "userProfile";
		}

	@RequestMapping(value = "/cancelBooking", method = RequestMethod.GET)
	public String cancelBooking(@RequestParam("bookingId") int bookingId,
			Model model, HttpSession session) {
		User user = (User) session.getAttribute("user");
		model.addAttribute("userObj", user);

		try {
			BookingInformation booking = airlineService
					.bookingCancel(bookingId);
			model.addAttribute("bookings",
					airlineService.viewBookings(user.getUsername(), "byUser"));
			model.addAttribute("message", "Your booking with booking Id-"
					+ booking.getBookingId() + " is successfully cancelled");
		} catch (Exception e) {
			model.addAttribute("message", "Server Error: " + e.getMessage());
		}
		return "userProfile";
	}
}
