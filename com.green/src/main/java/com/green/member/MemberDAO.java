package com.green.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class MemberDAO {


	
	// mySQL Driver 설치 및 JDBC 환경 설정 완료
	@Autowired
	private DataSource dataSource;


	// 쿼리문 사용할 공간
	public boolean isMember(String id) {
		System.out.println("MemberDAO - isMember()메서드 호출");
		return false;
	}
	
	public int insertMember(MemberDTO mdto) {
		System.out.println("MemberDAO - insertMember() 메서드 호출");
		
		
		// 실무에서 쿼리문 작성 시 대문자로 작성함
		// NO, REG_DATE, MOD_DATE는 default 값 존재하므로
		String sql = "INSERT INTO user_member(id, pw, mail, phone) VALUES(?,?,?,?)";
		int result = 0;
		
		// DB는 네크워크를 통해 자료를 가져오므로 try~catch 구문 이용
		try(// Connection 클래스를 이용해 dataSource를 getConnection()해야함
			// 사용하고 나면 반드시 반납(close()) 해야함
			// try 내에선 자동 반납됨
			Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			){
			
			// input 입력값이 mdto에 담긴상태에서
			// 그 값을 꺼내오기 위해 get 사용
			pstmt.setString(1, mdto.getId());
			pstmt.setString(2, mdto.getPw());
			pstmt.setString(3, mdto.getMail());
			pstmt.setString(4, mdto.getPhone());
			// excuteUpdate() : insert, delete, update를 실행하고 영향을 받은 행의 개수를 반환(int)
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	

	// 회원가입한 유저 모두 출력되는 메소드 작성
	public List<MemberDTO> allSelectMember() {
		String sql = "SELECT * FROM user_member";
		// List<E> 인터페이스이므로 구현할 수 없다.
		// ArrayList 이용
		List<MemberDTO> list = new ArrayList<MemberDTO>();
		System.out.println("MemberDAO - allSelectMember() 메서드 호출");
		try(
			Connection conn = dataSource.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
				// select 구문은 executeQuery()실행한 결과를 ResultSet 객체에 담는다.
			ResultSet rs = stmt.executeQuery();
			){
			//rs.next() : 다음행 값이 존재하면 true, 아니면 false
			while(rs.next()) {
				MemberDTO mdto = new MemberDTO();
				// mdto가방을 rs의 결과값을 저장하는 용도로 사용
				mdto.setNo(rs.getInt("no"));
				mdto.setId(rs.getString("id"));
				mdto.setPw(rs.getString("pw"));
				mdto.setMail(rs.getString("mail"));
				mdto.setPhone(rs.getString("phone"));
				mdto.setReg_date(rs.getString("reg_date"));
				mdto.setMod_date(rs.getString("mod_date"));
				
				//ArrayList에 추가한다.
				list.add(mdto);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
