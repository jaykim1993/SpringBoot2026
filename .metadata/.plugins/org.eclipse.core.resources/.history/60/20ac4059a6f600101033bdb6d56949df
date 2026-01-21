package com.green.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class OrderController {
	
	@GetMapping("/product/order")
	public String order() {
		return "Order";
	}
	
	@PostMapping("/product/orderResult")
	public ModelAndView orderResult(
			@RequestParam("itemName") String itemName,
			@RequestParam("itemPrice") int itemPrice,
			@RequestParam("itemQty") int itemQty,
			@RequestParam("ordererName") String ordererName
			) {
		
		int totalPrice = itemPrice*itemQty;
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("ordered_itemName", itemName);
		modelView.addObject("ordered_itemPrice", itemPrice);
		modelView.addObject("ordered_itemQty", itemQty);
		modelView.addObject("ordererName", ordererName);
		modelView.setViewName("OrderResult");
		modelView.addObject("totalPrice", totalPrice);
		
		
		return modelView;
		
	}
	
}
