package com.buyalskaya.fitclub.model.entity;

/**
 * The enum User role.
 * Includes all possible variants of user's roles in the current project
 *
 * @author Buyalskaya Yuliya
 * @version 1.0
 */
public enum UserRole {
    /**
     * Administrator user role.
     * The main role in this project that provides user to add and
     * edit information in the schedule
     */
    ADMINISTRATOR(1),
    /**
     * Instructor user role.
     * This role is used for instructors whom conduct workouts
     */
    INSTRUCTOR(2),
    /**
     * Client user role.
     * This is the common role that uses for all other register users and
     * gives the opportunity to subscribe on workouts.
     */
    CLIENT(3);
    /**
     * The value is used for storage the role's id
     */
    private int userRoleId;

    UserRole(int userRoleId) {
        this.userRoleId = userRoleId;
    }

    /**
     * Gets user role id.
     *
     * @return the user role id
     */
    public int getUserRoleId() {
        return userRoleId;
    }
}