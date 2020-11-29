package com.buyalskaya.fitclub.controller.command.impl;

import com.buyalskaya.fitclub.controller.AttributeName;
import com.buyalskaya.fitclub.controller.ParameterName;
import com.buyalskaya.fitclub.controller.Router;
import com.buyalskaya.fitclub.controller.command.AddRequestAttribute;
import com.buyalskaya.fitclub.controller.command.Command;
import com.buyalskaya.fitclub.exception.ServiceException;
import com.buyalskaya.fitclub.model.entity.*;
import com.buyalskaya.fitclub.model.service.ServiceFactory;
import com.buyalskaya.fitclub.util.ConfigurationManager;
import com.buyalskaya.fitclub.util.PageConfigName;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        String page;
        String login = request.getParameter(ParameterName.USER_LOGIN);
        String password = request.getParameter(ParameterName.USER_PASSWORD);
        try {
            boolean authorization = ServiceFactory.getInstance().getUserService().authorization(login, password);
            if (authorization) {
                User user = ServiceFactory.getInstance().getUserService().findUserByLogin(login).get();
                if (user.getStatus() == UserStatus.ACTIVE) {
                    UserRole userRole = user.getRole();
                    switch (userRole) {
                        case ADMINISTRATOR -> AddRequestAttribute.forAdministratorPage(request, login);
                        case INSTRUCTOR -> AddRequestAttribute.forInstructorPage(request, login);
                        default -> AddRequestAttribute.forClientPage(request, user.getLogin());
                    }
                    page = ConfigurationManager.getProperty(PageConfigName.PRIVATE_CABINET);
                    request.setAttribute(AttributeName.SHOW_MODAL, false);
                    request.setAttribute(AttributeName.LOGIN_WITHOUT_CONFIRM, false);
                } else {
                    page = ConfigurationManager.getProperty(PageConfigName.HOMEPAGE);
                    request.setAttribute(AttributeName.SHOW_MODAL, true);
                    request.setAttribute(AttributeName.LOGIN_WITHOUT_CONFIRM, true);
                }
            } else {
                page = ConfigurationManager.getProperty(PageConfigName.HOMEPAGE);
                request.setAttribute(AttributeName.SHOW_MODAL, true);
                request.setAttribute(AttributeName.INCORRECT_LOGIN_OR_PASSWORD, true);
            }
        } catch (ServiceException ex) {
            logger.log(Level.ERROR, ex);
            page = ConfigurationManager.getProperty(PageConfigName.ERROR_500);
        }
        request.getRequestDispatcher(page);
        return new Router(page);
    }
}