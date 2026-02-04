package com.green.board;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.board.mapper.BoardMapper;

@Service
public class BoardService {


	
	@Autowired
	private BoardMapper boardMapper;
//	BoardDAO boardDao;

	// 하나의 게시글이 추가되는 메서드를 BoardDAO에서 접근하여 사용
	public void insertBoardService(BoardDTO bdto) {
		System.out.println("3) B-Service insertBoardService");
		boardMapper.insertBoard(bdto);
	};
	
	// 게시글 전체 목록 셀렉트 서비스
	public List<BoardDTO> allBoardService() {
		System.out.println("3) B-Service allBoardService");
		return boardMapper.getAllBoard();
	};
	
	// 하나의 게시글을 출력하는 서비스
	public BoardDTO oneBoardService(int num) {
		System.out.println("3) B-Service oneBoardService");
		
		boardMapper.updateReadcount(num);
		
		return boardMapper.getOneBoard(num);
	};

	
	// 하나의 게시글을 수정하는 서비스
	public boolean updateService(BoardDTO bdto) {
		System.out.println("3) B-Service updateService");
		int result = boardMapper.updateBoard(bdto);
		
		if(result > 0) {
			System.out.println("게시글 수정 성공");
			return true;
		} else {
			System.out.println("게시글 수정 실패 - 비밀번호 불일치");
			
			return false;
		}
	};
	
	// 하나의 게시글을 삭제하는 서비스
	public boolean deleteService(int num, String writerPw) {
		System.out.println("3) B-Service deleteService");
		int result = boardMapper.deleteBoard(num, writerPw);
		System.out.println(num + writerPw);
		if(result > 0) {
			System.out.println("게시글 삭제 성공");
			return true;
		} else {
			System.out.println("게시글 삭제 실패 - 비밀번호 불일치");
			return false;
		}
	};
	
	// 게시글 검색하는 서비스
	public List<BoardDTO> searchService(String searchType, String searchKeyword) {
		System.out.println(
				"3) B-Service searchService / "
				+ "searchType: "+searchType+", "
				+ "searchKeyword: "+ searchKeyword);
		return boardMapper.getSearchBoard(searchType, searchKeyword);
	}
	
	// 페이징 위한 서비스
	// 전체 게시글 수 서비스
	public int allcountService() {
		System.out.println("3) B-Service allcountService");
		return boardMapper.getAllcount();
	};
	// 페이지별 데이터 출력 서비스
	public List<BoardDTO> pageListService(int startRow, int pageSize){
		System.out.println("3) B-Service pageListService");
		return boardMapper.getPageList(startRow, pageSize);
	};
	
	public int searchCountService(String searchType, String searchKeyword) {
		System.out.println("3) B-Service getSearchcount");
		return boardMapper.getSearchCount(searchType, searchKeyword);
	};
	
	// 2. 검색 게시글을 startRow 부터 pageSize 만큼 보기
	public List<BoardDTO> searchPageListService(int startRow, int pageSize, String searchType, String searchKeyword){
		System.out.println("3) B-Service etSearchPageList");
		return boardMapper.getSearchPageList(startRow, pageSize, searchType, searchKeyword);
	};
	
	// 마이 페이지용 게시글 출력 서비스
	public int myBoardCountService(String loginId) {
		System.out.println("3) B-Service myBoardCountService");
		return boardMapper.getMyBoardCount(loginId);
	};
	
	public List<BoardDTO> myBoardListService(
			String loginId, 				
			int startRow, 
			int pageSize
			){
		System.out.println("3) B-Service myBoardListService");
		return boardMapper.getMyBoardList(loginId, startRow, pageSize);
	};
}
