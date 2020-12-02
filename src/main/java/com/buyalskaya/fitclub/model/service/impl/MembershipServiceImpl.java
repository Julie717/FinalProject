package com.buyalskaya.fitclub.model.service.impl;

import com.buyalskaya.fitclub.exception.DaoException;
import com.buyalskaya.fitclub.exception.ServiceException;
import com.buyalskaya.fitclub.model.dao.DaoFactory;
import com.buyalskaya.fitclub.model.dao.MembershipDao;
import com.buyalskaya.fitclub.model.entity.ClientMembership;
import com.buyalskaya.fitclub.model.entity.Membership;
import com.buyalskaya.fitclub.model.service.MembershipService;
import com.buyalskaya.fitclub.util.DateTimeTransformer;
import com.buyalskaya.fitclub.validator.CommonValidator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MembershipServiceImpl implements MembershipService {
    @Override
    public List<ClientMembership> findActiveClientMemberships(int idClient) throws ServiceException {
        List<ClientMembership> memberships;
        try {
            MembershipDao membershipDao = DaoFactory.getInstance().getMembershipDao();
            memberships = membershipDao.findActiveClientMemberships(idClient);
        } catch (DaoException ex) {
            throw new ServiceException("Error during find client memberships", ex);
        }
        return memberships;
    }

    @Override
    public List<ClientMembership> findActiveClientMemberships(String idClient) throws ServiceException {
        List<ClientMembership> memberships = new ArrayList<>();
        if (CommonValidator.isIdValid(idClient)) {
            int idClientInt = Integer.parseInt(idClient);
            try {
                MembershipDao membershipDao = DaoFactory.getInstance().getMembershipDao();
                memberships = membershipDao.findActiveClientMemberships(idClientInt);
            } catch (DaoException ex) {
                throw new ServiceException("Error during find client memberships", ex);
            }
        }
        return memberships;
    }

    @Override
    public List<Membership> findAllMemberships() throws ServiceException {
        List<Membership> memberships;
        try {
            MembershipDao membershipDao = DaoFactory.getInstance().getMembershipDao();
            memberships = membershipDao.findAll();
        } catch (DaoException ex) {
            throw new ServiceException("Error during find memberships", ex);
        }
        return memberships;
    }

    @Override
    public boolean addClientMembership(String idMembership, String openDate, String idClient, String locale) throws ServiceException {
        boolean isAdded = false;
        if (CommonValidator.isIdValid(idMembership) &&
                CommonValidator.isDateValid(openDate, locale) &&
                CommonValidator.isIdValid(idClient)) {
            int idMembershipInt = Integer.parseInt(idMembership);
            LocalDate date = DateTimeTransformer.fromStringToLocalDate(openDate, locale);
            int idClientInt = Integer.parseInt(idClient);
            try {
                MembershipDao membershipDao = DaoFactory.getInstance().getMembershipDao();
                int classesAmount = membershipDao
                        .findClassesAmountInMembership(idMembershipInt);
                isAdded = membershipDao.addClientMembership(idMembershipInt, idClientInt, date, classesAmount);
            } catch (DaoException ex) {
                throw new ServiceException("Error during add client membership", ex);
            }
        }
        return isAdded;
    }

    @Override
    public boolean checkClientPresence(String idClient, String idSchedule,
                                       String idClientMembership, boolean isPresent) throws ServiceException {
        boolean isDone = false;
        if (CommonValidator.isIdValid(idClient) &&
                CommonValidator.isIdValid(idSchedule) &&
                CommonValidator.isIdValid(idClientMembership)) {
            int idClientInt = Integer.parseInt(idClient);
            int idScheduleInt = Integer.parseInt(idSchedule);
            int idClientMembershipInt = Integer.parseInt(idClientMembership);
            try {
                MembershipDao membershipDao = DaoFactory.getInstance().getMembershipDao();
                int classesAmount = membershipDao.findClassesAmountInClientMembership(idClientMembershipInt);
                int remainingClasses = membershipDao.findRemainingClassesInClientMembership(idClientMembershipInt);
                if (isPresent) {
                    if (remainingClasses > 0) {
                        isDone = membershipDao
                                .minusWorkoutInMembership(idClientMembershipInt, idClientInt, idScheduleInt);
                    }
                } else {
                    if (remainingClasses < classesAmount) {
                        isDone = membershipDao
                                .plusWorkoutInMembership(idClientMembershipInt, idClientInt, idScheduleInt);
                    }
                }
            } catch (DaoException ex) {
                throw new ServiceException("Error during check client presence", ex);
            }
        }
        return isDone;
    }
}