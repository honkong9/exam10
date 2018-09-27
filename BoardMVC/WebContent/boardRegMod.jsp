<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="java.util.ArrayList"%>
<%@ page import="kr.itedu.test1.*"%>
	<%int bid=Integer.parseInt(request.getParameter("bid")); %>
	<%
	BoardVO vo = (BoardVO)request.getAttribute("boardRegMod");
%>
	<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="boardRegMod" method="post">
제목<br>
<input type="text" name=box1 value="<%if(bid!=0){%><%=vo.getBtitle()%><%} %>"><br>

내용<br>
		
<textarea rows="3" cols="30" name=box2><%if(bid!=0){%><%=vo.getBcontent()%><%} %></textarea><br>

<input type="submit" value="등록">
<input type="hidden" name="hidden" value="<%=request.getParameter("bid") %>">
</form>


</body>
</html>