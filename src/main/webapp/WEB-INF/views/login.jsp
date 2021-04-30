<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Login</title>
    <head>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
              integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
              crossorigin="anonymous">
    </head>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="locale" var="loc"/>
    <fmt:message bundle="${loc}" key="navbar.login" var="login"/>
    <fmt:message bundle="${loc}" key="navbar.signUp" var="signUp"/>
    <fmt:message bundle="${loc}" key="login.username" var="username"/>
    <fmt:message bundle="${loc}" key="login.password" var="password"/>
</head>
<body>
<jsp:include page="navbar.jsp"/>
<div class="container">
    <div class="row">
        <div class="col-sm-4 offset-sm-4">
            <div class="login-form bg-light p-4 mb-3 border">
                <form action="/login" method="post">
                    <input type="hidden" name="command" value="login">
                    <h2 class="text-center pb-3">${login}</h2>
                    <div class="form-group">
                        <input type="text" name="username" class="form-control" placeholder="${username}"
                               required="required">
                    </div>
                    <div class="form-group">
                        <input type="password" name="password" class="form-control" placeholder="${password}"
                               required="required">
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary btn-block">${login}</button>
                    </div>
                    <c:if test="${not empty loginError}">
                        <p class="text-center text-danger"><fmt:message key="${loginError}" bundle="${loc}"/></p>
                    </c:if>
                </form>
            </div>
            <p class="text-center"><a href="/signUp?command=goToSignUpPage">${signUp}</a></p>
        </div>
    </div>
</div>
</body>
</html>