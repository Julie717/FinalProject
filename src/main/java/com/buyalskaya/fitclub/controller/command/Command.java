package com.buyalskaya.fitclub.controller.command;

import com.buyalskaya.fitclub.controller.Router;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    Router execute(HttpServletRequest request);
}