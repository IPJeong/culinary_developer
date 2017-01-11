<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="setting.jsp" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body onload="modifyFocus();">
	<h2 align="center"> 글 수정 </h2>
	<c:if test="${selectCnt != 1 }">
		<script type="text/javascript">
			errorAlert(passwdError);
		</script>
	</c:if>
	
	<c:if test="${selectCnt == 1}">
		<form action="modifyPro" method="post" name="modifyform" onsubmit="return modifyCheck();">
			<input type="hidden" name="num" value="${num}">
			<input type="hidden" name="pageNum" value="${pageNum}">
			
			<table align="center">
				<tr>
					<th colspan="2"> 수정할 정보를 입력하세요. </th>
				</tr>
				<tr>
					<th> 작성자 </th>
					<td> ${dto.writer} </td>
				</tr>
				<tr>
					<th> 글제목 </th>
					<td>
						<input class="input" type="text" name="subject" maxlength="50" value="${dto.subject}" style="width:250px">
					</td>
				</tr>
				<tr>
					<th> 글내용 </th>
					<td>
						<textarea class="input" rows="10" cols="40" name="content">${dto.content}</textarea>
					</td>
				</tr>
				<tr>
					<th> 비밀번호 </th>
					<td>
						<input class="input" type="password" name="passwd" maxlength="10" value="${dto.passwd}">
					</td>
				</tr>
				<tr>
					<th colspan="2">
						<input class="inputButton" type="submit" value="수정">
						<input class="inputButton" type="reset" value="수정취소">
						<input class="inputButton" type="button" value="목록보기" onclick="window.location='list.do?pageNum=${pageNum}'">
					</th>				
				</tr>
			</table>
		</form>
	</c:if>
	
</body>
</html>