package com.buyalskaya.fitclub.model.entity;

import java.util.StringJoiner;

/**
 * The type Client.
 * This is the one of the users that take part in service methods
 * for storage information about clients
 *
 * @author Buyalskaya Yuliya
 * @version 1.0
 */
public class Client extends User {
    /**
     * The value is used for storage the active id membership that belongs to client
     **/
    private int idClientMembership;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Client)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Client client = (Client) o;
        return idClientMembership == client.idClientMembership;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + idClientMembership;
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", this.getClass().getSimpleName() + "[", "]")
                .add(super.toString())
                .add("idClientMembership=" + idClientMembership)
                .toString();
    }
}