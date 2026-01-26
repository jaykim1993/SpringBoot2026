package com.green;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
// 회원 관련 화면 : @PostMapping -> html action method="POST" 설정
// 공개 정보 관련 화면 : @GetMapping -> html action 메서드 디폴트는 get
@Controller
public class MemberController {
	// DI(의존성 객체 주입)
	// MemberController가 직접 MemberService를 생성하지 않고
	// 스프링 컨테이너가 만든 MemberService를 주입시켜라
	// AppConfig 쓰거나 서비스 클래스 내 @Service 표기 필요
	@Autowired
	MemberService memberService;
	
	// 아래 작성한 메서드는 핸들러 메서드
	// 회원가입 페이지
	@GetMapping("/member/signup") // http://localhost:8090/member/signup
	public String SingupForm() {
		return "SignUpForm"; // src/main/recources/templates 이하 해당 html 파일명 입력
	}
	
	// 로그인 페이지
	@GetMapping("/member/signin") // http://localhost:8090/member/signin
	public String SinginForm() {
		return "SignInForm"; // src/main/recources/templates 이하 해당 html 파일명 입력
	}
	
	@PostMapping("/member/signup_confirm")
	public ModelAndView SignUpConfirm(MemberDTO mdto) {
		
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("YY-MM-dd HH:mm:ss");
		// ModelAndView 객체 생성
		ModelAndView modelView = new ModelAndView();
		// MemberService 비즈니스 로직 담당 클래스 객체 생성
		memberService.signUpConfirm(mdto);
		
		// model 담기
		modelView.addObject("now", sdf.format(now));
		modelView.addObject("new_id", mdto.getId());
		modelView.addObject("new_pw", mdto.getPw());
		modelView.addObject("new_email", mdto.getEmail());
		modelView.setViewName("SignUpResult");
		return modelView;
	}
	
	
	// 로그인하기 메서드 - 방법 2. ModelAndView
	@PostMapping("/member/signin_confirm")
	public ModelAndView SignInConfirm(MemberDTO mdto) {
		memberService.signInConfirm(mdto);
		
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("login_id", mdto.getId());
		modelView.addObject("login_pw", mdto.getPw());
		modelView.setViewName("SignInResult");
		return modelView;
	}
	


}


// 1.UI => input 자료 입력
// 2.DTO => 가방에 넣는다.
// 3. Controller => 출력하고 싶은 페이지 Mapping() URL로 
					// DTO의 자료를 .getter()로 꺼내 사용
					// ModelAndView에 넣고
					// 출력 페이지에 담아서 보낸다.
// 4. DAO => 쿼리문들의 집합으로 DB와 연동, 원하는 조건의 메소드 접근 가능
			// Controller, Service 클래스 모두 @Autowired를 이용하여 접근가능
// 5. Service => 비즈니스 로직을 담당하는 클래스로 DAO, DTO를 DI로 외부에서 객체 삽입받아 메소드 접근
