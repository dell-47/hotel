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
        <input type="hidden" name="invoiceId" value="${invoice.id}">

        <div class="card">
            <div class="card-header">
                ${user.firstName} ${user.lastName}
            </div>
            <div class="card-body">
                <h6 class="card-text">Total price</h6>
                <p class="card-text">${invoice.amount}</p>
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
