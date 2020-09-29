package com.buyalskaya.fitclub.controller.command.impl;

import com.buyalskaya.fitclub.controller.command.Command;
import com.buyalskaya.fitclub.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class EmptyCommand implements Command {
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";

    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("page.login");
        return page;
    }
}