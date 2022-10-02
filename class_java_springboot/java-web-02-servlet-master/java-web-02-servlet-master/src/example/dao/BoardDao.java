package example.dao;

import java.sql.SQLException;
import java.util.List;

import example.ibatis.BaseSqlMapConfig;

public class BoardDao {

	@SuppressWarnings("unchecked")
	public List<Board> selectBoardList() throws SQLException {
		return BaseSqlMapConfig.getSqlMapInstance().queryForList("selectBoardList", null);
	}

	public void insertBoard(Board board) throws SQLException {
		BaseSqlMapConfig.getSqlMapInstance().insert("insertBoard", board);
	}
}