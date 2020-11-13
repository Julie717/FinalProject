package com.buyalskaya.fitclub.controller.command.impl.navigation;

import com.buyalskaya.fitclub.controller.ParameterName;
import com.buyalskaya.fitclub.controller.Router;
import com.buyalskaya.fitclub.controller.command.AddRequestAttribute;
import com.buyalskaya.fitclub.controller.command.Command;
import com.buyalskaya.fitclub.exception.ServiceException;
import com.buyalskaya.fitclub.pageconfiguration.ConfigurationManager;
import com.buyalskaya.fitclub.pageconfiguration.PageConfigName;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;


public class AllUserPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        String numberPage = request.getParameter(ParameterName.USERS_NUMBER_PAGE);
        String page;
        try {
            AddRequestAttribute.forAllUsersPage(request, null, numberPage);
            page = ConfigurationManager.getProperty(PageConfigName.ALL_USERS);
        } catch (ServiceException ex) {
            logger.log(Level.ERROR, ex);
            page = ConfigurationManager.getProperty(PageConfigName.ERROR_500);
        }
        return new Router(page);
    }
}