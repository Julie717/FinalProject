package com.buyalskaya.fitclub.controller.filter;

import com.buyalskaya.fitclub.controller.AttributeName;
import com.buyalskaya.fitclub.controller.ParameterName;
import com.buyalskaya.fitclub.controller.command.CommandAccessControl;
import com.buyalskaya.fitclub.controller.command.CommandType;
import com.buyalskaya.fitclub.model.entity.User;
import com.buyalskaya.fitclub.pageconfiguration.ConfigurationManager;
import com.buyalskaya.fitclub.pageconfiguration.PageConfigName;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

@WebFilter(urlPatterns = {"/*"})
public class AccessControlFilter implements Filter {

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
                request.getRequestDispatcher(page).forward(request, response);
                return;
            }
        }
        chain.doFilter(req, resp);
    }
}