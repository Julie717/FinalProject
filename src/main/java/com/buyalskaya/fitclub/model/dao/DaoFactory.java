package com.buyalskaya.fitclub.model.dao;

import com.buyalskaya.fitclub.model.dao.impl.*;

public class DaoFactory {
    private static final DaoFactory INSTANCE = new DaoFactory();

    private final UserDao userDao = new UserDaoImpl();
    private final WorkoutDao workoutDao = new WorkoutDaoImpl();
    private final ScheduleDao scheduleDao = new ScheduleDaoImpl();
    private final MembershipDao membershipDao = new MembershipDaoImpl();
    private final HallDao hallDao = new HallDaoImpl();

    private DaoFactory() {
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public WorkoutDao getWorkoutDao() {
        return workoutDao;
    }

    public ScheduleDao getScheduleDao() {
        return scheduleDao;
    }

    public MembershipDao getMembershipDao() {
        return membershipDao;
    }

    public HallDao getHallDao() {
        return hallDao;
    }

    public static DaoFactory getInstance() {
        return INSTANCE;
    }
}