<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String boardSeq = request.getParameter("boardSeq");
	Connection connection = null;
	Statement stmt = null;
	ResultSet rs = null;
	String title = null;
	String boardType = null;
	String contents = null;
	try {
		// driver 
		Class.forName("com.mysql.jdbc.Driver");
		// 접속정보
		String jdbcUrl = "jdbc:mysql://localhost:3307/profile";
		String user = "root";
		String password = "1234";
		// db 커넥션 생성
		connection = DriverManager.getConnection(jdbcUrl, user, password);
		// stmt 생성 	
		stmt = connection.createStatement();
		String sql = "SELECT BOARD_SEQ, BOARD_TYPE, TITLE, CONTENTS, REG_DATE FROM T_BOARD WHERE BOARD_SEQ = " + boardSeq;
		// 쿼리 실행 후 결과 리턴
		rs = stmt.executeQuery(sql);
		// 상세 내용 출력
		while (rs.next()) {
			title = rs.getString("TITLE");
			boardType = rs.getString("BOARD_TYPE");
			contents = rs.getString("CONTENTS");
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
%>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap demo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
  </head>
  <body>
  	<%@ include file="/common/header.jsp" %>
	<div class="container">
		<form action="/board/save.jsp" method="post">
			<input type="hidden" name="boardSeq" value="<%=boardSeq%>"/>
			<div class="row mb-3">
				<label for="title" class="col-sm-2 col-form-label">제목</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" 
						name="title" id="title" value="<%=title %>" />
				</div>
			</div>
			<fieldset class="row mb-3">
				<legend class="col-form-label col-sm-2 pt-0">게시글 종류</legend>
				<div class="col-sm-10">
					<div class="form-check">
						<input class="form-check-input" type="radio" name="boardType"
							id="boardType1" value="NOTICE"  
							checked="<%= boardType.equals("NOTICE") ? "checked" : "" %>"> <label
							class="form-check-label" for="boardType1">공지사항</label>
					</div>
					<div class="form-check">
						<input class="form-check-input" type="radio" name="boardType"
							id="boardType2" value="COMMUNITY"
							checked="<%= boardType.equals("COMMUNITY") ? "checked" : "" %>"
							> <label
							class="form-check-label" for="boardType2">커뮤니티</label>
					</div>
				</div>
			</fieldset>
			<div class="row mb-3">
				<label for="contents" class="col-sm-2 col-form-label">내용</label>
				<div class="col-sm-10">
					<textarea class="form-control" name="contents" id="contents" rows="3"><%=contents %></textarea>
				</div>
			</div>
			<button type="submit" class="btn btn-primary">저장</button>
		</form>
	</div>
	<%@ include file="/common/footer.jsp" %>
  </body>
</html>