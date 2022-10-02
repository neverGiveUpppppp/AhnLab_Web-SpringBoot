<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!doctype html>
<html>
<head>
	<title>게시물 목록</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/common/header.jsp" />
	<div class="container">
		<h2>게시물 목록</h2>
		<table class="table table-striped table-hover">
		<thead>
		<tr>
			<th>번호</th>
			<th>종류</th>
			<th>제목</th>
			<th>등록일자</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach var="data" items="${list}">
		<tr>
			<td>${data.boardSeq}</td>
			<td>${data.boardType}</td>
			<td><a href="/board/detail?boardSeq=${data.boardSeq}">${data.title}</a></td>
			<td>${data.regDate}</td>
		</tr>
		</c:forEach>
		</tbody>
		</table>
		<a href="/board/form" class="btn btn-primary">등록</a>
	</div>
	<jsp:include page="/WEB-INF/jsp/common/footer.jsp" />
</body>
</html>