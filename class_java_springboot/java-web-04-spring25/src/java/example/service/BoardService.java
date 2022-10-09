package example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import example.mapper.Board;
import example.mapper.BoardMapper;

@Service
public class BoardService {
	
	@Autowired
	private BoardMapper boardMapper;

	public List<Board> selectBoardList() {
		return boardMapper.selectBoardList();
	}

}
