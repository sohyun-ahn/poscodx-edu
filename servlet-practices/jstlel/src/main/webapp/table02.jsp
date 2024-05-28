<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:set var="row" value="${param.r}" />
	<c:if test="${empty row }" >
		<c:set var="row" value="3" />
	</c:if>
	
	<c:set var="col" value="${param.c}" />
	<c:if test="${empty col }" >
		<c:set var="row" value="3" />
	</c:if>
	
	
	<table border="1" cellspacing="0">
		<c:forEach begin="${0}" end="${row - 1}" step="1" var="r">
			<tr>
				<c:forEach begin="${0}" end="${col - 1}" step="1" var="c">
					<td> cell(${r}, ${c})</td>
				</c:forEach>
			</tr>
		</c:forEach>
	
	</table>
</body>
</html>