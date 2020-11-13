package com.buyalskaya.fitclub.controller.command.impl;

import com.buyalskaya.fitclub.controller.Router;
import com.buyalskaya.fitclub.controller.command.Command;
import com.buyalskaya.fitclub.pageconfiguration.ConfigurationManager;
import com.buyalskaya.fitclub.pageconfiguration.PageConfigName;

import javax.servlet.http.HttpServletRequest;

public class DefaultCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty(PageConfigName.INDEX);
        return new Router(page);
    }
}