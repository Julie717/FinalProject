package com.buyalskaya.fitclub.controller.command.impl;

import com.buyalskaya.fitclub.controller.AttributeName;
import com.buyalskaya.fitclub.controller.ParameterName;
import com.buyalskaya.fitclub.controller.Router;
import com.buyalskaya.fitclub.controller.command.Command;
import com.buyalskaya.fitclub.controller.command.ReceiveParameterFromRequest;
import com.buyalskaya.fitclub.exception.ServiceException;
import com.buyalskaya.fitclub.model.service.ServiceFactory;
import com.buyalskaya.fitclub.util.ConfigurationManager;
import com.buyalskaya.fitclub.util.PageConfigName;
import com.buyalskaya.fitclub.util.MailCreator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * The type Registration command.
 * This command allows to register in the site
 *
 * @author Buyalskaya Yuliya
 * @version 1.0
 */
public class RegistrationCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        String page;
        Map<String, String> userParameters = ReceiveParameterFromRequest.userParameters(request);
        try {
            HttpSession session = request.getSession();
            String locale = (String) session.getAttribute(AttributeName.SESSION_LOCALE);
            boolean isRegister = ServiceFactory.getInstance().getUserService().registration(userParameters, locale);
            if (isRegister) {
                MailCreator.sendMailToConfirmRegistration(locale,
                        userParameters.get(ParameterName.USER_EMAIL),
                        userParameters.get(ParameterName.USER_NAME),
                        userParameters.get(ParameterName.USER_LOGIN));
                request.setAttribute(AttributeName.SHOW_MODAL_REGISTRATION, true);
                page = ConfigurationManager.getProperty(PageConfigName.HOMEPAGE);
            } else {
                if (userParameters.containsKey(ParameterName.DUPLICATE_LOGIN)) {
                    request.setAttribute(AttributeName.DUPLICATE_LOGIN, true);
                }
                request.setAttribute(AttributeName.USER_PARAMETERS, userParameters);
                page = ConfigurationManager.getProperty(PageConfigName.REGISTRATION);
            }
        } catch (ServiceException ex) {
            logger.log(Level.ERROR, ex);
            page = ConfigurationManager.getProperty(PageConfigName.ERROR_500);
        }
        return new Router(page);
    }
}