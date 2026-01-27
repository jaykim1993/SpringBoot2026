package com.green.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	// 전체 목록 출력 핸들러
	@GetMapping("/member/list")
	public String memberList(Model model) {
		System.out.println("MemberController - memberList()");
		List<MemberDTO> memberlist = memberservice.allListMember();
		model.addAttribute("list", memberlist);
		String nextPage = "member/memberList";
		return nextPage;
	}
	
//-------------------------------------------2026년 1월 27일 Controller 작성 부분------------------------------------------------
	// 개인 정보 상세보기 핸들러
	@GetMapping("/member/memberInfo")
	public String memberInfo(
			Model model, 
			MemberDTO mdto ,
			@RequestParam("id") String id // a태그에서 넘겨준 id를 이용하기 위해 사용
			) {
		System.out.println("MemberController - memberInfo()"+ id);
		MemberDTO onememberInfo = memberservice.oneListMember(id);
//		MemberDTO onememberInfo = memberservice.oneListMember(mdto.getId()); <- RequestParam 없이 할때
		model.addAttribute("oneInfo", onememberInfo);
		String nextPage = "member/memberInfo";
		return nextPage;
	}
	
	// 개인 정보 수정하는 화면으로 이동하는 핸들러
	@GetMapping("/member/modify")
	public String modifyForm(Model model, MemberDTO mdto) {
		System.out.println("MemberController - memberList()");
		MemberDTO onememberInfo = memberservice.oneListMember(mdto.getId());
		model.addAttribute("oneInfo", onememberInfo);
		String nextPage = "member/member_modify";
		return nextPage;
	}
	
	// 개인 정보 수정을 처리하는 핸들러
	// 비밀번호가 일치하는지의 비교에 관련된 핸들러
	@PostMapping("/member/modify")
	public String modifySubmit(
			MemberDTO mdto, 
			RedirectAttributes re
			) {
		System.out.println("MemberController - modifySubmit()");
		boolean result = memberservice.modifyMember(mdto);

		if(result) {
			re.addFlashAttribute("msg", "회원 정보가 수정되었습니다.");
			return "redirect:/member/list";
		} else {
			re.addFlashAttribute("msg", "비밀번호가 틀렸습니다. 다시 입력하세요.");
			return "redirect:/member/modify?id=" + mdto.getId();
		}
	}
		
	// 개인 한 사람의 정보를 삭제하는 핸들러
	@GetMapping("/member/delete")
	public String deleteMember(
			@RequestParam("id") String id, 
			RedirectAttributes re
			) {
		System.out.println("MemberController - deleteMember()");
		boolean result = memberservice.oneDelete(id);
		if(result) {
			re.addFlashAttribute("msg", "회원 정보가 삭제되었습니다.");
			return "redirect:/member/list";
		} else {
			re.addFlashAttribute("msg", "서버 오류 - 삭제 실패, 다시 시도해주세요.");
			return "redirect:/member/memberInfo?id=" + id;
		}
		
	}
	
}
