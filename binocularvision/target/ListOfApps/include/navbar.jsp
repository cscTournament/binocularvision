<%--@elvariable id="user" type="User"--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<nav class="navbar navbar-default navbar-static-top" style="background: #20B2aa">
    <div class="container-fluid">
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <c:if test="${empty user}">
                <form action="/jsp/user_login.jsp" class="navbar-form navbar-right">
                    <button type="submit" class="btn btn-default"><fmt:message key="nav.signin"/></button>
                </form>
                <form action="/WEB-INF/lib/jsp/registration.jsp" class="navbar-form navbar-right">
                    <button type="submit" class="btn btn-default"><fmt:message key="nav.register"/></button>
                </form>
            </c:if>
            <c:if test="${not empty user}">
                <form action="/controller" class="navbar-form navbar-right">
                    <fmt:message key="nav.welcome"/><ctg:info user="${user}"/>
                    <input type="hidden" name="action" value="logout">
                    <button type="submit" class="btn btn-default"><fmt:message key="nav.signout"/></button>
                </form>

            </c:if>
        </div>
    </div>
</nav>
