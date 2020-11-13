package com.buyalskaya.fitclub.model.dao;

import com.buyalskaya.fitclub.exception.DaoException;
import com.buyalskaya.fitclub.model.entity.ClientMembership;
import com.buyalskaya.fitclub.model.entity.Membership;

import java.time.LocalDate;
import java.util.List;

public interface MembershipDao extends CommonDao<Membership> {
    List<ClientMembership> findActiveClientMemberships(int idClient) throws DaoException;

    int findClassesAmountInMembership(int idMembership) throws DaoException;

    int findClassesAmountInClientMembership(int idClientMembership) throws DaoException;

    int findRemainingClassesInClientMembership(int idClientMembership) throws DaoException;

    boolean addClientMembership(int idMembership, int idClient, LocalDate date, int classesAmount) throws DaoException;

    boolean minusWorkoutInMembership(int idClientMembership, int idClient, int idSchedule) throws DaoException;

    boolean plusWorkoutInMembership(int idClientMembership, int idClient, int idSchedule) throws DaoException;
}