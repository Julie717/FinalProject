package com.buyalskaya.fitclub.controller.command.impl;

import com.buyalskaya.fitclub.controller.Router;
import com.buyalskaya.fitclub.controller.command.Command;
import com.buyalskaya.fitclub.util.ConfigurationManager;
import com.buyalskaya.fitclub.util.PageConfigName;

import javax.servlet.http.HttpServletRequest;

/**
 * The type Default command.
 *
 * @author Buyalskaya Yuliya
 * @version 1.0
 */
public class DefaultCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty(PageConfigName.INDEX);
        return new Router(page);
    }
}