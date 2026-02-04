package com.green.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.green.member.MemberDTO;

import jakarta.servlet.http.HttpSession;



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
	        RedirectAttributes re,
	        HttpSession session
	) {
	    System.out.println("게시글 등록 컨펌 BoardController-postingConfirm()");
	    // 1. 로그인 체크
	    MemberDTO loginedMember =
	            (MemberDTO) session.getAttribute("loginedMember");
	    if (loginedMember == null) {
	        re.addFlashAttribute("msg", "로그인 후 이용 가능합니다.");
	        return "redirect:/member/login";
	    }
	    // login 멤버가 존재 할 경우
	    // 2. 로그인 사용자 정보 세팅
	    bdto.setId(loginedMember.getId());
	    // 3. DB 저장
	    int result = boardservice.postingConfirm(bdto);
	    // 4. 결과 처리
	    if (result == 1) {
	        re.addFlashAttribute("msg", "게시글이 등록되었습니다.");
	        return "redirect:/board/main";
	    } else {
	        re.addFlashAttribute("msg", "오류 발생, 다시 작성해주세요.");
	        return "redirect:/board/newPost";
	    }
	}

	
	// 게시판 목록보기 핸들러
	@GetMapping("/board/main")
	public String boardList(
	        Model model,
	        @RequestParam(value="searchType", required=false) String searchType,
	        @RequestParam(value="searchKeyword", required=false) String searchKeyword,
	        @RequestParam(value="pageNum", defaultValue="1") int pageNum,
	        @RequestParam(value="pageSize", defaultValue="8") int pageSize
	) {
	    int totalCnt = boardservice.allcountService();
	    int pageBlock = 3;
	    System.out.println("검색옵션 : " + searchType);
	    System.out.println("검색내용 : " + searchKeyword);
	    PageHandler ph = new PageHandler(totalCnt, pageNum, pageSize, pageBlock);
	    List<BoardDTO> postlist;
	    if(searchType != null && searchKeyword != null && !searchKeyword.trim().isEmpty()) {
	    	postlist = boardservice.searchService(searchType, searchKeyword);
	    } else {
	    	postlist = boardservice.pageListService(ph.getStartRow(), pageSize);
	    }

	    model.addAttribute("list", postlist);
	    model.addAttribute("ph", ph);
		String nextPage = "board/BoardMain";
		return nextPage;
	}

	
	// 개별 글 보기 핸들러
	@GetMapping("/board/detail")
	public String postDetail(
			Model model, 
			BoardDTO bdto ,
			@RequestParam("no") int no,
			@RequestParam("num") int num,
			HttpSession session
			) {
		System.out.println("글 세부보기 - postDetail()/ 전달된 no = "+ no);
		// 로그인 체크 필요
		MemberDTO loginedMember =
	            (MemberDTO) session.getAttribute("loginedMember");
		BoardDTO postDetail = boardservice.postDetail(no);
		model.addAttribute("postDetail", postDetail);
		model.addAttribute("num", num);
		// 로그인 체크 필요
		model.addAttribute("isLogin", loginedMember != null);
		String nextPage = "board/BoardDetail";
		return nextPage;
	}

	// 글 정보 수정 화면 이동 핸들러
	@GetMapping("/board/modify")
	public String postModify(
			Model model, 
			BoardDTO bdto,
			@RequestParam("no") int no,
			@RequestParam("num") int num,
			RedirectAttributes re,
	        HttpSession session
			) {
		System.out.println("글 수정하기 페이지 - postModify()");
		// 로그인 체크 필요
	    MemberDTO loginedMember =
	            (MemberDTO) session.getAttribute("loginedMember");
		BoardDTO postDetail = boardservice.postDetail(no);
		// 로그인 체크 필요
		model.addAttribute("isLogin", loginedMember != null);
		model.addAttribute("postDetail", postDetail);
		model.addAttribute("num", num);
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
			return "redirect:/board/modify?id="+bdto.getNo();
		}
	}
	
	// 글 내용 삭제 핸들러
	@GetMapping("/board/delete")
	public String postDelete(
			@RequestParam("no") int no,
			RedirectAttributes re,
	        HttpSession session
			) {
		System.out.println("글 삭제하기 - postDelete()/ 전달된 no = "+ no);
		// 1. 로그인 체크
	    MemberDTO loginedMember =
	            (MemberDTO) session.getAttribute("loginedMember");
	    if (loginedMember == null) {
	        re.addFlashAttribute("msg", "로그인 후 이용 가능합니다.");
	        return "redirect:/member/login";
	    }
		boolean result = boardservice.deletePost(no);
		if(result) {
			re.addFlashAttribute("msg", "글이 삭제되었습니다.");
			return "redirect:/board/main";
		} else {
			re.addFlashAttribute("msg", "서버 오류 - 삭제 실패, 다시 시도해주세요.");
			return "redirect:/board/datail?no="+no;
		}

	}
	
}
