package com.buyalskaya.fitclub.controller.command.impl.navigation;

import com.buyalskaya.fitclub.controller.AttributeName;
import com.buyalskaya.fitclub.controller.ParameterName;
import com.buyalskaya.fitclub.controller.Router;
import com.buyalskaya.fitclub.controller.command.AddRequestAttribute;
import com.buyalskaya.fitclub.controller.command.Command;
import com.buyalskaya.fitclub.exception.ServiceException;
import com.buyalskaya.fitclub.model.entity.Client;
import com.buyalskaya.fitclub.model.entity.Schedule;
import com.buyalskaya.fitclub.model.service.ScheduleService;
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

public class EditSchedulePageCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String idSchedule = request.getParameter(ParameterName.SCHEDULE_ID);
        String numberWeek = request.getParameter(ParameterName.SCHEDULE_NUMBER_WEEK);
        if (idSchedule == null) {
            idSchedule = (String) session.getAttribute(AttributeName.SCHEDULE_ID);
        }
        if (numberWeek == null) {
            numberWeek = (String) session.getAttribute(AttributeName.SCHEDULE_NUMBER_WEEK);
        }
        session.setAttribute(AttributeName.SCHEDULE_ID, idSchedule);
        session.setAttribute(AttributeName.SCHEDULE_NUMBER_WEEK, numberWeek);
        String page;

        try {
            ScheduleService scheduleService = ServiceFactory.getInstance().getScheduleService();
            Optional<Schedule> schedule = scheduleService.findOneSchedule(idSchedule);
            if (schedule.isPresent()) {
                request.setAttribute(AttributeName.SCHEDULE, schedule.get());
                UserService userService = ServiceFactory.getInstance().getUserService();
                List<Client> subscribedClients = userService.findSubscribedClients(idSchedule);
                request.setAttribute(AttributeName.SUBSCRIBED_USERS, subscribedClients);
                AddRequestAttribute.forChangeSchedulePage(request);
                page = ConfigurationManager.getProperty(PageConfigName.EDIT_SCHEDULE);
            } else {
                logger.log(Level.ERROR, "Schedule isn't found");
                page = ConfigurationManager.getProperty(PageConfigName.ERROR_404);
            }
        } catch (ServiceException ex) {
            logger.log(Level.ERROR, ex);
            page = ConfigurationManager.getProperty(PageConfigName.ERROR_500);
        }
        return new Router(page);
    }
}