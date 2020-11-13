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
<div class="team-grid">
    <div class="container">
        <div class="row" style="text-align: center">
            <input type="hidden" id="localeCalendar" value="${sessionScope.locale}"/>
            <h2>
                <fmt:message key="allUser.title"/>
            </h2>
            <div class="col-md-10 col-md-offset-1">
                <div class="panel panel-default panel-table">
                    <div class="panel-heading">
                        <div class="row">
                            <div class="col col-xs-6 text-right" style="margin-bottom: 30px;height: 30px">
                                <form method="post"
                                      action="${pageContext.request.contextPath}/controller">
                                    <input type="hidden" name="commandName" id="commandName"
                                           value="search_user"/>
                                    <input type="search" style="min-width: 300px;height: 100%; padding-top: 0"
                                           id="surnameSearch"
                                           name="surnameSearch"/>
                                    <button type="submit" class="btn btn-sm btn-primary btn-create"
                                            onclick="searchClick()"
                                            style="height: 100%;padding-top: 5px">
                                        <fmt:message key="allUser.buttonSearch"/>
                                    </button>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="panel-body">
                        <table class="table table-striped table-bordered table-list">
                            <thead>
                            <tr>
                                <th style="text-align:center"><em class="fa fa-cog"></em></th>
                                <th><fmt:message key="allUser.login"/></th>
                                <th><fmt:message key="allUser.surname"/></th>
                                <th><fmt:message key="allUser.name"/></th>
                                <th><fmt:message key="allUser.role"/></th>
                                <th><fmt:message key="allUser.status"/></th>
                                <th><fmt:message key="allUser.startWorkingDate"/></th>
                                <th><fmt:message key="allUser.endWorkingDate"/></th>
                            </tr>
                            </thead>
                            <form method="post" action="${pageContext.request.contextPath}/controller" id="userRoleForm">
                                <input type="hidden" name="commandName" value="change_user_role"/>
                                <input type="hidden" name="idUser" id="idUser" value=""/>
                                <input type="hidden" name="userRole" id="userRole" value=""/>
                                <input type="hidden" name="userStatus" id="userStatus" value=""/>
                                <input type="hidden" name="startWorkingDate" id="startWorkingDate" value=""/>
                                <input type="hidden" name="endWorkingDate" id="endWorkingDate" value=""/>
                                <input type="hidden" id="surnameSearch2" name="surnameSearch"/>
                                <tbody>
                                <c:forEach var="user" items="${users}">
                                    <tr>
                                        <td style="text-align:center">
                                            <button type="button" class="btn btn-warning" id="edit${user.idUser}"
                                                    onclick="editClick('${user.idUser}')">
                                                <em class="fa fa-pencil"
                                                    style="font-size: 20px"></em>
                                            </button>
                                            <button type="submit" class="btn btn-success" id="save${user.idUser}" hidden
                                                    onclick="saveClick('${user.idUser}')">
                                                <em class="fa fa-save"
                                                    style="font-size: 20px"></em>
                                            </button>
                                        </td>
                                        <td>
                                                ${user.login}
                                        </td>
                                        <td>
                                                ${user.surname}
                                        </td>
                                        <td>
                                                ${user.name}
                                        </td>
                                        <td>
                                            <select class="form-control" style="width:170px; padding-left: 0"
                                                    id="role${user.idUser}" disabled
                                                    onchange="changeRole('${user.idUser}')">
                                                <c:forEach var="userRole" items="${userRoles.entrySet()}">
                                                    <c:choose>
                                                        <c:when test="${user.role.userRoleId==userRole.getKey()}">
                                                            <option value="${userRole.getKey()}"
                                                                    selected>${userRole.getValue()}
                                                            </option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option value="${userRole.getKey()}">
                                                                    ${userRole.getValue()}
                                                            </option>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:forEach>
                                            </select>
                                        </td>
                                        <td>
                                            <select class="form-control" style="width:150px; padding-left: 0"
                                                    id="status${user.idUser}" disabled>
                                                <c:forEach var="status" items="${userStatuses.entrySet()}">
                                                    <c:choose>
                                                        <c:when test="${user.status.userStatusId==status.getKey()}">
                                                            <option value="${status.getKey()}" selected>
                                                                    ${status.getValue()}
                                                            </option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option value="${status.getKey()}">
                                                                    ${status.getValue()}
                                                            </option>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:forEach>
                                            </select>
                                        </td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${user.role!='CLIENT'}">
                                                    <fmt:parseDate value="${user.startWorkingDate}" type="date"
                                                                   pattern="yyyy-MM-dd"
                                                                   var="startDate"/>
                                                    <input class="form-control choose-date"
                                                           id="startDate${user.idUser}"
                                                           disabled style="width:150px; padding-left: 0" onload="useCalendar('${user.idUser}')"
                                                           name="startWorkingDate${user.idUser}" required=""
                                                           data-provide="datepicker"
                                                           value="<fmt:formatDate value="${startDate}" dateStyle="short"/>">
                                                </c:when>
                                                <c:otherwise>
                                                    <input class="form-control choose-date"
                                                           id="startDate${user.idUser}"
                                                           style="width:150px; padding-left: 0" hidden oninput="useCalendar('${user.idUser}')"
                                                           name="startWorkingDate${user.idUser}" required=""
                                                           data-provide="datepicker"
                                                           value="" dateStyle="short"/>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${user.role!='CLIENT'}">
                                                    <fmt:parseDate value="${user.endWorkingDate}" type="date"
                                                                   pattern="yyyy-MM-dd"
                                                                   var="endDate"/>
                                                    <input class="form-control choose-date" id="endDate${user.idUser}"
                                                           disabled
                                                           style="width:150px; padding-left: 0"
                                                           name="endWorkingDate${ucccccser.idUser}"
                                                           data-provide="datepicker"
                                                           value="<fmt:formatDate value="${endDate}" dateStyle="short"/>">
                                                </c:when>
                                                <c:otherwise>
                                                    <input class="form-control choose-date" id="endDate${user.idUser}"
                                                           hidden
                                                           style="width:150px; padding-left: 0"
                                                           name="endWorkingDate${user.idUser}" data-provide="datepicker"
                                                           value="" dateStyle="short"/>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </form>
                        </table>
                    </div>
                    <div class="panel-footer">
                        <div class="row">
                            <div class="col col-xs-4"><fmt:message key="allUser.titlePage"/>
                                ${numberPage}
                                <fmt:message key="allUser.pageFrom"/>
                                ${pageAmount}
                            </div>
                            <div class="col col-xs-8">
                                <ul class="pagination hidden-xs pull-right" style="font-size: 18px">
                                    <c:if test="${numberPage!=1}">
                                        <li style="margin-right: 5px">
                                            <a href="${pageContext.request.contextPath}/controller?commandName=all_user_page&number_page=1">
                                                <i class="fa fa-angle-double-left">
                                                </i>
                                            </a>
                                        </li>
                                    </c:if>
                                    <c:if test="${numberPage!=1}">
                                        <li style="margin-right: 5px">
                                            <a href="${pageContext.request.contextPath}/controller?commandName=all_user_page&number_page=${numberPage-1}">
                                                <i class="fa fa-angle-left">
                                                </i>
                                            </a>
                                        </li>
                                    </c:if>
                                    <c:set var="showPage" value="2" scope="page"/>
                                    <c:choose>
                                        <c:when test="${pageAmount-numberPage>showPage}">
                                            <c:set var="lastPage" value="${numberPage+showPage}" scope="page"/>
                                        </c:when>
                                        <c:otherwise>
                                            <c:set var="lastPage" value="${pageAmount}" scope="page"/>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:choose>
                                        <c:when test="${numberPage-1>showPage}">
                                            <c:set var="firstPage" value="${numberPage-showPage}" scope="page"/>
                                        </c:when>
                                        <c:otherwise>
                                            <c:set var="firstPage" value="1" scope="page"/>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:forEach var="i" begin="${firstPage}" end="${lastPage}" step="1">
                                        <c:choose>
                                            <c:when test="${i==numberPage}">
                                                ${numberPage}
                                            </c:when>
                                            <c:otherwise>
                                                <li>
                                                    <a href="${pageContext.request.contextPath}/controller?commandName=all_user_page&number_page=${i}"> ${i}</a>
                                                </li>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                    <c:if test="${numberPage!=pageAmount}">
                                        <li style="margin-left: 5px">
                                            <a href="${pageContext.request.contextPath}/controller?commandName=all_user_page&number_page=${numberPage+1}">
                                                <i class="fa fa-angle-right">
                                                </i>
                                            </a>
                                        </li>
                                    </c:if>
                                    <c:if test="${numberPage!=pageAmount}">
                                        <li style="margin-left: 5px">
                                            <a href="${pageContext.request.contextPath}/controller?commandName=all_user_page&number_page=${pageAmount}">
                                                <i class="fa fa-angle-double-right"></i>
                                            </a>
                                        </li>
                                    </c:if>
                                </ul>
                            </div>
                        </div>
                    </div>
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
<script src="${pageContext.request.contextPath}/js/all_users.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/show-modal.js"></script>
</body>
</html>