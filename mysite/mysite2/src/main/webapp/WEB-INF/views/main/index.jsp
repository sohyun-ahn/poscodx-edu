<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="<%= request.getContextPath() %>/assets/css/main.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/includes/header.jsp" />
		<div id="wrapper">
			<div id="content">
				<div id="site-introduction">
					<img id="profile" src="<%= request.getContextPath() %>/assets/images/pochaco.png" width='400px'>
					<h2>안녕하세요. 안소현의  <strong style="color:#009900">MySite</strong>에 오신 것을 환영합니다.</h2>
					<p>
						이 사이트는  웹 프로그래밍 실습과제 예제 사이트입니다.<br>
						메뉴는 수업 배운 거 있는 거 없는 거 다 합쳐서
						만들어 놓은 사이트 입니다.
						<br>
						<strong><u>멋지죠</u>?</strong>
						<br>
						<br>
						<a href="<%= request.getContextPath() %>/guestbook">방명록</a>에 글 남기기<br>
					</p>
				</div>
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/includes/navigation.jsp" />
		<jsp:include page="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>