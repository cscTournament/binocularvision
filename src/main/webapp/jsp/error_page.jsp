<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page isErrorPage="true" %>
<%@ include file="../include/uselocale.jsp" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <title><fmt:message key="title.error.page"/></title>

</head>
<body>
<%@ include file="../include/navbar.jsp"%>
<% //TODO: to add tetris for developers? %@page import="by.gourianova.apptrainer.entity.tetris.BVTetris" %> %>

<% //TODO: to add russian translation?%>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <h1>Opps...</h1>
            <a href="../entertainment/Koh/index.html">fractals: type a number(1-10) and try to count sides of Koch snowflake </a>
            <% //TODO: переделать без выхода в интернет %>
           <br> <a href="http://csc.buxar-host.ru/"> Gleaming Star (please, follow instructions)</a>
            <br> <a href="../entertainment/Tanks2/index.html">Tanks (instructions as above)</a>
            <br> <a href="../entertainment/tic_toc/index.html">that's app just for rest: Tic toc</a>


            <h3><c:out value="${message}"/></h3>
            <c:remove var="message" scope="session"/>
        </div>
    </div>
</div>

<%--
<h1>Opps... </h1>


<table width="100%" border="1">
    <tr valign="top">
        <td width="40%"><b>Error:</b></td>
        <td>${pageContext.exception}</td>
    </tr>
    <tr valign="top">
        <td><b>URI:</b></td>
        <td>${pageContext.errorData.requestURI}</td>
    </tr>
    <tr valign="top">
        <td><b>Status code:</b></td>
        <td>${pageContext.errorData.statusCode}</td>
    </tr>
    <tr valign="top">
        <td><b>Stack trace:</b></td>
        <td>
            <c:forEach var="trace"
                       items="${pageContext.exception.stackTrace}">
                <p>${trace}</p>
            </c:forEach>
        </td>
    </tr>
</table>--%>


<%@ include file="../include/footer.jsp" %>
</body>
</html>
