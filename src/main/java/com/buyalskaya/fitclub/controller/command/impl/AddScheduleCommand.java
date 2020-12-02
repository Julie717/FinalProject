package com.buyalskaya.fitclub.controller.command.impl;

import com.buyalskaya.fitclub.controller.AttributeName;
import com.buyalskaya.fitclub.controller.Router;
import com.buyalskaya.fitclub.controller.command.AddRequestAttribute;
import com.buyalskaya.fitclub.controller.command.Command;
import com.buyalskaya.fitclub.controller.command.ReceiveParameterFromRequest;
import com.buyalskaya.fitclub.exception.ServiceException;
import com.buyalskaya.fitclub.model.service.ScheduleService;
import com.buyalskaya.fitclub.model.service.ServiceFactory;
import com.buyalskaya.fitclub.util.ConfigurationManager;
import com.buyalskaya.fitclub.util.PageConfigName;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * The type Add schedule command.
 * This command allows administrators to add a workout into schedule
 *
 * @author Buyalskaya Yuliya
 * @version 1.0
 */
public class AddScheduleCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        String page;
        Map<String, String> scheduleParameters = ReceiveParameterFromRequest
                .scheduleParameters(request);
        HttpSession session = request.getSession();
        String locale = (String) session.getAttribute(AttributeName.SESSION_LOCALE);
        try {
            ScheduleService scheduleService = ServiceFactory.getInstance().getScheduleService();
            boolean isAdded = scheduleService.addSchedule(scheduleParameters, locale);
            request.setAttribute(AttributeName.SHOW_MODAL_ADD_SCHEDULE, true);
            request.setAttribute(AttributeName.MESSAGE_ABOUT_ADD, isAdded);
            AddRequestAttribute.forChangeSchedulePage(request);
            page = ConfigurationManager.getProperty(PageConfigName.ADD_SCHEDULE);
        } catch (ServiceException ex) {
            logger.log(Level.ERROR, ex);
            page = ConfigurationManager.getProperty(PageConfigName.ERROR_500);
        }
        return new Router(page);
    }
}