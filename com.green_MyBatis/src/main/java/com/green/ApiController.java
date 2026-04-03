package com.green;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.green.carproduct.carProductDTO;
import com.green.carproduct.carProductService;
import com.green.member.MemberDTO;
import com.green.member.MemberService;

import jakarta.servlet.http.HttpSession;


// @RestController: 
// @Controller + @ResponseBody를 합친 어노테이션
// 컨트롤러 역할 + 데이터를 JSON으로 응답하여 사용
// 맨위에 하나만 붙이면 됨
@RestController
@RequestMapping("/api")
public class ApiController {
	@Autowired
	carProductService carproductservice;
	
	@Autowired
	MemberService memberservice;
	
	// 자동차 리스트를 JSON으로 변환하는 API
	@GetMapping("/cars")
	public List<carProductDTO> getCarList(){
		System.out.println("ApiController: 자동차 리스트 요청됨");
		
		// DB에서 데이터를 가져와서 그대로 리턴(Spring이 자동으로 JSON 배열로 변환)
		return carproductservice.getAllCarProduct();
	}
	
	// 이미지를  React에서 업로드, DTO에 한번에 받기
	// @ModelAttribute는 스프링 프레임워크에서 클라이언트가 보낸
	// 데이터를 자바 객체(DTO)로 자동 바인딩(연결)해주는 어노테이션
	@PostMapping("/cars/insert")
	public int insertCarProduct(
			@ModelAttribute carProductDTO cdto,
			@RequestParam("uploadFile") MultipartFile file
			) throws Exception {
		System.out.println("ApiController: 자동차 등록 요청");
		
		// 1. 저장령로
		String savePath = "C:/Spring_Boot/com.green_MyBatis/frontend/public/img/car/";
		
		// 파일이 존재하지 않을 경우 자동생성
		File dir = new File(savePath);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		String fileName = "";
		
		if(!file.isEmpty()) {
			
			String originalName = file.getOriginalFilename();
			
			// 중복방지 이름 변경하기
			// UUID로 가져온 32자 중에서 4자리만 가져와 파일명에 추가하기
			fileName = UUID.randomUUID().toString().substring(0,4) + "_" + originalName;
			
			File saveFile = new File(savePath + fileName);
			
			file.transferTo(saveFile);
		}
		
		// 2. DTO에 파일명 세팅
		cdto.setImg(fileName);
		
		// 3. DB 저장
		carproductservice.insertCarProduct(cdto);
		return 1;
	}
	
	// 회원가입 API(Post방식)
	// @RequestBody 어노테이션은 리액트에서 보낸 JSON 데이터를
	// -> 자바 객체(MEMberDTO)로 자동 변환해준다.
	// insert 할때만 사용한다.
	@PostMapping("/member/signup")
	public int signup(@RequestBody MemberDTO mdto) {
		System.out.println("ApiController: signup 요청됨");
		
		return memberservice.signupConfirm(mdto);
	}
	
	// 로그인
	// 서버에서는 보안상의 문제로 session 에 담아서 가져온다.
	@PostMapping("/member/login")
	public MemberDTO login(
			@RequestBody MemberDTO mdto, 
			HttpSession session 
			) {
		System.out.println("ApiController: login 요청됨");
		MemberDTO loginUser = memberservice.loginConfirm(mdto);
		if(loginUser != null) {
			session.setAttribute("loginUser",loginUser.getId());
		}
		return loginUser;
	}
	
	// 로그아웃
	@GetMapping("/member/logout")
	public int logout(HttpSession session) {
		session.invalidate();
		return 1;
	}
	
	// 개인정보보기
	@GetMapping("/member/myinfo")
	public MemberDTO myInfo(
			MemberDTO mdto, 
			HttpSession session // 세션에서 로그인한 사용자 꺼내기
			) {
		String loginId = (String) session.getAttribute("loginUser");
		
		if(loginId == null) {
			return null;
		}
		// 로그인 되어있으면 DB 조회
		return memberservice.oneSelect(loginId);
	}
	
	// 회원탈퇴
	// 삭제한다 => @DeleteMapping()
	@DeleteMapping("/member/delete")
	public int delete(HttpSession session) {
		String loginId = (String) session.getAttribute("loginUser");
		
		if(loginId == null) {
			return 0;
		}
		// 삭제가 완료되면 1(true), 삭제 실패하면 0(false)
		boolean result = memberservice.oneDelete(loginId);
		
		if(result) {
			// 로그아웃
			session.invalidate(); // 세션 삭제
			return 1;
		} else {
			return 0;
		}
		
	}
}	
