package com.buyalskaya.fitclub.model.service;

import com.buyalskaya.fitclub.model.service.impl.*;

public class ServiceFactory {
    private static final ServiceFactory INSTANCE = new ServiceFactory();

    private final UserService userService = new UserServiceImpl();
    private final WorkoutService workoutService = new WorkoutServiceImpl();
    private final ScheduleService scheduleService = new ScheduleServiceImpl();
    private final MembershipService membershipService = new MembershipServiceImpl();
    private final HallService hallService = new HallServiceImpl();

    private ServiceFactory() {
    }

    public UserService getUserService() {
        return userService;
    }

    public WorkoutService getWorkoutService() {
        return workoutService;
    }

    public ScheduleService getScheduleService() {
        return scheduleService;
    }

    public MembershipService getMembershipService() {
        return membershipService;
    }

    public HallService getHallService() {
        return hallService;
    }

    public static ServiceFactory getInstance() {
        return INSTANCE;
    }
}