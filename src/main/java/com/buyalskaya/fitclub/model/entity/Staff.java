package com.buyalskaya.fitclub.model.entity;

import java.time.LocalDate;

public class Staff extends User {
    private int workExperience;
    private LocalDate startWorkingDate;
    private LocalDate endWorkingDate;
    private String description;

    public Staff() {
    }

    public Staff(int idUser, UserRole role, UserStatus status) {
        super(idUser, role, status);
    }

    public Staff(int idUser, UserRole role, UserStatus status,
                 LocalDate startWorkingDate, LocalDate endWorkingDate) {
        super(idUser, role, status);
        this.startWorkingDate = startWorkingDate;
        this.endWorkingDate = endWorkingDate;
    }

    public int getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(int workExperience) {
        this.workExperience = workExperience;
    }

    public LocalDate getStartWorkingDate() {
        return startWorkingDate;
    }

    public void setStartWorkingDate(LocalDate startWorkingDate) {
        this.startWorkingDate = startWorkingDate;
    }

    public LocalDate getEndWorkingDate() {
        return endWorkingDate;
    }

    public void setEndWorkingDate(LocalDate endWorkingDate) {
        this.endWorkingDate = endWorkingDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj == null) || (getClass() != obj.getClass())) {
            return false;
        }
        Staff staff = (Staff) obj;
        return (workExperience == staff.workExperience) &&
                ((startWorkingDate != null) ? !startWorkingDate.equals(staff.startWorkingDate) :
                        staff.startWorkingDate != null) &&
                ((endWorkingDate != null) ? !endWorkingDate.equals(staff.endWorkingDate) :
                        staff.endWorkingDate != null) &&
                ((description != null) ? !description.equals(staff.description) : staff.description != null);
    }

    @Override
    public int hashCode() {
        return 31 * workExperience + startWorkingDate.hashCode()
                + endWorkingDate.hashCode() + description.hashCode();
    }
}