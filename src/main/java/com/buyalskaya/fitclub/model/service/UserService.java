package com.buyalskaya.fitclub.model.service;

import com.buyalskaya.fitclub.exception.ServiceException;
import com.buyalskaya.fitclub.model.entity.Client;
import com.buyalskaya.fitclub.model.entity.Staff;
import com.buyalskaya.fitclub.model.entity.User;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The interface User service.
 *
 * @author Buyalskaya Yuliya
 * @version 1.0
 */
public interface UserService {
    /**
     * Authorization boolean.
     * Is used to check correspondence of accuracy between login and password
     * from database and that user entered into an authorisation form
     *
     * @param login    the login
     * @param password the password
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean authorization(String login, String password) throws ServiceException;

    /**
     * Find user by login optional.
     * Is used to find user by login
     *
     * @param login the login
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<User> findUserByLogin(String login) throws ServiceException;

    /**
     * Find staff by login optional.
     * Is used to find staff by login
     *
     * @param login the login
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<Staff> findStaffByLogin(String login) throws ServiceException;

    /**
     * Registration boolean.
     * Is used to add user as a client(status - unconfirmed) to the database
     *
     * @param userParameters the user parameters
     * @param locale         the locale
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean registration(Map<String, String> userParameters, String locale) throws ServiceException;

    /**
     * Confirm registration boolean.
     * Is used to change a client status from unconfirmed to active
     *
     * @param login the login
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean confirmRegistration(String login) throws ServiceException;

    /**
     * Find all instructors list.
     * Is used to find all instructors
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Staff> findAllInstructors() throws ServiceException;

    /**
     * Update client info boolean.
     * Is used to change user's parameters in database
     *
     * @param newParameters the new parameters
     * @param currentUser   the current user
     * @param locale        the locale
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean updateClientInfo(Map<String, String> newParameters, User currentUser, String locale) throws ServiceException;

    /**
     * Update password boolean.
     * Is used to change user's password in database
     *
     * @param user                the user
     * @param oldPassword         the old password
     * @param newPassword         the new password
     * @param repeatedNewPassword the repeated new password
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean updatePassword(User user, String oldPassword, String newPassword,
                           String repeatedNewPassword) throws ServiceException;

    /**
     * Update staff info boolean.
     * Is used to change staff's parameters in database
     *
     * @param newParameters the new parameters
     * @param staff         the staff
     * @param locale        the locale
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean updateStaffInfo(Map<String, String> newParameters, Staff staff, String locale)
            throws ServiceException;

    /**
     * Update photo boolean.
     * Is used to add user's photo
     *
     * @param idUser the id user
     * @param photo  the photo
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean updatePhoto(int idUser, InputStream photo) throws ServiceException;

    /**
     * Find subscribed clients list.
     * Is used to find users that subscribed to workout
     *
     * @param idSchedule the id schedule
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Client> findSubscribedClients(String idSchedule) throws ServiceException;

    /**
     * Find name instructors map.
     * Is used to find instructor's names. Returns a map with ids and names of instructors
     *
     * @return the map
     * @throws ServiceException the service exception
     */
    Map<Integer, String> findNameInstructors() throws ServiceException;

    /**
     * Find client name string.
     * Is used to find client name.
     *
     * @param idClient the id client
     * @return the string
     * @throws ServiceException the service exception
     */
    String findClientName(String idClient) throws ServiceException;

    /**
     * Find user roles map.
     * Is used to find user's roles. Returns a map with ids and names of roles
     *
     * @return the map
     * @throws ServiceException the service exception
     */
    Map<Integer, String> findUserRoles() throws ServiceException;

    /**
     * Find user statuses map.
     * Is used to find user's statuses. Returns a map with ids and names of statuses
     *
     * @return the map
     * @throws ServiceException the service exception
     */
    Map<Integer, String> findUserStatuses() throws ServiceException;

    /**
     * Count pages amount int.
     * Is used to count amount of pages for showing all users.
     * amountOfRecords is a number of records that will be shown on the one page
     *
     * @param amountOfRecords the amount of records
     * @return the int
     * @throws ServiceException the service exception
     */
    int countPagesAmount(int amountOfRecords) throws ServiceException;

    /**
     * Find users in range list.
     * Is used to find amountOfRecords users to the number of page
     *
     * @param numberPage      the number page
     * @param amountOfRecords the amount of records
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Staff> findUsersInRange(String numberPage, int amountOfRecords) throws ServiceException;

    /**
     * Find user by surname list.
     * Is used to find users by surname
     *
     * @param surname the surname
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Staff> findUserBySurname(String surname) throws ServiceException;

    /**
     * Update user role or status boolean.
     * Is used to change user's role or status
     *
     * @param newParameters the new parameters
     * @param locale        the locale
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean updateUserRoleOrStatus(Map<String, String> newParameters, String locale) throws ServiceException;
}