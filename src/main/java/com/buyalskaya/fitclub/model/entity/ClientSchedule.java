package com.buyalskaya.fitclub.model.entity;

import java.util.StringJoiner;

/**
 * The type Client schedule.
 * Stored the information about workout that client is planning to visit
 *
 * @author Buyalskaya Yuliya
 * @version 1.0
 */
public class ClientSchedule extends Schedule {
    /**
     * The value is used for storage subscribed user for the workout or not.
     **/
    private boolean subscribed;

    /**
     * Is subscribed boolean.
     *
     * @return the boolean
     */
    public boolean isSubscribed() {
        return subscribed;
    }

    /**
     * Sets subscribed.
     *
     * @param subscribed the subscribed
     */
    public void setSubscribed(boolean subscribed) {
        this.subscribed = subscribed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ClientSchedule)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        ClientSchedule that = (ClientSchedule) o;
        return subscribed == that.subscribed;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (subscribed ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", this.getClass().getSimpleName() + "[", "]")
                .add(super.toString())
                .add("subscribed=" + subscribed)
                .toString();
    }
}