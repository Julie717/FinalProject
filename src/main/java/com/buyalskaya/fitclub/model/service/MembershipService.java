package com.buyalskaya.fitclub.model.service;

import com.buyalskaya.fitclub.exception.ServiceException;
import com.buyalskaya.fitclub.model.entity.ClientMembership;
import com.buyalskaya.fitclub.model.entity.Membership;

import java.util.List;

public interface MembershipService {
    List<ClientMembership> findActiveClientMemberships(int idClient) throws ServiceException;

    List<ClientMembership> findActiveClientMemberships(String idClient) throws ServiceException;

    List<Membership> findAllMemberships() throws ServiceException;

    boolean addClientMembership(String idMembership, String openDate, String idClient, String locale) throws ServiceException;

    boolean checkClientPresence(String idClient, String idSchedule,
                                String idClientMembership, boolean isPresent) throws ServiceException;
}