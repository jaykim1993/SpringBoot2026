package reviewboard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import reviewboard.dto.ReviewBoardDTO;

@Mapper
public interface ReviewBoardMapper {
	// 1. 하나의 게시글 insert 메서드
			public void insertReview(ReviewBoardDTO rdto);
			
			// 2. 전체 글 목록 select 메서드
			public int getAllCount();
			public List<ReviewBoardDTO> getAllReview(
					@Param("startRow") int startRow,
					@Param("pageSize") int pageSize
					);
			
			// 3. 게시글 하나 셀렉트 + readcount 누적 메서드
			public ReviewBoardDTO getOneReview(int id);
			public int updateReadcount(int id);
			
			// 4. 하나의 게시글 Update 하는 메서드
			public int updateReview(ReviewBoardDTO rdto);
			
			// 5. 하나의 게시글을 Delete 하는 메서드
			public int deleteReview(int id);
			
			// 6. 전체 별점 평균
			public double getRateAvg();
			
}
