package com.buyalskaya.fitclub.model.service.impl;

import com.buyalskaya.fitclub.exception.DaoException;
import com.buyalskaya.fitclub.exception.ServiceException;
import com.buyalskaya.fitclub.model.dao.DaoFactory;
import com.buyalskaya.fitclub.model.dao.UserDao;
import com.buyalskaya.fitclub.model.dao.impl.UserDaoImpl;
import com.buyalskaya.fitclub.model.entity.Staff;
import com.buyalskaya.fitclub.model.entity.User;
import com.buyalskaya.fitclub.model.entity.UserStatus;
import com.buyalskaya.fitclub.model.service.ServiceFactory;
import com.buyalskaya.fitclub.model.service.UserService;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.testng.PowerMockObjectFactory;
import org.testng.IObjectFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.ObjectFactory;
import org.testng.annotations.Test;

import java.util.*;

import static org.mockito.Mockito.mock;
import static org.testng.Assert.*;
import static org.testng.Assert.fail;

@PowerMockIgnore({"com.sun.org.apache.xerces.*", "javax.xml.*", "org.xml.*", "org.w3c.*",
        "com.sun.org.apache.xalan.*", "javax.management.*"})
@PrepareForTest({DaoFactory.class})
public class UserServiceImplTest {
    DaoFactory daoFactory;
    UserDao userDao;

    @ObjectFactory
    public IObjectFactory setObjectFactory() {
        return new PowerMockObjectFactory();
    }

    @BeforeMethod
    public void setUp() {
        PowerMockito.mockStatic(DaoFactory.class);
        daoFactory = mock(DaoFactory.class);
        userDao = mock(UserDaoImpl.class);
        Mockito.when(DaoFactory.getInstance()).thenReturn(daoFactory);
        Mockito.when(daoFactory.getUserDao()).thenReturn(userDao);
    }

    @Test
    public void authorizationPositiveTest() {
        UserService userService = ServiceFactory.getInstance().getUserService();
        String login = "malyshko_k";
        String password = "qwerTy123";
        try {
            Optional<String> correctPassword = Optional.of("$2a$10$2cpQF7Q.U5aovP5QNTjN/uaQefhI6TEXgXGJarm2xL2b.OIOLhZ/K");
            Mockito.when(userDao.findPasswordByLogin(Mockito.anyString())).thenReturn(correctPassword);
            boolean actual = userService.authorization(login, password);
            assertTrue(actual);
        } catch (ServiceException | DaoException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void authorizationNegativeTest() {
        UserService userService = ServiceFactory.getInstance().getUserService();
        String login = "";
        String password = "qwerTy123";
        try {
            boolean actual = userService.authorization(login, password);
            assertFalse(actual);
        } catch (ServiceException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void findUserByLoginPositiveTest() {
        UserService userService = ServiceFactory.getInstance().getUserService();
        String login = "malyshko_k";
        User user = new User();
        try {
            Mockito.when(userDao.findUserByLogin(Mockito.anyString())).thenReturn(Optional.of(user));
            Optional<User> actual = userService.findUserByLogin(login);
            assertEquals(actual, Optional.of(user));
        } catch (ServiceException | DaoException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void findUserByLoginNegativeTest() {
        UserService userService = ServiceFactory.getInstance().getUserService();
        String login = "malyshko_k";
        try {
            Mockito.when(userDao.findUserByLogin(Mockito.anyString())).thenReturn(Optional.empty());
            Optional<User> actual = userService.findUserByLogin(login);
            assertEquals(actual, Optional.empty());
        } catch (ServiceException | DaoException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void findStaffByLoginPositiveTest() {
        UserService userService = ServiceFactory.getInstance().getUserService();
        String login = "malyshko_k";
        Staff staff = new Staff();
        try {
            Mockito.when(userDao.findStaffByLogin(Mockito.anyString())).thenReturn(Optional.of(staff));
            Optional<Staff> actual = userService.findStaffByLogin(login);
            assertEquals(actual, Optional.of(staff));
        } catch (ServiceException | DaoException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void findStaffByLoginNegativeTest() {
        UserService userService = ServiceFactory.getInstance().getUserService();
        String login = "malyshko_k";
        try {
            Mockito.when(userDao.findStaffByLogin(Mockito.anyString())).thenReturn(Optional.empty());
            Optional<Staff> actual = userService.findStaffByLogin(login);
            assertEquals(actual, Optional.empty());
        } catch (ServiceException | DaoException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void registrationPositiveTest() {
        UserService userService = ServiceFactory.getInstance().getUserService();
        Map<String, String> userParameters = new HashMap<>();
        userParameters.put("login", "ivanova_vv");
        userParameters.put("password", "qwerTy123");
        userParameters.put("repeatedPassword", "qwerTy123");
        userParameters.put("name", "Виктория");
        userParameters.put("surname", "Иванова");
        userParameters.put("phone", "+375299876541");
        userParameters.put("email", "ivanov_vv@gmail.com");
        userParameters.put("birthday", "01.10.1986");
        String locale = "ru_RU";
        try {
            Mockito.when(userDao.findUserByLogin(Mockito.anyString())).thenReturn(Optional.empty());
            Mockito.when(userDao.add(Mockito.any(User.class), Mockito.anyString())).thenReturn(true);
            boolean actual = userService.registration(userParameters, locale);
            assertTrue(actual);
        } catch (ServiceException | DaoException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void registrationNegativeTest() {
        UserService userService = ServiceFactory.getInstance().getUserService();
        Map<String, String> userParameters = new HashMap<>();
        userParameters.put("login", "ivanova_vv");
        userParameters.put("password", "qwerTy123");
        userParameters.put("repeatedPassword", "qwerTy123");
        userParameters.put("name", "Виктория");
        userParameters.put("surname", "Иванова");
        userParameters.put("phone", "+375299876541");
        userParameters.put("email", "ivanov_vv@gmail.com");
        userParameters.put("birthday", "01.10.1986");
        String locale = "ru_RU";
        User user = new User();
        try {
            Mockito.when(userDao.findUserByLogin(Mockito.anyString())).thenReturn(Optional.of(user));
            boolean actual = userService.registration(userParameters, locale);
            assertFalse(actual);
        } catch (ServiceException | DaoException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void confirmRegistrationPositiveTest() {
        UserService userService = ServiceFactory.getInstance().getUserService();
        String login = "malyshko_k";
        try {
            Mockito.when(userDao.findUserStatusByLogin(Mockito.anyString())).thenReturn(UserStatus.UNCONFIRMED);
            Mockito.when(userDao.confirmRegistration(Mockito.anyString())).thenReturn(true);
            boolean actual = userService.confirmRegistration(login);
            assertTrue(actual);
        } catch (ServiceException | DaoException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void confirmRegistrationNegativeTest() {
        UserService userService = ServiceFactory.getInstance().getUserService();
        String login = "malyshko_k";
        try {
            Mockito.when(userDao.findUserStatusByLogin(Mockito.anyString())).thenReturn(UserStatus.BLOCKED);
            Mockito.when(userDao.confirmRegistration(Mockito.anyString())).thenReturn(false);
            boolean actual = userService.confirmRegistration(login);
            assertFalse(actual);
        } catch (ServiceException | DaoException e) {
            fail(e.getMessage());
        }
    }
}