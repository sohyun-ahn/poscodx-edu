<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<h1 class="logo">JBlog</h1>
<ul class="menu">
	<sec:authorize access="isAnonymous()">
		<li><a href="${pageContext.request.contextPath}/user/login"><spring:message code="menu.li.login"/></a></li>
		<li><a href="${pageContext.request.contextPath}/user/join"><spring:message code="menu.li.join"/></a></li>
	</sec:authorize>
	<sec:authentication property="principal" var="principal"/>
	<sec:authorize access="isAuthenticated()">
		<li><a href="${pageContext.request.contextPath}/user/logout"><spring:message code="menu.li.logout"/></a></li>
		<li><a href="${pageContext.request.contextPath}/${principal.username}"><spring:message code="menu.li.main"/></a></li>
	</sec:authorize>
</ul>