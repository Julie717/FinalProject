package com.buyalskaya.fitclub.controller.command.impl;

import com.buyalskaya.fitclub.controller.AttributeName;
import com.buyalskaya.fitclub.controller.Router;
import com.buyalskaya.fitclub.controller.command.Command;
import com.buyalskaya.fitclub.controller.command.ReceiveParameterFromRequest;
import com.buyalskaya.fitclub.exception.ServiceException;
import com.buyalskaya.fitclub.model.service.ContactService;
import com.buyalskaya.fitclub.model.service.ServiceFactory;
import com.buyalskaya.fitclub.util.ConfigurationManager;
import com.buyalskaya.fitclub.util.PageConfigName;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class ContactUsCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        String page;
        Map<String, String> contactParameters = ReceiveParameterFromRequest.contactUsParameters(request);
        HttpSession session = request.getSession();
        String locale = (String) session.getAttribute(AttributeName.SESSION_LOCALE);
        try {
            ContactService contactService = ServiceFactory.getInstance().getContactService();
            boolean isSend = contactService.sendMessageToAdmins(contactParameters, locale);
            request.setAttribute(AttributeName.SHOW_MODAL_CONTACT_US, true);
            request.setAttribute(AttributeName.SEND_MESSAGE_TO_ADMIN, isSend);
            page = ConfigurationManager.getProperty(PageConfigName.INDEX);
        } catch (ServiceException ex) {
            logger.log(Level.ERROR, ex);
            page = ConfigurationManager.getProperty(PageConfigName.ERROR_500);
        }
        return new Router(page);
    }
}