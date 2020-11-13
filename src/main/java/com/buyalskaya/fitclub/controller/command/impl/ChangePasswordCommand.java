package com.buyalskaya.fitclub.controller.command.impl;

import com.buyalskaya.fitclub.controller.AttributeName;
import com.buyalskaya.fitclub.controller.ParameterName;
import com.buyalskaya.fitclub.controller.Router;
import com.buyalskaya.fitclub.controller.command.AddRequestAttribute;
import com.buyalskaya.fitclub.controller.command.Command;
import com.buyalskaya.fitclub.exception.ServiceException;
import com.buyalskaya.fitclub.model.entity.User;
import com.buyalskaya.fitclub.model.service.ServiceFactory;
import com.buyalskaya.fitclub.model.service.UserService;
import com.buyalskaya.fitclub.pageconfiguration.ConfigurationManager;
import com.buyalskaya.fitclub.pageconfiguration.PageConfigName;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ChangePasswordCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(AttributeName.SESSION_USER);
        String oldPassword = request.getParameter(ParameterName.OLD_PASSWORD);
        String newPassword = request.getParameter(ParameterName.NEW_PASSWORD);
        String repeatedNewPassword = request.getParameter(ParameterName.REPEATED_NEW_PASSWORD);
        try {
            UserService userService = ServiceFactory.getInstance().getUserService();
            boolean isUpdate = userService.updatePassword(user, oldPassword, newPassword, repeatedNewPassword);
            request.setAttribute(AttributeName.INCORRECT_PASSWORD, !isUpdate);
            AddRequestAttribute.forClientPage(request, user.getIdUser());
            page = ConfigurationManager.getProperty(PageConfigName.PRIVATE_CABINET);
        } catch (ServiceException ex) {
            logger.log(Level.ERROR, ex);
            page = ConfigurationManager.getProperty(PageConfigName.ERROR_500);
        }
        return new Router(page);
    }
}