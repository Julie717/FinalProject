package com.buyalskaya.fitclub.controller.command.impl.navigation;

import com.buyalskaya.fitclub.controller.Router;
import com.buyalskaya.fitclub.controller.command.AddRequestAttribute;
import com.buyalskaya.fitclub.controller.command.Command;
import com.buyalskaya.fitclub.exception.ServiceException;
import com.buyalskaya.fitclub.util.ConfigurationManager;
import com.buyalskaya.fitclub.util.PageConfigName;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * The type Schedule page command.
 * This command allows to go to the page with information about all schedule
 *
 * @author Buyalskaya Yuliya
 * @version 1.0
 */
public class SchedulePageCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        String page;
        try {
            AddRequestAttribute.forSchedulePage(request);
            page = ConfigurationManager.getProperty(PageConfigName.SCHEDULE);
        } catch (ServiceException ex) {
            logger.log(Level.ERROR, ex);
            page = ConfigurationManager.getProperty(PageConfigName.ERROR_500);
        }
        request.getRequestDispatcher(page);
        return new Router(page);
    }
}