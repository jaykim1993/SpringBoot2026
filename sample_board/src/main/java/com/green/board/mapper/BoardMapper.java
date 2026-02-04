package com.green.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.green.board.BoardDTO;

@Mapper
public interface BoardMapper {
	
	public int insertPost(BoardDTO bdto);
	
	public List<BoardDTO> allSelectPost();
	
	public BoardDTO selectPost(int no);
	
	public int updateContent(BoardDTO bdto);
	
	public int deleteContent(int no);
	
	public List<BoardDTO> getSearchBoard(
			@Param("searchType") String searchType,
			@Param("searchKeyword") String searchKeyword
			);
	
	
	public int getAllcount();
	
	public List<BoardDTO> getPageList(
			@Param("startRow") int startRow,
			@Param("pageSize") int pageSize
			);



	
}
