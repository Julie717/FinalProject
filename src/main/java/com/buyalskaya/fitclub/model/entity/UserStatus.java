package com.buyalskaya.fitclub.model.entity;

public enum UserStatus {
    ACTIVE(1),
    UNCONFIRMED(2),
    BLOCKED(3);
    private int userStatusId;

    UserStatus(int userStatusId) {
        this.userStatusId = userStatusId;
    }

    public int getUserStatusId() {
        return userStatusId;
    }
}