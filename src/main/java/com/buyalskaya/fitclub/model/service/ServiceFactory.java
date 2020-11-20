package com.buyalskaya.fitclub.model.service;

import com.buyalskaya.fitclub.model.service.impl.*;

/**
 * The type Service factory.
 * Is used for storing  objects of services and provides an access to them
 *
 * @author Buyalskaya Yuliya
 * @version 1.0
 */
public class ServiceFactory {
    private static final ServiceFactory INSTANCE = new ServiceFactory();

    private final UserService userService = new UserServiceImpl();
    private final WorkoutService workoutService = new WorkoutServiceImpl();
    private final ScheduleService scheduleService = new ScheduleServiceImpl();
    private final MembershipService membershipService = new MembershipServiceImpl();
    private final HallService hallService = new HallServiceImpl();
    private final ContactService contactService = new ContactServiceImpl();

    private ServiceFactory() {
    }

    /**
     * Gets user service.
     *
     * @return the user service
     */
    public UserService getUserService() {
        return userService;
    }

    /**
     * Gets workout service.
     *
     * @return the workout service
     */
    public WorkoutService getWorkoutService() {
        return workoutService;
    }

    /**
     * Gets schedule service.
     *
     * @return the schedule service
     */
    public ScheduleService getScheduleService() {
        return scheduleService;
    }

    /**
     * Gets membership service.
     *
     * @return the membership service
     */
    public MembershipService getMembershipService() {
        return membershipService;
    }

    /**
     * Gets hall service.
     *
     * @return the hall service
     */
    public HallService getHallService() {
        return hallService;
    }

    /**
     * Gets contact service.
     *
     * @return the contact service
     */
    public ContactService getContactService() {
        return contactService;
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static ServiceFactory getInstance() {
        return INSTANCE;
    }
}