package com.buyalskaya.fitclub.model.dao;

import com.buyalskaya.fitclub.exception.DaoException;
import com.buyalskaya.fitclub.model.entity.ClientMembership;
import com.buyalskaya.fitclub.model.entity.Membership;

import java.time.LocalDate;
import java.util.List;

/**
 * The interface Membership dao.
 *
 * @author Buyalskaya Yuliya
 * @version 1.0
 */
public interface MembershipDao extends CommonDao<Membership> {
    /**
     * Find active client memberships list.
     * Is used to find client's membership. If a client doesn't have
     * any membership it will return an empty list
     *
     * @param idClient the id client
     * @return the list
     * @throws DaoException the dao exception
     */
    List<ClientMembership> findActiveClientMemberships(int idClient) throws DaoException;

    /**
     * Find classes amount in membership int.
     * Is used to find classes amount in current membership.
     * If membership isn't found it will return 0.
     *
     * @param idMembership the id membership
     * @return the int
     * @throws DaoException the dao exception
     */
    int findClassesAmountInMembership(int idMembership) throws DaoException;

    /**
     * Find classes amount in client membership int.
     * Is used to find classes amount in current client's membership.
     * If client's membership isn't found it will return 0.
     *
     * @param idClientMembership the id client membership
     * @return the int
     * @throws DaoException the dao exception
     */
    int findClassesAmountInClientMembership(int idClientMembership) throws DaoException;

    /**
     * Find remaining classes in client membership int.
     * Is used to find remaining classes amount in current client's membership.
     * If client's membership isn't found it will return 0.
     *
     * @param idClientMembership the id client membership
     * @return the int
     * @throws DaoException the dao exception
     */
    int findRemainingClassesInClientMembership(int idClientMembership) throws DaoException;

    /**
     * Add client membership boolean.
     * Is used to add a client's membership.
     *
     * @param idMembership  the id membership
     * @param idClient      the id client
     * @param date          the date
     * @param classesAmount the classes amount
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean addClientMembership(int idMembership, int idClient, LocalDate date, int classesAmount) throws DaoException;

    /**
     * Minus workout in membership boolean.
     * Is used to minus a workout from remaining classes in a client's membership.
     *
     * @param idClientMembership the id client membership
     * @param idClient           the id client
     * @param idSchedule         the id schedule
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean minusWorkoutInMembership(int idClientMembership, int idClient, int idSchedule) throws DaoException;

    /**
     * Plus workout in membership boolean.
     * Is used to plus a workout to remaining classes in a client's membership.
     *
     * @param idClientMembership the id client membership
     * @param idClient           the id client
     * @param idSchedule         the id schedule
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean plusWorkoutInMembership(int idClientMembership, int idClient, int idSchedule) throws DaoException;
}