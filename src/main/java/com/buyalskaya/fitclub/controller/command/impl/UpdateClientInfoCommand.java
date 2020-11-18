package com.buyalskaya.fitclub.controller.command.impl;

import com.buyalskaya.fitclub.controller.AttributeName;
import com.buyalskaya.fitclub.controller.ParameterName;
import com.buyalskaya.fitclub.controller.Router;
import com.buyalskaya.fitclub.controller.command.AddRequestAttribute;
import com.buyalskaya.fitclub.controller.command.Command;
import com.buyalskaya.fitclub.exception.ServiceException;
import com.buyalskaya.fitclub.model.entity.Staff;
import com.buyalskaya.fitclub.model.entity.User;
import com.buyalskaya.fitclub.model.entity.UserRole;
import com.buyalskaya.fitclub.model.service.ServiceFactory;
import com.buyalskaya.fitclub.util.ConfigurationManager;
import com.buyalskaya.fitclub.util.PageConfigName;
import com.buyalskaya.fitclub.util.CommonUtil;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class UpdateClientInfoCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession();
        String locale = (String) session.getAttribute(AttributeName.SESSION_LOCALE);
        User user = (User) session.getAttribute(AttributeName.SESSION_USER);
        boolean isUpdate = false;
        try {
            if (user.getRole() == UserRole.CLIENT) {
                Map<String, String> userParameters = receiveClientParametersFromRequest(request);
                if (!userParameters.isEmpty()) {
                    isUpdate = ServiceFactory.getInstance().getUserService()
                            .updateClientInfo(userParameters, user, locale);
                }
                AddRequestAttribute.forClientPage(request, user.getIdUser());
            } else {
                Staff staff = (Staff) user;
                Map<String, String> staffParameters = receiveStaffParametersFromRequest(request);
                if (!staffParameters.isEmpty()) {
                    isUpdate = ServiceFactory.getInstance().getUserService()
                            .updateStaffInfo(staffParameters, staff, locale);
                }
                if (user.getRole() == UserRole.INSTRUCTOR) {
                    AddRequestAttribute.forInstructorPage(request, staff.getLogin());
                } else {
                    AddRequestAttribute.forAdministratorPage(request, staff.getLogin());
                }
            }
            request.setAttribute(AttributeName.EDIT_INFO, !isUpdate);
            page = ConfigurationManager.getProperty(PageConfigName.PRIVATE_CABINET);
        } catch (ServiceException ex) {
            logger.log(Level.ERROR, ex);
            page = ConfigurationManager.getProperty(PageConfigName.ERROR_500);
        }
        return new Router(page);
    }

    private Map<String, String> receiveClientParametersFromRequest(HttpServletRequest request) {
        Map<String, String> userParameters = new HashMap();
        userParameters.put(ParameterName.USER_LOGIN, request.getParameter(ParameterName.USER_LOGIN));
        userParameters.put(ParameterName.USER_NAME, request.getParameter(ParameterName.USER_NAME));
        userParameters.put(ParameterName.USER_SURNAME, request.getParameter(ParameterName.USER_SURNAME));
        String phone = CommonUtil.transformPhone(request.getParameter(ParameterName.USER_PHONE));
        userParameters.put(ParameterName.USER_PHONE, phone);
        userParameters.put(ParameterName.USER_EMAIL, request.getParameter(ParameterName.USER_EMAIL));
        userParameters.put(ParameterName.USER_BIRTHDAY, request.getParameter(ParameterName.USER_BIRTHDAY));
        return userParameters;
    }

    private Map<String, String> receiveStaffParametersFromRequest(HttpServletRequest request) {
        Map<String, String> userParameters = receiveClientParametersFromRequest(request);
        userParameters.put(ParameterName.WORK_EXPERIENCE, request.getParameter(ParameterName.WORK_EXPERIENCE));
        userParameters.put(ParameterName.STAFF_DESCRIPTION, request.getParameter(ParameterName.STAFF_DESCRIPTION));
        return userParameters;
    }
}