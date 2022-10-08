package example.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import example.dao.Board;
import example.dao.BoardDao;

public class BoardDetailServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5374675393399980710L;
	
	// 보드dao 사용할 수 있게 객체등록
	private BoardDao boardDao = new BoardDao();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String boardSeq = req.getParameter("boardSeq");
		if (boardSeq == null || boardSeq.length() == 0) {
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/common/message.jsp");
			req.setAttribute("message", "게시물 번호가 없습니다.");
			req.setAttribute("save", false);
			dispatcher.forward(req, resp);			
			return;
		}
		try {
			Board board = boardDao.selectBoard(Integer.parseInt(boardSeq));
			// DB에 실제 존재하는지 체크
			if (board == null) {
				RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/common/message.jsp");
				req.setAttribute("message", "게시물이 존재하지 않습니다.");
				req.setAttribute("save", false);
				dispatcher.forward(req, resp);			
				return;		
			}
			// 현재 request요청(req)에 board 데이터를 board key로 저장 // "board" 키값or네임이라고도함. ("board", board);의 board는 밸류 
			req.setAttribute("board", board); // setAttribute(키or네임,밸류)
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/common/message.jsp");
			req.setAttribute("message", "게시물이 존재하지 않습니다.");
			req.setAttribute("save", false);
			dispatcher.forward(req, resp);			
			return;				
		}
		// 등록 화면
		req.getRequestDispatcher("/WEB-INF/jsp/board/detail.jsp")
		.forward(req, resp);
	}
	
}
