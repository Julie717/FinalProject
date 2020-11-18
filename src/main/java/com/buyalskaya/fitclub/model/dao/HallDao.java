package com.buyalskaya.fitclub.model.dao;

import com.buyalskaya.fitclub.exception.DaoException;

import java.util.Map;

/**
 * The interface Hall dao.
 *
 * @author Buyalskaya Yuliya
 * @version 1.0
 */
public interface HallDao extends CommonDao {
    /**
     * Find name halls map.
     * Is used to find hall's names and return the map with hall's ids and hall's names
     *
     * @return the map
     * @throws DaoException the dao exception
     */
    Map<Integer,String> findNameHalls() throws DaoException;

    /**
     * Is hall exist boolean.
     * Is used to check is a correct id hall was entered. If hall with current
     * id exists it will return true else - false
     *
     * @param idHall the id hall
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean isHallExist(int idHall) throws DaoException;
}