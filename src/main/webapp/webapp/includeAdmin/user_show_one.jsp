<%--@elvariable id="userOne" type="User"--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include/uselocale.jsp" %>
<html>
<head>
    <title><fmt:message key="title.main.page"/></title>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: url(../images/fon1.jpg);
        }
    </style>
</head>
<body>
<%@ include file="../include/navbar.jsp" %>
<div class="container-fluid">
    <div class="col-md-6 col-md-offset-6">
        <c:if test="${not empty emptyUser}">
        <div class="text-center">
            <h4><fmt:message key="message.empty.user"/></h4>
        </div>
        </c:if>
        <c:if test="${not empty userOne}">
        <table class="table table-condensed table-bordered">
            <tr>
                <td align="center" style="border-color:  #20B2AA"><fmt:message key="table.user.id"/></td>
                <td align="center" style="border-color: #dae5ff"><fmt:message key="table.user.first.name"/></td>
                <td align="center" style="border-color: #dae5ff"><fmt:message key="table.user.last.name"/></td>
                <td align="center" style="border-color: #dae5ff"><fmt:message key="table.user.login"/></td>
                <td align="center" style="border-color: #dae5ff"><fmt:message key="table.user.password"/></td>
                <td align="center" style="border-color: #dae5ff"><fmt:message key="table.user.balance"/></td>
                <td align="center" style="border-color: #dae5ff"><fmt:message key="table.user.role"/></td>
            </tr>
            <tr>
                <td align="center" style="border-color: #dae5ff">${userOne.id}</td>
                <td align="center" style="border-color: #dae5ff">${userOne.firstName}</td>
                <td align="center" style="border-color: #dae5ff">${userOne.lastName}</td>
                <td align="center" style="border-color: #dae5ff">${userOne.login}</td>
                <td align="center" style="border-color: #dae5ff">${userOne.password}</td>
                <td align="center" style="border-color: #dae5ff">${userOne.balance}</td>
                <td align="center" style="border-color: #dae5ff">${userOne.roleId}</td>
            </tr>
        </table>
        </c:if>
        <a href="/Controller?command=showadminpage" class="btn btn-default"><fmt:message key="continue"/></a>
    </div>
    <%@ include file="../include/footer_admin.jsp" %>
</body>
</html>

