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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/button.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Features-Boxed.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Footer-Clean.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Pretty-Registration-Form.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui.theme.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui.structure.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/MUSA_panel-table-1.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/MUSA_panel-table.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Team-Grid.css">
</head>

<body>
<c:import url="${pageContext.request.contextPath}/jsp/common/header.jsp"/>
<div class="features-boxed"></div>
<div class="team-grid">
    <div class="container">
        <div class="row" style="margin-top: 50px">
            <form method="post" action="${pageContext.request.contextPath}/controller">
                <div class="col" style="width: 400px">
                    <div class="row">
                        <div class="col">
                            <h4>${clientName}</h4>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="openDate">
                            <fmt:message key="membership.dateActivation"/>
                        </label>
                        <span class="required-input">*</span>
                        <input type="hidden" id="localeCalendar" value="${sessionScope.locale}">
                        <div class="input-group">
                            <div class="input-group-prepend">
                            <span class="input-group-text">
                                <i class="fa fa-calendar"></i>
                            </span>
                            </div>
                            <input class="form-control" type="text" id="openDate"
                                   name="openDate" required="" data-provide="datepicker"
                                   placeholder="<fmt:message key="membership.dateActivation"/>">
                        </div>
                        <input type="hidden" name="commandName" value="sale_membership"/>
                        <input type="hidden" name="idClient" value="${idClient}"/>
                        <input type="hidden" name="idSchedule" value="${idSchedule}"/>
                        <input type="hidden" name="idMembership" id="idMembership"/>
                    </div>
                </div>
                <div class="col align-self-end">
                    <div class="form-group">
                        <button class="btn btn-primary p-2 mr-2 mb-2" id="saleMembershipButton" type="submit">
                            <fmt:message key="membership.buttonSaleMembership"/>
                        </button>
                    </div>
                </div>
            </form>
        </div>
        <div class="col" style="padding-left: 0">
            <form method="post"
                  action="${pageContext.request.contextPath}/controller" style="padding-left: 0">
                <input type="hidden" name="commandName"
                       value="check_presence_page"/>
                <button type="submit" class="btn btn-sm btn-primary btn-create" id="backToClientMemberships"
                        style="background-color: purple">
                    <em class="fa fa-backward"></em>
                    <fmt:message key="saleMembership.buttonBack"/>
                </button>
            </form>
        </div>
    </div>
    <div class="container">
        <div class="row">
            <h4 style="margin-left:50px">
                <fmt:message key="membership.title"/>
            </h4>
            <div class="col-md-10 col-md-offset-1">
                <div class="panel-body">
                    <table class="table table-striped table-bordered table-list">
                        <thead>
                        <tr>
                            <th style="text-align:center">
                                <em class="fa fa-cog"></em>
                            </th>
                            <th>
                                <fmt:message key="membership.typeMembership"/>
                            </th>
                            <th>
                                <fmt:message key="membership.amountOfClasses"/>
                            </th>
                            <th>
                                <fmt:message key="membership.duration"/>
                            </th>
                            <th>
                                <fmt:message key="membership.price"/>
                            </th>
                            <th>
                                <fmt:message key="membership.startSale"/>
                            </th>
                            <th>
                                <fmt:message key="membership.endSale"/>
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="membership" items="${memberships}">
                            <tr>
                                <td align="center">
                                    <input type="radio" name="idMembershipRadio" required
                                           value="${membership.idMembership}"
                                           style="zoom:1.7;margin-top:10%; margin-bottom:10%"/>
                                </td>
                                <td>
                                        ${membership.typeByNumberPeople.concat(', ').concat(membership.periodTime)} </td>
                                <td>
                                        ${membership.classesAmount}
                                </td>
                                <td>
                                        ${membership.duration}
                                </td>
                                <td>
                                        ${membership.price}
                                </td>
                                <td>
                                    <fmt:parseDate value="${membership.startSale}" type="date"
                                                   pattern="yyyy-MM-dd"
                                                   var="startDate"/>
                                    <fmt:formatDate value="${startDate}" dateStyle="short"/>
                                </td>
                                <td>
                                    <fmt:parseDate value="${membership.endSale}" type="date"
                                                   pattern="yyyy-MM-dd"
                                                   var="endDate"/>
                                    <fmt:formatDate value="${endDate}" dateStyle="short"/>
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

<c:import url="${pageContext.request.contextPath}/jsp/common/footer.jsp"/>

<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/show-modal.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
<script src="${pageContext.request.contextPath}/js/from-and-to-calendar.js"></script>
<script src="${pageContext.request.contextPath}/js/edit_schedule.js"></script>
</body>

</html>