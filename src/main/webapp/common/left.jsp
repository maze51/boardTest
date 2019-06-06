<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<style>
		a:hover{
			cursor : pointer;
		}
	</style>
	<script>
		$(document).ready(function(){
			$("#blist a").on("click", function(){
				//console.log("a click");
				//console.log($(this).text());
				var bId = $(this).data("bid");
<<<<<<< HEAD
				$(".bi").val(bId);
				
				var bname = $(this).data("bname");
				$(".bn").val(bname);
				
				$("#frm").submit();
			})
		})
	</script>
</head>
<body>
	
=======
				$(".bn").val(bId);
				$("#frm").submit();
			})
		})
	</script>
</head>
<body>
	<form id="frm" class="form-signin" action="${pageContext.request.contextPath}/showBoard" method="post">
>>>>>>> refs/remotes/origin/master
		<div class="col-sm-3 col-md-2 sidebar">
			<ul class="nav nav-sidebar">
				<li class="active"><a href="${pageContext.request.contextPath}/createBoard">게시판 생성</a></li>
			</ul>	
		
			<ul id="blist" class="nav nav-sidebar">
				<c:forEach items="${BOARD_LIST }" var="board">
<<<<<<< HEAD
					<c:choose>
						<c:when test="${board.board_use == 1}">
							<form id="frm" class="form-signin" action="${pageContext.request.contextPath}/showBoard" method="get">
								<li class="active"><a data-bid="${board.board_id }" data-bname="${board.board_name}">${board.board_name}</a></li>	
								<input type="hidden" class="bi" name="boardId">
								<input type="hidden" class="bn" name="boardName">
							</form>
						</c:when>
					</c:choose>
				</c:forEach>
			</ul>
		</div>
	
=======
					<li class="active"><a data-bid="${board.board_id }" href="#">${board.board_name}</a></li>	
					<input type="hidden" class="bn" name="boardName">
				</c:forEach>
			</ul>
		</div>
	</form>
>>>>>>> refs/remotes/origin/master
</body>
