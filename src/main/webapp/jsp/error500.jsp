<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="prop.pagecontent"/>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title><fmt:message key="homepage.club_name"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Footer-Clean.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/error-styles.css">
</head>

<body>
<div id="appearance-error">
    <div class="jumbotron">
        <h1 id="error-title">
            <br><fmt:message key="error500.title"/></h1>
        <p id="error-name">
            <br><fmt:message key="error500.name"/><br>
            <br>
        </p>
        <p>
            <a class="btn btn-primary text-uppercase" role="button"
               href="${pageContext.request.contextPath}/controller?commandName=home_page">
                <fmt:message key="error.back"/>
            </a>
        </p>
    </div>
</div>
</body>

</html>
