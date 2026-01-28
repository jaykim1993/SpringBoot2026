package com.green.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	// 1. 게시글 작성 폼화면으로 이동하는 핸들러
	@GetMapping("/board/write")
	public String boardWriteForm(){
		System.out.println("1) B-Controller boardWriteForm");
		String nextPage = "board/boardWrite_form";
		return nextPage;
	}
	
	// 2. form에서 입력한 데이터를 DB에 저장하는 핸들러
	@PostMapping("/board/writePro")
	public String boardWritePro(BoardDTO bdto) {
		System.out.println("1) B-Controller boardWritePro");
		// 서비스의 insertBoardService 호출하여 DB에 저장
		boardService.insertBoardService(bdto);
		// 저장 후 게시판 목록으로 페이지 이동
		return "redirect:/board/list";
	}
	
	// 3. 전체 글 목록페이지로 이동하는 핸들러
	@GetMapping("/board/list")
	public String boardList(Model model){
		System.out.println("1) B-Controller boardList");
		List<BoardDTO> listboard = boardService.allBoardService();
		model.addAttribute("list", listboard);
		String nextPage = "board/boardList";
		return nextPage;
	}
	
	// 4. 하나의 게시글 상세정보 확인 핸들러
	@GetMapping("/board/boardInfo")
	public String boardInfo(
			Model model, 
			@RequestParam("num") int num
			) {
		System.out.println("1) B-Controller boardInfo");
		BoardDTO oneboardInfo = boardService.oneBoardService(num);
		model.addAttribute("oneboard", oneboardInfo);
		
		String nextPage = "board/boardInfo";
		return nextPage;
	}
	
	// 5. 게시글 수정 폼 이동 핸들러
	@GetMapping("/board/update")
	public String boardUpdateForm(
			@RequestParam("num") int num,
			Model model
			) {
		System.out.println("1) B-Controller boardUpdateForm / num = "+num);
		BoardDTO oneboardInfo = boardService.oneBoardService(num);
		model.addAttribute("oneboard", oneboardInfo);
		
		String nextPage = "board/boardUpdate_form";
		return nextPage;
	}
	
	// 6. 게시글 수정 처리 핸들러
	@PostMapping("/board/updatePro")
	public String boardUpdatePro(BoardDTO bdto, RedirectAttributes ra) {

	    boolean isSuccess = boardService.updateService(bdto);
	    System.out.println("1) B-Controller boardUpdatePro / 수정 됨? = " + isSuccess);

	    if (isSuccess) {
	        ra.addFlashAttribute("msg", "게시글이 수정되었습니다.");
	        return "redirect:/board/list";
	    } else {
	        ra.addFlashAttribute("msg", "비밀번호가 일치하지 않습니다.");
	        return "redirect:/board/update?num=" + bdto.getNum();
	    }
	}
	
}
