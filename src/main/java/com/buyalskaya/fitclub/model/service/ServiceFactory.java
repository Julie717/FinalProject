package com.buyalskaya.fitclub.model.service;

import com.buyalskaya.fitclub.model.service.impl.UserServiceImpl;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private final UserService userService = new UserServiceImpl();

    private ServiceFactory() {
    }

    public UserService getUserService() {
        return userService;
    }

    public static ServiceFactory getInstance() {
        return instance;
    }
}
