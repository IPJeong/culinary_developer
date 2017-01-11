<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="setting.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body onload="passwdFocus();">

	<form action="deletePro" method="post" name="passwdform" onsubmit="return passwdCheck()">
		<input type="hidden" name="num" value="${num}">
		<input type="hidden" name="pageNum" value="${pageNum}">
	
		<table align="center">
			<tr>
				<th colspan="2"> 비밀번호를 한번 더 입력하세요. </th>
			</tr>
			
			<tr>
				<th> 비밀번호 </th>
				<td>
					<input class="input" type="password" name="passwd" maxlength="10">
				</td>
			</tr>
			<tr>
				<th colspan="2">
					<input class="inputButton" type="submit" value="글삭제">
					<input class="inputButton" type="button" value="삭제취소" onclick="window.history.back()">
				</th>
			</tr>
		</table>
	
	
	</form>
</body>
</html>