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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Features-Boxed.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Footer-Clean.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/MUSA_panel-table-1.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/MUSA_panel-table.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Pretty-Registration-Form.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui.theme.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui.structure.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Team-Grid.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/button.css">
</head>

<body>
<c:import url="${pageContext.request.contextPath}/jsp/common/header.jsp"/>
<div class="team-grid" style="margin-top: 150px">
    <div class="container">
        <div class="row" style="width:100%">
            <form method="post" action="${pageContext.request.contextPath}/controller">
                <input type="hidden" name="commandName" value="add_schedule"/>
                <div class="col">
                    <div class="form-group">
                        <label for="from-workout-name">
                            <fmt:message key="scheduleEdit.workoutName"/>
                        </label>
                        <span class="required-input">*</span>
                        <div class="input-group">
                            <div class="input-group-prepend">
                            <span class="input-group-text">
                                <i class="fa fa-child"></i>
                            </span>
                            </div>
                            <select class="form-control" id="from-workout-name" name="idWorkout">
                                <c:forEach var="workout" items="${workoutsName.entrySet()}">
                                    <option value="${workout.getKey()}">${workout.getValue()}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12 col-sm-6 col-md-12 col-lg-6">
                            <div class="form-group">
                                <label for="from-workout-date">
                                    <fmt:message key="scheduleEdit.workoutDate"/>
                                </label>
                                <span class="required-input">*</span>
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                    <span class="input-group-text">
                                        <i class="fa fa-calendar"></i>
                                    </span>
                                    </div>
                                    <input type="hidden" id="localeCalendar" value="${sessionScope.locale}">
                                    <fmt:parseDate value="${schedule.startDate}" type="date"
                                                   pattern="yyyy-MM-dd"
                                                   var="startDate"/>
                                    <input class="form-control" id="from-workout-date"
                                           pattern="[0-9]{2}/[0-9]{2}/[0-9]{4}"
                                           name="startDate" required="" data-provide="datepicker"
                                           value="" placeholder="<fmt:message key="scheduleEdit.workoutDate"/>">
                                </div>
                            </div>
                        </div>
                        <div class="col">
                            <div class="form-group">
                                <label for="from-workout-time">
                                    <fmt:message key="scheduleEdit.workoutTime"/>
                                </label>
                                <span class="required-input">*</span>
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                    <span class="input-group-text">
                                        <i class="fa fa-clock-o"></i>
                                    </span>
                                    </div>
                                    <input class="form-control" type="time" id="from-workout-time"
                                           name="startTime" required="" min="08:00" max="22:00"
                                           value="" placeholder="<fmt:message key="scheduleEdit.workoutTime"/>">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12 col-sm-6 col-md-12 col-lg-6">
                            <div class="form-group">
                                <label for="from-workout-duration">
                                    <fmt:message key="scheduleEdit.workoutDuration"/>
                                </label>
                                <span class="required-input">*</span>
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                    <span class="input-group-text">
                                        <i class="fa fa-bell-o"></i>
                                    </span>
                                    </div>
                                    <select class="form-control" id="from-workout-duration" name="duration">
                                        <option value="55">55</option>
                                        <option value="85">85</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="col">
                            <div class="form-group">
                                <label for="from-workout-capacity">
                                    <fmt:message key="scheduleEdit.workoutCapacity"/>
                                </label>
                                <span class="required-input">*</span>
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                    <span class="input-group-text">
                                        <i class="fa fa-users"></i>
                                    </span>
                                    </div>
                                    <select class="form-control" id="from-workout-capacity" name="capacity">
                                        <c:forEach var="i" begin="1" end="20" step="1">
                                            <option value="${i}">${i}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="from-instructor-name">
                            <fmt:message key="scheduleEdit.workoutInstructor"/>
                        </label>
                        <span class="required-input">*</span>
                        <div class="input-group">
                            <div class="input-group-prepend">
                            <span class="input-group-text">
                                <i class="fa fa-user-circle"></i>
                            </span>
                            </div>
                            <select class="form-control" id="from-instructor-name" name="idInstructor">
                                <c:forEach var="instructor" items="${instructors.entrySet()}">
                                    <option value="${instructor.getKey()}">${instructor.getValue()}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="from-hall">
                            <fmt:message key="scheduleEdit.workoutHall"/>
                        </label>
                        <span class="required-input">*</span>
                        <div class="input-group">
                            <div class="input-group-prepend">
                            <span class="input-group-text">
                                <i class="fa fa-building-o"></i>
                            </span>
                            </div>
                            <select class="form-control" id="from-hall" name="idHall">
                                <c:forEach var="hall" items="${hallsName.entrySet()}">
                                    <option value="${hall.getKey()}"> ${hall.getValue()}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <button class="btn btn-primary p-2 mr-2 mb-2 edit_schedule" type="submit">
                        <fmt:message key="scheduleAdd.buttonSave"/>
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
<input id="showModalAddSchedule" type="hidden" value="${showModalAddSchedule}">
<div role="dialog" tabindex="-1" class="modal fade" id="modalAddSchedule">
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
                            <c:when test="${addMessage=='true'}">
                                <fmt:message key="schedule.addSuccess"/>
                            </c:when>
                            <c:otherwise>
                                <fmt:message key="schedule.addNotSuccess"/>
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
<script src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
<script src="${pageContext.request.contextPath}/js/from-and-to-calendar.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.maskedinput.js"></script>
<script src="${pageContext.request.contextPath}/js/add_schedule.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/show-modal.js"></script>
</body>
</html>
