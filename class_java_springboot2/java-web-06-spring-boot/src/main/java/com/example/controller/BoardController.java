package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.mapper.Board;
import com.example.service.BoardService;

import lombok.RequiredArgsConstructor;

/**
 * 게시물 컨트롤러
 */
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
	
	final Logger logger = LoggerFactory.getLogger(getClass());

	private final BoardService boardService;

	/**
	 * 게시물 목록 화면
	 * @param model
	 * @return
	 */
//	@RequestMapping(value = "/board/list", method = RequestMethod.GET)
	@GetMapping
	public String list(ModelMap model) {
		// 게시물 목록
		model.addAttribute("boardList", boardService.selectBoardList());
		return "/board/list";
	}
	
//	@GetMapping
//	public String list(Model model) {
//		logger.debug("boardList...");
//		// 게시물 목록 조회 후 model에 boardList key로 저장
//		model.addAttribute("boardList",boardService.selectBoardList());
//		// jsp를 호출
//		return "/board/list";
//	}
	
	/**
	 * 게시물 등록 화면
	 * @param model
	 * @return
	 */

	@GetMapping("/form")
	public String form(Model model) {
		// jsp를 호출
		return "/board/form";
	}
	
	/**
	 * 등록 / 업데이트 처리
	 * @param board
	 * @return
	 */
	
	@PostMapping("/save")
	public String save(Board board) {
		// 유효성 체크
		Assert.hasLength(board.getUserName(),"회원 이름을 입력해주세요.");
		Assert.hasLength(board.getTitle(), "제목을 입력해주세요.");
		Assert.hasLength(board.getBoardType(), "종류를 입력해주세요.");
		Assert.hasLength(board.getContents(), "내용을 입력해주세요.");
		Board selectBoard = null;
		
		// 등록이 아닌 수정화면에서 요청인 경우
		if(board.getBoardSeq() > 0) {
			// 기존에 등록된 데이터인지 조회
			selectBoard = boardService.selectBoard(board.getBoardSeq());
		}
		// 존재하지 않는 경우
		if(selectBoard == null) {
			//게시물 등록 처리
			boardService.insertBoard(board);
		}else {
			boardService.updateBoard(board);
		}
		
		// 목록 화면으로 이동
		return "redirect:/board";
	}
	
	
	@PostMapping("/delete")
	public HttpEntity<Boolean> delete(@RequestParam(required = true) int boardSeq){
		logger.debug("delete");
		
		Board board = boardService.selectBoard(boardSeq);
		Assert.notNull(board, "게시글 정보가 없습니다.");
		//삭제 처리
		boardService.deleteBoard(boardSeq);
		return new ResponseEntity<Boolean>(HttpStatus.OK);
		
	}
	
	
}
