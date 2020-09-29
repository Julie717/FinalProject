package com.buyalskaya.fitclub.model.dao;

import com.buyalskaya.fitclub.model.dao.impl.UserDaoImpl;

public class DaoFactory {
    private static final DaoFactory instance = new DaoFactory();

    private final UserDao userDao = new UserDaoImpl();

    private DaoFactory() {
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public static DaoFactory getInstance() {
        return instance;
    }
}