package com.buyalskaya.fitclub.model.dao;

import com.buyalskaya.fitclub.exception.DaoException;
import com.buyalskaya.fitclub.model.entity.User;

import java.util.Optional;

public interface UserDao {
    Optional<User> authorization(String login, String password) throws DaoException;

    boolean registration(User user) throws DaoException;
}