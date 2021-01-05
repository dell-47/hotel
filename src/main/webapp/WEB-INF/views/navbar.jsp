<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
</head>
<body>

<div class="container-fluid pb-5">
    <div class="row">
        <div class="col border">
            <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <a class="navbar-brand" href="/index.jsp"><b>Krakozhia luxury hotel</b></a>
                <div class="collapse navbar-collapse justify-content-end" id="navbarNav">

                    <c:choose>
                        <c:when test="${not empty user}">
                            <div class="navbar-nav ml-auto">
                                <div class="nav-item dropdown dropleft">
                                    <a class="nav-link dropdown-toggle" href="#" id="navbardrop"
                                       data-toggle="dropdown"> ${user.firstName}<b class="caret"></b></a>

                                    <div class="dropdown-menu" aria-labelledby="navbardrop">
                                        <c:choose>
                                            <c:when test="${user.role == 1}">
                                                <a class="dropdown-item" href="/admin?command=admin">Admin page</a>
                                            </c:when>
                                            <c:otherwise>
                                                <a class="dropdown-item" href="/profile?command=profile">Profile</a>
                                            </c:otherwise>
                                        </c:choose>
                                        <a class="dropdown-item" href="/logout?command=logout">Logout</a>
                                    </div>

                                </div>
                            </div>
                        </c:when>

                        <c:otherwise>
                            <div class="navbar-nav ml-auto action-buttons">
                                <div class="nav-item">
                                    <a href="/login?command=goToLoginPage" class="nav-link mr-4">Login</a>
                                </div>
                                <div class="nav-item">
                                    <a href="/signUp?command=goToSignUpPage" class="btn btn-primary sign-up-btn">Sign
                                        up</a>
                                </div>
                            </div>
                        </c:otherwise>
                    </c:choose>

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
