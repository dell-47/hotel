<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Invoice</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
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
                        Invoice #${invoice.id} <br>
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
                                <h6 class="mb-3">Bill to: </h6>
                            </div>
                            <div class="col-sm-8">
                                ${user.firstName} ${user.lastName}
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="row">
                            <div class="col-sm-7">
                                <h6 class="mb-3">Apartment type:</h6>
                            </div>
                            <div class="col-sm-5">
                                ${invoice.apartType}
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-7">
                                <h6 class="mb-3">Check-in date:</h6>
                            </div>
                            <div class="col-sm-5">
                                ${invoice.inDate}
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-7">
                                <h6 class="mb-3">Check-out date:</h6>
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
                                    Apartment cost
                                </td>
                                <td class="text-right">$ ${invoice.cost}</td>
                            </tr>
                            <tr>
                                <td>
                                    <h6>Subtotal</h6>
                                </td>
                                <td class="text-right"><h6>$ ${invoice.subtotalPrice}</h6></td>
                            </tr>
                            <tr class="border-bottom-0">
                                <td>
                                    <h6>Estimated taxes</h6>
                                </td>
                                <td class="text-right"><h6>$ ${invoice.taxes}</h6></td>
                            </tr>
                            <tr class="border-bottom-0">
                                <td>
                                    <strong>Total</strong>
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
        <div>
            <button type="submit" class="btn btn-primary w-25 float-right">Pay</button>
        </div>
    </form>
</div>
</body>
</html>