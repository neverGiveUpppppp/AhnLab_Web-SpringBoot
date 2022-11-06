package com.example.advice;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;



@ControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class ExampleControllerAdvice {
	
	private final MappingJackson2JsonView jsonView;


	

	final Logger logger = LoggerFactory.getLogger(getClass());
	
//	/**
//	 * Exception 발생에 대한 예외처리
//	 * @param e
//	 * @return
//	 */
//	@ExceptionHandler(Exception.class)
//	public ModelAndView handleException(Exception e) {
//		log.error("handleException",e);
//		ModelAndView view = new ModelAndView("error/error.html");
//		view.addObject("exception",e);
//		return view;
//		
//	}
//	@ExceptionHandler(Exception.class)
//	public String handleException(Exception e) {
//		logger.error("BoardController handleException",e);
//		model.addObject("exception",e);
//	return model;
//		return e;
//	}
//	
	
	/**
	 * Exception 발생에 대한 예외처리
	 * @param e
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(Exception e, HttpServletRequest request) {
		log.error("handleException", e);
		String requested = request.getHeader("X-Requested-With");
		// 응답값을 json 포맷으로 처리
		if (requested != null && requested.equals("XMLHttpRequest")) {
			log.info("해당 조건에는 json으로 응답처리");
			ModelAndView view = new ModelAndView(jsonView);
			// 응답을 오류 상태로
			view.setStatus(HttpStatus.BAD_REQUEST);
			view.addObject("message", e.getMessage());
			return view;
		}
		ModelAndView view = new ModelAndView("/error/message.html");
		view.addObject("message", e.getMessage());		
		return view;
	}

	
	@ExceptionHandler(BindException.class)
	public ModelAndView handleBindException(BindException e, 
											HttpServletRequest request) {
		log.error("handleBindException", e);
		FieldError fieldError = e.getFieldError();
		String requested = request.getHeader("X-Requested-With");
		// 응답값을 json 포맷으로 처리
		if (requested != null && requested.equals("XMLHttpRequest")) {
			log.info("해당 조건에는 json으로 응답처리");
			ModelAndView view = new ModelAndView(jsonView);	
			// 응답을 오류 상태로
			view.setStatus(HttpStatus.BAD_REQUEST);
			view.addObject("message", fieldError.getDefaultMessage());
			return view;
			// 스프링 빈에 올라간 뷰를 올려주거나 .. 강의 8:20
//			json 변환 컨버터를 넣어줘야함. 스프링 빈에 올려두고 써야하기 때문에 webMvConfiguration으로 가자
		}
		ModelAndView view = new ModelAndView("/error/message.html");
		view.addObject("message", fieldError.getDefaultMessage());		
		return view;

	}

	
//	@ExceptionHandler(DefaultException.class)
//	public ModelAndView handleException(DefaultException e) {
//		log.error("handleException",e);
//		ModelAndView view = new ModelAndView("error/error.html");
//		view.addObject("exception",e);
//		return view;
//	}
	
}
