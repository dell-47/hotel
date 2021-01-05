<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
</head>
<body>
<jsp:include page="navbar.jsp"/>

<div align="center">
    <form method="post" action="/availableApartments">
        <input type="hidden" name="command" value="viewAvailable">
        <input type="date" name="inDate" value="2020-12-24">
        <input type="date" name="outDate" value="2020-12-25">
        <input type="submit" name="ok" value="Check availability">
    </form>
</div>

<c:if test="${not empty datesValidationError}">
    <div align="center" class="alert alert-danger pt-2" role="alert">
            ${datesValidationError}
    </div>
</c:if>

<div class="row pb-2">
    <div class="col-lg-2"></div>
    <div class="col-lg-8"><h3>Apartment types</h3></div>
</div>
<c:forEach items="${requestScope.apartList}" var="aparts">
    <div class="row">
        <div class="col-sm-2"></div>
        <div class="col-sm-8">
            <div class="card mb-3">
                <div class="row no-gutters">
                    <div class="col-md-4 p-3">
                            <%--                        <img src="/resources/img/${aparts.image}" class="card-img" alt="hotel"/>--%>
                        <a target='_blank'><img src='${aparts.image}' border='0' alt='image'/></a>
                    </div>
                    <div class="col-md-8 pl-5">
                        <div class="card-body">
                            <h5 class="card-title"><c:out value="${aparts.type}"/></h5>
                            <p class="card-text"><c:out value="${aparts.description}"/></p>
                        </div>
                    </div>

                </div>
            </div>
        </div>
        <div class="col-sm-2"></div>
    </div>
</c:forEach>

<div class="d-flex justify-content-center p-4">
    <form method="post" action="/availableApartments">
        <input type="hidden" name="command" value="viewAvailable">
        <input type="date" name="inDate" value="2020-12-24">
        <input type="date" name="outDate" value="2020-12-25">
        <input type="submit" name="ok" value="Check availability">
    </form>
</div>

</body>
</html>