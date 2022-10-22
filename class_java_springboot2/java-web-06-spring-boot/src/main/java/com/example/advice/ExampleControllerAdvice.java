package com.example.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.example.exception.DefaultException;

import lombok.extern.slf4j.Slf4j;



@ControllerAdvice
@Slf4j
public class ExampleControllerAdvice {

	final Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * Exception 발생에 대한 예외처리
	 * @param e
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(Exception e) {
		log.error("handleException",e);
		ModelAndView view = new ModelAndView("error/error.html");
		view.addObject("exception",e);
		return view;
		
	}
//	@ExceptionHandler(Exception.class)
//	public String handleException(Exception e) {
//		logger.error("BoardController handleException",e);
//		model.addObject("exception",e);
//	return model;
//		return e;
//	}
//	
	@ExceptionHandler(BindException.class)
	public ModelAndView handleBindException(BindException e) {
		log.error("handleBindException", e);
		ModelAndView view = new ModelAndView("/error/message.html");
		FieldError fieldError = e.getFieldError();
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
