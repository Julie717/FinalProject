package com.buyalskaya.fitclub.model.dao;

public enum UserRole {
    ADMIN(1),
    INSTRUCTOR(2),
    CLIENT(3);

    private int idRole;

    UserRole(int idRole) {
        this.idRole = idRole;
    }

    public int getIdRole() {
        return idRole;
    }
}