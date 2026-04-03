package reviewboard.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import reviewboard.dto.PageHandler;
import reviewboard.dto.ReviewBoardDTO;
import reviewboard.service.ReviewBoardService;

@Controller
public class ReviewBoardController {
	
	@Autowired
	ReviewBoardService rbService;
	
	@GetMapping({"/",""})
	public String reviewList(
	        Model model,
	        @RequestParam(value="page", defaultValue="1") int page,
	        @RequestParam(value="pageSize", defaultValue="8") int pageSize
	) {

	    int totalCnt = rbService.getAllCount();

	    List<ReviewBoardDTO> reviewList = new ArrayList<>();
	    PageHandler ph = null;

	    if (totalCnt > 0) {
	        ph = new PageHandler(totalCnt, page, pageSize);
	        reviewList = rbService.getAllReview(
	                ph.getStartRow(),
	                ph.getEndRow()
	        );
	    }

	    // 평균 별점 (리뷰 없으면 0)
	    double rateAvg = (totalCnt > 0) ? rbService.getRateAvg() : 0.0;

	    // 별 문자열
	    int rateForStar = (int) Math.floor(rateAvg);
	    String stars = switch (rateForStar) {
	        case 5 -> "★★★★★";
	        case 4 -> "★★★★☆";
	        case 3 -> "★★★☆☆";
	        case 2 -> "★★☆☆☆";
	        case 1 -> "★☆☆☆☆";
	        default -> "☆☆☆☆☆";
	    };

	    model.addAttribute("list", reviewList);
	    model.addAttribute("ph", ph);
	    model.addAttribute("rateAvg", rateAvg);
	    model.addAttribute("stars", stars);

	    return "reviewList";
	}

	
	@GetMapping("/write")
	public String writeForm() {
		return "reviewNew";
	}
	
	@PostMapping("/writePro")
	public String writePro(
			ReviewBoardDTO rdto,
			RedirectAttributes ra) {
		rbService.insertReview(rdto);
		ra.addFlashAttribute("msg", "리뷰 업로드 완료.");
		return "redirect:/";
	}
	
	@GetMapping("/detail")
	public String reviewDetail(
			Model model,
			@RequestParam("id") int id
			) {
		ReviewBoardDTO detailone = rbService.getOneReview(id);
		model.addAttribute("oneReview", detailone);
		return "reviewDetail";
	}
	
	@GetMapping("/update")
	public String updateForm(
			@RequestParam("id") int id,
			Model model
			) {
		ReviewBoardDTO detailone = rbService.getOneReview(id);
		model.addAttribute("oneReview", detailone);
		return "reviewUpdate";
	}
	
	@PostMapping("/updatePro")
	public String updatePro(
			ReviewBoardDTO rdto, 
			RedirectAttributes ra
			) {
		boolean isSuccess = rbService.updateReview(rdto);
		if(isSuccess) {
			ra.addFlashAttribute("msg", "게시글이 수정되었습니다.");
			return "redirect:/";
		} else {
			ra.addFlashAttribute("msg", "수정 실패.");
			return "redirect:/update?id=" + rdto.getId();
		}
	}
	
	@GetMapping("/deletePro")
	public String delete(
			@RequestParam("id") int id,
			RedirectAttributes ra
			) {
		boolean isSuccess = rbService.deleteReview(id);
		if(isSuccess) {
			ra.addFlashAttribute("msg", "게시글이 삭제되었습니다.");
			return "redirect:/";
		} else {
			ra.addFlashAttribute("msg", "삭제 실패.");
			return "redirect:/update?id=" + id;
		}
	}
	
	
}
