<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap demo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
  </head>
  <body>
  	<jsp:include page="/WEB-INF/jsp/common/header.jsp"/>
	<div class="container">
		<form action="/board/form" method="post">
			<input type="hidden" name="boardSeq" value="${board.boardSeq }"/>
			<div class="row mb-3">
				<label for="title" class="col-sm-2 col-form-label">제목</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" 
						name="title" id="title" value="" />
				</div>
			</div>
			<div class="row mb-3">
				<label for="username" class="col-sm-2 col-form-label">회원명</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" 
						name="username" id="username" value="${board.username}" />
				</div>
			</div>
			<fieldset class="row mb-3">
				<legend class="col-form-label col-sm-2 pt-0">게시글 종류</legend>
				<div class="col-sm-10">
					<div class="form-check">
						<input class="form-check-input" type="radio" name="boardType"
							id="boardType1" value="NOTICE"  
							${board.boardType == 'NOTICE' ? 'checked="checked"' : ''}
						
							> <label
							class="form-check-label" for="boardType1">공지사항</label>
					</div>
					<div class="form-check">
						<input class="form-check-input" type="radio" name="boardType"
							id="boardType2" value="COMMUNITY"
							${board.boardType == 'COMMUNITY' ? 'checked="checked"' : ''}
							
							> <label
							class="form-check-label" for="boardType2">커뮤니티</label>
					</div>
				</div>
			</fieldset>
			<div class="row mb-3">
				<label for="contents" class="col-sm-2 col-form-label">내용</label>
				<div class="col-sm-10">
					<textarea class="form-control" name="contents" id="contents" rows="3">${board.contents}</textarea>
				</div>
			</div>
			<button type="submit" class="btn btn-primary">저장</button>
		</form>
	</div>
	<jsp:include page="/WEB-INF/jsp/common/footer.jsp" />
  </body>
</html>