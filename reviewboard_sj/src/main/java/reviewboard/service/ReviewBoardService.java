package reviewboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reviewboard.dto.ReviewBoardDTO;
import reviewboard.mapper.ReviewBoardMapper;



@Service
public class ReviewBoardService {

	@Autowired
	ReviewBoardMapper rbmapper;
	
	public void insertReview(ReviewBoardDTO rdto) {
		rbmapper.insertReview(rdto);
	};
	
	// 2. 전체 글 목록 개수 & 전체 글 목록(페이징)
	public int getAllCount() {
		return rbmapper.getAllCount();
	};
	public List<ReviewBoardDTO> getAllReview(int startRow, int pageSize){
		return rbmapper.getAllReview(startRow, pageSize);
	};
	
	// 3. 게시글 하나 셀렉트 & readcount 누적 메서드
	public ReviewBoardDTO getOneReview(int id) {
		rbmapper.updateReadcount(id);
		return rbmapper.getOneReview(id);
	};

	
	// 4. 하나의 게시글 Update 하는 메서드
	public boolean updateReview(ReviewBoardDTO rdto) {
		int result = rbmapper.updateReview(rdto);
		if(result > 0) {
			return true;
		} else {
			return false;
		}
	};
	
	// 5. 하나의 게시글을 Delete 하는 메서드
	public boolean deleteReview(int id) {
		int result = rbmapper.deleteReview(id);
		if(result > 0) {
			return true;
		} else {
			return false;
		}
	};
	
	// 6. 평균 구하기
	public double getRateAvg() {
		return rbmapper.getRateAvg();
	};
	
}
