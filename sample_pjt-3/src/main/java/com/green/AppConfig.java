package com.green;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
//	@Bean
//	public MemberService memberService() {
//		// Ioc 컨테이너에 MemberService()를 탑재시켜라
//		return new MemberService();
//	}
}

// 환경설정 클래스
// 각 페이지(컨트롤러)에서 직접 인스턴스화하지말고
// config 클래스에서 대신 객체 생성 하고 
// 필요한 클래스에서 @Autowired한다.
// 힙메모리 내 중복되는 객체 생성을 줄이기 위함