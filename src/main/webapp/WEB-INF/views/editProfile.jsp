<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Edit profile</title>
    <head>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
              integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
              crossorigin="anonymous">
        <fmt:setLocale value="${sessionScope.locale}"/>
        <fmt:setBundle basename="locale" var="loc"/>
        <fmt:message bundle="${loc}" key="editProfile.save" var="save"/>
        <fmt:message bundle="${loc}" key="editProfile.cancel" var="cancel"/>
        <fmt:message bundle="${loc}" key="signUp.first_name" var="first_name"/>
        <fmt:message bundle="${loc}" key="signUp.last_name" var="last_name"/>
        <fmt:message bundle="${loc}" key="signUp.email" var="email"/>
    </head>
</head>
<body>
<jsp:include page="navbar.jsp"/>
<div class="container-fluid">
    <div class="row justify-content-center">
        <div class="col-lg-3">
            <div class="login-form bg-light p-4 border">
                <form id="editProfileForm" action="/profile" method="post" class="mt-3">
                    <input type="hidden" name="command" value="saveProfile">
                    <input type="hidden" name="userId" value="${user.id}">
                    <div class="form-group">
                        <input type="text" name="firstName" class="form-control" placeholder="${first_name}">
                    </div>
                    <div class="form-group">
                        <input type="text" name="lastName" class="form-control" placeholder="${last_name}">
                    </div>
                    <div class="form-group">
                        <input type="email" id="email" name="email" class="form-control" placeholder="${email}">
                    </div>
                    <div class="form-group pt-3">
                        <a href="/profile?command=profile" class="btn-sm btn btn-secondary w-25">${cancel}</a>
                        <button type="submit" class="btn btn-sm btn-success float-right w-25">${save}</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.rawgit.com/PascaleBeier/bootstrap-validate/v2.2.0/dist/bootstrap-validate.js"></script>
<script>
    bootstrapValidate('#email', 'email:Enter a valid email address');
</script>
</body>
</html>