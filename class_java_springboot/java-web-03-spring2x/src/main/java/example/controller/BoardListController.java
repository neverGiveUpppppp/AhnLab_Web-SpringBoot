package example.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.BaseCommandController;

import example.service.BoardService;

public class BoardListController extends BaseCommandController {
	
	final Logger logger = LogManager.getLogger(getClass());

	private BoardService boardService;

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		// 데이터 set, view 페이지 지정
		logger.info("boardList!");
		ModelAndView view = new ModelAndView();
		try {
			// 게시물 목록 조회를 boardDao에 호출하고 결과 값을 boardList key 값으로 request에 저장한다.
			request.setAttribute("boardList", boardService.selectBoardList());
		} catch (SQLException e) {
//			logger.error(e); // printStackTrace 쓸 필요 없이 log4j사용
			logger.error("boardListError", e); // 로그 찍힐 때 " " 안에 메세지가 같이 찍힘
		}
		// view 페이지 지정
		view.setViewName("/board/list");
		// setView : xml,json같기 데이터를 렌더링할 때. 지금은 jsp만 렌더링하는 중
		return view;
	}

	public BoardService getBoardService() {
		return boardService;
	}

	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}

}
