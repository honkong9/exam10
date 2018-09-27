<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/common.css">
<link rel="stylesheet" type="text/css" href="css/detail.css">
</head>
<body>
	<a href="boardlist">메인으로 가기</a>
	
	<h1>${data.getBtitle()}</h1>
	<table id=okdk>
		<tr>
			<td>내용</td>
		</tr>
		<tr>
			<td>${data.getBcontent()}</td>
		</tr>
	</table>
	
	<a href="boardRegMod?bid="><input type="submit" id="edit" value="수정"></a>
	
</body>
</html>