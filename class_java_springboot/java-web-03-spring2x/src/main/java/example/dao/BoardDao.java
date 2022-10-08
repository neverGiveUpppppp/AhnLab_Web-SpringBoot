package example.dao;


import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class BoardDao extends SqlMapClientDaoSupport {

	@SuppressWarnings("unchecked")
	public List<Board> selectBoardList() throws SQLException {
		return getSqlMapClientTemplate().queryForList("selectBoardList");
	}
	
	public Board selectBoard(int boardSeq) throws SQLException {
		return (Board) getSqlMapClientTemplate().queryForObject("selectBoard", boardSeq);
	}
	
	
	public void insertBoard(Board board) throws SQLException {
		getSqlMapClientTemplate().insert("insertBoard", board);
	}
	
	
	public void deleteBoard(int boardSeq) throws SQLException {
		getSqlMapClientTemplate().insert("deleteBoard", boardSeq);
	}

	public void updateBoard(Board board) throws SQLException {
		getSqlMapClientTemplate().update("updateBoard", board);
	}
	
}
