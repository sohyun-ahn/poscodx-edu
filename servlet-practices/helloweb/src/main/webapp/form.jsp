<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/helloweb/join.jsp" method="post">
		<label>이메일: </label>
		<input type="email" name="email" value="" />
		<br/>
		<br/>
		
		<label>비밀번호: </label>
		<input type="password" name="password" value="" />
		<br/>
		<br/>
		
		<label>생년: </label>
		<select name="birthYear">
			<option value="2000">2000년</option>
			<option value="1999">1999년</option>
			<option value="1998">1998년</option>
		</select>
		<br/>
		<br/>
		
		<!-- name을 갖게 해주어서 한개만 선택되게 -->
		<label>성별: </label>
		여자 <input type="radio" name="gender" value="female" checked="checked" />
		남자 <input type="radio" name="gender" value="male" />
		<br/>
		<br/>
		
		<label>자기소개:</label>
		<br/>
		<br/>
		<textarea name="profile"></textarea>
		<br/>
		<br/>

		<label>취미:</label>
		<br/>
		<br/>
		코딩: <input type="checkbox" name="hobby" value="coding" />
		술먹기: <input type="checkbox" name="hobby" value="drinking" />
		요리: <input type="checkbox" name="hobby" value="cooking" />
		수영: <input type="checkbox" name="hobby" value="swimming" />
		<br/>
		<br/>

		
		<input type="submit" value="회원가입" />
	</form>
</body>
</html>