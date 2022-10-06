package example.dao;

import java.sql.SQLException;
import java.util.List;

import example.ibatis.BaseSqlMapConfig;

public class BoardDao {

	@SuppressWarnings("unchecked")
	public List<Board> selectBoardList() throws SQLException {
		return BaseSqlMapConfig.getSqlMapInstance().queryForList("selectBoardList");
	}
	
	public Board selectBoard(int boardSeq) throws SQLException {
		return (Board) BaseSqlMapConfig.getSqlMapInstance().queryForObject("selectBoard", boardSeq);
	}
	
	
	public void insertBoard(Board board) throws SQLException {
		BaseSqlMapConfig.getSqlMapInstance()
			.insert("insertBoard", board);
	}
	
	
	public void deleteBoard(int boardSeq) throws SQLException {
		BaseSqlMapConfig.getSqlMapInstance()
			.insert("deleteBoard", boardSeq);
	}
	
}