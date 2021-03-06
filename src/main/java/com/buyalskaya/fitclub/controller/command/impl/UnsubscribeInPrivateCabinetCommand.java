package com.buyalskaya.fitclub.controller.command.impl;

import com.buyalskaya.fitclub.controller.AttributeName;
import com.buyalskaya.fitclub.controller.ParameterName;
import com.buyalskaya.fitclub.controller.Router;
import com.buyalskaya.fitclub.controller.command.AddRequestAttribute;
import com.buyalskaya.fitclub.controller.command.Command;
import com.buyalskaya.fitclub.exception.ServiceException;
import com.buyalskaya.fitclub.model.entity.User;
import com.buyalskaya.fitclub.model.service.ServiceFactory;
import com.buyalskaya.fitclub.util.ConfigurationManager;
import com.buyalskaya.fitclub.util.PageConfigName;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The type Unsubscribe in private cabinet command.
 * This command allows clients to unsubscribe from workouts on which they are subscribed
 *
 * @author Buyalskaya Yuliya
 * @version 1.0
 */
public class UnsubscribeInPrivateCabinetCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(AttributeName.SESSION_USER);
        String idSchedule = request.getParameter(ParameterName.SCHEDULE_ID);
        String subscribe = request.getParameter(ParameterName.SUBSCRIBE);
        String page = (String) session.getAttribute(AttributeName.SESSION_CURRENT_PAGE);
        try {
            ServiceFactory.getInstance().getScheduleService().subscribeClient(user.getIdUser(),
                    idSchedule, subscribe);
            AddRequestAttribute.forClientPage(request, user.getLogin());
        } catch (ServiceException ex) {
            logger.log(Level.ERROR, ex);
            page = ConfigurationManager.getProperty(PageConfigName.ERROR_500);
        }
        return new Router(page);
    }
}