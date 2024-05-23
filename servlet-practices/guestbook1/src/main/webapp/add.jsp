<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="guestbook.dao.GuestbookDao"%>
<%@ page import="guestbook.vo.GuestbookVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	request.setCharacterEncoding("utf-8");
	
	String name = request.getParameter("name");
	String password = request.getParameter("password");
	String content = request.getParameter("content");
	
	
	GuestbookVo vo = new GuestbookVo();
	vo.setName(name);
	vo.setPassword(password);
	vo.setRegDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	vo.setContent(content);
	
	new GuestbookDao().insert(vo);
	
	response.sendRedirect("/guestbook1");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>성공적으로 이메일이 등록되었습니다.</h1>
</body>
</html>