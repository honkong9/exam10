<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix= 'c' uri = "http://java.sun.com/jsp/jstl/core" %>
<body>

		<table>
			<tr>
				<td id="one">번호</td>
				<td id="two">제목</td>
				<td id="three">등록일시</td>
			</tr>
	<c:forEach var="i" items="${data}">		
			<tr>

				<td>${i.getBid()}</td>
				<td><a href="boardDetail.bo?btype=${param.btype}&bid=${i.getBid()}">${i.getBtitle() }</a></td>
				<td>${i.getBcontent() }</td>
			</tr></c:forEach>
		</table>
		<form action="boardRegMod" method="get">
		<input type="hidden" name="bid" value="0">
		<input type="submit" value="글쓰기" >
		</form>

</body>
</html>