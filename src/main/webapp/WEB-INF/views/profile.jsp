<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Your account</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="locale" var="loc"/>
    <fmt:message bundle="${loc}" key="user_profile" var="user_profile"/>
    <fmt:message bundle="${loc}" key="first_name" var="first_name"/>
    <fmt:message bundle="${loc}" key="last_name" var="last_name"/>
    <fmt:message bundle="${loc}" key="email" var="email"/>
    <fmt:message bundle="${loc}" key="edit" var="edit"/>
</head>
<body>
<jsp:include page="navbar.jsp"/>
<div class="container-fluid">
    <div class="row justify-content-center">
        <div class="col-3">
            <div class="card">
                <div class="card-header">
                    <h5>${user_profile}</h5>
                </div>
                <div class="card-body">
                    <table class="table table-clear">
                        <tbody>
                        <tr>
                            <td class="border-top-0 w-50">
                                <p class="card-text">${first_name}:</p>
                            </td>
                            <td class="border-top-0">${user.firstName}</td>
                        </tr>

                        <tr>
                            <td class="w-50">
                                <p class="card-text">${last_name}:</p>
                            </td>
                            <td>${user.lastName}</td>
                        </tr>

                        <tr>
                            <td class="border-bottom-0 w-50">
                                <p class="card-text">${email}:</p>
                            </td>
                            <td class="border-bottom-0">${user.email}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>

            </div>
            <br>
            <div align="center">
                <a href="/profile?command=editProfile" class="btn btn-primary btn-sm w-50">${edit}</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>