<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div class="center-content">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<form class="login-form" method="post" action="${pageContext.request.contextPath}/user/auth">
      		<label><spring:message code="user.signin.label.id"/></label> <input type="text" name="id" value="${id}">
      		<label><spring:message code="user.signin.label.password"/></label> <input type="password" name="password">
      		<c:if test='${not empty id}'>
				<p>
					<spring:message code="user.signin.fail"/>
				</p>
			</c:if>
      		<spring:message code="user.signin.button.signin" var="btnText"/>
      		<input type="submit" value='${btnText}' />
		</form>
	</div>
</body>
</html>
