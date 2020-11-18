package com.buyalskaya.fitclub.model.dao;

import com.buyalskaya.fitclub.model.dao.impl.*;

/**
 * The type Dao factory.
 * Is used for storing  objects of dao and provides an access to them
 *
 * @author Buyalskaya Yuliya
 * @version 1.0
 */
public class DaoFactory {
    private static final DaoFactory INSTANCE = new DaoFactory();

    private final UserDao userDao = new UserDaoImpl();
    private final WorkoutDao workoutDao = new WorkoutDaoImpl();
    private final ScheduleDao scheduleDao = new ScheduleDaoImpl();
    private final MembershipDao membershipDao = new MembershipDaoImpl();
    private final HallDao hallDao = new HallDaoImpl();

    private DaoFactory() {
    }

    /**
     * Gets user dao.
     *
     * @return the user dao
     */
    public UserDao getUserDao() {
        return userDao;
    }

    /**
     * Gets workout dao.
     *
     * @return the workout dao
     */
    public WorkoutDao getWorkoutDao() {
        return workoutDao;
    }

    /**
     * Gets schedule dao.
     *
     * @return the schedule dao
     */
    public ScheduleDao getScheduleDao() {
        return scheduleDao;
    }

    /**
     * Gets membership dao.
     *
     * @return the membership dao
     */
    public MembershipDao getMembershipDao() {
        return membershipDao;
    }

    /**
     * Gets hall dao.
     *
     * @return the hall dao
     */
    public HallDao getHallDao() {
        return hallDao;
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static DaoFactory getInstance() {
        return INSTANCE;
    }
}