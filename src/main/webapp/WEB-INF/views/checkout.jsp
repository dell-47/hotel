<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Checkout</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="locale" var="loc"/>
    <fmt:message bundle="${loc}" key="review_booking" var="review_booking"/>
    <fmt:message bundle="${loc}" key="apartment" var="apartment"/>
    <fmt:message bundle="${loc}" key="check_in_date" var="check_in_date"/>
    <fmt:message bundle="${loc}" key="check_out_date" var="check_out_date"/>
    <fmt:message bundle="${loc}" key="price_summary" var="price_summary"/>
    <fmt:message bundle="${loc}" key="cost" var="cost"/>
    <fmt:message bundle="${loc}" key="subtotal" var="subtotal"/>
    <fmt:message bundle="${loc}" key="taxes" var="taxes"/>
    <fmt:message bundle="${loc}" key="total" var="total"/>
    <fmt:message bundle="${loc}" key="nights" var="nights"/>
    <fmt:message bundle="${loc}" key="book" var="book"/>
    <fmt:message bundle="${loc}" key="back" var="back"/>
</head>
<body>
<jsp:include page="navbar.jsp"/>
<div class="row pb-3">
    <div class="col-2"></div>
    <div class="col-8"><h3>${review_booking}</h3></div>
</div>
<div class="row">
    <div class="col-2"></div>
    <div class="col-8">
        <div class="card">
            <div class="row no-gutters">
                <div class="col-3 p-2">
                    <a target='_blank'><img class="img-fluid" src='${apart.image}' border='0' alt='image'/></a>
                </div>
                <div class="col-2 mt-2 border-left">
                    <div class="card-body">
                        <h5 class="font-weight-normal py-1">${apartment}:</h5>
                        <h5 class="font-weight-normal py-1">${nights}:</h5>
                        <h5 class="font-weight-normal py-1">${check_in_date}:</h5>
                        <h5 class="font-weight-normal py-1">${check_out_date}:</h5>
                    </div>
                </div>
                <div class="col-2 mt-2 border-right">
                    <div class="card-body">
                        <h5 class="py-1">${apart.type}</h5>
                        <h5 class="py-1">${checkOutData.nights}</h5>
                        <h5 class="py-1">${inDate}</h5>
                        <h5 class="py-1">${outDate}</h5>
                    </div>
                </div>
                <div class="col-3 mt-2">
                    <div class="card-body">
                        <h5 class="font-weight-normal py-1">${cost}:</h5>
                        <h5 class="font-weight-normal py-1">${subtotal}:</h5>
                        <h5 class="font-weight-normal py-1">${taxes}:</h5>
                        <h5 class="font-weight-bold py-1">${total}:</h5>
                    </div>
                </div>
                <div class="col-2 mt-2">
                    <div class="card-body">
                        <h5 class="py-1">$ ${checkOutData.cost}</h5>
                        <h5 class="py-1">$ ${checkOutData.subtotalPrice}</h5>
                        <h5 class="py-1">$ ${checkOutData.taxes}</h5>
                        <h5 class="font-weight-bold py-1">$ ${checkOutData.totalPrice}</h5>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<br><br>
<div class="row">
    <div class="col-2"></div>
    <div class="col-2">
        <button type="button" class="btn btn-secondary w-50" onclick="history.back()">${back}</button>
    </div>
    <div class="col-4"></div>
    <div class="col-2">
        <a href="/book?command=book" class="btn btn-primary float-right w-50">${book}</a>
    </div>
</div>
</body>
</html>