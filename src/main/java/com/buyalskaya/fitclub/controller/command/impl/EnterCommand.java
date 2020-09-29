package com.buyalskaya.fitclub.controller.command.impl;

import com.buyalskaya.fitclub.controller.command.Command;
import com.buyalskaya.fitclub.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class EnterCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return ConfigurationManager.getProperty("page.login");
    }
}