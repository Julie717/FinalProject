package com.buyalskaya.fitclub.model.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.StringJoiner;

/**
 * The type Membership.
 * This is the membership that fitness-club propose the clients
 *
 * @author Buyalskaya Yuliya
 * @version 1.0
 */
public class Membership extends CommonEntity {
    /**
     * The value is used for storage the membership's id
     **/
    private int idMembership;
    /**
     * The value is used for storage the period of time that membership is available
     **/
    private int duration;
    /**
     * The value is used for storage the amount of
     * classes that client can visit using this membership
     **/
    private int classesAmount;
    /**
     * The value is used for storage the membership's price
     **/
    private BigDecimal price;
    /**
     * The value is used for storage the first date from which
     * administrators can sale this membership
     **/
    private LocalDate startSale;
    /**
     * The value is used for storage the last date to those
     * administrators can sale this membership
     **/
    private LocalDate endSale;
    /**
     * The value is used for storage type of workouts like groups, individual and so on
     **/
    private String typeByNumberPeople;
    /**
     * The value is used for storage type of workouts by period of time
     * (only morning, morning and day,evening and so on)
     **/
    private String periodTime;

    /**
     * Gets id membership.
     *
     * @return the id membership
     */
    public int getIdMembership() {
        return idMembership;
    }

    /**
     * Sets id membership.
     *
     * @param idMembership the id membership
     */
    public void setIdMembership(int idMembership) {
        this.idMembership = idMembership;
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

    /**
     * Gets classes amount.
     *
     * @return the classes amount
     */
    public int getClassesAmount() {
        return classesAmount;
    }

    /**
     * Sets classes amount.
     *
     * @param classesAmount the classes amount
     */
    public void setClassesAmount(int classesAmount) {
        this.classesAmount = classesAmount;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Sets price.
     *
     * @param price the price
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Gets start sale.
     *
     * @return the start sale
     */
    public LocalDate getStartSale() {
        return startSale;
    }

    /**
     * Sets start sale.
     *
     * @param startSale the start sale
     */
    public void setStartSale(LocalDate startSale) {
        this.startSale = startSale;
    }

    /**
     * Gets end sale.
     *
     * @return the end sale
     */
    public LocalDate getEndSale() {
        return endSale;
    }

    /**
     * Sets end sale.
     *
     * @param endSale the end sale
     */
    public void setEndSale(LocalDate endSale) {
        this.endSale = endSale;
    }

    /**
     * Gets type by number people.
     *
     * @return the type by number people
     */
    public String getTypeByNumberPeople() {
        return typeByNumberPeople;
    }

    /**
     * Sets type by number people.
     *
     * @param typeByNumberPeople the type by number people
     */
    public void setTypeByNumberPeople(String typeByNumberPeople) {
        this.typeByNumberPeople = typeByNumberPeople;
    }

    /**
     * Gets period time.
     *
     * @return the period time
     */
    public String getPeriodTime() {
        return periodTime;
    }

    /**
     * Sets period time.
     *
     * @param periodTime the period time
     */
    public void setPeriodTime(String periodTime) {
        this.periodTime = periodTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Membership)) return false;
        Membership that = (Membership) o;
        if (idMembership != that.idMembership) {
            return false;
        }
        if (duration != that.duration) {
            return false;
        }
        if (classesAmount != that.classesAmount) {
            return false;
        }
        if (price != null ? !price.equals(that.price) : that.price != null) {
            return false;
        }
        if (startSale != null ? !startSale.equals(that.startSale) : that.startSale != null) {
            return false;
        }
        if (endSale != null ? !endSale.equals(that.endSale) : that.endSale != null) {
            return false;
        }
        if (typeByNumberPeople != null ? !typeByNumberPeople.equals(that.typeByNumberPeople) :
                that.typeByNumberPeople != null) {
            return false;
        }
        return periodTime != null ? periodTime.equals(that.periodTime) : that.periodTime == null;
    }

    @Override
    public int hashCode() {
        int result = idMembership;
        result = 31 * result + duration;
        result = 31 * result + classesAmount;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (startSale != null ? startSale.hashCode() : 0);
        result = 31 * result + (endSale != null ? endSale.hashCode() : 0);
        result = 31 * result + (typeByNumberPeople != null ? typeByNumberPeople.hashCode() : 0);
        result = 31 * result + (periodTime != null ? periodTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", this.getClass().getSimpleName() + "[", "]")
                .add("idMembership=" + idMembership)
                .add("duration=" + duration)
                .add("classesAmount=" + classesAmount)
                .add("price=" + price)
                .add("startSale=" + startSale)
                .add("endSale=" + endSale)
                .add("typeByNumberPeople='" + typeByNumberPeople + "'")
                .add("periodTime='" + periodTime + "'")
                .toString();
    }
}