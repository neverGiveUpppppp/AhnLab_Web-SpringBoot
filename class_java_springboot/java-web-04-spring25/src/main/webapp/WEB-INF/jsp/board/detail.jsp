<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
	<title>게시물 상세</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/common/header.jsp"/>
	<div class="container">
		<div class="card" style="width: 18rem;">
		  <img src="..." class="card-img-top" alt="...">
		  <div class="card-body">
		    <h5 class="card-title">${board.title}</h5>
		    <p class="card-text">${board.contents}</p>
		    <a href="javascript:;" class="btn btn-primary">${board.regDate}</a>
		  </div>
		</div>		
		<a href="/board/list" class="btn btn-primary mt-5">목록</a>
		<a href="/board/form?boardSeq=${board.boardSeq}" class="btn btn-primary mt-5">수정</a>
		<a href="javascript:handleDelete('/board/delete?boardSeq=${board.boardSeq}');" class="btn btn-primary mt-5">삭제</a>
	</div>
	<script>
	function handleDelete(url) {
		if (confirm('정말 삭제하시겠습니까?')) {
			location.href = url;
		}
	}
	</script>
	<jsp:include page="/WEB-INF/jsp/common/footer.jsp" />
</body>
</html>