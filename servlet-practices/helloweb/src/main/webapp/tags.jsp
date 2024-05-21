<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>안녕하세여</h1>
	<h2>안녕하세여</h2>
	<h3>안녕하세여</h3>
	<h4>안녕하세여</h4>
	<h5>안녕하세여</h5>
	<h6>안녕하세여</h6>
	
	<table border="1" cellspacing="10">
		<tr>
			<th>글번호</th>
			<th>글제목</th>
			<th>작성자</th>
		</tr>
		
		<tr>
			<td>1</td>
			<td>안녕</td>
			<td>둘리</td>
		</tr>
		
		<tr>
			<td>2</td>
			<td>메롱</td>
			<td>마이콜</td>
		</tr>
	</table>
	
	<br/>
	
	<!-- 현재경로(.) : /helloweb -->
	<img src="http://localhost:8080/helloweb/images/studygom.png" width="300px">
	<img src="/helloweb/images/studygom.png" width="300px">
	<img src="./images/studygom.png" width="300px">
	
	<p>
		문장입니다. <br/>
		문장입니다. <br/>
	</p>
	
	<a href="/helloweb/hello?id=ash">hello로 가기</a>
	<br/>
	<a href="/helloweb/form.jsp">form으로 가기</a>
	
	
	
	
	
</body>
</html>