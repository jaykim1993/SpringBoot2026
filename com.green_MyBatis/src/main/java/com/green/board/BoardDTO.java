package com.green.board;
// ui에서 입력한 값을 받아서 여러 로직에 사용
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
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getWriterPw() {
		return writerPw;
	}
	public void setWriterPw(String writerPw) {
		this.writerPw = writerPw;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public int getReadcount() {
		return readcount;
	}
	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
