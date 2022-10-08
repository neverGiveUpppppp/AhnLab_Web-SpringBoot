package example.service;

import java.sql.SQLException;
import java.util.List;

import example.dao.Board;
import example.dao.BoardDao;

/**
 * @author csy62
 *
 */
public class BoardService {
	
	private BoardDao boardDao;
	

	public BoardDao getBoardDao() {
		return boardDao;
	}

	public void setBoardDao(BoardDao boardDao) {
		this.boardDao = boardDao;
	}
	
	public List<Board> selectBoardList() throws SQLException {
		return boardDao.selectBoardList();
	}

	public Board selectBoard(int boardSeq) throws SQLException {
		return boardDao.selectBoard(boardSeq);
	}

	public void insertBoard(Board board) throws SQLException {
		boardDao.insertBoard(board);
	}

	public void deleteBoard(int boardSeq) throws SQLException {
		boardDao.deleteBoard(boardSeq);
	}

}
