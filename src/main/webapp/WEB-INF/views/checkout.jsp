<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Checkout</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
</head>
<body>
<jsp:include page="navbar.jsp"/>
<div class="col-sm-3 offset-sm-4">
    <form method="post" action="/book">
        <div class="card">
            <div class="card-header">
                Review your booking
            </div>
            <div class="card-body">

                <input type="hidden" name="command" value="book">
                <input type="hidden" name="apartId" value="${apart.id}">
                <input type="hidden" name="userId" value="${user.id}">
                <input type="hidden" name="inDate" value="${inDate}">
                <input type="hidden" name="outDate" value="${outDate}">



                <div class="row my-2">
                    <div class="col-sm-6">
                        <p class="card-text">Apartment:</p>
                    </div>
                    <div class="col">
                        <p class="card-text">${apart.type}</p>
                    </div>
                </div>
                <div class="row my-2">
                    <div class="col-sm-6">
                        <p class="card-text">Check-in date:</p>
                    </div>
                    <div class="col">
                        <p class="card-text">${inDate}</p>
                    </div>
                </div>
                <div class="row my-2">
                    <div class="col-sm-6">
                        <p class="card-text">Check-out date:</p>
                    </div>
                    <div class="col">
                        <p class="card-text">${outDate}</p>
                    </div>
                </div>

            </div>
        </div>

        <div class="card mt-3">
            <div class="card-header">
                Price summary
            </div>
            <div class="card-body">
                <div class="row my-2">
                    <div class="col-sm-6">
                        <p class="card-text">Apartment cost:</p>
                    </div>
                    <div class="col-sm-2">
                        <p class="card-text">${apart.price}</p>
                    </div>
                </div>
                <div class="row my-2">
                    <div class="col-sm-6">
                        <p class="card-text">Subtotal:</p>
                    </div>
                    <div class="col-sm-2 justify-content-end">
                        <p class="card-text">${checkOutData.subtotalPrice}</p>
                    </div>
                </div>
                <div class="row my-2">
                    <div class="col-sm-6">
                        <p class="card-text">Estimated taxes:</p>
                    </div>
                    <div class="col-sm-2">
                        <p class="card-text">${checkOutData.taxes}</p>
                    </div>
                </div>
                <div class="row my-2">
                    <div class="col-sm-6">
                        <p class="card-text"><b>Total price</b></p>
                    </div>
                    <div class="col-sm-2 justify-content-end">
                        <p class="card-text"><b>${checkOutData.totalPrice}</b></p>
                    </div>
                </div>

            </div>
        </div>
        <br>
        <div class="col text-center">
            <button type="submit" class="btn btn-primary mr-2 w-50 justify-content-center">Submit</button>
        </div>
    </form>
</div>
</body>
</html>
