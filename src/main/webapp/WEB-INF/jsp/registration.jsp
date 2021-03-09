<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
   <form action="/Controller" method="post">
		<input type="hidden" name="command" value="savenewuser" /> 
		Enter name:<br />
		<input type="text" name="name" value="" /><br /> 
		Enter surname:<br />
		<input type="text" name="surname" value="" /><br /> 
		
		<input type="submit" value="Отправить" /><br />
	</form>
</body>
</html>