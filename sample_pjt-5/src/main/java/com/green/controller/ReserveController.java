package com.green.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ReserveController {
	
	@GetMapping("/movie/reserve")
	public String reserve() {
		return "reserve";
	}
	
	@PostMapping("/movie/reserveConfirm")
	public ModelAndView orderResult(
			@RequestParam("movieTitle") String movieTitle,
			@RequestParam("reserveDate") String reserveDate,
			@RequestParam("reserveTime") String reserveTime,
			@RequestParam("people") int people,
			@RequestParam("reserverName") String reserverName
			) {
		
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("movieTitle", movieTitle);
		modelView.addObject("reserveDate", reserveDate);
		modelView.addObject("reserveTime", reserveTime);
		modelView.addObject("people", people);
		modelView.addObject("reserverName", reserverName);
		modelView.setViewName("reserveConfirm");
		

		return modelView;
		
	}
	
}
