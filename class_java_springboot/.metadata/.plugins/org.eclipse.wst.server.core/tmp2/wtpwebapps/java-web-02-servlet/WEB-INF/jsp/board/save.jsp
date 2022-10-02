<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String boardSeq = request.getParameter("boardSeq");
	String title = request.getParameter("title");
	String boardType = request.getParameter("boardType");
	String contents = request.getParameter("contents");
	String message = null;
	boolean validate = true;
	boolean save = false;
	// 유효성 체크
	if (isEmpty(title)) {
		message = "제목을 입력해주세요.";
		validate = false;
	}
	if (validate && isEmpty(boardType)) {
		message = "종류를 선택해주세요.";
		validate = false;
	}
	if (validate && isEmpty(contents)) {
		message = "내용을 입력해주세요.";
		validate = false;
	}	
	// 성공을 한경우 DB에 등록
	if (validate) {
		Connection connection = null;
		PreparedStatement stmt = null;
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
			String sql = null;
			// 쿼리 ? 순번으로 값을set
			// 수정요청으로 온 정보
			if (boardSeq != null && boardSeq.length() > 0) {
				StringBuffer sb = new StringBuffer();
				sb.append("UPDATE T_BOARD ");
				sb.append("SET ");
				sb.append("BOARD_TYPE = ?, ");
				sb.append("TITLE = ?, ");
				sb.append("CONTENTS = ? ");
				sb.append("WHERE BOARD_SEQ = ? ");
				
				sql = sb.toString();
				
				stmt = connection.prepareStatement(sql);
				stmt.setString(1, boardType);
				stmt.setString(2, title);
				stmt.setString(3, contents);
				stmt.setString(4, boardSeq);
			} else {
				// 최초 등록인경우만
				sql = "INSERT T_BOARD (BOARD_TYPE, TITLE, CONTENTS, REG_DATE) VALUES (?, ?, ?, NOW())";
				stmt = connection.prepareStatement(sql);
				stmt.setString(1, boardType);
				stmt.setString(2, title);
				stmt.setString(3, contents);
			}
			int result = stmt.executeUpdate();
			if (result == 0) {
				message = "게시물 등록 중 오류가 발생하였습니다.";
			} else {
				save = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "게시물 등록 중 시스템 에러가 발생하였습니다.";
		}			
	}
	
%>

<!-- JSP에 함수 생성시 !를 추가해서 사용 가능 -->
<%!
	
	// 공백체크 함수
	public boolean isEmpty(String value) {
		if (value == null || value.length() == 0) {
			return true;
		}
		return false;
	}
%>
<html>
<body>
	<script type="text/javascript">
	var save = <%=save%>;
	var message = '<%=message%>';
	// 등록 성공 시
	if (save) {
		alert('게시물 저장이 되었습니다.');
		location.href = '/board/list.jsp';
	} else {
		alert(message);
		// 뒤로가기
		history.back(-1);
	}
	</script>
</body>
</html>
