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
     * 게시물 목록 조회 및 화면
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/board/list", method = RequestMethod.GET) // 겟만 허용 //  {}써서 get,post 둘다 방는 방법있음
	// 복수의 url 실행할 수 있는 코드
	// @RequestMapping(value = { "/board/list", "/board/list2", "/board/notice" }, method = { RequestMethod.GET, RequestMethod.POST })
	// method 겟,포스트 둘 다 받을 수 있음 -> get과 post를 따로 나눠서 받고 싶다면 if쓰면 됨
	public String list(ModelMap model) {  // model변수는 null이 아님 스프링에서 쓸 수 있게 생성 시 바로 만들어놓음
		// 게시물 목록 조회 후 model에 boardList key로 저장
		model.addAttribute("boardList", boardService.selectBoardList());
		// jsp를 호출
		return "/board/list";
	}

}
