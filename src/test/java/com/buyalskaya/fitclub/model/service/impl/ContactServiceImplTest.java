package com.buyalskaya.fitclub.model.service.impl;

import com.buyalskaya.fitclub.exception.DaoException;
import com.buyalskaya.fitclub.exception.ServiceException;
import com.buyalskaya.fitclub.model.dao.DaoFactory;
import com.buyalskaya.fitclub.model.dao.UserDao;
import com.buyalskaya.fitclub.model.dao.impl.UserDaoImpl;
import com.buyalskaya.fitclub.model.entity.Staff;
import com.buyalskaya.fitclub.model.entity.UserRole;
import com.buyalskaya.fitclub.model.service.ContactService;
import com.buyalskaya.fitclub.model.service.ServiceFactory;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.testng.PowerMockObjectFactory;
import org.testng.IObjectFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.ObjectFactory;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.testng.Assert.*;

@PowerMockIgnore({"com.sun.org.apache.xerces.*", "javax.xml.*", "org.xml.*", "org.w3c.*",
        "com.sun.org.apache.xalan.*", "javax.management.*"})
@PrepareForTest({DaoFactory.class})
public class ContactServiceImplTest {
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
    public void sendMessageToAdminsNegativeTest() {
        ContactService contactService = ServiceFactory.getInstance().getContactService();
        Map<String, String> contactParameters = new HashMap<>();
        contactParameters.put("name", "Виктория Иванова");
        contactParameters.put("phone", "+375299876541");
        contactParameters.put("email", "ivanov_vv@gmail.com");
        contactParameters.put("message", "Здравствуйте! Можно ли записать на занятие во вторник в 19:00 в большой зал?");
        String locale = "ru_RU";
        List<Staff> admins = new ArrayList<>();
        try {
            Mockito.when(userDao.findAllStaffByRole(Mockito.any(UserRole.class))).thenReturn(admins);
            boolean actual = contactService.sendMessageToAdmins(contactParameters, locale);
            assertFalse(actual);
        } catch (ServiceException | DaoException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void sendMessageToAdminsExceptionTest() {
        ContactService contactService = ServiceFactory.getInstance().getContactService();
        Map<String, String> contactParameters = new HashMap<>();
        contactParameters.put("name", "Виктория Иванова");
        contactParameters.put("phone", "+375299876541");
        contactParameters.put("email", "ivanov_vv@gmail.com");
        contactParameters.put("message", "Здравствуйте! Можно ли записать на занятие во вторник в 19:00 в большой зал?");
        String locale = "ru_RU";
        try {
            Mockito.when(userDao.findAllStaffByRole(Mockito.any(UserRole.class))).thenThrow(DaoException.class);
            assertThrows(ServiceException.class, () -> contactService.sendMessageToAdmins(contactParameters, locale));
        } catch (DaoException e) {
            fail(e.getMessage());
        }
    }
}