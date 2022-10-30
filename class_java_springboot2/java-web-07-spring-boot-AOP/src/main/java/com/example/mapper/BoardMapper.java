package com.example.mapper;

import java.util.List;

import com.example.controller.form.BoardSaveForm;

public interface BoardMapper {
	
	List<Board> selectBoardList();

	Board selectBoard(int boardSeq);

//	void insertBoard(BoardSaveForm form); // Validation하면서 Board->BoardSaveForm를 
	void insertBoard(Board board); // AOP 만들면서 다시 BoardSaveForm-> Board로 변경

//	void updateBoard(BoardSaveForm form); // Validation하면서 Board->BoardSaveForm를
	void updateBoard(Board board); // AOP 만들면서 다시 BoardSaveForm-> Board로 변경

	void deleteBoard(int boardSeq);

	
	
}
