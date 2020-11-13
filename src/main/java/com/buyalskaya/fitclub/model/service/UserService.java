package com.buyalskaya.fitclub.model.service;

import com.buyalskaya.fitclub.exception.ServiceException;
import com.buyalskaya.fitclub.model.entity.Client;
import com.buyalskaya.fitclub.model.entity.Staff;
import com.buyalskaya.fitclub.model.entity.User;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {
    boolean authorization(String login, String password) throws ServiceException;

    Optional<User> findUserByLogin(String login) throws ServiceException;

    Optional<Staff> findStaffByLogin(String login) throws ServiceException;

    boolean registration(Map<String, String> userParameters, String locale) throws ServiceException;

    boolean confirmRegistration(String login) throws ServiceException;

    List<Staff> findAllInstructors() throws ServiceException;

    boolean updateClientInfo(Map<String, String> newParameters, User currentUser, String locale) throws ServiceException;

    boolean updatePassword(User user, String oldPassword, String newPassword,
                           String repeatedNewPassword) throws ServiceException;

    boolean updateStaffInfo(Map<String, String> newParameters, Staff staff, String locale)
            throws ServiceException;

    boolean updatePhoto(int idUser, InputStream photo) throws ServiceException;

    List<Client> findSubscribedClients(String idSchedule) throws ServiceException;

    Map<Integer, String> findNameInstructors() throws ServiceException;

    String findClientName(String idClient) throws ServiceException;

    Map<Integer, String> findUserRoles() throws ServiceException;

    Map<Integer, String> findUserStatuses() throws ServiceException;

    int countPagesAmount(int amountOfRecords) throws ServiceException;

    List<Staff> findUsersInRange(String numberPage, int amountOfRecords) throws ServiceException;

    List<Staff> findUserBySurname(String surname) throws ServiceException;

    boolean updateUserRoleOrStatus(Map<String, String> newParameters, String locale) throws ServiceException;
}