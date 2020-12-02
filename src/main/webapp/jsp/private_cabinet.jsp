<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="prop.pagecontent"/>
<%@ taglib prefix="ctg" uri="customtags" %>

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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/schedule-styles.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Team-Grid.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Footer-Clean.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>

<body>
<c:import url="${pageContext.request.contextPath}/jsp/common/header.jsp"/>
<div class="team-grid">
    <div class="container">
        <div class="row people">
            <div class="col-md-4 col-lg-3 item">
                <div>
                    <c:choose>
                        <c:when test="${not empty user.photo}">
                            <div>
                                <img width="220px" height="300px" src="data:image/jpeg;base64,${user.photo}"/>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="photoBorder">
                                <img width="220px" height="300px" src="/img/1.jpg"/>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
                <br/>
                <button id="addPhoto" class="client_button" type="button">
                    <fmt:message key="account.buttonPhoto"/>
                </button>
                <button class="client_button" type="button" id="changePrivateInfo">
                    <fmt:message key="account.buttonChangePrivateInfo"/>
                </button>
                <button class="client_button" type="button" data-toggle="modal" data-target="#modalChangePassword">
                    <fmt:message key="account.buttonChangePassword"/>
                </button>
            </div>
            <div class="col-12 col-md-6" id="message">
                <form class="custom-form" method="POST" id="updateUserInfo"
                      action="${pageContext.request.contextPath}/controller">
                    <input type="hidden" name="commandName" previousValue="update_user_info"
                           value="update_user_info"/>
                    <div class="form-group">
                        <label for="from-login">
                            <fmt:message key="header.login"/>
                        </label>
                        <span class="required-input">*</span>
                        <div class="input-group">
                            <div class="input-group-prepend">
                            <span class="input-group-text">
                                <i class="fa fa-user-o"></i>
                            </span>
                            </div>
                            <input class="form-control" type="text" id="from-login" name="login"
                                   pattern="[a-zA-Z][a-zA-Z_0-9-.]{4,20}"
                                   oninvalid="this.setCustomValidity('<fmt:message
                                           key="registration.loginDescription"/>')"
                                   onchange="this.setCustomValidity('')"
                                   previousValue="${sessionScope.user.login}"
                                   required="" value="${sessionScope.user.login}" disabled
                                   placeholder= <fmt:message key="header.login"/>>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="from-surname">
                            <fmt:message key="registration.surname"/>
                        </label>
                        <span class="required-input">*</span>
                        <div class="input-group">
                            <div class="input-group-prepend">
                            <span class="input-group-text">
                            <i class="fa fa-user-o"></i>
                        </span>
                            </div>
                            <input class="form-control" type="text" id="from-surname"
                                   pattern="[а-яА-Яa-zA-Z-]{2,50}" required=""
                                   previousValue="${sessionScope.user.surname}"
                                   oninvalid="this.setCustomValidity('<fmt:message key="registration.warnMessage"/>')"
                                   onchange="this.setCustomValidity('')"
                                   name="surname" value="${sessionScope.user.surname}" disabled
                                   placeholder=<fmt:message key="registration.surname"/>>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="from-name">
                            <fmt:message key="registration.name"/>
                        </label>
                        <span class="required-input">*</span>
                        <div class="input-group">
                            <div class="input-group-prepend">
                            <span class="input-group-text">
                                <i class="fa fa-user-o"></i>
                            </span>
                            </div>
                            <input class="form-control" type="text" id="from-name" name="name"
                                   pattern="[а-яА-Яa-zA-Z]{2,20}" required=""
                                   oninvalid="this.setCustomValidity('<fmt:message key="registration.warnMessage"/>')"
                                   onchange="this.setCustomValidity('')"
                                   previousValue="${sessionScope.user.name}"
                                   value="${sessionScope.user.name}" disabled
                                   placeholder=<fmt:message key="registration.name"/>></div>
                    </div>
                    <div class="form-group">
                        <label for="from-email">
                            <fmt:message key="registration.email"/>
                        </label>
                        <span class="required-input">*</span>
                        <div class="input-group">
                            <div class="input-group-prepend">
                            <span class="input-group-text">
                                <i class="fa fa-envelope-o"></i>
                            </span>
                            </div>
                            <input class="form-control" type="email" id="from-email"
                                   oninvalid="this.setCustomValidity('<fmt:message key="registration.warnMessage"/>')"
                                   onchange="this.setCustomValidity('')"
                                   previousValue="${sessionScope.user.email}"
                                   name="email" required="" value="${sessionScope.user.email}" disabled
                                   placeholder="<fmt:message key="registration.email"/>">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12 col-sm-6 col-md-12 col-lg-6">
                            <div class="form-group">
                                <label for="phone">
                                    <fmt:message key="registration.phone"/>
                                </label>
                                <span class="required-input">*</span>
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                    <span class="input-group-text">
                                        <i class="fa fa-phone"></i>
                                    </span>
                                    </div>
                                    <input class="form-control" id="phone" name="phone"
                                           previousValue="${sessionScope.user.phoneNumber}"
                                           required="" value="${sessionScope.user.phoneNumber}" disabled
                                           placeholder=<fmt:message key="registration.phone"/>></div>
                            </div>
                        </div>
                        <div class="col">
                            <input type="hidden" id="localeCalendar" value="${sessionScope.locale}"
                                   previousValue="${sessionScope.locale}">
                            <div class="form-group">
                                <label for="birthday">
                                    <fmt:message key="registration.birthday"/>
                                </label>
                                <span class="required-input">*</span>
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                    <span class="input-group-text">
                                        <i class="fa fa-birthday-cake"></i>
                                    </span>
                                    </div>
                                    <fmt:parseDate value="${sessionScope.user.birthday}" type="date"
                                                   var="birthday" pattern="yyyy-MM-dd"/>
                                    <c:choose>
                                        <c:when test="${sessionScope.locale =='ru_RU'}">
                                            <input class="form-control" id="birthday" name="birthday" disabled
                                                   previousValue=
                                                           "<fmt:formatDate value="${birthday}" pattern="dd.MM.yyyy"/>"
                                                   required="" data-provide="datepicker"
                                                   pattern="[0-9]{2}.[0-9]{2}.[0-9]{4}"
                                                   oninvalid="this.setCustomValidity('<fmt:message
                                                           key="registration.warnMessage"/>')"
                                                   onchange="this.setCustomValidity('')"
                                                   value="<fmt:formatDate value="${birthday}" pattern="dd.MM.yyyy"/>"
                                                   placeholder="<fmt:message key="registration.birthday"/>">
                                        </c:when>
                                        <c:otherwise>
                                            <input class="form-control" id="birthday" name="birthday" disabled
                                                   previousValue=
                                                           "<fmt:formatDate value="${birthday}" pattern="MM/dd/yyyy"/>"
                                                   required="" data-provide="datepicker"
                                                   pattern="[0-9]{2}/[0-9]{2}/[0-9]{4}"
                                                   oninvalid="this.setCustomValidity('<fmt:message
                                                           key="registration.warnMessage"/>')"
                                                   onchange="this.setCustomValidity('')"
                                                   value="<fmt:formatDate value="${birthday}" pattern="MM/dd/yyyy"/>"
                                                   placeholder="<fmt:message key="registration.birthday"/>">
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                        </div>
                    </div>
                    <c:if test="${sessionScope.user.role!='CLIENT'}">
                        <div class="row">
                            <div class="col-12 col-sm-6 col-md-12 col-lg-6">
                                <div class="form-group">
                                    <label for="workExperience">
                                        <fmt:message key="account.workExperience"/>
                                    </label>
                                    <span class="required-input">*</span>
                                    <div class="input-group">
                                        <div class="input-group-prepend">
                                    <span class="input-group-text">
                                        <i class="fa fa-signal"></i>
                                    </span>
                                        </div>
                                        <input class="form-control" id="workExperience" name="workExperience"
                                               pattern="\d{1,2}"
                                               previousValue="${sessionScope.user.workExperience}"
                                               required="" value="${sessionScope.user.workExperience}" disabled
                                               placeholder=<fmt:message key="account.workExperience"/>></div>
                                </div>
                            </div>
                            <div class="col">
                                <div class="form-group">
                                    <label for="birthday">
                                        <fmt:message key="account.startWorkingDate"/>
                                    </label>
                                    <div class="input-group">
                                        <div class="input-group-prepend">
                                    <span class="input-group-text">
                                        <i class="fa fa-calendar"></i>
                                    </span>
                                        </div>
                                        <fmt:parseDate value="${sessionScope.user.startWorkingDate}" type="date"
                                                       pattern="yyyy-MM-dd"
                                                       var="startWorkingDate"/>
                                        <c:choose>
                                            <c:when test="${sessionScope.locale =='ru_RU'}">
                                                <input class="form-control" name="startWorkingDate" disabled
                                                       previousValue=
                                                           <fmt:formatDate value="${startWorkingDate}"
                                                                           pattern="dd.MM.yyyy"/>
                                                               required="" data-provide="datepicker" value=
                                                    <fmt:formatDate value="${startWorkingDate}" pattern="dd.MM.yyyy"/>>
                                            </c:when>
                                            <c:otherwise>
                                                <input class="form-control" name="startWorkingDate" disabled
                                                       previousValue=
                                                           <fmt:formatDate value="${startWorkingDate}"
                                                                           pattern="MM/dd/yyyy"/>
                                                               required="" data-provide="datepicker" value=
                                                    <fmt:formatDate value="${startWorkingDate}" pattern="MM/dd/yyyy"/>>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <textarea id="previousDescription" hidden>${sessionScope.user.description}</textarea>
                        <div class="row">
                            <div class="col-12">
                                <div class="form-group">
                                    <label for="staffDescription">
                                        <fmt:message key="account.aboutYourself"/>
                                    </label>
                                    <div class="input-group" style="text-align: justify">
                                        <textarea class="form-control" id="staffDescription" name="staffDescription"
                                                  onkeyup="this.value = this.value.replace(/[<>]/g, '')"
                                                  disabled maxlength="2000">${sessionScope.user.description}</textarea>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:if>
                    <button id="saveChange" class="client_button" type="submit" hidden>
                        <fmt:message key="account.buttonSaveChanges"/>
                    </button>
                    <button id="cancel" class="client_button" type="button" hidden>
                        <fmt:message key="account.buttonCancel"/>
                    </button>
                    <hr class="d-flex d-md-none">
                    <input type="hidden" id="editInfo" value="${editInfo}" previousValue="${editInfo}">
                </form>
            </div>
        </div>
        <c:if test="${sessionScope.user.role=='CLIENT'}">
            <div class="row client_membership">
                <div class="col client_info">
                    <h2 class="h4">
                        <i class="fa fa-location-arrow"></i>
                        <fmt:message key="account.titleMembership"/>
                    </h2>
                    <c:choose>
                        <c:when test="${empty clientMemberships}">
                            <fmt:message key="account.clientMembershipsAbsent"/>
                        </c:when>
                        <c:otherwise>
                            <div class="row cl">
                                <div class="col-3 membership">
                                    <div class="row">
                                        <div class="col client_membership">
                                            <h5 class="text-center client_membership">
                                                <fmt:message key="account.titleWorkoutsType"/>
                                            </h5>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <c:forEach var="membership" items="${clientMemberships}">
                                            <div class="col client_membership">
                                                <p>
                                                        ${membership.typeByNumberPeople}
                                                </p>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </div>
                                <div class="col-3 membership">
                                    <div class="row">
                                        <div class="col client_membership">
                                            <h5 class="text-center client_membership">
                                                <fmt:message key="account.titleWorkoutsTime"/>
                                            </h5>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <c:forEach var="membership" items="${clientMemberships}">
                                            <div class="col client_membership">
                                                <p>
                                                        ${membership.periodTime}
                                                </p>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </div>
                                <div class="col-3 membership">
                                    <div class="row">
                                        <div class="col client_membership">
                                            <h5 class="text-center client_membership">
                                                <fmt:message key="account.titleWorkoutsRemainder"/>
                                            </h5>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <c:forEach var="membership" items="${clientMemberships}">
                                            <div class="col client_membership">
                                                <p style="color:darkred">
                                                        ${membership.remainingClasses}
                                                </p>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </div>
                                <div class="col-3 membership">
                                    <div class="row">
                                        <div class="col client_membership">
                                            <h5 class="text-center client_membership">
                                                <fmt:message key="account.titleStartDate"/>
                                            </h5>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <c:forEach var="membership" items="${clientMemberships}">
                                            <div class="col client_membership">
                                                <p>
                                                    <fmt:parseDate value="${membership.openDate}" type="date"
                                                                   pattern="yyyy-MM-dd"
                                                                   var="startDate"/>
                                                    <fmt:formatDate value="${startDate}" dateStyle="short"/>
                                                </p>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </div>
                                <div class="col-3 membership">
                                    <div class="row">
                                        <div class="col client_membership">
                                            <h5 class="text-center client_membership">
                                                <fmt:message key="account.titleEndDate"/>
                                            </h5>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <c:forEach var="membership" items="${clientMemberships}">
                                            <div class="col client_membership">
                                                <p>
                                                    <fmt:parseDate value="${membership.closeDate}" type="date"
                                                                   pattern="yyyy-MM-dd"
                                                                   var="endDate"/>
                                                    <fmt:formatDate value="${endDate}" dateStyle="short"/>
                                                </p>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </c:if>
        <c:if test="${sessionScope.user.role!='ADMINISTRATOR'}">
            <div class="row client_membership">
                <div class="col client_info">
                    <h2 class="h4">
                        <i class="fa fa-calendar"></i>
                        <fmt:message key="account.titleOwnWorkouts"/>
                    </h2>
                </div>
            </div>
            <div class="features-boxed client_membership" id="client_schedule">
                <c:choose>
                    <c:when test="${empty schedule}">
                        <fmt:message key="account.scheduleAbsent"/>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="hall" items="${hallsName}">
                            <div class="container features-boxed">
                                <p class="h5">
                                    <fmt:message key="schedule.hall"/>: ${hall}
                                </p>
                                <div class="date_range">
                                    <div class="row justify-content-center features1">
                                        <c:forEach var="schedule" items="${schedule}">
                                            <c:if test="${schedule.nameHall == hall}">
                                                <div class="col-auto col-sm-6 col-md-5 col-lg-4 offset-xl-0 item1">
                                                    <form method="post"
                                                          action="${pageContext.request.contextPath}/controller">
                                                        <input type="hidden" name="commandName"
                                                               previousValue="unsubscribe_in_private_cabinet"
                                                               value="unsubscribe_in_private_cabinet"/>
                                                        <input type="hidden" name="idSchedule"
                                                               previousValue="${schedule.idSchedule}"
                                                               value="${schedule.idSchedule}"/>
                                                        <input type="hidden" name="subscribe"
                                                               previousValue="true" value="true"/>
                                                        <ctg:schedule-box nameWorkout="${schedule.workout.name}"
                                                                          nameInstructor="${schedule.nameInstructor}"
                                                                          surnameInstructor="${schedule.surnameInstructor}"
                                                                          date="${schedule.startDate}"
                                                                          time="${schedule.startTime}"
                                                                          capacity="${schedule.capacity}"
                                                                          freeCapacity="${schedule.freeCapacity}"
                                                                          subscribed="true"/>
                                                    </form>
                                                </div>
                                            </c:if>
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </div>
        </c:if>
    </div>
</div>
<c:import url="${pageContext.request.contextPath}/jsp/common/add_photo.jsp"/>
<c:import url="${pageContext.request.contextPath}/jsp/common/change_password.jsp"/>
<c:import url="${pageContext.request.contextPath}/jsp/common/footer.jsp"/>

<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/private_cabinet.js"></script>
<script src="${pageContext.request.contextPath}/js/from-and-to-calendar.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.maskedinput.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/show-modal.js"></script>
</body>

</html>