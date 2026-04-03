package com.green;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.green.carproduct.carProductDTO;
import com.green.carproduct.carProductService;

@Controller
public class HomeController {
	// http://localhost:8090, 또는 http://localhost:8090/
	
	@Autowired
	carProductService carService;
	
	@GetMapping({"","/"})
	public String home(Model model) {
		System.out.println("HOMECONTROLLER 확인");
		List<carProductDTO> carlist = carService.getAllCarProduct();
		model.addAttribute("carlist", carlist);
		return "home";
	}
}
