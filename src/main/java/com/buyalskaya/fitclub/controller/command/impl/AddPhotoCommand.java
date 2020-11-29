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
import com.buyalskaya.fitclub.util.ConfigurationManager;
import com.buyalskaya.fitclub.util.PageConfigName;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;

public class AddPhotoCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(AttributeName.SESSION_USER);
        try {
            InputStream photo = request.getPart(ParameterName.USER_PHOTO).getInputStream();
            String extension = request.getPart(ParameterName.USER_PHOTO).getContentType();
            UserService userService = ServiceFactory.getInstance().getUserService();
            userService.updatePhoto(user.getIdUser(), photo, extension);
            switch (user.getRole()) {
                case CLIENT -> AddRequestAttribute.forClientPage(request, user.getLogin());
                case INSTRUCTOR -> AddRequestAttribute.forInstructorPage(request, user.getLogin());
                case ADMINISTRATOR -> AddRequestAttribute.forAdministratorPage(request, user.getLogin());
            }
            page = ConfigurationManager.getProperty(PageConfigName.PRIVATE_CABINET);
        } catch (IOException | ServletException | ServiceException ex) {
            logger.log(Level.ERROR, ex);
            page = ConfigurationManager.getProperty(PageConfigName.ERROR_500);
        }
        return new Router(page);
    }
}