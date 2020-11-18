<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="prop.pagecontent"/>
<%@ taglib prefix="ctg" uri="customtags" %>

<html>
<head>
    <title><fmt:message key="homepage.club_name"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/fonts/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/fonts/ionicons.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/schedule-styles.css">
</head>

<body>
<input type="hidden" id="lastDate" value="${lastDate}">
<c:import url="${pageContext.request.contextPath}/jsp/common/header.jsp"/>
<div class="features-boxed">
    <div class="intro">
        <h2 class="text-center dates">
            <fmt:message key="schedule.title"/>
        </h2>
    </div>
    <div class="date_range">
        <form method="post" action="${pageContext.request.contextPath}/controller">
            <input type="hidden" name="commandName" value="schedule_page">
            <input type="hidden" name="numberWeek" value="${numberWeek}" id="numberWeek"/>
            <button class="btn btn-primary arrow" type="submit" id="previousWeek">
                <i class="fa fa-chevron-left"></i>
            </button>
            <c:if test="${not empty schedules}">
                <fmt:parseDate value="${schedules.get(0).startDate}" type="date" pattern="yyyy-MM-dd"
                               var="startParsedDate"/>
                <fmt:parseDate value="${schedules.get(schedules.size()-1).startDate}" type="date"
                               pattern="yyyy-MM-dd"
                               var="endParsedDate"/>
                <label>
                    <fmt:formatDate value="${startParsedDate}" dateStyle="short"/>
                    - <fmt:formatDate value="${endParsedDate}" dateStyle="short"/>
                </label>
            </c:if>
            <button class="btn btn-primary arrow" type="submit" id="nextWeek">
                <i class="fa fa-chevron-right"></i>
            </button>
        </form>
    </div>
    <div class="container features-boxed">
        <p class="text-center hall">
            <c:if test="${empty schedules}">
                <fmt:message key="schedule.absent"/>
            </c:if>
        </p>
    </div>
    <input type="hidden" name="nextSchedule" value="${nextSchedule}" id="nextSchedule"/>
    <c:set var="showEmptyBox" value="true" scope="page"/>
    <c:forEach var="hall" items="${hallsDate.keySet()}">
        <div class="container features-boxed">
            <p class="text-center hall">
                <i class="fa fa-calendar"></i>
                <fmt:message key="schedule.hall"/>: ${hall}
            </p>
            <div class="date_range">
                <div class="row justify-content-center features1">
                    <c:forEach var="date" items="${hallsDate.get(hall)}">
                        <div class="col-auto col-sm-6 col-md-5 col-lg-4 offset-xl-0 item1">
                            <c:forEach var="time" items="${hallsTime.get(hall)}">
                                <c:forEach var="schedule" items="${schedules}">
                                    <c:if test="${schedule.startDate == date &&
                                            schedule.startTime == time &&
                                            schedule.nameHall == hall}">
                                        <form class="schedule_form" method="post"
                                              action="${pageContext.request.contextPath}/controller">
                                            <c:choose>
                                                <c:when test="${sessionScope.user.role=='CLIENT'}">
                                                    <input type="hidden" name="commandName"
                                                           value="subscribe_in_schedule"/>
                                                    <input type="hidden" name="subscribe"
                                                           value="${schedule.subscribed}"/>
                                                </c:when>
                                                <c:when test="${sessionScope.user.role=='ADMINISTRATOR'}">
                                                    <input type="hidden" name="commandName" value="edit_schedule_page"/>
                                                </c:when>
                                            </c:choose>
                                            <input type="hidden" name="idSchedule" value="${schedule.idSchedule}"/>
                                            <input type="hidden" name="numberWeek" value="${numberWeek}"
                                                   id="numberWeek"/>
                                            <c:choose>
                                                <c:when test="${sessionScope.user.role=='CLIENT'}">
                                                    <ctg:schedule-box nameWorkout="${schedule.workout.name}"
                                                                      nameInstructor="${schedule.nameInstructor}"
                                                                      surnameInstructor="${schedule.surnameInstructor}"
                                                                      date="${schedule.startDate}"
                                                                      time="${schedule.startTime}"
                                                                      capacity="${schedule.capacity}"
                                                                      freeCapacity="${schedule.freeCapacity}"
                                                                      subscribed="${schedule.subscribed}"/>
                                                </c:when>
                                                <c:otherwise>
                                                    <ctg:schedule-box nameWorkout="${schedule.workout.name}"
                                                                      nameInstructor="${schedule.nameInstructor}"
                                                                      surnameInstructor="${schedule.surnameInstructor}"
                                                                      date="${schedule.startDate}"
                                                                      time="${schedule.startTime}"
                                                                      capacity="${schedule.capacity}"
                                                                      freeCapacity="${schedule.freeCapacity}"/>
                                                </c:otherwise>
                                            </c:choose>
                                        </form>
                                        <c:set var="showEmptyBox" value="false"/>
                                    </c:if>
                                </c:forEach>
                                <c:if test="${showEmptyBox=='true'}">
                                    <ctg:schedule-box date="${date}"
                                                      time="${time}"/>
                                </c:if>
                                <c:set var="showEmptyBox" value="true"/>
                            </c:forEach>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </c:forEach>
</div>
<input id="showModalDeleteSchedule" type="hidden" value="${showModalDeleteSchedule}">
<div role="dialog" tabindex="-1" class="modal fade" id="modalDeleteSchedule">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">×</span></button>
            </div>
            <div class="modal-body">
                <div class="form-group" align="justify">
                    <p>
                        <c:choose>
                            <c:when test="${deleteMessage=='true'}">
                                <fmt:message key="schedule.deleteSuccess"/>
                            </c:when>
                            <c:when test="${editMessage=='true'}">
                                <fmt:message key="schedule.editSuccess"/>
                            </c:when>
                            <c:otherwise>
                                <fmt:message key="schedule.editNotSuccess"/>
                            </c:otherwise>
                        </c:choose>
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
<c:import url="${pageContext.request.contextPath}/jsp/common/footer.jsp"/>

<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/script.min.js"></script>
<script src="${pageContext.request.contextPath}/js/schedule_script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/show-modal.js"></script>
</body>
</html>