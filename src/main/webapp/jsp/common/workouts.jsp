<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="prop.pagecontent"/>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
<div class="container site-section" id="why">
    <h2><fmt:message key="homepage.workout_types"/></h2>
    <p><fmt:message key="homepage.workout_description"/></p>
    <div class="row" id="workouts">
        <div class="col-4" id="item1">
            <div class="jumbotron item">
                <h2><fmt:message key="homepage.workout_low_types"/></h2>
                <ul class="list-group">
                    <c:forEach var="workout" items="${workoutsLow}">
                        <li class="list-group-item">
                            <span>${workout.name}</span>
                        </li>
                    </c:forEach>
                </ul>
                <p class="text-break"></p>
                <p>
                    <a class="btn btn-primary" role="button"
                       href="${pageContext.request.contextPath}controller?commandName=low_workout_page">
                        <fmt:message key="homepage.workout_details"/>
                    </a>
                </p>
            </div>
        </div>
        <div class="col-4" id="item2">
            <div class="jumbotron item">
                <h2><fmt:message key="homepage.workout_middle_types"/></h2>
                <ul class="list-group">
                    <c:forEach var="workout" items="${workoutsMiddle}">
                        <li class="list-group-item">
                            <span>${workout.name}</span>
                        </li>
                    </c:forEach>
                </ul>
                <p class="text-break"></p>
                <p>
                    <a class="btn btn-primary" role="button"
                       href="${pageContext.request.contextPath}controller?commandName=middle_workout_page">
                        <fmt:message key="homepage.workout_details"/>
                    </a>
                </p>
            </div>
        </div>
        <div class="col-4 item" id="item3">
            <div class="jumbotron item">
                <h2><fmt:message key="homepage.workout_high_types"/></h2>
                <ul class="list-group">
                    <c:forEach var="workout" items="${workoutsHigh}">
                        <li class="list-group-item">
                            <span>${workout.name}</span>
                        </li>
                    </c:forEach>
                </ul>
                <p class="text-break"></p>
                <p>
                    <a class="btn btn-primary" role="button"
                       href="${pageContext.request.contextPath}controller?commandName=high_workout_page">
                        <fmt:message key="homepage.workout_details"/>
                    </a>
                </p>
            </div>
        </div>
    </div>
</div>
</body>
</html>
