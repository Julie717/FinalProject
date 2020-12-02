package com.buyalskaya.fitclub.controller.command.impl;

import com.buyalskaya.fitclub.controller.Router;
import com.buyalskaya.fitclub.controller.command.Command;
import com.buyalskaya.fitclub.util.ConfigurationManager;
import com.buyalskaya.fitclub.util.PageConfigName;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The type Logout command.
 * This command allows to logout from the site
 *
 * @author Buyalskaya Yuliya
 * @version 1.0
 */
public class LogoutCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty(PageConfigName.INDEX);
        HttpSession session = request.getSession();
        session.invalidate();
        return new Router(Router.DisPathType.REDIRECT, page);
    }
}