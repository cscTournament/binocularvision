<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="by.gourianova.binocularvision.util.tetris.BVTetris" %>
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
<% //TODO: to add tetris for developers?  %>
<div class="container">
    <div class="row">
        <div class="col-md-12 col-md-offset-3">

            <div class="form-group">
                <label  class="col-sm-2 control-label"><fmt:message key="error.rest"/></label>
                <br> <a href="http://csc.buxar-host.ru/"> Gleaming Star </a>
                <br> <a href= "/tetris"> Tetris (only for developers!) </a>

            </div>
            <div class="form-group">
                <br/>
            <H1>Oops...</H1><br>
            </div>
            <div class="form-group">

                <label  class="col-sm-2 control-label"><fmt:message key="error.surggestion"/></label>
            </div>
            <h2><c:out value="${message}"/></h2>
            <c:remove var="message" scope="session"/>
        </div>
    </div>


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
    </table>
    <!-- TODO: better picture-->
       <!-- <center><img src="../images/error.jpg" width="70%" height="50%"></center>-->


<%@ include file="../include/footer.jsp" %>
</body>
</html>
