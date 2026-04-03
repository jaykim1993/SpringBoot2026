package questBoard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import questBoard.dto.QuestBoardDTO;

@Mapper
public interface QuestBoardMapper {
	// 게시글 전체 출력
	public List<QuestBoardDTO> getAllBoard();
	// 글쓰기
	public void insertBoard(QuestBoardDTO qdto);
	// 글 하나 선택
	public QuestBoardDTO getOneBoard(int num);
	// 답글 존재 유무 탐색
	public int isReExist(int num);
	// 답글달기
	public void reInsert(QuestBoardDTO qdto);
}
