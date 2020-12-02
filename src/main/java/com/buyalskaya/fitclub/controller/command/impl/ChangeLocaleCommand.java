package com.buyalskaya.fitclub.controller.command.impl;

import com.buyalskaya.fitclub.controller.AttributeName;
import com.buyalskaya.fitclub.controller.RequestAttributeHandler;
import com.buyalskaya.fitclub.controller.Router;
import com.buyalskaya.fitclub.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The type Change locale command.
 * This command allows to switch language
 *
 * @author Buyalskaya Yuliya
 * @version 1.0
 */
public class ChangeLocaleCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        RequestAttributeHandler requestAttributeHandler =
                (RequestAttributeHandler) session.getAttribute(AttributeName.REQUEST_ATTRIBUTE_HANDLER);
        if (!session.isNew() && requestAttributeHandler != null) {
            requestAttributeHandler.getAttributes().forEach((key, value) -> request.setAttribute(key, value));

        }
        String page = (String) session.getAttribute(AttributeName.SESSION_CURRENT_PAGE);
        return new Router(page);
    }
}