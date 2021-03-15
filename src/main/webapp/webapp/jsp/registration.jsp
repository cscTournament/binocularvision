<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Registration Form</title>
</head>
<body>
   <form action="Controller" method="post">
		<input type="hidden" name="command" value="savenewuser" /> 
		Enter name:<br />
		<input type="text" name="name" value="" /><br /> 
		Enter surname:<br />
		<input type="text" name="surname" value="" /><br />
	   Enter e'mail (it'll your login):<br />
	   <input type="text" name="email" value="" /><br />
	   Enter password:<br />
	   <input type="text" name="password" value="" /><br />

	  <!--  <input type="text" name="balance" value="" /><br />-->

	   <input type="submit" value="Отправить" /><br />
	</form>
</body>
</html>