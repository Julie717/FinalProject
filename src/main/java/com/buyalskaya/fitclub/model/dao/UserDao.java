package com.buyalskaya.fitclub.model.dao;

import com.buyalskaya.fitclub.exception.DaoException;
import com.buyalskaya.fitclub.exception.ServiceException;
import com.buyalskaya.fitclub.model.entity.Client;
import com.buyalskaya.fitclub.model.entity.Staff;
import com.buyalskaya.fitclub.model.entity.User;
import com.buyalskaya.fitclub.model.entity.UserRole;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserDao extends CommonDao<User> {
    String findPasswordByLogin(String login) throws DaoException;

    Optional<User> findUserByLogin(String login) throws DaoException;

    Optional<Staff> findStaffByLogin(String login) throws DaoException;

    boolean add(User user, String password) throws DaoException;

    boolean confirmRegistration(String login) throws DaoException;

    List<Staff> findAllStaffByRole(UserRole userRole) throws DaoException;

    boolean updateUserInfo(User user) throws DaoException;

    boolean updatePassword(int idUser, String password) throws DaoException;

    boolean updateStaffInfo(Staff staff) throws DaoException;

    boolean updateUserAndStaffInfo(Staff staff) throws DaoException;

    boolean updatePhoto(int idUser, InputStream photo) throws DaoException;

    List<Client> findSubscribedClients(int idSchedule) throws DaoException;

    Map<Integer, String> findNameInstructors() throws DaoException;

    String findClientName(int idClient) throws DaoException;

    boolean isInstructorExist(int idInstructor) throws DaoException;

    Map<Integer, String> findUserRoles() throws DaoException;

    Map<Integer, String> findUserStatuses() throws DaoException;

    int countUsersAmount() throws DaoException;

    List<Staff> findUsersInRange(int start, int end) throws DaoException;

    List<Staff> findUserBySurname(String surname) throws DaoException;

 //   boolean updateRoleAndStatus(Staff user) throws DaoException;

    Optional<Staff> findUserOrStaffById(int idUser) throws DaoException;

  //  boolean updateStatus(Staff user) throws DaoException;
  boolean updateRoleFromClientToStaff(Staff user) throws DaoException;
    boolean updateRoleFromStaffToClient(Staff user) throws DaoException;
    boolean updateRoleFromClientToClient(Staff user) throws DaoException;
  boolean updateRoleFromStaffToStaff(Staff user) throws DaoException;
}