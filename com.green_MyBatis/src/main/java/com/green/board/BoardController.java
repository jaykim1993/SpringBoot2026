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
	public String boardWritePro(BoardDTO bdto, HttpSession session) {
		System.out.println("1) B-Controller boardWritePro");
		
		// Session 에서 로그인 데이터 불러오기
		// Session => Java Object 최상위 객체이므로 다운캐스팅 해야한다.
		// => 명시적 형변환
		MemberDTO loginedMember = (MemberDTO)session.getAttribute("loginedMember");
		// 로그인 정보가 MemberDTO 타입으로 loginedMember에 들어간다.
		if(loginedMember != null) {
			
			bdto.setId(loginedMember.getId());
			System.out.println("db에 저장될 아이디 확인 : "+ loginedMember.getId());
		}else {
			System.out.println("로그인 정보 담기 실패");
			return "redirect:/member/login";
		}
		// 서비스의 insertBoardService 호출하여 DB에 저장
		boardService.insertBoardService(bdto);
		// 저장 후 게시판 목록으로 페이지 이동
		return "redirect:/board/list";
	}
	
	// 3. 전체 글 목록페이지로 이동하는 핸들러
//	@GetMapping("/board/list")
//	public String boardList(Model model){
//		System.out.println("1) B-Controller boardList");
//		List<BoardDTO> listboard = boardService.allBoardService();
//		model.addAttribute("list", listboard);
//		String nextPage = "board/boardList";
//		return nextPage;
//	}
// ------------------- 검색(기능 8.)을 위한 수정 version ↓ ----------------------
	// 8. 검색 타입과 검색 키워드로 자료 출력한는 핸들러 => 3. 전체 목록 출력에 로직 추가한다.
	// 9. 페이징 핸들러 => 3. 전체 목록 출력에서 로직 추가
	@GetMapping("/board/list")
	public String boardList(
	        Model model,
	     // 8. 검색 옵션 업그레이드
	        @RequestParam(value="searchType", required=false) String searchType,
	        @RequestParam(value="searchKeyword", required=false) String searchKeyword,
	     // 9. 페이징 페이지 번호, 1부터 시작으로 초기값 지정하는 파라미터 추가
	        @RequestParam(value="page", required=false, defaultValue="1") int page, 
	     // 9. 페이징 페이지 사이즈, 한 화면에 보여지는 게시글 개수를 5개로 값 지정하는 파라미터 추가
	        @RequestParam(value="pageSize", required=false, defaultValue="8") int pageSize
	) {
	    System.out.println("1) B-Controller boardList");
	    List<BoardDTO> listboard;
	    PageHandler ph;
	    int totalCnt;
	    // 9. 페이징 업그레이드
	    // 생성자에서 받을 totalCnt 는 서비스에서,
	    // page와 pageSize는 받아온다, 다만 초기값은 지정해서

	    
	    // 8. 검색 업그레이드
	    if (searchType != null && searchKeyword != null && !searchKeyword.trim().isEmpty()) {
	    	totalCnt = boardService.searchCountService(searchType, searchKeyword);
	    	ph = new PageHandler(totalCnt, page, pageSize);
	        listboard = boardService.searchPageListService(ph.getStartRow(), ph.getEndRow(), searchType, searchKeyword);
	        if (listboard.isEmpty()) {
	            model.addAttribute("msg", "검색 결과가 없습니다.");
	        }
	    } else {
		    totalCnt = boardService.allcountService();
		    ph = new PageHandler(totalCnt, page, pageSize);
	    	//	listboard = boardService.allBoardService(); <- 페이징 없이 모든 게시글 출력이므로 더 이상 사용 x
	    	// 9. 페이징 되는 리스트로 업그레이드
	    	listboard = boardService.pageListService(ph.getStartRow(), ph.getEndRow());
	    }

	    model.addAttribute("list", listboard);
	    model.addAttribute("ph", ph);
	    
	    // 반드시 두 값을 모델로 담아 boardList.html로 넘겨야 한다.
	    model.addAttribute("searchType", searchType);
	    model.addAttribute("searchKeyword", searchKeyword);
	    
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
	
	 // 7. 게시글 삭제 처리 핸들러
	@GetMapping("/board/deletePro")
	public String boardDelete(
			@RequestParam("num") int num, 
			@RequestParam("writerPw") String writerPw,
			 RedirectAttributes ra
			) {
		boolean isSuccess = boardService.deleteService(num, writerPw);
		
	    if (isSuccess) {
	        ra.addFlashAttribute("msg", "게시글이 삭제되었습니다.");
	        return "redirect:/board/list";
	    } else {
	        ra.addFlashAttribute("msg", "비밀번호가 일치하지 않습니다.");
	        return "redirect:/board/boardInfo?num=" + num;
	    }
	}
	
	// 로그인된 나의 게시글 목록을 검색하는 핸들러
	@GetMapping("/board/mypage")
	public String myBoardList(
			Model model, 
			HttpSession session, 
			@RequestParam(value="page", defaultValue="1") int page
			) {
		
		// 세션 키 이름을 loginMember로 가져오기
		MemberDTO loginMember = (MemberDTO) session.getAttribute("loginedMember");
		if(loginMember == null) {
			return "redirect:/member/login";
		}
		
		int pageSize = 5;
		int totalCnt = boardService.myBoardCountService(loginMember.getId());
		
		PageHandler ph = new PageHandler(totalCnt, page, pageSize);
		List<BoardDTO> mylist = boardService.myBoardListService(loginMember.getId(), ph.getStartRow(), ph.getEndRow());
		
		model.addAttribute("list", mylist);
		model.addAttribute("ph", ph);
		
		return "/board/myPage";
	}
	
	
}
