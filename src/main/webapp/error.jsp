<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="locale" var="loc"/>
    <fmt:message bundle="${loc}" key="error.error" var="error"/>
    <fmt:message bundle="${loc}" key="error.error_message" var="error_message"/>
    <title>${error}</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/navbar.jsp"/>
<div align="center" class="alert alert-danger pt-2" role="alert">
    ${error_message}
</div>
</body>
</html>
