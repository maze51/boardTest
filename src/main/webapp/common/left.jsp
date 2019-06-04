<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<div class="col-sm-3 col-md-2 sidebar">
	<ul class="nav nav-sidebar">
		<li class="active"><a href="${pageContext.request.contextPath}/createBoard">게시판 생성</a></li>
		<li class="active"> </li>
		
<%-- 		<li class="active"><a href="${pageContext.request.contextPath}/userPagingList?page=1&pageSize=10">사용자페이징리스트</a></li> --%>
		
		<c:forEach items="${BOARD_LIST }" var="board">
			<form id="frm" class="form-signin" action="${pageContext.request.contextPath}/showBoard" method="post">
				<li class="active"><a href="${pageContext.request.contextPath}/showBoard">${board.board_name}</a></li>	
			<input type="hidden" name="boardName" value="${board.board_name }">
			</form>
		</c:forEach>
	</ul>
</div>