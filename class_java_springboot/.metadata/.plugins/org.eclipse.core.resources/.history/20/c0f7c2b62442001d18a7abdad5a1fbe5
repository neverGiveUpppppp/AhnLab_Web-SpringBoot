package example.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import example.dao.BoardDao;

public class BoardListServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9206316189905969040L;
	
	private BoardDao boardDao = new BoardDao();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 뷰페이지에 사용될 데이터를 set
		try {
			req.setAttribute("list", boardDao.selectBoardList());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 뷰페이지를 지정
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/board/list.jsp");
		dispatcher.forward(req, resp);
	}
	
}