<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Invoice</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="locale" var="loc"/>
    <fmt:message bundle="${loc}" key="invoice.invoice" var="invoice_text"/>
    <fmt:message bundle="${loc}" key="invoice.bill_to" var="bill_to"/>
    <fmt:message bundle="${loc}" key="invoice.apartment_type" var="apartment_type"/>
    <fmt:message bundle="${loc}" key="invoice.pay" var="pay"/>
    <fmt:message bundle="${loc}" key="checkout.check_in_date" var="check_in_date"/>
    <fmt:message bundle="${loc}" key="checkout.check_out_date" var="check_out_date"/>
    <fmt:message bundle="${loc}" key="checkout.cost" var="cost"/>
    <fmt:message bundle="${loc}" key="checkout.subtotal" var="subtotal"/>
    <fmt:message bundle="${loc}" key="checkout.taxes" var="taxes"/>
    <fmt:message bundle="${loc}" key="checkout.total" var="total"/>
    <fmt:message bundle="${loc}" key="checkout.back" var="back"/>
</head>
<body>
<jsp:include page="navbar.jsp"/>
<jsp:useBean id="date" class="java.util.Date"/>
<div class="col-sm-4 offset-sm-4">
    <form method="post" action="/pay">
        <input type="hidden" name="command" value="pay">
        <input type="hidden" name="reservationId" value="${invoice.id}">
        <div class="card">
            <div class="card-header">
                <div class="row">
                    <div class="col-sm-6">
                        ${invoice_text} #${invoice.id} <br>
                        <fmt:formatDate pattern="yyyy-MM-dd" value="${date}"/>
                    </div>
                    <div class="col-sm-6 text-right align-self-center">
                        <h5>Krakozhia luxury hotel</h5>
                    </div>
                </div>
            </div>
            <div class="card-body">
                <div class="row mb-4 pt-2">
                    <div class="col-sm-6">
                        <div class="row">
                            <div class="col-sm-4">
                                <h6 class="mb-3">${bill_to}: </h6>
                            </div>
                            <div class="col-sm-8">
                                ${user.firstName} ${user.lastName}
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="row">
                            <div class="col-sm-7">
                                <h6 class="mb-3">${apartment_type}:</h6>
                            </div>
                            <div class="col-sm-5">
                                ${invoice.apartType}
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-7">
                                <h6 class="mb-3">${check_in_date}:</h6>
                            </div>
                            <div class="col-sm-5">
                                ${invoice.inDate}
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-7">
                                <h6 class="mb-3">${check_out_date}:</h6>
                            </div>
                            <div class="col-sm-5">
                                ${invoice.outDate}
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <table class="table table-clear">
                            <tbody>
                            <tr>
                                <td>
                                    ${cost}
                                </td>
                                <td class="text-right">$ ${invoice.cost}</td>
                            </tr>
                            <tr>
                                <td>
                                    <h6>${subtotal}</h6>
                                </td>
                                <td class="text-right"><h6>$ ${invoice.subtotalPrice}</h6></td>
                            </tr>
                            <tr class="border-bottom-0">
                                <td>
                                    <h6>${taxes}</h6>
                                </td>
                                <td class="text-right"><h6>$ ${invoice.taxes}</h6></td>
                            </tr>
                            <tr class="border-bottom-0">
                                <td>
                                    <strong>${total}</strong>
                                </td>
                                <td class="text-right">
                                    <strong>$ ${invoice.totalPrice}</strong>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <br>
        <div class="row">
            <div class="col-6">
                <button type="button" class="btn btn-secondary w-50" onclick="history.back()">${back}</button>
            </div>
            <div class="col-6">
                <button type="submit" class="btn btn-primary w-50 float-right">${pay}</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>