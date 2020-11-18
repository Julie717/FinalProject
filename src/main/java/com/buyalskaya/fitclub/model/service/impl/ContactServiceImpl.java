package com.buyalskaya.fitclub.model.service.impl;

import com.buyalskaya.fitclub.exception.DaoException;
import com.buyalskaya.fitclub.exception.ServiceException;
import com.buyalskaya.fitclub.model.dao.DaoFactory;
import com.buyalskaya.fitclub.model.dao.UserDao;
import com.buyalskaya.fitclub.model.entity.Staff;
import com.buyalskaya.fitclub.model.entity.UserRole;
import com.buyalskaya.fitclub.model.service.ContactService;
import com.buyalskaya.fitclub.util.MailCreator;
import com.buyalskaya.fitclub.validator.CommonValidator;

import java.util.List;
import java.util.Map;

public class ContactServiceImpl implements ContactService {
    @Override
    public boolean sendMessageToAdmins(Map<String, String> contactParameters, String locale) throws ServiceException {
        boolean isSend = false;
        if (CommonValidator.isContactParametersValid(contactParameters)) {
            try {
                UserDao userDao = DaoFactory.getInstance().getUserDao();
                List<Staff> admins = userDao.findAllStaffByRole(UserRole.ADMINISTRATOR);
                if (!admins.isEmpty()) {
                    boolean isSendToAdmin;
                    for (Staff admin : admins) {
                        isSendToAdmin = MailCreator.sendMailToAdmin(admin.getEmail(), contactParameters, locale);
                        isSend = isSend || isSendToAdmin;
                    }
                }
            } catch (DaoException ex) {
                throw new ServiceException("Error during registration user", ex);
            }
        }
        return isSend;
    }
}