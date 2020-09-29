package com.buyalskaya.fitclub.model.service;

import com.buyalskaya.fitclub.exception.ServiceException;

public interface UserService {
    boolean authorization(String login, String password) throws ServiceException;
    boolean registration (String login, String password) throws ServiceException;
}