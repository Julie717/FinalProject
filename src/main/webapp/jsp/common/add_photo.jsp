<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="prop.pagecontent"/>

<body>
<div role="dialog" tabindex="-1" class="modal fade" id="modalChangePhoto">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title text-primary">
                    <fmt:message key="account.changePhotoTitle"/>
                </h3>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">×</span></button>
            </div>
            <div class="modal-body">
                <input type="hidden" id="language" value="${sessionScope.locale}"/>
                <form action="${pageContext.request.contextPath}/controller"
                      enctype="multipart/form-data"
                      method="post">
                    <input type="hidden" name="commandName" value="add_photo"/>
                    <div class="custom-file" style="margin-bottom: 10px">
                        <input class="custom-file-input" type="file" id="photo" translate="yes"
                               name="photo" accept="image/jpg"/>
                        <label style="white-space: nowrap;overflow: hidden;text-overflow: ellipsis"
                               class="custom-file-label" for="photo" id="labelPhoto">
                            <fmt:message key="account.photoLabel"/>
                        </label>
                    </div>
                    <div style="margin-left: 15%">
                        <button type="submit" class="btn btn-primary">
                            <fmt:message key="account.savePhoto"/>
                        </button>
                        <button style="margin-left: 10%" id="cancelPhoto" type="button"
                                class="btn btn-primary" data-dismiss="modal">
                            <fmt:message key="account.cancelPhoto"/>
                        </button>
                    </div>
                </form>
                <div>
                    <p>
                        <fmt:message key="account.photoFormat"/>
                    </p>
                </div>
            </div>
        </div>
    </div>
</div>
</body>