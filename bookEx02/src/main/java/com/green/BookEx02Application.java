package com.green;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookEx02Application {

	public static void main(String[] args) {
		SpringApplication.run(BookEx02Application.class, args);
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
