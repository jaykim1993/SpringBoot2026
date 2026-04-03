package questBoard.service;

import java.util.List;

import questBoard.dto.QuestBoardDTO;

public interface QuestBoardService {
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
