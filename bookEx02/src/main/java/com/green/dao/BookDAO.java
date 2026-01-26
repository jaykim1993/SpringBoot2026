package com.green.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.green.dto.BookDTO;
import com.green.dto.RentalDTO;



@Repository
public class BookDAO {

	public static Map<String, BookDTO> bookDB = new HashMap<>();
	public Map<String, RentalDTO> rentDB = new HashMap<>();



	// 책 목록 DB SQL
	public BookDTO insertBook(BookDTO bdto) {
		System.out.println("책DB 자료 추가하기");
		bookDB.put(bdto.getIsbn(), bdto);
		BookDTO bookInfo = bookDB.get(bdto.getIsbn());
		printBookInfo();
		return bookInfo;
	}
	

	// 대여목록 DB SQL
	public RentalDTO insertRent(RentalDTO rdto) {
		System.out.println("대여DB 자료 추가하기");
		rentDB.put(rdto.getIsbn(), rdto);
		RentalDTO rentInfo = rentDB.get(rdto.getIsbn());
		printRentInfo();
		return rentInfo;
	}
	
	// 책 목록 로그 출력
	public void printBookInfo() {
		System.out.println("책목록 콘솔");
		for(String key : bookDB.keySet()) {
			BookDTO bdto = bookDB.get(key);
			System.out.println("책제목 : "+ bdto.getTitle());
			System.out.println("저자 : "+ bdto.getAuthor());
			System.out.println("isbn : "+ bdto.getIsbn());
		}
	}
	// 대여 로그 출력
	public void printRentInfo() {
		System.out.println("대여 목록 콘솔");
		for(String key : rentDB.keySet()) {
			RentalDTO rdto = rentDB.get(key);
			System.out.println("책제목 : "+ rdto.getTitle());
			System.out.println("저자 : "+ rdto.getAuthor());
			System.out.println("대여자 : "+ rdto.getUser());
			System.out.println("대여일자 : "+ rdto.getsDate());
			System.out.println("반납일자 : "+ rdto.geteDate());
		}
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
