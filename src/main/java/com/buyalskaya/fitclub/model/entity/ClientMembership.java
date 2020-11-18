package com.buyalskaya.fitclub.model.entity;

import java.time.LocalDate;
import java.util.StringJoiner;

/**
 * The type Client membership.
 * This is the membership that belongs to a client
 */
public class ClientMembership extends Membership {
    /**
     * The value is used for storage id client's membership
     **/
    private int idClientMembership;
    /**
     * The value is used for storage the amount of classes
     * that client can visited using this membership
     **/
    private int remainingClasses;
    /**
     * The value is used for storage date from which this membership starts available
     **/
    private LocalDate openDate;
    /**
     * The value is used for storage date to those this membership available
     **/
    private LocalDate closeDate;

    /**
     * Gets id client membership.
     *
     * @return the id client membership
     */
    public int getIdClientMembership() {
        return idClientMembership;
    }

    /**
     * Sets id client membership.
     *
     * @param idClientMembership the id client membership
     */
    public void setIdClientMembership(int idClientMembership) {
        this.idClientMembership = idClientMembership;
    }

    /**
     * Gets remaining classes.
     *
     * @return the remaining classes
     */
    public int getRemainingClasses() {
        return remainingClasses;
    }

    /**
     * Sets remaining classes.
     *
     * @param remainingClasses the remaining classes
     */
    public void setRemainingClasses(int remainingClasses) {
        this.remainingClasses = remainingClasses;
    }

    /**
     * Gets open date.
     *
     * @return the open date
     */
    public LocalDate getOpenDate() {
        return openDate;
    }

    /**
     * Sets open date.
     *
     * @param openDate the open date
     */
    public void setOpenDate(LocalDate openDate) {
        this.openDate = openDate;
    }

    /**
     * Gets close date.
     *
     * @return the close date
     */
    public LocalDate getCloseDate() {
        return closeDate;
    }

    /**
     * Sets close date.
     *
     * @param closeDate the close date
     */
    public void setCloseDate(LocalDate closeDate) {
        this.closeDate = closeDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClientMembership)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        ClientMembership that = (ClientMembership) o;
        if (idClientMembership != that.idClientMembership) {
            return false;
        }
        if (remainingClasses != that.remainingClasses) {
            return false;
        }
        if (openDate != null ? !openDate.equals(that.openDate) : that.openDate != null) {
            return false;
        }
        return closeDate != null ? closeDate.equals(that.closeDate) : that.closeDate == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + idClientMembership;
        result = 31 * result + remainingClasses;
        result = 31 * result + (openDate != null ? openDate.hashCode() : 0);
        result = 31 * result + (closeDate != null ? closeDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", this.getClass().getSimpleName() + "[", "]")
                .add(super.toString())
                .add("idClientMembership=" + idClientMembership)
                .add("remainingClasses=" + remainingClasses)
                .add("openDate=" + openDate)
                .add("closeDate=" + closeDate)
                .toString();
    }
}