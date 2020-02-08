package ru.itmo.wp.model.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

public interface CommonRepository<T> {
    public List<T> findAll(String query, String... params);
    public T toClassObject(ResultSetMetaData metaData, ResultSet resultSet) throws SQLException;
}
