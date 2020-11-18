package com.buyalskaya.fitclub.model.service;

import com.buyalskaya.fitclub.exception.ServiceException;

import java.util.Map;

public interface ContactService {
    boolean sendMessageToAdmins(Map<String, String> contactParameters, String locale) throws ServiceException;
}