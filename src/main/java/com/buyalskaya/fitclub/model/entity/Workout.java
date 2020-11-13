package com.buyalskaya.fitclub.model.entity;

public class Workout extends CommonEntity {

    public enum Level {
        LOW(1),
        MIDDLE(2),
        HIGH(3);

        private int idLevel;

        Level(int idLevel) {
            this.idLevel = idLevel;
        }

        public int getIdLevel() {
            return idLevel;
        }
    }

    private int idWorkout;
    private String name;
    private Level level;
    private String description;

    public Workout() {
    }

    public Workout(int idWorkout) {
        this.idWorkout = idWorkout;
    }

    public Workout(int idWorkout, String name, Level level, String description) {
        this.idWorkout = idWorkout;
        this.name = name;
        this.level = level;
        this.description = description;
    }

    public int getIdWorkout() {
        return idWorkout;
    }

    public void setIdWorkout(int idWorkout) {
        this.idWorkout = idWorkout;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}