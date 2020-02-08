package ru.itmo.wp.model.repository.impl;

import ru.itmo.wp.model.database.QueryResult;
import ru.itmo.wp.model.exception.RepositoryException;
import ru.itmo.wp.model.repository.CommonRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ru.itmo.wp.model.database.DatabaseUtils.selectQuery;

public abstract class CommonRepositoryImpl<T> implements CommonRepository<T> {

    @Override
    public List<T> findAll(String query, Object... params) {
        List<T> list = new ArrayList<>();
        QueryResult selectResult = selectQuery(query, params);
        T t;
        try {
            while ((t = toClassObject(selectResult.statement.getMetaData(), selectResult.resultSet)) != null) {
                list.add(t);
            }
        } catch (SQLException e) {
            throw new RepositoryException("Wrong sql query", e);
        }
        return list;
    }
}
