package com.cg.as.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cg.as.entity.BookingInformation;
import com.cg.as.entity.Flight;
import com.cg.as.entity.User;
import com.cg.as.exception.AirlineException;
import com.cg.as.service.IAirlineService;

@Controller
public class AirlineController {

	@Autowired
	IAirlineService airlineService;

	@RequestMapping(value = "/listOfFlights", method = RequestMethod.POST)
	public String getAllFlights(
			@ModelAttribute("booking") BookingInformation bookingInformation,
			Model model) {
		try {
			String str = bookingInformation.getSrcCity()+"="+bookingInformation.getDestCity()+"="+bookingInformation.getTravelDate();
			List<Flight> flights = airlineService.viewListOfFlights(str, "byUser");


			model.addAttribute("flights", flights);
		} catch (AirlineException e) {
			e.printStackTrace();
		}
		return "flightList";
	}

	/*
	 * @RequestMapping(value="/booking.html") public String getAllBookings(Model
	 * model){ try { List<BookingInformation> bookings =
	 * airlineService.viewBookings("intu", "byUser");
	 * model.addAttribute("bookings", bookings);
	 * 
	 * } catch (AirlineException e) { e.getMessage(); } return "../../index"; }
	 */

	@RequestMapping("/menu")
	public String success(Model model) {
		model.addAttribute("booking", new BookingInformation());
		return "index";
	}

	/*
	 * @RequestMapping("/login") public String login() { return "login_signUp";
	 * }
	 */

	@RequestMapping("/loginCheck")
	public String loginValidation(@RequestParam("username") String username,
			@RequestParam("password") String password, Model model) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);

		try {
			user = airlineService.validLogin(user);
			if (username.equals(user.getUsername())) {
				model.addAttribute("booking", new BookingInformation());
				model.addAttribute("message", "Welcome " + user.getUsername());
				return "index";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("message", "Invalid username/password");
		return "login_signUp";
	}

	/*
	 * @RequestMapping("/login") public String login(Model model){
	 * model.addAttribute("user", new User()); return "Client"; }
	 */

	@RequestMapping(value = "/validLogin", method = RequestMethod.POST)
	public String validLogin(@ModelAttribute("user") User user,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "Client";
		} else {
			System.out.println(user.getUsername());
			model.addAttribute("hi", user.getUsername());
			return "msg";
		}
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
		} catch (AirlineException e1) {
			e1.printStackTrace();
		}
		
		return "success";
	}
	
	
}
