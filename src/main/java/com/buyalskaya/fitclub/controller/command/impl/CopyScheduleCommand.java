package com.buyalskaya.fitclub.controller.command.impl;

import com.buyalskaya.fitclub.controller.AttributeName;
import com.buyalskaya.fitclub.controller.Router;
import com.buyalskaya.fitclub.controller.command.AddRequestAttribute;
import com.buyalskaya.fitclub.controller.command.Command;
import com.buyalskaya.fitclub.exception.ServiceException;
import com.buyalskaya.fitclub.model.service.ScheduleService;
import com.buyalskaya.fitclub.model.service.ServiceFactory;
import com.buyalskaya.fitclub.util.ConfigurationManager;
import com.buyalskaya.fitclub.util.PageConfigName;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class CopyScheduleCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        String page;
        try {
            ScheduleService scheduleService = ServiceFactory.getInstance().getScheduleService();
            boolean isAdded = scheduleService.copyScheduleOfLastWeek();
            AddRequestAttribute.forWorkout(request);
            request.setAttribute(AttributeName.SHOW_MODAL_CONTACT_US, true);
            request.setAttribute(AttributeName.IS_COPY_SCHEDULE, isAdded);
            page = ConfigurationManager.getProperty(PageConfigName.HOMEPAGE);
        } catch (ServiceException ex) {
            logger.log(Level.ERROR, ex);
            page = ConfigurationManager.getProperty(PageConfigName.ERROR_500);
        }
        return new Router(page);
    }
}