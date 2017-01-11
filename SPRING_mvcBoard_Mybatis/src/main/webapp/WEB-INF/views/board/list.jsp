<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file= "setting.jsp" %>        

<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
</head>
<body>
<script type="text/javascript">
	$(function() {
	    var item = <%=request.getParameter("item")%>;
	    if(item != null) {
	    	$("#item").val(item);	
	    }
	});
</script>
	<h2 align="center"> 게시판</h2>
	<table style="width:1000px; align:center">
		<tr>
			<th colspan="6" align="right" style="height:25px">
				글목록 (글갯수 : ${cnt} ) &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="writeForm.do"> 글쓰기 </a>
			</th>
		</tr>
		<tr>
			<th style="width:5%"> 글번호 </th>
			<th style="width:35%"> 글제목 </th>
			<th style="width:10%"> 작성자 </th>
			<th style="width:15%"> 작성일 </th>
			<th style="width:5%"> 조회수 </th>
			<th style="width=:10%"> IP </th>
		</tr>	
		
		<!-- 게시글이 있으면 -->
		<c:if test="${cnt > 0}">
			<c:forEach var="dto" items="${dtos}" >
				<tr>
					<td align = "center">
						${number}
						<c:set var="number" value="${number -1}" /> 
					</td>
					
					<td>
						<c:if test="${dto.ref_level > 1}"> 
							<c:set var="wid" value="${dto.ref_level -1} * 10" />
							<img src="${project}images/level.gif" border="0" width="${wid}" height="15">
						</c:if>	
						
						<!-- 들여쓰기 > 0 : 답변글 -->
						<c:if test="${dto.ref_level > 0}">
							<img src="${project}images/re.gif" border="0" width="20" height="15">
						</c:if>	
						
						<a href="contentForm?num=${dto.num}&pageNum=${pageNum}&number=${number+1}">
							${dto.subject}
						</a>
						
						<c:if test = "${dto.readCnt > 10}">
							<img src = "${project}images/hot.gif" border="0" width="20" height="15">
						</c:if>
					</td>
					
					<td align="center"> 
						${dto.writer}
					</td>
					
					<td align="center">
						<fmt:formatDate type="both" pattern="yyyy-MM-dd HH:mm" value="${dto.reg_date}" />
					</td>
					
					<td align="center">
						${dto.readCnt}
					</td>
					
					<td align="center">
						${dto.ip}
					</td>
				</tr>
			</c:forEach>
			
			<form action="list" name="searchform" onsubmit="return keywordCheck();">
					<select class="input" name="item" id="item">
						<option value="subject">제목</option>
						<option value="content">내용</option>
						<option value="writer">작성자</option>
					<option value="sub+con">제목+내용</option>
				</select>	
				
				<input class="input" type="text" name="keyword" value="${keyword}">
				<input class="inputButton" type="submit" value="검색">
			</form>	
		</c:if>
		
		<!-- 게시글이 없으면 -->
		<c:if test="${cnt == 0}">
			<c:if test="${item != null}">
				<form action="list" name="searchform" onsubmit="return keywordCheck();">
						<select class="input" name="item" id="item">
							<option value="subject">제목</option>
							<option value="content">내용</option>
							<option value="writer">작성자</option>
						<option value="sub+con">제목+내용</option>
					</select>	
					
					<input class="input" type="text" name="keyword" value="${keyword}">
					<input class="inputButton" type="submit" value="검색">
				</form>	
				<tr>
					<td colspan="6" align="center" style="height:40px">
						검색결과를 찾지 못했습니다. 다시 검색해 주세요.
					</td>
				</tr>
			</c:if>
			<c:if test="${item == null}">
				<tr>
					<td colspan="6" align="center" style="height:40px">
						게시판에 글이 없습니다. 글을 작성해 주세요!
					</td>
				</tr>
			</c:if>
		
		</c:if>
	</table>
	
	<!-- <div align="center"> -->
	<table style="width:1000px; align:center">
		<tr>
			<th>
			<c:if test="${cnt > 0}">
				<c:if test="${keyword == null}">
					<!-- 처음[◀◀] 이전[◀]-->
					<c:if test="${startPage > pageBlock}">
					 	<a href="list">[◀◀]</a>
						<a href="list?pageNum=${startPage - pageBlock}">[◀] </a>
					</c:if>
					
					<c:forEach var="i" begin="${startPage}" end="${endPage}">
						<c:if test="${i == currentPage }">
							<span><b>[${i}]</b></span>
						</c:if>
						
						<c:if test="${i != currentPage}">
							<a href="list?pageNum=${i}">[${i}]</a>
						</c:if>
					</c:forEach>
					
					<!-- 다음[▶] 끝[▶▶]-->
					<c:if test="${pageCount > endPage}">
						<a href="list?pageNum=${startPage + pageBlock}">[▶]</a>
						<a href="list?pageNum=${pageCount}">[▶▶]</a>
					</c:if>
				</c:if>
				
				<c:if test="${keyword != null}">
					<!-- 처음[◀◀] 이전[◀]-->
					<c:if test="${startPage > pageBlock}">
					 	<a href="list?item=${item}&keyword=${keyword}">[◀◀]</a>
						<a href="list?pageNum=${startPage - pageBlock}&item=${item}&keyword=${keyword}">[◀] </a>
					</c:if>
					
					<c:forEach var="i" begin="${startPage}" end="${endPage}">
						<c:if test="${i == currentPage }">
							<span><b>[${i}]</b></span>
						</c:if>
						
						<c:if test="${i != currentPage}">
							<a href="list?pageNum=${i}&item=${item}&keyword=${keyword}">[${i}]</a>
						</c:if>
					</c:forEach>
					
					<!-- 다음[▶] 끝[▶▶]-->
					<c:if test="${pageCount > endPage}">
						<a href="list?pageNum=${startPage + pageBlock}&item=${item}&keyword=${keyword}">[▶]</a>
						<a href="list?pageNum=${pageCount}&item=${item}&keyword=${keyword}">[▶▶]</a>
					</c:if>
				</c:if>
			</c:if>
			</th>
		</tr>
	</table>
	<!-- </div> -->
</body>
</html>