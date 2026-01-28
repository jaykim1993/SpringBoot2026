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
	
	@Autowired
	private DataSource dataSource;

	// 글 등록 쿼리
	public int insertPost(BoardDTO bdto) {
		System.out.println("DAO 쿼리- 글 인서트 메서드");
		int result = 0;
		String sql = "INSERT INTO board(title, content, writer) VALUES(?,?,?)";
		try(
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				){
			pstmt.setString(1, bdto.getTitle());
			pstmt.setString(2, bdto.getContent());
			pstmt.setString(3, bdto.getWriter());
			
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	// 모든 글 출력되는 쿼리
	public List<BoardDTO> allSelectPost() {
		System.out.println("DAO 쿼리- 전체 글 셀렉트 메서드");
		
		List<BoardDTO> postlist = new ArrayList<BoardDTO>();
		String sql = "SELECT * FROM board ORDER BY id DESC";
		
		try(
			Connection conn = dataSource.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
				){
			while(rs.next()) {
				BoardDTO bdto = new BoardDTO();
				
				bdto.setId(rs.getInt("id"));
				bdto.setTitle(rs.getString("title"));
				bdto.setContent(rs.getString("content"));
				bdto.setWriter(rs.getString("writer"));
				bdto.setCreatedAt(rs.getString("createdAt"));
				
				postlist.add(bdto);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return postlist;
	}
	
	// 글 세부 보기용 쿼리 
	public BoardDTO selectPost(String id) {
		System.out.println("DAO 쿼리- 특정 글 셀렉트 메서드");
		
		BoardDTO bdto = new BoardDTO();
		String sql = "SELECT * FROM board WHERE id =?";
		
		try(
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				
				) {
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				bdto.setId(rs.getInt("id"));
				bdto.setTitle(rs.getString("title"));
				bdto.setContent(rs.getString("content"));
				bdto.setWriter(rs.getString("writer"));
				bdto.setCreatedAt(rs.getString("createdAt"));
			}

		} catch(Exception e) {
			e.printStackTrace();
		}
		return bdto;
	}
	
	// 글 내용 수정하기 쿼리
	public int updateContent(BoardDTO bdto) {
		System.out.println("DAO 쿼리- 글 수정 메서드");
		int result = 0;
		String sql = "UPDATE board SET title=?, content=? WHERE id=?";
		
		try(
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				){
			
			pstmt.setString(1, bdto.getTitle());
			pstmt.setString(2, bdto.getContent());
			pstmt.setInt(3, bdto.getId()); 
			result = pstmt.executeUpdate();
			System.out.println("UPDATE result = " + result);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// 글 삭제하기 쿼리
	public int deleteContent(String id) {
		int result = 0;
		String sql ="DELETE FROM board WHERE id=?";
		
		try(	
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				){
			pstmt.setString(1, id);
			result = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	
	
}
