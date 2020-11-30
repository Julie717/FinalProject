package com.buyalskaya.fitclub.model.dao;

import com.buyalskaya.fitclub.exception.DaoException;
import com.buyalskaya.fitclub.model.entity.*;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The interface User dao.
 *
 * @author Buyalskaya Yuliya
 * @version 1.0
 */
public interface UserDao extends CommonDao<User> {
    /**
     * Find password by login string.
     * Returns password, but if user isn't found returns null
     *
     * @param login the login
     * @return the string
     * @throws DaoException the dao exception
     */
    String findPasswordByLogin(String login) throws DaoException;

    /**
     * Find user by login optional.
     * Returns user, if user isn't found return empty
     *
     * @param login the login
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<User> findUserByLogin(String login) throws DaoException;

    /**
     * Find staff by login optional.
     * Returns staff, if staff isn't found return empty
     *
     * @param login the login
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<Staff> findStaffByLogin(String login) throws DaoException;

    /**
     * Add boolean.
     * Add user to database's table uses and returns true if it's done, else - false
     *
     * @param user     the user
     * @param password the password
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean add(User user, String password) throws DaoException;

    /**
     * Confirm registration boolean.
     * Change user's status to a confirmed.
     *
     * @param login the login
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean confirmRegistration(String login) throws DaoException;

    /**
     * Find all staff by role list.
     * Returns empty list if no staff is found
     *
     * @param userRole the user role
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Staff> findAllStaffByRole(UserRole userRole) throws DaoException;

    /**
     * Update user info boolean.
     * Is used to update user's parameters. Input parameter user
     * with new parameters. User in database is found by id from input parameter
     *
     * @param user the user
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean updateUserInfo(User user) throws DaoException;

    /**
     * Update password boolean.
     * Is used to update password
     *
     * @param idUser   the id user
     * @param password the password
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean updatePassword(int idUser, String password) throws DaoException;

    /**
     * Update staff info boolean.
     * Is used to update staff's parameters. Input parameter staff
     * with new parameters. Staff in database is found by id from input parameter
     *
     * @param staff the staff
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean updateStaffInfo(Staff staff) throws DaoException;

    /**
     * Update user and staff info boolean.
     * Is used to update staff's and user's parameters in two tables. Input parameter staff
     * with new parameters. Staff in database is found by id from input parameter
     *
     * @param staff the staff
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean updateUserAndStaffInfo(Staff staff) throws DaoException;

    /**
     * Update photo boolean.
     *
     * @param idUser the id user
     * @param photo  the photo
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean updatePhoto(int idUser, InputStream photo) throws DaoException;

    /**
     * Find subscribed clients list.
     * Is used to download photo
     *
     * @param idSchedule the id schedule
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Client> findSubscribedClients(int idSchedule) throws DaoException;

    /**
     * Find name instructors map.
     * Is used to find instructor's names. Returns a map with instructor's ids and names
     *
     * @return the map
     * @throws DaoException the dao exception
     */
    Map<Integer, String> findNameInstructors() throws DaoException;

    /**
     * Find client name string.
     * Is used to find client's name. If client isn't found returns null
     *
     * @param idClient the id client
     * @return the string
     * @throws DaoException the dao exception
     */
    String findClientName(int idClient) throws DaoException;

    /**
     * Is instructor exist boolean.
     * Is used to check if id instructor is entered correct
     *
     * @param idInstructor the id instructor
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean isInstructorExist(int idInstructor) throws DaoException;

    /**
     * Find user roles map.
     * Is used to find user's roles. Returns a map with role's ids and names
     *
     * @return the map
     * @throws DaoException the dao exception
     */
    Map<Integer, String> findUserRoles() throws DaoException;

    /**
     * Find user statuses map.
     * Is used to find user's statuses. Returns a map with status's ids and names
     *
     * @return the map
     * @throws DaoException the dao exception
     */
    Map<Integer, String> findUserStatuses() throws DaoException;

    /**
     * Count users amount int.
     * Is used to count amount of users. If users aren't found returns 0.
     *
     * @return the int
     * @throws DaoException the dao exception
     */
    int countUsersAmount() throws DaoException;

    /**
     * Find users in range list.
     * Is used to find any uses from start to end row number.
     * Returns staffs, because staffs have additional parameters
     *
     * @param start the start
     * @param end   the end
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Staff> findUsersInRange(int start, int end) throws DaoException;

    /**
     * Find user by surname list.
     * Is used to find any users by surname.
     * Returns staffs, because staffs have additional parameters
     *
     * @param surname the surname
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Staff> findUserBySurname(String surname) throws DaoException;

    /**
     * Find user status by login user status.
     * Is used to find user's status by login.
     *
     * @param login the login
     * @return the user status
     * @throws DaoException the dao exception
     */
    UserStatus findUserStatusByLogin(String login) throws DaoException;

    /**
     * Find user or staff by id optional.
     * Is used to find any user by id.
     * Returns staff, because staff has additional parameters
     *
     * @param idUser the id user
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<Staff> findUserOrStaffById(int idUser) throws DaoException;

    /**
     * Update role from client to staff boolean.
     * Is used to update client's role to staff's role
     *
     * @param user the user
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean updateRoleFromClientToStaff(Staff user) throws DaoException;

    /**
     * Update role from staff to client boolean.
     * Is used to update staff's role to client's role
     *
     * @param user the user
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean updateRoleFromStaffToClient(Staff user) throws DaoException;

    /**
     * Update role from client to client boolean.
     * Is used to update client's status
     *
     * @param user the user
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean updateRoleFromClientToClient(Staff user) throws DaoException;

    /**
     * Update role from staff to staff boolean.
     * Is used to update staff's role or status
     *
     * @param user the user
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean updateRoleFromStaffToStaff(Staff user) throws DaoException;
}