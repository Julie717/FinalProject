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
    <div class="row">
        <link href="${pageContext.request.contextPath}/fonts/font-awesome.min.css"
              rel='stylesheet' type='text/css'>
        <div class="container">
            <div class="row">
                <h4 style="margin-left:100px; margin-bottom:20px">
                    ${clientName}
                </h4>
                <div class="col-md-10 col-md-offset-1">
                    <div class="panel panel-default panel-table">
                        <div class="panel-heading">
                            <div class="row" style="margin-bottom:20px">
                                <div class="col">
                                    <form method="post"
                                          action="${pageContext.request.contextPath}/controller">
                                        <input type="hidden" name="commandName"
                                               value="sale_membership_page"/>
                                        <button type="submit" class="btn btn-sm btn-primary btn-create">
                                            <em class="fa fa-credit-card"></em>
                                            <fmt:message key="membership.buttonSaleMembership"/>
                                        </button>
                                    </form>
                                </div>
                                <div class="col">
                                    <form method="post"
                                          action="${pageContext.request.contextPath}/controller">
                                        <input type="hidden" name="commandName"
                                               value="edit_schedule_page"/>
                                        <button type="submit" class="btn btn-sm btn-primary btn-create"
                                                style="background-color: purple">
                                            <em class="fa fa-backward"></em>
                                            <fmt:message key="membership.buttonBack"/>
                                        </button>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <div class="panel-body">
                            <table class="table table-striped table-bordered table-list">
                                <thead>
                                <tr>
                                    <th>
                                        <fmt:message key="membership.check"/>
                                    </th>
                                    <th>
                                        <fmt:message key="membership.openDate"/>
                                    </th>
                                    <th>
                                        <fmt:message key="membership.closeDate"/>
                                    </th>
                                    <th>
                                        <fmt:message key="membership.remainingWorkouts"/>
                                    </th>
                                    <th>
                                        <fmt:message key="membership.description"/>
                                    </th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="membership" items="${clientMemberships}">
                                    <tr>
                                        <td align="center">
                                            <c:choose>
                                                <c:when test="${(empty sessionScope.idClientMembership || sessionScope.idClientMembership==0) && membership.remainingClasses>0}">
                                                    <a class="btn btn-success"
                                                       href="${pageContext.request.contextPath}/controller?commandName=client_present&id_client_membership=${membership.idClientMembership}">
                                                        <em class="fa fa-plus"></em>
                                                    </a>
                                                </c:when>
                                                <c:when test="${membership.idClientMembership==sessionScope.idClientMembership}">
                                                    <a class="btn btn-warning"
                                                       href="${pageContext.request.contextPath}/controller?commandName=client_absent">
                                                        <em class="fa fa-minus"></em>
                                                    </a>
                                                </c:when>
                                            </c:choose>
                                        </td>
                                        <td>
                                            <fmt:parseDate value="${membership.openDate}" type="date"
                                                           pattern="yyyy-MM-dd"
                                                           var="openDate"/>
                                            <fmt:formatDate value="${openDate}" dateStyle="short"/>
                                        </td>
                                        <td>
                                            <fmt:parseDate value="${membership.closeDate}" type="date"
                                                           pattern="yyyy-MM-dd"
                                                           var="closeDate"/>
                                            <fmt:formatDate value="${closeDate}" dateStyle="short"/>
                                        </td>
                                        <td>
                                                ${membership.remainingClasses}
                                        </td>
                                        <td>
                                                ${membership.typeByNumberPeople.concat(', ').concat(membership.periodTime)}
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

<c:import url="${pageContext.request.contextPath}/jsp/common/footer.jsp"/>

<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
<script src="${pageContext.request.contextPath}/js/from-and-to-calendar.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.maskedinput.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/show-modal.js"></script>
</body>
</html>