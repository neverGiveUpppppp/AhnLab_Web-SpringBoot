package example.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import example.dao.Board;
import example.dao.BoardDao;

public class BoardFormServlet extends HttpServlet {

	private static final long serialVersionUID = -9206316189905969040L;
	
	private BoardDao boardDao = new BoardDao();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 뷰페이지를 지정
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/board/form.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String title = req.getParameter("title");
		String boardType = req.getParameter("boardType");
		String contents = req.getParameter("contents");
		String message = null;
		String nextUrl = "";
		boolean validate = true;
		boolean save = false;
		// 유효성 체크
		if (isEmpty(title)) {
			message = "제목을 입력해주세요.";
			validate = false;
		}
		if (validate && isEmpty(boardType)) {
			message = "종류를 선택해주세요.";
			validate = false;
		}
		if (validate && isEmpty(contents)) {
			message = "내용을 입력해주세요.";
			validate = false;
		}	
		// 성공을 한경우 DB에 등록
		if (validate) {
			Board board = new Board();
			board.setBoardType(boardType);
			board.setTitle(title);
			board.setContents(contents);
			try {
				boardDao.insertBoard(board);
				save = true;
				nextUrl = "/board/list";
				message = "게시물 등록이 완료되었습니다.";
			} catch (Exception e) {
				e.printStackTrace();
				message = "게시물 등록 중 시스템 에러가 발생하였습니다.";
			}			
		}
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/common/message.jsp");
		req.setAttribute("message", message);
		req.setAttribute("save", save);
		req.setAttribute("nextUrl", nextUrl);
		dispatcher.forward(req, resp);
	}
	
	// 공백체크 함수
	public boolean isEmpty(String value) {
		if (value == null || value.length() == 0) {
			return true;
		}
		return false;
	}
	
}