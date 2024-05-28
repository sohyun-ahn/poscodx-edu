<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 경로 : http://localhost:8080/jstlel/table01.jsp?c=10&r=5
	String row = request.getParameter("r");
	String col = request.getParameter("c");
	
	int nRow = Integer.parseInt(row);
	int nCol = Integer.parseInt(col);
%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1" cellspacing=0>
	<%
		for(int r = 0; r < nRow; r++) {
	%>
		<tr>
		<% 
			for(int c = 0; c < nCol; c++) { 
		%>
		    <td>Cell(<%=r %>, <%=c %>)</td>
	    <% 
	    	}
		%>
		</tr>
		
	<%
		}
	%>
	</table>
</body>
</html>