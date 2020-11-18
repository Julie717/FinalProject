package com.buyalskaya.fitclub.controller.command.impl.navigation;

import com.buyalskaya.fitclub.controller.AttributeName;
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

public class HomePageCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        String page;
        try {
            AddRequestAttribute.forWorkout(request);
            if (request.getAttribute(AttributeName.SHOW_MODAL) == null) {
                request.setAttribute(AttributeName.SHOW_MODAL, false);
            }
            page = ConfigurationManager.getProperty(PageConfigName.HOMEPAGE);
        } catch (ServiceException ex) {
            logger.log(Level.ERROR,ex);
            page = ConfigurationManager.getProperty(PageConfigName.ERROR_500);
        }
        request.getRequestDispatcher(page);
        return new Router(page);
    }
}