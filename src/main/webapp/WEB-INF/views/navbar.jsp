<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/flag-icon-css/3.5.0/css/flag-icon.min.css"
          integrity="sha512-Cv93isQdFwaKBV+Z4X8kaVBYWHST58Xb/jVOcV9aRsGSArZsgAnFIhMpDoMDcFNoUtday1hdjn0nGp3+KZyyFw=="
          crossorigin="anonymous"/>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="locale" var="loc"/>
    <fmt:message bundle="${loc}" key="login" var="login"/>
    <fmt:message bundle="${loc}" key="signUp" var="signUp"/>
    <fmt:message bundle="${loc}" key="adminPage" var="adminPage"/>
    <fmt:message bundle="${loc}" key="profile" var="profile"/>
    <fmt:message bundle="${loc}" key="bookings" var="bookings"/>
    <fmt:message bundle="${loc}" key="logout" var="logout"/>
</head>
<body>
<style>
    .pl-1150 {
        padding-left: 1150px;
    }
</style>

<div class="container-fluid pb-5">
    <div class="row">
        <div class="col border">
            <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <a class="navbar-brand" href="/index.jsp"><b>Krakozhia luxury hotel</b></a>
                <div class="collapse navbar-collapse justify-content-end" id="navbarNav">
                    <div class="navbar-nav pl-1150">
                        <div class="nav-item dropdown">
                            <c:choose>
                                <c:when test="${locale == 'en'}">
                                    <a class="nav-link dropdown-toggle" href="#" id="dropdown09" data-toggle="dropdown"
                                       aria-haspopup="true" aria-expanded="false"><span
                                            class="flag-icon flag-icon-us"> </span> English</a>
                                    <div class="dropdown-menu" aria-labelledby="dropdown09">
                                        <a class="dropdown-item" href="/locale?command=changeLocale&lang=ru"><span
                                                class="flag-icon flag-icon-ru"></span> Русский</a>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <a class="nav-link dropdown-toggle" href="#" id="dropdown09" data-toggle="dropdown"
                                       aria-haspopup="true" aria-expanded="false"><span
                                            class="flag-icon flag-icon-ru"> </span> Русский</a>
                                    <div class="dropdown-menu" aria-labelledby="dropdown09">
                                        <a class="dropdown-item" href="/locale?command=changeLocale&lang=en"><span
                                                class="flag-icon flag-icon-us"></span> English</a>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                        <div class="navbar-nav ml-auto">
                        <c:choose>
                            <c:when test="${not empty user}">
                                <div class="nav-item dropdown dropleft ml-5">
                                    <a class="nav-link dropdown-toggle" href="#" id="navbardrop"
                                       data-toggle="dropdown"> ${user.firstName}<b class="caret"></b></a>
                                    <div class="dropdown-menu" aria-labelledby="navbardrop">
                                        <c:choose>
                                            <c:when test="${user.role == 1}">
                                                <a class="dropdown-item" href="/admin?command=admin">${adminPage}</a>
                                            </c:when>
                                            <c:otherwise>
                                                <a class="dropdown-item" href="/profile?command=profile">${profile}</a>
                                                <a class="dropdown-item" href="/bookings?command=bookings">${bookings}</a>
                                            </c:otherwise>
                                        </c:choose>
                                        <a class="dropdown-item" href="/logout?command=logout">${logout}</a>
                                    </div>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="nav-item">
                                    <a href="/login?command=goToLoginPage" class="nav-link mr-4">${login}</a>
                                </div>
                                <div class="nav-item">
                                    <a href="/signUp?command=goToSignUpPage"
                                       class="btn btn-primary sign-up-btn">${signUp}</a>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </nav>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx"
        crossorigin="anonymous"></script>
</body>
</html>