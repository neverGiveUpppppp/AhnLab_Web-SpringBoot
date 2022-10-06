package example.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import example.dao.BoardDao;

public class BoardListServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5374675393399980710L;
	
	private BoardDao boardDao = new BoardDao();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			// 게시물 목록 조회를 boardDao에 호출하고 결과 값을 boardList key 값으로 request에 저장한다.
			req.setAttribute("boardList", boardDao.selectBoardList());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 현재 req, resp 객체를 list.jsp에 사용할 수 있게 forward 시킨다.
		req.getRequestDispatcher("/WEB-INF/jsp/board/list.jsp")
		.forward(req, resp);
	}
	
}
