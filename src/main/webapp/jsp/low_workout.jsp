<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="prop.pagecontent"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title><fmt:message key="homepage.club_name"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/fonts/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/fonts/ionicons.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui.theme.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui.structure.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Footer-Clean.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>

<body>
<c:import url="${pageContext.request.contextPath}/jsp/common/header.jsp"/>
<div class="container site-section" id="welcome">
    <h1><fmt:message key="low_workout.title"/></h1>
    <div class="row">
        <div class="col-5" id="column_low_workout">
            <img src="${pageContext.request.contextPath}/img/low3.jpg" width="300px">
        </div>
        <div class="col">
            <h2>${workoutsLow.get(0).name}</h2>
            <p align="justify">${workoutsLow.get(0).description}</p>
        </div>
    </div>
    <div class="row">
        <div class="col" id="column_low_workout3">
            <h2>${workoutsLow.get(1).name}</h2>
            <p align="justify">${workoutsLow.get(1).description}</p>
        </div>
        <div class="col-5" id="column_low_workout2">
            <img src="${pageContext.request.contextPath}/img/low2.jpg" width="300px">
        </div>
    </div>
        <img align="center" src="${pageContext.request.contextPath}/img/low1.jpg" width="500px">
</div>
<c:import url="${pageContext.request.contextPath}/jsp/common/footer.jsp"/>

<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/show-modal.js"></script>
</body>
</html>