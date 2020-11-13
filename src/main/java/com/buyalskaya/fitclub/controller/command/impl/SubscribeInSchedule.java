package com.buyalskaya.fitclub.controller.command.impl;

import com.buyalskaya.fitclub.controller.AttributeName;
import com.buyalskaya.fitclub.controller.ParameterName;
import com.buyalskaya.fitclub.controller.Router;
import com.buyalskaya.fitclub.controller.command.Command;
import com.buyalskaya.fitclub.exception.ServiceException;
import com.buyalskaya.fitclub.model.entity.Schedule;
import com.buyalskaya.fitclub.model.entity.User;
import com.buyalskaya.fitclub.model.service.ServiceFactory;
import com.buyalskaya.fitclub.pageconfiguration.ConfigurationManager;
import com.buyalskaya.fitclub.pageconfiguration.PageConfigName;
import com.buyalskaya.fitclub.util.CommonUtil;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class SubscribeInSchedule implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(AttributeName.SESSION_USER);
        String idSchedule = request.getParameter(ParameterName.SCHEDULE_ID);
        String subscribe = request.getParameter(ParameterName.SUBSCRIBE);
        String numberWeek = request.getParameter(ParameterName.SCHEDULE_NUMBER_WEEK);
        String page = (String) session.getAttribute(AttributeName.SESSION_CURRENT_PAGE);
        try {
            ServiceFactory.getInstance().getScheduleService().subscribeClient(user.getIdUser(),
                    idSchedule, subscribe);
            List<Schedule> schedules = ServiceFactory.getInstance().getScheduleService()
                    .findScheduleWeek(numberWeek, user);
            boolean hasNextWeekSchedule = ServiceFactory.getInstance().getScheduleService()
                    .hasScheduleNextWeek(numberWeek);
            request.setAttribute(AttributeName.HAS_NEXT_WEEK_SCHEDULE, hasNextWeekSchedule);
            request.setAttribute(AttributeName.SCHEDULES, schedules);
            if (!schedules.isEmpty()) {
                request.setAttribute(AttributeName.HALLS_TIME, CommonUtil.findTimesInEachHall(schedules));
                request.setAttribute(AttributeName.HALLS_DATE, CommonUtil.findDatesInEachHall(schedules));
            }
            request.setAttribute(AttributeName.SCHEDULE_NUMBER_WEEK, numberWeek);
        } catch (ServiceException ex) {
            logger.log(Level.ERROR,ex);
            page = ConfigurationManager.getProperty(PageConfigName.ERROR_500);
        }
        return new Router(page);
    }
}