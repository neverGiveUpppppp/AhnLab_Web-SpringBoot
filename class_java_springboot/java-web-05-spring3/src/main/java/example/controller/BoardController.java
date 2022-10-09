package example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import example.service.BoardService;

/**
 * 게시물 컨트롤러
 */
@Controller
public class BoardController {
	
	final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private BoardService boardService;

	/**
	 * 게시물 목록 화면
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/board/list", method = RequestMethod.GET)
	public String list(ModelMap model) {
		// 게시물 목록
		model.addAttribute("boardList", boardService.selectBoardList());
		return "/board/list";
	}

}
