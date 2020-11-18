package com.buyalskaya.fitclub.model.entity;

/**
 * The enum User status.
 * Includes all possible variants of user's status in the current project
 *
 * @author Buyalskaya Yuliya
 * @version 1.0
 */
public enum UserStatus {
    /**
     * Active user status.
     */
    ACTIVE(1),
    /**
     * Unconfirmed user status.
     * It uses for users who don't confirm the registration through e-mail
     */
    UNCONFIRMED(2),
    /**
     * Blocked user status.
     */
    BLOCKED(3);
    /**
     * The value is used for storage the status's id
     */
    private int userStatusId;

    UserStatus(int userStatusId) {
        this.userStatusId = userStatusId;
    }

    /**
     * Gets user status id.
     *
     * @return the user status id
     */
    public int getUserStatusId() {
        return userStatusId;
    }
}