<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Sign up</title>
    <head>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
              integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
              crossorigin="anonymous">
        <fmt:setLocale value="${sessionScope.locale}"/>
        <fmt:setBundle basename="locale" var="loc"/>
        <fmt:message bundle="${loc}" key="signUp" var="signUp"/>
        <fmt:message bundle="${loc}" key="username" var="username"/>
        <fmt:message bundle="${loc}" key="password" var="password"/>
        <fmt:message bundle="${loc}" key="first_name" var="first_name"/>
        <fmt:message bundle="${loc}" key="last_name" var="last_name"/>
        <fmt:message bundle="${loc}" key="email" var="email"/>
        <fmt:message bundle="${loc}" key="confirm_password" var="confirm_password"/>
    </head>
</head>
<body>
<jsp:include page="navbar.jsp"/>
<div class="container-fluid">
    <div class="row justify-content-center">
        <div class="col-lg-3">
            <div class="login-form bg-light p-4 mb-3 border">
                <form id="signUpForm" action="/signUp" method="post">
                    <input type="hidden" name="command" value="signUp">
                    <h2 class="text-center pb-3">${signUp}</h2>
                    <div class="form-group">
                        <div class="row">
                            <div class="col-sm-6">
                                <input type="text" id="firstName" name="firstName" class="form-control"
                                       placeholder="${first_name}" required>
                            </div>
                            <div class="col-sm-6">
                                <input type="text" id="lastName" name="lastName" class="form-control"
                                       placeholder="${last_name}" required>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <input type="email" id="email" name="email" class="form-control" placeholder="${email}" required>
                    </div>

                    <div class="form-group">
                        <input type="text" id="username" name="username" class="form-control" placeholder="${username}"
                               required>
                    </div>

                    <div class="form-group">
                        <input type="password" id="password" name="password" class="form-control" placeholder="${password}"
                               required>
                    </div>
                    <div class="form-group">
                        <input type="password" id="password_confirm" name="password_confirm" class="form-control"
                               placeholder="${confirm_password}" required>
                    </div>

                    <div class="form-group pt-3">
                        <button type="submit" class="btn btn-primary btn-block">${signUp}</button>
                    </div>
                    <c:if test="${not empty usernameError}">
                        <p class="text-center text-danger"><fmt:message key="${usernameError}" bundle="${loc}"/></p>
                    </c:if>
                </form>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.rawgit.com/PascaleBeier/bootstrap-validate/v2.2.0/dist/bootstrap-validate.js"></script>
<script>
    bootstrapValidate('#email', 'email:Enter a valid email address');
    bootstrapValidate('#username', 'min:3:The username must contain at least 3 characters!');
    bootstrapValidate('#password', 'regex:^[a-zA-Z0-9_]+$:The password can only consist of alphabetical, number and underscore');
    bootstrapValidate('#password', 'min:6:The password must contain at least 6 characters!');
    bootstrapValidate('#password_confirm', 'matches:#password:The entered password are not the same');
</script>
</body>
</html>