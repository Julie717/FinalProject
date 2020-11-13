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
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="membership" items="${memberships}">
                                <tr>
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
<c:import url="${pageContext.request.contextPath}/jsp/common/footer.jsp"/>

<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
<script src="${pageContext.request.contextPath}/js/from-and-to-calendar.js"></script>
<script src="${pageContext.request.contextPath}/js/edit_schedule.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/show-modal.js"></script>
</body>

</html>