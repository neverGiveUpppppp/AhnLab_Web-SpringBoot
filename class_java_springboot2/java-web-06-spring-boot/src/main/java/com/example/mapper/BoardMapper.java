package com.example.mapper;

import java.util.List;

public interface BoardMapper {
	
	List<Board> selectBoardList();

	Board selectBoard(int boardSeq);

	void insertBoard(Board board);

	void updateBoard(Board board);

	void deleteBoard(int boardSeq);

	
	
}
