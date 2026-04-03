package replyBoard.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import replyBoard.dto.ReplyBoardDTO;
import replyBoard.service.ReplyBoardService;

@Controller
@RequestMapping("/board") 
public class ReplyBoardController {
	@Autowired
	ReplyBoardService rbs; // 반드시 서비스 인터페이스를 의존객체로 삽입함을 주의하자!
	
	@GetMapping("/list")
	public String boardList(Model model) {
		System.out.println("컨트롤러 보드리스트페이지 호출");
		List<ReplyBoardDTO> replyList = rbs.getAllReplyBoard();
		model.addAttribute("rlist", replyList);
		
		return "replyBoard/replyboardList";
	}
	
	@GetMapping("/writer")
	public String boardWriterForm() {
		System.out.println("컨트롤러 작성페이지 호출");
		return "replyBoard/replyboardWrite_Form";
	}
	
	@PostMapping("/writerPro")
	public String boardWriterPro(
			ReplyBoardDTO rdto,
			@RequestParam("file1") MultipartFile upload1,
			@RequestParam("file2") MultipartFile upload2			
			) throws IllegalStateException, IOException {
		System.out.println("컨트롤러 게시글 작성 호출");
		
		// 1. 파일을 저장할 실제 하드디스크의 위치를 지정한다.
		// WebConfig에서 설정한 'file:///c:/upload/' 경로와 반드시 일치해야 웹에서 불러올 수 있다.
		String savePath = "c:/upload/";
		
		// 2. [안전장치] 만약 폴더가 없으면 프로그램을 통해 자동으로 생성한다.
		File saveDir = new File(savePath);
		if( !saveDir.exists()) {
			saveDir.mkdirs(); // mkdirs()는 상위 폴더가 없어도 한꺼번에 다 만들어준다.
		}
		
		// 3. 첫번째 이미지 업로드 처리
		if(!upload1.isEmpty()) { // 사용자가 파일을 실제로 선택해서 보냈는지 확인
			// 사용자가 올린 원래 파일명(예: "my_car.jpg")을 가져온다.
			String originalName1 = upload1.getOriginalFilename(); // 만약 UUID로 새 조합 안할시, origianlName1을 그대로 아래 생략 후 사용
			
			// [중복방지] 파일명이 겹치지 않게 UUID를 생성한다.
			// UUID 는 36자리 중복 방지 작명이고 그중 
			// substring(0,4)를 이용해 앞 네자리만 가져와 
			// 파일명 조합으로 사용한다.
			// (예: abcd + _ + my_car.jpg")
			String saveName1 = UUID.randomUUID().toString().substring(0, 4) + "_" + originalName1;
			
			// savePath(경로)와 saveName1(이름)을 합쳐서 
			// 실제 저장될 파일 객체를 생성한다.
			// (예: "c:/upload/abcd_my_car.jpg")
			File file1 = new File(savePath + saveName1);
			// File file1 = new File("c:/upload/" + saveName1);
			
			// transferTo(): 이 명령어가 실행되는 순간 
			// 서버의 메모리에 있던 파일이 
			// 실제 하드디스크(c:/upload/)로 복사된다.
			upload1.transferTo(file1);
			
			// DB에 저장할 파일명 DTO에 세팅
			// [핵심] 하드디스크에 저장된 '새 파일명'을 DTO 객체에 담는다.
			// 그래야 나중에 DB의 upload1 컬럼에 이 이름이 기록된다.
			rdto.setUpload1(saveName1);
		}
		
		// 4. 두번째 이미지 업로드 처리
		if(!upload2.isEmpty()) { 
			String saveName2 = upload2.getOriginalFilename();
			File file2 = new File(savePath + saveName2);
			upload2.transferTo(file2);
			rdto.setUpload2(saveName2);
		}
		// 5. 게시글 내용과 함께 위에서 세팅한 '파일명'들을 DB에 최종 저장합니다.
		rbs.insertReplyBoard(rdto);
		return "redirect:/board/list";
	}
	
	@GetMapping("/detail")
	public String getOneBoard(
			@RequestParam("num") int num, 
			Model model
			) {
		System.out.println("컨트롤러 게시글 단독 페이지 호출");
		ReplyBoardDTO oneList = rbs.getOneBoard(num);
		model.addAttribute("onelist", oneList);
		return "replyBoard/replyBoardDetail";
	}
	
	@GetMapping("/reply")
	public String reWriteForm(
			@RequestParam("num") int num,
			@RequestParam("ref") int ref,
			@RequestParam("re_step") int re_step,
			@RequestParam("re_level") int re_level,
			Model model
			) {
		System.out.println("컨트롤러 게시글 답글 페이지 호출");
		model.addAttribute("num", num);
		model.addAttribute("ref", ref);
		model.addAttribute("re_step", re_step);
		model.addAttribute("re_level", re_level);
		return "replyBoard/replyBoardReWrite_Form";
	}
	
	@PostMapping("/reWritePro")
	public String reWritePro(ReplyBoardDTO rdto) {
		System.out.println("컨트롤러 게시글 답글 업데이트 인서트 호출");
		rbs.replyProcess(rdto);
		return "redirect:/board/list";
	}
}
