package com.green;

// DTO(Data Transfer Object)
// 데이터 전송객체
// => DTO 가방에 입력한 자료를 담아서 이동한다.
public class MemberDTO {
	// 접근제어자 private 이용
	private String id;
	private String pw;
	private String email;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
