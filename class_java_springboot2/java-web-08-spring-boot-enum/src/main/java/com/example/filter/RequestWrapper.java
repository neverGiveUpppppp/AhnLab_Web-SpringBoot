package com.example.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j // 롬복 임포트
public class RequestWrapper extends HttpServletRequestWrapper{
	// 클래스명 RequestWrapper 밑줄 : add constructor
	
	
	public RequestWrapper(HttpServletRequest request) {
		super(request);
		
	}
	
	@Override
	public String getParameter(String name) {
		log.info("getParameter name : {}", name); // 어떤게 먼지 실행되는지 파악하기 위해 로그 찍어보기
		String value = cleanXSS(super.getParameter(name));
		log.info("getParameter value : {}", value);
		return value;
	}
	
	@Override
	public String[] getParameterValues(String name) { // 배열이라 데이터처리 필요해서 소스가 길어짐
		// 현재 request name에 해당하는 파라미터 값을 배열로 가져옴
		String[] values = super.getParameterValues(name);
		// null인 경우 패스
		if (values == null) return values;
		for (int i = 0; i < values.length; i++) { // 안에 값을 변경시킬려는 것?
			values[i] = cleanXSS(values[i]);
		}
		return values;
//		키밸류 쓰는데 하나의 키에 값이 배열인 경우 배열을 받을 수 있게 해주고 값을 치환해주면 
//		리턴을 받을 수 있게됨(?) 강의 1:54 오후3:04
	}
	
	private String cleanXSS(String value) {
		if(value == null) return value;
		return value.replaceAll("scrpit", "");
	}

}
