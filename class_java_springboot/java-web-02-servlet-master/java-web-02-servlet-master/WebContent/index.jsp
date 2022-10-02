<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p>Hellow world</p>
	<h2>조건문</h2>
	<c:set var="cardDisplay" value="true"/>
	<c:if test="${cardDisplay}">
	<p>내 이름은 : 송영문</p>	
	</c:if>
	<h2>if ~ else 조건문</h2>
	<c:choose>
		<c:when test="${cardDisplay}">노출이 가능하다.</c:when>
		<c:otherwise>
			<p>노출이 불가능하다.</p>
		</c:otherwise>
	</c:choose>	
	<c:set var="names" value="${fn:split('Spring Boot,Vue.js,React,Test',',')}" />
	<c:set var="count" value="0"/>
	<h2>반복문</h2>
	<c:forEach var="name" items="${names}">
		<c:set var="count" value="${count + 1}"/>
		<p><c:out value="${count}"/></p>
		<p>${name}</p>
	</c:forEach>
</body>
</html>