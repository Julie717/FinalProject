package com.buyalskaya.fitclub.model.service.impl;

import com.buyalskaya.fitclub.controller.ParameterName;
import com.buyalskaya.fitclub.exception.DaoException;
import com.buyalskaya.fitclub.exception.ServiceException;
import com.buyalskaya.fitclub.model.creator.UserCreator;
import com.buyalskaya.fitclub.model.dao.UserDao;
import com.buyalskaya.fitclub.model.entity.*;
import com.buyalskaya.fitclub.model.dao.DaoFactory;
import com.buyalskaya.fitclub.model.service.UserService;
import com.buyalskaya.fitclub.util.DateTimeTransformer;
import com.buyalskaya.fitclub.util.Encryptor;
import com.buyalskaya.fitclub.util.ImageTransformer;
import com.buyalskaya.fitclub.validator.CommonValidator;
import com.buyalskaya.fitclub.validator.UserValidator;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.*;

public class UserServiceImpl implements UserService {

    @Override
    public boolean authorization(String login, String password) throws ServiceException {
        boolean isCorrectAuthorization = false;
        if (UserValidator.isLoginValid(login) && UserValidator.isPasswordValid(password)) {
            try {
                UserDao userDao = DaoFactory.getInstance().getUserDao();
                String correctPassword = userDao.findPasswordByLogin(login);
                if (Encryptor.isEqualPasswords(password, correctPassword)) {
                    isCorrectAuthorization = true;
                }
            } catch (DaoException ex) {
                throw new ServiceException("Error during authorization user", ex);
            }
        }
        return isCorrectAuthorization;
    }

    @Override
    public Optional<User> findUserByLogin(String login) throws ServiceException {
        Optional<User> user;
        if (UserValidator.isLoginValid(login)) {
            try {
                UserDao userDao=DaoFactory.getInstance().getUserDao();
                user =userDao.findUserByLogin(login);
            } catch (DaoException ex) {
                throw new ServiceException("Error during find user", ex);
            }
        } else {
            user = Optional.empty();
        }
        return user;
    }

    @Override
    public Optional<Staff> findStaffByLogin(String login) throws ServiceException {
        Optional<Staff> staff;
        if (UserValidator.isLoginValid(login)) {
            try {
                UserDao userDao=DaoFactory.getInstance().getUserDao();
                staff = userDao.findStaffByLogin(login);
            } catch (DaoException ex) {
                throw new ServiceException("Error during find staff", ex);
            }
        } else {
            staff = Optional.empty();
        }
        return staff;
    }

    @Override
    public boolean registration(Map<String, String> userParameters, String locale) throws ServiceException {
        boolean isRegister = false;
        if (UserValidator.isUserParametersValid(userParameters)) {
            String login = userParameters.get(ParameterName.USER_LOGIN);
            try {
                UserDao userDao=DaoFactory.getInstance().getUserDao();
                boolean isUserNotExist = userDao.findUserByLogin(login).isEmpty();
                if (isUserNotExist) {
                    String encryptPassword = Encryptor.encryptPassword(userParameters.get(ParameterName.USER_PASSWORD));
                    User user= UserCreator.createUserWithAllParameters(userParameters,locale);
                    isRegister = userDao.add(user, encryptPassword);
                } else {
                    userParameters.put(ParameterName.DUPLICATE_LOGIN, login);
                }
            } catch (DaoException ex) {
                throw new ServiceException("Error during registration user", ex);
            }
        }
        return isRegister;
    }

    @Override
    public boolean confirmRegistration(String login) throws ServiceException {
        boolean isConfirmed;
        try {
            UserDao userDao=DaoFactory.getInstance().getUserDao();
            isConfirmed =userDao.confirmRegistration(login);
        } catch (DaoException ex) {
            throw new ServiceException("Error during confirm registration user", ex);
        }
        return isConfirmed;
    }

    @Override
    public List<Staff> findAllInstructors() throws ServiceException {
        List<Staff> instructors;
        try {
            UserDao userDao=DaoFactory.getInstance().getUserDao();
            instructors = userDao.findAllStaffByRole(UserRole.INSTRUCTOR);
        } catch (DaoException ex) {
            throw new ServiceException("Error during find instructors", ex);
        }
        return instructors;
    }

    @Override
    public boolean updateClientInfo(Map<String, String> newParameters, User currentUser, String locale)
            throws ServiceException {
        boolean isUpdate = changeUserParameters(newParameters, currentUser, locale);
        if (isUpdate) {
            try {
                UserDao userDao=DaoFactory.getInstance().getUserDao();
                isUpdate = userDao.updateUserInfo(currentUser);
            } catch (DaoException ex) {
                throw new ServiceException("Error during update client's information", ex);
            }
        }
        return isUpdate;
    }

    @Override
    public boolean updateStaffInfo(Map<String, String> newParameters, Staff staff, String locale)
            throws ServiceException {
        boolean hasNewUserParameters = changeUserParameters(newParameters, staff, locale);
        boolean hasNewStaffParameters = changeStaffParameters(newParameters, staff);
        boolean isUpdate = false;
        try {
            UserDao userDao=DaoFactory.getInstance().getUserDao();
            if (hasNewUserParameters) {
                if (hasNewStaffParameters) {
                    isUpdate = userDao.updateUserAndStaffInfo(staff);
                } else {
                    isUpdate = userDao.updateUserInfo(staff);
                }
            } else {
                if (hasNewStaffParameters) {
                    isUpdate = userDao.updateStaffInfo(staff);
                }
            }
        } catch (DaoException ex) {
            throw new ServiceException("Error during update staff's information", ex);
        }
        return isUpdate;
    }

    @Override
    public boolean updatePassword(User user, String oldPassword, String newPassword,
                                  String repeatedNewPassword) throws ServiceException {
        boolean isUpdate = false;
        if (UserValidator.isPasswordValid(oldPassword) &&
                UserValidator.isPasswordValid(newPassword) &&
                UserValidator.isPasswordValid(repeatedNewPassword)) {
            if (newPassword.equals(repeatedNewPassword)) {
                try {
                    UserDao userDao=DaoFactory.getInstance().getUserDao();
                    String currentPassword = userDao.findPasswordByLogin(user.getLogin());
                    if (Encryptor.isEqualPasswords(oldPassword, currentPassword)) {
                        String encryptPassword = Encryptor.encryptPassword(newPassword);
                        isUpdate = userDao.updatePassword(user.getIdUser(), encryptPassword);
                    }
                } catch (DaoException ex) {
                    throw new ServiceException("Error during change password", ex);
                }
            }
        }
        return isUpdate;
    }

    private boolean changeUserParameters(Map<String, String> newParameters, User currentUser, String locale) {
        boolean isChanged = UserValidator.isUserUpdateParametersValid(newParameters);
        if (isChanged) {
            if (newParameters.containsKey(ParameterName.USER_LOGIN)) {
                currentUser.setLogin(newParameters.get(ParameterName.USER_LOGIN));
            }
            if (newParameters.containsKey(ParameterName.USER_NAME)) {
                currentUser.setName(newParameters.get(ParameterName.USER_NAME));
            }
            if (newParameters.containsKey(ParameterName.USER_SURNAME)) {
                currentUser.setSurname(newParameters.get(ParameterName.USER_SURNAME));
            }
            if (newParameters.containsKey(ParameterName.USER_PHONE)) {
                currentUser.setPhoneNumber(newParameters.get(ParameterName.USER_PHONE));
            }
            if (newParameters.containsKey(ParameterName.USER_EMAIL)) {
                currentUser.setEmail(newParameters.get(ParameterName.USER_EMAIL));
            }
            if (newParameters.containsKey(ParameterName.USER_BIRTHDAY)) {
                String birthday = newParameters.get(ParameterName.USER_BIRTHDAY);
                LocalDate birthdayDate = DateTimeTransformer.fromStringToLocalDate(birthday, locale);
                currentUser.setBirthday(birthdayDate);
            }
        }
        return isChanged;
    }

    private boolean changeStaffParameters(Map<String, String> newParameters, Staff staff) {
        boolean isChanged = UserValidator.isStaffUpdateParametersValid(newParameters);
        if (isChanged) {
            if (newParameters.containsKey(ParameterName.WORK_EXPERIENCE)) {
                int workExperience = Integer.parseInt(newParameters.get(ParameterName.WORK_EXPERIENCE));
                staff.setWorkExperience(workExperience);
            }
            if (newParameters.containsKey(ParameterName.STAFF_DESCRIPTION)) {
                staff.setDescription(newParameters.get(ParameterName.STAFF_DESCRIPTION));
            }
        }
        return isChanged;
    }

    @Override
    public boolean updatePhoto(int idUser, InputStream photo) throws ServiceException {
        boolean isUpdate = false;
        if (UserValidator.isPhotoExtensionValid(photo)) {
            InputStream decreaseImage = ImageTransformer.decreaseImage(photo);
            try {
                UserDao userDao=DaoFactory.getInstance().getUserDao();
                isUpdate = userDao.updatePhoto(idUser, decreaseImage);
            } catch (DaoException ex) {
                throw new ServiceException("Error during download photo", ex);
            }
        }
        return isUpdate;
    }

    @Override
    public List<Client> findSubscribedClients(String idSchedule) throws ServiceException {
        List<Client> subscribedUsers = null;
        if (CommonValidator.isIdValid(idSchedule)) {
            int idScheduleInt = Integer.parseInt(idSchedule);
            try {
                UserDao userDao=DaoFactory.getInstance().getUserDao();
                subscribedUsers = userDao.findSubscribedClients(idScheduleInt);
            } catch (DaoException ex) {
                throw new ServiceException("Error during find subscribed clients", ex);
            }
        }
        return subscribedUsers;
    }

    @Override
    public Map<Integer, String> findNameInstructors() throws ServiceException {
        Map<Integer, String> instructors;
        try {
            UserDao userDao=DaoFactory.getInstance().getUserDao();
            instructors = userDao.findNameInstructors();
        } catch (DaoException ex) {
            throw new ServiceException("Error during find instructors", ex);
        }
        return instructors;
    }

    @Override
    public String findClientName(String idClient) throws ServiceException {
        String clientName = null;
        if (CommonValidator.isIdValid(idClient)) {
            int idClientInt = Integer.parseInt(idClient);
            try {
                UserDao userDao=DaoFactory.getInstance().getUserDao();
                clientName = userDao.findClientName(idClientInt);
            } catch (DaoException ex) {
                throw new ServiceException("Error during find client name", ex);
            }
        }
        return clientName;
    }

    @Override
    public Map<Integer, String> findUserRoles() throws ServiceException {
        Map<Integer, String> roles;
        try {
            UserDao userDao=DaoFactory.getInstance().getUserDao();
            roles = userDao.findUserRoles();
        } catch (DaoException ex) {
            throw new ServiceException("Error during find roles", ex);
        }
        return roles;
    }

    @Override
    public Map<Integer, String> findUserStatuses() throws ServiceException {
        Map<Integer, String> statuses;
        try {
            UserDao userDao=DaoFactory.getInstance().getUserDao();
            statuses = userDao.findUserStatuses();
        } catch (DaoException ex) {
            throw new ServiceException("Error during find statuses", ex);
        }
        return statuses;
    }

    @Override
    public int countPagesAmount(int amountOfRecords) throws ServiceException {
        int pagesAmount;
        try {
            UserDao userDao=DaoFactory.getInstance().getUserDao();
            int usersAmount =userDao.countUsersAmount();
            pagesAmount = usersAmount / amountOfRecords;
            if (usersAmount % amountOfRecords > 0) {
                pagesAmount++;
            }
        } catch (DaoException ex) {
            throw new ServiceException("Error during find amount of users", ex);
        }
        return pagesAmount;
    }

    @Override
    public List<Staff> findUsersInRange(String numberPage, int amountOfRecords) throws ServiceException {
        List<Staff> users;
        int pageNumber = 1;
        if (CommonValidator.isPositiveInteger(numberPage)) {
            pageNumber = Integer.parseInt(numberPage);
        }
        int start = (pageNumber - 1) * amountOfRecords + 1;
        int end = pageNumber * amountOfRecords;
        try {
            UserDao userDao=DaoFactory.getInstance().getUserDao();
            users = userDao.findUsersInRange(start, end);
        } catch (DaoException ex) {
            throw new ServiceException("Error during find users in range", ex);
        }
        return users;
    }

    @Override
    public List<Staff> findUserBySurname(String surname) throws ServiceException {
        List<Staff> users;
        try {
            UserDao userDao=DaoFactory.getInstance().getUserDao();
            users = userDao.findUserBySurname(surname);
        } catch (DaoException ex) {
            throw new ServiceException("Error during find users surname", ex);
        }
        return users;
    }

    @Override
    public boolean updateUserRoleOrStatus(Map<String, String> newParameters, String locale) throws ServiceException {
        boolean isUpdate = false;
        if (UserValidator.isUserParametersForChangeRoleOrStatusValid(newParameters)) {
           Staff user=UserCreator.createStaffOrClient(newParameters,locale);
            try {
                UserDao userDao=DaoFactory.getInstance().getUserDao();
                Optional<Staff> currentUser =userDao.findUserOrStaffById(user.getIdUser());
                if (currentUser.isPresent()) {
                    if (user.getRole() == UserRole.CLIENT && currentUser.get().getRole() == UserRole.CLIENT) {
                        isUpdate = userDao.updateRoleFromClientToClient(user);
                    }
                    if (user.getRole() == UserRole.CLIENT && currentUser.get().getRole() != UserRole.CLIENT) {
                        isUpdate = userDao.updateRoleFromStaffToClient(user);
                    }
                    if (user.getRole() != UserRole.CLIENT && currentUser.get().getRole() == UserRole.CLIENT) {
                        isUpdate = userDao.updateRoleFromClientToStaff(user);
                    }
                    if (user.getRole() != UserRole.CLIENT && currentUser.get().getRole() != UserRole.CLIENT) {
                        isUpdate =userDao.updateRoleFromStaffToStaff(user);
                    }
                }
            } catch (DaoException ex) {
                throw new ServiceException("Error during update user role or status", ex);
            }
        }
        return isUpdate;
    }
}