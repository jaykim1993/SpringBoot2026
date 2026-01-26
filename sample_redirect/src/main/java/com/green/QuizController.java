package com.green;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class QuizController {

	@GetMapping("/quiz")
	public String quizPage() {
		return "quiz-view";
	}
	
	@GetMapping("/check")
	public String checkPage(
			@RequestParam("pass") String pass,
			RedirectAttributes re
			) {
			if(pass.equals("1234")){ // 정답이면 main 주소로 이동 (메세지 들고 간다)
				re.addFlashAttribute("msg","리다이렉트를 통해 안전하게 메인으로 이동하였습니다.");
				return "redirect:/main";
			} else { // 오답이면 quiz 주소로 이동 (메세지 들고 간다)
				re.addFlashAttribute("msg","비밀번호가 틀렸습니다. 다시 시도하세요.");
				return "redirect:/quiz";
			}
	}
	
	@GetMapping("/main")
	public String mainPage() {
		return "main-view";
	}
}
