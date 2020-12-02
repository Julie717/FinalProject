package com.buyalskaya.fitclub.controller.command.impl.navigation;

import com.buyalskaya.fitclub.controller.AttributeName;
import com.buyalskaya.fitclub.controller.Router;
import com.buyalskaya.fitclub.controller.command.Command;
import com.buyalskaya.fitclub.exception.ServiceException;
import com.buyalskaya.fitclub.model.entity.Membership;
import com.buyalskaya.fitclub.model.service.MembershipService;
import com.buyalskaya.fitclub.model.service.ServiceFactory;
import com.buyalskaya.fitclub.model.service.UserService;
import com.buyalskaya.fitclub.util.ConfigurationManager;
import com.buyalskaya.fitclub.util.PageConfigName;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public class SaleMembershipPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String idClient = (String) session.getAttribute(AttributeName.CLIENT_ID);
        String page;
        try {
            MembershipService membershipService = ServiceFactory.getInstance().getMembershipService();
            List<Membership> memberships = membershipService.findAllMemberships();
            request.setAttribute(AttributeName.MEMBERSHIPS, memberships);
            UserService userService = ServiceFactory.getInstance().getUserService();
            Optional<String> clientName = userService.findClientName(idClient);
            if (clientName.isPresent()) {
                request.setAttribute(AttributeName.CLIENT_NAME, clientName);
            }
            page = ConfigurationManager.getProperty(PageConfigName.SALE_MEMBERSHIP);
        } catch (ServiceException ex) {
            logger.log(Level.ERROR, ex);
            page = ConfigurationManager.getProperty(PageConfigName.ERROR_500);
        }
        return new Router(page);
    }
}