package com.buyalskaya.fitclub.controller.command.impl;

import com.buyalskaya.fitclub.controller.AttributeName;
import com.buyalskaya.fitclub.controller.ParameterName;
import com.buyalskaya.fitclub.controller.Router;
import com.buyalskaya.fitclub.controller.command.Command;
import com.buyalskaya.fitclub.exception.ServiceException;
import com.buyalskaya.fitclub.model.service.ServiceFactory;
import com.buyalskaya.fitclub.model.service.UserService;
import com.buyalskaya.fitclub.util.ConfigurationManager;
import com.buyalskaya.fitclub.util.PageConfigName;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The type Confirm registration command.
 * This command allows to confirm registration
 *
 * @author Buyalskaya Yuliya
 * @version 1.0
 */
public class ConfirmRegistrationCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static String ENGLISH = "en";
    private static final String DEFAULT_LOCALE = "ru_RU";
    private static final String ENGLISH_LOCALE = "en_US";

    @Override
    public Router execute(HttpServletRequest request) {
        String login = request.getParameter(ParameterName.USER_LOGIN);
        String language = request.getParameter(ParameterName.LANGUAGE);
        HttpSession session = request.getSession();
        if (ENGLISH.equals(language)) {
            session.setAttribute(AttributeName.SESSION_LOCALE, ENGLISH_LOCALE);
        } else {
            session.setAttribute(AttributeName.SESSION_LOCALE, DEFAULT_LOCALE);
        }
        String page;
        try {
            UserService userService = ServiceFactory.getInstance().getUserService();
            boolean isConfirmed = userService.confirmRegistration(login);
            request.setAttribute(AttributeName.USER_CONFIRM_REGISTRATION, isConfirmed);
            request.setAttribute(AttributeName.SHOW_MODAL_CONFIRM_REGISTRATION, true);
            page = ConfigurationManager.getProperty(PageConfigName.HOMEPAGE);
        } catch (ServiceException ex) {
            logger.log(Level.ERROR, ex);
            page = ConfigurationManager.getProperty(PageConfigName.ERROR_500);
        }
        return new Router(page);
    }
}