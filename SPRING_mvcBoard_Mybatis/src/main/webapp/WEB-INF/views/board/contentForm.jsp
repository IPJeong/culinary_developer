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
	<h2> 상세 페이지 </h2>
	
	<table>
		<tr>
			<th style="width:150px"> 글번호 </th>
			<td style="width:150px" align="center"> ${number} </td>	<!-- db 글번호가 아닌 현재 넘버링한 글번호 -->
			
			<th style="width:150px"> 조회수 </th>
			<td style="width:150px" align="center"> ${dto.readCnt}</td>
		</tr>
		<tr>
			<th style="width:150px"> 작성자 </th>
			<td style="width:150px"> ${dto.writer} </td>
			
			<th style="width:150px"> 작성일 </th>
			<td style="width:150px"> 
				<fmt:formatDate type="both" pattern="yyyy-MM-dd HH:mm" value="${dto.reg_date}" /> 
			</td>
		</tr>
		
		<tr>
			<th> 글제목 </th>
			<td colspan="3"> ${dto.subject} </td>
		</tr>
		<tr>
			<th> 글내용 </th>
			<td colspan="3"> ${dto.content} </td>
		</tr>
		<tr>
			<th colspan="4">
				<input class="inputButton" type="button" value="글수정" onclick="window.location='modifyForm?num=${dto.num}&pageNum=${pageNum}'">
				<input class="inputButton" type="button" value="글삭제" onclick="window.location='deleteForm?num=${dto.num}&pageNum=${pageNum}'">
				<input class="inputButton" type="button" value="답글쓰기" onclick="window.location='writeForm?num=${dto.num}&ref=${dto.ref}&ref_step=${dto.ref_step}&ref_level=${dto.ref_level}'">
				<input class="inputButton" type="button" value="목록보기" onclick="window.location='list.do?pageNum=${pageNum}'">
<!-- 				<input class="inputButton" type="button" value="목록보기" onclick="history.back();">	    -->
			</th>
		</tr>			
	</table>
</body>
</html>