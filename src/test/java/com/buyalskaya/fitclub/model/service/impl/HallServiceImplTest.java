package com.buyalskaya.fitclub.model.service.impl;

import com.buyalskaya.fitclub.exception.DaoException;
import com.buyalskaya.fitclub.exception.ServiceException;
import com.buyalskaya.fitclub.model.dao.DaoFactory;
import com.buyalskaya.fitclub.model.dao.HallDao;
import com.buyalskaya.fitclub.model.dao.impl.HallDaoImpl;
import com.buyalskaya.fitclub.model.service.HallService;
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

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.*;

@PowerMockIgnore({"com.sun.org.apache.xerces.*", "javax.xml.*", "org.xml.*", "org.w3c.*",
        "com.sun.org.apache.xalan.*", "javax.management.*"})
@PrepareForTest({DaoFactory.class})
public class HallServiceImplTest {
    DaoFactory daoFactory;
    HallDao hallDao;

    @ObjectFactory
    public IObjectFactory setObjectFactory() {
        return new PowerMockObjectFactory();
    }

    @BeforeMethod
    public void setUp() {
        PowerMockito.mockStatic(DaoFactory.class);
        daoFactory = Mockito.mock(DaoFactory.class);
        hallDao = Mockito.mock(HallDaoImpl.class);
        Mockito.when(DaoFactory.getInstance()).thenReturn(daoFactory);
        Mockito.when(daoFactory.getHallDao()).thenReturn(hallDao);
    }

    @Test
    public void findNameHallsPositiveTest() {
        HallService hallService = ServiceFactory.getInstance().getHallService();
        Map<Integer, String> hallNames = new HashMap<>();
        hallNames.put(1, "Big");
        hallNames.put(2, "Small");
        try {
            Mockito.when(hallDao.findNameHalls()).thenReturn(hallNames);
            Map<Integer, String> actual = hallService.findNameHalls();
            assertEquals(actual, hallNames);
        } catch (ServiceException | DaoException ex) {
            fail(ex.getMessage());
        }
    }

    @Test
    public void findNameHallsNegativeTest() throws DaoException {
        HallService hallService = ServiceFactory.getInstance().getHallService();
        Mockito.when(hallDao.findNameHalls()).thenThrow(DaoException.class);
        assertThrows(ServiceException.class, () -> hallService.findNameHalls());
    }
}