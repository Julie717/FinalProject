package com.buyalskaya.fitclub.model.service;

import com.buyalskaya.fitclub.exception.ServiceException;

import java.util.Map;

/**
 * The interface Contact service.
 *
 * @author Buyalskaya Yuliya
 * @version 1.0
 */
public interface ContactService {
    /**
     * Send message to admins boolean.
     * Is used to send message from user to all administrators
     *
     * @param contactParameters the contact parameters
     * @param locale            the locale
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean sendMessageToAdmins(Map<String, String> contactParameters, String locale) throws ServiceException;
}