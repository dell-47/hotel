<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.Date" %>
<%@ page import="by.it.hotel.controller.command.util.PaginationUtil" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Your account</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="locale" var="loc"/>
    <fmt:message bundle="${loc}" key="your_bookings" var="your_bookings"/>
    <fmt:message bundle="${loc}" key="pay_invoice" var="pay_invoice"/>
    <fmt:message bundle="${loc}" key="state" var="state_text"/>
    <fmt:message bundle="${loc}" key="cancel_booking" var="cancel_booking"/>
    <fmt:message bundle="${loc}" key="new_first" var="new_first"/>
    <fmt:message bundle="${loc}" key="old_first" var="old_first"/>
</head>
<body>
<jsp:include page="navbar.jsp"/>
<c:choose>
    <c:when test="${not empty paginatedList}">
        <div class="row py-1">
            <div class="col-sm-3"></div>
            <div class="col-sm-4"><h4>${your_bookings}</h4></div>
            <div class="col-2">
                <div class="dropdown">
                    <c:choose>
                        <c:when test="${order == 'oldFirst'}">
                            <a class="nav-link dropdown-toggle text-muted float-right" href="#" data-toggle="dropdown"
                               aria-haspopup="true" aria-expanded="false">${old_first} </a>
                            <div class="dropdown-menu" aria-labelledby="dropdown">
                                <a class="dropdown-item" href="/bookings?command=changeOrder&order=newFirst">${new_first}</a>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <a class="nav-link dropdown-toggle text-muted float-right" href="#" data-toggle="dropdown"
                               aria-haspopup="true" aria-expanded="false">${new_first} </a>
                            <div class="dropdown-menu" aria-labelledby="dropdown">
                                <a class="dropdown-item" href="/bookings?command=changeOrder&order=oldFirst">${old_first}</a>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
        <c:forEach items="${paginatedList}" var="reserv">
            <div class="row">
                <div class="col-3"></div>
                <div class="col-6">
                    <div class="card my-2">

                        <div class="row no-gutters">
                            <div class="col-2">
                                <img src="https://i.postimg.cc/Y03qy5b3/royal.jpg" class="card-img" alt="hotel"/>
                            </div>

                            <div class="col-8 my-auto px-3">
                                <h6>#${reserv.num}</h6>
                                    <h6><c:out value="${reserv.inDate} - ${reserv.outDate}"/></h6>
                                    <fmt:message bundle="${loc}" key="${reserv.state}" var="state"/>
                                    <c:choose>
                                        <c:when test="${reserv.state == 'state_declined'}">
                                            <p><span>${state_text}: </span><span
                                                    class="bg-danger text-white">&nbsp;${state}&nbsp;</span></p>
                                        </c:when>
                                        <c:otherwise>
                                            <p class="card-text"> ${state_text}: ${state}</p>
                                        </c:otherwise>
                                    </c:choose>
                            </div>
                            <div class="col-2 text-center my-auto">
                                <div class="card-body">
                                    <p class="card-text">$ <c:out value="${reserv.totalPrice}"/></p>

                                    <c:if test="${reserv.state eq 'state_confirmed'}">
                                        <a href="/invoice?command=invoice&reservationId=${reserv.id}"
                                           class="card-link">${pay_invoice}</a>
                                    </c:if>

                                </div>
                            </div>

                        </div>
                    </div>
                </div>
                <div class="col-sm-3 align-self-center">
                    <c:if test="${now.before(Date.valueOf(reserv.inDate)) && reserv.state != 'state_canceled' && reserv.state != 'state_declined'}">
                        <a href="/account?command=cancelBooking&reservationId=${reserv.id}"
                           class="btn-sm btn btn-danger w-25">${cancel_booking}</a>
                    </c:if>
                </div>
            </div>
        </c:forEach>
        <br>
        <c:set var="size" scope="page" value="${userReservationList.size()}" />
        <c:set var="pageCount" scope="session" value="${PaginationUtil.getPageCount(size)}" />
        <c:if test="${pageCount > 1}">
            <ul class="pagination w-75 pr-2 justify-content-end">
                <c:forEach var="i" begin="1" end="${pageCount}">
                    <li id="li${i}" class="page-item"><a class="page-link"
                                                         href="/bookings?command=page&pageNumber=${i}">${i}</a></li>
                </c:forEach>
            </ul>
            <script>
                document.getElementById("li${requestScope.pageNumber}").classList.add("active");
            </script>
        </c:if>
    </c:when>
    <c:otherwise>
        <h2 align="center"><fmt:message bundle="${loc}" key="no_bookings_message"/></h2>
    </c:otherwise>
</c:choose>
</body>
</html>