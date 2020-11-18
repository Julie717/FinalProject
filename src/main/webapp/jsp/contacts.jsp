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
    <div class="row">
        <div class="col-sm-6">
            <h2 class="h4">
                <i class="fa fa-location-arrow"></i>
                <fmt:message key="contacts.location"/>
            </h2>
            <img class="img-fluid" width="400px" src="${pageContext.request.contextPath}/img/map.jpg">
        </div>
        <div class="col-sm-6" style="text-align: left">
            <h2 class="h4">
                <i class="fa fa-location-arrow"></i>
                <fmt:message key="contacts.ourAddress"/>
            </h2>
            <div style="text-align: left">
                <p>
                    <fmt:message key="contacts.address"/>
                </p>
                <p>
                    <fmt:message key="contacts.floor"/>
                </p>
                <p>
                    <fmt:message key="contacts.phone"/>
                </p>
                <p>
                    <fmt:message key="contacts.email"/>
                </p>
            </div>
        </div>
    </div>
</div>
<c:import url="${pageContext.request.contextPath}/jsp/common/footer.jsp"/>

<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/show-modal.js"></script>
</body>
</html>