package com.green.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.dao.BookDAO;
import com.green.dto.BookDTO;
import com.green.dto.RentalDTO;


@Service
public class BookService {
	
	@Autowired
	BookDAO bookdao;
	
	public void addConfirm(BookDTO bdto) {
		System.out.println("책 등록 실행");
		BookDTO bookInfo = bookdao.insertBook(bdto);
		if(bookInfo != null ) {
			System.out.println("책 등록 성공");
		} else {
			System.out.println("책 등록 실패");
		}
	}
//	public void findIsbn(String title) {
//		if(BookDAO.bookDB.get(title).getIsbn()!=null) {
//			bookdao.rentDB.put(BookDAO.bookDB.get(title).getIsbn(), null)
//		};
//		
//	}
	public void rentConfirm(RentalDTO rdto) {
		System.out.println("대여 서비스 실행");
//        RentalDTO rentInfo = bookdao.insertRent(rdto);
        
        // 책DB를 탐색하고 해당 책 이 존재하면
        // 유저가 입력한 정보와 합쳐서 대여DB에 넣으며 
        // 그 내용을 출력
		
//		if(rentInfo != null && rentInfo.getTitle().equals(rdto.getTitle())) {
//			System.out.println("책 빌리기 성공");
//		} else {
//			System.out.println("책 빌리기 실패");
//		}
		
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
