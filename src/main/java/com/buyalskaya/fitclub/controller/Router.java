package com.buyalskaya.fitclub.controller;

import com.buyalskaya.fitclub.pageconfiguration.PageConfigName;

public class Router {
    public enum DisPathType {
        FORWARD,
        REDIRECT
    }

    private DisPathType disPathType = DisPathType.FORWARD;
    private String page = PageConfigName.INDEX;
    public Router() {
    }

    public Router(String page) {
        this.page = page;
    }

    public Router(DisPathType disPathType, String page) {
        this.disPathType = disPathType;
        this.page = page;
    }

    public DisPathType getDisPathType() {
        return disPathType;
    }

    public void setDisPathType(DisPathType disPathType) {
        this.disPathType = disPathType;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }
}