package com.green.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
	
	@Autowired
	MemberDAO memberDao;
	
	// id 중복체크0, 성공1, 실패-1 상수 변수
	public final static int user_id_already_exist = 0;
	public final static int user_signup_success = 1;
	public final static int user_signup_fail = -1;
	
	public List<MemberDTO> allListMember (){
		return memberDao.allSelectMember();
	}
	
	// 회원가입이 제대로 되었는지, 혹은 실패했는지 알려줘
	public int signupConfirm(MemberDTO mdto) {

		System.out.println("MemberService - signupConfirm() 메서드");
		boolean isMember = memberDao.isMember(mdto.getId());
		if(isMember == false) {
			// 중복된 아이디가 존재하지 않을때 DB에 회원 정보 추가한다.
			int result = memberDao.insertMember(mdto);
			if(result > 0) {
				return user_signup_success;
			} else {
				return user_signup_fail;
			}
		} else {
			return user_id_already_exist;
		}
	}
}

// controller -> service : DAO 메소드 찾아 잇어?
// DAO야 메소드 있어? -> DB에서 찾아옴
// DB -> id, pw값들고 -> DAO한테 보냄 -> service의 메소드로 보냄
// -> controller에게 id, pw 찾아서 보냄

// 서비스는 controller와 DAO 사이에서 중재전달 역할을 한다.