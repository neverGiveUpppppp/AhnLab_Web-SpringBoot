package example.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import example.dao.Board;
import example.dao.BoardDao;

public class BoardDetailServlet extends HttpServlet {

	private static final long serialVersionUID = -9206316189905969040L;
	
	private BoardDao boardDao = new BoardDao();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		
		
		
		// 등록화면
		
		
	}
	
	
	
}