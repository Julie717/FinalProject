<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="prop.pagecontent"/>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
<div class="footer-clean" id="footer">
    <footer>
        <div class="container">
            <div class="row no-gutters">
                <div class="col-sm-4 col-md-3 align-self-center item">
                    <ul>
                        <li><a href="${pageContext.request.contextPath}controller?commandName=about_us_page"><fmt:message key="header.about_us"/></a></li>
                        <li><a href="${pageContext.request.contextPath}controller?commandName=instructors_page"><fmt:message key="header.instructors"/></a></li>
                        <li><a href="${pageContext.request.contextPath}controller?commandName=workout_page"><fmt:message key="homepage.workout_types"/></a></li>
                    </ul>
                </div>
                <div class="col-sm-4 col-md-3 item">
                    <h3></h3>
                    <ul>
                        <li><a href="${pageContext.request.contextPath}controller?commandName=schedule_page"><fmt:message key="header.schedule"/></a></li>
                        <li><a href="${pageContext.request.contextPath}controller?commandName=membership_page"><fmt:message key="header.price"/></a></li>
                        <li><a href="${pageContext.request.contextPath}controller?commandName=contacts_page"><fmt:message key="header.contacts"/></a></li>
                    </ul>
                </div>
                <div class="col-lg-3 offset-3 item social">
                    <a href="${pageContext.request.contextPath}controller?commandName=telegram_page">
                        <i class="icon ion-paper-airplane"></i>
                    </a>
                    <a href="${pageContext.request.contextPath}controller?commandName=instagram_page">
                        <i class="icon ion-social-instagram-outline"></i>
                    </a>
                    <a href="${pageContext.request.contextPath}controller?commandName=vkontakte_page">
                        <i class="icon ion-social-vimeo"></i>
                    </a>
                    <p class="copyright"><fmt:message key="homepage.club_name"/> © 2020</p>
                </div>
            </div>
        </div>
    </footer>
</div>
</body>