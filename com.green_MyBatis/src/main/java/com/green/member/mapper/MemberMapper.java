package com.green.member.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.green.member.MemberDTO;

@Mapper // MemberMapper : 메퍼(연결: xml파일 SQL)역할을 한다.
public interface MemberMapper {
	// MemberDAO의 메서드를 추상메서드로 작성한다.
	// 설정된 메서드는 IoC컨테이너에 탑재된다.
	
	// 1. DB내 아이디 존재 유/무 중복 체크 메서드
	public boolean isMember(String id);
	
	// 2. 회원 개인을 추가하는 메서드
	public int insertMember(MemberDTO mdto);
	
	// 3. 회원 전체 목록 검색 메서드
	public List<MemberDTO> allSelectMember();
	
	// 4. 개인 한 사람의 정보 검색 메서드
	public MemberDTO oneSelectMember(String id);
	
	// 5. 개인 한 사람의 정보를 수정하는 메서드
	public int updateMember(MemberDTO mdto);
	
	// 6. 개인 한 사람의 패스워드 리턴하는 메서드
	public String getPass(String id);
	
	// 7. 개인 한 사람의 정보를 삭제하는 메서드
	public int deleteMember(String id);
}
