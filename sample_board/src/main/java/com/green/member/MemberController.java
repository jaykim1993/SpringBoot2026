package com.green.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;

@Controller
public class MemberController {
	
	@Autowired
	MemberService memberservice;
	
	// 회원가입 핸들러
	@GetMapping("/member/signup")
	public String signup() {
		System.out.println("MemberController - signup()");
		String nextPage = "member/signup_form";
		return nextPage;
	}
	
	// 회원가입 결과 핸들러
	@PostMapping("/member/signup_confirm")
	public String signupConfirm(MemberDTO mdto, Model model, RedirectAttributes re) {
		System.out.println("MemberController - signupConfirm()");
		String nextPage="member/signup_result";
		// 회원가입이 제대로 되었는지, 혹은 실패했는지 알려줘
		int result = memberservice.signupConfirm(mdto);
		// 회원 가입 성공 시, 회원 목록 주소로 이동
		if(result == MemberService.user_id_success) {
			re.addFlashAttribute("msg", "회원 가입이 성공하였습니다.");
			return "redirect:/";
		} else {
			// 회원가입이 실패한 경우
			model.addAttribute("result", result);
			return nextPage;
		}
	}
	
	// 로그인 양식 폼 이동
	@GetMapping("/member/login")
	public String loginForm() {
		System.out.println("MemberController - loginForm()");
		return "member/login_form";
	}
	// 로그인 확인 처리
	@PostMapping("/member/loginPro")
	public String loginPro(MemberDTO mdto, 
			RedirectAttributes re, 
			HttpSession session
			) {
		System.out.println("MemberController - loginPro()");
		
		MemberDTO loginedMember = memberservice.loginConfirm(mdto);
		
		if(loginedMember != null) {
			session.setAttribute("loginedMember", loginedMember);
			System.out.println("로그인성공, 담긴 id = "+ loginedMember.getId());
			re.addFlashAttribute("msg",loginedMember.getNickname() + "님 환영합니다!");
			return "redirect:/";
		} else {
				re.addFlashAttribute("msg", "아이디 또는 비밀번호가 일치하지 않습니다.");
				return "redirect:/member/login";
		}
	}
	
	// 로그아웃
	@GetMapping("/member/logout")
	public String logout(
			HttpSession session, 
			RedirectAttributes re
			) {
		// 1. 세션 무효화
		session.invalidate();
		
		// 2. 로그아웃 완료 메시지 전달(선택)
		re.addFlashAttribute("msg","로그아웃 되었습니다.");
		
		// 3. 홈 화면으로 리다이렉트
		return "redirect:/";
		
	}

	
}
