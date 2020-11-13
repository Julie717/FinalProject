package com.buyalskaya.fitclub.model.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.StringJoiner;

public class Schedule extends CommonEntity {
    private int idSchedule;
    private Workout workout;
    private int idInstructor;
    private  String nameInstructor;
    private String surnameInstructor;
    private LocalDate startDate;
    private LocalTime startTime;
    private int capacity;
    private int freeCapacity;
    private int idHall;
    private String nameHall;
    private boolean subscribed;
    private int duration;

    public int getIdSchedule() {
        return idSchedule;
    }

    public void setIdSchedule(int idSchedule) {
        this.idSchedule = idSchedule;
    }

    public Workout getWorkout() {
        return workout;
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }

    public int getIdInstructor() {
        return idInstructor;
    }

    public void setIdInstructor(int idInstructor) {
        this.idInstructor = idInstructor;
    }

    public String getNameInstructor() {
        return nameInstructor;
    }

    public void setNameInstructor(String nameInstructor) {
        this.nameInstructor = nameInstructor;
    }

    public String getSurnameInstructor() {
        return surnameInstructor;
    }

    public void setSurnameInstructor(String surnameInstructor) {
        this.surnameInstructor = surnameInstructor;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getFreeCapacity() {
        return freeCapacity;
    }

    public void setFreeCapacity(int freeCapacity) {
        this.freeCapacity = freeCapacity;
    }

    public int getIdHall() {
        return idHall;
    }

    public void setIdHall(int idHall) {
        this.idHall = idHall;
    }

    public String getNameHall() {
        return nameHall;
    }

    public void setNameHall(String nameHall) {
        this.nameHall = nameHall;
    }

    public boolean isSubscribed() {
        return subscribed;
    }

    public void setSubscribed(boolean subscribed) {
        this.subscribed = subscribed;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Schedule.class.getSimpleName() + "[", "]")
                .add("idSchedule=" + idSchedule)
                .add("workout=" + workout)
                .add("idInstructor=" + idInstructor)
                .add("nameInstructor='" + nameInstructor + "'")
                .add("surnameInstructor='" + surnameInstructor + "'")
                .add("startDate=" + startDate)
                .add("startTime=" + startTime)
                .add("capacity=" + capacity)
                .add("freeCapacity=" + freeCapacity)
                .add("nameHall='" + nameHall + "'")
                .add("subscribed=" + subscribed)
                .toString();
    }
}