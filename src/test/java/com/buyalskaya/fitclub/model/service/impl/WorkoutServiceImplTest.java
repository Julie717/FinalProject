package com.buyalskaya.fitclub.model.service.impl;

import com.buyalskaya.fitclub.exception.DaoException;
import com.buyalskaya.fitclub.exception.ServiceException;
import com.buyalskaya.fitclub.model.dao.DaoFactory;
import com.buyalskaya.fitclub.model.dao.WorkoutDao;
import com.buyalskaya.fitclub.model.dao.impl.WorkoutDaoImpl;
import com.buyalskaya.fitclub.model.entity.Workout;
import com.buyalskaya.fitclub.model.service.ServiceFactory;
import com.buyalskaya.fitclub.model.service.WorkoutService;
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
public class WorkoutServiceImplTest {
    DaoFactory daoFactory;
    WorkoutDao workoutDao;

    @ObjectFactory
    public IObjectFactory setObjectFactory() {
        return new PowerMockObjectFactory();
    }

    @BeforeMethod
    public void setUp() {
        PowerMockito.mockStatic(DaoFactory.class);
        daoFactory = mock(DaoFactory.class);
        workoutDao = mock(WorkoutDaoImpl.class);
        Mockito.when(DaoFactory.getInstance()).thenReturn(daoFactory);
        Mockito.when(DaoFactory.getInstance().getWorkoutDao()).thenReturn(workoutDao);
    }

    @Test
    public void findByLevelPositiveTest() {
        WorkoutService workoutService = ServiceFactory.getInstance().getWorkoutService();
        try {
            List<Workout> workouts = new ArrayList<>();
            Mockito.when(workoutDao.findByLevel(Mockito.any(Workout.Level.class))).thenReturn(workouts);
            List<Workout> actual = workoutService.findByLevel(Workout.Level.LOW);
            assertEquals(actual, workouts);
        } catch (ServiceException | DaoException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void findByLevelNegativeTest() {
        WorkoutService workoutService = ServiceFactory.getInstance().getWorkoutService();
        try {
            Mockito.when(workoutDao.findByLevel(Mockito.any(Workout.Level.class))).thenThrow(DaoException.class);
            assertThrows(ServiceException.class, () -> workoutService.findByLevel(Workout.Level.LOW));
        } catch (DaoException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void findNameWorkoutsPositiveTest() {
        WorkoutService workoutService = ServiceFactory.getInstance().getWorkoutService();
        try {
            Map<Integer, String> workouts = new HashMap<>();
            Mockito.when(workoutDao.findNameWorkouts()).thenReturn(workouts);
            Map<Integer, String> actual = workoutService.findNameWorkouts();
            assertEquals(actual, workouts);
        } catch (ServiceException | DaoException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void findNameWorkoutsNegativeTest() {
        WorkoutService workoutService = ServiceFactory.getInstance().getWorkoutService();
        try {
            Mockito.when(workoutDao.findNameWorkouts()).thenThrow(DaoException.class);
            assertThrows(ServiceException.class, () -> workoutService.findNameWorkouts());
        } catch (DaoException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void findAllWorkoutsPositiveTest() {
        WorkoutService workoutService = ServiceFactory.getInstance().getWorkoutService();
        try {
            List<Workout> workouts = new ArrayList<>();
            Mockito.when(workoutDao.findAll()).thenReturn(workouts);
            List<Workout> actual = workoutService.findAllWorkouts();
            assertEquals(actual, workouts);
        } catch (ServiceException | DaoException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void findAllWorkoutsNegativeTest() {
        WorkoutService workoutService = ServiceFactory.getInstance().getWorkoutService();
        try {
            Mockito.when(workoutDao.findAll()).thenThrow(DaoException.class);
            assertThrows(ServiceException.class, () -> workoutService.findAllWorkouts());
        } catch (DaoException e) {
            fail(e.getMessage());
        }
    }
}