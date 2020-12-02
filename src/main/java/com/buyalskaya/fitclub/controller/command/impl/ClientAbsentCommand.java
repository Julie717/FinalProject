package com.buyalskaya.fitclub.controller.command.impl;

import com.buyalskaya.fitclub.controller.AttributeName;
import com.buyalskaya.fitclub.controller.Router;
import com.buyalskaya.fitclub.controller.command.AddRequestAttribute;
import com.buyalskaya.fitclub.controller.command.Command;
import com.buyalskaya.fitclub.exception.ServiceException;
import com.buyalskaya.fitclub.model.service.MembershipService;
import com.buyalskaya.fitclub.model.service.ServiceFactory;
import com.buyalskaya.fitclub.util.ConfigurationManager;
import com.buyalskaya.fitclub.util.PageConfigName;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The type Client absent command.
 * This command allows administrators to unmark the client's presents
 *
 * @author Buyalskaya Yuliya
 * @version 1.0
 */
public class ClientAbsentCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String idClientMembership = (String) session.getAttribute(AttributeName.CLIENT_MEMBERSHIP_ID);
        session.removeAttribute(AttributeName.CLIENT_MEMBERSHIP_ID);
        String idSchedule = (String) session.getAttribute(AttributeName.SCHEDULE_ID);
        String idClient = (String) session.getAttribute(AttributeName.CLIENT_ID);
        String page;
        try {
            MembershipService membershipService = ServiceFactory.getInstance().getMembershipService();
            membershipService.checkClientPresence(idClient, idSchedule, idClientMembership, false);
            AddRequestAttribute.forCheckPresence(request, idClient);
            page = ConfigurationManager.getProperty(PageConfigName.CHECK_PRESENCE);
        } catch (ServiceException ex) {
            logger.log(Level.ERROR, ex);
            page = ConfigurationManager.getProperty(PageConfigName.ERROR_500);
        }
        return new Router(page);
    }
}