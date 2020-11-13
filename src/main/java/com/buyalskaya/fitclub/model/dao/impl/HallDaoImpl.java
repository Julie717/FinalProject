package com.buyalskaya.fitclub.model.dao.impl;

import com.buyalskaya.fitclub.exception.DaoException;
import com.buyalskaya.fitclub.model.connection.ConnectionPool;
import com.buyalskaya.fitclub.model.dao.ColumnName;
import com.buyalskaya.fitclub.model.dao.HallDao;
import com.buyalskaya.fitclub.model.dao.SqlQuery;
import com.buyalskaya.fitclub.model.entity.CommonEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HallDaoImpl implements HallDao {
    @Override
    public Map<Integer,String> findNameHalls() throws DaoException {
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        Statement statement = null;
        Map<Integer,String> hallNames= new HashMap<>();
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SqlQuery.SELECT_ALL_HALL_NAMES);
            String nameHall;
            int idHall;
            while (resultSet.next()) {
                nameHall=resultSet.getString(ColumnName.HALLS_NAME_HALL);
                idHall=resultSet.getInt(ColumnName.HALLS_ID);
                hallNames.put(idHall,nameHall);
            }
        } catch (SQLException ex) {
            throw new DaoException("SQL exception (request or table failed)", ex);
        } finally {
            closeStatement(statement);
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
        return hallNames;
    }

    @Override
    public boolean isHallExist(int idHall) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        boolean isExist = false;
        try {
            preparedStatement = connection.prepareStatement(SqlQuery.IS_HALL_EXIST);
            preparedStatement.setInt(1, idHall);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                isExist = resultSet.getInt(ColumnName.EXIST_HALL) > 0;
            }
        } catch (SQLException ex) {
            throw new DaoException("SQL exception (request or table failed)", ex);
        } finally {
            closeStatement(preparedStatement);
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
        return isExist;
    }

    @Override
    public boolean add(CommonEntity hall) throws DaoException {
        return false;
    }

    @Override
    public List findAll() throws DaoException {
        return null;
    }
}
