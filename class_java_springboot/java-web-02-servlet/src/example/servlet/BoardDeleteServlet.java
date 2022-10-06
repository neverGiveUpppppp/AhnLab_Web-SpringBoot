package example.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import example.dao.Board;
import example.dao.BoardDao;

public class BoardDeleteServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5374675393399980710L;
	
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
			// 삭제 쿼리 실행
			boardDao.deleteBoard(board.getBoardSeq());
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/common/message.jsp");
			req.setAttribute("message", "게시물이 존재하지 않습니다.");
			req.setAttribute("save", false);
			dispatcher.forward(req, resp);			
			return;				
		}
		// 삭제 성공 후 다음페이지 이동
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/common/message.jsp");
		req.setAttribute("message", "게시물이 삭제되었습니다.");
		req.setAttribute("save", true);
		req.setAttribute("nextUrl", "/board/list");
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
