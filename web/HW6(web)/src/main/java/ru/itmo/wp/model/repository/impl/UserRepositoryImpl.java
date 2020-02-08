package ru.itmo.wp.model.repository.impl;

import ru.itmo.wp.model.database.DatabaseUtils;
import ru.itmo.wp.model.database.QueryResult;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.RepositoryException;
import ru.itmo.wp.model.repository.UserRepository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

import static ru.itmo.wp.model.database.DatabaseUtils.selectQuery;
import static ru.itmo.wp.model.database.DatabaseUtils.updateQuery;

public class UserRepositoryImpl extends CommonRepositoryImpl<User> implements UserRepository {
    private final DataSource DATA_SOURCE = DatabaseUtils.getDataSource();

    private User findUserQuery(String query, Object... params) {
        QueryResult SelectResult = selectQuery(query, params);
        try {
            return toClassObject(SelectResult.statement.getMetaData(), SelectResult.resultSet);
        } catch (SQLException e) {
            throw new RepositoryException("Can't find User.", e);
        }
    }

    @Override
    public User find(long id) {
        return findUserQuery("SELECT * FROM User WHERE id=?", id + "");
    }

    @Override
    public User findByLogin(String login) {
        return findUserQuery("SELECT * FROM User WHERE login=?", login);
    }

    @Override
    public User findByLoginAndPasswordSha(String login, String passwordSha) {
        return findUserQuery("SELECT * FROM User WHERE login=? AND passwordSha=?", login, passwordSha);
    }

    @Override
    public List<User> findAll() {
        return findAll("SELECT * FROM User ORDER BY id DESC");
    }

    @Override
    public void save(User user, String passwordSha) {
        updateQuery("INSERT INTO `User` (`login`, `passwordSha`, `creationTime`) VALUES (?, ?, NOW())", user.getLogin(), passwordSha);
    }

    @Override
    public void updateAdmin(long userId, long newValue) {
        updateQuery("UPDATE User SET admin=? WHERE id=?", newValue, userId);
    }

    @Override
    public User toClassObject(ResultSetMetaData metaData, ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) {
            return null;
        }

        User user = new User();
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            switch (metaData.getColumnName(i)) {
                case "id":
                    user.setId(resultSet.getLong(i));
                    break;
                case "login":
                    user.setLogin(resultSet.getString(i));
                    break;
                case "admin":
                    user.setAdmin(resultSet.getBoolean(i));
                    break;
                case "creationTime":
                    user.setCreationTime(resultSet.getTimestamp(i));
                    break;
                default:
                    // No operations.
            }
        }

        return user;
    }
}
