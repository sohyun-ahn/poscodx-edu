<%@page import="java.util.List"%>
<%@page import="guestbook.dao.GuestbookDao"%>
<%@page import="guestbook.vo.GuestbookVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	request.setCharacterEncoding("utf-8");
	
	Long no = Long.parseLong(request.getParameter("no"));
	String password = request.getParameter("password");
	
	GuestbookDao dao = new GuestbookDao();
	if(dao.deleteByNoAndPassword(no, password)){		
		response.sendRedirect("/guestbook1") ; 
	} else {
		%>
		<script>			
		alert('비밀번호가 틀렸습니다.');
		</script>
		<%
	}
			
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>