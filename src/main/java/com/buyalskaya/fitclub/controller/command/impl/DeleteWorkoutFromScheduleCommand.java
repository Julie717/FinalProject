package com.buyalskaya.fitclub.controller.command.impl;

import com.buyalskaya.fitclub.controller.AttributeName;
import com.buyalskaya.fitclub.controller.ParameterName;
import com.buyalskaya.fitclub.controller.Router;
import com.buyalskaya.fitclub.controller.command.AddRequestAttribute;
import com.buyalskaya.fitclub.controller.command.Command;
import com.buyalskaya.fitclub.exception.ServiceException;
import com.buyalskaya.fitclub.model.service.ScheduleService;
import com.buyalskaya.fitclub.model.service.ServiceFactory;
import com.buyalskaya.fitclub.pageconfiguration.ConfigurationManager;
import com.buyalskaya.fitclub.pageconfiguration.PageConfigName;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class DeleteWorkoutFromScheduleCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String idSchedule = (String) session.getAttribute(ParameterName.SCHEDULE_ID);
        String numberWeek = (String) session.getAttribute(ParameterName.SCHEDULE_NUMBER_WEEK);
        String locale = (String) session.getAttribute(AttributeName.SESSION_LOCALE);
        String page;
        try {
            ScheduleService scheduleService = ServiceFactory.getInstance().getScheduleService();
            boolean isDeleted = scheduleService.deleteWorkoutFromSchedule(idSchedule, locale);
            if (isDeleted) {
                request.setAttribute(AttributeName.SCHEDULE_NUMBER_WEEK, numberWeek);
                AddRequestAttribute.forSchedulePage(request);
                request.setAttribute(AttributeName.MESSAGE_ABOUT_DELETE, true);
                page = ConfigurationManager.getProperty(PageConfigName.SCHEDULE);
            } else {
                request.setAttribute(AttributeName.SCHEDULE_ID, idSchedule);
                request.setAttribute(AttributeName.SCHEDULE_NUMBER_WEEK, numberWeek);
                page = ConfigurationManager.getProperty(PageConfigName.EDIT_SCHEDULE);
            }
            request.setAttribute(AttributeName.SHOW_MODAL_DELETE_SCHEDULE, true);
        } catch (ServiceException ex) {
            logger.log(Level.ERROR, ex);
            page = ConfigurationManager.getProperty(PageConfigName.ERROR_500);
        }
        return new Router(page);
    }
}