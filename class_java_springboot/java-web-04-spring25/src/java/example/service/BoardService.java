package example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import example.mapper.Board;
import example.mapper.BoardMapper;

@Service
public class BoardService {
	
	@Autowired
	private BoardDao boardDao;
	
	/**
	 * 게시물 목록 조회 후 리턴
	 * @return
	 */
	public List<Board> selectBoardList() {
		return boardDao.selectBoardList();
	}

}
