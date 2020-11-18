package com.buyalskaya.fitclub.controller;

import com.buyalskaya.fitclub.util.PageConfigName;

/**
 * The type Router.
 * Uses for returning a type of going to page
 *
 * @author Buyalskaya Yuliya
 * @version 1.0
 */
public class Router {
    /**
     * The enum Dis path type.
     */
    public enum DisPathType {
        /**
         * Forward dis path type.
         */
        FORWARD,
        /**
         * Redirect dis path type.
         */
        REDIRECT
    }

    private DisPathType disPathType = DisPathType.FORWARD;
    private String page = PageConfigName.INDEX;

    /**
     * Instantiates a new Router.
     */
    public Router() {
    }

    /**
     * Instantiates a new Router.
     *
     * @param page the page
     */
    public Router(String page) {
        this.page = page;
    }

    /**
     * Instantiates a new Router.
     *
     * @param disPathType the dis path type
     * @param page        the page
     */
    public Router(DisPathType disPathType, String page) {
        this.disPathType = disPathType;
        this.page = page;
    }

    /**
     * Gets dis path type.
     *
     * @return the dis path type
     */
    public DisPathType getDisPathType() {
        return disPathType;
    }

    /**
     * Sets dis path type.
     *
     * @param disPathType the dis path type
     */
    public void setDisPathType(DisPathType disPathType) {
        this.disPathType = disPathType;
    }

    /**
     * Gets page.
     *
     * @return the page
     */
    public String getPage() {
        return page;
    }

    /**
     * Sets page.
     *
     * @param page the page
     */
    public void setPage(String page) {
        this.page = page;
    }
}