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
	BoardService boardservice;
	
	
	// 글 쓰기 페이지 핸들러
	@GetMapping("/board/newPost")
	public String newPost() {
		System.out.println("게시글 등록하는 페이지 BoardController-newPost()");
		String nextPage="board/BoardNewPost";
		return nextPage;
	}
	
	// 글 등록 결과 핸들러
	@PostMapping("/board/posting_confirm")
	public String postingConfirm(
			BoardDTO bdto, 
			Model model,
			RedirectAttributes re
			) {
		System.out.println("게시글 등록하는 컨펌 BoardController-postingConfirm()");
		int result = boardservice.postingConfirm(bdto);
		if(result == 1) {
			re.addFlashAttribute("msg", "게시글이 등록되었습니다.");
			return "redirect:/board/main";
		} else {
			re.addFlashAttribute("msg", "오류발생, 다시 작성해주세요.");
			return "redirect:/board/newPost";
		}
	}
	
	// 게시판 목록보기 핸들러
	@GetMapping("/board/main")
	public String boardList(Model model) {
		System.out.println("게시글 전체 보이기 BoardController-boardList()");
		List<BoardDTO> postlist = boardservice.allPost();
		
		model.addAttribute("list", postlist);
		String nextPage = "board/BoardMain";
		return nextPage;
	}
	
	// 개별 글 보기 핸들러
	@GetMapping("/board/detail")
	public String postDetail(
			Model model, 
			BoardDTO bdto ,
			@RequestParam("id") String id
			) {
		System.out.println("글 세부보기 - postDetail()/ 전달된 id = "+ id);
		BoardDTO postDetail = boardservice.postDetail(id);
		model.addAttribute("postDetail", postDetail);
		String nextPage = "board/BoardDetail";
		return nextPage;
	}
	
	// 글 정보 수정 화면 이동 핸들러
	@GetMapping("/board/modify")
	public String postModify(
			Model model, 
			BoardDTO bdto,
			@RequestParam("id") String id
			) {
		System.out.println("글 수정하기 페이지 - postModify()");
		BoardDTO postDetail = boardservice.postDetail(id);
		model.addAttribute("postDetail", postDetail);
		String nextPage = "board/BoardEditPost";
		return nextPage;
	}
	
	// 글 정보 수정 처리 핸들러
	@PostMapping("/board/modify")
	public String modifySubmit(
			BoardDTO bdto,
			RedirectAttributes re
			) {
		System.out.println("글 수정하기 보내기 - modifySubmit()");
		boolean result = boardservice.editPost(bdto);
		
		if(result) {
			re.addFlashAttribute("msg", "글이 수정되었습니다.");
			return "redirect:/board/main";
		} else {
			re.addFlashAttribute("msg", "서버 오류 - 삭제 실패, 다시 시도해주세요.");
			return "redirect:/board/modify?id="+bdto.getId();
		}
	}
	
	// 글 내용 삭제 핸들러
	@GetMapping("/board/delete")
	public String postDelete(
			@RequestParam("id") String id,
			RedirectAttributes re
			) {
		System.out.println("글 삭제하기 - postDelete()/ 전달된 id = "+ id);
		boolean result = boardservice.deletePost(id);
		if(result) {
			re.addFlashAttribute("msg", "글이 삭제되었습니다.");
			return "redirect:/board/main";
		} else {
			re.addFlashAttribute("msg", "서버 오류 - 삭제 실패, 다시 시도해주세요.");
			return "redirect:/board/datail?id="+id;
		}

	}
	
}
