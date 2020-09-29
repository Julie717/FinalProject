package com.buyalskaya.fitclub.controller;

import com.buyalskaya.fitclub.controller.command.ActionProvider;
import com.buyalskaya.fitclub.controller.command.Command;
import com.buyalskaya.fitclub.controller.command.CommandType;
import com.buyalskaya.fitclub.resource.ConfigurationManager;
import com.buyalskaya.fitclub.resource.MessageManager;
import com.buyalskaya.fitclub.util.Encryptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/controller")
public class ProjectController extends HttpServlet {
    private static final String COMMAND_NAME = "commandName";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandName = request.getParameter(COMMAND_NAME);
        Command command = ActionProvider.defineCommand(commandName);
        String page = command.execute(request);
        if (page != null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
        } else {
            page = ConfigurationManager.getProperty("path.page.index");
            request.getSession().setAttribute("nullPage",
                    MessageManager.getProperty("message.nullpage"));
            response.sendRedirect(request.getContextPath() + page);
        }
    }
}