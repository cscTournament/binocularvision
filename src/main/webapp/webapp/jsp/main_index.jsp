<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="java.util.List"%>
<%@ page import="by.gourianova.binocularvision.controller.AppsListController" %>
<%@ page import="by.gourianova.binocularvision.model.AppItem" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html
		xmlns="http://www.w3.org/1999/xhtml"
		xmlns:ui="http://java.sun.com/jsf/facelets"
>
<head>
<meta charset="utf-8">
<title>login</title>
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

	<form action="Controller" method="post">
		<input type="hidden" name="command" value="login" /> Enter
		login:<br /> <input type="text" name="login" value="" alt="email" /><br />
		Enter password:<br /> <input type="password" name="password" value="" /><br />

		<input type="submit" value="Отправить" /><br />
	</form>

	<br />

	<a href="Controller?command=registration">Registration</a>

	<br />
	<br />
	<br />
	<br />
	<br />
	<center>&nbsp;<h3> <hr  width=30%  color="#20B2AA" ></h3></center>
	<center><h3> http addresses of your apps:</h3></center>
	<center>&nbsp;<h3> <hr  width=30%  color="#20B2AA" ></h3></center>
	<center>
    <table border="2">
	    <c:forEach var="a" items="#{appcontroller.appItems}">
		<tr>
			<td>
			   <font size="18" color="blue">
			   <c:out value="${a.category}" />
			</font></td>
		</tr>
		</c:forEach>
	</table>
	</center>
</body>
</html>
