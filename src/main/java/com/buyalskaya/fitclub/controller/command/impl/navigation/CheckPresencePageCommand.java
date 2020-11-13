package com.buyalskaya.fitclub.controller.command.impl.navigation;

import com.buyalskaya.fitclub.controller.AttributeName;
import com.buyalskaya.fitclub.controller.ParameterName;
import com.buyalskaya.fitclub.controller.Router;
import com.buyalskaya.fitclub.controller.command.AddRequestAttribute;
import com.buyalskaya.fitclub.controller.command.Command;
import com.buyalskaya.fitclub.exception.ServiceException;
import com.buyalskaya.fitclub.pageconfiguration.ConfigurationManager;
import com.buyalskaya.fitclub.pageconfiguration.PageConfigName;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CheckPresencePageCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        String idClient = request.getParameter(ParameterName.CLIENT_ID);
        String idClientMembership = request.getParameter(ParameterName.CLIENT_MEMBERSHIP_ID);
        HttpSession session = request.getSession();
        session.setAttribute(AttributeName.CLIENT_ID,idClient);
        session.setAttribute(AttributeName.CLIENT_MEMBERSHIP_ID, idClientMembership);
        String page;
        try {
            AddRequestAttribute.forCheckPresence(request,idClient);
            page = ConfigurationManager.getProperty(PageConfigName.CHECK_PRESENCE);
        } catch (ServiceException ex) {
            logger.log(Level.ERROR, ex);
            page = ConfigurationManager.getProperty(PageConfigName.ERROR_500);
        }
        return new Router(page);
    }
}