package com.green.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {


	
	@Autowired
	BoardDAO boardDao;


	
	// 하나의 게시글이 추가되는 메서드를 BoardDAO에서 접근하여 사용
	public void insertBoardService(BoardDTO bdto) {
		System.out.println("3) B-Service insertBoardService");
		boardDao.insertBoard(bdto);
	}
	
	// 게시글 전체 목록 셀렉트 서비스
	public List<BoardDTO> allBoardService() {
		System.out.println("3) B-Service allBoardService");
		return boardDao.getAllBoard();
	}
	
	// 하나의 게시글을 출력하는 서비스
	public BoardDTO oneBoardService(int num) {
		System.out.println("3) B-Service oneBoardService");
		return boardDao.getOneBoard(num);
	}
	
	// 하나의 게시글을 수정하는 서비스
	public boolean updateService(BoardDTO bdto) {
		System.out.println("3) B-Service updateService");
		int result = boardDao.updateBoard(bdto);
		
		if(result > 0) {
			System.out.println("게시글 수정 성공");
			return true;
		} else {
			System.out.println("게시글 수정 실패 - 비밀번호 불일치");
			
			return false;
		}
	}
	
	// 하나의 게시글을 삭제하는 서비스
	public boolean deleteService(int num, String writerPw) {
		System.out.println("3) B-Service deleteService");
		int result = boardDao.deleteBoard(num, writerPw);
		System.out.println(num + writerPw);
		if(result > 0) {
			System.out.println("게시글 삭제 성공");
			return true;
		} else {
			System.out.println("게시글 삭제 실패 - 비밀번호 불일치");
			return false;
		}
	}
	
	// 게시글 검색하는 서비스
	public List<BoardDTO> searchService(String searchType, String searchKeyword) {
		System.out.println(
				"3) B-Service searchService / "
				+ "searchType: "+searchType+", "
				+ "searchKeyword: "+ searchKeyword);
		return boardDao.getSearchBoard(searchType, searchKeyword);
	}
}
