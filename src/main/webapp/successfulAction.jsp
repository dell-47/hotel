<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Successful booking</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
</head>
<body>
<jsp:include page="/WEB-INF/views/navbar.jsp"/>
<c:choose>
    <c:when test="${action == 'signUp'}">
        <div class="col-sm-3 offset-sm-4">
            <div class="card">
                <div class="card-header">
                    Congratulations!
                </div>
                <div class="card-body">
                    <h6>You have successfully registered.</h6>
                    <p class="card-text">You can login now and continue booking</p>
                    <a href="/login?command=goToLoginPage" class="card-link">Login</a>
                </div>
            </div>
        </div>
    </c:when>
    <c:otherwise>
        <div class="col-sm-3 offset-sm-4">
            <div class="card">
                <div class="card-header">
                    Congratulations!
                </div>
                <div class="card-body">
                    <h6>Successful booking.</h6>
                    <p class="card-text">You can view the status of your reservation and pay the bill in your personal
                        account.</p>
                    <a href="/profile?command=profile&id=${user.id}" class="card-link">Your account</a>
                </div>
            </div>
        </div>
    </c:otherwise>
</c:choose>

</body>
</html>