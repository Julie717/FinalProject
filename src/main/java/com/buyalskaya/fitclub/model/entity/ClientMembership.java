package com.buyalskaya.fitclub.model.entity;

import java.time.LocalDate;

public class ClientMembership extends Membership {
    private int idClientMembership;
    private int remainingClasses;
    private LocalDate openDate;
    private LocalDate closeDate;

    public int getIdClientMembership() {
        return idClientMembership;
    }

    public void setIdClientMembership(int idClientMembership) {
        this.idClientMembership = idClientMembership;
    }

    public int getRemainingClasses() {
        return remainingClasses;
    }

    public void setRemainingClasses(int remainingClasses) {
        this.remainingClasses = remainingClasses;
    }

    public LocalDate getOpenDate() {
        return openDate;
    }

    public void setOpenDate(LocalDate openDate) {
        this.openDate = openDate;
    }

    public LocalDate getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(LocalDate closeDate) {
        this.closeDate = closeDate;
    }
}