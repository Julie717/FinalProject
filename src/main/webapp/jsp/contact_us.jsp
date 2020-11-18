<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="prop.pagecontent"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Contact-Form-Clean.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Footer-Clean.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>

<body>
<c:import url="${pageContext.request.contextPath}/jsp/common/header.jsp"/>
<div class="contact-clean">
    <form method="post"
          action="${pageContext.request.contextPath}/controller">
        <input type="hidden" name="commandName"
               value="contact_us"/>
        <h2 class="text-center">
            <fmt:message key="contactUs.title"/>
        </h2>
        <div class="form-group">
            <input class="form-control" type="text" id="name"
                   name="name" required="" pattern="[а-яА-Яa-zA-Z][а-яА-Яa-zA-Z- ]{1,50}"
                   oninvalid="this.setCustomValidity('<fmt:message
                           key="contactUs.nameDescription"/>')"
                   onchange="this.setCustomValidity('')"
                   placeholder="<fmt:message key="contactUs.name"/>">
        </div>
        <div class="form-group">
            <input class="form-control" type="text" id="phone"
                   name="phone" required=""
                   placeholder="<fmt:message key="contactUs.phone"/>">
        </div>
        <div class="form-group">
            <input class="form-control" type="email" id="email"
                   oninvalid="this.setCustomValidity('<fmt:message key="registration.warnMessage"/>')"
                   onchange="this.setCustomValidity('')"
                   name="email" required=""
                   placeholder="<fmt:message key="contactUs.email"/>">
        </div>
        <div class="form-group">
            <label for="message">
                <fmt:message key="contactUs.message"/>
            </label>
            <span class="required-input">*</span>
            <textarea class="form-control" name="message" id="message"
                      required="" onkeyup="this.value = this.value.replace(/[<>]/g, '')"
                      maxlength="500"></textarea>
        </div>
        <div class="form-group">
            <button class="btn btn-primary" type="submit">
                <fmt:message key="contactUs.button"/>
            </button>
        </div>
    </form>
</div>
<c:import url="${pageContext.request.contextPath}/jsp/common/footer.jsp"/>

<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.maskedinput.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/contact_us.js"></script>
</body>
</html>