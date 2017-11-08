package com.cg.as.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.cg.as.service.IAirlineService;

@Controller
public class AirlineController {
	
	@Autowired
	IAirlineService airlineService;
	
	
	
	

}
