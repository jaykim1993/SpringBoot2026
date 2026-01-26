package com.green.controller;




import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.green.dao.BookDAO;
import com.green.dto.BookDTO;
import com.green.dto.RentalDTO;
import com.green.service.BookService;




@Controller
public class BookController {
	
	@Autowired
	BookService bookservice;
	// 도서 등록 페이지
	@GetMapping("/book/addbook")
	public String addBook() {
		return "BookAdd";
	}

	// 도서 리스트
	@PostMapping("/book/booklist")
	public ModelAndView booklist(BookDTO bdto) {
		bookservice.addConfirm(bdto);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("book", BookDAO.bookDB);

		mv.setViewName("BookList");
		return mv;
	}


	
	// 도서대여 페이지
	@GetMapping("/book/rent")
	public String rentPage() {
		return "BookRent";
	}
	// 대여 확인 페이지
	@PostMapping("/book/rentList")
	public ModelAndView rentalProc(
			RentalDTO rdto,
	        @RequestParam(value="sDate") LocalDate sDate,
	        @RequestParam(value="eDate") LocalDate eDate,
			@RequestParam(value = "user") String user
			) {
		bookservice.rentConfirm(rdto);
		

		ModelAndView mv = new ModelAndView();
		mv.addObject("sDate", sDate);
		mv.addObject("eDate", eDate);
		mv.addObject("user", user);
		mv.addObject("book", rdto);

		mv.setViewName("BookRentList");
		return mv;
	}
	
}

//1.UI => input 자료 입력
//2.DTO => 가방에 넣는다.
//3. Controller => 출력하고 싶은 페이지 Mapping() URL로 
					// DTO의 자료를 .getter()로 꺼내 사용
					// ModelAndView에 넣고
					// 출력 페이지에 담아서 보낸다.
//4. DAO => 쿼리문들의 집합으로 DB와 연동, 원하는 조건의 메소드 접근 가능
			// Controller, Service 클래스 모두 @Autowired를 이용하여 접근가능
//5. Service => 비즈니스 로직을 담당하는 클래스로 DAO, DTO를 DI로 외부에서 객체 삽입받아 메소드 접근
