<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Invoice</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
</head>
<body>
<jsp:include page="navbar.jsp"/>

<div class="col-sm-3 offset-sm-4">
    <form method="post" action="/pay">
        <input type="hidden" name="command" value="pay">
        <input type="hidden" name="reservationId" value="${invoice.id}">

        <div class="card">
            <div class="card-header">
                ${user.firstName} ${user.lastName}
            </div>
            <div class="card-body">


                <div class="row my-2">
                    <div class="col-sm-6">
                        <p class="card-text">Apartment:</p>
                    </div>
                    <div class="col">
                        <p class="card-text">${invoice.apartType}</p>
                    </div>
                </div>
                <div class="row my-2">
                    <div class="col-sm-6">
                        <p class="card-text">Check-in date:</p>
                    </div>
                    <div class="col">
                        <p class="card-text">${invoice.inDate}</p>
                    </div>
                </div>
                <div class="row my-2">
                    <div class="col-sm-6">
                        <p class="card-text">Check-out date:</p>
                    </div>
                    <div class="col">
                        <p class="card-text">${invoice.outDate}</p>
                    </div>
                </div>



                <div class="row my-2">
                    <div class="col-sm-6">
                        <p class="card-text">Apartment cost:</p>
                    </div>
                    <div class="col-sm-2">
                        <p class="card-text">${invoice.cost}</p>
                    </div>
                </div>
                <div class="row my-2">
                    <div class="col-sm-6">
                        <p class="card-text">Subtotal:</p>
                    </div>
                    <div class="col-sm-2 justify-content-end">
                        <p class="card-text">${invoice.subtotalPrice}</p>
                    </div>
                </div>
                <div class="row my-2">
                    <div class="col-sm-6">
                        <p class="card-text">Estimated taxes:</p>
                    </div>
                    <div class="col-sm-2">
                        <p class="card-text">${invoice.taxes}</p>
                    </div>
                </div>
                <div class="row my-2">
                    <div class="col-sm-6">
                        <p class="card-text"><b>Total price</b></p>
                    </div>
                    <div class="col-sm-2 justify-content-end">
                        <p class="card-text"><b>${invoice.totalPrice}</b></p>
                    </div>
                </div>



            </div>
        </div>
        <br>
        <div>
            <button type="submit" class="btn btn-primary mr-2">Pay</button>
        </div>
    </form>
</div>
</body>
</html>
