<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");

	String email = request.getParameter("email");
	String password = request.getParameter("password");
	String birthYear = request.getParameter("birthYear");
	String gender = request.getParameter("gender");
	String profile = request.getParameter("profile");
	String[] hobbies = request.getParameterValues("hobby");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%=email %>
	<br/>
	
	<%=password %>
	<br/>
	
	<%=birthYear %>
	<br/>

	<%=gender %>
	<br/>

	<p>
		<%=profile %>
	</p>
	
	<!-- 반복문 -->
	<!-- 방법1 -->

	<%
		for(String hobby: hobbies){
	%>
		<span><%=hobby %></span>
	<%
		}
	%>
	
	<!-- 방법2 -->
	<forEach var="hobby" >
		<span>${hobby}</span>
	</forEach>
	

</body>
</html>