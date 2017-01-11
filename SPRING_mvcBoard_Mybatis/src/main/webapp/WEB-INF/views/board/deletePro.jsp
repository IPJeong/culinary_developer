<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="setting.jsp" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2 align="center"> 글 삭제</h2>
	
	<!-- 비밀번호가 다른 경우 -->
	<c:if test="${selectCnt == 0}">
		<script type="text/javascript">
			errorAlert(passwdError);
			history.back();
		</script>
	</c:if>
	
	<!-- 비밀번호가 일치하는 경우 -->
	<c:if test="${selectCnt != 0}">
	
		<!-- 답글이 있는 경우 삭제 안함 -->
		<c:if test="${deleteCnt == -1}">
			<script type="text/javascript">
				alert(replyError);
				window.location='list?pageNum=${pageNum}';
			</script>
		</c:if>
		
		<!-- 답글이 없는 경우 삭제 실패 -->
		<c:if test="${deleteCnt == 0}">
			<script type="text/javascript">
				errorAlert(deleteError);
			</script>
		</c:if>
		
		<!-- 답글이 없는 경우 삭제 성공 -->
		<c:if test="${deleteCnt == 1}">
			<script type="text/javascript">
				alert("게시글이 삭제되었습니다.");
				window.location='list?pageNum=${pageNum}';
			</script>
		</c:if>
	</c:if>
</body>
</html>