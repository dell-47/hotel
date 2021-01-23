<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Successful booking</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="locale" var="loc"/>
    <fmt:message bundle="${loc}" key="login" var="login"/>
    <fmt:message bundle="${loc}" key="congratulations" var="congratulations"/>
    <fmt:message bundle="${loc}" key="successful_booking" var="successful_booking"/>
    <fmt:message bundle="${loc}" key="successful_booking_message" var="booking_message"/>
    <fmt:message bundle="${loc}" key="your_account" var="your_account"/>
    <fmt:message bundle="${loc}" key="successful_registration" var="successful_registration"/>
    <fmt:message bundle="${loc}" key="successful_registration_message" var="registration_message"/>
</head>
<body>
<jsp:include page="/WEB-INF/views/navbar.jsp"/>
<c:choose>
    <c:when test="${action == 'signUp'}">
        <div class="col-sm-3 offset-sm-4">
            <div class="card">
                <div class="card-header">
                    ${congratulations}
                </div>
                <div class="card-body">
                    <h6>${successful_registration}</h6>
                    <p class="card-text">${registration_message}</p>
                    <a href="/login?command=goToLoginPage" class="card-link">${login}</a>
                </div>
            </div>
        </div>
    </c:when>
    <c:otherwise>
        <div class="col-sm-3 offset-sm-4">
            <div class="card">
                <div class="card-header">
                    ${congratulations}
                </div>
                <div class="card-body">
                    <h6>${successful_booking}</h6>
                    <p class="card-text">${booking_message}</p>
                    <a href="/bookings?command=bookings&id=${user.id}" class="card-link">${your_account}</a>
                </div>
            </div>
        </div>
    </c:otherwise>
</c:choose>

</body>
</html>