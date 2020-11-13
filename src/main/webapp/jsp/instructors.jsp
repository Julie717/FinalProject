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
    <h1><fmt:message key="header.instructors"/></h1>
    <c:forEach var="instructor" items="${instructors}">
    <div class="row" style="margin-bottom: 20px">
        <div class="col-5" id="instructorPhoto" >
            <c:if test="${not empty instructor.photo}">
            <img height="400px" style="margin-top: 10%" src="data:image/jpeg;base64,${instructor.photo}"/>
            </c:if>
        </div>
        <div class="col">
            <h2>${instructor.surname} ${instructor.name}</h2>
            <p align="justify">${instructor.description}</p>
        </div>
    </div>
    </c:forEach>
</div>
<c:import url="${pageContext.request.contextPath}/jsp/common/footer.jsp"/>

<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/show-modal.js"></script>
</body>
</html>