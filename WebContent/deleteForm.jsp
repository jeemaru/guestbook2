<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="com.javaex.dao.GuestbookDao"%>
<%@ page import="com.javaex.vo.GuestbookVo"%>

<%
int no = Integer.parseInt(request.getParameter("no"));
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form action="./gcr?" method="get">
	비밀번호 <input type="password" name="password" value=""> <button type="submit">확인</button><br>
	<input type="hidden" name="no" value="<%=no%>">
	<a href="/guestbook2/gcr?action=addList">메인으로 돌아가기</a>
	<input type="hidden" name="action" value="delete">
	</form>
	
</body>
</html>