package questBoard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import questBoard.dto.QuestBoardDTO;
import questBoard.mapper.QuestBoardMapper;
@Service
public class QuestBoardServiceImpl implements QuestBoardService {
	@Autowired
	QuestBoardMapper m;
	
	@Override
	public List<QuestBoardDTO> getAllBoard() {
		
		return m.getAllBoard();
	}

	@Override
	public void insertBoard(QuestBoardDTO qdto) {
		m.insertBoard(qdto);
		
	}

	@Override
	public QuestBoardDTO getOneBoard(int num) {
		return m.getOneBoard(num);
	}

	@Override
	public void reInsert(QuestBoardDTO qdto) {
		m.reInsert(qdto);
	}

	@Override
	public int isReExist(int num) {
		return m.isReExist(num);
	}

}
