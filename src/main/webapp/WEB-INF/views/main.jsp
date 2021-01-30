<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="locale" var="loc"/>
    <fmt:message bundle="${loc}" key="check" var="check"/>
    <fmt:message bundle="${loc}" key="types" var="types"/>
    <fmt:message bundle="${loc}" key="hotel_header" var="hotel_header"/>
    <fmt:message bundle="${loc}" key="hotel_description_part1" var="hotel_description_part1"/>
    <fmt:message bundle="${loc}" key="hotel_description_part2" var="hotel_description_part2"/>
</head>
<body>
<jsp:include page="navbar.jsp"/>

<div class="row pb-2">
    <div class="col-lg-2"></div>
    <div class="col-lg-8"><h2>${hotel_header}</h2></div>
</div>
<div class="row pb-2">
    <div class="col-lg-2"></div>
    <div class="col-lg-8"><h5 class="font-weight-normal py-1">${hotel_description_part1}</h5></div>
</div>
<div class="row pb-4">
    <div class="col-lg-2"></div>
    <div class="col-lg-8"><h5 class="font-weight-normal py-1">${hotel_description_part2}</h5></div>
</div>

<div align="center">
    <div class="row my-5 pb-5">
        <div class="col-2"></div>
        <div class="col-4 ml-4">
            <div class="row mb-4">
                <div class="col">
                    <a target='_blank'><img src='https://i.postimg.cc/zfMhhd3c/hotel4.jpg' class="img-fluid"
                                            alt='image'/></a>
                </div>
            </div>
            <div class="row mt-4">
                <div class="col-6 mt-1">
                    <a target='_blank'><img src='https://i.postimg.cc/WbJM7c0w/hotel2.jpg' class="img-fluid"
                                            alt='image'/></a>
                </div>
                <div class="col-6 mt-1">
                    <a target='_blank'><img src='https://i.postimg.cc/7ZHSrGpm/hotel3.jpg' class="img-fluid"
                                            alt='image'/></a>
                </div>
            </div>
        </div>
        <div class="col-4 ml-2">
            <a target='_blank'><img src='https://i.postimg.cc/PxRwmSx7/hotel1.jpg' class="float-left" alt='image'/></a>
        </div>
    </div>
</div>

<div align="center" class="pt-2">
    <form method="post" action="/availableApartments">
        <input type="hidden" name="command" value="viewAvailable">
        <input type="hidden" name="page" value="main">
        <input type="date" name="inDate" value="${todayDate}">
        <input type="date" name="outDate" value="${tomorrowDate}">
        <input type="submit" name="ok" value="${check}">
    </form>
</div>
<c:if test="${not empty datesValidationError}">
    <div align="center" class="alert alert-danger pt-2" role="alert">
        <fmt:message key="${datesValidationError}" bundle="${loc}"/>
    </div>
</c:if>
<div class="row pb-2">
    <div class="col-lg-2"></div>
    <div class="col-lg-8"><h3>${types}</h3></div>
</div>
<c:forEach items="${requestScope.apartList}" var="aparts">
    <div class="row">
        <div class="col-sm-2"></div>
        <div class="col-sm-8">
            <div class="card mb-3">
                <div class="row no-gutters">
                    <div class="col-md-4 p-3">
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
        <input type="date" name="inDate" value="${todayDate}">
        <input type="date" name="outDate" value="${tomorrowDate}">
        <input type="submit" name="ok" value="${check}">
    </form>
</div>

</body>
</html>