package com.buyalskaya.fitclub.controller.command.impl;

import com.buyalskaya.fitclub.controller.command.Command;
import com.buyalskaya.fitclub.exception.ServiceException;
import com.buyalskaya.fitclub.model.service.ServiceFactory;
import com.buyalskaya.fitclub.resource.ConfigurationManager;
import com.buyalskaya.fitclub.resource.MessageManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String password = request.getParameter(PARAM_NAME_PASSWORD);
        try {
            boolean resultAuthorization = ServiceFactory.getInstance().getUserService().authorization(login, password);
            if (resultAuthorization) {
                page = ConfigurationManager.getProperty("page.main");
            } else {
                request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginerror"));
                page = ConfigurationManager.getProperty("page.login");
            }
        } catch (ServiceException ex) {
            logger.log(Level.ERROR, ex.getMessage());
            page = ConfigurationManager.getProperty("page.error");
        }
        return page;
    }
}