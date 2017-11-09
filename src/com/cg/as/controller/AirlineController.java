package com.cg.as.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	@RequestMapping(value="/listOfFlights.html")
	public String getAllFlights(@ModelAttribute("booking") BookingInformation bookingInformation, Model model){
		try {
			List<Flight> flights = airlineService.viewListOfFlights("all", "all");
			model.addAttribute("flights", flights);	
		} catch (AirlineException e) {
			e.getMessage();
		}
		return "flightList";
	}
	
	/*@RequestMapping(value="/booking.html")
	public String getAllBookings(Model model){
		try {
			List<BookingInformation> bookings = airlineService.viewBookings("intu", "byUser");
			model.addAttribute("bookings", bookings);
			
		} catch (AirlineException e) {
			e.getMessage();
		}
		return "../../index";
	}*/
	
	@RequestMapping("/menu")
	public String success(Model model){
		model.addAttribute("booking", new BookingInformation());
		return "index";
	}
	
	@RequestMapping("/login")
	public String login()
	{
		return "login_signUp";
	}
	
	
	@RequestMapping("/loginCheck")
	public String loginValidation(@RequestParam("username") String username, @RequestParam("password") String password)
	{
		User user;
		try {
			user = airlineService.validLogin(username, password);
			if (username.equals(user.getUsername())) {
				return "success";
			}else {
				return "error";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

}
