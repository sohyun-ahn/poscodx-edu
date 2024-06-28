<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.9.0.js"></script>
<script>
$(function(){
	$("#languages a").click(function(event){
		event.preventDefault();
		console.log($(this).data("lang"));
		
		document.cookie =
			"lang="    + $(this).data("lang") + ";" +                 // name=value  
			"path="    + "${pageContext.request.contextPath}" + ";" + // path
			"max-age=" + (30*24*60*60)                                // max-age  
	
		// reload
		location.reload();
	});
});
</script>
<div id="header">
	<h1>${blogVo.title}</h1>
	<div id="languages">
		<c:choose>
			<c:when test='${language == "en"}'>
				<a href="" data-lang="ko">KR</a><a href="" class="active" data-lang="en">EN</a>
			</c:when>
			<c:otherwise>
				<a href="" data-lang="ko" class="active">KR</a><a href="" data-lang="en">EN</a>
			</c:otherwise>
		</c:choose>
	</div>	
	<ul>
		<sec:authorize access="!isAuthenticated()">
			<li><a href="${pageContext.request.contextPath}/user/login"><spring:message code="header.gnb.login"/></a></li>				
		</sec:authorize>
		<sec:authentication property="principal" var="principal"/>
		<sec:authorize access="isAuthenticated()">
			<li><a href="${pageContext.request.contextPath}/user/logout"><spring:message code="header.gnb.logout"/></a></li>
			<c:if test="${principal.username == blogVo.id }">
				<li><a href="${pageContext.request.contextPath}/${principal.username}/admin/basic"><spring:message code="header.gnb.settings"/></a></li>
			</c:if>
		</sec:authorize>
		<li><a href="${pageContext.request.contextPath}/${blogVo.id}" id="home"><spring:message code="header.gnb.main"/></a></li>
	</ul>
</div>