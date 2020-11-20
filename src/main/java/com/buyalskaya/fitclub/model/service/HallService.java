package com.buyalskaya.fitclub.model.service;

import com.buyalskaya.fitclub.exception.ServiceException;

import java.util.Map;

/**
 * The interface Hall service.
 *
 * @author Buyalskaya Yuliya
 * @version 1.0
 */
public interface HallService {
    /**
     * Find name halls map.
     * Is used to find name of all halls. It returns a map with ids and names of halls
     *
     * @return the map
     * @throws ServiceException the service exception
     */
    Map<Integer, String> findNameHalls() throws ServiceException;
}