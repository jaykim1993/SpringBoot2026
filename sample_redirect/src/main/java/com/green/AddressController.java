package com.green;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddressController {
	
	// ArrayList
	private List<AddressDTO> addressList = new ArrayList<>();
	
	// 1. 주소록 목록 화면
	@GetMapping("/addresses")
	public String list(Model model) {
		model.addAttribute("list", addressList);
		return "address-list";
	}
	
	// 2. 주소 등록 화면
	@PostMapping("/add-address")
	public String addAddress(AddressDTO adto) {
		addressList.add(adto);
		return "redirect:/addresses";
	}
	
}
