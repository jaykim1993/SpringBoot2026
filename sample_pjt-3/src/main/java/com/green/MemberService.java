package com.green;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


// @Service의 의미
// => MemberService 클래스는 비즈니스 로직을 작성하는 클래스이다.
// AppConfig가 필요없어지고, Controller에 @Autowired가 되어있어야 한다.
@Service
public class MemberService {
	// MemberDAO 를 MemberService 클래스에서 사용하는 방법
	@Autowired
	MemberDAO memberDao;

	public void signUpConfirm(MemberDTO mdto) {
		System.out.println("회원가입 출력화면이야");
		memberDao.insertMember(mdto);
	}

	public void signInConfirm(MemberDTO mdto) {
		System.out.println("회원로그인 출력화면이야");
		
		MemberDTO loginMember = memberDao.selectMember(mdto);

		if(loginMember != null && loginMember.getPw().equals(mdto.getPw())) {
			System.out.println("로그인 성공");
			System.out.println("아이디 : "+ loginMember.getId());
			System.out.println("비밀번호 : "+ loginMember.getPw());
		} else {
			System.out.println("로그인 실패");
		}
	}

}
