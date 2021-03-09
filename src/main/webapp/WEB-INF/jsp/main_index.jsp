<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="java.util.List, by.gourianova.binocularvision.bean.News"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String message = (String) request.getParameter("message");

	if (message != null) {
	%>
	<font color="red">

		<p><c:out value="${message}" /></p>
		<%

			out.write(message);
 }
 %>
	</font>

	<br />

	<form action="Controller" method="post">
		<input type="hidden" name="command" value="login" /> Enter
		login:<br /> <input type="text" name="login" value="" alt="email" /><br />
		Enter password:<br /> <input type="password" name="password" value="" /><br />

		<input type="submit" value="Отправить" /><br />
	</form>

	<br />

	<a href="Controller?command=registration">Registration</a>


    <table border="2">
	    <c:forEach var="n" items="${requestScope.news}">
		<tr>
			<td>
			   <font size="18" color="blue">
			   <c:out value="${n.title}" />
			</font></td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>
