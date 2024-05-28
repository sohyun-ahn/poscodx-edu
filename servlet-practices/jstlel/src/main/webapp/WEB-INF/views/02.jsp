<%@ page import="jstlel.UserVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// page context에 담기위해 자바코드를 써야함
	// jstl에는 pagecontext에 담는 코드가 있긴하다.
	UserVo vo4 = new UserVo();
	vo4.setNo(4L);
	vo4.setName("둘리4");
	pageContext.setAttribute("vo", vo4);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h4>scope(객체의 존속 범위)</h4>
	
	===== page scope ===== <br/>
	${vo.no } <br/>
	${vo.name} <br/>
	
	===== request scope ===== <br/>
	${requestScope.vo.no } <br/> <!-- 3출력 -->
	${requestScope.vo.name} <br/>
	
	===== session scope ===== <br/>
	${sessionScope.vo.no } <br/> <!-- 2출력 -->
	${sessionScope.vo.name} <br/>
	
	===== application scope ===== <br/>
	${applicationScope.vo.no } <br/> <!-- 1출력 -->
	${applicationScope.vo.name} <br/>
	
	
</body>
</html>