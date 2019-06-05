<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>게시판 관리</title>

<!-- css, js -->
<%@include file="/common/basicLib.jsp"%>
	<script>
	
	</script>

</head>
<body>
<body>
	<!-- header -->
	<%@include file="/common/header.jsp"%>

	<div class="container-fluid">
		<div class="row">

			<!-- left -->
			<%@include file="/common/left.jsp"%>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<div class="blog-header">
					<p class="lead blog-description">게시판 관리</p>
				</div>

				<div class="row">

					<div class="col-sm-8 blog-main">

						<div class="blog-post">
							<form class="form-inline" action="${pageContext.request.contextPath}/createBoard" method="post">
								<div class="form-group">
									<label class="sr-only">board</label>
									<p class="form-control-static">게시판 이름</p>
								</div>
								<div class="form-group">
									<label for="inputPassword2" class="sr-only"></label> 
									<input type="text" class="form-control" name="boardName">
								</div>
								<select class="form-control" name="useSelect">
									<option>사용</option>
									<option>미사용</option>
								</select>
								<button type="submit" class="btn btn-default">생성</button>
							</form>
						</div>
						
						<c:forEach items="${boardList}" var="board">
							<div class="blog-post">
							<form class="form-inline">
								<div class="form-group">
									<label class="sr-only">board</label>
									<p class="form-control-static">게시판 이름</p>
								</div>
								<div class="form-group">
									<label for="inputPassword2" class="sr-only"></label> 
									<input type="text" class="form-control" value="${board.board_name}">
								</div>
								<c:choose>
									<c:when test="${board.board_use == '1'}">
										<select class="form-control">
											<option selected>사용</option>
											<option>미사용</option>
										</select>
									</c:when>
									<c:when test="${board.board_use == '0'}">
										<select class="form-control">
											<option>사용</option>
											<option selected>미사용</option>
										</select>
									</c:when> 
								</c:choose>
								<button type="submit" class="btn btn-default">수정</button>
							</form>
							<br>
						</c:forEach>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>