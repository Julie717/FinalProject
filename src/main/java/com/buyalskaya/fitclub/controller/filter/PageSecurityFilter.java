package com.buyalskaya.fitclub.controller.filter;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The type Page security filter.
 * This filter doesn't allow to receive access to pages from jsp address.
 * Users must use commands.
 *
 * @author Buyalskaya Yuliya
 * @version 1.0
 */
@WebFilter(urlPatterns = "/jsp/*")
public class PageSecurityFilter implements Filter {
    private static final Logger logger = LogManager.getLogger();
    private static final int FORBIDDEN_ERROR = 403;

    public void doFilter(ServletRequest req, ServletResponse resp,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) resp;
        logger.log(Level.WARN, "An attempt to receive access to jsp outside commands");
        response.sendError(FORBIDDEN_ERROR);
    }
}