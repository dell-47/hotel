<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="locale" var="loc"/>
    <fmt:message bundle="${loc}" key="accessDenied.access_denied" var="access_denied"/>
    <title>${access_denied}</title>
</head>
<body>
<h1 align="center">${access_denied}</h1>
</body>
</html>
