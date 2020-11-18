package com.buyalskaya.fitclub.controller.filter;

import com.buyalskaya.fitclub.controller.AttributeName;
import com.buyalskaya.fitclub.controller.ParameterName;
import com.buyalskaya.fitclub.controller.command.CommandAccessControl;
import com.buyalskaya.fitclub.controller.command.CommandType;
import com.buyalskaya.fitclub.model.entity.User;
import com.buyalskaya.fitclub.util.ConfigurationManager;
import com.buyalskaya.fitclub.util.PageConfigName;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

/**
 * The type Access control filter.
 * This filter denies the access to commands than don't
 * available by user's role and returns a mistake 403 (forbidden access)
 *
 * @author Buyalskaya Yuliya
 * @version 1.0
 */
@WebFilter(urlPatterns = {"/*"})
public class AccessControlFilter implements Filter {
    private static final Logger logger = LogManager.getLogger();

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String commandName = request.getParameter(ParameterName.COMMAND_NAME);
        Optional<CommandType> commandType = Arrays.stream(CommandType.values())
                .filter(c -> c.name().equalsIgnoreCase(commandName))
                .findFirst();
        if (commandName != null && commandType.isPresent()) {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute(AttributeName.SESSION_USER);
            Set<CommandType> availableCommands;
            if (user == null) {
                availableCommands = CommandAccessControl.GUEST.getAvailableCommands();
            } else {
                availableCommands = switch (user.getRole()) {
                    case ADMINISTRATOR -> CommandAccessControl.ADMINISTRATOR.getAvailableCommands();
                    case INSTRUCTOR -> CommandAccessControl.INSTRUCTOR.getAvailableCommands();
                    case CLIENT -> CommandAccessControl.CLIENT.getAvailableCommands();
                };
            }
            if (!availableCommands.contains(commandType.get())) {
                String page = ConfigurationManager.getProperty(PageConfigName.ERROR_403);
                logger.log(Level.WARN, "An attempt to receive access to commands that doesn't allow for this user's role");
                request.getRequestDispatcher(page).forward(request, response);
                return;
            }
        }
        chain.doFilter(req, resp);
    }
}