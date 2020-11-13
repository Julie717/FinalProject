package com.buyalskaya.fitclub.controller.command.impl;

import com.buyalskaya.fitclub.controller.AttributeName;
import com.buyalskaya.fitclub.controller.ParameterName;
import com.buyalskaya.fitclub.controller.Router;
import com.buyalskaya.fitclub.controller.command.AddRequestAttribute;
import com.buyalskaya.fitclub.controller.command.Command;
import com.buyalskaya.fitclub.exception.ServiceException;
import com.buyalskaya.fitclub.model.service.ServiceFactory;
import com.buyalskaya.fitclub.model.service.UserService;
import com.buyalskaya.fitclub.pageconfiguration.ConfigurationManager;
import com.buyalskaya.fitclub.pageconfiguration.PageConfigName;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class ChangeUserRoleCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        String numberPage = request.getParameter(ParameterName.USERS_NUMBER_PAGE);
        String surnameSearch = request.getParameter(ParameterName.SURNAME_SEARCH);
        HttpSession session = request.getSession();
        String locale = (String) session.getAttribute(AttributeName.SESSION_LOCALE);
        String page;
        try {
            Map<String, String> userParameters = receiveUserParametersFromRequest(request);
            UserService userService=ServiceFactory.getInstance().getUserService();
            userService.updateUserRoleOrStatus(userParameters, locale);
            AddRequestAttribute.forAllUsersPage(request, surnameSearch,numberPage);
            page = ConfigurationManager.getProperty(PageConfigName.ALL_USERS);
        } catch (ServiceException ex) {
            logger.log(Level.ERROR, ex);
            page = ConfigurationManager.getProperty(PageConfigName.ERROR_500);
        }
        return new Router(page);
    }

    private Map<String, String> receiveUserParametersFromRequest(HttpServletRequest request) {
        Map<String, String> userParameters = new HashMap();
        userParameters.put(ParameterName.USER_ID, request.getParameter(ParameterName.USER_ID));
        userParameters.put(ParameterName.USER_ROLE, request.getParameter(ParameterName.USER_ROLE));
        userParameters.put(ParameterName.USER_STATUS, request.getParameter(ParameterName.USER_STATUS));
        userParameters.put(ParameterName.STAFF_START_WORKING_DATE,
                request.getParameter(ParameterName.STAFF_START_WORKING_DATE));
        userParameters.put(ParameterName.STAFF_END_WORKING_DATE,
                request.getParameter(ParameterName.STAFF_END_WORKING_DATE));
        return userParameters;
    }
}