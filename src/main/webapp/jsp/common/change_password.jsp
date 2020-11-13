<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="prop.pagecontent"/>

<body>
<div role="dialog" tabindex="-1" class="modal fade" id="modalChangePassword">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title text-primary">
                    <fmt:message key="account.changePasswordTitle"/>
                </h3>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">×</span></button>
            </div>
            <div class="modal-body">
                <form class="p-4" method="POST"
                      action="${pageContext.request.contextPath}/controller">
                    <input type="hidden" name="commandName" value="change_password"/>
                    <div class="input-group" style="margin-bottom: 10px">
                        <div class="input-group-prepend">
                            <span class="input-group-text">
                                <i class="fa fa-pencil"></i>
                            </span>
                        </div>
                        <input class="form-control" type="password" id="oldPassword" name="oldPassword"
                               pattern="(?=.*\d)(?=.*[A-Z])[\w\p{P}]{8,}"
                               oninvalid="this.setCustomValidity('<fmt:message
                                       key="registration.passwordDescription"/>')"
                               onchange="this.setCustomValidity('')"
                               required=""
                               placeholder='<fmt:message key="account.oldPassword"/>'>
                    </div>
                    <div class="input-group" style="margin-bottom: 10px">
                        <div class="input-group-prepend">
                            <span class="input-group-text">
                                <i class="fa fa-pencil"></i>
                            </span>
                        </div>
                        <input class="form-control" type="password" id="newPassword" name="newPassword"
                               pattern="(?=.*\d)(?=.*[A-Z])[\w\p{P}]{8,}"
                               oninvalid="this.setCustomValidity('<fmt:message
                                       key="registration.passwordDescription"/>')"
                               onchange="this.setCustomValidity('')"
                               required=""
                               placeholder='<fmt:message key="account.newPassword"/>'>
                    </div>
                    <div class="input-group" style="margin-bottom: 10px">
                        <div class="input-group-prepend">
                            <span class="input-group-text">
                                <i class="fa fa-pencil"></i>
                            </span>
                        </div>
                        <input class="form-control" type="password" id="repeatedNewPassword" name="repeatedNewPassword"
                               pattern="(?=.*\d)(?=.*[A-Z])[\w\p{P}]{8,}"
                               oninvalid="this.setCustomValidity('<fmt:message
                                       key="registration.passwordDescription"/>')"
                               onchange="this.setCustomValidity('')"
                               required=""
                               placeholder='<fmt:message key="account.repeatedNewPassword"/>'>
                    </div>
                    <div class="text-error" id="incorrectPassword" hidden style="color: darkred">
                        <fmt:message key="account.incorrectPassword"/>
                    </div>
                    <div style="margin-left: 15%">
                        <button type="submit" class="btn btn-primary">
                            <fmt:message key="account.savePassword"/>
                        </button>
                        <button style="margin-left: 10%" id="cancelPassword" type="button"
                                class="btn btn-primary" data-dismiss="modal">
                            <fmt:message key="account.cancelPassword"/>
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<input id="incorrectPasswordMessage" type="hidden" value="${incorrectPasswordMessage}">
<div role="dialog" tabindex="-1" class="modal fade" id="modalCorrectPasswordChange">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title text-primary">
                    <i class="fa fa-edit"></i>
                    <fmt:message key="account.changePasswordTitle"/>
                </h3>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">×</span></button>
            </div>
            <div class="modal-body">
                <div class="form-group" align="justify">
                    <p>
                        <fmt:message key="account.wasChangedPassword"/>
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
</body>