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
			<div id="board">
				<form id="search_form" action="${pageContext.request.contextPath}/board" method="post">
					<input type="text" id="kwd" name="kwd" />
					<input type="submit" value="찾기" />
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>	
						<c:set var="count" value='${boardListLength}' />
						<c:set var="currentPage" value="${(param.p == null)?1:param.p}" />
						<c:set var="startNo" value="${count-5*(currentPage-1)}" />
						<c:set var="paramKWD" value="&kwd=${param.kwd}" />
						<c:set var="search" value="${param.kwd == null || param.kwd=='' ? '' : paramKWD}" />
						<c:forEach items='${boardList}' var='vo' varStatus='status'> 
							<tr>
								<td>${startNo - status.index}</td>
								<td style="text-align:left; padding-left: ${20*vo.depth}px">
									<c:if test="${vo.depth > 0}" >
										<img src="${pageContext.request.contextPath}/assets/images/reply.png">
									</c:if>
									<a href='${pageContext.request.contextPath}/board?a=view&no=${vo.no}&p=${currentPage}${search}'>${fn:replace(fn:replace(fn:replace(vo.title, ">", "&gt;"), "<", "&lt;"), newline, "<br>") }</a>
								</td>
								<td>${vo.userName}</td>
								<td>${vo.hit}</td>
								<td>${vo.regDate}</td>
								<td>
									<c:if test="${vo.userNo == authUser.no }">
										<a href="${pageContext.request.contextPath}/board?a=delete&no=${vo.no}&p=${currentPage}" class="del">삭제</a>
									</c:if>
								</td>
							</tr>
						</c:forEach>
				</table>
				
				<!-- pager 추가 -->
				<c:set var="lastPage" value="${fn : substringBefore((count-1)/5+1, '.')}" />
				<c:set var="startPage" value="${currentPage-(currentPage-1)%5}"/>
				<c:set var="prevStartPage" value="${(startPage==1)?1:(startPage-5)}" />
				<c:set var="nextStartPage" value="${(startPage+5)>lastPage?lastPage:(startPage+5)}" />
				<c:set var="prevPage" value="${(currentPage==1)?1:currentPage-1 }" />
				<c:set var="nextPage" value="${(currentPage+1>lastPage)?currentPage:currentPage+1}" />
				
				<div class="pager">
					<ul>
						<li><a href="${pageContext.request.contextPath}/board?p=${prevStartPage}${search}">◀◀</a></li>
						<li><a href="${pageContext.request.contextPath}/board?p=${prevPage}${search}">◀</a></li>
						<c:forEach var='i' begin='0' end='4'> 
							<c:set var="page" value="${startPage+i}" />
							<c:choose>
								<c:when test='${page > lastPage}'>
									<li>${page}</li>
								</c:when>
								<c:when test='${currentPage == page}'>
									<li class="selected">${page}</li>
								</c:when>
								<c:otherwise>
									<li><a href="${pageContext.request.contextPath}/board?p=${page}${search}">${page}</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<li><a href="${pageContext.request.contextPath}/board?p=${nextPage}${search}">▶</a></li>
						<li><a href="${pageContext.request.contextPath}/board?p=${nextStartPage}${search}">▶▶</a></li>
					</ul>
				</div>					
				<div class="bottom">
					<c:if test='${authUser != null}' >
						<a href='${pageContext.request.contextPath}/board?a=writeform' id="new-book">글쓰기</a>
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
	<script>alert("✅️️ 성공적으로 삭제하였습니다!️")</script>
</c:if>
