<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="prop.pagecontent"/>
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
<div id="promo">
    <div class="jumbotron">
        <h1><br><strong><fmt:message key="homepage.title"/></strong><br></h1>
        <p><br><fmt:message key="homepage.invitation"/><br><br></p>
        <p><a class="btn btn-primary text-uppercase" role="button"><fmt:message key="homepage.signup"/></a></p>
    </div>
</div>
<input type="hidden" id="showRegistrationMessage" value="${showRegistrationMessage}">
<input type="hidden" id="showConfirmRegistrationMessage" value="${showConfirmRegistrationMessage}">
<div role="dialog" tabindex="-1" class="modal fade" id="modalRegistration">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title text-primary">
                    <i class="fa fa-edit"></i>
                    <fmt:message key="registration.resultTitle"/>
                </h3>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">×</span></button>
            </div>
            <div class="modal-body">
                <div class="form-group" align="justify">
                    <p>
                        <c:if test="${not empty showRegistrationMessage}">
                            <fmt:message key="registration.resultMessage"/>
                        </c:if>
                        <c:if test="${not empty showConfirmRegistrationMessage and confirmRegistration == true}">
                            <fmt:message key="confirmRegistration.successMessage"/>
                        </c:if>
                        <c:if test="${not empty showConfirmRegistrationMessage and confirmRegistration == false}">
                            <fmt:message key="confirmRegistration.warnMessage"/>
                        </c:if>
                    </p>
                </div>
                <div class="form-group">
                    <button type="button" class="btn btn-primary btn-block" data-dismiss="modal">
                        <fmt:message key="homepage.close"/>
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="container site-section" id="welcome">
    <h1><fmt:message key="homepage.welcome"/></h1>
    <p><fmt:message key="homepage.description"/></p>
</div>
<c:import url="${pageContext.request.contextPath}/jsp/common/workouts.jsp"/>
<div class="container site-section" id="hall">
    <h2><fmt:message key="homepage.halls"/></h2>
    <p><fmt:message key="homepage.hall_description"/></p>
    <div class="row justify-content-center" id="halls">
        <div class="col-6"><img id="hall1" src="${pageContext.request.contextPath}/img/big_hall.jpg"></div>
        <div class="col-6"><img id="hall2" src="${pageContext.request.contextPath}/img/small_hall.jpg"></div>
    </div>
</div>
<c:import url="${pageContext.request.contextPath}/jsp/common/footer.jsp"/>

<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/show-modal.js"></script>

</body>

</html>