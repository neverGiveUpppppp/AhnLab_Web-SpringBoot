package com.example.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j // lombok import
public class XssFilter implements Filter {  // filter import : javax.servlet.Filter
	// 클래스명 XssFilter 밑줄 : add unimplemented
	
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("filterConfig : {}",filterConfig); // log.info : @Slf4j 임포트해야함
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		log.info("doFilter : {}",request.getParameter("title"));
		chain.doFilter(new RequestWrapper((HttpServletRequest) request), response);
		
	} 

	
}
