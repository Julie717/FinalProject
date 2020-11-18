package com.buyalskaya.fitclub.model.service.impl;

import com.buyalskaya.fitclub.exception.DaoException;
import com.buyalskaya.fitclub.exception.ServiceException;
import com.buyalskaya.fitclub.model.dao.DaoFactory;
import com.buyalskaya.fitclub.model.dao.MembershipDao;
import com.buyalskaya.fitclub.model.dao.impl.MembershipDaoImpl;
import com.buyalskaya.fitclub.model.entity.ClientMembership;
import com.buyalskaya.fitclub.model.entity.Membership;
import com.buyalskaya.fitclub.model.service.MembershipService;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

@PowerMockIgnore({"com.sun.org.apache.xerces.*", "javax.xml.*", "org.xml.*", "org.w3c.*",
        "com.sun.org.apache.xalan.*", "javax.management.*"})
@PrepareForTest({DaoFactory.class})
public class MembershipServiceImplTest {
    DaoFactory daoFactory;
    MembershipDao membershipDao;

    @ObjectFactory
    public IObjectFactory setObjectFactory() {
        return new PowerMockObjectFactory();
    }

    @BeforeMethod
    public void setUp() {
        PowerMockito.mockStatic(DaoFactory.class);
        daoFactory = Mockito.mock(DaoFactory.class);
        membershipDao = Mockito.mock(MembershipDaoImpl.class);
        Mockito.when(DaoFactory.getInstance()).thenReturn(daoFactory);
        Mockito.when(daoFactory.getMembershipDao()).thenReturn(membershipDao);
    }

    @Test
    public void findActiveClientMembershipsPositiveTest() {
        MembershipService membershipService = ServiceFactory.getInstance().getMembershipService();
        List<ClientMembership> memberships = new ArrayList<>();
        try {
            Mockito.when(membershipDao.findActiveClientMemberships(Mockito.anyInt())).thenReturn(memberships);
            List<ClientMembership> actual = membershipService.findActiveClientMemberships(15);
            assertEquals(actual, memberships);
        } catch (ServiceException | DaoException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void findActiveClientMembershipsNegativeTest() throws DaoException {
        MembershipService membershipService = ServiceFactory.getInstance().getMembershipService();
        Mockito.when(membershipDao.findActiveClientMemberships(Mockito.anyInt())).thenThrow(DaoException.class);
        assertThrows(ServiceException.class, () -> membershipService.findActiveClientMemberships(1));
    }

    @Test
    public void findActiveClientMembershipsStringPositiveTest() {
        MembershipService membershipService = ServiceFactory.getInstance().getMembershipService();
        List<ClientMembership> memberships = new ArrayList<>();
        try {
            Mockito.when(membershipDao.findActiveClientMemberships(Mockito.anyInt())).thenReturn(memberships);
            List<ClientMembership> actual = membershipService.findActiveClientMemberships("15");
            assertEquals(actual, memberships);
        } catch (ServiceException | DaoException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void findActiveClientMembershipsStringNegativeTest() throws DaoException {
        MembershipService membershipService = ServiceFactory.getInstance().getMembershipService();
        Mockito.when(membershipDao.findActiveClientMemberships(Mockito.anyInt())).thenThrow(DaoException.class);
        assertThrows(ServiceException.class, () -> membershipService.findActiveClientMemberships(1));
    }

    @Test
    public void findAllMembershipsPositiveTest() {
        MembershipService membershipService = ServiceFactory.getInstance().getMembershipService();
        List<Membership> memberships = new ArrayList<>();
        try {
            Mockito.when(membershipDao.findAll()).thenReturn(memberships);
            List<Membership> actual = membershipService.findAllMemberships();
            assertEquals(actual, memberships);
        } catch (ServiceException | DaoException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void findAllMembershipsNegativeTest() throws DaoException {
        MembershipService membershipService = ServiceFactory.getInstance().getMembershipService();
        Mockito.when(membershipDao.findAll()).thenThrow(DaoException.class);
        assertThrows(ServiceException.class, () -> membershipService.findAllMemberships());
    }

    @Test
    public void addClientMembershipPositiveTest() {
        MembershipService membershipService = ServiceFactory.getInstance().getMembershipService();
        try {
            String idMembership = "10";
            String openDate = "15.11.2020";
            String idClient = "8";
            String locale = "ru_RU";
            int classesAmount = 8;
            Mockito.when(membershipDao.findClassesAmountInMembership(Mockito.anyInt()))
                    .thenReturn(classesAmount);
            Mockito.when(membershipDao.addClientMembership(10,
                    8, LocalDate.of(2020, 11, 15), classesAmount))
                    .thenReturn(true);
            boolean actual = membershipService.addClientMembership(idMembership, openDate, idClient, locale);
            assertTrue(actual);
        } catch (DaoException | ServiceException ex) {
            fail(ex.getMessage());
        }
    }

    @Test
    public void addClientMembershipNegativeTest() {
        MembershipService membershipService = ServiceFactory.getInstance().getMembershipService();
        try {
            String idMembership = "10";
            String openDate = "5.5.2020";
            String idClient = "8";
            String locale = "ru_RU";
            boolean actual = membershipService.addClientMembership(idMembership, openDate,
                    idClient, locale);
            assertFalse(actual);
        } catch (ServiceException ex) {
            fail(ex.getMessage());
        }
    }

    @Test
    public void addClientMembershipExceptionTest() throws DaoException {
        String idMembership = "10";
        String openDate = "15.11.2020";
        String idClient = "8";
        String locale = "ru_RU";
        MembershipService membershipService = ServiceFactory.getInstance().getMembershipService();
        Mockito.when(membershipDao.findClassesAmountInMembership(Mockito.anyInt())).thenThrow(DaoException.class);
        assertThrows(ServiceException.class, () -> membershipService.addClientMembership(idMembership, openDate,
                idClient, locale));
    }

    @Test
    public void checkClientPresencePositiveTest() {
        MembershipService membershipService = ServiceFactory.getInstance().getMembershipService();
        try {
            String idClient = "8";
            String idSchedule = "315";
            String idClientMembership = "25";
            boolean isPresent = true;
            int classesAmount = 8;
            int remainingClasses = 3;
            Mockito.when(membershipDao.findClassesAmountInClientMembership(Mockito.anyInt()))
                    .thenReturn(classesAmount);
            Mockito.when(membershipDao.findRemainingClassesInClientMembership(Mockito.anyInt()))
                    .thenReturn(remainingClasses);
            Mockito.when(membershipDao.minusWorkoutInMembership(25, 8, 315))
                    .thenReturn(true);
            boolean actual = membershipService.checkClientPresence(idClient, idSchedule, idClientMembership, isPresent);
            assertTrue(actual);
        } catch (DaoException | ServiceException ex) {
            fail(ex.getMessage());
        }
    }

    @Test
    public void checkClientPresenceNegativeTest() {
        MembershipService membershipService = ServiceFactory.getInstance().getMembershipService();
        try {
            String idClient = "8";
            String idSchedule = "315";
            String idClientMembership = "25";
            boolean isPresent = true;
            int classesAmount = 8;
            int remainingClasses = 0;
            Mockito.when(membershipDao.findClassesAmountInClientMembership(Mockito.anyInt()))
                    .thenReturn(classesAmount);
            Mockito.when(membershipDao.findRemainingClassesInClientMembership(Mockito.anyInt()))
                    .thenReturn(remainingClasses);
            Mockito.when(membershipDao.minusWorkoutInMembership(25, 8, 315))
                    .thenReturn(true);
            boolean actual = membershipService.checkClientPresence(idClient, idSchedule, idClientMembership, isPresent);
            assertFalse(actual);
        } catch (DaoException | ServiceException ex) {
            fail(ex.getMessage());
        }
    }

    @Test
    public void checkClientPresenceExceptionTest() throws DaoException {
        String idClient = "8";
        String idSchedule = "315";
        String idClientMembership = "25";
        boolean isPresent = true;
        int classesAmount = 8;
        MembershipService membershipService = ServiceFactory.getInstance().getMembershipService();
        Mockito.when(membershipDao.findClassesAmountInClientMembership(Mockito.anyInt()))
                .thenReturn(classesAmount);
        Mockito.when(membershipDao.findRemainingClassesInClientMembership(Mockito.anyInt()))
                .thenThrow(DaoException.class);
        assertThrows(ServiceException.class, () -> membershipService.checkClientPresence(idClient, idSchedule,
                idClientMembership, isPresent));
    }
}