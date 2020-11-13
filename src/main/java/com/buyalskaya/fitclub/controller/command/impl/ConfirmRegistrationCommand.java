package com.buyalskaya.fitclub.controller.command.impl;

import com.buyalskaya.fitclub.controller.AttributeName;
import com.buyalskaya.fitclub.controller.ParameterName;
import com.buyalskaya.fitclub.controller.Router;
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

public class ConfirmRegistrationCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        String login = request.getParameter(ParameterName.USER_LOGIN);
        String page;
        try {
            UserService userService=ServiceFactory.getInstance().getUserService();
            boolean isConfirmed = userService.confirmRegistration(login);
            request.setAttribute(AttributeName.USER_CONFIRM_REGISTRATION, isConfirmed);
            request.setAttribute(AttributeName.SHOW_MODAL_CONFIRM_REGISTRATION,true);
            page = ConfigurationManager.getProperty(PageConfigName.HOMEPAGE);
        } catch (ServiceException ex) {
            logger.log(Level.ERROR,ex);
            page = ConfigurationManager.getProperty(PageConfigName.ERROR_500);
        }
        return new Router(page);
    }
}