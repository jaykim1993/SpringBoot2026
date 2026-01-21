package com.green.controller;



import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class RentController {
	

	// 도서대여 페이지
	@GetMapping("/book/rental")
	public String rentPage() {
		return "Rental";
	}
	
	// 대여 확인 페이지
	@PostMapping("/book/rentalProc")
	public ModelAndView rentalProc(
			@RequestParam("title") List<String> title,
			@RequestParam("author") List<String> author,
			@RequestParam("isbn") List<String> isbn,
			@RequestParam("user") List<String> user
			) {
		
		ModelAndView mv = new ModelAndView();
	    mv.addObject("titles", title);
	    mv.addObject("authors", author);
	    mv.addObject("isbns", isbn);
	    mv.addObject("users", user);

	    mv.setViewName("RentalProc");
		mv.setViewName("RentalProc");
		return mv;
	}
	
	
}
