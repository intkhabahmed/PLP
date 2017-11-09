package com.cg.as.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cg.as.entity.BookingInformation;
import com.cg.as.entity.Flight;
import com.cg.as.entity.User;
import com.cg.as.exception.AirlineException;
import com.cg.as.service.IAirlineService;

@Controller
public class AirlineController {
	
	@Autowired
	IAirlineService airlineService;
	
	@RequestMapping("/index")
	public String getAllFlights(Model model){
		try {
			List<Flight> flights = airlineService.viewListOfFlights("all", "all");
			model.addAttribute("flights", flights);
			
		} catch (AirlineException e) {
			e.getMessage();
		}
		return "../../index";
	}
	
	@RequestMapping("/booking")
	public String getAllBookings(Model model){
		try {
			List<BookingInformation> bookings = airlineService.viewBookings("intu", "byUser");
			model.addAttribute("bookings", bookings);
			
		} catch (AirlineException e) {
			e.getMessage();
		}
		return "../../index";
	}
	
	@RequestMapping("/login")
	public String login(Model model){
		model.addAttribute("user", new User());
		return "Client";
	}
	
	@RequestMapping(value="/validLogin",method=RequestMethod.POST)
	public String validLogin(@ModelAttribute("user") User user,BindingResult result,Model model){
		if(result.hasErrors())
		{
			return "Client";
		}
		else
		{
		System.out.println(user.getUsername());
		model.addAttribute("hi",user.getUsername());
		return "msg";
		}
	}

}
