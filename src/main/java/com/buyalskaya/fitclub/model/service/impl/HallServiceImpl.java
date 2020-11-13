package com.buyalskaya.fitclub.model.service.impl;

import com.buyalskaya.fitclub.exception.DaoException;
import com.buyalskaya.fitclub.exception.ServiceException;
import com.buyalskaya.fitclub.model.dao.DaoFactory;
import com.buyalskaya.fitclub.model.dao.HallDao;
import com.buyalskaya.fitclub.model.service.HallService;

import java.util.Map;

public class HallServiceImpl implements HallService {
    @Override
    public Map<Integer, String> findNameHalls() throws ServiceException {
        Map<Integer, String> halls;
        try {
            HallDao hallDao = DaoFactory.getInstance().getHallDao();
            halls = hallDao.findNameHalls();
        } catch (DaoException ex) {
            throw new ServiceException("Error during find hall names", ex);
        }
        return halls;
    }
}