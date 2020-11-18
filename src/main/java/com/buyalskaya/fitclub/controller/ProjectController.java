package com.buyalskaya.fitclub.controller;

import com.buyalskaya.fitclub.controller.command.CommandProvider;
import com.buyalskaya.fitclub.controller.command.Command;
import com.buyalskaya.fitclub.model.connection.ConnectionPool;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The type Project controller.
 * It is used for communication between client and server
 *
 * @author Buyalskaya Yuliya
 * @version 1.0
 */
@WebServlet(urlPatterns = "/controller")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 5 * 5)
public class ProjectController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Process request.
     * Receives a request from the client, call an appropriate command
     * and makes forward or redirect to the next page that command returned
     *
     * @param request  the request
     * @param response the response
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandName = request.getParameter(ParameterName.COMMAND_NAME);
        Command command = CommandProvider.defineCommand(commandName);
        Router router = command.execute(request);
        if (Router.DisPathType.FORWARD.equals(router.getDisPathType())) {
            request.getRequestDispatcher(router.getPage()).forward(request, response);
        } else {
            response.sendRedirect(router.getPage());
        }
    }

    @Override
    public void destroy() {
        ConnectionPool.INSTANCE.destroyPool();
    }
}