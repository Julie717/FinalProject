<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="prop.pagecontent"/>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/schedule-styles.css">
</head>
<body>
<nav class="navbar navbar-light navbar-expand-md sticky-top bg-white text-left">
    <div class="container">
        <c:if test="${sessionScope.user.role=='ADMINISTRATOR'}">
            <li class="nav-item dropdown" style="list-style-type: none">
                <a class="dropdown-toggle nav-link" data-toggle="dropdown" aria-expanded="false">
                    <i class="fa fa-align-justify" style="color:green;font-size:20px"></i>
                </a>
                <div class="dropdown-menu" role="menu">
                    <a class="dropdown-item" role="presentation"
                       href="${pageContext.request.contextPath}controller?commandName=add_schedule_page">
                        <fmt:message key="header.adminAddWorkoutInSchedule"/>
                    </a>
                    <a class="dropdown-item" role="presentation"
                       href="${pageContext.request.contextPath}/controller?commandName=all_user_page">
                        <fmt:message key="header.adminChangeUserRole"/>
                    </a>
                </div>
            </li>
        </c:if>
        <a class="navbar-brand" href="${pageContext.request.contextPath}controller?commandName=home_page">
            <img id="logo" src="${pageContext.request.contextPath}/img/emblem.jpg">
            <strong><fmt:message key="homepage.club_name"/></strong>
        </a>
        <button data-toggle="collapse" class="navbar-toggler" data-target="#navcol-1">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navcol-1">
            <ul class="nav navbar-nav ml-auto">
                <li class="nav-item" role="presentation">
                    <a class="nav-link active" href="#" id="ggg">
                        <fmt:message key="header.about_us"/>
                    </a>
                </li>
                <li class="nav-item" role="presentation">
                    <a class="nav-link"
                       href="${pageContext.request.contextPath}controller?commandName=schedule_page"><fmt:message
                            key="header.schedule"/></a>
                </li>
                <li class="nav-item" role="presentation">
                    <a class="nav-link" href="${pageContext.request.contextPath}controller?commandName=membership_page"><fmt:message key="header.price"/></a>
                </li>
                <li class="nav-item" role="presentation">
                    <a class="nav-link"
                       href="${pageContext.request.contextPath}controller?commandName=instructors_page"><fmt:message
                            key="header.instructors"/></a>
                </li>
                <li class="nav-item" role="presentation">
                    <a class="nav-link" href="#"><fmt:message key="header.contacts"/></a>
                </li>
                <li class="nav-item" role="presentation" id="language">
                    <c:choose>
                        <c:when test="${sessionScope.locale == 'en_US'}">
                            <a class="nav-link"
                               href="${pageContext.request.contextPath}controller?commandName=change_locale&locale=ru_RU">
                                <fmt:message key="header.language"/>
                            </a>
                        </c:when>
                        <c:otherwise>
                            <a class="nav-link"
                               href="${pageContext.request.contextPath}controller?commandName=change_locale&locale=en_US"/>
                            <fmt:message key="header.language"/>
                            </a>
                        </c:otherwise>
                    </c:choose>
                </li>
                <c:choose>
                    <c:when test="${not empty sessionScope.user}">
                        <li class="nav-item dropdown enter" id="enter1">
                            <a class="dropdown-toggle nav-link" data-toggle="dropdown" aria-expanded="false">
                                <fmt:message key="header.privateCabinet"/>
                            </a>
                            <div class="dropdown-menu" role="menu">
                                <a class="dropdown-item" role="presentation"
                                   href="${pageContext.request.contextPath}controller?commandName=private_cabinet_page">
                                    <fmt:message key="header.privateCabinet"/>
                                </a>
                                <a class="dropdown-item" role="presentation"
                                   href="${pageContext.request.contextPath}controller?commandName=logout">
                                    <fmt:message key="header.logout"/>
                                </a>
                            </div>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item dropdown" id="enter2">
                            <a class="dropdown-toggle nav-link" data-toggle="dropdown" aria-expanded="false">
                                <fmt:message key="header.enter"/>
                            </a>
                            <div class="dropdown-menu" role="menu">
                                <a class="dropdown-item" role="presentation" data-toggle="modal"
                                   data-target="#modalLogin">
                                    <fmt:message key="header.enter"/>
                                </a>
                                <a class="dropdown-item" role="presentation"
                                   href="${pageContext.request.contextPath}controller?commandName=registration_page">
                                    <fmt:message key="header.registration"/>
                                </a>
                            </div>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>
</nav>
<input type="hidden" id="showModal" value="${showModal}"/>
<input type="hidden" id="incorrectLoginOrPassword" value="${incorrectLoginOrPassword}"/>
<input type="hidden" id="loginWithoutConfirm" value="${loginWithoutConfirm}"/>
<div role="dialog" tabindex="-1" class="modal fade" id="modalLogin">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title text-primary">
                    <fmt:message key="header.enterTitle"/>
                </h3>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">×</span></button>
            </div>
            <div class="modal-body">
                <form name="loginForm" id="loginForm" class="p-4" method="POST"
                      action="${pageContext.request.contextPath}/controller">
                    <input type="hidden" name="commandName" value="login"/>
                    <div class="form-group">
                        <input type="login" class="form-control" name="login" id="login"
                               placeholder=
                               <fmt:message key="header.login"/> value=""/>
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" name="password" id="password"
                               placeholder=
                               <fmt:message key="header.password"/> value=""/>
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary btn-block" id="ddd">
                            <fmt:message key="header.enter"/>
                        </button>
                    </div>
                    <div class="text-error" id="incorrectData" hidden style="color: darkred">
                        <fmt:message key="header.incorrectLoginOrPassword"/>
                    </div>
                    <div class="text-error" id="incorrectLoginWithoutConfirm" hidden style="color: darkred">
                        <fmt:message key="registration.resultMessage"/>
                    </div>
                    <a class="register"
                       href="${pageContext.request.contextPath}controller?commandName=registration_page">
                        <fmt:message key="header.registration"/>
                    </a>
                </form>
            </div>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/js/protectionF5.js"></script>
</body>