<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="prop.pagecontent"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title><fmt:message key="homepage.club_name"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/fonts/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui.theme.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui.structure.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/fonts/ionicons.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Footer-Clean.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Pretty-Registration-Form.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
<input type="hidden" id="localeCalendar" value="${sessionScope.locale}">
<c:import url="${pageContext.request.contextPath}/jsp/common/header.jsp"/>
<div class="container site-section" id="registration">
    <div class="row register-form">
        <div class="col-md-8 offset-md-2" id="registrationForm">
            <form class="custom-form" name="homePage" method="POST"
                  action="${pageContext.request.contextPath}/controller">
                <input type="hidden" name="commandName" value="registration"/>
                <h1>
                    <fmt:message key="registration.title"/>
                </h1>
                <div class="form-row form-group">
                    <div class="col-sm-4 label-column">
                        <label class="col-form-label">
                            <fmt:message key="registration.surname"/>
                        </label>
                    </div>
                    <div class="col-sm-6 input-column">
                        <input class="form-control" type="text" required="" name="surname"
                               pattern="[а-яА-Яa-zA-Z-]{2,50}"
                               value="${userParameters.get('surname')}">
                        <div class="warnMessage">
                            <c:if test="${not empty userParameters and empty userParameters.get('surname')}">
                                <fmt:message key="registration.warnMessage"/>
                            </c:if>
                        </div>
                    </div>
                </div>
                <div class=" form-row form-group">
                    <div class="col-sm-4 label-column">
                        <label class="col-form-label">
                            <fmt:message key="registration.name"/>
                        </label>
                    </div>
                    <div class="col-sm-6 input-column">
                        <input class="form-control" type="text" required="" name="name"
                               pattern="[а-яА-Яa-zA-Z]{2,20}"
                               value="${userParameters.get('name')}">
                        <div class="warnMessage">
                            <c:if test="${not empty userParameters and empty userParameters.get('name')}">
                                <fmt:message key="registration.warnMessage"/>
                            </c:if>
                        </div>
                    </div>
                </div>
                <div class="form-row form-group">
                    <div class="col-sm-4 label-column">
                        <label class="col-form-label">
                            <fmt:message key="header.login"/>
                        </label>
                    </div>
                    <div class="col-sm-6 input-column">
                        <input class="form-control" type="text" required="" name="login"
                               pattern="[a-zA-Z][a-zA-Z_0-9-.]{4,20}"
                               value="${userParameters.get('login')}">
                        <div class="field-description">
                            <fmt:message key="registration.loginDescription"/>
                        </div>
                        <div class="warnMessage">
                            <c:choose>
                                <c:when test="${duplicateLogin}">
                                    <fmt:message key="registration.duplicateLoginMessage"/>
                                </c:when>
                                <c:otherwise>
                                    <c:if test="${not empty userParameters and empty userParameters.get('login')}">
                                        <fmt:message key="registration.warnMessage"/>
                                    </c:if>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>
                <div class="form-row form-group">
                    <div class="col-sm-4 label-column">
                        <label class="col-form-label">
                            <fmt:message key="header.password"/>
                        </label>
                    </div>
                    <div class="col-sm-6 input-column">
                        <input class="form-control" type="password" required="" name="password"
                               pattern="(?=.*\d)(?=.*[A-Z])[\w\p{P}]{8,}" value="${userParameters.get('password')}">
                        <div class="field-description">
                            <fmt:message key="registration.passwordDescription"/>
                        </div>
                        <div class="warnMessage">
                            <c:if test="${not empty userParameters and empty userParameters.get('password')}">
                                <fmt:message key="registration.warnMessage"/>
                            </c:if>
                        </div>
                    </div>
                </div>
                <div class="form-row form-group">
                    <div class="col-sm-4 label-column">
                        <label class="col-form-label">
                            <fmt:message key="registration.repeatedPassword"/>
                        </label>
                    </div>
                    <div class="col-sm-6 input-column">
                        <input class="form-control" type="password" required="" name="repeatedPassword"
                               value="${userParameters.get('repeatedPassword')}">
                        <div class="warnMessage">
                            <c:if test="${not empty userParameters and empty userParameters.get('repeatedPassword')}">
                                <fmt:message key="registration.warnMessage"/>
                            </c:if>
                        </div>
                    </div>
                </div>
                <div class="form-row form-group">
                    <div class="col-sm-4 label-column">
                        <label class="col-form-label">
                            <fmt:message key="registration.email"/>
                        </label>
                    </div>
                    <div class="col-sm-6 input-column">
                        <input class="form-control" type="email" required="" name="email"
                               value="${userParameters.get('email')}">
                        <div class="warnMessage">
                            <c:if test="${not empty userParameters and empty userParameters.get('email')}">
                                <fmt:message key="registration.warnMessage"/>
                            </c:if>
                        </div>
                    </div>
                </div>
                <div class="form-row form-group">
                    <div class="col-sm-4 label-column">
                        <label class="col-form-label">
                            <fmt:message key="registration.phone"/>
                        </label>
                    </div>
                    <div class="col-sm-6 input-column">
                        <input class="form-control" id="phone" required=""
                               name="phone" value="${userParameters.get('phone')}">
                        <div class="warnMessage">
                            <c:if test="${not empty userParameters and empty userParameters.get('phone')}">
                                <fmt:message key="registration.warnMessage"/>
                            </c:if>
                        </div>
                    </div>
                </div>
                <div class="form-row form-group">
                    <div class="col-sm-4 label-column">
                        <label class="col-form-label">
                            <fmt:message key="registration.birthday"/>
                        </label>
                    </div>
                    <div class="col-sm-6 input-column">
                        <input id="startDate" class="form-control" required=""
                               name="birthday" data-provide="datepicker"
                               value="${userParameters.get('birthday')}">
                        <div class="warnMessage">
                            <c:if test="${not empty userParameters and empty userParameters.get('birthday')}">
                                <fmt:message key="registration.warnMessage"/>
                            </c:if>
                        </div>
                    </div>
                </div>
                <button class="btn btn-light submit-button" type="submit" id="register">
                    <fmt:message key="header.registration"/>
                </button>
            </form>
        </div>
    </div>
</div>
<c:import url="${pageContext.request.contextPath}/jsp/common/footer.jsp"/>

<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
<script src="${pageContext.request.contextPath}/js/from-and-to-calendar.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.maskedinput.js"></script>
<script src="${pageContext.request.contextPath}/js/show-modal.js"></script>
</body>
</html>

