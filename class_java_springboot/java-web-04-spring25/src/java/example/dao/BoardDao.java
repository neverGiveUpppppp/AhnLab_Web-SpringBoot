package example.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDao {
	
	@Autowired
	private SqlMapClientTemplate sqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	public List<Board> selectBoardList() {
		return sqlMapClientTemplate.queryForList("selectBoardList");
	}
	
	public Board selectBoard(int boardSeq) {
		return (Board) sqlMapClientTemplate.queryForObject("selectBoard", boardSeq);
	}
	
	
	public void insertBoard(Board board) {
		sqlMapClientTemplate.insert("insertBoard", board);
	}
	
	
	public void deleteBoard(int boardSeq) {
		sqlMapClientTemplate.insert("deleteBoard", boardSeq);
	}

	public void updateBoard(Board board) {
		sqlMapClientTemplate.update("updateBoard", board);
	}

}
