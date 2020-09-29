package com.buyalskaya.fitclub.model.service.impl;

import com.buyalskaya.fitclub.exception.DaoException;
import com.buyalskaya.fitclub.exception.ServiceException;
import com.buyalskaya.fitclub.model.dao.DaoFactory;
import com.buyalskaya.fitclub.model.entity.User;
import com.buyalskaya.fitclub.model.service.UserService;
import com.buyalskaya.fitclub.util.Encryptor;
import com.buyalskaya.fitclub.validator.UserValidator;

import java.util.Optional;

public class UserServiceImpl implements UserService {
    @Override
    public boolean authorization(String login, String password) throws ServiceException {
        UserValidator userValidator = new UserValidator();
        boolean isCorrect = false;
        if (userValidator.isLoginValid(login) && userValidator.isPasswordValid(password)) {
            String encryptPassword = Encryptor.encryptPassword(password);
            try {
                Optional<User> user = DaoFactory.getInstance().getUserDao().authorization(login, encryptPassword);
                isCorrect = !user.isEmpty();
            } catch (DaoException ex) {
                throw new ServiceException("Error during authorization user", ex);
            }
        }
        return isCorrect;
    }

    @Override
    public boolean registration(String login, String password) throws ServiceException {
        return false;
    }
}
