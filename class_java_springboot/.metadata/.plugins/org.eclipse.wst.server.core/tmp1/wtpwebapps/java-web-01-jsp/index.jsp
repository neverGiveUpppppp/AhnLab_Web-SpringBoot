<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<p>Hello world</p>
	
<%
	boolean cardDisplay = true;

	if(cardDisplay){

%>
	<p>내 이름은 : 송영문</p>
<%
	} 
	
	List<String> names = Arrays.asList("Spring BOOT", "Vue.js", "React");

	int count = 0;
	for(String name : names){
			count++;
			out.println("<p>" + count + "</p>");
%>
	<p><%= name %></p>
<%
	}
%>	



</body>
</html>