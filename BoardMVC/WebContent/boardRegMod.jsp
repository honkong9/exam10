<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="java.util.ArrayList"%>

	<%int bid=Integer.parseInt(request.getParameter("bid")); %>

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
<input type="text" name=box1 value=""><br>

내용<br>
		
<textarea rows="3" cols="30" name=box2></textarea><br>

<input type="submit" value="등록">
<input type="hidden" name="hidden" value="<%=request.getParameter("bid") %>">
</form>


</body>
</html>