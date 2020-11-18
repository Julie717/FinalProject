package com.buyalskaya.fitclub.model.entity;

import java.time.LocalDate;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * The type Staff.
 * This is the one of the users that take part in service methods
 * for storage information about fitness-club's staffs (instructors and administrators)
 *
 * @author Buyalskaya Yuliya
 * @version 1.0
 */
public class Staff extends User {
    /**
     * The value is used for storage the work experience
     **/
    private int workExperience;
    /**
     * The value is used for storage the start date of work
     **/
    private LocalDate startWorkingDate;
    /**
     * The value is used for storage the end day of work if the staff quit
     **/
    private LocalDate endWorkingDate;
    /**
     * The value is used for storage the staff's description
     **/
    private String description;

    /**
     * Instantiates a new Staff.
     */
    public Staff() {
    }

    /**
     * Instantiates a new Staff.
     *
     * @param idUser the id user
     * @param role   the role
     * @param status the status
     */
    public Staff(int idUser, UserRole role, UserStatus status) {
        super(idUser, role, status);
    }

    /**
     * Instantiates a new Staff.
     *
     * @param idUser           the id user
     * @param role             the role
     * @param status           the status
     * @param startWorkingDate the start working date
     * @param endWorkingDate   the end working date
     */
    public Staff(int idUser, UserRole role, UserStatus status,
                 LocalDate startWorkingDate, LocalDate endWorkingDate) {
        super(idUser, role, status);
        this.startWorkingDate = startWorkingDate;
        this.endWorkingDate = endWorkingDate;
    }

    /**
     * Gets work experience.
     *
     * @return the work experience
     */
    public int getWorkExperience() {
        return workExperience;
    }

    /**
     * Sets work experience.
     *
     * @param workExperience the work experience
     */
    public void setWorkExperience(int workExperience) {
        this.workExperience = workExperience;
    }

    /**
     * Gets start working date.
     *
     * @return the start working date
     */
    public LocalDate getStartWorkingDate() {
        return startWorkingDate;
    }

    /**
     * Sets start working date.
     *
     * @param startWorkingDate the start working date
     */
    public void setStartWorkingDate(LocalDate startWorkingDate) {
        this.startWorkingDate = startWorkingDate;
    }

    /**
     * Gets end working date.
     *
     * @return the end working date
     */
    public LocalDate getEndWorkingDate() {
        return endWorkingDate;
    }

    /**
     * Sets end working date.
     *
     * @param endWorkingDate the end working date
     */
    public void setEndWorkingDate(LocalDate endWorkingDate) {
        this.endWorkingDate = endWorkingDate;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Staff)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Staff staff = (Staff) o;
        if (startWorkingDate != null ? !startWorkingDate.equals(staff.startWorkingDate) :
                staff.startWorkingDate != null) {
            return false;
        }
        if (endWorkingDate != null ? !endWorkingDate.equals(staff.endWorkingDate) :
                staff.endWorkingDate != null) {
            return false;
        }
        if (description != null ? !description.equals(staff.description) : staff.description != null) {
            return false;
        }
        return workExperience == staff.workExperience;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + workExperience;
        result = 31 * result + (startWorkingDate != null ? startWorkingDate.hashCode() : 0);
        result = 31 * result + (endWorkingDate != null ? endWorkingDate.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", this.getClass().getSimpleName() + "[", "]")
                .add(super.toString())
                .add("workExperience=" + workExperience)
                .add("startWorkingDate=" + startWorkingDate)
                .add("endWorkingDate=" + endWorkingDate)
                .add("description='" + description + "'")
                .toString();
    }
}