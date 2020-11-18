package com.buyalskaya.fitclub.controller.filter;

import com.buyalskaya.fitclub.controller.AttributeName;
import com.buyalskaya.fitclub.controller.ParameterName;
import com.buyalskaya.fitclub.controller.RequestAttributeHandler;
import com.buyalskaya.fitclub.controller.command.CommandType;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Change locale filter.
 * This filter changes locale in session, returns current page
 * and stores the information from current request
 *
 * @author Buyalskaya Yuliya
 * @version 1.0
 */
@WebFilter(dispatcherTypes = {
        DispatcherType.REQUEST,
        DispatcherType.FORWARD
}, urlPatterns = {"/*"})
public class ChangeLocaleFilter implements Filter {
    private static final String DEFAULT_LOCALE = "ru_RU";
    private static final String REGEX_URL = "(/[\\w/_]*.jsp)";

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession();
        saveRequestAttributes(request);
        String locale = (String) session.getAttribute(AttributeName.SESSION_LOCALE);
        String pageUrl = request.getRequestURL().toString();
        String pageName = findPageName(pageUrl);
        if (pageName != null) {
            session.setAttribute(AttributeName.SESSION_CURRENT_PAGE, pageName);
        }
        if (locale == null) {
            session.setAttribute(AttributeName.SESSION_LOCALE, DEFAULT_LOCALE);
        }
        String newLocale = request.getParameter(AttributeName.SESSION_LOCALE);
        if (newLocale != null) {
            session.setAttribute(AttributeName.SESSION_LOCALE, newLocale);
        }
        chain.doFilter(req, resp);
    }

    private void saveRequestAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession();
        RequestAttributeHandler requestAttributeHandler;
        if (session.isNew()) {
            requestAttributeHandler = new RequestAttributeHandler();
        } else {
            requestAttributeHandler = (RequestAttributeHandler) session.getAttribute(AttributeName.REQUEST_ATTRIBUTE_HANDLER);
        }
        String commandName = request.getParameter(ParameterName.COMMAND_NAME);
        if (CommandType.CHANGE_LOCALE.name().equalsIgnoreCase(commandName)) {
            request.removeAttribute(AttributeName.SHOW_MODAL);
            request.removeAttribute(AttributeName.SHOW_MODAL_CONFIRM_REGISTRATION);
            request.removeAttribute(AttributeName.INCORRECT_PASSWORD);
            request.removeAttribute(AttributeName.INCORRECT_LOGIN_OR_PASSWORD);
            request.removeAttribute(AttributeName.SHOW_MODAL_DELETE_SCHEDULE);
            request.removeAttribute(AttributeName.SHOW_MODAL_REGISTRATION);
            request.removeAttribute(AttributeName.SHOW_MODAL_ADD_SCHEDULE);
            request.removeAttribute(AttributeName.SHOW_MODAL_CONTACT_US);
        }
        requestAttributeHandler.setAttributes(request);
        session.setAttribute(AttributeName.REQUEST_ATTRIBUTE_HANDLER, requestAttributeHandler);
    }

    private String findPageName(String pageUrl) {
        Pattern pattern = Pattern.compile(REGEX_URL);
        Matcher matcher = pattern.matcher(pageUrl);
        String pageName = null;
        if (matcher.find()) {
            pageName = matcher.group(0);
        }
        return pageName;
    }
}