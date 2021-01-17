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
    <fmt:message bundle="${loc}" key="submit" var="submit"/>
</head>
<body>
<jsp:include page="navbar.jsp"/>
<div class="col-sm-3 offset-sm-4">
    <form method="post" action="/book">
        <div class="card">
            <div class="card-header">
                ${review_booking}
            </div>
            <div class="card-body">
                <input type="hidden" name="command" value="book">
<%--                <input type="hidden" name="apartId" value="${apart.id}">
                <input type="hidden" name="userId" value="${user.id}">
                <input type="hidden" name="inDate" value="${inDate}">
                <input type="hidden" name="outDate" value="${outDate}">--%>
                <div class="row my-2">
                    <div class="col-sm-6">
                        <p class="card-text">${apartment}:</p>
                    </div>
                    <div class="col">
                        <p class="card-text">${apart.type}</p>
                    </div>
                </div>
                <div class="row my-2">
                    <div class="col-sm-6">
                        <p class="card-text">${check_in_date}:</p>
                    </div>
                    <div class="col">
                        <p class="card-text">${inDate}</p>
                    </div>
                </div>
                <div class="row my-2">
                    <div class="col-sm-6">
                        <p class="card-text">${check_out_date}:</p>
                    </div>
                    <div class="col">
                        <p class="card-text">${outDate}</p>
                    </div>
                </div>

            </div>
        </div>
        <div class="card mt-3">
            <div class="card-header">
                ${price_summary}
            </div>
            <div class="card-body">
                <div class="row my-2">
                    <div class="col-sm-6">
                        <p class="card-text">${cost}:</p>
                    </div>
                    <div class="col-sm-6">
                        <p class="card-text">$ ${checkOutData.cost}</p>
                    </div>
                </div>
                <div class="row my-2">
                    <div class="col-sm-6">
                        <p class="card-text">${subtotal}:</p>
                    </div>
                    <div class="col-sm-6 justify-content-end">
                        <p class="card-text">$ ${checkOutData.subtotalPrice}</p>
                    </div>
                </div>
                <div class="row my-2">
                    <div class="col-sm-6">
                        <p class="card-text">${taxes}:</p>
                    </div>
                    <div class="col-sm-6">
                        <p class="card-text">$ ${checkOutData.taxes}</p>
                    </div>
                </div>
                <div class="row my-2">
                    <div class="col-sm-6">
                        <p class="card-text"><b>${total}:</b></p>
                    </div>
                    <div class="col-sm-6 justify-content-end">
                        <p class="card-text"><b>$ ${checkOutData.totalPrice}</b></p>
                    </div>
                </div>
            </div>
        </div>
        <br>
        <div class="col text-center">
            <button type="submit" class="btn btn-primary mr-2 w-50 justify-content-center">${submit}</button>
        </div>
    </form>
</div>
</body>
</html>