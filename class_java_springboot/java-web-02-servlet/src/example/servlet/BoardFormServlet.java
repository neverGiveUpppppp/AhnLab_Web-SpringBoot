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

public class BoardFormServlet extends HttpServlet {

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
		req.getRequestDispatcher("/WEB-INF/jsp/board/form.jsp")
		.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 인코딩 : 한글깨짐방지용
		req.setCharacterEncoding("UTF-8"); // doGet은 톰캣에서 자동으로 인코딩해줘서 잘안깨짐. 톰캣에 옵션설정이 있음
		
		// 저장기능 로직을 구현
		String boardSeq = req.getParameter("boardSeq");
		String title = req.getParameter("title");
		String username =  req.getParameter("username");
		String boardType = req.getParameter("boardType");
		String contents = req.getParameter("contents");
		String message = null;
		boolean validate = true;
		boolean save = false;
		String nextUrl = "";
		// 유효성 체크
		if (isEmpty(title)) {
			message = "제목을 입력해주세요.";
			validate = false;
		}
		if (isEmpty(username)) {
			message = "회원명을 입력해주세요.";
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
			board.setUsername(username);
			board.setContents(contents);
			try {
				boolean first = isEmpty(boardSeq);
				Board selectBoard = null;
				// 등록이 아닌 수정화면에서 요청인경우
				if (!first) {
					// 기존에 등록된 데이터인지 조회
					selectBoard = boardDao.selectBoard(Integer.parseInt(boardSeq));	
				}

				// 존재하지 않는 경우
				if(selectBoard == null) {
					//게시물 등록 처리
					boardDao.insertBoard(board);
				}else {
					board.setBoardSeq(Integer.parseInt(boardSeq));
					boardDao.updateBoard(board);
				}
				
				nextUrl = "/board/list";
				message = "게시물 등록이 성공하였습니다.";
				save = true;
			} catch (Exception e) {
				e.printStackTrace();
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
