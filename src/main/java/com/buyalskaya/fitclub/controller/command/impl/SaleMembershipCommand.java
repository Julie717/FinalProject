package com.buyalskaya.fitclub.controller.command.impl;

import com.buyalskaya.fitclub.controller.AttributeName;
import com.buyalskaya.fitclub.controller.ParameterName;
import com.buyalskaya.fitclub.controller.Router;
import com.buyalskaya.fitclub.controller.command.AddRequestAttribute;
import com.buyalskaya.fitclub.controller.command.Command;
import com.buyalskaya.fitclub.exception.ServiceException;
import com.buyalskaya.fitclub.model.entity.ClientMembership;
import com.buyalskaya.fitclub.model.service.ServiceFactory;
import com.buyalskaya.fitclub.pageconfiguration.ConfigurationManager;
import com.buyalskaya.fitclub.pageconfiguration.PageConfigName;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class SaleMembershipCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        String openDate = request.getParameter(ParameterName.CLIENT_MEMBERSHIP_OPEN_DATE);
        String idMembership = request.getParameter(ParameterName.MEMBERSHIP_ID);
        HttpSession session = request.getSession();
        String idClient = (String) session.getAttribute(AttributeName.CLIENT_ID);
        String locale = (String) session.getAttribute(AttributeName.SESSION_LOCALE);
        String page;
        try {
            ServiceFactory.getInstance().getMembershipService().addClientMembership(idMembership, openDate, idClient, locale);
            AddRequestAttribute.forCheckPresence(request,idClient);
            List<ClientMembership> memberships = ServiceFactory.getInstance().getMembershipService()
                    .findActiveClientMemberships(idClient);
            request.setAttribute(AttributeName.CLIENT_MEMBERSHIPS, memberships);
            String clientName = ServiceFactory.getInstance().getUserService().findClientName(idClient);
            request.setAttribute(AttributeName.CLIENT_NAME, clientName);
            page = ConfigurationManager.getProperty(PageConfigName.CHECK_PRESENCE);
        } catch (ServiceException ex) {
            logger.log(Level.ERROR, ex);
            page = ConfigurationManager.getProperty(PageConfigName.ERROR_500);
        }
        return new Router(page);
    }
}