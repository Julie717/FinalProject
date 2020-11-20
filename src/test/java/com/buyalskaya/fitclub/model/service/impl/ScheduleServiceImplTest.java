package com.buyalskaya.fitclub.model.service.impl;

import com.buyalskaya.fitclub.exception.DaoException;
import com.buyalskaya.fitclub.exception.ServiceException;
import com.buyalskaya.fitclub.model.dao.DaoFactory;
import com.buyalskaya.fitclub.model.dao.ScheduleDao;
import com.buyalskaya.fitclub.model.dao.UserDao;
import com.buyalskaya.fitclub.model.dao.impl.ScheduleDaoImpl;
import com.buyalskaya.fitclub.model.dao.impl.UserDaoImpl;
import com.buyalskaya.fitclub.model.entity.*;
import com.buyalskaya.fitclub.model.service.ScheduleService;
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
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.testng.Assert.*;

@PowerMockIgnore({"com.sun.org.apache.xerces.*", "javax.xml.*", "org.xml.*", "org.w3c.*",
        "com.sun.org.apache.xalan.*", "javax.management.*"})
@PrepareForTest({DaoFactory.class})
public class ScheduleServiceImplTest {
    DaoFactory daoFactory;
    ScheduleDao scheduleDao;
    UserDao userDao;

    @ObjectFactory
    public IObjectFactory setObjectFactory() {
        return new PowerMockObjectFactory();
    }

    @BeforeMethod
    public void setUp() {
        PowerMockito.mockStatic(DaoFactory.class);
        daoFactory = mock(DaoFactory.class);
        scheduleDao = mock(ScheduleDaoImpl.class);
        userDao = mock(UserDaoImpl.class);
        Mockito.when(DaoFactory.getInstance()).thenReturn(daoFactory);
        Mockito.when(daoFactory.getScheduleDao()).thenReturn(scheduleDao);
        Mockito.when(daoFactory.getUserDao()).thenReturn(userDao);
    }

    @Test
    public void findScheduleWeekClientTest() {
        ScheduleService scheduleService = ServiceFactory.getInstance().getScheduleService();
        List<Schedule> schedules = new ArrayList<>();
        User user = new User();
        user.setRole(UserRole.CLIENT);
        try {
            Mockito.when(scheduleDao.findAllScheduleForClient(Mockito.any(LocalDate.class),
                    Mockito.any(LocalDate.class), Mockito.anyInt())).thenReturn(schedules);
            List<Schedule> actual = scheduleService.findScheduleWeek("0", user);
            assertEquals(actual, schedules);
        } catch (ServiceException | DaoException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void findScheduleWeekAdminTest() {
        ScheduleService scheduleService = ServiceFactory.getInstance().getScheduleService();
        List<Schedule> schedules = new ArrayList<>();
        User user = new User();
        user.setRole(UserRole.ADMINISTRATOR);
        try {
            Mockito.when(scheduleDao.findSchedule(Mockito.any(LocalDate.class),
                    Mockito.any(LocalDate.class))).thenReturn(schedules);
            List<Schedule> actual = scheduleService.findScheduleWeek("1", user);
            assertEquals(actual, schedules);
        } catch (ServiceException | DaoException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void findScheduleWeekNegativeTest() throws DaoException {
        ScheduleService scheduleService = ServiceFactory.getInstance().getScheduleService();
        Mockito.when(scheduleDao.findSchedule(Mockito.any(LocalDate.class),
                Mockito.any(LocalDate.class))).thenThrow(DaoException.class);
        User user = new User();
        user.setRole(UserRole.ADMINISTRATOR);
        assertThrows(ServiceException.class, () -> scheduleService.findScheduleWeek("1", user));
    }

    @Test
    public void hasScheduleNextWeekPositiveTest() {
        ScheduleService scheduleService = ServiceFactory.getInstance().getScheduleService();
        List<Schedule> schedules = new ArrayList<>();
        Schedule schedule = new Schedule();
        schedule.setIdSchedule(115);
        schedules.add(schedule);
        User user = new User();
        user.setRole(UserRole.ADMINISTRATOR);
        try {
            Mockito.when(scheduleDao.findSchedule(Mockito.any(LocalDate.class),
                    Mockito.any(LocalDate.class))).thenReturn(schedules);
            boolean actual = scheduleService.hasScheduleNextWeek("0");
            assertTrue(actual);
        } catch (ServiceException | DaoException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void hasScheduleNextWeekNegativeTest() {
        ScheduleService scheduleService = ServiceFactory.getInstance().getScheduleService();
        List<Schedule> schedules = new ArrayList<>();
        User user = new User();
        user.setRole(UserRole.ADMINISTRATOR);
        try {
            Mockito.when(scheduleDao.findSchedule(Mockito.any(LocalDate.class),
                    Mockito.any(LocalDate.class))).thenReturn(schedules);
            boolean actual = scheduleService.hasScheduleNextWeek("1");
            assertFalse(actual);
        } catch (ServiceException | DaoException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void hasScheduleNextWeekExceptionTest() throws DaoException {
        ScheduleService scheduleService = ServiceFactory.getInstance().getScheduleService();
        Mockito.when(scheduleDao.findSchedule(Mockito.any(LocalDate.class),
                Mockito.any(LocalDate.class))).thenThrow(DaoException.class);
        assertThrows(ServiceException.class, () -> scheduleService.hasScheduleNextWeek("1"));
    }

    @Test
    public void findClientSchedulePositiveTest() {
        ScheduleService scheduleService = ServiceFactory.getInstance().getScheduleService();
        List<ClientSchedule> schedules = new ArrayList<>();
        try {
            Mockito.when(scheduleDao.findClientSchedule(Mockito.anyInt())).thenReturn(schedules);
            List<ClientSchedule> actual = scheduleService.findClientSchedule(15);
            assertEquals(actual, schedules);
        } catch (ServiceException | DaoException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void findClientScheduleNegativeTest() throws DaoException {
        ScheduleService scheduleService = ServiceFactory.getInstance().getScheduleService();
        Mockito.when(scheduleDao.findClientSchedule(Mockito.anyInt())).thenThrow(DaoException.class);
        assertThrows(ServiceException.class, () -> scheduleService.findClientSchedule(10));
    }

    @Test
    public void findInstructorSchedulePositiveTest() {
        ScheduleService scheduleService = ServiceFactory.getInstance().getScheduleService();
        List<ClientSchedule> schedules = new ArrayList<>();
        try {
            Mockito.when(scheduleDao.findInstructorSchedule(Mockito.anyInt())).thenReturn(schedules);
            List<ClientSchedule> actual = scheduleService.findInstructorSchedule(15);
            assertEquals(actual, schedules);
        } catch (ServiceException | DaoException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void findInstructorScheduleNegativeTest() throws DaoException {
        ScheduleService scheduleService = ServiceFactory.getInstance().getScheduleService();
        Mockito.when(scheduleDao.findInstructorSchedule(Mockito.anyInt())).thenThrow(DaoException.class);
        assertThrows(ServiceException.class, () -> scheduleService.findInstructorSchedule(10));
    }

    @Test
    public void subscribeClientPositiveTest() {
        ScheduleService scheduleService = ServiceFactory.getInstance().getScheduleService();
        int idUser = 10;
        String idSchedule = "375";
        String subscribe = "true";
        try {
            Mockito.when(scheduleDao.unsubscribeClient(Mockito.anyInt(), Mockito.anyInt())).thenReturn(true);
            boolean actual = scheduleService.subscribeClient(idUser, idSchedule, subscribe);
            assertTrue(actual);
        } catch (ServiceException | DaoException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void subscribeClientNegativeTest() {
        ScheduleService scheduleService = ServiceFactory.getInstance().getScheduleService();
        int idUser = 10;
        String idSchedule = "375";
        String subscribe = "false";
        try {
            Mockito.when(scheduleDao.subscribeClient(Mockito.anyInt(), Mockito.anyInt())).thenReturn(false);
            boolean actual = scheduleService.subscribeClient(idUser, idSchedule, subscribe);
            assertFalse(actual);
        } catch (ServiceException | DaoException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void subscribeClientExceptionTest() throws DaoException {
        int idUser = 10;
        String idSchedule = "375";
        String subscribe = "false";
        ScheduleService scheduleService = ServiceFactory.getInstance().getScheduleService();
        Mockito.when(scheduleDao.subscribeClient(Mockito.anyInt(), Mockito.anyInt())).thenThrow(DaoException.class);
        assertThrows(ServiceException.class, () -> scheduleService.subscribeClient(idUser, idSchedule, subscribe));
    }

    @Test
    public void findOneSchedulePositiveTest() {
        ScheduleService scheduleService = ServiceFactory.getInstance().getScheduleService();
        Schedule schedule = new Schedule();
        try {
            Mockito.when(scheduleDao.findOneSchedule(Mockito.anyInt())).thenReturn(Optional.of(schedule));
            Optional<Schedule> actual = scheduleService.findOneSchedule("18");
            assertEquals(actual.get(), schedule);
        } catch (ServiceException | DaoException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void findOneScheduleNegativeTest() throws DaoException {
        ScheduleService scheduleService = ServiceFactory.getInstance().getScheduleService();
        Mockito.when(scheduleDao.findOneSchedule(Mockito.anyInt())).thenThrow(DaoException.class);
        assertThrows(ServiceException.class, () -> scheduleService.findOneSchedule("157"));
    }

    @Test
    public void deleteWorkoutFromSchedulePositiveTest() {
        ScheduleService scheduleService = ServiceFactory.getInstance().getScheduleService();
        Schedule schedule = new Schedule();
        try {
            List<Client> subscribedClients = new ArrayList<>();
            Mockito.when(userDao.findSubscribedClients(Mockito.anyInt())).thenReturn(subscribedClients);
            Mockito.when(scheduleDao.findOneSchedule(Mockito.anyInt())).thenReturn(Optional.of(schedule));
            Mockito.when(scheduleDao.deleteWorkoutFromSchedule(Mockito.anyInt())).thenReturn(true);
            boolean actual = scheduleService.deleteWorkoutFromSchedule("18", "ru_RU");
            assertTrue(actual);
        } catch (ServiceException | DaoException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void deleteWorkoutFromScheduleNegativeTest() {
        ScheduleService scheduleService = ServiceFactory.getInstance().getScheduleService();
        try {
            boolean actual = scheduleService.deleteWorkoutFromSchedule("a18", "ru_RU");
            assertFalse(actual);
        } catch (ServiceException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void deleteWorkoutFromScheduleExceptionTest() throws DaoException {
        ScheduleService scheduleService = ServiceFactory.getInstance().getScheduleService();
        Mockito.when(userDao.findSubscribedClients(Mockito.anyInt())).thenThrow(DaoException.class);
        assertThrows(ServiceException.class, () -> scheduleService.deleteWorkoutFromSchedule("157", "ru_RU"));
    }
}