package com.green.board;

import lombok.Data;

// ui에서 입력한 값을 받아서 여러 로직에 사용

//lombok을 이용해 DTO의 getter, setter 자동생성
@Data
public class BoardDTO {
	// 반드시 MySQL에 작성한 테이블 필드명 순서, 데이터 타입이랑 같게
	private int num; // 글번호
	private String writer; // 글쓴이
	private String subject; // 글제목
	private String writerPw; // 글 비밀번호
	private String reg_date; // 글 작성일자
	private int readcount; // 조회수
	private String content; // 글 내용
	private String id;


	
}
