package com.green;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

// DAO 는 SQL 문의 집합소, 데이터 직접 처리하는 로직
@Repository // 데이터 저장소임을 알려줌
public class MemberDAO {

	// 원래는 DB 커넥션이 존재해야 하나 현재 DB없기에
	// DB 역할을 대신할 Map 생성
	// keyset = id = mdto.getId()
	private Map<String, MemberDTO> memberDB = new HashMap<>();
	
	public void insertMember(MemberDTO mdto) {
		System.out.println("[MemberService] 회원 추가 메서드 insertMember()");
		memberDB.put(mdto.getId(), mdto);
		printMember();
	}
	// 회원가입 로그 출력용
	public void printMember() {
		for(String key : memberDB.keySet()) {
			MemberDTO mdto = memberDB.get(key);
			System.out.println("id : "+ mdto.getId());
			System.out.println("pw : "+ mdto.getPw());
			System.out.println("email : "+ mdto.getEmail());
		}
	}
	public MemberDTO selectMember(MemberDTO mdto) {
		System.out.println("[MemberService] 회원 검색 메서드 selectMember()");
		MemberDTO loginMember = memberDB.get(mdto.getId());
		return loginMember;
	}

}
