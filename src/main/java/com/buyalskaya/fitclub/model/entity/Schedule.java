package com.buyalskaya.fitclub.model.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.StringJoiner;

/**
 * The type Schedule.
 * that stores the information about one workout in schedule
 *
 * @author Buyalskaya Yuliya
 * @version 1.0
 */
public class Schedule extends CommonEntity {
    /**
     * The value is used for storage the schedule's id
     **/
    private int idSchedule;
    /**
     * The value is used for storage the workout parameters
     **/
    private Workout workout;
    /**
     * The value is used for storage the instructor's id  that conducts the workout
     **/
    private int idInstructor;
    /**
     * The value is used for storage the instructor's name that conducts the workout
     **/
    private String nameInstructor;
    /**
     * The value is used for storage the instructor's surname that conducts the workout
     **/
    private String surnameInstructor;
    /**
     * The value is used for storage the date when the workout starts
     **/
    private LocalDate startDate;
    /**
     * The value is used for storage the time when the workout starts
     **/
    private LocalTime startTime;
    /**
     * The value is used for storage amount of places for the workout
     **/
    private int capacity;
    /**
     * The value is used for storage free places for the workout
     **/
    private int freeCapacity;
    /**
     * The value is used for storage the hall's id
     **/
    private int idHall;
    /**
     * The value is used for storage the hall's name
     **/
    private String nameHall;

    /**
     * The value is used for storage the workout's duration
     **/
    private int duration;

    /**
     * Gets id schedule.
     *
     * @return the id schedule
     */
    public int getIdSchedule() {
        return idSchedule;
    }

    /**
     * Sets id schedule.
     *
     * @param idSchedule the id schedule
     */
    public void setIdSchedule(int idSchedule) {
        this.idSchedule = idSchedule;
    }

    /**
     * Gets workout.
     *
     * @return the workout
     */
    public Workout getWorkout() {
        return workout;
    }

    /**
     * Sets workout.
     *
     * @param workout the workout
     */
    public void setWorkout(Workout workout) {
        this.workout = workout;
    }

    /**
     * Gets id instructor.
     *
     * @return the id instructor
     */
    public int getIdInstructor() {
        return idInstructor;
    }

    /**
     * Sets id instructor.
     *
     * @param idInstructor the id instructor
     */
    public void setIdInstructor(int idInstructor) {
        this.idInstructor = idInstructor;
    }

    /**
     * Gets name instructor.
     *
     * @return the name instructor
     */
    public String getNameInstructor() {
        return nameInstructor;
    }

    /**
     * Sets name instructor.
     *
     * @param nameInstructor the name instructor
     */
    public void setNameInstructor(String nameInstructor) {
        this.nameInstructor = nameInstructor;
    }

    /**
     * Gets surname instructor.
     *
     * @return the surname instructor
     */
    public String getSurnameInstructor() {
        return surnameInstructor;
    }

    /**
     * Sets surname instructor.
     *
     * @param surnameInstructor the surname instructor
     */
    public void setSurnameInstructor(String surnameInstructor) {
        this.surnameInstructor = surnameInstructor;
    }

    /**
     * Gets start date.
     *
     * @return the start date
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Sets start date.
     *
     * @param startDate the start date
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets start time.
     *
     * @return the start time
     */
    public LocalTime getStartTime() {
        return startTime;
    }

    /**
     * Sets start time.
     *
     * @param startTime the start time
     */
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    /**
     * Gets capacity.
     *
     * @return the capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Sets capacity.
     *
     * @param capacity the capacity
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Gets free capacity.
     *
     * @return the free capacity
     */
    public int getFreeCapacity() {
        return freeCapacity;
    }

    /**
     * Sets free capacity.
     *
     * @param freeCapacity the free capacity
     */
    public void setFreeCapacity(int freeCapacity) {
        this.freeCapacity = freeCapacity;
    }

    /**
     * Gets id hall.
     *
     * @return the id hall
     */
    public int getIdHall() {
        return idHall;
    }

    /**
     * Sets id hall.
     *
     * @param idHall the id hall
     */
    public void setIdHall(int idHall) {
        this.idHall = idHall;
    }

    /**
     * Gets name hall.
     *
     * @return the name hall
     */
    public String getNameHall() {
        return nameHall;
    }

    /**
     * Sets name hall.
     *
     * @param nameHall the name hall
     */
    public void setNameHall(String nameHall) {
        this.nameHall = nameHall;
    }

    /**
     * Gets duration.
     *
     * @return the duration
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Sets duration.
     *
     * @param duration the duration
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Schedule)) {
            return false;
        }
        Schedule schedule = (Schedule) o;
        if (idSchedule != schedule.idSchedule) {
            return false;
        }
        if (idInstructor != schedule.idInstructor) {
            return false;
        }
        if (capacity != schedule.capacity) {
            return false;
        }
        if (freeCapacity != schedule.freeCapacity) {
            return false;
        }
        if (idHall != schedule.idHall) {
            return false;
        }
        if (duration != schedule.duration) {
            return false;
        }
        if (workout != null ? !workout.equals(schedule.workout) : schedule.workout != null) {
            return false;
        }
        if (nameInstructor != null ? !nameInstructor.equals(schedule.nameInstructor) :
                schedule.nameInstructor != null) {
            return false;
        }
        if (surnameInstructor != null ? !surnameInstructor.equals(schedule.surnameInstructor) :
                schedule.surnameInstructor != null) {
            return false;
        }
        if (startDate != null ? !startDate.equals(schedule.startDate) : schedule.startDate != null) {
            return false;
        }
        if (startTime != null ? !startTime.equals(schedule.startTime) : schedule.startTime != null) {
            return false;
        }
        return nameHall != null ? nameHall.equals(schedule.nameHall) : schedule.nameHall == null;
    }

    @Override
    public int hashCode() {
        int result = idSchedule;
        result = 31 * result + (workout != null ? workout.hashCode() : 0);
        result = 31 * result + idInstructor;
        result = 31 * result + (nameInstructor != null ? nameInstructor.hashCode() : 0);
        result = 31 * result + (surnameInstructor != null ? surnameInstructor.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + capacity;
        result = 31 * result + freeCapacity;
        result = 31 * result + idHall;
        result = 31 * result + (nameHall != null ? nameHall.hashCode() : 0);
        result = 31 * result + duration;
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", this.getClass().getSimpleName() + "[", "]")
                .add("idSchedule=" + idSchedule)
                .add("workout=" + workout)
                .add("idInstructor=" + idInstructor)
                .add("nameInstructor='" + nameInstructor + "'")
                .add("surnameInstructor='" + surnameInstructor + "'")
                .add("startDate=" + startDate)
                .add("startTime=" + startTime)
                .add("capacity=" + capacity)
                .add("freeCapacity=" + freeCapacity)
                .add("idHall=" + idHall)
                .add("nameHall='" + nameHall + "'")
                .add("duration=" + duration)
                .toString();
    }
}