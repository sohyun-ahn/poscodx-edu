<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	pageContext.setAttribute("newline", "\n");
%>    
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>JSTL Test: forEach, fn:length(list)</h1>
	
	<h3> ${ fn:length("list") }</h3>
	
	<c:set var="count" value='${ fn: length("list") }' /> <!-- pageContext.setAttribute("count", 3)%> 과 같은 코드 -->
	<c:forEach items='${list}' var='vo' varStatus='status'>
		<h4>[${count - status.index}] (${status.index} : ${status.count}) ${vo.no} : ${vo.name} </h4> 
	</c:forEach>
	
	<h4>JSTL Test: fn:replace</h4>
	<p>
		${fn:replace(content, newline, "<br/>")}
	</p>
		
</body>
</html>