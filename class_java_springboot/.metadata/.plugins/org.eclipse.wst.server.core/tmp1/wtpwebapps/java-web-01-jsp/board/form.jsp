<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
	<title>게시물 등록</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<form action="/board/save.jsp" method="post">
			<div class="row mb-3">
				<label for="title" class="col-sm-2 col-form-label">제목</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="title" id="title">
				</div>
			</div>
			<fieldset class="row mb-3">
				<legend class="col-form-label col-sm-2 pt-0">게시글 종류</legend>
				<div class="col-sm-10">
					<div class="form-check">
						<input class="form-check-input" type="radio" name="boardType"
							id="boardType1" value="NOTICE"> <label
							class="form-check-label" for="boardType1">공지사항</label>
					</div>
					<div class="form-check">
						<input class="form-check-input" type="radio" name="boardType"
							id="boardType2" value="COMMUNITY"> <label
							class="form-check-label" for="boardType2">커뮤니티</label>
					</div>
				</div>
			</fieldset>
			<div class="row mb-3">
				<label for="contents" class="col-sm-2 col-form-label">내용</label>
				<div class="col-sm-10">
					<textarea class="form-control" name="contents" id="contents" rows="3"></textarea>
				</div>
			</div>
			<button type="submit" class="btn btn-primary">저장</button>
		</form>
	</div>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8" crossorigin="anonymous"></script>
</body>
</html>