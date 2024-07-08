<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Text Page</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script>
$(function(){
	$('button').click(function(){
		var vo = {
				name: "둘리",
				password: "1234",
				content: '호이~~~~~'
		}
		
		$.ajax({
			url:'/ch08/api/post2',
			async: true,
			type: 'post',
			dataType: 'json',
			contentType: 'application/json',
			data: JSON.stringify(vo),
			success: function(response){
				// console.log(response);
				if(response.result !== 'success'){
					console.error(response.message);
					return;
				}
				
				var vo = response.data;
				
				var html = "";
				html += ("<h3>" + vo.no + "</h3>");
				html += ("<h4>" + vo.name + "</h4>");
				html += ("<h5>" + vo.content + "</h5>");
				
				$("#data").html(html);
				
			},
			error: function(xhr, status, err){
				console.error(status, err);
			}
		});
	})
});
</script>
</head>
<body>
	<h1>Ajax Test : JSON Format Data : $.ajax() 함수 사용하기</h1>
	<button>데이터 보내기(POST, PUT, DELETE)</button>
	<div id="data"></div>
</body>
</html>