package com.buyalskaya.fitclub.model.entity;

import java.util.StringJoiner;

/**
 * The type Workout.
 * Is used for store the information about workout
 *
 * @author Buyalskaya Yuliya
 * @version 1.0
 */
public class Workout extends CommonEntity {
    /**
     * The enum Level.
     * Contains all possible workout's levels
     */
    public enum Level {
        /**
         * Low level.
         */
        LOW(1),
        /**
         * Middle level.
         */
        MIDDLE(2),
        /**
         * High level.
         */
        HIGH(3);
        /**
         * The value is used for storage the level's id
         */
        private int idLevel;

        Level(int idLevel) {
            this.idLevel = idLevel;
        }

        /**
         * Gets id level.
         *
         * @return the id level
         */
        public int getIdLevel() {
            return idLevel;
        }
    }

    /**
     * The value is used for storage the workout's id
     */
    private int idWorkout;
    /**
     * The value is used for storage the workout's name
     */
    private String name;
    /**
     * The value is used for storage the workout's level
     */
    private Level level;
    /**
     * The value is used for storage the workout's description
     */
    private String description;

    /**
     * Instantiates a new Workout.
     */
    public Workout() {
    }

    /**
     * Instantiates a new Workout.
     *
     * @param idWorkout the id workout
     */
    public Workout(int idWorkout) {
        this.idWorkout = idWorkout;
    }

    /**
     * Instantiates a new Workout.
     *
     * @param idWorkout   the id workout
     * @param name        the name
     * @param level       the level
     * @param description the description
     */
    public Workout(int idWorkout, String name, Level level, String description) {
        this.idWorkout = idWorkout;
        this.name = name;
        this.level = level;
        this.description = description;
    }

    /**
     * Gets id workout.
     *
     * @return the id workout
     */
    public int getIdWorkout() {
        return idWorkout;
    }

    /**
     * Sets id workout.
     *
     * @param idWorkout the id workout
     */
    public void setIdWorkout(int idWorkout) {
        this.idWorkout = idWorkout;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets level.
     *
     * @return the level
     */
    public Level getLevel() {
        return level;
    }

    /**
     * Sets level.
     *
     * @param level the level
     */
    public void setLevel(Level level) {
        this.level = level;
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
        if (!(o instanceof Workout)) {
            return false;
        }
        Workout workout = (Workout) o;
        if (idWorkout != workout.idWorkout) {
            return false;
        }
        if (name != null ? !name.equals(workout.name) : workout.name != null) {
            return false;
        }
        if (level != workout.level) {
            return false;
        }
        return description != null ? description.equals(workout.description) : workout.description == null;
    }

    @Override
    public int hashCode() {
        int result = idWorkout;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (level != null ? level.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Workout.class.getSimpleName() + "[", "]")
                .add("idWorkout=" + idWorkout)
                .add("name='" + name + "'")
                .add("level=" + level)
                .add("description='" + description + "'")
                .toString();
    }
}