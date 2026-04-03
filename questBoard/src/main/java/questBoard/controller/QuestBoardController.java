package questBoard.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import questBoard.dto.QuestBoardDTO;
import questBoard.service.QuestBoardService;

@Controller
@RequestMapping("/board")
public class QuestBoardController {
	@Autowired
	QuestBoardService s;
	
	@GetMapping("/list")
	public String list(Model model) {
		List<QuestBoardDTO> listAll = s.getAllBoard();
		model.addAttribute("list", listAll);
		return "questBoard/listForm";
	}
	
	@GetMapping("/new")
	public String newForm() {
		return "questBoard/wirteForm";
	}
	
	@PostMapping("/newPro")
	public String newPro(
			QuestBoardDTO qdto,
			@RequestParam("file1") MultipartFile img
			) throws IllegalStateException, IOException {
		
		String savePath = "c:/Spring_Boot/questBoard/src/main/resources/static/img/";
		File saveDir = new File(savePath);
		if(!saveDir.exists()) {
			saveDir.mkdirs();
		}
		if(!img.isEmpty()) {
			String imgName = img.getOriginalFilename();
			File file1 = new File(savePath + imgName);
			
			img.transferTo(file1);
			qdto.setImg(imgName);
		}
		s.insertBoard(qdto);
		return "redirect:/board/list";
	}
	
	@GetMapping("/one")
	public String oneForm(
			@RequestParam("num") int num,
			Model model
			) {
		QuestBoardDTO one = s.getOneBoard(num);
		// System.out.println(s.isReExist(num)); // 1 이면 답글 쓰기 버튼 활성화 , 2이면 버튼 안 나타나게 인수 넘겨주려함
		model.addAttribute("onelist", one);
		model.addAttribute("step", s.isReExist(num));
		return "questBoard/oneForm";
	}
	
	@GetMapping("/reply")
	public String replyForm(
			@RequestParam("num") int num,
			@RequestParam("ref") int ref,
			@RequestParam("re_step") int re_step,
			Model model,
			RedirectAttributes ra
			) {

		model.addAttribute("num", num);
		model.addAttribute("ref", ref);
		model.addAttribute("re_step", re_step);
		return "questBoard/replyForm";
	}
	
	@PostMapping("/replyPro")
	public String replyPro(QuestBoardDTO qdto) {
		s.reInsert(qdto);
		return "redirect:/board/list";
	}
	
}
