package com.green.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDAO {
	// application.properties 설정된 환경의 MySQL과 연결한다
	@Autowired
	private DataSource dataSource;
	
	// 쿼리문 작성시 KeyPoint
		// 1. 한사람, 하나에 관련된 자료를 insert, select 할때는 DTO 객체에 담아 사용한다. 고로 데이터 타입이 DTO
		// 2. 전체 목록, 혹은 여러 자료를  insert, select 할때는 List 객체에 담는다.
		// 3. 필드명 하나 select 할때는 String, int, boolean 타입으로 담는다.
		// 4. 메서드 생성 시 void는 return 없음
		// 5. 메서드 생성 시 data type 존재하면 return 값 필요
		// 6. try ~ catch 사용
	
	// 하나의 게시글 insert 하는 쿼리
	public void insertBoard(BoardDTO bdto) {
		System.out.println("2) BoardDAO - insertBoard 호출");
		
		String sql = "INSERT INTO board02(writer, subject, writerPw, content) VALUES(?,?,?,?)";
		
		try(	// 환경설정
				// 주입한 데이터베이스의 원본의 자료들을 연결
				// conn = (url, username, userPassword)
				// conn = (localhost:3306, "root", "12345678")
				Connection conn = dataSource.getConnection();
				// 입력한 sql문을 mySQL에 보냄
				PreparedStatement pstmt = conn.prepareStatement(sql);
				) {
				// ? 대응하기
				pstmt.setString(1, bdto.getWriter());
			    pstmt.setString(2, bdto.getSubject());
			    pstmt.setString(3, bdto.getWriterPw());
			    pstmt.setString(4, bdto.getContent());
			    // insert, delete, update => executeUpdate();
			    // select => executeQuery(); / ResultSet 객체에 담아 출력해야함
			    int result = pstmt.executeUpdate();
			    System.out.println("insert 결과: " + result);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// 전체 글 목록 select 하는 쿼리
	public List<BoardDTO> getAllBoard(){
		System.out.println("2) BoardDAO - getAllBoard 호출");
		// List<> 인스턴스
		List<BoardDTO> boardlist = new ArrayList<BoardDTO>();
		String sql ="SELECT * FROM board02 ORDER BY num DESC";
		
		try(
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				){
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardDTO bdto = new BoardDTO();
				bdto.setNum(rs.getInt("num"));
				bdto.setWriter(rs.getString("writer"));
				bdto.setSubject(rs.getString("subject"));
				bdto.setWriterPw(rs.getString("writerPw"));
				bdto.setReg_date(rs.getString("reg_date"));
				bdto.setReadcount(rs.getInt("readcount"));
				bdto.setContent(rs.getString("content"));
				// List bdto 담기
				boardlist.add(bdto);

			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return boardlist;
	}
	
	// 게시글 하나 셀렉스 쿼리 + readcount누적하기
	// sql 두개 사용하자 (Update / Select)
	public BoardDTO getOneBoard(int num) {
		System.out.println("2) BoardDAO - getOneBoard 호출");
		// BoardDTO 인스턴스화
		BoardDTO bdto = new BoardDTO();
		String sql = "UPDATE board02 SET readcount=readcount+1 WHERE num=?";
		String sql2 = "SELECT * FROM board02 WHERE num=?";
		
		try(
				Connection conn = dataSource.getConnection();
				){
			// sql 첫번째 (조회수 증가시키기)
			try(
					PreparedStatement pstmt = conn.prepareStatement(sql);
					){
				pstmt.setInt(1,num);
				pstmt.executeUpdate();
			}
			// sql 두번째 (게시글 불러오기)
			try(
					PreparedStatement pstmt = conn.prepareStatement(sql2);
					){
				pstmt.setInt(1, num);
				ResultSet rs = pstmt.executeQuery();
				if(rs.next()) {
					bdto.setNum(rs.getInt("num"));
					bdto.setWriter(rs.getString("writer"));
					bdto.setSubject(rs.getString("subject"));
					bdto.setWriterPw(rs.getString("writerPw"));
					bdto.setReg_date(rs.getString("reg_date"));
					bdto.setReadcount(rs.getInt("readcount"));
					bdto.setContent(rs.getString("content"));
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return bdto;
	}
	
	// 하나의 게시글 Update 쿼리
	public int updateBoard(BoardDTO bdto) {
		System.out.println("2) BoardDAO - updateBoard 호출");
		
		int result = 0;
		// 제목과 내용만 수정 가능하도록 설정(번호와 비밀번호가 DB에 일치해야만 함)
		String sql = "UPDATE board02 SET subject=?, content=? WHERE num=? AND writerPw=?";
		try(	
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				) {
				// ? 대응하기
			    pstmt.setString(1, bdto.getSubject());
			    pstmt.setString(2, bdto.getContent());
			    pstmt.setInt(3, bdto.getNum());
			    pstmt.setString(4, bdto.getWriterPw());
			    
			    result = pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// 하나의 게시글을 Delete 쿼리
	// 게시글 작성시 비밀번호 입력하였기 때문에 삭제시에도 비밀번호와 번호가 일치하는지 체크
	public int deleteBoard(int num, String writerPw) {
		System.out.println("2) BoardDAO - deleteBoard 호출");
		
		int result = 0;
		String sql = "DELETE FROM board02 WHERE num=? AND writerPw=?";
		try(	
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				) {
				// ? 대응하기
			    pstmt.setInt(1, num);
			    pstmt.setString(2, writerPw);
			    
			    result = pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// 게시글 검색하는 쿼리
	// 검색 시 공식 
	// => searchType, searchKeyword 매개변수 필요
	// 검색 결과가 다수가 출력 예상되므로 ArrayList에 담아야한다.
	public List<BoardDTO> getSearchBoard(String searchType, String searchKeyword) {
		System.out.println("2) BoardDAO - searchBoard 호출");
		
		List<BoardDTO> searchlist = new ArrayList<BoardDTO>();
		
		String sql = "";
		
		// 키워드에 따라 다른 sql문 전달
		if("subject".equals(searchType)) {
			// subject 검색 부분
			// 입력한 문자를 포함하는 검색 명령어
			sql = "SELECT * FROM board02 WHERE subject LIKE ? ORDER BY num DESC";
		}else {
			// content 검색 부분
			sql = "SELECT * FROM board02 WHERE content LIKE ? ORDER BY num DESC";
		}
		

		try(	
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				) {
				pstmt.setString(1, "%"+searchKeyword+"%");
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					BoardDTO bdto = new BoardDTO();
					bdto.setNum(rs.getInt("num"));
					bdto.setWriter(rs.getString("writer"));
					bdto.setSubject(rs.getString("subject"));
					bdto.setWriterPw(rs.getString("writerPw"));
					bdto.setReg_date(rs.getString("reg_date"));
					bdto.setReadcount(rs.getInt("readcount"));
					bdto.setContent(rs.getString("content"));
					
					searchlist.add(bdto);
				}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return searchlist;
	}

}
