package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.controller.form.BoardSaveForm;
import com.example.mapper.Board;
import com.example.mapper.BoardMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // 롬복 어노테이션
public class BoardService {
	
//	@Autowired 제거하고 @RequiredArgsConstructor 추가
	private final BoardMapper boardMapper; // @RequiredArgsConstructor 추가했으면 final 추가

	public List<Board> selectBoardList() {
		return boardMapper.selectBoardList();
	}

	public Board selectBoard(int boardSeq) {
		return boardMapper.selectBoard(boardSeq);
	}

	public void insertBoard(BoardSaveForm form) {
		boardMapper.insertBoard(form);
	}

	public void updateBoard(BoardSaveForm form) {
		boardMapper.updateBoard(form);
	}

	public void deleteBoard(int boardSeq) {
		boardMapper.deleteBoard(boardSeq);
	}
	
}
