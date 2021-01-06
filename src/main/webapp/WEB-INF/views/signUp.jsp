<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Sign up</title>
    <head>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
              integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
              crossorigin="anonymous">
        <script src="https://cdn.rawgit.com/PascaleBeier/bootstrap-validate/v2.2.0/dist/bootstrap-validate.js"></script>
    </head>
</head>
<body>
<jsp:include page="navbar.jsp"/>

<div class="container-fluid">
    <div class="row justify-content-center">
        <div class="col-lg-3">
            <div class="login-form bg-light p-4 mb-3 border">
                <form action="/signUp" method="post">
                    <input type="hidden" name="command" value="login">
                    <h2 class="text-center pb-3">Sign up</h2>
                    <div class="form-group">
                        <div class="row">
                            <div class="col-sm-6">
                                <input type="text" name="firstName" class="form-control" placeholder="First Name"
                                       required="required">
                            </div>
                            <div class="col-sm-6">
                                <input type="text" name="lastName" class="form-control" placeholder="Last Name"
                                       required="required">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <input type="email" name="email" class="form-control" placeholder="Email"
                               required="required">
                    </div>

                    <div class="form-group">
                        <input type="text" name="username" class="form-control" placeholder="Username"
                               required="required">
                    </div>

                    <div class="form-group">
                        <input type="password" name="password" class="form-control" placeholder="Password"
                               required="required">
                    </div>
                    <div class="form-group">
                        <input type="password" name="password_confirm" class="form-control"
                               placeholder="Confirm Password"
                               required="required">
                    </div>

                    <div class="form-group pt-3">
                        <button type="submit" class="btn btn-primary btn-block">Sign up</button>
                    </div>
                    <p class="text-center text-danger"><c:out value="${signUpError}"/></p>
                </form>
            </div>
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
