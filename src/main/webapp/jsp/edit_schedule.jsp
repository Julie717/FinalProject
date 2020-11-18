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
        <div class="intro"></div>
        <div class="row">
            <div class="col-md-4 col-lg-3 item3">
                <form method="post" action="${pageContext.request.contextPath}/controller">
                    <input type="hidden" name="commandName"
                           value="delete_workout_from_schedule"/>
                    <button class="btn btn-primary p-2 mr-2 mb-2 edit_schedule" type="submit">
                        <fmt:message key="scheduleEdit.buttonCancelWorkout"/>
                    </button>
                </form>
                <form method="post"
                      action="${pageContext.request.contextPath}/controller">
                    <input type="hidden" name="commandName"
                           value="edit_schedule"/>
                    <input type="hidden" name="numberWeek"
                           value="${sessionScope.numberWeek}"/>
                    <input type="hidden" name="idWorkout" id="idWorkout" value=""/>
                    <input type="hidden" name="idHall" id="idHall" value=""/>
                    <input type="hidden" name="idInstructor" id="idInstructor" value=""/>
                    <input type="hidden" id="startDate" name="startDate" value=""/>
                    <input type="hidden" id="startTime" name="startTime" value=""/>
                    <input type="hidden" id="duration" name="duration" value=""/>
                    <input type="hidden" id="capacity" name="capacity" value=""/>
                    <button type="submit" class="btn btn-primary p-2 mr-2 mb-2 edit_schedule"
                            style="background-color: purple" id="saveChanges">
                        <em class="fa fa-save"></em>
                        <fmt:message key="scheduleEdit.buttonBackAndSave"/>
                    </button>
                </form>
                <form method="post"
                      action="${pageContext.request.contextPath}/controller">
                    <input type="hidden" name="commandName"
                           value="schedule_page"/>
                    <input type="hidden" name="numberWeek"
                           value="${sessionScope.numberWeek}"/>
                    <button type="submit" class="btn btn-primary p-2 mr-2 mb-2 edit_schedule"
                            style="background-color: purple">
                        <em class="fa fa-backward"></em>
                        <fmt:message key="scheduleEdit.buttonBack"/>
                    </button>
                </form>
            </div>
            <div class="col-12 col-md-6" id="message">
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
                        <select class="form-control" id="from-workout-name">
                            <c:forEach var="workout" items="${workoutsName.entrySet()}">
                                <c:choose>
                                    <c:when test="${workout.getValue()==schedule.workout.name}">
                                        <option value="${workout.getKey()}" selected>${workout.getValue()}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${workout.getKey()}">${workout.getValue()}</option>
                                    </c:otherwise>
                                </c:choose>
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
                                <c:choose>
                                    <c:when test="${sessionScope.locale =='ru_RU'}">
                                        <input class="form-control" id="from-workout-date"
                                               name="startDate" required="" data-provide="datepicker"
                                               value="<fmt:formatDate value="${startDate}"  pattern="dd.MM.yyyy"/>"
                                               placeholder="<fmt:message key="scheduleEdit.workoutDate"/>">
                                    </c:when>
                                    <c:otherwise>
                                        <input class="form-control" id="from-workout-date"
                                               name="startDate" required="" data-provide="datepicker"
                                               value="<fmt:formatDate value="${startDate}"  pattern="MM/dd/yyyy"/>"
                                               placeholder="<fmt:message key="scheduleEdit.workoutDate"/>">
                                    </c:otherwise>
                                </c:choose>
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
                                       value="${schedule.startTime}"
                                       placeholder="<fmt:message key="scheduleEdit.workoutTime"/>">
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
                                    <c:choose>
                                        <c:when test="${schedule.duration==55}">
                                            <option value="55" selected>55</option>
                                            <option value="85">85</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="55">55</option>
                                            <option value="85" selected>85</option>
                                        </c:otherwise>
                                    </c:choose>
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
                                        <c:choose>
                                            <c:when test="${schedule.capacity==i}">
                                                <option value="${i}" selected>${i}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${i}">${i}</option>
                                            </c:otherwise>
                                        </c:choose>
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
                        <select class="form-control" id="from-instructor-name">
                            <c:forEach var="instructor" items="${instructors.entrySet()}">
                                <c:choose>
                                    <c:when test="${instructor.getValue()==schedule.surnameInstructor.concat(' ').concat(schedule.nameInstructor)}">
                                        <option value="${instructor.getKey()}"
                                                selected>${instructor.getValue()}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${instructor.getKey()}">${instructor.getValue()}</option>
                                    </c:otherwise>
                                </c:choose>
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
                        <select class="form-control" id="from-hall">
                            <c:forEach var="hall" items="${hallsName.entrySet()}">
                                <c:choose>
                                    <c:when test="${hall.getValue()==schedule.nameHall}">
                                        <option value="${hall.getKey()}" selected>${hall.getValue()}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${hall.getKey()}"> ${hall.getValue()}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <hr class=" d-flex d-md-none">
            </div>
        </div>
        <div class="row">
            <link href="${pageContext.request.contextPath}/fonts/font-awesome.min.css"
                  rel='stylesheet' type='text/css'>
            <div class="container">
                <div class="row">
                    <h4 style="margin-left:100px; margin-top:50px">
                        <fmt:message key="scheduleEdit.subscribedClients"/>
                    </h4>
                    <div class="col-md-10 col-md-offset-1">
                        <div class="panel panel-default panel-table">
                            <div class="panel-body">
                                <table class="table table-striped table-bordered table-list">
                                    <thead>
                                    <tr>
                                        <th>
                                            <fmt:message key="scheduleEdit.tablePresent"/>
                                        </th>
                                        <th>
                                            <fmt:message key="scheduleEdit.tableLogin"/>
                                        </th>
                                        <th>
                                            <fmt:message key="scheduleEdit.tableSurnameName"/>
                                        </th>
                                        <th>
                                            <fmt:message key="scheduleEdit.tablePhone"/>
                                        </th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="client" items="${subscribedUsers}">
                                        <tr>
                                            <td align="center">
                                                <c:choose>
                                                    <c:when test="${empty client.idClientMembership || client.idClientMembership==0}">
                                                        <a class="btn btn-success"
                                                           href="${pageContext.request.contextPath}/controller?commandName=check_presence_page&id_client=${client.idUser}&id_client_membership=${client.idClientMembership}">
                                                            <em class="fa fa-plus"></em>
                                                        </a>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <a class="btn btn-warning"
                                                           href="${pageContext.request.contextPath}/controller?commandName=check_presence_page&id_client=${client.idUser}&id_client_membership=${client.idClientMembership}">
                                                            <em class="fa fa-minus"></em>
                                                        </a>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td>
                                                    ${client.login}
                                            </td>
                                            <td>
                                                    ${client.surname.concat(' ').concat(client.name)}
                                            </td>
                                            <td>
                                                    ${client.phoneNumber}
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
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
                        <fmt:message key="schedule.deleteNotSuccess"/>
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
<script src="${pageContext.request.contextPath}/js/edit_schedule.js"></script>
<script src="${pageContext.request.contextPath}/js/show-modal.js"></script>
</body>
</html>