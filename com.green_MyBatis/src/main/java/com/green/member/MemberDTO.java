package com.green.member;

import lombok.Data;

// 데이터를 전송하는 객체

// lombok을 이용해 DTO의 getter, setter 자동생성
@Data
public class MemberDTO {
	
	private int no; // 사용자 고유번호(PK)
	private String id;
	private String pw;
	private String mail;
	private String phone;
	private String reg_date; // 사용자 정보 등록일
	private String mod_date; // 사용자 정보 수정일
	

}
