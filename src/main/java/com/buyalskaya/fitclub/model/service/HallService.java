package com.buyalskaya.fitclub.model.service;

import com.buyalskaya.fitclub.exception.ServiceException;

import java.util.List;
import java.util.Map;

public interface HallService {
    Map<Integer, String> findNameHalls() throws ServiceException;
}