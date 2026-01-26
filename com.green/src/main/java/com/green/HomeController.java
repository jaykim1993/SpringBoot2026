package com.green;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	// http://localhost:8090, 또는 http://localhost:8090/
	@GetMapping({"","/"})
	public String home() {
		System.out.println("HOMECONTROLLER 확인");
		return "home";
	}
}
