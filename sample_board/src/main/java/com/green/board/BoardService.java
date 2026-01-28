package com.green.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class BoardService {
	@Autowired
	BoardDAO boardDAO;
	
	// 글 등록 유부 확인 서비스
	public int postingConfirm(BoardDTO bdto) {
		return boardDAO.insertPost(bdto);
	}
	
	// 전체 글목록 출력 서비스
	public List<BoardDTO> allPost (){
		return boardDAO.allSelectPost();
	}
	
	// 글 세부 출력 서비스
	public BoardDTO postDetail(String id) {
		return boardDAO.selectPost(id);
	}
	
	// 글 수정하기 서비스
	public boolean editPost(BoardDTO bdto) {
		return boardDAO.updateContent(bdto) == 1;
	}
	
	// 글 삭제하기 서비스
	public boolean deletePost(String id) {
		return boardDAO.deleteContent(id) == 1;
	}
}
