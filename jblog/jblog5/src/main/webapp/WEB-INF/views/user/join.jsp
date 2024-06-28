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
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css" type="text/css">
<script src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.9.0.js"></script>
<script>
$(function() {
	$("#btn-checkId").click(function() {
		var id = $("#blog-id").val();
		if(id == '') {
			return;
		}
		
		$.ajax({
			url: "${pageContext.request.contextPath}/user/api/checkid?id=" + id,
			type: "get",
			dataType: "json",
			error: function(xhr, status, err){
				console.error(err);			
			},
			success: function(response) {
				if(response.result == "fail") {
					console.error(response.message);
					return;
				}
				
				if(response.data) {
					alert("존재하는 아이디입니다. 다른 아이디를 사용해 주세요.");
					$("#blog-id").val("");
					$("#blog-id").focus();
					return;
				}
				
				// 사용할 수 있는 아이디
				$("#btn-checkId").hide();
				$("#img-checkId").show();
			}
		});
	})
});


</script>

</head>
<body>
	<div class="center-content">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<form class="join-form" id="join-form" method="post" action="${pageContext.request.contextPath}/user/join">
			<label class="block-label" for="name"><spring:message code="user.join.label.name"/></label>
			<input id="name"name="name" type="text" value="${name}" />
			
			<label class="block-label" for="blog-id"><spring:message code="user.join.label.id"/></label>
			<input id="blog-id" name="id" type="text" /> 
			<spring:message code="user.join.button.id.check" var="btnCheckText"/>
			<input id="btn-checkId" type="button" value="${btnCheckText}" />
			<img id="img-checkId" style="display: none;" src="${pageContext.request.contextPath}/assets/images/check.png">

			<label class="block-label" for="password"><spring:message code="user.join.label.password"/></label>
			<input id="password" name="password" type="password" />

			<fieldset>
				<legend><spring:message code="user.join.label.terms"/></legend>
				<input id="agree-prov" type="checkbox" name="agreeProv" value="y" />
				<label class="l-float"><spring:message code="user.join.label.terms.message"/></label>
			</fieldset>
			
			<spring:message code="user.join.button.signup" var="btnText"/>
			<input type="submit" value="${btnText}" />

		</form>
	</div>
</body>
</html>
