package replyBoard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import replyBoard.dto.ReplyBoardDTO;
import replyBoard.mapper.ReplyBoardMapper;

@Service
public class ReplyBoardServiceImpl  implements ReplyBoardService{
	
	@Autowired
	ReplyBoardMapper rbm;

	@Override
	public void insertReplyBoard(ReplyBoardDTO rdto) {
		System.out.println("서비스-글 추가 서비스 호출");
		rbm.insertReplyBoard(rdto);
	}

	@Override
	public List<ReplyBoardDTO> getAllReplyBoard() {
		System.out.println("서비스-전체 글 리스트 호출");
		return rbm.getAllReplyBoard();
	}

	@Override
	public ReplyBoardDTO getOneBoard(int num) {
		System.out.println("서비스-특정 글 리스트 호출");
		return rbm.getOneBoard(num);
	}

	@Override
	public void reWriteInsert(ReplyBoardDTO rdto) {
		System.out.println("서비스-답글 삽입 호출");
		rbm.reWriteInsert(rdto);
	}

	@Override
	public void reSqUpdate(ReplyBoardDTO rdto) {
		System.out.println("서비스-이전 답글 업그레이드 호출");
		rbm.reSqUpdate(rdto);
	}

	@Override
	public void replyProcess(ReplyBoardDTO rdto) {
		System.out.println("서비스-전체 글 리스트 호출");
		rbm.reSqUpdate(rdto); // 기존 답글 확인후 출력순위(re_strp) 밀어주기
		rbm.reWriteInsert(rdto); // 새로운 답글 입력
	}

}
