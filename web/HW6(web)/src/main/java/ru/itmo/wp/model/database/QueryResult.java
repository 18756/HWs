package ru.itmo.wp.model.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class QueryResult {
    public QueryResult(PreparedStatement statement, ResultSet resultSet) {
        this.statement = statement;
        this.resultSet = resultSet;
    }

    public PreparedStatement statement;
    public ResultSet resultSet;
}