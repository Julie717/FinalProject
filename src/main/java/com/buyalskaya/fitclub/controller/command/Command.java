package com.buyalskaya.fitclub.controller.command;

import com.buyalskaya.fitclub.controller.Router;

import javax.servlet.http.HttpServletRequest;

/**
 * The interface Command.
 * The controller transfer the request to commands that must implement this interface
 * for making special actions to receive a respond for client
 *
 * @author Buyalskaya Yuliya
 * @version 1.0
 */
public interface Command {
    /**
     * Execute router.
     *
     * @param request the request
     * @return the router
     */
    Router execute(HttpServletRequest request);
}