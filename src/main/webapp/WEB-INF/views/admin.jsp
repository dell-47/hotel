<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Administration</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
</head>
<body>
<jsp:include page="navbar.jsp"/>
<c:choose>
    <c:when test="${not empty adminList}">
        <div class="row pb-3">
            <div class="col-sm-3"></div>
            <div class="col-sm-6"><h4>New bookings</h4></div>
        </div>
        <c:forEach items="${requestScope.adminList}" var="reserv">
            <div class="row py-2">
                <div class="col-lg-3"></div>
                <div class="col-lg-6">
                    <div class="card">
                        <div class="card-header">
                            ${reserv.time.toString().replace("T", " ")}
                        </div>
                        <div class="row">

                            <form method="post" action="/admin">
                                <input type="hidden" name="command" value="confirm">
                                <input type="hidden" name="reservationId" value="${reserv.id}">
                                <input type="hidden" name="price" value="${reserv.totalPrice}">

                                <div class="form-group row align-items-center h-100 ml-3">

                                    <div class="col-md-3 mt-3">
                                        <h6 class="card-title"><c:out value=" ${reserv.apartType}"/></h6>
                                        <p class="card-text"><c:out value="${reserv.inDate} - ${reserv.outDate}"/></p>
                                    </div>

                                    <div class="col-md-2">
                                        <a href="/admin?command=check&apartTypeId=${reserv.apartTypeId}&reservationId=${reserv.id}&inDate=${reserv.inDate}&outDate=${reserv.outDate}"
                                           class="card-link">Check availability</a>
                                    </div>

                                    <div class="col-md-4">

                                        <c:if test="${not empty availableAparts && reserv.id == reservationId}">
                                            <div class="card-body">
                                                <div class="card-title">Choose number of apartment</div>
                                                <select name="apartId" class="selectpicker" data-width="100%"
                                                        title="Choose one of the following...">
                                                    <c:forEach items="${requestScope.availableAparts}"
                                                               var="availableApart">
                                                        <option value="${availableApart.id}"><c:out
                                                                value="${availableApart.num}"/></option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </c:if>

                                    </div>

                                    <div class="col-md-2  text-center">
                                        <c:if test="${not empty availableAparts && reserv.id == reservationId}">
                                            <div class="card-body">

                                                <button type="submit" class="btn btn-primary">Confirm</button>

                                            </div>
                                        </c:if>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3"></div>
            </div>
        </c:forEach>
    </c:when>
    <c:otherwise>
        <h2 align="center">Pity... No new bookings</h2>
    </c:otherwise>
</c:choose>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"
        integrity="sha384-q2kxQ16AaE6UbzuKqyBE9/u/KzioAlnx2maXQHiDX9d4/zp8Ok3f+M7DPm+Ib6IU"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.min.js"
        integrity="sha384-pQQkAEnwaBkjpqZ8RU1fF1AKtTcHJwFl3pblpTlHXybJjHpMYo79HY3hIi4NKxyj"
        crossorigin="anonymous"></script>
</body>
</html>
