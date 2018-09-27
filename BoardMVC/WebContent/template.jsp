<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page errorPage="error.jsp"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/common.css?ver=1">
<link rel="stylesheet" type="text/css" href="css/boardList.css?ver=3">
</head>

<body>
	<div class="container">
	<h1><jsp:include page="top.jsp"/></h1>
	<jsp:include page="menu.jsp"/>
		
<jsp:include page= "${content}.jsp"/>
		
	</div>
</body>
</html>