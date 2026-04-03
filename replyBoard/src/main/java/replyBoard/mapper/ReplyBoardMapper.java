package replyBoard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import replyBoard.dto.ReplyBoardDTO;

@Mapper
public interface ReplyBoardMapper {
	// 각종 SQL을 위한 메서드 작성
	
	// 게시글 작성하여 추가하기
	public void insertReplyBoard(ReplyBoardDTO rdto);
	
	// 게시글 전체 목록 검색
	public List<ReplyBoardDTO> getAllReplyBoard();
	
	// 하나의 게시글을 리턴받는 메서드
	public ReplyBoardDTO getOneBoard(int num);
	
	// 답글 작성하여 추가하기
	public void reWriteInsert(ReplyBoardDTO rdto);
	
	// 답글 작성시 부모글의 re_level보다 큰 값들을 모두 1씩 증가시키는 메서드
	public void reSqUpdate(ReplyBoardDTO rdto);
	
}
