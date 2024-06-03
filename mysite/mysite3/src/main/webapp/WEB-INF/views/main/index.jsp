<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/assets/css/main.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="wrapper">
			<div id="content">
				<div id="site-introduction">
					<img id="profile" src="${pageContext.request.contextPath}/assets/images/pochaco.png" width='420px'>
					<h2>안녕하세요. 안소현의  <strong style="color:#009900">MySite</strong>에 오신 것을 환영합니다.</h2>
					<p>
						이 사이트는  웹 프로그래밍 실습과제 예제 사이트입니다.<br/>
						메뉴는 수업 배운 거 있는 거 없는 거 다 합쳐서
						만들어 놓은 사이트 입니다.
						<br/>
						<strong><u>멋지죠</u>?</strong>
						<br/>
						<br/>
						방문한 여러분, 지금 제 사이트가 어떻게든 굴러는 가거든요?? <br/>
						혹시 안굴러가면 방명록에 (착.하.게.) 말해주세요...☆⛤
						<br/>
						<br/>
						★✩✦ <a href="${pageContext.request.contextPath}/guestbook">방명록</a>에 글 남기기 ✫✬☆<br/>
					</p>
				</div>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>