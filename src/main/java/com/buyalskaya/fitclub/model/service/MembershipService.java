package com.buyalskaya.fitclub.model.service;

import com.buyalskaya.fitclub.exception.ServiceException;
import com.buyalskaya.fitclub.model.entity.ClientMembership;
import com.buyalskaya.fitclub.model.entity.Membership;

import java.util.List;

/**
 * The interface Membership service.
 *
 * @author Buyalskaya Yuliya
 * @version 1.0
 */
public interface MembershipService {
    /**
     * Find active client memberships list.
     * Is used to find all active client's memberships by client's id with type of input parameter int.
     * If memberships aren't found it returns an empty list.
     *
     * @param idClient the id client
     * @return the list
     * @throws ServiceException the service exception
     */
    List<ClientMembership> findActiveClientMemberships(int idClient) throws ServiceException;

    /**
     * Find active client memberships list.
     * Is used to find all active client's memberships by client's id with type of input parameter String.
     * If memberships aren't found it returns an empty list.
     *
     * @param idClient the id client
     * @return the list
     * @throws ServiceException the service exception
     */
    List<ClientMembership> findActiveClientMemberships(String idClient) throws ServiceException;

    /**
     * Find all memberships list.
     * Is used to find all active memberships that fitness club sells
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Membership> findAllMemberships() throws ServiceException;

    /**
     * Add client membership boolean.
     * Is used to sell memberships for clients
     *
     * @param idMembership the id membership
     * @param openDate     the open date
     * @param idClient     the id client
     * @param locale       the locale
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean addClientMembership(String idMembership, String openDate, String idClient,
                                String locale) throws ServiceException;

    /**
     * Check client presence boolean.
     * Is used to check client's presence. If client is present it subtract
     * a workout from a client's membership, and, vise-versa, if client is absent
     * it add a workout to client membership.
     * The result true means that operation was passed correctly
     *
     * @param idClient           the id client
     * @param idSchedule         the id schedule
     * @param idClientMembership the id client membership
     * @param isPresent          the is present
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean checkClientPresence(String idClient, String idSchedule,
                                String idClientMembership, boolean isPresent) throws ServiceException;
}