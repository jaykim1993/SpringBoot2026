package com.green.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.board.mapper.BoardMapper;

@Service

public class BoardService {
	@Autowired
	BoardMapper boardMapper;
	
	// 글 등록 유부 확인 서비스
	public int postingConfirm(BoardDTO bdto) {
		return boardMapper.insertPost(bdto);
	}
	
	// 전체 글목록 출력 서비스
	public List<BoardDTO> allPost (){
		return boardMapper.allSelectPost();
	}
	
	// 글 세부 출력 서비스
	public BoardDTO postDetail(int id) {
		return boardMapper.selectPost(id);
	}
	
	// 글 수정하기 서비스
	public boolean editPost(BoardDTO bdto) {
		return boardMapper.updateContent(bdto) == 1;
	}
	
	// 글 삭제하기 서비스
	public boolean deletePost(int no) {
		return boardMapper.deleteContent(no) == 1;
	}
	
	// 게시글 검색 서비스
	public List<BoardDTO> searchService(String searchType, String searchKeyword){
		return boardMapper.getSearchBoard(searchType, searchKeyword);
	}
	
	// 전체 게시글 수 카운트 서비스
	public int allcountService() {
		return boardMapper.getAllcount();
	};
	
	// 페이지별 게시글 출력 서비스
	public List<BoardDTO> pageListService(int startRow, int pageSize){
		return boardMapper.getPageList(startRow, pageSize);
	}
}
