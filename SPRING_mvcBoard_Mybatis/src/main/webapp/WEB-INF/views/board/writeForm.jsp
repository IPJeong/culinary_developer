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
	<h2> 글쓰기 </h2>
	<form action="writePro" method="post" name="writeform" onsubmit="return writeCheck();">
		<input type="hidden" name="num" value="${num}">
		<input type="hidden" name="ref" value="${ref}">
		<input type="hidden" name="ref_step" value="${ref_step}">
		<input type="hidden" name="ref_level" value="${ref_level}">
		
		<table>
			<tr>
				<th> 작성자 </th>
				<td>
					<input class="input" type="text" name="writer" maxlength="20">
				</td>
			</tr>
			<tr>
				<th> 비밀번호 </th>
				<td>
					<input class="input" type="password" name="passwd" maxlength="10">
				</td>
			</tr>
			
			<tr>
				<th> 글제목 </th>
				<td>
					<input class="input" type="text" name="subject" maxlength="50" style="width:270px">
				</td>
			</tr>
			
			<tr>
				<th> 글내용 </th>
				<td>
					<textarea class="input" rows="10" cols="38" name="content" ></textarea>
				</td>
			</tr>
			<tr>
				<th colspan="2">
					<input class="inputButton" type="submit" value="작성">
					<input class="inputButton" type="reset" value="취소">
					<input class="inputButton" type="button" value="목록으로" onclick="window.location='list'">
				</th>
			</tr>
		</table>
	</form>
</body>
</html>