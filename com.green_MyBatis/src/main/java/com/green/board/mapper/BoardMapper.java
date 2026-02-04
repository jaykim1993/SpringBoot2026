package com.green.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.green.board.BoardDTO;

@Mapper
public interface BoardMapper {
	
	// DB 탐색 용 메서드
		// 1. 하나의 게시글 insert 메서드
		public void insertBoard(BoardDTO bdto);
		
		// 2. 전체 글 목록 select 메서드
		public List<BoardDTO> getAllBoard();
		
		// 3. 게시글 하나 셀렉트 + readcount 누적 메서드
		public BoardDTO getOneBoard(int num);
		public int updateReadcount(int num);
		
		// 4. 하나의 게시글 Update 하는 메서드
		public int updateBoard(BoardDTO bdto);
		
		// 5. 하나의 게시글을 Delete 하는 메서드
			// 매게변수가 2개 이상인 경우는
			// @Param을 통해 XML에서 사용할 이름을 명시적으로 지정
		public int deleteBoard(
				@Param("num") int num, 
				@Param("writerPw") String writerPw);
		
		// 6. 게시글 검색하는 메서드
			// 매게변수가 2개 이상인 경우는
			// @Param을 통해 XML에서 사용할 이름을 명시적으로 지정
		public List<BoardDTO> getSearchBoard(
				@Param("searchType") String searchType, 
				@Param("searchKeyword") String searchKeyword);
	
	// Paging 위한 메서드(전체)
		// 1. 전체 게시글 개수 구하는 메서드
		public int getAllcount();
		
		// 2. 전체 게시글을 startRow 부터 pageSize 만큼 보기
		public List<BoardDTO> getPageList(
				@Param("startRow") int startRow, 
				@Param("pageSize") int pageSize);
		
	// Paging 위한 메서드(검색)
		// 1. 검색 게시글 개수 구하는 메서드
		public int getSearchCount(
				@Param("searchType") String searchType, 
				@Param("searchKeyword") String searchKeyword);
		
		// 2. 검색 게시글을 startRow 부터 pageSize 만큼 보기
		public List<BoardDTO> getSearchPageList(
				@Param("startRow") int startRow, 
				@Param("pageSize") int pageSize,
				@Param("searchType") String searchType, 
				@Param("searchKeyword") String searchKeyword);
		
		// 로그인 된 상태에서 나만의 게시글을 mypage.html에 출력
		public int getMyBoardCount(String loginId);
		
		public List<BoardDTO> getMyBoardList(
				@Param("loginId") String loginId, 				
				@Param("startRow") int startRow, 
				@Param("pageSize") int pageSize);
}
