package com.cg.as.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.cg.as.entity.BookingInformation;
import com.cg.as.entity.Flight;
import com.cg.as.entity.User;
import com.cg.as.exception.AirlineException;
import com.cg.as.service.IAirlineService;

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
		} catch (AirlineException e) {
			e.printStackTrace();
		}
		return "flightList";
	}

	/**
	 * @param model
	 * @return
	 */
	@RequestMapping("/index")
	public String success(Model model) {
		model.addAttribute("user", new User());
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
		model.addAttribute("user", new User());
		return "signup";
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
				model.addAttribute("date", Date.valueOf(LocalDate.now()));
				returnPage = "index";
			}

		} catch (Exception e) {
			model.addAttribute("error", "Invalid Username/Password, Try Again");
			model.addAttribute("user", new User());
			returnPage = "login";
		}
		return returnPage;
	}

	@RequestMapping("/loginAfterSearch")
	public String loginValidationAfterSearch(@ModelAttribute("user") User user,
			Model model, HttpServletRequest req,
			HttpSession session) {
		String returnPage = "";
		model.addAttribute("booking", session.getAttribute("bookingInfo"));
		try {
			user = airlineService.validLogin(user);
			if (user != null) {

				returnPage = "booking";
			}

		} catch (Exception e) {
			model.addAttribute("error", "Invalid Username/Password, Try Again");
			model.addAttribute("user", new User());

			returnPage = "login";
		}

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

	@RequestMapping(value = "/booking", method = RequestMethod.POST)
	public String bookFlight(
			@ModelAttribute("booking") BookingInformation bookingInformation) {
	
		return "index";
	}
	
}
