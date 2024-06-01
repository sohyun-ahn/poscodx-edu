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
<link href="${pageContext.request.contextPath}/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="board" class="board-form">
				<table class="tbl-ex">
					<tr>
						<th colspan="2">글보기</th>
					</tr>
					<tr>
						<td class="label">제목</td>
						<td>${fn:replace(fn:replace(fn:replace(boardVo.title, ">", "&gt;"), "<", "&lt;"), newline, "<br>") }</td>
					</tr>
					<tr>
						<td class="label">내용</td>
						<td>
							<div class="view-content">${fn:replace(fn:replace(fn:replace(boardVo.content, ">", "&gt;"), "<", "&lt;"), newline, "<br>") }</div>
						</td>
					</tr>
				</table>
				<div class="bottom">
					<c:set var="paramKWD" value="&kwd=${param.kwd}" />
					<c:set var="search" value="${param.kwd == null || param.kwd=='' ? '' : paramKWD}" />
					<a href='${pageContext.request.contextPath}/board?p=${param.p}${search}'>글목록</a>
					<c:if test='${authUser != null}' >
						<c:if test='${boardVo.userNo == authUser.no}' >
							<a href='${pageContext.request.contextPath}/board?a=modifyform&no=${boardVo.no}&p=${param.p}${search}'>글수정</a>
						</c:if>
						<a href='${pageContext.request.contextPath}/board?a=writeform&no=${boardVo.no}&p=${param.p}${search}'>답글달기</a>
					</c:if>
				</div>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>
<c:if test='${param.result == "success"}'>
	<script>alert("✅️️ 성공적으로 수정하였습니다.️")</script>
</c:if>
