package com.buyalskaya.fitclub.model.entity;

public enum UserRole {
    ADMINISTRATOR(1),
    INSTRUCTOR(2),
    CLIENT(3);

    private int userRoleId;

    UserRole(int userRoleId) {
        this.userRoleId = userRoleId;
    }

    public int getUserRoleId() {
        return userRoleId;
    }
}