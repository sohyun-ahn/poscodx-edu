<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% pageContext.setAttribute("newline", "\n"); %>
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<h1>방명록 예제</h1>
		<form action="${pageContext.request.contextPath}/add" method="post">
			<table border=1 width=500>
				<tr>
					<td>이름</td><td><input type="text" name="name"></td>
					<td>비밀번호</td><td><input type="password" name="password"></td>
				</tr>
				<tr>
					<td colspan=4><textarea name="content" id="content" cols=58 rows=5></textarea></td>
				</tr>
				<tr>
					<td colspan=4 align=right><input type="submit" VALUE=" 확인 "></td>
				</tr>
			</table>
		</form>
		<c:set var="count" value='${ fn: length(guestList) }' />
		<c:forEach items='${guestList}' var='vo' varStatus='status'>
			<br/>
			<table border=1 width=500>
				<tr>
					<td>[${count - status.index}]</td>
					<td>${vo.name}</td>
					<td>${vo.regDate}</td>
					<td><a href="${pageContext.request.contextPath}/delete/${vo.no}">삭제</a></td>
				</tr>
				<tr>
					<td colspan=4>
						${fn:replace(fn:replace(fn:replace(vo.content, ">", "&gt;"), "<", "&lt;"), newline, "<br>") }
					</td>
				</tr>
			</table>
		</c:forEach>
	</div>
</body>
</html>