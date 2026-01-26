package com.green.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {
	
	@Autowired
	MemberService memberservice;
	
	// 회원가입 화면
	@GetMapping("/member/signup")
	public String signup() {
		System.out.println("MemberController - signup()");
		String nextPage = "member/signup_form";
		return nextPage;
	}
	
	// 회원가입 결과 학인
	@PostMapping("/member/signup_confirm")
	public String signupConfirm(MemberDTO mdto, Model model) {
		System.out.println("MemberController - signupConfirm()");
		String nextPage="member/signup_result";
		// 회원가입이 제대로 되었는지, 혹은 실패했는지 알려줘
		int result = memberservice.signupConfirm(mdto);
		// 회원 가입 성공 시, 회원 목록 주소로 이동
		if(result == MemberService.user_signup_success) {
			return "redirect:/member/list";
		} else {
			// 회원가입이 실패한 경우
			model.addAttribute("result", result);
			return nextPage;
		}
	}
	
	// 전체 목록 출력 화면
	@GetMapping("/member/list")
	public String memberList(Model model) {
		List<MemberDTO> memberlist = memberservice.allListMember();
		model.addAttribute("list", memberlist);
		String nextPage = "member/memberList";
		return nextPage;
	}
}
