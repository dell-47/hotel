<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="locale" var="loc"/>
    <fmt:message bundle="${loc}" key="select" var="select"/>
    <fmt:message bundle="${loc}" key="check" var="check"/>
    <fmt:message bundle="${loc}" key="available_aparts" var="available_aparts"/>
</head>
<body>
<jsp:include page="navbar.jsp"/>
<div align="center">
    <form method="post" action="/availableApartments">
        <input type="hidden" name="command" value="viewAvailable">
        <input type="hidden" name="page" value="viewAvailable">
        <input type="date" name="inDate" value="${inDate}">
        <input type="date" name="outDate" value="${outDate}">
        <input type="submit" name="ok" value="${check}">
    </form>
</div>
<c:choose>
    <c:when test="${not empty datesValidationError}">
        <div align="center" class="alert alert-danger pt-2" role="alert">
            <fmt:message key="${datesValidationError}" bundle="${loc}"/>
        </div>
    </c:when>
    <c:otherwise>
        <c:choose>
            <c:when test="${not empty availableAparts}">
                <div class="row pt-5 pb-4">
                    <div class="col-sm-2"></div>
                    <div class="col-sm-8">
                        <h4 class="text-dark text-center">${available_aparts} </h4>
                        <h5 class="text-center">${inDate} - ${outDate}</h5>
                    </div>
                </div>
                <c:forEach items="${availableAparts}" var="aparts">
                    <div class="row">
                        <div class="col-sm-2"></div>
                        <div class="col-sm-8">
                            <div class="card mb-3">
                                <div class="row no-gutters">
                                    <div class="col-md-3 p-3">
                                        <a target='_blank'><img src='${aparts.image}' border='0' alt='image'/></a>
                                    </div>
                                    <div class="col-md-7 pl-5">
                                        <div class="card-body">
                                            <h5 class="card-title"><c:out value="${aparts.type}"/></h5>
                                            <p class="card-text"><c:out value="${aparts.description}"/></p>
                                        </div>
                                    </div>
                                    <div class="col-md-2 text-center pt-5">

                                        <div class="card-body">
                                            <form action="/checkout" method="post">
                                                <p class="card-text">$ <c:out value="${aparts.price}"/></p>
                                                <a href="/checkout?command=checkout&inDate=${inDate}&outDate=${outDate}&id=${aparts.id}"
                                                   class="btn btn-danger btn-md">${select}</a>
                                            </form>
                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-2"></div>
                    </div>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <h2 align="center">Sorry, there are no available apartment for these dates</h2>
                <div class="d-flex align-items-center justify-content-center pt-5">
                    <a href="/main?command=goToMainPage" class="text-muted">you can try again with other dates</a>
                </div>
            </c:otherwise>
        </c:choose>
    </c:otherwise>
</c:choose>
</body>
</html>