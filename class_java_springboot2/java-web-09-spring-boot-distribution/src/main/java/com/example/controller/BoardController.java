package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.annotation.RequestConfig;
import com.example.controller.form.BoardSaveForm;
import com.example.domain.Board;
import com.example.domain.BoardType;
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
//	@GetMapping
//	@RequestConfig(menu = "BOARD")
//	public String list(Model model) {
//		logger.debug("boardList...");
//		// 게시물 목록 조회 후 model에 boardList key로 저장
//		model.addAttribute("boardList",boardService.selectBoardList());
//		// jsp를 호출
//		return "/board/list";
//	}

	@GetMapping("/{boardType}")
	@RequestConfig(menu = "BOARD")
	public String list(Model model, @PathVariable BoardType boardType, 
			@RequestParam(required = false) String query) {
		// 게시물 목록 조회 후 model에 boardList key로 저장
		model.addAttribute("boardList", boardService.selectBoardList(boardType, query));
		model.addAttribute("boardType", boardType);
		model.addAttribute("boardTypes", BoardType.values());
		// jsp를 호출
		return "board/list";
	}

	
	
	
	
	
//	/**
//	 * 게시물 상세 화면
//	 * @param model
//	 * @return
//	 */
//	
//	@GetMapping("/{boardSeq}")
//	@RequestConfig(menu = "BOARD")
////	public String detail(Model model, @PathVariable(name = "boardSeq") int boardSeq) { // 스프링에서 requried를 true로 기본값으로 준다고함
//	public String detail(Model model, @PathVariable int boardSeq) { // 스프링에서 requried를 true로 기본값으로 준다고함
//		// 게시물 조회
//		Board board = boardService.selectBoard(boardSeq);
//		Assert.notNull(board, "게시글 정보 없습니다");
//		// detail.html에서 board를 사용하기 위해 model에 넣는다
//		model.addAttribute("board", board);
//		return "/board/detail";
//	}
	
	
	/**
	 * 게시물 상세 화면
	 * @param model
	 * @return
	 */
	@GetMapping("/{boardType}/{boardSeq}")
	@RequestConfig(menu = "BOARD")
	public String detail(Model model, @PathVariable BoardType boardType,
			@PathVariable(required = true) int boardSeq) {
		logger.debug("detail");
		Board board = boardService.selectBoard(boardSeq);
		Assert.notNull(board, "게시글 정보가 없습니다.");
		// 게시물 상세정보 set
		model.addAttribute("board", board);
		model.addAttribute("boardType", boardType);
		model.addAttribute("boardTypes", BoardType.values());
		// jsp를 호출
		return "board/detail";
	}

	
	
	
	
	
	
//	
//	/**
//	 * 게시물 등록 화면
//	 * @param model
//	 * @return
//	 */
//
//	@GetMapping("/form")
//	@RequestConfig(menu = "BOARD")
//	public String form(Model model) {
//		// jsp를 호출
//		return "/board/form";
//	}
//	
	
	/**
	 * 게시물 등록 화면
	 * @param model
	 * @return
	 */
	@GetMapping("/{boardType}/form")
	@RequestConfig(menu = "BOARD")
	public String form(Model model, @PathVariable BoardType boardType) {
		model.addAttribute("boardType", boardType);
		model.addAttribute("boardTypes", BoardType.values());
		return "board/form";
	}

	
	
	/**
	 * 게시물 등록 화면
	 * 바디에 넣어서 보내는 방식. 위에꺼는 기존 방식
	 * @param model
	 * @return
	 */
	
	@GetMapping("/form-body")
	@RequestConfig(menu = "BOARD")
	public String formBody(Model model) {
		// jsp를 호출
		return "board/form-body";
	}
	
	
//	/**
//	 * 게시물 수정 화면
//	 * @param model
//	 * @param boardSeq
//	 * @return 
//	 */
//	
//	// AOP 적용 전
//	@GetMapping("/edit/{boardSeq}")
//	public String edit(Model model, @PathVariable(required = true)int boardSeq){ // 여기 required도 디폴트가 true라 생략가능
//		logger.debug("edit");
//		// 데이터 조회
//		Board board = boardService.selectBoard(boardSeq);
//		Assert.notNull(board, "게시글 정보가 없습니다.");
//		model.addAttribute("board", board);
//		//jsp 호출
//		return "/board/form";
//	}
	
//	// AOP 적용 후
//	@GetMapping("/edit/{boardSeq}")
//	@RequestConfig(menu = "BOARD")
//	public String edit(Model model, @PathVariable int boardSeq) {
//		model.addAttribute("board", boardService.selectBoard(boardSeq));
//		return "/board/form";
//	}

	
	/**
	 * 게시물 수정 화면
	 * @param model
	 * @param boardSeq
	 * @return 
	 */
	
	@GetMapping("/{boardType}/edit/{boardSeq}")
	@RequestConfig(menu = "BOARD")
	public String edit(Model model, @PathVariable BoardType boardType, 
			@PathVariable int boardSeq) {
		// 게시물 상세정보 set
		model.addAttribute("board", boardService.selectBoard(boardSeq));
		model.addAttribute("boardType", boardType);
		model.addAttribute("boardTypes", BoardType.values());
		// jsp를 호출
		return "board/form";
	}

	
	
	
	
	/**
	 * 게시물 등록/저장 요청 처리
	 * @param model
	 * @return
	 */
	
//	@PostMapping("/save")
//	public String save(Board board) {
//		Board board = new Board();
//		board.setBoardType(boardType);
//		board.setTitle(title);
//		board.setContents(contents);
//		try {
//			// 게시물 등록 처리
//			boardDao.insertBoard(board);
//			nextUrl = "/board/list";
//			message = "게시물 등록이 성공하였습니다.";
//			save = true;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		위 코드 간소화		
//		boardDao.insertBoard(board);
//		return "board/form";
		
		
		
//		// 유효성 체크
//		Assert.hasLength(board.getUserName(),"회원 이름을 입력해주세요.");
//		Assert.hasLength(board.getTitle(), "제목을 입력해주세요.");
//		Assert.hasLength(board.getBoardType(), "종류를 입력해주세요.");
//		Assert.hasLength(board.getContents(), "내용을 입력해주세요.");
//		Board selectBoard = null;
//	// 등록이 아닌 수정화면에서 요청인 경우
//	if(board.getBoardSeq() > 0) {
//		// 기존에 등록된 데이터인지 조회
//		selectBoard = boardService.selectBoard(board.getBoardSeq());
//	}
//	// 수정인 경우 업데이트
//	if(selectBoard != null) {
//		boardService.updateBoard(board);
//	}else {
//		//게시물 등록 처리
//		boardService.insertBoard(board);
//	}
//	
//	// 목록 화면으로 이동(URL 리다이렉트)
//	return "redirect:/board";
//}		

//	@PostMapping("/save")
//	public String save(@Validatedz BoardSaveForm form) { 
//			
//		// 유효성 체크
//		Board selectBoard = null;
//		
//		// 등록이 아닌 수정화면에서 요청인 경우
//		if(form.getBoardSeq() > 0) {
//			// 기존에 등록된 데이터인지 조회
//			selectBoard = boardService.selectBoard(form.getBoardSeq());
//		}
//		// 수정인 경우 업데이트
//		if(selectBoard != null) {
//			boardService.updateBoard(form);
//		}else {
//			//게시물 등록 처리
//			boardService.insertBoard(form);
//		}
//		
//		// 목록 화면으로 이동(URL 리다이렉트)
//		return "redirect:/board";
//	}
	
	
	
	/**
	 * 등록 / 업데이트 처리
	 * @param form
	 * @param authentication
	 * @return
	 */
	// 위의 save() 코드에서 AOP하면서 아래 코드로 변경
//	@PostMapping("/save")
//	public String save(@Validated BoardSaveForm form, Authentication authentication) { 
//		boardService.save(form, authentication);
//	}
	
	
	
	
	/**
	 * 등록 처리
	 * @param form
	 * @return
	 */
	@PostMapping("/save")
	@RequestConfig(menu = "BOARD")
	public String save(@Validated BoardSaveForm form, Authentication authentication) {
		Board board = boardService.save(form, authentication);
		// 목록 화면으로 이동
//		return "redirect:/board";
		return "redirect:/board/" + form.getBoardType().name() + "/" + board.getBoardSeq();
	}
	
	
	/**
	 * 업데이트 처리
	 * @param form
	 * @return
	 */
	@PostMapping("/update")
	@RequestConfig(menu = "BOARD")
	public String update(@Validated BoardSaveForm form) {
		boardService.update(form);
		// 상세화면으로 이동
//		return "redirect:/board/" + form.getBoardSeq();
		return "redirect:/board/" + form.getBoardType().name() + "/" + form.getBoardSeq();

	}

	
	/**
	 * 게시물 등록/저장 요청 처리(클라이언트 바디에 json으로 받기)
	 * @param model
	 * @return
	 */
	@PostMapping("/save-body")
	@ResponseBody
	@RequestConfig(menu = "BOARD")
	public HttpEntity<Integer> saveBody(@Validated @RequestBody BoardSaveForm form,
			Authentication auth) { // 인티저 넣는 이유 : 성공했을 때 보드시크 받기 때문에 값을 인티저로 주기위함
		// 리퀘스트바디를 넣어줘야 클래스 객체를 읽어줌
			
		// 유효성 체크
		Board selectBoard = null;
		
		// 등록이 아닌 수정화면에서 요청인 경우
		if(form.getBoardSeq() > 0) {
			// 기존에 등록된 데이터인지 조회
			selectBoard = boardService.selectBoard(form.getBoardSeq());
		}
		// 수정인 경우 업데이트
		if(selectBoard != null) {
			boardService.update(form);
		}else {
			//게시물 등록 처리
			boardService.save(form, auth);
		}
		
		// 목록 화면으로 이동(URL 리다이렉트)
		return new ResponseEntity<Integer>(form.getBoardSeq(), HttpStatus.OK);
		// HttpStatus.OK가 성공시 반환값. 에러는 다른걸로 콜백됨
	}
	
	
	
	/**
	 * 게시글 삭제 처리
	 * @param model
	 * @param boardSeq
	 * @return
	 */
	@PostMapping("/delete")
	@ResponseBody // json타입으로 서버가 받을 수 있게(?)
	@RequestConfig(menu = "BOARD")
//	public HttpEntity<Boolean> delete(@RequestParam(required = true) int boardSeq){ // 여기 required도 디폴트가 true라 생략가능
	public HttpEntity<Boolean> delete(@RequestParam int boardSeq){ // 여기 required도 디폴트가 true라 생략가능
		logger.debug("delete");
		
		// 데이터 조회
		Board board = boardService.selectBoard(boardSeq);
		// board가 null일 경우 에러 메세지 출력
		Assert.notNull(board, "게시글 정보가 없습니다.");
		//삭제 처리
		boardService.deleteBoard(boardSeq);
		return new ResponseEntity<Boolean>(HttpStatus.OK);
		
	}
	

	
	
	/**
	 * Exception 발생에 대한 예외처리
	 * controller advice에서 예외메세지 출력 전 컨트롤러에서 발생시켜보기(컨트롤러 안에있는게 우선순위)
	 * @param e
	 * @return
	 */
	//@ExceptionHandler(Exception.class) // 유저에게 원하는 에러만 보여주기 위해 BindException 사용했는데 우선순위가 컨트롤러가 먼저라 주석처리함
	public ModelAndView handleException(Exception e) {
		logger.error("BoardController handleException",e);
		ModelAndView view = new ModelAndView("error/error.html");
		view.addObject("exception",e);
		return view;
		
	}
	
	
}
