package com.buyalskaya.fitclub.model.dao;

import com.buyalskaya.fitclub.exception.DaoException;

import java.util.Map;

public interface HallDao extends CommonDao {
    Map<Integer,String> findNameHalls() throws DaoException;
    boolean isHallExist(int idHall) throws DaoException;
}