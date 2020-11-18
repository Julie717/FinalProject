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
    <h1><fmt:message key="aboutUs.title"/></h1>
    <div class="row">
        <div class="col-5" style="padding-left:16%;margin-right: 14%;margin-bottom: 20px">
            <img src="${pageContext.request.contextPath}/img/about_us.jpg" width="450px">
        </div>
        <div class="col-5" style="max-width: 350px;display: inline-block">
            <p align="justify" style="text-indent: 30px"><fmt:message key="aboutUs.paragraph1"/></p>
        </div>
    </div>
    <div class="row">
        <p align="justify" style="text-indent: 30px"><fmt:message key="aboutUs.paragraph2"/></p>
        <p align="justify" style="text-indent: 30px">
            <fmt:message key="aboutUs.paragraph3"/>
            <strong><a style="color: black" title="<fmt:message key="aboutUs.link1"/>"
                       href="${pageContext.request.contextPath}controller?commandName=membership_page"
                       target="_blank"><fmt:message key="aboutUs.link1"/></a></strong>
        </p>
        <p align="justify" style="text-indent: 30px"><fmt:message key="aboutUs.paragraph4"/>
            <strong><a style="color: black" title="<fmt:message key="aboutUs.link2"/>"
                       href="${pageContext.request.contextPath}controller?commandName=contacts_page"
                       target="_blank"><fmt:message key="aboutUs.link2"/></a></strong></p>
        <p align="justify" style="text-indent: 30px"><fmt:message key="aboutUs.paragraph5"/></p>
        <p align="justify" style="text-indent: 30px"><fmt:message key="aboutUs.paragraph6"/></p>
    </div>
</div>
<c:import url="${pageContext.request.contextPath}/jsp/common/footer.jsp"/>

<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/show-modal.js"></script>
</body>
</html>