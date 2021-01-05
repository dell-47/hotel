<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Your account</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
</head>
<body>
<jsp:include page="navbar.jsp"/>
<div class="row pb-3">
    <div class="col-sm-3"></div>
    <div class="col-sm-6"><h4>Your bookings</h4></div>
</div>
<c:forEach items="${requestScope.reservationList}" var="reserv">
    <div class="row">
        <div class="col-sm-3"></div>
        <div class="col-sm-6">
            <div class="card mb-3">
                <div class="row no-gutters">

                    <div class="col-md-2">
                        <img src="https://i.postimg.cc/Y03qy5b3/royal.jpg" class="card-img" alt="hotel"/>
                    </div>

                    <div class="col-md-8">
                        <div class="card-body">
                            <h6 class="card-title"><c:out value="${reserv.inDate} - ${reserv.outDate}"/></h6>
                            <p class="card-text"> State: <c:out value=" ${reserv.state}"/></p>
                        </div>
                    </div>

                    <div class="col-md-2 text-center">
                        <div class="card-body">
                            <p class="card-text">$ <c:out value="${reserv.totalPrice}"/></p>

                            <c:if test="${reserv.state eq 'confirmed'}">
                                <a href="/invoice?command=invoice&reservationId=${reserv.id}" class="card-link">Pay the bill</a>
                            </c:if>

                        </div>
                    </div>

                </div>
            </div>
        </div>
        <div class="col-sm-3"></div>
    </div>
</c:forEach>
</body>
</html>
