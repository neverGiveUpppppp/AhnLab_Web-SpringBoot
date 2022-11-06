package com.example.service;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.controller.form.BoardSaveForm;
import com.example.domain.Board;
import com.example.domain.BoardType;
import com.example.mapper.BoardMapper;
import com.example.security.userdetails.SecurityUserDetails;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // 롬복 어노테이션
public class BoardService {
	
//	@Autowired 제거하고 @RequiredArgsConstructor 추가
	private final BoardMapper boardMapper; // @RequiredArgsConstructor 추가했으면 final 추가

//	public List<Board> selectBoardList() {
//		return boardMapper.selectBoardList();
//	}
	
	public List<Board> selectBoardList(BoardType boardType, String query) {
		return boardMapper.selectBoardList(boardType, query);
	}


	public Board selectBoard(int boardSeq) {
		return boardMapper.selectBoard(boardSeq);
	}


	public void deleteBoard(int boardSeq) {
		boardMapper.deleteBoard(boardSeq);
	}
	
	
	public Board save(BoardSaveForm form, Authentication authentication) {
		SecurityUserDetails details = (SecurityUserDetails) 
			authentication.getPrincipal();
		Board board = new Board();
		board.setBoardSeq(form.getBoardSeq());	
		board.setBoardType(form.getBoardType());
		board.setTitle(form.getTitle());
		board.setContents(form.getContents());
		board.setUserName(details.getNickname());
		board.setMemberSeq(details.getMemberSeq());
		boardMapper.insertBoard(board);
		return board;
	}
	
	public void update(BoardSaveForm form) {
		Board board = new Board();
		board.setBoardSeq(form.getBoardSeq());	
		board.setBoardType(form.getBoardType());
		board.setTitle(form.getTitle());
		board.setContents(form.getContents());
		boardMapper.updateBoard(board);
	}

	
	
}
